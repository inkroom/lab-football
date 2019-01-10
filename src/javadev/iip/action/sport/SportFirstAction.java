package javadev.iip.action.sport;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;

import org.apache.commons.net.io.SocketOutputStream;
import org.apache.commons.validator.Msg;
import org.jfree.date.RelativeDayOfWeekRule;

import com.sun.xml.internal.ws.api.pipe.Tube;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;
import oracle.net.aso.i;

/**
 * 功能描述： 体育基础信息
 * 
 * @author曹旭峰 Create Date: 2016-8-31
 */
public class SportFirstAction extends BaseAction {

	private String token;
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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
	// 专职体育教师人数；
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

	// 上一年在校人数
	private String lastnumOfStus;
	// 上一年实际班级数量
	private String lastactualClassNumber;
	// 上一年应配教师数
	private String lasthavaTeacher;
	// 上一年专职体育教室人数；
	private String lastnumFulltimeTeachers;
	// 上一年兼职体育教师人数；
	private String lastnumParttimeTeachers;
	// 上一年体育教室缺额比
	private String lastsportsTeacherVacancyRatio;
	// 上一年体育教师缺额数；
	private String lastsportsTeacherVacancyNum;
	// 上一年体育教师师生比；
	private String lastratioOfStudentsToTeachers;
	// 上一年体育教师参训人数
	private String lastnumPhysicalTeachers;
	// 上一年教师受县级以表彰人数
	private String lastnumRecognitionTeachers;
	
	private String tube = "";

	public String getLastnumOfStus() {
		return lastnumOfStus;
	}

	public void setLastnumOfStus(String lastnumOfStus) {
		this.lastnumOfStus = lastnumOfStus;
	}

	public String getLastactualClassNumber() {
		return lastactualClassNumber;
	}

	public void setLastactualClassNumber(String lastactualClassNumber) {
		this.lastactualClassNumber = lastactualClassNumber;
	}

	public String getLasthavaTeacher() {
		return lasthavaTeacher;
	}

	public void setLasthavaTeacher(String lasthavaTeacher) {
		this.lasthavaTeacher = lasthavaTeacher;
	}

	public String getLastnumFulltimeTeachers() {
		return lastnumFulltimeTeachers;
	}

	public void setLastnumFulltimeTeachers(String lastnumFulltimeTeachers) {
		this.lastnumFulltimeTeachers = lastnumFulltimeTeachers;
	}

	public String getLastnumParttimeTeachers() {
		return lastnumParttimeTeachers;
	}

	public void setLastnumParttimeTeachers(String lastnumParttimeTeachers) {
		this.lastnumParttimeTeachers = lastnumParttimeTeachers;
	}

	public String getLastsportsTeacherVacancyRatio() {
		return lastsportsTeacherVacancyRatio;
	}

	public void setLastsportsTeacherVacancyRatio(String lastsportsTeacherVacancyRatio) {
		this.lastsportsTeacherVacancyRatio = lastsportsTeacherVacancyRatio;
	}

	public String getLastsportsTeacherVacancyNum() {
		return lastsportsTeacherVacancyNum;
	}

	public void setLastsportsTeacherVacancyNum(String lastsportsTeacherVacancyNum) {
		this.lastsportsTeacherVacancyNum = lastsportsTeacherVacancyNum;
	}

	public String getLastratioOfStudentsToTeachers() {
		return lastratioOfStudentsToTeachers;
	}

	public void setLastratioOfStudentsToTeachers(String lastratioOfStudentsToTeachers) {
		this.lastratioOfStudentsToTeachers = lastratioOfStudentsToTeachers;
	}

	public String getLastnumPhysicalTeachers() {
		return lastnumPhysicalTeachers;
	}

	public void setLastnumPhysicalTeachers(String lastnumPhysicalTeachers) {
		this.lastnumPhysicalTeachers = lastnumPhysicalTeachers;
	}

	public String getLastnumRecognitionTeachers() {
		return lastnumRecognitionTeachers;
	}

	public void setLastnumRecognitionTeachers(String lastnumRecognitionTeachers) {
		this.lastnumRecognitionTeachers = lastnumRecognitionTeachers;
	}

	// 出错信息
	private String errorInfo;

	private ServiceManager serviceManager;

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public String getHavaTeacher() {
		return havaTeacher;
	}

