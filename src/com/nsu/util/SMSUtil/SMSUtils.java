package com.nsu.util.SMSUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author 王树浩
 * @version V1.0
 * @Title: football1st
 * @Package com.nsu.util.SMSUtil
 * @Description: (短信Utils HTTP接口 发送短信)
 * 说明:		http://web.cr6868.com/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&sign=签名&stime=发送时间&type=pt&extno=自定义扩展码
 * @date 2017/5/17 09:27
 */
public class SMSUtils {

    private static final Log log = LogFactory.getLog(SMSUtils.class);
    /**
     * 发送短信
     * @param tel
     //* @param code
     * @return
     * @throws IOException
     */
    public static String send( String tel, String msg) throws IOException {
        log.info(tel);
        //发送内容
        String content = msg;
        String sign="";
//
//        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("http://web.cr6868.com/asmx/smsservice.aspx?");
//
//        // 向StringBuffer追加用户名
        sb.append("name=18190734310");
//
//        // 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
        sb.append("&pwd=A70F85A4B52EEC4649D2DA7CEA15");

        // 向StringBuffer追加手机号码
        sb.append("&mobile="+tel);
        // 向StringBuffer追加消息内容转URL标准码
        sb.append("&content="+URLEncoder.encode(content,"UTF-8"));

        //追加发送时间，可为空，为空为及时发送
        sb.append("&stime=");

        //加签名
        sb.append("&sign="+URLEncoder.encode(sign,"UTF-8"));

        //type为固定值pt  extno为扩展码，必须为数字 可为空
        sb.append("&type=pt&extno=");
//        // 创建url对象
        //String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
        log.info("sb:"+sb.toString());
        URL url = new URL(sb.toString());

        // 打开url连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置url请求方式 ‘get’ 或者 ‘post’
        connection.setRequestMethod("POST");

        // 发送
        InputStream is =url.openStream();

        //转换返回值
        String returnStr = SMSUtils.convertStreamToString(is);
        //String returnStr ="0";
        // 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
        log.info(returnStr);
        // 返回发送结果

        return  returnStr;

    }

    public static void main(String[] s){
        try {
            System.out.println(send("18180517987","【四川省校园足球】尊敬的用户，您本次的的验证码为：1234。请在30分钟内使用，谢谢！"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 转换返回值类型为UTF-8格式.
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }
}
