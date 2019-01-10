package com.nsu.utils.student.exame;

import com.nsu.bean.student.exam.QuestionsBean;
import org.codehaus.jackson.type.TypeReference;
import com.nsu.entity.ExamRecord;
import com.nsu.entity.Problem;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * 考试记录操作工具类
 * 此工具不会与数据库交互更新
 * 主要功能是操作考试json数据中的题和答案
 *
 * @author XueLong
 * @version V1.0
 * @ClassName: ExamRecordHelper
 * @Package com.nsu.utils.student.exame
 * @Description: 考试记录操作工具类
 * @date 2017/8/6 14:38
 */
public class ExamRecordHelper {

    //考试记录实体对象
    private ExamRecord examRecord;

    //缓存问题实体对象集合
    private ArrayList<QuestionsBean> questionList;

    /**
     * 构造函数
     *
     * @param examRecord 考试记录实体对象
     * @Title: ExamRecordHelper的构造函数
     * @Description: 构造函数,初始化数据项
     * @author XueLong
     * @date 2017 -08-06 16:16:12
     */
    public ExamRecordHelper(ExamRecord examRecord) throws IOException {
        this.examRecord = examRecord;
        init();
    }

    /**
     * 重置方法,使用考试记录重新初始化
     *
     * @param examRecord 考试记录实体对象
     * @throws IOException Json处理中的IO异常
     * @Title: reSet
     * @Description: 重置考试考试记录
     * @author XueLong
     * @date 2017 -08-07 22:23:11
     */
    public void reSet(ExamRecord examRecord) throws IOException {
        this.examRecord = examRecord;
        init();
    }

    /**
     * 初始化方法
     *
     * @throws IOException Json处理中的IO异常
     * @Title: init
     * @Description: 初始化方法, 初始化数据项
     * @author qingyi xuelongqy@foxmail.com
     * @date 2017 -08-06 16:22:30
     */
    private void init() throws IOException {
        //初始化缓存集合
        questionList = new ArrayList<>();
        //生成问题对象集合
        makeQuestionList();
    }

    /**
     * 通过数据库中考生作答情况生成问题对象集合
     *
     * @throws IOException Json处理过程中的IO异常
     * @Title: makeQuestionList
     * @Description: 生成问题对象集合
     * @author XueLong
     * @date 2017 -08-07 22:17:40
     */
    private void makeQuestionList()  throws IOException{
        //生成考试作答答案集合
        LinkedHashMap<Long,ArrayList<String>> answerMap = jsonToAnswerMapAuto(examRecord.getStuAnswer());
        answerMap.forEach((pId,answer)->{
            //将考试作答记录保存到前端显示的问题实体对象中
            QuestionsBean questionsBean = new QuestionsBean();
            questionsBean.setpId(pId);
            questionsBean.setAnswer(answer);
            questionList.add(questionsBean);
        });
    }

    /**
     * 通过下标获取到对应的问题实体对象
     *
     * @param index 下标(一般对应第几题减一)
     * @return 问题实体对象
     * @Title: getQuestion
     * @Description: 获取实体对象
     * @author XueLong
     * @date 2017 -08-07 22:28:32
     */
    public QuestionsBean getQuestion(int index) throws Exception {
        if (index < 0 || index > questionList.size()){
            throw new Exception("未能获取指定序列号的题目!");
        }
        return questionList.get(index);
    }


    /**
     * 设置问题答案
     * 记录考试的作答答案
     *
     * @param index  问题的下标
     * @param answer 作答的答案(ArrayList类型)
     * @throws IOException Json处理过程中的IO异常
     * @Title: setAnswer方法
     * @Description: 设置问题答案
     * @author XueLong
     * @date 2017 -08-08 00:01:53
     */
    public void setAnswer(int index,ArrayList<String> answer) throws IOException {
        //通过下标获取问题
        QuestionsBean question = questionList.get(index);
        //设置答案
        question.setAnswer(answer);
        //更新作答答案
        updateAnswersJson();
    }

    /**
     * 设置问题答案
     * 记录考试的作答答案
     *
     * @param index  问题的下标
     * @param answerJson 作答的答案(json类型)
     * @throws IOException Json处理过程中的IO异常
     * @Title: setAnswer方法
     * @Description: 设置问题答案
     * @author XueLong
     * @date 2017 -08-08 00:01:55
     */
    public void setAnswer(int index,String answerJson) throws IOException {
        //将Json格式的字符串转换为ArrayList类型
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> answer = objectMapper.readValue(answerJson, ArrayList.class);
        //设置答案
        setAnswer(index,answer);
    }

