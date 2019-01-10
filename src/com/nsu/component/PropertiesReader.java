package com.nsu.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2018/1/10
 * @Time 10:00
 * @Descorption
 */
public class PropertiesReader extends PropertyPlaceholderConfigurer {
    private Properties prop;

    public String getValue(String key) {
        return prop.getProperty(key, "");
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        this.prop = props;
        logger.info(props);
        super.processProperties(beanFactoryToProcess, props);
    }
}
