package com.nsu.service.team.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamApplyMatchBean;
import com.nsu.dao.team.TeamApplyMatchDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamApplyMatchService;
import com.nsu.util.TimeToolUtil;
import com.nsu.util.V;

/**
 * 球队比赛生申请管理接口实现类
 *
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-17 10:50:52
 */
@Service("teamApplyMatchService")
public class TeamApplyMatchServiceImpl extends BaseService implements ITeamApplyMatchService {

    @Autowired
    TeamApplyMatchDao teamApplyMatchDao;

    @Override
    public String updateTeamApplyMatch(String teamID, String comID, String teamType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teamID", teamID);
        map.put("comID", comID);
        try {
            //判断这项赛事是否还可以报名；即报名时间是否截止
            TeamApplyMatchBean bean = teamApplyMatchDao.findOneMatch(comID);
            int num = teamApplyMatchDao.findMathContinue(comID);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (format.parse(bean.getComEndTime()).after(new Date())) {
                //可以报名
                //判断级别是否符合
                log.info("teamType "+teamType+"   "+bean.getComType());
                if (!bean.getComType().equals("不限") && !teamType.equals(bean.getComType())) {
                    return "级别不符合，不能报名！";
                }
                //查询球队是否申请过这个比赛
                String status = teamApplyMatchDao.findTeamAlreadyApply(map);
                if (!V.checkEmpty(status)) {
                    //申请过
                    //判断状态： 审核中、审核通过不允许再报名
                    if (status.equals("0")) {
                        return "请等待举办方审核!";
                    } else if (status.equals("1")) {
                        return "已报名成功，请勿重复操作!";
                    } else {
                        //不通过，允许操作;更新申请状态位
                        num = teamApplyMatchDao.updateApplyMatchRecord(map);
                        if (num == 1) {
                            return null;
                        } else {
                            return "服务器忙,请稍后再试!";
                        }
                    }
                } else {
                    //没申请过：允许操作；插入申请
                    num = teamApplyMatchDao.insertApplyMatchRecord(map);
                    if (num == 1) {
                        return null;
                    } else {
                        return "服务器忙,请稍后再试!";
                    }
                }
            } else {
                //不可报名,已到截止时间
                return "对不起!已截止报名!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "服务器忙,请稍后再试!";
        }

    }

    @Override
    public Page findTeamApplyMatchInfoBySeacherKey(String teamID, String matchName) {
        int pageNum = 1;
        int totalRecordsNum = 1;
        Page page = null;

        //competition_notice表查询赛事信息
        //过滤掉比赛时间结束的比赛
        try {

            page = new Page(pageNum, totalRecordsNum, 2);
            List records = ProcessingMatchData(teamApplyMatchDao.findTeamMatchsByMatchName(teamID, matchName));
            page.setRecords(records);

        } catch (Exception e) {
            log.error(e.getMessage());

        }
        return page;
    }

    @Override
    public Page findTeamApplyMatchInfoRecordsByTeamID(String teamID, String num) {
        int pageNum = 1;
        int totalRecordsNum = 0;
        Page page = null;
        if (num != null && !num.equals("")) {
            pageNum = Integer.parseInt(num);
        }

        try {
            //获取总页数
            totalRecordsNum = teamApplyMatchDao.findTeamApplyMatchTotalRecordsNum(teamID);
            page = new Page(pageNum, totalRecordsNum, 8);

            page.setUrl("/team/team_apply_view/");

            List records = ProcessingMatchData(teamApplyMatchDao.findTeamApplyMatchPageRecords(teamID, page.getStartIndex(), page.getPageSize()));

            page.setRecords(records);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return page;
    }

    /**
     * 将查询到的赛事数据进一步处理
     *
     * @param teamApplyMatchList
     * @return
     * @author ljl
     * @createDate 2017-04-21 15:49:02
     */
    public List<TeamApplyMatchBean> ProcessingMatchData(List<TeamApplyMatchBean> teamApplyMatchList) {

        if (teamApplyMatchList != null && teamApplyMatchList.size() > 0) {
            for (int i = 0; i < teamApplyMatchList.size(); i++) {
                teamApplyMatchList.get(i).setComTime(null);

                //是否报名了，且报名是否审核通过了
                String comStatus = teamApplyMatchList.get(i).getComStatus();
                if (!V.checkEmpty(comStatus)) {
                    //判断审核状态
                    if (comStatus.equals("1") || comStatus.equals("0")) {
                        //报名通过了或正正在审核
                        teamApplyMatchList.get(i).setAllowApply(0);
                        teamApplyMatchList.get(i).setApplyText("待审核");
                        if (comStatus.equals("1")) {
                            teamApplyMatchList.get(i).setApplyText("报名成功");
                            //允许安排球员
                            teamApplyMatchList.get(i).setAllowPlanPlayers(1);
                        }
                    } else {
                        //报名没通过，允许再次报名
                        //判断报名时间是否过了
                        //获取报名截止时间和现在相差多少
                        double timeInterval = TimeToolUtil.nowTimeInterval(teamApplyMatchList.get(i).getComEndTime());
                        if (timeInterval <= 0) {
                            //报名时间已经过了，不能再报名了
                            teamApplyMatchList.get(i).setAllowApply(0);
                            teamApplyMatchList.get(i).setApplyText("报名截止");
                        } else {
                            teamApplyMatchList.get(i).setAllowApply(1);
                            //不允许安排球员
                            teamApplyMatchList.get(i).setAllowPlanPlayers(0);
                        }
                    }
                } else {
                    //还没报过名，判断报名时间是否终止
                    //获取报名截止时间和现在相差多少
                    double timeInterval = TimeToolUtil.nowTimeInterval(teamApplyMatchList.get(i).getComEndTime());
                    if (timeInterval <= 0) {
                        //报名时间已经过了，不能再报名了
                        teamApplyMatchList.get(i).setAllowApply(0);
                        teamApplyMatchList.get(i).setApplyText("报名截止");
                    } else {
                        teamApplyMatchList.get(i).setAllowApply(1);
                        //不允许安排球员
                        teamApplyMatchList.get(i).setAllowPlanPlayers(0);
                    }
                }

            }
            return teamApplyMatchList;
        } else {
            return null;
        }
    }

}