    /**
     * 更新作答答案
     * 将问题实体对象集合中的数据转化为数据库存储的json数据
     *
     * @return 更新后的json格式作答答案
     * @throws IOException Json处理过程中的IO异常
     * @Title: updateAnswersJson
     * @Description: 更新作答答案
     * @author XueLong
     * @date 2017 -08-07 23:55:00
     */
    public String updateAnswersJson() throws IOException {
        //转换为json的Map格式
        LinkedHashMap<Long,ArrayList<String>> answerMap = new LinkedHashMap<>();
        //遍历问题实体对象
        for (QuestionsBean question : questionList){
            answerMap.put(question.getpId(),question.getAnswer());
        }
        //将Map转化为json格式
        String answerJson = mapToAnswerJsonAuto(answerMap);
        //将考试记录中的作答情况更新
        examRecord.setStuAnswer(answerJson);
        return answerJson;
    }

    /**
     * 获取考试出题规则Map
     * 键为难度,值为数量
     *
     * @param numOfQues 数据库的Json格式出题规则,包括(难度和数量)
     * @return 出题规则Map
     * @throws IOException Json转换过程中的IO异常
     * @Title: makeRuleOfQues
     * @Description: 获取考试出题规则Map
     * @author XueLong
     * @date 2017 -08-10 16:06:45
     */
    public static LinkedHashMap<Integer,Integer> makeRuleOfQues(String numOfQues) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(numOfQues, new TypeReference<LinkedHashMap<Integer,Integer>>(){});
    }

    /**
     * 将学生考试的题目和空答案转成json格式
     *
     * @param queList 考题列表
     * @return string 转换成json格式的考题和答案
     * @throws IOException Json处理过程中的IO异常
     * @Title: makeAnswersJson
     * @Description: 将学生考试的题目和空答案转成json格式
     * @author XueLong
     * @date 2017 -07-18 17:00:38
     */
    public static String makeAnswersJson(List<Problem> queList) throws IOException {
        //转换为json的Map格式
        LinkedHashMap<Long,ArrayList<String>> answerMap = new LinkedHashMap<>();
        //遍历考题
        for(Problem p:queList){
            answerMap.put(p.getpId(),new ArrayList<>());
        }
        //将Map转化为json格式
        return mapToAnswerJsonAuto(answerMap);
    }

    /**
     * 将数据库获取的json格式考试作答答案转换为集合类型
     * 调用Jackson自带的Api处理
     *
     * @param answerJson json格式的作答答案
     * @return 集合类型的作答答案
     * @throws IOException Json处理过程中的IO异常
     * @Title: jsonToAnswerMapAuto
     * @Description: 将数据库获取的json格式考试作答答案转换为集合类型
     * @author XueLong
     * @date 2017 -08-07 18:02:48
     */
    public static LinkedHashMap<Long,ArrayList<String>> jsonToAnswerMapAuto(String answerJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(answerJson, new TypeReference<LinkedHashMap<Long,ArrayList<String>>>(){});
    }

    /**
     * 将Map的作答答案转换为json类型
     * 调用Jackson自带的Api处理
     *
     * @param answerMap Map类型的作答答案
     * @return the string
     * @throws IOException Json处理过程中的IO异常
     * @Title: mapToAnswerJsonAuto
     * @Description: 将Map的作答答案转换为json类型
     * @author XueLong
     * @date 2017 -08-08 00:48:15
     */
    public static String mapToAnswerJsonAuto(LinkedHashMap<Long,ArrayList<String>> answerMap) throws IOException {
        //将Map转化为json格式
        ObjectMapper objectMapper = new ObjectMapper();
        String answerJson = objectMapper.writeValueAsString(answerMap);
        return answerJson;
    }

    /**
     * 将Json格式的问题选项转换为Map类型
     * 调用Jackson自带的Api处理
     *
     * @param selectsJson the selects json
     * @return Map类型的问题选项
     * @throws IOException Json处理过程中的IO异常
     * @Title: jsonToSelectsMapAuto
     * @Description: 将Json格式的问题选项转换为Map类型
     * @author XueLong
     * @date 2017 -08-08 01:11:35
     */
    public static LinkedHashMap<String,String> jsonToSelectsMapAuto(String selectsJson) throws IOException {
        //转换为json的Map格式
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(selectsJson);
        LinkedHashMap<String,String> selectsMap = objectMapper.readValue(selectsJson,new TypeReference<LinkedHashMap<String,String>>(){});
        return selectsMap;
    }

    public ExamRecord getExamRecord() {
        return examRecord;
    }

    public ArrayList<QuestionsBean> getQuestionList() {
        return questionList;
    }
}
