package com.nsu.controller.site;

import com.nsu.common.Constants;
import com.nsu.controller.BaseController;
import com.nsu.dao.site.SitePlayerAccessDao;
import com.nsu.staticvar.CommonVar;
import com.nsu.staticvar.SiteVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author LuLongShan
 * @Date 2017年 04月13日 2017/4/13 15:53
 * @package com.nsu.controller.site
 */
@Controller
@RequestMapping("/site")
public class SitePlayerAccessController extends BaseController{


    private String TeamA;
    private String TeamB;

    @Autowired
    protected SitePlayerAccessDao mapper;

    @RequestMapping("/playerCheck")
    public ModelAndView showPlayers(HttpSession session) {
        ModelAndView mView = new ModelAndView();
        if (null == session.getAttribute(Constants.LOGIN_USER)) {
            mView.setViewName(SiteVar.SITE_LOGIN);
            return mView;
        }
        Map<String, String> userMap = (Map) session.getAttribute(Constants.LOGIN_USER);
        List<Map<String, String>> list = mapper.getCheckPlayerByRid(Long.parseLong(userMap.get(CommonVar.Account.ID)));
        log.info("现场管理 map="+userMap);
        Map<String, List<Map<String, String>>> data = divisionTeams(list);
        mView.addObject("A", TeamA);
        mView.addObject("B", TeamB);
        mView.addObject("data", data);
        mView.setViewName(SiteVar.SITE_PLAYER_CHECK);
        return mView;
    }

    private Map<String, List<Map<String, String>>> divisionTeams(List<Map<String, String>> list) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        String key = "TEAM_NAME";
        //获取球队名
        Set<String> teamName = new HashSet<>();
        for (Map<String, String> map : list) {
            teamName.add(map.get(key));
        }
        String[] names = new String[2];
        names = teamName.toArray(names);
        List<Map<String, String>> playerA = new ArrayList<>();
        List<Map<String, String>> playerB = new ArrayList<>();
        for (Map<String, String> map : list) {
            if (names[0].equals(map.get(key))) {
                playerA.add(map);
            } else {
                playerB.add(map);
            }
        }
        setTeamA(names[0]);
        setTeamB(names[1]);
        result.put("A", playerA);
        result.put("B", playerB);
        return result;
    }

    public String getTeamA() {
        return TeamA;
    }

    public void setTeamA(String teamA) {
        TeamA = teamA;
    }

    public String getTeamB() {
        return TeamB;
    }

    public void setTeamB(String teamB) {
        TeamB = teamB;
    }
}
