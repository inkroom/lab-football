package com.nsu.service.coach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.coach.CoachInfoBean;

/**
 * 教练个人信息的相关数据操作Service
 * @author Dengjiawei
 *
 */
public interface CoachInfoService {
	/**
	 * 通过教练员的ID查询个人基础信息
	 * @param coachID
	 */
	public Map<String, Object> getCoachInfoBase(String coachID);
	/**
	 * 通过教练员id查询积分信息
	 * @param coachID
	 * @return
	 */
	Map<String, Object> getCoachintegral(@Param("coachID")String coachID);
	/**
	 * 修改教练员数据的时候先回显数据
	 * @param coachID
	 * @return
	 */
	public Map<String, Object> getCoachEditInfo(String coachID);
	/**
	 * 查询教练员其他没有级别比赛的数据
	 * @param coachID
	 * @return
	 */
	public List<Map<String, Object>> getCoachOtherMatch(String coachID);  
	/**
	 * 查询教练员校级比赛的数据
	 * @param coachID
	 * @return
	 */
	public List<Map<String, Object>> getCoachSchoolMatch(String coachID);  
	/**
	 * 教练头像上传
	 * @param imgStr
	 * @param path
	 * @return
	 */
	public boolean headPicUpload(String coachID, String path);
	/**
	 * 查询教练员县级比赛的数据
	 * @param coachID
	 * @return
	 */
	public List<Map<String, Object>> getCoachCountryMatch(String coachID);
	/**
	 * 查询教练员市级比赛的数据
	 * @param coachID
	 * @return
	 */
	public List<Map<String,Object>>getCoachCityMatch(String coachID);
	/**
	 * 查询教练员省级比赛的数据
	 * @param coachID
	 * @return
	 */
	public List<Map<String, Object>> getCoachProvinceMatch(String coachID);
	/**
	 * 将相应的coach更新到数据库中
	 * @param coach
	 */
	public boolean saveCoachEditInfo(CoachInfoBean coach);
	/**
	 * 精彩图片上传
	 * @param coachAccountID
	 * @param path
	 * @return
	 */
	public boolean updateWonderfulPhoto(String coachAccountID,String path);
	/**获取精彩图片
	 * @param coachID
	 * @return
	 */
	public List<Map<String, Object>>  getCoachWonderfulPhoto(String coachID);
	/**
	 * 将验证码存入数据库
	 * @param A_EMAIL_CHECK_CODE
	 * @param A_ID
	 */
	public void saveEmail(String A_EMAIL_CHECK_CODE,String A_ID);
	/**
	 * 获取用户的邮件验证码
	 * @param A_ID
	 * @return
	 */
	public String getEmailCode(String A_ID);
}
