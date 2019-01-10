package com.nsu.service.school.admin;

import java.util.List;
import java.util.Map;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolFeatureAdminService extends BaseService {
	//INSERT INTO 'school_feature' ('title', 'pic', 'content', 'content_text', 'type', 'school_url', 'oper_date', '_status') VALUES ('1', '1', '1', '1', '1', '1', '1', '1');
	private final static String ADD_FEATURE = "INSERT INTO school_feature (title, pic, content, content_text, type, school_url, oper_date, _status) VALUES (?, ?, ?, ?, ?, ?, now(), '1');";
	public boolean addFeature(String title,String content_text,String content,String pic,String type,String schoolUrl) throws Exception {
		return jt.update(ADD_FEATURE,new Object[]{title,pic,content,content_text,type,schoolUrl})==1;
	}
	
	private final static String GET_FEATURE_LIST = "select * from school_feature where school_url = ? and type = ? and _status = 1 order by oper_date desc limit ?,?";
	public List<Map<String, Object>> getPeopleList(String schoolUrl,String type,Map<String, Integer> page) throws Exception {
		return jt.queryForList(GET_FEATURE_LIST,new Object[]{schoolUrl,type,page.get("pageStart"),page.get("pageEnd")});
	}
	
	private final static String GET_FEATURE_TATOL = "select count(*) from school_feature where school_url = ? and type = ? and _status = 1";
	public long getPeopleTATOL(String schoolUrl,String type) throws Exception {
		return jt.queryForObject(GET_FEATURE_TATOL, Long.class,new Object[]{schoolUrl,type});
	}
	
	private final static String GET_FEATURE_MAP = "select * from school_feature where school_url = ? and sc_feature = ?";
	public Map<String, Object> getFeatureMap(String schoolUrl,String scId) throws Exception {
		return jt.queryForMap(GET_FEATURE_MAP,new Object[]{schoolUrl,scId});
	}
	
	private final static String UPDATE_FEATURE = "update school_feature set title =? , content_text = ? , content =? ,oper_date = now() where sc_feature = ? and school_url = ? and _status = 1";
	private final static String UPDATE_FEATURE_PIC = "update school_feature set title =? , pic = ? , content_text = ? , content =? ,oper_date = now() where sc_feature = ? and school_url = ? and _status = 1";
	public boolean updateFeature(String title,String content_text,String content,String pic,String schoolUrl,String scId) throws Exception {
		
		if (pic == null) {
			return jt.update(UPDATE_FEATURE,new Object[]{title, content_text ,content,scId,schoolUrl}) == 1;
		}else {
			return jt.update(UPDATE_FEATURE_PIC,new Object[]{title,pic,content_text,content,scId,schoolUrl}) == 1;
		}
	}
	
	private final static String DELETE_FEATURE = "update school_feature set _status = '0' where sc_feature = ? and school_url = ?";
	public boolean deleteFeature(String schoolUrl,String scId) throws Exception {
		return jt.update(DELETE_FEATURE,new Object[]{scId,schoolUrl}) == 1 ;
	}
	
}