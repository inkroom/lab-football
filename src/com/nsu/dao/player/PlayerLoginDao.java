package com.nsu.dao.player;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Title: PlayerLoginDao.java
 * @Package com.nsu.dao.player
 * @Description: 球员登录Dao
 * @author 侯松梁
 * @date 2017年4月10日 下午7:29:26
 * @version V1.0
 */
public interface PlayerLoginDao {

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPlayer(Map<String, Object> map) throws Exception;

	/**
	 * 更新登录时间
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateLoginTime(Map<String, Object> map) throws Exception;

	/**
	 * 存入球员注册信息
	 * 
	 * @Description (插入注册帐号信息)
	 * @param user
	 * @return
	 * @throws Exception
	 * @author hm
	 */
	public int insertUser(@Param("user") Map<String, Object> user) throws Exception;

	/**
	 * 
	 * @Description (获取当前username所属的A_ID)
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int getA_ID(@Param("user") String user) throws Exception;

	/**
	 * 
	 * @Description (插入球员身份证和a_id)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insertAccountToPlayer(@Param("param") Map<String, Object> param) throws Exception;

	/**
	 * 
	 * @Description (获取身份证号码)
	 * @param id_card
	 * @return
	 * @throws Exception
	 */
	public int getID_CARD(@Param("id_card") String id_card) throws Exception;

	/**
	 * 
	 * @Description (获取用户信息)
	 * @param a_id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUserInfo(@Param("username") String username) throws Exception;

	/**
	 * 根据id获取球员姓名
	 * 
	 * @param A_ID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getUserNameById(@Param("A_ID") String A_ID) throws Exception;

	/**
	 * 判
	 * 
	 * @Description (判断用户名是否存在)
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int userIsExist(@Param("user") String user) throws Exception;

	/**
	 * 
	 * @Description (更新用户时间)
	 * @param bir
	 * @return
	 * @throws Exception
	 */
	public int updateBir(@Param("bir") String bir, @Param("a_id") int a_id) throws Exception;

	/**
	 * 
	 * @Description (更新用户的手机信息)
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public int updateMobileInfo(@Param("mobile") Map<String, Object> mobile) throws Exception;
	/**
	 * 插入Player_Score表初始连接
	 * TODO 这里用一句话描述这个方法的作用
	 * @param p_id
	 * @return
	 */
	public int insertPyScore(@Param("p") Map<String, Object> p);
	/**
	 * 获取p_id
	 * TODO 这里用一句话描述这个方法的作用
	 * @param a_id
	 * @return
	 * @throws Exception
	 */
	public int getPid(@Param("a_id") int a_id) throws Exception;
}
