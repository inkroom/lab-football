package javadev.iip.service.art;

import java.util.Map;
import javadev.iip.service.BaseService;

/**
 * 功能描述：处理从ArtFourthAction中传过来的值
 * @author   刘俊
 * @version 1.0 
 * Create Date: 2016-8-31
 */
public class ArtFourthService extends BaseService{

	/**
	 * 接受ArtFourthAction中通过Map传过来的值并上传到数据库
	 * @param test
	 * @return
	 */
	public boolean updateArtFourth(Map<String, Object> map){
			jt.update("declare i integer; begin PRC_UPD_ART_REPORT_STUDENT_GZR(?,?,?,? ,?,?,?,?,? ,?,i,?,?,?,?,?); "
					+ "dbms_output.put_line(i); end;",new Object[]{map.get("o_id"),map.get("data1"),map.get("data2"),
							map.get("data3"),map.get("data4"),map.get("data5"),map.get("data6"),map.get("data7"),map.get("data8"),map.get("c_id"),
							map.get("data9"),map.get("data10"),map.get("data11"),map.get("data12"),map.get("score")});
		return false;
	}
}
