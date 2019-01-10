package javadev.iip.action.fiveplan;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.util.RegistDateUtil;
import javadev.iip.util.validata.V;

public class RepostFromActionTwoAction extends BaseAction{
	/**
	 * 功能描述：第二页的表单的提交
	 * 
	 * @author 李月
	 * @version:1.0
	 * Create Date ：16-08-30
	 */
	
	private Date fp_year ;//这个表示当前系统时间
	private String a_id;//这个是用户的唯一标示Id
	private String o_id;//组织编号
	
	private V v = new V();//验证工具
	private String isAchieveMusicRoom ;//音乐专用教室是否达标
	private String spendForAddMusicRoom;//新增教室投入金额
	private String isAchieveMusicEqui;//音乐器材设备是否达标
	private String spendForMusicEquiment;//新增音乐器材投入金额；
	private String isAchieveDrawClass;//美术专用教室是否达标
	private String spendForDrawRoom;//美术专用教室投入金额(万元)
	private String isAchieveDrawEqui;//美术器材设备是否达标
	private String spendForDrawEqui;//新增美术器材投入金额
	private Map<String,Object> oldData;//旧数据
	private Map<String,Object> nowData;//今年的数据
	private String nowYear;

	private String errorInfo;//跳转错误
	
	private String newToken2;

	
	/**
	 * 功能描述 :进入表单页面
	 * @return String 
	 * @author 李月
	 */
	//进入表单页面
	public String intoRepostForm()
	{
		Map<String, Object> Page1=getServMgr().getPageOneService().getAllOldData(o_id, (getFpYear()+1)+"",1);
		if(Page1!=null && Page1.get("is_achieve_play_ground")==null)
	    {
	    	errorInfo="请将信息填写完整!";
	    	newToken2 = (String) getRequest().getSession().getAttribute("token");
	    	return "topage1";
	    }
		if(getFpYear()!=0){
			nowData = getServMgr().getRespostFromActionTwoService().getAllOldDatas(getOID(),(getFpYear()+1)+"","1");
			initNowData();//
			oldData=getServMgr().getRespostFromActionTwoService().getAllOldData(getOID(),getFpYear()+"","2");
		}
		return "intoRepostFormAction";
		
	}
	
