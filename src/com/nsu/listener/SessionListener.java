package com.nsu.listener;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
import com.nsu.common.Constants;

public class SessionListener implements HttpSessionListener {

	private static final Logger log = Logger.getLogger(SessionListener.class);

	// 集合对象，保存session 对象的引用
	private static HashMap hm = new HashMap();

	// 实现HttpSessionListener接口，完成session创建事件控制
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		hm.put(session.getId(), session);
		log.debug("*******************create session :" + session.getId());
	}

	// 实现HttpSessionListener接口，完成session销毁事件控制
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		Map user = (Map) session.getAttribute(Constants.LOGIN_USER);

		hm.remove(session.getId());
		log.debug("*******************destory session :" + session.getId());
	}

	// 返回全部session对象集合
	public static Iterator getSet() {
		return hm.values().iterator();
	}

	// 依据session id返回指定的session对象
	public static HttpSession getSession(String sessionId) {
		return (HttpSession) hm.get(sessionId);
	}

}