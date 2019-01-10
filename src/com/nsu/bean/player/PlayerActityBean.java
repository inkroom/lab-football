package com.nsu.bean.player;

public class PlayerActityBean {
	private String com_id;	/*赛事id*/
	private String com_name;	/*赛事名称*/
	private String com_end;	/*赛事结束时间*/
	private String com_winner;	/*冠军球队id*/
	private String com_second;	/*亚军球队id*/
	private String com_third;	/*季军球队id*/
	private String com_type;	/*赛事类型*/
	private String com_big_level;	/*所属级别*/
	private String com_rank;	/*获得名次*/
	private String team_id;	/*球队id*/
	private String com_level;	/*赛事级别*/
	private String com_grounp;	/*足球组别*/
	private String team_name;	/*球队名称*/
	
	public String getTeam_name() {
		return team_name;
	}
	
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getCom_grounp() {
		return com_grounp;
	}

	public void setCom_grounp(String com_grounp) {
		this.com_grounp = com_grounp;
	}

	public String getCom_level() {
		return com_level;
	}

	public void setCom_level(String com_level) {
		this.com_level = com_level;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getCom_rank() {
		return com_rank;
	}

	public void setCom_rank(String com_rank) {
		this.com_rank = com_rank;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_end() {
		return com_end;
	}

	public void setCom_end(String com_end) {
		this.com_end = com_end;
	}

	public String getCom_winner() {
		return com_winner;
	}

	public void setCom_winner(String com_winner) {
		this.com_winner = com_winner;
	}

	public String getCom_second() {
		return com_second;
	}

	public void setCom_second(String com_second) {
		this.com_second = com_second;
	}

	public String getCom_third() {
		return com_third;
	}

	public void setCom_third(String com_third) {
		this.com_third = com_third;
	}

	public String getCom_type() {
		return com_type;
	}

	public void setCom_type(String com_type) {
		this.com_type = com_type;
	}

	public String getCom_big_level() {
		return com_big_level;
	}

	public void setCom_big_level(String com_big_level) {
		this.com_big_level = com_big_level;
	}

	@Override
	public String toString() {
		return "PlayerActityBean{" +
				"com_id='" + com_id + '\'' +
				", com_name='" + com_name + '\'' +
				", com_end='" + com_end + '\'' +
				", com_winner='" + com_winner + '\'' +
				", com_second='" + com_second + '\'' +
				", com_third='" + com_third + '\'' +
				", com_type='" + com_type + '\'' +
				", com_big_level='" + com_big_level + '\'' +
				'}';
	}
}
