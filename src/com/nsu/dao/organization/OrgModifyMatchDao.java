package com.nsu.dao.organization;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrgModifyMatchDao {
	
	/**
	 * 查询当前机构可以修改和删除的赛事列表
	 * @param o_id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getEffectiveMatchList(long o_id) throws Exception;

	/**
	 * 删除可以删除的赛事
	 * @param match_id
	 * @return
	 * @throws Exception
	 */
	public int deleteMatch(int match_id)throws Exception;

	/**
	 * 得到需要修改的赛事的信息
	 * @param match_id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMatchByMatchId(int match_id) throws Exception;

	/**
	 * 修改指定的赛事
	 * @param match_id
	 * @param match_info
	 * @return
	 * @throws Exception
	 */
	public int modifyMatchInfo(@Param("match_id")int match_id,
		@Param("match_info")Map<String, String> match_info) throws Exception;

	/**
	 * 在赛事成功被修改之后，发布一条推送消息
	 * @param match_id
	 * @param a_id
	 * @param o_id
	 * @param msg_type
	 * @param msg_title
	 * @param msg_text
	 * @return
	 * @throws Exception
	 */
	public int addMatchMsg(@Param("match_id")int match_id,@Param("a_id")int a_id, @Param("o_id")long o_id,
		@Param("msg_type")int msg_type, @Param("msg_title")String msg_title,
		@Param("msg_text")String msg_text) throws Exception;

	/**
	 * @param match_name
	 * @param o_id 
	 * @return
	 */
	public List<Map<String, Object>> selectByMatchName(@Param("match_name")String match_name, @Param("o_id")long o_id);
	
	/**
	 * 判断赛事名是否存在
	 * @param o_id
	 * @param match_name
	 * @param com_id
	 * @return
	 * @throws Exception
	 */
	public int isDuplicationMatchName(@Param("o_id")long o_id, @Param("match_name")String match_name,@Param("com_id")int com_id) throws Exception;
	
	/**
	 * 判断当前赛事是否更改
	 * @param match_info
	 * @return
	 * @throws Exception
	 */
	public int isChangeMatch(@Param("match_info")Map<String, String> match_info,@Param("com_id")int com_id) throws Exception;
	
	/**
	 * 更新操作时间
	 * @param match_id
	 * @throws Exception
	 */
	public void updateTime(@Param("match_id")int match_id) throws Exception;
	
	/**
	 * 得到赛程场次
	 * @param match_id
	 * @return
	 * @throws Exception
	 */
	public int getScheduleCount(String match_id) throws Exception;
}
