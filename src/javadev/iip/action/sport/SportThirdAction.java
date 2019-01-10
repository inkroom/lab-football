package javadev.iip.action.sport;

import java.util.HashMap;
import java.util.Map;
import javadev.iip.action.BaseAction;
import javadev.iip.util.validata.V;
import javadev.core.Constants;

/**
 * 功能描述： 体育经费和工作等级评估信息action
 * @author 刘俊良
 * @version 1.0 
 * @CreateDate 2016-8-31
 */
public class SportThirdAction extends BaseAction{
	
	private String isAddPointProject;//是否有加分项
	
	private String expense = "";//支出总额
	private String expenseField = "";//体育场地经费支出
	private String expenseEquip = "";//体育专用器材经费支出
	private String expenseActivity = "";//体育工作经费支出
	
	private String isInsurance = "";//制定体育活动意外伤害保障措施
	
	private String errorInfo = null;
	private Map<String, Object> oldData = new HashMap<String, Object>();
	private static V v = new V();
    private String token;
    
	/**
	 * 功能的描述: sportThirdView action执行方法，检测以前的数据并加载到页面中
	 * @author: 刘俊良
	 * @return: 执行结果
	 * @CreateDate: 2016年9月1日14:48:00
	 */
	public String intoSportThirdView() throws Exception {
		
		if(checkPrevForm() == 0) {
			setAllOldData();
			setFormDate();
			return SUCCESS;
		}else if(checkPrevForm() == 2){
			setErrorInfo("当前系统未开放");
			return "noOpen";
		}else{
			setErrorInfo("当前页面还有未完成的数据项，请先填写完成");
			return "intoSportNdView";
		}
	}
	
