package javadev.iip.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
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
 *  用于导出艺术报表
 * @author MeiXiebing
 * Create Date: 2016-8-31
 */
public class GetArtExcelAction extends BaseAction{
	
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
      * 下载艺术报表excel
     */
    @Override
    public String execute() throws Exception{
    	
		user = (Map<String, Object>) ActionContext.getContext().getSession().get(Constants.LOGIN_USER);
		if (excelBytName("art")) {
			try {
				String path = WriteExcel.class.getResource("/").getPath();
				int n = path.indexOf("WEB-INF");
				path = path.substring(0, n);
				File file = new File(path+"download"+File.separator+user.get("O_ID")+"-art.xls");
				// 文件不存在时
				if (!file.exists()) {
					log.error("---"+file.getPath());
					log.error("<script>alert('不好意思，文件不存在或已被删除！')</script>");
					setErrorInfo("系统异常");
				}

				HttpServletResponse response = getResponse();
				response.setContentType("UTF-8");
				response.setHeader("Content-type", "application/octet-stream");
				fileName = user.get("O_NAME")+"-艺术年度报表.xls";
				fileName = new String(fileName.getBytes(), "ISO8859-1");
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
				response.reset();
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
			return null;
		}else {
			setErrorInfo("请填写完艺术报表");
			return ERROR;
		}
	}
    

    
    
    
    /**
     * 生成艺术报表excel
     * @param fileName
     * @return
     * @throws DocumentException
     * @throws BiffException
     * @throws IOException
     */
    public boolean excelBytName(String fileName) throws DocumentException, BiffException, IOException {

    	String O_ID = user.get("O_ID").toString();
		Map<String, Object> data = ((ServiceManager) BeanManager.getBean("serviceManager")).getOtherService().getArtData(O_ID);
		Map<String, Object> writer = ((ServiceManager) BeanManager.getBean("serviceManager")).getOtherService().getArtWriterData(O_ID);
		
		
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
			if (data.get("IS_COMPLETE") != null) {
				if (data.get("IS_COMPLETE").toString().equals("1")) {
					data.put("IS_COMPLETE", "是");
				}else {
					data.put("IS_COMPLETE", "否");
				}
			}else {
				data.put("IS_COMPLETE", "否");
			}
		} catch (Exception e) {
			// TODO: handle exception
			data.put("IS_COMPLETE", "否");
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
		
		
		try {
			if (data.get("IS_FEATURED") != null) {
				if (!data.get("IS_FEATURED").toString().equals("1")) {
					data.put("IS_FEATURED", "否");
				}else {
					data.put("IS_FEATURED", "是");
				}	
			}else {
				data.put("IS_FEATURED", "否");
			}
		} catch (Exception e) {
			// TODO: handle exception
			data.put("IS_FEATURED", "否");
		}
		
		
		try {
			if (data.get("IS_TEST_PERFORMED") != null) {
				if (!data.get("IS_TEST_PERFORMED").toString().equals("1")) {
					data.put("IS_TEST_PERFORMED", "否");
				}else {
					data.put("IS_TEST_PERFORMED", "是");
				}
			}else {
				data.put("IS_TEST_PERFORMED", "否");
			}
		} catch (Exception e) {
			// TODO: handle exception
			data.put("IS_TEST_PERFORMED", "否");
		}
		
		
		
		try {
			if (data.get("SELF_REMARK_GRADE") != null) {
				if (data.get("SELF_REMARK_GRADE").toString().equals("1")) {
					data.put("SELF_REMARK_GRADE", "优秀");
				}else if (data.get("SELF_REMARK_GRADE").toString().equals("2")) {
					data.put("SELF_REMARK_GRADE", "良好");
				}else if (data.get("SELF_REMARK_GRADE").toString().equals("3")) {
					data.put("SELF_REMARK_GRADE", "合格");
				}else if (data.get("SELF_REMARK_GRADE").toString().equals("4")) {
					data.put("SELF_REMARK_GRADE", "不合格");
				}else {
					data.put("SELF_REMARK_GRADE", "数据异常");
				}
			}else {
				data.put("SELF_REMARK_GRADE", "数据异常");
			}
		} catch (Exception e) {
			// TODO: handle exception
			data.put("SELF_REMARK_GRADE", "数据异常");
		}
		
		
		
		try {
			AnalysisXML analysisXML = new AnalysisXML(ServletActionContext.getServletContext().getResourceAsStream("excel"+File.separator+"excelArt.xml"));

			ExcelBean excelBean = analysisXML.readXML();
			WriteExcel write = new WriteExcel(data, excelBean);
			write.excelBuild(user.get("O_ID")+"-art.xls");
			write.write();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
				

    }
}
