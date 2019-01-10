package javadev.iip.service.fiveplan;

import java.util.Map;
import javadev.iip.service.BaseService;
/**
 * 功能描述：用于 体育、艺术教师培训与教研员配备情况的service
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月1日08：53：24
 */
public class SportsArtTeachEquipService extends BaseService{

	//通过之前登录的账号ID，获得（体育教师培训人次，体育教师培训培训费用，音乐教师培训人次，音乐教师培训费用，美术教师培训人次，美术教师培训费用）

	private static final String SQL_GET_ALL_SMD=  "select FIVE_PLAN_REPORT.PHY_TEACHER_EXER,FIVE_PLAN_REPORT.SPEND_PHY_TEACHER_EXER,FIVE_PLAN_REPORT.MUSIC_TEACHER_EXER,FIVE_PLAN_REPORT.SPEND_MUSIC_TEACHER_EXER,FIVE_PLAN_REPORT.DRAW_TEACHER_EXER,FIVE_PLAN_REPORT.SPEND_DRAW_TEACHER_EXER from FIVE_PLAN_REPORT where o_id =?  and STATUS=1";

	/*
	 *通过登录的ID，和填表的时间返回map,没有信息，就返回null 
	 */private static final String SQL_GET_ALL_OLDSMD= "select FIVE_PLAN_REPORT.PHY_TEACHER_EXER,FIVE_PLAN_REPORT.SPEND_PHY_TEACHER_EXER,FIVE_PLAN_REPORT.MUSIC_TEACHER_EXER,FIVE_PLAN_REPORT.SPEND_MUSIC_TEACHER_EXER,FIVE_PLAN_REPORT.DRAW_TEACHER_EXER,FIVE_PLAN_REPORT.SPEND_DRAW_TEACHER_EXER from FIVE_PLAN_REPORT where o_id =? and TO_CHAR(FP_YEAR,'YYYY')=? and STATUS=2 and STATUS=2";
		private static final String SQL_S = "select person_name,person_phone from FIVE_PLAN_WRITE where O_id=? and status = 1";
		private static final String SQL_INSQL = "update FIVE_PLAN_WRITE set person_name=?,person_phone=? where o_id = ? and status=1";
		
		private static final String SQL_INSERTLAST = "update FIVE_PLAN_REPORT set PHY_TEACHER_EXER=?,SPEND_PHY_TEACHER_EXER=?,MUSIC_TEACHER_EXER=?,SPEND_MUSIC_TEACHER_EXER=?,DRAW_TEACHER_EXER=?,SPEND_DRAW_TEACHER_EXER=?,IS_FINISH_WRITE=1 where o_id=?  and status = 1";
	 
	public Map<String,Object> getAllOldData(String a_id,String fp_year){
		try{
		
			return jt.queryForMap(SQL_GET_ALL_OLDSMD,new Object[]{a_id,fp_year});

		}catch(Exception e ){
		
			return null ;
		}
	}
	public Map<String,Object> getAllNewData(String a_id,String fp_year){
		try{
			
			return jt.queryForMap(SQL_GET_ALL_SMD,new Object[]{a_id});

		}catch(Exception e ){
		
			return null ;
		}
	}

	//通过登录ID 更新数据并返回
	public boolean updateALlDataS(String phy_teacher_exer,
			String spend_phy_teacher_exer,	String music_teacher_exer,
			String spend_music_teacher_exer,
			String draw_teacher_exer,
			String spend_draw_teacher_exer,String a_id){
		try{
			//jt.update("declare r integer;begin PRC_UPD_FIVE_PLAN_REPORT_PB(?,?,?,?,?,?,?,r);dbms_output.put_line(r);end;",new Object[]{phy_teacher_exer,spend_phy_teacher_exer,music_teacher_exer,spend_music_teacher_exer,draw_teacher_exer,spend_draw_teacher_exer,a_id});
			jt.update(SQL_INSERTLAST,new Object[]{phy_teacher_exer,spend_phy_teacher_exer,music_teacher_exer,spend_music_teacher_exer,draw_teacher_exer,spend_draw_teacher_exer,a_id});
			return true ;
		}catch(Exception e){
			return false;
		}
	}
	//查询联系人信息
	//insert into FIVE_PLAN_WRITE(FPW_ID,person_name,person_phone,create_date) values ( sysdate)
	public Map<String,Object> getInformation(String o_id) throws Exception{
		return jt.queryForMap(SQL_S,new Object[]{o_id});
	}
	public boolean updatedata(String name,String phone,String o_id) {
		try{
			jt.update(SQL_INSQL,new Object[]{name,phone,o_id});
			return true;
		}catch(Exception e ){
			e.printStackTrace();
			return false;
		}	
	}
	private static final String UPDATE_IS_FINISH_WRITE = "update FIVE_PLAN_REPORT set IS_FINISH_WRITE=1 where o_id=? and status = 1";
	public boolean updateIsFinishWrite(String o_id){
		try {
			return jt.update(UPDATE_IS_FINISH_WRITE,new Object[]{o_id}) == 1;
		} catch (Exception e) {
			return false;
		}
	}
}
