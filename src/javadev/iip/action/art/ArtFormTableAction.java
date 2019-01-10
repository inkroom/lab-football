package javadev.iip.action.art;

import java.text.DecimalFormat;
import java.util.Map;


import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

public class ArtFormTableAction extends BaseAction {

	
	    private String schoolType;
	    private String classNum;
	    private String studentNum;
		private String musicClass;
		private String artClass;	
		private String integratedArtClass;
		private String localArtClass;
		private String enoughClass;
		private String srscroeClass;
		private String screenings;
		private String frequency;
		private String artSocieties;
		private String participating;
		private String srscroeActivity;
		
		private String musicShouldTeacher;
		private String musicRealTeacher;
		private String musicGapsTeacher;
		private String musicPartTimeTeacher;
		private String musicRatioST;
		private String musicClassNum;
		private String artShouldTeacher;
		private String artRealTeacher;
		private String artGapsTeacher;
		private String artPartTimeTeacher;
		private String artRatioST;
		private String artClassNum;
		private String train;
		private String honor;
		private String selfAssessment;
		
		private String musClassroomIdeal;
		private String musClassroomPresent;
		private String musClassroomPequired;
		private String paintClassroomIdeal;
		private String paintClassroomPresent;
		private String paintPequired;
		private String isEquipQualified;
		private String fundRequired;
		private String artClassroomOther;
		private String artVenuesNum;
		private String venuesArea;
		private String selfRemarkEnsurance;
		
		
		private String features;
		private String evaluationOne;
		private String assessment;
		private String good;
		private String fine;
		private String qualified;
		private String notQualified;
		private String artEvaluation;
		private String classRoomPay;
		private String artEdaPay;
		private String artPay;
		
		private String artCurriculumProblems;	
		private String artCurriculumImprovement;		
		private String artActivityProblems;
		private String artActivityImprovement;
		private String artTeacherProblems;	
		private String artTeacherImprovement;
		private String conditionGuaranteeProblems;
		private String conditionGuaranteeImprovement;
		private String characteristicProblems;
		private String characteristicImprovement;
		private String assessmentProblems;
		private String assessmentImprovement;
		private String artCousreNames;
		private String artClubName;
		private String artCultureAtmosphere;
		private String artVenueNamesOther;
		private String achieveArtFeature;
		
		private String addScore;
        private String addType;
		
		private String token;
		private String errorInfo;
	    private static V v=new V();
	    private ServiceManager serviceManager;
	    
	    private String goodRatio;
	    private String fineRatio;
	    private String qualifiedRatio;
	    private String notQualifiedRatio;

