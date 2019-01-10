package javadev.iip.action.art;

import java.util.HashMap;

import java.util.Map;


import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

/**                                        
 * 功能描述：艺术工作年度自评表                   
 * @author： 朱明民                 
 * @version：1.0                     
 * create Date ：<创建日期，格式: 2016.9.7> 
 */ 

public class ArtSomeAction extends BaseAction{
	//艺术课程存在的主要问题
	private String artCurriculumProblems;
	//艺术课程改进措施
	private String artCurriculumImprovement;
	//艺术活动存在的主要问题
	private String artActivityProblems;
	//艺术活动改进措施
	private String artActivityImprovement;
	//艺术教师存在的主要问题
	private String artTeacherProblems;
	//艺术教师改进措施
	private String artTeacherImprovement;
	//条件保障存在的主要问题
	private String conditionGuaranteeProblems;
	//条件保障改进措施
	private String conditionGuaranteeImprovement;
	//特色发展存在的主要问题
	private String characteristicProblems;
	//特色发展改进措施
	private String characteristicImprovement;
	//学生艺术素质测评存在的主要问题
	private String assessmentProblems;
	//学生艺术素质测评改进措施
	private String assessmentImprovement;
	//地方艺术课程名称
	private String artCousreNames;
	//学生艺术社团项目名称
	private String artClubName;
	//校园文化艺术环境基本情况
	private String artCultureAtmosphere;
	//其他艺术活动室或艺术场馆名称
	private String artVenueNamesOther;
	//学校艺术教育特色发展成果
	private String achieveArtFeature;
	
	private String errorInfo;
	private ServiceManager serviceManager;
	private static V v =  new V();
	private String token;

	@SuppressWarnings({ "unchecked", "unused"})
	
	public String artSomeSelf(){
		setToken(getSession().get("token").toString());
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> userself = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String  o_id = userself.get("O_ID").toString();
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 =serviceManager.getOtherService().getArtData(o_id);
		
		if (map==null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}
		if (map2==null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}
		
		if (v.checkEmpty(map2.get("STUDENTS_GOOD"))) {
			errorInfo = "艺术表尚未填写完毕！";
			return "someBack";
		}

		
		
		//验证12个输入框输入字数不超过200
		if(V.isChinese(artCousreNames)==false){
			errorInfo = "请正确输入地方艺术课程名称！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artClubName)==false){
			errorInfo = "请正确输入学生艺术社团项目名称！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artCultureAtmosphere)==false){
			errorInfo = "请正确输入校园文化艺术环境基本情况！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artVenueNamesOther)==false){
			errorInfo = "请正确输入其他艺术活动室或艺术场馆名称！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(achieveArtFeature)==false){
			errorInfo = "请正确输入学校艺术教育特色发展成果！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artCurriculumProblems)==false){
			errorInfo = "请正确输入艺术课程存在的主要问题！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artCurriculumImprovement)==false){
			errorInfo = "请正确输入艺术课程改进措施！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artActivityProblems)==false){
			errorInfo = "请正确输入艺术活动存在的主要问题！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artActivityImprovement)==false){
			errorInfo = "请正确输入艺术活动改进措施！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artTeacherProblems)==false){
			errorInfo = "请正确输入艺术教师存在的主要问题！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(artTeacherImprovement)==false){
			errorInfo = "请正确输入艺术教师改进措施！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(conditionGuaranteeProblems)==false){
			errorInfo = "请正确输入条件保障存在的主要问题！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(conditionGuaranteeImprovement)==false){
			errorInfo = "请正确输入条件保障改进措施！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(characteristicProblems)==false){
			errorInfo = "请正确输入特色发展存在的主要问题！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(characteristicImprovement)==false){
			errorInfo = "请正确输入特色发展改进措施！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(assessmentProblems)==false){
			errorInfo = "请正确输入学生艺术素质测评存在的主要问题！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		if(V.isChinese(assessmentImprovement)==false){
			errorInfo = "请正确输入学生艺术素质测评改进措施！（不能纯英文汉字符号且不超过200）";
			return "error";
		}
		
		/**
		 * 将前端信息存入Map
		 * 传入service类
		 */
    
		map.put("ArtSome1", artCurriculumProblems);
		map.put("ArtSome2", artCurriculumImprovement);
		map.put("ArtSome3", artActivityProblems);
		map.put("ArtSome4", artActivityImprovement);
		map.put("ArtSome5", artTeacherProblems);
		map.put("ArtSome6", artTeacherImprovement);
		map.put("ArtSome7", conditionGuaranteeProblems);
		map.put("ArtSome8", conditionGuaranteeImprovement);
		map.put("ArtSome9", characteristicProblems);
		map.put("ArtSome10", characteristicImprovement);
		map.put("ArtSome11", assessmentProblems);
		map.put("ArtSome12", assessmentImprovement);
		map.put("ArtSome13", artCousreNames);
	    map.put("ArtSome14", artClubName);
	    map.put("ArtSome15", artCultureAtmosphere);
	    map.put("ArtSome16", artVenueNamesOther);
	    map.put("ArtSome17", achieveArtFeature);
		map.put("ArtSomeid", o_id);
		map.put("C_ID" , getSession().get(Constants.LOGIN_USER_C_ID).toString());
		
	
	     if (isChangedData() ) {
	    	 System.out.println("---------------------zhix");
		        serviceManager.getArtSomeService().insertArtSome(map);
		        return "artSomeSelf";
		    }
	     System.out.println("==========me");
		return "artSomeSelf" ;
		
	}
	

