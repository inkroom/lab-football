package javadev.iip.action.art;

import java.util.HashMap;
import java.util.Map;
import javadev.core.Constants;
import javadev.iip.action.BaseAction;
import javadev.iip.service.ServiceManager;
import javadev.iip.util.validata.V;

/**
 * 功能描述：艺术课程和活动统计 @author： 朱明民 @version：1.0 create Date ：<创建日期，格式: 2016.8.30>
 */

public class ArtFirstAction extends BaseAction {
	private String actualClassNumber;
	// 在校生人数
	private String numOfStus;
	// 实际班级数量
	// 上一年在校人数
	private String lastnumOfStus;
	// 上一年实际班级数量
	private String lastactualClassNumber;
	// 音乐课时
	private String musicClass;
	private String musicClassLast;
	// 美术课时
	private String artClass;
	private String artClassLast;
	// 综合艺术课时
	private String integratedArtClass;
	private String integratedArtClassLast;
	// 地方/学校艺术课时
	private String localArtClass;
	private String localArtClassLast;
	// 是否开齐开足
	private String enoughClass;
	private String enoughClassLast;
	// 每周艺术课程自评得分
	private String srscroeClass;
	private String srscroeClassLast;
	// 学校每年开展艺术节场（次/年）
	private String screenings;
	private String screeningsLast;
	// 每周开展活动频次（次/周）
	private String frequency;
	private String frequencyLast;
	// 艺术社团数量
	private String artSocieties;
	private String artSocietiesLast;
	// 艺术活动学生参与面（%）
	private String participating;
	private String participatingLast;
	// 艺术活动自评得分
	private String srscroeActivity;
	private String srscroeActivityLast;

	private String errorInfo;
	private static V v = new V();
	private String token;
	private ServiceManager serviceManager;

	@SuppressWarnings({ "unchecked", "unused" })
	public String verification() throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> mapsport = new HashMap<String, Object>();
		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("O_ID").toString();
		mapsport = serviceManager.getOtherService().getSportData(o_id);
		map2 = serviceManager.getOtherService().getArtData(o_id);
		setToken(getSession().get("token").toString());

		if (map == null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}
		if (mapsport == null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}

		if (v.checkEmpty(mapsport.get("RATIO_TEA_STU")) || v.checkEmpty(mapsport.get("FIELD_TRACK_300_400M"))
				|| v.checkEmpty(mapsport.get("EXPENSE_ACTIVITY"))) {
			errorInfo = "体育表尚未填写完毕！";
			return "firstBack";
		}

		// 验证musicClass（音乐课时）
		if (V.isNumeric(musicClass) == false || v.judgeSize(-1, 100000000, musicClass) == false) {
			errorInfo = "请输入正确音乐课时";
			return "error";
		}
		// 验证artClass（美术课时）
		if (V.isNumeric(artClass) == false || v.judgeSize(-1, 100000000, artClass) == false) {
			errorInfo = "请输入正确美术课时";
			return "error";
		}
		// 验证integratedArtClass（综合艺术课程）
		if (V.isNumeric(integratedArtClass) == false || v.judgeSize(-1, 100000000, integratedArtClass) == false) {
			errorInfo = "请输入正确综合艺术课程";
			return "error";
		}
		// 验证localArtClass（地方/学校艺术课时）
		if (V.isNumeric(localArtClass) == false || v.judgeSize(-1, 100000000, localArtClass) == false) {
			errorInfo = "请输入正确地方/学校艺术课时";
			return "error";
		}
		// 验证srscroeClass（每周艺术课程自评得分）
		if (V.isNumeric(srscroeClass) == false || v.judgeSize(-1, 31, srscroeClass) == false) {
			errorInfo = "请输入正确每周艺术课程自评得分";
			return "error";
		}
		// 验证screenings（学校每年开展艺术节场（次/年））
		if (V.isNumeric(screenings) == false || v.judgeSize(-1, 100000000, screenings) == false) {
			errorInfo = "请输入正确学校每年开展艺术节场（次/年）";
			return "error";
		}
		// 验证frequency（每周开展活动频次（次/周））
		if (V.isDoubleOrNum(frequency) == false|| v.judgeSize(-1, 100000000, frequency) == false) {
			errorInfo = "请输入正确每周开展活动频次（次/周）";
			return "error";
		}
		// 验证artSocieties（艺术社团数量）
		if (V.isNumeric(artSocieties) == false || v.judgeSize(-1, 100000000, artSocieties) == false) {
			errorInfo = "请输入正确艺术社团数量";
			return "error";
		}
		// 验证participating（艺术活动学生参与面（%））
		if (V.isNumeric(participating) == false || v.judgeSize(-1, 101, participating) == false) {
			errorInfo = "请输入正确艺术活动学生参与面（%）（100%以内包括100%）";
			return "error";
		}
		// 验证srscroeActivity（艺术活动自评得分）
		if (V.isNumeric(srscroeActivity) == false || v.judgeSize(-1, 21, srscroeActivity) == false) {
			errorInfo = "请输入正确艺术活动自评得分";
			return "error";
		}

