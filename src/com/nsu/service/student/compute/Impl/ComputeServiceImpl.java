package com.nsu.service.student.compute.Impl;

import com.nsu.bean.student.compute.OtherScoreBean;
import com.nsu.bean.student.compute.ProblemBean;
import com.nsu.bean.student.compute.ComputeViewBean;
import com.nsu.dao.student.compute.ComputeDao;
import com.nsu.service.student.compute.ComputeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫建宏
 * @Description:
 * @date 2017/7/17
 */
@Service
public class ComputeServiceImpl implements ComputeService {
    @Resource
    ComputeDao computeDao;
    @Override

    public List<ProblemBean> getProblemAnswer(List<Long> P_idList) {
        return computeDao.getProblemAnswer(P_idList);
    }

    @Override
    public void updateScore(OtherScoreBean otherScoreBean) {
        computeDao.updateScore(otherScoreBean);
    }

    @Override
    public String getStudentAnswer(long E_I_ID) {
        return computeDao.getStudentAnswer(E_I_ID);
    }

    @Override
    public OtherScoreBean getOtherScore(long E_I_ID) {

        return computeDao.getOtherScore(E_I_ID);
    }

    @Override
    public List<ComputeViewBean> getScoreBean(long s_id) {
        return computeDao.getScoreBean(s_id);
    }

}
