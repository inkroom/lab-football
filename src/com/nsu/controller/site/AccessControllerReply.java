package com.nsu.controller.site;

import com.nsu.bean.player.ResultJson;
import com.nsu.staticvar.CommonVar;
import com.nsu.util.RSAencrypt.MobileToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * @Author LuLongShan
 * @Date 2017年 04月12日 2017/4/12 15:42
 * @package com.nsu.controller.site
 * @description 球员APP验证
 */
@Controller
@RequestMapping("/mobile/site")
public class AccessControllerReply extends SitePlayerAccessController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    /**
     * @param forms json串 通过的
     */
    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    @ResponseBody
    @SendTo("/topic/waitting")
    public ResultJson accessReply(String token, String forms) throws Exception {
        Map<String, Object> info = MobileToken.getTokenMap(token);
        // 解析token
        String R_Id = info.get(CommonVar.Scene.R_ID).toString();
        List<Long> listAid;
        try {
            String data = MobileToken.analysisParam(forms).toString();
            JSONObject object = JSONObject.fromObject(data);
            Long playerAid = Long.parseLong(object.get("message").toString());
//            通过现场管理员RId查询当场比赛的所有球员id
            listAid = mapper.getPlayerAIdByRId(Long.parseLong(R_Id));
//            List<Map<String, String>> list = mapper.getPlayerAIdByRId(Long.parseLong(R_Id));
//            for (Map map : list) {
//                listAid.add(Long.parseLong(map.get("R_Id").toString()));
//            }
//            System.out.println("R_Id=" + R_Id + "   PLAYER_ID" + playerAid + "   info=" + info + "   list=" + list);
//            log.info("R_Id=" + R_Id + "   PLAYER_ID" + playerAid + "   info=" + info + "   list=" + list);
//            if (!listAid.contains(playerAid)) {
            if (!listAid.contains(playerAid)){
                return new ResultJson("500", "验证失败,请确认是否为参赛球员！");
            } else {
                simpMessagingTemplate.setDefaultDestination("/topic/waitting");
                simpMessagingTemplate.convertAndSend(simpMessagingTemplate.getDefaultDestination(), data);
                mapper.updatePlayerStatus(playerAid, 1);
                return new ResultJson("200", "验证通过!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson("500", "系统异常! + exception :" + e.getMessage());
        }
    }
}
