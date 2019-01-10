package javadev.iip.action;

import java.util.Map;

import javadev.core.Constants;
import javadev.core.bean.BeanManager;
import javadev.core.common.CommonBaseAction;
import javadev.iip.service.ServiceManager;

public class BaseAction extends CommonBaseAction {

	/* ServiceManager */
	public ServiceManager getServMgr() {
		return (ServiceManager) BeanManager.getBean("serviceManager");
	}

	/* 获取登录用户对象 */
	public Map getLoginUser() {
		return (Map) getSession().get(Constants.LOGIN_USER);
	}

	public Long getLoginUserId() {
		// TODO Auto-generated method stub
		return Long.parseLong(getSession().get(Constants.LOGIN_USER_ID).toString());
	}
}
