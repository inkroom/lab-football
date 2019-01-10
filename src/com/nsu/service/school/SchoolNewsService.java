package com.nsu.service.school;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolNewsService extends BaseService {
	
	private final static String GET_NEWS_LIST = "select * from school_news where school_url = ? and type = ? and _status = 1 order by oper_date desc limit ?,?";
	public List<Map<String, Object>> getNewsList(String schoolUrl,String type,Map<String, Integer> page,HttpServletRequest request) throws Exception {
		return jt.queryForList(GET_NEWS_LIST,new Object[]{schoolUrl,type,page.get("pageStart"),page.get("pageEnd")});
	}
	
	private final static String GET_NEWS_TATOL = "select count(*) from school_news where school_url = ? and type = ? and _status = 1";
	public long getNewsTATOL(String schoolUrl,String type) throws Exception {
		return jt.queryForObject(GET_NEWS_TATOL, Long.class,new Object[]{schoolUrl,type});
	}
	
	private final static String GET_NEWS_MAP = "select * from school_news where school_url = ? and type=? and sc_news = ?";
	public Map<String, Object> getNewMap(String schoolUrl,String typeNews,String scId,HttpServletRequest request)throws Exception{
		return jt.queryForMap(GET_NEWS_MAP,new Object[]{schoolUrl,typeNews,scId});
	}

	
}