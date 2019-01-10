package com.nsu.controller.organization.system;


import com.github.pagehelper.PageInfo;
import com.nsu.bean.index.AjaxBean;
import com.nsu.bean.organization.CompetitionBean;
import com.nsu.bean.organization.EndRaceBean;
import com.nsu.bean.organization.ScheduleAcountPwdBean;
import com.nsu.bean.organization.ScheduleBean;
import com.nsu.common.Constants;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.controller.BaseController;
import com.nsu.service.organization.OrgCompetitionScheduleService;
import com.nsu.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 查看机构的赛事安排
 *
 * @author 辜鹏
 * @ClassName: OrgCompetition_Schedule_Controller
 * @date 17/4/17    上午11:26
 */
@Controller
@RequestMapping("/org")
public class OrgCompetitionScheduleController extends BaseController {

    @Resource
    private OrgCompetitionScheduleService service;

    private List<Map<String, Object>> ls;


    /**
     * 1.查询该机构的 安排的所有赛事信息 可以进行模糊查询
     *
     * @return 所有赛事信息
     */
    @RequestMapping("race_schedule_view")
    public String test() {
        return "/organization/organization_system/competition_schedule";
    }

    @RequestMapping(value = "findQueryAll", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<CompetitionBean> findQueryAll(HttpServletRequest request,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "8") Integer pageSize,
                                                  String parameter,
                                                  HttpServletResponse response,
                                                  RedirectAttributes redirectAttributes, Model model) {

        try {
            /*从机构登录用户中获取ORG_ID*/
            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");

            PageInfo<CompetitionBean> pageInfo = service.findQueryAll(org_id, pageNum, pageSize, parameter);

            return pageInfo;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }


    /**
     * 2.查询结束赛事的赛事信息 可以进行模糊查询
     */
    @InterceptorAnno(createToken = true)
    @RequestMapping("com_end_view")
    public String findEndView() {
        return "/organization/organization_system/end_rance";

    }


    @RequestMapping("find_com_end")
    @ResponseBody
    public PageInfo findEndView2(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String parameter,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "8") Integer pageSize) {

        try {

            /*从机构登录用户中获取ORG_ID*/
            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");

            PageInfo<CompetitionBean> pageInfo = service.findQueryAll(org_id, pageNum, pageSize, parameter);

            return pageInfo;


        } catch (Exception e) {

            log.error(e.getMessage());

        }

        return null;
    }


    /**
     * 2.2结束整个赛事, 插入单个赛事的 球队第一名 第二名 第三名
     *
     * @param request
     * @param bean
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @RequestMapping("end_prize")
    @ResponseBody
    public AjaxBean endPrizeInfo(HttpServletRequest request, String bean, String id) {

        AjaxBean ajaxBean = new AjaxBean();

        try {
            if (service.checkWouldEndCom(id)){
                   /*1.或得机构ID*/
                Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
                Object org_id = usermap.get("ORG_ID");

                List<Map<String, Object>> listmap = JsonMapper.getInstance().fromJson(bean, List.class);

                List<Map<String, Object>> tempListMap = new ArrayList<>();

                for (int i = 0, j = 0; i < listmap.size(); i = i + 5) {

                    Map<String, Object> map = new HashedMap();

                    map.put(listmap.get(i).get("name").toString(), listmap.get(i).get("value"));
                    if(i+1<listmap.size())
                        map.put(listmap.get(i + 1).get("name").toString(), listmap.get(i + 1).get("value"));
                    if(i+2<listmap.size())
                        map.put(listmap.get(i + 2).get("name").toString(), listmap.get(i + 2).get("value"));
                    map.put("org_id", org_id);
                    map.put("com_id", id);
                    tempListMap.add(j, map);
                }

              /*插入第一名、第二名、第三名*/
                service.InsertEndPrize(tempListMap.get(0));

