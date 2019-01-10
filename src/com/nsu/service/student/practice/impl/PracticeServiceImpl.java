package com.nsu.service.student.practice.impl;

import com.nsu.bean.student.practice.PracticeSearchBean;
import com.nsu.bean.student.practice.QuestionBean;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.dao.student.practice.PracticeDao;
import com.nsu.service.student.practice.PracticeService;
import com.nsu.utils.jedis.JedisClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/7/17
 * @Time 9:52
 * @Descorption
 */
@Service
public class PracticeServiceImpl implements PracticeService {
    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    private JedisClient client;


    @Autowired
    private PracticeDao practiceDao;
    @Override
    public List<QuestionBean> getAllQuestion(PracticeSearchBean search) throws Exception {
//        String client_search = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_SEARCH_KEY);
//        String client_list = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_LIST_KEY);
        String client_search = client.get(EXERCISE_QUESTION_SEARCH_KEY);
        String client_list = client.get(EXERCISE_QUESTION_LIST_KEY);
        log.info("  搜索条件=  " + search);
        log.info(" context 中的题目=    " + client_list);
        JsonMapper mapper = JsonMapper.getInstance();
        List<QuestionBean> list;
        if (!"[]".equals(client_search) && !"[]".equals(client_list)) {//缓存不为空
            PracticeSearchBean searchBean = mapper.fromJson(client_search, PracticeSearchBean.class);
            if (search.equals(searchBean)) {//搜索条件不变，从缓存里取数据
                list = mapper.fromJson(client_list, mapper.createCollectionType(ArrayList.class, QuestionBean.class));
                log.info("来自缓存的数据    " + list);
                return list;
            }
        }
        //缓存为空，或者搜索条件改变，从数据库重新获取数据
        list = practiceDao.getAllQuestion(Integer.parseInt(search.getType())
                , Integer.parseInt(search.getDifficulty()), Integer.parseInt(search.getSubject()));
        log.info("来自数据库的数据    " + list);
        if (list != null) {
            for (int i = list.size() - 1; i >= 0; i--) {
                QuestionBean ques = list.get(i);
                if (!checkPoint(ques.getPoint(), search.getPoint())) {
                    list.remove(i);
                }
            }
        }
        log.info("处理考点后的list = " + list);
        log.info("*** search json *** " + mapper.toJson(search));
//        request.getServletContext().setAttribute(EXERCISE_QUESTION_SEARCH_KEY, mapper.toJson(search));
        client.set(EXERCISE_QUESTION_SEARCH_KEY, EXERCISE_SECENDS, mapper.toJson(search));
        log.info("*** list json **" + mapper.toJson(list));
//        request.getServletContext().setAttribute(EXERCISE_QUESTION_LIST_KEY, mapper.toJson(list));
        client.set(EXERCISE_QUESTION_LIST_KEY, EXERCISE_SECENDS, mapper.toJson(list));
        return list;
    }

//    public List<QuestionBean> getAllQuestion(PracticeSearchBean search, HttpServletRequest request) throws Exception {
//        String client_search = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_SEARCH_KEY);
//        String client_list = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_LIST_KEY);
////        String client_search = client.get(EXERCISE_QUESTION_SEARCH_KEY);
////        String client_list = client.get(EXERCISE_QUESTION_LIST_KEY);
//        log.info("  搜索条件=  " + search);
//        log.info(" context 中的题目=    " + client_list);
//        JsonMapper mapper = JsonMapper.getInstance();
//        List<QuestionBean> list;
//        if (!"[]".equals(client_search) && !"[]".equals(client_list)) {//缓存不为空
//            PracticeSearchBean searchBean = mapper.fromJson(client_search, PracticeSearchBean.class);
//            if (search.equals(searchBean)) {//搜索条件不变，从缓存里取数据
//                list = mapper.fromJson(client_list, mapper.createCollectionType(ArrayList.class, QuestionBean.class));
//                log.info("来自缓存的数据    " + list);
//                return list;
//            }
//        }
//        //缓存为空，或者搜索条件改变，从数据库重新获取数据
//        list = practiceDao.getAllQuestion(Integer.parseInt(search.getType())
//                , Integer.parseInt(search.getDifficulty()), Integer.parseInt(search.getSubject()));
//        log.info("来自数据库的数据    " + list);
//        if (list != null) {
//            for (int i = list.size() - 1; i >= 0; i--) {
//                QuestionBean ques = list.get(i);
//                if (!checkPoint(ques.getPoint(), search.getPoint())) {
//                    list.remove(i);
//                }
//            }
//        }
//        log.info("处理考点后的list = " + list);
//        log.info("*** search json *** " + mapper.toJson(search));
//        request.getServletContext().setAttribute(EXERCISE_QUESTION_SEARCH_KEY, mapper.toJson(search));
////        client.set(EXERCISE_QUESTION_SEARCH_KEY, EXERCISE_SECENDS, mapper.toJson(search));
//        log.info("*** list json **" + mapper.toJson(list));
//        request.getServletContext().setAttribute(EXERCISE_QUESTION_LIST_KEY, mapper.toJson(list));
////        client.set(EXERCISE_QUESTION_LIST_KEY, EXERCISE_SECENDS, mapper.toJson(list));
//        return list;
//    }

