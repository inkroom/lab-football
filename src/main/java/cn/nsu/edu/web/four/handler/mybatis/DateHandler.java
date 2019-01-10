package cn.nsu.edu.web.four.handler.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/10/23
 * @Time 18:19
 * @Descorption
 */
public class DateHandler extends BaseTypeHandler<Date> {
    public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        Timestamp timestamp = new Timestamp(parameter.getTime());
        ps.setTimestamp(i, timestamp, Calendar.getInstance());
    }
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, date.toString());
    }
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getTimestamp(s, Calendar.getInstance());
    }
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getTimestamp(i, Calendar.getInstance());
    }
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getTime(i, Calendar.getInstance());
    }
}
