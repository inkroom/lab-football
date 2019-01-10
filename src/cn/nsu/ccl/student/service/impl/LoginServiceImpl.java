package cn.nsu.ccl.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nsu.ccl.student.dao.LoginDao;
import cn.nsu.ccl.student.service.LoginService;

/**
 * 考生端service实现类
 *
 * @author 墨盒
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao subjectDao;



    @Override
    public int login(String studentId, String studentName, String keyWord) {
        int examId = -1;
        try {
            examId = subjectDao.getExamId(studentId, studentName, keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examId;
    }

    @Override
    public boolean updateStatus(String studentID, int ExamId, int status) {
        try {
            return subjectDao.updateStatus(studentID, String.valueOf(ExamId), status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
