package cn.nsu.edu.web.four.daos.jdbc.match;

import cn.nsu.edu.web.four.beans.match.*;
import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MatchDao {
    int deleteById(Integer idMatch) throws Exception;

    /**
     * 插入赛事（基本信息）
     *
     * @param match（org_id, `name`,start_date, num_player, apply_deadline, `level`, status） id_Match,end_date可选
     * @return int
     * @throws Exception
     */
    int insert(Match match) throws Exception;

    /**
     * 通过赛事ID查赛事信息
     *
     * @param idMatch 赛事ID
     * @return Match对象，包含赛事信息
     * @throws Exception
     */
    Match selectById(Integer idMatch) throws Exception;

    /**
     * 通过Id更新对应Match
     *
     * @param match 新的赛事Bean
     * @return 影响行数
     * @throws Exception
     */
    int updateById(Match match) throws Exception;

    List<Match> selectByIdWithOrg(int orgId);

    List<Match> selectByStaff(int orgId);

    List<Match> selectByStaffOther(int orgId);

    List<MatchPlayer> selectPlayerByOrgId(@Param("oid") int orgId);

    List<Map<String,Object>> listTeamIdByorgId(int orgId);

    int selectMstaff(@Param("pid") int pid, @Param("mid") int mid, @Param("tid") int tid);

    int insertPlayer(List<MatchPlayer> list);

    List<MatchExamine> selectExamineOrg(int matchId);

    List<TeamInfo> selectExamineTeam(@Param("mid") int mId, @Param("oid") int oid);

    List<Player> selectExaminePlayer(@Param("tid") int tId, @Param("mid") int mid);

    int countByMatchName(MatchExample example);

    void updateStaffStatus(@Param("status") int status, @Param("orgid") int orgId, @Param("mid") int mid);

    Integer getLevel(int matchId);

    int statusCount(@Param("mid") int matchId, @Param("oid") int oid);

    /**
     * 通过赛事Id查询对应的主办方机构Id。
     *
     * @param matchId 赛事Id
     * @return 主办方机构Id
     */
    Integer getOrgIdByMatchId(Integer matchId);

    Integer getOrgIdByMatch(Integer matchId);
    /**
     * 通过赛事Id查询映射表M_Match_Staff中属于该赛事且通过审核的所有机构
     *
     * @param matchId 赛事Id
     * @return 机构List
     * @throws Exception
     * @author Xuing
     */
    List<Organization> selectMatchStaffOrgsByMatchId(Integer matchId) throws Exception;

    /**
     * 通过赛事Id和机构Id查询映射表M_Match_Staff中属于该赛事属于该机构且通过审核的所有队伍
     *
     * @param matchId 赛事Id
     * @param orgId   机构Id
     * @return 队伍List
     * @throws Exception
     * @author Xuing
     */
    List<Team> selectMatchStaffTeamsByMatchOrgId(@Param("matchId") Integer matchId, @Param("orgId") Integer orgId);

    Integer checkOrgStatus(@Param("oid") int orgId, @Param("mid") int mid);

    int deleteStaff(@Param("oid") int orgId, @Param("mid") int mid);
}