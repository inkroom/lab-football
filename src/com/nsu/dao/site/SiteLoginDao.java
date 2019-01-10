package com.nsu.dao.site;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface SiteLoginDao {

	String selectRId(Long SEId);
	/**
	 * 得到用户的基本信息
	 * @param userName
	 * @return
	 */
	Map<String, Object> getUserInfo(@Param("userName") String userName);
	/**
	 * 查询账号是否存在
	 * @return
	 */
	List<String> selectName();
	/**
	 * 通过账号查询密码
	 * @param name
	 * @return
	 */
	String selectPasswordByName(@Param("name") String name);
	
	/**
	 * 查询账号是否可用
	 * @return
	 */
	Integer selectStatus(@Param("userName")String userName);
	/**
	 * 通过账号查询A_ID
	 * @param name
	 * @return
	 */
	Long selectAIDByName(@Param("name") String name);
	
	Long selectSEID(@Param("AID") long AID);
	
	/**
	 * 得到用户姓名
	 * @param A_ID
	 * @return
	 */
	String selectUserName(@Param("A_ID")int A_ID);
	/**
	 * 通过A_ID得到用户完善信息记录
	 * @param A_ID
	 * @return
	 */
	Map<String, String> selectInfoByAID(@Param("A_ID") int A_ID);
	/**
	 * 完善信息
	 * @param name
	 * @param phone
	 * @param A_ID
	 */
	void insertPreInfo(@Param("name") String name,@Param("phone") String phone,@Param("A_ID") int A_ID);
	/**
	 * 手机端的基本信息
	 * @param mobile
	 */
	void updateMobileInfo(@Param("mobile") Map<String, Object> mobile);
}
