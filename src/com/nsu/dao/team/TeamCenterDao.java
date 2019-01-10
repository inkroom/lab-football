package com.nsu.dao.team;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamBasicInfoBean;
import com.nsu.bean.team.TeamCenterDO;
import com.nsu.bean.team.TeamCoachBean;
import com.sun.xml.internal.ws.message.StringHeader;

/**
* @ClassName: 球队显示信息中心
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 侯润达
* @date 2017年4月12日 下午5:27:00 
*
 */
public interface TeamCenterDao {

	/**
	 * @Description: 查询球队基础信息
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public Map<String, Object> findFootballTeamDao(String TEAM_ID) ;
	
	/**
	 * @Description: 查询球队教练信息
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public List<TeamCenterDO> findTeamCoachDao(String TEAM_ID) ;

	/**
	 * @Description: 查询球队所有队员
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public List<TeamCenterDO> findTeamplayerDao(String TEAM_ID) throws Exception;

	
	/**
	 * 查询所有的教练员信息
	 * @author ljl
	 * @createDate 2017-04-13 16:44:36
	 * @return
	 * @throws Exception
	 */
	public List<TeamCoachBean> findTeamCoachsInfo(String teamID) throws Exception;
	
	/**
	 * 查询球队队员数目
	 * @author ljl
	 * @createDate 2017-04-13 16:33:46
	 * @param teamID
	 * @return
	 * @throws Exception
	 */
	public int findTeamPlayersNum(String teamID) throws Exception;
	
	/**
	 * 更新球队的基本信息
	 * @author ljl
	 * @createDate 2017-04-13 11:30:57
	 * @param teamBasicInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTeamBasicInfo(TeamBasicInfoBean teamBasicInfo) throws Exception;
	
	/**
	 * 更新球队在account表中的A_NAME
	 * @author ljl
	 * @createDate 2017-04-13 11:31:01
	 * @param teamBasicInfo
	 * @return
	 * @throws Exception
	 */
	public int updateTeamANameInAccount(Map<String, Object> map) throws Exception;
	
	/**
	 * 向TEAMNOTICE表插入球队的基本信息
	 * @author ljl
	 * @createDate 2017-04-13 16:10:13
	 * @param teamBasicInfo
	 * @return
	 * @throws Exception
	 */
	public int insertIntoTeamNoticeTble(TeamBasicInfoBean teamBasicInfo) throws Exception;

	/**
	 * 查询球队原始信息
	 * 查询球队的；队名、类型、领队名、手机号、励志标语
	 * @author ljl
	 * @createDate 2017-04-14 14:59:54
	 * @param teamID
	 * @return
	 */
	public TeamBasicInfoBean findTeamBasicInfo(String teamID) throws Exception;

	/**
	 * 更新球队队徽
	 * @author ljl
	 * @createDate 2017-04-18 09:04:30
	 * @param teamID
	 * @param filePath
	 * @return
	 */
	public int updateTeamLogo(@Param("teamID") String teamID, @Param("filePath") String filePath);

	/**
	 * 更新球队队旗
	 * @author ljl
	 * @createDate 2017-04-18 09:04:33
	 * @param teamID
	 * @param filePath
	 * @return
	 */
	public int updateTeamFlag(@Param("teamID") String teamID, @Param("filePath") String filePath);
	
	/**
	 * @Description: 查询球队所有队员
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public List<TeamCenterDO> findTeamplayerCheckDao(String TEAM_ID);

	
	/**
	 * 查询所有的教练员信息
	 * @author ljl
	 * @createDate 2017-04-13 16:44:36
	 * @return
	 * @throws Exception
	 */
	public List<TeamCenterDO> findTeamCoachCheckInfo(String TEAM_ID) ;

	/**
	 * 更新球队队歌
	 * @author ljl
	 * @createDate 2017-04-20 09:59:20
	 * @param teamID
	 * @param path
	 * @return
	 */
	public int updateTeamSong(@Param("teamID") Object teamID, @Param("songPath") Object songPath);

	/**
	 * 查询球队邮箱
	 * @author ljl
	 * @createDate 2017-04-25 18:11:58
	 * @param teamID
	 * @return
	 * @throws Exception
	 */
	public String findTeamEmail(@Param("teamID") String teamID) throws Exception;
	
	/**
	 * @Description: 查询机构信息	
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:21:51
	 * @return
	 */
	public TeamCenterDO findTeamOrgDao(String TEAM_ID) throws Exception;
	
	
	/**
	 * @Description: 查询所有机构信息
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:21:51
	 * @return
	 */
	public TeamCenterDO findALLOrgDao(String ORG_ID) throws Exception;
	
	/**
	 * @Description: 修改机构申请机构id以及申请状态	
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午11:31:29
	 * @return
	 */
	public int updateOrgStatus(@Param("ORG_ID")String ORG_ID,@Param("TEAM_ID")String TEAM_ID);

	/**
	 * 更新球队邮箱
	 * @author ljl
	 * @createDate 2017-04-28 09:10:04
	 * @param map
	 * @return
	 */
	public int updateTeamAEmailInAccount(Map<String, Object> map) throws Exception;
	
	/**
	 * @Description: 根据P_ID查询A_ID	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public int findAIDBYPID(String P_ID) throws Exception;
	
	/**
	 * @Description: 根据COACH_ID查询A_ID	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public int findAIDBYCOACHID(String COACH_ID) throws Exception;
	
	/**
	 * @Description: 根据TEAM_ID查询A_ID和TEAM_NAME	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public TeamCenterDO findAIDBYTEAM(String TEAM_ID) throws Exception;
	
	/**
	 * @Description: 查询团队比赛总数	
	 * @author 侯润达
	 * @create_date 2017年6月5日 下午4:54:42
	 * @return
	 */
	public int findMatchTeamNUM(String TEAM_ID) throws Exception;
	
	/**
	 * @Description: 查询团队比赛获胜数
	 * @author 侯润达
	 * @create_date 2017年6月5日 下午4:54:42
	 * @return
	 */
	public int findMatchTeamWIN(String TEAM_ID) throws Exception;
	
	/**
	 * @Description: 根据ORG_ID查询A_ID	
	 * @author 侯润达
	 * @create_date 2017年6月7日 下午4:47:43
	 * @return
	 */
	public int findAIDBYORG(String ORG) throws Exception;
}
