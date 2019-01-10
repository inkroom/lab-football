package javadev.iip.action.art;

import java.util.HashMap;
import java.util.Map;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.util.validata.V;

/**
 * 功能描述： 艺术条件保障信息
 * @version：1.0 
 * @author 刘俊良 
 * @CreateDate 2016-8-31
 */
public class ArtThirdAction extends BaseAction {

	private String musClassroomIdeal;// 音乐专用教室应有
	private String musClassroomPresent;// 实有
	private String musClassroomPequired;// 缺额

	private String paintClassroomIdeal;// 美术专用教室应有
	private String paintClassroomPresent;
	private String paintPequired;

	private String isEquipQualified;// 是否达标
	private String fundRequired;// 配备达标资金缺额

	private String artClassroomOther;// 其他艺术活动室
	private String artVenuesNum;// 艺术场馆
	private String venuesArea;// 场馆面积
	private String selfRemarkEnsurance;// 自评得分

	private Map<String, Object> oldData = new HashMap<String, Object>();
	
	private static V v = new V();
	private String errorInfo;
	private String token;
	 

	
	/**
	 * 功能的描述: artThirdView action执行方法，检测以前的数据并加载到页面中
	 * @author: 刘俊良
	 * @return: 返回执行结果
	 * @CreateDate: 2016年9月1日14:48:00
	 */
	public String intoArtThirdView() throws Exception {
		if(checkPrevForm() == 0) {
			setAllOldData();
			setFormDate();
			return SUCCESS;
		}else if(checkPrevForm() == 2){
			setErrorInfo("当前系统未开放");
			return "noOpen";
		}else{
			setErrorInfo("当前页面还有未完成的数据项，请先填写完成");
			return "intoArtNdView";
		}
	}
	
	/**
	 * 功能的描述: artThird action执行方法，检测输入是否合法；决定是否存入数据库
	 * @author: 刘俊良
	 * @return: 返回执行结果
	 * @CreateDate: 2016年8月31日18:04:23
	 */
	public String insertArtThird() throws Exception {
		
		
		if(checkPrevForm() == 2){
				setErrorInfo("当前系统未开放");
				return "noOpen";
			}
		if(checkPrevForm() == 1){
			setErrorInfo("当前页面还有未完成的数据项，请先填写完成");
			return "intoArtNdView";
		}
		
		setAllOldData();
		//检测数据
		if(checkArtThirdFormDate()==false){
			
			return ERROR;
		}
		//存储数据
		if(isChangedData() && !getServMgr().getArtThirdService().uploadForm(getFormDate())) {
			
			setErrorInfo("服务器忙！请稍后重新提交");
			return ERROR;
		}
		
		return SUCCESS;

	}
	
	
	/**
	 * 功能的描述: 前一个表的信息是否填写完整
	 * @author 刘俊良
	 * @return 返回前一表单是否填写完整
	 * @CreateDate 2016年月9日11:04:23
	 */
	public int checkPrevForm(){
		
		setToken(getSession().get("token").toString());
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		return getServMgr().getArtThirdService().detectionArtndData(oid);
	}
	
	/**
	 * 功能的描述: 检测ArtThird输入的表单数据是否非法
	 * @author 刘俊良
	 * @return 返回输入数据是否非法
	 * @CreateDate 2016年8月31日18:04:23
	 */
	public boolean checkArtThirdFormDate(){
		
		if (!isNum(getMusClassroomIdeal(), 100000000)) {
			setErrorInfo("音乐专用教室应有只能输入数字或未填写");
			return false;
		}
		if (!isNum(getMusClassroomPresent(), 100000000)) {
			setErrorInfo("音乐专用教室实有只能输入数字或未填写");
			return false;
		}
		
		Integer i = Integer.parseInt(getMusClassroomIdeal()) - Integer.parseInt(getMusClassroomPresent());
		if(i < 0){
			i = 0;
		}
		setMusClassroomPequired(i+"");
		
		if (!isNum(getPaintClassroomIdeal(), 100000000)) {
			setErrorInfo("美术专用教室应有只能输入整数或未填写");
			return false;
		}
		if (!isNum(getPaintClassroomPresent(), 100000000)) {
			setErrorInfo("美术专用教室实有只能输入整数或未填写");
			return false;
		}
		
		i = Integer.parseInt(getPaintClassroomIdeal())-Integer.parseInt(getPaintClassroomPresent());
		if(i < 0){
			i = 0;
		}
		setPaintPequired(i+"");
		
		if (!isNum(getArtClassroomOther(), 100000000)) {
			setErrorInfo("其他艺术活动室只能输入整数或未填写");
			return false;
		}
		if (!isNum(getArtVenuesNum(), 100000000)) {
			setErrorInfo("艺术场馆只能输入整数或未填写");
			return false;
		}
		
		if(getArtVenuesNum().equals("0") == false){
			if (!isFloats(getVenuesArea(), 10000)) {
				setErrorInfo("场馆面积只能输入数字或数字过大、未填写");
				return false;
			}
			if(getVenuesArea().equals("0") == true){
				setErrorInfo("有艺术场馆，则场馆面积不能为0");
				return false;
			}
		}else{
			setVenuesArea("0");
		}
		
		if (!isNum(getSelfRemarkEnsurance(), 21)) {
			setErrorInfo("自评得分只能输入整数或未填写，满分20分");
			return false;
		}
		if (getIsEquipQualified().equals("2") && !isFloats(getFundRequired(), 100000000)) {
			
			setErrorInfo("配备达标资金缺额只能输入数字");
			return false;
		}
		if(getIsEquipQualified().equals("1")){
			setFundRequired("0");
		}
		return true;
	}
	
