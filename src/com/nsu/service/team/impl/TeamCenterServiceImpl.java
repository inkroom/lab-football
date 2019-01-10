package com.nsu.service.team.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsu.util.InfoProUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nsu.bean.team.TeamBasicInfoBean;
import com.nsu.bean.team.TeamCenterDO;
import com.nsu.bean.team.TeamCoachBean;
import com.nsu.staticvar.TeamVar;
import com.nsu.dao.team.TeamCenterDao;
import com.nsu.dao.team.TeamReviewPlayerDao;
import com.nsu.dao.team.TeamSendVerificationCodeDao;
import com.nsu.service.BaseService;
import com.nsu.service.team.ITeamCenterService;
import com.nsu.util.V;

import net.sf.json.JSONArray;

/**
 * 球队中心service接口实现类
 * 
 * @author ljl
 * @version 1.0
 * @createDate 2017-04-12 14:34:40
 */
@Service("teamCenterService")
public class TeamCenterServiceImpl extends BaseService implements ITeamCenterService {

	@Autowired
	TeamCenterDao teamCenterDao;
	@Autowired
	TeamSendVerificationCodeDao teamSendVerificationCodeDao;
	@Autowired
	TeamReviewPlayerDao teamReviewPlayerDao;

	@Override
	public String updateTeamBasicInfo(TeamBasicInfoBean teamBasicInfo) {

		// 验证数据
		String result = checkData(teamBasicInfo);

		// 存储数据
		if (V.checkEmpty(result) == true) {
			result = updateTeamBasicData(teamBasicInfo);
		}
		return result;
	}

