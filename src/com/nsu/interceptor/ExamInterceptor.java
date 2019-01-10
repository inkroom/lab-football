package com.nsu.interceptor;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.student.exam.ExamInfoBean;
import com.nsu.common.Constants;
import com.nsu.common.Key;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.entity.ExamInformation;
import com.nsu.utils.jedis.JedisClientPool;
import com.nsu.utils.student.exame.ExamRecordHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/19
 * @Time 16:15
 * @Descorption
 */
public class ExamInterceptor implements HandlerInterceptor {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private JedisClientPool pool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HandlerMethod method = (HandlerMethod) o;
        log.info(o);
        InterceptorAnno i = method.getMethodAnnotation(InterceptorAnno.class);
        boolean isAjax = i != null && i.isAjax();
        boolean result = true;
        String message = null;
        if (request.getSession().getAttribute(Key.SESSION_EXAM_INFORMATION) == null || request.getSession().getAttribute(Key.SESSION_EXAM_RECORD_HELPER) == null) {
            message = ("未曾参加考试！");
            result = false;
        } else {
            ExamRecordHelper helper = (ExamRecordHelper) request.getSession().getAttribute(Key.SESSION_EXAM_RECORD_HELPER);
            ExamInfoBean examInfo = (ExamInfoBean) request.getSession().getAttribute(Key.SESSION_EXAM_INFORMATION);
            long sId = (long) ((Map) request.getSession().getAttribute(Key.SESSION_USER)).get(Key.MAP_S_ID);
            if (pool.ttl(sId + Key.REDIS_SUFFIX_TIME) == 0 || pool.get(sId + Key.REDIS_SUFFIX_TIME) == null) {
                message = "答题时间已到，交卷失败";
                result = false;
            }
            Date now = new Date();
            if (examInfo.getStartTime().after(now)) {//考试未开始
                message = "考试未开始！";
                result = false;
            }
            if (examInfo.getEndTime().before(now)) {//考试已结束
                message = "考试已结束！";
                result = false;
            }
        }
        if (!result) {
            if (isAjax) {
                AjaxBean ajaxBean = new AjaxBean("500", message);
                response.getOutputStream().write(JsonMapper.getInstance().toJson(ajaxBean).getBytes(Constants.ENCODING));
                return false;
            }
            request.setAttribute("message", message);
            request.getRequestDispatcher(request.getContextPath() + "/message.html").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
