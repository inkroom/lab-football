package javadev.iip.service.art;

import java.util.Map;

import javadev.iip.service.BaseService;

public class ArtEndService extends BaseService{
	public boolean updateArtEnd(String name,String phone,String O_ID) {
		  try{
			    String sql="update art_write set PERSON_NAME=?,PERSON_PHONE=?,OPER_BY=? , OPER_DATE=SYSDATE  where O_ID=?";
				jt.update(sql,new Object[]{name,phone,O_ID,O_ID});
			    return true;	
		  }catch(Exception e){
			  e.printStackTrace();
			  return false;
		  }
    }
	
	public Map<String, Object> selectArtEnd(String o_id) throws Exception{	
			String sql="select PERSON_NAME ,PERSON_PHONE FROM ART_WRITE WHERE O_ID=? AND STATUS = 1";
		     return jt.queryForMap(sql, new Object[]{o_id});
	}
	
	
}
