package cn.nsu.ccl.student.dao.impl;

import java.util.ArrayList;

import cn.nsu.ccl.student.dao.LoginDao;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import cn.nsu.ccl.comm.enviorment.ComEnviorment;
import cn.nsu.ccl.student.entity.ExamInfo;


/**
 * <p>SubjectDaoImpl类的描述</p>
 *
 * @author 欧磊
 * @ClassName: SubjectDaoImpl
 * @Description: TODO(考生端dao层的实现类)
 * @date 2016年11月27日 下午8:48:37
 */


@Repository
public class LoginDaoImpl extends ComEnviorment implements LoginDao {



    @Override
    public int getExamId(String studentID, String studentName, String keyWord) throws Exception {
        //System.out.println(examineeID+","+examineeName+","+keyWord);
        int examId = -1;
        String sql = GET_SQL(new String[]{studentID, studentName, keyWord}, "CAll getExamId('?','?','?')");
        SqlRowSet rowSet = getJdbc().queryForRowSet(sql);
        if (rowSet.next()) {
            examId = rowSet.getInt("examId");
            return examId;
        }
        return examId;
    }

    @Override
    public boolean updateStatus(String studentID, String ExamId, int status) throws Exception {
        try {
            String sql = GET_SQL(new String[]{studentID, ExamId, status + ""},
                    "CAll UPDATE_STATUS('?','?',?)");
            int count = getJdbc().update(sql);
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
