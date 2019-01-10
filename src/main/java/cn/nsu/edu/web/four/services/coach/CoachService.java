package cn.nsu.edu.web.four.services.coach;

import cn.nsu.edu.web.four.beans.coach.Coach;
import cn.nsu.edu.web.four.beans.coach.MTeamCoach;

import java.util.List;

public interface CoachService {

    //增加教练
    public int addCoach(Coach coach);

    //修改就职状态
    public int coachStatus(Integer idCoach);

    //获取全部教练信息
    List<Coach> getAllCoach(Integer org_id,int state);

    //获取教练信息
    public Coach getCoach(Integer id_coach);

    //修改教练信息
    public int updateCoach(Coach coach);

    //修改头像
    public int updatePhoto(Coach coach);

    //获取教练任职信息
    List<MTeamCoach> getAllTeamByCoach(Integer coach_id);

}
