package com.nsu.controller;

import com.nsu.bean.common.AjaxBean;
import com.nsu.common.Constants;
import com.nsu.exception.validate.AnalysisException;
import com.nsu.exception.validate.CustomValidateException;
import com.nsu.exception.validate.DataException;
import com.nsu.exception.validate.IllegalFormatException;
import com.nsu.utils.InfoProtUtil;
import com.nsu.utils.V;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 功能描述：基础的Controller
 *
 * @author 梅谢兵
 * @version 1.0 Create Date: 2016-8-31
 */


public class BaseController {

    protected final Log log = LogFactory.getLog(getClass());


    /**
     * 向session中存放密码加密所需要的盐
     *
     * @param session
     */
    protected static void setSaltForSession(HttpSession session) {
        String salt = InfoProtUtil.getRandomString(8);
        session.setAttribute(Constants.SALT_IN_SESSION, salt);
    }

    /**
     * 向session存储数据
     *
     * @param key     存储数据的key值
     * @param value   存储数据的value值
     * @param session
     */
    protected static void setSessionParameter(String key, Object value, HttpSession session) {
        session.setAttribute(key, value);
    }

    /**
     * 向redirectAttributes中存储临时信息
     *
     * @param key                存储数据的key值
     * @param value              存储数据的value值
     * @param redirectAttributes
     */
    protected static void setFlashParameter(String key, Object value, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(key, value);
    }

    /**
     * 表单验证
     *
     * @param value 验证通过返回null，表单验证失败返回500和错误信息的AjaxBean
     *              自定义正则等异常返回带有500和固定错误提示信息的AjaxBean
     * @return
     */
    protected AjaxBean checkForm(Object value) {
        try {
            V.validateByAnnotation(value, "");
            return null;
        } catch (DataException e) {
            return new AjaxBean("500", e.getMessage());
        } catch (AnalysisException | CustomValidateException | IllegalFormatException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new AjaxBean("500", "服务器异常，请联系管理人员！");
        }
    }
}
