package javadev.iip.action.sport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.jfree.chart.labels.IntervalCategoryItemLabelGenerator;
import org.jfree.util.BooleanList;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

/***
 * 功能描述:体育工作自评结果报表
 * 
 * @author 曹旭峰 Create Date: 2016-9-7
 * 
 * 
 * 
 *
 */
public class sportsReportBySelf extends BaseAction {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String stage;

	// 单位全称;
	private String unitName;
	// 校长
	private String headMaster;
	// 主管校长；
	private String competentPresident;
	// 电话（校长）；
	private String masterPhone;
	// 教务主任
	private String educateDirector;
	// 体育主管;
	private String sportsDirector;
	// 电话（主任）
	private String directorPhone;
	// 体育教师数;
	private String sportsTeacherNum;
	// 体育教师缺额人数
	private String sportsTeacherVacancyNum;
	// 体育教师平均周课时;
	private String teacherEveryCourses;

	// 一至九年级个年级学生数;
	private String the1GradeNum;
	private String the2GradeNum;
	private String the3GradeNum;
	private String the4GradeNum;
	private String the5GradeNum;
	private String the6GradeNum;
	private String the7GradeNum;
	private String the8GradeNum;
	private String the9GradeNum;

	// 一至九年级各年级班级数;
	private String the1Grade;
	private String the2Grade;
	private String the3Grade;
	private String the4Grade;
	private String the5Grade;
	private String the6Grade;
	private String the7Grade;
	private String the8Grade;
	private String the9Grade;

	// 高中一至三年纪各年级的学生数;
	private String the1HigherNum;
	private String the2HigherNum;
	private String the3HigherNum;

	// 高中一至三年纪各年级的班级数;
	private String the1Higher;
	private String the2Higher;
	private String the3Higher;

	// 各项指标评估结果的得分;
	private String item1Num;
	private String item2Num;
	private String item3Num;
	private String item4Num;
	private String item5Num;
	private String item6Num;
	private String item7Num;
	private String item8Num;
	private String item9Num;
	private String item10Num;
	private String item11Num;
	private String item12Num;
	private String item13Num;
	private String item14Num;
	private String item15Num;
	private String item16Num;
	private String item17Num;
	private String item18Num;
	private String item19Num;
	private String item20Num;
	private String item21Num;
	private String item22Num;
	private String item23Num;
	private String item24Num;
	private String item25Num;
	private String item26Num;
	private String item27Num;
	private String item28Num;
	private String item29Num;
	private String item30Num;
	private String item31Num;
	private String item32Num;
	private String item33Num;
	private String item34Num;
	private String item35Num;
	private String item36Num;
	private String item37Num;
	private String item38Num;

	// 各项指标评估结果的存在主要问题;
	private String item1Str;
	private String item2Str;
	private String item3Str;
	private String item4Str;
	private String item5Str;
	private String item6Str;
	private String item7Str;
	private String item8Str;
	private String item9Str;
	private String item10Str;
	private String item11Str;
	private String item12Str;
	private String item13Str;
	private String item14Str;
	private String item15Str;
	private String item16Str;
	private String item17Str;
	private String item18Str;
	private String item19Str;
	private String item20Str;
	private String item21Str;
	private String item22Str;
	private String item23Str;
	private String item24Str;
	private String item25Str;
	private String item26Str;
	private String item27Str;
	private String item28Str;
	private String item29Str;
	private String item30Str;
	private String item31Str;
	private String item32Str;
	private String item33Str;
	private String item34Str;
	private String item35Str;
	private String item36Str;
	private String item37Str;
	private String item38Str;

	// 加分项目
	private String bonusItems;
	// 加分分数
	private String bonusNum;
	// 得分总计
	private String totalScore;
	// 自评等级
	private String selfRatingScale;

	// 输出错误信息;
	private String errorInfo;
	
	private String sumOfStus;

	public String getSumOfStus() {
		return sumOfStus;
	}

