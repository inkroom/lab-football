/**
 *
 */
package com.nsu.dao.coach;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.org.glassfish.gmbal.ParameterNames;

/**
 * @author 潘泳言   * @date 2017年4月17日 下午2:19:37
 * @version V1.0
 * @Title: CoachTrainningDao.java
 * @Package com.nsu.dao.coach
 * @Description: TODO
 */
public interface CoachTrainingDao {

    /**
     * @param coachID
     * @return
     */
    public List<String> getCoachTeams(String coachID);

    /**
     * @param title
     * @param teamid
     * @param place
     * @param time
     * @param typs
     * @param coachid
     */
    public void coachTrainingIssue(@Param("trainName") String title,
                                   @Param("trainTeamid") String teamid,
                                   @Param("trainPlace") String place,
                                   @Param("trainTime") String time,
                                   @Param("trainTyps") String typs,
                                   @Param("coachId") String coachid
    );

    /**
     * @param title
     * @param teamid
     * @param place
     * @param time
     * @param typs
     * @param coachid
     */
    public void coachSystemIssue(@Param("A_ID") String A_ID,
                                 @Param("SM_TYPE") int SM_TYPE,
                                 @Param("SM_TITLE") String SM_TITLE,
                                 @Param("SM_TEXT") String SM_TEXT,
                                 @Param("SM_DATE") String SM_DATE,
                                 @Param("SM_STATUS") int SM_STATUS,
                                 @Param("coachId") String coachId
    );

    /**
     * @param team_id
     * @return
     */
    List<String> ByTeamidSelectPlayerid(String team_id);

    /**
     * @param string
     * @return
     */
    public String ByPlayerSelectdeviceInfo(String string);
}
