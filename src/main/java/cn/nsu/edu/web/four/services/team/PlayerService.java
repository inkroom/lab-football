package cn.nsu.edu.web.four.services.team;

import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.PageQueryBean;
import cn.nsu.edu.web.four.beans.teams.PlayerCondition;
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
public interface PlayerService {
/**
* @author:YuanXin
* @Description:多个条件查询符合的学生集合
* @Date: 16:17 2018/3/22/022
**/

    List<Player> findPlayersByconditions(@Param("playerCondition") PlayerCondition playerCondition,@Param("orgId") Integer orgId) throws Exception;

    /**
    * @author:YuanXin
    * @Description:根据球员ID查询对应的球员
    * @Date: 18:51 2018/3/22/022
    **/

    Player findPlayerByPlayerId(Integer idPlayer);
    /**
    * @author:YuanXin
    * @Description:根据IDS查询所有的球员
    * @Date: 17:00 2018/3/25/025
    **/

    List<Player> findPlayerByPlayerIds(List<Integer> ids);

    /**
    * @author:YuanXin
    * @Description:查询所有被插入的球员id
    * @Date: 14:59 2018/3/28/028
    **/

    List<Integer> findAllPlayerIds();
}
