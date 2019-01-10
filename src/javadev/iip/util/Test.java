package javadev.iip.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jfree.ui.about.SystemPropertiesTableModel;

import javadev.core.util.InfoProtUtil;

public class Test {
	public static void main(String[] args) throws IOException {
//		String url = "/Users/MeiXiebing/Study/JavaWeb/WorkSpace/SchoolProject/football2nd/webapp/WEB-INF/classes/";
//		int teString = url.lastIndexOf("/WEB-INF/classes/");
//		url = url.substring(0,teString);
//		System.out.println(url);
		
		System.out.println(InfoProtUtil.parseStrToMd5L32("123456"));
	}
	
	
	public static String getLevel(int level)
	  {
	    //A mutable sequence of characters.
	    StringBuilder sb=new StringBuilder();
	    for(int l=0;l<level;l++)
	    {
	      sb.append("|--");
	    }
	    return sb.toString();
	  }
	
	
	
	public static void getAllFiles(File dir,int level)
	  {
	    System.out.println(getLevel(level)+dir.getName());
	    level++;
	    File[] files=dir.listFiles();
	    for(int i=0;i<files.length;i++)
	    {
	      if(files[i].isDirectory())
	      {
	        //这里面用了递归的算法
	        getAllFiles(files[i],level);
	      }
	      else {
	        System.out.println(getLevel(level)+files[i]);
	      }	
	    }		 
	  }
	
}
