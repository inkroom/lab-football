package com.nsu.dao.student.compute;

import com.nsu.bean.student.compute.OtherScoreBean;
import com.nsu.bean.student.compute.ProblemBean;
import com.nsu.bean.student.compute.ComputeViewBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 闫建宏
 * @Description:
 * @date 2017/7/17
 */
public interface ComputeDao {
    public List<ProblemBean> getProblemAnswer(List<Long> p_idList);
    public String getStudentAnswer(long E_I_ID);
    public void updateScore(OtherScoreBean otherScoreBean);
    public OtherScoreBean getOtherScore(long E_I_ID);
    public List<ComputeViewBean> getScoreBean(long s_id);
}
