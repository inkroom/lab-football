package com.nsu.service.organization.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.organization.CompetitionBean;
import com.nsu.dao.organization.OrgModifyMatchDao;
import com.nsu.service.BaseService;
import com.nsu.service.organization.IOrgModifyMatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service("iOrgModifyMatchService")
public class OrgModifyMatchServiceImpl extends BaseService implements IOrgModifyMatchService {

	@Resource
	private OrgModifyMatchDao orgModifyMatchDao;
	
	@Override
	public List<Map<String, Object>> getEffectiveMatchList(long o_id) throws Exception {
		return orgModifyMatchDao.getEffectiveMatchList(o_id);
	}

	@Override
	public int deleteMatch(int match_id,long o_id,
			int msg_type, String msg_title, String msg_text, int a_id) throws Exception {
		int i = orgModifyMatchDao.deleteMatch(match_id);
		if (i == 1) {
			orgModifyMatchDao.updateTime(match_id);
			orgModifyMatchDao.addMatchMsg(match_id, a_id, o_id, msg_type, msg_title, msg_text);
		}
		return i;
	}

	@Override
	public Map<String, Object> getMatchByMatchId(int match_id) throws Exception {
		return orgModifyMatchDao.getMatchByMatchId(match_id);
	}

	@Override
	public boolean modifyMatchInfo(int match_id, long o_id, Map<String, String> match_info, int msg_type,
		String msg_title, String msg_text, int a_id) throws Exception {
		boolean mask = false;
		int modifyMatchInfoResult = orgModifyMatchDao.modifyMatchInfo(match_id, match_info);
		if(modifyMatchInfoResult == 1){
			int addMatchMsgResult = orgModifyMatchDao.addMatchMsg(match_id, a_id, o_id, msg_type, msg_title,msg_text);
			if(addMatchMsgResult == 1){
				mask = true;
			}
		}
		return mask;
	}
	@Override
	public List<Map<String, Object>> selectByMatchName(String match_name,long o_id) {
		return orgModifyMatchDao.selectByMatchName(match_name,o_id);
	}

	@Override
	public boolean isDuplicationMatchName(long o_id, String match_name, int com_id) throws Exception {
		boolean mask = false;
		int result = orgModifyMatchDao.isDuplicationMatchName(o_id, match_name, com_id);
		if(result == 0){
			mask = true;
		}
		return mask;
	}

	@Override
	public boolean isChangeMatch(Map<String, String> match_info, int com_id) throws Exception {
		boolean mask = false;
		int result = orgModifyMatchDao.isChangeMatch(match_info, com_id);
		if(result == 0){
			mask = true;
		}
		return mask;
	}

	@Override
	public PageInfo<Map<String, Object>> getPageEffectiveMatchList(Integer pageNum, long o_id) throws Exception {
		//定义每页条数
		int pageSize = 8;
		PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = orgModifyMatchDao.getEffectiveMatchList(o_id);
		//已删除赛事存放
//		List<Map<String, Object>> delList = new ArrayList<>();
//		//已结束赛事存放
//		List<Map<String, Object>> overList = new ArrayList<>();
//		for (Map<String, Object> map : list) {
//			if (Integer.parseInt(map.get("status").toString()) == 3) {
//				delList.add(map);
//			}
//			if (Integer.parseInt(map.get("status").toString()) == 2) {
//				overList.add(map);
//			}
//		}
//		
//		list.removeAll(overList);
//		list.removeAll(delList);
//
//		list.addAll(overList);
//		list.addAll(delList);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);

        return pageInfo;
	}

	@Override
	public int getScheduleCount(String match_id) throws Exception {
		return orgModifyMatchDao.getScheduleCount(match_id);
	}

}
