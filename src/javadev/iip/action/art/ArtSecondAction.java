package javadev.iip.action.art;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

/**
 * 功能描述: 统计艺术教师情况
 * @author: 曾绩平
 * @version: 1.0 
 * Create Date: 2016-8-31
 */

public class ArtSecondAction extends BaseAction {

	private String token;
	private static V v = new V();
	private String studentNum;
	//（音乐教师）应配专职数
	private String musicShouldTeacher;
	private String musicShouldTeacherLast;
	//（音乐教师）实有教师数
	private String musicRealTeacher;
	private String musicRealTeacherLast;
	//（音乐教师）缺额专职数
	private String musicGapsTeacher;
	private String musicGapsTeacherLast;
	//（音乐教师）兼职教师数
	private String musicPartTimeTeacher;
	private String musicPartTimeTeacherLast;
	//（音乐教师）生师比
	private String musicRatioST;
	private String musicRatioSTLast;
	//（音乐教师）周课时数
	private String musicClassNum;
	private String musicClassNumLast;
	//（美术教师）应配专职数
	private String artShouldTeacher;
	private String artShouldTeacherLast;
	//（美术教师）实有教师数
	private String artRealTeacher;
	private String artRealTeacherLast;
	//（美术教师）缺额专职数
	private String artGapsTeacher;
	private String artGapsTeacherLast;
	//（美术教师）兼职教师数
	private String artPartTimeTeacher;
	private String artPartTimeTeacherLast;
	//（美术教师）生师比
	private String artRatioST;
	private String artRatioSTLast;
	//（美术教师）周课时数
	private String artClassNum;
	private String artClassNumLast;
	//参加县级以上培训人数
	private String train;
	private String trainLast;
	//荣获县级以上表彰人数
	private String honor;
	private String honorLast;
	//自评得分
	private String selfAssessment;
	private String selfAssessmentLast;

	private String errorInfo;
	private ServiceManager serviceManager=new ServiceManager();

	
	
	
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

	

	public String getMusicShouldTeacherLast() {
		return musicShouldTeacherLast;
	}

	public void setMusicShouldTeacherLast(String musicShouldTeacherLast) {
		this.musicShouldTeacherLast = musicShouldTeacherLast;
	}

	public String getMusicRealTeacherLast() {
		return musicRealTeacherLast;
	}

	public void setMusicRealTeacherLast(String musicRealTeacherLast) {
		this.musicRealTeacherLast = musicRealTeacherLast;
	}

	public String getMusicGapsTeacherLast() {
		return musicGapsTeacherLast;
	}

	public void setMusicGapsTeacherLast(String musicGapsTeacherLast) {
		this.musicGapsTeacherLast = musicGapsTeacherLast;
	}

	public String getMusicPartTimeTeacherLast() {
		return musicPartTimeTeacherLast;
	}

	public void setMusicPartTimeTeacherLast(String musicPartTimeTeacherLast) {
		this.musicPartTimeTeacherLast = musicPartTimeTeacherLast;
	}

	public String getMusicRatioSTLast() {
		return musicRatioSTLast;
	}

	public void setMusicRatioSTLast(String musicRatioSTLast) {
		this.musicRatioSTLast = musicRatioSTLast;
	}

	public String getMusicClassNumLast() {
		return musicClassNumLast;
	}

	public void setMusicClassNumLast(String musicClassNumLast) {
		this.musicClassNumLast = musicClassNumLast;
	}

	public String getArtShouldTeacherLast() {
		return artShouldTeacherLast;
	}

	public void setArtShouldTeacherLast(String artShouldTeacherLast) {
		this.artShouldTeacherLast = artShouldTeacherLast;
	}

	public String getArtRealTeacherLast() {
		return artRealTeacherLast;
	}

	public void setArtRealTeacherLast(String artRealTeacherLast) {
		this.artRealTeacherLast = artRealTeacherLast;
	}

