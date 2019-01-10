package javadev.iip.service.fiveplan;

import java.util.Map;

import org.apache.struts2.components.Debug;

import javadev.iip.service.BaseService;

public class PageOneService extends BaseService{
	/**
	 * 功能描述：第二页的表单的Service
	 * 
	 * @author 侯润达
	 * @version:1.0
	 * Create Date ：16-08-30
	 */ 
	//查询语句：
	private static final String SQL_GET_ALL_POA="select is_achieve_play_ground,"
			+ "add_play_ground,add_play_ground_area,spend_for_add_play_ground,"
			+ "extension_modify_play_ground,area_exten_modify_play_ground,"
			+ "spend_for_mod_play_ground,is_add_euqi_gro,"
			+ "spend_euqi_gro_totaL,stage FROM FIVE_PLAN_REPORT"
			+ " where o_id=? and TO_CHAR(FP_YEAR,'YYYY')=?  and status=?";
	
	private static final String SQL_GET_ALL_POAS="select is_achieve_play_ground,"
			+ "add_play_ground,add_play_ground_area,spend_for_add_play_ground,"
			+ "extension_modify_play_ground,area_exten_modify_play_ground,"
			+ "spend_for_mod_play_ground,is_add_euqi_gro,"
			+ "spend_euqi_gro_totaL,stage FROM FIVE_PLAN_REPORT"
			+ " where o_id=?  and status=?";
	/**
	 * 功能:查询上一年的数据
	 * @return oldYearData
	 * @author 侯润达
	 */
	public Map<String,Object> getAllOldData(String o_id,String fp_year,int status){
		try{
			
			return jt.queryForMap(SQL_GET_ALL_POA,new Object[]{o_id,fp_year,status});

		}catch(Exception e ){

			return null ;
		}
	}
	public Map<String,Object> getAllOldDatas(String o_id,String fp_year,int status){
		try{
			
			return jt.queryForMap(SQL_GET_ALL_POAS,new Object[]{o_id,status});

		}catch(Exception e ){

			return null ;
		}
	}
	

	//通过登录ID， 更新数据并返回
	public boolean uploadForm(String o_id,String is_achieve_play_ground,String add_play_ground, 
			String add_play_ground_area, String spend_for_add_play_ground,
			String extension_modify_play_ground, String area_exten_modify_play_ground, String spend_for_mod_play_ground,
			String is_add_euqi_gro,String spend_euqi_gro_totaL,String stage,String a_id){
 
      
		try {
			jt.update("CALL PRC_UPD_STAGE(?,?)",new Object[]{o_id,stage});
            
			int s =jt.update("declare r integer;begin PRC_UPD_FIVE_PLAN_REPORT_HRD(?,?,?,?,?,?,?,?,?,?,?,r);dbms_output.put_line(r);end;",
					new Object[]{is_achieve_play_ground,add_play_ground,add_play_ground_area,spend_for_add_play_ground,extension_modify_play_ground,area_exten_modify_play_ground,
					spend_for_mod_play_ground,is_add_euqi_gro,spend_euqi_gro_totaL,stage,a_id});
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
