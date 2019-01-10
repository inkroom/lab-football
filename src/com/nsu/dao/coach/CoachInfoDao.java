package com.nsu.dao.coach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.coach.CoachInfoBean;

public interface CoachInfoDao {
	/**
	 * 通过教练员id查询基础信息
	 * @param coachID
	 * @return
	 */
	Map<String, Object> getCoachInfoBase(@Param("coachID")String coachID);
	/**
	 * 通过教练员id查询积分信息
	 * @param coachID
	 * @return
	 */
	Map<String, Object> getCoachintegral(@Param("coachID")String coachID);
	/**
	 * 修改教练员信息的时候先回显数据
	 * @param coachID
	 * @return
	 */
	Map<String, Object> getCoachEditInfo(@Param("coachID")String coachID);
	/**
	 * 通过coachID查询教练员的校级比赛
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getCoachSchoolMatch(@Param("coachID")String coachID);
	/**
	 * 通过coachID查询教练员的县级比赛
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getCoachCountyMatch(@Param("coachID")String coachID);
	/**
	 * 通过coachID查询教练员的市级比赛
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getCoachCityMatch(@Param("coachID")String coachID);
	/**
	 * 通过coachID查询教练员的省级比赛
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getCoachProvinceMatch(@Param("coachID")String coachID);
	/**
	 * 查询教练员其他没有级别比赛的数据
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getCoachOtherMatch(@Param("coachID")String coachID); 
	/**
	 * 获取教练所带的所有球队id
	 * @param coachID
	 * @return
	 */
	List<Map<String, Object>> getCoachTeamsId(@Param("coachID")String coachID);
	/**
	 * 将相应的coach更新到数据库中
	 * @param coach
	 */
	Integer saveCoachEditInfo(@Param("coach")CoachInfoBean coach);
	/**
	 * 教练头像上传
	 * @param imgStr
	 * @param path
	 * @return
	 */
	int headPicUpload(@Param("coachID")String coachID,@Param("path")String path);
	
	
	int updateWonderfulPhoto(@Param("coachAccountID")String coachAccountID,@Param("path")String path);
	/**
	 * 根据id获取教练员精彩图片
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getWdpic(@Param("coachID")String coachID);
	
	/**
	 * 将验证码存入数据库
	 * @param A_EMAIL_CHECK_CODE
	 * @param A_ID
	 */
	int saveEmail(@Param("A_EMAIL_CHECK_CODE")String A_EMAIL_CHECK_CODE,@Param("A_ID")String A_ID);
	/**
	 * 获取用户的邮件验证码
	 * @param A_ID
	 * @return
	 */
	String getEmailCode(String A_ID);
}
