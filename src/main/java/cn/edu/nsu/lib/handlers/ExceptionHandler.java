package cn.edu.nsu.lib.handlers;

import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.utils.RequestUtil;
import cn.edu.nsu.lib.utils.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/16
 * @Time 16:48
 * @Descorption
 */
public class ExceptionHandler extends SimpleMappingExceptionResolver {
    private Log logger = LogFactory.getLog(getClass());


    private String message;

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
//        log.debug("  o = " + o.getClass() + "   exception = " + e);
//        e.printStackTrace();
        MessageException messageException = null;
//        try {
        if (e instanceof MessageException) {
            messageException = (MessageException) e;
            if (messageException.getException() != null) {
                messageException.getException().printStackTrace();
                logger.error(messageException.getException());
            }
        } else {
            e.printStackTrace();
        }
//        } catch (NoSuchMessageException e1) {
//            message = e.getMessage();
//        }
        if (o instanceof HandlerMethod) {
            if (RequestUtil.isAjax(httpServletRequest)) {
                try {
                    ResponseUtil.outJson(httpServletResponse,
                            new AjaxBean(messageException == null ?
                                    Result.EXCEPTION : messageException.getResult(),
                                    messageException == null ? null : messageException.getMessage()).toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                return null;
            } else {
                ModelAndView modelAndView = new ModelAndView(Constants.URL_MESSAGE);
                modelAndView.addObject(Constants.KEY_REQUEST_MESSAGE, e.getMessage() + "    " + e);
                return modelAndView;
            }
        }
        return null;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
