package javadev.iip.action.fiveplan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
/**
 * 功能描述：用于 汇总显示页面Action
 * @author:彭波
 * @version：1.0
 * create Date ：2016年9月26日16：13：23
 */
public class ViewFiveSumAction extends BaseAction{
	
	private String isFinshMessage;
	//表1的字段
	private String is_achieve_play_ground;
	private String add_play_ground;
	private String add_play_ground_area;
	private String spend_for_add_play_ground;
	private String extension_modify_play_ground;
	private String area_exten_modify_play_ground;
	private String spend_for_mod_play_ground;
	private String is_add_euqi_gro;
	private String spend_euqi_gro_totaL;
	//表2的字段
	private String is_achieve_music_room;
	private String spend_for_music_equi;
	private String is_achieve_music_equi;
	private String spend_for_music_equit;
	private String is_achieve_draw_room;
	private String spend_for_draw_room;
	private String is_achieve_draw_equi;
	private String spend_for_draw_equi;
	//表3的字段
	private String add_new_phy_teachers_full;
	private String retire_phy_teacher;
	private String growth_phy_teacher;
	private String add_new_music_teachers_full;
	private String retire_music_teacher;
	private String growth_music_teacher;
	private String add_new_draw_teachers_full;
	private String retire_draw_teacher;
	private String growth_draw_teacher;
	//表四的字段
	private String phy_teacher_exer;
	private String spend_phy_teacher_exer;
	private String music_teacher_exer;
	private String spend_music_teacher_exer;
	private String draw_teacher_exer;
	private String spend_draw_teacher_exer;
	private Map<String,Object> uMp;
	private Map<String, Object> preData;
	private String o_name;
	
