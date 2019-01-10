package cn.nsu.edu.web.four.daos.jdbc.team;

import cn.nsu.edu.web.four.beans.teams.Team;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface CreateTeamDao {

    /**
     * 创建球队
     */

    public int  createTeam(@Param("team") Team team) throws  Exception;


    /**
     * 添加教练
     *
     */

    public  int  addCoach(@Param("idTeam") int idTeam,
                          @Param("idCoach") int idCoach,
                          @Param("status") int status,
                          @Param("time") Date time) throws  Exception;


    /**
     *添加球员
     */

    public int   addPlayer(@Param("list")List<Integer> list,@Param("idTeam") int id_team,
                           @Param("status") int status ,
                           @Param("time") Date time) throws  Exception;




}
