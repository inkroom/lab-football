package cn.edu.nsu.lib.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/21
 * @Time 10:40
 * @Descorption springjdbc，sql注入防范有待商榷
 */
@Repository
public class TestJdbcDao extends BaseDao {
    public Map<String, Object> sel(String a) throws Exception {
        //参数用?代替
        SqlRowSet set = jdbc.queryForRowSet(getSql("select * from account WHERE nickname='?'", a));
        if (set.next()) {
            Map<String, Object> map = new HashMap<>();
            map.put("nickname", set.getObject("nickname"));
            map.put("password", set.getObject(4));//注意，下标从1开始
            return map;
        }
        return null;
    }
}
