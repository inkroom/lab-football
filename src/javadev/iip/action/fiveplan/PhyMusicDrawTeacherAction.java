
 
package javadev.iip.action.fiveplan;




import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import javadev.iip.action.BaseAction;
import javadev.iip.util.validata.V;
import javadev.core.Constants;

/**
 *<p>体育、音乐、美术老师的添加修改查询操作的 控制层</p>
 *@author 王树浩
 *@version 1.0
 */
public class PhyMusicDrawTeacherAction extends BaseAction{
	private String account_id;
	
	private String retire_phy_teacher; //退休和离职体育老师
	private String growth_phy_teacher; //净增体育老师
	private String add_new_phy_teachers_full;//新增专职体育老师人数
	private String retire_music_teacher;//退休和离职音乐老师
	private String growth_music_teacher;//净增音乐老师
	private String add_new_music_teachers_full;//新增专职音乐老师人数
	private String retire_draw_teacher;//退休和离职美术老师
	private String growth_draw_teacher;//净增美术老师
	private String add_new_draw_teachers_full;//新增专职美术老师人数
	private String[] t_name; //老师姓名
	private String[] type;//老师职责   1.体育老师 2.音乐老师 3.美术老师
	private String[] t_age;//老师年龄
	private String[] file_no;//老师文件编号
	private String[] t_major;//老师专业
	private String errorInfo;//错误信息
	private int num;//老师人数
	private List<Map<String,Object>> listFpId;
	private Map<String, Object> oldPhyMusicDrawDate; //去年的老师离职增长数据
	private Map<String,Object> nowPhyMusicDrawDate;  //今年的老师老师泪痣增长数据
	private List<Map<String, Object>> PhyTeacherList; //体育老师数据
	private List<Map<String, Object>> MusicTeacherList;//音乐老师数据
	private List<Map<String, Object>> DrawTeacherList;//美术老师数据
	private Map<String, Object> fp_id; 
	private Map<String, Object> fp_year;
	private List<Map<String, Object>> listTm_id;  
	private String tm_id;
	V v =new V();
	private Map<String, Object> onePageDate; 
	private Map<String, Object> towPageDate;
	private String newToken1;
	private String token;
	ActionContext actionContext=ActionContext.getContext();
	Map<String,Object> session=actionContext.getSession();
	private String card_id;
/*-----------------------------------Action------------------------------------- */
	
