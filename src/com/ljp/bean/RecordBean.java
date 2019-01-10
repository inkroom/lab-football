package com.ljp.bean;

import org.dom4j.Element;

import com.ljp.utils.Utils;

public class RecordBean {
	
	private int startColumn;
	private int endColumn;
	private int startRow;
	private int endRow;
	private DataBean data;
	private FormatBean format;
	
	public RecordBean(int startColumn, int endColumn, int startRow, int endRow,
			DataBean data,FormatBean format) {
		super();
		this.startColumn = startColumn;
		this.endColumn = endColumn;
		this.startRow = startRow;
		this.endRow = endRow;
		this.data = data;
		this.format = format;
	}

	public RecordBean(Element recordElement) {
		String column = recordElement.attributeValue("column");
		if(Utils.isNullorEmpty(column))throw new RuntimeException("record标签必须要有column属性！");
		int[] columnReg = Utils.getColumnorRows(column);
		if(columnReg.length == 1){
			this.startColumn = columnReg[0];
		}else{
			
			this.startColumn = columnReg[0];
			this.endColumn = columnReg[1];
		}
		
		String rows = recordElement.attributeValue("rows");
		if(Utils.isNullorEmpty(rows))throw new RuntimeException("record标签必须要有rows属性！");
		int[] rowsReg = Utils.getColumnorRows(rows);
		if(rowsReg.length == 1){
			this.startRow = rowsReg[0];
		}else{
			this.startRow = rowsReg[0];
			this.endRow = rowsReg[1];
		}
		
		Element dataElement = recordElement.element("data");
		this.data = new DataBean(dataElement);
	}

	public int getStartColumn() {
		return startColumn;
	}
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}
	public int getEndColumn() {
		return endColumn;
	}
	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public DataBean getData() {
		return data;
	}
	public void setData(DataBean data) {
		this.data = data;
	}
	public FormatBean getFormat() {
		return format;
	}
	public void setFormat(FormatBean format) {
		this.format = format;
	}
	
}
