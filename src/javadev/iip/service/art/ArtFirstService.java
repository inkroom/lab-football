package javadev.iip.service.art;


import java.util.Map;
import javadev.iip.service.BaseService;


/**
 * 功能描述：处理从ArtFirstAction中传过来的值
 * @author   朱明民
 * @version 1.0 
 * Create Date: 2016-8-31
 */

public class ArtFirstService extends BaseService{
	
	/**
	 * 接受ArtFirstAction中通过Map传过来的值存入数据库
	 * @param map
	 * @return 
	 */
	   
	public boolean insertArtFirst(Map<String, String> map){
		
		System.out.println("+++++++++++++++++++++++++"+map.get("DATA8"));

		jt.update("declare i integer; begin PRC_UPD_ART_REPORT_CLASS_GZR(?,?,?,?,? ,?,?,?,?,? ,?,?,?,i); dbms_output.put_line(i); end;",
				new Object[]{map.get("DATAid"),map.get("DATA1"),map.get("DATA2"),map.get("DATA3"),map.get("DATA4"),map.get("DATA5"),map.get("DATA6"),map.get("DATA7"),map.get("DATA8"),map.get("DATA9"),map.get("DATA10"),map.get("DATA11"),map.get("C_ID")});
       
		
		return false;
	}
	
	
}
