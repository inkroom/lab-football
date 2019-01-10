package javadev.iip.service.test;

import java.util.HashMap;
import java.util.Map;

import javadev.iip.service.BaseService;

public class RepostFormService extends BaseService {

	//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
		//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
		//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
		//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
	/**
	 * 功能描述：demo表单的提交service
	 * 
	 * @author 朱春雨
	 * Create Date ：16-08-30
	 */
	//得到上一年的数据
	public Map<String,Object> getAllOldData()
	{
		Map<String,Object> oldData = new HashMap();;
		oldData.put("odata1", 1);
		oldData.put("odata2", 2);
		oldData.put("odata3", 3);
		oldData.put("odata4", 4);
		oldData.put("odata5", 5);
		oldData.put("odata6", 6);
		oldData.put("odata7", 7);
		oldData.put("odata8", 8);
		oldData.put("odata9", 9);
		oldData.put("odata10",10 );
		oldData.put("odata11", 11);
		oldData.put("odata12", 12);
		oldData.put("odata13", 13);
		return oldData;
		
	}
	//提交表单
	public boolean uploadForm(String data1,String data2,String data3,String data4,String data5,String data6,String data7,String data8,String data9,String data10,String data11,String data12,String data13) {
        System.out.println("得到的数据为："+data1+" "+data2+" "+data3+" "+data4+" "+data5+" "+data6+" "+data7+" "+data8+" "+data9+" "+data10+" "+data11+" "+data12+" "+data13);    
		return true;		
	}
}
