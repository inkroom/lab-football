package cn.nsu.edu.web.four.daos.jdbc.team;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public interface TeamInfosToPlayerMapper {
    /**
     * @author:YuanXin
     * @Description:添加所有被选中的球员id
     * @Date: 13:56 2018/3/28/028
     **/

    Integer insertPlayerInfo(@Param("playerIds") ArrayList<Integer> playerIds, @Param("teamId") Integer teamId);
    /**
    * @author:YuanXin
    * @Description:查询这只球队已经插入的所有球员id
    * @Date: 15:46 2018/3/28/028
    **/

    List<Integer> selectAllPlayersByTeamId(Integer dbTeamId);
}
