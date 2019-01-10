package com.nsu.dao.live;

import com.nsu.bean.live.ContestBean;

import java.util.List;
import java.util.Map;

/**
 *  @Title: LiveDao.java
 *  @Package com.nsu.dao.live
 *  @Description: (直播的DAO数据交互层)
 *  @author 王树浩 
 *  @date 2017/4/18 10:56
 *  @version V1.0 
 */
public interface LiveDao {
    /**
     * @ClassName: LiveDao
     * @Description: <查询所有的市>
     * <查询出所有市的 代码>
     * @date 2017/4/18 15:03
     * @author 王树浩
     */
    public List<Map<String, Object>> findCity() throws  Exception;
    /**
     * @ClassName: LiveDao
     * @Description: <查询所有结束的的比赛>
     * <查询当天当前城市 小学、中学、高中、大学 等 直播结束的比赛>
     * @date 2017/4/19 17:21
     * @author 王树浩
     */
    public List<ContestBean> findGames(ContestBean contestBean) throws  Exception;
    //查询出还未开始直播的比赛
    public List<ContestBean>  findNoLiveGames(ContestBean contestBean) throws  Exception;

    /**
     * @ClassName: LiveDao
     * @Description: <通过 队伍ID 查询出队伍的信息>
     * <通过 队伍ID 查询出队伍的信息>
     * @date 2017/4/19 17:50
     * @author 王树浩
     */
    public Map<String, Object> findTeamData(String teamId) throws Exception;
    /**
     * @ClassName: LiveDao
     * @Description: <通过赛事ID 查询赛事信息>
     * @date 2017/4/21 18:15
     * @author 王树浩
     */
    public Map<String, Object> findLiveOverData(String raceId) throws Exception;
    /**
     * @ClassName: LiveDao
     * @Description: <通过 赛程ID查询出直播结束比赛的比赛记录>
     * @date 2017/4/22 10:43
     * @author 王树浩
     */
    public List<Map<String, Object>> findOverGamesData(Map<String, Object> map) throws Exception;
    /** 
     * @ClassName: LiveDao
     * @Description: <查询直播当前比赛成绩>
     * <详细介绍>
     * @date 2017/4/23 17:18 
     * @author 王树浩 
     */
    public Map<String, Object> findLiveScore(String raceId) throws Exception;




}
