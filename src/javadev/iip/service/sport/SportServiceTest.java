package javadev.iip.service.sport;

import java.util.HashMap;
import java.util.Map;
import javadev.iip.service.BaseService;

/**
 * 体育Service层
 * @author MeiXiebing
 *
 */
public class SportServiceTest extends BaseService {
	
	/**
	 * 获取体育所有数据
	 */
//	private final String Get_Sport_Data_ID =  "SELECT * FORM where  ";
	public Map getSportData(Map<String , String> test){
		
		System.out.println("--------"+test.get("name"));
		
		Map<String, String> map = new HashMap<String,String>();
		return map;
	}
	
}
