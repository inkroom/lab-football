package cn.nsu.edu.web.four.handler.spring;

import cn.nsu.edu.web.four.utils.encrypt.DecryptAble;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/22
 * @Time 16:24
 * @Descorption
 */
public class PropertiesPlaceholder extends PropertyPlaceholderConfigurer {

    private DecryptAble decrypt;
    private Properties prop;


    public String getValue(String key) {
        return prop.getProperty(key, "");
    }

    public PropertiesPlaceholder(DecryptAble decrypt) {
        this.decrypt = decrypt;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

        this.prop = props;
        for (Object key : props.keySet()) {
            String keys = key.toString();
            if (keys.contains("password") && decrypt != null) {//对密码进行解密
                String value = props.getProperty(keys);
                logger.info("解密字段= "+keys+"  "+value);
                props.setProperty(keys, decrypt.decrypt(value));
            }
        }
//        log.info(props.toString());
        logger.info(props);
        super.processProperties(beanFactoryToProcess, props);
    }


}
