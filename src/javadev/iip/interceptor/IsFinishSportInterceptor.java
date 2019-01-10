package javadev.iip.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import javadev.core.Constants;
import javadev.core.bean.BeanManager;
import javadev.core.common.Anonymous;
import javadev.core.util.InfoProtUtil;
import javadev.core.util.QueryUtil;
import javadev.iip.service.IsPassService;
import javadev.iip.service.ServiceManager;
import javadev.iip.service.UserService;

/**
 * 登录拦截器，用于阻止未登录用户访问系统
 */
public class IsFinishSportInterceptor implements Interceptor {
	protected final Log log = LogFactory.getLog(getClass());

	IsPassService isPassService;

	public void destroy() {
	}

	public void init() {
		isPassService = ((ServiceManager) BeanManager.getBean("serviceManager")).getIsPassService();
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		
		
		String o_id = null;
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			return Action.LOGIN;
		}
		
		Map<String, Object> user = (Map<String, Object>) ActionContext.getContext().getSession().get(Constants.LOGIN_USER);
		if (user == null) {
			return Action.LOGIN;
		}
		o_id = user.get("O_ID").toString();
		String status = isPassService.getAuditStatusSport(o_id);
		
		
		
		if (status.equals("1")) {
			ActionContext.getContext().getSession().put("isFinshMessage", "体育报表已经审核通过,请在汇总数据下查看。");
			return "index";
		}
		
		return invocation.invoke();
	}

}
