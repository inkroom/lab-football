/**
 * @author :mengyixin
 * @Description: 2018/3/23
 */
package cn.nsu.edu.web.four.services.referee;

import cn.nsu.edu.web.four.daos.jdbc.referee.RefereeDao;
import cn.nsu.edu.web.four.daos.jdbc.schedule.ModifyScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyScoreService {

    @Autowired
    public RefereeDao dao;

    public boolean modifyScore(int scoreA, int scoreB, int schId) {
        try {
            return dao.updateScore(scoreA, scoreB, schId) >= 1;
//            modify.modifyScore(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