	/**
	 * 功能的描述: sportThird action执行方法，检测输入是否合法；决定是否存入数据库
	 * @author 刘俊良
	 * @return 返回执行结果
	 * @CreateDate 2016年8月31日18:04:23
	 */
	public String insertSportThird() throws Exception {
		
		//检测数据
		if(checkPrevForm() == 2){
			setErrorInfo("当前系统未开放");
			return "noOpen";
		}
		if(checkPrevForm() == 1){
			setErrorInfo("当前页面还有未完成的数据项，请先填写完成");
			return "intoSportNdView";
		}
		
		setAllOldData();
		if(checkSportThirdFormDate() == false){
			return ERROR;
		}
		//存储数据
		if(isChangedData() && getServMgr().getSportThirdService().uploadForm(getFormDate()) == false){
			
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
		return getServMgr().getSportThirdService().detectionSportNdData(oid);
	}
	
	
	/**
	 * 功能的描述: 检测sportThird输入的表单数据是否非法
	 * @author 刘俊良
	 * @return 返回输入数据是否非法
	 * @CreateDate 2016年8月31日18:04:23
	 */
	public boolean checkSportThirdFormDate(){
		
		
		if (!isFloats(getExpenseField())){
			setErrorInfo("体育场地经费支出只能输入数字或未填写");
			return false;
		}
		
		if (!isFloats(getExpenseEquip())){
			setErrorInfo("体育专用器材经费支出只能输入数字或未填写");
			return false;
		}
		
		if (!isFloats(getExpenseActivity())){
			setErrorInfo("体育工作经费支出只能输入数字或未填写");
			return false;
		}
		Double i = Double.parseDouble(getExpenseField()) + Double.parseDouble(getExpenseEquip()) + Double.parseDouble(getExpenseActivity());
		setExpense(""+i);
		
		return true;
	}
	
	/**
	 * 功能描述：检测填写的数据和已填写的数据是否有不同之处
	 * @author 刘俊良
	 * @return 是否改动过数据
	 * @CreateDate 2016年9月26日9:49:02
	 */
	public boolean isChangedData(){
		
		boolean flag = true;
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		Map<String, Object> map = getServMgr().getSportThirdService().seltDate(oid);
		
		if(map != null){
			String str = v.checkEmpty(map.get("isAddPointProject"))==true?"":map.get("isAddPointProject").toString();
			if(str.length() > 0 && str.equals(isAddPointProject)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("expenseField"))==true?"":map.get("expenseField").toString();
			if(!flag && str.length() > 0 && str.equals(expenseField)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("expenseEquip"))==true?"":map.get("expenseEquip").toString();
			if(!flag && str.length() > 0 && str.equals(expenseEquip)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("expenseActivity"))==true?"":map.get("expenseActivity").toString();
			if(!flag && str.length() > 0 && str.equals(expenseActivity)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("isInsurance"))==true?"":map.get("isInsurance").toString();
			if(!flag && str.length() > 0 && str.equals(isInsurance)){
				flag = false;
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 功能的描述: 判断是否是小数以及数字位数小于等于8
	 * @author: 刘俊良
	 * @param: str
	 * @return: 返回是否是小数以及数字位数小于等于8
	 * @Create Date: 2016年8月31日18:04:23
	 */
	public boolean isFloats(String str) {
		
		String regex = "^([1-9][0-9]{1,11}(.[0-9]{1,4}))|([0-9](.[0-9]{1,4}))|([1-9][0-9]*)|([0-9])$";
		if (str!=null && str.length()>0 && str.length()<14 && v.comparePattern(str, regex) && v.judgeSize(-1, 100000000, str)) {

			return true;
		} else {

			return false;
		}
     }
	
	/**
	 * 功能的描述: 获取并设置本页前一年的数据;并初始化errorinfo
	 * @author: 刘俊良
	 * @CreateDate: 2016年8月31日18:04:23
	 */
	public void setAllOldData() {
		
		setErrorInfo("");//初始化错误信息
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		setOldData(getServMgr().getSportThirdService().getAllOldData(oid));
	}
	
	/**
	 * 功能的描述: 将表单数据加入map中以便存入数据库,并返回map
	 * 
	 * @author: 刘俊良
	 * @return 返回存储的map数据
	 * @CreateDate: 2016年8月31日18:04:23
	 */
	public Map<String, String> getFormDate() {
		
	    Map<String, String> map = new HashMap<String, String>();
		
		map.put("isAddPointProject", getIsAddPointProject());
		
		map.put("expense", getExpense());
		map.put("expenseField", getExpenseField());
		map.put("expenseEquip", getExpenseEquip());
		map.put("expenseActivity", getExpenseActivity());
		
		map.put("isInsurance", getIsInsurance());
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		map.put("O_ID", oid);
		map.put("C_ID", getSession().get(Constants.LOGIN_USER_C_ID).toString());
		return map;
	}
	
	/**
	 * 功能的描述  ：从数据库获取SportThird今年的数据，为当前表单的值赋值；以便前台显示
	 * @author 刘俊良
	 * @CreateDate: 2016年9月1日19:15:23
	 */
	public void setFormDate(){
		
		@SuppressWarnings("unchecked")
		String oid = ((Map<String, Object>)getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		Map<String, Object> map = getServMgr().getSportThirdService().seltDate(oid);
		if(map != null){
			setIsAddPointProject( map.get("isAddPointProject").toString());		
		    setExpense( map.get("expense").toString());
			setExpenseField( map.get("expenseField").toString());
			setExpenseEquip( map.get("expenseEquip").toString());
			setExpenseActivity( map.get("expenseActivity").toString());
				
			setIsInsurance( map.get("isInsurance").toString());
		}
	}
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getIsAddPointProject() {
		return isAddPointProject;
	}

	public void setIsAddPointProject(String isAddPointProject) {
		this.isAddPointProject = isAddPointProject;
	}

	public Map<String, Object> getOldData() {
		return oldData;
	}

	public void setOldData(Map<String, Object> oldData) {
		this.oldData = oldData;
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}
	
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	public String getExpense() {
		return expense;
	}
	
	public void setExpense(String expense) {
		this.expense = expense.trim();
	}
	
	public String getExpenseField() {
		return expenseField;
	}
	
	public void setExpenseField(String expenseField) {
		this.expenseField = expenseField.trim();
	}
	
	public String getExpenseEquip() {
		return expenseEquip;
	}
	
	public void setExpenseEquip(String expenseEquip) {
		this.expenseEquip = expenseEquip.trim();
	}
	
	public String getExpenseActivity() {
		return expenseActivity;
	}
	
	public void setExpenseActivity(String expenseActivity) {
		this.expenseActivity = expenseActivity.trim();
	}
	
	public String getIsInsurance() {
		return isInsurance;
	}
	
	public void setIsInsurance(String isInsurance) {
		this.isInsurance = isInsurance;
	}
	
}
