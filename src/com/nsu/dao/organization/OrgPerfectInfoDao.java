package com.nsu.dao.organization;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 
* @ClassName: OrgPerfectInfoDao 
* @Description: 机构系统完善信息Dao
* @author 严涛
* @date 2017年4月10日 下午7:19:10 
*
 */
public interface OrgPerfectInfoDao {
	
	
	/**
	 * 
	* @Title: updateOrganizationOrg 
	* @Description: 更新Account表数据
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
	* @Description: 根据机构ID查找ORG-name，A-name，A-phone
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
	* @Description: 机构更新机构Name DAO
	* @param @param userName
	* @param @param userPhone
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int modifyOrganizationName ( @Param("orgName") String orgName,@Param("org_id") long org_id) throws Exception;
	
	/**
	 * 
	* @Title: modifyAccountName 
	* @Description: 机构更新机构的用户name DAO
	* @param @param userName
	* @param @param org_id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int modifyAccountName(@Param("userName") String userName,@Param("org_id")long org_id) throws Exception; 

}
