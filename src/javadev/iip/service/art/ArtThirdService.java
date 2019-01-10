package javadev.iip.service.art;

import java.util.HashMap;
import java.util.Map;
import javadev.iip.service.BaseService;
import javadev.iip.util.validata.V;

/**
 * 功能描述： 从ArtThirdAction传来的数据处理
 * @author刘俊良
 * @Create Date: 2016-8-31
 */
public class ArtThirdService extends BaseService {
	
	private static V v = new V();
	
	/**
	 * 功能的描述: 获取前一年的数据
	 * @author 刘俊良
	 * @return 获取到的数据
	 * @CreateDate 2016年9月1日19:25:23
	 */
	public Map<String,Object> getAllOldData(String oid) {
		Map<String,Object> oldData = new HashMap<String, Object>();
		Map<String,Object> oldArtData = new HashMap<String, Object>();
	   
	    oldArtData = getServMgr().getOtherService().getArtLastYearData(oid);
	   if(oldArtData != null){
			if(v.checkEmpty(oldArtData.get("MUS_CLASSROOM_IDEAL"))) {
				oldData.put("oldMusClassroomIdeal", "");
			}else{
				oldData.put("oldMusClassroomIdeal", oldArtData.get("MUS_CLASSROOM_IDEAL"));
			}
			
			if(v.checkEmpty(oldArtData.get("MUS_CLASSROOM_PRESENT"))){
				oldData.put("oldMusClassroomPresent", "");
			}else{
				oldData.put("oldMusClassroomPresent", oldArtData.get("MUS_CLASSROOM_PRESENT"));
			}
			
			if(v.checkEmpty(oldArtData.get("MUS_CLASSROOM_REQUIRED"))) {
				oldData.put("oldMusClassroomPequired", "");
			}else{
				oldData.put("oldMusClassroomPequired", oldArtData.get("MUS_CLASSROOM_REQUIRED"));
			}
			
			if(v.checkEmpty(oldArtData.get("PAINT_CLASSROOM_IDEAL"))) {
				oldData.put("oldPaintClassroomIdeal", "");
			}else{
				oldData.put("oldPaintClassroomIdeal", oldArtData.get("PAINT_CLASSROOM_IDEAL"));
			}	
			
			if(v.checkEmpty(oldArtData.get("PAINT_CLASSROOM_PRESENT"))) {
				oldData.put("oldPaintClassroomPresent", "");
			}else{
				oldData.put("oldPaintClassroomPresent", oldArtData.get("PAINT_CLASSROOM_PRESENT"));
			}
			
			if(v.checkEmpty(oldArtData.get("PAINT_CLASSROOM_REQUIRED"))) {
				oldData.put("oldPaintPequired", "");
			}else{
				oldData.put("oldPaintPequired", oldArtData.get("PAINT_CLASSROOM_REQUIRED"));
			}
			
			if(v.checkEmpty(oldArtData.get("FUND_REQUIRED"))) {
				oldData.put("oldFundRequired", "");
			}else{
				oldData.put("oldFundRequired", oldArtData.get("FUND_REQUIRED")+"万元");
				
			}
			
			if(v.checkEmpty(oldArtData.get("ART_CLASSROOM_OTHER"))) {
				oldData.put("oldArtClassroomOther", "");
				
			}else{
				oldData.put("oldArtClassroomOther", oldArtData.get("ART_CLASSROOM_OTHER"));
			}
			
			if(v.checkEmpty(oldArtData.get("ART_VENUES_NUM"))) {
				oldData.put("oldArtVenuesNum", "");
			}else{
				oldData.put("oldArtVenuesNum", oldArtData.get("ART_VENUES_NUM"));
				
			}
			
			if(v.checkEmpty(oldArtData.get("VENUES_AREA"))) {
				oldData.put("oldVenuesArea", "");
			}else{
				oldData.put("oldVenuesArea", oldArtData.get("VENUES_AREA")+"平方米");
			}
			
			if(v.checkEmpty(oldArtData.get("SELF_REMARK_ENSURANCE"))) {
				oldData.put("oldSelfRemarkEnsurance", "");
			}else{
				oldData.put("oldSelfRemarkEnsurance", oldArtData.get("SELF_REMARK_ENSURANCE"));
			}
			return oldData;
	   }else{
		   return null;
	   }
	}
	