	public void setHavaTeacher(String havaTeacher) {
		this.havaTeacher = havaTeacher;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	V v = new V();

	/**
	 * 将前端信息存入Map 传入service类
	 */
	private void putMag() {
		
		
		
		Map<String, String> map = new HashMap<>();

		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		
		String o_id = user.get("O_ID").toString();
		//获得艺术表单的数据;
//		Map map2=serviceManager.getOtherService().getArtData(o_id);
//		
//		//System.out.println("+++++++++++++++++++++++++++++++++++++"+map2);
//		
//		try{
//			String teacherNum = map2.get("TEACHERS_MUS_PRESENT").toString();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		
		
		//System.out.println("Hello+++++++++++++++++++++++++LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL+++++++++++++++++++++++++++++++++"+map2.get("TEACHERS_MUS_PRESENT"));
		String c_id = getSession().get(Constants.LOGIN_USER_C_ID).toString();
		String stages = user.get("STAGE").toString();
		

		if(!stages.equals(schoolType)){
			
			user.put("STAGE", schoolType);
			getSession().put(Constants.LOGIN_USER, user);
		}
		
		//计算体育教师缺额数;
		sportsTeacherVacancyNum = (Integer.parseInt(havaTeacher) - Integer.parseInt(numFulltimeTeachers))+"";
		//计算体育教师缺额比，并保留两位小数;
		double ratio = (Integer.parseInt(sportsTeacherVacancyNum) * 100 /  Integer.parseInt(havaTeacher));
		sportsTeacherVacancyRatio = (int)ratio+"";
		//sportsTeacherVacancyRatio = new DecimalFormat("#.00").format(ratio);
		//计算体育师生比比，并保留两位小数;
		double ratio1;
		/**
		 *当专职教师数为0是，使用1进行计算,是师生比最大化；
		 */
		if(Integer.parseInt(numFulltimeTeachers) == 0){
			ratio1 = Integer.parseInt(numOfStus);
		}else{
			ratio1 = (Integer.parseInt(numOfStus) / Integer.parseInt(numFulltimeTeachers));
		}
		ratioOfStudentsToTeachers = (int)ratio1+"";
		
		if(!tube.equals(numOfStus)){
			errorInfo="在校人数已修改:①请在体育年度自评表中修改对应的班级数与学生数！②请在艺术工作年度报表中修改艺术素质测评中的优秀等级!";
		}
		
		map.put("schoolType", schoolType);
		map.put("numOfStus", numOfStus);
		map.put("actualClassNumber", actualClassNumber);
		map.put("enoughPE", enoughPE);
		map.put("onehourPE", onehourPE);
		map.put("havaTeacher", havaTeacher);
		map.put("numActivityInClass", numActivityInClass);
		map.put("numFulltimeTeachers", numFulltimeTeachers);
		map.put("numParttimeTeachers", numParttimeTeachers);
		map.put("sportsTeacherVacancyNum", sportsTeacherVacancyNum);
		map.put("sportsTeacherVacancyRatio", sportsTeacherVacancyRatio);
		map.put("ratioOfStudentsToTeachers", ratioOfStudentsToTeachers);
		map.put("numPhysicalTeachers", numPhysicalTeachers);
		map.put("numRecognitionTeachers", numRecognitionTeachers);
		map.put("o_id", o_id);
		map.put("c_id", c_id);
		
		
		
		//获取的新数据;
		String oid = user.get("O_ID").toString();
		Map oldData = getServiceManager().getOtherService().getSportData(oid);
		
		Iterator<String> newIter = map.keySet().iterator();
		while(newIter.hasNext()){
			String newKey = (String)newIter.next();
			if(!map.get(newKey).equals(oldData.get(newKey))){
				break;
			}
		}
		
		// serviceManager.getSportFirstService().insertData(map);
		serviceManager.getSportFirstService().insertData(map);
		// Map<String, Object> nowData =
		// getServiceManager().getOtherService().getSportData(user.get("C_ID").toString());
		String username = user.get("username").toString();
		serviceManager.getSportFirstService().insertArtTeacherData(username);

	}

	/**
	 * 点击下一步时调用
	 * 
	 * @return
	 */   
	public String view() {
		
		setToken(getSession().get("token").toString());
		// 验证逻辑
		
		if (V.isNumeric(numOfStus) == false || v.judgeSize(0, 100000000, numOfStus) == false) {
			errorInfo = "请输入正确的在校人数";
			return "error";
		}
		// 验证 actualClassNumber（实际班级数量）

		if (V.isNumeric(actualClassNumber) == false || v.judgeSize(-1, 100000000, actualClassNumber) == false) {
			errorInfo = "请输入正确的班级数！";
			return "error";
		}

		// 验证numActivityInClass（组织大课间体育活动数）
		if (V.isNumeric(numActivityInClass) == false || v.judgeSize(-1, 100000000, numActivityInClass) == false) {
			errorInfo = "请输入正确的课间体育活动数！";
			return "error";
		}
		// 验证havaTeacherNum(应配教师数)
		if (V.isNumeric(havaTeacher) == false || v.judgeSize(0, 100000000, havaTeacher) == false) {
			errorInfo = "请输入正确的应配教师数！";
			return "error";
		}
		// 验证numFulltimeTeachers(专职体育教师人数)
		if (V.isNumeric(numFulltimeTeachers) == false || v.judgeSize(-1, 100000000, numFulltimeTeachers) == false) {
			errorInfo = "请输入正确的专职体育教师数！";
			return "error";
		}
		// 验证numParttimeTeachers(专职体育教师人数)
		if (V.isNumeric(numParttimeTeachers) == false || v.judgeSize(-1, 100000000, numParttimeTeachers) == false) {
			errorInfo = "请输入正确的兼职体育教师人数！";
			return "error";
		}
//		// 验证sportsTeacherVacancyNum(体育教师缺额数)
//		if (V.isNumeric(sportsTeacherVacancyNum) == false
//				|| v.judgeSize(-1, 100000000, sportsTeacherVacancyNum) == false) {
//			errorInfo = "请输入正确的体育教师缺额数人数！";
//			return "error";
//		}

		// 验证numPhysicalTeachers（体育教师参训人数）
		if (V.isNumeric(numPhysicalTeachers) == false || v.judgeSize(-1, 100000000, numPhysicalTeachers) == false) {
			errorInfo = "请输入正确的体育教师参训人数！";
			return "error";
		}
		// 验证numPhysicalTeachers（教师受县级以上表彰人数）
		if (V.isNumeric(numRecognitionTeachers) == false
				|| v.judgeSize(-1, 100000000, numRecognitionTeachers) == false) {
			errorInfo = "请输入正确的教师受县级以上表彰人数！";
			return "error";
		}
		// 调用putMsg(),将数据通过map传入到 service 中
		try {
			int stuNum = Integer.parseInt(numOfStus);
		} catch (Exception e) {
			// TODO: handle exception
			setErrorInfo("学生人数填写不规范！");
			return "error";
		}
		
				putMag();
		return SUCCESS;

	}

	@Override
	public String execute() throws Exception {
		setToken(getSession().get("token").toString());
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("O_ID").toString();
		Map map = getServiceManager().getOtherService().getSportData(o_id);
		Map lastmap = getServiceManager().getOtherService().getSportLastYearData(o_id);
		if(map != null){
			
			if (!v.checkEmpty(map.get("stu_total"))) {
				numOfStus = map.get("stu_total").toString();
				tube= map.get("stu_total").toString();
			}
			if (!v.checkEmpty(map.get("stage"))) {
				schoolType = map.get("stage").toString().substring(0, 1);
				System.out.println("=======================lenght"+schoolType.length());
			}
			if (!v.checkEmpty(map.get("class_total"))) {
				actualClassNumber = map.get("class_total").toString();
	 		}
			if (!v.checkEmpty(map.get("hours_total"))) {
				enoughPE = map.get("hours_total").toString();
			}
			if (!v.checkEmpty(map.get("is_one_hr_daily"))) {
				onehourPE = map.get("is_one_hr_daily").toString();
			}
			if (!v.checkEmpty(map.get("is_long_break_activity"))) {
				numActivityInClass = map.get("is_long_break_activity").toString();
				
			}
			if (!v.checkEmpty(map.get("havaTeacher"))) {
				havaTeacher = map.get("havaTeacher").toString();
			}
			if (!v.checkEmpty(map.get("teachers_full_time"))) {
				numFulltimeTeachers = map.get("teachers_full_time").toString();
				System.out.println("=======================lenght"+numFulltimeTeachers.length());
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
		}else{
			schoolType = user.get("STAGE").toString();
			errorInfo = "当前系统未开放！";
			return "noOpen";
		}
		
	
		if(lastmap!=null){
			// 获取上一年的数据；
			lastnumOfStus = lastmap.get("stu_total").toString();
			lastactualClassNumber = lastmap.get("class_total").toString();
			lasthavaTeacher = lastmap.get("havaTeacher").toString();
			lastnumFulltimeTeachers = lastmap.get("teachers_full_time").toString();
			lastnumParttimeTeachers = lastmap.get("teachers_part_time").toString();
			lastsportsTeacherVacancyRatio = lastmap.get("RATIO_TEA_GAPS").toString();
			lastsportsTeacherVacancyNum = lastmap.get("teachers_required").toString();
			lastratioOfStudentsToTeachers = lastmap.get("RATIO_TEA_STU").toString();
			lastnumPhysicalTeachers = lastmap.get("teachers_trained").toString();
			lastnumRecognitionTeachers = lastmap.get("teachers_awarded").toString();
		}

		

		return SUCCESS;
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

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

}
