package javadev.iip.action.fiveplan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.util.SystemSeparatorUtil;
import javadev.iip.util.fileUploadContext;

public class FileAction extends BaseAction{
	
	//获得系统路径格式
	private String separator = SystemSeparatorUtil.getSystemSeparator();
	private String Path = SystemSeparatorUtil.getRootPath();
	private String username;
	private File myUpload;
	private String myUploadFileName;
	private String myUploadContentType;
	private String errorInfo;
	//五年计划上传入口
	public String pageToTl(){
		errorInfo = null;
		return "pageToTl";
	}
	/**
	 * 功能的描述: 上传action
	 * @author:朱春雨
	 * @return:String
	 * Create Date:2016-8-30
	 */
	public String upload() throws Exception{
		if(myUpload==null)
		{
			errorInfo="上传文件为空！";
			return "ERROR";
	     }
	
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		fileUploadContext fileUC = ((fileUploadContext)ac.getBean("fileUploadContext"));
		String a_id = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("A_ID").toString();
		String o_id = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		
		String path = Path+separator+
				fileUC.getSrc()+separator+
				new SimpleDateFormat("YYYY-M-d"+separator+"HH-mm-ss").format(new Date());
		String intoPath = Path+separator+
				fileUC.getSrc()+separator+
				new SimpleDateFormat("YYYY-M-d"+separator+"HH-mm-ss").format(new Date())+separator+myUploadFileName;
		String strNewFileName = myUploadFileName;
		
		String [] types = fileUC.getType();
		System.out.println(getMyUploadFileName());
		int extIndex = getMyUploadFileName().lastIndexOf(".")+1;
		System.out.println(extIndex);
		String ext=getMyUploadFileName().substring(extIndex).toLowerCase();
		System.out.println(ext);
		
		boolean flag = false; 
		for(int i=0;i<types.length;i++){
			if(ext.equalsIgnoreCase(types[i])){
				flag = true;
			}
		}
		if(!flag){
			errorInfo="上传文件格式错误！";
			return "ERROR";
		}
		System.out.println(path);
		createDirectory(new File(path));
		try(FileOutputStream fos = new FileOutputStream(path+separator+getMyUploadFileName());  
				FileInputStream fis = new FileInputStream(myUpload);  ){  
			
			
			byte[] buffer = new byte[1024];  
			int len = 0;
			try {  
				while((len = fis.read(buffer)) > 0){  
					fos.write(buffer, 0, len);  
				}  
			} 
			catch (IOException e) {  
				e.printStackTrace();  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}
		getServMgr().getFileService().addUploadFiles(intoPath, "2", "1",a_id,a_id,"7",o_id);
		System.out.println("_______________________");
		System.out.println(path);
		System.out.println("_______________________");
		errorInfo="上传成功！";
		return SUCCESS;
	}
	public void createDirectory(File file){
		if  (!file .exists()&&!file .isDirectory()){       
		    createDirectory(file.getParentFile());
		    file.mkdir();
		} 
		else{  
		  
		}  
	}
	public File getMyUpload() {
		return myUpload;
	}
	public void setMyUpload(File myUpload) {
		this.myUpload = myUpload;
	}
	public String getMyUploadFileName() {
		return myUploadFileName;
	}
	public void setMyUploadFileName(String myUploadFileName) {
		this.myUploadFileName = myUploadFileName;
	}
	public String getMyUploadContentType() {
		return myUploadContentType;
	}
	public void setMyUploadContentType(String myUploadContentType) {
		this.myUploadContentType = myUploadContentType;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
}