package javadev.iip.action.sport;

import java.util.Map;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

public class sportFormTable extends BaseAction {

	// 学校级别
	// "1">普通初中
	// "2">普通高中
	// "3">职业高中
	// "4">九年一贯制学校
	// "5">十二年一贯制学校
	// "6">完全中学
	private String schoolType;
	// 在校生人数
	private String numOfStus;
	// 实际班级数量
	private String actualClassNumber;
	// 体育课是否开足
	private String enoughPE;
	// 落实每天一小时锻炼数
	private String onehourPE;
	// 应配教师数
	private String havaTeacher;
	// 组织大课件体育活动数；
	private String numActivityInClass;
	// 专职体育教室人数；
	private String numFulltimeTeachers;
	// 兼职体育教师人数；
	private String numParttimeTeachers;
	// 体育教师缺额比
	private String sportsTeacherVacancyRatio;
	// 体育教师缺额数；
	private String sportsTeacherVacancyNum;
	// 体育教师师生比；
	private String ratioOfStudentsToTeachers;
	// 体育教师参训人数
	private String numPhysicalTeachers;
	// 教师受县级以表彰人数
	private String numRecognitionTeachers;

	// SportScenod属性
	// 200米田径场块数
	private String length2;
	// 300米田径场块数
	private String length3;
	// 300米至400米田径场块数
	private String length3t4;
	// 400米田径场块数
	private String length4;
	// 篮球场块数
	private String basketball;
	// 排球场块数
	private String volleyball;
	// 器械体操/游戏区面积
	private String gameArea;
	// 体育馆个数
	private String sportHall;
	// 体育馆面积
	private String sportHallArea;
	// 游泳池个数
	private String swimPool;
	// 游泳池面积
	private String swimPoolArea;
	// 学生体质测试室个数
	private String testRoom;
	// 体育器材是否达标
	private String storard;

	// 自评等级
	private String selfRatingScale;
	private String isAddPointProject;//是否有加分项
	private String expense = "";// 支出总额
	private String expenseField = "";// 体育场地经费支出
	private String expenseEquip = "";// 体育专用器材经费支出
	private String expenseActivity = "";// 体育工作经费支出

	private String isInsurance = "";// 制定体育活动意外伤害保障措施
	private String token;
	
