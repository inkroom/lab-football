package javadev.iip.action.fiveplan;

import java.util.HashMap;
import java.util.Map;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
/**
 * 功能描述：获取5年报表的审核状态
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月26日19：53：24
 */
public class AuditStatusAction extends BaseAction{
	private String audit_status;
	private String paudit_status;
	private String o_name;
	private Map<String,Object> sMp;
	private Map<String,Object> uMp;
	public String intoStatusPage(){
		Map data = (Map) getSession().get(Constants.LOGIN_USER);
		String o_id= data.get("O_ID").toString();
		sMp = getServMgr().getAuditStatusService().getAudit(o_id);
		String paudit_status = sMp.get("audit_status").toString();
		audit_status =isChange(paudit_status);
		sMp.put("audit_status", audit_status);
		uMp=getServMgr().getDownLoadExcelService().getUserName(o_id);
		return "intoStatus";
	}
	private String isChange(Object object){
		String str = object.toString();
		if(str.equals("0")){
			return "待审核";
		}else if(str.equals("1")){
			return "审核通过";
		}else if(str.equals("2")){
			return "审核未通过";
		}else{
			return "null";
		}
	}
	
	
	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public Map<String, Object> getsMp() {
		return sMp;
	}

	public void setsMp(Map<String, Object> sMp) {
		this.sMp = sMp;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public Map<String, Object> getuMp() {
		return uMp;
	}
	public void setuMp(Map<String, Object> uMp) {
		this.uMp = uMp;
	}
	
}
