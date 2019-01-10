package com.nsu.controller.site;

import com.nsu.common.Constants;
import com.nsu.dao.site.SitePlayerAccessMapper;
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
public class SitePlayerAccess {


    private String TeamA;
    private String TeamB;

    @Autowired
    protected SitePlayerAccessMapper mapper;

    @RequestMapping("/playerCheck")
    public ModelAndView showPlayers(HttpSession session) {
        ModelAndView mView = new ModelAndView();
        if (null == session.getAttribute(Constants.LOGIN_USER)) {
            mView.setViewName(SitePages.SITE_LOGIN);
            return mView;
        }
        Map<String, String> userMap = (Map) session.getAttribute(Constants.LOGIN_USER);
        List<Map<String, String>> list = mapper.getPlayerByAId(Integer.parseInt( userMap.get("A_ID")));
        Map<String, List<Map<String, String>>> data = divisionTeams(list);
        mView.addObject("A", TeamA);
        mView.addObject("B", TeamB);
        mView.addObject("data", data);
        mView.setViewName(SitePages.SITE_PLAYERCHECK);
        return mView;
    }

    private Map<String, List<Map<String, String>>> divisionTeams(List<Map<String, String>> list) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        String key = "TEA_NAME";
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
