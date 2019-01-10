package com.nsu.bean.organization;

/**
 * 
* @ClassName: EVPIDao 
* @Description: 机构完善信息Dao 
* @author 严涛
* @date 2017年4月13日 上午9:47:46 
*
 */
public class EVPIDO {
	private long org_id;//机构id
	private String user_name;//用户姓名
	private String user_phone;//用户电话
	public long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(long org_id) {
		this.org_id = org_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	

}
