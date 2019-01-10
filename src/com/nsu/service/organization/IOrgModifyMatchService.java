package com.nsu.service.organization;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.organization.CompetitionBean;

public interface IOrgModifyMatchService {
	
	/**
	 * 查询当前机构可以修改和删除的赛事列表
	 * @param o_id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getEffectiveMatchList(long o_id) throws Exception;

	/**
	 * 删除指定的赛事
	 * @param match_id
	 * @return
	 * @throws Exception
	 */
	public int deleteMatch(int match_id,long o_id,
			int msg_type, String msg_title, String msg_text, int a_id) throws Exception;

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
	 * @param o_id
	 * @param match_info
	 * @param msg_type
	 * @param msg_title
	 * @param msg_text
	 * @param a_id
	 * @return
	 * @throws Exception
	 */
	public boolean modifyMatchInfo(int match_id, long o_id, Map<String, String> match_info,
		int msg_type, String msg_title, String msg_text, int a_id) throws Exception;

	/**
	 * @param match_name
	 * @param o_id 
	 * @return
	 */
	public List<Map<String, Object>> selectByMatchName(String match_name, long o_id);
	
	/**
	 * 判断赛事名是否存在
	 * @param o_id
	 * @param match_name
	 * @param com_id
	 * @return
	 * @throws Exception
	 */
	public boolean isDuplicationMatchName(long o_id, String match_name,int com_id) throws Exception;
	
	/**
	 * 判断当前赛事是否更改
	 * @param match_info
	 * @return
	 * @throws Exception
	 */
	public boolean isChangeMatch(Map<String, String> match_info, int com_id) throws Exception;
	
	/**
	 * 查询当前机构可以修改和删除的赛事列表(分页)
	 * @param o_id
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Map<String, Object>> getPageEffectiveMatchList(Integer pageNum, long o_id) throws Exception;
	
	/**
	 * 得到赛程场次
	 * @param match_id
	 * @return
	 * @throws Exception
	 */
	public int getScheduleCount(String match_id) throws Exception;
}