	/**
	 * 功能描述 :进入上一个页面
	 * @return
	 * @author 李月
	 */
	public String intoPreForm(){
		newToken2 = (String) getRequest().getSession().getAttribute("token");
		return "pre";
	}
	/**
	 * 功能描述:提交表单 
	 * @return String 
	 * @author 李月
	 */
	//提交表单
	public String uploadForm(){	
		log.info(newToken2+"0000000000000000000newToken1");
		
		oldData=getServMgr().getRespostFromActionTwoService().getAllOldData(getOID(),getFpYear()+"","2");
	
		
		log.info("spendForAddMusicRoom"+spendForAddMusicRoom);
		log.info("spendForMusicEquiment"+spendForMusicEquiment);
		log.info("spendForDrawRoom"+spendForDrawRoom);
		log.info("spendForDrawEqui"+spendForDrawEqui);
		if(!v.checkEmpty(isAchieveMusicRoom) 
				&&!v.checkEmpty(spendForAddMusicRoom)&&!v.checkEmpty(isAchieveMusicEqui)
				&&!v.checkEmpty(spendForMusicEquiment)  &&!v.checkEmpty(isAchieveDrawClass)
				&&!v.checkEmpty(spendForDrawRoom)&&!v.checkEmpty(isAchieveDrawEqui)
				&&!v.checkEmpty(spendForDrawEqui)){
				isEmpty();
				
				log.info("哈哈哈哈或或或或或或或或或或或或");
		
			if(checkisNewAchieveMusicRoom(isAchieveMusicRoom)
					&&checkSpendMeony(spendForAddMusicRoom,"新增音乐专用教室投入金额(万元)")
					
					&&checkisNewAchieveMusicRoom(isAchieveMusicEqui)
					&&checkSpendMeony(spendForMusicEquiment,"新增音乐器材设备投入金额(万元)")
					
					&&checkisNewAchieveMusicRoom(isAchieveDrawClass)
					&&checkSpendMeony(spendForDrawRoom,"新增美术专用教室投入金额(万元)")
				
					&&checkisNewAchieveMusicRoom(isAchieveDrawEqui)
					&&checkSpendMeony(spendForDrawEqui,"新增美术设备投入金额(万元)")
					){
				
				log.info("----------------验证过啦------------------------");
				nowData = getServMgr().getRespostFromActionTwoService().getAllOldDatas(getOID(),(getFpYear()+1)+"","1");
				if(nowData!=null && nowData.size()!=0){
				
				if(isAchieve(nowData.get("isAchieveMusicRoom").toString()).equals(isAchieveMusicRoom) && 
						nowData.get("spendForAddMusicRoom").toString().equals(spendForAddMusicRoom) &&
				   isAchieve(nowData.get("isAchieveMusicEqui").toString()).equals(isAchieveMusicEqui) &&
						nowData.get("spendForMusicEquiment").toString().equals(spendForMusicEquiment)&&
				   isAchieve(nowData.get("isAchieveDrawClass").toString()).equals(isAchieveDrawClass)&&
						nowData.get("spendForDrawRoom").toString().equals(spendForDrawRoom)&&
				   isAchieve(nowData.get("isAchieveDrawEqui").toString()).equals(isAchieveDrawEqui)&&
						nowData.get("spendForDrawEqui").toString().equals(spendForDrawEqui)
						){
					
					log.info("我们相同啦啦啦啦啦");
					return "uploadForm";
				}}
				
				//返回一个map以点上一页返回
				boolean flag;
			
				flag = getServMgr().getRespostFromActionTwoService().uploadForm(isAchieveMusicRoom, spendForAddMusicRoom, isAchieveMusicEqui, 
						spendForMusicEquiment,  isAchieveDrawClass,
						spendForDrawRoom, isAchieveDrawEqui, spendForDrawEqui,getAID());
				
				if(flag){
//					ActionContext actionContext = ActionContext.getContext();
//					Map<String, Object> prePageData = actionContext.getSession();
//					prePageData.put("isAchieveMusicRoom", isAchieveMusicRoom);
//					prePageData.put("spendForAddMusicRoom", spendForAddMusicRoom);
//					prePageData.put("isAchieveMusicEqui", isAchieveMusicEqui);
//					prePageData.put("spendForMusicEquiment", spendForMusicEquiment);
//					prePageData.put("isAchieveDrawClass", isAchieveDrawClass);
//					prePageData.put("spendForDrawRoom", spendForDrawRoom);
//					prePageData.put("isAchieveDrawEqui", isAchieveDrawEqui);
//					prePageData.put("spendForDrawEqui", spendForDrawEqui);
					log.info("%^%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+newToken2);
					return "uploadForm";
				}else{
					errorInfo="数据库插入失败!";
					return "error";
				}
			
			}else{
				return "error";
			}
			
		}
		return "error";
	}

	

	/**
	 * 功能描述 :验证是否学校是否达标填写
	 *  @author 李月
	 * 
	 * @return
	 */
	
	private boolean checkisNewAchieveMusicRoom(String isStandard){
		if(isStandard.equals("1") || isStandard.equals("2")||isStandard.equals("3")){
			return true;
		}else{
			errorInfo="选择不能为空!";
			System.out.println("-------"+errorInfo+"---------");
			return false;
		}
	}
	/**
	 * 功能描述 :验证验证投入金额填写是否正确
	 * @return
	 * @author 李月
	 */
	
	private boolean checkSpendMeony(String spendMoney,String name){
		String   regex1 = "^[1-9][0-9]{0,7}([.][0-9]{1,4})$";//整数是七位小数后面只能有一位
		String  regex2 = "^[1-9][0-9]{1,7}$";//整数是8位，没有小数,第一位不可以为0
		String  regex3 = "^[0-9]([.][1-9]{1,4})$";//只有两位，个位为0-9小数后面只有一位
		String regex4 = "^[0-9]{1,1}$";//匹配一位的整数
		log.info("正则匹配:"+Pattern.matches(regex1, spendMoney)+"  "+Pattern.matches(regex2, spendMoney)
		+"  "+spendMoney.equals("0")+"  "+Pattern.matches(regex3, spendMoney)
		);
		if(Pattern.matches(regex1, spendMoney) || Pattern.matches(regex2, spendMoney) || spendMoney.equals("0") 
				|| Pattern.matches(regex3, spendMoney)|| Pattern.matches(regex4, spendMoney)){
			return true;
		}else{
			errorInfo = name+"整数最大为8位非负数，小数最多精确到4位";
			System.out.println("-------"+errorInfo+"---------");
			return false;
		}
	}
	/**
	 * 功能描述 :去掉字符串上面的空格
	 * @return
	 * @author 李月
	 */
	private void isEmpty(){
		isAchieveMusicRoom = isAchieveMusicRoom.trim();
		spendForAddMusicRoom = spendForAddMusicRoom.trim();
		isAchieveMusicEqui = isAchieveMusicEqui.trim();
		spendForMusicEquiment = spendForMusicEquiment.trim();
		isAchieveDrawClass = isAchieveDrawClass.trim();
		spendForDrawRoom = spendForDrawRoom.trim();
		isAchieveDrawEqui = isAchieveDrawEqui.trim();
		spendForDrawEqui = spendForDrawEqui.trim();
	}
	/**
	 * 功能描述 :得到o_id
	 * @return String
	 * @author 李月
	 */
	private String getOID(){	
		HashMap<String, Object> data = (HashMap<String, Object>)(getSession().get(Constants.LOGIN_USER));
		if(data!=null){			
			log.info("O_ID为——————————————————————"+data.get("O_ID").toString());
			return data.get("O_ID").toString();
		}else{
			return "";
		}
		
	}
	/**
	 * 功能描述 :得到a_id
	 * @return String
	 * @author 李月
	 */
	private String getAID(){
	
		String a_id =  ((Map<String,Object>) getSession().get(Constants.LOGIN_USER)).get("A_ID").toString();
		if(!v.checkEmpty(a_id)){
			return a_id;
		}else{
			return "";
		}
	}
	/**
	 * 功能描述 :去年的年份
	 * @return String
	 * @author 李月
	 */
	private int  getFpYear(){
		Date date=new Date();
		SimpleDateFormat time=new SimpleDateFormat("yyyy"); 
		Integer time1=Integer.valueOf(time.format(date))-1;
		return time1;
	}
	/**
	 * 功能描述 :在数据库里得到今年的数据
	 * @return String
	 * @author 李月
	 */
	private void initNowData(){
		
	
		if(nowData!=null && nowData.size()!=0){
			log.info("hhahhahhahha"+nowData);
			try {
				if(!v.checkEmpty(nowData.get("isAchieveMusicRoom"))){
					isAchieveMusicRoom=isAchieve(nowData.get("isAchieveMusicRoom").toString());
				}
				if(!v.checkEmpty(nowData.get("spendForAddMusicRoom"))){
					spendForAddMusicRoom=nowData.get("spendForAddMusicRoom").toString();
				}
				//2
				if(!v.checkEmpty(nowData.get("isAchieveMusicEqui"))){
					isAchieveMusicEqui=isAchieve(nowData.get("isAchieveMusicEqui").toString());
				}
				if(!v.checkEmpty(nowData.get("spendForMusicEquiment"))){
					spendForMusicEquiment=nowData.get("spendForMusicEquiment").toString();
				}
				//3
				if(!v.checkEmpty(nowData.get("isAchieveDrawClass"))){
					isAchieveDrawClass=isAchieve(nowData.get("isAchieveDrawClass").toString());
				}
				if(!v.checkEmpty(nowData.get("spendForDrawRoom"))){
					spendForDrawRoom=nowData.get("spendForDrawRoom").toString();
				}
				
				//4
				if(!v.checkEmpty(nowData.get("isAchieveDrawEqui"))){
					isAchieveDrawEqui=isAchieve(nowData.get("isAchieveDrawEqui").toString());
				}
				if(!v.checkEmpty(nowData.get("spendForDrawEqui"))){
					spendForDrawEqui=nowData.get("spendForDrawEqui").toString();
				}
				
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
	}
	
	/**
	 * 功能:判断数据库取出来的是是，还是否 然后转成数字
	 * @param object
	 * @return
	 *  @author 李月
	 */
	private String isAchieve(Object object){
		String str = object.toString();
		if(str.equals("今年新增达标")){
			return "1";
		}else if(str.equals("否")){
			return "2";
		}else if(str.equals("往年已达标")){
			return "3";
		}
		else{
			return "";
		}
	}
	
	
	
	//--------------get和set方法------
	public String getIsAchieveMusicRoom() {
		return isAchieveMusicRoom;
	}
	public void setIsAchieveMusicRoom(String isAchieveMusicRoom) {
		this.isAchieveMusicRoom = isAchieveMusicRoom;
	}
	public String getSpendForMusicEqui() {
		return spendForAddMusicRoom;
	}
	public void setSpendForMusicEqui(String spendForMusicEqui) {
		this.spendForAddMusicRoom = spendForMusicEqui;
	}

	public String getIsAchieveMusicEqui() {
		return isAchieveMusicEqui;
	}
	public void setIsAchieveMusicEqui(String isAchieveMusicEqui) {
		this.isAchieveMusicEqui = isAchieveMusicEqui;
	}
	public String getSpendForMusicEquiment() {
		return spendForMusicEquiment;
	}
	public void setSpendForMusicEquiment(String spendForMusicEquiment) {
		this.spendForMusicEquiment = spendForMusicEquiment;
	}

	public String getIsAchieveDrawClass() {
		return isAchieveDrawClass;
	}
	public void setIsAchieveDrawClass(String isAchieveDrawClass) {
		this.isAchieveDrawClass = isAchieveDrawClass;
	}
	public String getSpendForDrawRoom() {
		return spendForDrawRoom;
	}
	public void setSpendForDrawRoom(String spendForDrawRoom) {
		this.spendForDrawRoom = spendForDrawRoom;
	}
	
	public String getIsAchieveDrawEqui() {
		return isAchieveDrawEqui;
	}
	public void setIsAchieveDrawEqui(String isAchieveDrawEqui) {
		this.isAchieveDrawEqui = isAchieveDrawEqui;
	}
	public String getSpendForDrawEqui() {
		return spendForDrawEqui;
	}
	public void setSpendForDrawEqui(String spendForDrawEqui) {
		this.spendForDrawEqui = spendForDrawEqui;
	}
	
	public String getSpendForAddMusicRoom() {
		return spendForAddMusicRoom;
	}
	public void setSpendForAddMusicRoom(String spendForAddMusicRoom) {
		this.spendForAddMusicRoom = spendForAddMusicRoom;
	}
	public Map<String, Object> getOldData() {
		return oldData;
	}
	public void setOldData(Map<String, Object> oldData) {
		this.oldData = oldData;
	}
	public String getNowYear() {
		return nowYear;
	}
	public void setNowYear(String nowYear) {
		this.nowYear = nowYear;
	}
	

	public Map<String, Object> getNowData() {
		return nowData;
	}

	public void setNowData(Map<String, Object> nowData) {
		this.nowData = nowData;
	}

	

	public String getNewToken2() {
		return newToken2;
	}

	public void setNewToken2(String newToken2) {
		this.newToken2 = newToken2;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	

}
