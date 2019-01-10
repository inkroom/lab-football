package com.nsu.service.organization;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.organization.CompetitionBean;
import com.nsu.bean.organization.ScheduleAcountPwdBean;
import com.nsu.bean.organization.ScheduleBean;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface OrgCompetitionScheduleService {


    /**
     * @Description: TODO(1.2. 查询该机构的 安排的所有赛事信息  可以进行模糊查询 )
     * @param username
     * @param pageNum
     * @param pageSize
     * @param parameter
     * @return
     */
    public PageInfo<CompetitionBean> findQueryAll(Object username, Integer pageNum, Integer pageSize, String parameter) throws Exception;


    /**
     * @Description: TODO 判断赛事是否可以结束
     * @param comId 赛事Id
     * @return
     * @throws Exception
     */
    public boolean checkWouldEndCom(String comId) throws Exception;


    /**
     * @Description: TODO( 2.2 结束整个赛事,插入单个赛事的 球队第一名、第二名、第三名)
     * @param bean
     * @throws Exception
     */
    public  void InsertEndPrize(Map<String, Object> bean) throws  Exception;


    /**
     * @Description: TODO(3.通过赛事ID,查询所有赛程信息 可以进行模糊查询)
     * @param org_id
     * @param com_id
     * @param stage
     * @param parameter
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ScheduleBean> searchSchedule(Object org_id, String com_id, String stage, String parameter, Integer pageNum, Integer pageSize) throws Exception;


    /**
     * @Description: TODO(4.1_1 得到参赛球队的ID集合)
     * @param org_id
     * @param com_id
     * @param stage
     * @return
     */
    public List<Map<String,Object>> findScheduleTeamID(Object org_id, String com_id, String stage) throws Exception;


    /**
     * @Description: TODO( 4.1_2 通过参赛球队的ID 得到球队的基本信息)
     * @param list
     * @return
     */
    public List<Map<String, Object>> findTeamInfo(List<Map<String, Object>> list) throws Exception;


    /**
     * @Description: TODO(4.2插入赛程信息)
     * @param schedule
     * @param account
     * @param username
     * @param com_id
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> addScheduleArrangement(List<Map<String, Object>> schedule, String account, Object username, String com_id) throws Exception;


    /**
     * @Description: TODO( 5 查询单个赛程的详细信息)
     * @param r_id
     * @return
     * @throws Exception
     */
    public Map findScheduleInfo(BigInteger r_id) throws Exception;


    /**
     * @Description: TODO(6.编辑单个赛程的详细信息)
     * @param schedule
     * @param org_id
     * @param com_id
     */
    public void updateSchedule(Map<String, Object> schedule, Object org_id, String com_id) throws Exception;

    /**
     * @Description: TODO(7.删除单个赛程的详细信息)
     * @param r_id
     */
    public void deleteSchedule(String r_id) throws Exception;

    /**
     * @Description: TODO(8.查找队徽地址)
     * @param team_id
     * @return
     */
    public String findPicAdress(String team_id);


    /**
     * @Description: TODO(手机端 1. 查询是否存在此用户)
     * @param a_username
     * @return
     */
    public List<Map<String,Object>> getUserInfo(String a_username);


    /**
     * @Description: TODO(手机端  通过机构赛事ID 获得 赛程信息)
     * @param a_username
     * @param com_id
     * @return
     */
    public List getRaceInfo(String a_username, String com_id);

    /**
     * 重置现场管理员密码
     * @param bean
     * @return
     */
    public void updateScheulePwd(ScheduleAcountPwdBean bean) throws Exception;

    /*********获取比赛报名结束时间/
     *
     */
    public String getComEndTime(String com_id);
}
