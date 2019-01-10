
 
package javadev.iip.service.fiveplan;


import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;

import javadev.iip.service.BaseService;



/**
 *<p>音乐，美术，体育老师添加离职净增以及增长率的service类</p>
 *@author 王树浩
 *@version 1.0
 */

public class PhyMusicDrawTeacherService extends BaseService{
	/**
	 *  添加体育，音乐，美术老师离职净增以及增长率
	 * @author 王树浩
	 * @param retire_phy_teacher    退休和离职体育老师
	 * @param growth_phy_teacher    净增体育老师
	 * @param retire_music_teacher  退休和离职音乐老师
	 * @param growth_music_teacher  净增音乐老师
	 * @param retire_draw_teacher   退休和离职美术老师
	 * @param growth_draw_teacher   净增美术老师
	 * @param add_new_phy_teachers_full   新增体育老师
	 * @param add_new_music_teachers_full   新增音乐老师
	 * @param add_new_draw_teachers_full   新增美术老师
	 * @return 
	 */
	public boolean addPhyMusicDraw(String retire_phy_teacher,String growth_phy_teacher,String retire_music_teacher,String growth_music_teacher,String retire_draw_teacher
			,String growth_draw_teacher,String add_new_phy_teachers_full,String add_new_music_teachers_full,String add_new_draw_teachers_full,String a_id){
		
		
				try{
					
				if(jt.update("declare  r integer;begin PRC_UPD_FIVE_PLAN_REPORT_WSH(?,?,?,?,?,?,?,?,?,?,r);dbms_output.put_line(r); end;",
						new Object[]{retire_phy_teacher,growth_phy_teacher,retire_music_teacher,growth_music_teacher,retire_draw_teacher,growth_draw_teacher,add_new_phy_teachers_full,add_new_music_teachers_full,add_new_draw_teachers_full,a_id})==1){
					return true;
				}else{
					return false;
				}
				}catch(Exception e){
					return false;
				}
			
	}
	/**
	 * 通过ft_id向数据库添加新增老师数据 
	 * @author 王树浩
	 * @param t_name 老师姓名
	 * @param type 老师职责
	 * @param t_age 老师年龄
	 * @param file_no 老师文件编号
	 * @param t_major 老师专业
	 * @return
	 */
	public boolean addTeacherMsg(String fp_id,String t_name,String type,String t_age,String file_no,String t_major,String create_by){
		System.out.println("create_by"+create_by);
			try{
			if (jt.update("declare r varchar2(10); begin PRC_INS_TEACHER_MSG_WSH(?,?,?,?,?,?,?,r); dbms_output.put_line(r);end;",new Object[]{t_name,type,t_age,file_no,t_major,fp_id,create_by})==1) {
				return true;
			}else {
				return false;
			}
			}catch(Exception e){
				return false;
			}
		
		
	}
	//通过fp_id和日期 在数据库中查询体育老师信息
	private String SQL_GET_PHY_TM_ID_BY_TM_ID="select TYPE,T_AGE,T_NAME,T_MAJOR,FILE_NO,TM_ID from TEACHER_MSG where FP_ID=? AND TO_CHAR(CREATE_DATE,'YYYY')=? AND TYPE=? AND STATUS=1";
	/**
	 * 通过fp_id和日期 在数据库中查询老师信息
	 * @author 王树浩
	 * @return
	 */
	public List<Map<String, Object>> selectTeacherMsg(String fp_id,String CREATE_DATE,String type){
		//Map<String, Object> map=new HashMap<String, Object>();
		try{
		return jt.queryForList(SQL_GET_PHY_TM_ID_BY_TM_ID, new Object[] { fp_id,CREATE_DATE,type});
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	 // 通过fp_year和a_id 查询 去年的音乐，美术，体育老师添加离职净增以及增长率
	private String SQL_GET_PHYMUSICDRAW_BY_FP_YEARANDA_ID="select RETIRE_PHY_TEACHER,GROWTH_PHY_TEACHER,RETIRE_MUSIC_TEACHER,GROWTH_MUSIC_TEACHER,RETIRE_DRAW_TEACHER,GROWTH_DRAW_TEACHER,ADD_NEW_PHY_TEACHERS_FULL,ADD_NEW_MUSIC_TEACHERS_FULL,ADD_NEW_DRAW_TEACHERS_FULL from FIVE_PLAN_REPORT where o_id=? and TO_CHAR(FP_YEAR,'YYYY')=? AND STATUS=?";
	private String SQL_GET_PHYMUSICDRAW_BY_FP_YEARANDA_IDS="select RETIRE_PHY_TEACHER,GROWTH_PHY_TEACHER,RETIRE_MUSIC_TEACHER,GROWTH_MUSIC_TEACHER,RETIRE_DRAW_TEACHER,GROWTH_DRAW_TEACHER,ADD_NEW_PHY_TEACHERS_FULL,ADD_NEW_MUSIC_TEACHERS_FULL,ADD_NEW_DRAW_TEACHERS_FULL from FIVE_PLAN_REPORT where o_id=?  AND STATUS=?";

	/**
	 * 通过fp_year和a_id 查询 去年的音乐，美术，体育老师添加离职净增以及增长率
	 * @author 王树浩
	 * @param fp_year
	 * @param a_id
	 * @return
	 */
	public Map<String, Object> selectPhyMusicDraw(String fp_year,String o_id,String STATUS){
		try{
			return jt.queryForMap(SQL_GET_PHYMUSICDRAW_BY_FP_YEARANDA_ID,new Object[]{o_id,fp_year,STATUS});
		}catch(Exception e){
			return null;
			
		}
		}
	public Map<String, Object> selectPhyMusicDraws(String fp_year,String o_id,String STATUS){
		try{
			return jt.queryForMap(SQL_GET_PHYMUSICDRAW_BY_FP_YEARANDA_IDS,new Object[]{o_id,STATUS});
		}catch(Exception e){
			return null;
			
		}
		}
	private String SQL_GET_FP_id_BY_FP_YEARANDA_ID="select FP_ID from FIVE_PLAN_REPORT where o_id=?  AND STATUS=?";
	/**
	 * 通过 o_id 和 FP_Year查询 FP_id
	 * @author 王树浩
	 * @param o_id
	 * @param Fp_Year
	 * @return
	 */
	public Map<String, Object> selectFp_id(String o_id,String fp_year,String STATUS){
		try {
			return jt.queryForMap(SQL_GET_FP_id_BY_FP_YEARANDA_ID,new Object[]{o_id,STATUS});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		
	}
	/**
	 * 更新数据库中的数据，修改状态位。
	 * @author 王树浩
	 * @param tm_id
	 * @param o_id
	 */
	public void delete(int tm_id,String o_id){
		jt.update("declare begin PRC_UPD_DEL_TM(?,?); end;",new Object[]{o_id,tm_id});
	}
	/**
	 * 查询fp_id 对应的TM_ID
	 * @author 王树浩
	 * @return
	 */
	private String SQL_GET_TM_id_BY_FP_id="SELECT TM_ID FROM TEACHER_MSG WHERE FP_ID=?";
	public List<Map<String, Object>> selectTM_ID(int fp_id){
		try{
		return jt.queryForList(SQL_GET_TM_id_BY_FP_id,new Object[]{fp_id});
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}
	//查询当前年份
	private String SQL_GET_FP_YEAR_BY_STATUS="select distinct year from report_record where status = 1";
	/**
	 * 查询当前年份
	 * @author 王树浩
	 * @return
	 */
	public Map<String, Object> selectFP_YEAR(){
		try{
		return jt.queryForMap(SQL_GET_FP_YEAR_BY_STATUS);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	private String SQL_GET_ID_CARD="select file_no from TEACHER_MSG WHERE STATUS=1 AND file_no=?";
	public  String selectId_Card(String file_no){
		try{
			return jt.queryForMap(SQL_GET_ID_CARD, new Object[]{file_no}).toString();
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
}