	/**
	 * 跳转到PhyMusicDrawTeacherFrom.ftl 并查询去年数据
	 * @author 王树浩
	 * @return
	 */
	public String get()  {	
		//System.out.println("error ********************************"+errorInfo);
		newToken1=(String) getRequest().getSession().getAttribute("token");
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time1=Integer.valueOf(time.format(date))-1;
		Integer time2=Integer.valueOf(time.format(date));	
		String old_year=time1.toString();
		String now_year=time2.toString();
		onePageDate=getServMgr().getPageOneService().getAllOldDatas(getOID(),now_year,1);
		towPageDate=getServMgr().getRespostFromActionTwoService().getAllOldDatas(getOID(), now_year,"1");
		if(onePageDate!=null && onePageDate.get("is_achieve_play_ground")==null){
			errorInfo="请将信息填写完整!";
			//session.put("errorInfo", errorInfo);
			return "topage1";
		}
		if(towPageDate!=null && towPageDate.get("isAchieveMusicRoom")==null){
			errorInfo="请将信息填写完整!";
			//session.put("errorInfo", errorInfo);
			return "topage2";
		}
		oldPhyMusicDrawDate= getServMgr().getPhyMusicDrawService().selectPhyMusicDraw(old_year,getOID(),"2");
		nowPhyMusicDrawDate= getServMgr().getPhyMusicDrawService().selectPhyMusicDraws(now_year, getOID(),"1");
		
		fp_id=getServMgr().getPhyMusicDrawService().selectFp_id(getOID(),now_year,"1");
		//System.out.println("fp_id"+fp_id);
		if (fp_id!=null) {
			PhyTeacherList=getServMgr().getPhyMusicDrawService().selectTeacherMsg(fp_id.get("fp_id").toString(),now_year,"1");
			MusicTeacherList=getServMgr().getPhyMusicDrawService().selectTeacherMsg(fp_id.get("fp_id").toString(), now_year,"2");
			DrawTeacherList=getServMgr().getPhyMusicDrawService().selectTeacherMsg(fp_id.get("fp_id").toString(), now_year,"3");
		}
		
		return "success";
	}
	
	
	/**
	 * 保存上传的表单数据
	 * @author 王树浩
	 * @return 保存信息
	 */
	public String savePhyMusicDraw(){
		newToken1=(String)getRequest().getSession().getAttribute("token");
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time2=Integer.valueOf(time.format(date));	
		String now_year=time2.toString();
		nowPhyMusicDrawDate=fp_year=getServMgr().getPhyMusicDrawService().selectPhyMusicDraw(now_year, getOID(), "1");
		fp_id=getServMgr().getPhyMusicDrawService().selectFp_id(getOID(),now_year,"1");
		//System.out.println("fp_id"+fp_id);
		if(fp_id!=null){
			listTm_id=getServMgr().getPhyMusicDrawService().selectTM_ID(Integer.valueOf(fp_id.get("fp_id").toString()));
		}
		V v=new V();
		if(!(Integer.parseInt(add_new_phy_teachers_full)-Integer.parseInt(retire_phy_teacher.replaceAll(" ", ""))==Integer.parseInt(growth_phy_teacher.replaceAll(" ", "")))){
			errorInfo="净增长老师数不正确";
			return"error";
		}
		if (v.checkEmpty(retire_phy_teacher.replaceAll(" ", ""))||Integer.parseInt(retire_phy_teacher.replaceAll(" ", ""))<0||!(V.isZNumbers(retire_phy_teacher.replaceAll(" ", "")))) {
			errorInfo="输入错误，离职人数为正整数且不为空";
			//session.put("errorInfo", errorInfo);
			return "error";
		}
		if (v.checkEmpty(retire_music_teacher.replaceAll(" ", ""))||Integer.parseInt(retire_music_teacher.replaceAll(" ", ""))<0||!(V.isZNumbers(retire_music_teacher.replaceAll(" ", "")))) {
			errorInfo="输入错误，离职人数为正整数且不为空";
			//session.put("errorInfo", errorInfo);
			return "error";
		}if (v.checkEmpty(retire_draw_teacher.replaceAll(" ", ""))||Integer.parseInt(retire_draw_teacher.replaceAll(" ", ""))<0||!(V.isZNumbers(retire_draw_teacher.replaceAll(" ", "")))) {
			errorInfo="输入错误，离职人数为正整数且不为空";
			//session.put("errorInfo", errorInfo);
			return "error";
		}
		if (v.checkEmpty(growth_phy_teacher)||!(V.checkNumber(growth_phy_teacher))) {
			errorInfo="输入错误，净增老师人数为正整数且不为空";
			//session.put("errorInfo", errorInfo);
			return "error";
		}
		if (v.checkEmpty(growth_music_teacher)||!(V.checkNumber(growth_music_teacher))) {
			errorInfo="输入错误，净增老师人数为正整数且不为空";
			//session.put("errorInfo", errorInfo);
			return "error";
		}
		if (v.checkEmpty(growth_draw_teacher)||!(V.checkNumber(growth_draw_teacher))) {
			errorInfo="输入错误，净增老师人数为正整数且不为空";
			//session.put("errorInfo", errorInfo);
			return "error";
		}
		if(!(t_age==null)){
			for (int i = 0; i < t_age.length; i++) {
				if (v.checkEmpty(t_age[i].replaceAll(" ", ""))||Integer.parseInt(t_age[i].replaceAll(" ", ""))<0||!V.isZNumbers(t_age[i].replaceAll(" ", ""))) {
					errorInfo="输入错误，年龄为正数且不为负";
					//session.put("errorInfo", errorInfo);
					return "error";
				}
				if(!v.isChinese(t_name[i].replaceAll(" ", ""))||v.checkEmpty(t_name[i].replaceAll(" ", ""))){
					errorInfo="输入错误，老师姓名不为空且为2-7个汉字，或4-14个字符（字母）";
					//session.put("errorInfo", errorInfo);
					return"error";
				}
				
				
			}
		}
		
		if(listTm_id!=null){
		for (int j = 0; j < listTm_id.size(); j++) {
			
			getServMgr().getPhyMusicDrawService().delete(Integer.valueOf(listTm_id.get(j).get("TM_ID").toString()),getOID());
		}
		}
		
		if(!(t_age==null)){
			for (int i = 0; i < t_age.length; i++) {
//				System.out.println("id="+Id_CardUtil.isValidatedAllIdcard(file_no[i]));
//				System.out.println("db="+getServMgr().getPhyMusicDrawService().selectId_Card(file_no[i])!=null);
//				if(!Id_CardUtil.isValidatedAllIdcard(file_no[i])||getServMgr().getPhyMusicDrawService().selectId_Card(file_no[i])==null){
//					errorInfo="身份证输入错误";
//					return"error";
//				}else{
				if (!getServMgr().getPhyMusicDrawService().addTeacherMsg(fp_id.get("fp_id").toString().replaceAll(" ", ""), t_name[i].replaceAll(" ", ""), type[i].replaceAll(" ", ""), t_age[i].replaceAll(" ", ""), file_no[i].replaceAll(" ", ""), t_major[i].replaceAll(" ", ""),getOID())) {
					errorInfo="保存失败！";
					return "error";
				}
				//}
			}
		}
		//System.out.println("retire_phy_teacher"+retire_phy_teacher.replace(" "," "));
		//System.out.println("nowPhyMusicDrawDate:"+nowPhyMusicDrawDate);
		
		if(!(v.checkEmpty(nowPhyMusicDrawDate)) &&!(nowPhyMusicDrawDate.get("retire_phy_teacher")==null && nowPhyMusicDrawDate.get("retire_music_teacher")==null &&
				nowPhyMusicDrawDate.get("retire_draw_teacher")==null&&nowPhyMusicDrawDate.get("growth_phy_teacher")==null&&
				nowPhyMusicDrawDate.get("growth_music_teacher")==null&&nowPhyMusicDrawDate.get("growth_draw_teacher")==null&&
				nowPhyMusicDrawDate.get("add_new_phy_teachers_full")==null&&nowPhyMusicDrawDate.get("add_new_music_teachers_full")==null&&
				nowPhyMusicDrawDate.get("add_new_draw_teachers_full")==null)){
			//System.out.println("nowPhyMusicDrawDate w:no null");
			if(!(retire_phy_teacher.equals(nowPhyMusicDrawDate.get("retire_phy_teacher").toString()) && retire_music_teacher.equals(nowPhyMusicDrawDate.get("retire_music_teacher").toString()) &&
					retire_draw_teacher.equals(nowPhyMusicDrawDate.get("retire_draw_teacher").toString())&&growth_phy_teacher.equals(nowPhyMusicDrawDate.get("growth_phy_teacher").toString())&&
					growth_music_teacher.equals(nowPhyMusicDrawDate.get("growth_music_teacher").toString())&&growth_draw_teacher.equals(nowPhyMusicDrawDate.get("growth_draw_teacher").toString())&&
					add_new_phy_teachers_full.equals(nowPhyMusicDrawDate.get("add_new_phy_teachers_full").toString())&&add_new_music_teachers_full.equals(nowPhyMusicDrawDate.get("add_new_music_teachers_full").toString())&&
					add_new_draw_teachers_full.equals(nowPhyMusicDrawDate.get("add_new_draw_teachers_full").toString()))){
				if (getServMgr().getPhyMusicDrawService().addPhyMusicDraw(retire_phy_teacher.replaceAll(" ", ""),growth_phy_teacher,retire_music_teacher.replaceAll(" ", ""),growth_music_teacher,retire_draw_teacher.replaceAll(" ", ""), growth_draw_teacher,add_new_phy_teachers_full,add_new_music_teachers_full,add_new_draw_teachers_full,getAID()))
				 {
					//System.out.println("修改了数据");
					return "success";
				}else {
					errorInfo="保存失败！";
					return "error";
				}
			}else {
				//System.out.println("未修改数据");
				return "success";
			}
		}
			else {
			//System.out.println("nowPhyMusicDrawDate w: null");
			if (getServMgr().getPhyMusicDrawService().addPhyMusicDraw(retire_phy_teacher.replaceAll(" ", ""),growth_phy_teacher,retire_music_teacher.replaceAll(" ", ""),growth_music_teacher,retire_draw_teacher.replaceAll(" ", ""), growth_draw_teacher,add_new_phy_teachers_full,add_new_music_teachers_full,add_new_draw_teachers_full,getAID()))
			 {
				//System.out.println("修改了数据");
				return "success";
			}else {
				errorInfo="保存失败！";
				return "error";
			}
		}
		
	}
	/**
	 * 删除填错的体育老师
	 * @return
	 */
	public String deletePhyTeacher(){
		getServMgr().getPhyMusicDrawService().delete(Integer.valueOf(tm_id),getOID());
		if(getServMgr().getPhyMusicDrawService().addPhyMusicDraw(retire_phy_teacher,String.valueOf(Integer.parseInt(growth_phy_teacher)-1), retire_music_teacher, growth_music_teacher, retire_draw_teacher, growth_draw_teacher,String.valueOf(Integer.parseInt(add_new_phy_teachers_full)-1), add_new_music_teachers_full, add_new_draw_teachers_full, getAID())){
			return "success";
		}else{
			errorInfo="删除失败";
			return "error";
		}
		
	}
	/**
	 * 删除填错的音乐老师
	 * @return
	 */
	public String deleteMusicTeacher(){
		getServMgr().getPhyMusicDrawService().delete(Integer.valueOf(tm_id),getOID());
		if(getServMgr().getPhyMusicDrawService().addPhyMusicDraw(retire_phy_teacher, growth_phy_teacher, retire_music_teacher,String.valueOf(Integer.parseInt(growth_music_teacher)-1), retire_draw_teacher, growth_draw_teacher, add_new_phy_teachers_full,String.valueOf(Integer.parseInt(add_new_music_teachers_full)-1), add_new_draw_teachers_full, getAID())){
			return "success";
		}else{
			errorInfo="删除失败";
			return "error";
		}
		
	}
	/**
	 * 删除填错的美术老师
	 * @return
	 */
	public String deleteDrawTeacher(){
		getServMgr().getPhyMusicDrawService().delete(Integer.valueOf(tm_id),getOID());
		if(getServMgr().getPhyMusicDrawService().addPhyMusicDraw(retire_phy_teacher, growth_phy_teacher, retire_music_teacher, growth_music_teacher, retire_draw_teacher, String.valueOf(Integer.parseInt(growth_draw_teacher)-1), add_new_phy_teachers_full, add_new_music_teachers_full,String.valueOf(Integer.parseInt(add_new_draw_teachers_full)-1), getAID())){
			return "success";
		}else{
			errorInfo="删除失败";
			return "error";
		}
	}
	/**
	 *  查询 是否有重复id
	 * @return
	 */
	public String id_card(){
		
		
		System.out.println("card_id="+card_id);
		String responseText="";
		String idCard=getServMgr().getPhyMusicDrawService().selectId_Card(card_id);
		
		//System.out.println(card_id);
		//System.out.println("Id_CardUtil"+Id_CardUtil.isValidatedAllIdcard(card_id));
		if (card_id.equals("")) {
			responseText="3";  //重复
		}else{
			if(idCard!=null) {
				responseText="1";  //未填写
				//System.out.println(1);
			}else if (!V.isfile(card_id)) {
				responseText="4";  //有特殊字符
			}else{
				responseText="2";  //通过
			//	System.out.println(2);
			}
		}
		
		//System.out.println(id_card);
			
		 HttpServletResponse response=ServletActionContext.getResponse();      
         response.setContentType("text/html;charset=utf-8");
         PrintWriter out = null;
        
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         out.print(responseText);    
         
         out.flush();    
         out.close();
         return null;
	}
	
