package com.nsu.bean.site;

import java.util.Date;


/**
 * 直播的信息
 * @author 刘俊
 *
 */
public class SiteLive {

	
	//主队比分
	private int l_score;
	//客队比分
	private int r_score;
	//直播文本
	private String html;
	//直播图片
	private String imgUrl;
	//文本类型
	private int type;
	//直播主队名称
	private String Hteam_name;
	//直播客队名称
	private String Vteam_name;
	//直播开始时间
	private Date startTime;
	//主队队徽url
	private String Hteam_logo;
	//客队队徽url
	private String Vteam_logo;
	

	public int getL_score() {
		return l_score;
	}
	public void setL_score(int l_score) {
		this.l_score = l_score;
	}
	public int getR_score() {
		return r_score;
	}
	public void setR_score(int r_score) {
		this.r_score = r_score;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getHteam_name() {
		return Hteam_name;
	}
	public void setHteam_name(String hteam_name) {
		Hteam_name = hteam_name;
	}
	public String getVteam_name() {
		return Vteam_name;
	}
	public void setVteam_name(String vteam_name) {
		Vteam_name = vteam_name;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getHteam_logo() {
		return Hteam_logo;
	}
	public void setHteam_logo(String hteam_logo) {
		Hteam_logo = hteam_logo;
	}
	public String getVteam_logo() {
		return Vteam_logo;
	}
	public void setVteam_logo(String vteam_logo) {
		Vteam_logo = vteam_logo;
	}
	
}
