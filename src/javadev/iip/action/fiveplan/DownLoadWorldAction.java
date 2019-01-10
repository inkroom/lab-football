package javadev.iip.action.fiveplan;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javadev.iip.action.BaseAction;

/**
 * 功能描述：用于下载的说明文档的Action
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月11日08：53：24
 */
public class DownLoadWorldAction extends BaseAction{

	public String downLoadWorld(){
		try{
			String uri=this.getClass().getClassLoader().getResource("/").getPath();
			String url= uri.substring(0, uri.length()-16)+"worldDoc/操作指南.zip";
			System.out.println(url);
			File file = new File(url);
			HttpServletResponse response = getResponse();
			response.setContentType("UTF-8");
			String filenamedisplay = URLEncoder.encode("操作指南.zip", "UTF-8");
			if("FF".equals(getBrowser(getRequest()))){
			    //针对火狐浏览器处理方式不一样了
				filenamedisplay = new String("操作指南.zip".getBytes("UTF-8"),"iso-8859-1");
			}
			response.setHeader("Content-type", "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+filenamedisplay);
			response.setHeader("Content-Length", file.length() + "");

			byte[] by = new byte[1024];
			FileInputStream in = new FileInputStream(file);
			int length = 0;
			while ((length = in.read(by)) > 0) {
				response.getOutputStream().write(by, 0, length);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
			in.close();
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}return "loading";
	}
	private String getBrowser(HttpServletRequest request){
	    String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
	    if(UserAgent!=null){
	        if (UserAgent.indexOf("msie") >=0 ) return "IE";
	        if (UserAgent.indexOf("firefox") >= 0) return "FF";
	        if (UserAgent.indexOf("safari") >= 0) return "SF";
	    }
	    return null;
	}
}
