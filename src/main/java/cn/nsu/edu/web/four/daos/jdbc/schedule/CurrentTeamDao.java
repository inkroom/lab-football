package cn.nsu.edu.web.four.daos.jdbc.schedule;

import cn.nsu.edu.web.four.beans.schedule.CurrentTeam;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author 灵魂都在冒香气的神
 * @date 2018/3/21.
 */
@Repository
public interface CurrentTeamDao
{
    List<CurrentTeam> getTeamByOrg(Integer org_id);
}
