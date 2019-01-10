package com.nsu.dao.organization;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrgCreateMatchDao {
	
	/**
	 * 将添加过的赛事id查询出来，以备发送消息时使用
	 * @param o_id
	 * @param match_name
	 * @return
	 * @throws Exception
	 */
	public Long selectMatchID(@Param("o_id")long o_id,@Param("match_name")String match_name) throws Exception;
	
	/**
	 * 判断同一个机构创建的赛事名称是否有重复
	 * @param o_id
	 * @param match_name
	 * @return
	 * @throws Exception
	 */
	public Long isDuplicationMatchName(@Param("o_id")long o_id,
			@Param("match_name")String match_name) throws Exception;
	
	/**
	 * 添加一场赛事
	 * @param o_id
	 * @param match_info
	 * @return
	 * @throws Exception
	 */
	public int addMatchInfo(@Param("o_id")long o_id, 
			@Param("match_info")Map<String, String> match_info) throws Exception;

	/**
	 * 在赛事成功添加之后，发布一条推送消息
	 * @param a_id
	 * @param o_id
	 * @param msg_type
	 * @param msg_title
	 * @param msg_text
	 * @param com_id
	 * @return
	 * @throws Exception
	 */
	public int addMatchMsg(@Param("a_id")int a_id, @Param("o_id")long o_id, 
			@Param("msg_type")int msg_type, @Param("msg_title")String msg_title, 
			@Param("msg_text")String msg_text, @Param("com_id")Long com_id) throws Exception;
}
