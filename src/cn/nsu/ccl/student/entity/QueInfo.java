package cn.nsu.ccl.student.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 描述：当前考题信息
 * 类名：QueInfo
 * 创建时间：2016年8月29日
 * 作用和功能：当前考题信息
 * 使用说明：考生登录后，得到当前考试单个题目信息
 *
 * @author 蔡娅蕊
 */
public class QueInfo {

    private String queStem; // 考题题目

    private String queOption; // 题目选项

    private String queType; // 考试类型

    private String queKey; // 考生作答答案，仅限本题，多选题之间以@@分隔

    private int queId; // 考题Id号

    private LinkedHashMap<String, String> queOptions; // 分解后的题目选项

    private LinkedHashMap<String, String> queKeys; // 分解后的考生作答答案

    /**
     * 描述：切割从数据库传来的考题选项
     * 方法名：splitOptions
     * 返回值：LinkedHashMap<String,String> 考题选项集合
     * 开发人员：薛龙
     * 创建时间：2016年8月29日
     *
     * @param queOption 数据库传来的考题选项
     * @return queOptions
     * @author 薛龙
     */
    private LinkedHashMap<String, String> splitOptions(String queOption) {
        // 切割选项
        String[] options = queOption.split("##");

        // 生成乱序
        List<String> optionsList = Arrays.asList(options);
        Collections.shuffle(optionsList);

        // 初始化项
        char optionId = 'A';
        queOptions = new LinkedHashMap<String, String>();

        // 遍历选项，存入Map集合
        for (int i = 0; i < optionsList.size(); i++) {
            queOptions.put(optionId + "", optionsList.get(i));
            optionId++;
        }

        return queOptions;
    }

    public String getQueStem() {
        return queStem;
    }

    public void setQueStem(String queStem) {
        this.queStem = queStem;
    }


    public void setQueOption(String queOption) {
        if (queOption == null) {
            queOption = "";
        }
        this.queOption = queOption;
        queOptions = splitOptions(queOption); // 切割选项
    }

    public String getQueType() {
        return queType;
    }

    public void setQueType(String queType) {
        this.queType = queType;
    }

    public String getQueKey() {
        return queKey;
    }

    public void setQueKey(String queKey) {
        if (queKey == null) {
            queKey = "";
        }
        this.queKey = queKey;
    }

    public LinkedHashMap<String, String> getQueOptions() {
        return queOptions;
    }

    public void setQueOptions(LinkedHashMap<String, String> queOptions) {
        this.queOptions = queOptions;
    }

    public String getQueOption() {
        return queOption;
    }

    public int getQueId() {
        return queId;
    }

    public void setQueId(int queId) {
        this.queId = queId;
    }


    public LinkedHashMap<String, String> getQueKeys() {
        return queKeys;
    }

    public void setQueKeys(LinkedHashMap<String, String> queKeys) {
        this.queKeys = queKeys;
    }
}