	/**
	 * 查询团队基本信息
	 */
	@Override
	public Map<String, Object> findFootballTeamInfo(String TEAM_ID) {
		// 操作数据库
		Map<String, Object> map = teamCenterDao.findFootballTeamDao(TEAM_ID);
		log.info("89978***-*-*: " + map);
		if (map != null && map.isEmpty() == false) {
			// 计算积分，并存于map
			String TS_SCHOOL_VICTOR = V.checkEmpty(map.get("TS_SCHOOL_VICTOR")) == true ? "0"
					: map.get("TS_SCHOOL_VICTOR").toString();// 得到校级获胜球数
			String TS_COUNTRY_VICTOR = V.checkEmpty(map.get("TS_COUNTRY_VICTOR")) == true ? "0"
					: map.get("TS_COUNTRY_VICTOR").toString();// 得到县级获胜球数
			String TS_CITY_VICTOR = V.checkEmpty(map.get("TS_CITY_VICTOR")) == true ? "0"
					: map.get("TS_CITY_VICTOR").toString();// 得到市级获胜球数
			String TS_PROVINCE_VICTOR = V.checkEmpty(map.get("TS_PROVINCE_VICTOR")) == true ? "0"
					: map.get("TS_PROVINCE_VICTOR").toString();// 得到省级获胜球数
			String TS_OTHER_WIN = V.checkEmpty(map.get("TS_OTHER_WIN")) == true ? "0"
					: map.get("TS_OTHER_WIN").toString();// 得到不限获胜球数
			String TS_SCHOOL_LOSE = V.checkEmpty(map.get("TS_SCHOOL_LOSE")) == true ? "0"
					: map.get("TS_SCHOOL_LOSE").toString();// 得到校级获胜球数
			String TS_COUNTRY_LOSE = V.checkEmpty(map.get("TS_COUNTRY_LOSE")) == true ? "0"
					: map.get("TS_COUNTRY_LOSE").toString();// 得到县级获胜球数
			String TS_CITY_LOSE = V.checkEmpty(map.get("TS_CITY_LOSE")) == true ? "0"
					: map.get("TS_CITY_LOSE").toString();// 得到市级获胜球数
			String TS_PROVINCE_LOSE = V.checkEmpty(map.get("TS_PROVINCE_LOSE")) == true ? "0"
					: map.get("TS_PROVINCE_LOSE").toString();// 得到省级获胜球数
			String TS_OTHER_LOSS = V.checkEmpty(map.get("TS_OTHER_LOSS")) == true ? "0"
					: map.get("TS_OTHER_LOSS").toString();// 得到不限输球数
			String TEAM_SCORE = "";
			String winer = "";// 获胜场数
			String loser = "";// 失败场数
			winer = String.valueOf(Integer.valueOf(TS_SCHOOL_VICTOR) + Integer.valueOf(TS_COUNTRY_VICTOR)
					+ Integer.valueOf(TS_CITY_VICTOR) + Integer.valueOf(TS_PROVINCE_VICTOR)
					+ Integer.valueOf(TS_OTHER_WIN));
			loser = String.valueOf(
					Integer.valueOf(TS_SCHOOL_LOSE) + Integer.valueOf(TS_COUNTRY_LOSE) + Integer.valueOf(TS_CITY_LOSE)
							+ Integer.valueOf(TS_PROVINCE_LOSE) + Integer.valueOf(TS_OTHER_LOSS));
			TEAM_SCORE = String.valueOf(Integer.valueOf(winer) * 3 + Integer.valueOf(loser));
			map.put("TEAM_SCORE", TEAM_SCORE);

			// 团队比赛总场数
//			int MATCHTIMES = 0;
//			if(MATCHTIMES!=0){
//			try {
//				MATCHTIMES = teamCenterDao.findMatchTeamNUM(TEAM_ID);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			}
			map.put("MATCHTIMES", Integer.valueOf(winer)+Integer.valueOf(loser));
			
//			//团队比赛获胜数
//			int win = 0;
//			if(win!=0){
//            try {
//				 win = teamCenterDao.findMatchTeamWIN(TEAM_ID);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			}
//            log.info("查看获胜场数！！！！！"+win);
			
			// 计算平均胜率
            float AVGWIN = 0;
            if(Integer.valueOf(winer)+Integer.valueOf(loser)!=0){
			AVGWIN =  Float.valueOf(winer)*100/(Float.valueOf(winer)+ Float.valueOf(loser));
            }
            float b = (float)(Math.round(AVGWIN*100))/100;//小数点后两位
            String a = V.subZeroAndDot(String.valueOf(b));
			map.put("AVGWIN", a);

			if (V.checkEmpty(map.get("TEAM_PHONE_NUM")) == false) {
				String phone = InfoProUtil.xorInfo(map.get("TEAM_PHONE_NUM").toString());
				map.remove("TEAM_PHONE_NUM");
				map.put("TEAM_PHONE_NUM", phone);
			}
			if (V.checkEmpty(map.get("TEAM_TYPE")) == false) {
				String teamType = map.get("TEAM_TYPE").toString();
				map.remove("TEAM_TYPE");
				if (teamType.equals("1")) {
					map.put("TEAM_TYPE", "小学");
				} else if (teamType.equals("2")) {
					map.put("TEAM_TYPE", "初中");
				} else if (teamType.equals("3")) {
					map.put("TEAM_TYPE", "高中");
				} else {
					map.put("TEAM_TYPE", "混合");
				}
			}

			return map;
		} else {
			map = new HashMap<String, Object>();
			map.put("MATCHTIMES", 0);
			map.put("AVGWIN", 0);
			return map;
		}

	}

	/**
	 * 查询球队所有球员信息
	 */
	@Override
	public List<TeamCenterDO> findTeamPlayerInfo(String TEAM_ID) {
		try {
			List<TeamCenterDO> playeList = teamCenterDao.findTeamplayerDao(TEAM_ID);
			//计算球员积分
			if(playeList!=null && playeList.size()>0){
				for(int i=0; i<playeList.size(); i++){
					String aid = playeList.get(i).getA_ID();
					
					if(V.checkEmpty(aid)){
						playeList.get(i).setP_GRADE_TABLE_ID("0");
					}else{
						
						try{
							Map<String, Object> teamMap = teamReviewPlayerDao.getPlayerInfo(aid);
							if(teamMap!=null && teamMap.size()>0){
								int num = TeamVar.calculaPlayerScore(teamMap);
								playeList.get(i).setP_GRADE_TABLE_ID(num+"");
							}
						}catch(Exception e){
							e.printStackTrace();
							playeList.get(i).setP_GRADE_TABLE_ID("0");
						}
					}
				}
			}
			
			return playeList;
		} catch (Exception e) {

			log.error(e.getMessage());
			return null;
		}

	}

	/**
	 * 查询所有教师名字
	 */
	@Override
	public List<TeamCenterDO> findTeamCoachName(String TEAM_ID) {
		List<TeamCenterDO> list = teamCenterDao.findTeamCoachDao(TEAM_ID);
		// for (String string : list) {
		// log.info("*********"+string);
		// }
		log.info("查了几个教练" + list.size());
		return list;
	}

