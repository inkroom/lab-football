package cn.nsu.edu.web.four.daos.jdbc.team;

import cn.nsu.edu.web.four.beans.teams.Team;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OperateInfoDao {


    /**
     * 删除球队球员
     *只需要改变映射表中球员所在球队的status
     */

    public  int   updatePlayer(@Param("idTeam")int idTeam,
                               @Param("idPlayer")int idPlayer,
                               @Param("status")int status,
                               @Param("time")Date time ) throws Exception;

    /**
     * 更新球队教练
     * 直接改变了教练所在球队的status
     */

    public int   updateCoach(@Param("idTeam")int idTeam,
                             @Param("idCoach")int idCoach,
                             @Param("status")int status,
                             @Param("time") Date time)throws  Exception;

    /**
     * 球队退役
     */

    public  int  retireTeam(@Param("idTeam") int idTeam,
                            @Param("status") int status,
                            @Param("retireTime") Date retireTime) throws  Exception;

    /**
     * 修改球队信息
     */

    public int   upDateTeamInfo(@Param("team") Team team) throws Exception ;
}

