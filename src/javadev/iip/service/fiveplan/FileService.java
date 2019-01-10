package javadev.iip.service.fiveplan;

import javadev.iip.service.BaseService;

public class FileService extends BaseService{
	
	private static final String INSERT_UPLOADFILES = "insert into UPLOAD_FILES(U_ID,"
			+ "SAVE_PATH,FILE_TYPE,STATUS,CREATE_DATE,CREATE_BY,OPER_DATE,OPER_BY,REPORT_TYPE,O_ID) "
			+ "VALUES (seq_upload_files.nextval,?,?,?,sysdate,?,sysdate,?,?,?)";
	
	public boolean addUploadFiles(String save_path,String file_type , String status,
			 String create_by,String oper_by ,String report_type,String o_id){
		return jt.update(INSERT_UPLOADFILES,save_path,file_type,status,create_by,oper_by,report_type,o_id)>0?true:false;
	}

}
