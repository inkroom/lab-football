package cn.edu.nsu.lib.controllers.teacher;

import cn.edu.nsu.lib.annotions.Authority;
import cn.edu.nsu.lib.bean.AjaxBean;
import cn.edu.nsu.lib.bean.teacher.*;
import cn.edu.nsu.lib.controllers.BaseController;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.teacher.TeacherServiceManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * class_name: Teacher_Controller
 * describe: 教师端控制器 用于实现教师端主页
 * creat_user: 蒋玖宏 julse@qq.com
 * creat_date: 2017/9/27
 * creat_time: 21:40
 **/


/**
 * 绕过权限验证，项目完成时删除implement anyone
 */
@Controller
@RequestMapping(value = "/Teacher")

public class TeacherController extends BaseController {
    @Autowired
    private HttpSession seesion;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TeacherServiceManager service;

    private List<LabEntity> mLabEntityList;
    private List<StudentEntity> mStudentEntityList;

    /**
     * 当前教师id
     */
    // private String mT_id = "16310121007";
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @Authority(role = Authority.Role.TEACHER)
    public String toIndex() throws Exception {
        Map<String, Object> loginMap = getLogin(request);

        //是否取得登录信息
        if (loginMap != null) {
            String mT_id = String.valueOf((long) loginMap.get("id"));
            mLabEntityList = service.getTeacherService().findLabList(mT_id);
            request.setAttribute("lablist", mLabEntityList);
            if (loginMap.containsKey("name")) {
                request.setAttribute("username", loginMap.get("name"));
                log.info("设置名字成功" + loginMap.get(("name")));
                //获得实验室详细信息实体类列表

//        readExcel("1");//测试解析excel能否成功
                return "/common/teacher_index";
            }
            //第一次登录没有username，设置为未完善
            else if (loginMap.containsKey("id")) {
                mLabEntityList = service.getTeacherService().findLabList(mT_id);
                request.setAttribute("lablist", mLabEntityList);
                request.setAttribute("username", "请完善信息");
                return "/common/teacher_index";
            }
        }
        //拿不到登录信息自动跳转到登录失效页面
        return "common/500";

    }

    /**
     * method_name: mainpage
     * param: []
     * describe: 跳转到教师端主页，显示实验室信息
     * creat_user: 蒋玖宏 julse@qq.com
     * creat_date: 2017/9/27
     **/
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    @Authority(role = Authority.Role.TEACHER)
    public String toMainpage() throws Exception {
        String mT_id = String.valueOf((long) getLogin(request).get("id"));
        //登陆之后才有
//        String t_id = (String)seesion.getAttribute(Constants.KEY_MAP_USERNAME);
        log.info("该教师id为：" + mT_id);
        //获得实验室详细信息实体类列表
        mLabEntityList = service.getTeacherService().findLabList(mT_id);
        log.info("labEntityList.size:" + mLabEntityList.size());
        if (mLabEntityList != null & mLabEntityList.size() > 0) {
            //将实验室集合发送到页面
            request.setAttribute("lab", mLabEntityList.get(0));
            request.setAttribute("labEntityList", mLabEntityList);//返回实验室集合
            String lab_id = mLabEntityList.get(0).getId();
            //获取第一个实验室对应的学生信息作为默认显示的信息
            mStudentEntityList = service.getTeacherService().findStuList(lab_id);
            //获取该实验室的通知列表
            List<NoticeEntity> noticeEntityList = service.getTeacherService().findNoticeList(lab_id, mT_id);
            //获取该实验室教师信息
            List<TeacherEntity> teacherEntitieList = service.getTeacherService().findTeacherList(lab_id);
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(studentEntityList);
            request.setAttribute("stuList", mStudentEntityList);
            request.setAttribute("noticeEntityList", noticeEntityList);
            request.setAttribute("teacherEntitieList", teacherEntitieList);
//            request.setAttribute("stuList",json);
//            request.setAttribute("stuList","我是后台传来的消息");
//            log.info("json内容 "+json);
            //获取第一个实验室对应的管理员
            StudentEntity stu_admin = service.getTeacherService().findStuInfo(mLabEntityList.get(0).getLab_admin());
            request.setAttribute("stu_admin", stu_admin);
            log.info("学生列表studentEntityList=  " + mStudentEntityList.size());
        }
        log.info("进入mianPage，返回教师端页面时附带的信息");
        return "/teacher/teacherpage";
    }

