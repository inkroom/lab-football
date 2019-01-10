package javadev.iip.action;

import java.util.Map;

import javadev.core.Constants;
import javadev.core.util.InfoProtUtil;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

/**
 *  用于修改密码，审核结果
 * @author MeiXiebing
 * Create Date: 2016-8-31
 */
public class OtherAction extends BaseAction {
	private V v = new V();
	//修改密码参数
		private String oldPassword;
		private String newPassword1;
		private String newPassword2;
		private String errorInfo;
		private ServiceManager serviceManager;
		
		private String sport;
		private String selfSport;
		private String art;
		
		private String isFinshMessage;
		
		
		public String execute() {
			setSaltForSession();
			return SUCCESS;
		}
		
		
		
		
		public String updatePassword(){
			if (v.checkEmpty(newPassword1)||v.checkEmpty(newPassword2)||!newPassword1.equals(newPassword2)||newPassword1.length()<6||newPassword1.length()>32||newPassword1.equals(oldPassword)) {
				errorInfo = "新密码格式错误！请输入6-16位规范密码！改密码与原密码不能一致！";
			}else {
				Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
				if (InfoProtUtil.comparePass(user.get("PASSWORD").toString(),(String)getSession().get(Constants.SALT_IN_SESSION), oldPassword )) {
					String[] password = {newPassword1,"nono"};
					String userName = user.get("USERNAME").toString();
					if (getServiceManager().getOtherService().updatePassword(password, userName,getSession().get(Constants.LOGIN_USER_C_ID).toString())) {
						errorInfo = "修改密码成功！请重新登录";
						getSession().clear();
						getSession().put("message", errorInfo);
						return SUCCESS;
					}else {
						errorInfo = "修改密码成功！请重新登录";
					}
				}else {
					errorInfo = "原密码错误！";
				}
			}
			getSession().put("message", errorInfo);
			return ERROR;
		}
		
		
		public String indexView(){
			try {
				isFinshMessage  = (String) getSession().get("isFinshMessage");
				getSession().remove("isFinshMessage");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return SUCCESS;
		}
		
		
		/**
		 * 查看审核结果页面
		 * @return
		 */
		public String auditStatus() {
			Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
			try {
				sport = getServiceManager().getOtherService().getSportData(user.get("O_ID").toString()).get("AUDIT_STATUS").toString();
				selfSport = getServiceManager().getSportReportBySelfService().getSelfData(user.get("O_ID").toString()).get("AUDIT_STATUS").toString();
				art = getServiceManager().getOtherService().getArtData(user.get("O_ID").toString()).get("AUDIT_STATUS").toString();
				System.out.println("***********************************");
				System.out.println("***********************************");
				System.out.println("sport:"+sport+"-length:"+sport.length()+"-"+"selfSport:"+selfSport+"-length"+selfSport.length()+"-art:"+art);
				System.out.println("***********************************");
				System.out.println("***********************************");
			} catch (Exception e) {
				// TODO: handle exception
				sport = "0";
				selfSport = "0";
				art = "0";
			}
			return SUCCESS;
		}



		public String getOldPassword() {
			return oldPassword;
		}




		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}




		public String getNewPassword1() {
			return newPassword1;
		}




		public void setNewPassword1(String newPassword1) {
			this.newPassword1 = newPassword1;
		}




		public String getNewPassword2() {
			return newPassword2;
		}




		public void setNewPassword2(String newPassword2) {
			this.newPassword2 = newPassword2;
		}




		public ServiceManager getServiceManager() {
			return serviceManager;
		}




		public void setServiceManager(ServiceManager serviceManager) {
			this.serviceManager = serviceManager;
		}




		public String getErrorInfo() {
			return errorInfo;
		}




		public void setErrorInfo(String errorInfo) {
			this.errorInfo = errorInfo;
		}




		public String getSport() {
			return sport;
		}




		public void setSport(String sport) {
			this.sport = sport;
		}




		public String getSelfSport() {
			return selfSport;
		}




		public void setSelfSport(String selfSport) {
			this.selfSport = selfSport;
		}




		public String getArt() {
			return art;
		}




		public void setArt(String art) {
			this.art = art;
		}




		public String getIsFinshMessage() {
			return isFinshMessage;
		}




		public void setIsFinshMessage(String isFinshMessage) {
			this.isFinshMessage = isFinshMessage;
		}
		
		
		
		
		
		
		
}
