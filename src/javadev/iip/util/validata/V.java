package javadev.iip.util.validata;



import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


/**
 * ����Ϊ����Ч���࣬�ṩ����У�麯�������Ҹ���ָ����������Ե���validateData()�������з��������У�顣
 * ����Ч�麯���Ĺ����ṩ�У�
 * 1.�ж�������ַ����Ƿ�Ϊ�ջ���ַ���
 * 2.�Ƚ��ַ����Ƿ����
 * 3.�ж������ַ��������鳤��
 * 4.�ж��������͵��Ƿ���ָ����������
 * 5.�ж��ַ����Ƿ��Ƿ���ָ������������
 * 6.�ж������Ƿ���ָ��������֮��
 * 7.����ƥ��
 * 8.�ж��Ƿ����ָ����������ʽ
 * 9.�ж����������Ƿ�����ͬһ���� 
 * 
 * orders������ܹ��������ǩ��
 * 1.N��ǩ��Ч����������keyֵ
 * д����N:ddd1/N
 * 2.p��ǩ��Ч�����
 * д����P:LMax=12|Equ=1234|Pat=\\d+|LMin=4/P
 * p��ǩ������ϸ���ܿ��Բο�����Ч���������ͽ��вο���
 * 3.M��ǩ��Ч��ʧ�ܵķ�����Ϣ
 * д����M���û���������6-12λ/M
 * 4.A��ǩ��ҪЧ��������Ƿ�Ϊ����(��д�����ԵĻ�Ĭ��Ϊfalse)
 * д����A��true/A
 * 5.R��ǩ�����Ϊ������Ч�����������(max��Ч�����������е�Ԫ��)
 * д����R��0,max/R    R:0,5/R
 * ����ӵ�е�������ǩN��ǩP��ǩM��ǩ
 * 
 * ʾ����orders�����"N:ddd1/N P:LMax=12|Equ=1234|Pat=\\d+|LMin=4/P M:�û���������6-12λ/M"
 * Ҳ�ɲο�example testVali
 * 
 * validateData�������Խ���һ�����һ���ַ������顣
 * 
 * @author ������
 * @see TestVali
 * @version 1.0
 */
public class V {
	
	/**
	 * У����������
	 * �ַ�����������󳤶����int�ͣ�
	 */
	public static final String LMAX = "LMax"; 
	/**
	 * У����������
	 * �ַ�����������С�������int�ͣ�
	 */
	public static final String LMIN = "LMin"; 
	/**
	 * У����������
	 * ����ȡֵ������ޣ�double�ͣ�
	 */
	public static final String NMAX = "NMax"; 
	/**
	 * У����������
	 * ����ȡֵ��С���ޣ�double�ͣ�
	 */
	public static final String NMIN = "NMin"; 
	/**
	 * У����������
	 * �ַ����Ա����String�ͣ�
	 */
	public static final String EQUALS = "Equ";
	/**
	 * У����������
	 * �����Ƿ�Ϊ������ޣ�
	 */
	public static final String EMPTY = "Emp";
	/**
	 * У����������
	 * �ַ����Ƿ����������ʽ��String�ͣ�
	 */
	public static final String PATTERN = "Pat";
	
	
	
	
	/**
	 * ����haveTime ���ж�ȡ��ȡʱ��
	 * @param haveTime
	 * @return
	 */
	public String getDateNow(boolean haveTime){
		SimpleDateFormat df = null;
		if (haveTime) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		}else {
			df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		}
		
