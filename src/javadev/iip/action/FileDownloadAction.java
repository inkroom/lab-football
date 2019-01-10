package javadev.iip.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 *  用于下载报表填写规范文档
 * @author MeiXiebing
 * Create Date: 2016-8-31
 */
public class FileDownloadAction extends ActionSupport{
	
	private String fileName;
	
	
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getDownloadFile()
    {
    	
    	fileName="操作指南.zip";//要下载文件的名称。
    	try {
    		fileName = new String(fileName.getBytes(), "ISO8859-1");
    	} catch (UnsupportedEncodingException e) {     
    		e.printStackTrace();
    	}
        return ServletActionContext.getServletContext().getResourceAsStream("upload/操作指南.zip");
    }
    
    @Override
    public String execute() throws Exception{
        return SUCCESS;
    }
}
