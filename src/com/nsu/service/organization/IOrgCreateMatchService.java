package com.nsu.service.organization;

import java.util.Map;

public interface IOrgCreateMatchService {
	
	/**
	 * 找到这场赛事对应的com_id
	 * @param o_id
	 * @param match_name
	 * @return
	 * @throws Exception
	 */
	public int selectMatchID(long o_id, String match_name) throws Exception;
	
	/**
	 * 判断同一个机构创建的赛事名称是否有重复
	 * @param o_id
	 * @param match_name
	 * @return
	 * @throws Exception
	 */
	public boolean isDuplicationMatchName(long o_id, String match_name) throws Exception;
	
	/**
	 * 举办一场赛事
	 * @param o_id
	 * @param match_info
	 * @param msg_type
	 * @param msg_title
	 * @param msg_text
	 * @param a_id
	 * @return
	 * @throws Exception
	 */
	public boolean addMatchInfo(long o_id, Map<String, String> match_info, 
			int msg_type, String msg_title, String msg_text, int a_id) throws Exception;
	
}
