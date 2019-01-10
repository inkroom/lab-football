package com.nsu.service.organization;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: IApplicationService 
* @Description: 球队申请管理Service 
* @author 严涛
* @date 2017年4月17日 下午5:26:41 
*
 */
public interface IApplicationService {
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有球队的申请 
	* @param @param pageNum
	* @param @param pageSize
	* @param @return
	* @param @throws Exception    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws
	 */
	public PageInfo<Map<Object, Object>> findAll(@Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize,
			@Param("ORG_ID") long ORG_ID) throws Exception;
	
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 搜索
	* @param @param pageNum
	* @param @param pageSize
	* @param @return
	* @param @throws Exception    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<Object, Object>> findone(@Param("ORG_ID") long ORG_ID,@Param("comName") String comName) throws Exception;
	/**
	 * 
	* @Title: seeTeamMessage 
	* @Description: 机构查看球队中队员详情Service
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<Object,Object>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findPlayerMessage(long team_id) throws Exception;
	
	
	
	/**
	 * 
	* @Title: selectMatchIDByMatchName 
	* @Description: 根据赛事名称查赛事id Service
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
	* @Description: 查看球队详情Service
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
	* @Description: 更新通过球队审核状态Service 
	* @param @param team_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateTeamStatus(@Param("team_id") long team_id ,@Param("match_id") Integer match_id) throws Exception;
	
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
	public int updateTeam( @Param("team_id") long team_id ,@Param("match_id") Integer match_id) throws Exception;
	
	
	
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
	

}
