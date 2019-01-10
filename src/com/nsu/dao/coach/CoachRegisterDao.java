package com.nsu.dao.coach;

import org.apache.ibatis.annotations.Param;

public interface CoachRegisterDao {
	/**
	 * 添加新注册的教练员到account中
	 * @param passwd
	 * @param phone
	 * @return
	 */
	int addCoachToAccount(@Param("idNum")String idNum,@Param("passwd")String passwd, @Param("phone")String phone);
	/**
	 * 添加新注册的教练员到coach中
	 * @param ID_Num
	 * @return
	 */
	int addCoachToCoach(@Param("AId")String AId,@Param("birth")String birth);
	/**
	 * 通过手机号查询account表中教练的aid
	 * @param phone
	 * @return
	 */
	String getCoachAId(@Param("phone")String phone);
	
	/**
	 * 查看教练员手机是否已经被注册
	 * @param phone
	 * @return
	 */
	int checkCoachRegPhone(@Param("phone")String phone);
	/**
	 * 查看教练员身份证是否已经被注册
	 * @param IdNum
	 * @return
	 */
	int checkCoachRegIdNum(@Param("IdNum")String IdNum);
	/**
	 * @param phone
	 * @return
	 */
	String selectCoachID(String phone);
	/**
	 * @param coachId
	 * @return
	 */
	 void addCoachScoreStauts(String coachId);
}
