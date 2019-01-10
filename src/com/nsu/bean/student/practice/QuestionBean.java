package com.nsu.bean.student.practice;

import com.nsu.common.mapper.JsonMapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @version 1.0
 * @auther 墨盒
 * @Date 2017/7/17
 * @Time 9:43
 * @Descorption
 */
public class QuestionBean implements Cloneable {
    private long id;
    private String content;//题干
    private int difficulty;
    private int type;
    private String choice;//选项
    private String point;
    private LinkedHashMap<String, String> select;//分解后的选项
    private String answer;//答案
    private int score;

    public QuestionBean() {
    }


    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public LinkedHashMap<String, String> getSelect() {
        return select;
    }

    public void setSelect(LinkedHashMap<String, String> select) {
        this.select = select;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QuestionBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", difficulty=" + difficulty +
                ", type=" + type +
                ", choice='" + choice + '\'' +
                ", point='" + point + '\'' +
                ", select=" + select +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
        this.select = JsonMapper.getInstance().fromJson(choice, LinkedHashMap.class);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public QuestionBean clone() throws CloneNotSupportedException {
        return (QuestionBean) super.clone();
    }
}
