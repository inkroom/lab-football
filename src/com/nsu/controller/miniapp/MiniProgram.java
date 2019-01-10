package com.nsu.controller.miniapp;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: MiniProgram
 * @Package com.nsu.controller.miniapp
 * @Description:
 * @date 9/21/17
 */
@Controller
public class MiniProgram {
    //小程序appid
    private final static String APP_ID = "wx6d72193ef09e99fe";
    //小程序secret
    private final static String APP_SECRET = "71ca6c149784de754a2c0fac75e3c207";
    //获取 URL 头部
    private final static String MINI_API_URL_HEAD = "https://api.weixin.qq.com/sns/jscode2session?appid="+APP_ID+"&secret="+APP_SECRET+"&js_code=";
    // //获取 URL 尾部
    private final static String MINI_API_URL_TAIL = "&grant_type=authorization_code";

    private final static String charset = "UTF-8";

    @Test
    public void test() {
        System.out.println(getMesssage("013COeIW0SUdSW1LjJKW0zciIW0COeIc"));
    }

    @RequestMapping("/index/get_openid")
    @ResponseBody
    public String getMesssage(String js_code){
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(MINI_API_URL_HEAD+js_code+MINI_API_URL_TAIL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (sbParams != null && sbParams.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                osw.flush();
            }
            // 读取返回内容
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
        }

        return resultBuffer.toString();
    }


}
