package javadev.core.common.fileupload;

/**
 * 功能描述：定义上传文件的类型
 * 
 * @author RenQiang
 * @version 1.0 Create Date: 2016-04-18
 */
public enum UploadType {
	Player_Photo("Player_Wonderful_Photo", 1, 1), // 运动员精彩图片
	Player_ID_Photo("Player_ID_Photo", 2, 1), // 运动员证件照
	Player_Video("Player_Video", 3, 2), // 运动员精彩视频
	Coach_Photo("Coach_Wonderful_Photo", 4, 1), // 教练员精彩图片
	Coach_Video("Coach_Video", 5, 2), // 教练员精彩视频
	Coach_ID_Photo("Coach_ID_Photo", 6, 1), // 教练员证件照
	Game_Photo("Game_Wonderful_Photo", 7, 1), // 比赛现场图片
	Game_Video("Game_Video", 8, 2), // 比赛现场视频
	Team_flag("Team_Photo", 9, 1), // 队旗
	Team_Badge("Team_Photo", 10, 1); // 队徽

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
