package com.mvc.boot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * set system log file location
 */
@WebListener
public class LogListener implements ServletContextListener {
    public static final String log4jdirkey = "LOGDIR";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String log4jdir = sce.getServletContext().getInitParameter(log4jdirkey);
        System.setProperty(log4jdirkey, log4jdir);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.getProperties().remove(log4jdirkey);
    }
}
