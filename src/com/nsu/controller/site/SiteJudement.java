package com.nsu.controller.site;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.controller.BaseController;
import com.nsu.service.site.SiteJudementService;
import com.nsu.service.site.SiteLiveService;
import com.nsu.util.V;

/**
 * 裁判书controller
 *
 * @author 刘俊
 */
@Controller
@RequestMapping("/site")
public class SiteJudement extends BaseController {

    @Autowired
    SiteJudementService service;
    @Autowired
    SiteLiveService liveService;

    /**
     * 进入裁判书页面
     *
     * @param session
     * @param model
     * @return
     */
    @InterceptorAnno(createToken = true)
    @RequestMapping("judement")
    public String judgementPage(HttpSession session, Model model) {

        int A_ID = Integer.parseInt(RequestUtil.getAccountInfoInSession(session, CommonVar.Account.ID));
        log.info("A_ID是+ " + A_ID);
        if (V.checkEmpty(A_ID)) {
            model.addAttribute("info", "nosession");
        } else {
            long RID = 0;
            try {
                RID = liveService.selectRID(A_ID);
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
            // 判断页面是否可用，状态为3时可以填写裁判书
            try {
                ((Map) session.getAttribute(Constants.LOGIN_USER)).put("RID", RID);
                Map<String, Object> infoBefore = liveService.selectInfoBeforeGame(A_ID);
                long nowTime = new Date().getTime();
                String time = String.valueOf(infoBefore.get("R_START_TIME"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(time);
                long startTime = date.getTime();
                log.info("*******************" + liveService.selectStatus(RID) + "*******************");
                if (liveService.selectStatus(RID) == 3) {
                    Map<String, Object> site = service.selectInfo(A_ID);
                    for (String key : site.keySet()) {
                        if (key.equals("R_V_TEAM_ID")) {
                            int t_id = Integer.parseInt(String.valueOf(site.get("R_V_TEAM_ID")));
                            model.addAttribute("VName", service.selectTeamName(t_id));
                        }
                        if (key.equals("R_H_TEAM_ID")) {
                            int t_id = Integer.parseInt(String.valueOf(site.get("R_H_TEAM_ID")));
                            model.addAttribute("HName", service.selectTeamName(t_id));
                        } else {
                            model.addAttribute("" + key + "", site.get(key));
                        }
                    }

                    // 回显信息放入map
                    List<Map<String, Object>> HTeamplayerList = service.selectPlayerList(RID, 0);
                    List<Map<String, Object>> VTeamPlayerList = service.selectPlayerList(RID, 1);
                    List<Map<String, Object>> HTeamAlternateList = service.selectAlternateList(RID, 0);
                    List<Map<String, Object>> VTeamAlternateList = service.selectAlternateList(RID, 1);
                    List<Map<String, Object>> WranList = service.selectWranList(RID);
                    List<Map<String, Object>> OutList = service.selectOutList(RID);
                    List<Map<String, Object>> detailsList = service.selectDetails(RID);

                    List<Map<String, Object>> HTeamInBall = service.selectInBall(RID, 0);
                    List<Map<String, Object>> VTeamInBall = service.selectInBall(RID, 1);
                    List<Map<String, Object>> PlayerLists = service.AllPlayerList(HTeamplayerList, VTeamPlayerList);
                    List<Map<String, Object>> AlternateLists = service.AllAlternateList(HTeamAlternateList,
                            VTeamAlternateList);
                    List<Map<String, Object>> InBallLists = service.AllInBallList(HTeamInBall, VTeamInBall);

                    // 得到上场比赛球员的信息列表传入后台
                    int HTeamID = (int) site.get("R_H_TEAM_ID");
                    int VTeamId = (int) site.get("R_V_TEAM_ID");
                    List<String> HTeamPlayers = service.AllPlayers(RID, HTeamID);
                    List<String> VTeamPlayers = service.AllPlayers(RID, VTeamId);
                    List<Integer> HTeamPlayerNum = service.AllPlayersNum(RID, HTeamID);
                    List<Integer> VTeamPlayerNum = service.AllPlayersNum(RID, VTeamId);

                    log.info("输出测试人员");
                    for (int i = 0; i < HTeamPlayers.size(); i++) {
                        log.info("主队上场人员++++" + HTeamPlayers.get(i) + "  号码是" + HTeamPlayerNum.get(i));
                    }
                    for (int i = 0; i < VTeamPlayers.size(); i++) {
                        log.info("客队队上场人员++++" + HTeamPlayers.get(i) + "  号码是" + VTeamPlayerNum.get(i));
                    }
                    log.info("结束=====");

                    model.addAttribute("InBallPlayers", InBallLists);
                    model.addAttribute("PlayerLists", PlayerLists);
                    model.addAttribute("AlterLists", AlternateLists);
                    model.addAttribute("WranList", WranList);
                    model.addAttribute("OutList", OutList);
                    model.addAttribute("detailsList", detailsList.get(0));

                    model.addAttribute("HTeamPlayers", HTeamPlayers);
                    model.addAttribute("VTeamPlayers", VTeamPlayers);
                    model.addAttribute("HTeamPlayerNum", HTeamPlayerNum);
                    model.addAttribute("VTeamPlayerNum", VTeamPlayerNum);

                } else if (liveService.selectStatus(RID) == 4) {
                    model.addAttribute("type", "posted");
                } else if (nowTime < startTime) {
                    model.addAttribute("type", "noOpen");
                } else {
                    model.addAttribute("type", "noEnd");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return SiteVar.SITE_JUDGEMENT;

    }

    /**
     * 保存裁判书信息
     *
     * @param params
     * @param HPlayer
     * @param VPlayer
     * @param HAlternate
     * @param VAlternate
     * @param Wran
     * @param Out
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @InterceptorAnno(checkToken = true, removeToken = true)
    @RequestMapping("insertJudement")
    @ResponseBody
    public void insertJudgement(@RequestParam("params") String[] params, @RequestParam("HPlayer") String[] HPlayer,
                                @RequestParam("VPlayer") String[] VPlayer, @RequestParam("HAlternate") String[] HAlternate,
                                @RequestParam("VAlternate") String[] VAlternate, @RequestParam("InHPlayer") String[] InHPlayer,
                                @RequestParam("InVPlayer") String[] InVPlayer, @RequestParam("Wran") String[] Wran,
                                @RequestParam("Out") String[] Out, HttpServletRequest request, HttpServletResponse response,
                                HttpSession session) throws IOException {
        log.info("params " + params.length);
        log.info("params +" + params[0]);
        log.info("params +" + params[1]);
        log.info("params +" + params[2]);
        log.info("params +" + params[3]);
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) session.getAttribute(Constants.LOGIN_USER);
        int A_ID = Integer.parseInt(map.get("A_ID"));
        long R_ID = 0;
        try {
            R_ID = liveService.selectRID(A_ID);
            service.insertInfo(params, HPlayer, VPlayer, HAlternate, VAlternate, InHPlayer, InVPlayer, Wran, Out, R_ID,
                    A_ID);
            // 4代表已经提交过的判断书
            liveService.setStatus("4", R_ID);
            //设置当前帐号不可再使用，手机号可以再使用
            liveService.setA_Status(A_ID);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().print("200");
    }
}
