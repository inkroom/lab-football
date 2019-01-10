package cn.nsu.edu.web.four.controllers.match;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.match.*;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.match.MatchService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import cn.nsu.edu.web.four.utils.time.TimeJudge;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static cn.nsu.edu.web.four.beans.match.MatchStatic.*;

/**
 * @author 痞老板
 * @Project: four
 * @Package:cn.nsu.edu.web.four.controllers.match
 * @date 2018/3/20 11:25
 * @description
 **/
@Controller
@RequestMapping(value = "match")
public class MatchController {
    @Autowired
    MatchService service;

    @Autowired
    HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @param
     * @return void
     * @author 痞老板
     * @description 测试用 上线后注掉
     * @date 2018/4/1 15:35
     */
//    @ModelAttribute
//    public void sessionSet() {
//        Map<String, Object> account = new HashMap<>();
//        account.put(BaseStatic.KEY_ORGANIZATION_ID, "10000");
//        RequestUtil.login(request, account, Role.ORGANIZATION);
//    }


    /**
     * @param num
     * @return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author 痞老板
     * @description 异步获取分页list 需要获取请求页码和索引值（导航栏上面给点击事件 1为未参加赛事 2为发布赛事 3为已参加赛事）
     * 返回的pageinfo 对象是封装分页对象 在前端可以参考http://blog.csdn.net/maoyuanming0806/article/details/77720754 来取值
     * @date 2018/3/22 10:37
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/listMatch")
    public MessageDto listMatch(@RequestParam(required = false, defaultValue = "1", value = "pn") String pn,
                                @RequestParam(required = false, defaultValue = "-1", value = "num") String num) {
        if (StringUtils.isEmpty(num) || StringUtils.isEmpty(pn)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Integer pa = ParseUtil.parseInt(pn);
        Integer nums = ParseUtil.parseInt(num);
        if (pa == null || nums == null) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        List<Match> list;
        Integer orgId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));

        PageHelper.startPage(pa, MATCH_PAGE_DATA_COUNT);
        switch (nums) {
            case 1:
                list = service.selectByStaffOther(orgId);
                break;
            case 2:
                list = service.selectByStaff(orgId);
                break;
            case 3:
                list = service.selectByIdWithOrg(orgId);
                break;
            default:
                return new MessageDto(Result.FAIL);
        }
        PageInfo<Match> pageInfo = new PageInfo(list, MATCH_NAVIGATE_PAGES);
        MessageDto dto;
        if (list == null || list.isEmpty()) {
            log.info("赛事列表为空");
            return new MessageDto(Result.FAIL);
        }
        //2表示已结束 1正在进行 0正在报名
        for (Match match : pageInfo.getList()) {
////            if (TimeJudge.compareTime(match.getEndDate())){
////                match.setStatus(2);
////            }
            if (!TimeJudge.compareTime(match.getApplyDeadline())) {
                match.setStatus(MATCH_STATUS_DURING);
            } else {
                match.setStatus(MATCH_STATUS_SIGN_UP);
            }
        }
        log.info("赛事列表获取成功");
        dto = new MessageDto(Result.SUCCESS);
        dto.put("pageInfo", pageInfo);
        return dto;
    }


    /**
     * @param model
     * @param mathcid
     * @return java.lang.String
     * @author 痞老板
     * @description 报名表列表 列出球队和球员 注意数据结构为 teamlist->teamMap->teamName,playerList->MatchPlayer
     * @date 2018/3/22 20:38
     */
//    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
//    @RequestMapping(value = "/listSign")
//    public String listSign(Model model, @RequestParam(required = false, defaultValue = "0", value = "id") String mathcid) {
//        if (StringUtils.isEmpty(mathcid)) {
//            return "common/404";
//        }
//        Integer mathcId = ParseUtil.parseInt(mathcid);
//        Integer orgId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));
//        if (mathcId == null || orgId == null) {
//            return "common/404";
//        }
//        model.addAttribute("matchid", mathcid);
//        Gson gs = new Gson();
//        String object = gs.toJson(service.listTeamAndPlayer(orgId));
//        object = object.replaceAll("\"", "\\\"");
//        log.info(object);
//        model.addAttribute("teamList", object);
//        return "match/matchSignUp";
//
//    }

