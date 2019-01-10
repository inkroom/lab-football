package javadev.iip.service.sport;

import java.util.Map;

import javadev.iip.service.BaseService;

/**
 * 功能描述:用于
 * @author 曹旭峰 
 *@version 1.0 
 * Create Date: 2016-11-14
 */

public class SportEndServive extends BaseService{

	private static final String SET_SQL = "UPDATE PHY_WRITE SET PERSON_NAME=?,PERSON_PHONE=?,OPER_BY=?, OPER_DATE = SYSDATE WHERE O_ID=? ";
	public boolean modifyData(String PERSON_NAME, String PERSON_PHONE, String o_id){
		try{
			jt.update(SET_SQL,new Object[]{PERSON_NAME,PERSON_PHONE,o_id,o_id});
			return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
	
	private static final String GET_SQl = "select PERSON_NAME,PERSON_PHONE from PHY_WRITE where O_ID = ? AND STATUS = 1"; 
	
	public Map<String, Object> getNowData(String o_id) throws Exception{
		return jt.queryForMap(GET_SQl, new Object[]{o_id});
	}

}
