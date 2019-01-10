package cn.edu.nsu.lib.controllers.admin;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.services.leader.LeaderMangerService;
import cn.edu.nsu.lib.services.student.StuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ChenGang
 * @Title: AdminIndexController
 * @Package cn.edu.nsu.lib.controllers.admin
 * @Description:
 * @date 2017/11/8 0008 下午 5:20
 **/
@Controller
@RequestMapping("/admin/")
public class AdminIndexController extends BaseController{
    @Autowired
    HttpServletRequest request;
    @Autowired
    LeaderMangerService leaderMangerService;
    @Autowired
    private StuNoticeService stuNoticeService;
    //跳转页面
    @RequestMapping("index")
    @Authority(role = Authority.Role.MANAGER)//只有管理员端可以访问
    public String AdminIndex()
    {
        log.info("进入管理者界面");
        Map<String, Object> loginMap = getLogin(request);
        //是否取得登录信息
        if(loginMap!=null)
        {
            log.info("loginMap不为空");

            if(loginMap.containsKey("name")){
                request.setAttribute("username",loginMap.get("name"));
                log.info("设置名字成功"+loginMap.get(("name")));
                return "/common/admin_index";
            }
            //第一次登录没有usernamm没有填写，设置为未完善
            else if(loginMap.containsKey("id"))
            {
                request.setAttribute("username","未完善");
                log.info("登陆成功");
                return "/common/admin_index";
            }
        }
        //拿不到登录信息自动跳转到登录失效页面
        return  "common/500";

    }
    @RequestMapping("up_notices")
    @Authority(role = Authority.Role.MANAGER)//只有管理员端可以访问
    public String up_notice()
    {

        return  "administrator/up_announcement";

    }
}
