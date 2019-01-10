package cn.edu.nsu.lib.controllers.student;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.student.StuBasicBean;
import cn.edu.nsu.lib.bean.student.StuPrizeBean;
import cn.edu.nsu.lib.config.Constants;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.student.StuBaseService;
import cn.edu.nsu.lib.utils.RequestUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StuInfoMangerControllers extends BaseController {
    @Autowired
    private StuBaseService stuBaseService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "submitStuInfo", method = RequestMethod.POST)
    @ResponseBody
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public AjaxBean submitStuInfo(StuBasicBean stuBasicBean) {
//获取登陆ID
        Map<String, Object> login = RequestUtil.getLogin(request);
        stuBasicBean.setId((long) login.get("id"));
        try {
            Map<String, Object> loginMap = stuBaseService.getStudentInfo(stuBasicBean.getId());

            //判断是否完善过信息
            if (loginMap != null) {
                try {
                    //如果完善就更新
                    stuBaseService.reSetStuInfo(stuBasicBean);
                    return new AjaxBean(Result.LOGIN_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxBean(Result.LOGIN_NOT);
                }
            } else {
                try {
                    //如果没有完善就插入
                    stuBaseService.insertStuInfo(stuBasicBean);
                    return new AjaxBean(Result.LOGIN_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxBean(Result.LOGIN_NOT);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new AjaxBean(Result.LOGIN_NOT);
    }

    @RequestMapping(value = "/viewStuInfo", method = RequestMethod.GET)
    @ResponseBody
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public AjaxBean ViewStuInfo() throws Exception {

        Map<String, Object> loginMap = RequestUtil.getLogin(request);
        if (loginMap != null) {
            long id = (long) loginMap.get("id");
            //得到学生基础信息
            Map<String, Object> stuInfoMap = stuBaseService.getStuInfo(id);
            //得到学生获奖信息

            List<StuPrizeBean> stuPrizeList = stuBaseService.getProzeList(id);

            //得到学生成绩信息
            //取消学生成绩录入
         //   List<StuScoreImplBean> stuScoreList = stuBaseService.getStuScoreList(id);


            AjaxBean ajaxBean = new AjaxBean(Result.LOGIN_SUCCESS);

            //把学生信息设置到ajaxbean返回s
            ajaxBean.put("stuInfoMap", stuInfoMap);
            ajaxBean.put("stuPrizeList", stuPrizeList);
          //  ajaxBean.put("stuScoreList", stuScoreList);

            return ajaxBean;
        }
        return new AjaxBean(Result.LOGIN_NOT);
    }

    @RequestMapping(value = "submitPrizeInfo", method = RequestMethod.POST)
    @ResponseBody
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER})
    public AjaxBean submitPrizeInfo(StuPrizeBean stuPrizeBean) throws Exception {

//获取登陆ID
        Map<String, Object> loginMap = getLogin(request);
        try {
            Map<String, Object> map = stuBaseService.getStudentInfo((long) loginMap.get("id"));
            stuPrizeBean.setLab_id((int) map.get(Constants.KAY_MAP_LAB_ID));
            String url = stuPrizeBean.getUrl();
            stuPrizeBean.setUrl(url == null ? "" : (url.startsWith("http://") || url.startsWith("https://") ? url : "http://" + url));
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxBean(Result.LOGIN_NOT);
        }
        if (loginMap.containsKey("id")) {
            stuPrizeBean.setOwner((long) loginMap.get("id"));
            stuPrizeBean.setIs_checked(0);
            stuBaseService.insertPriceInfo(stuPrizeBean);
        } else {
            return new AjaxBean(Result.LOGIN_NOT);
        }
        return new AjaxBean(Result.LOGIN_SUCCESS);
    }

    @Value("${local_path}")
    String basePath;

    @RequestMapping(value = "downLoad", method = RequestMethod.GET)
    @Authority(role = {Authority.Role.STUDENT, Authority.Role.MANAGER, Authority.Role.LEADER, Authority.Role.TEACHER})
    public ResponseEntity<byte[]> uplodeStuFile(String filePath) {
        try {
            String path = basePath + "/" + filePath;
            File file = new File(path);
            String fileName = new String(file.getName().getBytes(Constants.CHARSET), "iso-8859-1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("下载时出现错误");
//            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("下载时出现错误");
        }
        return null;
    }
    //退出实验室功能
    @RequestMapping(value = "exitLab", method = RequestMethod.GET)
    @Authority(role={Authority.Role.STUDENT, Authority.Role.MANAGER})
    @ResponseBody
    public AjaxBean exitLib()
    {
    long stuId =(long) getLogin(request).get("id");
        try {
            stuBaseService.exitLab(stuId);
            return  new AjaxBean(Result.SUCCESS);
        } catch (Exception e) {
            return new AjaxBean(Result.FAIL);
        }

    }
}
