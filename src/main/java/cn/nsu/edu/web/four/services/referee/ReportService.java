package cn.nsu.edu.web.four.services.referee;

import cn.nsu.edu.web.four.beans.referee.PlayerDescription;
import cn.nsu.edu.web.four.beans.referee.Report;
import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.daos.jdbc.referee.ReportDao;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.exception.RollbackException;
import cn.nsu.edu.web.four.schedule.FinishSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

@Service
public class ReportService {

    @Autowired
    private ReportDao dao;
    @Autowired
    private FinishSchedule finishSchedule;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${upload.image.base.path}")
    private String path;

    /**
     * 验证json数据
     *
     * @param list
     * @return
     */
    public Result checkJson(List<PlayerDescription> list) {
        Date now = new Date();
        for (PlayerDescription player : list) {
            if (player.getType() == 1) {
                player.setDate(now);
            } else if (player.getType() == 2) {

            } else if (player.getType() == 3) {

            } else if (player.getType() == 4) {

            } else if (player.getType() == 5) {

            } else {
                return Result.PARAM_NOT_SUIT;
            }
        }
        return Result.SUCCESS;
    }

    @Transactional
    public boolean addReport(Report report, List<PlayerDescription> list, Schedule schedule, Integer refId) {
        try {
            report.setSchId(schedule.getIdSchedule());
            report.setRefId(refId);
            if (schedule.getGoalA().equals(schedule.getGoalB())) {
//                report.setRefId(-1);
                report.setResult(null);
            } else if (schedule.getGoalA() > schedule.getGoalB()) {
                report.setResult(schedule.getTeamA());
            } else {
                report.setResult(schedule.getTeamB());
            }
            if (dao.insertReport(report) >= 1) {
//                list.forEach(description -> description.setReportId(report.getId()));
                //更新积分
                //获取赛事级别
                int level = 1;
                int result = -1;
                //更新教练积分
                dao.updateCoachScore(schedule.getTeamA(), getScore(report.getResult() == null ? null : (report.getResult().equals(schedule.getTeamA())), level));
                dao.updateCoachScore(schedule.getTeamB(), getScore(report.getResult() == null ? null : (report.getResult().equals(schedule.getTeamB())), level));

                //更新球队积分
                dao.updateTeamScore(schedule.getTeamA(), getScore(report.getResult() == null ? null : (report.getResult().equals(schedule.getTeamA())), level));
                dao.updateTeamScore(schedule.getTeamB(), getScore(report.getResult() == null ? null : (report.getResult().equals(schedule.getTeamB())), level));

                //更新球员积分

                for (PlayerDescription player : list) {
                    if (player.getType() == 1 || player.getType() == 2) {//仅给首发和替补加分
                        result = dao.updatePlayerScore(player.getPlayerId(), getScore(report.getResult() == null ? null : (report.getResult() == player.getTeamId()), level));
                        if (result != 1) {
                            throw new RollbackException("更新球员积分失败");
                        }
                    }
                    result = dao.insertPlayer(player, report.getId());
                    if (result != 1) {
                        throw new RollbackException("插入球员失败");
                    }
                }

//                result = dao.insertPlayer(list, report.getId());
//                if (result != list.size()) {
//                    log.error("插入数量不对，数据回滚，数据report=" + report + "，list=" + list);
//                    throw new RollbackException("批量插入数量不对");
//                }
                //更新比赛状态码
                result = dao.updateScheduleStatus(schedule.getIdSchedule(), 2);
                if (result < 1) {
                    log.error("更新赛程状态失败，对应赛程id" + schedule.getIdSchedule());
                    throw new RollbackException("更新赛程状态失败，对应赛程id" + schedule.getIdSchedule());
                }
                //修改session、中的状态
                schedule.setStatus(2);
                //启动删除图片和数据的定时器
                finishSchedule.setDirectory(path + BaseStatic.PREFIX_LIVE_DIRECTORY + schedule.getIdSchedule());
                finishSchedule.setSchId(schedule.getIdSchedule());
                finishSchedule.start();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发生异常，数据回滚，数据report=" + report + "，list=" + list);
            throw new RollbackException(e);
        }
    }

    /**
     * 获取积分
     *
     * @param result 输赢，true为胜利，null为平局
     * @param level  级别
     * @return 应该增加的积分
     */
    private int getScore(Boolean result, int level) {
        if (result == null) {
            return 2 * (level + 1);
        } else if (result) {//赢
            return 3 * (level + 1);
        } else {
            return (level + 1);
        }
    }

    public List<PlayerDescription> getPlayers(Integer schId) {
        try {
            return dao.selectPlayer(schId);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void deleteMessage(int schId) {
        try {
            dao.deleteMessage(schId);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

//    public Schedule getSchedule(Integer schId) {
//        try {
//            return dao.selectSchedule(schId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


}
