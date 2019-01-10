package javadev.iip.service;


public class IsPassService extends BaseService {

	private String isPassSport = "SELECT AUDIT_STATUS FROM FIVE_PLAN_REPORT WHERE O_ID=? AND STATUS = 1";

	public String getAuditStatusFive(String o_id){
		try {
			System.out.println("----------------------------------------------------"+o_id);
			System.out.println(jt.queryForObject(isPassSport, String.class,o_id));
			System.out.println("----------------------------------------------------");
			return jt.queryForObject(isPassSport, String.class,o_id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	

}
