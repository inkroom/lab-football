package com.ljp.bean;

import org.dom4j.Element;

public class DataBean {
	
	private String data;
	private String dataType;
	private boolean isArray = false;
	private int amongLength;
	private int increaseLength;
	private int increaseType;
	private int imageWidth;
	private int imageHeight;
	
	public final static int INCRESSTYPE_HORIZONTAL=0;
	public final static int INCRESSTYPE_VERTICAL=1;
	
	public DataBean(Element dataElement){
		this.isArray = Boolean.parseBoolean(dataElement.attributeValue("isArray"));
		
		if(this.isArray){
			try{
				this.amongLength = Integer.parseInt(dataElement.attributeValue("amongLength"));
				this.increaseLength = Integer.parseInt(dataElement.attributeValue("increaseLength"));
			}catch(Exception e){throw new RuntimeException("data标签的amongLength和increaseLength属性必须为一个数字！");}
			
			String type = dataElement.attributeValue("increaseType");
			switch(type){
			case "horizontal":
				this.increaseType = 0;
				break;
			case "vertical":
				this.increaseType = 1;
				break;
			default:
				throw new RuntimeException("data标签的increaseType属性值为horizontal或者vertical！");
			}
		}
		
		this.data = dataElement.getText();
		this.dataType = dataElement.attributeValue("type");
		if("image".equals(this.dataType)){
			try{
				this.imageWidth = Integer.parseInt(dataElement.attributeValue("image-width"));
				this.imageHeight = Integer.parseInt(dataElement.attributeValue("image-height"));
			}catch(NumberFormatException e){
				throw new RuntimeException("data标签的image-xxx属性必须为一个整数！"+dataElement.attributeValue("image-width")+" "+dataElement.attributeValue("image-height"));
			}
		}
			
	}
	
	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
		
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	public int getIncreaseLength() {
		return increaseLength;
	}
	public void setIncreaseLength(int increaseLength) {
		this.increaseLength = increaseLength;
	}
	public int getIncreaseType() {
		return increaseType;
	}
	public void setIncreaseType(int increaseType) {
		this.increaseType = increaseType;
	}
	public int getAmongLength() {
		return amongLength;
	}
	public void setAmongLength(int amongLength) {
		this.amongLength = amongLength;
	}
}
