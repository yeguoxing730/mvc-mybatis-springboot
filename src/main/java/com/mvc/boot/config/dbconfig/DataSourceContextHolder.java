package com.mvc.boot.config.dbconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地线程，数据源上下文
 *
 */
public class DataSourceContextHolder {

	private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
	
	//线程本地环境
	private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }


    public static void setLoopKey(String dataSourceName) {
        local.set(dataSourceName);
        log.info("数据库切换到"+dataSourceName+"...");
    }

    public static String getLoopKey() {
        return local.get();
    }


    
    public static void clear(){
    	local.remove();
    }
}
