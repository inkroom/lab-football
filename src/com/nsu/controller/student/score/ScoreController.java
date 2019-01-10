package com.nsu.controller.student.score;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.student.score.SchoolBean;
import com.nsu.bean.student.score.ScoreBean;
import com.nsu.bean.student.score.ScoreSearchBean;
import com.nsu.common.Anonymous;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.controller.student.classmanage.ClassManagerController;
import com.nsu.service.student.score.ScoreService;
import com.nsu.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;


/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/13
 * @Time 17:12
 * @Descorption 积分榜功能controller
 */
@Controller
@RequestMapping("/student/score/")
public class ScoreController extends BaseController implements Anonymous {

    public static final String KEY_SCHOOL = "_KEY_SCHOOL_";
    public static final String KEY_SCORE = "_KEY_SCORE_";
    public static final String KEY_NUMBER = "_KEY_NUMBER_";

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private HttpServletRequest request;
//    @Resource
//    RedirectAttributes redirectAttributes;

    @RequestMapping(value = {"index"})
    @InterceptorAnno(createToken = true)
    public String toIndex() {
//        System.out.println("redirectAttributes  = " + redirectAttributes);
        try {
            String mySchool = ((Map) request.getSession().getAttribute(ClassManagerController.KEY)).get(KEY_SCHOOL).toString();
            List<SchoolBean> schools = scoreService.getSchools();
            log.info("schools = " + schools);
//            ScoreBean myScore = scoreService.getMyScore(10L, scores);

//            request.setAttribute("totalPage", scores.size() / Constants.DEFAULT_PAGE_SIZE);
//            request.setAttribute("nowPage", 0);

            request.setAttribute("school", mySchool);
            request.setAttribute("schools", schools);
//            request.setAttribute("myScore", myScore);

            log.info("name = " + request.getParameter("name"));
            log.info("value = " + request.getParameter("value"));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "redirect:\\1\\error.html";
        }
        return "student/scoreBoard";
    }

    public AjaxBean init() {
        AjaxBean ajax = new AjaxBean("200");

        try {
            String mySchool = ((Map) request.getSession().getAttribute(ClassManagerController.KEY)).get(KEY_SCHOOL).toString();
            List<ScoreBean> scores = scoreService.getScores(null, false, false, mySchool);

            ajax.put("scores", Constants.DEFAULT_PAGE_SIZE > scores.size() ? scores : scores.subList(0, Constants.DEFAULT_PAGE_SIZE));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new AjaxBean("500", "服务器异常，请稍后重试！");
        }

        return ajax;
    }


    @InterceptorAnno(checkToken = true, isAjax = true)
    @RequestMapping(value = {"search"}, produces = "application/json;charset=" + Constants.ENCODING)
    @ResponseBody
    public AjaxBean search(ScoreSearchBean searchBean) {
//        String _csrs = "";
//        log.info("isBlur = " + isBlur + "  isStudent = " + isStudent + "  content = " + content + "   schoolName = " + schoolName);
        log.info(searchBean.toString() + "  ---- " + searchBean.getSchoolName() + "  ----" + searchBean.getNow());
        List<ScoreBean> scores;
        AjaxBean ajax = new AjaxBean();
        try {
            if (VerifyUtil.isNotEmpty(searchBean.getSchoolName())) {//查看指定学校名称下的排行榜
                log.info(" ---- 查看指定学校名称下的排行榜");
                scores = scoreService.getScores(null, false, false, searchBean.getSchoolName());
                ajax.setStatus("200");
                ajax.put("totalPage", scores.size() / Constants.DEFAULT_PAGE_SIZE + 1);
                ajax.put("nowPage", searchBean.getNow());
                ajax.put("scores", scores.subList((searchBean.getNow() - 1) * Constants.DEFAULT_PAGE_SIZE,
                        (searchBean.getNow() * Constants.DEFAULT_PAGE_SIZE) > scores.size() ?
                                scores.size() : searchBean.getNow() * Constants.DEFAULT_PAGE_SIZE));
                log.info("  scores =  " + scores.toString());
                return ajax;
            }
            //输入搜索条件
            ajax = checkForm(searchBean);
            if (ajax != null && "500".equals(ajax.getStatus()))
                return ajax;
            log.info(" ---- 搜索下面的数据");
            ajax = new AjaxBean("200");
            scores = scoreService.getScores(searchBean.getContent(),
                    searchBean.getIsStudent() == 1, searchBean.getIsBlur() == 1, null);
            ajax.put("totalPage", scores.size() / Constants.DEFAULT_PAGE_SIZE + 1);
            ajax.put("nowPage", searchBean.getNow());
            ajax.put("scores", scores.subList((searchBean.getNow() - 1) * Constants.DEFAULT_PAGE_SIZE,
                    (searchBean.getNow() * Constants.DEFAULT_PAGE_SIZE) > scores.size() ?
                            scores.size() : searchBean.getNow() * Constants.DEFAULT_PAGE_SIZE));
            return ajax;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new AjaxBean("500", "服务器异常，请稍后重试！");
        }
//        return new AjaxBean("500", "服务器异常，请稍后重试！");
    }

//    private List<?> page(List<?> list, int nowPage, HttpServletRequest request) {
//        request.setAttribute("totalPage", list.size() / Constants.DEFAULT_PAGE_SIZE);
//        return list.subList(nowPage * Constants.DEFAULT_PAGE_SIZE,
//                (nowPage + 1) * Constants.DEFAULT_PAGE_SIZE);
//    }

//    public static void main(String[] args) {
//        del("D:\\temp\\qq\\245900986\\FileRecv\\augone\\test");
////        rename("D:\\temp\\qq\\245900986\\FileRecv\\学生端前端页面\\test\\项目网站\\class_add.html");
////        addPrev("D:\\temp\\qq\\245900986\\FileRecv\\学生端前端页面\\test\\项目网站\\class_add.html","<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\" %>");
//    }
//
//    private static void del(String path) {
//        File file = new File(path);
//        if (file.isDirectory()) {
//            File files[] = file.listFiles();
//            for (File item : files) {
//                del(item.getAbsolutePath());
//            }
//            return;
//        }
//        if (file.getName().contains(".html")) {
////            file.delete();
//            addPrev(file.getAbsolutePath(), "<%@ page language=\\\"java\\\" contentType=\\\"text/html; charset=UTF-8\\\" pageEncoding=\\\"UTF-8\\\" %>");
//            rename(file.getAbsolutePath());
//        }
//        if (file.getName().contains("(1)."))
//            file.delete();
//    }
//
//
//    private static void addPrev(String path, String head) {
//        StringBuilder builder = new StringBuilder(head + "\n");
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(path));
//            String temp;
//            while ((temp = reader.readLine()) != null) {
//                builder.append(temp);
//                builder.append("\n");
//            }
//            reader.close();
//            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
//            writer.write(builder.toString());
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void rename(String path) {
//        File file = new File(path.substring(0, path.lastIndexOf(".html")) + ".jsp");
//        new File(path).renameTo(file);
//    }
//
//    @RequestMapping(value = "test")
//    @ResponseBody
//    public AjaxBean test(User user) {
//        log.info("   ---- "+user);
//        return new AjaxBean("500", "测试");
//    }


}
