package cn.nsu.edu.web.four.services.match;

import cn.nsu.edu.web.four.beans.match.Match;
import cn.nsu.edu.web.four.beans.match.MatchExamine;
import cn.nsu.edu.web.four.beans.match.MatchPlayer;
import cn.nsu.edu.web.four.beans.match.TeamInfo;
import cn.nsu.edu.web.four.beans.organization.Organization;
import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 痞老板
 * @Project: four
 * @Package:cn.nsu.edu.web.four.services.match
 * @date 2018/3/20 11:06
 * @description
 **/
public interface MatchService {
    //查询发布赛事列表
    List<Match> selectByIdWithOrg(int orgId);
    //查询已加入赛事
    List<Match> selectByStaff(int orgId);
    //查询未加入赛事
    List<Match> selectByStaffOther(int orgId);
    //获取分好队的队员以及队名
    List<Map<String, Object>> listTeamAndPlayer(int orgId, int mid);
    //插入选择参加赛事的队员
    int insertPlayer(List<MatchPlayer> list, Integer matchId, Integer orgId);
    //查询待审核机构
    List<MatchExamine> listExamine(int matchId);

    List<Player> listExaminePlayer(int teamId, int mid);

    List<TeamInfo> listExamineTeam(int mId, int oid);
    //更新审核信息
    void updateStaffStatus(int status, int orgId, int mid);


    //赛事名称查重
    boolean checkMatchName(String name, Integer matchId);

    //获取赛事level
    Integer getLevel(int matchId);


    /**
     * 插入赛事
     * @author Xuing
     * @param match org_id, `name`,start_date, num_player, apply_deadline, `level`, status
     * @return true 成功 false 失败
     */
    boolean insertMatch(Match match);

    /**
     *  通过赛事ID获取赛事信息
     *  @author Xuing
     * @param matchId 赛事ID
     * @return 赛事信息 查询不到返回null
     */
    Match getMatchInfoById(Integer matchId);

    /**
     * 修改赛事信息
     * @author Xuing
     * @param match 新的赛事Bean,需包含Id
     * @return 成功true，失败flase
     */
    boolean modifyMatch(Match match);

    /**
     * 通过赛事ID获取通过了审核的机构列表
     *
     * @param matchId 赛事Id
     * @return 机构列表
     * @author Xuing
     */
    List<Organization> getMatchOrgList(Integer matchId);

    /**
     * 通过赛事Id和组织Id获取通过了审核的队伍列表
     *
     * @param matchId 赛事Id
     * @param orgId   组织Id
     * @return 队伍列表
     * @author Xuing
     */
    List<Team> getMatchStaffTeamList(Integer matchId, Integer orgId);

    boolean checkMatchDate(Date beforeDate, Date afterDate);

    int checkOrgStatus(Integer orgId, Integer mid);

    boolean checkAuthority(Integer matchId, HttpServletRequest request);

}
