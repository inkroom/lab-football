package javadev.iip.action.fiveplan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.util.validata.V;
import oracle.net.aso.p;
/**
 * 功能描述：用于 体育、艺术教师培训与教研员配备情况的Action
 * @author:彭波
 * @version：1.0
 * create Date ：2016年8月30日18：53：24
 */
public class SportsArtTeachEquipAction extends BaseAction{
	private String phy_teacher_exer;
	private String spend_phy_teacher_exer;
	private String music_teacher_exer;
	private String spend_music_teacher_exer;
	private String draw_teacher_exer;
	private String spend_draw_teacher_exer;
	private String errorInfo;
	private String 	newToken4;
	private Map<String, Object> oldData;
	private Map<String, Object> preData;
	private Map<String, Object> stepData;
	private Map<String,Object> Page1;
	private Map<String,Object> Page2;
	private Map<String,Object> Page3;
	private Map<String, Object> information;
	private String person_name;
	private String person_phone;
	private String is_achieve_play_ground;
	public Map<String, Object> getPreData() {
		return preData;
	}
	public void setPreData(Map<String, Object> preData) {
		this.preData = preData;
	}
	//获取旧数据并进入页面
	public String getAllOldData() throws Exception{
		Map data = (Map) getSession().get(Constants.LOGIN_USER);
		String a_id= data.get("A_ID").toString();	
		String o_id= data.get("O_ID").toString();
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time1=Integer.valueOf(time.format(date))-1;
		Integer time2=Integer.valueOf(time.format(date));
		String fp_year=time1.toString();
		String now_year= time2.toString();
		newToken4 = (String) getRequest().getSession().getAttribute("token");
		Page1=getServMgr().getPageOneService().getAllOldDatas(o_id, now_year,1);
		
		if(Page1!=null && Page1.get("is_achieve_play_ground")==null)
		{
			errorInfo="请将信息填写完整!";
			return "topage1";
		}
		Page2=getServMgr().getRespostFromActionTwoService().getAllOldDatas(o_id, now_year,"1");
		if(Page2!=null && Page2.get("isAchieveMusicRoom")==null)
		{
			errorInfo="请将信息填写完整!";
			return "topage2";
		}
		Page3=getServMgr().getPhyMusicDrawService().selectPhyMusicDraws(now_year, o_id,"1");
		if(Page3!=null && Page3.get("retire_phy_teacher")==null)
		{
			errorInfo="请将信息填写完整!";
			return "topage3";
		}
		preData = getServMgr().getSportsArtTeachEquipService().getAllNewData(o_id, now_year);
		System.out.println("----------------");
		System.out.println(preData);
		System.out.println("------------");
		information = getServMgr().getSportsArtTeachEquipService().getInformation(o_id);
		System.out.println("------------------");
		System.out.println(information);
		oldData=getServMgr().getSportsArtTeachEquipService().getAllOldData(o_id,fp_year);
		return "intoSportsArtTeachEquip";	
	}
	public static boolean judgeSize(double min,double max,String value){
		Double s=Double.parseDouble(value);
		if(s>=min&&s<=max){

			return true;
		}
		return false;
	}
	public String  preStep() {

		return "pre";
	}
	//提交表单
	public String uploadSportsArtTeachEquipForm() throws Exception{
		
		Map data = (Map) getSession().get(Constants.LOGIN_USER);
		String a_id= data.get("A_ID").toString();	
		String o_id= data.get("O_ID").toString();
	
		//去掉两边的空格	
		phy_teacher_exer=phy_teacher_exer.trim();
		spend_phy_teacher_exer=spend_phy_teacher_exer.trim();
		music_teacher_exer=music_teacher_exer.trim();
		spend_music_teacher_exer=spend_music_teacher_exer.trim();
		draw_teacher_exer=draw_teacher_exer.trim();
		spend_draw_teacher_exer=spend_draw_teacher_exer.trim();
		//体育,美术，音乐教师培训（人次）正整数的约束 

		if(!(V.isZNumbers(phy_teacher_exer))){return "intoSportsArtTeachEquip";}
		if(!(V.isZNumbers(music_teacher_exer))){return "intoSportsArtTeachEquip";}
		if(!(V.isZNumbers(draw_teacher_exer))){return "intoSportsArtTeachEquip";}

		//体育,美术，音乐教师培训费用验证，如果有小数，保留4位小数

		if(!(V.isNumeric(spend_phy_teacher_exer))){return "intoSportsArtTeachEquip";}
		if(!(V.isNumeric(spend_music_teacher_exer))){return "intoSportsArtTeachEquip";}
		if(!(V.isNumeric(spend_draw_teacher_exer))){return "intoSportsArtTeachEquip";}

		if(!(judgeSize(0, 99999999, phy_teacher_exer))){return "intoSportsArtTeachEquip";}
		if(!(judgeSize(0, 99999999, music_teacher_exer))){return "intoSportsArtTeachEquip";}
		if(!(judgeSize(0, 99999999, draw_teacher_exer))){return "intoSportsArtTeachEquip";}


		if(!(judgeSize(0, 99999999, spend_phy_teacher_exer.toString()))){return "intoSportsArtTeachEquip";}
		if(!(judgeSize(0, 99999999, spend_music_teacher_exer))){return "intoSportsArtTeachEquip";}		
		if(!(judgeSize(0, 99999999, spend_draw_teacher_exer))){return "intoSportsArtTeachEquip";}

		//获取之前的数据，记住，不是上一年的
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time2=Integer.valueOf(time.format(date));
		String now_year= time2.toString();	
		stepData = getServMgr().getSportsArtTeachEquipService().getAllNewData(o_id, now_year);
		String nphy_teacher_exer="",nspend_phy_teacher_exer="",nmusic_teacher_exer="",
				nspend_music_teacher_exer="",ndraw_teacher_exer="",nspend_draw_teacher_exer="";
		if(stepData.get("phy_teacher_exer")!=null){
			nphy_teacher_exer=stepData.get("phy_teacher_exer").toString();
			log.info(nphy_teacher_exer);
		}if(stepData.get("spend_phy_teacher_exer")!=null){
			nspend_phy_teacher_exer=stepData.get("spend_phy_teacher_exer").toString();
			log.info(nspend_phy_teacher_exer);
		}if(stepData.get("music_teacher_exer")!=null){
			nmusic_teacher_exer=stepData.get("music_teacher_exer").toString();
			log.info(nmusic_teacher_exer);
		}if(stepData.get("spend_music_teacher_exer")!=null){
			nspend_music_teacher_exer=stepData.get("spend_music_teacher_exer").toString();
			log.info(nspend_music_teacher_exer);
		}if(stepData.get("draw_teacher_exer")!=null){
			ndraw_teacher_exer=stepData.get("draw_teacher_exer").toString();
			log.info(ndraw_teacher_exer);
		}if(stepData.get("spend_draw_teacher_exer")!=null){
			nspend_draw_teacher_exer=stepData.get("spend_draw_teacher_exer").toString();
			log.info(nspend_draw_teacher_exer);
		}
		if((phy_teacher_exer.trim().equals(nphy_teacher_exer)) && (spend_phy_teacher_exer.trim().equals(nspend_phy_teacher_exer))
				&& (music_teacher_exer.trim().equals(nmusic_teacher_exer))	&& (spend_music_teacher_exer.trim().equals(nspend_music_teacher_exer))
				&& (draw_teacher_exer.trim().equals(ndraw_teacher_exer)) &&(spend_draw_teacher_exer.trim().equals(nspend_draw_teacher_exer))){

			log.info("*********************数据不变**************************");
			getServMgr().getSportsArtTeachEquipService().updateIsFinishWrite(o_id);
			return "uploadSportsArtTeachEquipForm";
		}
		getServMgr().getSportsArtTeachEquipService().updateALlDataS(phy_teacher_exer, spend_phy_teacher_exer, 
					music_teacher_exer, spend_music_teacher_exer, draw_teacher_exer, spend_draw_teacher_exer, o_id);
		getServMgr().getSportsArtTeachEquipService().updatedata(person_name, person_phone, o_id);
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			session.put("phy_teacher_exer", phy_teacher_exer);
			session.put("spend_phy_teacher_exer", spend_phy_teacher_exer);
			session.put("music_teacher_exer", music_teacher_exer);
			session.put("spend_music_teacher_exer", spend_music_teacher_exer);
			session.put("draw_teacher_exer", draw_teacher_exer);
			session.put("spend_draw_teacher_exer", spend_draw_teacher_exer);
			log.info("*********************数据更新成功**************************");
		return "uploadSportsArtTeachEquipForm";
	}
	public String getPhy_teacher_exer() {
		return phy_teacher_exer;
	}
	public void setPhy_teacher_exer(String phy_teacher_exer) {
		this.phy_teacher_exer = phy_teacher_exer;
	}
	public String getSpend_phy_teacher_exer() {
		return spend_phy_teacher_exer;
	}
	public void setSpend_phy_teacher_exer(String spend_phy_teacher_exer) {
		this.spend_phy_teacher_exer = spend_phy_teacher_exer;
	}
	public String getMusic_teacher_exer() {
		return music_teacher_exer;
	}
	public void setMusic_teacher_exer(String music_teacher_exer) {
		this.music_teacher_exer = music_teacher_exer;
	}
	public String getSpend_music_teacher_exer() {
		return spend_music_teacher_exer;
	}
	public void setSpend_music_teacher_exer(String spend_music_teacher_exer) {
		this.spend_music_teacher_exer = spend_music_teacher_exer;
	}
	public String getDraw_teacher_exer() {
		return draw_teacher_exer;
	}
	public void setDraw_teacher_exer(String draw_teacher_exer) {
		this.draw_teacher_exer = draw_teacher_exer;
	}
	public String getSpend_draw_teacher_exer() {
		return spend_draw_teacher_exer;
	}
	public void setSpend_draw_teacher_exer(String spend_draw_teacher_exer) {
		this.spend_draw_teacher_exer = spend_draw_teacher_exer;
	}
	public Map<String, Object> getOldData() {
		return oldData;
	}
	public void setOldData(Map<String, Object> oldData) {
		this.oldData = oldData;
	}
	public Map<String, Object> getPage1() {
		return Page1;
	}
	public void setPage1(Map<String, Object> page1) {
		Page1 = page1;
	}
	public Map<String, Object> getPage2() {
		return Page2;
	}
	public void setPage2(Map<String, Object> page2) {
		Page2 = page2;
	}
	public Map<String, Object> getPage3() {
		return Page3;
	}
	public void setPage3(Map<String, Object> page3) {
		Page3 = page3;
	}
	public String getIs_achieve_play_ground() {
		return is_achieve_play_ground;
	}
	public void setIs_achieve_play_ground(String is_achieve_play_ground) {
		this.is_achieve_play_ground = is_achieve_play_ground;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getNewToken4() {
		return newToken4;
	}
	public void setNewToken4(String newToken4) {
		this.newToken4 = newToken4;
	}
	public Map<String, Object> getStepData() {
		return stepData;
	}
	public void setStepData(Map<String, Object> stepData) {
		this.stepData = stepData;
	}

	public Map<String, Object> getInformation() {
		return information;
	}
	public void setInformation(Map<String, Object> information) {
		this.information = information;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getPerson_phone() {
		return person_phone;
	}
	public void setPerson_phone(String person_phone) {
		this.person_phone = person_phone;
	}

}