	/**
	 * 功能描述：检测填写的数据和已填写的数据是否有不同之处
	 * @author 刘俊良
	 * @return 是否改动过数据
	 * @CreateDate 2016年9月26日10:07:02
	 */
	public boolean isChangedData(){
		
		boolean flag = true;
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		Map<String, Object> map = getServMgr().getArtThirdService().seltDate(oid);
		
		if(map != null){	
			
			//检测音乐教室数据
			String str = v.checkEmpty(map.get("musClassroomIdeal"))==true?"":map.get("musClassroomIdeal").toString();
			if(str.length() > 0 && str.equals(musClassroomIdeal)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("musClassroomPresent"))==true?"":map.get("musClassroomPresent").toString();
			if(!flag && str.length() > 0 && str.equals(musClassroomPresent)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("musClassroomPequired"))==true?"":map.get("musClassroomPequired").toString();
			if(!flag && str.length() > 0 && str.equals(musClassroomPequired)){
				flag = false;
			}else{
				flag = true;
			}
			
			//检测美术教室
			str = v.checkEmpty(map.get("paintClassroomIdeal"))==true?"":map.get("paintClassroomIdeal").toString();
			if(!flag && str.length() > 0 && str.equals(paintClassroomIdeal)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("paintClassroomPresent"))==true?"":map.get("paintClassroomPresent").toString();
			if(!flag && str.length() > 0 && str.equals(paintClassroomPresent)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("paintPequired"))==true?"":map.get("paintPequired").toString();
			if(!flag && str.length() > 0 && str.equals(paintPequired)){
				flag = false;
			}else{
				flag = true;
			}
			
			//配备是否达标
			str = v.checkEmpty(map.get("isEquipQualified"))==true?"":map.get("isEquipQualified").toString();
			if(!flag && str.length() > 0 && str.equals(isEquipQualified)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("fundRequired"))==true?"":map.get("fundRequired").toString();
			if(!flag && str.length() > 0 && str.equals(fundRequired)){
				flag = false;
			}else{
				flag = true;
			}
			
			//其他
			str = v.checkEmpty(map.get("artClassroomOther"))==true?"":map.get("artClassroomOther").toString();
			if(!flag && str.length() > 0 && str.equals(artClassroomOther)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("artVenuesNum"))==true?"":map.get("artVenuesNum").toString();
			if(!flag && str.length() > 0 && str.equals(artVenuesNum)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("venuesArea"))==true?"":map.get("venuesArea").toString();
			if(!flag && str.length() > 0 && str.equals(venuesArea)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("selfRemarkEnsurance"))==true?"":map.get("selfRemarkEnsurance").toString();
			if(!flag && str.length() > 0 && str.equals(selfRemarkEnsurance)){
				flag = false;
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
	
	
	/**
	 * 功能的描述: 判断是否是小数以及数字位数小于等于8
	 * @author 刘俊良
	 * @param str
	 * @return String
	 * @CreateDate 2016年8月31日18:04:23
	 */
	public boolean isFloats(String str , double num) {

		String regex = "^([1-9][0-9]{1,11}(.[0-9]{1,4}))|([0-9](.[0-9]{1,4}))|([1-9][0-9]*)|([0-9])$";
		if (str !=null && str.length()<14 && v.comparePattern(str, regex) && v.judgeSize(-1, num, str)) {

			return true;
		} else {

			return false;
		}
	}
	
	/**
	 * 功能的描述: 判断是否是整数
	 * @author: 刘俊良
	 * @param: str num
	 * @return: 返回是否是整数
	 * @Create Date: 2016年8月31日18:04:23
	 */
	public boolean isNum(String str , double num){
		String regex = "^([1-9][0-9]*)|([0-9])$";
		if (str!=null && str.length()>0 && str.length()<13 && v.comparePattern(str, regex) && v.judgeSize(-1, num, str)) {

			return true;
		} else {

			return false;
		}
	}

	
	/**
	 * 功能的描述: 获取并设置artThird页面前一年的数据
	 * @author 刘俊良
	 * @CreateDate 2016年8月31日18:04:23
	 */
	public void setAllOldData() {
		
		setErrorInfo("");//初始化错误信息
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		setOldData(getServMgr().getArtThirdService().getAllOldData(oid));
	}
	
	
	/**
	 * 功能的描述: 将artThird表单数据加入map中以便存入数据库
	 * @author 刘俊良
	 * @return map
	 * @CreateDate 2016年8月31日18:04:23
	 */
	public  Map<String, Object> getFormDate() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("musClassroomIdeal", getMusClassroomIdeal());
		map.put("musClassroomPresent", getMusClassroomPresent());
		map.put("musClassroomPequired", getMusClassroomPequired());
		
		map.put("paintClassroomIdeal", getPaintClassroomIdeal());
		map.put("paintClassroomPresent", getPaintClassroomPresent());
		map.put("paintPequired", getPaintPequired());

		map.put("isEquipQualified", getIsEquipQualified());
		map.put("fundRequired", getFundRequired());

		map.put("artClassroomOther", getArtClassroomOther());
		map.put("artVenuesNum", getArtVenuesNum());
		map.put("venuesArea", getVenuesArea());
		map.put("selfRemarkEnsurance", getSelfRemarkEnsurance());
		
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		map.put("O_ID", oid);
		map.put("C_ID", getSession().get(Constants.LOGIN_USER_C_ID).toString());
		return map;
	}
	
	/**
	 * 功能的描述  ：从数据库获取艺术条件保障信息今年的数据，为当前表单的值赋值；以便前台显示
	 * @author 刘俊良
	 * @CreateDate: 2016年9月1日19:15:23
	 */
	public void setFormDate(){
		
		
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		Map<String, Object> map = getServMgr().getArtThirdService().seltDate(oid);
		setErrorInfo("");
		if(map != null){	
			setMusClassroomIdeal( map.get("musClassroomIdeal").toString()); 
			setMusClassroomPresent( map.get("musClassroomPresent").toString());
			setMusClassroomPequired( map.get("musClassroomPequired").toString());
			
			setPaintClassroomIdeal( map.get("paintClassroomIdeal").toString());
			setPaintClassroomPresent( map.get("paintClassroomPresent").toString());
			setPaintPequired( map.get("paintPequired").toString());
			
			setIsEquipQualified( map.get("isEquipQualified").toString());
			setFundRequired( map.get("fundRequired").toString());
			
			setArtClassroomOther( map.get("artClassroomOther").toString());
			setArtVenuesNum( map.get("artVenuesNum").toString());
			setVenuesArea( map.get("venuesArea").toString());
			setSelfRemarkEnsurance( map.get("selfRemarkEnsurance").toString());
		}
			
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public Map<String, Object> getOldData() {
		return oldData;
	}

	public void setOldData(Map<String, Object> oldData) {
		this.oldData = oldData;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getMusClassroomIdeal() {
		return musClassroomIdeal;
	}

	public void setMusClassroomIdeal(String musClassroomIdeal) {
		this.musClassroomIdeal = musClassroomIdeal;
	}

	public String getMusClassroomPresent() {
		return musClassroomPresent;
	}

	public void setMusClassroomPresent(String musClassroomPresent) {
		this.musClassroomPresent = musClassroomPresent;
	}

	public String getMusClassroomPequired() {
		return musClassroomPequired;
	}

	public void setMusClassroomPequired(String musClassroomPequired) {
		this.musClassroomPequired = musClassroomPequired;
	}

	public String getPaintClassroomIdeal() {
		return paintClassroomIdeal;
	}

	public void setPaintClassroomIdeal(String paintClassroomIdeal) {
		this.paintClassroomIdeal = paintClassroomIdeal;
	}

	public String getPaintClassroomPresent() {
		return paintClassroomPresent;
	}

	public void setPaintClassroomPresent(String paintClassroomPresent) {
		this.paintClassroomPresent = paintClassroomPresent;
	}

	public String getPaintPequired() {
		return paintPequired;
	}

	public void setPaintPequired(String paintPequired) {
		this.paintPequired = paintPequired;
	}

	public String getIsEquipQualified() {
		return isEquipQualified;
	}

	public void setIsEquipQualified(String isEquipQualified) {
		this.isEquipQualified = isEquipQualified;
	}

	public String getFundRequired() {
		return fundRequired;
	}

	public void setFundRequired(String fundRequired) {
		this.fundRequired = fundRequired;
	}

	public String getArtClassroomOther() {
		return artClassroomOther;
	}

	public void setArtClassroomOther(String artClassroomOther) {
		this.artClassroomOther = artClassroomOther;
	}

	public String getArtVenuesNum() {
		return artVenuesNum;
	}

	public void setArtVenuesNum(String artVenuesNum) {
		this.artVenuesNum = artVenuesNum;
	}

	public String getVenuesArea() {
		return venuesArea;
	}

	public void setVenuesArea(String venuesArea) {
		this.venuesArea = venuesArea;
	}

	public String getSelfRemarkEnsurance() {
		return selfRemarkEnsurance;
	}

	public void setSelfRemarkEnsurance(String selfRemarkEnsurance) {
		this.selfRemarkEnsurance = selfRemarkEnsurance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
