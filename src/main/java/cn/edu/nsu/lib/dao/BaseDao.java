package cn.edu.nsu.lib.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/17
 * @Time 20:27
 * @Descorption
 */
public class BaseDao {
    protected Log log = LogFactory.getLog(getClass());
    @Autowired
    protected JdbcTemplate jdbc;
    @Autowired
    protected SqlSession sqlSession;

    /**
     * 拼接sql语句
     *
     * @param sql   sql语句，参数用?代替
     * @param value 需要替换的参数
     * @return 拼接后的sql字符串
     */
    protected String getSql(String sql, String... value) throws Exception {
        log.info("参数长度为" + value.length);
        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sql.length(); i++) {
            if (sql.charAt(i) == '?') {
                try {
                    builder.append(value[index]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new Exception("?数量和参数不匹配");
                }
                index++;
            } else {
                builder.append(sql.charAt(i));
            }
        }
        return builder.toString();
    }
}
