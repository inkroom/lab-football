package javadev.iip.util.validata;



import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import javassist.expr.Instanceof;


/**
 * 此类为数据效验类，提供多种校验函数，并且根据指定的命令可以调用validateData()方法经行符合命令的校验。
 * 多种效验函数的功能提供有：
 * 1.判断输入的字符串是否为空或空字符串
 * 2.比较字符串是否相等
 * 3.判断输入字符串和数组长度
 * 4.判断数字类型的是否在指定的区间上
 * 5.判断字符串是否是符合指定的日期类型
 * 6.判断日期是否在指定的日期之间
 * 7.正则匹配
 * 8.判断是否符合指定的正则表达式
 * 9.判断两个对象是否属于同一个类 
 * 
 * orders（命令）总共用五个标签：
 * 1.N标签：效验对象的索引key值
 * 写法：N:ddd1/N
 * 2.p标签：效验规则
 * 写法：P:LMax=12|Equ=1234|Pat=\\d+|LMin=4/P
 * p标签更多详细功能可以参看类中效验属性类型进行参考。
 * 3.M标签：效验失败的返回信息
 * 写法：M：用户名必须在6-12位/M
 * 4.A标签：要效验的属性是否为数组(不写此属性的话默认为false)
 * 写法：A：true/A
 * 5.R标签：如果为数组则效验数组的区间(max则效验数组中所有的元素)
 * 写法：R：0,max/R    R:0,5/R
 * 必须拥有的三个标签N标签P标签M标签
 * 
 * 示例：orders（命令）"N:ddd1/N P:LMax=12|Equ=1234|Pat=\\d+|LMin=4/P M:用户名必须在6-12位/M"
 * 也可参考example testVali
 * 
 * validateData方法可以接受一组命令即一个字符串数组。
 * 
 * @author 刘佳鹏
 * @see TestVali
 * @version 1.0
 */
public class V {
	
	/**
	 * 校验属性类型
	 * 字符串或数组最大长度命令（int型）
	 */
	public static final String LMAX = "LMax"; 
	/**
	 * 校验属性类型
	 * 字符串或数组最小长度命令（int型）
	 */
	public static final String LMIN = "LMin"; 
	/**
	 * 校验属性类型
	 * 数字取值最大上限（double型）
	 */
	public static final String NMAX = "NMax"; 
	/**
	 * 校验属性类型
	 * 数字取值最小上限（double型）
	 */
	public static final String NMIN = "NMin"; 
	/**
	 * 校验属性类型
	 * 字符串对比命令（String型）
	 */
	public static final String EQUALS = "Equ";
	/**
	 * 校验属性类型
	 * 对象是否为空命令（无）
	 */
	public static final String EMPTY = "Emp";
	/**
	 * 校验属性类型
	 * 字符串是否符合正则表达式（String型）
	 */
	public static final String PATTERN = "Pat"; 
	
	/**
	 * 判断输入的字符串是否为空或空字符串
	 * @author 刘佳鹏
	 * @param param 进行判断的对象
	 * @return 返回判断的对象是否为空
	 */
	public boolean checkEmpty(Object param){
		if(param == null)
			return true;
		if("".equals(param.toString().trim()))
			return true;
		return false;
	}
	
