package javadev.iip.util.excel.bean;

import java.util.Map;

public class FormatBean {
	
	private String name;
	private Map<String,String> paramsMap;
	
	public FormatBean(Map<String, String> paramsMap) {
		super();
		this.name = paramsMap.get("name");
		this.paramsMap = paramsMap;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getParamsMap() {
		return paramsMap;
	}
	public void setParamsMap(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}
}
