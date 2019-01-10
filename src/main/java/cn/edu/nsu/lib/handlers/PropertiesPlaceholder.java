package cn.edu.nsu.lib.handlers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/22
 * @Time 16:24
 * @Descorption
 */
public class PropertiesPlaceholder extends PropertyPlaceholderConfigurer {
    private static Map<String, String> sMap;
    private Map<String, String> map;
    private Properties prop;

    public String getValue(String key) {
        return prop.getProperty(key, "");
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

        map = new HashMap<>();
        sMap = new HashMap<>();
        this.prop = props;
        for (Object key : props.keySet()) {
            String keys = key.toString();
            sMap.put(keys, props.getProperty(keys));
            map.put(keys, props.getProperty(keys));
        }
        logger.info(props);
        super.processProperties(beanFactoryToProcess, props);
    }



}
