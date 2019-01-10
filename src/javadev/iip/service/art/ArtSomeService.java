package javadev.iip.service.art;

import java.util.Map;

import javadev.iip.service.BaseService;

public class ArtSomeService extends BaseService{
	/**
	 * 接受ArtSomeAction中通过Map传过来的值存入数据库
	 * @param map
	 * @return 
	 */
	   
	public boolean insertArtSome(Map<String, String> map){
	
	 
		jt.update("begin PRC_UPD_ART_REP_LU(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?);end;",			
		new Object[]{map.get("ArtSome1"),map.get("ArtSome2"),map.get("ArtSome3"),map.get("ArtSome4"),map.get("ArtSome5"),map.get("ArtSome6"),map.get("ArtSome7"),map.get("ArtSome8"),map.get("ArtSome9"),map.get("ArtSome10"),map.get("ArtSome11"),map.get("ArtSome12"),map.get("ArtSome13"),map.get("ArtSome14"),map.get("ArtSome15"),map.get("ArtSome16"),map.get("ArtSome17"),map.get("ArtSomeid"),map.get("C_ID")});
       
		
		return false;
	}

}
