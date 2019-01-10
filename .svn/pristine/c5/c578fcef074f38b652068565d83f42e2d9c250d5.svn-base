package com.nsu.controller.student.compute;

import com.nsu.bean.common.AjaxBean;
import com.nsu.bean.student.compute.OtherScoreBean;
import com.nsu.bean.student.compute.ProblemBean;
import com.nsu.common.Anonymous;
import com.nsu.common.annotation.InterceptorAnno;
import com.nsu.service.student.compute.ComputeService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * Score view controller类的描述
 *
 * @author Yjh
 * @Description: 返回关于成绩查看的相关信息
 * @version: V1.0
 * @date 2017 -07-18 17:26:46
 */
@Controller
@RequestMapping("student/compute")
public class ComputeViewController implements Anonymous {
    @Resource
    ComputeService computeService;
    @InterceptorAnno(isAjax = true)
    @RequestMapping("view")
    @ResponseBody
    public AjaxBean ScoreView(){
        AjaxBean ajaxBean = new AjaxBean();
        try{
            ajaxBean.setSuccess(true);
            ajaxBean.setStatus("200");
            ajaxBean.put("view",computeService.getScoreBean(3));
            return ajaxBean;
        }
        catch (Exception e){
            ajaxBean.setSuccess(false);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("查看成绩失败");
            return ajaxBean;
        }
    }

    @InterceptorAnno(isAjax = true)
    @RequestMapping("compute")
    @ResponseBody
    public AjaxBean ScoreCompute(HttpSession session){
        AjaxBean ajaxBean = new AjaxBean();
        try{

            ajaxBean.setSuccess(true);
            ajaxBean.setStatus("200");
            ajaxBean.put("Record",getRecord(1));
            return ajaxBean;
        }
        catch (Exception e){
            ajaxBean.setSuccess(false);
            ajaxBean.setStatus("500");
            ajaxBean.setMsg("提交失败，请稍后再试");
            return ajaxBean;
        }
    }

    /**
     *选择题的分值计算方法
     */
    private  int  getScore(int E_I_ID) throws IOException {
        //得到学生答案
        String student_answer = computeService.getStudentAnswer(E_I_ID);
        int score = 0;
        int count= 0;
        //将学生作答信息映射成json
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String,ArrayList<String>> m=mapper.readValue(student_answer,HashMap.class);
        //获取学生作答的所有对应题号，先得到所有key值
        Set key = m.keySet();
        List<Long> list = new ArrayList<Long>(key);
        //根据对应题号获取答案
        List<ProblemBean> answer = computeService.getProblemAnswer(list);
        //从第一题开始遍历答案并且对比，每道题临时存储在answerList这个ArrayList<String>里面
        ArrayList AnswerList;
        //循环比对，当所有答案对比正确才增加分数
        for(int i=0;i<answer.size();i++){
            AnswerList = mapper.readValue(answer.get(i).getAnswer(),ArrayList.class);
            if(m.get(answer.get(i).getProblemId().toString()).size()==AnswerList.size()){
                for(int j=0;j<AnswerList.size();j++){
                    for(int z=0;z<AnswerList.size();z++){
                        if(m.get(answer.get(i).getProblemId().toString()).get(j).equals(AnswerList.get(z))){
                            count++;
                            break;
                        }
                    }
                }
                if(count==AnswerList.size())
                    score+=answer.get(i).getValue();
            }
            count=0;
        }
        return score;
    }

    private double getRecord(int E_I_ID) throws IOException {
        int OtherScore=0;
        int score = getScore(E_I_ID);
        OtherScoreBean otherScoreBean = computeService.getOtherScore(E_I_ID);
        OtherScore = otherScoreBean.getOutActivity()+otherScoreBean.getPracticeActivity()+otherScoreBean.getStrongSkill();
        double record = (score+OtherScore)*0.25;
        otherScoreBean.setE_i_id(E_I_ID);
        otherScoreBean.setExam(score);
        otherScoreBean.setRecord(record);
        System.out.println(record);
        computeService.updateScore(otherScoreBean);
        return record;
    }
}
