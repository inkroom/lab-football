package com.nsu.service.publicty.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.GameInfoBean;
import com.nsu.bean.publicity.TeamInfoBean;
import com.nsu.dao.publicity.GameInfoDao;
import com.nsu.service.BaseService;
import com.nsu.service.publicty.IGameInfoService;

/**
* @Title: GameInfoServiceImpl
* @Package com.nsu.service.publicty.impl;
* @Description: 赛事公示GameInfoServiceImpl
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

@Service("iGameInfoService")
public class GameInfoServiceImpl extends BaseService implements IGameInfoService {

	/**
	 * 自动注入gameInfoDao
	 */
	@Resource
	private GameInfoDao gameInfoDao;

	/**
	 * 查询赛事信息
	 * @param pageNum
	 * @param pageSize 
	 * @return PageInfo<GameInfoBean>
	 */
	@Override
	public PageInfo<GameInfoBean> getGameAllService(int pageNum, int pageSize) throws Exception {
		log.info("============getGameAllService===========");
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<GameInfoBean> list = gameInfoDao.getGameAll();//DB操作查询数据
        PageInfo<GameInfoBean> pageInfo=new PageInfo<GameInfoBean>(list); //将查询到的数据封装到List中
        return pageInfo;
	}
	
	/**
	 * 查询赛事信息（机构）
	 * @param pageNum
	 * @param pageSize
	 * @param id 
	 * @return PageInfo<GameInfoBean>
	 */
	@Override
	public PageInfo<GameInfoBean> getGameAllOrgService(int pageNum, int pageSize, long id) throws Exception {
		log.info("============getGameAllService===========");
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<GameInfoBean> list = gameInfoDao.getGameAllOrg(id);//DB操作查询数据
        PageInfo<GameInfoBean> pageInfo=new PageInfo<GameInfoBean>(list); //将查询到的数据封装到List中
        return pageInfo;
	}

	/**
	 * 根据赛事名查询赛事信息
	 * @param name
	 * @return List<GameInfoBean>
	 */
	@Override
	public List<GameInfoBean> getGameByNameService(String name) throws Exception {
		log.info("============getGameByNameService===========");
		return gameInfoDao.getGameByName(name);
	}

	/**
	 * 查询赛事详细信息
	 * @param id
	 * @return GameInfoBean
	 */
	@Override
	public GameInfoBean getGameDetailService(String id) throws Exception {
		log.info("============getGameDetailService===========");
		GameInfoBean game = gameInfoDao.getGamedetail(id);
		//1省，2市，3县，4校，5不限
		switch(game.getLevel()){
		case "1":
			game.setLevel("省级");
			break;
		case "2":
			game.setLevel("市级");
			break;
		case "3":
			game.setLevel("县级");
			break;
		case "4":
			game.setLevel("校级");
			break;
		case "5":
			game.setLevel("不限");
			break;
		}
		
		//足球组别：1、 5人， 2、7人 3、11人 4、不限
		switch(game.getGro()){
		case "1":
			game.setGro("5人");
			break;
		case "2":
			game.setGro("7人");
			break;
		case "3":
			game.setGro("11人");
			break;
		case "4":
			game.setGro("不限");
			break;
		}
		
		//赛事级别：1小学，2中学，3高中，4大学 ，5不限
		switch(game.getGrade()){
		case "1":
			game.setGrade("小学");
			break;
		case "2":
			game.setGrade("初中");
			break;
		case "3":
			game.setGrade("高中");
			break;
		case "4":
			game.setGrade("大学");
			break;
		case "5":
			game.setGrade("不限");
			break;
		}
		
		//赛事类型：1男子，2女子，3混合
		switch(game.getType()){
		case "1":
			game.setType("男子");
			break;
		case "2":
			game.setType("女子");
			break;
		case "3":
			game.setType("混合");
			break;
		}
		return game;
	}

	/**
	 * 查询参赛球队
	 * @param id
	 * @return List<TeamInfoBean>
	 */
	@Override
	public List<TeamInfoBean> getTeamService(String id) throws Exception {
		log.info("============getTeamService===========");
		return gameInfoDao.getTeam(id);
	}

	@Override
	public List<GameInfoBean> showGameService() throws Exception {
		log.info("============showGameService===========");
		return gameInfoDao.showGame();
	}
	
}
