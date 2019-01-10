package javadev.iip.service.sport;

import java.util.HashMap;
import java.util.Map;
import javadev.iip.service.BaseService;
import javadev.iip.util.validata.V;

/*功能描述： 从sportThirdAction传来的数据处理
* @author刘俊良
* @Create Date: 2016-8-31
*/
public class SportThirdService extends BaseService {
	
	private static V v = new V();
	/**
	 * 功能的描述: 获取前一年的数据
	 * @author 刘俊良
	 * @return 获取到的数据
	 * @CreateDate 2016年9月1日19:25:23
	 */
	public Map<String,Object> getAllOldData(String oid) {
			Map<String,Object> oldData = new HashMap<String,Object>();
			Map<String,Object> oldRptData = new HashMap<String, Object>();
			oldRptData = getServMgr().getOtherService().getSportLastYearData(oid);
			
			if(oldRptData != null){
					
				if(v.checkEmpty(oldRptData.get("EXPENSE"))) {
					oldData.put("oldExpense", "");
				}else{
					oldData.put("oldExpense", oldRptData.get("EXPENSE")+"万元");
					
				}	
						
				if(v.checkEmpty(oldRptData.get("EXPENSE_FIELD"))) {
					oldData.put("oldExpenseField", "");
				}else{
					oldData.put("oldExpenseField", oldRptData.get("EXPENSE_FIELD")+"万元");
					
				}
				
				if(v.checkEmpty(oldRptData.get("EXPENSE_FIELD"))) {
					oldData.put("oldExpenseField", "");
				}else{
					oldData.put("oldExpenseField", oldRptData.get("EXPENSE_FIELD")+"万元");
					
				}
				
				if(v.checkEmpty(oldRptData.get("EXPENSE_EQUIP"))) {
					oldData.put("oldExpenseEquip", "");
				}else{
					oldData.put("oldExpenseEquip", oldRptData.get("EXPENSE_EQUIP")+"万元");
					
				}
				
				if(v.checkEmpty(oldRptData.get("EXPENSE_ACTIVITY"))) {
					oldData.put("oldExpenseActivity", "");
				}else{
					oldData.put("oldExpenseActivity", oldRptData.get("EXPENSE_ACTIVITY")+"万元");
					
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
	public boolean uploadForm (Map<String, String> map) {
		
		boolean flag = true;
		String sql = "declare i integer; begin PRC_UPD_PHY_REPORT_Z(?, ?, ?,?,?,?, ?,?,i); dbms_output.put_line(i); end;";
		
		try{
			jt.update(sql, new Object[]{map.get("O_ID"), map.get("isAddPointProject"), map.get("expense"),map.get("expenseField"),map.get("expenseEquip"),map.get("expenseActivity"),map.get("isInsurance"),map.get("C_ID")});
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 功能的描述: 获取体育经费和工作等级评估信息今年填写的数据
	 * @author: 刘俊良
	 * @return: 获取到的数据
	 * @CreateDate: 2016年9月1日19:25:23
	 */
	public Map<String, Object> seltDate(String oid){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> nowData = new HashMap<String,Object>();
		nowData = getServMgr().getOtherService().getSportData(oid);
			
		if(nowData != null){	
			if(v.checkEmpty(nowData.get("IS_ADD_POINT_PROJECT"))){
				map.put("isAddPointProject", "1");
			}else{
				map.put("isAddPointProject", nowData.get("IS_ADD_POINT_PROJECT"));
				
			}
			
			if(v.checkEmpty(nowData.get("EXPENSE"))){
				map.put("expense", "");
			}else{
				map.put("expense", nowData.get("EXPENSE"));
				
			}
			
			if(v.checkEmpty(nowData.get("EXPENSE_FIELD"))){
				map.put("expenseField", "");
			}else{
				map.put("expenseField", nowData.get("EXPENSE_FIELD"));
				
			}
			
			if(v.checkEmpty(nowData.get("EXPENSE_EQUIP"))){
				map.put("expenseEquip", "");
			}else{
				map.put("expenseEquip", nowData.get("EXPENSE_EQUIP"));
				
			}
			
			if(v.checkEmpty(nowData.get("EXPENSE_ACTIVITY"))){
				map.put("expenseActivity", "");
			}else{
				map.put("expenseActivity", nowData.get("EXPENSE_ACTIVITY"));
				
			}
			
			if(v.checkEmpty(nowData.get("IS_INSURANCE"))){
				map.put("isInsurance", "1");
			}else{
				map.put("isInsurance", nowData.get("IS_INSURANCE"));
				
			}
			return map;
		}else{
			return null;	
		}
	}
	
	/**
	 * 功能的描述: 检验sportSecond填写的数据是否填写完成
	 * @author: 刘俊良
	 * @return: 是否填写完整
	 * @CreateDate: 2016年9月7日19:00:23
	 */
	public int detectionSportNdData(String oid) {
		
		Map<String, Object> ndData = getServMgr().getOtherService().getSportData(oid);
		int flag = 0;
		if(ndData != null){
			if(flag == 0 && v.checkEmpty(ndData.get("FIELD_TRACK_200M"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("FIELD_TRACK_300M"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("FIELD_TRACK_300_400M"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("FIELD_TRACK_400M"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("FIELDS_BASKETBALL"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("FIELDS_VOLLEYBALL"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("FIELDS_GYM_AREA"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("STADIUM_TOTAL"))) {
				flag = 1;
			}
			
			if(flag == 0 && v.checkEmpty(ndData.get("STADIUM_AREA"))) {
				flag = 1;
			}
		}else{
			flag = 2;
		}
		return flag;
	}

}
