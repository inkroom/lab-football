package com.nsu.controller.SMS;

import com.nsu.common.Anonymous;
import com.nsu.common.mapper.JsonMapper;
import com.nsu.controller.BaseController;
import com.nsu.controller.site.SitePages;
import com.nsu.service.site.SiteLiveService;
import com.nsu.service.sms.ISMSService;
import com.nsu.util.IPUtils;
import com.nsu.util.InfoProtUtil;
import com.nsu.util.RSAencrypt.RSAUtils;
import com.nsu.util.SMSUtil.AjaxJson;
import com.nsu.util.SMSUtil.SMSUtils;
import com.nsu.util.StringHelper;
import com.nsu.util.jedis.JedisClient;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static com.nsu.util.RSAencrypt.RSAUtils.loadPrivateKeyByFile;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.controller.SMS
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/5/17 10:41
 */
@Controller
@RequestMapping("/sms")
public class SMSController extends BaseController implements Anonymous {
    @Autowired
    private ISMSService smsService ;
    @Autowired
    private JedisClient jedisClient;
    /**
     * web获取验证码
     * errorCood 返回值说明
     * 3:当天发送次数上限 2:发送失败 1：发送成功 -1：手机号被使用 0：未知原因
     * @return
     */
    @RequestMapping(value = "getRegisterCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson getRegisterCode(Model model, HttpServletRequest request, String phone, String type, String smsphone,String flag){
        log.info("********phone + type" + phone + ","+type+ ","+ smsphone);
        log.info("*****************"+flag);
        AjaxJson ajaxJson = new AjaxJson();
        String msg = null;
        String result = null;
        try {
            //IP注册次数
            if (jedisClient.exists(IPUtils.getIpAddress(request))){
                if (Integer.parseInt(jedisClient.get(IPUtils.getIpAddress(request)))>=100){
                    log.info("jedisClient.get(IPUtils.getIpAddress(request)).toString()******="+jedisClient.get(IPUtils.getIpAddress(request)).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您的IP发送短信次数上限，请明天再试。");
                    return ajaxJson;
                }
            }
            //验证手机号是否注册
            if(smsphone==null||flag!=null){
                if (!smsService.findUserMobile(phone,type)){
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("-1");
                    ajaxJson.setMsg("手机号已被使用！");
                    return ajaxJson;
                }
            }
            String randomCode = String.valueOf((int) (Math.random() * 900000 + 100000)); //生成验证码
            //查询该手机号当天是否注册上限
            if (jedisClient.exists("day"+phone)){
                if (Integer.parseInt(jedisClient.get("day"+phone))>=10){
                    log.info("jedisClient.get(day.phone).toString()******="+jedisClient.get("day"+phone).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您发送信息次数超过限制，请明天再试。");
                    return ajaxJson;
                }
            }
            //查询该手机号一个小时内是否注册上限
            if (jedisClient.exists("hour"+phone)){
                if (Integer.parseInt(jedisClient.get("hour"+phone))>=5){
                    log.info("jedisClient.get(hour.phone).toString()******="+jedisClient.get("hour"+phone).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您发送信息次数超过限制，请一个小时后再试。");
                    return ajaxJson;
                }
            }
            //查询该手机号一分钟内是否注册上限
            if (jedisClient.exists("minutes"+phone)){
                if (Integer.parseInt(jedisClient.get("minutes"+phone))>=1){
                    log.info("jedisClient.get(minutes.phone).toString()******="+jedisClient.get("minutes"+phone).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您发送信息次数超过限制，请两分钟后再试。");
                    return ajaxJson;
                }
            }
            // 短信信息
            msg = "【四川省校园足球】尊敬的用户，您本次的的验证码为："+randomCode + "。请在30分钟内使用，谢谢！ ";
            result = SMSUtils.send(phone,msg);

            if (!result.split(",")[0].equals("0")){ //获取短信发送状态
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("2");
                ajaxJson.setMsg("短信发送失败,请联系管理员。");
            }else {
                ajaxJson.setSuccess(true);
                ajaxJson.setErrorCode("1");
                ajaxJson.setMsg("短信发送成功");
                //request.getSession().getServletContext().setAttribute(phone, randomCode);
                jedisClient.set(type+"web"+phone,randomCode); //网页获取验证码存入缓存
                jedisClient.set(type+"web"+phone+"num","1"); //网页获取验证码存入缓存
                jedisClient.expire(type+"web"+phone,1800);
                jedisClient.expire(type+"web"+phone+"num",1800);
                if (!jedisClient.exists("day"+phone)){
                    jedisClient.set("day"+phone,"1");  //如果不存在则重新定义一个缓存赋初始值为 1
                    jedisClient.expire("day"+phone,86400);  //缓存为一天
                }else {
                    jedisClient.incr("day"+phone);
                    log.info("day+phone).time******="+jedisClient.get("day"+phone).toString());
                }
                if (!jedisClient.exists("hour"+phone)){
                    jedisClient.set("hour"+phone,"1");
                    jedisClient.expire("hour"+phone,3600);
                }else {
                    jedisClient.incr("hour"+phone);
                    log.info("jedisClient.get(\"hour\"+phone).toString()******="+jedisClient.ttl("hour"+phone));
                }
                if (!jedisClient.exists("minutes"+phone)){
                    jedisClient.set("minutes"+phone,"1");
                    jedisClient.expire("minutes"+phone,120);
                }else {
                    jedisClient.incr("minutes"+phone);
                    log.info("jedisClient.get(\"minutes\"+phone).toString()******="+jedisClient.ttl("minutes"+phone));
                }
                if (!jedisClient.exists(IPUtils.getIpAddress(request))){
                    jedisClient.set(IPUtils.getIpAddress(request),"1");
                    jedisClient.expire(IPUtils.getIpAddress(request),86400);
                }else {
                    jedisClient.incr(IPUtils.getIpAddress(request));
                    log.info("jedisClient.get(IPUtils.getIpAddress(request)).toString()******="+jedisClient.ttl(IPUtils.getIpAddress(request)));
                }
            }
            Map<String, Object> map = new HashedMap();
            map.put("account", InfoProtUtil.xorInfo(phone));
            String logRandom = InfoProtUtil.parseStrToMd5L32(StringHelper.getRandomGeneration(5)+randomCode);
            map.put("msg", "【四川省校园足球】尊敬的用户，您本次的的验证码为："+logRandom + "。请在30分钟内使用，谢谢！ ");
            map.put("return",result);
            map.put("ip",IPUtils.getIpAddress(request));
            map.put("type","1");
            if (smsphone != null){
                map.put("createBy","更换手机");
            }else{
                map.put("createBy","账号注册");
            }
            smsService.addSMSLog(map);  //存入日志
        } catch (IOException e){
            ajaxJson.setSuccess(false);
            ajaxJson.setErrorCode("0");
            ajaxJson.setMsg("因未知原因导致短信发送失败，请联系管理员。");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ajaxJson;
    }
    /**
     * 手机获取验证码
     * errorCood 返回值说明
     * 3:当天发送次数上限 2:发送失败 1：发送成功 -1：手机号被使用 0：未知原因
     * @return
     */
    @RequestMapping(value = "getMobileRegisterCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson getMobileRegisterCode( HttpServletRequest request, String forms){
        log.info("********phone + type" + forms );
        String tokenDe = null;
        try {
            tokenDe = RSAUtils.decryptByPrivateKey(forms,RSAUtils.loadPrivateKeyByStr(RSAUtils.loadPrivateKeyByFile()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            log.error(e.getCause());
        }
        log.info("json*** = "+tokenDe.toString());
        Map<String, Object> formsmap = JsonMapper.getInstance().fromJson(tokenDe,Map.class);
        String phone = formsmap.get("phone").toString();
        String type = formsmap.get("type").toString();
        AjaxJson ajaxJson = new AjaxJson();
        String msg = null;
        String result = null;
        try {
            //IP注册次数
            if (jedisClient.exists(IPUtils.getIpAddress(request))){
                if (Integer.parseInt(jedisClient.get(IPUtils.getIpAddress(request)))>=100){
                    log.info("jedisClient.get(IPUtils.getIpAddress(request)).toString()******="+jedisClient.get(IPUtils.getIpAddress(request)).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您的IP发送短信次数上限，请明天再试。");
                    return ajaxJson;
                }
            }
            //验证手机号是否注册
//            if (smsphone == null){
//                if (!smsService.findUserMobile(phone,type)){
//                    ajaxJson.setSuccess(false);
//                    ajaxJson.setErrorCode("-1");
//                    ajaxJson.setMsg("手机号已被使用！");
//                    return ajaxJson;
//                }
//            }

            String randomCode = String.valueOf((int) (Math.random() * 900000 + 100000)); //生成验证码
            //查询该手机号当天是否注册上限
            if (jedisClient.exists("day"+phone)){
                if (Integer.parseInt(jedisClient.get("day"+phone))>=10){
                    log.info("jedisClient.get(day.phone).toString()******="+jedisClient.get("day"+phone).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您发送信息次数超过限制，请明天再试。");
                    return ajaxJson;
                }
            }
            //查询该手机号一个小时内是否注册上限
            if (jedisClient.exists("hour"+phone)){
                if (Integer.parseInt(jedisClient.get("hour"+phone))>=5){
                    log.info("jedisClient.get(hour.phone).toString()******="+jedisClient.get("hour"+phone).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您发送信息次数超过限制，请一个小时后再试。");
                    return ajaxJson;
                }
            }
            //查询该手机号一分钟内是否注册上限
            if (jedisClient.exists("minutes"+phone)){
                if (Integer.parseInt(jedisClient.get("minutes"+phone))>=1){
                    log.info("jedisClient.get(minutes.phone).toString()******="+jedisClient.get("minutes"+phone).toString());
                    ajaxJson.setSuccess(false);
                    ajaxJson.setErrorCode("3");
                    ajaxJson.setMsg("您发送信息次数超过限制，请两分钟后再试。");
                    return ajaxJson;
                }
            }
            // 短信信息
            msg = "【四川省校园足球】尊敬的用户，您本次的的验证码为："+randomCode + "。请在30分钟内使用，谢谢！ ";
            result = SMSUtils.send(phone,msg);   //发送短信
            log.info("*************信息发送结果"+result+"********************");
            if (!result.split(",")[0].equals("0")){ //获取短信发送状态
                ajaxJson.setSuccess(false);
                ajaxJson.setErrorCode("2");
                ajaxJson.setMsg("短信发送失败,请联系管理员。");
            }else {
                ajaxJson.setSuccess(true);
                ajaxJson.setErrorCode("1");
                ajaxJson.setMsg("短信发送成功");
                jedisClient.set("mob"+phone,randomCode);  //把验证码存入缓存
                jedisClient.set("mob"+phone+"num","1"); //网页获取验证码存入缓存
                jedisClient.expire(type+"mob"+phone,1800);
                jedisClient.expire(type+"mob"+phone+"num",1800);
                if (!jedisClient.exists("day"+phone)){
                    jedisClient.set("day"+phone,"1");
                    jedisClient.expire("day"+phone,86400);
                }else {
                    jedisClient.incr("day"+phone);
                    log.info("day+phone).time******="+jedisClient.get("day"+phone).toString());
                }
                if (!jedisClient.exists("hour"+phone)){
                    jedisClient.set("hour"+phone,"1");
                    jedisClient.expire("hour"+phone,3600);
                }else {
                    jedisClient.incr("hour"+phone);
                    log.info("jedisClient.get(\"hour\"+phone).toString()******="+jedisClient.ttl("hour"+phone));
                }
                if (!jedisClient.exists("minutes"+phone)){
                    jedisClient.set("minutes"+phone,"1");
                    jedisClient.expire("minutes"+phone,120);
                }else {
                    jedisClient.incr("minutes"+phone);
                    log.info("jedisClient.get(\"minutes\"+phone).toString()******="+jedisClient.ttl("minutes"+phone));
                }
                if (!jedisClient.exists(IPUtils.getIpAddress(request))){
                    jedisClient.set(IPUtils.getIpAddress(request),"1");
                    jedisClient.expire(IPUtils.getIpAddress(request),86400);
                }else {
                    jedisClient.incr(IPUtils.getIpAddress(request));
                    log.info("jedisClient.get(IPUtils.getIpAddress(request)).toString()******="+jedisClient.ttl(IPUtils.getIpAddress(request)));
                }
            }
            Map<String, Object> map = new HashedMap();
            map.put("account", InfoProtUtil.xorInfo(phone));
            String logRandom = InfoProtUtil.parseStrToMd5L32(StringHelper.getRandomGeneration(5)+randomCode);
            map.put("msg", "【四川省校园足球】尊敬的用户，您本次的的验证码为："+logRandom + "。请在30分钟内使用，谢谢！ ");
            map.put("return",result);
            map.put("ip",IPUtils.getIpAddress(request));
            map.put("type","1");
            map.put("createBy","手机绑定");
            smsService.addSMSLog(map);  //存入日志
        } catch (IOException e){
            ajaxJson.setSuccess(false);
            ajaxJson.setErrorCode("0");
            ajaxJson.setMsg("因未知原因导致短信发送失败，请联系管理员。");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ajaxJson;
    }
    /**
     * web端ajax验证手机验证码是否正确
     */
    @ResponseBody
    @RequestMapping(value = "validateMobileCode", method = RequestMethod.POST)
    public AjaxJson validateMobileCode(String type,String mobile, String randomCode){
        AjaxJson ajaxJson = new AjaxJson();
        log.info(type+mobile+randomCode);
        log.info(" *********8" + jedisClient.get(type+"web"+mobile+"num"));
        log.info(jedisClient.get(type+"web"+mobile+"num"));
        log.info(jedisClient.get(type+"web"+mobile));
        if (jedisClient.get(type+"web"+mobile+"num") != null){
            if (Integer.parseInt(jedisClient.get(type+"web"+mobile+"num"))>5){
                ajaxJson.setErrorCode("0");
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("验证码错误");
                log.info(" if *** \"mob\"+mobile+\"num\" = **** = " + jedisClient.get(type + "web" + mobile + "num"));
                jedisClient.del(type+"web"+mobile);
                jedisClient.del(type + "web" + mobile + "num");
                return ajaxJson;
            }
        }
        if (randomCode.equals(jedisClient.get(type+"web"+mobile))) {
            ajaxJson.setErrorCode("1");
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("验证码正确");
        } else {
            ajaxJson.setErrorCode("-1");
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("验证码错误");
            if (jedisClient.get(type+"web"+mobile+"num") != null){
                jedisClient.incr(type + "web" + mobile + "num");
            }
            log.info("\"mob\"+mobile+\"num\" = **** = " + jedisClient.get(type + "web" + mobile + "num"));
        }
        return ajaxJson;
    }
    /**
     * 手机端验证手机验证码是否正确
     */
    @ResponseBody
    @RequestMapping(value = "validateCode", method = RequestMethod.POST)
    public AjaxJson validateCode(String forms) {
        AjaxJson ajaxJson = new AjaxJson();
        log.info("********phone + type" + forms );
        String tokenDe = null;
        try {

            tokenDe = RSAUtils.decryptByPrivateKey(forms,RSAUtils.loadPrivateKeyByStr(RSAUtils.loadPrivateKeyByFile()));  //解密
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("json***** = "+tokenDe.toString());
        Map<String, Object> formsmap = JsonMapper.getInstance().fromJson(tokenDe,Map.class);
        String mobile = formsmap.get("mobile").toString();
        String randomCode = formsmap.get("randomCode").toString();
        if(jedisClient.get("mob"+mobile+"num") != null) {
            if (Integer.parseInt(jedisClient.get("mob" + mobile + "num")) > 5) {
                ajaxJson.setErrorCode("-1");
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("验证码错误");
                jedisClient.del("mob" + mobile + "num");
                jedisClient.del("mob" + mobile);
                log.info(" if *** \"mob\"+mobile+\"num\" = **** = " + jedisClient.get("mob" + mobile + "num"));
                return ajaxJson;
            }
        }
        if (randomCode.equals(jedisClient.get("mob"+mobile))) {
            ajaxJson.setMsg("验证码正确");
            ajaxJson.setSuccess(true);
            ajaxJson.setErrorCode("1");
            jedisClient.del("mob"+mobile+"num");
            jedisClient.del("mob"+mobile);
        } else {
            ajaxJson.setMsg("验证码错误");
            ajaxJson.setSuccess(false);
            ajaxJson.setErrorCode("-1");
            jedisClient.incr("mob" + mobile + "num");
            log.info("\"mob\"+mobile+\"num\" = **** = " + jedisClient.get("mob" + mobile + "num"));
        }
        return ajaxJson;
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/to_sms_test")
    public String toSMSTest(){
        return "/test/SMS_test";
    }
}
