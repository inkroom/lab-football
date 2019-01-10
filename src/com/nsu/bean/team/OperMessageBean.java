package com.nsu.bean.team;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送的操作信息pojo
 * @author ljl
 * @version 1.0
 * @createDate 2017-06-01 17:27:18
 */
public class OperMessageBean {

	private String senderID;//发送者id
	private String receivederID;//接收者id
	private String title;//消息标题
	private String text;//消息内容
	private int senderType;//发送者类型
	private int receivederType;//接收者类型
	private boolean senderIDIsAid;//发送者id是否是A_ID
	private boolean receivederIDIsAid;//接收者类型id是否是A_ID
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public OperMessageBean(){
		senderType = -1;
		receivederType = -1;
		
		senderIDIsAid = true;
		receivederIDIsAid = true;
	}
	public OperMessageBean(String senderID, String receivederID, String title, String text){
		senderType = -1;
		receivederType = -1;
		
		senderIDIsAid = true;
		receivederIDIsAid = true;
		
		this.senderID = senderID;
		this.receivederID = receivederID;
		this.title = title;
		this.text = text;
	}
	public OperMessageBean(String senderID, String receivederID, String title, String text
			, int senderType, int receivederType, boolean senderIDIsAid, boolean receivederIDIsAid){
		this.senderType = senderType;
		this.receivederType = receivederType;
		
		this.senderIDIsAid = senderIDIsAid;
		this.receivederIDIsAid = receivederIDIsAid;
		
		this.senderID = senderID;
		this.receivederID = receivederID;
		
		this.title = title;
		this.text = text;
	}
	
	public Map<String, Object> getMap() {
		map.put("PS_SEND_ID", senderID);
		map.put("PS_RECEIVE", receivederID);
		map.put("PS_TITLE", title);
		map.put("PS_TEXT", text);
		return map;
	}
	public int getSenderType() {
		return senderType;
	}
	public void setSenderType(int senderType) {
		this.senderType = senderType;
	}
	public int getReceivederType() {
		return receivederType;
	}
	public void setReceivederType(int receivederType) {
		this.receivederType = receivederType;
	}
	public boolean isSenderIDIsAid() {
		return senderIDIsAid;
	}

	public void setSenderIDIsAid(boolean senderIDIsAid) {
		this.senderIDIsAid = senderIDIsAid;
	}

	public boolean isReceivederIDIsAid() {
		return receivederIDIsAid;
	}

	public void setReceivederIDIsAid(boolean receivederIDIsAid) {
		this.receivederIDIsAid = receivederIDIsAid;
	}

	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getReceivederID() {
		return receivederID;
	}
	public void setReceivederID(String receivederID) {
		this.receivederID = receivederID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