    /**
     * @param data  数据库存储的考点，json数组
     * @param value 用户搜索的考点
     * @return 如果数据库存储有用户的考点，则返回true，否则返回false
     */
    private boolean checkPoint(String data, String value) {
        List<String> pointList = JsonMapper.getInstance().fromJson(data, ArrayList.class);
        if (pointList != null && pointList.size() > 0)
            for (String point : pointList) {
                if (point.equals(value))
                    return true;
            }
        return false;
    }

    @Override
    public QuestionBean getAnswer(long questionId) throws Exception {
//        String client_list = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_LIST_KEY);
        String client_list = client.get(EXERCISE_QUESTION_LIST_KEY);
        JsonMapper mapper = JsonMapper.getInstance();
        List<QuestionBean> list;
        QuestionBean question = null;
        if (client_list != null) {//缓存不为空
            list = mapper.fromJson(client_list, mapper.createCollectionType(ArrayList.class, QuestionBean.class));
            for (QuestionBean questionBean : list) {
                if (questionBean.getId() == questionId) {
                    question = questionBean;
                }
            }
        } else {//缓存为空，从数据库获取题目
            question = practiceDao.getOneQuestion(questionId);
        }
        return question;
    }

    @Override
    public Boolean checkAnswer(long questionId, String answer) throws Exception {
        QuestionBean question = getAnswer(questionId);
        if (question != null) {
            List<String> answers = JsonMapper.getInstance().fromJson(question.getAnswer(), ArrayList.class);
            List<String> sAnswer = JsonMapper.getInstance().fromJson(answer, ArrayList.class);
            if (answers.size() != sAnswer.size()) {
                return false;
            }
//            boolean result = false;
            int i = 0;
            for (i = 0; i < answers.size(); i++) {
                if (!answers.get(i).equals(sAnswer.get(i))) {
                    commit(questionId, question.getScore(), false);
                    return false;
                }
            }
            commit(questionId, question.getScore(), true);
            return true;
//            return question.getAnswer().equals(answer);
        } else {//题目不存在
            return null;
        }
    }

    @Override
    public boolean commit(long studentId, int score, boolean isRight) throws Exception {
        return practiceDao.commit(studentId, score, isRight) >= 1;
    }

