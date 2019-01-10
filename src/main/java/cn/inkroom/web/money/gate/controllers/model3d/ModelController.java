package cn.inkroom.web.money.gate.controllers.model3d;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ChenGang
 * @Title: ModelController
 * @Package cn.inkroom.web.money.gate.controllers.model3d
 * @Description:
 * @date 2018/3/13 0013 下午 9:59
 **/
@Controller

public class ModelController{
    @RequestMapping("3dModel")
    public String model3d()
    {
        return "/3dModel/3dExample";
    }
}
