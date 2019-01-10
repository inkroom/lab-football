package com.nsu.bean.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JacksonDemoBean {
	@JsonProperty("name")
	private String teamName;
	@JsonProperty("id")
	private String teamId;
	@JsonProperty("leader")
	private String teamLeader;
	@JsonProperty("time")
	private String createTime;
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "JacksonDemoBean{" +
				"teamName='" + teamName + '\'' +
				", teamId='" + teamId + '\'' +
				", teamLeader='" + teamLeader + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
