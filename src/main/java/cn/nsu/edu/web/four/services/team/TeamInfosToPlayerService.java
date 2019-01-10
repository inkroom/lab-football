package cn.nsu.edu.web.four.services.team;

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
public interface TeamInfosToPlayerService {
    /**
    * @author:YuanXin
    * @Description:添加所有被选中的球员id
    * @Date: 13:56 2018/3/28/028
    **/

    Integer addPlayerInfo(ArrayList<Integer> allID,Integer teamId);
    /**
    * @author:YuanXin
    * @Description:查询这只球队已经插入的所有球员id
    * @Date: 15:45 2018/3/28/028
    **/

    List<Integer> findAllPlayersByTeamId(Integer dbTeamId);
}