		public String execute() throws Exception{

			
			setToken(getSession().get("token").toString());
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
			String o_id = user.get("O_ID").toString();
			Map<String, Object> mapSport = getServiceManager().getOtherService().getSportData(o_id);
			Map<String, Object> mapArt = serviceManager.getOtherService().getArtData(o_id);
			
			
			if (mapArt == null) {
				errorInfo = "当前系统为开放";
				return "noOpen";
			}

		
			if (!mapArt.get("IS_FINISH_WRITE").toString().equals("1")) {
				errorInfo="当前系统存在未填写完成内容";
				return "backView";
			}
			
			
			
			
			if (!v.checkEmpty(mapSport.get("STAGE"))) {
				schoolType=mapSport.get("STAGE").toString();
			}if (!v.checkEmpty(mapSport.get("CLASS_TOTAL"))) {
				classNum=mapSport.get("CLASS_TOTAL").toString();
			}if (!v.checkEmpty(mapSport.get("STU_TOTAL"))) {
				studentNum=mapSport.get("STU_TOTAL").toString();
			}
			
			if(!v.checkEmpty(mapArt.get("MUS_HOUR"))){
	        	musicClass=mapArt.get("MUS_HOUR").toString();
	        }if(!v.checkEmpty(mapArt.get("PAINT_HOUR"))){
	        	artClass=mapArt.get("PAINT_HOUR").toString();
	        }if(!v.checkEmpty(mapArt.get("ART_COMPREHENSIVE_HOUR"))){
	            integratedArtClass=mapArt.get("ART_COMPREHENSIVE_HOUR").toString();
	        }if(!v.checkEmpty(mapArt.get("ART_HOUR"))){
	            localArtClass=mapArt.get("ART_HOUR").toString();
	        }if(!v.checkEmpty(mapArt.get("IS_COMPLETE"))){
	        	enoughClass=mapArt.get("IS_COMPLETE").toString();
	        }if(!v.checkEmpty(mapArt.get("SELF_REMARK_ART_COURSE"))){
	        	srscroeClass=mapArt.get("SELF_REMARK_ART_COURSE").toString();
	        }if(!v.checkEmpty(mapArt.get("ART_FESTIVAL_ANNUAL"))){	
	        	screenings=mapArt.get("ART_FESTIVAL_ANNUAL").toString();
	        }if(!v.checkEmpty(mapArt.get("ART_ACTIVITIES_WEEKLY"))){
	        	frequency=mapArt.get("ART_ACTIVITIES_WEEKLY").toString();
	        }if(!v.checkEmpty(mapArt.get("CLUB_TOTAL"))){
	        	artSocieties=mapArt.get("CLUB_TOTAL").toString();
	        }if(!v.checkEmpty(mapArt.get("ACTIVITIES_PARTICIPATE_RATIO"))){
	        	participating=mapArt.get("ACTIVITIES_PARTICIPATE_RATIO").toString();
	        }if(!v.checkEmpty(mapArt.get("SELF_REMARK_ART_ACTIVITY"))){
	        	srscroeActivity=mapArt.get("SELF_REMARK_ART_ACTIVITY").toString();
	        }
	        
	        
	        if(mapArt.get("TEACHERS_MUS_IDEAL")!=null){
				musicShouldTeacher = mapArt.get("TEACHERS_MUS_IDEAL").toString();
			}if(mapArt.get("TEACHERS_MUS_PRESENT")!=null){
				musicRealTeacher = mapArt.get("TEACHERS_MUS_PRESENT").toString();
			}if(mapArt.get("TEACHERS_MUS_REQUIRED")!=null){
				musicGapsTeacher = mapArt.get("TEACHERS_MUS_REQUIRED").toString();
			}if(mapArt.get("TEACHERS_MUS_PART_TIME")!=null){
				musicPartTimeTeacher = mapArt.get("TEACHERS_MUS_PART_TIME").toString();
			}if(mapArt.get("RATIO_MUS_STU_TEACHER")!=null){
				musicRatioST = mapArt.get("RATIO_MUS_STU_TEACHER").toString();
			}if(mapArt.get("HOURS_WEEKLY_MUS_TEACHER")!=null){
				musicClassNum = mapArt.get("HOURS_WEEKLY_MUS_TEACHER").toString();
			}if(mapArt.get("PAINT_TEACHERS_IDEAL")!=null){
				artShouldTeacher = mapArt.get("PAINT_TEACHERS_IDEAL").toString();
			}if(mapArt.get("PAINT_TEACHERS_PRESENT")!=null){
				artRealTeacher = mapArt.get("PAINT_TEACHERS_PRESENT").toString();
			}if(mapArt.get("PAINT_TEACHERS_REQUIRED")!=null){
				artGapsTeacher = mapArt.get("PAINT_TEACHERS_REQUIRED").toString();
			}if(mapArt.get("PAINT_TEACHERS_PART_TIME")!=null){
				artPartTimeTeacher = mapArt.get("PAINT_TEACHERS_PART_TIME").toString();
			}if(mapArt.get("RATIO_PAINT_STU_TEACHER")!=null){
				artRatioST = mapArt.get("RATIO_PAINT_STU_TEACHER").toString();
			}if(mapArt.get("HOURS_WEEKLY_PAINT_TEACHER")!=null){
				artClassNum = mapArt.get("HOURS_WEEKLY_PAINT_TEACHER").toString();
			}if(mapArt.get("TEACHERS_ART_TRAINED")!=null){
				train = mapArt.get("TEACHERS_ART_TRAINED").toString();
			}if(mapArt.get("TEACHERS_ART_AWARDED")!=null){
				honor = mapArt.get("TEACHERS_ART_AWARDED").toString();
			}if(mapArt.get("SELF_REMARK_ART_TEACHER")!=null){
				selfAssessment = mapArt.get("SELF_REMARK_ART_TEACHER").toString();
			}
	        
			
			if(!v.checkEmpty(mapArt.get("MUS_CLASSROOM_IDEAL"))) {
				musClassroomIdeal=mapArt.get("MUS_CLASSROOM_IDEAL").toString();
			}if(!v.checkEmpty(mapArt.get("MUS_CLASSROOM_PRESENT"))){
				musClassroomPresent=mapArt.get("MUS_CLASSROOM_PRESENT").toString();
			}if(!v.checkEmpty(mapArt.get("MUS_CLASSROOM_REQUIRED"))) {
				musClassroomPequired=mapArt.get("MUS_CLASSROOM_REQUIRED").toString();
			}if(!v.checkEmpty(mapArt.get("PAINT_CLASSROOM_IDEAL"))) {
				paintClassroomIdeal=mapArt.get("PAINT_CLASSROOM_IDEAL").toString();
			}if(!v.checkEmpty(mapArt.get("PAINT_CLASSROOM_PRESENT"))) {
				paintClassroomPresent=mapArt.get("PAINT_CLASSROOM_PRESENT").toString();
			}if(!v.checkEmpty(mapArt.get("PAINT_CLASSROOM_REQUIRED"))) {
				paintPequired=mapArt.get("PAINT_CLASSROOM_REQUIRED").toString();
			}if(!v.checkEmpty(mapArt.get("IS_EQUIP_QUALIFIED"))) {
				isEquipQualified=mapArt.get("IS_EQUIP_QUALIFIED").toString();
			}if(!v.checkEmpty(mapArt.get("FUND_REQUIRED"))) {
				fundRequired=mapArt.get("FUND_REQUIRED").toString();
			}if(!v.checkEmpty(mapArt.get("ART_CLASSROOM_OTHER"))) {
				artClassroomOther=mapArt.get("ART_CLASSROOM_OTHER").toString();
			}if(!v.checkEmpty(mapArt.get("ART_VENUES_NUM"))) {
				artVenuesNum=mapArt.get("ART_VENUES_NUM").toString();
			}if(!v.checkEmpty(mapArt.get("VENUES_AREA"))) {
				venuesArea=mapArt.get("VENUES_AREA").toString();
			}if(!v.checkEmpty(mapArt.get("SELF_REMARK_ENSURANCE"))) {
				selfRemarkEnsurance=mapArt.get("SELF_REMARK_ENSURANCE").toString();
			}
			
			
			if (!v.checkEmpty(mapArt.get("IS_FEATURED"))) {
				setFeatures(mapArt.get("IS_FEATURED").toString());
			}
			if (!v.checkEmpty(mapArt.get("SELF_REMARK_FEATURE"))) {
				evaluationOne = mapArt.get("SELF_REMARK_FEATURE").toString();
			}
			if (!v.checkEmpty(mapArt.get("IS_TEST_PERFORMED"))) {
				setAssessment(mapArt.get("IS_TEST_PERFORMED").toString());
			}
			if (!v.checkEmpty(mapArt.get("STUDENTS_EXCELLENT"))) {
				good = mapArt.get("STUDENTS_EXCELLENT").toString();
			}
			if (!v.checkEmpty(mapArt.get("STUDENTS_GOOD"))) {
				fine = mapArt.get("STUDENTS_GOOD").toString();
			}
			if (!v.checkEmpty(mapArt.get("STUDENTS_PASS"))) {
				qualified = mapArt.get("STUDENTS_PASS").toString();
			}
			if (!v.checkEmpty(mapArt.get("STUDENTS_FAIL"))) {
				notQualified = mapArt.get("STUDENTS_FAIL").toString();
			}
			if (!v.checkEmpty(mapArt.get("SELF_REMARK_ART_QUALITY"))) {
				artEvaluation = mapArt.get("SELF_REMARK_ART_QUALITY").toString();
			}
			if (!v.checkEmpty(mapArt.get("EXPENSE_ART_CONSTRUCTION"))) {
				classRoomPay = mapArt.get("EXPENSE_ART_CONSTRUCTION").toString();
			}
			if (!v.checkEmpty(mapArt.get("EXPENSE_ART_EQUIP"))) {
				artEdaPay = mapArt.get("EXPENSE_ART_EQUIP").toString();
			}
			if (!v.checkEmpty(mapArt.get("EXPENSE_ACTIVITY"))) {
				artPay = mapArt.get("EXPENSE_ACTIVITY").toString();
			}
			
			 if(mapArt.get("PROBLEM_IN_ART_COURSE")!=null){
				 artCurriculumProblems=mapArt.get("PROBLEM_IN_ART_COURSE").toString();      
			 }
			 if(mapArt.get("SOLUTION_TO_PROBLEM_ART_COURSE")!=null){
				 artCurriculumImprovement=mapArt.get("SOLUTION_TO_PROBLEM_ART_COURSE").toString();
		      }
			 if(mapArt.get("PROBLEM_ART_ACTIVITY")!=null){
				 artActivityProblems=mapArt.get("PROBLEM_ART_ACTIVITY").toString();
		      }
			 if(mapArt.get("SOLUTION_TO_PROBLEM_ACTIVITY")!=null){
				 artActivityImprovement=mapArt.get("SOLUTION_TO_PROBLEM_ACTIVITY").toString();
		      }
			 if(mapArt.get("PROBLEM_ART_TEACHER")!=null){
				 artTeacherProblems=mapArt.get("PROBLEM_ART_TEACHER").toString();
		      }
			 if(mapArt.get("SOLUTION_TO_PROBLEM_TEACHER")!=null){
				 artTeacherImprovement=mapArt.get("SOLUTION_TO_PROBLEM_TEACHER").toString();
		      }
			 if(mapArt.get("PROBLEM_ENSURANCE")!=null){
				 conditionGuaranteeProblems=mapArt.get("PROBLEM_ENSURANCE").toString();
		      }
			 if(mapArt.get("SOLUTION_TO_PROBLEM_ENSURANCE")!=null){
				 conditionGuaranteeImprovement=mapArt.get("SOLUTION_TO_PROBLEM_ENSURANCE").toString();
		      }
			 if(mapArt.get("PROBLEM_ART_FEATURE")!=null){
				 characteristicProblems=mapArt.get("PROBLEM_ART_FEATURE").toString();
		      }
			 if(mapArt.get("SOLUTION_TO_PROBLEM_FEATURE")!=null){
				 characteristicImprovement=mapArt.get("SOLUTION_TO_PROBLEM_FEATURE").toString();
		      }
			 if(mapArt.get("PROBLEM_ART_QUALITY")!=null){
				 assessmentProblems=mapArt.get("PROBLEM_ART_QUALITY").toString();
		      } 
			 if(mapArt.get("SOLUTION_TO_QUALITY")!=null){
				 assessmentImprovement=mapArt.get("SOLUTION_TO_QUALITY").toString();
		      }	 
			 if(mapArt.get("ART_COURSE_NAMES")!=null){
				 artCousreNames=mapArt.get("ART_COURSE_NAMES").toString();
		      }
			 if(mapArt.get("ART_CLUB_NAME")!=null){
				 artClubName=mapArt.get("ART_CLUB_NAME").toString();
		      }
			 if(mapArt.get("ART_CULTURE_ATMOSPHERE")!=null){
				 artCultureAtmosphere=mapArt.get("ART_CULTURE_ATMOSPHERE").toString();
		      }
			 if(mapArt.get("ART_VENUE_NAMES_OTHER")!=null){
				 artVenueNamesOther=mapArt.get("ART_VENUE_NAMES_OTHER").toString();
		      }
			 if(mapArt.get("ACHIEVE_ART_FEATURE")!=null){
				 achieveArtFeature=mapArt.get("ACHIEVE_ART_FEATURE").toString();
		      }
			 
			 DecimalFormat df = new DecimalFormat("0.00");
			 System.out.println("_________________________________________________fslafjodisflkxvxcvxdfsd"+good);
			 
			 if(!v.checkEmpty(good)){
				 float ratio1 = (float) Integer.parseInt(good)*100/Integer.parseInt(studentNum);
				 goodRatio = df.format(ratio1) + "";
			 }
			 if(!v.checkEmpty(fine)){
				 float ratio2 = (float) Integer.parseInt(fine)*100/Integer.parseInt(studentNum);
				 fineRatio = df.format(ratio2) + "";
			 }
			 if(!v.checkEmpty(qualified)){
				 float ratio3 = (float) Integer.parseInt(qualified)*100/Integer.parseInt(studentNum);
				 qualifiedRatio = df.format(ratio3) + "";
			 }
			 if(!v.checkEmpty(notQualified)){
				 float ratio4 = (float) Integer.parseInt(notQualified)*100/Integer.parseInt(studentNum);
				 notQualifiedRatio = df.format(ratio4) + "";
			 }
			 
			 
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
			    if (mapArt.get("SELF_REMARK_FEATURE")!=null) {
					String art5=mapArt.get("SELF_REMARK_FEATURE").toString();
					val5=Integer.parseInt(art5);
				}
			    if (mapArt.get("SELF_REMARK_ART_QUALITY")!=null) {
					String art6=mapArt.get("SELF_REMARK_ART_QUALITY").toString();
					val6=Integer.parseInt(art6);
				}
			   
	
			    val=val1+val2+val3+val4+val5+val6;
			    addScore=val+"";
			    
			    if (val>=60&&val<=74) {
					addType="3";
				}else if(val>=75&&val<89){
					addType="2";
				}else if(val>=89){
					addType="1";
				}else {
					addType="4";
				}
			    
			return "success";
		}
		
		
		
