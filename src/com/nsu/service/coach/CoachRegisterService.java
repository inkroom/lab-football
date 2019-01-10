package com.nsu.service.coach;

public interface CoachRegisterService {
	/**
	 * 验证教练员身份证是否被注册
	 * @param idNum
	 * @return
	 */
	boolean checkCoachRegIdNum(String idNum);
	/**
	 * 验证教练员手机是否已经被注册
	 * @param phone
	 * @return
	 */
	boolean checkCoachRegPhone(String phone);
	/**
	 * 教练员注册
	 * @param IdNum
	 * @param passwd
	 * @param phone
	 * @return
	 */
	boolean insertCoachRegister(String IdNum, String passwd, String phone);
	/**
	 * @param phone
	 */
	String selectCoachID(String phone);
	/**
	 * @param selectCoachID
	 */
	void addCoachScoreStauts(String selectCoachID);
}
