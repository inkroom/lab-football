package cn.nsu.ccl.student.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.nsu.ccl.student.enviorment.DealQues;

/**
 * 描述：考试题目信息
 * 类名：ExamQueInfo
 * 创建时间：2016年8月29日
 * 作用和功能：考试题目信息
 * 使用说明：考生登录后，获取考试题目信息总览
 *
 * @author 蔡娅蕊
 */
public class ExamQueInfo {

    private int examId; // 考试ID号

    private Map<String, Integer> examQueNum;//不同类型的考题数量，key为考题类型

    private Map<String, ArrayList<Integer>> examQues; // 所有考题题号，key为类型,value为题目ID


    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public void setExamQueNum(Map<String, Integer> examQueNum) {
        this.examQueNum = examQueNum;
    }

    public Map<String, ArrayList<Integer>> getExamQues() {
        return examQues;
    }

    public void setExamQues(Map<String, ArrayList<Integer>> examQues) {
        this.examQues = examQues;
    }

    public Map<String, Integer> getExamQueNum() {
        return examQueNum;
    }


//    /**
//     * <p>setExamQues方法的描述</p>
//     *
//     * @param randomId
//     * @param eachType
//     * @Title: ExamQueInfo的setExamQues方法
//     * @Description: TODO(解析每道题的题型，并将对应题型以##分隔存到对应key的map里)
//     * @author 墨盒 fuqianqing@163.com
//     * @date 2016年11月19日 下午1:52:36
//     */
//    public void setExamQues(String randomId, String eachType) {
//        StringBuilder[] builders = new StringBuilder[DealQues.TYPES.length];
//        for (int i = 0; i < builders.length; i++) {
//            builders[i] = new StringBuilder();
//        }
//
//        String ids[] = randomId.split(DealQues.SPLIT_ANSWAER);
//        String types[] = eachType.split(DealQues.SPLIT_ANSWAER);
//        for (int i = 0; i < types.length; i++) {
//            for (int j = 0; j < builders.length; j++) {
//                if (types[i].equals(DealQues.TYPES[j])) {
//                    builders[j].append(ids[i]);
//                    builders[j].append(i == types.length - 1 ? "" : DealQues.SPLIT_ANSWAER);
//                }
//            }
//        }
//        HashMap<String, String> map = new HashMap<>();
//        for (int i = 0; i < DealQues.TYPES.length; i++) {
//            map.put(DealQues.TYPES[i], builders[i].toString());
//        }
//        this.examQues = map;
//    }

//    /**
//     * <p>setQueKey方法的描述</p>
//     *
//     * @param queKey
//     * @param eachType
//     * @Title: ExamQueInfo的setQueKey方法
//     * @Description: TODO(将考生做的答案分类型存到对应的map里，题号一一对应)
//     * @author 墨盒 fuqianqing@163.com
//     * @date 2016年11月19日 下午2:24:56
//     */
//    public void setQueKey(String queKey, String eachType) {
//        StringBuilder[] builders = new StringBuilder[DealQues.TYPES.length];
//        for (int i = 0; i < builders.length; i++) {
//            builders[i] = new StringBuilder();
//        }
//
//        String queKeys[] = queKey.split(DealQues.SPLIT_CHOICE);
//        String types[] = eachType.split(DealQues.SPLIT_CHOICE);
//        for (int i = 0; i < types.length; i++) {
//            for (int j = 0; j < builders.length; j++) {
//                if (types[i].equals(DealQues.TYPES[j])) {
//                    builders[j].append(queKeys[i]);
//                    builders[j].append(i == types.length - 1 ? "" : DealQues.SPLIT_CHOICE);
//                }
//            }
//        }
//        HashMap<String, String> map = new HashMap<>();
//        for (int i = 0; i < DealQues.TYPES.length; i++) {
//            map.put(DealQues.TYPES[i], builders[i].toString());
//        }
////		this.queKey = queKey;
//        this.queKey = map;
//    }

}
