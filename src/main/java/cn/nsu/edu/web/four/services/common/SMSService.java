package cn.nsu.edu.web.four.services.common;

import cn.nsu.edu.web.four.beans.common.PhoneCode;
import cn.nsu.edu.web.four.config.BaseStatic;
import cn.nsu.edu.web.four.daos.redis.common.SmsDao;
import cn.nsu.edu.web.four.enums.Result;
import cn.nsu.edu.web.four.handler.sms.ProfileFactory;
import cn.nsu.edu.web.four.utils.code.CodeUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 短信服务
 */
@Service("smsService")
public class SMSService {

//    @Value("${sms.aliyun.connect.timeout}")
//    private String connectTimeout;
//    @Value("${sms.aliyun.read.timeout}")
//    private String readTimeout;
//    @Value("${sms.aliyun.access.key}")
//    private String accessKey;
//    @Value("${sms.aliyun.access.key.secret}")
//    private String accessKeySecret;
//    @Value("${sms.aliyun.template.code}")
//    private String templateCode;
//    @Value("${sms.aliyun.template.sign.name}")
//    private String signName;


    @Value("${sms.aliyun.template.json.code}")
    private String jsonCode;
    @Autowired
    private IAcsClient client;
    @Autowired
    private SendSmsRequest smsRequest;
//    @Autowired
//    private ProfileFactory profileFactory;

    @Autowired
    private SmsDao dao;


//    final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
//    final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）


    private Logger log = LoggerFactory.getLogger(getClass());

    private String defaultMessage;

    private String returnMessage;


    private HashMap<String, Result> message;

//    public String getMessage() {
//        return returnMessage;
//    }

    public SMSService() {
        message = new HashMap<>();
        message.put("isv.BUSINESS_LIMIT_CONTROL", Result.SEND_PHONE_ERROR_COUNT);
    }

//    public void setDefaultMessage(String defaultMessage) {
//        this.defaultMessage = defaultMessage;
//    }

//    public void setMessage(HashMap<String, String> message) {
//        this.message = message;
//    }

    /**
     * 发送验证码
     *
     * @param phone   对应的电话号码
     * @param request 请求，
     * @return 如果发送成功，返回Result.SUCCESS，发送次数达到上限，返回Result.SEND_PHONE_ERROR_COUNT,其余错误返回Result.SEND_PHONE_ERROR_OTHER
     * @see cn.nsu.edu.web.four.enums.Result
     */
    public Result createCode(String phone, HttpServletRequest request) {
        try {
            //生成验证码
            PhoneCode code = new PhoneCode(phone, CodeUtil.createPhoneCode(6));
            smsRequest.setPhoneNumbers(phone);
            smsRequest.setTemplateParam("{\"" + jsonCode + "\":\"" + code.getCode() + "\"}");

            SendSmsResponse sendSmsResponse = this.client.getAcsResponse(smsRequest);
            String responseCode = sendSmsResponse.getCode();
            if ("OK".equals(responseCode)) {
                if (dao.setPhoneCode(code)) {//保存成功
                    log.debug("电话：" + phone + ",code:" + code.getCode());
                    request.getSession().setAttribute(BaseStatic.KEY_SESSION_PHONE, phone);
//                        request.getSession().setAttribute(BaseStatic.KEY_SESSION_);
                    return Result.SUCCESS;
                } else {
                    log.error("验证码保存失败");
                }
            } else {
                return message.getOrDefault(responseCode, Result.SEND_PHONE_ERROR_OTHER);
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.EXCEPTION;
        }
        return Result.SEND_PHONE_ERROR_OTHER;

    }

    /**
     * 获得之前发送验证码的电话号码
     *
     * @param request 请求
     * @return 电话号码
     */
    public String getPhone(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_PHONE);
    }

//    private void create() throws ClientException {
//
//        System.setProperty("sun.net.client.defaultConnectTimeout", connectTimeout + "");
//        System.setProperty("sun.net.client.defaultReadTimeout", readTimeout + "");
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey,
//                accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//
//        client = new DefaultAcsClient(profile);
//        smsRequest = new SendSmsRequest();
//    }

    /**
     * 验证手机验证码
     *
     * @param code    用户的验证码
     * @param request 请求
     * @return 如果验证码不存在，返回Result.CODE_NOT_EXISTS，<p>验证码不正确，返回Result.CODE_ERROR_COUNT，</p><p>验证码错误次数上限，返回Result.CODE_ERROR_COUNT，</p>验证成功，返回Result.SUCCESS
     * @see cn.nsu.edu.web.four.enums.Result
     */
    public Result checkCode(String code, HttpServletRequest request) {
        //获取手机号
        String phone = (String) request.getSession().getAttribute(BaseStatic.KEY_SESSION_PHONE);
        if (StringUtils.isEmpty(phone))
            return Result.CODE_NOT_EXISTS;
        //获取存储的code
        PhoneCode phoneCode = dao.getPhoneCode(phone);
        if (phoneCode == null) {
            returnMessage = "验证码不存在，或验证码已过期";
            return Result.CODE_NOT_EXISTS;
        }
        //验证时间
        //构造到期时间
        Calendar c = Calendar.getInstance();
        c.setTime(phoneCode.getSendTime());
        c.setTimeInMillis(phoneCode.getSendTime().getTime() + (BaseStatic.SMS_RETAIN_TIME * 1000));
        log.debug("到期时间=" + c.toString());
        if (c.getTime().before(new Date())) {//验证验证码是否过期
            returnMessage = "验证码不存在，或验证码已过期";
            return Result.CODE_NOT_EXISTS;
        }
        if (phoneCode.getCount() >= BaseStatic.SMS_MAX_ERROR_COUNT) {
            returnMessage = "验证码错误次数已超过三次，请重新发送验证码";
            return Result.CODE_ERROR_COUNT;
        }
        if (phoneCode.getCode().equals(code)) {
            dao.removePhoneCode(code);
            return Result.SUCCESS;
        } else {
            phoneCode.setCount(phoneCode.getCount() + 1);
            returnMessage = "验证码错误";
            return Result.CODE_NOT_CORRECT;
        }
    }
}