		return df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
	}
	
	
	/**
	 * ����haveTime ���ж�ȡ��ȡʱ��
	 * @param haveTime
	 * @return
	 */
	public static String getLastYear(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");//�������ڸ�ʽ
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, -1);
	    Date last = c.getTime();
        return df.format(last);
	}
	
	
	public static String getLastYear(boolean haveTime){
		SimpleDateFormat df = null;
		if (haveTime) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		}else {
			df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		}
		
		
		System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.YEAR, -1);
	    Date last = c.getTime();
        System.out.println(df.format(last));
        return df.format(last);
	}
	

	
	
	/**
	 * @author ������
	 * @param param �����жϵĶ���
	 * �ж�������ַ����Ƿ�Ϊ������
	 */
	public static  boolean isNumeric(String str){
		if (str == null || str.equals("") ) {
			return false;  
		}
		for (int i = str.length();--i>=0;){
			if (str.charAt(0) == '0'){  
				if (str.length() == 1) {
					return true;
				}else {
					return false;
				}
			} 
			if (!Character.isDigit(str.charAt(i))){  
				return false;  
			}  
		}  
		return true;  
	} 
	
	//判断str里面有汉字 
	
	public static boolean isChinese(String str){
		if(str == null || str.isEmpty()){
			return true;
		}else if (str.length()>200) {
			return false;
	}
	return true;
	}
	
	//
	public static  boolean isMax(String str , int max){
		if (str == null || str.equals("") ) {
			return false;  
		}
		for (int i = str.length();--i>=0;){
			if (str.charAt(0) == '0'){  
				if (str.length() == 1) {
					
				}else {
					return false;
				}
			} 
			if (!Character.isDigit(str.charAt(i))){  
				return false;  
			}  
		}  
		if(Integer.parseInt(str)>max){
			return false;
		}
		return true;  
	} 
	public static boolean isDoubleOrNum(String str){
		Pattern pattern = Pattern.compile("^-?[0.0-0.9]\\d*$");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()) {
		    return false;
		}else if(Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$").matcher(str).find()){
		    return true;
		}else {
		    return false;
		}
	}
	public static boolean isDouble(String str){
		Pattern pattern = Pattern.compile("^-?[0-9]\\d*$");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()) {
		    return false;
		}else if(Pattern.compile("^-?([0-9]\\d*\\.\\d*|0\\.\\d*[0-9]\\d*|0?\\.0+|0)$").matcher(str).find()){
		    return true;
		}else {
		    return false;
		}
	
	}
	
	/**
	 * �ж�������ַ����Ƿ�Ϊ�ջ���ַ���
	 * @author ������
	 * @param param �����жϵĶ���
	 * @return �����жϵĶ����Ƿ�Ϊ��
	 */
	public boolean checkEmpty(Object param){
		if(param == null)
			return true;
		if("".equals(param.toString().trim()))
			return true;
		return false;
	}
	
	/**
	 * �Ƚ��ַ����Ƿ����
	 * @author ������
	 * @param str1 ��һ���Ƚϵ��ַ�����Ϊ�Ƚϵ�Դ
	 * @param str2 �ڶ����Ƚϵ��ַ�����Ϊ�Ƚϵ�Ŀ��
	 * @param isIgnore �Ƿ���Դ�Сд
	 * @return �����ַ����Ƿ����
	 */
	public boolean compareString(String str1,Object str2,boolean isIgnore){
		if(str1 == null||str2==null) return false;
		if(isIgnore){
			if(str1.equalsIgnoreCase(str2.toString()))
				return true;
		}
		else
			if(str1.equals(str2.toString()))return true;
		return false;
	}
	
	/**
	 * �ж������ַ��������鳤��
	 * @author ������
	 * @param min �����������Сֵ
	 * @param max ������������ֵ
	 * @param param Ҫ�����жϵĶ���
	 * @return Ҫ�жϵĶ����Ƿ��ڳ���������
	 */
	public boolean checkLength(int min,int max,Object param){
		if(param==null)return false;
		if(param.getClass().isArray()){
			if(Array.getLength(param)>=min&&Array.getLength(param)<=max)
				return true;
		}else{
			if(param.toString().length()>=min&&param.toString().length()<=max)
				return true;
		}
		return false;
	}
	
	/**
	 * �ж��������͵��Ƿ���ָ����������
	 * @author ������
	 * @param min �����������Сֵ
	 * @param max ������������ֵ
	 * @param value Ҫ�жϵ�����
	 * @return �жϵ������Ƿ��ڱ�������
	 */
	public boolean judgeSize(double min,double max,double value){
		if(value>min&&value<max)
			return true;
		return false;
	}
	/**
	 * �ж��������͵��Ƿ���ָ����������
	 * @author ������
	 * @param min �����������Сֵ
	 * @param max ������������ֵ
	 * @param value Ҫ�жϵ��ַ���������
	 * @return �жϵ������Ƿ��ڱ�������
	 */
	public boolean judgeSize(double min,double max,Object value){
		return judgeSize(min,max,Double.parseDouble(value.toString()));
	}
	
	/**
	 * �ж��ַ����Ƿ��Ƿ���ָ������������
	 * @author ������
	 * @param value Ҫ�жϵ�����
	 * @param rexge Ҫ���ϵ����ڸ�ʽ(�����ʽ�ۿ�jdk SimpleDateFormat��)
	 * @see SimpleDateFormat
	 * @return �����ַ����Ƿ����ָ����ʱ���ʽ
	 */
	public boolean isDateFormat(String value,String rexge){
		SimpleDateFormat dateFormat = new SimpleDateFormat(rexge);
		try {
			dateFormat.parse(value);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �ж������Ƿ���ָ��������֮��,������ж�min����max������null
	 * @author ������
	 * @param value ��Ҫ�жϵ�ʱ�� 
	 * @param min ����֮�����Сֵ
	 * @param max ����֮������ֵ
	 * @return ����ʱ���Ƿ��ڸ�ʱ��εĽ��boolean��ʽ
	 */
	public boolean isBetweenDate(Date value,Date min,Date max){
		if(min == null)
			if(value.getTime()<max.getTime())
				return true;
		if(max == null)
			if(value.getTime()>min.getTime())
				return true;
		if(value.getTime()>min.getTime()&&value.getTime()<max.getTime())
			return true;
		return false;
	}
	
	/**
	 * �ж������Ƿ���ָ��������֮��,������ж�min����max������null
	 * @author ������
	 * @param value ��Ҫ�жϵ�ʱ��
	 * @param min ����֮�����Сֵ
	 * @param max ����֮������ֵ
	 * @param regex �������ڸ�ʽ���������鿴jdk SimpleDateFormat��
	 * @see SimpleDateFormat
	 * @return ����ʱ���Ƿ��ڸ�ʱ��εĽ��boolean��ʽ
	 */
	public boolean isBetweenDateString(String value,String min,String max,String regex){
		SimpleDateFormat format = new SimpleDateFormat(regex);
		Date maxDate = null;
		Date minDate = null;
		try{
			Date valueDate = format.parse(value);
			
			if(min!=null)
				minDate = format.parse(min);
			if(max!=null)
				maxDate = format.parse(max);
				
			return isBetweenDate(valueDate, minDate, maxDate);
		}catch(ParseException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ����ƥ��
	 * @author ������
	 * @param value Ҫ��������ƥ����ַ���
	 * @param regex ������ʽ
	 * @param getResult ���Ϊtrue�򷵻�matcher�������Ϊnull�򷵻�ƥ���Ƿ�ɹ��Ľ��
	 * @return ����matcher�������ƥ���Ƿ�ɹ��Ľ��
	 */
	public Object isPattern(String value,String regex,boolean getResult){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		if(getResult)
			return matcher;
		else
			return matcher.matches();
	}
	
	/**
	 * �ж��Ƿ����ָ����������ʽ
	 * @author ������
	 * @param value Ҫ��������ƥ����ַ���
	 * @param regex ������ʽ
	 * @return �Ƿ�ƥ�䵽���ϵ��ַ���
	 */
	public boolean comparePattern(String value,String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	/**
	 * �ж����������Ƿ�����ͬһ���� 
	 * @author ������
	 * @param obj1 Ҫ�Աȵĵ�һ������
	 * @param obj2 Ҫ�Աȵĵڶ�������
	 * @return ���������Ƿ����
	 */
	public boolean compareObject(Object obj1,Object obj2){
		return obj1.getClass().isInstance(obj2);
	}
	
	/**
	 * ����У���ʽ
	 * @author ������
	 * @param orders ����ָ���Ĺ�����д��Ч����������뿴V��˵����
	 * @return ���ؽ�����������ʽ��list���棬ÿ�����������ValiForm bean��
	 * @throws ValiException �����뿴�׳��쳣����ϸ��Ϣ��
	 */
	private List<ValiForm> getValiForm(String[] orders){
		List<ValiForm> list = new ArrayList<ValiForm>();
		
		Pattern name = Pattern.compile("N:.*(?=/(N))");
		Pattern params = Pattern.compile("P:.*(?=/(P))");
		Pattern errorMessage = Pattern.compile("M:.*(?=/(M))");
		Pattern isArray = Pattern.compile("A:.*(?=/A)");
		Pattern range = Pattern.compile("R:.*(?=/R)");
		
		Matcher matcher = null;
		try{
			for(int index = 0;index < orders.length;index++){
				ValiForm valiForm = new ValiForm();
				
				matcher = name.matcher(orders[index]);
				matcher.find();
				valiForm.setName(matcher.group().split(":")[1]);
			
				matcher = params.matcher(orders[index]);
				matcher.find();
				valiForm.setValiParam(getParamsByString(matcher.group().split(":")[1]));
	
				matcher = errorMessage.matcher(orders[index]);
				matcher.find();
				valiForm.setErrorMessage(matcher.group().split(":")[1]);
				
				matcher = isArray.matcher(orders[index]);
				if(matcher.find())
					valiForm.setArray(matcher.group().split(":")[1]);
				
				matcher = range.matcher(orders[index]);
				if(matcher.find())
					valiForm.setRange(matcher.group().split(":")[1].split(","));
				
				list.add(valiForm);
			}
		}catch(IllegalStateException e){
			throw new ValiException("��д��Ч�������������ϸ��飡(��ǩ�Ƿ�պϣ��Ƿ��б����N|P|M��ǩ��)"+e.getMessage());
		}catch(NumberFormatException e){
			throw new ValiException("��д��Ч�������������ϸ��飡(���ָ�ʽ�Ƿ���ȷ��)"+e.getMessage());
		}
		return list;
	}
	
	/**
	 * ������ֵ�Բ�������װ��map�з���
	 * @author ������
	 * @param str ����Ķ��Ч���׼����ֵ����ʽ|��ֵ����ʽ��xx=xx|xx=xx|xx=xxֻ��Emp���Բ���д=��
	 * @return ���ؽ������Ч���׼ֵ��map���棬key��Ч�����ͣ�value��Ч���׼
	 * @throws ValiException �����뿴�׳��쳣����ϸ��Ϣ��
	 */
	private Map<String,Object> getParamsByString(String str){
		Map<String,Object> map = new HashMap<String,Object>();
		String[] params = str.split("\\|");
		for(int index = 0;index<params.length;index++){
			String[] entry = params[index].split("=");
			if(EMPTY.equals(entry[0])){
				map.put(entry[0], null);
				continue;
			}
			try{
				map.put(entry[0], entry[1]);
			}catch(ArrayIndexOutOfBoundsException e){
				throw new ValiException("����Խ�磬�뱣������У���������ȷ��ȷ������=�����߱�����ֵ!(Emp�������) \r\nԽ�������±꣺"+e.getMessage());
			}
		}
		return map;
	}
	
	/**
	 * ����У����������
	 * @author ������
	 * @param orders �������У������˺���������ṩ��Ч�����Ч��map�е�����
	 * @param map ����ҪЧ��ľ���ֵ
	 * @return �˺������ؼ���Ĵ�����map���б��棬map��key�����Ե����֣�value�����ṩ�Ĵ�����ϢM��xxx/M
	 * @throws ValiException �����뿴�׳��쳣����ϸ��Ϣ��
	 */
	public Map<String,String> validateData(String[] orders,Map map){
		Map<String,String> errorMessages = new HashMap<String,String>();
		
		try{
			List<ValiForm> list = getValiForm(orders);
			for(int i = 0;i<list.size();i++){
				ValiForm vali = list.get(i);
				Iterator<String> iterator = vali.getValiParam().keySet().iterator();
				
				while(iterator.hasNext()){
					String valiType = iterator.next();
					if(!map.containsKey(vali.getName()))continue;
					if(vali.isArray()){
						int length = vali.getEnd()==-1?Array.getLength(map.get(vali.getName())):vali.getEnd();
						for(int eq = vali.getStart(); eq< length;eq++){
							vali.setCurrentPosition(eq);
							validata(Array.get(map.get(vali.getName()),eq),errorMessages,vali,valiType);
						}
					}else
						validata(map.get(vali.getName()), errorMessages, vali, valiType);
				}		
			}
		}catch(NumberFormatException e){
			throw new ValiException("����ת���쳣���뱣֤ҪУ���"+LMAX+","+LMIN+","+NMAX+","+NMIN+"����ת������ȷ�����֣�\r\n �����ת���ַ���"+e.getMessage());
		}catch(NullPointerException e){
			e.printStackTrace();
			throw new ValiException("���������ʽ����ȷ�����������������!"+e.getMessage());
		}
		
		return errorMessages;
	}
	
	/**
	 * ����У����������
	 * @author ������
	 * @param orders �������У������˺���������ṩ��Ч�����Ч��map�е�����
	 * @param request �˴������request
	 * @param params ��ֵ����Ҫ����Ч����������ƣ������Ʊ�����request�е�ֵһ�£��������Ϊnull��Ч��request���е��������
	 * @return �˺������ؼ���Ĵ�����map���б��棬map��key�����Ե����֣�value�����ṩ�Ĵ�����ϢM��xxx/M
	 * @throws ValiException �����뿴�׳��쳣����ϸ��Ϣ��
	 */
	@SuppressWarnings(value = { "unchecked" })
	public Map<String,String> validateData(String[] orders,HttpServletRequest request,String[] params){
		if(params == null)
			return validateData(orders, request.getParameterMap());
		
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i = 0;i<params.length;i++)
			map.put(params[i], request.getParameterMap().get(params[i]));
		return validateData(orders, map);
	}
	
	/**
	 * ����valiFormУ��map�е�ʵ������
	 * @author ������
	 * @param value ҪУ�������
	 * @param errorMessages ������Ϣ����
	 * @param valiBean Ч������Ĵ���bean
	 * @param valiType Ч������(Ҳ�Ǳ���Ч���׼��keyֵ)
	 */
	private void validata(Object value,Map<String, String> errorMessages, ValiForm valiBean,String valiType) {
		switch(valiType){
			case LMAX:
			case LMIN:
				int lengthMax = Integer.MAX_VALUE;
				int lengthMin = Integer.MIN_VALUE;
				
				if(valiBean.getValiParam().containsKey(LMIN))
					lengthMin =  Integer.parseInt(valiBean.getValiParam().get(LMIN).toString());
				if(valiBean.getValiParam().containsKey(LMAX))
					lengthMax = Integer.parseInt(valiBean.getValiParam().get(LMAX).toString());
				
				if(!checkLength(lengthMin, lengthMax, value)){
					errorMessages.put(valiBean.getNameForArray(), valiBean.getErrorMessage());
				}
				break;
			case NMAX:
			case NMIN:
				Double NumberMin = Double.MIN_VALUE;
				Double NumberMax = Double.MAX_VALUE;
				
				if(valiBean.getValiParam().containsKey(NMIN))
					NumberMin =  Double.parseDouble(valiBean.getValiParam().get(NMIN).toString());
				if(valiBean.getValiParam().containsKey(NMAX))
					NumberMax = Double.parseDouble(valiBean.getValiParam().get(NMAX).toString());
				
				if(!judgeSize(NumberMin, NumberMax, value))
					errorMessages.put(valiBean.getNameForArray(), valiBean.getErrorMessage());
				
				break;
			case EQUALS:
				if(!compareString(value+"", valiBean.getValiParam().get(EQUALS), false))
					errorMessages.put(valiBean.getNameForArray(), valiBean.getErrorMessage());
				break;
			case EMPTY:
				if(checkEmpty(value))
					errorMessages.put(valiBean.getNameForArray(), valiBean.getErrorMessage());
				break;
			case PATTERN:
				if(!comparePattern(value.toString(), valiBean.getValiParam().get(PATTERN).toString()))
					errorMessages.put(valiBean.getNameForArray(), valiBean.getErrorMessage());
				break;
			default:
				break;
		}
	}
}
