package javadev.iip.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.components.ElseIf;

import javadev.core.Constants;
import javadev.core.common.Anonymous;
import javadev.core.util.InfoProtUtil;
import javadev.iip.service.OtherService;
import javadev.iip.service.ServiceManager;

/**
 *  用于登录等出
 * @author MeiXiebing
 * Create Date: 2016-8-31
 */
public class LoginAction extends BaseAction implements
Anonymous {

	private OtherService otherService;
	private String userName;
	private String userPassword;
	private String randomCode;
	private String userInfo;
	private String errorInfo;
	private ServiceManager serviceManager;
	

	
	
	/**
	 * 登录页面
	 * @return
	 */
	public String loginView() {

		
		
		try {
			errorInfo = getSession().get("errorInfo").toString();
			getSession().remove("errorInfo");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		if(getSession().get(Constants.LOGIN_USER)!=null){
			Map<String,Object> map = (Map<String,Object>) getSession().get(Constants.LOGIN_USER);
			if(map.get("O_ID")!=null&&!"".equals(map.get("O_ID").toString().trim())){
				return "alreadyLogin";
			}
		}
		setSaltForSession();
		return SUCCESS;
	}
	
	
	
	/**
	 * 登录判断
	 * @return
	 */
	public String login() {
		
		Map<String, Object> dateMap =getServiceManager().getOtherService().getOpenDate();
		Date dateStart = (Date)dateMap.get("START_DATE_DISTRICT");
		Date dateEnd = (Date) dateMap.get("END_DATE_DISTRICT");
		Date dateNow = new Date();
		if (dateNow.compareTo(dateEnd)!= -1 || dateNow.compareTo(dateStart)!=1) {
			errorInfo = "系统已关闭！";
			getSession().put("errorInfo", errorInfo);
			return "login";
		}
		
		
		try {
			if (randomCode == null || randomCode.equals("")
					|| !randomCode.equals(getSession().get(Constants.RANDOM_CODE).toString().trim())) {
					System.out.println(randomCode+"------------------------");
					System.out.println(getSession().get(Constants.RANDOM_CODE).toString().trim()+"+++++++++++++++++++++++++++++++");
				errorInfo = "验证码错误";
				getSession().put("errorInfo", errorInfo);
				return "login";
			}
		} catch (Exception e) {
			errorInfo = "验证码错误";
			getSession().put("errorInfo", errorInfo);
			e.printStackTrace();
			return "login";
		}

		map = getServMgr().getOtherService().getShoolById(userName);
		

		if (map == null) {
			errorInfo = "当前用户不存在！";
			getSession().put("errorInfo", errorInfo);
			return "login";
		}
		



		if (map.get("STATUS") == null) {

			errorInfo = "当前用户不能登录，请联系上级管理员！";
			getSession().put("errorInfo", errorInfo);
			return "login";

		}
		if (map.get("STATUS").toString().equals("1")) {
			
			System.out.println("===========================7987897988989798798789798798"+getSession().get(Constants.SALT_IN_SESSION));
			String salt = (String) getSession().get(Constants.SALT_IN_SESSION);
			
			System.out.println(InfoProtUtil.parseStrToMd5L32((String) map.get("PASSWORD")+salt));
			System.out.println(userPassword);
			if (InfoProtUtil.comparePass((String) map.get("PASSWORD"), salt, userPassword)) {
				map.put("TYPE", "1");
				
				getSession().clear();
				getServiceManager().getOtherService().updateLogin(userName);
				
				getSession().clear();
				getSession().put(Constants.LOGIN_USER, map);
				getSession().put(Constants.LOGIN_USER_ID, map.get("O_NAME"));
				getSession().put(Constants.LOGIN_USER_C_ID, map.get("A_ID"));
				setToken();

				return "success";
			}

			errorInfo = "当前用户不存在！或密码错误！";
			getSession().put("errorInfo", errorInfo);
			return "login";
		}
		errorInfo = "当前用户不能登录,请联系上级管理员！";
		getSession().put("errorInfo", errorInfo);
		return "login";
	}
	
	
	/**
	 * 登出
	 * @return
	 */
	public String logout(){
		errorInfo = "账号已安全退出";
		getRequest().getSession().invalidate();
		return SUCCESS;
	}
	

	
	public void getData(){
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		System.out.println("=============="+user.get("O_ID").toString());
		Map<String, Object> nowData = getServiceManager().getOtherService().getSportData(user.get("O_ID").toString());
		
		System.out.println("99999999999999999999999999999999-----"+getServiceManager().getOtherService().getC_ID_BY_O_ID(user.get("O_ID").toString()));
		
//		for (String key : nowData.keySet()) {
//			System.out.println("map.put(\""+ key + "\",\"" +nowData.get(key)+"\");");
//		}
//		
//		
//		for (String key : nowData.keySet()) {
//			   System.out.println("NOW ：key= "+ key + " and value= " + nowData.get(key));
//		}
		
		
//		Map<String, Object> lastData = getServiceManager().getOtherService().getSportLastYearData(user.get("O_ID").toString());
//		Map<String, Object> artData  =  getServiceManager().getOtherService().getArtData(user.get("O_ID").toString());
//		
//		for (String key : artData.keySet()) {
//			   System.out.println("map.put(\""+  key + "\",\"" + artData.get(key)+"\");");
//		}
//		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}


	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}





	public OtherService getOtherService() {
		return otherService;
	}

	public void setOtherService(OtherService otherService) {
		this.otherService = otherService;
	}


	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	private Map<String, Object> map = new HashMap<String,Object>();
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}


	public String getErrorInfo() {
		return errorInfo;
	}


	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}



	
	

	
	
}
