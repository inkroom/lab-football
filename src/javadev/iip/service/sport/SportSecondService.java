package javadev.iip.service.sport;

import java.util.HashMap;
import java.util.Map;
import javadev.iip.service.BaseService;


/**
 * 功能描述：处理从SportSecondAction中传过来的值
 * @author   刘俊
 * @version 1.0 
 * Create Date: 2016-8-31
 */
public class SportSecondService extends BaseService {

	
	
	/**
	 * 接受SportSecondAction中通过Map传过来的值并上传到数据库
	 * @param test
	 * @return
	 */
	public boolean updateSportSecond(Map<String, Object> map) throws Exception {	
	
		try {
			jt.update("declare i integer; begin PRC_UPD_PHY_REPORT_SPORT_GZR(?,?,?,? ,?,?,?,?,? ,?,?,?,?,?,?,i); "
					+ "dbms_output.put_line(i); end;",new Object[]{map.get("o_id"),map.get("data1"),map.get("data2"),
							map.get("data3"),map.get("data4"),map.get("data5"),map.get("data6"),map.get("data7"),map.get("data8"),
							map.get("data9"),map.get("data10"),map.get("data11"),map.get("data12"),map.get("data13"),map.get("c_id")});
			System.out.println("==================aaaaaaaaaaaa");
		} catch (Exception e) {
			log.error("===========================");
		}
		return false;
	}
	
}
