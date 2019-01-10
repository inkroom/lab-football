package com.nsu.service.organization.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.nsu.dao.organization.OrgPerfectInfoDao;
import com.nsu.service.BaseService;
import com.nsu.service.organization.IOrgPerfectInfoService;

/**
 * 
* @ClassName: OrgPerfectInfoServiceImpl 
* @Description: 机构系统完善信息Service的实现类
* @author 严涛
* @date 2017年4月10日 下午7:43:27 
*
 */
@Service("iOrgPerfectInfoService")
public class OrgPerfectInfoServiceImpl extends BaseService implements IOrgPerfectInfoService{

	@Resource
	private OrgPerfectInfoDao OrgPerfectInfodao;

	@Override
	public int updateOrganizationOrg(@Param("user_name") String user_name , @Param("user_phone") String user_phone,@Param("user_email") String user_email,@Param("org_id") long org_id) throws Exception {
		int Resultnum = OrgPerfectInfodao.updateOrganizationOrg(user_name, user_phone,user_email,org_id);
		if(Resultnum == 1){
			return 1;
		}
		return 0;
	}

	@Override
	public Map<Object, String> getOrganizationMessage(long org_id) throws Exception {
		
		return OrgPerfectInfodao.getOrganizationMessage(org_id);
	}


	@Override
	public int modifyOrganizationName(@Param("orgName") String orgName,@Param("org_id") long org_id) throws Exception {
		int num1 = OrgPerfectInfodao.modifyOrganizationName(orgName, org_id);
		if(num1 == 1){
			return 1;
		}
		return 0;
	}

	@Override
	public int modifyAccountName(@Param("userName") String userName,@Param("org_id") long org_id) throws Exception {
		int num1 = OrgPerfectInfodao.modifyAccountName(userName, org_id);
		if(num1 == 1){
			return 1;
		}
		return 0;
	}

}
