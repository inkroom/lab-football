package javadev.iip.util;

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
            System.out.println("windows");  
	        rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));  
	        rootPath = rootPath.replace("/", "\\");  
        }  
        //linux下  
        if("/".equals(File.separator)){  
            System.out.println("linux");  
	        rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));  
	        rootPath = rootPath.replace("\\", "/");  
        }  
        return rootPath;  
     } 
}
