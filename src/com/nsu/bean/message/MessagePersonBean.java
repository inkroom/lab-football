/**
 * @Title: MessagePersonBean.java 
 * @Package com.nsu.bean.message  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年5月19日 下午3:19:41
 * @version V1.0 
 */
package com.nsu.bean.message;

/** 
 * @ClassName: MessagePersonBean   
 * @Description: TODO<描述这个类的作用>  
 * <详细介绍>  
 * @date 2017年5月19日 下午3:19:41   
 * @author 朱明民  
 *   
 */
public class MessagePersonBean {
	private String PS_ID;//个人消息主键
	private String PS_TITLE;//个人消息标题
	private String PS_TEXT;//个人消息内容
	private String PS_DATE;//个人消息时间
	private String PS_SEND_ID;//发送人ID
	private String PS_RECEIVE;//接收人ID
	private String UM_ID;
	public String getPS_ID() {
		return PS_ID;
	}
	public void setPS_ID(String pS_ID) {
		PS_ID = pS_ID;
	}
	public String getPS_TITLE() {
		return PS_TITLE;
	}
	public void setPS_TITLE(String pS_TITLE) {
		PS_TITLE = pS_TITLE;
	}
	public String getPS_TEXT() {
		return PS_TEXT;
	}
	public void setPS_TEXT(String pS_TEXT) {
		PS_TEXT = pS_TEXT;
	}
	public String getPS_DATE() {
		return PS_DATE;
	}
	public void setPS_DATE(String pS_DATE) {
		PS_DATE = pS_DATE;
	}
	public String getPS_SEND_ID() {
		return PS_SEND_ID;
	}
	public void setPS_SEND_ID(String pS_SEND_ID) {
		PS_SEND_ID = pS_SEND_ID;
	}
	public String getPS_RECEIVE() {
		return PS_RECEIVE;
	}
	public void setPS_RECEIVE(String pS_RECEIVE) {
		PS_RECEIVE = pS_RECEIVE;
	}
	
	
	@Override
	public String toString() {
		return "MessagePersonBean [PS_ID=" + PS_ID + ", PS_TITLE=" + PS_TITLE + ", PS_TEXT=" + PS_TEXT
				+ ", PS_DATE=" + PS_DATE   + ", PS_SEND_ID=" + PS_SEND_ID + ",PS_RECEIVE="+PS_RECEIVE+",UM_ID="+UM_ID+"]";
	}
	public String getUM_ID() {
		return UM_ID;
	}
	public void setUM_ID(String uM_ID) {
		UM_ID = uM_ID;
	}


}
