package com.nsu.service.site.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nsu.controller.BaseController;
import com.nsu.dao.site.SiteJudementDao;
import com.nsu.service.site.SiteJudementService;

@Service
public class SiteJudementServiceImpl implements SiteJudementService {

	protected static final Log log = LogFactory.getLog(BaseController.class);

	@Autowired
	SiteJudementDao judement;

	@Override
	public Map<String, Object> selectInfo(int A_ID) throws  Exception{
		return judement.selectInfo(A_ID);
	}

	@Override
	public String selectTeamName(int T_ID)throws  Exception {
		return judement.selectNameById(T_ID);
	}

	@Override
	public void insertInfo(String[] parmas, String[] HPlayer, String[] VPlayer, String[] HAlternate,
			String[] VAlternate, String[] InHPlayer,String[] InVPlauyer,String[] Wran, String[] Out, long R_ID, int A_ID)throws  Exception {

		judement.deletePlayer(R_ID);
		judement.deleteAlter(R_ID);
		judement.deleteWran(R_ID);
		judement.deleteOut(R_ID);
		judement.deleteInBall(R_ID);

		int fileNum = Integer.parseInt(parmas[0]);
		String gamePlace = parmas[1];
		int HTeamregular = Integer.parseInt(parmas[2]);
		int VTeamregular = Integer.parseInt(parmas[3]);
		int HTeamExtra = Integer.parseInt(parmas[4]);
		int VTeamExtra = Integer.parseInt(parmas[5]);
		int HTeamPenalty = Integer.parseInt(parmas[6]);
		int VTeamPenalty = Integer.parseInt(parmas[7]);
		int winStatus = Integer.parseInt(parmas[8]);
		String winTeam = parmas[9];
		String redText = parmas[10];
		String wranText = parmas[11];
		String errorText = parmas[12];
		String refPhone = parmas[13];
		String refName = parmas[14];
		judement.updateDetails(R_ID, fileNum, gamePlace, HTeamregular, VTeamregular, HTeamExtra, VTeamExtra,
				HTeamPenalty, VTeamPenalty, winStatus, winTeam, redText, wranText, errorText, refPhone, refName);

		// 上场主队运动员循环插入数据库
		for (int i = 1; i < HPlayer.length; i++) {
			List<String> list = splitString(HPlayer[i]);
			if (isEmpty(list)) {
				String playerName = list.get(0);
				String playerPlace = list.get(1);
				int playerNum = Integer.parseInt(list.get(2));
				judement.insertPlayer(R_ID, playerName, playerNum, 0, playerPlace);
			}
		}

		// 上场客队运动员循环插入数据库
		for (int i = 1; i < VPlayer.length; i++) {
			List<String> list = splitString(VPlayer[i]);
			if (isEmpty(list)) {
				String playerName = list.get(0);
				String playerPlace = list.get(1);
				int playerNum = Integer.parseInt(list.get(2));
				judement.insertPlayer(R_ID, playerName, playerNum, 1, playerPlace);
			}
		}
		// 循环插入主队替补
		for (int i = 1; i < HAlternate.length; i++) {
			List<String> list = splitString(HAlternate[i]);
			if (isEmpty(list)) {
				String playerInName = list.get(0);
				int playerInNum = Integer.parseInt(list.get(1));
				String playerOutName = list.get(2);
				int playerOutNum = Integer.parseInt(list.get(3));
				String time = list.get(4);
				judement.insertAlternate(R_ID, playerInName, playerInNum, playerOutName, playerOutNum, 0, time);
			}
		}
		// 循环插入客队替补
		for (int i = 1; i < VAlternate.length; i++) {
			List<String> list = splitString(VAlternate[i]);
			if (isEmpty(list)) {
				String playerInName = list.get(0);
				int playerInNum = Integer.parseInt(list.get(1));
				String playerOutName = list.get(2);
				int playerOutNum = Integer.parseInt(list.get(3));
				String time = list.get(4);
				judement.insertAlternate(R_ID, playerInName, playerInNum, playerOutName, playerOutNum, 1, time);
			}
		}


		// 循环警告信息
		for (int i = 1; i < Wran.length; i++) {
			List<String> list = splitString(Wran[i]);
			if (isEmpty(list)) {
				int teamName = Integer.parseInt(list.get(0));
				int playerNum = Integer.parseInt(list.get(1));
				String playerName = list.get(2);
				String time = list.get(3);
				String reason = list.get(4);
				judement.insertWran(R_ID, teamName, playerNum, playerName, time, reason);
			}
		}
		// 循环罚令出场信息
		for (int i = 1; i < Out.length; i++) {
			List<String> list = splitString(Out[i]);
			if (isEmpty(list)) {
				int teamName = Integer.parseInt(list.get(0));
				int playerNum = Integer.parseInt(list.get(1));
				String playerName = list.get(2);
				String time = list.get(3);
				String reason = list.get(4);
				judement.insertOut(R_ID, teamName, playerNum, playerName, time, reason);
			}
		}


		log.info("主队名单大小"+InHPlayer.length);
		// 循环插入主队进球
		for (int i = 1; i < InHPlayer.length; i++) {
			List<String> list = splitString(InHPlayer[i]);
			if (isEmpty(list)) {
				String PlayerName = list.get(0);
				int Num = Integer.parseInt(list.get(1));
				String time = list.get(2);
				int HTeamId=judement.selectHTeamId(R_ID);
				log.info(HTeamId+"    "+Num);
				if (String.valueOf(judement.selectPlayerId(HTeamId,Num))!=null){
					long playerID = judement.selectPlayerId(HTeamId,Num);
					judement.insertInBall(R_ID,0,Num,PlayerName,playerID,time);
				}

			}
		}
		// 循环插入客队进球
		for (int i = 1; i < InVPlauyer.length; i++) {
			List<String> list = splitString(InVPlauyer[i]);
			if (isEmpty(list)) {
				String PlayerName = list.get(0);
				int TeamNum = Integer.parseInt(list.get(1));
				String InBalltime = list.get(2);
				int VTeamId=judement.selectVTeamId(R_ID);
				if (String.valueOf(judement.selectPlayerId(VTeamId,TeamNum))!=null){
					long playerID = judement.selectPlayerId(VTeamId,TeamNum);
					judement.insertInBall(R_ID,1,TeamNum,PlayerName,playerID,InBalltime);
				}
			}
		}


	}

