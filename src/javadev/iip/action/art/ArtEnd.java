package javadev.iip.action.art;


import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

public class ArtEnd extends BaseAction{
     private String name;
     private String phone;
     private String errorInfo;
 	 private String token;
 	 private static V v = new V();
 	 private ServiceManager serviceManager;
     
    
     @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	setToken(getSession().get("token").toString()); 
    	Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("O_ID").toString();
    	Map art=serviceManager.getArtEndService().selectArtEnd(o_id);
    	Map<String, Object> map2 = new HashMap<String, Object>();
		map2 =serviceManager.getOtherService().getArtData(o_id);
    	
	   
		//读取前一年填写人信息，显示页面
    	if (v.checkEmpty(map2.get("STUDENTS_GOOD"))) {
			errorInfo = "当前页面尚未填写完毕！";
			return "backview";
		}
    	if (!v.checkEmpty(art.get("PERSON_NAME"))) {
			name=art.get("PERSON_NAME").toString();
		}
    	if (!v.checkEmpty(art.get("PERSON_PHONE"))) {
			phone=art.get("PERSON_PHONE").toString();
		}
    	
		return "noerror";
     }
     
     public String getData() throws Exception{
 	    setToken(getSession().get("token").toString());
 	    @SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("O_ID").toString();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 =serviceManager.getOtherService().getArtData(o_id);
 	    Map<String, Object> map = new HashMap<String, Object>();
 	    
 	    if (v.checkEmpty(map2.get("STUDENTS_GOOD"))) {
			errorInfo = "当前页面尚未填写完毕！";
			return "backview";
		}
 	    //校验填写信息的合法性
 	    if (v.checkEmpty(name)||!V.isChinese(name)) {
			errorInfo="请输入填写人姓名";
			return "error";
		}
    	if (v.checkEmpty(phone)||!V.isNumeric(phone)) {
			errorInfo="请输入填写人手机号码";
			return "error";
    	}
    	if (isChangedData()) {
    		serviceManager.getArtEndService().updateArtEnd(name,phone,o_id);
        	return "success";
		}
    		return "success";
     }

     public boolean isChangedData() throws Exception {
    	boolean flag = true;
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
 		String  o_id = user.get("O_ID").toString();
 		Map map=serviceManager.getArtEndService().selectArtEnd(o_id);
        if (map != null) {
			String str = v.checkEmpty(map.get("PERSON_NAME")) == true ? "": map.get("PERSON_NAME").toString();
			if (str.length() > 0 && str.equals(name)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("PERSON_PHONE")) == true ? "" : map.get("PERSON_PHONE").toString();
			if (!flag && str.length() > 0 && str.equals(phone)) {
				flag = false;
			} else {
				flag = true;
			}
       }
		return flag;
     }

     
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
}