    @Override
    public QuestionBean getOneQuestion(PracticeSearchBean search) throws Exception {
        List<QuestionBean> list = getAllQuestion(search);
        if (list.size() == 0) {
            return null;
        }
//        List<QuestionBean> newList = list.subList(0, list.size());
//        log.info("   初始  list  =   " + newList);
//        for (int i = newList.size() - 1; i >= 0; i--) {
//            QuestionBean q = newList.get(i);
//            if (!checkPoint(q.getPoint(), search.getPoint())) {
//                newList.remove(i);
//            }
//            List<String> dataPoints = JsonMapper.getInstance().fromJson(q.getPoint(), ArrayList.class);
//            log.info("  解析 考点  list  =   " + dataPoints);
//            if (dataPoints != null)
//                for (String point : dataPoints) {
//                    log.info("数据库里的 point之一 = " + point + "   搜索的point " + search.getPoint());
//                    if (!point.equals(search.getPoint())) {//考点不符合
//                        newList.remove(i);
//                    }
//                }
//        }
//        log.info("   处理完后 " + newList);
//        if (newList.size() == 0) {
//            return null;
//        }
        Random r = new Random();
        return sortChoice(list.get(r.nextInt(list.size())));
    }

    @Override
    public QuestionBean getOneQuestion(long id) throws Exception {
//        String client_search = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_SEARCH_KEY);
//        String client_list = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_LIST_KEY);
//        String client_search = client.get(EXERCISE_QUESTION_SEARCH_KEY);
        String client_list = client.get(EXERCISE_QUESTION_LIST_KEY);
        if ("[]".equals(client_list)) {//没有题目
            return practiceDao.getOneQuestion(id);
        }
        List<QuestionBean> list = JsonMapper.getInstance().fromJson(client_list,
                JsonMapper.getInstance().createCollectionType(ArrayList.class, QuestionBean.class));
        for (QuestionBean q :
                list) {
            if (q.getId() == id)
                return q;
        }
        return null;
    }

    @Override
    public String getAnswer(QuestionBean questionBean) throws Exception {
//        String client_search = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_SEARCH_KEY);
//        String client_list = (String) request.getServletContext().getAttribute(EXERCISE_QUESTION_LIST_KEY);
//        String client_search = client.get(EXERCISE_QUESTION_SEARCH_KEY);
        log.info("   之前的question        QuestionBean questionBean     " + questionBean);
        List<String> answers = JsonMapper.getInstance().fromJson(questionBean.getAnswer(), ArrayList.class);
        log.info("   答案list        List<String> answers     " + answers);
        LinkedHashMap<String, String> selects = questionBean.getSelect();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            builder.append(answers.get(i));
            if (i != answers.size() - 1)
                builder.append("、");
//            String answer = answers.get(i);
//            String temp;
//            if ((temp = selects.get(answer)) != null) {
//                builder.append(answer);
//                builder.append(".");
//                builder.append(temp);
//                builder.append("\n");
//            }
        }
        return builder.toString();
    }

    /**
     * 打乱选项部分
     *
     * @param question 题目
     */
    private QuestionBean sortChoice(QuestionBean question) {
//        if (question.getChoice() != null) {
//            JsonMapper mapper = JsonMapper.getInstance();
//            ArrayList map = convert(question);
//            if (map != null) {
//                Collections.shuffle(map);
//                question.setChoice(mapper.toJson(map));
//            }
//        }
        return question;
    }

    private ArrayList convert(QuestionBean question) {
        try {
            return JsonMapper.getInstance().readValue(question.getChoice(), ArrayList.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
//    @Test
//    public void test() {
//        String choice = "[{\"A\":\"3423中文\"},{\"B\":\",./;'\"},{\"C\":\",./;'\"},{\"D\":\",./;'\"}]";
//        JsonMapper mapper = JsonMapper.getInstance();
//        com.fasterxml.jackson.databind.JavaType t = mapper.createCollectionType(HashMap.class, String.class, String.class);
//        ArrayList map = null;
//        try {
//            map = mapper.readValue(choice, ArrayList.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(((HashMap) map.get(0)));
//        Collections.shuffle(map);
//        System.out.println(mapper.toJson(map));
//    }

}
