package com.nsu.controller.live;

import com.nsu.bean.live.ContestBean;
import com.nsu.common.Anonymous;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.controller.BaseController;
import com.nsu.service.live.ILiveService;
import com.nsu.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.controller.live
 * @Description: (直播的控制层)
 * @date 2017/4/18 16:49
 */
@Controller
@RequestMapping("live")
public class LiveController extends BaseController implements Anonymous {
    @Autowired
    private ILiveService liveService;

    /**
     * @ClassName: LiveController
     * @Description: <进入直播主页面>
     * <详细介绍>
     * @date 2017/4/18 16:50
     * @author 王树浩
     */
    @RequestMapping("live_index")
    public String toLiveIndex(Model model) {
        try {
            model.addAttribute("listCity", liveService.findCity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/live/live_index";
    }

    /**
     * @ClassName: LiveController
     * @Description: <查询出直播结束的比赛>
     * <通过比赛等级，>
     * @date 2017/4/20 17:26
     * @author 王树浩
     */
    @RequestMapping(value = "list_over_games", method = RequestMethod.POST)
    public void findOverGames(HttpServletResponse response, String subjectData, Integer pageNum) {
        try {
            ContestBean contestBean = JsonMapper.getInstance().fromJson(subjectData, ContestBean.class);
            ResponseUtil.write(response, JsonMapper.getInstance().toJson(liveService.findOverGames(contestBean, pageNum, 10)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询出还未开始的比赛
    @RequestMapping(value = "list_NoStart_games", method = RequestMethod.POST)
    public void findNoStartGames(HttpServletResponse response, String subjectData, Integer pageNum) {
        try {
            ContestBean contestBean = JsonMapper.getInstance().fromJson(subjectData, ContestBean.class);
            ResponseUtil.write(response, JsonMapper.getInstance().toJson(liveService.findNoLiveGames(contestBean, pageNum, 10)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @ClassName: LiveController
     * @Description: 查询出当天所有直播或者尚未开始直播的的比赛>
     * <查询当天当前城市 小学、中学、高中、大学等，直播或者尚未开始直播的比赛>
     * @date 2017/5/11 15:39
     * @author 王树浩
     */

    @RequestMapping(value = "list_live_games", method = RequestMethod.POST)
    public void findLiveGames(HttpServletResponse response, String subjectData, Integer pageNum) {
        try {
            ContestBean contestBean = JsonMapper.getInstance().fromJson(subjectData, ContestBean.class);
            //List<ContestBean> list = liveService.findLiveGame(contestBean,pageNum,10);
            ResponseUtil.write(response, JsonMapper.getInstance().toJson(liveService.findLiveGame(contestBean, pageNum, 10)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @ClassName: LiveController
     * @Description: <进入直播页面>
     * @date 2017/4/21 16:09
     * @author 王树浩
     */
    @RequestMapping(value = "{raceId:[1-9]*[0-9]+}/live_page")
    public String toLivePage(@PathVariable("raceId") String raceId, Model model, HttpSession session) {
        log.info("**********jinrule" + raceId);
        try {
            model.addAttribute("map", liveService.findLiveOverData(raceId));
            model.addAttribute("list", liveService.findOverGameData(raceId, "2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("RID", raceId);
        return "/live/live_page";
    }

    /**
     * @ClassName: LiveController
     * @Description: <进入回放页面>
     * @date 2017/4/21 16:09
     * @author 王树浩
     */
    @RequestMapping(value = "{raceId:[1-9]*[0-9]+}/over_page")
    public String toOverPage(@PathVariable("raceId") String raceId, Model model) {
        log.info("**********jinrule" + raceId);
        try {
            model.addAttribute("map", liveService.findLiveOverData(raceId));
            log.info("map************" + liveService.findLiveOverData(raceId).toString());
            model.addAttribute("list", liveService.findOverGameData(raceId, "1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/live/over_page";
    }
}
