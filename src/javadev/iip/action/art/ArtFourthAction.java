package javadev.iip.action.art;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;
import oracle.net.aso.e;

/**
 * 功能描述：艺术类特色发展及艺术素质测评表单数据验证提交
 * 
 * @author 刘俊
 * @version 1.0 Create Date: 2016-8-31
 */
public class ArtFourthAction extends BaseAction {

	private ServiceManager serviceManager;
	// 特色发展是否具有特色
	private String features;
	// 特色发展自评得分
	private String evaluationOne;
	// 艺术素质测评是否展开测评
	private String assessment;
	// 优秀等级数量
	private String good;
	// 良好等级数量
	private String fine;
	// 合格等级数量
	private String qualified;
	// 不合格等级数量
	private String notQualified;
	// 艺术素质测评自评得分
	private String artEvaluation;

	// 上一年特色发展自评得分
	private String lastEva;
	// 上一年优秀数量
	private String lastGood;
	// 上一年良好数量
	private String lastFine;
	// 上一年合格数量
	private String lastPass;
	// 上一年不合格数量
	private String lastNot;
	// 上一年艺术素质测评得分
	private String lastArtE;
	// 得到所有学生数量
	private String studentNum;
	
	private String classRoomPay;
	private String artEdaPay;
	private String artPay;
	private String classf;
	private String artEdaf;
	private String artPayf;
	
	private String lastClassRoomPay;
	private String lastArtEdaPay;
	private String lastArtPay;
	// 返回的错误信息
	private String errorInfo;

	private String token;

	private static V v = new V();

