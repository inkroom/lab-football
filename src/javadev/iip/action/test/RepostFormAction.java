package javadev.iip.action.test;

import java.util.Map;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.util.validata.V;

public class RepostFormAction extends BaseAction{

	//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
	//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
	//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
	//注意：数据名字请不要照此命名，请结合数据库或页面取得的数据名字命名。
	private V v=new V();
	private String data1="";
	private String data2="";
	private String data3="";
	private String data4="";
	private String data5="";
	private String data6="";
	private String data7="";
	private String data8="";
	private String data9="";
	private String data10="";
	private String data11="";
	private String data12="";
	private String data13="";
	Map<String,Object> oldData;
	String errorMessage;
	/**
	 * 功能描述：demo表单的提交
	 * 
	 * @author 朱春雨
	 * Create Date ：16-08-30
	 */
	//进入界面
	public String intoRepostFormAction()
	{
		return "intoRepostFormAction";
	}
	//获取旧数据并进入界面
		public String getAllOldData()
		{
			oldData=getServMgr().getRepostFormService().getAllOldData();
			return "intoRepostFormAction";
		}
	//提交表单
	public String uploadForm()
	{
		data1 = data1.trim();
		data2 = data2.trim();
		data3 = data3.trim();
		data4 = data4.trim();
		data5 = data5.trim();
		data6 = data6.trim();
		data7 = data7.trim();
		data8 = data8.trim();
		data9 = data9.trim();
		data10 = data10.trim();
		data11 = data11.trim();
		data12 = data12.trim();
		data13 = data13.trim();

	    if(v.checkLength(2, 5, data1)==false )
	    {
	    	errorMessage = "数据长度不符合要求";
	    }
		getServMgr().getRepostFormService().uploadForm(data1, data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,data13);

		return "uploadForm";
	}
	
	public Map<String, Object> getOldData() {
		return oldData;
	}
	public void setOldData(Map<String, Object> oldData) {
		this.oldData = oldData;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData4() {
		return data4;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public String getData5() {
		return data5;
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	public String getData6() {
		return data6;
	}
	public void setData6(String data6) {
		this.data6 = data6;
	}
	public String getData7() {
		return data7;
	}
	public void setData7(String data7) {
		this.data7 = data7;
	}
	public String getData8() {
		return data8;
	}
	public void setData8(String data8) {
		this.data8 = data8;
	}
	public String getData9() {
		return data9;
	}
	public void setData9(String data9) {
		this.data9 = data9;
	}
	public String getData10() {
		return data10;
	}
	public void setData10(String data10) {
		this.data10 = data10;
	}
	public String getData11() {
		return data11;
	}
	public void setData11(String data11) {
		this.data11 = data11;
	}
	public String getData12() {
		return data12;
	}
	public void setData12(String data12) {
		this.data12 = data12;
	}
	public String getData13() {
		return data13;
	}
	public void setData13(String data13) {
		this.data13 = data13;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