	public void setSumOfStus(String sumOfStus) {
		this.sumOfStus = sumOfStus;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	private ServiceManager serviceManager;

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void showValue(){
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
			
		setSportsTeacherNum("0");
		// 获取SportFirstAction中的体育老师缺额数
		String o_id = user.get("O_ID").toString();
		Map newMap = getServiceManager().getOtherService().getSportData(o_id);
		if (newMap != null) {
			if(!v.checkEmpty(newMap.get("teachers_full_time"))){
				setSportsTeacherNum(newMap.get("teachers_full_time").toString());
			}
			
		}
	}
	
	
	@Override
	public String execute() throws Exception {

		setToken(getSession().get("token").toString());

		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);

		// 获取SportFirstAction中的体育老师缺额数
		String o_id = user.get("O_ID").toString();
		Map newMap = getServiceManager().getOtherService().getSportData(o_id);
		//sumOfStus = Map.get("0");
		setSumOfStus("0");
		setSportsTeacherVacancyNum("0");
		setSportsTeacherNum("0");
		if (newMap != null) {
			if (!v.checkEmpty(newMap.get("teachers_required"))) {
				setSportsTeacherVacancyNum(newMap.get("teachers_required").toString());
			}
			if(!v.checkEmpty(newMap.get("stu_total"))){
				setSumOfStus(newMap.get("stu_total").toString());
			}
			if(!v.checkEmpty(newMap.get("teachers_full_time"))){
				setSportsTeacherNum(newMap.get("teachers_full_time").toString());
			}
			
			// sportsTeacherVacancyNum
		}
		if(sumOfStus.equals("0")){
			errorInfo = "请先填写体育年度报表！";
		}
		// end
		showValue();
		
		
		Map<String, Object> selfData = getServiceManager().getSportReportBySelfService()
				.getSelfData(user.get("O_ID").toString());
		if (selfData != null) {
			setStage(user.get("STAGE").toString());
			if (!v.checkEmpty(user.get("O_NAME"))) {
				setUnitName(user.get("O_NAME").toString());
			}
			if (!v.checkEmpty(selfData.get("PRESIDENT"))) {
				setHeadMaster(selfData.get("PRESIDENT").toString());
			}

			if (!v.checkEmpty(selfData.get("SUPERVISOR"))) {
				setCompetentPresident(selfData.get("SUPERVISOR").toString());
			}

			if (!v.checkEmpty(selfData.get("SUPERVISOR_PHONE"))) {
				setMasterPhone(selfData.get("SUPERVISOR_PHONE").toString());
			}

			if (!v.checkEmpty(selfData.get("PHY_DIRECTOR"))) {
				setEducateDirector(selfData.get("PHY_DIRECTOR").toString());
			}

			if (!v.checkEmpty(selfData.get("PHY_LEADER"))) {
				setSportsDirector(selfData.get("PHY_LEADER").toString());
			}

			if (!v.checkEmpty(selfData.get("PHY_LEADER_PHONE"))) {
				setDirectorPhone(selfData.get("PHY_LEADER_PHONE").toString());
			}

			if (!v.checkEmpty(selfData.get("TEACHER_TOTAL"))) {
				setSportsTeacherNum(selfData.get("TEACHER_TOTAL").toString());
			}

//			if (!v.checkEmpty(selfData.get("TEACHER_REQUIRED"))) {
//				setSportsTeacherVacancyNum(selfData.get("TEACHER_REQUIRED").toString());
//			}

			if (!v.checkEmpty(selfData.get("TEACHER_AVG_WEEKLY_HOUR"))) {
				setTeacherEveryCourses(selfData.get("TEACHER_AVG_WEEKLY_HOUR").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_ONE"))) {
				setThe1GradeNum(selfData.get("STUDENT_GRADE_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_TWO"))) {
				setThe2GradeNum(selfData.get("STUDENT_GRADE_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_THREE"))) {
				setThe3GradeNum(selfData.get("STUDENT_GRADE_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_FOUR"))) {
				setThe4GradeNum(selfData.get("STUDENT_GRADE_FOUR").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_FIVE"))) {
				setThe5GradeNum(selfData.get("STUDENT_GRADE_FIVE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_SIX"))) {
				setThe6GradeNum(selfData.get("STUDENT_GRADE_SIX").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_SEVEN"))) {
				setThe7GradeNum(selfData.get("STUDENT_GRADE_SEVEN").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_EIGHT"))) {
				setThe8GradeNum(selfData.get("STUDENT_GRADE_EIGHT").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_NINE"))) {
				setThe9GradeNum(selfData.get("STUDENT_GRADE_NINE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_ONE"))) {
				setThe1Grade(selfData.get("CLASS_GRADE_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_TWO"))) {
				setThe2Grade(selfData.get("CLASS_GRADE_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_THREE"))) {
				setThe3Grade(selfData.get("CLASS_GRADE_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_FOUR"))) {
				setThe4Grade(selfData.get("CLASS_GRADE_FOUR").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_FIVE"))) {
				setThe5Grade(selfData.get("CLASS_GRADE_FIVE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_SIX"))) {
				setThe6Grade(selfData.get("CLASS_GRADE_SIX").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_SEVEN"))) {
				setThe7Grade(selfData.get("CLASS_GRADE_SEVEN").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_EIGHT"))) {
				setThe8Grade(selfData.get("CLASS_GRADE_EIGHT").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_NINE"))) {
				setThe9Grade(selfData.get("CLASS_GRADE_NINE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_SENIOR_ONE"))) {
				setThe1Higher(selfData.get("CLASS_SENIOR_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_SENIOR_ONE"))) {
				setThe1HigherNum(selfData.get("STUDENT_SENIOR_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_SENIOR_TWO"))) {
				setThe2Higher(selfData.get("CLASS_SENIOR_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_SENIOR_TWO"))) {
				setThe2HigherNum(selfData.get("STUDENT_SENIOR_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS__SENIOR_THREE"))) {
				setThe3Higher(selfData.get("CLASS__SENIOR_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_SENIOR_THREE"))) {
				setThe3HigherNum(selfData.get("STUDENT_SENIOR_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_LEAD_GROUP"))) {
				setItem1Num(selfData.get("SCORE_LEAD_GROUP").toString());
			}

			if (!v.checkEmpty(selfData.get("QUESTION_LEAD_GROUP"))) {
				setItem1Str(selfData.get("QUESTION_LEAD_GROUP").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_PHY_ADD_SCH_PLAN"))) {
				setItem2Num(selfData.get("SCORE_PHY_ADD_SCH_PLAN").toString());
			}

			if (!v.checkEmpty(selfData.get("QUESTION_PHY_ADD_SCH_PLAN"))) {
				setItem2Str(selfData.get("QUESTION_PHY_ADD_SCH_PLAN").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_ACCIDENTAL_INJURY"))) {
				setItem3Num(selfData.get("SCORE_ACCIDENTAL_INJURY").toString());
			}

			if (!v.checkEmpty(selfData.get("QUESTION_ACCIDENTAL_INJURY"))) {
				setItem3Str(selfData.get("QUESTION_ACCIDENTAL_INJURY").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_PHY_ADD_WORK_DUTY"))) {
				setItem4Num(selfData.get("SCORE_PHY_ADD_WORK_DUTY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_ADD_WORK_DUTY"))) {
				setItem4Str(selfData.get("QUESTION_PHY_ADD_WORK_DUTY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_LEADER_LISTEN"))) {
				setItem5Num(selfData.get("SCORE_LEADER_LISTEN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_LEADER_LISTEN"))) {
				setItem5Str(selfData.get("QUESTION_LEADER_LISTEN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_REALIZE_PHY_HOUR"))) {
				setItem6Num(selfData.get("SCORE_REALIZE_PHY_HOUR").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_REALIZE_PHY_HOUR"))) {
				setItem6Str(selfData.get("QUESTION_REALIZE_PHY_HOUR").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PUBLISH_SUNSHINE_PHY"))) {
				setItem7Num(selfData.get("SCORE_PUBLISH_SUNSHINE_PHY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PUBLISH_SUNSHINE_PHY"))) {
				setItem7Str(selfData.get("QUESTION_PUBLISH_SUNSHINE_PHY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_ACTIVITY"))) {
				setItem8Num(selfData.get("SCORE_PHY_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_ACTIVITY"))) {
				setItem8Str(selfData.get("QUESTION_PHY_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_TEACHING_PLAN"))) {
				setItem9Num(selfData.get("SCORE_PHY_TEACHING_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_TEACHING_PLAN"))) {
				setItem9Str(selfData.get("QUESTION_PHY_TEACHING_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_COURSE_TEACHING"))) {
				setItem10Num(selfData.get("SCORE_COURSE_TEACHING").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_COURSE_TEACHING"))) {
				setItem10Str(selfData.get("QUESTION_COURSE_TEACHING").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_IMPROVE_COURSE_REFORM"))) {
				setItem11Num(selfData.get("SCORE_IMPROVE_COURSE_REFORM").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_IMPROVE_COURSE_REFORM"))) {
				setItem11Str(selfData.get("QUESTION_IMPROVE_COURSE_REFORM").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_CHECK"))) {
				setItem12Num(selfData.get("SCORE_PHY_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_CHECK"))) {
				setItem12Str(selfData.get("QUESTION_PHY_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_SUNSHINE_PHY_PLAN"))) {
				setItem13Num(selfData.get("SCORE_SUNSHINE_PHY_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_SUNSHINE_PHY_PLAN"))) {
				setItem13Str(selfData.get("QUESTION_SUNSHINE_PHY_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_ACTIVITY_ADD_PLAN"))) {
				setItem14Num(selfData.get("SCORE_ACTIVITY_ADD_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_ACTIVITY_ADD_PLAN"))) {
				setItem14Str(selfData.get("QUESTION_ACTIVITY_ADD_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_LONG_BREAK_ACTIVITY"))) {
				setItem15Num(selfData.get("SCORE_LONG_BREAK_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_LONG_BREAK_ACTIVITY"))) {
				setItem15Str(selfData.get("QUESTION_LONG_BREAK_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_SPORTS_MEET"))) {
				setItem16Num(selfData.get("SCORE_SPORTS_MEET").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_SPORTS_MEET"))) {
				setItem16Str(selfData.get("QUESTION_SPORTS_MEET").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_SKILLS_RATIO_85"))) {
				setItem17Num(selfData.get("SCORE_PHY_SKILLS_RATIO_85").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_SKILLS_RATIO_85"))) {
				setItem17Str(selfData.get("QUESTION_PHY_SKILLS_RATIO_85").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_SAFETY"))) {
				setItem18Num(selfData.get("SCORE_PHY_SAFETY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_SAFETY"))) {
				setItem18Str(selfData.get("QUESTION_PHY_SAFETY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_TOTAL"))) {
				setItem19Num(selfData.get("SCORE_TEACHER_TOTAL").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_TOTAL"))) {
				setItem19Str(selfData.get("QUESTION_TEACHER_TOTAL").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHING_REMARK"))) {
				setItem20Num(selfData.get("SCORE_TEACHING_REMARK").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHING_REMARK"))) {
				setItem20Str(selfData.get("QUESTION_TEACHING_REMARK").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_TREATMENT"))) {
				setItem21Num(selfData.get("SCORE_TEACHER_TREATMENT").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_TREATMENT"))) {
				setItem21Str(selfData.get("QUESTION_TEACHER_TREATMENT").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_ACTIVITY_TEST_WORKLOAD"))) {
				setItem22Num(selfData.get("SCORE_ACTIVITY_TEST_WORKLOAD").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_ACT_TEST_WORKD"))) {
				setItem22Str(selfData.get("QUESTION_ACT_TEST_WORKD").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_PREPARE"))) {
				setItem23Num(selfData.get("SCORE_TEACHER_PREPARE").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_PREPARE"))) {
				setItem23Str(selfData.get("QUESTION_TEACHER_PREPARE").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_TRAINED"))) {
				setItem24Num(selfData.get("SCORE_TEACHER_TRAINED").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_TRAINED"))) {
				setItem24Str(selfData.get("QUESTION_TEACHER_TRAINED").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FIELD_EQUIP"))) {
				setItem25Num(selfData.get("SCORE_FIELD_EQUIP").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FIELD_EQUIP"))) {
				setItem25Str(selfData.get("QUESTION_FIELD_EQUIP").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FIELD_STANDARD"))) {
				setItem26Num(selfData.get("SCORE_FIELD_STANDARD").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FIELD_STANDARD"))) {
				setItem26Str(selfData.get("QUESTION_FIELD_STANDARD").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_STUDIO_CHECK"))) {
				setItem27Num(selfData.get("SCORE_STUDIO_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_STUDIO_CHECK"))) {
				setItem27Str(selfData.get("QUESTION_STUDIO_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FIELD_EQIOP_PRINCIPAL"))) {
				setItem28Num(selfData.get("SCORE_FIELD_EQIOP_PRINCIPAL").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FIELD_EQIOP_PRINCIPAL"))) {
				setItem28Str(selfData.get("QUESTION_FIELD_EQIOP_PRINCIPAL").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_VACATION_OPEN_STUDIO"))) {
				setItem29Num(selfData.get("SCORE_VACATION_OPEN_STUDIO").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_VACATION_OPEN_STUDIO"))) {
				setItem29Str(selfData.get("QUESTION_VACATION_OPEN_STUDIO").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FUNDING_REQUIRED"))) {
				setItem30Num(selfData.get("SCORE_FUNDING_REQUIRED").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FUNDING_REQUIRED"))) {
				setItem30Str(selfData.get("QUESTION_FUNDING_REQUIRED").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_PHYSICAL_TEST"))) {
				setItem31Num(selfData.get("SCORE_PHYSICAL_TEST").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHYSICAL_TEST"))) {
				setItem31Str(selfData.get("QUESTION_PHYSICAL_TEST").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_SAVE_TEST_DATA"))) {
				setItem32Num(selfData.get("SCORE_SAVE_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_SAVE_TEST_DATA"))) {
				setItem32Str(selfData.get("QUESTION_SAVE_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_REPORT_TEST_DATA"))) {
				setItem33Num(selfData.get("SCORE_REPORT_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_REPORT_TEST_DATA"))) {
				setItem33Str(selfData.get("QUESTION_REPORT_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_STU_PASS_RATIO_95"))) {
				setItem34Num(selfData.get("SCORE_STU_PASS_RATIO_95").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_STU_PASS_RATIO_95"))) {
				setItem34Str(selfData.get("QUESTION_STU_PASS_RATIO_95").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_STU_GOOD_RATIO_40"))) {
				setItem35Num(selfData.get("SCORE_STU_GOOD_RATIO_40").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_STU_GOOD_RATIO_40"))) {
				setItem35Str(selfData.get("QUESTION_STU_GOOD_RATIO_40").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PUBLISH_TEST_RESULT"))) {
				setItem36Num(selfData.get("SCORE_PUBLISH_TEST_RESULT").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PUBLISH_TEST_RESULT"))) {
				setItem36Str(selfData.get("QUESTION_PUBLISH_TEST_RESULT").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_HEALTH_ADD_ARCHIVES"))) {
				setItem37Num(selfData.get("SCORE_HEALTH_ADD_ARCHIVES").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_HEALTH_ADD_ARCHIVES"))) {
				setItem37Str(selfData.get("QUESTION_HEALTH_ADD_ARCHIVES").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_ANALYZE_TEST_RESULT"))) {
				setItem38Num(selfData.get("SCORE_ANALYZE_TEST_RESULT").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_ANALYZE_TEST_RESULT"))) {
				setItem38Str(selfData.get("QUESTION_ANALYZE_TEST_RESULT").toString());
			}

			if (!v.checkEmpty(selfData.get("PLUS_PROJECT"))) {
				setBonusItems(selfData.get("PLUS_PROJECT").toString());
			}

			if (!v.checkEmpty(selfData.get("PLUS_SCORE"))) {
				setBonusNum(selfData.get("PLUS_SCORE").toString().substring(0, 1));
				
			}

			if (!v.checkEmpty(selfData.get("SCORE_TOTAL"))) {
				setTotalScore(selfData.get("SCORE_TOTAL").toString());
			}

			if (!v.checkEmpty(selfData.get("SELF_REMARK_GRADE"))) {
				// int temp =
				// Integer.parseInt(selfData.get("SELF_REMARK_GRADE"));
				// int temp = (int) selfData.get("SELF_REMARK_GRADE");
				String temp = (String) selfData.get("SELF_REMARK_GRADE");
				if (temp.equals("4")) {
					selfRatingScale = "不合格";
				}
				if (temp.equals("3")) {
					selfRatingScale = "合格";
				}
				if (temp.equals("2")) {
					selfRatingScale = "良好";
				}
				if (temp.equals("1")) {
					selfRatingScale = "优秀";
				}
				setSelfRatingScale(selfRatingScale);
			}

		} else {
			errorInfo = "系统暂未开放！";
			return "noOpen";
		}

		return SUCCESS;
	}

	V v = new V();

	public String updateData() {

		showValue();
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		setToken(getSession().get("token").toString());
		setStage(user.get("STAGE").toString());
		setUnitName(user.get("O_NAME").toString());
		// 校长验证电话号码；
		if (!vlidatePhoneNumber(masterPhone)) {
			errorInfo = "请输入正确的校长电话号码！";
			return "error";
		}
		// 主任验证电话号码；
		if (!vlidatePhoneNumber(directorPhone)) {
			errorInfo = "请输入正确主任的电话号码！";
			return "error";
		}

		// 验证姓名
		if (!isChinsesStr(headMaster)) {
			errorInfo = "姓名必须为2~20为的纯中文或者纯英文，不包含其他字符！";
			return "error";
		}
		if (!isChinsesStr(competentPresident)) {
			errorInfo = "姓名必须为2~20为的纯中文或者纯英文，不包含其他字符！";
			return "error";
		}
		if (!isChinsesStr(educateDirector)) {
			errorInfo = "姓名必须为2~20为的纯中文或者纯英文，不包含其他字符！";
			return "error";
		}
		if (!isChinsesStr(sportsDirector)) {
			errorInfo = "姓名必须为2~20为的纯中文或者纯英文，不包含其他字符！";
			return "error";
		}

		if (!V.isNumeric(sportsTeacherNum) || sportsTeacherNum.length() > 8) {

			errorInfo = "请输入正确的体育教师数量！";
			return "error";
		}
		
		// 获取SportFirstAction中的体育老师缺额数
				String o_id = user.get("O_ID").toString();
				Map newMap = getServiceManager().getOtherService().getSportData(o_id);
				if (newMap != null) {
					//System.out.println("=======================newMap" + newMap.get("teachers_required").toString());
					
					if (!v.checkEmpty(newMap.get("teachers_required"))) {
						setSportsTeacherVacancyNum(newMap.get("teachers_required").toString());
					}
					
					// sportsTeacherVacancyNum
				}
				 
		if (!V.isNumeric(sportsTeacherVacancyNum) || sportsTeacherVacancyNum.length() > 8) {
			
			errorInfo = "Test+请输入正确的体育教师缺额数！";
			return "error";
		}
//		if (!V.isNumeric(teacherEveryCourses) || teacherEveryCourses.length() > 8) {
//
//			errorInfo = "请输入正确的体育教师平均周课时！";
//			return "error";
//		}
		if(!isFloats(teacherEveryCourses)){
			errorInfo = "请输入正确的体育教师平均周课时！";
			return "error";
		}

		if (Integer.parseInt(stage) == 1) {
			the7GradeNum = "0";
			the8GradeNum = "0";
			the9GradeNum = "0";

			the7Grade = "0";
			the8Grade = "0";
			the9Grade = "0";

			the1HigherNum = "0";
			the2HigherNum = "0";
			the3HigherNum = "0";

			the1Higher = "0";
			the2Higher = "0";
			the3Higher = "0";
			if (!V.isNumeric(the1GradeNum) || the1GradeNum.length() > 8 ) {

				errorInfo = "请输入正确一年纪学生数量！";
				return "error";
			}
			if (!V.isNumeric(the2GradeNum) || the2GradeNum.length() > 8 ) {
				errorInfo = "请输入正确二年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the3GradeNum) || the3GradeNum.length() > 8 ) {
				errorInfo = "请输入正确三年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the4GradeNum) || the4GradeNum.length() > 8) {
				errorInfo = "请输入正确四年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the5GradeNum) || the5GradeNum.length() > 8 ) {
				errorInfo = "请输入正确五年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the6GradeNum) || the6GradeNum.length() > 8 ) {
				errorInfo = "请输入正确六年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the1Grade) || the1Grade.length() > 8 ) {

				errorInfo = "请输入正确一年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the2Grade) || the2Grade.length() > 8) {
				errorInfo = "请输入正确二年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the3Grade) || the3Grade.length() > 8 ) {
				errorInfo = "请输入正确三年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the4Grade) || the4Grade.length() > 8 ) {
				errorInfo = "请输入正确四年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the5Grade) || the5Grade.length() > 8 ) {
				errorInfo = "请输入正确五年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the6Grade) || the6Grade.length() > 8 ) {
				errorInfo = "请输入正确六年级班级数量！";
				return "error";
			}
		} else if (Integer.parseInt(stage) == 2) {

			the1HigherNum = "0";
			the2HigherNum = "0";
			the3HigherNum = "0";

			the1Higher = "0";
			the2Higher = "0";
			the3Higher = "0";

			the1GradeNum = "0";
			the2GradeNum = "0";
			the3GradeNum = "0";
			the4GradeNum = "0";
			the5GradeNum = "0";
			the6GradeNum = "0";

			the1Grade = "0";
			the2Grade = "0";
			the3Grade = "0";
			the4Grade = "0";
			the5Grade = "0";
			the6Grade = "0";
			if (!V.isNumeric(the7GradeNum) || the7GradeNum.length() > 8) {
				errorInfo = "请输入正确七年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the8GradeNum) || the8GradeNum.length() > 8 ) {
				errorInfo = "请输入正确八年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the9GradeNum) || the9GradeNum.length() > 8) {
				errorInfo = "请输入正确九年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the7Grade) || the7Grade.length() > 8 ) {
				errorInfo = "请输入正确七年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the8Grade) || the8Grade.length() > 8 ) {
				errorInfo = "请输入正确八年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the9Grade) || the9Grade.length() > 8 ) {
				errorInfo = "请输入正确九年级班级数量！";
				return "error";
			}

		} else if (Integer.parseInt(stage) == 3 || Integer.parseInt(stage) == 4) {
			the7GradeNum = "0";
			the8GradeNum = "0";
			the9GradeNum = "0";

			the7Grade = "0";
			the8Grade = "0";
			the9Grade = "0";

			the1GradeNum = "0";
			the2GradeNum = "0";
			the3GradeNum = "0";
			the4GradeNum = "0";
			the5GradeNum = "0";
			the6GradeNum = "0";

			the1Grade = "0";
			the2Grade = "0";
			the3Grade = "0";
			the4Grade = "0";
			the5Grade = "0";
			the6Grade = "0";

			if (!V.isNumeric(the1Higher) || the1Higher.length() > 8 ) {
				errorInfo = "请输入正确高一班级数量！";
				return "error";
			}
			if (!V.isNumeric(the2Higher) || the2Higher.length() > 8 ) {
				errorInfo = "请输入正确高二班级数量！";
				return "error";
			}
			if (!V.isNumeric(the3Higher) || the3Higher.length() > 8 ) {
				errorInfo = "请输入正确高三班级数量！";
				return "error";
			}
			if (!V.isNumeric(the1HigherNum) || the1HigherNum.length() > 8) {
				errorInfo = "请输入正确高一学生数量！";
				return "error";
			}
			if (!V.isNumeric(the2HigherNum) || the2HigherNum.length() > 8 ) {
				errorInfo = "请输入正确高二学生数量！";
				return "error";
			}
			if (!V.isNumeric(the3HigherNum) || the3HigherNum.length() > 8) {
				errorInfo = "请输入正确高三学生数量！";
				return "error";
			}

		} else if (Integer.parseInt(stage) == 5) {
			the1HigherNum = "0";
			the2HigherNum = "0";
			the3HigherNum = "0";

			the1Higher = "0";
			the2Higher = "0";
			the3Higher = "0";

			if (!V.isNumeric(the1GradeNum) || the1GradeNum.length() > 8 ) {

				errorInfo = "请输入正确一年纪学生数量！";
				return "error";
			}
			if (!V.isNumeric(the2GradeNum) || the2GradeNum.length() > 8 ) {
				errorInfo = "请输入正确二年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the3GradeNum) || the3GradeNum.length() > 8 ) {
				errorInfo = "请输入正确三年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the4GradeNum) || the4GradeNum.length() > 8 ) {
				errorInfo = "请输入正确四年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the5GradeNum) || the5GradeNum.length() > 8 ) {
				errorInfo = "请输入正确五年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the6GradeNum) || the6GradeNum.length() > 8) {
				errorInfo = "请输入正确六年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the7GradeNum) || the7GradeNum.length() > 8) {
				errorInfo = "请输入正确七年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the8GradeNum) || the8GradeNum.length() > 8 ) {
				errorInfo = "请输入正确八年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the9GradeNum) || the9GradeNum.length() > 8) {
				errorInfo = "请输入正确九年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the1Grade) || the1Grade.length() > 8) {

				errorInfo = "请输入正确一年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the2Grade) || the2Grade.length() > 8 ) {
				errorInfo = "请输入正确二年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the3Grade) || the3Grade.length() > 8 ) {
				errorInfo = "请输入正确三年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the4Grade) || the4Grade.length() > 8 ) {
				errorInfo = "请输入正确四年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the5Grade) || the5Grade.length() > 8 ) {
				errorInfo = "请输入正确五年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the6Grade) || the6Grade.length() > 8 ) {
				errorInfo = "请输入正确六年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the7Grade) || the7Grade.length() > 8 ) {
				errorInfo = "请输入正确七年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the8Grade) || the8Grade.length() > 8 ) {
				errorInfo = "请输入正确八年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the9Grade) || the9Grade.length() > 8 ) {
				errorInfo = "请输入正确九年级班级数量！";
				return "error";
			}

		} else if (Integer.parseInt(stage) == 6) {

			if (!V.isNumeric(the1GradeNum) || the1GradeNum.length() > 8 ) {

				errorInfo = "请输入正确一年纪学生数量！";
				return "error";
			}
			if (!V.isNumeric(the2GradeNum) || the2GradeNum.length() > 8 ) {
				errorInfo = "请输入正确二年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the3GradeNum) || the3GradeNum.length() > 8 ) {
				errorInfo = "请输入正确三年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the4GradeNum) || the4GradeNum.length() > 8 ) {
				errorInfo = "请输入正确四年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the5GradeNum) || the5GradeNum.length() > 8) {
				errorInfo = "请输入正确五年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the6GradeNum) || the6GradeNum.length() > 8) {
				errorInfo = "请输入正确六年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the7GradeNum) || the7GradeNum.length() > 8) {
				errorInfo = "请输入正确七年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the8GradeNum) || the8GradeNum.length() > 8 ) {
				errorInfo = "请输入正确八年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the9GradeNum) || the9GradeNum.length() > 8) {
				errorInfo = "请输入正确九年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the1Grade) || the1Grade.length() > 8 ) {

				errorInfo = "请输入正确一年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the2Grade) || the2Grade.length() > 8) {
				errorInfo = "请输入正确二年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the3Grade) || the3Grade.length() > 8 ) {
				errorInfo = "请输入正确三年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the4Grade) || the4Grade.length() > 8 ) {
				errorInfo = "请输入正确四年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the5Grade) || the5Grade.length() > 8) {
				errorInfo = "请输入正确五年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the6Grade) || the6Grade.length() > 8) {
				errorInfo = "请输入正确六年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the7Grade) || the7Grade.length() > 8) {
				errorInfo = "请输入正确七年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the8Grade) || the8Grade.length() > 8 ) {
				errorInfo = "请输入正确八年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the9Grade) || the9Grade.length() > 8 ) {
				errorInfo = "请输入正确九年级班级数量！";
				return "error";
			}

			// -----=
			if (!V.isNumeric(the1Higher) || the1Higher.length() > 8 ) {
				errorInfo = "请输入正确高一班级数量！";
				return "error";
			}
			if (!V.isNumeric(the2Higher) || the2Higher.length() > 8 ) {
				errorInfo = "请输入正确高二班级数量！";
				return "error";
			}
			if (!V.isNumeric(the3Higher) || the3Higher.length() > 8 ) {
				errorInfo = "请输入正确高三班级数量！";
				return "error";
			}
			if (!V.isNumeric(the1HigherNum) || the1HigherNum.length() > 8 ) {
				errorInfo = "请输入正确高一学生数量！";
				return "error";
			}
			if (!V.isNumeric(the2HigherNum) || the2HigherNum.length() > 8) {
				errorInfo = "请输入正确高二学生数量！";
				return "error";
			}
			if (!V.isNumeric(the3HigherNum) || the3HigherNum.length() > 8 ) {
				errorInfo = "请输入正确高三学生数量！";
				return "error";
			}

		} else if (Integer.parseInt(stage) == 7) {

			the1GradeNum = "0";
			the2GradeNum = "0";
			the3GradeNum = "0";
			the4GradeNum = "0";
			the5GradeNum = "0";
			the6GradeNum = "0";

			the1Grade = "0";
			the2Grade = "0";
			the3Grade = "0";
			the4Grade = "0";
			the5Grade = "0";
			the6Grade = "0";
			if (!V.isNumeric(the7GradeNum) || the7GradeNum.length() > 8 ) {
				errorInfo = "请输入正确七年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the8GradeNum) || the8GradeNum.length() > 8 ) {
				errorInfo = "请输入正确八年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the9GradeNum) || the9GradeNum.length() > 8 ) {
				errorInfo = "请输入正确九年级学生数量！";
				return "error";
			}
			if (!V.isNumeric(the7Grade) || the7Grade.length() > 8) {
				errorInfo = "请输入正确七年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the8Grade) || the8Grade.length() > 8) {
				errorInfo = "请输入正确八年级班级数量！";
				return "error";
			}
			if (!V.isNumeric(the9Grade) || the9Grade.length() > 8 ) {
				errorInfo = "请输入正确九年级班级数量！";
				return "error";
			}

			// -----=
			if (!V.isNumeric(the1Higher) || the1Higher.length() > 8 ) {
				errorInfo = "请输入正确高一班级数量！";
				return "error";
			}
			if (!V.isNumeric(the2Higher) || the2Higher.length() > 8) {
				errorInfo = "请输入正确高二班级数量！";
				return "error";
			}
			if (!V.isNumeric(the3Higher) || the3Higher.length() > 8) {
				errorInfo = "请输入正确高三班级数量！";
				return "error";
			}
			if (!V.isNumeric(the1HigherNum) || the1HigherNum.length() > 8 ) {
				errorInfo = "请输入正确高一学生数量！";
				return "error";
			}
			if (!V.isNumeric(the2HigherNum) || the2HigherNum.length() > 8) {
				errorInfo = "请输入正确高二学生数量！";
				return "error";
			}
			if (!V.isNumeric(the3HigherNum) || the3HigherNum.length() > 8) {
				errorInfo = "请输入正确高三学生数量！";
				return "error";
			}
		}

		// if (!V.isNumeric(the1GradeNum) || the1GradeNum.length() > 8) {
		//
		// errorInfo = "请输入正确一年纪学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the2GradeNum) || the2GradeNum.length() > 8) {
		// errorInfo = "请输入正确二年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the3GradeNum) || the3GradeNum.length() > 8) {
		// errorInfo = "请输入正确三年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the4GradeNum) || the4GradeNum.length() > 8) {
		// errorInfo = "请输入正确四年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the5GradeNum) || the5GradeNum.length() > 8) {
		// errorInfo = "请输入正确五年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the6GradeNum) || the6GradeNum.length() > 8) {
		// errorInfo = "请输入正确六年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the7GradeNum) || the7GradeNum.length() > 8) {
		// errorInfo = "请输入正确七年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the8GradeNum) || the8GradeNum.length() > 8) {
		// errorInfo = "请输入正确八年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the9GradeNum) || the9GradeNum.length() > 8) {
		// errorInfo = "请输入正确九年级学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the1Grade) || the1Grade.length() > 8) {
		//
		// errorInfo = "请输入正确一年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the2Grade) || the2Grade.length() > 8) {
		// errorInfo = "请输入正确二年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the3Grade) || the3Grade.length() > 8) {
		// errorInfo = "请输入正确三年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the4Grade) || the4Grade.length() > 8) {
		// errorInfo = "请输入正确四年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the5Grade) || the5Grade.length() > 8) {
		// errorInfo = "请输入正确五年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the6Grade) || the6Grade.length() > 8) {
		// errorInfo = "请输入正确六年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the7Grade) || the7Grade.length() > 8) {
		// errorInfo = "请输入正确七年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the8Grade) || the8Grade.length() > 8) {
		// errorInfo = "请输入正确八年级班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the9Grade) || the9Grade.length() > 8) {
		// errorInfo = "请输入正确九年级班级数量！";
		// return "error";
		// }
		//
		// // -----=
		// if (!V.isNumeric(the1Higher) || the1Higher.length() > 8) {
		// errorInfo = "请输入正确高一班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the2Higher) || the2Higher.length() > 8) {
		// errorInfo = "请输入正确高二班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the3Higher) || the3Higher.length() > 8) {
		// errorInfo = "请输入正确高三班级数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the1HigherNum) || the1HigherNum.length() > 8) {
		// errorInfo = "请输入正确高一学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the2HigherNum) || the2HigherNum.length() > 8) {
		// errorInfo = "请输入正确高二学生数量！";
		// return "error";
		// }
		// if (!V.isNumeric(the3HigherNum) || the3HigherNum.length() > 8) {
		// errorInfo = "请输入正确高三学生数量！";
		// return "error";
		// }
		
		// 验证加分项目;
		if (!ScoreNum(bonusItems.trim())) {
			
			errorInfo = "请输入正确的加分项目内容！";
			return "error";
		}
		//
		if (V.isDouble(item1Num) || !V.isMax(item1Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}

		if (V.isDouble(item2Num) || !V.isMax(item2Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item3Num) || !V.isMax(item3Num, 1)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item4Num) || !V.isMax(item4Num, 1)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item5Num) || !V.isMax(item5Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item6Num) || !V.isMax(item6Num, 7)) {
			errorInfo = "请重新输入，得分在0到7之间！";
			return "error";
		}
		if (V.isDouble(item7Num) || !V.isMax(item7Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item8Num) || !V.isMax(item8Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
			return "error";
		}
		if (V.isDouble(item9Num) || !V.isMax(item9Num, 4)) {
			errorInfo = "请重新输入，得分在0到4之间！";
			return "error";
		}
		if (V.isDouble(item10Num) || !V.isMax(item10Num, 5)) {
			errorInfo = "请重新输入，得分在0到5之间！";
			return "error";
		}
		if (V.isDouble(item11Num) || !V.isMax(item11Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
			return "error";
		}
		if (V.isDouble(item12Num) || !V.isMax(item12Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
		}
		if (V.isDouble(item13Num) || !V.isMax(item13Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item14Num) || !V.isMax(item14Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item15Num) || !V.isMax(item15Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
			return "error";
		}
		if (V.isDouble(item16Num) || !V.isMax(item16Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
			return "error";
		}
		if (V.isDouble(item17Num) || !V.isMax(item17Num, 4)) {
			errorInfo = "请重新输入，得分在0到4之间！";
			return "error";
		}
		if (V.isDouble(item18Num) || !V.isMax(item18Num, 1)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item19Num) || !V.isMax(item19Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
			return "error";
		}
		if (V.isDouble(item20Num) || !V.isMax(item20Num, 3)) {
			errorInfo = "请重新输入，得分在0到3之间！";
			return "error";
		}
		if (V.isDouble(item21Num) || !V.isMax(item21Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item22Num) || !V.isMax(item22Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item23Num) || !V.isMax(item23Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item24Num) || !V.isMax(item24Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item25Num) || !V.isMax(item25Num, 1)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item26Num) || !V.isMax(item26Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item27Num) || !V.isMax(item27Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item28Num) || !V.isMax(item28Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item29Num) || !V.isMax(item29Num, 4)) {
			errorInfo = "请重新输入，得分在0到4之间！";
			return "error";
		}
		if (V.isDouble(item30Num) || !V.isMax(item30Num, 5)) {
			errorInfo = "请重新输入，得分在0到5之间！";
			return "error";
		}
		if (V.isDouble(item31Num) || !V.isMax(item31Num, 3)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item32Num) || !V.isMax(item32Num, 1)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item33Num) || !V.isMax(item33Num, 1)) {
			errorInfo = "请重新输入，得分在0到1之间！";
			return "error";
		}
		if (V.isDouble(item34Num) || !V.isMax(item34Num, 5)) {
			errorInfo = "请重新输入，得分在0到5之间！";
			return "error";
		}
		if (V.isDouble(item35Num) || !V.isMax(item35Num, 4)) {
			errorInfo = "请重新输入，得分在0到4之间！";
			return "error";
		}
		if (V.isDouble(item36Num) || !V.isMax(item36Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item37Num) || !V.isMax(item37Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}
		if (V.isDouble(item38Num) || !V.isMax(item38Num, 2)) {
			errorInfo = "请重新输入，得分在0到2之间！";
			return "error";
		}

		// 验证加分分数;

		if (bonusItems == null || bonusNum == null || bonusNum.equals("")) {
			bonusNum = "0";
		}

		if (!V.isNumeric(bonusNum) || !bonusNum.equals("") || bonusNum != null) {

			try {
				int tem = Integer.parseInt(bonusNum);
				if (tem != 0) {
					if (!V.isMax(bonusNum, 8)) {
						errorInfo = "你的输入不符合规范，必须为0~8的正数！";
						return "error";
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				errorInfo = "请输入规范数字";
				return "error";
			}
		}

		if (!V.isChinese(item1Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item2Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item3Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item4Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item5Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item6Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item7Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item8Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item9Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item10Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item11Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item12Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item13Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item14Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item15Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item16Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item17Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item18Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item19Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item20Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item21Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item22Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item23Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item24Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item25Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item26Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item27Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item28Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item29Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item30Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item31Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item32Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item33Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item34Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item35Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item36Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item37Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}
		if (!V.isChinese(item38Str)) {
			errorInfo = "你的输入不符合规范，不能为全英文！";
			return "error";
		}

		// 获得数据中的已存在的数据;
		Map<String, Object> oldUser = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);

		Map<String, Object> oldData = getServiceManager().getSportReportBySelfService()
				.getSelfData(oldUser.get("O_ID").toString());
		// 新获取的数据;
		Map<String, String> newData = putMsg();

		Iterator<String> newIter = newData.keySet().iterator();
		while (newIter.hasNext()) {
			String newKey = (String) newIter.next();
			if (!newData.get(newKey).equals(oldData.get(newKey))) {
				break;
			}
		}

		errorInfo = "填写完成！";
		serviceManager.getSportReportBySelfService().insertData(newData);
		return SUCCESS;
	}

	/***
	 * 验证字符串问全中文;
	 */
	private boolean isChinsesStr(String str) {
		String regex = "(([\u4E00-\u9FA5]{2,20})|([a-zA-Z]{2,20}))";
		if (!Pattern.matches(regex, str)) {
			return false;
		}

		return true;
	}

	/***
	 * 验证加分项目s
	 */
	private boolean ScoreNum(String str) {
//		String regex = "(([\u4E00-\u9FA5]{2,50})|([a-zA-Z]{2,50}))";
//		if (str.equals("") || str.length() == 0) {
//			return true;
//		}
//		if (!Pattern.matches(regex, str)) {
//			return false;
//		}
		if(str.length() > 30){
			return false;
		}

		return true;
	}

	/***
	 * 验证电话号码
	 */
	private Boolean vlidatePhoneNumber(String str) {

		if (isNum(str) && str.length() < 21) {
			return true;
		} else {
			return false;
		}

	}

	private static boolean isNum(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/***
	 * 验证评估指标内容(要点)得分;
	 */
	private String vlidateItemNum(String str, int maxInt) {
		// 将String 转化为Int;
		Integer it = new Integer(str);
		int i = it.intValue();

		if (!V.isNumeric(str)) {
			return "success";
		} else {
			if (i > maxInt) {
				return "error";
			} else {
				return "success";
			}
		}

	}

	/***
	 * 将从表单得到的数据存入map中;
	 * 
	 * @return Map<String,String>
	 */
	public Map<String, String> putMsg() {

		// 获取C_ID;
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);

		String o_id = user.get("O_ID").toString();
		String c_id = getSession().get(Constants.LOGIN_USER_C_ID).toString();
		// 计算项目得分的总和；
		float counts = Float.valueOf(item1Num) + Float.valueOf(item2Num) + Float.valueOf(item3Num)
				+ Float.valueOf(item4Num) + Float.valueOf(item5Num) + Float.valueOf(item6Num) + Float.valueOf(item7Num)
				+ Float.valueOf(item8Num) + Float.valueOf(item9Num) + Float.valueOf(item10Num)
				+ Float.valueOf(item11Num) + Float.valueOf(item12Num) + Float.valueOf(item13Num)
				+ Float.valueOf(item14Num) + Float.valueOf(item15Num) + Float.valueOf(item16Num)
				+ Float.valueOf(item17Num) + Float.valueOf(item18Num) + Float.valueOf(item19Num)
				+ Float.valueOf(item20Num) + Float.valueOf(item21Num) + Float.valueOf(item22Num)
				+ Float.valueOf(item23Num) + Float.valueOf(item24Num) + Float.valueOf(item25Num)
				+ Float.valueOf(item26Num) + Float.valueOf(item27Num) + Float.valueOf(item28Num)
				+ Float.valueOf(item29Num) + Float.valueOf(item30Num) + Float.valueOf(item31Num)
				+ Float.valueOf(item32Num) + Float.valueOf(item33Num) + Float.valueOf(item34Num)
				+ Float.valueOf(item35Num) + Float.valueOf(item36Num) + Float.valueOf(item37Num)
				+ Float.valueOf(item38Num);
		if (bonusNum != null) {
			totalScore = (counts + Float.valueOf(bonusNum)) + "";
		} else {
			totalScore = counts + "";

		}

	
		
		// 自评等级在存入数据库之前昨相应的转换;
		// int temp = Integer.parseInt(totalScore);
		float temp = Float.valueOf(totalScore);
		if (temp < 60) {
			selfRatingScale = 4 + "";

		} else if (temp >= 60 && temp <= 74) {
			selfRatingScale = 3 + "";
		} else if (temp >= 75 && temp < 89) {
			selfRatingScale = 2 + "";
		} else {
			selfRatingScale = 1 + "";
		}
		Map<String, String> map = new HashMap<>();

		// 体育自评表单校长、主任基本信息数据;
		map.put("unitName", unitName);
		map.put("headMaster", headMaster);
		map.put("competentPresident", competentPresident);
		map.put("masterPhone", masterPhone);
		map.put("educateDirector", educateDirector);
		map.put("sportsDirector", sportsDirector);
		map.put("directorPhone", directorPhone);
		map.put("sportsTeacherNum", sportsTeacherNum);
		map.put("sportsTeacherVacancyNum", sportsTeacherVacancyNum);
		map.put("teacherEveryCourses", teacherEveryCourses);

		// 1~9年级学生数;
		map.put("the1GradeNum", the1GradeNum);
		map.put("the2GradeNum", the2GradeNum);
		map.put("the3GradeNum", the3GradeNum);
		map.put("the4GradeNum", the4GradeNum);
		map.put("the5GradeNum", the5GradeNum);
		map.put("the6GradeNum", the6GradeNum);
		map.put("the7GradeNum", the7GradeNum);
		map.put("the8GradeNum", the8GradeNum);
		map.put("the9GradeNum", the9GradeNum);

		// 1~9年纪班级数;
		map.put("the1Grade", the1Grade);
		map.put("the2Grade", the2Grade);
		map.put("the3Grade", the3Grade);
		map.put("the4Grade", the4Grade);
		map.put("the5Grade", the5Grade);
		map.put("the6Grade", the6Grade);
		map.put("the7Grade", the7Grade);
		map.put("the8Grade", the8Grade);
		map.put("the9Grade", the9Grade);

		// 高中一至三年级各级学生数;
		map.put("the1HigherNum", the1HigherNum);
		map.put("the2HigherNum", the2HigherNum);
		map.put("the3HigherNum", the3HigherNum);

		// 高中一至三年级各级班级数;
		map.put("the1Higher", the1Higher);
		map.put("the2Higher", the2Higher);
		map.put("the3Higher", the3Higher);

		// 各项指标评估得分；
		map.put("item1Num", item1Num);
		map.put("item2Num", item2Num);
		map.put("item3Num", item3Num);
		map.put("item4Num", item4Num);
		map.put("item5Num", item5Num);
		map.put("item6Num", item6Num);
		map.put("item7Num", item7Num);
		map.put("item8Num", item8Num);
		map.put("item9Num", item9Num);
		map.put("item10Num", item10Num);
		map.put("item11Num", item11Num);
		map.put("item12Num", item12Num);
		map.put("item13Num", item13Num);
		map.put("item14Num", item14Num);
		map.put("item15Num", item15Num);
		map.put("item16Num", item16Num);
		map.put("item17Num", item17Num);
		map.put("item18Num", item18Num);
		map.put("item19Num", item19Num);
		map.put("item20Num", item20Num);
		map.put("item21Num", item21Num);
		map.put("item22Num", item22Num);
		map.put("item23Num", item23Num);
		map.put("item24Num", item24Num);
		map.put("item25Num", item25Num);
		map.put("item26Num", item26Num);
		map.put("item27Num", item27Num);
		map.put("item28Num", item28Num);
		map.put("item29Num", item29Num);
		map.put("item30Num", item30Num);
		map.put("item31Num", item31Num);
		map.put("item32Num", item32Num);
		map.put("item33Num", item33Num);
		map.put("item34Num", item34Num);
		map.put("item35Num", item35Num);
		map.put("item36Num", item36Num);
		map.put("item37Num", item37Num);
		map.put("item38Num", item38Num);

		// 各项指标评定存在的主要问题;
		map.put("item1Str", item1Str);
		map.put("item2Str", item2Str);
		map.put("item3Str", item3Str);
		map.put("item4Str", item4Str);
		map.put("item5Str", item5Str);
		map.put("item6Str", item6Str);
		map.put("item7Str", item7Str);
		map.put("item8Str", item8Str);
		map.put("item9Str", item9Str);
		map.put("item10Str", item10Str);
		map.put("item11Str", item11Str);
		map.put("item12Str", item12Str);
		map.put("item13Str", item13Str);
		map.put("item14Str", item14Str);
		map.put("item15Str", item15Str);
		map.put("item16Str", item16Str);
		map.put("item17Str", item17Str);
		map.put("item18Str", item18Str);
		map.put("item19Str", item19Str);
		map.put("item20Str", item20Str);
		map.put("item21Str", item21Str);
		map.put("item22Str", item22Str);
		map.put("item23Str", item23Str);
		map.put("item24Str", item24Str);
		map.put("item25Str", item25Str);
		map.put("item26Str", item26Str);
		map.put("item27Str", item27Str);
		map.put("item28Str", item28Str);
		map.put("item29Str", item29Str);
		map.put("item30Str", item30Str);
		map.put("item31Str", item31Str);
		map.put("item32Str", item32Str);
		map.put("item33Str", item33Str);
		map.put("item34Str", item34Str);
		map.put("item35Str", item35Str);
		map.put("item36Str", item36Str);
		map.put("item37Str", item37Str);
		map.put("item38Str", item38Str);
		System.out.println(" YYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
		System.out.println("CCCCCCCCCCCCCCCCCCCCCC"+bonusItems);
		System.out.println(" YYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
		// 加分项目;
		map.put("bonusItems", bonusItems);
		// 加分分数;
		map.put("bonusNum", bonusNum);
		// 总计的得分;
		map.put("totalScore", totalScore);
		// 自评等级;
		map.put("selfRatingScale", selfRatingScale);
		// 存入C_ID;
		map.put("o_id", o_id);
		map.put("c_id", c_id);
		return map;
	}
	/**
	 * 功能的描述: 判断是否是小数以及数字位数小于等于8
	 * @param str
	 * @return
	 */
	public boolean isFloats(String str) {

		String regex = "^([1-9][0-9]{1,11}(.[0-9]{1,4}))|([0-9](.[0-9]{1,4}))|([1-9][0-9]*)|([0-9])$";
		if (str != null && str.length() > 0 && str.length() < 14 && v.comparePattern(str, regex)
				&& v.judgeSize(-1, 100000000, str)) {

			return true;
		} else {

			return false;
		}
	}
	
	
	public String sportSelfView(){
		setToken(getSession().get("token").toString());

		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);

		// 获取SportFirstAction中的体育老师缺额数
		String o_id = user.get("O_ID").toString();
		Map newMap = getServiceManager().getOtherService().getSportData(o_id);
		
		
		
		//sumOfStus = Map.get("0");
		setSumOfStus("0");
		setSportsTeacherVacancyNum("0");
		setSportsTeacherNum("0");
		if (newMap != null) {
			if (!v.checkEmpty(newMap.get("teachers_required"))) {
				setSportsTeacherVacancyNum(newMap.get("teachers_required").toString());
			}
			if(!v.checkEmpty(newMap.get("stu_total"))){
				setSumOfStus(newMap.get("stu_total").toString());
			}
			if(!v.checkEmpty(newMap.get("teachers_full_time"))){
				setSportsTeacherNum(newMap.get("teachers_full_time").toString());
			}
			
			// sportsTeacherVacancyNum
		}
		if(sumOfStus.equals("0")){
			errorInfo = "请先填写体育年度报表！";
		}
		// end
		showValue();
		
		
		Map<String, Object> selfData = getServiceManager().getSportReportBySelfService()
				.getSelfData(user.get("O_ID").toString());
		if (selfData == null) {
			errorInfo = "当前系统未开放！";
			return "noOpen";
		}
		
		
		
		
		if (selfData != null) {
			setStage(user.get("STAGE").toString());
			if (!v.checkEmpty(user.get("O_NAME"))) {
				setUnitName(user.get("O_NAME").toString());
			}
			if (!v.checkEmpty(selfData.get("PRESIDENT"))) {
				setHeadMaster(selfData.get("PRESIDENT").toString());
			}

			if (!v.checkEmpty(selfData.get("SUPERVISOR"))) {
				setCompetentPresident(selfData.get("SUPERVISOR").toString());
			}

			if (!v.checkEmpty(selfData.get("SUPERVISOR_PHONE"))) {
				setMasterPhone(selfData.get("SUPERVISOR_PHONE").toString());
			}

			if (!v.checkEmpty(selfData.get("PHY_DIRECTOR"))) {
				setEducateDirector(selfData.get("PHY_DIRECTOR").toString());
			}

			if (!v.checkEmpty(selfData.get("PHY_LEADER"))) {
				setSportsDirector(selfData.get("PHY_LEADER").toString());
			}

			if (!v.checkEmpty(selfData.get("PHY_LEADER_PHONE"))) {
				setDirectorPhone(selfData.get("PHY_LEADER_PHONE").toString());
			}

			if (!v.checkEmpty(selfData.get("TEACHER_TOTAL"))) {
				setSportsTeacherNum(selfData.get("TEACHER_TOTAL").toString());
			}

//			if (!v.checkEmpty(selfData.get("TEACHER_REQUIRED"))) {
//				setSportsTeacherVacancyNum(selfData.get("TEACHER_REQUIRED").toString());
//			}

			if (!v.checkEmpty(selfData.get("TEACHER_AVG_WEEKLY_HOUR"))) {
				setTeacherEveryCourses(selfData.get("TEACHER_AVG_WEEKLY_HOUR").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_ONE"))) {
				setThe1GradeNum(selfData.get("STUDENT_GRADE_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_TWO"))) {
				setThe2GradeNum(selfData.get("STUDENT_GRADE_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_THREE"))) {
				setThe3GradeNum(selfData.get("STUDENT_GRADE_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_FOUR"))) {
				setThe4GradeNum(selfData.get("STUDENT_GRADE_FOUR").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_FIVE"))) {
				setThe5GradeNum(selfData.get("STUDENT_GRADE_FIVE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_SIX"))) {
				setThe6GradeNum(selfData.get("STUDENT_GRADE_SIX").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_SEVEN"))) {
				setThe7GradeNum(selfData.get("STUDENT_GRADE_SEVEN").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_EIGHT"))) {
				setThe8GradeNum(selfData.get("STUDENT_GRADE_EIGHT").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_GRADE_NINE"))) {
				setThe9GradeNum(selfData.get("STUDENT_GRADE_NINE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_ONE"))) {
				setThe1Grade(selfData.get("CLASS_GRADE_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_TWO"))) {
				setThe2Grade(selfData.get("CLASS_GRADE_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_THREE"))) {
				setThe3Grade(selfData.get("CLASS_GRADE_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_FOUR"))) {
				setThe4Grade(selfData.get("CLASS_GRADE_FOUR").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_FIVE"))) {
				setThe5Grade(selfData.get("CLASS_GRADE_FIVE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_SIX"))) {
				setThe6Grade(selfData.get("CLASS_GRADE_SIX").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_SEVEN"))) {
				setThe7Grade(selfData.get("CLASS_GRADE_SEVEN").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_EIGHT"))) {
				setThe8Grade(selfData.get("CLASS_GRADE_EIGHT").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_GRADE_NINE"))) {
				setThe9Grade(selfData.get("CLASS_GRADE_NINE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_SENIOR_ONE"))) {
				setThe1Higher(selfData.get("CLASS_SENIOR_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_SENIOR_ONE"))) {
				setThe1HigherNum(selfData.get("STUDENT_SENIOR_ONE").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS_SENIOR_TWO"))) {
				setThe2Higher(selfData.get("CLASS_SENIOR_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_SENIOR_TWO"))) {
				setThe2HigherNum(selfData.get("STUDENT_SENIOR_TWO").toString());
			}

			if (!v.checkEmpty(selfData.get("CLASS__SENIOR_THREE"))) {
				setThe3Higher(selfData.get("CLASS__SENIOR_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("STUDENT_SENIOR_THREE"))) {
				setThe3HigherNum(selfData.get("STUDENT_SENIOR_THREE").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_LEAD_GROUP"))) {
				setItem1Num(selfData.get("SCORE_LEAD_GROUP").toString());
			}

			if (!v.checkEmpty(selfData.get("QUESTION_LEAD_GROUP"))) {
				setItem1Str(selfData.get("QUESTION_LEAD_GROUP").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_PHY_ADD_SCH_PLAN"))) {
				setItem2Num(selfData.get("SCORE_PHY_ADD_SCH_PLAN").toString());
			}

			if (!v.checkEmpty(selfData.get("QUESTION_PHY_ADD_SCH_PLAN"))) {
				setItem2Str(selfData.get("QUESTION_PHY_ADD_SCH_PLAN").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_ACCIDENTAL_INJURY"))) {
				setItem3Num(selfData.get("SCORE_ACCIDENTAL_INJURY").toString());
			}

			if (!v.checkEmpty(selfData.get("QUESTION_ACCIDENTAL_INJURY"))) {
				setItem3Str(selfData.get("QUESTION_ACCIDENTAL_INJURY").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_PHY_ADD_WORK_DUTY"))) {
				setItem4Num(selfData.get("SCORE_PHY_ADD_WORK_DUTY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_ADD_WORK_DUTY"))) {
				setItem4Str(selfData.get("QUESTION_PHY_ADD_WORK_DUTY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_LEADER_LISTEN"))) {
				setItem5Num(selfData.get("SCORE_LEADER_LISTEN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_LEADER_LISTEN"))) {
				setItem5Str(selfData.get("QUESTION_LEADER_LISTEN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_REALIZE_PHY_HOUR"))) {
				setItem6Num(selfData.get("SCORE_REALIZE_PHY_HOUR").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_REALIZE_PHY_HOUR"))) {
				setItem6Str(selfData.get("QUESTION_REALIZE_PHY_HOUR").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PUBLISH_SUNSHINE_PHY"))) {
				setItem7Num(selfData.get("SCORE_PUBLISH_SUNSHINE_PHY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PUBLISH_SUNSHINE_PHY"))) {
				setItem7Str(selfData.get("QUESTION_PUBLISH_SUNSHINE_PHY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_ACTIVITY"))) {
				setItem8Num(selfData.get("SCORE_PHY_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_ACTIVITY"))) {
				setItem8Str(selfData.get("QUESTION_PHY_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_TEACHING_PLAN"))) {
				setItem9Num(selfData.get("SCORE_PHY_TEACHING_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_TEACHING_PLAN"))) {
				setItem9Str(selfData.get("QUESTION_PHY_TEACHING_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_COURSE_TEACHING"))) {
				setItem10Num(selfData.get("SCORE_COURSE_TEACHING").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_COURSE_TEACHING"))) {
				setItem10Str(selfData.get("QUESTION_COURSE_TEACHING").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_IMPROVE_COURSE_REFORM"))) {
				setItem11Num(selfData.get("SCORE_IMPROVE_COURSE_REFORM").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_IMPROVE_COURSE_REFORM"))) {
				setItem11Str(selfData.get("QUESTION_IMPROVE_COURSE_REFORM").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_CHECK"))) {
				setItem12Num(selfData.get("SCORE_PHY_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_CHECK"))) {
				setItem12Str(selfData.get("QUESTION_PHY_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_SUNSHINE_PHY_PLAN"))) {
				setItem13Num(selfData.get("SCORE_SUNSHINE_PHY_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_SUNSHINE_PHY_PLAN"))) {
				setItem13Str(selfData.get("QUESTION_SUNSHINE_PHY_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_ACTIVITY_ADD_PLAN"))) {
				setItem14Num(selfData.get("SCORE_ACTIVITY_ADD_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_ACTIVITY_ADD_PLAN"))) {
				setItem14Str(selfData.get("QUESTION_ACTIVITY_ADD_PLAN").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_LONG_BREAK_ACTIVITY"))) {
				setItem15Num(selfData.get("SCORE_LONG_BREAK_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_LONG_BREAK_ACTIVITY"))) {
				setItem15Str(selfData.get("QUESTION_LONG_BREAK_ACTIVITY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_SPORTS_MEET"))) {
				setItem16Num(selfData.get("SCORE_SPORTS_MEET").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_SPORTS_MEET"))) {
				setItem16Str(selfData.get("QUESTION_SPORTS_MEET").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_SKILLS_RATIO_85"))) {
				setItem17Num(selfData.get("SCORE_PHY_SKILLS_RATIO_85").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_SKILLS_RATIO_85"))) {
				setItem17Str(selfData.get("QUESTION_PHY_SKILLS_RATIO_85").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PHY_SAFETY"))) {
				setItem18Num(selfData.get("SCORE_PHY_SAFETY").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHY_SAFETY"))) {
				setItem18Str(selfData.get("QUESTION_PHY_SAFETY").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_TOTAL"))) {
				setItem19Num(selfData.get("SCORE_TEACHER_TOTAL").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_TOTAL"))) {
				setItem19Str(selfData.get("QUESTION_TEACHER_TOTAL").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHING_REMARK"))) {
				setItem20Num(selfData.get("SCORE_TEACHING_REMARK").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHING_REMARK"))) {
				setItem20Str(selfData.get("QUESTION_TEACHING_REMARK").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_TREATMENT"))) {
				setItem21Num(selfData.get("SCORE_TEACHER_TREATMENT").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_TREATMENT"))) {
				setItem21Str(selfData.get("QUESTION_TEACHER_TREATMENT").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_ACTIVITY_TEST_WORKLOAD"))) {
				setItem22Num(selfData.get("SCORE_ACTIVITY_TEST_WORKLOAD").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_ACT_TEST_WORKD"))) {
				setItem22Str(selfData.get("QUESTION_ACT_TEST_WORKD").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_PREPARE"))) {
				setItem23Num(selfData.get("SCORE_TEACHER_PREPARE").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_PREPARE"))) {
				setItem23Str(selfData.get("QUESTION_TEACHER_PREPARE").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_TEACHER_TRAINED"))) {
				setItem24Num(selfData.get("SCORE_TEACHER_TRAINED").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_TEACHER_TRAINED"))) {
				setItem24Str(selfData.get("QUESTION_TEACHER_TRAINED").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FIELD_EQUIP"))) {
				setItem25Num(selfData.get("SCORE_FIELD_EQUIP").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FIELD_EQUIP"))) {
				setItem25Str(selfData.get("QUESTION_FIELD_EQUIP").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FIELD_STANDARD"))) {
				setItem26Num(selfData.get("SCORE_FIELD_STANDARD").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FIELD_STANDARD"))) {
				setItem26Str(selfData.get("QUESTION_FIELD_STANDARD").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_STUDIO_CHECK"))) {
				setItem27Num(selfData.get("SCORE_STUDIO_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_STUDIO_CHECK"))) {
				setItem27Str(selfData.get("QUESTION_STUDIO_CHECK").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FIELD_EQIOP_PRINCIPAL"))) {
				setItem28Num(selfData.get("SCORE_FIELD_EQIOP_PRINCIPAL").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FIELD_EQIOP_PRINCIPAL"))) {
				setItem28Str(selfData.get("QUESTION_FIELD_EQIOP_PRINCIPAL").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_VACATION_OPEN_STUDIO"))) {
				setItem29Num(selfData.get("SCORE_VACATION_OPEN_STUDIO").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_VACATION_OPEN_STUDIO"))) {
				setItem29Str(selfData.get("QUESTION_VACATION_OPEN_STUDIO").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_FUNDING_REQUIRED"))) {
				setItem30Num(selfData.get("SCORE_FUNDING_REQUIRED").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_FUNDING_REQUIRED"))) {
				setItem30Str(selfData.get("QUESTION_FUNDING_REQUIRED").toString());
			}

			if (!v.checkEmpty(selfData.get("SCORE_PHYSICAL_TEST"))) {
				setItem31Num(selfData.get("SCORE_PHYSICAL_TEST").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PHYSICAL_TEST"))) {
				setItem31Str(selfData.get("QUESTION_PHYSICAL_TEST").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_SAVE_TEST_DATA"))) {
				setItem32Num(selfData.get("SCORE_SAVE_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_SAVE_TEST_DATA"))) {
				setItem32Str(selfData.get("QUESTION_SAVE_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_REPORT_TEST_DATA"))) {
				setItem33Num(selfData.get("SCORE_REPORT_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_REPORT_TEST_DATA"))) {
				setItem33Str(selfData.get("QUESTION_REPORT_TEST_DATA").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_STU_PASS_RATIO_95"))) {
				setItem34Num(selfData.get("SCORE_STU_PASS_RATIO_95").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_STU_PASS_RATIO_95"))) {
				setItem34Str(selfData.get("QUESTION_STU_PASS_RATIO_95").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_STU_GOOD_RATIO_40"))) {
				setItem35Num(selfData.get("SCORE_STU_GOOD_RATIO_40").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_STU_GOOD_RATIO_40"))) {
				setItem35Str(selfData.get("QUESTION_STU_GOOD_RATIO_40").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_PUBLISH_TEST_RESULT"))) {
				setItem36Num(selfData.get("SCORE_PUBLISH_TEST_RESULT").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_PUBLISH_TEST_RESULT"))) {
				setItem36Str(selfData.get("QUESTION_PUBLISH_TEST_RESULT").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_HEALTH_ADD_ARCHIVES"))) {
				setItem37Num(selfData.get("SCORE_HEALTH_ADD_ARCHIVES").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_HEALTH_ADD_ARCHIVES"))) {
				setItem37Str(selfData.get("QUESTION_HEALTH_ADD_ARCHIVES").toString());
			}
			if (!v.checkEmpty(selfData.get("SCORE_ANALYZE_TEST_RESULT"))) {
				setItem38Num(selfData.get("SCORE_ANALYZE_TEST_RESULT").toString());
			}
			if (!v.checkEmpty(selfData.get("QUESTION_ANALYZE_TEST_RESULT"))) {
				setItem38Str(selfData.get("QUESTION_ANALYZE_TEST_RESULT").toString());
			}

			if (!v.checkEmpty(selfData.get("PLUS_PROJECT"))) {
				setBonusItems(selfData.get("PLUS_PROJECT").toString());
			}

			if (!v.checkEmpty(selfData.get("PLUS_SCORE"))) {
				setBonusNum(selfData.get("PLUS_SCORE").toString().substring(0, 1));
				
			}

			if (!v.checkEmpty(selfData.get("SCORE_TOTAL"))) {
				setTotalScore(selfData.get("SCORE_TOTAL").toString());
			}

			if (!v.checkEmpty(selfData.get("SELF_REMARK_GRADE"))) {
				// int temp =
				// Integer.parseInt(selfData.get("SELF_REMARK_GRADE"));
				// int temp = (int) selfData.get("SELF_REMARK_GRADE");
				String temp = (String) selfData.get("SELF_REMARK_GRADE");
				if (temp.equals("4")) {
					selfRatingScale = "不合格";
				}
				if (temp.equals("3")) {
					selfRatingScale = "合格";
				}
				if (temp.equals("2")) {
					selfRatingScale = "良好";
				}
				if (temp.equals("1")) {
					selfRatingScale = "优秀";
				}
				setSelfRatingScale(selfRatingScale);
			}

		} else {
			errorInfo = "系统暂未开放！";
			return "noOpen";
		}

		return SUCCESS;
	}

	public String getUnitName() {
		return unitName;
	}

	public String getThe1Higher() {
		return the1Higher;
	}

	public void setThe1Higher(String the1Higher) {
		this.the1Higher = the1Higher;
	}

	public String getThe2Higher() {
		return the2Higher;
	}

	public void setThe2Higher(String the2Higher) {
		this.the2Higher = the2Higher;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getHeadMaster() {
		return headMaster;
	}

	public void setHeadMaster(String headMaster) {
		this.headMaster = headMaster;
	}

	public String getCompetentPresident() {
		return competentPresident;
	}

	public void setCompetentPresident(String competentPresident) {
		this.competentPresident = competentPresident;
	}

	public String getMasterPhone() {
		return masterPhone;
	}

	public void setMasterPhone(String masterPhone) {
		this.masterPhone = masterPhone;
	}

	public String getEducateDirector() {
		return educateDirector;
	}

	public void setEducateDirector(String educateDirector) {
		this.educateDirector = educateDirector;
	}

	public String getSportsDirector() {
		return sportsDirector;
	}

	public void setSportsDirector(String sportsDirector) {
		this.sportsDirector = sportsDirector;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public String getSportsTeacherNum() {
		return sportsTeacherNum;
	}

	public void setSportsTeacherNum(String sportsTeacherNum) {
		this.sportsTeacherNum = sportsTeacherNum;
	}

	public String getSportsTeacherVacancyNum() {
		return sportsTeacherVacancyNum;
	}

	public void setSportsTeacherVacancyNum(String sportsTeacherVacancyNum) {
		this.sportsTeacherVacancyNum = sportsTeacherVacancyNum;
	}

	public String getTeacherEveryCourses() {
		return teacherEveryCourses;
	}

	public void setTeacherEveryCourses(String teacherEveryCourses) {
		this.teacherEveryCourses = teacherEveryCourses;
	}

	public String getThe1GradeNum() {
		return the1GradeNum;
	}

	public void setThe1GradeNum(String the1GradeNum) {
		this.the1GradeNum = the1GradeNum;
	}

	public String getThe2GradeNum() {
		return the2GradeNum;
	}

	public void setThe2GradeNum(String the2GradeNum) {
		this.the2GradeNum = the2GradeNum;
	}

	public String getThe3GradeNum() {
		return the3GradeNum;
	}

	public void setThe3GradeNum(String the3GradeNum) {
		this.the3GradeNum = the3GradeNum;
	}

	public String getThe4GradeNum() {
		return the4GradeNum;
	}

	public void setThe4GradeNum(String the4GradeNum) {
		this.the4GradeNum = the4GradeNum;
	}

	public String getThe5GradeNum() {
		return the5GradeNum;
	}

	public void setThe5GradeNum(String the5GradeNum) {
		this.the5GradeNum = the5GradeNum;
	}

	public String getThe6GradeNum() {
		return the6GradeNum;
	}

	public void setThe6GradeNum(String the6GradeNum) {
		this.the6GradeNum = the6GradeNum;
	}

	public String getThe7GradeNum() {
		return the7GradeNum;
	}

	public void setThe7GradeNum(String the7GradeNum) {
		this.the7GradeNum = the7GradeNum;
	}

	public String getThe8GradeNum() {
		return the8GradeNum;
	}

	public void setThe8GradeNum(String the8GradeNum) {
		this.the8GradeNum = the8GradeNum;
	}

	public String getThe9GradeNum() {
		return the9GradeNum;
	}

	public void setThe9GradeNum(String the9GradeNum) {
		this.the9GradeNum = the9GradeNum;
	}

	public String getThe1Grade() {
		return the1Grade;
	}

	public void setThe1Grade(String the1Grade) {
		this.the1Grade = the1Grade;
	}

	public String getThe2Grade() {
		return the2Grade;
	}

	public void setThe2Grade(String the2Grade) {
		this.the2Grade = the2Grade;
	}

	public String getThe3Grade() {
		return the3Grade;
	}

	public void setThe3Grade(String the3Grade) {
		this.the3Grade = the3Grade;
	}

	public String getThe4Grade() {
		return the4Grade;
	}

	public void setThe4Grade(String the4Grade) {
		this.the4Grade = the4Grade;
	}

	public String getThe5Grade() {
		return the5Grade;
	}

	public void setThe5Grade(String the5Grade) {
		this.the5Grade = the5Grade;
	}

	public String getThe6Grade() {
		return the6Grade;
	}

	public void setThe6Grade(String the6Grade) {
		this.the6Grade = the6Grade;
	}

	public String getThe7Grade() {
		return the7Grade;
	}

	public void setThe7Grade(String the7Grade) {
		this.the7Grade = the7Grade;
	}

	public String getThe8Grade() {
		return the8Grade;
	}

	public void setThe8Grade(String the8Grade) {
		this.the8Grade = the8Grade;
	}

	public String getThe9Grade() {
		return the9Grade;
	}

	public void setThe9Grade(String the9Grade) {
		this.the9Grade = the9Grade;
	}

	public String getThe1HigherNum() {
		return the1HigherNum;
	}

	public void setThe1HigherNum(String the1HigherNum) {
		this.the1HigherNum = the1HigherNum;
	}

	public String getThe2HigherNum() {
		return the2HigherNum;
	}

	public void setThe2HigherNum(String the2HigherNum) {
		this.the2HigherNum = the2HigherNum;
	}

	public String getThe3HigherNum() {
		return the3HigherNum;
	}

	public void setThe3HigherNum(String the3HigherNum) {
		this.the3HigherNum = the3HigherNum;
	}

	public String getThe3Higher() {
		return the3Higher;
	}

	public void setThe3Higher(String the3Higher) {
		this.the3Higher = the3Higher;
	}

	public String getItem1Num() {
		return item1Num;
	}

	public void setItem1Num(String item1Num) {
		this.item1Num = item1Num;
	}

	public String getItem2Num() {
		return item2Num;
	}

	public void setItem2Num(String item2Num) {
		this.item2Num = item2Num;
	}

	public String getItem3Num() {
		return item3Num;
	}

	public void setItem3Num(String item3Num) {
		this.item3Num = item3Num;
	}

	public String getItem4Num() {
		return item4Num;
	}

	public void setItem4Num(String item4Num) {
		this.item4Num = item4Num;
	}

	public String getItem5Num() {
		return item5Num;
	}

	public void setItem5Num(String item5Num) {
		this.item5Num = item5Num;
	}

	public String getItem6Num() {
		return item6Num;
	}

	public void setItem6Num(String item6Num) {
		this.item6Num = item6Num;
	}

	public String getItem7Num() {
		return item7Num;
	}

	public void setItem7Num(String item7Num) {
		this.item7Num = item7Num;
	}

	public String getItem8Num() {
		return item8Num;
	}

	public void setItem8Num(String item8Num) {
		this.item8Num = item8Num;
	}

	public String getItem9Num() {
		return item9Num;
	}

	public void setItem9Num(String item9Num) {
		this.item9Num = item9Num;
	}

	public String getItem10Num() {
		return item10Num;
	}

	public void setItem10Num(String item10Num) {
		this.item10Num = item10Num;
	}

	public String getItem11Num() {
		return item11Num;
	}

	public void setItem11Num(String item11Num) {
		this.item11Num = item11Num;
	}

	public String getItem12Num() {
		return item12Num;
	}

	public void setItem12Num(String item12Num) {
		this.item12Num = item12Num;
	}

	public String getItem13Num() {
		return item13Num;
	}

	public void setItem13Num(String item13Num) {
		this.item13Num = item13Num;
	}

	public String getItem14Num() {
		return item14Num;
	}

	public void setItem14Num(String item14Num) {
		this.item14Num = item14Num;
	}

	public String getItem15Num() {
		return item15Num;
	}

	public void setItem15Num(String item15Num) {
		this.item15Num = item15Num;
	}

	public String getItem16Num() {
		return item16Num;
	}

	public void setItem16Num(String item16Num) {
		this.item16Num = item16Num;
	}

	public String getItem17Num() {
		return item17Num;
	}

	public void setItem17Num(String item17Num) {
		this.item17Num = item17Num;
	}

	public String getItem18Num() {
		return item18Num;
	}

	public void setItem18Num(String item18Num) {
		this.item18Num = item18Num;
	}

	public String getItem19Num() {
		return item19Num;
	}

	public void setItem19Num(String item19Num) {
		this.item19Num = item19Num;
	}

	public String getItem20Num() {
		return item20Num;
	}

	public void setItem20Num(String item20Num) {
		this.item20Num = item20Num;
	}

	public String getItem21Num() {
		return item21Num;
	}

	public void setItem21Num(String item21Num) {
		this.item21Num = item21Num;
	}

	public String getItem22Num() {
		return item22Num;
	}

	public void setItem22Num(String item22Num) {
		this.item22Num = item22Num;
	}

	public String getItem23Num() {
		return item23Num;
	}

	public void setItem23Num(String item23Num) {
		this.item23Num = item23Num;
	}

	public String getItem24Num() {
		return item24Num;
	}

	public void setItem24Num(String item24Num) {
		this.item24Num = item24Num;
	}

	public String getItem25Num() {
		return item25Num;
	}

	public void setItem25Num(String item25Num) {
		this.item25Num = item25Num;
	}

	public String getItem26Num() {
		return item26Num;
	}

	public void setItem26Num(String item26Num) {
		this.item26Num = item26Num;
	}

	public String getItem27Num() {
		return item27Num;
	}

	public void setItem27Num(String item27Num) {
		this.item27Num = item27Num;
	}

	public String getItem28Num() {
		return item28Num;
	}

	public void setItem28Num(String item28Num) {
		this.item28Num = item28Num;
	}

	public String getItem29Num() {
		return item29Num;
	}

	public void setItem29Num(String item29Num) {
		this.item29Num = item29Num;
	}

	public String getItem30Num() {
		return item30Num;
	}

	public void setItem30Num(String item30Num) {
		this.item30Num = item30Num;
	}

	public String getItem31Num() {
		return item31Num;
	}

	public void setItem31Num(String item31Num) {
		this.item31Num = item31Num;
	}

	public String getItem32Num() {
		return item32Num;
	}

	public void setItem32Num(String item32Num) {
		this.item32Num = item32Num;
	}

	public String getItem33Num() {
		return item33Num;
	}

	public void setItem33Num(String item33Num) {
		this.item33Num = item33Num;
	}

	public String getItem34Num() {
		return item34Num;
	}

	public void setItem34Num(String item34Num) {
		this.item34Num = item34Num;
	}

	public String getItem35Num() {
		return item35Num;
	}

	public void setItem35Num(String item35Num) {
		this.item35Num = item35Num;
	}

	public String getItem36Num() {
		return item36Num;
	}

	public void setItem36Num(String item36Num) {
		this.item36Num = item36Num;
	}

	public String getItem37Num() {
		return item37Num;
	}

	public void setItem37Num(String item37Num) {
		this.item37Num = item37Num;
	}

	public String getItem38Num() {
		return item38Num;
	}

	public void setItem38Num(String item38Num) {
		this.item38Num = item38Num;
	}

	public String getItem1Str() {
		return item1Str;
	}

	public void setItem1Str(String item1Str) {
		this.item1Str = item1Str;
	}

	public String getItem2Str() {
		return item2Str;
	}

	public void setItem2Str(String item2Str) {
		this.item2Str = item2Str;
	}

	public String getItem3Str() {
		return item3Str;
	}

	public void setItem3Str(String item3Str) {
		this.item3Str = item3Str;
	}

	public String getItem4Str() {
		return item4Str;
	}

	public void setItem4Str(String item4Str) {
		this.item4Str = item4Str;
	}

	public String getItem5Str() {
		return item5Str;
	}

	public void setItem5Str(String item5Str) {
		this.item5Str = item5Str;
	}

	public String getItem6Str() {
		return item6Str;
	}

	public void setItem6Str(String item6Str) {
		this.item6Str = item6Str;
	}

	public String getItem7Str() {
		return item7Str;
	}

	public void setItem7Str(String item7Str) {
		this.item7Str = item7Str;
	}

	public String getItem8Str() {
		return item8Str;
	}

	public void setItem8Str(String item8Str) {
		this.item8Str = item8Str;
	}

	public String getItem9Str() {
		return item9Str;
	}

	public void setItem9Str(String item9Str) {
		this.item9Str = item9Str;
	}

	public String getItem10Str() {
		return item10Str;
	}

	public void setItem10Str(String item10Str) {
		this.item10Str = item10Str;
	}

	public String getItem11Str() {
		return item11Str;
	}

	public void setItem11Str(String item11Str) {
		this.item11Str = item11Str;
	}

	public String getItem12Str() {
		return item12Str;
	}

	public void setItem12Str(String item12Str) {
		this.item12Str = item12Str;
	}

	public String getItem13Str() {
		return item13Str;
	}

	public void setItem13Str(String item13Str) {
		this.item13Str = item13Str;
	}

	public String getItem14Str() {
		return item14Str;
	}

	public void setItem14Str(String item14Str) {
		this.item14Str = item14Str;
	}

	public String getItem15Str() {
		return item15Str;
	}

	public void setItem15Str(String item15Str) {
		this.item15Str = item15Str;
	}

	public String getItem16Str() {
		return item16Str;
	}

	public void setItem16Str(String item16Str) {
		this.item16Str = item16Str;
	}

	public String getItem17Str() {
		return item17Str;
	}

	public void setItem17Str(String item17Str) {
		this.item17Str = item17Str;
	}

	public String getItem18Str() {
		return item18Str;
	}

	public void setItem18Str(String item18Str) {
		this.item18Str = item18Str;
	}

	public String getItem19Str() {
		return item19Str;
	}

	public void setItem19Str(String item19Str) {
		this.item19Str = item19Str;
	}

	public String getItem20Str() {
		return item20Str;
	}

	public void setItem20Str(String item20Str) {
		this.item20Str = item20Str;
	}

	public String getItem21Str() {
		return item21Str;
	}

	public void setItem21Str(String item21Str) {
		this.item21Str = item21Str;
	}

	public String getItem22Str() {
		return item22Str;
	}

	public void setItem22Str(String item22Str) {
		this.item22Str = item22Str;
	}

	public String getItem23Str() {
		return item23Str;
	}

	public void setItem23Str(String item23Str) {
		this.item23Str = item23Str;
	}

	public String getItem24Str() {
		return item24Str;
	}

	public void setItem24Str(String item24Str) {
		this.item24Str = item24Str;
	}

	public String getItem25Str() {
		return item25Str;
	}

	public void setItem25Str(String item25Str) {
		this.item25Str = item25Str;
	}

	public String getItem26Str() {
		return item26Str;
	}

	public void setItem26Str(String item26Str) {
		this.item26Str = item26Str;
	}

	public String getItem27Str() {
		return item27Str;
	}

	public void setItem27Str(String item27Str) {
		this.item27Str = item27Str;
	}

	public String getItem28Str() {
		return item28Str;
	}

	public void setItem28Str(String item28Str) {
		this.item28Str = item28Str;
	}

	public String getItem29Str() {
		return item29Str;
	}

	public void setItem29Str(String item29Str) {
		this.item29Str = item29Str;
	}

	public String getItem30Str() {
		return item30Str;
	}

	public void setItem30Str(String item30Str) {
		this.item30Str = item30Str;
	}

	public String getItem31Str() {
		return item31Str;
	}

	public void setItem31Str(String item31Str) {
		this.item31Str = item31Str;
	}

	public String getItem32Str() {
		return item32Str;
	}

	public void setItem32Str(String item32Str) {
		this.item32Str = item32Str;
	}

	public String getItem33Str() {
		return item33Str;
	}

	public void setItem33Str(String item33Str) {
		this.item33Str = item33Str;
	}

	public String getItem34Str() {
		return item34Str;
	}

	public void setItem34Str(String item34Str) {
		this.item34Str = item34Str;
	}

	public String getItem35Str() {
		return item35Str;
	}

	public void setItem35Str(String item35Str) {
		this.item35Str = item35Str;
	}

	public String getItem36Str() {
		return item36Str;
	}

	public void setItem36Str(String item36Str) {
		this.item36Str = item36Str;
	}

	public String getItem37Str() {
		return item37Str;
	}

	public void setItem37Str(String item37Str) {
		this.item37Str = item37Str;
	}

	public String getItem38Str() {
		return item38Str;
	}

	public void setItem38Str(String item38Str) {
		this.item38Str = item38Str;
	}

	public String getBonusItems() {
		return bonusItems;
	}

	public void setBonusItems(String bonusItems) {
		this.bonusItems = bonusItems;
	}

	public String getBonusNum() {
		return bonusNum;
	}

	public void setBonusNum(String bonusNum) {
		this.bonusNum = bonusNum;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getSelfRatingScale() {
		return selfRatingScale;
	}

	public void setSelfRatingScale(String selfRatingScale) {
		this.selfRatingScale = selfRatingScale;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
