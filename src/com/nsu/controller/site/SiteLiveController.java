package com.nsu.controller.site;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nsu.staticvar.CommonVar;
import com.nsu.staticvar.SiteVar;
import com.nsu.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.common.UploadBaseController;
import com.nsu.service.site.SiteLiveService;
import com.nsu.service.site.SiteService;
import com.nsu.util.V;
import com.nsu.util.jedis.JedisClient;

/**
 * 现场直播controller
 *
 * @author 刘俊
 */
@Controller
@RequestMapping("/site")
public class SiteLiveController extends UploadBaseController {

    private final String ROLE = "site";
    private final String FILE_TYPE = "image";
    @Autowired
    private JedisClient JedisClient;
    @Autowired
    SiteLiveService service;

    @Autowired
    SiteService siteService;

    /**
     * 进入主页面
     *
     * @param model
     * @param session
     * @return
     * @throws ParseException
     */
    @InterceptorAnno(createToken = true)
    @RequestMapping("gameLive")
    public String gameLive(Model model, HttpSession session) throws ParseException {

        int A_ID = Integer.parseInt(RequestUtil.getAccountInfoInSession(session, CommonVar.Account.ID));
        if (V.checkEmpty(A_ID)) {
            model.addAttribute("info", "nosession");
        } else {
            int l_score;
            int r_score;
            String HTeam_Name;
            String VTeam_Name;
            long RID = 0;
            try {
                RID = service.selectRID(A_ID);
                ((Map) session.getAttribute(Constants.LOGIN_USER)).put("RID", RID);
                Map<String, Object> infoBefore = service.selectInfoBeforeGame(A_ID);
                long nowTime = new Date().getTime();
                String time = String.valueOf(infoBefore.get("R_START_TIME"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(time);
                long startTime = date.getTime();
                if (nowTime < startTime) {
                    model.addAttribute("info", "noOpen");
                } else {
                    if (service.selectStatus(RID) == 3 ||service.selectStatus(RID) == 4) {
                        model.addAttribute("info", "error");
                    } else {
                        Map<String, Integer> mapNow = service.selectSocreNow(A_ID);
                        if (mapNow != null) {
                            l_score = mapNow.get("RL_H_T_SCORE");
                            r_score = mapNow.get("RL_V_T_SCORE");
                            model.addAttribute("l_score", l_score);
                            model.addAttribute("r_score", r_score);
                        } else {
                            model.addAttribute("l_score", 0);
                            model.addAttribute("r_score", 0);
                        }

                        int tIdOne = (int) infoBefore.get("R_H_TEAM_ID");
                        int tIdTwo = (int) infoBefore.get("R_V_TEAM_ID");
                        String HTeamFlag = service.selectLogo(tIdOne);
                        String VTeamFlag = service.selectLogo(tIdTwo);
                        HTeam_Name = service.selectTeamNames(tIdOne);
                        VTeam_Name = service.selectTeamNames(tIdTwo);
                        model.addAttribute("HTeamFlag", HTeamFlag);
                        model.addAttribute("VTeamFlag", VTeamFlag);
                        model.addAttribute("RID", infoBefore.get("R_ID"));
                        model.addAttribute("gameName", service.selectGameName(RID));
                        model.addAttribute("time", infoBefore.get("R_START_TIME"));
                        model.addAttribute("HTeam_Name", HTeam_Name);
                        model.addAttribute("VTeam_Name", VTeam_Name);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }

        }
        return SiteVar.SITE_GAME_LIVE;

    }

    @RequestMapping("readTime")
    @ResponseBody
    public String readTime(HttpSession session, @RequestParam("count") int count) {
        String message ;
        String key = "couldRead";
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute(Constants.LOGIN_USER);
        if (userMap == null || userMap.size() < 1) {
            message = "error";
        } else {
            userMap.put(key, count);
            session.setAttribute(Constants.LOGIN_USER, userMap);
            message = "success";
        }
        System.out.println(message + "   ");
        return message;
    }

    /**
     * 图片上传并返回url给前端
     *
     * @param request
     * @param response
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("uploadImage")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, MultipartFile myFileName,
                         HttpSession session) throws IOException {
        String SerPath = null;
        if (myFileName != null) {
            try {
                @SuppressWarnings("unchecked")
                Map<String, String> map = (Map<String, String>) session.getAttribute(Constants.LOGIN_USER);
                String A_ID = map.get("A_ID");
                String filePath = fileUploadReplace(myFileName, request, ROLE, FILE_TYPE, A_ID,
                        myFileName.getOriginalFilename());
                SerPath = filePath;
            } catch (Exception e) {
                log.error(e.getMessage());
                SerPath = "error";
                e.printStackTrace();
            }
        }
        return SerPath;
    }

    /**
     * 向数据库插入直播信息
     *
     * @param siteLive
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
//    @InterceptorAnno(checkToken = true, removeToken = true)
    @RequestMapping(value = "insertInfo", method = RequestMethod.POST)
    @ResponseBody
    public void insertInfo(com.nsu.bean.site.SiteLive siteLive,
                           HttpServletResponse response, HttpSession session) throws IOException {
        int l_score = siteLive.getL_score();
        int r_score = siteLive.getR_score();
        String text = siteLive.getHtml();
        String imgUrl = siteLive.getImgUrl();
        int type = siteLive.getType();
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) session.getAttribute(Constants.LOGIN_USER);
        int A_ID = Integer.parseInt(map.get(CommonVar.Account.ID));
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        long RID = 0;
        try {
            RID = service.selectRID(A_ID);
            service.insertLiveInfo(l_score, r_score, dateString, text, imgUrl, type, RID);
            service.updateDetailStatus(RID, 2);
            response.getWriter().print("200");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 用户完善信息
     *
     * @param request
     * @param response
     * @param name
     * @param phone
     * @param session
     * @throws IOException
     */
    @RequestMapping("preInfo")
    @ResponseBody
    public void PreInfo(HttpServletRequest request, HttpServletResponse response, String name, String phone,
                        String randomCode, HttpSession session) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) session.getAttribute(Constants.LOGIN_USER);
        int AID = Integer.parseInt(map.get(CommonVar.Account.ID));
        log.info("*********************"+request.getSession()+"******************");
        log.info("*****************"+AID+"***********************");
        String result = null;
        Map<String, String> resultMap = V.verificationCode(JedisClient, 5, 1, phone, "", randomCode);
        int resultCode = -2;
        if (resultMap != null) {
            resultCode = Integer.parseInt(resultMap.get("result"));
            if (resultCode == 1) {
                try {
                    result = siteService.insertPreInfo(name, phone, AID);
                    JedisClient.del("5" + "web" + phone);
                    JedisClient.del("5" + "web" + phone + "num");
                } catch (Exception e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            } else {
                result = resultMap.get("resultMsg");
            }
        } else {
            result = "error";
        }
        response.getWriter().print(result);
    }

    /**
     * 结束比赛
     *
     * @param siteLive
     * @param session
     * @return
     */
    @RequestMapping("endGame")
    @ResponseBody
    public String endGame(com.nsu.bean.site.SiteLive siteLive, HttpSession session) {
        int l_score = siteLive.getL_score();
        int r_score = siteLive.getR_score();
//        int data_l_score = 0;
//        int data_r_score = 0;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        long RID = 0;
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) session.getAttribute(Constants.LOGIN_USER);
        int A_ID = Integer.parseInt(map.get(CommonVar.Account.ID));
        Map<String, Integer> mapNow = null;
        try {
            mapNow = service.selectSocreNow(A_ID);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
//        if (mapNow != null) {
//            data_l_score = (int) mapNow.get("RL_H_T_SCORE");
//            data_r_score = (int) mapNow.get("RL_V_T_SCORE");
//        }
//        if (data_l_score != l_score || data_r_score != r_score) {
//            try {
//
//                RID = service.selectRID(A_ID);
//                service.insertLiveInfo(l_score, r_score, dateString, "比赛结束", null, 1, RID);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        try{
            RID = service.selectRID(A_ID);
            service.insertLiveInfo(l_score, r_score, dateString, "比赛结束", null, 1, RID);
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            String HTeam_Name;
            String VTeam_Name;
            RID = service.selectRID(A_ID);
            Map<String, Object> infoBefore = service.selectInfoBeforeGame(A_ID);
            int tIdOne = (int) infoBefore.get("R_H_TEAM_ID");
            int tIdTwo = (int) infoBefore.get("R_V_TEAM_ID");
            HTeam_Name = service.selectTeamNames(tIdOne);
            VTeam_Name = service.selectTeamNames(tIdTwo);
            int winTeamNum = 0;
            if (l_score < r_score) {
                winTeamNum = 1;
                service.endTime(dateString, VTeam_Name, RID);
            } else {
                service.endTime(dateString, HTeam_Name, RID);
            }
            RID = service.selectRID(A_ID);
            service.endGame(RID);
            service.endStatus(dateString, service.slectSE(A_ID));
            service.updateDetailStatus(RID, 3);
            service.updateScore(winTeamNum, RID, A_ID);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return SiteVar.REDIRECT_SITE_JUDGEMENT;
    }
}
