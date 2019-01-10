package javadev.iip.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.If;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javadev.core.Constants;
import javadev.core.bean.BeanManager;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.excel.bean.ExcelBean;
import javadev.iip.util.excel.excel.AnalysisXML;
import javadev.iip.util.excel.excel.WriteExcel;
import jxl.read.biff.BiffException;


/**
 *  用于导出体育报表
 * @author MeiXiebing
 * Create Date: 2016-8-31
 */
public class GetSportExcelAction extends BaseAction{
	
	private String errorInfo;
	private String fileName;
	Map<String, Object> user;
	
    public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    
	/**
     * 下载体育报表excel文件
     * @return
     */
    @Override
    public String execute() throws Exception{
		user = (Map<String, Object>) ActionContext.getContext().getSession().get(Constants.LOGIN_USER);
		if (excelBytName()) {
			try {
				String path = WriteExcel.class.getResource("/").getPath();
				int n = path.indexOf("WEB-INF");
				path = path.substring(0, n);
				File file = new File(path+"download"+File.separator+user.get("O_ID")+"-sport.xls");
				
				// 文件不存在时
				if (!file.exists()) {
					log.error("---"+file.getPath());
					log.error("<script>alert('不好意思，文件不存在或已被删除！')</script>");
					setErrorInfo("系统异常");
				}

				HttpServletResponse response = getResponse();
				response.setContentType("UTF-8");
				response.setHeader("Content-type", "application/octet-stream");
			
				
				
				fileName = user.get("O_NAME")+"-体育年度报表.xls";
				fileName = new String(fileName.getBytes(), "ISO8859-1");
				if("IE".equals(getBrowser(getRequest()))){
					//IE浏览器的特殊处理
					fileName = URLEncoder.encode(fileName, "UTF-8");

				}
				response.setHeader("Content-Disposition", "attachment;filename="+fileName);
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
				file.delete();
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
			return null;
		}else {
			setErrorInfo("请填写完体育报表");
			return ERROR;
		}
	}
	//判断不同的浏览器
    private String getBrowser(HttpServletRequest request){
	    String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
	    if(UserAgent!=null){
	        if (UserAgent.indexOf("msie") >=0 ) return "IE";
	        if (UserAgent.indexOf("firefox") >= 0) return "FF";
	        if (UserAgent.indexOf("safari") >= 0) return "SF";
	    }
	    return null;
	}

    
    /**
     * 生成体育报表excel文件
     * @return
     */
    public boolean excelBytName() {
		
    	
    	
    	
    	String O_ID = user.get("O_ID").toString();
		try {
			Map<String, Object> data =  ((ServiceManager) BeanManager.getBean("serviceManager")).getOtherService().getSportData(O_ID);
			Map<String, Object> writer = ((ServiceManager) BeanManager.getBean("serviceManager")).getOtherService().getSportWriterData(O_ID);
			
			try {
				data.put("PERSON_NAME", writer.get("PERSON_NAME"));
				data.put("PERSON_PHONE", writer.get("PERSON_PHONE"));
			} catch (Exception e) {
				// TODO: handle exception
				data.put("PERSON_NAME", "请在填写报表时，填写姓名");
				data.put("PERSON_PHONE","请在填写报表时，填写电话");
			}
			
			System.out.println(writer);
			System.out.println(data);
			
			if (data.get("IS_FINISH_WRITE") == null || !data.get("IS_FINISH_WRITE").toString().equals("1")) {
				return false;
			}
			
			try {
				switch (data.get("STAGE").toString()) {
				case "1":
					data.put("STAGE", "普通小学");
					break;
				case "2":
					data.put("STAGE", "普通初中");
					break;

				case "3":
					data.put("STAGE", "普通高中");
					break;

				case "4":
					data.put("STAGE", "职业高中");
					break;

				case "5":
					data.put("STAGE", "九年一贯制学校");
					break;

				case "6":
					data.put("STAGE", "十二年一贯制学校");
					break;

				case "7":
					data.put("STAGE", "完全中学");
					break;

				default:
					break;
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			try {
				if (data.get("HOURS_TOTAL") != null) {
					if (!data.get("HOURS_TOTAL").toString().equals("1")) {
						data.put("HOURS_TOTAL", "否");
					}else {
						data.put("HOURS_TOTAL", "是");
					}
				}else {
					data.put("HOURS_TOTAL", "否");
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.put("HOURS_TOTAL", "否");
			}
			

			try {
				if (data.get("IS_ONE_HR_DAILY") != null) {
					if (!data.get("IS_ONE_HR_DAILY").toString().equals("1")) {
						data.put("IS_ONE_HR_DAILY", "否");
					}else {
						data.put("IS_ONE_HR_DAILY", "是");
					}
				}else {
					data.put("IS_ONE_HR_DAILY", "否");
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.put("IS_ONE_HR_DAILY", "否");
			}
			
			

			
			
			
			try {
				if (data.get("IS_LONG_BREAK_ACTIVITY") != null) {
					if (!data.get("IS_LONG_BREAK_ACTIVITY").toString().equals("1")) {
						data.put("IS_LONG_BREAK_ACTIVITY", "否");
					}else {
						data.put("IS_LONG_BREAK_ACTIVITY", "是");
					}
				}else {
					data.put("IS_LONG_BREAK_ACTIVITY", "否");
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.put("IS_LONG_BREAK_ACTIVITY", "否");
			}
			
			

			
			try {
				if (data.get("IS_INSURANCE") != null) {
					if (!data.get("IS_INSURANCE").toString().equals("1")) {
						data.put("IS_INSURANCE", "否");
					}else {
						data.put("IS_INSURANCE", "是");
					}
				}else {
					data.put("IS_INSURANCE", "否");
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.put("IS_INSURANCE", "否");
			}

			
			try {
				if (data.get("IS_ADD_POINT_PROJECT") != null) {
					if (!data.get("IS_ADD_POINT_PROJECT").toString().equals("1")) {
						data.put("IS_ADD_POINT_PROJECT", "否");
					}else {
						data.put("IS_ADD_POINT_PROJECT", "是");
					}
				}else {
					data.put("IS_ADD_POINT_PROJECT", "否");
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.put("IS_ADD_POINT_PROJECT", "否");
			}
			
			
			try {
				if (data.get("IS_EQUIP_QUALIFIED") != null) {
					if (!data.get("IS_EQUIP_QUALIFIED").toString().equals("1")) {
						data.put("IS_EQUIP_QUALIFIED", "否");
					}else {
						data.put("IS_EQUIP_QUALIFIED", "是");
					}
				}else {
					data.put("IS_EQUIP_QUALIFIED", "否");
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.put("IS_EQUIP_QUALIFIED", "否");
			}
			


			String uri=this.getClass().getClassLoader().getResource("/").getPath();		
			String url =uri.substring(0, uri.length()-16);
			AnalysisXML analysisXML = new AnalysisXML(url+"excel/excelSport.xml");

    		ExcelBean excelBean = analysisXML.readXML();
    		WriteExcel write = new WriteExcel(data, excelBean);
    		System.out.print("=====================dir===================="+user.get("O_ID")+"-sport.xls");
    		write.excelBuild(user.get("O_ID")+"-sport.xls");
			write.write();
			return true;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("=============DocumentException=================");
			return false;
		}catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("=============BiffException=================");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("=============IOException=================");
			return false;
		}
  
    }
}