	/**
	 * 获得当前数据重现在前台页面上 去年数据放在label中
	 */
	public String execute() throws Exception {

		setToken(getSession().get("token").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapLast = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("o_id").toString();
		map = serviceManager.getOtherService().getArtData(o_id);
		mapLast = serviceManager.getOtherService().getArtLastYearData(o_id);

		if (map == null) {
			errorInfo = "当前系统未开放";
			return "noOpen";
		} else if (map.get("STU_TOTAL") != null) {
			studentNum = map.get("STU_TOTAL").toString();
		} else {
			
		}

		if (v.checkEmpty(map.get("ART_VENUES_NUM")) || v.checkEmpty(map.get("SELF_REMARK_ENSURANCE"))) {
			errorInfo = "当前页面还有未完成的数据项，请先填写完成！";
			return "backView";
		}

		if (!v.checkEmpty(map.get("IS_FEATURED"))) {
			setFeatures(map.get("IS_FEATURED").toString());
		}
		if (!v.checkEmpty(map.get("SELF_REMARK_FEATURE"))) {
			evaluationOne = map.get("SELF_REMARK_FEATURE").toString();
		}
		if (!v.checkEmpty(map.get("IS_TEST_PERFORMED"))) {
			setAssessment(map.get("IS_TEST_PERFORMED").toString());
		}
		if (!v.checkEmpty(map.get("STUDENTS_EXCELLENT"))) {
			good = map.get("STUDENTS_EXCELLENT").toString();
		}
		if (!v.checkEmpty(map.get("STUDENTS_GOOD"))) {
			fine = map.get("STUDENTS_GOOD").toString();
		}
		if (!v.checkEmpty(map.get("STUDENTS_PASS"))) {
			qualified = map.get("STUDENTS_PASS").toString();
		}
		if (!v.checkEmpty(map.get("STUDENTS_FAIL"))) {
			notQualified = map.get("STUDENTS_FAIL").toString();
		}
		if (!v.checkEmpty(map.get("SELF_REMARK_ART_QUALITY"))) {
			artEvaluation = map.get("SELF_REMARK_ART_QUALITY").toString();
		}
		if (!v.checkEmpty(map.get("EXPENSE_ART_CONSTRUCTION"))) {
			classRoomPay = map.get("EXPENSE_ART_CONSTRUCTION").toString();
		}
		if (!v.checkEmpty(map.get("EXPENSE_ART_EQUIP"))) {
			artEdaPay = map.get("EXPENSE_ART_EQUIP").toString();
		}
		if (!v.checkEmpty(map.get("EXPENSE_ACTIVITY"))) {
			artPay = map.get("EXPENSE_ACTIVITY").toString();
		}

		if (mapLast != null) {
			lastEva = mapLast.get("SELF_REMARK_FEATURE").toString();
			lastGood = mapLast.get("STUDENTS_EXCELLENT").toString();
			lastFine = mapLast.get("STUDENTS_GOOD").toString();
			lastPass = mapLast.get("STUDENTS_PASS").toString();
			lastNot = mapLast.get("STUDENTS_FAIL").toString();
			lastArtE = mapLast.get("SELF_REMARK_ART_QUALITY").toString();
			lastClassRoomPay=mapLast.get("EXPENSE_ART_CONSTRUCTION").toString();
			lastArtEdaPay=mapLast.get("EXPENSE_ART_EQUIP").toString();
		    lastArtPay=mapLast.get("EXPENSE_ACTIVITY").toString();
		}
		return "success";
	}

	@SuppressWarnings("unused")
	public String getData() {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapArt = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("o_id").toString();
		setToken(getSession().get("token").toString());
		mapArt = serviceManager.getOtherService().getArtData(o_id);

		studentNum = mapArt.get("STU_TOTAL").toString();
		int allNumber = Integer.parseInt(studentNum);

		if (mapArt == null) {
			errorInfo = "当前系统未开放";
			return "noOpen";
		}

		/**
		 * 验证上一步的表单中是否存在未填写内容 若存在未填写内容则跳转到那一页
		 */
		if (v.checkEmpty(mapArt.get("ART_VENUES_NUM")) || v.checkEmpty(mapArt.get("SELF_REMARK_ENSURANCE"))) {
			errorInfo = "当前页面还有未完成的数据项，请先填写完成！";
			return "back";
		}

		// 特色发展自评得分输入是否合法
		if (V.isNumeric(evaluationOne) == false || v.judgeSize(-1, 11, evaluationOne) == false) {
			errorInfo = "特色发展自评得分填写错误，请输入0~10之间的数字";
			return "error";
		}

		if (assessment.equals("2")) {
			good = "0";
			fine = "0";
			qualified = "0";
			notQualified = "0";
		} else {
			// 优秀学生数量输入是否合法
			if (V.isNumeric(good) == false || v.judgeSize(-1, 1 + allNumber, good) == false) {
				errorInfo = "优秀学生数量输入错误或人数总和超过贵校总人数" + allNumber + ",请输入正确人数";
				return "error";
			}
			// 良好学生数量输入是否合法
			if (V.isNumeric(fine) == false || v.judgeSize(-1, 1 + allNumber - Integer.parseInt(good), fine) == false) {
				errorInfo = "良好学生数量输入错误或人数总和超过贵校总人数" + allNumber + ",请输入正确人数";
				return "error";
			}
			// 合格学生数量输入是否合法
			if (V.isNumeric(qualified) == false || v.judgeSize(-1,
					1 + allNumber - Integer.parseInt(good) - Integer.parseInt(fine), qualified) == false) {
				errorInfo = "合格学生数量输入错误或人数总和超过贵校总人数" + allNumber + ",请输入正确人数";
				return "error";
			}
			// 不合格学生数量输入是否合法
			if (V.isNumeric(notQualified) == false || !(Integer.parseInt(good) + Integer.parseInt(fine) 
			    +Integer.parseInt(qualified)+Integer.parseInt(notQualified)==allNumber)){
				errorInfo = "学生数量输入错误或人数总和不等于贵校总人数" + allNumber + ",请输入正确人数";
				return "error";
			}
		}
		// 艺术自评得分输入是否合法
		if (V.isNumeric(artEvaluation) == false || v.judgeSize(-1, 11, artEvaluation) == false) {
			errorInfo = "艺术素质自评得分填写错误，请输入0~10之间的数字";
			return "error";
		}
		
		DecimalFormat df = new DecimalFormat("0.0000");
		// 专用教室/艺术场馆建设支出
		
		if(!v.checkEmpty(classRoomPay)){
			 float ratio = Float.parseFloat(classRoomPay);
			 classf = df.format(ratio)+"";
		 }else if( v.judgeSize(-1, 99999999,classRoomPay ) == false) {
			errorInfo = "专用教室/艺术场馆建设支出填写错误";
			return "error";
		}
		// 艺术教育器材支出
		if(!v.checkEmpty(artEdaPay)){
			 float ratio2 = Float.parseFloat(artEdaPay);
			 artEdaf = df.format(ratio2) + "";
		 }else if ( v.judgeSize(-1, 99999999, artEdaPay) == false) {
			errorInfo = "艺术教育器材支出填写错误";
			return "error";
		}
		// 艺术活动支出
		if(!v.checkEmpty(artPay)){
			 float ratio3 = Float.parseFloat(artPay);
			 artPayf = df.format(ratio3) + "";
		 }else if (v.judgeSize(-1, 99999999, artPay) == false) {
			errorInfo = "艺术活动支出填写错误";
			return "error";
		}
	
		/**
		 * 将前端信息存入Map 传入service类
		 */

		  int val=0;
		    int val1 = 0,val2 = 0,val3 = 0,val4 = 0,val5 = 0,val6 = 0;
		    if(mapArt.get("SELF_REMARK_ART_COURSE")!=null){
		    	 String art1=mapArt.get("SELF_REMARK_ART_COURSE").toString();
		    	 val1=Integer.parseInt(art1);
		     }
		    if(mapArt.get("SELF_REMARK_ART_ACTIVITY")!=null){
		    	 String art2=mapArt.get("SELF_REMARK_ART_ACTIVITY").toString();
		    	 val2=Integer.parseInt(art2);
			}
		    if (mapArt.get("SELF_REMARK_ENSURANCE")!=null) {
				String art3=mapArt.get("SELF_REMARK_ART_TEACHER").toString();
				val3=Integer.parseInt(art3);
			}
		    if (mapArt.get("SELF_REMARK_ENSURANCE")!=null) {
				String art4=mapArt.get("SELF_REMARK_ENSURANCE").toString();
				val4=Integer.parseInt(art4);
			}
		    if (evaluationOne!=null) {
				val5=Integer.parseInt(evaluationOne);
			}
		    if (artEvaluation!=null) {
				val6=Integer.parseInt(artEvaluation);
			}

		    val=val1+val2+val3+val4+val5+val6;
		    String addScore=val+""; 
		    if (val>=60&&val<=74) {
				map.put("data12",3);
			}else if(val>=75&&val<89){
				map.put("data12",2);
			}else if(val>=89){
				map.put("data12",1);
			}else {
				map.put("data12",4);
			}
		    
		
		
		map.put("score", addScore);
		map.put("data1", features);
		map.put("data2", evaluationOne);
		map.put("data3", assessment);
		map.put("data4", good);
		map.put("data5", fine);
		map.put("data6", qualified);
		map.put("data7", notQualified);
		map.put("data8", artEvaluation);
		map.put("data9", classf);
		map.put("data10", artEdaf);
		map.put("data11", artPayf);
		map.put("o_id", o_id);
		
		if (isChangedData()) {
			serviceManager.getArtFourthService().updateArtFourth(map);
		    return "success";
		}
		return "success";
	}
	
	
	public boolean isChangedData() {

		boolean flag = true;
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String  o_id = user.get("O_ID").toString();
		Map<String, Object> map = serviceManager.getOtherService().getSportData(o_id);

		if (map != null) {
			
			String str = v.checkEmpty(map.get("IS_FEATURED")) == true ? "": map.get("IS_FEATURED").toString();
			if (str.length() > 0 && str.equals(features)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("SELF_REMARK_FEATURE")) == true ? "" : map.get("SELF_REMARK_FEATURE").toString();
			if (!flag && str.length() > 0 && str.equals(evaluationOne)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("IS_TEST_PERFORMED")) == true ? "": map.get("IS_TEST_PERFORMED").toString();
			if (!flag && str.length() > 0 && str.equals(assessment)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("STUDENTS_EXCELLENT")) == true ? "" : map.get("STUDENTS_EXCELLENT").toString();
			if (!flag && str.length() > 0 && str.equals(good)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("STUDENTS_GOOD")) == true ? "" : map.get("STUDENTS_GOOD").toString();
			if (!flag && str.length() > 0 && str.equals(fine)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("STUDENTS_PASS")) == true ? "" : map.get("STUDENTS_PASS").toString();
			if (!flag && str.length() > 0 && str.equals(qualified)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("SELF_REMARK_ART_QUALITY")) == true ? "" : map.get("SELF_REMARK_ART_QUALITY").toString();
			if (!flag && str.length() > 0 && str.equals(notQualified)) {
				flag = false;
			} else {
				flag = true;
			}

			str = v.checkEmpty(map.get("STUDENTS_FAIL")) == true ? "" : map.get("STUDENTS_FAIL").toString();
			if (!flag && str.length() > 0 && str.equals(artEvaluation)) {
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}


	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String isFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String getEvaluationOne() {
		return evaluationOne;
	}

	public void setEvaluationOne(String evaluationOne) {
		this.evaluationOne = evaluationOne;
	}

	public String getQualified() {
		return qualified;
	}

	public void setQualified(String qualified) {
		this.qualified = qualified;
	}

	public String getNotQualified() {
		return notQualified;
	}

	public void setNotQualified(String notQualified) {
		this.notQualified = notQualified;
	}

	public String getArtEvaluation() {
		return artEvaluation;
	}

	public void setArtEvaluation(String artEvaluation) {
		this.artEvaluation = artEvaluation;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getLastEva() {
		return lastEva;
	}

	public void setLastEva(String lastEva) {
		this.lastEva = lastEva;
	}

	public String getLastGood() {
		return lastGood;
	}

	public void setLastGood(String lastGood) {
		this.lastGood = lastGood;
	}

	public String getLastFine() {
		return lastFine;
	}

	public void setLastFine(String lastFine) {
		this.lastFine = lastFine;
	}

	public String getLastPass() {
		return lastPass;
	}

	public void setLastPass(String lastPass) {
		this.lastPass = lastPass;
	}

	public String getLastNot() {
		return lastNot;
	}

	public void setLastNot(String lastNot) {
		this.lastNot = lastNot;
	}

	public String getLastArtE() {
		return lastArtE;
	}

	public void setLastArtE(String lastArtE) {
		this.lastArtE = lastArtE;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getClassRoomPay() {
		return classRoomPay;
	}

	public void setClassRoomPay(String classRoomPay) {
		this.classRoomPay = classRoomPay;
	}

	public String getArtEdaPay() {
		return artEdaPay;
	}

	public String getFeatures() {
		return features;
	}

	public void setArtEdaPay(String artEdaPay) {
		this.artEdaPay = artEdaPay;
	}
	public String getArtPay() {
		return artPay;
	}
	public void setArtPay(String artPay) {
		this.artPay = artPay;
	}

	public String getLastClassRoomPay() {
		return lastClassRoomPay;
	}

	public void setLastClassRoomPay(String lastClassRoomPay) {
		this.lastClassRoomPay = lastClassRoomPay;
	}

	public String getLastArtEdaPay() {
		return lastArtEdaPay;
	}

	public void setLastArtEdaPay(String lastArtEdaPay) {
		this.lastArtEdaPay = lastArtEdaPay;
	}

	public String getLastArtPay() {
		return lastArtPay;
	}

	public void setLastArtPay(String lastArtPay) {
		this.lastArtPay = lastArtPay;
	}

}
