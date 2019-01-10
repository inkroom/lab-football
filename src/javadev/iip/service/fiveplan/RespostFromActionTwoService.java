package javadev.iip.service.fiveplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.util.finder.ClassFinder.Info;

import javadev.iip.service.BaseService;

public class RespostFromActionTwoService extends BaseService{
	/**
	 * 功能描述：第二页的表单的Service
	 * 
	 * @author 李月
	 * @version:1.0
	 * Create Date ：16-08-30
	 */

	//查询语句：
	private static final String SQL_GET_ALL_SMD="select FIVE_PLAN_REPORT.IS_ACHIEVE_MUSIC_ROOM,"+
												"FIVE_PLAN_REPORT.spend_for_music_equi,"+
												"FIVE_PLAN_REPORT.IS_ACHIEVE_MUSIC_EQUI,"+
												"FIVE_PLAN_REPORT.SPEND_FOR_MUSIC_EQUIT,"+
												"FIVE_PLAN_REPORT.IS_ACHIEVE_DRAW_ROOM,"+
												"FIVE_PLAN_REPORT.SPEND_FOR_DRAW_ROOM,"+
												"FIVE_PLAN_REPORT.IS_ACHIEVE_DRAW_EQUI,"+
												"FIVE_PLAN_REPORT.SPEND_FOR_DRAW_EQUI"+
												" FROM FIVE_PLAN_REPORT where "
												+ "o_id=? and TO_CHAR(FP_YEAR,'YYYY')=?  and status=?";
	
	/*
		    
select FIVE_PLAN_REPORT.IS_ACHIEVE_MUSIC_ROOM,FIVE_PLAN_REPORT.spend_for_music_equi,FIVE_PLAN_REPORT.IS_ACHIEVE_MUSIC_EQUI,
												FIVE_PLAN_REPORT.SPEND_FOR_MUSIC_EQUIT,
												FIVE_PLAN_REPORT.IS_ACHIEVE_DRAW_ROOM,
												FIVE_PLAN_REPORT.SPEND_FOR_DRAW_ROOM,
									.			FIVE_PLAN_REPORT.IS_ACHIEVE_DRAW_EQUI,
												FIVE_PLAN_REPORT.SPEND_FOR_DRAW_EQUI,
                        five_plan_report.FP_YEAR
												FROM FIVE_PLAN_REPORT where c_id=(select c_id from CAMPUS_COMMON_DATA where o_id = '8888888888') and TO_CHAR(FP_YEAR,'YYYY')=2015;
              
	*/
	
	
	private static final String SQL_GET_ALL_SMDS="select FIVE_PLAN_REPORT.IS_ACHIEVE_MUSIC_ROOM,"+
			"FIVE_PLAN_REPORT.spend_for_music_equi,"+
			"FIVE_PLAN_REPORT.IS_ACHIEVE_MUSIC_EQUI,"+
			"FIVE_PLAN_REPORT.SPEND_FOR_MUSIC_EQUIT,"+
			"FIVE_PLAN_REPORT.IS_ACHIEVE_DRAW_ROOM,"+
			"FIVE_PLAN_REPORT.SPEND_FOR_DRAW_ROOM,"+
			"FIVE_PLAN_REPORT.IS_ACHIEVE_DRAW_EQUI,"+
			"FIVE_PLAN_REPORT.SPEND_FOR_DRAW_EQUI"+
			" FROM FIVE_PLAN_REPORT where "
			+ "o_id=? and status=?";
	/**
	 * 功能:查询上一年的数据
	 * @return oldYearData
	 * @author 李月
	 */
	public Map<String,Object> getAllOldData(String o_id,String fp_year,String status){
		Map<String, Object> oldYearData = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
	
	//	System.out.println("--------------------------------正在查询全部数据；啦");
		try {
			data = jt.queryForMap(SQL_GET_ALL_SMD,new Object[]{o_id,fp_year,status});
			if(data!= null && data.size()!=0){
				//将去年的数据封装到HashMap中去传到页面去
				oldYearData.put("isAchieveMusicRoom", isAchieve(data.get("IS_ACHIEVE_MUSIC_ROOM")));
				oldYearData.put("spendForAddMusicRoom", data.get("SPEND_FOR_MUSIC_EQUI"));
				oldYearData.put("isAchieveMusicEqui", isAchieve(data.get("IS_ACHIEVE_MUSIC_EQUI")));
				oldYearData.put("spendForMusicEquiment", data.get("SPEND_FOR_MUSIC_EQUIT"));
				oldYearData.put("isAchieveDrawClass", isAchieve(data.get("IS_ACHIEVE_DRAW_ROOM")));
				oldYearData.put("spendForDrawRoom", data.get("SPEND_FOR_DRAW_ROOM"));
				oldYearData.put("isAchieveDrawEqui", isAchieve(data.get("IS_ACHIEVE_DRAW_EQUI")));
				oldYearData.put("spendForDrawEqui", data.get("SPEND_FOR_DRAW_EQUI"));
//				log.info("xixixixixi-----------"+isAchieve(data.get("IS_ACHIEVE_MUSIC_ROOM")));
				return oldYearData;
			}
			else{
				return null;
			}
			
		} catch (Exception e) {
			log.info("异常消息啦++++++++++++++++++++"+e.getMessage());
			return null;
		}
	
	}
	
	
	public Map<String,Object> getAllOldDatas(String o_id,String fp_year,String status){
		Map<String, Object> oldYearData = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
	
	//	System.out.println("--------------------------------正在查询全部数据；啦");
		try {
			data = jt.queryForMap(SQL_GET_ALL_SMDS,new Object[]{o_id,status});
			if(data!= null && data.size()!=0){
				//将去年的数据封装到HashMap中去传到页面去
				oldYearData.put("isAchieveMusicRoom", isAchieve(data.get("IS_ACHIEVE_MUSIC_ROOM")));
				oldYearData.put("spendForAddMusicRoom", data.get("SPEND_FOR_MUSIC_EQUI"));
				oldYearData.put("isAchieveMusicEqui", isAchieve(data.get("IS_ACHIEVE_MUSIC_EQUI")));
				oldYearData.put("spendForMusicEquiment", data.get("SPEND_FOR_MUSIC_EQUIT"));
				oldYearData.put("isAchieveDrawClass", isAchieve(data.get("IS_ACHIEVE_DRAW_ROOM")));
				oldYearData.put("spendForDrawRoom", data.get("SPEND_FOR_DRAW_ROOM"));
				oldYearData.put("isAchieveDrawEqui", isAchieve(data.get("IS_ACHIEVE_DRAW_EQUI")));
				oldYearData.put("spendForDrawEqui", data.get("SPEND_FOR_DRAW_EQUI"));
//				log.info("xixixixixi-----------"+isAchieve(data.get("IS_ACHIEVE_MUSIC_ROOM")));
				return oldYearData;
			}
			else{
				return null;
			}
			
		} catch (Exception e) {
			log.info("异常消息啦++++++++++++++++++++"+e.getMessage());
			return null;
		}
	
	}
	
	
	
	
	
