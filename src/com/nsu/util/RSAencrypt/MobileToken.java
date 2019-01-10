package com.nsu.util.RSAencrypt;

import com.nsu.util.InfoProUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: ${Class}
 * @Package com.nsu.util.RSAencrypt
 * @Description:
 * @date 17/4/19
 */
public class MobileToken {

    protected static final Log log = LogFactory.getLog(MobileToken.class);

    public static SimpleDateFormat getSDF(){
        SimpleDateFormat sdf=new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        return sdf;
    }

    /**
     * 生成token
     * @param username
     * @param password
     * @param type
     * @param IMEI
     * @return
     * @throws Exception
     */
    public static String getAndroidToken(String username,String password,String type,String aId,String IMEI) throws Exception {
        username = InfoProUtil.xorInfo(username);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put(Constants.AndroidUserName,username);
//        map.put(Constants.AndroidPassword,password);
//        map.put(Constants.AndroidIMED,IMEI);
//        map.put(Constants.AndroidTime,new Date());
//
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(Constants.AndroidUserName,username);
//        jsonObject.put(Constants.AndroidPassword,password);
//        jsonObject.put(Constants.AndroidIMED,IMEI);
//        jsonObject.put(Constants.AndroidTime,new Date());
//        TokenBean tokenBean = new TokenBean(username,password,IMEI,new Date());
//        byte[] toekn = ByteUtils.toByteArray(tokenBean);
//        System.out.println(toekn.length);
//        System.out.println(jsonObject.toString().getBytes().length);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(InfoProUtil.xorInfo(username)+",");
            stringBuffer.append(password+",");
            stringBuffer.append(type+",");
            stringBuffer.append(aId+",");
            stringBuffer.append(IMEI+",");
            stringBuffer.append(getSDF().format(new Date()));
            //String tokenByte = encryptString(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile()),stringBuffer.toString().getBytes());
            String tokenByte = RSAUtils.encryptByPublicKey(stringBuffer.toString(),RSAUtils.loadPublicKeyByStr(RSAUtils.loadPublicKeyByFile()));


            return tokenByte;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }

    }

    /**
     *  解析token 返回String数组 数组第一位是 加密的username 第二位password
     * @param token
     * @return
     * @throws Exception
     */
    public static String[] analysisToken(String token) throws Exception {
        System.out.println(token);
        try {
//            byte[] origToken = decryptString(RSAEncrypt.loadPrivateKeyByStr(loadPrivateKeyByFile()),token);
//            String tokenDe = new String(origToken);
            String tokenDe = RSAUtils.decryptByPrivateKey(token,RSAUtils.loadPrivateKeyByStr(RSAUtils.loadPrivateKeyByFile()));
            String[] tem = tokenDe.split(",");
            return tem;
        } catch (NoSuchAlgorithmException e) {
            log.info("无此加密算法");
            return null;
        } catch (NoSuchPaddingException e) {
            log.info("NoSuchPaddingException");
            return null;
        } catch (InvalidKeyException e) {
            log.info("加密私钥非法,请检查");
            return null;
        } catch (IllegalBlockSizeException e) {
            log.info("明文长度非");
            return null;
        } catch (BadPaddingException e) {
            log.info("明文数据已损坏");
            return null;
        } catch (NullPointerException e){
            log.info("明文数据为空");
            return null;
        }
    }

    public static Map<String,Object> getTokenMap(String token){
        try {
            Map<String,Object> tokenMap = new HashMap<String,Object>();
//            byte[] origToken = decryptString(RSAEncrypt.loadPrivateKeyByStr(loadPrivateKeyByFile()),token);
//            String tokenDe = new String(origToken);
            String tokenDe = RSAUtils.decryptByPrivateKey(token,RSAUtils.loadPrivateKeyByStr(RSAUtils.loadPrivateKeyByFile()));
            String[] tem = tokenDe.split(",");
            tokenMap.put("A_USERNAME",tem[0]);
            tokenMap.put("A_PASS",tem[1]);
            tokenMap.put("A_TYPE",tem[2]);
            tokenMap.put("A_ID",tem[3]);
            tokenMap.put("A_DEVICE_INFO",tem[4]);
            return tokenMap;
        } catch (NoSuchAlgorithmException e) {
            log.info("无此加密算法");
            return null;
        } catch (NoSuchPaddingException e) {
            log.info("NoSuchPaddingException");
            return null;
        } catch (InvalidKeyException e) {
            log.info("加密私钥非法,请检查");
            return null;
        } catch (IllegalBlockSizeException e) {
            log.info("明文长度非");
            return null;
        } catch (BadPaddingException e) {
            log.info("明文数据已损坏");
            return null;
        } catch (NullPointerException e){
            log.info("明文数据为空");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析mobile 传过来的JSON
     * @param param
     * @return
     * @throws Exception
     */
    public static JSONObject analysisParam(String param) throws Exception {
        //byte[] jsonObjectByte =  decryptString(RSAEncrypt.loadPrivateKeyByStr(loadPrivateKeyByFile()),param);
        String jsonObjectByte = RSAUtils.decryptByPrivateKey(param,RSAUtils.loadPrivateKeyByStr(RSAUtils.loadPrivateKeyByFile()));
        System.out.println(jsonObjectByte);
        JSONObject jsonObject = new JSONObject(jsonObjectByte);
        return jsonObject;
    }

    /**
     * 测试 token
     */
    @Test
    public void testToken(){


        try {
            //String token = getAndroidToken("13458187837","16d7a4fca","1","99000855597219");
            //System.out.println(token);
            String token2 = "drzPEDgXgajq4o9KIhzN5pJuFPdBDMQS7Exmqjow3oiimbatTXv9tt%2BWaa8vTPhu4GAnvTT4jsQXoyaxiJXXmRbfRmkmvqI9DKMX5eqdAqBJz9qGBXi6YKb7gs9GRrIbRwCJ8EZFRw45v2lY6EF0%2FrgO2WPju0%2BuIfNB4boKSwo%3D";
            String[] tokenList = analysisToken(token2);

            System.out.println("--");
            for (String temp : tokenList){
                System.out.println(temp);
            }
        }catch (Exception e){

        }

    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testJson() throws Exception {



        String tem = ""   ;

//        /**
//         * Android 加密
//         */
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("meixiebing","123");
//        String tem = encryptString(loadPublicKeyByStr(loadPublicKeyByFile()),jsonObject.toString().getBytes());
        String tem1 = "drzPEDgXgajq4o9KIhzN5pJuFPdBDMQS7Exmqjow3oiimbatTXv9tt%2BWaa8vTPhu4GAnvTT4jsQXoyaxiJXXmRbfRmkmvqI9DKMX5eqdAqBJz9qGBXi6YKb7gs9GRrIbRwCJ8EZFRw45v2lY6EF0%2FrgO2WPju0%2BuIfNB4boKSwo%3D";
        /**
         * 服务器端解密
         */;
        JSONObject jsonObjectNew = analysisParam(tem1);
        log.info(jsonObjectNew.toString());


    }

}