    /**
     * 处理前端发送的ajax请求
     *
     * @return 包含学生信息列表，实验室名称列表，实验室信息列表，通知列表
     * @throws Exception
     */
    @RequestMapping(value = "/init")
    @ResponseBody
    @Authority(role = Authority.Role.TEACHER)
    public AjaxBean getlab(String data) throws Exception {
      //  String mT_id = String.valueOf((long) getLogin(request).get("id"));
        log.info("data:" + data);
        AjaxBean ajax = null;
        if (mLabEntityList != null & mLabEntityList.size() > 0) {
            if ("labs".equals(data)) {
                ajax = new AjaxBean(Result.SUCCESS);
                ajax.put("labEntityList", mLabEntityList);
            } else {//提交的是实验室的序号
                int i = Integer.parseInt(data);
                String lab_id = mLabEntityList.get(i).getId();
                if (lab_id != null) {
                    List<StudentEntity> studentEntityList = service.getTeacherService().findStuList(lab_id);
                    //获取该实验室对应的管理员
                    StudentEntity stu_admin = service.getTeacherService().findStuInfo(mLabEntityList.get(i).getLab_admin());
                    //获取该实验室的通知列表
                   // List<NoticeEntity> noticeEntityList = service.getTeacherService().findNoticeList(lab_id, mT_id);
                    List<NoticeEntity> noticeEntityList = service.getTeacherService().findNoticeList(lab_id);
                            List<TeacherEntity> teacherEntityList = service.getTeacherService().findTeacherList(lab_id);
                    ajax = new AjaxBean(Result.SUCCESS);
                    //发送学生信息列表
                    ajax.put("stuEntityList", studentEntityList);
                    //发送实验室名称
                    ajax.put("lab_name", mLabEntityList.get(i).getName());
                    //发送实验室管理员id和名称
                    ajax.put("stu_admin_id", stu_admin.getId());
                    //发送通知列表
                    ajax.put("noticeEntityList", noticeEntityList);
                    //发送教师列表
                    ajax.put("teacherEntityList", teacherEntityList);
                }

            }
        } else {
            ajax = new AjaxBean(Result.LAB_NOT_FOUND);
        }
        log.info("返回实验室名称列表");
        return ajax;
    }

