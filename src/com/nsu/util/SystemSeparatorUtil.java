package com.nsu.util;

import java.io.File;

public class SystemSeparatorUtil {
	/*获取系统文件路径格式*/
	public static String getSystemSeparator(){
		return System.getProperties().getProperty("file.separator");
	}
	public static String classPath = SystemSeparatorUtil.class.getClassLoader().getResource("/").getPath();  
    //对项目的根路径进行解析，拿到项目路径  
      
    public static String getRootPath() {  
        String rootPath = "";  
        //windows下  
        if("\\".equals(File.separator)){
	        rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));  
	        rootPath = rootPath.replace("/", "\\");  
        }  
        //linux下  
        if("/".equals(File.separator)){
	        rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));  
	        rootPath = rootPath.replace("\\", "/");  
        }  
        return rootPath;  
     }
    
    //将自定义构造的路径改造为符合当前系统的路径
    public static String getCorrectPtah(String path){
    	 //windows下  
        if("\\".equals(File.separator)){  
           
	        path = path.replace("/", "\\");  
        }  
        //linux下  
        if("/".equals(File.separator)){  
         
	        path = path.replace("\\", "/");  
        }  
        return path;  
    }
}
