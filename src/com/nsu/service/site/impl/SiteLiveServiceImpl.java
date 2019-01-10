package com.nsu.service.site.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsu.controller.BaseController;
import com.nsu.dao.site.SiteJudementDao;
import com.nsu.dao.site.SiteLiveDao;
import com.nsu.service.site.SiteLiveService;

@Service
public class SiteLiveServiceImpl implements SiteLiveService {

	@Autowired
	SiteLiveDao siteLive;
	@Autowired
	SiteJudementDao judement;

	protected static final Log log = LogFactory.getLog(BaseController.class);

	@Override
	public void insertLiveInfo(int Hteam_score, int Vteam_score, String nTime, String LiveText, String LiveImg,
			int infoType, long r_id) throws  Exception{
		siteLive.insertLiveInfo(Hteam_score, Vteam_score, nTime, LiveText, LiveImg, infoType, r_id);
	}

	@Override
	public Map<String, Object> selectInfoBeforeGame(int A_ID) throws  Exception{
		Map<String, Object> list = siteLive.selectAllBeforeGame(A_ID);
		return list;
	}

	@Override
	public long selectRID(int A_ID) throws  Exception{
		log.info("A_ID的值是  =====  "+A_ID);
		Long R_ID = siteLive.selectRID(A_ID);
		return R_ID;
	}

	@Override
	public Map<String, Integer> selectSocreNow(int A_ID) throws  Exception{
		Map<String, Integer> score = new HashMap<String,Integer>();
		if (siteLive.selectScores(A_ID)!=null) {
			score =  siteLive.selectScores(A_ID);
		}else{
			score.put("RL_H_T_SCORE", 0);
			score.put("RL_V_T_SCORE", 0);
		}
		return score;
	}

	@Override
	public String selectTeamNames(int tId)throws  Exception {
		return siteLive.selectTeamName(tId);
	}

	@Override
	public void endGame(long RID)throws  Exception {
		siteLive.endGame(RID);
	}

	@Override
	public void endTime(String time,String win, long RID)throws  Exception {
		siteLive.endTime(time, win,RID);
	}

	@Override
	public int selectStatus(long RID) throws  Exception{
		int status = siteLive.selectStatus(RID);
		if (String.valueOf(status) == null || String.valueOf(status).equals("")) {
			status = 1;
		}
		return status;
	}

	@Override
	public void setStatus(String status,long R_ID) {
		siteLive.setStatus(status,R_ID);
	}

	@Override
	public void setA_Status(long A_ID) {
		siteLive.setA_Status(A_ID);
	}

	@Override
	public void updateDetailStatus(long RID, int status)throws  Exception {
		siteLive.updateDetailsStauts(RID, status);
	}

