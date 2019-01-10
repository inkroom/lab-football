package com.ljp.bean;

import java.util.List;
import java.util.Map;

public class ExcelBean {
	
	private int configType = 0;
	private String sourceFile;
	private String targetFile;
	private List<SheetBean> listSheet;
	protected Map<String,FormatBean> mapFormat;
	
		String uri=this.getClass().getClassLoader().getResource("/").getPath();
	    String s =  uri.substring(0, uri.length()-16);

	public static final int CONFIG_BUILD = 0;
	public static final int CONFIG_COPY = 1;
	
	public int getConfigType() {
		return configType;
	}
	public void setConfigType(int configType) {
		this.configType = configType;
	}
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public String getTargetFile() {
		if(targetFile==null||targetFile.trim().equals(""))
			targetFile = sourceFile;
		return targetFile;
	}
	public void setTargetFile(String targetFile) {
		this.targetFile = s+targetFile;
	}
	public List<SheetBean> getListSheet() {
		return listSheet;
	}
	public void setListSheet(List<SheetBean> list) {
		this.listSheet = list;
	}
	public Map<String, FormatBean> getMapFormat() {
		return mapFormat;
	}
	public void setMapFormat(Map<String, FormatBean> map) {
		this.mapFormat = map;
	}
	
}
