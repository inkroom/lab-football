package cn.nsu.edu.web.four.services.team;

import cn.nsu.edu.web.four.beans.teams.TeamInfos;
/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
public interface TeamInfosToCoachService {
    /**
    * @author:YuanXin
    * @Description:插入教练与球队对应的ID
    * @Date: 13:18 2018/3/28/028
    **/

    int addCoachInfo(TeamInfos teamInfos);

    Integer findCoachByOrg(TeamInfos teamInfos);
}
