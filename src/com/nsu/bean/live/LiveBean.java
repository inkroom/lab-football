package com.nsu.bean.live;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.bean.live
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/4/21 18:10
 */
public class LiveBean {
    @JsonProperty("comId")
    private String competitionId; //赛事ID
    @JsonProperty("comName")
    private String competitionName; //赛事名称
    @JsonProperty("rId")
    private String raceId; //赛程ID
    @JsonProperty("hTId")
    private String homeTeamId; //主队ID
    @JsonProperty("vTId")
    private String visitingTeamId; //客队ID
    @JsonProperty("startTime")
    private String startTime; //赛程开始时间
    @JsonProperty("endTime")
    private String endTime; //赛课结束时间
    @JsonProperty("hTName")
    private String homeTeamName;//主队名称
    @JsonProperty("vTName")
    private String visitingTeamName;//客队名称
    @JsonProperty("hTBadgePach")
    private String homeTeamBadgePach;//主队头像路径
    @JsonProperty("vTBadgepach")
    private String visitingTeamBadgePach;//客队头像路径
}
