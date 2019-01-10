package cn.nsu.edu.web.four.handler.spring;

import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.http.ResponseUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler extends SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        logger.error(ex.toString());
        if (RequestUtil.isAjax(request)) {
            try {
                MessageDto dto = new MessageDto(Result.EXCEPTION);
                dto.setToken((String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_TOKEN));
                ResponseUtil.outJson(response, dto.toString());
            } catch (Exception e) {
                logger.error("输出message异常，{}",e);
            }
            return null;
        } else {
           logger.error("调到500");
            return new ModelAndView("forward:/500");
        }
    }
}
