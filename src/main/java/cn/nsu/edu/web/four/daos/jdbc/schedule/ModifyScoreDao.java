/**
 * @author :mengyixin
 * @Description:
 *  2018/3/23
 */
package cn.nsu.edu.web.four.daos.jdbc.schedule;

import cn.nsu.edu.web.four.beans.schedule.Schedule;

public interface ModifyScoreDao {

    public void modifyScore(Schedule schedule)throws Exception;
}
