package com.nsu.service.school.admin;

import com.nsu.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SchoolIndexAdminService extends BaseService {
	/**
	 * 修改学校主页照片
	 */
	private final static String UPDATE_SCHOOL_INDEX_ROLL_PIC = "update school_picture set save_path = ? where school_url = ? and pic_num = ? and type = 1";
	public boolean UpdateSchoolIndexRollPic(String savePath,String schoolUrl,String picNum){
		return jt.update(UPDATE_SCHOOL_INDEX_ROLL_PIC,new Object[]{savePath,schoolUrl,picNum}) == 1;
	}
	
	private final static String UPDATE_SCHOOL_INDEX_HEAD_PIC = "update school_picture set save_path = ? where school_url = ? and type = 2";
	public boolean UpdateSchoolIndexHeadPic(String savePath,String schoolUrl){
		return jt.update(UPDATE_SCHOOL_INDEX_HEAD_PIC,new Object[]{savePath,schoolUrl}) == 1;
	}
}