	private String getAID(){
		@SuppressWarnings("unchecked")
		String a_id =  ((Map<String,Object>) getSession().get(Constants.LOGIN_USER)).get("A_ID").toString();
		if(!v.checkEmpty(a_id)){
			return a_id;
		}else{
			return "";
		}
	}
	private String getOID(){
		@SuppressWarnings("unchecked")
		String a_id =  ((Map<String,Object>) getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		if(!v.checkEmpty(a_id)){
			return a_id;
		}else{
			return "";
		}
	}
	
	
/*---------------------------------get set-------------------------------- */
	
	public List<Map<String, Object>> getPhyTeacherList() {
		return PhyTeacherList;
	}
	public Map<String, Object> getFp_id() {
		return fp_id;
	}
	public void setFp_id(Map<String, Object> fp_id) {
		this.fp_id = fp_id;
	}
	public Map<String, Object> getFp_year() {
		return fp_year;
	}
	public void setFp_year(Map<String, Object> fp_year) {
		this.fp_year = fp_year;
	}
	public List<Map<String, Object>> getListTm_id() {
		return listTm_id;
	}
	public void setListTm_id(List<Map<String, Object>> listTm_id) {
		this.listTm_id = listTm_id;
	}
	public void setPhyTeacherList(List<Map<String, Object>> phyTeacherList) {
		PhyTeacherList = phyTeacherList;
	}
	public List<Map<String, Object>> getMusicTeacherList() {
		return MusicTeacherList;
	}
	public void setMusicTeacherList(List<Map<String, Object>> musicTeacherList) {
		MusicTeacherList = musicTeacherList;
	}
	public List<Map<String, Object>> getDrawTeacherList() {
		return DrawTeacherList;
	}
	public void setDrawTeacherList(List<Map<String, Object>> drawTeacherList) {
		DrawTeacherList = drawTeacherList;
	}
	public Map<String, Object> getNowPhyMusicDrawDate() {
		return nowPhyMusicDrawDate;
	}
	public void setNowPhyMusicDrawDate(Map<String, Object> nowPhyMusicDrawDate) {
		this.nowPhyMusicDrawDate = nowPhyMusicDrawDate;
	}
	
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public Map<String, Object> getOldPhyMusicDrawDate() {
		return oldPhyMusicDrawDate;
	}
	public void setOldPhyMusicDrawDate(Map<String, Object> oldPhyMusicDrawDate) {
		this.oldPhyMusicDrawDate = oldPhyMusicDrawDate;
	}
	public List<Map<String, Object>> getListFpId() {
		return listFpId;
	}
	public void setListFpId(List<Map<String, Object>> listFpId) {
		this.listFpId = listFpId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	private Map<String, Object> map;
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String geterrorInfo() {
		return errorInfo;
	}
	public void seterrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String[] getT_name() {
		return t_name;
	}
	public void setT_name(String[] t_name) {
		this.t_name = t_name;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	public String[] getT_age() {
		return t_age;
	}
	public void setT_age(String[] t_age) {
		this.t_age = t_age;
	}
	public String[] getFile_no() {
		return file_no;
	}
	public void setFile_no(String[] file_no) {
		this.file_no = file_no;
	}
	public String[] getT_major() {
		return t_major;
	}
	public void setT_major(String[] t_major) {
		this.t_major = t_major;
	}
	public String getRetire_phy_teacher() {
		return retire_phy_teacher;
	}
	public void setRetire_phy_teacher(String retire_phy_teacher) {
		this.retire_phy_teacher = retire_phy_teacher;
	}
	public String getGrowth_phy_teacher() {
		return growth_phy_teacher;
	}
	public void setGrowth_phy_teacher(String growth_phy_teacher) {
		this.growth_phy_teacher = growth_phy_teacher;
	}
	
	public String getRetire_music_teacher() {
		return retire_music_teacher;
	}
	public void setRetire_music_teacher(String retire_music_teacher) {
		this.retire_music_teacher = retire_music_teacher;
	}
	public String getGrowth_music_teacher() {
		return growth_music_teacher;
	}
	public void setGrowth_music_teacher(String growth_music_teacher) {
		this.growth_music_teacher = growth_music_teacher;
	}
	public String getRetire_draw_teacher() {
		return retire_draw_teacher;
	}
	public void setRetire_draw_teacher(String retire_draw_teacher) {
		this.retire_draw_teacher = retire_draw_teacher;
	}
	public String getGrowth_draw_teacher() {
		return growth_draw_teacher;
	}
	public void setGrowth_draw_teacher(String growth_draw_teacher) {
		this.growth_draw_teacher = growth_draw_teacher;
	}
	public String getTm_id() {
		return tm_id;
	}
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}


	public String getNewToken1() {
		return newToken1;
	}


	public void setNewToken1(String newToken1) {
		this.newToken1 = newToken1;
	}


	public String getAdd_new_phy_teachers_full() {
		return add_new_phy_teachers_full;
	}


	public void setAdd_new_phy_teachers_full(String add_new_phy_teachers_full) {
		this.add_new_phy_teachers_full = add_new_phy_teachers_full;
	}


	public String getAdd_new_music_teachers_full() {
		return add_new_music_teachers_full;
	}


	public void setAdd_new_music_teachers_full(String add_new_music_teachers_full) {
		this.add_new_music_teachers_full = add_new_music_teachers_full;
	}


	public String getAdd_new_draw_teachers_full() {
		return add_new_draw_teachers_full;
	}


	public void setAdd_new_draw_teachers_full(String add_new_draw_teachers_full) {
		this.add_new_draw_teachers_full = add_new_draw_teachers_full;
	}


	public String getErrorInfo() {
		return errorInfo;
	}


	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}


	public Map<String, Object> getOnePageDate() {
		return onePageDate;
	}


	public void setOnePageDate(Map<String, Object> onePageDate) {
		this.onePageDate = onePageDate;
	}


	public Map<String, Object> getTowPageDate() {
		return towPageDate;
	}


	public void setTowPageDate(Map<String, Object> towPageDate) {
		this.towPageDate = towPageDate;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getCard_id() {
		return card_id;
	}


	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	
	
}
