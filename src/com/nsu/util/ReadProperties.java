package com.nsu.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.util
 * @Description:
 * @date 17/4/11
 */


public class ReadProperties {
    private static InputStream readProperties(String fileName){
        Properties prop =  new  Properties();
        InputStream in = null;
        URL classPath = Thread.currentThread().getContextClassLoader().getResource("");
        String proFilePath = classPath.toString();
        //移除开通的file:/六个字符
        proFilePath = proFilePath.substring(5);
        //如果为window系统下,则把路径中的路径分隔符替换为window系统的文件路径分隔符
        proFilePath = proFilePath.replace("/", File.separator);
        proFilePath = proFilePath.replace("\\", File.separator);
        try {
            in = new BufferedInputStream(new FileInputStream(proFilePath+"save.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static String getPropertiesValue(String fileName,String attribute){
        Properties prop =  new  Properties();
        try {
            prop.load(readProperties(fileName));
            return prop.getProperty(attribute);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
