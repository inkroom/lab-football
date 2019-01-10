package javadev.iip.util.excel.bean;

import java.util.List;

public class SheetBean {
	
	private String name;
	private int index;
	private List<RecordBean> recordBeans;
	private FormatBean formatBean;
	
	public SheetBean() {
		super();
	}

	public SheetBean(String name, int index, List<RecordBean> recordBeans,
			FormatBean formatBean) {
		super();
		this.name = name;
		this.index = index;
		this.recordBeans = recordBeans;
		this.formatBean = formatBean;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<RecordBean> getRecordBeans() {
		return recordBeans;
	}
	public void setRecordBeans(List<RecordBean> recordBeans) {
		this.recordBeans = recordBeans;
	}
	public FormatBean getFormatBean() {
		return formatBean;
	}
	public void setFormatBean(FormatBean formatBean) {
		this.formatBean = formatBean;
	}
	
}