                //redirectAttributes.addFlashAttribute("list", list);
                ajaxBean.setStatus("200");
                ajaxBean.setSuccess(true);
            }else {
                ajaxBean.setStatus("200");
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg("当前赛事下有未结束的比赛或当前赛事下没有任何比赛！");
            }


            return ajaxBean;
        } catch (Exception e) {
            log.error(e.getMessage());
            ajaxBean.setStatus("200");
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("系统操作异常，请重试！");
        }

        return ajaxBean;

    }


    /**
     * 3.通过赛事ID, 查询所有赛程信息 可以进行模糊查询
     *
     * @param com_id
     * @param stage
     * @param request
     * @return
     */
    @InterceptorAnno(createToken = true,isRestful = true)
    @RequestMapping("/{COM_ID}/{COM_LEVEL}/schedule_view")
    public String find_all_schedule1(@PathVariable("COM_ID") String com_id,
                                     @PathVariable("COM_LEVEL") String stage,
                                     HttpServletRequest request) {

        request.getSession().setAttribute("com_id", com_id);
        request.getSession().setAttribute("stage", stage);

        return "/organization/organization_system/schedule_arrange";
    }

    @RequestMapping("/{COM_ID}/{COM_LEVEL}/search_schedule")
    @ResponseBody
    @InterceptorAnno(isRestful = true)
    public PageInfo findAllSchedule(@PathVariable("COM_ID") String com_id,
                                    @PathVariable("COM_LEVEL") String com_level,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "7") Integer pageSize,
                                    HttpServletRequest request,
                                    Model model,
                                    String parameter) {

        try {

            /*获得当前机构的ORG_ID*/
            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");


            PageInfo<ScheduleBean> pageInfo = service.searchSchedule(org_id, com_id, com_level, parameter, pageNum, pageSize);

            model.addAttribute("com_id", com_id);

            return pageInfo;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 4.1 进入赛程安排页面 保存 机构信息, 赛事ID, 级别, 开始时间, 结束时间
     *
     * @param request
     * @param redirectAttributes
     * @param com_id
     * @param stage
     * @param st_time
     * @param end_time
     * @return
     */
    @RequestMapping("/{COM_ID}/{stage}/{st_time}/{end_time}/arrangement_view")
    @InterceptorAnno(isRestful = true)
    public String arrange_view(HttpServletRequest request, RedirectAttributes redirectAttributes,
                               @PathVariable("COM_ID") String com_id,
                               @PathVariable("stage") String stage,
                               @PathVariable("st_time") String st_time,
                               @PathVariable("end_time") String end_time) {


      /*安排赛事*/

        try {

            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");//机构ID


            List<Map<String, Object>> ls = service.findScheduleTeamID(org_id, com_id, stage);//得到参赛球队的ID集合
            List<Map<String, Object>> list = service.findTeamInfo(ls);//通过参赛球队的ID 得到球队的基本信息

            request.getSession().setAttribute("st_time", st_time);//赛事发布的时间
            request.getSession().setAttribute("end_time", end_time);//赛事结束时间
            request.getSession().setAttribute("com_id", com_id);//赛事ID
            request.getSession().setAttribute("stage", stage);//赛事级别
            redirectAttributes.addFlashAttribute("list", list);/*获得球队信息*/

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return "redirect:/org/arrange.html";
    }

    @InterceptorAnno(createToken = true)
    @RequestMapping("arrange")
    public String to_arrange() {
        return "/organization/organization_system/agency_schedule_arrange";
    }


    /**
     * 4.2 提交表单赛程安排信息
     *
     * @param request
     * @param schedule
     * @param redirectAttributes
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @RequestMapping("add_arrangement")
    @ResponseBody
    public AjaxBean addScheduleArrangement(HttpServletRequest request, String schedule, RedirectAttributes redirectAttributes) {
        AjaxBean ajaxBean = new AjaxBean();
        try {

              /*1.得到 前台提交的表单信息*/
            List<Map<String, Object>> listmap = JsonMapper.getInstance().fromJson(schedule, List.class);
            List<Map<String, Object>> tempListMap = new ArrayList<>();
            for (int i = 0, j = 0; i < listmap.size(); i = i + 5, j++) {

                Map<String, Object> map = new HashMap<>();

                map.put(listmap.get(i).get("name").toString(), listmap.get(i).get("value"));
                map.put(listmap.get(i + 1).get("name").toString(), listmap.get(i + 1).get("value"));
                map.put(listmap.get(i + 2).get("name").toString(), listmap.get(i + 2).get("value"));
                map.put(listmap.get(i + 3).get("name").toString(), listmap.get(i + 3).get("value"));
                map.put(listmap.get(i + 4).get("name").toString(), listmap.get(i + 4).get("value"));

                tempListMap.add(j, map);
            }


              /*2.校验主队客队赛程时间*/

            Object st_time = request.getSession().getAttribute("st_time");/*赛事发布的时间*/
            Object end_time = request.getSession().getAttribute("end_time");/*赛事结束时间*/

            boolean checkTimeInfo = checkTimeInfo(tempListMap, st_time, end_time, redirectAttributes);
            // if (!checkTimeInfo)  return "false";

              /*生成现场管理员账号、密码*/
            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);

            Object org_id = usermap.get("ORG_ID");/*获得机构ID*/
            String org_c_code = (String) usermap.get("ORG_C_CODE");/*一个市的 ID*/
            String com_id = (String) request.getSession().getAttribute("com_id");/*插入赛事ID*/

            String account = org_c_code + com_id;/*现场管理员的部分ID*/

            List<Map<String, Object>> list =
                    service.addScheduleArrangement(tempListMap, account, org_id, com_id);//插入赛程信息

            request.getSession().setAttribute("listSEInfo", list);

                /*回显用户原始信息*/
            //redirectAttributes.addFlashAttribute("list", list);
            ajaxBean.setStatus("200");
            ajaxBean.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            ajaxBean.setStatus("200");
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("系统操作异常，请重试！");
        }

        return ajaxBean;
    }


    /*校验主队客队赛程时间*/
    private boolean checkTimeInfo(List<Map<String, Object>> tempListMap, Object st_time, Object end_time, RedirectAttributes redirectAttributes) {

        for (int i = 0; i < tempListMap.size(); i++) {
            Object r_start_time = tempListMap.get(i).get("R_START_TIME");
            Object r_end_time = tempListMap.get(i).get("R_END_TIME");

                /*赛程开始时间小于赛事开始时间*/
                /*赛程结束时间小于赛事结束时间*/

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            try {
                    /*赛程*/
                Date dt1 = df.parse((String) st_time);//将字符串转换为date类型
                Date dt2 = df.parse((String) end_time);
                    /*赛事*/
                Date dt3 = df.parse((String) r_start_time);
                Date dt4 = df.parse((String) r_end_time);

                //比较时间大小,dt1小于dt2
                if (dt1.getTime() < dt2.getTime()) {
                    if (dt3.getTime() < dt4.getTime()) {
                        if (dt1.getTime() < dt3.getTime()) {
                            if (dt2.getTime() < dt4.getTime()) {
                                return true;
                            }
                            redirectAttributes.addFlashAttribute("error", "赛程时间结束时间必须小于赛事结束时间");
                            return false;
                        }
                        redirectAttributes.addFlashAttribute("error", "赛程时间开始时间必须小于赛事开始时间");
                        return false;
                    }
                    redirectAttributes.addFlashAttribute("error", "赛事时间开始时间必须小于赛事结束时间");
                    return false;

                }
                redirectAttributes.addFlashAttribute("error", "赛程时间开始时间必须小于赛程结束时间");
                return false;
            } catch (ParseException e) {
                log.error(e.getMessage());
            }

        }


        return true;
    }


    /**
     * 5.1回显插入赛程信息 进入页面
     *
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("race_details_view")
    public String findAddInfo(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        try {

            List<Map<String, Object>> listSEInfo =
                    (List<Map<String, Object>>) request.getSession().getAttribute("listSEInfo");
            redirectAttributes.addFlashAttribute("list", listSEInfo);

            return "redirect:/org/race_details.html";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("race_details")
    public String findAddInfo1() {

        return "/organization/organization_system/schedulearrange_info";
    }


    /**
     * 5.2存储现场管理员账号密码, 导出execl 数据
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("excel")
    public ModelAndView viewExcel(HttpServletRequest request) throws Exception {


        try {

            List<Map<String, Object>> listSEInfo =
                    (List<Map<String, Object>>) request.getSession().getAttribute("listSEInfo");

            Map<String, Object> model = new HashMap<>();

            model.put("list", listSEInfo);

            return new ModelAndView(new ViewExcel(), model);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
          /*  ViewExcel.buildExcelDocument(model,file_path,fileName);*/

    }


    /**
     * 6.查询单个赛程的详细信息
     *
     * @param request
     * @param id
     * @param com_id
     * @return
     */
    @RequestMapping("/edit_schedule")
    @ResponseBody
    public Map findSchedule(HttpServletRequest request, BigInteger id, String com_id) {

        try {

            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");

            String stage = (String) request.getSession().getAttribute("stage");


            List<Map<String, Object>> ls = service.findScheduleTeamID(org_id, com_id, stage);//得到参赛球队的ID集合
            List<Map<String, Object>> list = service.findTeamInfo(ls);//通过参赛球队的ID 得到球队的基本信息

            Map<String, Object> temp_map = new HashMap<>();

            for (int i = 0; i < list.size(); i++) {

                temp_map.put(list.get(i).get("TEAM_ID").toString(), list.get(i).get("TEAM_NAME"));
            }

            /*拿到该赛程的 开始时间结束时间*/
            Map map = service.findScheduleInfo(id);

            log.info("mapinfo" + map);

            map.put("team_name", temp_map);


            return map;

        } catch (Exception e) {
            log.error(e.getMessage());

        }

        return null;
    }


    /**
     * 7.编辑单个赛程的详细信息
     *
     * @param request
     * @param schedule
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @RequestMapping("/update_schedule")
    @ResponseBody
    public AjaxBean updateSchedule(HttpServletRequest request, String schedule, String com_id) {

        AjaxBean ajaxBean = new AjaxBean();
        try {
            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");

            List<Map<String, Object>> listmap = JsonMapper.getInstance().fromJson(schedule, List.class);

            List<Map<String, Object>> tempListMap = new ArrayList<>();

            for (int i = 0, j = 0; i < listmap.size(); i = i + 6) {

                Map<String, Object> map = new HashedMap();
                map.put(listmap.get(i).get("name").toString(), listmap.get(i).get("value"));
                if(i+1<listmap.size())
                    map.put(listmap.get(i + 1).get("name").toString(), listmap.get(i + 1).get("value"));
                if(i+2<listmap.size())
                    map.put(listmap.get(i + 2).get("name").toString(), listmap.get(i + 2).get("value"));
                if(i+3<listmap.size())
                    map.put(listmap.get(i + 3).get("name").toString(), listmap.get(i + 3).get("value"));
                if(i+4<listmap.size())
                    map.put(listmap.get(i + 4).get("name").toString(), listmap.get(i + 4).get("value"));
                if(i+5<listmap.size())
                    map.put(listmap.get(i + 5).get("name").toString(), listmap.get(i + 5).get("value"));

                tempListMap.add(j, map);
            }
            String end_time = service.getComEndTime(com_id);
            log.info(tempListMap.get(0).get("R_START_TIME"));
            //end_time是报名结束时间 只需赛程开始时间大于报名结束时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date END_TIME = sdf.parse(end_time);
            Date temp = sdf.parse((String)(tempListMap.get(0).get("R_START_TIME")));
            if(temp.getTime()>END_TIME.getTime()){
                service.updateSchedule(tempListMap.get(0), org_id, com_id);
                ajaxBean.setStatus("200");
                ajaxBean.setSuccess(true);
                return ajaxBean;
            }else{
                ajaxBean.setStatus("400");
                ajaxBean.setSuccess(false);
                ajaxBean.setMsg("赛程时间设置错误，请重新设置！");
                return ajaxBean;
            }


        } catch (Exception e) {
            ajaxBean.setStatus("500");
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("系统操作异常，请重试！");
            log.error(e.getMessage());
        }
        return ajaxBean;
    }


    /**
     * 8.删除单个赛程的详细信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/delete_schedule")
    @ResponseBody
    public String deleteSchedule(HttpServletRequest request, String id) {

        try {

            service.deleteSchedule(id);
            return "ok";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 9. 得倒队徽 地址
     *
     * @param team_id
     * @return
     */
    @RequestMapping("getTeamPic")
    @ResponseBody
    public String getPic(String team_id) {
        try {

            String adress = service.findPicAdress(team_id);
            return adress;

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 10.结束赛事
     *
     * @param request
     * @param level
     * @param id
     * @return
     */
    @RequestMapping("/race_end")
    @ResponseBody
    public Map<String, Object> endRance(HttpServletRequest request, String level, String id) {

        try {
            Map<String, Object> map = new HashMap<>();//存放返回的信息

             /*1.或得机构ID*/
            Map usermap = (Map) request.getSession().getAttribute(Constants.LOGIN_USER);
            Object org_id = usermap.get("ORG_ID");
            List<Map<String, Object>> ls = service.findScheduleTeamID(org_id, id, level);//得到参赛球队的ID集合

            List<Map<String, Object>> list = service.findTeamInfo(ls);//通过参赛球队的ID 得到球队的基本信息
            Map<String, Object> temp_map = new HashMap<>();

            for (int i = 0; i < list.size(); i++) {

                temp_map.put(list.get(i).get("TEAM_ID").toString(), list.get(i).get("TEAM_NAME"));

            }

            map.put("team_name", temp_map);

            return map;
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    /**
     * 11.重置现场管理员密码
     *
     * @param a_username
     * @param a_password
     * @return
     */
    @InterceptorAnno(checkToken = true, removeToken = true, isAjax = true)
    @RequestMapping(value = "/edit_schedulePwd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxBean resetSCPwd(String a_username, String a_password) {

        AjaxBean ajaxBean = new AjaxBean();
        try {

            ScheduleAcountPwdBean scheduleacountpwdbean = new ScheduleAcountPwdBean();
            scheduleacountpwdbean.setA_username(a_username);
            scheduleacountpwdbean.setA_password(a_password);

            service.updateScheulePwd(scheduleacountpwdbean);

            ajaxBean.setStatus("200");
            ajaxBean.setSuccess(true);

            return ajaxBean;

        } catch (Exception e) {
            e.printStackTrace();
            ajaxBean.setStatus("200");
            log.error(e.getMessage());
            ajaxBean.setSuccess(false);
            ajaxBean.setMsg("系统操作异常，请重试！");
        }
        return ajaxBean;
    }
}



