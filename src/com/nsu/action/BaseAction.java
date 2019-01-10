package com.nsu.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsu.common.Constants;
import com.nsu.common.FestivalTime;
import com.nsu.service.ServiceManager;
import com.nsu.util.core.InfoProtUtil;
import com.opensymphony.xwork2.Action;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseAction implements Action {

    protected String isFestival = FestivalTime.isFestival(FestivalTime.AddFestival());
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    protected ServiceManager serviceManager;
    /* 存放user信息  */
    protected Map<String, Object> user = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getLoginUser() {
        return (Map<String, Object>) getSession().getAttribute(Constants.LOGIN_USER);
    }

    protected String getSchoolUrl() {
        return getLoginUser().get(Constants.SCHOOL_URL).toString();
    }
    /* 日志 */
    protected final Log log = LogFactory.getLog(getClass());

    /* 默认返回SUCCESS */
    public String execute() throws Exception {
        return SUCCESS;
    }

    public HttpSession getSession() {
//		return ActionContext.getContext().getSession();
        return request.getSession();
    } // 封装为Map的session attributes

//	protected HttpSession getSession(boolean create){
//		if(create)ActionContext.getContext().getSession();
//		return ActionContext.getContext().getSession();
//	}

    public HttpServletRequest getRequest() {
        return request;
    } // 原始的request

    public HttpServletResponse getResponse() {
        return response;
    } // 原始的response

    public ServletContext getServletContext() {
        return request.getServletContext();
    } // 原始的ServletContext

//    public ActionContext getActionContext() {
//        return ActionContext.getContext();
//    } // 原始的ServletContext

//	public Map<String, Object> getApplication() {
//		return ActionContext.getContext().getApplication();
//	} // 封装为Map的application

//    //用于异步方式向客户端写回信息
//    protected void writeRespInfo(String info) throws IOException {
//        HttpServletResponse response = getResponse();
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out = response.getWriter();
//        out.println(info);
//        out.flush();
//        out.close();
//    }
//	/*
//	 *授予客户端token令牌
//	 */
//	protected void setToken(){
//		Cookie cookie = new Cookie("token",InfoProtUtil.getRandomString(16));
//		cookie.setPath("/");
//		getResponse().addCookie(cookie);
//		getRequest().getSession().setAttribute("token", cookie.getValue());
//	}

    //向session中存放密码加密所需要的盐
    protected void setSaltForSession() {
        String salt = InfoProtUtil.getRandomString(8);
        getSession().setAttribute(Constants.SALT_IN_SESSION, salt);
    }


    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    private String getAddr(HttpServletRequest request) {
        //淘宝IP地址库：http://ip.taobao.com/instructions.php
        String add = null;
        String ip = getIp(request);
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            URLConnection connection = U.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String, Object> map = (Map) jsonObject;
            String code = String.valueOf(map.get("code"));//0：成功，1：失败。
            if ("1".equals(code)) {//失败
                String data = String.valueOf(map.get("data"));//错误信息
            } else if ("0".equals(code)) {//成功
                Map<String, Object> data = (Map<String, Object>) map.get("data");

                String country = String.valueOf(data.get("country"));//国家
                String area = String.valueOf(data.get("area"));//
                String city = String.valueOf(data.get("city"));//省（自治区或直辖市）
                String region = String.valueOf(data.get("region"));//市（县）
                add = country + "-" + city + "-" + region;
            }
            return add;
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return "异常";
        } catch (IOException e) {
            e.printStackTrace();
            return "异常";
        }

    }


    public void saveLog(HttpServletRequest request, String username, String type) {
        String ip = getIp(request);
        String addr = getAddr(request);
        getServiceManager().getLogService().saveLogs(ip, addr, ip, type, username);
    }


    public void saveCount(String schoolUrl) {
        getServiceManager().getLogService().saveCounts(schoolUrl);
    }


}
