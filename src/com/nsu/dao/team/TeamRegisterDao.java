package com.nsu.dao.team;

import org.apache.ibatis.annotations.Param;

import com.nsu.bean.team.TeamRegisterDO;

public interface TeamRegisterDao {

	/**
	 * @Description: 将TeamRegister插入到xxx中
	 * @author 侯润达
	 * @create_date 2017年4月10日 下午9:49:42
	 * @return
	 */
	public int insertTeamRegisterDOTo(TeamRegisterDO teamRegisterDO);

	/**
	 * 查询球队管理员身份证是否存在
	 * @author ljl
	 * @createDate 2017-04-11 13:51:04
	 * @param idCard
	 * @return 返回当前角色查询身份证在数据库中的数量
	 */
	public int findTeamIDCardIsAvailable(String idCard) throws Exception;

	/**
	 * 向数据库插入球队管理员注册信息
	 * @author ljl
	 * @createDate 2017-04-11 15:13:30
	 * @param teamUserInfo 球队管理员注册信息
	 * @return 
	 */
	public int insertTeamRegisterInfoIntoAccount(TeamRegisterDO teamUserInfo) throws Exception;

	/**
	 * 查询手机号在数据库中的类型为球队管理员的数量
	 * @author ljl
	 * @createDate 2017-04-11 18:22:14
	 * @param phone
	 * @return 
	 */
	public int findPhoneNum(String phone) throws Exception;
	
	/**
	 * 向team表插入新的记录
	 * @author ljl
	 * @createDate 2017-04-12 16:26:54
	 * @param AId
	 * @param oper
	 * @return
	 * @throws Exception
	 */
	public int insertTeamTable(TeamRegisterDO teamUserInfo) throws Exception;

	/**
	 * 查询球队编号是否唯一
	 * @author ljl
	 * @createDate 2017-04-18 17:12:30
	 * @param code
	 * @return
	 */
	public int findIsOnlyCode(String code) throws Exception;

	/**
	 * 向球队积分表TEAM_SCORE插入一条新记录
	 * @author ljl
	 * @createDate 2017-04-21 14:55:28
	 * @param teamID
	 * @return
	 */
	public int insertTeamScoreTable(@Param("teamID") String teamID, @Param("account") String account) throws Exception;
}