	private ServiceManager serviceManager;
	private String errorInfo;
	private static V v = new V();
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String execute() throws Exception {

		// 获得first中的数据;
		setToken(getSession().get("token").toString());
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("O_ID").toString();
		Map map = getServiceManager().getOtherService().getSportData(o_id);
		
		if (map == null) {
			errorInfo = "当前系统未开放！";
			return "noOpen";
		}
		//验证体育报表中是否存在未填写内容 若存在未填写内容则跳转到那一页
		if (v.checkEmpty(map.get("RATIO_TEA_STU"))||v.checkEmpty(map.get("FIELD_TRACK_300_400M"))||v.checkEmpty(map.get("EXPENSE_ACTIVITY"))) {
			errorInfo = "体育表尚未填写完毕！";
			return "backView";
		}
		//加载数据
		setData(map, o_id);
		return SUCCESS;
	}
	/**
	 * 加载数据
	 * @param map
	 */
	public void setData(Map map, String oid){
		//基础信息
		if (!v.checkEmpty(map.get("stu_total"))) {
			numOfStus = map.get("stu_total").toString();
		}
		if (!v.checkEmpty(map.get("stage"))) {
			String[] eduType = {"", "普通小学", "普通初中", "普通高中", "职业高中", "九年一贯制学校", "十二年一贯制学校", "完全中学"};
			schoolType = map.get("stage").toString().substring(0, 1);
			schoolType = eduType[Integer.parseInt(schoolType)];
		}
		if (!v.checkEmpty(map.get("class_total"))) {
			actualClassNumber = map.get("class_total").toString();
		}
		if (!v.checkEmpty(map.get("hours_total"))) {
			enoughPE = map.get("hours_total").toString();
			if(enoughPE.equals("1")){
				enoughPE = "1";
			}else{
				enoughPE = "0";
			}
		}
		if (!v.checkEmpty(map.get("is_one_hr_daily"))) {
			onehourPE = map.get("is_one_hr_daily").toString();
			if(onehourPE.equals("1")){
				onehourPE = "1";
			}else{
				onehourPE = "0";
			}
		}
		if (!v.checkEmpty(map.get("is_long_break_activity"))) {
			numActivityInClass = map.get("is_long_break_activity").toString();
			if(numActivityInClass.equals("1")){
				numActivityInClass = "1";
			}else{
				numActivityInClass = "0";
			}
		}
		if (!v.checkEmpty(map.get("havaTeacher"))) {
			havaTeacher = map.get("havaTeacher").toString();
		}
		if (!v.checkEmpty(map.get("teachers_full_time"))) {
			numFulltimeTeachers = map.get("teachers_full_time").toString();
		}
		if (!v.checkEmpty(map.get("teachers_part_time"))) {
			numParttimeTeachers = map.get("teachers_part_time").toString();
		}
		if (!v.checkEmpty(map.get("teachers_required"))) {
			sportsTeacherVacancyNum = map.get("teachers_required").toString();
		}
		if (!v.checkEmpty(map.get("teachers_awarded"))) {
			numRecognitionTeachers = map.get("teachers_awarded").toString();
		}
		if (!v.checkEmpty(map.get("RATIO_TEA_GAPS"))) {
			sportsTeacherVacancyRatio = map.get("RATIO_TEA_GAPS").toString();
			
		}
		if (!v.checkEmpty(map.get("RATIO_TEA_STU"))) {
			ratioOfStudentsToTeachers = map.get("RATIO_TEA_STU").toString();
		}
		if(!v.checkEmpty(map.get("teachers_trained"))){
			numPhysicalTeachers = map.get("teachers_trained").toString();
		}
		
	    //场地设施
		if (!v.checkEmpty(map.get("FIELD_TRACK_200M"))) {
			length2 = map.get("FIELD_TRACK_200M").toString();
		}
		if (!v.checkEmpty(map.get("FIELD_TRACK_300M"))) {
			length3 = map.get("FIELD_TRACK_300M").toString();
		}
		if (!v.checkEmpty(map.get("FIELD_TRACK_300_400M"))) {
			length3t4 = map.get("FIELD_TRACK_300_400M").toString();
		}
		if (!v.checkEmpty(map.get("FIELD_TRACK_400M"))) {
			length4 = map.get("FIELD_TRACK_400M").toString();
		}
		if (!v.checkEmpty(map.get("FIELDS_BASKETBALL"))) {
			basketball = map.get("FIELDS_BASKETBALL").toString();
		}
		if (!v.checkEmpty(map.get("FIELDS_VOLLEYBALL"))) {
			volleyball = map.get("FIELDS_VOLLEYBALL").toString();
		}
		if (!v.checkEmpty(map.get("FIELDS_GYM_AREA"))) {
			gameArea = map.get("FIELDS_GYM_AREA").toString();
		}
		if (!v.checkEmpty(map.get("STADIUM_TOTAL"))) {
			sportHall = map.get("STADIUM_TOTAL").toString();
		}
		if (!v.checkEmpty(map.get("STADIUM_AREA"))) {
			sportHallArea = map.get("STADIUM_AREA").toString();
		}
		if (!v.checkEmpty(map.get("POOL_TOTAL"))) {
			swimPool = map.get("POOL_TOTAL").toString();
		}
		if (!v.checkEmpty(map.get("POOL_AREA"))) {
			swimPoolArea = map.get("POOL_AREA").toString();
		}
		if (!v.checkEmpty(map.get("TEST_ROOM_TOTAL"))) {
			testRoom = map.get("TEST_ROOM_TOTAL").toString();
		}
		if (!v.checkEmpty(map.get("IS_EQUIP_QUALIFIED"))) {
			storard = map.get("IS_EQUIP_QUALIFIED").toString();
			if(storard.equals("1")){
				storard = "1";
			}else{
				storard = "0";
			}
		}
		
		//学校等级评估
		if(!v.checkEmpty(map.get("IS_ADD_POINT_PROJECT"))){
			isAddPointProject = map.get("IS_ADD_POINT_PROJECT").toString();
			if(isAddPointProject.equals("1")){
				isAddPointProject = "1";
			}else{
				isAddPointProject = "0";
			}
		}
		if(!v.checkEmpty(map.get("EXPENSE"))){
			expense = map.get("EXPENSE").toString();
		}
		if(!v.checkEmpty(map.get("EXPENSE_FIELD"))){
			expenseField = map.get("EXPENSE_FIELD").toString();
		}
		if(!v.checkEmpty(map.get("EXPENSE_EQUIP"))){
			expenseEquip = map.get("EXPENSE_EQUIP").toString();
		}
		if(!v.checkEmpty(map.get("EXPENSE_ACTIVITY"))){
			expenseActivity = map.get("EXPENSE_ACTIVITY").toString();
		}
		if(!v.checkEmpty(map.get("IS_INSURANCE"))){
			isInsurance = map.get("IS_INSURANCE").toString();
		}
		
		map = getServiceManager().getSportReportBySelfService().getSelfData(oid);
		if(map != null){
			selfRatingScale = v.checkEmpty(map.get("SELF_REMARK_GRADE"))==true?"":map.get("SELF_REMARK_GRADE").toString();
		}
	}
	
	
	
	public String getSelfRatingScale() {
		return selfRatingScale;
	}

