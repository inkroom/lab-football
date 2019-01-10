package javadev.iip.action.fiveplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javadev.core.common.Anonymous;
import javadev.core.util.InfoProtUtil;
import javadev.iip.action.BaseAction;
import javadev.iip.util.validata.V;
import javadev.core.Constants;

public class FivePlanLoginAction extends BaseAction implements Anonymous{

	private String newToken1;
	private String username;
	private String password;
	private String randomCode;
	private String errorInfo=null;
	private String initialPassword = "123456";
	private V v=new V();
	private Map<String, Object> myfivePlan;
	private Map<String, Object> loginTime;

	//五年计划登录页面接口
	public String pageToTl(){
		setSaltForSession();
		if(errorInfo!=null&&errorInfo.equals("logout"))
			errorInfo ="账号已安全退出";
		else
			errorInfo = null;
		return "pageToTl";
	}

	/**
	 * 功能的描述: 五年计划登录
	 * @author:朱春雨
	 * @return:String
	 * Create Date:2016-8-30
	 * @throws Exception 
	 */
	public String login() throws Exception {
		loginTime = getServMgr().getFivePlanLoginService().getLoginTime();
		Date time = new Date();
		String df = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(time);		
		int i= compare_date(df,loginTime.get("END_DATE_DISTRICT").toString());
		if(i != -1){
			errorInfo = "系统未开放";
			return "fivePlanLoginError";
		}
		
		if(v.checkEmpty(username)==true|| v.checkEmpty(password)==true){
			errorInfo = "账号或密码不能为空";
			return "fivePlanLoginError";
		}
		else if(!randomCode.equals(getSession().get(Constants.RANDOM_CODE).toString())){
			//else if(!randomCode.equals("123456")){
			errorInfo = "验证码输入错误";
			return "fivePlanLoginError";
		}
		System.out.println("-------------------------------");
		System.out.println(password);
		System.out.println("-------------------------------");
		
		
		myfivePlan = getServMgr().getFivePlanLoginService().getFivePlanByUserName(username);

		if (myfivePlan == null) {
			errorInfo = "账号不存在";
			return "fivePlanLoginError";
		}
		String pss = myfivePlan.get("PASSWORD").toString();
		System.out.println("-------------------------------");
		System.out.println(pss);
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println(getSession().get(Constants.SALT_IN_SESSION).toString());
		System.out.println("-------------------------------");
		if(pss!=null){				
			try {	
			if (InfoProtUtil.comparePass(pss,getSession().get(Constants.SALT_IN_SESSION).toString(),password)) {
				setSaltForSession();
				errorInfo = "登录成功";
				getServMgr().getFivePlanLoginService().intoLogin(username,myfivePlan.get("A_ID"));
				myfivePlan.put("TYPE", 2);
				getSession().put(Constants.LOGIN_USER, myfivePlan);
				getSession().put(Constants.LOGIN_USER_ID, myfivePlan.get("O_NAME"));
				setToken();	
				return "fivePlanLogin";
			}
			} catch (Exception e) {
				errorInfo = "账号或密码错误";
				return "fivePlanLoginError"; 
			}
		}
		errorInfo = "账号或密码错误";
		return "fivePlanLoginError";
	}
	public String logout() 
	{
		getSession().remove(Constants.LOGIN_USER);
		errorInfo ="logout";
		return "fivePlanLoginout";

	}

	
    public static int compare_date(String DATE1, String DATE2) {
        
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getInitialPassword() {
		return initialPassword;
	}

	public void setInitialPassword(String initialPassword) {
		this.initialPassword = initialPassword;
	}

	public Map<String, Object> getMyfivePlan() {
		return myfivePlan;
	}

	public void setMyfivePlan(Map<String, Object> myfivePlan) {
		this.myfivePlan = myfivePlan;
	}

	public String getNewToken1() {
		return newToken1;
	}

	public void setNewToken1(String newToken1) {
		this.newToken1 = newToken1;
	}


}
