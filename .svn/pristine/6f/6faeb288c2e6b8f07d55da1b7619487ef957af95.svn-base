package com.nsu.bean.student.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * 前端页面所需的问题数据实体类
 *
 * @author XueLong
 * @version V1.0
 * @ClassName: QuestionsBean
 * @Package com.nsu.bean.student.exam
 * @Description: 前端页面所需的问题数据实体类
 * @date 2017/8/7 17:01
 */
public class QuestionsBean {
    //问题编号
    private Long pId;
    //题型1.单选2.多选3.连线题4.判断题
    private Integer type;
    //难易程度1. 简单2.中等3. 难
    private Integer level;
    //所属科目
    private Integer subject;
    //问题题干
    private String question;
    //问题选项
    private LinkedHashMap<String,String> selects;
    //作答答案
    private ArrayList<String> answer;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LinkedHashMap<String, String> getSelects() {
        //随机排序
        ArrayList<String> keys = new ArrayList<>();
        keys.addAll(this.selects.keySet());
        Collections.shuffle(keys);
        LinkedHashMap<String,String> newSelects = new LinkedHashMap<>();
        keys.forEach(key -> {
            newSelects.put(key,this.selects.get(key));
        });
        this.selects = newSelects;
        return selects;
    }

    public void setSelects(LinkedHashMap<String, String> selects) {
        this.selects = selects;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