	@Override
	public void updateScore(int winTeamNum, long R_ID, int A_ID)throws  Exception {

		// 先得到比赛级别
		int level = judement.selectLevel(R_ID);
		// 得到主客队id
		int HTeamId = (int) judement.selectInfo(A_ID).get("R_H_TEAM_ID");
		int VTeamID = (int) judement.selectInfo(A_ID).get("R_V_TEAM_ID");

		// 得到主队教练
		List<Long> Tcoachs = judement.selectCoachs(HTeamId);
		// 得到客队教练
		List<Long> VCoachs = judement.selectCoachs(VTeamID);

		List<Long> players = judement.selectPlayers(R_ID, HTeamId);
		List<Long> VPlayers = judement.selectPlayers(R_ID, VTeamID);

		switch (level) {
		case 5:
			if (winTeamNum == 0) {
				judement.updateTeamInfo(HTeamId, "TS_OTHER_WIN");
				judement.updateTeamInfo(VTeamID, "TS_OTHER_LOSS");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_OTHER_WIN");
					}
				}
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_OTHER_LOSS");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < players.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_OTHER_WIN");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_OTHER_LOSS");
					}
				}

			} else if (winTeamNum == 1) {
				judement.updateTeamInfo(HTeamId, "TS_OTHER_LOSS");
				judement.updateTeamInfo(VTeamID, "TS_OTHER_WIN");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_OTHER_LOSS");
					}
				}
				if (!VCoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_OTHER_WIN");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_OTHER_LOSS");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_OTHER_WIN");
					}
				}
			}
			break;
		case 4:
			if (winTeamNum == 0) {

				judement.updateTeamInfo(HTeamId, "TS_SCHOOL_VICTOR");
				judement.updateTeamInfo(VTeamID, "TS_SCHOOL_LOSE");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_SCHOOL_WIN");
					}
				}
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_SCHOOL_LOSS");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < players.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_SCHOOL_WIN");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_SCHOOL_LOSE");
					}
				}

			} else if (winTeamNum == 1) {
				judement.updateTeamInfo(HTeamId, "TS_SCHOOL_LOSE");
				judement.updateTeamInfo(VTeamID, "TS_SCHOOL_VICTOR");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_SCHOOL_LOSS");
					}
				}
				if (!VCoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_SCHOOL_WIN");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_SCHOOL_LOSE");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_SCHOOL_WIN");
					}
				}

			}
			break;
		case 3:
			if (winTeamNum == 0) {
				judement.updateTeamInfo(HTeamId, "TS_COUNTRY_VICTOR");
				judement.updateTeamInfo(VTeamID, "TS_COUNTRY_LOSE");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_COUNTRY_WIN");
					}
				}
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_COUNTRY_LOSE");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < players.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_COUNTY_WIN");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_COUNTY_LOSE");
					}
				}
			} else if (winTeamNum == 1) {
				judement.updateTeamInfo(HTeamId, "TS_COUNTRY_LOSE");
				judement.updateTeamInfo(VTeamID, "TS_COUNTRY_VICTOR");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_COUNTRY_LOSE");
					}
				}
				if (!VCoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_COUNTRY_WIN");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_COUNTY_LOSE");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_COUNTY_WIN");
					}
				}
			}

			break;
		case 2:
			if (winTeamNum == 0) {
				judement.updateTeamInfo(HTeamId, "TS_CITY_VICTOR");
				judement.updateTeamInfo(VTeamID, "TS_CITY_LOSE");

				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_CITY_WIN");
					}
				}
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_CITY_LOSE");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < players.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_CITY_WIN");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_CITY_LOSE");
					}
				}
			} else if (winTeamNum == 1) {
				judement.updateTeamInfo(HTeamId, "TS_CITY_LOSE");
				judement.updateTeamInfo(VTeamID, "TS_CITY_VICTOR");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_CITY_LOSE");
					}
				}
				if (!VCoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_CITY_WIN");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_CITY_LOSE");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_CITY_WIN");
					}
				}
			}

			break;
		case 1:
			if (winTeamNum == 0) {
				judement.updateTeamInfo(HTeamId, "TS_PROVINCE_VICTOR");
				judement.updateTeamInfo(VTeamID, "TS_PROVINCE_LOSE");

				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_PRCVINCE_WIN");
					}
				}
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_PRCVINCE_LOSE");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < players.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_PROVINE_WIN");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_PROVINE_LOSE");
					}
				}
			} else if (winTeamNum == 1) {
				judement.updateTeamInfo(HTeamId, "TS_PROVINCE_LOSE");
				judement.updateTeamInfo(VTeamID, "TS_PROVINCE_VICTOR");
				if (!Tcoachs.isEmpty()) {
					for (int i = 0; i < Tcoachs.size(); i++) {
						judement.updateCoachInfo(Tcoachs.get(i), "CS_PRCVINCE_LOSE");
					}
				}
				if (!VCoachs.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updateCoachInfo(VCoachs.get(i), "CS_PRCVINCE_WIN");
					}
				}
				if (!players.isEmpty()) {
					for (int i = 0; i < VCoachs.size(); i++) {
						judement.updatePlayerInfo(players.get(i), "PS_PROVINE_LOSE");
					}
				}
				if (!VPlayers.isEmpty()) {
					for (int i = 0; i < VPlayers.size(); i++) {
						judement.updatePlayerInfo(VPlayers.get(i), "PS_PROVINE_WIN");
					}
				}
			}
			break;
		default:
			log.info("没有当前赛级");
			break;
		}
		log.info("比赛记录更新完成");
	}

	@Override
	public String selectLogo(int TID) throws  Exception{
		String logo = "resources/img/site/png.png";
		if (siteLive.selectTeamLogo(TID)!=null&&!siteLive.selectTeamLogo(TID).equals("")) {
			logo = siteLive.selectTeamLogo(TID);
		}
		return logo;
	}



	@Override
	public long slectSE(int AID) throws  Exception{
		long seid =0;
		if (String.valueOf(siteLive.selectSE(AID))!=null) {
			seid = siteLive.selectSE(AID);
		}
		return seid;
	}

	@Override
	public void endStatus(String time, long SEID) throws  Exception{
		siteLive.endStatus(SEID, time);
		log.info("更新时间完成");
		
	}

	@Override
	public String selectGameName(long RID) throws Exception {
		return siteLive.selectGameName(RID);
	}

}
