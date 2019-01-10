package com.nsu.service.team.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nsu.bean.team.Page;
import com.nsu.bean.team.TeamCoachDO;
import com.nsu.controller.BaseController;
import com.nsu.dao.team.TeamCoachDao;
import com.nsu.service.team.ITeamCoachService;
import com.nsu.util.V;

@Service("teamCoachService")
public class TeamCoachServiceImpl extends BaseController implements ITeamCoachService {
	@Resource
	TeamCoachDao teamCoachdao;

	/**
	 * @Description: 查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午3:19:45
	 * @return
	 */
	@Override
	public Page selectCoachInfoService(String TEAM_ID,String num){
		int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		
		try{
			//获取总页数
			totalRecordsNum = teamCoachdao.selectCoachCount(TEAM_ID);
			page = new Page(pageNum, totalRecordsNum, 8);
			
			page.setUrl("/team/team_coach_view/");
			
			List records = teamCoachdao.selectCoachInfo(TEAM_ID, page.getStartIndex(), page.getPageSize());
			page.setRecords(records);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

		}
		
		return page;
	}

	/**
	 * @Description: 根据姓名查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月21日 上午10:26:20
	 * @return
	 */
	@Override
	public Page selectCoachInfoByNameService(String COACH_NAME, String TEAM_ID) {		
		int pageNum = 1;
		int totalRecordsNum = 0;
		Page page = null;
		try{
			page = new Page(pageNum, 1, 8);
			page.setUrl("/team/team_coach_view/");
			List records = teamCoachdao.selectCoachInfoByName(COACH_NAME, TEAM_ID);
			page.setRecords(records);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());

		}
		return page;
	}

	/**
	 * @Description: 根据教练员ID查询教练员信息	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	@Override
	public TeamCoachDO selectCoachInfoByCOACHIDService(String COACH_ID,String COACH_STATUS) {
		TeamCoachDO list = teamCoachdao.selectCoachInfoByCOACHID(COACH_ID,COACH_STATUS);
		String a = V.checkEmpty(list.getCS_PRCVINCE_WIN())==true?"0":list.getCS_PRCVINCE_WIN().toString();
		String b = V.checkEmpty(list.getCS_CITY_WIN())==true?"0":list.getCS_CITY_WIN().toString();
		String c = V.checkEmpty(list.getCS_COUNTRY_WIN())==true?"0":list.getCS_COUNTRY_WIN().toString();
		String d = V.checkEmpty(list.getCS_SCHOOL_WIN())==true?"0":list.getCS_SCHOOL_WIN().toString();
		String e = V.checkEmpty(list.getCS_OTHER_WIN())==true?"0":list.getCS_OTHER_WIN().toString();
		
		String a1 = V.checkEmpty(list.getCS_PRCVINCE_LOSE())==true?"0":list.getCS_PRCVINCE_LOSE().toString();
		String b1 = V.checkEmpty(list.getCS_CITY_LOSE())==true?"0":list.getCS_CITY_LOSE().toString();
		String c1 = V.checkEmpty(list.getCS_COUNTRY_LOSE())==true?"0":list.getCS_COUNTRY_LOSE().toString();
		String d1 = V.checkEmpty(list.getCS_SCHOOL_LOSS())==true?"0":list.getCS_SCHOOL_LOSS().toString();
		String e1 = V.checkEmpty(list.getCS_OTHER_LOSS())==true?"0":list.getCS_OTHER_LOSS().toString();
		
		//计算得分
		String PRCVINCE_GRADE = String.valueOf(Integer.valueOf(a)*3+Integer.valueOf(a1));
		String CITY_GRADE = String.valueOf(Integer.valueOf(b)*3+Integer.valueOf(b1));
		String COUNTRY_GRADE = String.valueOf(Integer.valueOf(c)*3+Integer.valueOf(c1));
		String SCHOOL_GRADE = String.valueOf(Integer.valueOf(d)*3+Integer.valueOf(d1));
		String OTHER_GRADE = String.valueOf(Integer.valueOf(e)*3+Integer.valueOf(e1));
		
		
		list.setPRCVINCE_GRADE(PRCVINCE_GRADE);
		list.setCITY_GRADE(CITY_GRADE);
		list.setCOUNTRY_GRADE(COUNTRY_GRADE);
		list.setSCHOOL_GRADE(SCHOOL_GRADE);
		list.setOTHER_GRADE(OTHER_GRADE);
		return list;
	}

	/**
	 * @Description: 通过按钮	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	@Override
	public String updatecoachoneService(String COACH_ID,String TEAM_ID) {
		int num =teamCoachdao.updatecoachone(COACH_ID,TEAM_ID);
		if(num>0){
		return "success";
		}
		return "error";
	}

	/**
	 * @Description: 不通过按钮	
	 * @author 侯润达
	 * @create_date 2017年4月22日 下午5:34:48
	 * @return
	 */
	@Override
	public String updatecoachtwoService(String COACH_ID,String TEAM_ID) {
		int num =teamCoachdao.updatecoachtwo(COACH_ID,TEAM_ID);
		if(num>0){
		return "success";
		}
		return "error";
	}
}
