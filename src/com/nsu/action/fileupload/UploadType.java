package com.nsu.action.fileupload;

/**
 * 功能描述：定义上传文件的类型
 * 
 * @author RenQiang
 * @version 1.0 Create Date: 2016-04-18
 */
public enum UploadType {
	User_Picture_Edit_SchoolInfo("User_Picture_Edit_SchoolInfo", 1, 1),
	User_Picture_Edit_SchoolNews("User_Picture_Edit_SchoolNews", 2, 1),
	User_Picture_Edit_SchoolPeople("User_Picture_Edit_SchoolPeople", 3, 1),
	User_Picture_Edit_SchoolFeature("User_Picture_Edit_SchoolFeature", 4, 1),
	
	User_Picture_SchoolIndex("User_Picture_SchoolIndex", 5, 1),
	User_Picture_SchoolAdvertisement("User_Picture_SchoolAdvertisement", 6, 1),
	User_Picture_SchoolPeople("User_Picture_SchoolPeople", 7, 1),
	User_Picture_SchoolFeature("User_Picture_SchoolFeature", 8, 1);
	
	private String role;
	private int index;
	private int dbType; // 数据库中的类型字段

	private UploadType(String role, int index, int dbType) {
		this.role = role;
		this.index = index;
		this.dbType = dbType;
	}

	public String getRole() {
		return role;
	}

	public int getIndex() {
		return index;
	}

	public int getDbType() {
		return dbType;
	}

}
