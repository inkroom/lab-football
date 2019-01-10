package cn.edu.nsu.lib.controllers;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.handlers.Anyone;
import cn.edu.nsu.lib.handlers.MessageException;
import cn.edu.nsu.lib.handlers.PropertiesPlaceholder;
import cn.edu.nsu.lib.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 22:31
 * @Descorption
 */
@Controller
//集成Anyone接口，将会绕过所有拦截器
public class TestController extends BaseController implements Anyone {
    @Autowired
    private TestService testService;//注入接口即可

    @RequestMapping("/index")
    public String index() throws Exception {
        System.out.println(" ----进入首页");
        log.info("------ 进入首页");
        testService.testDao();
        return "test";
    }


    @RequestMapping("/test")
    @ResponseBody
//    @Authority//默认为学生权限，不使用该注解则任何人可以访问该url
    @Authority(role = Authority.Role.LEADER)  //仅有leader权限可访问
//    @Authority(role = {Authority.Role.MANAGER, Authority.Role.TEACHER})//管理员和教师均可访问
    public AjaxBean testJson(String data) throws Exception {
        System.out.println("进入test");
        log.info("进入test");
        if ("error".equals(data)) {
            log.info("此时抛出异常，");
            throw new MessageException(Result.EXCEPTION);
        }
//        try{
//            //这里是某个语句，可以预测异常类型
//        }catch (Exception e){
        //将已知异常类型进行封装再抛出
//        throw new MessageException("test.fail",Result.FILE_EXISTS);
//            throw new MessageException("test.fail",e);
//        }
        log.info("一切正常");
        AjaxBean ajax = new AjaxBean(Result.FAIL);
        ajax.put("key", "额外的消息");//使用该方法传递额外数据，前台ajax访问为data.data.key，
        return ajax;
    }
}
