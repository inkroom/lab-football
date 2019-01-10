package com.nsu.service.team;

import com.nsu.bean.team.OperMessageBean;

/**
 * 球队发送信息接口
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-27 15:12:18
 */
public interface ITeamSendVerificationCodeService {

	/**
	 * 获取验证码
	 * @author ljl
	 * @createDate 2017-04-27 15:26:08
	 * @param teamID
	 * @param type 0获取邮箱验证码，1获取手机验证码
	 * @return
	 */
	public String findVerificationCode(String aid, int type);
	
	/**
	 * 发送邮箱验证码
	 * @author ljl
	 * @createDate 2017-04-27 15:22:34
	 * @param teamID 球队ID
	 * @return
	 */
	public boolean sendEmailCode(String aid, String email);

	/**
	 * 发送手机验证码
	 * @author ljl
	 * @createDate 2017-04-27 15:30:22
	 * @param phone
	 * @return
	 */
	public boolean sendPhoneCode(String phone);

	/**
	 * 根据aid查询邮箱
	 * @author ljl
	 * @createDate 2017-04-28 10:07:36
	 * @param aid
	 * @return
	 */
	public String findTeambindingEmail(String aid);
	
	/**
	 * 发送个人消息
	 * <p>只可以根据球员、教练员、机构、球队、赛事id以及A_ID发送信息</p>
	 * @author ljl
	 * @createDate 2017-06-02 08:45:24
	 * @param messageInfo 消息相关信息（包含*发送者id，*接收者id，*发送信息的标题和内容，发送者和接收者的id是否是aid，发送者和接收者类型）
	 */
	public void sendOperMessage(OperMessageBean messageInfo);

}
