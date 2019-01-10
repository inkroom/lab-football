package com.nsu.bean.team;

import java.util.List;
import java.util.Map;

/**
 * 球队手机端返回数据bean
 * @author ljl
 * @version 1.0
 * @createDate 2017-05-10 15:45:46
 */
public class TeamMobileResultBean {
	
	//赛事id
	private String comID;
	//赛事名字
	private String comName;
	//赛程信息
	private List<Map<String, Object>> raceDetaileslsit;
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public List<Map<String, Object>> getRaceDetaileslsit() {
		return raceDetaileslsit;
	}
	public void setRaceDetaileslsit(List<Map<String, Object>> raceDetaileslsit) {
		this.raceDetaileslsit = raceDetaileslsit;
	}
	
}
