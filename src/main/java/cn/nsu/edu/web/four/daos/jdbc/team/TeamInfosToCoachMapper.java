package cn.nsu.edu.web.four.daos.jdbc.team;

import cn.nsu.edu.web.four.beans.teams.TeamInfos; /**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public interface TeamInfosToCoachMapper {
    /**
    * @author:YuanXin
    * @Description:插入教练与球队对应的ID
    * @Date: 13:20 2018/3/28/028
    **/

    int insertCoachInfo(TeamInfos teamInfos);

    Integer selectCoachByOrg(TeamInfos teamInfos);
}
