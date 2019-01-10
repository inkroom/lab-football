package cn.nsu.edu.web.four.services.team;

import cn.nsu.edu.web.four.beans.teams.CoachInfo;

import java.util.List;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public interface CoachInfoService {
    /**
    * @author:YuanXin
    * @Description:根据用户名查找教练的基本信息
    * @Date: 11:36 2018/3/22/022
    **/

    List<CoachInfo> findCoachByName(String coachName,Integer orgId);
    /**
    * @author:YuanXin
    * @Description:根据用ID查询教练
    * @Date: 14:47 2018/3/22/022
    **/

    CoachInfo findCoachById(Integer idCoach);
    /**
    * @author:YuanXin
    * @Description:获得所有得教练信息
    * @Date: 8:48 2018/3/23/023
    **/

    List<CoachInfo> findAllCoaches(Integer orgId) throws Exception;
}
