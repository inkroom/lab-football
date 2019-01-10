package com.nsu.service.organization;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
* @ClassName: IOrgPerfectInfoService 
* @Description: 机构系统完善信息Service
* @author 严涛
* @date 2017年4月10日 下午7:16:08 
*
 */
public interface IOrgPerfectInfoService {
	
	
	/**
	 * 
	* @Title: updateOrganizationOrg 
	* @Description: 机构完善信息Service
	* @param @param org_name
	* @param @param org_phone
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateOrganizationOrg(@Param("user_name") String user_name , @Param("user_phone") String user_phone,@Param("user_email") String user_email,@Param("org_id") long org_id) throws Exception;
	
	/**
	 * 
	* @Title: getOrganizationMessage 
	* @Description: 查询A-name，A-phone的Service 
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Map<Object,String>    返回类型 
	* @throws
	 */
	public Map<Object,String > getOrganizationMessage(long org_id) throws Exception;
	
	/**
	 * 
	* @Title: modifyOrganizationMessage 
	* @Description: 修改机构name Service
	* @param @param userName
	* @param @param userPhone
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int modifyOrganizationName (@Param("orgName") String orgName,@Param("org_id") long org_id) throws Exception;
	
	/**
	 * 
	* @Title: modifyAccountName 
	* @Description: 修改机构用户name Service
	* @param @param userName
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int modifyAccountName(@Param("userName") String userName,@Param("org_id") long org_id) throws Exception; 

}
