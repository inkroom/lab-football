package sms;

import cn.nsu.edu.web.four.beans.common.PhoneCode;
import cn.nsu.edu.web.four.handler.sms.ProfileFactory;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * 測試自動注入的sms服務
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-util.xml")
public class SmsService {

//    @Value("${sms.aliyun.template.json.code}")
//    private String jsonCode;
//    @Autowired
//    private IAcsClient client;
//    @Autowired
//    private SendSmsRequest smsRequest;
//    @Autowired
//    private ProfileFactory profileFactory;
//    @Value("${sms.aliyun.template.code}")
//    private String templateCode;
//    @Value("${sms.aliyun.template.sign.name}")
//    private String signName;
//    final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
//    final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

    @Autowired
    ObjectMapper mapper;

    @Test
    public void test() {

        try {
            PhoneCode code = mapper.readValue("{\"phone\":\"18161202825\",\"code\":\"202658\",\"count\":0,\"sendTime\":1523257006046}",PhoneCode.class);
            System.out.println(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//
//            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
////
////            client = new DefaultAcsClient(profileFactory.create());
//
////            SendSmsRequest smsRequest = new SendSmsRequest();
////            smsRequest.setTemplateCode(templateCode);
////            smsRequest.setSignName(signName);
////            smsRequest.setMethod(MethodType.POST);
//            smsRequest.setPhoneNumbers("1816120285");
//            smsRequest.setTemplateParam("{\"" + jsonCode + "\":\"123456\"}");
//            SendSmsResponse sendSmsResponse = this.client.getAcsResponse(smsRequest);
//            String responseCode = sendSmsResponse.getCode();
//            System.out.println(responseCode);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

    }
}
