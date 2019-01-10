package cn.inkroom.web.money.gate.aop;

import cn.inkroom.web.money.gate.utils.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * 控制层AOP，记录ip，url，参数，返回值
 */
public class ControllerAop implements AfterReturningAdvice {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object org) throws Throwable {


        String url = getUrl(org);
        StringBuilder builder = new StringBuilder("ip:");
        builder.append(getIp(org));
        builder.append(",url:");
        if (url != null) {
            builder.append(url);
        } else {
            builder.append("无法获取");
        }
        builder.append(",参数为：{");

        builder.append(getParameter(org));
//        for (int i = 0; i < objects.length; i++) {
//            builder.append("\"");
//            builder.append(objects[i]);
//            builder.append("\"");
//            if (i != objects.length - 1)
//                builder.append(",");
//        }
        builder.append("}");
        if (returnValue != null) {
            builder.append(",返回值:");
            builder.append(returnValue.toString());
//            builder.append(returnValue.getClass());
        }
        log.info(builder.toString());
    }


    private String getIp(Object org) {
        return RequestUtil.getIp(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
    }

    /**
     * 获取参数列表
     *
     * @param org
     * @return
     */
    private String getParameter(Object org) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Enumeration<String> names = request.getParameterNames();
        StringBuilder builder = new StringBuilder();
        while (names.hasMoreElements()) {
            String temp = names.nextElement();
            builder.append("[");
            builder.append(temp);
            builder.append(":");
            builder.append(request.getParameter(temp));
            builder.append("]");
            builder.append(",");
        }
//        log.info("获取的参数信息："+builder.toString());
        //去掉最后一个分隔符
        if (builder.length() > 0)
            builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 获取访问路径
     *
     * @param org
     * @return
     */
    private String getUrl(Object org) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRequestURL().toString();
//        ServletWebRequest servletWebRequest=new ServletWebRequest(request);
//        HttpServletResponse response=servletWebRequest.getResponse();
//
//        log.info(request.getRequestURI());
//        //获取所有成员变量
//        try {
//            try {
//                org = AopTargetUtils.getTarget(org);
//                log.info("原始class=" + AopTargetUtils.getTarget(org).toString() + "    " + AopTargetUtils.getTarget(org).getClass());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Field fields[] = org.getClass().getFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                log.info("成员变量=" + AopTargetUtils.getTarget(field.get(org)).getClass().toString());
//                if (field.get(org) instanceof HttpServletRequest) {
//                    return ((HttpServletRequest) field.get(org)).getRequestURL().toString();
//                }
//            }
//
//            fields = org.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                log.info("成员变量=" + AopTargetUtils.getTarget(field.get(org)).getClass().toString());
//                if (field.getDeclaringClass().equals(HttpServletRequest.class)) {
//                if (field.get(org) instanceof HttpServletRequest) {
//                    return ((HttpServletRequest) field.get(org)).getRequestURL().toString();
//                }
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;

    }
}
