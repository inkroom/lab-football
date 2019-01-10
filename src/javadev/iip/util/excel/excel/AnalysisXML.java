package javadev.iip.util.excel.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javadev.iip.util.excel.bean.ExcelBean;
import javadev.iip.util.excel.bean.FormatBean;
import javadev.iip.util.excel.bean.RecordBean;
import javadev.iip.util.excel.bean.SheetBean;
import javadev.iip.util.excel.utils.Utils;

public class AnalysisXML {
	
	private InputStream inRead;
	
	public AnalysisXML(String fileLocation) throws FileNotFoundException{
		this.inRead = new FileInputStream(fileLocation);
	}
	
	public AnalysisXML(File file) throws FileNotFoundException{
		this.inRead = new FileInputStream(file);
	}
	
	public AnalysisXML(InputStream in){
		this.inRead = in;
	}
	
	public ExcelBean readXML() throws DocumentException{
		ExcelBean excel = new ExcelBean();
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(inRead);
		Element root = document.getRootElement();
		
		/*
		 * ���������ļ��Ľ���������config��ǩ��
		 */
		Element systemConfig = root.element("config");
		if(systemConfig == null)throw new RuntimeException("���õ�xml�ļ�����ӵ��config��ǩ��");
		readConfigLabel(systemConfig,excel);
		
		/*
		 * ���������ļ�format��ǩ�Ľ���
		 */
		List<Element> formatList = root.elements("format");
		readFormatLabel(formatList,excel);
		
		/*
		 * ���������ļ�sheet��ǩ�Ľ���
		 */
		List<Element> sheetList = root.elements("sheet");
		readSheetLabel(sheetList,excel);
		
		return excel;
	}
	
	//����format��ǩ
	private void readFormatLabel(List<Element> formatList,ExcelBean excel){
		HashMap<String,FormatBean> map = new HashMap<String,FormatBean>();
		
		for(int i=0;i<formatList.size();i++){
			
			Element formatElement = formatList.get(i);
			Iterator<Attribute> iterator = formatElement.attributeIterator();
			HashMap<String,String> paramsMap = new HashMap<String,String>();
			while(iterator.hasNext()){
				Attribute attribute = iterator.next();
				paramsMap.put(attribute.getName(),attribute.getText());
			}
			if(Utils.isNullorEmpty(paramsMap.get("name")))throw new RuntimeException("format����ʽ��ǩ����Ҫ��name���ԣ�");
			
			FormatBean formatBean = new FormatBean(paramsMap);
			map.put(paramsMap.get("name"), formatBean);
			
		}
		
		excel.setMapFormat(map);
	}
	
	//����record��ǩ
	private List<RecordBean> readRecordLabel(List<Element> recordElements,ExcelBean excel){
		List<RecordBean> list = new ArrayList<RecordBean>();
		
		for(int i=0;i<recordElements.size();i++){
			Element recordElement = recordElements.get(i);
			RecordBean recordBean = new RecordBean(recordElement);
			
			recordBean.setFormat(excel.getMapFormat().get(recordElement.attributeValue("format")));
			
			list.add(recordBean);
		}
		
		return list;
	}
	
	//����sheet��ǩ
	private void readSheetLabel(List<Element> sheetList,ExcelBean excel){
		
		List<SheetBean> list = new ArrayList<SheetBean>();
		
		for(int i = 0;i<sheetList.size();i++){
			Element sheetElement = sheetList.get(i);
			
			String sheetName = sheetElement.attributeValue("name");
			if(sheetName.equals("sheet"))sheetName = "sheet"+(i+1);
			
			int sheetIndex = Integer.parseInt(sheetElement.attributeValue("index"));
		
			List<Element> recordElements = sheetElement.elements("record");
			List<RecordBean> recordList = readRecordLabel(recordElements,excel);
					
			String formatName = sheetElement.attributeValue("format");
			FormatBean formatBean = null;
			if(!Utils.isNullorEmpty(formatName)){
				formatBean = excel.getMapFormat().get(formatName);
			}
			
			list.add(new SheetBean(sheetName, sheetIndex, recordList, formatBean));		
		}
		
		excel.setListSheet(list);
	}
	
	//����config��ǩ
	private void readConfigLabel(Element systemConfig,ExcelBean excel){
		String type = systemConfig.attributeValue("type");
		
		if(type.equals("copy"))excel.setConfigType(ExcelBean.CONFIG_COPY);
		
		if(excel.getConfigType()==ExcelBean.CONFIG_COPY){
			if(Utils.isNullorEmpty(systemConfig.elementText("sourceFile")))throw new RuntimeException("config��ǩ�����copy���ͱ���ӵ��sourceFile��ǩ������sourceFile��ǩ���ݲ���Ϊ�գ�");
			excel.setSourceFile(systemConfig.elementText("sourceFile"));
		}
		
		if(Utils.isNullorEmpty(systemConfig.elementText("targetFile")))throw new RuntimeException("config��ǩ����ӵ��targetFile��ǩ������targetFile��ǩ���ݲ���Ϊ�գ�");
		
		excel.setTargetFile(systemConfig.elementText("targetFile"));
	}
	
//	public static void main(String[] args){
//		try {
//			ExcelBean excel = new AnalysisXML("resource/excel.xml").readXML();
//			
//			//System.out.println(excel.getConfigType()+" "+excel.getSourceFile()+" "+excel.getTargetFile()+" "+excel.getMapFormat().get("1").getParamsMap());
//			
//			for(int i=0;i<excel.getListSheet().size();i++){
//				SheetBean sheet = excel.getListSheet().get(i);
//				System.out.println(sheet.getName()+" "+sheet.getIndex()+" "+sheet.getFormatBean());
//				List<RecordBean> list = sheet.getRecordBeans();
//				list.forEach(e->{
//					System.out.println();
//				});
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//	}
	
}
