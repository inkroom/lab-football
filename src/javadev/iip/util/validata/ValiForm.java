package javadev.iip.util.validata;


import java.util.Map;

/**
 * 封装orders命令的信息
 */
public class ValiForm {
	
	private String name;
	private byte type[];
	private Map<String,Object> valiParam;
	private boolean isArray;
	private int start;
	private int currentPosition;
	private int end;
	private String errorMessage;
	
	public ValiForm() {}

	public ValiForm(String name, byte[] type,  Map<String,Object> valiParam,String errorMessage) {
		super();
		this.name = name;
		this.type = type;
		this.valiParam = valiParam;
		this.errorMessage = errorMessage;
	}
	
	public String getName(){
		return name;
	}
	public String getNameForArray(){
		if(this.isArray)return name+currentPosition;
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getType() {
		return type;
	}
	public void setType(byte[] type) {
		this.type = type;
	}
	public void setType(String[] type) {
		this.type = new byte[type.length];
		for(int i = 0;i<type.length;i++){
			this.type[i] = Byte.parseByte(type[i]);
		}
	}
	public  Map<String,Object> getValiParam() {
		return valiParam;
	}
	public void setValiParam( Map<String,Object> valiParam) {
		this.valiParam = valiParam;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	public void setArray(String isArray){
		if("true".equalsIgnoreCase(isArray))
			this.isArray = true;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public void setRange(String[] range){
		this.start = Integer.parseInt(range[0]);
		if("max".equalsIgnoreCase(range[1]))
			this.end = -1;
		else
			this.end = Integer.parseInt(range[1]);
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
	
}
