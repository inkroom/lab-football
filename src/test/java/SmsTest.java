import cn.nsu.edu.web.four.services.common.SMSService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class SmsTest {



    @Value("${sms.aliyun.connect.timeout}")
    private String connectTimeout ;
    @Value("${sms.aliyun.read.timeout}")
    private String readTimeout;
    @Value("${sms.aliyun.access.key}")
    private String accessKey;
    @Value("${sms.aliyun.access.key.secret}")
    private String accessKeySecret;
    @Value("${sms.aliyun.template.json.code}")
    private String jsonCode;
    @Value("${sms.aliyun.template.code}")
    private String templateCode;
    @Value("${sms.aliyun.template.sign.name}")
    private String signName;


    final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

    IAcsClient acsClient;
    SendSmsRequest request;
    @Before
    public void ready(){
        System.out.println("ready");
        try {
            //设置超时时间-可自行调整

//初始化ascClient需要的几个参数

//替换成你的AK
//            final String accessKeyId = "yourAccessKeyId";//你的accessKeyId,参考本文档步骤2
//            final String accessKeySecret = "yourAccessKeySecret";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers("18161202825");
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            request.setTemplateParam("{\""+jsonCode+"\":\"123\"}");
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//            request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSms(){

        System.out.println("request="+request);
        System.out.println("acsClient="+acsClient);
        try {
            request.setTemplateParam("{\""+jsonCode+"\":\"test\"}");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            if(sendSmsResponse.getCode() != null ) {
                System.out.println(sendSmsResponse.getCode());
    //请求成功
            }
            request.setTemplateParam("{\""+jsonCode+"\":\"456789\"}");
            sendSmsResponse = acsClient.getAcsResponse(request);

            if(sendSmsResponse.getCode() != null ) {

                System.out.println(sendSmsResponse.getCode());
                //请求成功
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
