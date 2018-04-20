package com.mvc.boot.config.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeguo on 2018/3/18.
 */

//@EnableTransactionManagement
@Order(0)
@Configuration
@MapperScan(basePackages="com.mvc.boot.dao")
public class MybatisConfiguration {
    private static Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);
    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = DbTypes.M_STU)
    @ConfigurationProperties(prefix = "druid.study.master")
    public DataSource studyMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = DbTypes.S_STU)
    @ConfigurationProperties(prefix = "druid.study.slave")
    public DataSource studySlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = DbTypes.M_PDU)
    @ConfigurationProperties(prefix = "druid.product.master")
    public DataSource productMasterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = DbTypes.S_PDU)
    @ConfigurationProperties(prefix = "druid.product.slave")
    public DataSource productSlaveDataSource1() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource() {
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource(){
            @Override
            protected Object determineCurrentLookupKey() {
                String dataSourceName = DataSourceContextHolder.getLoopKey();
                System.out.println("使用数据库"+dataSourceName+".............");
                return dataSourceName;
            }
        };
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DbTypes.M_PDU, productMasterDataSource());
        targetDataResources.put(DbTypes.S_PDU, productSlaveDataSource1());
        targetDataResources.put(DbTypes.M_STU, studyMasterDataSource());
        targetDataResources.put(DbTypes.S_STU, studySlaveDataSource1());
        proxy.setDefaultTargetDataSource(productMasterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    @Value("${druid.datasource.mapperLocations}")
    private String mapperLocations;
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        log.info("--------------------  sqlSessionFactory init ---------------------");
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource());
//            // 读取配置
//            sessionFactoryBean.setTypeAliasesPackage("com.fei.springboot.domain");
            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);

//            //添加分页插件、打印sql插件
//            Interceptor[] plugins = new Interceptor[]{pageHelper(),new SqlPrintInterceptor()};
//            sessionFactoryBean.setPlugins(plugins);

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            log.error("mybatis resolver mapper*xml is error",e);
            return null;
        } catch (Exception e) {
            log.error("mybatis sqlSessionFactoryBean create error",e);
            return null;
        }
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate()throws Exception  {
        return new SqlSessionTemplate(sqlSessionFactorys());
    }
}
