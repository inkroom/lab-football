package javadev.iip.service.sport;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import javadev.core.Constants;
import javadev.iip.service.BaseService;

public class SportReportBySelfService extends BaseService{

	public void insertData(Map<String, String> map){
		
		System.out.println("=================map===============map=========="+map);

		//通过Session 的到包含用户登陆数据的Map;
		Map loginMsg=(Map) (ActionContext.getContext().getSession().get(Constants.LOGIN_USER));
		
		jt.update("begin PRC_UPD_PHY_SELF_REMARK_GZR(?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?,?,?,?,? "
				+ ",?,?,?,?,?,?); end;"
				,new Object[]{loginMsg.get("o_id"),
						map.get("unitName"),
						map.get("headMaster"),
						map.get("competentPresident"),
						map.get("masterPhone"),
						map.get("educateDirector"),
						map.get("sportsDirector"),
						map.get("directorPhone"),
						map.get("sportsTeacherNum"),
						map.get("sportsTeacherVacancyNum"),
						map.get("teacherEveryCourses"),
						
						map.get("the1GradeNum"),
						map.get("the2GradeNum"),
						map.get("the3GradeNum"),
						map.get("the4GradeNum"),
						map.get("the5GradeNum"),
						map.get("the6GradeNum"),
						map.get("the7GradeNum"),
						map.get("the8GradeNum"),
						map.get("the9GradeNum"),
						
						map.get("the1Grade"),
						map.get("the2Grade"),
						map.get("the3Grade"),
						map.get("the4Grade"),
						map.get("the5Grade"),
						map.get("the6Grade"),
						map.get("the7Grade"),
						map.get("the8Grade"),
						map.get("the9Grade"),
						
						map.get("the1HigherNum"),
						map.get("the2HigherNum"),
						map.get("the3HigherNum"),
						
						map.get("the1Higher"),
						map.get("the2Higher"),
						map.get("the3Higher"),
						
						map.get("item1Num"),
						map.get("item2Num"),
						map.get("item3Num"),
						map.get("item4Num"),
						map.get("item5Num"),
						map.get("item6Num"),
						map.get("item7Num"),
						map.get("item8Num"),
						map.get("item9Num"),
						map.get("item10Num"),
						map.get("item11Num"),
						map.get("item12Num"),
						map.get("item13Num"),
						map.get("item14Num"),
						map.get("item15Num"),
						map.get("item16Num"),
						map.get("item17Num"),
						map.get("item18Num"),
						map.get("item19Num"),
						map.get("item20Num"),
						map.get("item21Num"),
						map.get("item22Num"),
						map.get("item23Num"),
						map.get("item24Num"),
						map.get("item25Num"),
						map.get("item26Num"),
						map.get("item27Num"),
						map.get("item28Num"),
						map.get("item29Num"),
						map.get("item30Num"),
						map.get("item31Num"),
						map.get("item32Num"),
						map.get("item33Num"),
						map.get("item34Num"),
						map.get("item35Num"),
						map.get("item36Num"),
						map.get("item37Num"),
						map.get("item38Num"),
						
						map.get("item1Str"),
						map.get("item2Str"),
						map.get("item3Str"),
						map.get("item4Str"),
						map.get("item5Str"),
						map.get("item6Str"),
						map.get("item7Str"),
						map.get("item8Str"),
						map.get("item9Str"),
						map.get("item10Str"),
						map.get("item11Str"),
						map.get("item12Str"),
						map.get("item13Str"),
						map.get("item14Str"),
						map.get("item15Str"),
						map.get("item16Str"),
						map.get("item17Str"),
						map.get("item18Str"),
						map.get("item19Str"),
						map.get("item20Str"),
						map.get("item21Str"),
						map.get("item22Str"),
						map.get("item23Str"),
						map.get("item24Str"),
						map.get("item25Str"),
						map.get("item26Str"),
						map.get("item27Str"),
						map.get("item28Str"),
						map.get("item29Str"),
						map.get("item30Str"),
						map.get("item31Str"),
						map.get("item32Str"),
						map.get("item33Str"),
						map.get("item34Str"),
						map.get("item35Str"),
						map.get("item36Str"),
						map.get("item37Str"),
						map.get("item38Str"),
						
						map.get("bonusItems"),
						map.get("bonusNum"),
						map.get("totalScore"),
						map.get("selfRatingScale"),
						map.get("c_id")
						});
	}
	
	final static String  GET_SELF_DATA = "SELECT * FROM PHY_SELF_REMARK WHERE O_ID = ? AND STATUS = 1";
	public Map<String, Object> getSelfData(String O_ID){
		try {
			List<Map<String, Object>> list = jt.queryForList(GET_SELF_DATA,O_ID);
			if (list.size() != 1) {
				return null;
			}else {
				return list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
}
