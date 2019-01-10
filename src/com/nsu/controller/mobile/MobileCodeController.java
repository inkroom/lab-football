package com.nsu.controller.mobile;

import com.nsu.bean.index.AjaxBean;
import com.nsu.service.core.IMobileTokenService;
import com.nsu.util.RSAencrypt.MobileToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: MobileCode
 * @Package com.nsu.controller.mobile
 * @Description:
 * @date 6/2/17
 */
@Controller
@RequestMapping("/mobile")
public class MobileCodeController {

    @Resource
    private IMobileTokenService mobileTokenService;

    @ResponseBody
    @RequestMapping(value = "/update_code",method = RequestMethod.POST)
    public AjaxBean getUpdateCode(@RequestParam(required = true) String token){
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setStatus("200");
        Map<String,Object> forms = MobileToken.getTokenMap(token);

        String updateCode = null;
        try {
            String aId = forms.get("A_ID").toString();
            String aType = forms.get("A_TYPE").toString();
            updateCode = mobileTokenService.getMoileCode(aId,aType);
        }catch (Exception e){
            ajaxBean.setStatus("500");
            e.printStackTrace();
        }
        ajaxBean.put("code",updateCode);
        return ajaxBean;
    }
}
