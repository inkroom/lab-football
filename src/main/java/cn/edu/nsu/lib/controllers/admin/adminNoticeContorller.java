package cn.edu.nsu.lib.controllers.admin;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.leader.LFileBean;
import cn.edu.nsu.lib.bean.leader.LLabBean;
import cn.edu.nsu.lib.bean.student.StuNoticeBean;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.leader.LeaderMangerService;
import cn.edu.nsu.lib.services.student.StuBaseService;
import cn.edu.nsu.lib.services.student.StuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenGang
 * @Title: TeacherNoticeContorller
 * @Package cn.edu.nsu.lib.controllers.teacher
 * @Description:教师notice控制类
 * @date 2017/11/8 0008 下午 2:17
 **/
@Controller
@RequestMapping("/admin/")
public class adminNoticeContorller extends BaseController{
    @Autowired
    LeaderMangerService leaderMangerService;
    @Autowired
    StuNoticeService stuNoticeService;
    @Autowired
    StuBaseService stuBaseService;
    @Autowired
    HttpServletRequest request;
    /*
    *@auther ChenGang
    *@说明：进入上传公告页面
    **/
    @RequestMapping("up_noticePage")
    @Authority(role = Authority.Role.MANAGER)
    public String upnoticePage(Model model)
    {
        try {
            ArrayList<LLabBean> lLabBeans=leaderMangerService.getLabInfoListNoTeacher();
            model.addAttribute("lLaBeans",lLabBeans);
        } catch (Exception e) {
            e.printStackTrace();
            return "/500";
        }
        return "/leader/up_announcement";
    }
    /*
    *@auther ChenGang
    *@说明：公告上传
    **/
    @Value("${local_path}")
    String basePath;
    //上传公告
    @RequestMapping("UploadFile")
    @Authority(role = Authority.Role.MANAGER)
    @ResponseBody
    public AjaxBean fileUpload(LFileBean lFileBean)  {
        if(lFileBean !=null) {
            //获取登录人的ID
            Map<String, Object> loginMap = getLogin(request);
            lFileBean.setPublisher((long)loginMap.get("id"));
            try {
                //获取登录人的信息map
             Map<String ,Object> map= stuBaseService.getStudentInfo((long)loginMap.get("id"));
             //设置公告的实验室id
                lFileBean.setLab_id((int)map.get("lab_id"));
            } catch (Exception e) {
                e.printStackTrace();
             return new AjaxBean(Result.FAIL);
            }
            //先设置正确的系统时间（东八区自动减8小时也是醉了）
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
           // log.info(lFileBean.toString());
            lFileBean.setTime(dateFormat.format(new Date()));
            if(lFileBean.getCommonsMultipartFile()!=null) {
                CommonsMultipartFile file = lFileBean.getCommonsMultipartFile();
                String filename=file.getOriginalFilename();
                //设置文件名
                lFileBean.setFile_name(filename);
                //获取后缀
                String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                //加入时间戳
                String labPath =lFileBean.getPublisher() + "/" +dateFormat.format(new Date())+"."+suffix;
                lFileBean.setFile_path(labPath);
                log.info(lFileBean.toString());
                String path = basePath + "/" + labPath;
                File newFile = new File(path);
                if (!newFile.exists())   //如果目录不存在就创建目录
                    newFile.mkdirs();
                try {
                    file.transferTo(newFile);
                    log.info("上传成功");
                } catch (IOException e) {
                    e.printStackTrace();

                }
                lFileBean.setCommonsMultipartFile(null);
            }
            try {
                leaderMangerService.insertNotice(lFileBean);
                     return  new AjaxBean(Result.LOGIN_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return  new AjaxBean(Result.FAIL);
            }
        }
        return  new AjaxBean(Result.FAIL);
    }
    /*
  *@auther ChenGang
  *@说明：进入查看公告页面
  **/
    @RequestMapping("notice_page")
    @Authority(role = Authority.Role.MANAGER)
    public String lookAnnouncement(Model model)
    {
        List<StuNoticeBean> stuNoticelist = null;
        try {
            stuNoticelist = stuNoticeService.findNoticeList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("noticeList",stuNoticelist );

        return "/leader/look_announcement";
    }
     /*@auther ChenGang
  *@说明：欢迎页面
  **/
    @RequestMapping("welcome")
    @Authority(role = Authority.Role.MANAGER)
    public String welcome()
    {
        return "/administrator/welcome";
    }


}
