package com.nsu.service.team;

import com.nsu.bean.team.TeamRegisterDO;

public interface ITeamRegisterService {

	/**
	 * 球队管理员身份证是否可用
	 * @author ljl
	 * @createDate 2017-04-11 13:47:20
	 * @param idCard
	 * @return 当前身份证可用，返回true, 否则false
	 */
	public boolean findTeanIDCardIsAvailable(String idCard);

	/**
	 * 球队管理员注册信息校验以及插入
	 * @author ljl
	 * @createDate 2017-04-11 15:05:54
	 * @param teamUserInfo
	 * @param phoneCode
	 * @return 返回处理结果
	 */
	public String insertRegisterTeamManager(TeamRegisterDO teamUserInfo, String phoneCode);
}