	public String intoViewFiveSum(){	
		try {
			isFinshMessage = getSession().get("isFinshMessage").toString();
			getSession().remove("isFinshMessage");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		Date date = new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time1=Integer.valueOf(time.format(date));
		String fp_year=time1.toString();
		Map data = (Map) getSession().get(Constants.LOGIN_USER);
		String a_id= data.get("A_ID").toString();
		String o_id= data.get("O_ID").toString();
	
		uMp=getServMgr().getDownLoadExcelService().getUserName(o_id);
		
		preData=getServMgr().getDownLoadExcelService().getAllData(o_id, fp_year);
		if(preData.get("is_achieve_play_ground")!=null){	
		String is_achieve_play_ground = preData.get("is_achieve_play_ground").toString();
		is_achieve_play_ground=isChange(is_achieve_play_ground);
		String is_add_euqi_gro = preData.get("is_add_euqi_gro").toString();
		is_add_euqi_gro=isChange(is_add_euqi_gro);
		preData.put("is_achieve_play_ground", is_achieve_play_ground);preData.put("is_add_euqi_gro", is_add_euqi_gro);
		}
		if(preData.get("is_achieve_music_room")!=null){	
		String is_achieve_music_room = preData.get("is_achieve_music_room").toString();
		is_achieve_music_room=isChange2(is_achieve_music_room);
		String is_achieve_music_equi = preData.get("is_achieve_music_equi").toString();
		is_achieve_music_equi=isChange2(is_achieve_music_equi);
		String is_achieve_draw_room = preData.get("is_achieve_draw_room").toString();
		is_achieve_draw_room=isChange2(is_achieve_draw_room);
		String is_achieve_draw_equi = preData.get("is_achieve_draw_equi").toString();
		is_achieve_draw_equi=isChange2(is_achieve_draw_equi);
		preData.put("is_achieve_music_room", is_achieve_music_room);preData.put("is_achieve_music_equi", is_achieve_music_equi);
		preData.put("is_achieve_draw_room", is_achieve_draw_room);preData.put("is_achieve_draw_equi", is_achieve_draw_equi);
	}
		return "intointoViewFiveSum";
	} 
	private String isChange(Object object){
		String str = object.toString();
		if(str.equals("1")){
			return "是，今年新增达标";
		}else if(str.equals("2")){
			return "否，还未配备达标";
		}else if(str.equals("3")){
			return "是，往年配备达标";
		}else{
			return " ";
		}
	}
	private String isChange2(Object object){
		String str = object.toString();
		if(str.equals("1")){
			return "是，今年新增达标";
		}else if(str.equals("2")){
			return "否，还未配备达标";
		}else if(str.equals("3")){
			return "是，往年配备达标";
		}else{
			return " ";
		}
	}
	public String getIs_achieve_play_ground() {
		return is_achieve_play_ground;
	}
	public void setIs_achieve_play_ground(String is_achieve_play_ground) {
		this.is_achieve_play_ground = is_achieve_play_ground;
	}
	public String getAdd_play_ground() {
		return add_play_ground;
	}
	public void setAdd_play_ground(String add_play_ground) {
		this.add_play_ground = add_play_ground;
	}
	public String getAdd_play_ground_area() {
		return add_play_ground_area;
	}
	public void setAdd_play_ground_area(String add_play_ground_area) {
		this.add_play_ground_area = add_play_ground_area;
	}
	public String getSpend_for_add_play_ground() {
		return spend_for_add_play_ground;
	}
	public void setSpend_for_add_play_ground(String spend_for_add_play_ground) {
		this.spend_for_add_play_ground = spend_for_add_play_ground;
	}
	public String getExtension_modify_play_ground() {
		return extension_modify_play_ground;
	}
	public void setExtension_modify_play_ground(String extension_modify_play_ground) {
		this.extension_modify_play_ground = extension_modify_play_ground;
	}
	public String getArea_exten_modify_play_ground() {
		return area_exten_modify_play_ground;
	}
	public void setArea_exten_modify_play_ground(String area_exten_modify_play_ground) {
		this.area_exten_modify_play_ground = area_exten_modify_play_ground;
	}
	public String getSpend_for_mod_play_ground() {
		return spend_for_mod_play_ground;
	}
	public void setSpend_for_mod_play_ground(String spend_for_mod_play_ground) {
		this.spend_for_mod_play_ground = spend_for_mod_play_ground;
	}
	public String getIs_add_euqi_gro() {
		return is_add_euqi_gro;
	}
	public void setIs_add_euqi_gro(String is_add_euqi_gro) {
		this.is_add_euqi_gro = is_add_euqi_gro;
	}
	public String getSpend_euqi_gro_totaL() {
		return spend_euqi_gro_totaL;
	}
	public void setSpend_euqi_gro_totaL(String spend_euqi_gro_totaL) {
		this.spend_euqi_gro_totaL = spend_euqi_gro_totaL;
	}
	public String getIs_achieve_music_room() {
		return is_achieve_music_room;
	}
	public void setIs_achieve_music_room(String is_achieve_music_room) {
		this.is_achieve_music_room = is_achieve_music_room;
	}
	public String getSpend_for_music_equi() {
		return spend_for_music_equi;
	}
	public void setSpend_for_music_equi(String spend_for_music_equi) {
		this.spend_for_music_equi = spend_for_music_equi;
	}
	public String getIs_achieve_music_equi() {
		return is_achieve_music_equi;
	}
	public void setIs_achieve_music_equi(String is_achieve_music_equi) {
		this.is_achieve_music_equi = is_achieve_music_equi;
	}
	public String getSpend_for_music_equit() {
		return spend_for_music_equit;
	}
	public void setSpend_for_music_equit(String spend_for_music_equit) {
		this.spend_for_music_equit = spend_for_music_equit;
	}
	public String getIs_achieve_draw_room() {
		return is_achieve_draw_room;
	}
	public void setIs_achieve_draw_room(String is_achieve_draw_room) {
		this.is_achieve_draw_room = is_achieve_draw_room;
	}
	public String getSpend_for_draw_room() {
		return spend_for_draw_room;
	}
	public void setSpend_for_draw_room(String spend_for_draw_room) {
		this.spend_for_draw_room = spend_for_draw_room;
	}
	public String getIs_achieve_draw_equi() {
		return is_achieve_draw_equi;
	}
	public void setIs_achieve_draw_equi(String is_achieve_draw_equi) {
		this.is_achieve_draw_equi = is_achieve_draw_equi;
	}
	public String getSpend_for_draw_equi() {
		return spend_for_draw_equi;
	}
	public void setSpend_for_draw_equi(String spend_for_draw_equi) {
		this.spend_for_draw_equi = spend_for_draw_equi;
	}
	public String getAdd_new_phy_teachers_full() {
		return add_new_phy_teachers_full;
	}
	public void setAdd_new_phy_teachers_full(String add_new_phy_teachers_full) {
		this.add_new_phy_teachers_full = add_new_phy_teachers_full;
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
	public String getAdd_new_music_teachers_full() {
		return add_new_music_teachers_full;
	}
	public void setAdd_new_music_teachers_full(String add_new_music_teachers_full) {
		this.add_new_music_teachers_full = add_new_music_teachers_full;
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
	public String getAdd_new_draw_teachers_full() {
		return add_new_draw_teachers_full;
	}
	public void setAdd_new_draw_teachers_full(String add_new_draw_teachers_full) {
		this.add_new_draw_teachers_full = add_new_draw_teachers_full;
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
	public Map<String, Object> getPreData() {
		return preData;
	}
	public void setPreData(Map<String, Object> preData) {
		this.preData = preData;
	}
	public Map<String, Object> getuMp() {
		return uMp;
	}
	public void setuMp(Map<String, Object> uMp) {
		this.uMp = uMp;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public String getIsFinshMessage() {
		return isFinshMessage;
	}
	public void setIsFinshMessage(String isFinshMessage) {
		this.isFinshMessage = isFinshMessage;
	}
	
}
