package javadev.iip.util;

public class fileUploadContext {
    private String [] type;
	
	private String src; 
	
	public String [] getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type.split(",");
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}

}
