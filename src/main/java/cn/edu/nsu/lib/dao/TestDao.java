package cn.edu.nsu.lib.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 20:26
 * @Descorption 这是需要自己实现dao层的类
 */
@Repository
public class TestDao extends BaseDao {
    public Map<String, Object> sel(String a) throws Exception {
        //也开始使用JdbcTemplate，但是这个在防止sql注入方面有待商榷
//        jdbc.update("sql语句");
        // 2017/9/17 namespace+id，找到对应的sql语句，使用重载方法注入参数
        // TODO: 2017/9/17 未测试，
        return sqlSession.selectOne("cn.edu.nsu.lib.dao.TestIntDao.sel",a);
    }
}
