package cn.nsu.edu.web.four.daos.jdbc.team;

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
public interface PlayerMapper {
    /**
    * @author:YuanXin
    * @Description:根据多个条件查询符合条件的学生集合
    * @Date: 16:20 2018/3/22/022
    **/

    List<Player> selectPlayersByconditions(@Param("name") String name,@Param("grade")Integer grade,@Param("classes") Integer classes,@Param("orgId") Integer orgId);
    /**
    * @author:YuanXin
    * @Description:根据球员ID查询该球员的信息
    * @Date: 18:52 2018/3/22/022
    **/

    Player selectPlayerById(Integer idPlayer);

    /**
    * @author:YuanXin
    * @Description:根据IDS查询所有的球员信息
    * @Date: 17:01 2018/3/25/025
    **/

    List<Player> selectPlayerByIds(@Param("ids") List<Integer> ids);
    /**
    * @author:YuanXin
    * @Description:查询所有被插入的id值
    * @Date: 15:01 2018/3/28/028
    **/

    List<Integer> selectAllPlayerIds();
    /**
    * @author:YuanXin
    * @Description:获得总数据
    * @Date: 14:22 2018/3/30/030
    **/

    int selectCountsByCondition(PageQueryBean playerCondition);
    /**
    * @author:YuanXin
    * @Description:获得分页的数据
    * @Date: 14:38 2018/3/30/030
    **/

    List<Player> selectPlayerByPage(PageQueryBean playerCondition);
}