	public void setSelfRatingScale(String selfRatingScale) {
		this.selfRatingScale = selfRatingScale;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getNumOfStus() {
		return numOfStus;
	}

	public void setNumOfStus(String numOfStus) {
		this.numOfStus = numOfStus;
	}

	public String getActualClassNumber() {
		return actualClassNumber;
	}

	public void setActualClassNumber(String actualClassNumber) {
		this.actualClassNumber = actualClassNumber;
	}

	public String getEnoughPE() {
		return enoughPE;
	}

	public void setEnoughPE(String enoughPE) {
		this.enoughPE = enoughPE;
	}

	public String getOnehourPE() {
		return onehourPE;
	}

	public void setOnehourPE(String onehourPE) {
		this.onehourPE = onehourPE;
	}

	public String getHavaTeacher() {
		return havaTeacher;
	}

	public void setHavaTeacher(String havaTeacher) {
		this.havaTeacher = havaTeacher;
	}

	public String getNumActivityInClass() {
		return numActivityInClass;
	}

	public void setNumActivityInClass(String numActivityInClass) {
		this.numActivityInClass = numActivityInClass;
	}

	public String getNumFulltimeTeachers() {
		return numFulltimeTeachers;
	}

	public void setNumFulltimeTeachers(String numFulltimeTeachers) {
		this.numFulltimeTeachers = numFulltimeTeachers;
	}

	public String getNumParttimeTeachers() {
		return numParttimeTeachers;
	}

	public void setNumParttimeTeachers(String numParttimeTeachers) {
		this.numParttimeTeachers = numParttimeTeachers;
	}

	public String getSportsTeacherVacancyRatio() {
		return sportsTeacherVacancyRatio;
	}

	public void setSportsTeacherVacancyRatio(String sportsTeacherVacancyRatio) {
		this.sportsTeacherVacancyRatio = sportsTeacherVacancyRatio;
	}

	public String getSportsTeacherVacancyNum() {
		return sportsTeacherVacancyNum;
	}

	public void setSportsTeacherVacancyNum(String sportsTeacherVacancyNum) {
		this.sportsTeacherVacancyNum = sportsTeacherVacancyNum;
	}

	public String getRatioOfStudentsToTeachers() {
		return ratioOfStudentsToTeachers;
	}

	public void setRatioOfStudentsToTeachers(String ratioOfStudentsToTeachers) {
		this.ratioOfStudentsToTeachers = ratioOfStudentsToTeachers;
	}

	public String getNumPhysicalTeachers() {
		return numPhysicalTeachers;
	}

	public void setNumPhysicalTeachers(String numPhysicalTeachers) {
		this.numPhysicalTeachers = numPhysicalTeachers;
	}

	public String getNumRecognitionTeachers() {
		return numRecognitionTeachers;
	}

	public void setNumRecognitionTeachers(String numRecognitionTeachers) {
		this.numRecognitionTeachers = numRecognitionTeachers;
	}

	public String getLength2() {
		return length2;
	}

	public void setLength2(String length2) {
		this.length2 = length2;
	}

	public String getLength3() {
		return length3;
	}

	public void setLength3(String length3) {
		this.length3 = length3;
	}

	public String getLength3t4() {
		return length3t4;
	}

	public void setLength3t4(String length3t4) {
		this.length3t4 = length3t4;
	}

	public String getLength4() {
		return length4;
	}

	public void setLength4(String length4) {
		this.length4 = length4;
	}

	public String getBasketball() {
		return basketball;
	}

	public void setBasketball(String basketball) {
		this.basketball = basketball;
	}

	public String getVolleyball() {
		return volleyball;
	}

	public void setVolleyball(String volleyball) {
		this.volleyball = volleyball;
	}

	public String getGameArea() {
		return gameArea;
	}

	public void setGameArea(String gameArea) {
		this.gameArea = gameArea;
	}

	public String getSportHall() {
		return sportHall;
	}

	public void setSportHall(String sportHall) {
		this.sportHall = sportHall;
	}

	public String getSportHallArea() {
		return sportHallArea;
	}

	public void setSportHallArea(String sportHallArea) {
		this.sportHallArea = sportHallArea;
	}

	public String getSwimPool() {
		return swimPool;
	}

	public void setSwimPool(String swimPool) {
		this.swimPool = swimPool;
	}

	public String getSwimPoolArea() {
		return swimPoolArea;
	}

	public void setSwimPoolArea(String swimPoolArea) {
		this.swimPoolArea = swimPoolArea;
	}

	public String getTestRoom() {
		return testRoom;
	}

	public void setTestRoom(String testRoom) {
		this.testRoom = testRoom;
	}

	public String getStorard() {
		return storard;
	}

	public void setStorard(String storard) {
		this.storard = storard;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getExpenseField() {
		return expenseField;
	}

	public void setExpenseField(String expenseField) {
		this.expenseField = expenseField;
	}

	public String getExpenseEquip() {
		return expenseEquip;
	}

	public void setExpenseEquip(String expenseEquip) {
		this.expenseEquip = expenseEquip;
	}

	public String getExpenseActivity() {
		return expenseActivity;
	}

	public void setExpenseActivity(String expenseActivity) {
		this.expenseActivity = expenseActivity;
	}

	public String getIsInsurance() {
		return isInsurance;
	}

	public void setIsInsurance(String isInsurance) {
		this.isInsurance = isInsurance;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getIsAddPointProject() {
		return isAddPointProject;
	}

	public void setIsAddPointProject(String isAddPointProject) {
		this.isAddPointProject = isAddPointProject;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}
