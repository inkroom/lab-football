package com.nsu.bean.player;

/**
 * @ClassName PlayerMatchBean
 * @Description TODO(球员相关的比赛实体)
 * @author hm
 * @Date 2017年4月17日 下午2:55:41
 * @version 1.0.0
 */
public class PlayerMatchBean {
	/**
	 * 赛事ID
	 */
	private int com_id;
	/**
	 * 赛程ID
	 */
	private int r_id;
	/**
	 * 赛事名称
	 */
	private String r_name;
	/**
	 * 比赛时间
	 */
	private String r_time;
	/**
	 * 主队ID
	 */
	private String r_h_team_id;
	/**
	 * 客队ID
	 */
	private String r_v_team_id;
	/**
	 * 常规赛主队等分
	 */
	private int r_regular_h_t_s;
	/**
	 * 常规赛客队得分
	 */
	private int r_regular_v_t_s;
	/**
	 * 加时赛主队得分
	 */
	private int r_overtime_h_t_s;
	/**
	 * 加时赛客队得分
	 */
	private int r_overtime_v_t_s;
	/**
	 * 点球决胜主队得分
	 */
	private int r_pena_h_t_s;
	/**
	 * 点球决胜客队得分
	 */
	private int r_pena_v_t_s;
	
	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_time() {
		return r_time;
	}

	public void setR_time(String r_time) {
		this.r_time = r_time;
	}

	public String getR_h_team_id() {
		return r_h_team_id;
	}

	public void setR_h_team_id(String r_h_team_id) {
		this.r_h_team_id = r_h_team_id;
	}

	public String getR_v_team_id() {
		return r_v_team_id;
	}

	public void setR_v_team_id(String r_v_team_id) {
		this.r_v_team_id = r_v_team_id;
	}

	public int getR_regular_h_t_s() {
		return r_regular_h_t_s;
	}

	public void setR_regular_h_t_s(int r_regular_h_t_s) {
		this.r_regular_h_t_s = r_regular_h_t_s;
	}

	public int getR_regular_v_t_s() {
		return r_regular_v_t_s;
	}

	public void setR_regular_v_t_s(int r_regular_v_t_s) {
		this.r_regular_v_t_s = r_regular_v_t_s;
	}

	public int getR_overtime_h_t_s() {
		return r_overtime_h_t_s;
	}

	public void setR_overtime_h_t_s(int r_overtime_h_t_s) {
		this.r_overtime_h_t_s = r_overtime_h_t_s;
	}

	public int getR_overtime_v_t_s() {
		return r_overtime_v_t_s;
	}

	public void setR_overtime_v_t_s(int r_overtime_v_t_s) {
		this.r_overtime_v_t_s = r_overtime_v_t_s;
	}

	public int getR_pena_h_t_s() {
		return r_pena_h_t_s;
	}

	public void setR_pena_h_t_s(int r_pena_h_t_s) {
		this.r_pena_h_t_s = r_pena_h_t_s;
	}

	public int getR_pena_v_t_s() {
		return r_pena_v_t_s;
	}

	public void setR_pena_v_t_s(int r_pena_v_t_s) {
		this.r_pena_v_t_s = r_pena_v_t_s;
	}

	@Override
	public String toString() {
		return "PlayerMatchBean [com_id=" + com_id + ", r_id=" + r_id + ", r_name=" + r_name + ", r_time=" + r_time
				+ ", r_h_team_id=" + r_h_team_id + ", r_v_team_id=" + r_v_team_id + ", r_regular_h_t_s="
				+ r_regular_h_t_s + ", r_regular_v_t_s=" + r_regular_v_t_s + ", r_overtime_h_t_s=" + r_overtime_h_t_s
				+ ", r_overtime_v_t_s=" + r_overtime_v_t_s + ", r_pena_h_t_s=" + r_pena_h_t_s + ", r_pena_v_t_s="
				+ r_pena_v_t_s + "]";
	}

}
