package javadev.iip.service.sport;

import java.util.HashMap;
import java.util.Map;

import javadev.core.Constants;

import org.codehaus.jackson.map.type.MapLikeType;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.jta.JtaAfterCompletionSynchronization;


import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.classfile.Constant;

import javadev.iip.service.BaseService;
/**
 * 功能描述：用于体育基础数据的service
 * @author 曹旭峰
 * @version 1.0 
 * Create Date: 2016-8-31
 */
public class SportFirstService extends BaseService{
	
	public boolean updataSTAGE(String O_ID,String STAGE){
		try {
			jt.update("begin PRC_UPD_STAGE(?,?); end;",new Object[]{O_ID,STAGE});
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
			

	}
	
	public void insertArtTeacherData(String o_id){
		//log.info("===================1=====================");
		jt.update("call PRC_UPD_ART_REPORT_RATIO_GZR(?)",
			new Object[]{o_id});

		
//		jt.update("declare i integer; begin PRC_UPD_ART_REPORT_RATIO_GZR(?,i); dbms_output.put_line(i); end;",
//				new Object[]{o_id});

	}
	public boolean insertData(Map<String, String> test){
		System.out.println("-------------------------------"+test.get("c_id")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("schoolType")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("numOfStus")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("actualClassNumber")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("enoughPE")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("onehourPE")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("numActivityInClass")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("havaTeacher")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("numFulltimeTeachers")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("numParttimeTeachers")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("sportsTeacherVacancyNum")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("sportsTeacherVacancyRatio")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("ratioOfStudentsToTeachers")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("numPhysicalTeachers")+"----------------------------------------------");
		System.out.println("---------------------------"+test.get("numRecognitionTeachers")+"----------------------------------------------");
		
		
			
		//点击下一步时对数据进行更新;
		jt.update("declare i integer; begin PRC_INS_PHY_REPORT_GZR(?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,i); dbms_output.put_line(i); end;"
						,new Object[]{test.get("o_id"),test.get("numOfStus"), test.get("schoolType"),test.get("actualClassNumber") , test.get("enoughPE"),
								test.get("onehourPE"), test.get("numActivityInClass"), test.get("havaTeacher"), test.get("numFulltimeTeachers"),
								test.get("numParttimeTeachers"), test.get("sportsTeacherVacancyNum"), test.get("numPhysicalTeachers"), test.get("numRecognitionTeachers"),test.get("sportsTeacherVacancyRatio"), test.get("ratioOfStudentsToTeachers"),test.get("o_id"),test.get("c_id")});
				
		
		return false;
		
		
	}
	/***
	 * 搜索自定字段名的数据
	 * @param test
	 * @return 是否搜索成功
	 */
//	public Map<String, Object> selectData(String c_id){
//		
//		String sql = "SELECT stu_total,stage,class_total,hours_total,is_one_hr_daily,is_long_break_activity,havaTeacher,teachers_full_time,teachers_part_time,teachers_required,teachers_trained,teachers_awarded,RATIO_TEA_GAPS,RATIO_TEA_STU"+
//		" from PHY_REPORT where C_ID ="+c_id;
//		Map map = jt.queryForMap(sql);
//		System.out.println("=========================MAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAPMAP==============================="+map);
//		return map;
//	}

}

















 