    /**
     * @param model
     * @return java.lang.String
     * @author 痞老板
     * @description 批量上传队员信息 注意 上传字段只需要:matchId,orgId,teamId,playerId
     * @date 2018/3/22 20:43
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/addPlayer")
    public MessageDto insertPlayer(@RequestBody ModelList model) {
        if (model.getPlayers().size()!=0){
            Integer matchId = ParseUtil.parseInt(request.getSession().getAttribute(BaseStatic.KEY_MATCH_ID));
            Integer OrgId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(BaseStatic.KEY_ORGANIZATION_ID));
            int a = service.insertPlayer(model.getPlayers(), matchId, OrgId);
            if (a != 0) {
                MessageDto dto = new MessageDto(Result.SUCCESS);
                return dto;
            } else if (a == -1) {
                return new MessageDto(Result.FAIL, "不可重复报名");
            }

        }
            return new MessageDto(Result.FAIL);
    }


    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "/turnMatchList")
    public String turnMatchList() {
        return "match/matchList";
    }
    /**
     *@author 痞老板
     *@description
     *@param matchId, model
     *@return java.lang.String
     *@date 2018/3/30 15:33
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "/turnDetailsAdmin")
    public String turnDetailsAdmin(@RequestParam(value = "id") String matchId, Model model) {
        if (StringUtils.isEmpty(matchId)) {
            return "common/404";
        }
        if (ParseUtil.parseInt(matchId) == null) {
            return "common/404";
        }
        if (!service.checkAuthority(ParseUtil.parseInt(matchId), request)) {
            return "common/401";
        }
        model.addAttribute("mid", matchId);
        log.info("转到赛事详情展示页");
        return "match/matchDetailsAdmin";
    }

    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "turnUpdate")
    public String turnUpdateMatch(@RequestParam("id") String mid, Model model) {
        if (StringUtils.isEmpty(mid)) {
            return "common/404";
        }
        if (ParseUtil.parseInt(mid) == null) {
            return "common/404";
        }
        if (!service.checkAuthority(ParseUtil.parseInt(mid), request)) {
            return "common/401";
        }
        model.addAttribute("mid", mid);
        return "match/modifyMatch";
    }

    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "turnAddSch")
    public String turnAddSch(@RequestParam("id") String mid, Model model) {
        if (StringUtils.isEmpty(mid)) {
            return "common/404";
        }
        if (ParseUtil.parseInt(mid) == null) {
            return "common/404";
        }
        if (!service.checkAuthority(ParseUtil.parseInt(mid), request)) {
            return "common/401";
        }
        model.addAttribute("mid", mid);
        return "match/addSch";
    }

    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @RequestMapping(value = "/turnDetails")
    public String turnDetails(@RequestParam("id") String matchId, Model model) {
        if (StringUtils.isEmpty(matchId)) {
            return "common/404";
        }
        Integer mid = ParseUtil.parseInt(matchId);
        if (mid == null) {
            return "common/404";
        }
        model.addAttribute("mid", matchId);
        request.getSession().setAttribute(BaseStatic.KEY_MATCH_ID, matchId);
        Integer orgId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));
        if (orgId == null) {
            return "common/404";
        }
        /**
         * 报名表信息
         */
        Gson gs = new Gson();
        String object = gs.toJson(service.listTeamAndPlayer(orgId, mid));
        object = object.replaceAll("\"", "\\\"");
        log.info(object);
        model.addAttribute("state", service.checkOrgStatus(orgId, mid));
        model.addAttribute("teamList", object);
        return "match/matchDetails";
    }


    /**
     * @param matchId
     * @return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author 痞老板
     * @description 赛事审核列表
     * @date 2018/3/29 10:07
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/listExamine")
    public MessageDto listExamine(@RequestParam("mid") String matchId) {
        if (StringUtils.isEmpty(matchId)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Integer mid = ParseUtil.parseInt(matchId);
        if (mid == null) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        if (!service.checkAuthority(ParseUtil.parseInt(mid), request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
        List<MatchExamine> list = service.listExamine(mid);
        if (list == null || list.isEmpty()) {
            log.info("审核列表为空");
            return new MessageDto(Result.FAIL);
        }
        log.info("审核列表获取成功");
        MessageDto messageDto = new MessageDto(Result.SUCCESS);
        messageDto.put("examine", list);
        return messageDto;
    }

    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/listExamineTeam")
    public MessageDto listExamineTeam(@RequestParam("mid") String mid,
                                      @RequestParam("oid") String orid,
                                      @RequestParam(required = false, defaultValue = "1", value = "pn") String pn) {
        if (StringUtils.isEmpty(mid) || StringUtils.isEmpty(pn) || StringUtils.isEmpty(orid)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        if (!service.checkAuthority(ParseUtil.parseInt(mid), request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
        Integer pa = ParseUtil.parseInt(pn);
        Integer maid = ParseUtil.parseInt(mid);
        Integer oid = ParseUtil.parseInt(orid);
        if (maid == null || pa == null) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        PageHelper.startPage(pa, MATCH_PAGE_DATA_COUNT);
        List<TeamInfo> list = service.listExamineTeam(maid, oid);
        if (list == null || list.isEmpty()) {
            log.info("审核列表为空");
            return new MessageDto(Result.FAIL);
        }
        PageInfo<MatchExamine> pageInfo = new PageInfo(list, MATCH_NAVIGATE_PAGES);
        MessageDto dto = new MessageDto(Result.SUCCESS);
        dto.put("pageInfo", pageInfo);
        return dto;
    }


    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/listExaminePlayer")
    public MessageDto listExaminePlayer(@RequestParam("tid") String teamid,
                                        @RequestParam("mid") String tmid,
                                        @RequestParam(required = false, defaultValue = "1", value = "pn") String pn) {
        if (StringUtils.isEmpty(teamid) || StringUtils.isEmpty(pn) || StringUtils.isEmpty(tmid)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Integer tid = ParseUtil.parseInt(teamid);
        Integer pa = ParseUtil.parseInt(pn);
        Integer mid = ParseUtil.parseInt(tmid);
        if (tid == null || pa == null) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        if (!service.checkAuthority(ParseUtil.parseInt(mid), request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
        PageHelper.startPage(pa, MATCH_PAGE_DATA_COUNT);
        List<Player> list = service.listExaminePlayer(tid, mid);
        if (list == null || list.isEmpty()) {
            log.info("学生列表为空");
            return new MessageDto(Result.FAIL);
        }
        PageInfo<MatchExamine> pageInfo = new PageInfo(list, MATCH_NAVIGATE_PAGES);
        MessageDto messageDto = new MessageDto(Result.SUCCESS);
        messageDto.put("pageInfo", pageInfo);
        return messageDto;
    }

    /**
     * @param
     * @return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author 痞老板
     * @description 通过审核修改状态
     * @date 2018/3/22 20:51
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/setExamine")
    public MessageDto setExamine(@RequestParam("id") String orgId, @RequestParam("status") String status, @RequestParam("mid") String mId) {
        if (StringUtils.isEmpty(orgId) || StringUtils.isEmpty(status) || StringUtils.isEmpty(mId)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        if (!service.checkAuthority(ParseUtil.parseInt(mId), request)) {
            return new MessageDto(Result.NO_AUTHORITY);
        }
        Integer oid = ParseUtil.parseInt(orgId);
        Integer statu = ParseUtil.parseInt(status);
        Integer mid = ParseUtil.parseInt(mId);
        if (oid == null || statu == null || mid == null) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        service.updateStaffStatus(statu, oid, mid);
        log.info("修改审核状态");
        MessageDto dto = new MessageDto(Result.SUCCESS);
        return dto;
    }

    /**
     * @param match
     * @return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author Xuing
     * @description 创建赛事
     * @date 2018-3-26 20:59:07
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/createMatch", method = {RequestMethod.GET, RequestMethod.POST})
    public MessageDto insertMatch(@Valid Match match, BindingResult result) {
        log.info(request.getParameter("name"));
        log.info(request.getParameter("post.name"));
        log.info(request.getHeader("name"));
        Integer orgId = ParseUtil.parseInt(RequestUtil.getLogin(request).get(KEY_ORGANIZATION_ID));
        MessageDto resultDto;
        if (result.hasErrors()) {
            return new MessageDto(Result.FAIL, result.getFieldError().getDefaultMessage());
        }
        if (orgId == null) {
            return new MessageDto(Result.LOGIN_NOT);
        }
        resultDto = checkMatch(match);
        if (resultDto.getStatus() != Result.SUCCESS.value()) {
            return resultDto;
        }

        log.info(match.toString());
        match.setOrgStaff(new Staff(orgId));
        match.setStatus(MATCH_STATUS_SIGN_UP);
        if (!service.insertMatch(match)) {
            log.info("创建赛事失败");
            return new MessageDto(Result.FAIL, "创建赛事失败");
        }
        log.info("创建赛事成功");
        return resultDto;
    }

    private MessageDto checkMatch(Match match) {
        if (!service.checkMatchName(match.getName(), match.getIdMatch())) {
            return new MessageDto(Result.FAIL, "赛事名称重复");
        }
        if (!service.checkMatchDate(match.getApplyDeadline(), match.getStartDate())) {
            return new MessageDto(Result.FAIL, "报名截止日期不能晚于开赛日期");
        }
        if (match.getEndDate() != null && !service.checkMatchDate(match.getStartDate(), match.getEndDate())) {
            return new MessageDto(Result.FAIL, "开始时间不能小于结束时间");
        }
        return new MessageDto(Result.SUCCESS);
    }

    /**
     * @param match
     * @return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author Xuing
     * @description 修改赛事信息
     * @date 2018-3-28 09:30:56
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL}, checkToken = true)
    @ResponseBody
    @RequestMapping(value = "/modifyMatch")
    public MessageDto modifyMatchInfo(@Valid Match match, BindingResult result) {
        MessageDto resultDto;
        //权限检测
        if (!service.checkAuthority(match.getIdMatch(), request)) {
            return new MessageDto(Result.NO_AUTHORITY, "您没有权限修改");
        }
        resultDto = checkMatch(match);
        if (resultDto.getStatus() != Result.SUCCESS.value()) {
            return resultDto;
        }
        if (result.hasErrors()) {
            return new MessageDto(Result.FAIL, result.getFieldError().getDefaultMessage());
        }
        if (service.modifyMatch(match)) {
            log.info("修改赛事成功");
            MessageDto dto = new MessageDto(Result.SUCCESS);
            return dto;
        } else {
            log.info("修改赛事失败");
            return new MessageDto(Result.FAIL);
        }
    }

    /**
     *
     *
     * @param id
     * @return cn.nsu.edu.web.four.dto.ctv.MessageDto
     * @author Xuing
     * @description 通过ID获取赛事信息 /MatchInfo/{id}
     * @date 2018-3-28 09:08:20
     */
    @Security(roles = {Role.ORGANIZATION, Role.SCHOOL})
    @ResponseBody
    @RequestMapping(value = "/MatchInfo/{id:[1-9]+[0-9]*}")
    public MessageDto getMatchInfoByid(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        Integer mid = ParseUtil.parseInt(id);
        if (mid == null) {
            return new MessageDto(Result.PARAM_NOT_SUIT);
        }
        MessageDto dto = new MessageDto(Result.FAIL);
        Match matchInfo = service.getMatchInfoById(mid);
        if (matchInfo != null) {
            dto = new MessageDto(Result.SUCCESS);
            dto.put("matchInfo", matchInfo);
        }
        return dto;
    }

}
