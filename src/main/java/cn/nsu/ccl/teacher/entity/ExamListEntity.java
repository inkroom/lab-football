/**
 * <p>QuestionListEntity.java文件的详细描述</p>
 * @Title: QuestionListEntity.java
 * @Package cn.nsu.ccl.teacher.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:16:07
 * @version V1.0
 */
package cn.nsu.ccl.teacher.entity;

/**
 * <p>QuestionListEntity类的描述</p>
 * @ClassName: QuestionListEntity
 * @Description: TODO(题库集合实体类)
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:16:07
 */
public class ExamListEntity {
	private int libraryId;					//题库编号
	private String libraryName;				//题库名字
	private String choiceNumber;			//单选题个数
	private String multiputeChoiceNumber;	//多选题个数
	private String judgeNumber;				//判断题个数
	
	public int getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public String getChoiceNumber() {
		return choiceNumber;
	}
	public void setChoiceNumber(String choiceNumber) {
		this.choiceNumber = choiceNumber;
	}
	public String getMultiputeChoiceNumber() {
		return multiputeChoiceNumber;
	}
	public void setMultiputeChoiceNumber(String multiputeChoiceNumber) {
		this.multiputeChoiceNumber = multiputeChoiceNumber;
	}
	public String getJudgeNumber() {
		return judgeNumber;
	}
	public void setJudgeNumber(String judgeNumber) {
		this.judgeNumber = judgeNumber;
	}
	
}
