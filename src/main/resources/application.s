debug: true
# mybatis xml文件位置
mapperlocationusedcar: "classpath:/mybatis//*.xml"
mapperlocationusedcarlog: "classpath:/mybatis//*.xml"
# 静态文件过滤，防止被拦截器拦截
staticspattern: "/**/*.js,/**/*.gif,/**/*.jpg,/**/*.bmp,/**/*.png,/**/*.css,/**/*.ico"
server:
  port: 8080
  tomcat:
    accept-count: 3000
    uri-encoding: UTF-8
    min-spare-threads: 200
    max-threads: 2000
  ssl:
    enabled: false
spring:
  profiles:
    active: dev
  datasource:
    druid:
      write:
        driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
        url:
        username:
        password:
        max-active: 300
        min-idle: 1
        initial-size: 2
        max-wait: 60000
        time-between-eviction-runs-millis: 60000
        min-evictable-idle-time-millis: 300000
        validation-query: select 1
        test-while-idle: true
        test-on-borrow: false
        test-on-return: false
        pool-prepared-statements: true
        max-pool-prepared-statement-per-connection-size: 200
        filters: mergeStat
      logwrite:
              driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
              url:
              username:
              password:
              max-active: 300
              initial-size: 2
              max-wait: 60000
              time-between-eviction-runs-millis: 60000
              min-evictable-idle-time-millis: 300000
              validation-query: select 1
              test-while-idle: true
              test-on-borrow: false
              test-on-return: false
              pool-prepared-statements: false
              max-pool-prepared-statement-per-connection-size: 200
      web-stat-filter:
        enabled: true
        url-pattern:  /*
        exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
        session-stat-enable: true
        profile-enable: true
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        reset-enable: true
        login-username:
        login-password:

mybatis:
  configuration:
    #数据库超过180秒仍未响应则超时 sql执行时间,单位秒
    default-statement-timeout:  180
    cache-enabled: true
    # 查询时，关闭关联对象即时加载以提高性能 默认false
    lazy-loading-enabled: true
    #设置关联对象加载的形态，此处为按需加载字段(加载字段由SQL指 定)，不会加载关联表的所有字段，以提高性能 默认true
    aggressive-lazy-loading: true
    #对于未知的SQL查询，允许返回不同的结果集以达到通用的效果 默认 true
    multiple-result-sets-enabled: true
     #允许使用列标签代替列名 默认 true
    use-column-label: true
    #给予被嵌套的resultMap以字段-属性的映射支持
    auto-mapping-behavior: PARTIAL
    # 不打印sql执行日志
    log-impl: org.apache.ibatis.logging.nologging.NoLoggingImpl
management:
  endpoints:
    web:
      exposure:
        include: info,health,beans,conditions,configprops,env,flyway,httptrace,liquibase,metrics,mappings,sessions
  health:
    db:
      enabled: false
