package javadev.iip.action.fiveplan;

import java.util.Date;
import java.util.Map;

import javadev.core.Constants;
import javadev.core.util.InfoProtUtil;
import javadev.iip.action.BaseAction;

public class FivePlanModifyPDAction extends BaseAction{
	
	private String username;
	private String password;
	private String randomCode;
	
	private String newPassword;
	private String confirmPassword;
	private String errorInfo;
	private Map<String, Object> myfiveplan;
	
	public String pageToTl(){
		setSaltForSession();
		errorInfo = null;
		return "pageToTl";
	}
	/**
	 * 功能的描述: 五年计划修改密码
	 * @author:朱春雨
	 * @return:String
	 * Create Date:2016-8-30
	 */
	public String updatePassword() {
		if(password == null || password.equals("")){
			errorInfo = "原密码不能为空";
			setSaltForSession();
			return "updatePasswordError";
		}else if (validatePassword()) {
			errorInfo = "原密码不正确";
			setSaltForSession();
			return "updatePasswordError";
		}else if(!newPassword.equals(confirmPassword)){
			errorInfo = "新密码两次输入不一致";
			setSaltForSession();
			return "updatePasswordError";
		}else if(validatePassword(newPassword))
		{
			errorInfo = "新密码不能与原密码相同";
			setSaltForSession();
			return "updatePasswordError";
		}
		
		 
		if(getServMgr().getFivePlanModifyPDService().updatePassword(((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("USERNAME").toString(),((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("A_ID").toString(), newPassword)){
			getSession().remove(Constants.LOGIN_USER);
			setSaltForSession();
			errorInfo ="修改成功，请重新登录";
			return "updatePassword";
		}else {
			errorInfo = "系统错误，修改失败";
			setSaltForSession();
			return "updatePasswordError";
		}
		
	}
	//比较原密码和新密码是否相同
	public boolean validatePassword(String newpass){
		return (InfoProtUtil.comparePass(newpass, getSession().get(Constants.SALT_IN_SESSION).toString(), password));
	}
	public boolean validatePassword(){
		myfiveplan = getServMgr().getFivePlanModifyPDService().getFivePlanByUserName(((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("USERNAME").toString());
		return (!InfoProtUtil.comparePass(myfiveplan.get("PASSWORD").toString(), getSession().get(Constants.SALT_IN_SESSION).toString(), password));
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public Map<String, Object> getMyfiveplan() {
		return myfiveplan;
	}
	public void setMyfiveplan(Map<String, Object> myfiveplan) {
		this.myfiveplan = myfiveplan;
	}
	

}
