package com.nsu.service.live.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsu.bean.live.ContestBean;
import com.nsu.dao.live.LiveDao;
import com.nsu.service.BaseService;
import com.nsu.service.live.ILiveService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.service.live.impl
 * @Description: (直播页面的业务处理层实现)
 * @date 2017/4/18 17:34
 */
@Service
public class LiveServiceImpl extends BaseService implements ILiveService{
    @Autowired
    private LiveDao liveDao;

    @Override
    public List<Map<String, Object>> findCity() throws Exception {
        List<Map<String, Object>> list = liveDao.findCity();
        for (Map<String, Object> map : list) {
            map.put("name", map.get("name").toString().split("教育局")[0].toString());
        }
        return list;
    }

    @Override
    public PageInfo<ContestBean> findOverGames(ContestBean contestBean, Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        log.info("cotestbean.competitonCityCode" + contestBean.getCompetitionCityCode());
        List<ContestBean> list = liveDao.findGames(contestBean);
        PageInfo<ContestBean> pageInfo = new PageInfo<>(list);
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            Map<String, Object> homeMap = findTeamData(pageInfo.getList().get(i).getHomeTeamId()); //查询主队信息
            pageInfo.getList().get(i).setHomeTeamName(homeMap.get("teamName").toString()); // 写入主队名称
            pageInfo.getList().get(i).setHomeTeamBadgePach(homeMap.get("teamBadge").toString()); // 写入主队头像
            Map<String, Object> visitingMap = findTeamData(pageInfo.getList().get(i).getVisitingTeamId());//查询客队信息
            pageInfo.getList().get(i).setVisitingTeamName(visitingMap.get("teamName").toString()); // 写入客队名称
            pageInfo.getList().get(i).setVisitingTeamBadgePach(visitingMap.get("teamBadge").toString()); // 写入客队头像
            Map<String, Object> liveScore = findLiveScore(pageInfo.getList().get(i).getRaceId());
            pageInfo.getList().get(i).setHomeScore(Integer.parseInt(liveScore.get("hNowScore").toString()));  //主队分数
            pageInfo.getList().get(i).setVisitingScore(Integer.parseInt(liveScore.get("vNowScore").toString())); //客队分数
//            pageInfo.getList().get(i).setHomeScore(Integer.parseInt(pageInfo.getList().get(i).getOvertimeHomeTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getPenaHomeTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getRegularHomeTeamScore()));  /*插入主队总分*/
//            pageInfo.getList().get(i).setVisitingScore(Integer.parseInt(pageInfo.getList().get(i).getOvertimeVisitingTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getPenaVisitingTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getRegularVisitingTeamScore())); /* 插入客队总分 */
        }
        return pageInfo;
    }

    @Override
    public PageInfo<ContestBean> findNoLiveGames(ContestBean contestBean, Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        log.info("cotestbean.competitonCityCode" + contestBean.getCompetitionCityCode());
        List<ContestBean> list = liveDao.findNoLiveGames(contestBean);
        PageInfo<ContestBean> pageInfo = new PageInfo<>(list);
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            Map<String, Object> homeMap = findTeamData(pageInfo.getList().get(i).getHomeTeamId()); //查询主队信息
            pageInfo.getList().get(i).setHomeTeamName(homeMap.get("teamName").toString()); // 写入主队名称
            pageInfo.getList().get(i).setHomeTeamBadgePach(homeMap.get("teamBadge").toString()); // 写入主队头像
            Map<String, Object> visitingMap = findTeamData(pageInfo.getList().get(i).getVisitingTeamId());//查询客队信息
            pageInfo.getList().get(i).setVisitingTeamName(visitingMap.get("teamName").toString()); // 写入客队名称
            pageInfo.getList().get(i).setVisitingTeamBadgePach(visitingMap.get("teamBadge").toString()); // 写入客队头像
            Map<String, Object> liveScore = findLiveScore(pageInfo.getList().get(i).getRaceId());
        }
            return pageInfo;
        }






    @Override
    public PageInfo<ContestBean> findLiveGame(ContestBean contestBean, Integer pageNum, Integer pageSize) throws Exception {
        log.info("contestBean*****"+contestBean.getCompetitionCityCode());
        PageHelper.startPage(pageNum, pageSize);
        List<ContestBean> list = liveDao.findGames(contestBean);
        PageInfo<ContestBean> pageInfo= new PageInfo<>(list);
        for (int i=0; i<pageInfo.getList().size(); i++){
            Map<String, Object> homeMap=findTeamData( pageInfo.getList().get(i).getHomeTeamId()); //查询主队信息
            pageInfo.getList().get(i).setHomeTeamName(homeMap.get("teamName").toString()); // 写入主队名称
            pageInfo.getList().get(i).setHomeTeamBadgePach(homeMap.get("teamBadge").toString()); // 写入主队头像
            Map<String, Object> visitingMap=findTeamData(pageInfo.getList().get(i).getVisitingTeamId());//查询客队信息
            pageInfo.getList().get(i).setVisitingTeamName(visitingMap.get("teamName").toString()); // 写入客队名称
            pageInfo.getList().get(i).setVisitingTeamBadgePach(visitingMap.get("teamBadge").toString()); // 写入客队头像
//            Map<String, Object> liveScore = findLiveScore(pageInfo.getList().get(i).getRaceId());
//            pageInfo.getList().get(i).setHomeScore(Integer.parseInt(liveScore.get("hNowScore").toString()));  //主队分数
//            pageInfo.getList().get(i).setVisitingScore(Integer.parseInt(liveScore.get("vNowScore").toString())); //客队分数
//            pageInfo.getList().get(i).setHomeScore(Integer.parseInt(pageInfo.getList().get(i).getOvertimeHomeTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getPenaHomeTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getRegularHomeTeamScore()));  /*插入主队总分*/
//            pageInfo.getList().get(i).setVisitingScore(Integer.parseInt(pageInfo.getList().get(i).getOvertimeVisitingTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getPenaVisitingTeamScore())+
//                    Integer.parseInt(pageInfo.getList().get(i).getRegularVisitingTeamScore())); /* 插入客队总分 */
        }
        return pageInfo;
//        for (ContestBean contestBean1: list) {
//            Map<String,Object> homeMap=findTeamData(contestBean1.getHomeTeamId()); //查询主队信息
//            if(homeMap.get("teamName")!=null){
//                contestBean1.setHomeTeamName(homeMap.get("teamName").toString());// 写入主队名称
//            }
//            if(homeMap.get("teamBadge")!=null) {
//                contestBean1.setHomeTeamBadgePach(homeMap.get("teamBadge").toString());// 写入主队头像
//            }
//            Map<String,Object> visitingMap=findTeamData(contestBean1.getVisitingTeamId());//查询客队信息
//            if(visitingMap.get("teamName")!=null) {
//                contestBean1.setVisitingTeamName(visitingMap.get("teamName").toString());// 写入客队名称
//            }
//            if(visitingMap.get("teamBadge")!=null) {
//                contestBean1.setVisitingTeamBadgePach(visitingMap.get("teamBadge").toString());// 写入客队头像
//            }
////            contestBean1.setHomeScore(Integer.parseInt(contestBean1.getRegularHomeTeamScore())+
////                    Integer.parseInt(contestBean1.getPenaHomeTeamScore())+
////                    Integer.parseInt(contestBean1.getOvertimeHomeTeamScore()));/*插入主队总分*/
////            contestBean1.setVisitingScore(Integer.parseInt(contestBean1.getRegularVisitingTeamScore())+
////                    Integer.parseInt(contestBean1.getPenaVisitingTeamScore())+
////                    Integer.parseInt(contestBean1.getOvertimeVisitingTeamScore()));/* 插入客队总分 */
//        }
//        return list;
    }

    @Override
    public Map<String, Object> findTeamData(String teamId) throws Exception {
        return liveDao.findTeamData(teamId);
    }

    @Override
    public Map<String, Object> findLiveOverData(String raceId) throws Exception {
        Map<String, Object> map = liveDao.findLiveOverData(raceId);
        Map<String,Object> homeMap=findTeamData(map.get("homeTeamId").toString()); //查询主队信息
        if(homeMap.get("teamName")!=null){
            map.put("homeTeamName",homeMap.get("teamName"));// 写入主队名称
        }
        if(homeMap.get("teamBadge")!=null) {
            map.put("homeTeamBadgePach",homeMap.get("teamBadge"));// 写入主队头像
        }
        Map<String,Object> visitingMap=findTeamData(map.get("visitingTeamId").toString());//查询客队信息
        if(visitingMap.get("teamName")!=null) {
            map.put("visitingTeamName",visitingMap.get("teamName"));// 写入客队名称
        }
        if(visitingMap.get("teamBadge")!=null) {
            map.put("visitingTeamBadgePach",visitingMap.get("teamBadge"));// 写入客队名称// 写入客队头像
        }
        map.put("homeScore",Integer.parseInt(map.get("regularHomeTeamScore").toString())+Integer.parseInt(map.get("overtimeHomeTeamScore").toString())+Integer.parseInt(map.get("penaHomeTeamScore").toString()));
        map.put("visitingScore",Integer.parseInt(map.get("regularVisitingTeamScore").toString())+Integer.parseInt(map.get("overtimeVisitingTeamScore").toString())+Integer.parseInt(map.get("penaVisitingTeamScore").toString()));
        Map<String, Object> LiveScore = findLiveScore(raceId);
        if(LiveScore != null){
            map.put("hNowScore",LiveScore.get("hNowScore").toString());
            map.put("vNowScore",LiveScore.get("vNowScore").toString());
        }else {
            map.put("hNowScore",0);
            map.put("vNowScore",0);
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> findOverGameData(String raceID,String liveType) throws Exception {
        Map<String,Object> map=new HashedMap();
        map.put("raceId",raceID);
        map.put("liveType",liveType);
        return liveDao.findOverGamesData(map);
    }

    @Override
    public Map<String, Object> findLiveScore(String raceId) throws Exception {
        return liveDao.findLiveScore(raceId);
}


}
