/**
 * @Title: MessageDO.java 
 * @Package com.nsu.bean.message  
 * @Description: 消息bean
 * @author 朱明民 
 * @date 2017年4月17日 上午10:58:06
 * @version V1.0 
 */
package com.nsu.bean.message;

/** 
 * @ClassName: MessageDO   
 * @Description: 消息bean
 * @date 2017年4月17日 上午10:58:06   
 * @author 朱明民  
 *   
 */
public class MessageBean {
	private String SM_ID;//消息主键
	private String SM_TITLE;//消息标题
	private String SM_TEXT;//消息内容
	private String SM_DATE;//消息时间
	private String SM_TYPE;//消息状态
	private String A_ID;//账户ID
	private String UM_ID;//
	public String getSM_TITLE() {
		return SM_TITLE;
	}
	public void setSM_TITLE(String sM_TITLE) {
		SM_TITLE = sM_TITLE;
	}
	public String getSM_TEXT() {
		return SM_TEXT;
	}
	public void setSM_TEXT(String sM_TEXT) {
		SM_TEXT = sM_TEXT;
	}
	public String getSM_DATE() {
		return SM_DATE;
	}
	public void setSM_DATE(String sM_DATE) {
		SM_DATE = sM_DATE;
	}
	public String getA_ID() {
		return A_ID;
	}
	public void setA_ID(String a_ID) {
		A_ID = a_ID;
	}
	public String getSM_ID() {
		return SM_ID;
	}
	public void setSM_ID(String sM_ID) {
		SM_ID = sM_ID;
	}
	

	@Override
	public String toString() {
		return "MessageBean [SM_ID=" + SM_ID + ", SM_TITLE=" + SM_TITLE + ", SM_TEXT=" + SM_TEXT
				+ ", SM_DATE=" + SM_DATE + ", SM_TYPE=" + SM_TYPE + ", A_ID=" + A_ID +",UM_ID="+UM_ID+ "]";
	}
	public String getSM_TYPE() {
		return SM_TYPE;
	}
	public void setSM_TYPE(String sM_TYPE) {
		SM_TYPE = sM_TYPE;
	}
	public String getUM_ID() {
		return UM_ID;
	}
	public void setUM_ID(String uM_ID) {
		UM_ID = uM_ID;
	}
	
}