	/**
	 * 以;截取字符串返回一个list
	 * 
	 * @param info
	 * @return
	 */
	static List<String> splitString(String info) throws  Exception{
		List<String> list = new ArrayList<String>();
		String[] ary = info.split(";");
		for (String item : ary) {
			list.add(item);
		}
		return list;
	}

	static boolean isEmpty(List<String> list) throws  Exception{
		boolean isCheck = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null || list.get(i).equals("")) {
				isCheck = false;
			}
		}
		return isCheck;
	}


	@Override
	public List<Map<String, Object>> AllPlayerList(List<Map<String, Object>> HTeamplayerList,
			List<Map<String, Object>> VTeamPlayerList) throws  Exception{

		//将主客队两个list合并，考虑两方人数不等问题
		List<Map<String, Object>> PlayerLists = new ArrayList<Map<String,Object>>();
		int PlayerSize = HTeamplayerList.size();
		int VPlayeSize = VTeamPlayerList.size();
		if (PlayerSize>=VPlayeSize) {
			for (int i = 0; i < VPlayeSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HTeamPlayerName", HTeamplayerList.get(i).get("R_PLAYER_NAME"));
				itemMap.put("HTeamPlayerRole", HTeamplayerList.get(i).get("R_PLAYER_POSITION"));
				itemMap.put("HTeamPlayerNum", HTeamplayerList.get(i).get("R_PLAYER_NUM"));
				itemMap.put("VTeamPlayerName", VTeamPlayerList.get(i).get("R_PLAYER_NAME"));
				itemMap.put("VTeamPlayerRole", VTeamPlayerList.get(i).get("R_PLAYER_POSITION"));
				itemMap.put("VTeamPlayerNum", VTeamPlayerList.get(i).get("R_PLAYER_NUM"));
				PlayerLists.add(itemMap);
			}
			for (int i = VPlayeSize; i < PlayerSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HTeamPlayerName", HTeamplayerList.get(i).get("R_PLAYER_NAME"));
				itemMap.put("HTeamPlayerRole", HTeamplayerList.get(i).get("R_PLAYER_POSITION"));
				itemMap.put("HTeamPlayerNum", HTeamplayerList.get(i).get("R_PLAYER_NUM"));
				itemMap.put("VTeamPlayerName", "");
				itemMap.put("VTeamPlayerRole", "");
				itemMap.put("VTeamPlayerNum", "");
				PlayerLists.add(itemMap);
			}
		}else {
			for (int i = 0; i < PlayerSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HTeamPlayerName", HTeamplayerList.get(i).get("R_PLAYER_NAME"));
				itemMap.put("HTeamPlayerRole", HTeamplayerList.get(i).get("R_PLAYER_POSITION"));
				itemMap.put("HTeamPlayerNum", HTeamplayerList.get(i).get("R_PLAYER_NUM"));
				itemMap.put("VTeamPlayerName", VTeamPlayerList.get(i).get("R_PLAYER_NAME"));
				itemMap.put("VTeamPlayerRole", VTeamPlayerList.get(i).get("R_PLAYER_POSITION"));
				itemMap.put("VTeamPlayerNum", VTeamPlayerList.get(i).get("R_PLAYER_NUM"));
				PlayerLists.add(itemMap);
			}
			for (int i = PlayerSize; i < VPlayeSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HTeamPlayerName", "");
				itemMap.put("HTeamPlayerRole", "");
				itemMap.put("HTeamPlayerNum", "");
				itemMap.put("VTeamPlayerName", VTeamPlayerList.get(i).get("R_PLAYER_NAME"));
				itemMap.put("VTeamPlayerRole", VTeamPlayerList.get(i).get("R_PLAYER_POSITION"));
				itemMap.put("VTeamPlayerNum", VTeamPlayerList.get(i).get("R_PLAYER_NUM"));
				PlayerLists.add(itemMap);
			}
		}
		return PlayerLists;
	}

	@Override
	public List<Map<String, Object>> AllAlternateList(List<Map<String, Object>> HTeamAlternateList,
			List<Map<String, Object>> VTeamAlternateList) throws  Exception {
		//合并替补选手名单	
		List<Map<String, Object>> AlternateLists = new ArrayList<Map<String,Object>>();
		int AlterSize = HTeamAlternateList.size();
		int VAlterSize = VTeamAlternateList.size();
		if (AlterSize>=VAlterSize) {
			for (int i = 0; i < VAlterSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HInName", HTeamAlternateList.get(i).get("B_I_NAME"));
				itemMap.put("HInNum", HTeamAlternateList.get(i).get("B_I_NUM"));
				itemMap.put("HOutName", HTeamAlternateList.get(i).get("B_O_NAME"));
				itemMap.put("HOutNum", HTeamAlternateList.get(i).get("B_O_NUM"));
				itemMap.put("HTime", HTeamAlternateList.get(i).get("B_TIME"));
				itemMap.put("VInName", VTeamAlternateList.get(i).get("B_I_NAME"));
				itemMap.put("VInNum", VTeamAlternateList.get(i).get("B_I_NUM"));
				itemMap.put("VOutName", VTeamAlternateList.get(i).get("B_O_NAME"));
				itemMap.put("VOutNum", VTeamAlternateList.get(i).get("B_O_NUM"));
				itemMap.put("VTime", VTeamAlternateList.get(i).get("B_TIME"));
				AlternateLists.add(itemMap);
			}
			for (int i = VAlterSize; i < AlterSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HInName", HTeamAlternateList.get(i).get("B_I_NAME"));
				itemMap.put("HInNum", HTeamAlternateList.get(i).get("B_I_NUM"));
				itemMap.put("HOutName", HTeamAlternateList.get(i).get("B_O_NAME"));
				itemMap.put("HOutNum", HTeamAlternateList.get(i).get("B_O_NUM"));
				itemMap.put("HTime", HTeamAlternateList.get(i).get("B_TIME"));
				itemMap.put("VInName", "");
				itemMap.put("VInNum", "");
				itemMap.put("VOutName", "");
				itemMap.put("VOutNum", "");
				itemMap.put("VTime", "");
				AlternateLists.add(itemMap);
			}
		}else {
			for (int i = 0; i < AlterSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HInName", HTeamAlternateList.get(i).get("B_I_NAME"));
				itemMap.put("HInNum", HTeamAlternateList.get(i).get("B_I_NUM"));
				itemMap.put("HOutName", HTeamAlternateList.get(i).get("B_O_NAME"));
				itemMap.put("HOutNum", HTeamAlternateList.get(i).get("B_O_NUM"));
				itemMap.put("HTime", HTeamAlternateList.get(i).get("B_TIME"));
				itemMap.put("VInName", VTeamAlternateList.get(i).get("B_I_NAME"));
				itemMap.put("VInNum", VTeamAlternateList.get(i).get("B_I_NUM"));
				itemMap.put("VOutName", VTeamAlternateList.get(i).get("B_O_NAME"));
				itemMap.put("VOutNum", VTeamAlternateList.get(i).get("B_O_NUM"));
				itemMap.put("VTime", VTeamAlternateList.get(i).get("B_TIME"));
				AlternateLists.add(itemMap);
			}
			for (int i = AlterSize; i < VAlterSize; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("HInName", "");
				itemMap.put("HInNum", "");
				itemMap.put("HOutName", "");
				itemMap.put("HOutNum", "");
				itemMap.put("HTime", "");
				itemMap.put("VInName", VTeamAlternateList.get(i).get("B_I_NAME"));
				itemMap.put("VInNum", VTeamAlternateList.get(i).get("B_I_NUM"));
				itemMap.put("VOutName", VTeamAlternateList.get(i).get("B_O_NAME"));
				itemMap.put("VOutNum", VTeamAlternateList.get(i).get("B_O_NUM"));
				itemMap.put("VTime", VTeamAlternateList.get(i).get("B_TIME"));
				AlternateLists.add(itemMap);
			}
		}
		return AlternateLists;
	}

	@Override
	public List<Map<String, Object>> AllInBallList(List<Map<String, Object>> InBallHTeam, List<Map<String, Object>> InBallVTeam) throws Exception {

		//合并替补选手名单
		List<Map<String, Object>> InBallLists = new ArrayList<Map<String,Object>>();
		int HInball = InBallHTeam.size();
		int VInball = InBallVTeam.size();
		if (HInball>=VInball) {
			for (int i = 0; i < VInball; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("H_PLAYER_NAME", InBallHTeam.get(i).get("PLAYER_NAME"));
				itemMap.put("H_PLAYER_NUM", InBallHTeam.get(i).get("PLAYER_NUM"));
				itemMap.put("H_TIME", InBallHTeam.get(i).get("TIME"));
				itemMap.put("V_PLAYER_NAME", InBallVTeam.get(i).get("PLAYER_NAME"));
				itemMap.put("V_PLAYER_NUM", InBallVTeam.get(i).get("PLAYER_NUM"));
				itemMap.put("V_TIME", InBallVTeam.get(i).get("TIME"));
				InBallLists.add(itemMap);
			}
			for (int i = VInball; i < HInball; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("H_PLAYER_NAME", InBallHTeam.get(i).get("PLAYER_NAME"));
				itemMap.put("H_PLAYER_NUM", InBallHTeam.get(i).get("PLAYER_NUM"));
				itemMap.put("H_TIME", InBallHTeam.get(i).get("TIME"));
				itemMap.put("V_PLAYER_NAME", "");
				itemMap.put("V_PLAYER_NUM", "");
				itemMap.put("V_TIME", "");
				InBallLists.add(itemMap);
			}
		}else {
			for (int i = 0; i < HInball; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("H_PLAYER_NAME", InBallHTeam.get(i).get("PLAYER_NAME"));
				itemMap.put("H_PLAYER_NUM", InBallHTeam.get(i).get("PLAYER_NUM"));
				itemMap.put("H_TIME", InBallHTeam.get(i).get("TIME"));
				itemMap.put("V_PLAYER_NAME", InBallVTeam.get(i).get("PLAYER_NAME"));
				itemMap.put("V_PLAYER_NUM", InBallVTeam.get(i).get("PLAYER_NUM"));
				itemMap.put("V_TIME", InBallVTeam.get(i).get("TIME"));
				InBallLists.add(itemMap);
			}
			for (int i = HInball; i < VInball; i++) {
				Map<String, Object> itemMap = new HashMap<String,Object>();
				itemMap.put("H_PLAYER_NAME", "");
				itemMap.put("H_PLAYER_NUM", "");
				itemMap.put("H_TIME", "");
				itemMap.put("V_PLAYER_NAME", InBallVTeam.get(i).get("PLAYER_NAME"));
				itemMap.put("V_PLAYER_NUM", InBallVTeam.get(i).get("PLAYER_NUM"));
				itemMap.put("V_TIME", InBallVTeam.get(i).get("TIME"));
				InBallLists.add(itemMap);
			}
		}

		return InBallLists;
	}


	@Override
	public List<Map<String, Object>> selectPlayerList(long RID, int teamNum) throws  Exception{
		List<Map<String, Object>> playerMap = judement.selectPlayerList(RID, teamNum);
		return playerMap;
	}

	@Override
	public List<Map<String, Object>> selectAlternateList(long RID, int teamNum) throws  Exception{
		List<Map<String, Object>> altMap = judement.selectAlternateList(RID, teamNum);
		return altMap;
	}

	@Override
	public List<Map<String, Object>> selectWranList(long RID) throws  Exception{
		List<Map<String, Object>> wranMap = judement.selectWranList(RID);
		return wranMap;
	}

	@Override
	public List<Map<String, Object>> selectOutList(long RID) throws  Exception{
		List<Map<String, Object>> outMap = judement.selectOutList(RID);
		return outMap;
	}

	@Override
	public List<Map<String, Object>> selectDetails(long RID) throws  Exception{
		List<Map<String, Object>> detailsMap = judement.selectDetails(RID);
		return detailsMap;
	}

	@Override
	public List<Map<String, Object>> selectInBall(long RID,int teamNum) throws Exception {
			List<Map<String, Object>> InBallList = judement.selectInBall(RID,teamNum);
			return InBallList;

	}

	@Override
	public List<String> AllPlayers(long RID, int TID) throws  Exception{
		List<Long> playerIDs=judement.selectPlayers(RID, TID);
		List<String> playerNames = new ArrayList<String>();
		for (int i = 0; i < playerIDs.size(); i++) {
			if (judement.selectPlayerNameByID(playerIDs.get(i))!=null) {
				playerNames.add(judement.selectPlayerNameByID(playerIDs.get(i)));
			}
		}
		return playerNames;
	}

	@Override
	public List<Integer> AllPlayersNum(long RID, long TID) throws Exception {
		List<Long> playerIDs=judement.selectPlayers(RID, TID);
		
		List<Integer> playerNums= new ArrayList<Integer>();
		log.info("队伍的id 是"+TID+"  比赛的ID是"+RID);
		
		for (int i = 0; i < playerIDs.size(); i++) {
			log.info("i 是 "+i+"  球员的id是 "+playerIDs.get(i));
			if (String.valueOf(judement.selectGamePlayers(TID,playerIDs.get(i)))!=null
					&&!String.valueOf(judement.selectGamePlayers(TID,playerIDs.get(i))).equals("")) {
				playerNums.add(judement.selectGamePlayers(TID,playerIDs.get(i)));
			}
		}
		return playerNums;
	}
}
