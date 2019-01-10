package javadev.iip.action.fiveplan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.components.Debug;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;

import javadev.iip.util.validata.V;
import oracle.net.aso.s;

public class PageOneAction extends BaseAction{


	private String newToken1;
	private V v=new V();//验证工具
	private String is_achieve_play_ground;//新增运动场是否达标
	private String add_play_ground;//其中新建（个）
	private String add_play_ground_area;//新增面积（㎡）
	private String spend_for_add_play_ground;//投入金额（万元）
	private String extension_modify_play_ground;//改扩建（个）
	private String area_exten_modify_play_ground;//改扩建面积（㎡）
	private String spend_for_mod_play_ground;//投入金额（万元）
	private String is_add_euqi_gro;//新增体育器材是否达标
	private String spend_euqi_gro_totaL;//投入金额（万元）
	private String stage;//学校类型
	private Map<String,Object> oldData;//旧数据
	private Map<String,Object> newData;//新数据
	private Map<String, Object> oldYearData;
	private String errorInfo;//打印错误信息

	Map<String, Object> data = new HashMap<String,Object>();
	public static final int  Flag = 1;

	
    
	public String intoRepostForm()
	{
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time1=Integer.valueOf(time.format(date))-1;
		Integer time2=Integer.valueOf(time.format(date));
		String year1=time1.toString();
		String year2=time2.toString();

		oldYearData = getServMgr().getPageOneService().getAllOldData(getOID(),year1,2);
		newData = getServMgr().getPageOneService().getAllOldDatas( getOID(), year2,1);
		try {
			if(!v.checkEmpty(newData.get("stage")))
		        stage=newData.get("stage").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(!v.checkEmpty(newData.get("is_achieve_play_ground")))
		        is_achieve_play_ground=newData.get("is_achieve_play_ground").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(!v.checkEmpty(newData.get("is_add_euqi_gro")))
		        is_add_euqi_gro=newData.get("is_add_euqi_gro").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "intoRepostForm";
	
	}
	private String getAID(){
		String a_id =  ((Map) getSession().get(Constants.LOGIN_USER)).get("A_ID").toString();
		if(!v.checkEmpty(a_id)){
			return a_id;
		}else{
			return "";
		}
	}
	private String getOID(){
		String o_id =  ((Map) getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		if(!v.checkEmpty(o_id)){
			return o_id;
		}else{
			return "";
		}
	}
	public String uploadPageOneForm(){

		newToken1 = (String) getRequest().getSession().getAttribute("token");
		
        if(add_play_ground==null)
        	add_play_ground="";
        if(add_play_ground_area==null)
        	add_play_ground_area="";
        if(spend_for_add_play_ground==null)
        	spend_for_add_play_ground="";
        if(extension_modify_play_ground==null)
        	extension_modify_play_ground="";
        if(area_exten_modify_play_ground==null)
        	area_exten_modify_play_ground="";
        if(spend_for_mod_play_ground==null)
        	spend_for_mod_play_ground="";
        if(spend_euqi_gro_totaL==null)
        	spend_euqi_gro_totaL="";
		if(is_achieve_play_ground.equals("1"))
		{
		add_play_ground=add_play_ground.trim();
		add_play_ground_area=add_play_ground_area.trim();
		spend_for_add_play_ground=spend_for_add_play_ground.trim();
		extension_modify_play_ground=extension_modify_play_ground.trim();
		area_exten_modify_play_ground=area_exten_modify_play_ground.trim();
		spend_for_mod_play_ground=spend_for_mod_play_ground.trim();
		}

		if(is_add_euqi_gro.equals("1"))
	    spend_euqi_gro_totaL=spend_euqi_gro_totaL.trim();
		
		if(is_achieve_play_ground.equals("1"))
		{
		if(!(V.isZCPNumbers(add_play_ground))){errorInfo="其中新建（个）最大为8位非负整数"; return "errorIn";}
		if(!(V.isZCPNumbers(extension_modify_play_ground))){errorInfo="改扩建（个）最大为8位非负整数"; return "errorIn";}
		if(!(V.isZCPNumbers(add_play_ground_area))){errorInfo="新建面积（㎡）最大为8位非负整数"; return "errorIn";}
		if(!(V.isZCPNumbers(area_exten_modify_play_ground))){errorInfo="改扩建面积（㎡）最大为8位非负整数";return "errorIn";}
		if(!(V.isZCNumbers(spend_for_add_play_ground))){errorInfo="投入金额（万元）整数最大为8位非负数，小数最多精确到4位";return "errorIn";}
		if(!(V.isZCNumbers(spend_for_mod_play_ground))){errorInfo="投入金额（万元）整数最大为8位非负数，小数最多精确到4位";return "errorIn";}
		}

		if(is_add_euqi_gro.equals("1"))
		{		
		if(!(V.isZCNumbers(spend_euqi_gro_totaL))){errorInfo="投入金额（万元）整数最大为8位非负数，小数最多精确到4位";return "errorIn";}
		}
		
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time2=Integer.valueOf(time.format(date));
		String year2=time2.toString();
		newData = getServMgr().getPageOneService().getAllOldData( getOID(), year2,1);
		String nstage = "",nis_achieve_play_ground="",nadd_play_ground="",nadd_play_ground_area="",nspend_for_add_play_ground="",nextension_modify_play_ground="",narea_exten_modify_play_ground="",nspend_for_mod_play_ground="",nis_add_euqi_gro="",nspend_euqi_gro_totaL="";
		try {
			if(!v.checkEmpty(newData.get("stage")))
		        nstage=newData.get("stage").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(!v.checkEmpty(newData.get("is_achieve_play_ground")))
		        nis_achieve_play_ground=newData.get("is_achieve_play_ground").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(!v.checkEmpty(newData.get("add_play_ground")))
		        nadd_play_ground=newData.get("add_play_ground").toString();	
		} catch (Exception e) {
			// TODO: handle exception
		}try {
			if(!v.checkEmpty(newData.get("add_play_ground_area")))
		        nadd_play_ground_area=newData.get("add_play_ground_area").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}try {
			if(!v.checkEmpty(newData.get("spend_for_add_play_ground")))
		        nspend_for_add_play_ground=newData.get("spend_for_add_play_ground").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}try {
			if(!v.checkEmpty(newData.get("extension_modify_play_ground")))
		        nextension_modify_play_ground=newData.get("extension_modify_play_ground").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}try {
			if(!v.checkEmpty(newData.get("area_exten_modify_play_ground")))
		        narea_exten_modify_play_ground=newData.get("area_exten_modify_play_ground").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}try {
			if(!v.checkEmpty(newData.get("spend_for_mod_play_ground")))
				nspend_for_mod_play_ground=newData.get("spend_for_mod_play_ground").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(!v.checkEmpty(newData.get("is_add_euqi_gro")))
		        nis_add_euqi_gro=newData.get("is_add_euqi_gro").toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if(!v.checkEmpty(newData.get("spend_euqi_gro_totaL")))
		        nspend_euqi_gro_totaL=newData.get("spend_euqi_gro_totaL").toString();
		} catch (Exception e) {
			// TODO: handle exception
		} 
		log.debug("iiiiiiiiiiiiiiiiiiiiiiiii"+stage+"."+nstage+"."+is_achieve_play_ground+"."+nis_achieve_play_ground+"."+add_play_ground+"."+nadd_play_ground+"."+add_play_ground_area+"."+nadd_play_ground_area+"."+spend_for_add_play_ground+"."+nspend_for_add_play_ground+"."+extension_modify_play_ground+"."+nextension_modify_play_ground+"."+area_exten_modify_play_ground+"."+narea_exten_modify_play_ground+"."+spend_for_mod_play_ground+"."+nspend_for_mod_play_ground+"."+is_add_euqi_gro+"."+nis_add_euqi_gro+"."+spend_euqi_gro_totaL+"."+nspend_euqi_gro_totaL);
		 if(stage.equals(nstage)&&is_achieve_play_ground.equals(nis_achieve_play_ground)&&add_play_ground.trim().equals(nadd_play_ground)&&add_play_ground_area.trim().equals(nadd_play_ground_area)&&spend_for_add_play_ground.trim().equals(nspend_for_add_play_ground)&&extension_modify_play_ground.trim().equals(nextension_modify_play_ground)&&area_exten_modify_play_ground.trim().equals(narea_exten_modify_play_ground)&&spend_for_mod_play_ground.trim().equals(nspend_for_mod_play_ground)&&is_add_euqi_gro.trim().equals(nis_add_euqi_gro)&&spend_euqi_gro_totaL.trim().equals(nspend_euqi_gro_totaL))
		 {
        	log.debug("ooooooooooooooooooooooooo");
        }
        else {
		getServMgr().getPageOneService().uploadForm(getOID(),is_achieve_play_ground, add_play_ground, 
				add_play_ground_area,  spend_for_add_play_ground,
				extension_modify_play_ground, area_exten_modify_play_ground, spend_for_mod_play_ground,is_add_euqi_gro,spend_euqi_gro_totaL,stage,getAID());
        }
		
		return "uploadPageOneForm";
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getStage() {
		return stage;
	}
    

	public void setStage(String stage) {
		this.stage = stage;
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

	public Map<String, Object> getOldData() {
		return oldData;
	}

	public void setOldData(Map<String, Object> oldData) {
		this.oldData = oldData;
	}

	public static int getFlag() {
		return Flag;
	}
	public Map<String, Object> getOldYearData() {
		return oldYearData;
	}
	public void setOldYearData(Map<String, Object> oldYearData) {
		this.oldYearData = oldYearData;
	}
	public Map<String, Object> getNewData() {
		return newData;
	}
	public void setNewData(Map<String, Object> newData) {
		this.newData = newData;
	}

	public String getNewToken1() {
		return newToken1;
	}
	public void setNewToken1(String newToken1) {
		this.newToken1 = newToken1;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
    
	
    
}