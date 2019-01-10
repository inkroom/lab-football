package cn.nsu.ccl.student.enviorment;

import java.util.*;

import cn.nsu.ccl.student.entity.ExamInfo;
import cn.nsu.ccl.student.entity.ExamQueInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>描述：考试界面处理考题的类</p>
 * <p>类名：DealQues</p>
 * <p>创建时间：2016年8月27日</p>
 * <p>作用和功能：处理考题信息</p>
 * <p>使用说明：考生考试时候使用</p>
 *
 * @author 薛龙
 */
public class DealQues {
    //    public static final String SPLIT_ANSWAER = "##";
    public static final String SPLIT_ANSWAER_STUDENT = "@@";


    /**
     * <p>生成初始化考试的json数据</p>
     *
     * @param examQueInfo 考题信息
     * @param examInfo    考试信息
     * @return 初始化的json字符串
     * @Title: DealQues的azyExam方法
     * @Description: TODO(创建json数据ques部分)
     * @author 墨盒 fuqianqing@163.com
     * @date 2016年11月22日 下午8:13:24
     */
    public JSONObject azyExam(ExamQueInfo examQueInfo, ExamInfo examInfo) {
        JSONObject top = new JSONObject();
        JSONArray queArray = new JSONArray();
        Map<String, Integer> queNum = examQueInfo.getExamQueNum();//题目数量
        Map<String, ArrayList<Integer>> ques = examQueInfo.getExamQues();//题目ID
        System.out.println("azyExam queNum = " + queNum + "  ques = " + ques);
        Iterator<String> types = queNum.keySet().iterator();
        while (types.hasNext()) {
            JSONObject queObj = new JSONObject();
            String type = types.next();
            queObj.put("type", type);
            queObj.put("num", queNum.get(type));

            ArrayList<Integer> ids = ques.get(type);

            if (ids != null) {//确保存在该题型
                JSONArray summaries = new JSONArray();
                for (Integer id : ids) {
                    JSONObject object = new JSONObject();
                    object.put("id", id);
                    summaries.add(object);
                }
                queObj.put("summaries", summaries);
                queArray.add(queObj);
            }
        }
        top.put("ques", queArray);

        JSONObject examObj = new JSONObject();
        examObj.put("endTime", examInfo.getEndTime().getTime());
        examObj.put("title", examInfo.getExamName());
        examObj.put("nowTime", new Date().getTime());

        top.put("exam", examObj);
        return top;
    }

    /**
     * <p>解析前台传来的答案，解析成json数组</p>
     *
     * @param answer 答案
     * @return 解析后的json数组
     */
    public String azyAnswer(String answer) {
        if (answer==null||answer.equals("")){
            return "[]";
        }
        String answers[] = answer.split(SPLIT_ANSWAER_STUDENT);
        JSONArray array = new JSONArray();
        for (String item : answers) {
            array.add(item);
        }
        return array.toString();
    }
}
