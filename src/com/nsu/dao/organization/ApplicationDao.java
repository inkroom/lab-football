package com.nsu.dao.organization;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
* @ClassName: ApplicationDao 
* @Description: 球队申请管理Dao 
* @author 严涛
* @date 2017年4月17日 下午5:24:55 
*
 */
public interface ApplicationDao {
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有球队的申请 Dao
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<Object, Object>> findAll(long ORG_ID) throws Exception;
	
	/**
	 * 
	* @Title: updateTeamAffiliation 
	* @Description: 根据机构ID更新球队所属机构
	* @param @param ORG_ID
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateTeamAffiliation(long ORG_ID) throws Exception;
	
	/**
	 * 
	* @Title: findone 
	* @Description: 搜索
	* @param @param ORG_ID
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Map<Object,Object>>    返回类型 
	* @throws
	 */
	public List<Map<Object, Object>> findone(@Param("ORG_ID") long ORG_ID,@Param("comName") String comName) throws Exception;
	
	/**
	 * 
	* @Title: seeTeamMessage 
	* @Description: 查询球队中队员详情
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<Object,Object>    返回类型       
	* @throws
	 */
	public List<Map<String, Object>> findPlayerMessage(long team_id) throws Exception;
	
	
	/**
	 * 
	* @Title: seeTeamMessage 
	* @Description: 通过球队ID查球队教练
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<Object,Object>    返回类型       
	* @throws
	 */
	public List<String> selectCoachByTeamID(long team_id) throws Exception;
	
	
	/**
	 * 
	* @Title: selectMatchIDByMatchName 
	* @Description: 根据赛事名称查赛事id dao
	* @param @param match_name
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public Map<String, Object> selectMatchIDByMatchName(String match_name) throws Exception;
	
	/**
	 * 
	* @Title: findTeamMessage 
	* @Description: 查询球队详情Dao
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String, Object> findTeamMessage(long team_id) throws Exception;
	
	/**
	 * 
	* @Title: updateTeamStatus 
	* @Description: 更新通过球队审核状态Dao
	* @param @param team_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateTeamStatus(@Param("team_id") long team_id,@Param("match_id") Integer match_id) throws Exception;
	
	/**
	 * 
	* @Title: updateTeam 
	* @Description: 更新不通过球队审核状态Dao 
	* @param @param team_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateTeam(@Param("team_id") long team_id,@Param("match_id") Integer match_id) throws Exception;


	
	/**
	 * 
	* @Title: selectTeamIDByName 
	* @Description: 根据球队名称查球队ID DAO
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public int updateTeamOrgID(@Param("org_id") long org_id,@Param("team_id") long team_id) throws Exception;
	
	
	/**
	 * 
	* @Title: findTeamMessage 
	* @Description: 根据机构ID查A_ID
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public int selectAId(long org_id) throws Exception;
	
	
	/**
	 * 
	* @Title: findTeamMessage 
	* @Description: 根据机构ID查A_ID
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public String selectOrgName(long org_id) throws Exception;
	
	
	/**
	 * 
	* @Title: selectR_V_TEAM 
	* @Description: 根据球队ID查球队参加赛事的客场总数
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int selectR_V_TEAM(long team_id) throws Exception;
	
	/**
	 * 
	* @Title: selectR_V_TEAM 
	* @Description: 根据球队ID查球队参加赛事的主场总数
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	
	public int selectR_H_TEAM(long team_id) throws Exception;
	
	
	/**
	 * 
	* @Title: selectR_H_TEAM_WIN 
	* @Description: 根据球队ID查球队参加赛事的主场赢球总数 
	* @param @param team_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int selectR_H_TEAM_WIN(long team_id) throws Exception;
	
	/**
	 * 
	* @Title: selectR_H_TEAM_WIN 
	* @Description: 根据球队ID查球队参加赛事的客场赢球总数 
	* @param @param team_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int selectR_V_TEAM_WIN(long team_id) throws Exception;
}
