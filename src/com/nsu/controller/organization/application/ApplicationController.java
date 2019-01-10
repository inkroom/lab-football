package com.nsu.controller.organization.application;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.service.message.ISelectMessageService;
import com.nsu.service.organization.IApplicationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 严涛
 * @ClassName: ApplicationController
 * @Description: 机构申请管理的
 * @date 2017年4月17日 下午3:29:58
 */
@Controller
@RequestMapping(value = "/org")
public class ApplicationController extends BaseController {
    @Resource
    private IApplicationService iApplicationService;
    @Resource(name = "SelectMessage")
    ISelectMessageService SelectMessage;
    private String OrgApplications = "/organization/organization_system/audit_application";
    private Map<Object, Object> map1 = new HashMap<>();//保存机构ID
    private List<Map<Object, Object>> message = new ArrayList<>();//保存球队信息
    private String match_name;//赛事名称
    private long team_id;//球队ID
    private long org_id;//机构ID
    private int A_ID;//球队A_ID

    @RequestMapping(value = "/macth_application")
    public String getApplications() {
        return OrgApplications;
    }

    /**
     * @param 设定文件
     * @return void    返回类型
     * @throws
     * @Title: selectTeamID
     * @Description: 根据球队名称查球队ID
     */
    @RequestMapping(value = "/seeTeamAllMessage", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
    @ResponseBody
    public String selectTeamID(HttpSession session, Model model, String name, String COM_NAME, long TEAM_ID) {
        match_name = COM_NAME;
        team_id = TEAM_ID;
        List<Map<String, Object>> playerList;//存储球员信息
        Map<String, Object> map;//存储球队信息
        JSONObject jsonArray = new JSONObject();
        JSONArray jsonstr;
        JSONArray jsonplayer;
        try {
            map = iApplicationService.findTeamMessage(team_id);//球队信息
            A_ID = (int) map.get("A_ID");
            playerList = iApplicationService.findPlayerMessage(team_id);//球员信息
            jsonstr = JSONArray.fromObject(map);
            jsonplayer = JSONArray.fromObject(playerList);
            jsonArray.put("jsonstr", jsonstr);
            jsonArray.put("jsonplayer", jsonplayer);

            return jsonArray.toString();

        } catch (Exception e) {
            model.addAttribute("Msg", "系统异常，请稍后再试！");
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/list")
    public void findAll(Integer pageNum, HttpServletResponse response, HttpSession session) {
        try {
            map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
            long ORG_ID = (long) map1.get("ORG_ID");
            org_id = ORG_ID;
            PageInfo<Map<Object, Object>> pageInfo = iApplicationService.findAll(pageNum, 10, ORG_ID);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pageInfo", pageInfo);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(jsonObject.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: seeTeamMessage
     * @Description: 通过搜索赛事查看申请
     */
    @RequestMapping(value = "/team_select")
    public String seeTeamMessage(Model model, HttpSession session,
                                 @RequestParam(value = "comName", required = true) String comName) {
        map1 = (Map) session.getAttribute(Constants.LOGIN_USER);
        long ORG_ID = (long) map1.get("ORG_ID");
        Map<String, Object> isClick = new HashMap<>();
        isClick.put("num", 1);
        try {
            message = iApplicationService.findone(ORG_ID, comName);
            if (message != null && !message.isEmpty()) {
                model.addAttribute("message", message);
                model.addAttribute("isClick", isClick);
            } else {
                model.addAttribute("Msg", "抱歉！找不到您要搜索的赛事！");
            }
        } catch (Exception e) {
            model.addAttribute("Msg", "抱歉！找不到您要搜索的赛事！！");
            e.printStackTrace();
        }
        return OrgApplications;
    }

    /**
     * @param 设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateStatus
     * @Description: 更新"通过"球队审核状态
     */
    @RequestMapping(value = "/update_teamStatus", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
    @ResponseBody
    public String updateStatus(HttpSession session) {
        Integer status;
        String result;
        JSONObject jsonstr = new JSONObject();
        Integer match_id = 0;
        Map<String, Object> match_ids = null;
        Map<String, Object> messageMap = new HashMap<>();
        try {
            Long ID = iApplicationService.selectAId(org_id);//机构A_ID
            String orgName = iApplicationService.selectOrgName(org_id);//机构名称
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^orgName:" + orgName);
            messageMap.put("PS_RECEIVE", A_ID);
            messageMap.put("PS_SEND_ID", ID);
            messageMap.put("PS_TITLE", "申请加入" + match_name + "的审核结果");
            messageMap.put("PS_TEXT", orgName + "同意了你加入" + match_name + "的申请");
            match_ids = iApplicationService.selectMatchIDByMatchName(match_name);
            match_id = (Integer) match_ids.get("COM_ID");
            int num = iApplicationService.updateTeamStatus(team_id, match_id);
            if (num == 1) {
                result = "操作成功!";
                status = 200;
                jsonstr.put("status", status);
                jsonstr.put("message", result);
                SelectMessage.insertPersonMessage(messageMap);
            } else {
                result = "操作失败,请重试!";
            }
        } catch (Exception e) {
            result = "操作失败,请重试!";
            e.printStackTrace();
        }
        return jsonstr.toString();
    }

    /**
     * @param 设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateStatus
     * @Description: 更新不通过球队审核状态
     */
    @RequestMapping(value = "/update_teamfailerStatus", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
    @ResponseBody
    public String updatefailerStatus(HttpSession session) {
        Integer status = 404;
        String result = "登录会话失效,请重新登录!";
        JSONObject jsonstr = new JSONObject();
        Integer match_id = 0;
        Map<String, Object> match_ids = null;
        Map<String, Object> messageMap = new HashMap<>();
        try {
            Long ID = iApplicationService.selectAId(org_id);//机构A_ID
            String orgName = iApplicationService.selectOrgName(org_id);//机构名称
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^orgName:" + orgName);
            messageMap.put("PS_RECEIVE", A_ID);
            messageMap.put("PS_SEND_ID", ID);
            messageMap.put("PS_TITLE", "申请加入" + match_name + "的审核结果");
            messageMap.put("PS_TEXT", orgName + "拒绝了你加入" + match_name + "的申请");
            match_ids = iApplicationService.selectMatchIDByMatchName(match_name);
            match_id = (Integer) match_ids.get("COM_ID");
            int num = iApplicationService.updateTeam(team_id, match_id);
            if (num == 1) {
                result = "操作成功!";
                status = 200;
                jsonstr.put("status", status);
                jsonstr.put("message", result);
                SelectMessage.insertPersonMessage(messageMap);
            } else {
                result = "操作失败,请重试!";
            }
        } catch (Exception e) {
            result = "操作失败,请重试!";
            e.printStackTrace();
        }
        return jsonstr.toString();
    }
}
