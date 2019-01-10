package com.nsu.service.school;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nsu.service.BaseService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SchoolService extends BaseService {
    private final static String LIST_SIZE = "6";

    private final static String GET_SCHOOL_INFO_BY_URL = "select * from school where school_url = ? and _status = 1 ";

    public Map<String, Object> getSchoolBySchoolUrl(String schoolUrl) throws Exception {
        Map<String, Object> map = null;
        try {
            map = jt.queryForMap(GET_SCHOOL_INFO_BY_URL, schoolUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    private final static String GET_SCHOOL_INDEX_ROLL_URL = "select * from school_picture where type = '1' and school_url = ? and _status = 1 ";

    public List<Map<String, Object>> getSchoolIndexRollImage(String schoolUrl) throws Exception {
        return jt.queryForList(GET_SCHOOL_INDEX_ROLL_URL, schoolUrl);
    }

    private static final String GET_HEAD = "select save_path from school_picture where school_url = ? and type = 2 and _status = 1";

    public String getSchoolHeadPic(String schoolUrl) throws Exception {
        return jt.queryForObject(GET_HEAD, new Object[]{schoolUrl}, String.class);
    }


    private final static String GET_SCHOOL_PEOPLE_LIST = "select * from school_people where school_url = ? and type = ? and _status = 1 order by oper_date desc limit 0," + LIST_SIZE;

    public List<Map<String, Object>> getSchoolPeopleList(String schoolUrl, String type) throws Exception {
        return jt.queryForList(GET_SCHOOL_PEOPLE_LIST, new Object[]{schoolUrl, type});
    }


    private final static String GET_SCHOOL_FEATURE_LIST = "select * from school_feature where school_url = ? and type = ? and _status = 1 order by oper_date desc limit 0," + LIST_SIZE;

    public List<Map<String, Object>> getSchoolFeatureList(String schoolUrl, String type) throws Exception {
        return jt.queryForList(GET_SCHOOL_FEATURE_LIST, new Object[]{schoolUrl, type});
    }


    private final static String GET_SCHOOL_NEWS_LIST = "select * from school_news where school_url = ? and type = ? and _status = 1 order by oper_date desc limit 0," + LIST_SIZE;

    public List<Map<String, Object>> getSchoolNewsList(String schoolUrl, String type) throws Exception {
        return jt.queryForList(GET_SCHOOL_NEWS_LIST, new Object[]{schoolUrl, type});
    }

    private final static String GET_SCHOOL_FEATURE_MAP = "select * from school_feature where school_url = ? and type = ? and _status = 1 order by oper_date desc limit0," + LIST_SIZE;

    public Map<String, Object> getSchoolFeaturesList(String schoolUrl, String type) throws Exception {
        return jt.queryForMap(GET_SCHOOL_FEATURE_MAP, new Object[]{schoolUrl, type});
    }


}