	/**
	 * 更新球队基本信息
	 * 
	 * @author ljl
	 * @createDate 2017-04-14 19:29:37
	 * @param teamBasicInfo
	 * @return
	 */
	public String updateTeamBasicData(TeamBasicInfoBean teamBasicInfo) {
		String result = "";
		int num = 0;
		try {
			// 获取球队球员数目
			teamBasicInfo.setTeamPlayerNum(teamCenterDao.findTeamPlayersNum(teamBasicInfo.getTeamID()));
			List<TeamCoachBean> coachList = teamCenterDao.findTeamCoachsInfo(teamBasicInfo.getTeamID());

			// 将球队教练员转换为json数组
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if (coachList != null && coachList.size() > 0) {
				for (int i = 0; i < coachList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("cName", coachList.get(i).getCoachName());
					map.put("cID", coachList.get(i).getCoachID());
					list.add(map);
				}
				teamBasicInfo.setTeamCoach(JSONArray.fromObject(list).toString());
				// teamBasicInfo.setTeamCoach(JSONArray.fromObject(coachList).toString());
			}

			// 调用dao 完善或存储数据
			// 向teamNotice表插入数据，用于记录球队的修改记录
			String phone = InfoProUtil.xorInfo(teamBasicInfo.getTeamLeaderPhone());
			teamBasicInfo.setTeamLeaderPhone(phone);
			String account = InfoProUtil.xorInfo(teamBasicInfo.getAccount());
			teamBasicInfo.setAccount(account);
			log.info("处理后的存储数据：" + teamBasicInfo);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("A_NAME", teamBasicInfo.getTeamName());
			map.put("A_OPER_BY", teamBasicInfo.getAccount());
			map.put("A_ID", teamBasicInfo.getaID());
			map.put("A_EMAIL", teamBasicInfo.getEmail());
			num = teamCenterDao.updateTeamANameInAccount(map);

			if (num == 1) {
				num = teamCenterDao.insertIntoTeamNoticeTble(teamBasicInfo);
				if (num == 1) {
					num = teamCenterDao.updateTeamBasicInfo(teamBasicInfo);
					if (num == 1) {
						result = "";
					} else {
						result = "修改失败";
						log.info("更新team失败");
					}
				} else {
					result = "修改失败";
					log.info("插入teamnotice失败");
				}
			} else {
				result = "修改失败";
				log.info("更account 的aneam 失败");
			}

		} catch (Exception e) {

			log.error(e.getMessage());
			log.info("----更新数据失败-----");
			result = "修改失败";
		}
		return result;
	}

	/**
	 * 验证常规数据数据
	 * 
	 * @author ljl
	 * @createDate 2017-04-12 16:16:40
	 * @return
	 */
	public String checkData(TeamBasicInfoBean teamBasicInfo) {

		// 球队名,最长30
		if (V.checkCHSAndENAndNumber(teamBasicInfo.getTeamName(), 30) == false) {
			return "球队名仅支持中英数字(最长30)";
		}

		// 励志标语
		if (V.checkCHSAndENOrPeriodOrComma(teamBasicInfo.getTeamInspirationalSlogan(), 60) == false) {
			return "励志标语支持中英数字和普通标点符号(最长60)";
		}

		// 领队名字
		if (V.checkPersonName(teamBasicInfo.getTeamLeader(), 20) == false) {
			return "名字仅支持中英文(最长20,点请用·)";
		}

		// 领队手机号
		if (V.checkPhone(teamBasicInfo.getTeamLeaderPhone()) == false) {
			return "请输入正确的手机号码（11位）";
		}

		return null;
	}

