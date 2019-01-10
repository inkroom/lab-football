package javadev.iip.service.fiveplan;
/**
 * 功能描述：获取5年报表的审核状态
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月26日19：53：24
 */
import java.util.Map;
import javadev.iip.service.BaseService;

public class AuditStatusService extends BaseService{
	private static final String SQL_STATUS="select audit_status from FIVE_PLAN_REPORT where o_id = ? and status = 1";
	
	public Map<String, Object> getAudit(String o_id){
		try {
			return jt.queryForMap(SQL_STATUS,new Object[]{o_id});
	 
		} catch (Exception e) {
			return null;
		}
	}
}
