package javadev.iip.service;


public class IsPassService extends BaseService {

	private String isPassSport = "SELECT AUDIT_STATUS FROM PHY_REPORT WHERE O_ID=? AND STATUS = 1";
	/**
	 * 获取 体育是否通过
	 * @param o_id
	 * @return
	 */
	public String getAuditStatusSport(String o_id){
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
	
	
	private String isPassSportSelf = "SELECT AUDIT_STATUS FROM PHY_SELF_REMARK WHERE O_ID=? AND STATUS = 1 ";
	/**
	 * 获取 体育自评是否通过
	 * @param o_id
	 * @return
	 */
	public String getAuditStatusSportSelf(String o_id){
		try {
			return jt.queryForObject(isPassSportSelf, String.class,o_id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	private String isPassArtSelf = "SELECT AUDIT_STATUS FROM ART_REPORT WHERE O_ID=? AND STATUS = 1 ";
	/**
	 * 获取 艺术是否通过
	 * @param o_id
	 * @return
	 */
	public String getAuditStatusArt(String o_id){
		try {
			return jt.queryForObject(isPassArtSelf, String.class,o_id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
