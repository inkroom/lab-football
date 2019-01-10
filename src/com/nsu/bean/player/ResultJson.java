package com.nsu.bean.player;

/**
 * @ClassName ResultDto
 * @Description TODO(json返回dto)
 * @author hm
 * @Date 2017年4月13日 上午9:05:49
 * @version 1.0.0
 */
public class ResultJson {
	private String status;
	private String msg;
	private Object userData;
	private String number;
	
	public ResultJson(String status) {
		this.status = status;
	}
	public ResultJson(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	public ResultJson(String status, Object data) {
		super();
		this.status = status;
		this.setUserData(data);
	}
	public ResultJson(String status, String msg, Object userData) {
		this.status = status;
		this.msg = msg;
		this.setUserData(userData);
	}
	public ResultJson(String status, Object userData, String number) {
		this.status = status;
		this.setUserData(userData);
		this.setNumber(number);
	}
	public ResultJson() {
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the data
	 */
	public Object getUserData() {
		return userData;
	}
	public void setUserData(Object userData) {
		this.userData = userData;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
