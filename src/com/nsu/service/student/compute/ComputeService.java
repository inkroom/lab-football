package com.nsu.service.student.compute;

import com.nsu.bean.student.compute.OtherScoreBean;
import com.nsu.bean.student.compute.ProblemBean;
import com.nsu.bean.student.compute.ComputeViewBean;

import java.util.List;

/**
 * @author 闫建宏
 * @Description:
 * @date 2017/7/17
 */
public interface ComputeService {
    public List<ProblemBean> getProblemAnswer(List<Long> P_idList);
    public void updateScore(OtherScoreBean otherScoreBean);
    public String getStudentAnswer(long E_I_ID);
    public OtherScoreBean getOtherScore(long E_I_ID);
    public List<ComputeViewBean> getScoreBean(long s_id);
}
