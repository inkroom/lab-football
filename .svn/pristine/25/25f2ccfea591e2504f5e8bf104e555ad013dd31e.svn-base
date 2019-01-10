package com.nsu.service.student.score.impl;

import com.nsu.bean.student.score.SchoolBean;
import com.nsu.bean.student.score.ScoreBean;
import com.nsu.dao.student.score.ScoreDao;
import com.nsu.service.student.score.ScoreService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/14
 * @Time 10:27
 * @Descorption
 */
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public List<SchoolBean> getSchools() throws Exception {
        return scoreDao.getSchools();
    }

    @Override
    public List<ScoreBean> getScores(String name, Boolean isStudent, Boolean isBlur, String schoolName) throws Exception {
        if (name == null && schoolName == null && isBlur == null && isStudent == null) {
            throw new IllegalAccessException("参数不能全部为null");
        }
        if (name != null && isStudent == null && isBlur == null)
            throw new IllegalAccessException("内容不为null时");
        log.info(" name =  " + name + "   isStudent = " + isStudent + "   isBlur =  " + isBlur + "  schoolName = " + schoolName);
        List<ScoreBean> scores = scoreDao.getScores((schoolName == null && isBlur == Boolean.TRUE) ? "%" + name + "%" : name,
                isStudent == null ? false : isStudent,
                isBlur == null ? false : isBlur, schoolName);
        //处理排行，考虑并列
        long rank = 1;
        for (int i = 0; i < scores.size(); i++) {
            if (i == 0) {
                scores.get(i).setRank(rank);
            } else {
                if (scores.get(i - 1).getScore() != scores.get(i).getScore()) {
                    rank++;
                }
                scores.get(i).setRank(rank);
            }
        }
        return scores;
    }

    @Override
    public ScoreBean getMyScore(long studentId, List<ScoreBean> scores) throws Exception {
        for (ScoreBean score : scores) {
            if (score.getStudentId() == studentId)
                return score;
        }
        return null;
    }
}
