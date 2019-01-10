package cn.nsu.edu.web.four.services.team;

import cn.nsu.edu.web.four.beans.teams.TeamInfos; /**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public interface TeamInfosToTeamService {
    /**
    * @author:YuanXin
    * @Description:添加创建球队时的球队信息
    * @Date: 17:18 2018/3/27/027
    **/

    int addTeamInfo(TeamInfos teamInfos);
    /**
    * @author:YuanXin
    * @Description:先从数据库查找是否有相同的球队名字，有的话，返回球队ID
    * @Date: 15:24 2018/3/28/028
    **/

    Integer findTeamInfo(String teamName);
    /**
     * @author:YuanXin
     * @Description:查询数据是否存在该队名
     * @Date: 16:42 2018/3/28/028
     **/
    Integer findTeamName(String teamName);
}