    /**
     * 处理ajax通知请求
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/notice")
    @ResponseBody
    @Authority(role = Authority.Role.TEACHER)
    public AjaxBean getNotice(String data) throws Exception {
        String mT_id = String.valueOf((long) getLogin(request).get("id"));
        log.info("data:" + data);
        AjaxBean ajax = null;
        if (mLabEntityList != null & mLabEntityList.size() > 0) {
            //提交的是实验室的序号
            int i = Integer.parseInt(data);
            String lab_id = mLabEntityList.get(i).getId();
            if (lab_id != null) {
                //获取该实验室的通知列表
                List<NoticeEntity> noticeEntityList = service.getTeacherService().findNoticeList(lab_id, mT_id);
                ajax = new AjaxBean(Result.SUCCESS);
                //发送通知列表
                ajax.put("noticeEntityList", noticeEntityList);
            }
        } else {
            ajax = new AjaxBean(Result.NOTICE_NOT_FOUND);
        }
        log.info("返回实验室名称列表");
        return ajax;
    }

    @RequestMapping(value = "/stu_info", method = RequestMethod.GET)
    @Authority(role = Authority.Role.TEACHER)
    public String getStuInfo(String stu_id) throws Exception {
        log.info("学生序号是：" + stu_id);
        StudentEntity stu = service.getTeacherService().findStuInfo(stu_id);
        List<PrizeEntity> prizeEntityList = service.getTeacherService().findPrizeList(stu_id);
        List<ScoreEntity> scoreEntityList = service.getTeacherService().findCourseList(stu_id);
        request.setAttribute("stu", stu);
        request.setAttribute("prizeList", prizeEntityList);
        request.setAttribute("scoreList", scoreEntityList);
        return "teacher/stuInfo";
    }

    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    @ResponseBody
    @Authority(role = Authority.Role.TEACHER)
    public AjaxBean addNotice(String noticeTitle, String noticeContent, @RequestParam(value = "noticeFile", required = false) CommonsMultipartFile noticeFile, String[] choose_lab_cb) throws Exception {
        String mT_id = String.valueOf((long) getLogin(request).get("id"));
        log.info("添加通知：" + noticeTitle + noticeFile.getName() + noticeContent + noticeFile.getOriginalFilename() + choose_lab_cb[0]);
        NoticeEntity notice = new NoticeEntity();
        notice.setTitle(noticeTitle);
        notice.setContent(noticeContent);
        notice.setFile_name(noticeFile.getOriginalFilename());
        notice.setPublisher(mT_id);
        notice.setFile_name(noticeFile.getOriginalFilename());
        AjaxBean ajax = null;
        if (!noticeFile.isEmpty()) {
            String path = uploadFile(noticeFile, "zip");
            log.info("path = " + path);
            notice.setFile_path(path);
        } else {//没有文件传输
            ajax = new AjaxBean(Result.SUCCESS);
        }
        service.getTeacherService().addNotice(notice, choose_lab_cb);
        return ajax;
    }

    @RequestMapping(value = "/upload_stu", method = RequestMethod.POST)
    @ResponseBody
    @Authority(role = Authority.Role.TEACHER)
    public AjaxBean upload_stu(String lab_id, @RequestParam(value = "stu_excel", required = false) CommonsMultipartFile stu_excel_file) throws Exception {
        log.info("上传学生信息" + lab_id);
        AjaxBean ajax = new AjaxBean(Result.SUCCESS);
        int count = readExcel(stu_excel_file, lab_id,ajax);
        ajax.put("count",count+"");
        return ajax;
    }

//    public static void main(String[] args) {
//
//
//
//        Workbook vb= null;
//        try {
//            vb = new XSSFWorkbook(new FileInputStream("E:\\file\\upload\\notice\\学号姓名.xlsx"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Cell thecell = vb.getSheetAt(0).getRow(2).getCell(0);
//        vb.getSheetAt(0).getRow(2).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
//        String cell = vb.getSheetAt(0).getRow(2).getCell(0).getStringCellValue();
//        System.out.println(cell);
//    }

    /**
     * 读取excel
     *
     * @return
     */
    private int readExcel(CommonsMultipartFile file, String lab_id, AjaxBean ajax) {
//        Workbook vb = new XSSFWorkbook(new FileInputStream("E:\\file\\upload\\notice\\学号姓名.xlsx"));
        Workbook vb = null;
        try {
            vb = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            log.info("获取文件流异常");
        }
        Sheet sheet = vb.getSheetAt(0);//excel工作簿第一页
        int row_sum = sheet.getLastRowNum();//获取excel行数
        log.info("excel行数：" + row_sum);
        int count = 0;
        for (int i = 1; i <= row_sum; i++) {//第一行是标题
            Row row = sheet.getRow(i);//解析第i行
            Cell cell = row.getCell(0);//将学号列设置为字符串格式
            cell.setCellType(Cell.CELL_TYPE_STRING);
            StudentEntity stu = new StudentEntity();
            stu.setId(row.getCell(0).getStringCellValue());//学号
            stu.setName(row.getCell(1).getStringCellValue());//姓名
            stu.setLab_id(lab_id);
            if(stu.getId().length()==11){
                count += service.getTeacherService().addStu(stu);
            }else{
                log.info(stu.getName()+"的学号不合法");
            }
        }
        ajax.put("fail_count",(row_sum-1-count)+"");
        log.info("插入学生信息条数：count:" + count);
        return count;
    }

}
