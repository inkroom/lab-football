package cn.nsu.edu.web.four.daos.jdbc.team;

import cn.nsu.edu.web.four.beans.teams.CoachInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public interface CoachInfoMapper {
    /**
    * @author:YuanXin
    * @Description:根据用户名查找教练的基本信息
    * @Date: 11:41 2018/3/22/022
    **/

    List<CoachInfo> findCoachByName(@Param("coachName") String coachName,@Param("orgId")Integer orgId);
    /**
    * @author:YuanXin
    * @Description:根据ID查询教练
    * @Date: 14:47 2018/3/22/022
    **/

    CoachInfo selectCoachById(Integer idCoach);
    /**
    * @author:YuanXin
    * @Description:查询所有的教练信息
    * @Date: 8:50 2018/3/23/023
    **/

    List<CoachInfo> selectAllCoaches(@Param("orgId") Integer orgId);
}