	@Override
	public TeamBasicInfoBean findTeamBasicInfo(String teamID) {
		try {
			TeamBasicInfoBean teamBasicInfo = teamCenterDao.findTeamBasicInfo(teamID);
			log.info(teamID);
			String email = teamCenterDao.findTeamEmail(teamID);
			teamBasicInfo.setEmail(email);
			return teamBasicInfo;
		} catch (Exception e) {

			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public String updateTeamPhotos(String teamID, String filePath, String type) {
		if (type.equals("teamLogo") == true) {
			// 队徽
			int num = teamCenterDao.updateTeamLogo(teamID, filePath);
			if (num == 1) {
				return null;
			} else {
				return "图片上传失败";
			}
		} else {
			// 队旗
			int num = teamCenterDao.updateTeamFlag(teamID, filePath);
			if (num == 1) {
				return null;
			} else {
				return "图片上传失败";
			}
		}
	}

	/**
	 * @Description: 查询球队教练员名字
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	@Override
	public List<TeamCenterDO> findTeamCoachNameCheck(String TEAM_ID) {
		List<TeamCenterDO> list = teamCenterDao.findTeamCoachCheckInfo(TEAM_ID);
		return list;
	}

	/**
	 * @Description: 查询球队所有队员
	 * @author 侯润达
	 * @create_date 2017年4月12日 下午4:52:15
	 * @return
	 */
	@Override
	public List<TeamCenterDO> findTeamplayerInfoCheck(String TEAM_ID) {
		List<TeamCenterDO> list = teamCenterDao.findTeamplayerCheckDao(TEAM_ID);
		return list;
	}

	@Override
	public String updateTeamSong(String teamID, Object path) {
		String result = "";
		try {
			int num = teamCenterDao.updateTeamSong(teamID, path);
			if (num == 1) {
				result = "上传成功";
			} else {
				result = "上传失败";
			}
		} catch (Exception e) {

			log.error(e.getMessage());
			result = "上传失败";
		}
		return result;
	}

	/**
	 * @Description: 查询团队机构信息
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:38:09
	 * @return
	 */
	@Override
	public TeamCenterDO findTeamOrgDaoService(String ORG_ID) {
		try {
			return teamCenterDao.findTeamOrgDao(ORG_ID);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return null;
	}

	/**
	 * @Description: 查询所有机构信息
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午9:38:09
	 * @return
	 */
	@Override
	public TeamCenterDO findALLOrgDaoService(String ORG_ID) {

		try {
			return teamCenterDao.findALLOrgDao(ORG_ID);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * @Description: 修改机构申请机构id以及申请状态
	 * @author 侯润达
	 * @create_date 2017年4月27日 上午11:33:14
	 * @return
	 */
	@Override
	public String updateOrgStatusService(String ORG_ID, String TEAM_ID) {
		int num = teamCenterDao.updateOrgStatus(ORG_ID, TEAM_ID);
		if (num > 0) {
			return "success";
		}
		return "error";
	}

	@Override
	public String updateTeamEmail(String aid, String email, String code, String account) {
		
		if (V.checkEmpty(email) == true) {
			return "请填写邮箱";
		}
		if (V.checkEmail(email, 100) == false) {
			return "请填写正确的邮箱";
		}
		try {
			// 更新邮箱
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("A_EMAIL", email);
			map.put("A_OPER_BY", account);
			map.put("A_ID", aid);
			int num = teamCenterDao.updateTeamAEmailInAccount(map);
			if (num > 0) {
				return "";
			} else {
				return "操作失败，请重试";
			}
		} catch (Exception e) {

			log.error(e.getMessage());
			return "服务忙，请重试";
		}
	}

	@Override
	public String findEamilVerificationCode(String aid) {
		try {
			return teamSendVerificationCodeDao.findEmailVerificationCode(aid);
		} catch (Exception e) {

			log.error(e.getMessage());
			return null;
		}

	}

	/**
	 * @Description: 根据P_ID查询A_ID
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	@Override
	public Long findAIDBYPIDService(String P_ID) {
		try {
			return teamCenterDao.findAIDBYPID(P_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	/**
	 * @Description: 根据COACH_ID查询A_ID
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	@Override
	public Long findAIDBYCOACHIDService(String COACH_ID) {
		try {
			return teamCenterDao.findAIDBYCOACHID(COACH_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	/**
	 * @Description: 根据TEAM_ID查询A_ID和TEAM_NAME
	 * @author 侯润达
	 * @create_date 2017年6月2日 上午10:25:57
	 * @return
	 */
	@Override
	public TeamCenterDO findAIDBYTEAMService(String TEAM_ID) {
		try {
			return teamCenterDao.findAIDBYTEAM(TEAM_ID);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Long findAIDBYORGService(String ORG) {
		try {
			return teamCenterDao.findAIDBYORG(ORG);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0L;
	}
	
	
 

}
