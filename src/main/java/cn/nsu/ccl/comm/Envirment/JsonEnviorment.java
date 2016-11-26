package cn.nsu.ccl.comm.Envirment;

import java.util.ArrayList;

import cn.nsu.ccl.teacher.entity.QuestionEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonEnviorment {
	/**
	 * 功能：将题目集合解析成json字符串
	 * 创建时间：2016-9-1
	 * 开发者：墨盒
	 * @param questionList
	 * @return
	 */
	public String splitQuestion(ArrayList<QuestionEntity> questionList){
		
		JSONObject obj = new JSONObject();
		obj.put("status",1);
		JSONArray array = new JSONArray();
		for(int i=0;i<questionList.size();i++){
			QuestionEntity question = questionList.get(i);
			JSONObject job = new JSONObject();
			job.put("libraryId", question.getLibraryId());
			job.put("questionId", question.getQuestionId());
			job.put("questionContent", question.getQuestionContent());
			job.put("choice", question.getChoice());
			job.put("answer", question.getAnswer());
			job.put("type", question.getType());
			
			array.add(job);
		}
		obj.put("data", array);
		return obj.toString();
	}
	/**
	 * 功能：题目json数据解析成集合
	 * 创建时间：2016-9-1
	 * 开发者：墨盒
	 * @param questionList
	 * @return
	 */
	public ArrayList<QuestionEntity> analyzeQuestion(String questionJson) {
		ArrayList<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
		JSONObject job = JSONObject.fromObject(questionJson);
		JSONArray array = job.getJSONArray("data");
		for(int i=0;i<array.size();i++){
			QuestionEntity question = new QuestionEntity();
			JSONObject obj = array.getJSONObject(i);
			String temp = obj.getString("questionId");
			question.setQuestionId(delString(temp));
			
			temp = obj.getString("questionId");
			question.setQuestionId(delString(temp));
			
			temp = obj.getString("questionContent");
			question.setQuestionContent(delString(temp));
			
			temp = obj.getString("choice");
			question.setChoice(delString(temp));
			
			temp = obj.getString("answer");
			question.setAnswer(delString(temp));
			
			temp = obj.getString("type");
			question.setType(delString(temp));
			
			questionList.add(question);
		}
		return questionList;
	}
	private String delString(String temp){
		if(temp==null||temp.equals("")){
			return " ";
		}
		return temp;
	}
}