	/**
	 * 比较字符串是否相等
	 * @author 刘佳鹏
	 * @param str1 第一个比较的字符串作为比较的源
	 * @param str2 第二个比较的字符串作为比较的目标
	 * @param isIgnore 是否忽略大小写
	 * @return 两个字符串是否相等
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
	 * 判断是否为数字
	 * @author 彭波
	 * @param str 要进行判断的对象
	 * @return 要判断是否为正实数字(包括小数)
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("-?[0-9]*.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}	
		return true;
	}
	/**
	 * 判断是否为数字
	 * @author 彭波
	 * @param str 要进行判断的对象
	 * @return 要判断是否为正实数字(包括小数)
	 */
	public static boolean isfile(String str){
		Pattern pattern = Pattern.compile("[a-z0-9A-Z\u4e00-\u9fa5\\-_]+$");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}	
		return true;
	}
	/**
	 * 判断是否为8位非负整数
	 *  * @author 朱春雨
	 * @param str 要进行判断的对象
	 * @return 要判断是否为8位非负整数
	 */
	public static boolean isZCPNumbers(String str)
	{
		Pattern pattern = Pattern.compile("^(([1-9][0-9]{0,7})|([0-0]{1,1}))$");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}	
		return true;
	}
	/**
	 * 整数最大为8位非负数，小数最多精确到4位
	 *  * @author 朱春雨
	 * @param str 要进行判断的对象
	 * @return 整数最大为8位非负数，小数最多精确到4位
	 */
	public static boolean isZCNumbers(String str)
	{
		Pattern pattern = Pattern.compile("^(([1-9][0-9]{0,7}\\.[0-9]{1,4})|([0-9]{1,1}\\.[0-9]{1,4})|([1-9][0-9]{0,7})|([0-0]{1,1}))$");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}	
		return true;
	}
	/**
	 * 判断是否为数字
	 * @author 彭波
	 * @param str 要进行判断的对象
	 * @return 要判断是否为正实数字(有小数的情况下保留小数点后4位)
	 */
	public static boolean isZNumbers(String str){
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,4})?$");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}	
		return true;
	}
	/**
	 * 判断输入字符串和数组长度
	 * @author 刘佳鹏
	 * @param min 长度区间的最小值
	 * @param max 长度区间的最大值
	 * @param param 要进行判断的对象
	 * @return 要判断的对象是否在长度区间内
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
	 * 判断数字类型的是否在指定的区间上
	 * @author 刘佳鹏
	 * @param min 数字区间的最小值
	 * @param max 数字区间的最大值
	 * @param value 要判断的数字
	 * @return 判断的数字是否在本区间上
	 */
	public boolean judgeSize(double min,double max,double value){
		if(value>min&&value<max)
			return true;
		return false;
	}
	/**
	 * 判断数字类型的是否在指定的区间上
	 * @author 刘佳鹏
	 * @param min 数字区间的最小值
	 * @param max 数字区间的最大值
	 * @param value 要判断的字符类型数字
	 * @return 判断的数字是否在本区间上
	 */
	public boolean judgeSize(double min,double max,Object value){
		return judgeSize(min,max,Double.parseDouble(value.toString()));
	}
	
	/**
	 * 判断字符串是否是符合指定的日期类型
	 * @author 刘佳鹏
	 * @param value 要判断的类型
	 * @param rexge 要符合的日期格式(具体格式观看jdk SimpleDateFormat类)
	 * @see SimpleDateFormat
	 * @return 返回字符串是否符合指定的时间格式
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
	 * 判断是否为数字
	 * @author 王树浩
	 * @param value
	 * @return
	 */
	
	 public static boolean checkNumber(String value){  
	        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
	        return value.matches(regex);  
	    }  
	/**
	 * 判断字符是否是中文或者英文小于7个大于2个
	 * @param str
	 * @return
	 */
	public boolean isChinese(String str){
		Pattern pattern = Pattern.compile("([\u4e00-\u9fa5]{2,7})|([A-Za-z0-9 ]{4,14})");
		Matcher isChinese=pattern.matcher(str);
		if (isChinese.matches()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 判断日期是否在指定的日期之间,如果不判断min或者max请输入null
	 * @author 刘佳鹏
	 * @param value 你要判断的时间 
	 * @param min 日期之间的最小值
	 * @param max 日期之间的最大值
	 * @return 返回时间是否在该时间段的结果boolean形式
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
	 * 判断日期是否在指定的日期之间,如果不判断min或者max请输入null
	 * @author 刘佳鹏
	 * @param value 你要判断的时间
	 * @param min 日期之间的最小值
	 * @param max 日期之间的最大值
	 * @param regex 是你日期格式的正则（详情看jdk SimpleDateFormat）
	 * @see SimpleDateFormat
	 * @return 返回时间是否在该时间段的结果boolean形式
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
	 * 正则匹配
	 * @author 刘佳鹏
	 * @param value 要进行正则匹配的字符串
	 * @param regex 正则表达式
	 * @param getResult 如果为true则返回matcher对象如何为null则返回匹配是否成功的结果
	 * @return 返回matcher对象或者匹配是否成功的结果
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
	 * 判断是否符合指定的正则表达式
	 * @author 刘佳鹏
	 * @param value 要进行正则匹配的字符串
	 * @param regex 正则表达式
	 * @return 是否匹配到符合的字符串
	 */
	public boolean comparePattern(String value,String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		
		return matcher.matches();
	}
	
	/**
	 * 判断两个对象是否属于同一个类 
	 * @author 刘佳鹏
	 * @param obj1 要对比的第一个对象
	 * @param obj2 要对比的第二个对象
	 * @return 两个对象是否相等
	 */
	public boolean compareObject(Object obj1,Object obj2){
		return obj1.getClass().isInstance(obj2);
	}
	
	/**
	 * 解析校验格式
	 * @author 刘佳鹏
	 * @param orders 根据指定的规则填写的效验命令（详情请看V类说明）
	 * @return 返回解析后的命令格式用list保存，每条命令都保存在ValiForm bean中
	 * @throws ValiException 具体请看抛出异常的详细信息！
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
			throw new ValiException("填写的效验命令错误，请仔细检查！(标签是否闭合？是否有必须的N|P|M标签？)"+e.getMessage());
		}catch(NumberFormatException e){
			throw new ValiException("填写的效验命令错误，请仔细检查！(数字格式是否正确？)"+e.getMessage());
		}
		return list;
	}
	
	/**
	 * 解析键值对参数并封装到map中返回
	 * @author 刘佳鹏
	 * @param str 传入的多个效验标准（键值对形式|键值对形式）xx=xx|xx=xx|xx=xx只有Emp可以不用写=号
	 * @return 返回解析后的效验标准值用map保存，key是效验类型，value是效验标准
	 * @throws ValiException 具体请看抛出异常的详细信息！
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
				throw new ValiException("数组越界，请保持输入校验命令的正确，确保参数=号两边必须有值!(Emp命令除外) \r\n越界数组下标："+e.getMessage());
			}
		}
		return map;
	}
	
	/**
	 * 整体校验框架主函数
	 * @author 刘佳鹏
	 * @param orders 是输入的校验命令，此函数会根据提供的效验命令，效验map中的数据
	 * @param map 是你要效验的具体值
	 * @return 此函数返回检查后的错误集用map经行保存，map的key是属性的名字，value是你提供的错误信息M：xxx/M
	 * @throws ValiException 具体请看抛出异常的详细信息！
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
			throw new ValiException("数字转换异常，请保证要校验的"+LMAX+","+LMIN+","+NMAX+","+NMIN+"可以转换成正确的数字！\r\n 出错的转换字符："+e.getMessage());
		}catch(NullPointerException e){
			e.printStackTrace();
			throw new ValiException("输入的命令式不正确，请调整后重新输入!"+e.getMessage());
		}
		
		return errorMessages;
	}
	
	/**
	 * 整体校验框架主函数
	 * @author 刘佳鹏
	 * @param orders 是输入的校验命令，此函数会根据提供的效验命令，效验map中的数据
	 * @param request 此次请求的request
	 * @param params 此值是你要经行效验的属性名称，此名称必须与request中的值一致，如果此项为null则效验request所有的请求参数
	 * @return 此函数返回检查后的错误集用map经行保存，map的key是属性的名字，value是你提供的错误信息M：xxx/M
	 * @throws ValiException 具体请看抛出异常的详细信息！
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
	 * 根据valiForm校验map中的实际属性
	 * @author 刘佳鹏
	 * @param value 要校验的属性
	 * @param errorMessages 错误信息回显
	 * @param valiBean 效验命令的储存bean
	 * @param valiType 效验类型(也是本次效验标准的key值)
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
