package cn.edu.nsu.lib.controllers.leader;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.leader.LAccountBean;
import cn.edu.nsu.lib.bean.leader.LFileBean;
import cn.edu.nsu.lib.bean.leader.LLabBean;
import cn.edu.nsu.lib.bean.leader.LTeacherBean;
import cn.edu.nsu.lib.bean.student.StuNoticeBean;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.leader.LeaderMangerService;
import cn.edu.nsu.lib.services.student.StuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenGang
 * @Title: LeaderController
 * @Package cn.edu.nsu.lib.controllers.Leader
 * @Description:
 * @date 2017/11/3 0003 下午 10:06
 **/
@Controller
@RequestMapping("/leader/")
public class LeaderController extends BaseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    LeaderMangerService leaderMangerService;
    @Autowired
    private StuNoticeService stuNoticeService;

    //跳转页面
    @RequestMapping("index")
    @Authority(role = Authority.Role.LEADER)//只有领导端可以访问
    public String labIndex() {
        log.info("进入领导界面");
        Map<String, Object> loginMap = getLogin(request);
        //是否取得登录信息
//        if (loginMap != null) {
        log.info("loginMap不为空");

        if (loginMap.containsKey("name")) {
            request.setAttribute("username", loginMap.get("name"));
            log.info("设置名字成功" + loginMap.get(("name")));
            return "/common/leader_index";
        }
        //第一次登录没有usernamm没有填写，设置为未完善
        else if (loginMap.containsKey(Constants.KEY_MAP_ID)) {
            request.setAttribute("username", "领导端");
            log.info("登陆成功");
            return "/common/leader_index";
        }
//        }
//        //拿不到登录信息自动跳转到登录失效页面
        return "common/500";

    }

    @RequestMapping("lab_mange/{lId}")
    @Authority(role = Authority.Role.LEADER)
    public String labManage(@PathVariable("lId") long lId, Model model) {
        try {
            LLabBean labInfo = leaderMangerService.getLabInfo(lId);

            log.info(labInfo);
            model.addAttribute("labInfo", labInfo);
            model.addAttribute("teacherInfoList", leaderMangerService.getTeacherInfoList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/leader/lab_mange";
    }

    @RequestMapping("lad_add")
    @Authority(role = Authority.Role.LEADER)
    public String labAdd() {
        return "leader/lab_add";
    }

    //显示实验室列表
    @RequestMapping("lad_list")
    @Authority(role = Authority.Role.LEADER)
    public String labList(Model model) {
        try {
            List<LLabBean> labInfoList = leaderMangerService.getLabInfoList();
            log.info(labInfoList);
            model.addAttribute("labInfoList", labInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "leader/lab_list";
    }

    @RequestMapping("leader_index01")
    @Authority(role = Authority.Role.LEADER)
    public String labIndex01() {
        return "/leader/leader_index01";
    }

    @RequestMapping("look_announcement")
    @Authority(role = Authority.Role.LEADER)
    public String lookAnnouncement(Model model) {
        AjaxBean ajaxBean = new AjaxBean(Result.SUCCESS);
        List<StuNoticeBean> stuNoticelist = null;
        try {
            stuNoticelist = stuNoticeService.findNoticeList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("noticeList", stuNoticelist);

        return "/leader/look_announcement";
    }

    @RequestMapping("teacher_add")
    @Authority(role = Authority.Role.LEADER)
    public String teacherAdd() {
        return "/leader/teacher_add";
    }

    @RequestMapping("teacher_cipher/{id}")
    @Authority(role = Authority.Role.LEADER)

    public String teacherCipher(@PathVariable("id") long id, Model model) throws Exception {
        LTeacherBean lTeacherBean = leaderMangerService.getTeacherInfo(id);
        if(lTeacherBean==null)
        {
            return  "/leader/teacher_mange";
        }
        model.addAttribute("lTeacherBean", lTeacherBean);
        return "/leader/teacher_cipher";
    }

    @RequestMapping("teacher_mange")
    @Authority(role = Authority.Role.LEADER)
    public String teacher_mange(Model model) {
        try {
            List<LTeacherBean> teacherInfoList = leaderMangerService.getTeacherInfoList();
            log.info(teacherInfoList);
            model.addAttribute("teacherInfoList", teacherInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/leader/teacher_mange";
    }

    @RequestMapping("up_announcement")
    @Authority(role = Authority.Role.LEADER)
    public String upAnnouncement(Model model) {
        try {
            ArrayList<LLabBean> lLabBeans = leaderMangerService.getLabInfoListNoTeacher();
            model.addAttribute("lLaBeans", lLabBeans);
        } catch (Exception e) {
            e.printStackTrace();
            return "/common/500";
        }
        return "/leader/up_announcement";
    }

    //添加实验室
    @Authority(role = Authority.Role.LEADER)
    @RequestMapping(value = "subLabInfo", method = RequestMethod.POST)
    public @ResponseBody
    AjaxBean setLabInfo(LLabBean lLabBean) {
        try {
            leaderMangerService.setLabInfo(lLabBean);
            log.info(lLabBean);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            return new AjaxBean(Result.FAIL);
        }
    }

    //根据实验室给实验室添加老师
    @Authority(role = Authority.Role.LEADER)
    @RequestMapping(value = "addLabTeacher")
    @ResponseBody
    public AjaxBean addLabTeacher(long lId, long tId) {
        log.info(lId + "________________" + tId);

        try {

            leaderMangerService.addLabTeacher(lId, tId);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.FAIL);
        }
    }

    //修改密码
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxBean resetPassword(long id) {

        try {

            leaderMangerService.resetPassword(id);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.FAIL);
        }

    }

    //删除老师
    @RequestMapping("delTeacher/{tId}")
    @Authority(role = Authority.Role.LEADER)
    @ResponseBody
    public AjaxBean delTeacher(@PathVariable("tId") long lId) {

        try {
            leaderMangerService.delTeacher(lId);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.FAIL);
        }

    }

    @Value("${local_path}")
    String basePath;

    //上传公告
    @RequestMapping("UploadFile")
            public String fileUpload(LFileBean lFileBean, Model model) {
                if (lFileBean != null) {
                    Map<String, Object> loginMap = getLogin(request);
                    lFileBean.setPublisher((long) loginMap.get("id"));
                    //先设置正确的系统时间（东八区自动减8小时也是醉了）
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            // log.info(lFileBean.toString());
            lFileBean.setTime(dateFormat.format(new java.util.Date()));
            if (lFileBean.getCommonsMultipartFile() != null) {
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
                } catch (IOException e) {
                    e.printStackTrace();

                }
                lFileBean.setCommonsMultipartFile(null);
            }
            try {
                leaderMangerService.insertNotice(lFileBean);
                model.addAttribute("result", new AjaxBean(Result.LOGIN_SUCCESS));
            } catch (Exception e) {
                model.addAttribute("result", new AjaxBean(Result.FAIL));
                e.printStackTrace();
            }
        }
        return "/leader/200";
    }

    //新建老师
    @RequestMapping("addTeacher")
    @Authority(role = Authority.Role.LEADER)
    @ResponseBody
    public AjaxBean addTeacher(LAccountBean lAccountBean) {
        try {
            leaderMangerService.addTeacher(lAccountBean);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.FAIL);
        }
    }

    //删除实验室
    @RequestMapping("delLib")
    @Authority(role = Authority.Role.LEADER)
    @ResponseBody
    public AjaxBean delLab(int lab_id) {

        try {
            log.info(lab_id);
            leaderMangerService.delLab(lab_id);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.FAIL);
        }

    }

    //从实验室移除老师
    @RequestMapping("removeTeacher")
    @Authority(role = Authority.Role.LEADER)
    @ResponseBody
    public AjaxBean removeTeacher(long t_id, long lab_id) {

        try {
            log.info(t_id + lab_id);
            leaderMangerService.removeTeacher(t_id, lab_id);
            return new AjaxBean(Result.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.FAIL);
        }

    }
}