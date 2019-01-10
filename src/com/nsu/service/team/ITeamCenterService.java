package com.nsu.service.team;

import java.util.List;
import java.util.Map;

import com.nsu.bean.team.TeamBasicInfoBean;
import com.nsu.bean.team.TeamCenterDO;

/**
 * 球队中心service接口
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-12 14:33:30
 */
public interface ITeamCenterService {

	/**
	 * 检查球队基本信息合法性和存储数据
	 * @author ljl
	 * @createDate 2017-04-12 16:05:50
	 * @param files
	 * @param teamBasicInfo
	 * @return 返回处理结果
	 */
	
	public String updateTeamBasicInfo(TeamBasicInfoBean teamBasicInfo);
	/**
	 * @Description: 查询球队基础信息
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public Map<String, Object> findFootballTeamInfo(String TEAM_ID);
	
	/**
	 * @Description: 查询球队教练员名字
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return 拼接为一个字符串
	 */
	public List<TeamCenterDO> findTeamCoachName(String TEAM_ID);

	/**
	 * @Description: 查询球队所有队员
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public List<TeamCenterDO> findTeamPlayerInfo(String TEAM_ID);
	
	/**
	 * 查询球队原始信息
	 * 查询球队的；队名、类型、领队名、手机号、励志标语、图片、音乐、邮箱
	 * @author ljl
	 * @createDate 2017-04-14 14:55:43
	 * @param teamID 球队ID
	 * @return
	 */
	public TeamBasicInfoBean findTeamBasicInfo(String teamID);
	
	/**
	 * 更新球队队旗或队徽图片
	 * @author ljl
	 * @createDate 2017-04-18 08:55:54
	 * @param teamID 球队ID
	 * @param filePath 图片存储路径
	 * @return
	 */
	public String updateTeamPhotos(String teamID, String filePath, String type);

	
	/**
	 * @Description: 查询球队教练员名字
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return 
	 */
	public List<TeamCenterDO> findTeamCoachNameCheck(String TEAM_ID) throws Exception;

	/**
	 * @Description: 查询球队所有队员
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	public List<TeamCenterDO> findTeamplayerInfoCheck(String TEAM_ID) throws Exception;
	
	/**
	 * 更新球队队歌
	 * @author ljl
	 * @createDate 2017-04-20 09:56:05
	 * @param teamID
	 * @param object
	 * @return
	 */
	public String updateTeamSong(String teamID, Object object);
	
	/**
	 * @Description: 查询团队机构信息	
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:38:09
	 * @return
	 */
	public TeamCenterDO findTeamOrgDaoService(String TEAM_ID);
	
	/**
	 * @Description: 查询所有机构信息
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:38:09
	 * @return
	 */
	public TeamCenterDO findALLOrgDaoService(String ORG_ID);
	
	/**
	 * @Description: 修改机构申请机构id以及申请状态	
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午11:33:14
	 * @return
	 */
	public String updateOrgStatusService(String ORG_ID,String TEAM_ID);

	/**
	 * 更新球队邮箱
	 * @author ljl
	 * @createDate 2017-04-28 08:49:07
	 * @param aid
	 * @param email
	 * @param code
	 * @return
	 */
	public String updateTeamEmail(String aid, String email, String code, String account);

	/**
	 * 获取邮箱验证码	
	 * @author ljl
	 * @createDate 2017-04-28 11:15:28
	 * @param aid
	 * @return
	 */
	public String findEamilVerificationCode(String aid);
	
	/**
	 * @Description: 根据P_ID查询A_ID	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public Long findAIDBYPIDService(String P_ID);
	
	/**
	 * @Description: 根据COACH_ID查询A_ID	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public Long findAIDBYCOACHIDService(String COACH_ID);
	
	/**
	 * @Description: 根据TEAM_ID查询A_ID和TEAM_NAME	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public TeamCenterDO findAIDBYTEAMService(String TEAM_ID);
	
	/**
	 * @Description: 根据COACH_ID查询A_ID	
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	public Long findAIDBYORGService(String ORG);
}