		public String getClassNum() {
			return classNum;
		}
		public void setClassNum(String classNum) {
			this.classNum = classNum;
		}
		public String getStudentNum() {
			return studentNum;
		}
		public void setStudentNum(String studentNum) {
			this.studentNum = studentNum;
		}
		public String getSchoolType() {
			return schoolType;
		}
		public void setSchoolType(String schoolType) {
			this.schoolType = schoolType;
		}
     	public String getMusicClass() {
			return musicClass;
		}
		public void setMusicClass(String musicClass) {
			this.musicClass = musicClass;
		}
		public String getArtClass() {
			return artClass;
		}
		public void setArtClass(String artClass) {
			this.artClass = artClass;
		}
		public String getIntegratedArtClass() {
			return integratedArtClass;
		}
		public void setIntegratedArtClass(String integratedArtClass) {
			this.integratedArtClass = integratedArtClass;
		}
		public String getLocalArtClass() {
			return localArtClass;
		}
		public void setLocalArtClass(String localArtClass) {
			this.localArtClass = localArtClass;
		}
		public String getEnoughClass() {
			return enoughClass;
		}
		public void setEnoughClass(String enoughClass) {
			this.enoughClass = enoughClass;
		}
		public String getSrscroeClass() {
			return srscroeClass;
		}
		public void setSrscroeClass(String srscroeClass) {
			this.srscroeClass = srscroeClass;
		}
		public String getScreenings() {
			return screenings;
		}
		public void setScreenings(String screenings) {
			this.screenings = screenings;
		}
		public String getFrequency() {
			return frequency;
		}
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		public String getArtSocieties() {
			return artSocieties;
		}
		public void setArtSocieties(String artSocieties) {
			this.artSocieties = artSocieties;
		}
		public String getParticipating() {
			return participating;
		}
		public void setParticipating(String participating) {
			this.participating = participating;
		}
		public String getSrscroeActivity() {
			return srscroeActivity;
		}
		public void setSrscroeActivity(String srscroeActivity) {
			this.srscroeActivity = srscroeActivity;
		}
		public String getMusicShouldTeacher() {
			return musicShouldTeacher;
		}
		public void setMusicShouldTeacher(String musicShouldTeacher) {
			this.musicShouldTeacher = musicShouldTeacher;
		}
		public String getMusicRealTeacher() {
			return musicRealTeacher;
		}
		public void setMusicRealTeacher(String musicRealTeacher) {
			this.musicRealTeacher = musicRealTeacher;
		}
		public String getMusicGapsTeacher() {
			return musicGapsTeacher;
		}
		public void setMusicGapsTeacher(String musicGapsTeacher) {
			this.musicGapsTeacher = musicGapsTeacher;
		}
		public String getMusicPartTimeTeacher() {
			return musicPartTimeTeacher;
		}
		public void setMusicPartTimeTeacher(String musicPartTimeTeacher) {
			this.musicPartTimeTeacher = musicPartTimeTeacher;
		}
		public String getMusicRatioST() {
			return musicRatioST;
		}
		public void setMusicRatioST(String musicRatioST) {
			this.musicRatioST = musicRatioST;
		}
		public String getMusicClassNum() {
			return musicClassNum;
		}
		public void setMusicClassNum(String musicClassNum) {
			this.musicClassNum = musicClassNum;
		}
		public String getArtShouldTeacher() {
			return artShouldTeacher;
		}
		public void setArtShouldTeacher(String artShouldTeacher) {
			this.artShouldTeacher = artShouldTeacher;
		}
		public String getArtRealTeacher() {
			return artRealTeacher;
		}
		public void setArtRealTeacher(String artRealTeacher) {
			this.artRealTeacher = artRealTeacher;
		}
		public String getArtGapsTeacher() {
			return artGapsTeacher;
		}
		public void setArtGapsTeacher(String artGapsTeacher) {
			this.artGapsTeacher = artGapsTeacher;
		}
		public String getArtPartTimeTeacher() {
			return artPartTimeTeacher;
		}
		public void setArtPartTimeTeacher(String artPartTimeTeacher) {
			this.artPartTimeTeacher = artPartTimeTeacher;
		}
		public String getArtRatioST() {
			return artRatioST;
		}
		public void setArtRatioST(String artRatioST) {
			this.artRatioST = artRatioST;
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
		public String getMusClassroomIdeal() {
			return musClassroomIdeal;
		}
		public void setMusClassroomIdeal(String musClassroomIdeal) {
			this.musClassroomIdeal = musClassroomIdeal;
		}
		public String getMusClassroomPresent() {
			return musClassroomPresent;
		}
		public void setMusClassroomPresent(String musClassroomPresent) {
			this.musClassroomPresent = musClassroomPresent;
		}
		public String getMusClassroomPequired() {
			return musClassroomPequired;
		}
		public void setMusClassroomPequired(String musClassroomPequired) {
			this.musClassroomPequired = musClassroomPequired;
		}
		public String getPaintClassroomIdeal() {
			return paintClassroomIdeal;
		}
		public void setPaintClassroomIdeal(String paintClassroomIdeal) {
			this.paintClassroomIdeal = paintClassroomIdeal;
		}
		public String getPaintClassroomPresent() {
			return paintClassroomPresent;
		}
		public void setPaintClassroomPresent(String paintClassroomPresent) {
			this.paintClassroomPresent = paintClassroomPresent;
		}
		public String getPaintPequired() {
			return paintPequired;
		}
		public void setPaintPequired(String paintPequired) {
			this.paintPequired = paintPequired;
		}
		public String getIsEquipQualified() {
			return isEquipQualified;
		}
		public void setIsEquipQualified(String isEquipQualified) {
			this.isEquipQualified = isEquipQualified;
		}
		public String getFundRequired() {
			return fundRequired;
		}
		public void setFundRequired(String fundRequired) {
			this.fundRequired = fundRequired;
		}
		public String getArtClassroomOther() {
			return artClassroomOther;
		}
		public void setArtClassroomOther(String artClassroomOther) {
			this.artClassroomOther = artClassroomOther;
		}
		public String getArtVenuesNum() {
			return artVenuesNum;
		}
		public void setArtVenuesNum(String artVenuesNum) {
			this.artVenuesNum = artVenuesNum;
		}
		public String getVenuesArea() {
			return venuesArea;
		}
		public void setVenuesArea(String venuesArea) {
			this.venuesArea = venuesArea;
		}
		public String getSelfRemarkEnsurance() {
			return selfRemarkEnsurance;
		}
		public void setSelfRemarkEnsurance(String selfRemarkEnsurance) {
			this.selfRemarkEnsurance = selfRemarkEnsurance;
		}
		public ServiceManager getServiceManager() {
			return serviceManager;
		}
		public void setServiceManager(ServiceManager serviceManager) {
			this.serviceManager = serviceManager;
		}
		public String getFeatures() {
			return features;
		}
		public void setFeatures(String features) {
			this.features = features;
		}
		public String getEvaluationOne() {
			return evaluationOne;
		}
		public void setEvaluationOne(String evaluationOne) {
			this.evaluationOne = evaluationOne;
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
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getErrorInfo() {
			return errorInfo;
		}
		public void setErrorInfo(String errorInfo) {
			this.errorInfo = errorInfo;
		}
		public String getArtCurriculumProblems() {
			return artCurriculumProblems;
		}
		public void setArtCurriculumProblems(String artCurriculumProblems) {
			this.artCurriculumProblems = artCurriculumProblems;
		}
		public String getArtCurriculumImprovement() {
			return artCurriculumImprovement;
		}
		public void setArtCurriculumImprovement(String artCurriculumImprovement) {
			this.artCurriculumImprovement = artCurriculumImprovement;
		}
		public String getArtActivityProblems() {
			return artActivityProblems;
		}
		public void setArtActivityProblems(String artActivityProblems) {
			this.artActivityProblems = artActivityProblems;
		}
		public String getArtActivityImprovement() {
			return artActivityImprovement;
		}
		public void setArtActivityImprovement(String artActivityImprovement) {
			this.artActivityImprovement = artActivityImprovement;
		}
		public String getArtTeacherProblems() {
			return artTeacherProblems;
		}
		public void setArtTeacherProblems(String artTeacherProblems) {
			this.artTeacherProblems = artTeacherProblems;
		}
		public String getArtTeacherImprovement() {
			return artTeacherImprovement;
		}
		public void setArtTeacherImprovement(String artTeacherImprovement) {
			this.artTeacherImprovement = artTeacherImprovement;
		}
		public String getConditionGuaranteeProblems() {
			return conditionGuaranteeProblems;
		}
		public void setConditionGuaranteeProblems(String conditionGuaranteeProblems) {
			this.conditionGuaranteeProblems = conditionGuaranteeProblems;
		}
		public String getConditionGuaranteeImprovement() {
			return conditionGuaranteeImprovement;
		}
		public void setConditionGuaranteeImprovement(String conditionGuaranteeImprovement) {
			this.conditionGuaranteeImprovement = conditionGuaranteeImprovement;
		}
		public String getCharacteristicProblems() {
			return characteristicProblems;
		}
		public void setCharacteristicProblems(String characteristicProblems) {
			this.characteristicProblems = characteristicProblems;
		}
		public String getCharacteristicImprovement() {
			return characteristicImprovement;
		}
		public void setCharacteristicImprovement(String characteristicImprovement) {
			this.characteristicImprovement = characteristicImprovement;
		}
		public String getAssessmentProblems() {
			return assessmentProblems;
		}
		public void setAssessmentProblems(String assessmentProblems) {
			this.assessmentProblems = assessmentProblems;
		}
		public String getAssessmentImprovement() {
			return assessmentImprovement;
		}
		public void setAssessmentImprovement(String assessmentImprovement) {
			this.assessmentImprovement = assessmentImprovement;
		}
		public String getArtCousreNames() {
			return artCousreNames;
		}
		public void setArtCousreNames(String artCousreNames) {
			this.artCousreNames = artCousreNames;
		}
		public String getArtClubName() {
			return artClubName;
		}
		public void setArtClubName(String artClubName) {
			this.artClubName = artClubName;
		}
		public String getArtCultureAtmosphere() {
			return artCultureAtmosphere;
		}
		public void setArtCultureAtmosphere(String artCultureAtmosphere) {
			this.artCultureAtmosphere = artCultureAtmosphere;
		}
		public String getArtVenueNamesOther() {
			return artVenueNamesOther;
		}
		public void setArtVenueNamesOther(String artVenueNamesOther) {
			this.artVenueNamesOther = artVenueNamesOther;
		}
		public String getAchieveArtFeature() {
			return achieveArtFeature;
		}
		public void setAchieveArtFeature(String achieveArtFeature) {
			this.achieveArtFeature = achieveArtFeature;
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
		public void setArtEdaPay(String artEdaPay) {
			this.artEdaPay = artEdaPay;
		}
		public String getArtPay() {
			return artPay;
		}
		public void setArtPay(String artPay) {
			this.artPay = artPay;
		}



		public String getAddScore() {
			return addScore;
		}



		public String getAddType() {
			return addType;
		}



		public void setAddType(String addType) {
			this.addType = addType;
		}



		public void setAddScore(String addScore) {
			this.addScore = addScore;
		}



		public String getGoodRatio() {
			return goodRatio;
		}



		public void setGoodRatio(String goodRatio) {
			this.goodRatio = goodRatio;
		}



		public String getFineRatio() {
			return fineRatio;
		}



		public void setFineRatio(String fineRatio) {
			this.fineRatio = fineRatio;
		}



		public String getQualifiedRatio() {
			return qualifiedRatio;
		}



		public void setQualifiedRatio(String qualifiedRatio) {
			this.qualifiedRatio = qualifiedRatio;
		}



		public String getNotQualifiedRatio() {
			return notQualifiedRatio;
		}



		public void setNotQualifiedRatio(String notQualifiedRatio) {
			this.notQualifiedRatio = notQualifiedRatio;
		}
		
		
		
}
