package com.nsu.service.publicty.impl;

import java.util.List;

import javax.annotation.Resource;

import com.nsu.util.InfoProUtil;
import com.nsu.util.StringHelper;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.publicity.CoachPubBean;
import com.nsu.dao.publicity.CoachPubDao;
import com.nsu.service.BaseService;
import com.nsu.service.publicty.ICoachPubService;

/**
* @Title: CoachPubServiceImpl
* @Package com.nsu.service.publicty.impl;
* @Description: 教练员公示CoachPubServiceImpl
* @author 曾绩平
* @date 2017-04-12
* @version V1.0
*/

@Service("iCoachPubService")
public class CoachPubServiceImpl extends BaseService implements ICoachPubService {

	/**
	 * 自动注入coachDao
	 */
	@Resource
	private CoachPubDao coachDao;

	/**
	 * 查询教练员信息
	 * @param pageNum
	 * @return List<CoachPubBean>
	 */
	@Override
	public PageInfo<CoachPubBean> getCoachAllService(int pageNum, int pageSize) throws Exception {
		PageHelper.startPage(pageNum,pageSize);//开始分页,物理分页
        List<CoachPubBean> list = coachDao.getCoachAll();//DB操作查询数据
        
        for(CoachPubBean coach : list) {
			String cardid = InfoProUtil.xorInfo(coach.getBirthday());
			Integer age = StringHelper.IDCardNoToAge(cardid);
			coach.setBirthday(age.toString());
		}
        
        PageInfo<CoachPubBean> pageInfo=new PageInfo<CoachPubBean>(list); //将查询到的数据封装到List中
        return pageInfo;
	}

	/**
	 * 根据教练员名查询教练员信息
	 * @param name
	 * @return List<CoachPubBean>
	 */
	@Override
	public List<CoachPubBean> getCoachByNameService(String name) throws Exception {
		List<CoachPubBean> coaches = coachDao.getCoachByName(name);
		
		for(CoachPubBean coach : coaches) {
			String cardid = InfoProUtil.xorInfo(coach.getBirthday());
			Integer age = StringHelper.IDCardNoToAge(cardid);
			coach.setBirthday(age.toString());
		}
		
		return coaches;
	}

	/**
	 * 查询教练员详细信息
	 * @param id
	 * @return CoachPubBean
	 */
	@Override
	public CoachPubBean getCoachDetailService(String id) throws Exception {
		CoachPubBean coach = coachDao.getCoachDetail(id);
		
		String cardid = InfoProUtil.xorInfo(coach.getBirthday());
		Integer age = StringHelper.IDCardNoToAge(cardid);
		coach.setBirthday(age.toString());
		
		if(coach.getExp() == null) {
			coach.setExp("暂无");
		}
		if(coach.getJob() == null) {
			coach.setJob("暂无");
		}
		
		return coach;
	}

	@Override
	public List<CoachPubBean> showCoachService() throws Exception {
		return coachDao.showCoach();
	}

	/**
	 * 查询教练员所属的球队
	 * @param id
	 * @return String
	 */
	@Override
	public String getCoachTeamService(String id) throws Exception {
		String teames = "";
		List<String> list = coachDao.getCoachTeam(id);
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
	 * 查询教练员积分
	 * @param id
	 * @return CoachPubBean
	 */
	@Override
	public CoachPubBean getCoachScoreService(String id) throws Exception {
		CoachPubBean score = coachDao.getCoachScore(id);
		
		int score1 = Integer.parseInt(score.getScWin()) * 3 + Integer.parseInt(score.getScLose());
		int score2 = Integer.parseInt(score.getCounWin()) * 3 + Integer.parseInt(score.getCounLose());
		int score3 = Integer.parseInt(score.getCityWin()) * 3 + Integer.parseInt(score.getCityLose());
		int score4 = Integer.parseInt(score.getpWin()) * 3 + Integer.parseInt(score.getpLose());
		int score5 = Integer.parseInt(score.getOtherWin()) * 3 + Integer.parseInt(score.getOtherLose());
		
		score.setScAll( score1 + "" );
		score.setCounAll( score2 + "" );
		score.setCityAll( score3 + "" );
		score.setpAll( score4 + "" );
		score.setOtherAll( score5 + "" );
		
		return score;
	}
	
}
