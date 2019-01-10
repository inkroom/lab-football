/**
 * @Title: MessageReadBean.java 
 * @Package com.nsu.bean.message  
 * @Description: TODO(描述文件用途) 
 * @author 朱明民 
 * @date 2017年5月22日 下午2:59:55
 * @version V1.0 
 */
package com.nsu.bean.message;

/** 
 * @ClassName: MessageReadBean   
 * @Description: TODO<描述这个类的作用>  
 * <详细介绍>  
 * @date 2017年5月22日 下午2:59:55   
 * @author 朱明民  
 *   
 */
public class MessageReadBean {
	private String ID;//已读消息主键
	private String TITLE;//消息标题
	private String TEXT;//消息内容
	private String DATE;//消息时间
	private String UM_TYPE;//消息类型
	
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getUM_TYPE() {
		return UM_TYPE;
	}
	public void setUM_TYPE(String uM_TYPE) {
		UM_TYPE = uM_TYPE;
	}
	
	@Override
	public String toString() {
		return "MessageReadBean [ID=" + ID + ", TITLE=" + TITLE + ", TEXT=" + TEXT
				+ ", DATE=" + DATE + ", UM_TYPE=" + UM_TYPE +  "]";
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	

}
