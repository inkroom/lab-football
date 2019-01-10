package cn.nsu.edu.web.four.controllers.coath;

import cn.nsu.edu.web.four.annotation.Security;
import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.config.RegexStatic;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.enums.Role;
import cn.nsu.edu.web.four.services.coach.CoachService;
import cn.nsu.edu.web.four.services.common.UploadService;
import cn.nsu.edu.web.four.utils.http.RequestUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import cn.nsu.edu.web.four.utils.string.WordCheck;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: four
 * @description:
 * @author: ZhuShengpeng
 * @create: 2018-03-21 20:35
 **/

@Controller
@RequestMapping("/coach/")
public class CoachController {

    @Autowired
    public CoachService coachService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UploadService uploadService;



    //    向前端返回全部教练信息并跳转至“全部教练页”(用学校id读取这个学校的全部教练)
    @RequestMapping(value = "getAllCoach", method = RequestMethod.GET)
    @Security(roles = Role.SCHOOL)
    public String getAllCoach1(@RequestParam(value = "status", defaultValue = "0") String status, @RequestParam(value = "pn", defaultValue = "1") String pn, Model model) {

        if (WordCheck.isNumeric(status, "[0,1]") && WordCheck.isNumeric(pn, "[1-9][0-9]*")) {

            Map<String, Object> logingMap = RequestUtil.getLogin(request);
            Integer org_id = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_ORGANIZATION_ID).toString());

            Integer statusNum = ParseUtil.parseInt(status);
            Integer pnNum = ParseUtil.parseInt(pn);

            PageHelper.startPage(pnNum, 8);
            List<Coach> coach = coachService.getAllCoach(org_id, statusNum);
            PageInfo pageInfo = new PageInfo(coach, 5);

            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("org_id", org_id);
            model.addAttribute("status", status);
            return "coach/coach_list";
        } else {
            return "forward:/404";
        }
    }

    //向前端返回单个教练信息并跳转至“教练具体信息页”（用教练id获取这个教练的全部信息）
    @RequestMapping(value = "getCoach", method = RequestMethod.GET)
    @Security(roles = Role.SCHOOL)
    public String getCoach(@RequestParam(value = "id_coach") String idCoach, Model model) {
        if (WordCheck.isNumeric(idCoach, "[5-9][0-9]{4}$")) {

            Map<String, Object> logingMap = RequestUtil.getLogin(request);
            Integer org_id = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_ORGANIZATION_ID).toString());

            Integer idCoachNum = ParseUtil.parseInt(idCoach);
            Coach coach = coachService.getCoach(idCoachNum);
            if (coach != null) {
                if (coach.getOrgId().equals(org_id)) {
                    model.addAttribute("coach", coach);
                    model.addAttribute("coachTeam", coachService.getAllTeamByCoach(idCoachNum));
                    return "coach/coach_info";
                }
            }
        }
        return "forward:/404";
    }


    //添加教练信息
    @ResponseBody
    @RequestMapping(value = "addCoach", method = RequestMethod.POST)
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto addCoach(@Validated Coach coach, BindingResult result) {
        Map<String, Object> logingMap = RequestUtil.getLogin(request);
        Integer org_id = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_ORGANIZATION_ID).toString());
        if (coach.getOrgId().equals(org_id) && result.getFieldError() == null) {
                int i = coachService.addCoach(coach);
                if (i == 1) {
                    return new MessageDto(Result.SUCCESS);
                }
        }
        return new MessageDto(Result.FAIL);
    }


    //修改教练基础信息
    @ResponseBody
    @RequestMapping(value = "updateCoach", method = RequestMethod.POST)
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto updateCoach(@Validated Coach coach, BindingResult result) {

        Map<String, Object> logingMap = RequestUtil.getLogin(request);
        Integer org_id = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_ORGANIZATION_ID).toString());
        if (coachService.getCoach(coach.getIdCoach()).getOrgId().equals(org_id) && result.getFieldError() == null) {
            int i = coachService.updateCoach(coach);
            if (i == 1) {
                return new MessageDto(Result.SUCCESS);
            }
        }
        return new MessageDto(Result.FAIL);
    }

    //修改图片
    @ResponseBody
    @RequestMapping(value = "updatePhoto", method = RequestMethod.POST)
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto updatePhoto(@RequestParam("file") CommonsMultipartFile file, Coach coach) {
        Map<String, Object> logingMap = RequestUtil.getLogin(request);
        Integer org_id = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_ORGANIZATION_ID).toString());
        if (coachService.getCoach(coach.getIdCoach()).getOrgId().equals(org_id)) {
            String photo = uploadService.upload(file, request, 204800);
            if (photo != null) {
                coach.setPhoto(photo);
                int i = coachService.updatePhoto(coach);
                if (i == 1) {
                    return new MessageDto(Result.SUCCESS);
                }
            }
        }
        return new MessageDto(Result.FAIL);
    }


    //修改教练状态
    @ResponseBody
    @RequestMapping(value = "coachStatus", method = RequestMethod.POST)
    @Security(roles = Role.SCHOOL, checkToken = true)
    public MessageDto coachStatus(Integer idCoach) {
        Map<String, Object> logingMap = RequestUtil.getLogin(request);
        Integer org_id = ParseUtil.parseInt(logingMap.get(BaseStatic.KEY_ORGANIZATION_ID).toString());

        if (coachService.getCoach(idCoach).getOrgId().equals(org_id)) {
            int i = coachService.coachStatus(idCoach);
            if (i == 1) {
                return new MessageDto(Result.SUCCESS);
            }
        }
        return new MessageDto(Result.FAIL);
    }

}
