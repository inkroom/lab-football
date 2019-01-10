package com.nsu.dao.organization;


import com.nsu.bean.organization.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface OrgCompetitionScheduleDao {


    /**
     * @Description: TODO( 1 2 查询该机构的 所有赛事信息  可以通过赛事名称 进行模糊查询)
     * @param parameter
     * @return
     */
    public List<CompetitionBean> findQueryAll(@Param(value = "org_id") Object org_id,
                                             @Param(value = "parameter") String parameter) throws  Exception ;

    /**
     * @Description: TODO 判断赛事是否可以结束
     * @param comId 赛事Id
     * @return
     * @throws Exception
     */
    public boolean checkWouldEndCom(String comId) throws Exception;

    /**
     *
     * @param comId
     * @return
     * @throws Exception
     */
    public boolean checkHaveRaceCom(String comId) throws Exception;



    /**
     * @Description: TODO(2.2结束整个赛事,插入单个赛事的 球队第一名 第二名 第三名)
     * @param id
     * @param bean
     * @throws Exception
     */
    public void updateEndPrize( Map<String, Object> map) throws  Exception;


    /**
     * @Description: TODO(3. 查询某赛事 发布的所有赛程  可以通过赛程地区进行模糊搜索 )
     * @param org_id
     * @param com_id
     * @param stage
     * @param parameter
     * @return
     */
    public List<ScheduleBean> searchSchedule(@Param(value = "org_id") Object org_id,
                                                   @Param(value = "com_id") String com_id,
                                                   @Param(value = "stage") String stage,
                                                   @Param(value = "parameter") String parameter) throws Exception;

    /**
     * @Description: TODO(4_1_1 得到参赛球队的ID集合)
     * @param org_id
     * @param com_id
     * @param stage
     * @return
     */
    public List<Map<String,Object>> findScheduleTeamID(@Param(value = "org_id") Object org_id,
                                                       @Param(value = "com_id") String com_id,
                                                       @Param(value = "stage") String stage) throws  Exception;


    /**
     * @Description: TODO( 4_1_2 通过参赛球队的ID 得到球队的基本信息)
     * @param list
     * @return
     */
    public List<Map<String,Object>> findTeamInfo(@Param(value = "list") List<Map<String, Object>> list) throws Exception;


    /**
     * @Description: TODO( 4_2_1 赛程表插入  主队编号 开始时间 结束时间  比赛地区  客队编号)
     * @param list
     * @param com_id
     * @throws Exception
     */
    public  void addScheduleArrangement(@Param(value = "list") List<Map<String, Object>> list,
                                        @Param(value = "com_id") String com_id)throws  Exception ;


    /**
     * @Description: TODO(4_2_2 SCENE 表插入现场管理员ID)
     * @param se_username
     */
    public void addSceneID(@Param(value = "list") List<Map<String, Object>> list);


    /**
     * @Description: TODO(4_2_3更新赛程表中的 现场管理员ID)
     * @param r_id
     * @param se_username
     */
     public  void updateSceneID(@Param(value = "r_id") Object r_id,
                               @Param(value = "se_username") Object se_username);


    /**
     * @Description: TODO(4_2_4.Account表 插入现场管理员 ID Password )
     * @param list
     * @throws Exception
     */
     public void addAccountSceneIDPwd(@Param(value = "list") List<Map<String, Object>> list) throws Exception;

    /**
     * @Description: TODO( 4_2_5通过球队ID拿到球队名称)
     * @param team_id
     * @return
     */
    public Object findTeamNameByID(@Param(value = "team_id") Object team_id) throws Exception;

    /**
     * @Description: TODO( 4_2_6 SYSTEM_MSG 插入一条赛程发布消息)
     * @param a_id
     * @param com_id
     * @param SM_TITLE
     * @param SM_TEXT
     */
    public void addSysMsgInfo(@Param(value = "a_id") Object a_id,
                              @Param(value = "com_id") String com_id,
                              @Param(value = "SM_TITLE") String SM_TITLE,
                              @Param(value = "SM_TEXT") String SM_TEXT);


    /**
     * @Description: TODO(5. 查询单个赛程的详细信息)
     * @param r_id
     * @return
     */
    public ScheduleBean findScheduleInfo(BigInteger r_id) throws  Exception;

    /**
     * @Description: TODO(6.  编辑单个赛程的详细信息)
     * @param schedule
     */
    public  void updateSchedule(Map<String, Object> schedule) throws  Exception;


    /**
     * @Description: TODO(7.  删除单个赛程的详细信息)
     * @param r_id
     */
    public void deleteSchedule(String r_id) throws  Exception;

    /**
     * @Description: TODO(8. 查找队徽地址)
     * @param team_id
     * @return
     */
    public String findPicAdress(String team_id);



    /**
     * @Description: TODO(手机端1. 查询是否存在此用户)
     * @param a_username
     * @return
     */
    public List<Map<String,Object>> findUserInfo(String a_username);

    /**
     * @Description: TODO(手机端 2.通过机构赛事ID 获得 赛程信息)
     * @param a_username
     * @param com_id
     * @return
     */
    public List<MobileScheduleBean> getRaceInfo(@Param(value = "a_username") String a_username,
                                                @Param(value = "com_id") String com_id);

    /**
     * @Description: TODO(通过机构ID 获取机构名)
     * @param org_id
     * @return
     */
    public String findOrgName(Object org_id);

    /**
     * @Description: 更新表中 SCENE表中 A_ID
     * @param a_id
     * @param se_id
     */
   public void updateSceneA_ID(@Param(value = "a_id") Object a_id,
                               @Param(value = "se_id") Object se_id);

    /**
     * @Description: 获得球队 A_ID 插入赛程发布信息
     * @param r_h_team_id
     */
   public String findTeamA_ID(String id);

    /**
     * 重置现场管理员密码
     * @param bean
     * @return
     */
    public void updateScheulePwd(ScheduleAcountPwdBean bean) throws  Exception;

    /**
     * 获取报名结束
     * @param com_id
     * @return
     */
    public String getComEndTime(String com_id);
}