	/**
	 * 功能提交表单数据到数据库里面去
	 * @param isAchieveMusicRoom
	 * @param spendForAddMusicRoom
	 * @param isAchieveMusicEqui
	 * @param spendForMusicEquiment
	 * @param isAchieveDrawClass
	 * @param spendForDrawRoom
	 * @param isAchieveDrawEqui
	 * @param spendForDrawEqui
	 * @return
	 *  @author 李月
	 */
	public boolean uploadForm(String isAchieveMusicRoom,String spendForAddMusicRoom,String isAchieveMusicEqui, 
			String spendForMusicEquiment, String isAchieveDrawClass,
			String spendForDrawRoom, String isAchieveDrawEqui, String spendForDrawEqui,String a_id){

		//传入的858是a_id
		String sql = "declare  r integer;begin PRC_UPD_FIVE_PLAN_REPORT_LY(?,?,?,?,?,?,?,?,?,r); dbms_output.put_line(r);end;";
		try {
			//插入数据库
			jt.update(sql, new Object[]{isAchieveMusicRoom,
					spendForAddMusicRoom,isAchieveMusicEqui,
					spendForMusicEquiment,isAchieveDrawClass,
					spendForDrawRoom, isAchieveDrawEqui,
					spendForDrawEqui,a_id});
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * 功能:判断数据库取出来的是1 还是2 然后转成中文数字
	 * @param object
	 * @return
	 *  @author 李月
	 */
	private String isAchieve(Object object){
		String str = object.toString();
		if(str.equals("1")){
			return "今年新增达标";
		}else if(str.equals("2")){
			return "否";
		}else if(str.equals("3")){
			return "往年已达标";
		}
		else{
			return "";
		}
	}
	
	
	
	/**
	 * 判断上一页是否填完了，如果填完了才能跳到下一页，否则不能进入现在这个页面
	 * @author 李月
	 */
	public boolean isJump(String o_id,String fp_year){
		String sql = "select FIVE_PLAN_REPORT.is_achieve_play_ground,FIVE_PLAN_REPORT.add_play_ground FROM FIVE_PLAN_REPORT where o_id=? and TO_CHAR(FP_YEAR,'YYYY')=?  and status=?";
		
		try {
			Map data = jt.queryForMap(sql,new Object[]{o_id,fp_year,});
			if(data.get("IS_ACHIEVE_PLAY_GROUND") == null){//如果查询结果为空返回true
				
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			log.info("isJump()抛出异常啦啦了"+e.getMessage());
		}
		return true;
	
	}
	
	
	

}
