package com.ljp.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ljp.bean.DataBean;
import com.ljp.bean.ExcelBean;
import com.ljp.bean.FormatBean;
import com.ljp.bean.RecordBean;
import com.ljp.bean.SheetBean;
import com.ljp.utils.Utils;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExcel {
	
	private WritableWorkbook write;
	private ExcelBean excel;
	private Map<String,Object> dataMap;
	
	public WriteExcel(Map<String,Object> dataMap,ExcelBean excel){
		this.excel = excel;
		this.dataMap = dataMap;
	}
	
	public WriteExcel(ExcelBean excel){
		this.excel = excel;
	}
	
	public WritableWorkbook excelBuild() throws BiffException, IOException{
		int buildType = excel.getConfigType();
		
		if(buildType == ExcelBean.CONFIG_COPY){
			write = Workbook.createWorkbook(new File(excel.getTargetFile()), 
						Workbook.getWorkbook(new File(getPath()+excel.getSourceFile())));
		}else if(buildType == ExcelBean.CONFIG_BUILD){
			write = Workbook.createWorkbook(new File(excel.getTargetFile()));
		}
		
		List<SheetBean> listSheet = excel.getListSheet();
		for(int i = 0;i<listSheet.size();i++){
			SheetBean sheetBean = listSheet.get(i);
			WritableSheet sheet = buildType==ExcelBean.CONFIG_BUILD?
					write.createSheet(sheetBean.getName(), sheetBean.getIndex()-1):write.getSheet(sheetBean.getIndex()-1);
			
			List<RecordBean> listRecord = sheetBean.getRecordBeans();
			for(int eq = 0;eq<listRecord.size();eq++){
				RecordBean recordBean = listRecord.get(eq);
			
				if(recordBean.getEndColumn() != 0||recordBean.getEndRow()!=0){
					try{
						sheet.mergeCells(recordBean.getStartColumn(), recordBean.getStartRow(),
								recordBean.getEndColumn(), recordBean.getEndRow());
					}catch(Exception e){
						throw new RuntimeException("�ϲ���Ԫ��"+recordBean.getStartColumn()+" "+recordBean.getEndRow()
								+" "+recordBean.getEndColumn()+" "+recordBean.getEndRow()+"�����˴���");
					}
				}
				
				DataBean dataBean = recordBean.getData();
				WritableCellFormat format = new WritableCellFormat();
			
				FormatBean formatBean = recordBean.getFormat()==null?sheetBean.getFormatBean():recordBean.getFormat();
				Utils.setFormat(format,formatBean);
				
				try{
					
					if(dataBean.isArray()){
						int increaseLength = dataBean.getIncreaseLength()>((String[])Utils.getData(dataMap,dataBean.getData())).length?
				 				((String[])Utils.getData(dataMap,dataBean.getData())).length:dataBean.getAmongLength();
						 switch(dataBean.getIncreaseType()){
						 	case DataBean.INCRESSTYPE_HORIZONTAL:
						 		for(int index = 0;index<increaseLength;index++){
						 			Utils.addCell(recordBean.getStartColumn()+index*dataBean.getIncreaseLength(), recordBean.getStartRow(), 
											dataBean, ((String[])Utils.getData(dataMap,dataBean.getData()))[index], sheet,format);
						 		}
						 		break;
						 	case DataBean.INCRESSTYPE_VERTICAL:
						 		for(int index = 0;index<increaseLength;index++){
						 			Utils.addCell(recordBean.getStartColumn(), recordBean.getStartRow()+index*dataBean.getIncreaseLength(), 
											dataBean, ((String[])Utils.getData(dataMap,dataBean.getData()))[index], sheet,format);
						 		}
						 		break;
						 }
					}else{
						Utils.addCell(recordBean.getStartColumn(), recordBean.getStartRow(), 
								dataBean, Utils.getData(dataMap,dataBean.getData()), sheet,format);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
		return write;
	}

	private String getPath() {
		String uri=this.getClass().getClassLoader().getResource("/").getPath();
		return uri.substring(0, uri.length()-16);
	}

	public void write(){
		try{
			write.write();
			write.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("д��excelʧ�ܣ�");
		}
	}

	public WritableWorkbook getWrite() {
		return write;
	}

	public ExcelBean getExcel() {
		return excel;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
}
