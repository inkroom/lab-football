package com.nsu.service.organization.impl;

import com.nsu.dao.organization.OrgCreateMatchDao;
import com.nsu.service.BaseService;
import com.nsu.service.organization.IOrgCreateMatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
@Service("iOrgCreateMatchService")
public class OrgCreateMatchServiceImpl extends BaseService implements IOrgCreateMatchService {
	@Resource
	private OrgCreateMatchDao orgCreateMatchDao;
	
	/**
	 * 如果dao层返回的值大于0，说明有重复的赛事名称，则返回false;
	 * 如果dao层返回的值等于0，说明没有重复的赛事名称，则返回true.
	 * @param o_id
	 * @param match_name
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isDuplicationMatchName(long o_id, String match_name) throws Exception {
		boolean mask = false;
		int result = orgCreateMatchDao.isDuplicationMatchName(o_id, match_name);
		if(result == 0){
			mask = true;
		}
		return mask;
	}

	@Override
	public boolean addMatchInfo(long o_id, Map<String, String> match_info, 
			int msg_type, String msg_title, String msg_text, int a_id) throws Exception {
		boolean mask = false;
		int addMatchInfoResult = orgCreateMatchDao.addMatchInfo(o_id, match_info);
		if(addMatchInfoResult == 1){
			int com_id = selectMatchID(o_id, match_info.get("match_name"));
			int addMatchMsgResult = orgCreateMatchDao.addMatchMsg(a_id, o_id, msg_type, 
					msg_title, msg_text, com_id);
			if(addMatchMsgResult == 1){
				mask = true;
			}
		}
		return mask;
	}

	@Override
	public int selectMatchID(long o_id, String match_name) throws Exception {
		return orgCreateMatchDao.selectMatchID(o_id, match_name);
	}

}
