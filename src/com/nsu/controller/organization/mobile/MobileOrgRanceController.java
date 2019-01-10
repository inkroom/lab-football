package com.nsu.controller.organization.mobile;

import com.nsu.bean.organization.ScheduleBean;
import com.nsu.bean.player.ResultJson;
import com.nsu.controller.BaseController;
import com.nsu.service.organization.OrgCompetitionScheduleService;
import com.nsu.util.RSAencrypt.MobileToken;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 辜鹏
 * @ClassName: MobileOrgRanceController
 * @Description: TODO<手机登录返回赛事赛程信息>
 * @date 17/4/28    上午10:15
 */
@RequestMapping("/mobile/org")
@Controller
public class MobileOrgRanceController extends BaseController {

    // 返回信息
    private final String successCode = "200";
    private final String errorCode = "404";
    private final String systemError = "查询失败，请稍后查看！";

    @Resource
    private OrgCompetitionScheduleService service;

    /*
     * @Description: TODO(得倒赛事信息)
     * @param
     * @return     返回类型
     * @throws
     */

    @ResponseBody
    @PostMapping(value = "/getmatch")
    public ResultJson getMatchInfo(@RequestParam("token") String token,
                                   @RequestParam("com_id") String com_id) {

            Map<String, Object> info = null;
            //返回值存放
            List<ScheduleBean> result = new ArrayList<>();

        try {


            //解析token
            info = MobileToken.getTokenMap(token);
            String A_USERNAME = info.get("A_USERNAME").toString();

            log.info("username" + A_USERNAME);
            log.info("com_id" + com_id);

            //通过机构赛事ID 获得 赛程信息
            result = service.getRaceInfo(A_USERNAME, com_id);



            log.info("list info" + result);
            return new ResultJson(successCode, result, String.valueOf(result.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(errorCode, systemError);
        }

    }


}