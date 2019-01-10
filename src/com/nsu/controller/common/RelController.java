package com.nsu.controller.common;

import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: RelController
 * @Package com.nsu.controller.common
 * @Description:
 * @date 5/17/17
 */
@Controller
public class RelController extends BaseController {

    @InterceptorAnno(isRestful = true)
    @RequestMapping(value = "/{path}/no_login")
    public String relMethod(@PathVariable String path, Model model){
        model.addAttribute("path",path);
        return "/other/rel";
    }

    @InterceptorAnno(isRestful = true)
    @RequestMapping(value = "/{path}/error_authority")
    public String errorAuthorityMethod(@PathVariable String path, Model model){
        model.addAttribute("path",path);
        return "/other/authority_error";
    }

    @InterceptorAnno(isRestful = true)
    @RequestMapping(value = "/{path}/error")
    public String errorMethod(@PathVariable String path, Model model){
        model.addAttribute("path",path);
        System.out.println(" this error view");
        return "/other/error";
    }

}
