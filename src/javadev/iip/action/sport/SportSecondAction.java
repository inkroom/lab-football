package javadev.iip.action.sport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

/**
 * 功能描述：体育类场地设施数据表单验证及提交
 * 
 * @author 刘俊
 * @version 1.0 Create Date: 2016-8-31
 */
public class SportSecondAction extends BaseAction {

	private ServiceManager serviceManager;
	// 200米田径场块数
	private String length2;
	// 300米田径场块数
	private String length3;
	// 300米至400米田径场块数
	private String length3t4;
	// 400米田径场块数
	private String length4;
	// 篮球场块数
	private String basketball;
	// 排球场块数
	private String volleyball;
	// 器械体操/游戏区面积
	private String gameArea;
	// 体育馆个数
	private String sportHall;
	// 体育馆面积
	private String sportHallArea;
	// 游泳池个数
	private String swimPool;
	// 游泳池面积
	private String swimPoolArea;
	// 学生体质测试室个数
	private String testRoom;
	// 体育器材是否达标
	private String storard;

	// 上一年200米田径场
	private String last2;
	// 上一年300米田径场
	private String last3;
	// 上一年300米至400米田径场
	private String last3t4;
	// 上一年400米田径场
	private String last4;
	// 上一年篮球场个数
	private String lastBasket;
	// 上一年排球场个数
	private String lastVolley;
	// 上一年器械体操/游戏区面积
	private String lastGameArea;
	// 上一年体育馆个数
	private String lastSportHall;
	// 上一年体育馆面积
	private String lastSportArea;
	// 上一年游泳池个数
	private String lastSwimPool;
	// 上一年游泳池面积
	private String lastSwimArea;
	// 上一年学生体质测试室个数
	private String lastTestRoom;
	// 储存错误信息
	private String errorInfo;
	// 验证功能
	private static V v = new V();

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获得数据库内容 显示当前数据和前一年数据
	 * 
	 * @return success
	 */
	public String execute() throws Exception {

		setToken(getSession().get("token").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("o_id").toString();
		map = serviceManager.getOtherService().getSportData(o_id);

		if (map == null) {
			errorInfo = "当前系统未开放";
			return "noOpen";
		}

		/**
		 * 验证上一步的表单中是否存在未填写内容 若存在未填写内容则跳转到那一页
		 */
		if (v.checkEmpty(map.get("stu_total")) || v.checkEmpty(map.get("RATIO_TEA_STU"))) {
			errorInfo = "当前页面还有未完成的数据项，请先填写完成！";
			return "backView";
		}

		/**
		 * 验证当前数据库中数据是否为空 若不为空则赋值给当前输入框内
		 */

		if (!v.checkEmpty(map.get("FIELD_TRACK_200M"))) {
			length2 = map.get("FIELD_TRACK_200M").toString();
		}
		if (!v.checkEmpty(map.get("FIELD_TRACK_300M"))) {
			length3 = map.get("FIELD_TRACK_300M").toString();
		}
		if (!v.checkEmpty(map.get("FIELD_TRACK_300_400M"))) {
			length3t4 = map.get("FIELD_TRACK_300_400M").toString();
		}
		if (!v.checkEmpty(map.get("FIELD_TRACK_400M"))) {
			length4 = map.get("FIELD_TRACK_400M").toString();
		}
		if (!v.checkEmpty(map.get("FIELDS_BASKETBALL"))) {
			basketball = map.get("FIELDS_BASKETBALL").toString();
		}
		if (!v.checkEmpty(map.get("FIELDS_VOLLEYBALL"))) {
			volleyball = map.get("FIELDS_VOLLEYBALL").toString();
		}
		if (!v.checkEmpty(map.get("FIELDS_GYM_AREA"))) {
			gameArea = map.get("FIELDS_GYM_AREA").toString();
		}
		if (!v.checkEmpty(map.get("STADIUM_TOTAL"))) {
			sportHall = map.get("STADIUM_TOTAL").toString();
		}
		if (!v.checkEmpty(map.get("STADIUM_AREA"))) {
			sportHallArea = map.get("STADIUM_AREA").toString();
		}
		if (!v.checkEmpty(map.get("POOL_TOTAL"))) {
			swimPool = map.get("POOL_TOTAL").toString();
		}
		if (!v.checkEmpty(map.get("POOL_AREA"))) {
			swimPoolArea = map.get("POOL_AREA").toString();
		}
		if (!v.checkEmpty(map.get("TEST_ROOM_TOTAL"))) {
			testRoom = map.get("TEST_ROOM_TOTAL").toString();
		}
		if (!v.checkEmpty(map.get("IS_EQUIP_QUALIFIED"))) {
			storard = map.get("IS_EQUIP_QUALIFIED").toString();
		}

		/**
		 * 验证数据库中前一年数据是否为空
		 */
		map1 = serviceManager.getOtherService().getSportLastYearData(o_id);
		if (map1 != null) {
			last2 = map1.get("FIELD_TRACK_200M").toString();
			last3 = map1.get("FIELD_TRACK_300M").toString();
			last3t4 = map1.get("FIELD_TRACK_300_400M").toString();
			last4 = map1.get("FIELD_TRACK_400M").toString();
			lastBasket = map1.get("FIELDS_BASKETBALL").toString();
			lastVolley = map1.get("FIELDS_VOLLEYBALL").toString();
			lastGameArea = map1.get("FIELDS_GYM_AREA").toString();
			lastSportHall = map1.get("STADIUM_TOTAL").toString();
			lastSportArea = map1.get("STADIUM_AREA").toString();
			lastSwimPool = map1.get("POOL_TOTAL").toString();
			lastSwimArea = map1.get("POOL_AREA").toString();
			lastTestRoom = map1.get("TEST_ROOM_TOTAL").toString();
		}

		return "success";

	}

	/**
	 * 验证数据合法性 合法后传入service层
	 * 
	 * @param
	 * @return
	 */
	public String getData() throws Exception {

		setToken(getSession().get("token").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("o_id").toString();
		map = serviceManager.getOtherService().getSportData(o_id);

		if (map == null) {
			errorInfo = "当前系统未开放";
			return "noOpen";
		}

		/**
		 * 验证上一步的表单中是否存在未填写内容 若存在未填写内容则跳转到那一页
		 */
		if (v.checkEmpty(map.get("stu_total")) || v.checkEmpty(map.get("RATIO_TEA_STU"))) {
			errorInfo = "当前页面还有未完成的数据项，请先填写完成！";
			return "back";
		}

		// 验证200米田径块输入是否合法
		if (V.isNumeric(length2) == false || v.judgeSize(-1, 100000000, length2) == false) {
			errorInfo = "200米田径块输入数字有误";
			return "error";
		}
		// 验证300米田径块输入是否合法
		if (V.isNumeric(length3) == false || v.judgeSize(-1, 100000000, length3) == false) {
			errorInfo = "300米田径块输入数字有误";
			return "error";
		}
		// 验证300米至400田径块输入是否合法
		if (V.isNumeric(length3t4) == false || v.judgeSize(-1, 100000000, length3t4) == false) {
			errorInfo = "300至400米田径块输入数字有误";
			return "error";
		}
		// 验证400米田径块输入是否合法
		if (V.isNumeric(length4) == false || v.judgeSize(-1, 100000000, length4) == false) {
			getSession().put("error", "400米田径块输入数字有误");
			return "error";
		}
		// 验证篮球场个数输入是否合法
		if (V.isNumeric(basketball) == false || v.judgeSize(-1, 100000000, basketball) == false) {
			errorInfo = "篮球场个数输入有误";
			return "error";
		}
		// 验证排球场个数输入是否合法
		if (V.isNumeric(volleyball) == false || v.judgeSize(-1, 100000000, volleyball) == false) {
			errorInfo = "排球场个数输入有误";
			return "error";
		}
		// 验证器械体操/游戏区面积输入是否合法
		if (V.isNumeric(gameArea) == false || v.judgeSize(-1, 100000000, gameArea) == false) {
			errorInfo = "器械体操/游戏区面积输入有误";
			return "error";
		}
		// 验证体育馆个数输入是否合法
		if (V.isNumeric(sportHall) == false || v.judgeSize(-1, 100000000, sportHall) == false) {
			errorInfo = "体育馆个数输入有误";
			return "error";
		}
		// 验证体育馆面积输入是否合法
		if (V.isNumeric(sportHallArea) == false || v.judgeSize(-1, 100000000, sportHallArea) == false) {
			errorInfo = "体育馆面积输入有误";
			return "error";
		}

		if (Integer.parseInt(sportHall) != 0 && Integer.parseInt(sportHallArea) == 0) {
			errorInfo = "体育馆面积输入不合法，存在体育馆，面积则不能为0";
			return "error";
		}
		if (Integer.parseInt(sportHallArea) != 0 && Integer.parseInt(sportHall) == 0) {
			errorInfo = "体育馆面积输入不合法，没有体育馆，面积为0";
			return "error";
		}
		// 验证游泳池个数输入是否合法
		if (V.isNumeric(swimPool) == false || v.judgeSize(-1, 100000000, swimPool) == false) {
			errorInfo = "游泳池个数输入有误";
			return "error";
		}
		// 验证游泳馆面积输入是否合法
		if (V.isNumeric(swimPoolArea) == false || v.judgeSize(-1, 100000000, swimPoolArea) == false) {
			errorInfo = "游泳馆面积输入有误";
			return "error";
		}

		if (Integer.parseInt(swimPool) != 0 && Integer.parseInt(swimPoolArea) == 0) {
			errorInfo = "游泳池面积输入不合法，存在游泳池，面积则不能为0";
			return "error";
		}
		if (Integer.parseInt(swimPoolArea) != 0 && Integer.parseInt(swimPool) == 0) {
			errorInfo = "游泳池面积输入不合法，没有游泳池，面积为0";
			return "error";
		}
		// 验证学生体质测试室个数输入是否合法
		if (V.isNumeric(testRoom) == false || v.judgeSize(-1, 100000000, testRoom) == false) {
			errorInfo = "学生体质测试室个数有误";
			return "error";
		}

		/**
		 * 将前端信息存入Map 传入service类
		 */
		map.put("data1", length2);
		map.put("data2", length3);
		map.put("data3", length3t4);
		map.put("data4", length4);
		map.put("data5", basketball);
		map.put("data6", volleyball);
		map.put("data7", gameArea);
		map.put("data8", sportHall);
		map.put("data9", sportHallArea);
		map.put("data10", swimPool);
		map.put("data11", swimPoolArea);
		map.put("data12", testRoom);
		map.put("data13", storard);
		map.put("o_id", o_id);
		map.put("c_id", getSession().get(Constants.LOGIN_USER_C_ID).toString());
		
		if (isChangedData()) {
			serviceManager.getSportSecondService().updateSportSecond(map);
			System.out.println("修改了==============");
			return "success";
		}
		System.out.println("没有修改================");
		return "success";
	}

	public boolean isChangedData() {

		boolean flag = true;
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String  o_id = user.get("O_ID").toString();
		Map<String, Object> map = serviceManager.getOtherService().getSportData(o_id);

		if (map != null) {
			
			String str = v.checkEmpty(map.get("FIELD_TRACK_200M")) == true ? "": map.get("FIELD_TRACK_200M").toString();
			
			if (str.length() > 0 && str.equals(length2)) {
				flag = false;
			}else{
				flag = true;
			}

			str = v.checkEmpty(map.get("FIELD_TRACK_300M")) == true ? "" : map.get("FIELD_TRACK_300M").toString();
			if (!flag && str.length() > 0 && str.equals(length3)) {
				flag = false;
			} else{
				flag = true;
			}

			str = v.checkEmpty(map.get("FIELD_TRACK_300_400M")) == true ? "": map.get("FIELD_TRACK_300_400M").toString();
			if (!flag && str.length() > 0 && str.equals(length3t4)) {
				flag = false;
			}else{
				flag = true;
			}

			str = v.checkEmpty(map.get("FIELD_TRACK_400M")) == true ? "" : map.get("FIELD_TRACK_400M").toString();
			if (!flag && str.length() > 0 && str.equals(length4)) {
				flag = false;
			}else{
				flag = true;
			}

			str = v.checkEmpty(map.get("FIELDS_BASKETBALL")) == true ? "" : map.get("FIELDS_BASKETBALL").toString();
			if (!flag && str.length() > 0 && str.equals(basketball)) {
				flag = false;
			}else{
				flag = true;
			}

			str = v.checkEmpty(map.get("FIELDS_VOLLEYBALL")) == true ? "" : map.get("FIELDS_VOLLEYBALL").toString();
			if (!flag && str.length() > 0 && str.equals(volleyball)) {
				flag = false;
			}else{
				flag = true;
			}

			str = v.checkEmpty(map.get("FIELDS_GYM_AREA")) == true ? "" : map.get("FIELDS_GYM_AREA").toString();
			if (!flag && str.length() > 0 && str.equals(gameArea)) {
				flag = false;
			}else{
				flag = true;
			}

			str = v.checkEmpty(map.get("STADIUM_TOTAL")) == true ? "" : map.get("STADIUM_TOTAL").toString();
			if (!flag && str.length() > 0 && str.equals(sportHall)) {
				flag = false;
			}else{
				flag = true;
			}
		
			str = v.checkEmpty(map.get("STADIUM_AREA"))==true?"":map.get("STADIUM_AREA").toString();
			if(!flag && str.length() > 0 && str.equals(sportHallArea)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("POOL_TOTAL"))==true?"":map.get("POOL_TOTAL").toString();
			if(!flag && str.length() > 0 && str.equals(swimPool)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("POOL_AREA"))==true?"":map.get("POOL_AREA").toString();
			if(!flag && str.length() > 0 && str.equals(swimPoolArea)){
				flag = false;
			}else{
				flag = true;
			}
			
			str = v.checkEmpty(map.get("TEST_ROOM_TOTAL"))==true?"":map.get("TEST_ROOM_TOTAL").toString();
			if(!flag && str.length() > 0 && str.equals(testRoom)){
				flag = false;
			}else{
				flag = true;
			}
			str = v.checkEmpty(map.get("IS_EQUIP_QUALIFIED"))==true?"":map.get("IS_EQUIP_QUALIFIED").toString();
			if(!flag && str.length() > 0 && str.equals(storard)){
				flag = false;
			}else{
				flag = true;
			}

		}
		return flag;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getLength2() {
		return length2;
	}

	public void setLength2(String length2) {
		this.length2 = length2;
	}

	public String getLength3() {
		return length3;
	}

	public void setLength3(String length3) {
		this.length3 = length3;
	}

	public String getLength3t4() {
		return length3t4;
	}

	public void setLength3t4(String length3t4) {
		this.length3t4 = length3t4;
	}

	public String getLength4() {
		return length4;
	}

	public void setLength4(String length4) {
		this.length4 = length4;
	}

	public String getBasketball() {
		return basketball;
	}

	public void setBasketball(String basketball) {
		this.basketball = basketball;
	}

	public String getVolleyball() {
		return volleyball;
	}

	public void setVolleyball(String volleyball) {
		this.volleyball = volleyball;
	}

	public String getGameArea() {
		return gameArea;
	}

	public void setGameArea(String gameArea) {
		this.gameArea = gameArea;
	}

	public String getSportHall() {
		return sportHall;
	}

	public void setSportHall(String sportHall) {
		this.sportHall = sportHall;
	}

	public String getSportHallArea() {
		return sportHallArea;
	}

	public void setSportHallArea(String sportHallArea) {
		this.sportHallArea = sportHallArea;
	}

	public String getSwimPool() {
		return swimPool;
	}

	public void setSwimPool(String swimPool) {
		this.swimPool = swimPool;
	}

	public String getSwimPoolArea() {
		return swimPoolArea;
	}

	public void setSwimPoolArea(String swimPoolArea) {
		this.swimPoolArea = swimPoolArea;
	}

	public String getTestRoom() {
		return testRoom;
	}

	public void setTestRoom(String testRoom) {
		this.testRoom = testRoom;
	}

	public String isStorard() {
		return storard;
	}

	public void setStorard(String storard) {
		this.storard = storard;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getLast2() {
		return last2;
	}

	public void setLast2(String last2) {
		this.last2 = last2;
	}

	public String getLast3() {
		return last3;
	}

	public void setLast3(String last3) {
		this.last3 = last3;
	}

	public String getLast3t4() {
		return last3t4;
	}

	public void setLast3t4(String last3t4) {
		this.last3t4 = last3t4;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) {
		this.last4 = last4;
	}

	public String getLastBasket() {
		return lastBasket;
	}

	public void setLastBasket(String lastBasket) {
		this.lastBasket = lastBasket;
	}

	public String getLastVolley() {
		return lastVolley;
	}

	public void setLastVolley(String lastVolley) {
		this.lastVolley = lastVolley;
	}

	public String getLastGameArea() {
		return lastGameArea;
	}

	public void setLastGameArea(String lastGameArea) {
		this.lastGameArea = lastGameArea;
	}

	public String getLastSportHall() {
		return lastSportHall;
	}

	public void setLastSportHall(String lastSportHall) {
		this.lastSportHall = lastSportHall;
	}

	public String getLastSportArea() {
		return lastSportArea;
	}

	public void setLastSportArea(String lastSportArea) {
		this.lastSportArea = lastSportArea;
	}

	public String getLastSwimPool() {
		return lastSwimPool;
	}

	public void setLastSwimPool(String lastSwimPool) {
		this.lastSwimPool = lastSwimPool;
	}

	public String getLastSwimArea() {
		return lastSwimArea;
	}

	public void setLastSwimArea(String lastSwimArea) {
		this.lastSwimArea = lastSwimArea;
	}

	public String getLastTestRoom() {
		return lastTestRoom;
	}

	public void setLastTestRoom(String lastTestRoom) {
		this.lastTestRoom = lastTestRoom;
	}

}
