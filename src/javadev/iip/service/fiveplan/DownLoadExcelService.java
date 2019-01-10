package javadev.iip.service.fiveplan;

import java.util.Map;

import javadev.iip.service.BaseService;
/**
 * 功能描述：获取全部的数据
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月10日11：23：24
 */
public class DownLoadExcelService extends BaseService{

	private static final String SQL_ALL_FIVE_PLAN_REPORT=
			"select *from FIVE_PLAN_REPORT where o_id = ?  and STATUS =1";
	private static final String SQL_USER_ID="select O_NAME from ORG where  o_id=?";
	public Map<String, Object> getAllData(String o_id,String fp_year ){
		try {

			return jt.queryForMap(SQL_ALL_FIVE_PLAN_REPORT,new Object[]{o_id});
			
		} catch (Exception e) {
			return null;
		}
	}
	public Map<String, Object> getUserName(String o_id){
		try {

			return jt.queryForMap(SQL_USER_ID,new Object[]{o_id});
			
		} catch (Exception e) {
			return null;
		}
	}
}
