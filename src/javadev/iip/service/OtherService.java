package javadev.iip.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import javadev.iip.util.validata.V;
import oracle.net.aso.f;
import oracle.net.aso.l;

/**
 * 
 * @author 梅谢兵
 *
 */
public class OtherService extends BaseService {
	
	/**
	 * 获取系统开放时间
	 */
	
	private static final String GET_OPEN_DATE = "select START_DATE_DISTRICT,END_DATE_DISTRICT from REPORT_RECORD where STATUS = 1";
	public Map<String, Object> getOpenDate(){
		try {
			return jt.queryForMap(GET_OPEN_DATE);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	
	/**
	 * 登录
	 */
	private static final String GET_SCHOOL_O_NAME_BY_O_ID = "SELECT * FROM ACCOUNT join ORG on ACCOUNT.O_ID = ORG.O_ID where ACCOUNT.USERNAME = ? and ORG.LEVEL_CODE = 4 and ACCOUNT.STATUS = 1 and ACCOUNT.type = 1";
	public Map<String, Object> getShoolById(String userName){
		try {
			List<Map<String, Object>> list = jt.queryForList(GET_SCHOOL_O_NAME_BY_O_ID, userName);
			if (list.size()!=1) {
				return null;
			}else {
				return list.get(0);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 更新登录时间
	 */
	private static final String UPDATE_LOGIN_ = "UPDATE ACCOUNT SET RECENT_LOGIN = sysdate WHERE USERNAME = ?";
	public void updateLogin(String userName){	
		try {
			jt.update(UPDATE_LOGIN_, userName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static final String JUST_GET_C_ID_BY_O_ID = "SELECT C_ID FROM CAMPUS_COMMON_DATA WHERE O_ID = ? AND STATUS = 1";
	/**
	 * 获取c_id
	 * @param O_ID
	 * @return
	 */
	public String getC_ID_BY_O_ID(String O_ID) {
		try {
			List<Map<String, Object>> list = jt.queryForList(JUST_GET_C_ID_BY_O_ID,O_ID);
			if (list.size()==1 && list.get(0).get("C_ID") !=null) {
				return list.get(0).get("C_ID").toString();
			}
			return "0000000";
		} catch (Exception e) {
			// TODO: handle exception
			return "0000000";
		}
	}
	
	
	
	
	/**
	 * 获取去年体育报表信息
	 */
	private static final String GET_SPORT_LAST_YEAR_DATA = "SELECT * FROM PHY_REPORT WHERE O_ID=? and to_char(year,'yyyy') = ?";
	public Map<String, Object> getSportLastYearData(String O_ID){
		Map< String, Object > map = getSportData(O_ID);
		log.info("======="+map);
		try {
			if (map!=null&&map.get("YEAR")!=null) {
				List<Map<String, Object>> list = jt.queryForList(GET_SPORT_LAST_YEAR_DATA,new Object[]{O_ID, V.getLastYear((Date) map.get("YEAR"))});
				if (list.size()!=1) {
					return null;
				}else {
					return list.get(0);
				}
			}else {
				return null;
			}
			
		} catch (EmptyResultDataAccessException e) {
			
		}
		return null;
	}
	
	/**
	 * 获取当年体育报表信息
	 */
	private static final String GET_SPORT_DATA = "SELECT * FROM PHY_REPORT WHERE O_ID = ? AND STATUS = 1";
	public Map<String, Object> getSportData(String O_ID){
		try {
			
			List<Map<String, Object>> list = jt.queryForList(GET_SPORT_DATA,O_ID);
			if (list.size()!=1) {
				return null;
			}else {
				return list.get(0);
			}
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}
	
	
	private static final String GET_SPORT_WRITER_DATA = "select *from PHY_WRITE WHERE O_ID = ?";
	public Map<String , Object> getSportWriterData(String o_id) {
		try {
			List<Map<String, Object>> list = jt.queryForList(GET_SPORT_WRITER_DATA,o_id);
			if (list.size()!=1) {
				return null;
			}else {
				return list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	private static final String GET_ART_WRITER_DATE = "select *　from ART_WRITE WHERE O_ID = ?";
	public Map<String , Object> getArtWriterData(String o_id) {
		try {
			List<Map<String, Object>> list = jt.queryForList(GET_ART_WRITER_DATE,o_id);
			if (list.size()!=1) {
				return null;
			}else {
				return list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	/**
	 * 获取去年艺术报表信息
	 */
	private static final String GET_ART_LAST_YEAR_DATA = "SELECT * FROM ART_REPORT WHERE O_ID=? and to_char(year,'yyyy') = ?";
	public Map<String, Object> getArtLastYearData(String O_ID){
		Map< String, Object > map = getSportData(O_ID);
		try {
			if (map!=null&&map.get("YEAR")!=null) {
				
				List<Map<String, Object>> list = jt.queryForList(GET_ART_LAST_YEAR_DATA,new Object[]{O_ID, V.getLastYear((Date) map.get("YEAR"))});
				if (list.size()!=1) {
					return null;
				}else {
					return list.get(0);
				}
			}else {
				return null;
			}
			
		} catch (EmptyResultDataAccessException e) {
			
		}
		return null;
	}
	
	/**
	 * 获取当年艺术报表信息
	 */
	private static final String GET_ART_DATA = "SELECT * FROM ART_REPORT WHERE O_ID = ? AND STATUS = 1";
	public Map<String, Object> getArtData(String O_ID){
		try {
			
			List<Map<String, Object>> list = jt.queryForList(GET_ART_DATA,O_ID);
			if (list.size()!=1) {
				return null;
			}else {
				return list.get(0);
			}
		} catch (EmptyResultDataAccessException e) {
		}
		return null;
	}

	/**
	 * 修改密码
	 * @param passoword
	 * @return
	 */
	private static final String UPDATE_PASSWORD = "UPDATE ACCOUNT SET PASSWORD = ? , SALT = ? where USERNAME = ?";
	private static final String UPDATE_PASSWORD_STATUS = "UPDATE ACCOUNT SET OPER_DATE = sysdate , OPER_BY = ? where USERNAME = ?";
	public boolean updatePassword(String[] passoword,String userName,String a_Id){
		try {
			if (jt.update(UPDATE_PASSWORD, passoword[0],passoword[1],userName) == 1) {
				jt.update(UPDATE_PASSWORD_STATUS, a_Id,userName);
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
}
