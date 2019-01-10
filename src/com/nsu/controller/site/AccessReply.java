package com.nsu.controller.site;

import com.nsu.bean.player.ResultJson;
import com.nsu.util.RSAencrypt.MobileToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
public class AccessReply extends SitePlayerAccess {

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
        String A_ID = info.get("A_ID").toString();
        List<Integer> listAid = new ArrayList<>();
        try {
            String data = MobileToken.analysisParam(forms).toString();
            JSONObject object = JSONObject.fromObject(data);
            Long playerAid = Long.parseLong(object.get("message").toString());
            List<Map<String, String>> list = mapper.getPlayerByAId(Integer.parseInt(A_ID));
            for (Map map : list) {
                listAid.add((Integer) map.get("A_ID"));
            }
            if (listAid.isEmpty() | listAid.contains(object.get("message").toString())) {
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
