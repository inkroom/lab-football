package cn.nsu.edu.web.four.services.referee;

import cn.nsu.edu.web.four.beans.schedule.Schedule;
import cn.nsu.edu.web.four.dto.stc.referee.PlayerInformationDto;
import cn.nsu.edu.web.four.dto.stc.referee.ScheduleInformationDto;
import cn.nsu.edu.web.four.exception.RollbackException;

import java.util.Date;
import java.util.List;

public interface RefereeService {

    //通过裁判员登陆后的赛程id查询赛事名称，比赛开始时间，主队名，客队名
    ScheduleInformationDto getScheduleInformation(Integer scheduleId) throws Exception;

    //通过赛程id获取球员的姓名和照片
    PlayerInformationDto getPlayerInformation(Integer teamId ) throws Exception;


    /**
     * 向赛程出场人员表插入球员信息
     * @param playerAId 主队球员
     * @param playerBId 客队球员
     * @param scheduleId 有赛程id
     * @param teamAId 主队id
     * @param teamBId 客队id
     * @param refereeId 裁判员id
     * @return 结果
     * @throws RollbackException 数据回滚
     */
    boolean insertPlayerInformation(List<Integer> playerAId,List<Integer> playerBId, Integer scheduleId,Integer teamAId,Integer teamBId,Integer refereeId) throws RollbackException;



}
