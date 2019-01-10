package com.nsu.service.school.admin;

import java.util.List;
import java.util.Map;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolNewsAdminService extends BaseService {
	//INSERT INTO 'school_feature' ('title', 'pic', 'content', 'content_text', 'type', 'school_url', 'oper_date', '_status') VALUES ('1', '1', '1', '1', '1', '1', '1', '1');
	private final static String ADD_NEWS = "INSERT INTO school_news (title, content, content_text, type, school_url, oper_date, _status) VALUES (?, ?, ?, ?, ?, now(), '1');";
	public boolean addNews(String title,String content_text,String content,String type,String schoolUrl) throws Exception {
		return jt.update(ADD_NEWS,new Object[]{title,content,content_text,type,schoolUrl})==1;
	}
	
	private final static String GET_NEWS_LIST = "select * from school_news where school_url = ? and type = ? and _status = 1 order by oper_date desc limit ?,?";
	public List<Map<String, Object>> getNewsList(String schoolUrl,String type,Map<String, Integer> page) throws Exception {
		return jt.queryForList(GET_NEWS_LIST,new Object[]{schoolUrl,type,page.get("pageStart"),page.get("pageEnd")});
	}
	
	private final static String GET_NEWS_TATOL = "select count(*) from school_news where school_url = ? and type = ? and _status = 1";
	public long getNewsTATOL(String schoolUrl,String type) throws Exception {
		return jt.queryForObject(GET_NEWS_TATOL, Long.class,new Object[]{schoolUrl,type});
	}
	
	private final static String GET_NEWS_MAP = "select * from school_news where school_url = ? and sc_news = ?";
	public Map<String, Object> getNewsMap(String schoolUrl,String scId) throws Exception {
		return jt.queryForMap(GET_NEWS_MAP,new Object[]{schoolUrl,scId});
	}
	
	private final static String UPDATE_NEWS = "update school_news set title =? , content_text = ? , content =? ,oper_date = now() where sc_news = ? and school_url = ? and _status = 1";
	public boolean updateNews(String title,String content_text,String content,String schoolUrl,String scId) throws Exception {
		return jt.update(UPDATE_NEWS,new Object[]{title, content_text ,content,scId,schoolUrl}) == 1;
	}
	
	private final static String DELETE_NEWS = "update school_news set _status = '0' where sc_news = ? and school_url = ?";
	public boolean deleteNews(String schoolUrl,String scId) throws Exception {
		return jt.update(DELETE_NEWS,new Object[]{scId,schoolUrl}) == 1 ;
	}
	
}