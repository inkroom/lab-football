package javadev.iip.action.sport;

import java.util.Map;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

import java.util.regex.Pattern;
/***
 * 功能描述: 用于确定用户的信息;
 * 
 * @author曹旭峰 Create Date: 2016-11-14
 *
 */

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;
public class SportEnd extends BaseAction {
	
	private String name;
	private String phone;
	private String errorInfo;
	private V v;
	public ServiceManager serviceManager;
	Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
	String o_id = user.get("O_ID").toString();
	
	
	public String  intoPage() throws Exception {
		Map map = getServiceManager().getSportEndServive().getNowData(o_id);
		System.out.println("===dadadadaa========"+map);
		if (map!=null) {
			if ((map.get("PERSON_NAME")!=null)) {
				name = map.get("PERSON_NAME").toString();
			}
			if ((map.get("PERSON_PHONE")!=null)) {
				phone = map.get("PERSON_PHONE").toString();
			}
		}
		return "into";
	}
	
	public String submitData(){
		
		if (!isChinsesStr(name)) {
			errorInfo = "姓名必须为2~20为的纯中文或者纯英文，不包含其他字符！";
			return "error";
		}
		if (!vlidatePhoneNumber(phone)) {
			errorInfo = "请输入正确主任的电话号码！";
			return "error";
		}		
		getServiceManager().getSportEndServive().modifyData(name, phone, o_id);
		return "succ";
	}
	
	/**
	 * 
	 * 验证方法
	 * */
	private boolean isChinsesStr(String str) {
		String regex = "(([\u4E00-\u9FA5]{2,20})|([a-zA-Z]{2,20}))";
		if (!Pattern.matches(regex, str)) {
			errorInfo="请输入正确格式的姓名！";
			return false;
		}

		return true;
	}
	private Boolean vlidatePhoneNumber(String str) {

		if (isNum(str) && str.length() <21) {
			return true;
		} else {
			errorInfo="请输入正确格式的电话号码！";
			return false;
		}
	}
	private static  boolean isNum(String str){
		if (str == null || str.equals("") ) {
			
			return false;  
		}
		for (int i = str.length();--i>=0;){
			if (!Character.isDigit(str.charAt(i))){  
				return false;  
			}  
		}  
		return true;  
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


	public ServiceManager getServiceManager() {
		return serviceManager;
	}


	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}


	public Map<String, Object> getUser() {
		return user;
	}


	public void setUser(Map<String, Object> user) {
		this.user = user;
	}


	public String getO_id() {
		return o_id;
	}


	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	
}