	/**
	 * 功能的描述: 存储填写的数据到数据库
	 * @author 刘俊良
	 * @param map
	 * @return 存储数据是否成功
	 * @CreateDate 2016年9月1日19:25:23
	 */
	public boolean uploadForm(Map<String, Object> map){
			
		boolean flag = true;
		String sql = "declare i integer; begin PRC_UPD_ART_REPORT_CLASS_Z(?, ?,?,?, ?,?,?, ?,?, ?,?,?,?, ?, i); dbms_output.put_line(i); end;";
		
		try{
			jt.update(sql, new Object[]{map.get("O_ID"), map.get("musClassroomIdeal"),map.get("musClassroomPresent"),map.get("musClassroomPequired"), map.get("paintClassroomIdeal"),map.get("paintClassroomPresent"),map.get("paintPequired"), map.get("isEquipQualified"),map.get("fundRequired"), map.get("artClassroomOther"),map.get("artVenuesNum"), map.get("venuesArea"),map.get("selfRemarkEnsurance"), map.get("C_ID")});
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 功能的描述: 获取 艺术条件保障信息今年填写的数据
	 * @author: 刘俊良
	 * @return: 获取到的数据
	 * @CreateDate: 2016年9月1日19:25:23
	 */
	public Map<String, Object> seltDate(String oid){
		Map<String, Object> nowData = new HashMap<String, Object>();
		Map<String,Object> nowArtData = new HashMap<String,Object>();
		
		nowArtData = getServMgr().getOtherService().getArtData(oid);
		if(nowArtData != null){
			if (v.checkEmpty(nowArtData.get("MUS_CLASSROOM_IDEAL"))){
				nowData.put("musClassroomIdeal", "");
			}else{
				nowData.put("musClassroomIdeal", nowArtData.get("MUS_CLASSROOM_IDEAL"));
			}
				
			if (v.checkEmpty(nowArtData.get("MUS_CLASSROOM_PRESENT"))){
				nowData.put("musClassroomPresent", "");
			}else{
				nowData.put("musClassroomPresent", nowArtData.get("MUS_CLASSROOM_PRESENT"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("MUS_CLASSROOM_REQUIRED"))){
				nowData.put("musClassroomPequired", "");
			}else{
				nowData.put("musClassroomPequired", nowArtData.get("MUS_CLASSROOM_REQUIRED"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("PAINT_CLASSROOM_IDEAL"))){
				nowData.put("paintClassroomIdeal", "");
			}else{
				nowData.put("paintClassroomIdeal", nowArtData.get("PAINT_CLASSROOM_IDEAL"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("PAINT_CLASSROOM_PRESENT"))){
				nowData.put("paintClassroomPresent", "");
			}else{
				nowData.put("paintClassroomPresent", nowArtData.get("PAINT_CLASSROOM_PRESENT"));
			}
				
			if (v.checkEmpty(nowArtData.get("PAINT_CLASSROOM_REQUIRED"))){
				nowData.put("paintPequired", "");
			}else{
				nowData.put("paintPequired", nowArtData.get("PAINT_CLASSROOM_REQUIRED"));
			}
				
			if (v.checkEmpty(nowArtData.get("IS_EQUIP_QUALIFIED"))) {
				nowData.put("isEquipQualified", "1");
			}else{
				nowData.put("isEquipQualified", nowArtData.get("IS_EQUIP_QUALIFIED"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("FUND_REQUIRED"))){
				nowData.put("fundRequired", "");
			}else{
				nowData.put("fundRequired", nowArtData.get("FUND_REQUIRED"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("ART_CLASSROOM_OTHER"))){
				nowData.put("artClassroomOther", "");
			}else{
				nowData.put("artClassroomOther", nowArtData.get("ART_CLASSROOM_OTHER"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("ART_VENUES_NUM"))){
				nowData.put("artVenuesNum", "");
			}else{
				nowData.put("artVenuesNum", nowArtData.get("ART_VENUES_NUM"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("VENUES_AREA"))){
				nowData.put("venuesArea", "");
			}else{
				nowData.put("venuesArea", nowArtData.get("VENUES_AREA"));
				
			}
				
			if (v.checkEmpty(nowArtData.get("SELF_REMARK_ENSURANCE"))){
				nowData.put("selfRemarkEnsurance", "");
			}else{
				nowData.put("selfRemarkEnsurance", nowArtData.get("SELF_REMARK_ENSURANCE"));
				
			}
			
			return nowData;
		}else{
			return null;
		}
	}
	
	/**
	 * 功能的描述: 检验ArtSecond填写的数据是否填写完成
	 * @author: 刘俊良
	 * @return: 是否填写完整
	 * @CreateDate: 2016年9月7日19:00:23
	 */
	public int detectionArtndData(String oid) {
		
		Map<String, Object> ndData = getServMgr().getOtherService().getArtData(oid);
		int flag = 0;
		if(ndData != null){
			if(flag == 0 && v.checkEmpty(ndData.get("TEACHERS_MUS_IDEAL"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("TEACHERS_MUS_PRESENT"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("SELF_REMARK_ART_TEACHER"))) {
				flag = 1;
			}
		}else{
			flag = 2;
		}
		return flag;
	}
	
}
