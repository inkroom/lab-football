package com.nsu.dao.team;

import org.apache.ibatis.annotations.Param;

/**
 * 球队相关验证码操作dao
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-27 15:34:48
 */
public interface TeamSendVerificationCodeDao {

	/**
	 * 更新球队验证码
	 * @author ljl
	 * @createDate 2017-04-27 15:43:16
	 * @param teamID
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public int updateTeamVerificationCode(@Param("aid") String aid, @Param("code") String code) throws Exception;

	/**
	 * 获取球队邮箱验证码
	 * @author ljl
	 * @createDate 2017-04-27 16:01:27
	 * @param aid
	 * @return
	 */
	public String findEmailVerificationCode(String aid) throws Exception;

	/**
	 * 根据aid查询球队邮箱
	 * @author ljl
	 * @createDate 2017-04-28 10:11:12
	 * @param aid
	 * @return
	 * @throws Exception
	 */
	public String findTeamBindingEmail(String aid) throws Exception;

	/**
	 * 根据角色id查询aid
	 * @author ljl
	 * @createDate 2017-06-02 09:38:38
	 * @param id
	 * @param type
	 * @return
	 */
	public String findAidByRoleId(@Param("id") Object id, @Param("type") Object type) throws Exception;
}