	public String getArtGapsTeacherLast() {
		return artGapsTeacherLast;
	}

	public void setArtGapsTeacherLast(String artGapsTeacherLast) {
		this.artGapsTeacherLast = artGapsTeacherLast;
	}

	public String getArtPartTimeTeacherLast() {
		return artPartTimeTeacherLast;
	}

	public void setArtPartTimeTeacherLast(String artPartTimeTeacherLast) {
		this.artPartTimeTeacherLast = artPartTimeTeacherLast;
	}

	public String getArtRatioSTLast() {
		return artRatioSTLast;
	}

	public void setArtRatioSTLast(String artRatioSTLast) {
		this.artRatioSTLast = artRatioSTLast;
	}

	public String getArtClassNumLast() {
		return artClassNumLast;
	}

	public void setArtClassNumLast(String artClassNumLast) {
		this.artClassNumLast = artClassNumLast;
	}

	public String getTrainLast() {
		return trainLast;
	}

	public void setTrainLast(String trainLast) {
		this.trainLast = trainLast;
	}

	public String getHonorLast() {
		return honorLast;
	}

	public void setHonorLast(String honorLast) {
		this.honorLast = honorLast;
	}

	public String getSelfAssessmentLast() {
		return selfAssessmentLast;
	}

	public void setSelfAssessmentLast(String selfAssessmentLast) {
		this.selfAssessmentLast = selfAssessmentLast;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getMusicShouldTeacher() {
		return musicShouldTeacher;
	}

	public void setMusicShouldTeacher(String musicShouldTeacher) {
		this.musicShouldTeacher = musicShouldTeacher;
	}

	public String getArtShouldTeacher() {
		return artShouldTeacher;
	}

	public void setArtShouldTeacher(String artShouldTeacher) {
		this.artShouldTeacher = artShouldTeacher;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getMusicRealTeacher() {
		return musicRealTeacher;
	}

	public void setMusicRealTeacher(String musicRealTeacher) {
		this.musicRealTeacher = musicRealTeacher;
	}

	public String getMusicPartTimeTeacher() {
		return musicPartTimeTeacher;
	}

	public void setMusicPartTimeTeacher(String musicPartTimeTeacher) {
		this.musicPartTimeTeacher = musicPartTimeTeacher;
	}

	public String getMusicClassNum() {
		return musicClassNum;
	}

	public void setMusicClassNum(String musicClassNum) {
		this.musicClassNum = musicClassNum;
	}

	public String getArtRealTeacher() {
		return artRealTeacher;
	}

	public void setArtRealTeacher(String artRealTeacher) {
		this.artRealTeacher = artRealTeacher;
	}

	public String getArtPartTimeTeacher() {
		return artPartTimeTeacher;
	}

	public void setArtPartTimeTeacher(String artPartTimeTeacher) {
		this.artPartTimeTeacher = artPartTimeTeacher;
	}

	public String getArtClassNum() {
		return artClassNum;
	}

	public void setArtClassNum(String artClassNum) {
		this.artClassNum = artClassNum;
	}

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public String getSelfAssessment() {
		return selfAssessment;
	}

	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}

	/**
	 * 功能的描述: 检测输入是否合法，合法后将数据存入数据库
	 * @author: 曾绩平
	 * @return: String
	 * @throws Exception 
	 * @CreateDate: 2016-8-31
	 */
	public String jump() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String  o_id = user.get("O_ID").toString();
		map = serviceManager.getOtherService().getArtData(o_id);
		setToken(getSession().get("token").toString());
		
		if (v.checkEmpty(map.get("MUS_HOUR"))) {
			errorInfo = "当前页面还有未完成的数据项，请先填写完成！";
			return "back";
		}
		

		//验证音乐教师应配专职数（musicShouldTeacher）
		if(V.isNumeric(musicShouldTeacher) == false || v.judgeSize(-1, 100000000, musicShouldTeacher) ==false){
			errorInfo="请输入正确的音乐教师应配专职数";
			return "error";
		}
				
		//验证音乐教师实有专职数（musicRealTeacher）
		if(V.isNumeric(musicRealTeacher) == false || v.judgeSize(-1, 100000000, musicRealTeacher) ==false){
			errorInfo="请输入正确的音乐教师实有专职数";
			return "error";
		}

		//验证音乐教师兼职教师数（musicPartTimeTeacher）
		if(V.isNumeric(musicPartTimeTeacher) == false || v.judgeSize(-1, 100000000, musicPartTimeTeacher) ==false){
			errorInfo="请输入正确的音乐教师兼职教师数";
			return "error";
		}

		//验证音乐教师周课时数（musicClassNum）
		if(V.isNumeric(musicClassNum) == false || v.judgeSize(-1, 100000000, musicClassNum) ==false){
			errorInfo="请输入正确的音乐教师周课时数";
			return "error";
		}

		//验证美术教师应配专职数（artShouldTeacher）
		if(V.isNumeric(artShouldTeacher) == false || v.judgeSize(-1, 100000000, artShouldTeacher) ==false){
			errorInfo="请输入正确的美术教师应配专职数";
			return "error";
		}

		//验证美术教师实有专职数（artRealTeacher）
		if(V.isNumeric(artRealTeacher) == false || v.judgeSize(-1, 100000000, artRealTeacher) ==false){
			errorInfo="请输入正确的美术教师实有专职数";
			return "error";
		}

		//验证美术教师实有专职数（artPartTimeTeacher）
		if(V.isNumeric(artPartTimeTeacher) == false || v.judgeSize(-1, 100000000, artPartTimeTeacher) ==false){
			errorInfo="请输入正确的美术教师兼职教师数";
			return "error";
		}

		//验证美术教师实有专职数（artClassNum）
		if(V.isNumeric(artClassNum) == false || v.judgeSize(-1, 100000000, artClassNum) ==false){
			errorInfo="请输入正确的美术教师周课时数";
			return "error";
		}

		//验证参加县级以上培训人数（train）
		if(V.isNumeric(train) == false || v.judgeSize(-1, 100000000, train) ==false){
			errorInfo="请输入正确的参加县级以上培训人数";
			return "error";
		}

		//验证荣获县级以上表彰人数（honor）
		if(V.isNumeric(honor) == false || v.judgeSize(-1, 100000000, honor) ==false){
			errorInfo="请输入正确的荣获县级以上表彰人数";
			return "error";
		}
		
		//验证自评得分（selfAssessment）
		if(V.isNumeric(selfAssessment) == false || v.judgeSize(-1, 21, selfAssessment) ==false){
			errorInfo="请输入正确的自评得分";
			return "error";
		}
		
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 = serviceManager.getOtherService().getArtData(o_id);
		
		if(map2 == null){
			errorInfo = "当前系统未开放！";
			return "notOpen";
		}
		
		//计算生师比
		studentNum = map2.get("STU_TOTAL").toString();
		
		if(Integer.parseInt(musicRealTeacher) == 0){
			musicRatioST = 0 + "";
		}else{
			int numRatio = Integer.parseInt(studentNum) / Integer.parseInt(musicRealTeacher);
			musicRatioST = numRatio + "";
		}
		
		if(Integer.parseInt(artRealTeacher) == 0){
			artRatioST = 0 + "";
		}else{
			int numRatio2 = Integer.parseInt(studentNum)  / Integer.parseInt(artRealTeacher);
			artRatioST = numRatio2 + "";
		}
		
		//计算专职教师缺额数
		int num = Integer.parseInt(musicShouldTeacher) - Integer.parseInt(musicRealTeacher);
		if(num < 0){
			num = 0;
		}
		musicGapsTeacher = num + "";
		
		int num2 = Integer.parseInt(artShouldTeacher) - Integer.parseInt(artRealTeacher);
		if(num2 < 0){
			num2 = 0;
		}
		artGapsTeacher = num2 + "";
		

		if(map.get("TEACHERS_MUS_IDEAL")!=null){
			
		}else{
			map.put("TEACHERS_MUS_IDEAL","");
		}
		
		if(map.get("TEACHERS_MUS_PRESENT")!=null){
			
		}else{
			map.put("TEACHERS_MUS_PRESENT","");
		}
		
		if(map.get("TEACHERS_MUS_REQUIRED")!=null){
			
		}else{
			map.put("TEACHERS_MUS_REQUIRED","");
		}
		
		if(map.get("TEACHERS_MUS_PART_TIME")!=null){
			
		}else{
			map.put("TEACHERS_MUS_PART_TIME","");
		}
		
		if(map.get("RATIO_MUS_STU_TEACHER")!=null){
			
		}else{
			map.put("RATIO_MUS_STU_TEACHER","");
		}
		
		if(map.get("HOURS_WEEKLY_MUS_TEACHER")!=null){
			
		}else{
			map.put("HOURS_WEEKLY_MUS_TEACHER","");
		}
		
		if(map.get("PAINT_TEACHERS_IDEAL")!=null){
			
		}else{
			map.put("PAINT_TEACHERS_IDEAL","");
		}
		
		if(map.get("PAINT_TEACHERS_PRESENT")!=null){
			
		}else{
			map.put("PAINT_TEACHERS_PRESENT","");
		}
		
		if(map.get("PAINT_TEACHERS_REQUIRED")!=null){
			
		}else{
			map.put("PAINT_TEACHERS_REQUIRED","");
		}
		
		if(map.get("PAINT_TEACHERS_PART_TIME")!=null){
			
		}else{
			map.put("PAINT_TEACHERS_PART_TIME","");
		}
		
		if(map.get("RATIO_PAINT_STU_TEACHER")!=null){
			
		}else{
			map.put("RATIO_PAINT_STU_TEACHER","");
		}
		
		if(map.get("HOURS_WEEKLY_PAINT_TEACHER")!=null){
			
		}else{
			map.put("HOURS_WEEKLY_PAINT_TEACHER","");
		}
		
		if(map.get("TEACHERS_ART_TRAINED")!=null){
			
		}else{
			map.put("TEACHERS_ART_TRAINED","");
		}
		
		if(map.get("TEACHERS_ART_AWARDED")!=null){
			
		}else{
			map.put("TEACHERS_ART_AWARDED","");
		}
		
		if(map.get("SELF_REMARK_ART_TEACHER")!=null){
			
		}else{
			map.put("SELF_REMARK_ART_TEACHER","");
		}
		
		if(map == null){
			errorInfo = "当前系统未开放！";
			return "notOpen";
		}
		
		
		Map<String, Object> mapNew = new HashMap<String, Object>();
		mapNew.put("TEACHERS_MUS_IDEAL", musicShouldTeacher);
		mapNew.put("TEACHERS_MUS_PRESENT", musicRealTeacher);
		mapNew.put("TEACHERS_MUS_REQUIRED", musicGapsTeacher);
		mapNew.put("TEACHERS_MUS_PART_TIME", musicPartTimeTeacher);
		mapNew.put("RATIO_MUS_STU_TEACHER", musicRatioST);
		mapNew.put("HOURS_WEEKLY_MUS_TEACHER", musicClassNum);
		mapNew.put("PAINT_TEACHERS_IDEAL", artShouldTeacher);
		mapNew.put("PAINT_TEACHERS_PRESENT", artRealTeacher);
		mapNew.put("PAINT_TEACHERS_REQUIRED", artGapsTeacher);
		mapNew.put("PAINT_TEACHERS_PART_TIME", artPartTimeTeacher);
		mapNew.put("RATIO_PAINT_STU_TEACHER", artRatioST);
		mapNew.put("HOURS_WEEKLY_PAINT_TEACHER", artClassNum);
		mapNew.put("TEACHERS_ART_TRAINED", train);
		mapNew.put("TEACHERS_ART_AWARDED", honor);
		mapNew.put("SELF_REMARK_ART_TEACHER", selfAssessment);
		
		
		/**
		 * 将前端信息存入Map,传入service类
		 */
		
		
		//判断用户是否修改了数据
		Set<String> set = mapNew.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String key = it.next();

			String val0 = map.get(key).toString();
			String val1 = mapNew.get(key).toString();
			System.out.println("-------------------------------------------------------"+val0+"   "+val1);
			if (!val0.equals(val1)) {
				System.out.println("musicShouldTeacher "+ musicShouldTeacher+"\nmusicRealTeacher "+musicRealTeacher+"\nmusicGapsTeacher "+musicGapsTeacher+"\nmusicPartTimeTeacher "+musicPartTimeTeacher+"\nmusicRatioST "+musicRatioST+"\nmusicClassNum "+musicClassNum+"\nartShouldTeacher "+artShouldTeacher+"\nartRealTeacher "+artRealTeacher+"\nartGapsTeacher "+artGapsTeacher+"\nartPartTimeTeacher "+artPartTimeTeacher+"\nartRatioST "+artRatioST+"\nartClassNum "+artClassNum+"\ntrain "+train+"\nhonor "+honor+"\nselfAssessment "+selfAssessment);
				map.put("musicShouldTeacher", musicShouldTeacher);
				map.put("musicRealTeacher", musicRealTeacher);
				map.put("musicGapsTeacher", musicGapsTeacher);
				map.put("musicPartTimeTeacher", musicPartTimeTeacher);
				map.put("musicRatioST", musicRatioST);
				map.put("musicClassNum", musicClassNum);
				map.put("artShouldTeacher", artShouldTeacher);
				map.put("artRealTeacher", artRealTeacher);
				map.put("artGapsTeacher", artGapsTeacher);
				map.put("artPartTimeTeacher", artPartTimeTeacher);
				map.put("artRatioST", artRatioST);
				map.put("artClassNum", artClassNum);
				map.put("train", train);
				map.put("honor", honor);
				map.put("selfAssessment", selfAssessment);
				map.put("o_id", o_id);
				map.put("c_id", getSession().get(Constants.LOGIN_USER_C_ID).toString());
				serviceManager.getArtSecondService().insertArtSecond(map);
				System.out.println("-------------------------------------------------------已修改");
				return "success";
			}
		}
		System.out.println("-------------------------------------------------------未修改");
		return SUCCESS;
	}
	
	/**
	 * 功能的描述: 默认执行方法，将数据库的内容取出后放到页面
	 * @author: 曾绩平
	 * @return: String
	 * @throws Exception: Exception
	 * @CreateDate: 2016-9-7
	 */
	public String execute() throws Exception {
		setToken(getSession().get("token").toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapLast = new HashMap<String, Object>();
		
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String  o_id2 = user.get("O_ID").toString();
		map = serviceManager.getOtherService().getArtData(o_id2);
		mapLast = serviceManager.getOtherService().getArtLastYearData(o_id2);
		
		if (v.checkEmpty(map.get("MUS_HOUR"))) {
			errorInfo = "当前页面还有未完成的数据项，请先填写完成！";
			return "back";
		}

		
		if( map == null ){
			errorInfo = "当前系统未开放！";
			return "notOpen";
		}
		
		
		if(map.get("STU_TOTAL")!=null){
			studentNum = map.get("STU_TOTAL").toString();
		}
		
		if(map.get("TEACHERS_MUS_IDEAL")!=null){
			musicShouldTeacher = map.get("TEACHERS_MUS_IDEAL").toString();
		}
		
		if(map.get("TEACHERS_MUS_PRESENT")!=null){
			musicRealTeacher = map.get("TEACHERS_MUS_PRESENT").toString();
		}
		
		if(map.get("TEACHERS_MUS_REQUIRED")!=null){
			musicGapsTeacher = map.get("TEACHERS_MUS_REQUIRED").toString();
		}
		
		if(map.get("TEACHERS_MUS_PART_TIME")!=null){
			musicPartTimeTeacher = map.get("TEACHERS_MUS_PART_TIME").toString();
		}
		
		if(map.get("RATIO_MUS_STU_TEACHER")!=null){
			musicRatioST = map.get("RATIO_MUS_STU_TEACHER").toString();
		}
		
		if(map.get("HOURS_WEEKLY_MUS_TEACHER")!=null){
			musicClassNum = map.get("HOURS_WEEKLY_MUS_TEACHER").toString();
		}
		
		if(map.get("PAINT_TEACHERS_IDEAL")!=null){
			artShouldTeacher = map.get("PAINT_TEACHERS_IDEAL").toString();
		}
		
		if(map.get("PAINT_TEACHERS_PRESENT")!=null){
			artRealTeacher = map.get("PAINT_TEACHERS_PRESENT").toString();
		}
		
		if(map.get("PAINT_TEACHERS_REQUIRED")!=null){
			artGapsTeacher = map.get("PAINT_TEACHERS_REQUIRED").toString();
		}
		
		if(map.get("PAINT_TEACHERS_PART_TIME")!=null){
			artPartTimeTeacher = map.get("PAINT_TEACHERS_PART_TIME").toString();
		}
		
		if(map.get("RATIO_PAINT_STU_TEACHER")!=null){
			artRatioST = map.get("RATIO_PAINT_STU_TEACHER").toString();
		}
		
		if(map.get("HOURS_WEEKLY_PAINT_TEACHER")!=null){
			artClassNum = map.get("HOURS_WEEKLY_PAINT_TEACHER").toString();
		}
		
		if(map.get("TEACHERS_ART_TRAINED")!=null){
			train = map.get("TEACHERS_ART_TRAINED").toString();
		}
		
		if(map.get("TEACHERS_ART_AWARDED")!=null){
			honor = map.get("TEACHERS_ART_AWARDED").toString();
		}
		
		if(map.get("SELF_REMARK_ART_TEACHER")!=null){
			selfAssessment = map.get("SELF_REMARK_ART_TEACHER").toString();
		}
		
		if (mapLast !=null) {
			musicShouldTeacherLast = mapLast.get("TEACHERS_MUS_IDEAL").toString();
			musicRealTeacherLast = mapLast.get("TEACHERS_MUS_PRESENT").toString();
			musicGapsTeacherLast = mapLast.get("TEACHERS_MUS_REQUIRED").toString();
			musicPartTimeTeacherLast = mapLast.get("TEACHERS_MUS_PART_TIME").toString();
			musicRatioSTLast = mapLast.get("RATIO_MUS_STU_TEACHER").toString();
			musicClassNumLast = mapLast.get("HOURS_WEEKLY_MUS_TEACHER").toString();
			artShouldTeacherLast = mapLast.get("PAINT_TEACHERS_IDEAL").toString();
			artRealTeacherLast = mapLast.get("PAINT_TEACHERS_PRESENT").toString();
			artGapsTeacherLast = mapLast.get("PAINT_TEACHERS_REQUIRED").toString();
			artPartTimeTeacherLast = mapLast.get("PAINT_TEACHERS_PART_TIME").toString();
			artRatioSTLast = mapLast.get("RATIO_PAINT_STU_TEACHER").toString();
			artClassNumLast = mapLast.get("HOURS_WEEKLY_PAINT_TEACHER").toString();
			trainLast = mapLast.get("TEACHERS_ART_TRAINED").toString();
			honorLast = mapLast.get("TEACHERS_ART_AWARDED").toString();
			selfAssessmentLast = mapLast.get("SELF_REMARK_ART_TEACHER").toString();
		}
		
		return SUCCESS;
	}
}

