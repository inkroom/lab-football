package com.nsu.service.live;

import com.github.pagehelper.PageInfo;
import com.nsu.bean.live.ContestBean;

import java.util.List;
import java.util.Map;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.service.live
 * @Description: (直播页面的业务处理层接口)
 * @date 2017/4/18 17:31
 */
public interface ILiveService {
    /**
     * @ClassName: ILiveService
     * @Description: <查找所有城市的接口>
     * <查找该省份所有的城市>
     * @date 2017/4/18 17:33
     * @author 王树浩
     */
    public List<Map<String, Object>> findCity() throws  Exception;
    /**
     * @ClassName: ILiveService
     * @Description: <查询所有结束的的比赛>
     * <查询当前城市 小学、中学、高中、大学 等 直播结束的比赛>
     * @date 2017/4/19 17:41
     * @author 王树浩
     */
    public PageInfo<ContestBean> findOverGames(ContestBean contestBean, Integer pageNum, Integer pageSize) throws  Exception;




    /**
     * @ClassName: ILiveService
     * @Description: <查询出当天所有直播或者尚未开始直播的的比赛>
     * <查询当天当前城市 小学、中学、高中、大学等，直播或者尚未开始直播的比赛>
     * @date 2017/4/20 17:48
     * @author 王树浩
     */
    public PageInfo<ContestBean> findLiveGame(ContestBean contestBean, Integer pageNum, Integer pageSize) throws  Exception;
    /**
     * @ClassName: ILiveService
     * @Description: <通过 队伍ID 查询出队伍的信息>
     * <通过 队伍ID 查询出队伍的信息>
     * @date 2017/4/19 17:57
     * @author 王树浩
     */
    public Map<String , Object> findTeamData(String teamId) throws Exception;
    /**
     * @ClassName: ILiveService
     * @Description: <查询出直播或者直播结束的比赛信息>
     * <详细介绍>
     * @date 2017/4/21 21:13
     * @author 王树浩
     */
    public Map<String ,Object> findLiveOverData(String raceId) throws Exception;
    /**
     * @ClassName: LiveDao
     * @Description:
     * <通过 赛程ID查询出直播结束比赛的比赛记录>
     * @date 2017/4/22 10:43
     * @author 王树浩
     */
    public List<Map<String, Object>> findOverGameData(String raceID,String liveType) throws  Exception;
    /**
     * @ClassName: ILiveService
     * @Description: <查询出当前直播的最新比分>
     * <详细介绍>
     * @date 2017/4/23 17:30
     * @author 王树浩
     */
    public Map<String, Object> findLiveScore(String raceId) throws Exception;
    //查询出还未开始的比赛
    public PageInfo<ContestBean> findNoLiveGames(ContestBean contestBean, Integer pageNum, Integer pageSize) throws  Exception;
}
