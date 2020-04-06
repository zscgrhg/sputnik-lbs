package com.zte.sputnik.lbs;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;

/**
 * 分离sputnik日志
 * @author zscgrhg
 */
public class LoggerBuilder {
    public static final LoggerContext LOGGER_CONTEXT = createFromClasspathResource();

    public static final Logger of(Class<?> clazz) {
        return LOGGER_CONTEXT.getLogger(clazz);
    }

    public static LoggerContext createFromClasspathResource() {
        LoggerContext context = new LoggerContext();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            String fileName = "sputnik.xml";
            configurator.setContext(context);
            configurator.doConfigure(LoggerBuilder.class.getClassLoader().getResourceAsStream(fileName));
        } catch (JoranException e) {
            throw new RuntimeException(e);
        }
        return context;
    }
}
