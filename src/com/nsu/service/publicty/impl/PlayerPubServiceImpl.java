package com.nsu.service.publicty.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.PlayerPubBean;
import com.nsu.dao.publicity.PlayerPubDao;
import com.nsu.service.BaseService;
import com.nsu.service.publicty.IPlayerPubService;

/**
 * @Title: PlayerPubServiceImpl
 * @Package com.nsu.service.publicty.impl;
 * @Description: 球员公示PlayerPubServiceImpl
 * @author 曾绩平
 * @date 2017-04-12
 * @version V1.0
 */

@Service("iPlayerPubService")
public class PlayerPubServiceImpl extends BaseService implements IPlayerPubService {

	/**
	 * 自动注入playerDao
	 */
	@Resource
	private PlayerPubDao playerDao;

	/**
	 * 查询球员信息
	 * @param 
	 * @return List<PlayerPubBean>
	 */
	@Override
	public PageInfo<PlayerPubBean> getPlayerAllService(int pageNum, int pageSize) throws Exception {
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
		List<PlayerPubBean> list = playerDao.getPlayerAll();//DB操作查询数据
		for(PlayerPubBean player: list) {
			String id = player.getId();
			Map<String, String> map = getScoreService(id);
			player.setScore(map.get("score"));
		}
		PageInfo<PlayerPubBean> pageInfo=new PageInfo<PlayerPubBean>(list); //将查询到的数据封装到List中
		return pageInfo;
	}

	/**
	 * 根据球员名查询球员信息
	 * @param name
	 * @return List<PlayerPubBean>
	 */
	@Override
	public List<PlayerPubBean> getPlayerByNameService(String name) throws Exception {
		List<PlayerPubBean> list = playerDao.getPlayerByName(name);
		for(PlayerPubBean player: list) {
			String id = player.getId();
			Map<String, String> map = getScoreService(id);
			player.setScore(map.get("score"));
		}
		return list;
	}

	/**
	 * 查询球员详细信息
	 * @param id
	 * @return PlayerPubBean
	 */
	@Override
	public PlayerPubBean getPlayerDetailService(String id) throws Exception {
		PlayerPubBean player = playerDao.getPlayerDetail(id);
		
		Map<String, String> map = getScoreService(id);
		player.setScore(map.get("score"));
		player.setMatches(map.get("count"));
		
		if(player.getClassHonor()==null) {
			player.setClassHonor("暂无数据");
		}
		if(player.getSchoolHonor()==null) {
			player.setSchoolHonor("暂无数据");
		}
		if(player.getCountryHonor()==null) {
			player.setCountryHonor("暂无数据");
		}
		if(player.getCityHonor()==null) {
			player.setCityHonor("暂无数据");
		}
		return player;
	}

	@Override
	public List<PlayerPubBean> showPlayerService() throws Exception {
		return playerDao.showPlayer();
	}

	/**
	 * 查询球员所属的球队
	 * @param id
	 * @return String
	 */
	@Override
	public String getPlayerTeamService(String id) throws Exception {
		String teames = "";
		List<String> list = playerDao.getPlayerTeam(id);
		if(list.size() == 0) {
			teames = "暂无";
		}else {
			for(String team: list) {
				teames += team +"<br>";
			}
		}
		return teames;
	}

	/**
	 * 查询积分
	 * @param id
	 * @return Map<String, String>
	 */
	@Override
	public Map<String, String> getScoreService(String id) throws Exception {
		Map<String, String> map = new HashMap<>();
		
		Map<String, Object> info = playerDao.getScore(id);
		
		Integer win1 = (Integer) info.get("PS_CITY_WIN");
		Integer flat1 = (Integer) info.get("PS_CITY_FLAT");
		Integer lose1 = (Integer) info.get("PS_CITY_LOSE");
		Integer win2 = (Integer) info.get("PS_PROVINE_WIN");
		Integer flat2 = (Integer) info.get("PS_PROVINE_FLAT");
		Integer lose2 = (Integer) info.get("PS_PROVINE_LOSE");
		Integer win3 = (Integer) info.get("PS_COUNTY_WIN");
		Integer flat3 = (Integer) info.get("PS_COUNTY_FLAT");
		Integer lose3 = (Integer) info.get("PS_COUNTY_LOSE");
		Integer win4 = (Integer) info.get("PS_SCHOOL_WIN");
		Integer flat4 = (Integer) info.get("PS_SCHOOL_FLAT");
		Integer lose4 = (Integer) info.get("PS_SCHOOL_LOSE");
		
		// 定义总场次
		Integer count = win1 + flat1 + lose1 + win2 + flat2 + lose2 + win3 + flat3 + lose3 + win4 + flat4 + lose4;
		
		// 定义积分
		Integer score = win1 * 3 + flat1 * 2 + lose1 + win2 * 3 + flat2 * 2 + lose2 + win3 * 3 + flat3 * 2 + lose3 + win4 * 3 + flat4 * 2 + lose4;
		
		map.put("count", count + "");
		map.put("score", score + "");
		return map;
	}

}
