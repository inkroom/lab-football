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
 * @Description: 题库列表实体类
 * @author: 蒋玖宏
 * @email:2213974854@qq.com
 * @date 2016年11月18日 下午3:16:07
 */
public class QuestionLibList {
	private int libraryId;					//题库编号
	private String libraryName;				//题库名字
	private String sChoice;					//单选题个数
	private String mChoice;					//多选题个数
	private String tofChoice;				//判断题个数
	private String tChoice;					//填空题个数
	private String jdChoice;				//简单解答题个数
	private String zdChoice;				//中等解答题个数
	private String knChoice;				//困难解答题个数
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
	public String getsChoice() {
		return sChoice;
	}
	public void setsChoice(String sChoice) {
		this.sChoice = sChoice;
	}
	public String getmChoice() {
		return mChoice;
	}
	public void setmChoice(String mChoice) {
		this.mChoice = mChoice;
	}
	public String getTofChoice() {
		return tofChoice;
	}
	public void setTofChoice(String tofChoice) {
		this.tofChoice = tofChoice;
	}
	public String getJdChoice() {
		return jdChoice;
	}
	public void setJdChoice(String jdChoice) {
		this.jdChoice = jdChoice;
	}
	public String getZdChoice() {
		return zdChoice;
	}
	public void setZdChoice(String zdChoice) {
		this.zdChoice = zdChoice;
	}
	public String getKnChoice() {
		return knChoice;
	}
	public void setKnChoice(String knChoice) {
		this.knChoice = knChoice;
	}
	public String gettChoice() {
		return tChoice;
	}
	public void settChoice(String tChoice) {
		this.tChoice = tChoice;
	}
	

	
}
