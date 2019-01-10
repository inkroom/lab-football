package com.nsu.service.organization.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.organization.CompetitionBean;
import com.nsu.bean.organization.MobileScheduleBean;
import com.nsu.bean.organization.ScheduleAcountPwdBean;
import com.nsu.bean.organization.ScheduleBean;
import com.nsu.dao.message.MessageDao;
import com.nsu.dao.organization.OrgCompetitionScheduleDao;
import com.nsu.service.BaseService;
import com.nsu.service.organization.OrgCompetitionScheduleService;
import com.nsu.util.InfoProUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgCompetitionScheduleServiceImpl   extends BaseService  implements OrgCompetitionScheduleService {

    @Resource
    private OrgCompetitionScheduleDao dao;
    @Resource
    private MessageDao messageDao;


    /**
     * @Description: TODO( 1 2 查询该机构的 所有赛事信息  可以进行赛事信息 模糊查询 )
     * @param org_id
     * @param pageNum
     * @param pageSize
     * @param parameter
     * @return
     */
    @Override
    public PageInfo<CompetitionBean> findQueryAll(Object org_id, Integer pageNum, Integer pageSize, String parameter) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<CompetitionBean> list = dao.findQueryAll(org_id, parameter);
        PageInfo<CompetitionBean> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public boolean checkWouldEndCom(String comId) throws Exception {
        return (!dao.checkWouldEndCom(comId))&&(dao.checkHaveRaceCom(comId));
    }

    /**
     * @Description: TODO( 2.2 结束整个赛事,插入单个赛事的 球队第一名、第二名、第三名)
     * @param bean
     * @throws Exception
     */
    @Override
    public void InsertEndPrize(Map<String, Object> bean)  throws  Exception{

        dao.updateEndPrize(bean);


    }



    /**
     * @Description: TODO( 3.搜索改赛事下所有赛事信息  ,可通过赛程地区进行模糊搜素)
     * @param org_id
     * @param com_id
     * @param stage
     * @param parameter
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo searchSchedule(Object org_id, String com_id, String stage, String parameter, Integer pageNum, Integer pageSize) throws Exception {

        PageHelper.startPage(pageNum, pageSize);


        List<ScheduleBean> list = dao.searchSchedule(org_id,com_id,stage,parameter);
        PageInfo<ScheduleBean> pageInfo = new PageInfo<>(list);

        return pageInfo;

    }

    /**
     * @Description: TODO(4.1_1 得到参赛球队的ID集合)
     * @param org_id
     * @param com_id
     * @param stage
     * @return
     */
    @Override
    public List<Map<String, Object>> findScheduleTeamID(Object org_id, String com_id, String stage) throws Exception {

        List<Map<String, Object>> list= dao.findScheduleTeamID(org_id,com_id,stage);

        return list;
    }

    /**
     * @Description: TODO(4.1_2通过参赛球队的ID 得到球队的基本信息)
     * @param ls
     * @return
     */
    @Override
    public List<Map<String, Object>> findTeamInfo(List<Map<String, Object>> ls) throws Exception {

        List<Map<String,Object>> list=dao.findTeamInfo(ls);

        return list;
    }


    /**
     * @Description: TODO(4.2 提交表单赛程信息)
     * @param schedule
     * @param account
     * @param username
     * @param com_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String,Object>> addScheduleArrangement(List<Map<String, Object>> schedule, String account, Object username, String com_id) throws Exception {


         dao.addScheduleArrangement(schedule,com_id);//  1.赛程表插入  主队编号 开始时间 结束时间  比赛地区  客队编号

         List<Map<String, Object>> list_rid=new ArrayList<>();    //2.接收 R_ID集合

         for (int i = 0; i < schedule.size(); i++) {

            Object r_id = schedule.get(i).get("R_ID");
            Map<String, Object> map = new HashMap<>();
            map.put("R_ID", r_id);

            list_rid.add(i,map);

         }


        /*3.生成 现场管理员账号、密码*/

        List<Map<String, Object>> templist_account = new ArrayList<>();
        List<Map<String, Object>> templist_password = new ArrayList<>();



        /*管理员账号 生成  －－*/

          /*存储原始账号*/
        List original_account = new ArrayList();
        for (int i = 0; i < list_rid.size(); i++) {

            Map<String, Object> map = new HashMap<>();


            Object r_id = list_rid.get(i).get("R_ID");
            Object new_account = r_id + account;

            original_account.add(i,new_account);

            /*现场管理员账号进行 异或加密*/
            map.put("SE_USERNAME", InfoProUtil.xorInfo( new_account.toString()));
            templist_account.add(i, map);

        }


        /*管理员密码生成*/

         /*存储原始密码*/
        List original_password = new ArrayList();
        for (int i = 0; i < list_rid.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            String password = InfoProUtil.getRandomString(8);

            original_password.add(i,password);

            String md5password = InfoProUtil.parseStrToMd5L32(password);

            map.put("SE_PASSWORD", md5password);
            templist_password.add(i,map);

        }


          /*4.SCENE  */

            dao.addSceneID(list_rid);

        List<Map<String,Object>> list_se_id = new ArrayList<>();
        for (int i = 0; i < list_rid.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            map.put("SE_ID", list_rid.get(i).get("SE_ID"));
            list_se_id.add(i,map);
        }


          /*5.更新 赛程表中 现场管理员ID*/
        for (int i = 0; i < list_rid.size(); i++) {

            dao.updateSceneID(list_rid.get(i).get("R_ID"),list_rid.get(i).get("SE_ID"));

        }


        /*6.Account 表 插入 现场管理员账号密码,返回A_ID*/

        List<Map<String,Object>> list_aid = new ArrayList<>();
        List<Map<String, Object>> list_info = new ArrayList<>();
        for (int i = 0; i < list_rid.size(); i++) {


            Map<String, Object> map = new HashMap<>();
            map.put("SE_USERNAME", templist_account.get(i).get("SE_USERNAME"));
            map.put("SE_PASSWORD", templist_password.get(i).get("SE_PASSWORD"));
            map.put("ORG_ID", username);
            map.put("SE_ID", list_rid.get(i).get("SE_ID"));
            list_info.add(i,map);
        }
            dao.addAccountSceneIDPwd(list_info);



        /*7.通过 球队ID拿到球队名字
            返回 插入的  赛程信息 */

        List<Map<String, Object>> list_SEinfo = new ArrayList<>();

        for (int i = 0; i < schedule.size(); i++) {

            Map<String, Object> map = new HashMap<>();

            String R_H_TEAM_NAME= (String) dao.findTeamNameByID( schedule.get(i).get("R_H_TEAM_ID"));

            String R_V_TEAM_NAME= (String) dao.findTeamNameByID(schedule.get(i).get("R_V_TEAM_ID"));

            String R_H_TEAM_Pic = dao.findPicAdress((String) schedule.get(i).get("R_H_TEAM_ID"));
            String R_V_TEAM_Pic = dao.findPicAdress((String) schedule.get(i).get("R_V_TEAM_ID"));

            map.put("R_H_TEAM_ID", schedule.get(i).get("R_H_TEAM_ID"));
            map.put("R_V_TEAM_ID", schedule.get(i).get("R_V_TEAM_ID"));

            map.put("R_H_TEAM_NAME",R_H_TEAM_NAME);/*主队名*/
            map.put("R_V_TEAM_NAME",R_V_TEAM_NAME );/*客队名*/

            map.put("R_START_TIME", schedule.get(i).get("R_START_TIME"));/*赛程开始时间*/
            map.put("R_END_TIME", schedule.get(i).get("R_END_TIME"));/*赛程结束时间*/
            map.put("R_REGION", schedule.get(i).get("R_REGION"));/*赛程地区*/

            map.put("A_USERNAME", original_account.get(i));/*现场管理员账号*/
            map.put("A_PASSWORD", original_password.get(i));/*现场管理员密码*/

            map.put("R_H_TEAM_Pic", R_H_TEAM_Pic);
            map.put("R_V_TEAM_Pic", R_V_TEAM_Pic);

            list_SEinfo.add(i, map);

        }

        /*8.更新表中 SCENE表中 A_ID*/
        for (int i = 0; i < list_info.size(); i++) {
            dao.updateSceneA_ID(list_info.get(i).get("A_ID"), list_info.get(i).get("SE_ID"));
        }

        /*9.通过机构ID 获取 机构名*/
        String org_name=dao.findOrgName(username);

        /*10. SYSTEM_MSG 插入一条赛程发布消息*/
        for (int i = 0; i < list_info.size(); i++) {
            String R_H_TEAM_NAME = (String) list_SEinfo.get(i).get("R_H_TEAM_NAME");
            String R_V_TEAM_NAME = (String) list_SEinfo.get(i).get("R_V_TEAM_NAME");
            String SM_TITLE=R_H_TEAM_NAME+" VS "+R_V_TEAM_NAME;

            String SM_TEXT= org_name+"安排了"+R_H_TEAM_NAME+","+R_V_TEAM_NAME +"比赛<br>"+"地区:"+schedule.get(i).get("R_REGION")+"<br>"+"赛程开始时间:"+ schedule.get(i).get("R_START_TIME")+"<br>"+"赛程结束时间 :" +schedule.get(i).get("R_END_TIME") ;
            dao.addSysMsgInfo(list_info.get(i).get("A_ID"),com_id,SM_TITLE,SM_TEXT);
        }



        /*11.向比赛相关的球队插入球队消息*/

        for (int i = 0; i < list_info.size(); i++) {

            String R_H_TEAM_NAME = (String) list_SEinfo.get(i).get("R_H_TEAM_NAME");
            String R_V_TEAM_NAME = (String) list_SEinfo.get(i).get("R_V_TEAM_NAME");
            String SM_TITLE=R_H_TEAM_NAME+" VS "+R_V_TEAM_NAME;

            String SM_TEXT= org_name+"修改了"+R_H_TEAM_NAME+","+R_V_TEAM_NAME +"比赛<br>"+"地区:"+schedule.get(i).get("R_REGION")+"<br>"+"赛程开始时间:"+ schedule.get(i).get("R_START_TIME")+"<br>"+"赛程结束时间 :" +schedule.get(i).get("R_END_TIME") ;

            /*获得球队 A_ID 插入赛程发布信息*/


            log.info("(String) list_SEinfo.get(i))h"+(String) list_SEinfo.get(i).get("R_H_TEAM_ID"));
            log.info("(String) list_SEinfo.get(i)v"+(String) list_SEinfo.get(i).get("R_V_TEAM_ID"));

            String  h_id= dao.findTeamA_ID((String) list_SEinfo.get(i).get("R_H_TEAM_ID"));
            log.info("--==="+h_id);
            String  v_id=  dao.findTeamA_ID((String) list_SEinfo.get(i).get("R_V_TEAM_ID"));
            log.info("--==="+v_id);

            Map h_map =new HashMap();
            Map v_map =new HashMap();

            /**
             * 插入个人消息(map)
             * #{PS_RECEIVE}接收人ID, #{PS_SEND_ID}发送人ID, #{PS_TITLE}消息标题, #{PS_TEXT}消息内容
             */

            h_map.put("PS_RECEIVE",h_id);
            h_map.put("PS_SEND_ID",username);
            h_map.put("PS_TITLE",SM_TITLE);
            h_map.put("PS_TEXT",SM_TEXT);


            v_map.put("PS_RECEIVE",v_id);
            v_map.put("PS_SEND_ID",username);
            v_map.put("PS_TITLE",SM_TITLE);
            v_map.put("PS_TEXT",SM_TEXT);

            log.info(h_map);
            log.info(v_map);
            messageDao.insertPersonMessage(h_map);
            messageDao.insertPersonMessage(v_map);


        }

       return list_SEinfo;



        }

    /**
     * @Description: TODO(5 查询单个赛程的详细信息)
     * @param r_id
     * @return
     * @throws Exception
     */
    @Override
    public Map findScheduleInfo(BigInteger r_id) throws Exception {

        ScheduleBean map = dao.findScheduleInfo(r_id);

        Object r_h_team_name = dao.findTeamNameByID( map.getR_h_team_id());
        Object r_v_team_name = dao.findTeamNameByID( map.getR_v_team_id());

        HashMap<Object, Object> temp_map = new HashMap<>();

        temp_map.put("R_H_TEAM_ID", map.getR_h_team_id());
        temp_map.put("R_V_TEAM_ID", map.getR_v_team_id());
        temp_map.put("R_H_TEAM_NAME", r_h_team_name);
        temp_map.put("R_V_TEAM_NAME", r_v_team_name);
        temp_map.put("R_START_TIME", map.getR_start_time());
        temp_map.put("R_END_TIME", map.getR_end_time());
        temp_map.put("R_REGION", map.getR_region());

        return temp_map;
    }

    /**
     * @Description: TODO( 6. 编辑单个赛程的详细信息)
     * @param schedule
     * @param org_id
     * @param com_id
     */
    @Override
    public void updateSchedule(Map<String, Object> schedule, Object org_id, String com_id) throws  Exception{

        dao.updateSchedule(schedule);


         /*更新 时  SYSTEM_MSG 插入一条赛程发布消息*/

            String R_H_TEAM_NAME= (String) dao.findTeamNameByID( schedule.get("R_H_TEAM_ID"));

             String R_V_TEAM_NAME= (String) dao.findTeamNameByID(schedule.get("R_V_TEAM_ID"));

            String SM_TITLE=R_H_TEAM_NAME+" VS "+R_V_TEAM_NAME;

            String org_name=dao.findOrgName(org_id);
            String SM_TEXT= org_name+" 修改了"+R_H_TEAM_NAME+","+R_V_TEAM_NAME +"比赛<br>"+"地区:"+schedule.get("R_REGION")+"<br>"+"赛程开始时间:"+ schedule.get("R_START_TIME")+"<br>"+"赛程结束时间 :" +schedule.get("R_END_TIME") ;
            dao.addSysMsgInfo(schedule.get("A_ID"),com_id,SM_TITLE,SM_TEXT);
    }

    /**
     * @Description: TODO(7. 删除单个赛程的详细信息)
     * @param r_id
     */
    @Override
    public void deleteSchedule(String r_id) throws Exception {

        dao.deleteSchedule(r_id);
    }

    /**
     * @Description: TODO(8.查找队徽地址)
     * @param team_id
     * @return
     */
    @Override
    public String findPicAdress(String team_id) {

        String pic_adress= dao.findPicAdress(team_id);

        return pic_adress;
    }



    /**
     * @Description: TODO(手机端 1. 查询是否存在此用户)
     * @param a_username
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserInfo(String a_username) {

        List<Map<String, Object>> list=dao.findUserInfo(a_username);
        return list;
    }

    /**
     * @Description: TODO(手机端 2.通过机构赛事ID 获得 赛程信息)
     * @param a_username
     * @param com_id
     * @return
     */
    @Override
    public List getRaceInfo(String a_username, String com_id) {

        List<MobileScheduleBean> list=dao.getRaceInfo(a_username, com_id);

        return list;
    }

    /**
     * 重置现场管理员密码
     * @param bean
     * @return
     */
    @Override
    public void updateScheulePwd(ScheduleAcountPwdBean bean) throws Exception {
         dao.updateScheulePwd(bean);
    }

    @Override
    public String getComEndTime(String com_id){
        log.info("date*********************:"+dao.getComEndTime(com_id));
        return dao.getComEndTime(com_id);
    }


}
