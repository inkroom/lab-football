/**
 * <p>Question.java文件的详细描述</p>
 * @Title: Question.java
 * @Package cn.nsu.ccl.teacher.entity
 * @Description: 每一道题， 备选答案为字符串数组
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午4:01:32
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>Question类的描述</p>
 * @ClassName: Question
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月19日 下午4:01:32
 */
public class ExamEntity {
	private int libraryId;//题库编号

	private String questionId;//问题编号

	private String questionContent;//题干

	private String choice;//备选答案，以##分隔

	private String answer;//正确答案，存放答案文本， 多选以##分隔

	private String type;//题目类型，约束为单选，多选，判断

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