	private boolean isChangedData() {
		boolean flag = false;
		@SuppressWarnings("unchecked")
		String o_id = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		Map<String, Object> map = serviceManager.getOtherService().getArtData(o_id);
		
		
		if(map != null){	
			
			String str13 = null;
			 if(map.get("ART_COURSE_NAMES")!=null){
				 str13=map.get("ART_COURSE_NAMES").toString();
		      }
			 if(str13==null||str13.equals("")){
					if (artCousreNames==null||artCousreNames.equals("")) {
						flag=false;
					}else{
					
						flag=true;
					}
				}else if (!str13.equals(artCousreNames)) {
					flag=true;
				}
			 
			 String str14 = null;
			 if(map.get("ART_CLUB_NAME")!=null){
				 str14=map.get("ART_CLUB_NAME").toString();
		      }
			
			 if(flag==false){
			 if(str14==null||str14.equals("")){
					if (artClubName==null||artClubName.equals("")) {
						flag=false;
					}else{
						flag=true;
					}
				}else if (!str14.equals(artClubName)) {
					flag=true;
				}
			 }
			
			 String str15 = null;
			 if(map.get("ART_CULTURE_ATMOSPHERE")!=null){
				 str15=map.get("ART_CULTURE_ATMOSPHERE").toString();
		      }
			 
			 if(flag==false){
			 if(str15==null||str15.equals("")){
					if (artCultureAtmosphere==null||artCultureAtmosphere.equals("")) {
						flag=false;
					}else{
						flag=true;
					}
				}else if (!str15.equals(artCultureAtmosphere)) {
					flag=true;
				}
			 }
			
			 
		 String str16 = null;
		 if(map.get("ART_VENUE_NAMES_OTHER")!=null){
			 str16=map.get("ART_VENUE_NAMES_OTHER").toString();
	      }
		 if(flag==false){
		 if(str16==null||str16.equals("")){
				if (artVenueNamesOther==null||artVenueNamesOther.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str16.equals(artVenueNamesOther) ){
				flag = true;
			}
		 }
		
		 String str17 = null;
		 if(map.get("ACHIEVE_ART_FEATURE")!=null){
			 str17=map.get("ACHIEVE_ART_FEATURE").toString();
	      }
		 if(flag==false){
		 if(str17==null||str17.equals("")){
				if (achieveArtFeature==null||achieveArtFeature.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str17.equals(achieveArtFeature) ){
				flag = true;
			}
		 }
		
		String str1 = null;
		if(map.get("PROBLEM_IN_ART_COURSE")!=null){
			 str1=map.get("PROBLEM_IN_ART_COURSE").toString();      
		 }
		 if(flag==false){
		if(str1==null||str1.equals("")){
			if (artCurriculumProblems==null||artCurriculumProblems.equals("")) {
				flag=false;
			}else{
				flag=true;
			}
		}else if (!str1.equals(artCurriculumProblems) ){
			flag = true;
		}}
		
		String str2 = null;
		 if(map.get("SOLUTION_TO_PROBLEM_ART_COURSE")!=null){
			 str2=map.get("SOLUTION_TO_PROBLEM_ART_COURSE").toString();
	      }
		 if(flag==false){
		 if(str2==null||str2.equals("")){
				if (artCurriculumImprovement==null||artCurriculumImprovement.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str2.equals(artCurriculumImprovement) ){
				flag = true;
			}}
	
		 String str3 = null; 
		 if(map.get("PROBLEM_ART_ACTIVITY")!=null){
			 str3=map.get("PROBLEM_ART_ACTIVITY").toString();
	      }
		 if(flag==false){
		 if(str3==null||str3.equals("")){
				if (artActivityProblems==null||artActivityProblems.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str3.equals(artActivityProblems) ){
				flag = true;
			}}
	

		 String str4 = null;
		 if(map.get("SOLUTION_TO_PROBLEM_ACTIVITY")!=null){
			 str4=map.get("SOLUTION_TO_PROBLEM_ACTIVITY").toString();
	      }
		 if(flag==false){
		 if(str4==null||str4.equals("")){
				if (artActivityImprovement==null||artActivityImprovement.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str4.equals(artActivityImprovement) ){
				flag = true;
			}}
	
		 String str5 = null;
		 if(map.get("PROBLEM_ART_TEACHER")!=null){
			 str5=map.get("PROBLEM_ART_TEACHER").toString();
	      }
		 if(flag==false){
		 if(str5==null||str5.equals("")){
				if (artTeacherProblems==null||artTeacherProblems.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str5.equals(artTeacherProblems) ){
				flag = true;
			}}
		 
		 String str6 = null;
		 if(map.get("SOLUTION_TO_PROBLEM_TEACHER")!=null){
			 str6=map.get("SOLUTION_TO_PROBLEM_TEACHER").toString();
	      }
		 if(flag==false){
		 if(str6==null||str6.equals("")){
				if (artTeacherImprovement==null||artTeacherImprovement.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str6.equals(artTeacherImprovement) ){
				flag = true;
			}
		 }
		 
		 String str7 = null;
		 if(map.get("PROBLEM_ENSURANCE")!=null){
			 str7=map.get("PROBLEM_ENSURANCE").toString();
	      }
		 if(flag==false){
		 if(str7==null||str7.equals("")){
				if (conditionGuaranteeProblems==null||conditionGuaranteeProblems.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str7.equals(conditionGuaranteeProblems) ){
				flag = true;
			}}
		 
		 String str8 = null;
		 if(map.get("SOLUTION_TO_PROBLEM_ENSURANCE")!=null){
			 str8=map.get("SOLUTION_TO_PROBLEM_ENSURANCE").toString();
	      }
		 if(flag==false){
		 if(str8==null||str8.equals("")){
				if (conditionGuaranteeImprovement==null||conditionGuaranteeImprovement.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str8.equals(conditionGuaranteeImprovement) ){
				flag = true;
			}}
		 
		 String str9 = null;
		 if(map.get("PROBLEM_ART_FEATURE")!=null){
			 str9=map.get("PROBLEM_ART_FEATURE").toString();
	      }
		 if(flag==false){
		 if(str9==null||str9.equals("")){
				if (characteristicProblems==null||characteristicProblems.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str9.equals(characteristicProblems) ){
				flag = true;
			}}
		 
		 String str10 = null;	 
		 if(map.get("SOLUTION_TO_PROBLEM_FEATURE")!=null){
			 str10=map.get("SOLUTION_TO_PROBLEM_FEATURE").toString();
	      }
		 if(flag==false){
		 if(str10==null||str10.equals("")){
				if (characteristicImprovement==null||characteristicImprovement.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str10.equals(characteristicImprovement) ){
				flag = true;
			}}
		 
		 String str11 = null;
		 if(map.get("PROBLEM_ART_QUALITY")!=null){
			 str11=map.get("PROBLEM_ART_QUALITY").toString();
	      } 
		 if(flag==false){
		 if(str11==null||str11.equals("")){
				if (assessmentProblems==null||assessmentProblems.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str11.equals(assessmentProblems) ){
				flag = true;
			}}
		 
		 String str12 = null;
		 if(map.get("SOLUTION_TO_QUALITY")!=null){
			 str12=map.get("SOLUTION_TO_QUALITY").toString();
	      }
		 if(flag==false){
		 if(str12==null||str12.equals("")){
				if (assessmentImprovement==null||assessmentImprovement.equals("")) {
					flag=false;
				}else{
					flag=true;
				}
			}else if (!str12.equals(assessmentImprovement) ){
				flag = true;
			}}

		}
		return flag;
	}

	@SuppressWarnings({ "unchecked"})
	public String execute() throws Exception{
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		
		String  o_id = user.get("O_ID").toString();
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3 =serviceManager.getOtherService().getArtData(o_id);
		map2 = serviceManager.getOtherService().getArtData(o_id);
		setToken(getSession().get("token").toString());
		
		if (map2==null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}
		
		if (map3==null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}
		
		if (v.checkEmpty(map3.get("STUDENTS_GOOD"))) {
			errorInfo = "艺术表尚未填写完毕！";
			return "someBackView";
		}
	
		
		
		
		 if(map2.get("PROBLEM_IN_ART_COURSE")!=null){
			 artCurriculumProblems=map2.get("PROBLEM_IN_ART_COURSE").toString();      
		 }
		 if(map2.get("SOLUTION_TO_PROBLEM_ART_COURSE")!=null){
			 artCurriculumImprovement=map2.get("SOLUTION_TO_PROBLEM_ART_COURSE").toString();
	      }
		 if(map2.get("PROBLEM_ART_ACTIVITY")!=null){
			 artActivityProblems=map2.get("PROBLEM_ART_ACTIVITY").toString();
	      }
		 if(map2.get("SOLUTION_TO_PROBLEM_ACTIVITY")!=null){
			 artActivityImprovement=map2.get("SOLUTION_TO_PROBLEM_ACTIVITY").toString();
	      }
		 if(map2.get("PROBLEM_ART_TEACHER")!=null){
			 artTeacherProblems=map2.get("PROBLEM_ART_TEACHER").toString();
	      }
		 if(map2.get("SOLUTION_TO_PROBLEM_TEACHER")!=null){
			 artTeacherImprovement=map2.get("SOLUTION_TO_PROBLEM_TEACHER").toString();
	      }
		 if(map2.get("PROBLEM_ENSURANCE")!=null){
			 conditionGuaranteeProblems=map2.get("PROBLEM_ENSURANCE").toString();
	      }
		 if(map2.get("SOLUTION_TO_PROBLEM_ENSURANCE")!=null){
			 conditionGuaranteeImprovement=map2.get("SOLUTION_TO_PROBLEM_ENSURANCE").toString();
	      }
		 if(map2.get("PROBLEM_ART_FEATURE")!=null){
			 characteristicProblems=map2.get("PROBLEM_ART_FEATURE").toString();
	      }
		 if(map2.get("SOLUTION_TO_PROBLEM_FEATURE")!=null){
			 characteristicImprovement=map2.get("SOLUTION_TO_PROBLEM_FEATURE").toString();
	      }
		 if(map2.get("PROBLEM_ART_QUALITY")!=null){
			 assessmentProblems=map2.get("PROBLEM_ART_QUALITY").toString();
	      } 
		 if(map2.get("SOLUTION_TO_QUALITY")!=null){
			 assessmentImprovement=map2.get("SOLUTION_TO_QUALITY").toString();
	      }	 
		 if(map2.get("ART_COURSE_NAMES")!=null){
			 artCousreNames=map2.get("ART_COURSE_NAMES").toString();
	      }
		 if(map2.get("ART_CLUB_NAME")!=null){
			 artClubName=map2.get("ART_CLUB_NAME").toString();
	      }
		 if(map2.get("ART_CULTURE_ATMOSPHERE")!=null){
			 artCultureAtmosphere=map2.get("ART_CULTURE_ATMOSPHERE").toString();
	      }
		 if(map2.get("ART_VENUE_NAMES_OTHER")!=null){
			 artVenueNamesOther=map2.get("ART_VENUE_NAMES_OTHER").toString();
	      }
		 if(map2.get("ACHIEVE_ART_FEATURE")!=null){
			 achieveArtFeature=map2.get("ACHIEVE_ART_FEATURE").toString();
	      }
		return "success";
		
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

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