		/**
		 * 将前端信息存入Map 传入service类
		 */

		map.put("DATA1", musicClass);
		map.put("DATA2", artClass);
		map.put("DATA3", integratedArtClass);
		map.put("DATA4", localArtClass);
		map.put("DATA5", enoughClass);
		map.put("DATA6", srscroeClass);
		map.put("DATA7", screenings);
		map.put("DATA8", frequency);
		map.put("DATA9", artSocieties);
		map.put("DATA10", participating);
		map.put("DATA11", srscroeActivity);
		map.put("DATAid", o_id);
		map.put("C_ID", getSession().get(Constants.LOGIN_USER_C_ID).toString());

		if (isChangedData()) {
			serviceManager.getArtFirstService().insertArtFirst(map);
			return "ArtFirstForm";
		}
		return "ArtFirstForm";

	}

	/*
	 * 将service类的map取出 传入到前端输入框
	 */
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		setToken(getSession().get("token").toString());
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map2Last = new HashMap<String, Object>();
		Map<String, Object> map3Last = new HashMap<String, Object>();

		Map<String, Object> user = (Map<String, Object>) getSession().get(Constants.LOGIN_USER);
		String o_id = user.get("O_ID").toString();
		map2 = serviceManager.getOtherService().getArtData(o_id);
		map3 = serviceManager.getOtherService().getSportData(o_id);
		map2Last = serviceManager.getOtherService().getArtLastYearData(o_id);
		map3Last = serviceManager.getOtherService().getSportLastYearData(o_id);

		if (map2 == null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}
		if (map3 == null) {
			errorInfo = "当前系统未开放";
			return "errorTime";
		}

		if (v.checkEmpty(map3.get("RATIO_TEA_STU")) || v.checkEmpty(map3.get("FIELD_TRACK_300_400M"))
				|| v.checkEmpty(map3.get("EXPENSE_ACTIVITY"))) {
			errorInfo = "体育表尚未填写完毕！";
			return "firstBackView";
		}

		if (map2 != null) {
			if (!v.checkEmpty(map3.get("stu_total"))) {
				numOfStus = map3.get("stu_total").toString();
			}
			if (!v.checkEmpty(map3.get("class_total"))) {
				actualClassNumber = map3.get("class_total").toString();
			}
			if (!v.checkEmpty(map2.get("MUS_HOUR"))) {
				musicClass = map2.get("MUS_HOUR").toString();
			}
			if (!v.checkEmpty(map2.get("PAINT_HOUR"))) {
				artClass = map2.get("PAINT_HOUR").toString();
			}
			if (!v.checkEmpty(map2.get("ART_COMPREHENSIVE_HOUR"))) {
				integratedArtClass = map2.get("ART_COMPREHENSIVE_HOUR").toString();
			}
			if (!v.checkEmpty(map2.get("ART_HOUR"))) {
				localArtClass = map2.get("ART_HOUR").toString();
			}
			if (!v.checkEmpty(map2.get("IS_COMPLETE"))) {
				enoughClass = map2.get("IS_COMPLETE").toString();
			}
			if (!v.checkEmpty(map2.get("SELF_REMARK_ART_COURSE"))) {
				srscroeClass = map2.get("SELF_REMARK_ART_COURSE").toString();
			}
			if (!v.checkEmpty(map2.get("ART_FESTIVAL_ANNUAL"))) {
				screenings = map2.get("ART_FESTIVAL_ANNUAL").toString();
			}
			if (!v.checkEmpty(map2.get("ART_ACTIVITIES_WEEKLY"))) {
				frequency = map2.get("ART_ACTIVITIES_WEEKLY").toString();
			}
			if (!v.checkEmpty(map2.get("CLUB_TOTAL"))) {
				artSocieties = map2.get("CLUB_TOTAL").toString();
			}
			if (!v.checkEmpty(map2.get("ACTIVITIES_PARTICIPATE_RATIO"))) {
				participating = map2.get("ACTIVITIES_PARTICIPATE_RATIO").toString();
			}
			if (!v.checkEmpty(map2.get("SELF_REMARK_ART_ACTIVITY"))) {
				srscroeActivity = map2.get("SELF_REMARK_ART_ACTIVITY").toString();
			}
		}

		if (map2Last != null) {
			lastnumOfStus = map3Last.get("stu_total").toString();
			lastactualClassNumber = map3Last.get("class_total").toString();
			musicClassLast = map2Last.get("MUS_HOUR").toString();
			artClassLast = map2Last.get("PAINT_HOUR").toString();
			integratedArtClassLast = map2Last.get("ART_COMPREHENSIVE_HOUR").toString();
			localArtClassLast = map2Last.get("ART_HOUR").toString();
			enoughClassLast = map2Last.get("IS_COMPLETE").toString();
			srscroeClassLast = map2Last.get("SELF_REMARK_ART_COURSE").toString();
			screeningsLast = map2Last.get("ART_FESTIVAL_ANNUAL").toString();
			frequencyLast = map2Last.get("ART_ACTIVITIES_WEEKLY").toString();
			artSocietiesLast = map2Last.get("CLUB_TOTAL").toString();
			srscroeActivityLast = map2Last.get("ACTIVITIES_PARTICIPATE_RATIO").toString();
			participatingLast = map2Last.get("SELF_REMARK_ART_ACTIVITY").toString();

		}

		return "success";
	}

	public boolean isChangedData() {

		boolean flag = true;
		@SuppressWarnings("unchecked")
		String o_id = ((Map<String, Object>) getSession().get(Constants.LOGIN_USER)).get("O_ID").toString();
		Map<String, Object> map = serviceManager.getOtherService().getArtData(o_id);

		if (map != null) {

			String str = v.checkEmpty(map.get("MUS_HOUR")) == true ? "" : map.get("MUS_HOUR").toString();
			if (str.length() > 0 && str.equals(musicClass)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("PAINT_HOUR")) == true ? "" : map.get("PAINT_HOUR").toString();
			if (!flag && str.length() > 0 && str.equals(artClass)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("ART_COMPREHENSIVE_HOUR")) == true ? ""
					: map.get("ART_COMPREHENSIVE_HOUR").toString();
			if (!flag && str.length() > 0 && str.equals(integratedArtClass)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("ART_HOUR")) == true ? "" : map.get("ART_HOUR").toString();
			if (!flag && str.length() > 0 && str.equals(localArtClass)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("IS_COMPLETE")) == true ? "" : map.get("IS_COMPLETE").toString();
			if (!flag && str.length() > 0 && str.equals(enoughClass)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("SELF_REMARK_ART_COURSE")) == true ? ""
					: map.get("SELF_REMARK_ART_COURSE").toString();
			if (!flag && str.length() > 0 && str.equals(srscroeClass)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("ART_FESTIVAL_ANNUAL")) == true ? "" : map.get("ART_FESTIVAL_ANNUAL").toString();
			if (!flag && str.length() > 0 && str.equals(screenings)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("ART_ACTIVITIES_WEEKLY")) == true ? ""
					: map.get("ART_ACTIVITIES_WEEKLY").toString();
			if (!flag && str.length() > 0 && str.equals(frequency)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("CLUB_TOTAL")) == true ? "" : map.get("CLUB_TOTAL").toString();
			if (!flag && str.length() > 0 && str.equals(artSocieties)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("ACTIVITIES_PARTICIPATE_RATIO")) == true ? ""
					: map.get("ACTIVITIES_PARTICIPATE_RATIO").toString();
			if (!flag && str.length() > 0 && str.equals(participating)) {
				flag = false;
			} else {
				flag = true;
			}
			str = v.checkEmpty(map.get("SELF_REMARK_ART_ACTIVITY")) == true ? ""
					: map.get("SELF_REMARK_ART_ACTIVITY").toString();
			if (!flag && str.length() > 0 && str.equals(srscroeActivity)) {
				flag = false;
			} else {
				flag = true;
			}

		}
		return flag;
	}

	public String getMusicClass() {
		return musicClass;
	}

	public void setMusicClass(String musicClass) {
		this.musicClass = musicClass;
	}

	public String getArtClass() {
		return artClass;
	}

	public void setArtClass(String artClass) {
		this.artClass = artClass;
	}

	public String getIntegratedArtClass() {
		return integratedArtClass;
	}

	public void setIntegratedArtClass(String integratedArtClass) {
		this.integratedArtClass = integratedArtClass;
	}

	public String getLocalArtClass() {
		return localArtClass;
	}

	public void setLocalArtClass(String localArtClass) {
		this.localArtClass = localArtClass;
	}

	public String getEnoughClass() {
		return enoughClass;
	}

	public void setEnoughClass(String enoughClass) {
		this.enoughClass = enoughClass;
	}

	public String getSrscroeClass() {
		return srscroeClass;
	}

	public void setSrscroeClass(String srscroeClass) {
		this.srscroeClass = srscroeClass;
	}

	public String getScreenings() {
		return screenings;
	}

	public void setScreenings(String screenings) {
		this.screenings = screenings;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getArtSocieties() {
		return artSocieties;
	}

	public void setArtSocieties(String artSocieties) {
		this.artSocieties = artSocieties;
	}

	public String getParticipating() {
		return participating;
	}

	public void setParticipating(String participating) {
		this.participating = participating;
	}

	public String getSrscroeActivity() {
		return srscroeActivity;
	}

	public void setSrscroeActivity(String srscroeActivity) {
		this.srscroeActivity = srscroeActivity;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public String getMusicClassLast() {
		return musicClassLast;
	}

	public void setMusicClassLast(String musicClassLast) {
		this.musicClassLast = musicClassLast;
	}

	public String getArtClassLast() {
		return artClassLast;
	}

	public void setArtClassLast(String artClassLast) {
		this.artClassLast = artClassLast;
	}

	public String getIntegratedArtClassLast() {
		return integratedArtClassLast;
	}

	public void setIntegratedArtClassLast(String integratedArtClassLast) {
		this.integratedArtClassLast = integratedArtClassLast;
	}

	public String getLocalArtClassLast() {
		return localArtClassLast;
	}

	public void setLocalArtClassLast(String localArtClassLast) {
		this.localArtClassLast = localArtClassLast;
	}

	public String getEnoughClassLast() {
		return enoughClassLast;
	}

	public void setEnoughClassLast(String enoughClassLast) {
		this.enoughClassLast = enoughClassLast;
	}

	public String getSrscroeClassLast() {
		return srscroeClassLast;
	}

	public void setSrscroeClassLast(String srscroeClassLast) {
		this.srscroeClassLast = srscroeClassLast;
	}

	public String getScreeningsLast() {
		return screeningsLast;
	}

	public void setScreeningsLast(String screeningsLast) {
		this.screeningsLast = screeningsLast;
	}

	public String getFrequencyLast() {
		return frequencyLast;
	}

	public void setFrequencyLast(String frequencyLast) {
		this.frequencyLast = frequencyLast;
	}

	public String getArtSocietiesLast() {
		return artSocietiesLast;
	}

	public void setArtSocietiesLast(String artSocietiesLast) {
		this.artSocietiesLast = artSocietiesLast;
	}

	public String getParticipatingLast() {
		return participatingLast;
	}

	public void setParticipatingLast(String participatingLast) {
		this.participatingLast = participatingLast;
	}

	public String getSrscroeActivityLast() {
		return srscroeActivityLast;
	}

	public void setSrscroeActivityLast(String srscroeActivityLast) {
		this.srscroeActivityLast = srscroeActivityLast;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

	public String getActualClassNumber() {
		return actualClassNumber;
	}

	public void setActualClassNumber(String actualClassNumber) {
		this.actualClassNumber = actualClassNumber;
	}

	public String getNumOfStus() {
		return numOfStus;
	}

	public void setNumOfStus(String numOfStus) {
		this.numOfStus = numOfStus;
	}

	public String getLastnumOfStus() {
		return lastnumOfStus;
	}

	public void setLastnumOfStus(String lastnumOfStus) {
		this.lastnumOfStus = lastnumOfStus;
	}

	public String getLastactualClassNumber() {
		return lastactualClassNumber;
	}

	public void setLastactualClassNumber(String lastactualClassNumber) {
		this.lastactualClassNumber = lastactualClassNumber;
	}

	
}
