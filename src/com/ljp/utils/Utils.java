package com.ljp.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.biff.FontRecord;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Font;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.ljp.bean.DataBean;
import com.ljp.bean.FormatBean;

public class Utils {
	
	private static Map<String,Colour> colorMap = new HashMap<String,Colour>();
	private static Map<String,Alignment> alignmentMap = new HashMap<String,Alignment>();
	private static Map<String,Border> borderMap = new HashMap<String,Border>();
	private static Map<String,BorderLineStyle> borderLineMap = new HashMap<String,BorderLineStyle>();
	
	static{
		colorMap = createColorMap();
		alignmentMap = createAlignmentMap();
		borderMap = createBorderMap();
		borderLineMap = createBorderLineMap();
	}
	
	/**
	 * 将颜色放入map值保存key颜色名称、value color类
	 * @return
	 */
	private static Map<String,Colour> createColorMap(){
		Map<String,Colour> map = new HashMap<String,Colour>();
		for(int i = 0;i<Colour.getAllColours().length;i++){
			map.put(Colour.getAllColours()[i].getDescription(), Colour.getAllColours()[i]);
		}
		return map;
	}
	
	private static Map<String,BorderLineStyle> createBorderLineMap() {
		Map<String,BorderLineStyle> map = new HashMap<String,BorderLineStyle>();
		
		map.put("medium", BorderLineStyle.MEDIUM);
		map.put("thick", BorderLineStyle.THICK);
		map.put("thin", BorderLineStyle.THIN);
		
		//大写
		Map<String,BorderLineStyle> map2 = new HashMap<String,BorderLineStyle>();
		for(Entry<String,BorderLineStyle> en:map.entrySet()){
			map2.put(en.getKey().toUpperCase(), en.getValue());
		}
		map.putAll(map2);
		return map;
	}

	private static Map<String, Border> createBorderMap() {
		Map<String,Border> map = new HashMap<String,Border>();
		
		map.put("all", Border.ALL);
		map.put("bottom", Border.BOTTOM);
		map.put("left", Border.LEFT);
		map.put("right", Border.RIGHT);
		map.put("top", Border.TOP);
		map.put("none", Border.NONE);
		
		//大写
		Map<String,Border> map2 = new HashMap<String,Border>();
		for(Entry<String,Border> en:map.entrySet()){
			map2.put(en.getKey().toUpperCase(), en.getValue());
		}
		map.putAll(map2);
		return map;
	}

	private static Map<String,Alignment> createAlignmentMap(){
		Map<String,Alignment> map = new HashMap<String,Alignment>();
		//小写
		map.put("center", Alignment.CENTRE);
		map.put("left", Alignment.LEFT);
		map.put("right", Alignment.RIGHT);
		map.put("fill", Alignment.FILL);
		map.put("general", Alignment.GENERAL);
		map.put("justify", Alignment.JUSTIFY);
		
		//大写
		Map<String,Alignment> map2 = new HashMap<String,Alignment>();
		for(Entry<String,Alignment> en:map.entrySet()){
			map2.put(en.getKey().toUpperCase(), en.getValue());
		}
		map.putAll(map2);
		return map;
	}
	
	public static boolean isNullorEmpty(String str){
		return (str==null||str.trim().equals(""));
	}
	
	public static int[] getColumnorRows(String str){
		Pattern pattern = Pattern.compile("(\\d+,\\d+)");
		Matcher matcher = pattern.matcher(str);
		
		if(!matcher.find()){
			try{
				return new int[]{Integer.parseInt(str)};
			}catch(Exception e){
				throw new RuntimeException("column或者rows的格式不正确，标准格式为数字或（数字，数字）");
			}
		}
		
		String[] params = matcher.group().split(",");

		return new int[]{Integer.parseInt(params[0]),Integer.parseInt(params[1])};
	}

	public static void addCell(int x,int y,DataBean dataBean,Object data,WritableSheet sheet,WritableCellFormat  format) 
			throws RowsExceededException, WriteException, MalformedURLException{
		switch(dataBean.getDataType()){
		default:
		case "label":
		case "number":
		case "blank":
			Label label = new Label(x, y, data.toString());
			label.setCellFormat(format);
			sheet.addCell(label);
			break;
		case "link":
			sheet.addHyperlink(new WritableHyperlink(x, y, new URL(data.toString())));
			break;
		case "image":
			sheet.addImage(new WritableImage(x, y, dataBean.getImageWidth(), dataBean.getImageHeight(), new File(data.toString())));
			break;
		}
	}

	public static Object getData(Map<String, Object> dataMap, String data) {
		if(isNullorEmpty(data))return "";
		
		if(data.charAt(0) == '"'&&data.charAt(data.length()-1) == '"')return data.replace("\"","");
		
		return dataMap.get(data);
	}

	public static void setFormat(WritableCellFormat format,
			FormatBean formatBean){
		if(formatBean == null)return;
		Iterator<Entry<String,String>> iterator = formatBean.getParamsMap().entrySet().iterator();
		
		WritableFont font = new WritableFont(Font.createFont("宋体"));
		Border border = Border.ALL;
		BorderLineStyle borderStyle = BorderLineStyle.MEDIUM;
		Colour borderColour = Colour.BLACK;
		
		while(iterator.hasNext()){
			Entry<String,String> entry = iterator.next();
			try{
				switch(entry.getKey()){
				case "border":
					if(borderMap.get(entry.getValue())==null)
						throw new RuntimeException("请确定边框的方位正确！"+entry.getValue());
					border = borderMap.get(entry.getValue());
					format.setBorder(border, borderStyle, borderColour);
					break;
				case "border-style":
					if(borderLineMap.get(entry.getValue())==null)
						throw new RuntimeException("请输入正确的边框样式！"+entry.getValue());
					borderStyle = borderLineMap.get(entry.getValue());
					format.setBorder(border, borderStyle, borderColour);
					break;
				case "border-color":
					if(colorMap.get(entry.getValue().toLowerCase())==null)
						throw new RuntimeException("没有找到颜色"+entry.getValue()+"，请检查颜色是否正确！");
					borderColour = colorMap.get(entry.getValue().toLowerCase());
					format.setBorder(border, borderStyle, borderColour);
					break;
				case "background":
					if(colorMap.get(entry.getValue().toLowerCase())==null)
						throw new RuntimeException("没有找到颜色"+entry.getValue()+"，请检查颜色是否正确！");
					format.setBackground(colorMap.get(entry.getValue()));
					break;
				case "alignment":
					if(alignmentMap.get(entry.getValue())==null)
						throw new RuntimeException("没有文字格式："+entry.getKey()+",请检查输入是否正确！");
					format.setAlignment(alignmentMap.get(entry.getValue()));
					break;
				case "font-size":
					try{
						font.setPointSize(Integer.parseInt(entry.getValue()));
					}catch(NumberFormatException e){
						throw new RuntimeException("文字大小："+entry.getKey()+",请检查输入是否正确！（文字大小必须是整数）");
					}
					format.setFont(font);
					break;
				case "color":
					font.setColour(colorMap.get(entry.getValue().toLowerCase()));
					format.setFont(font);
					break;
				}
			}catch(WriteException e){
				e.printStackTrace();
				throw new RuntimeException("format标签属性配置有误，请检查！");
			}
		}
	}
}
