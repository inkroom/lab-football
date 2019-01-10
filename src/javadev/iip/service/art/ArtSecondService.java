package javadev.iip.service.art;

import java.util.Map;

import javadev.iip.service.BaseService;

/**
 * 功能描述：处理从ArtFirstAction中传过来的值
 * @author 曾绩平
 * @version 1.0 
 * Create Date: 2016-8-31
 */

public class ArtSecondService extends BaseService {

	/**
	 * 接收ArtFirstActon中通过Map传过来的值
	 * @param map
	 */
	public void insertArtSecond(Map<String, Object> map){
		
		//将从ArtFirstActon传过来的值存入数据库
		jt.update("declare i integer; begin PRC_UPD_ART_REPORT_gaozr(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,? ,i); dbms_output.put_line(i); end;",
				new Object[]{map.get("o_id"), map.get("musicShouldTeacher"), map.get("musicRealTeacher"), map.get("musicGapsTeacher"), map.get("musicPartTimeTeacher"), map.get("musicRatioST"), map.get("musicClassNum"),
						map.get("artShouldTeacher"), map.get("artRealTeacher"), map.get("artGapsTeacher"), map.get("artPartTimeTeacher"), map.get("artRatioST"), map.get("artClassNum"),
						map.get("train"), map.get("honor"), map.get("selfAssessment"), map.get("c_id")});

	}
}
