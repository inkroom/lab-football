package cn.nsu.edu.web.four.handler.sms;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class ProfileFactory {

    private String accessKey;
    private String accessKeySecret;
    private String product;
    private String domain;

    private String endpointName;
    private String reqionId;

    private long connectTimeout;
    private long readTimeout;

    public ProfileFactory(String accessKey, String accessKeySecret, long connectTimeout, long readTimeout) {
        this(accessKey, accessKeySecret, "Dysmsapi", "dysmsapi.aliyuncs.com", "cn-hangzhou", "cn-hangzhou", connectTimeout, readTimeout);
    }

    public ProfileFactory(String accessKey, String accessKeySecret, String product, String domain, String endpointName, String reqionId, long connectTimeout, long readTimeout) {
        this.accessKey = accessKey;
        this.accessKeySecret = accessKeySecret;
        this.product = product;
        this.domain = domain;
        this.endpointName = endpointName;
        this.reqionId = reqionId;

        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public IClientProfile create() {
        System.setProperty("sun.net.client.defaultConnectTimeout", connectTimeout + "");
        System.setProperty("sun.net.client.defaultReadTimeout", readTimeout + "");
        IClientProfile profile = DefaultProfile.getProfile(reqionId, accessKey,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint(endpointName, reqionId, product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return profile;
    }

}
