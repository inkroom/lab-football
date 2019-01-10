package com.nsu.controller.email;

import com.nsu.common.Anonymous;
import com.nsu.controller.BaseController;
import com.nsu.service.sms.ISMSService;
import com.nsu.util.*;
import com.nsu.util.SMSUtil.AjaxJson;
import com.nsu.util.jedis.JedisClient;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 邮箱发送邮件的控制层接口类
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.controller.email
 * @date 2017/5/31 11:15
 */
@Controller
@RequestMapping("email")
public class EmaileController extends BaseController implements Anonymous {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private ISMSService smsService;
    @ResponseBody
    @RequestMapping(value = "getCode", method = RequestMethod.POST)
    public AjaxJson getEmailCode(String email,HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();
        //IP注册次数
        if (jedisClient.exists("email"+IPUtils.getIpAddress(request))){
            if (Integer.parseInt(jedisClient.get("email"+IPUtils.getIpAddress(request)))>=100){
                log.info("jedisClient.get(IPUtils.getIpAddress(request)).toString()******="+jedisClient.get(IPUtils.getIpAddress(request)).toString());
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("3");
                ajaxJson.setMsg("您的IP发送短信次数上限，请明天再试或者更换电脑注册。");
                return ajaxJson;
            }
        }
        String randomCode = String.valueOf((int) (Math.random() * 900000 + 100000)); //生成验证码
        if (jedisClient.exists("day"+email)){
            if (Integer.parseInt(jedisClient.get("day"+email))>=10){
                log.info("jedisClient.get(day.phone).toString()******="+jedisClient.get("day"+email).toString());
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("3");
                ajaxJson.setMsg("您发送邮件次数超过限制，请明天再试或者更换邮箱注册。");
                return ajaxJson;
            }
        }
        //查询该手机号一个小时内是否注册上限
        if (jedisClient.exists("hour"+email)){
            if (Integer.parseInt(jedisClient.get("hour"+email))>=5){
                log.info("jedisClient.get(hour.phone).toString()******="+jedisClient.get("hour"+email).toString());
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("3");
                ajaxJson.setMsg("您发送邮件次数超过限制，请一个小时后再试或者更换邮箱注册。");
                return ajaxJson;
            }
        }

        //查询该手机号两分钟内是否注册上限
        if (jedisClient.exists("minutes"+email)){
            if (Integer.parseInt(jedisClient.get("minutes"+email))>=1){
                log.info("jedisClient.get(minutes.phone).toString()******="+jedisClient.get("minutes"+email).toString());
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("3");
                ajaxJson.setMsg("您发送邮件次数超过限制，请两分钟后再试或者更换邮箱注册。");
                return ajaxJson;
            }
        }
        boolean status = Mail.sendEmail(email, randomCode);
        if (status){
            ajaxJson.setSuccess(true);
            ajaxJson.setErrorCode("1");
            ajaxJson.setMsg("邮件发送成功");
            jedisClient.set(email,randomCode);
            jedisClient.expire(email,1800);
            jedisClient.set(email+"num","1");
            jedisClient.expire(email+"num",1800);
            if (!jedisClient.exists("day"+email)){
                jedisClient.set("day"+email,"1");  //如果不存在则重新定义一个一天的缓存赋初始值为 1
                jedisClient.expire("day"+email,86400);  //缓存为一天
            }else {
                jedisClient.incr("day"+email);
                log.info("day+phone).time******="+jedisClient.get("day"+email).toString());
            }
            if (!jedisClient.exists("hour"+email)){
                jedisClient.set("hour"+email,"1");
                jedisClient.expire("hour"+email,3600);
            }else {
                jedisClient.incr("hour"+email);
                log.info("jedisClient.get(\"hour\"+phone).toString()******="+jedisClient.ttl("hour"+email));
            }
            if (!jedisClient.exists("minutes"+email)){
                jedisClient.set("minutes"+email,"1");
                jedisClient.expire("minutes"+email,120);
            }else {
                jedisClient.incr("minutes"+email);
                log.info("jedisClient.get(\"minutes\"+phone).toString()******="+jedisClient.ttl("minutes"+email));
            }
            if (!jedisClient.exists(IPUtils.getIpAddress(request))){
                jedisClient.set("email"+IPUtils.getIpAddress(request),"1");
                jedisClient.expire("email"+IPUtils.getIpAddress(request),86400);
            }else {
                jedisClient.incr("email"+IPUtils.getIpAddress(request));
                log.info("jedisClient.get(IPUtils.getIpAddress(request)).toString()******="+jedisClient.ttl("email"+IPUtils.getIpAddress(request)));
            }
        }else {
            ajaxJson.setSuccess(false);
            ajaxJson.setErrorCode("1");
            ajaxJson.setMsg("邮件发送失败");
        }
        Map<String, Object> map = new HashedMap();
        map.put("account", InfoProUtil.xorInfo(email));
        String logRandom = InfoProUtil.parseStrToMd5L32(StringHelper.getRandomGeneration(5)+randomCode);
        map.put("msg", "邮件验证码为："+logRandom);
        map.put("return",status);
        map.put("ip",IPUtils.getIpAddress(request));
        map.put("type","2");
        map.put("createBy","邮箱验证");
        try {
            smsService.addSMSLog(map);  // 存入日志
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxJson;
    }

    /**
     * 验证邮箱验证码是否正确，错误五次以上清除验证码
     * @param email
     * @param randomCode
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getValidateCode", method = RequestMethod.POST)
    public AjaxJson getValidateCode(String email, String randomCode, HttpServletResponse response){
        AjaxJson ajaxJson = new AjaxJson();
        if (jedisClient.get(email+"num") != null){
            if (Integer.parseInt(jedisClient.get(email+"num"))>5){
                ajaxJson.setErrorCode("0");
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("验证码错误");
                log.info(" if *** \"mob\"+mobile+\"num\" = **** = " + jedisClient.get(email+"num"));
                jedisClient.del(email+"num");
                jedisClient.del(email);
                return ajaxJson;
            }
        }
        if (randomCode.equals(jedisClient.get(email))){
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("验证码正确");
            ajaxJson.setErrorCode("1");
        }else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("验证码错误");
            ajaxJson.setErrorCode("-1");
            if (jedisClient.get(email+"num") != null) {
                jedisClient.incr(email + "num");
            }
            log.info("\"+email+\"num\" = **** = " + jedisClient.get(email+"num"));
        }
        return ajaxJson;
    }
    @RequestMapping("to_email")
    public String toEmailTest(){
        return "/test/email_test";
    }
}
