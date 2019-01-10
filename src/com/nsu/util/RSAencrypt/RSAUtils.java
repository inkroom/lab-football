package com.nsu.util.RSAencrypt;

import com.nsu.util.NewFileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @author 梅谢兵
 * @version V1.0
 * @Title: RSAUtils
 * @Package com.nsu.util.RSAencrypt
 * @Description:
 * @date 6/15/17
 */
public class RSAUtils {



    protected static final Log log = LogFactory.getLog(RSAUtils.class);

    private static final String middle = "rsa";
    private static final String publicEnd = File.separator + "publicKey.keystore1";
    private static final String privateEnd = File.separator + "privateKey.keystore1";

    private static final String EN_RSA_TYPE = "RSA";

    private static final String KEY_RSA_TYPE = "RSA";

    //private static String getSavePath = NewFileUtils.getBasePath(middle);

    private static String getSavePath = NewFileUtils.getProjectPath(middle);

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair() {
        String filePath = getSavePath;
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(KEY_RSA_TYPE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString =new String( Base64.getEncoder().encode(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(Base64.getEncoder().encode(privateKey.getEncoded()));
            // 将密钥对写入到文件

            String privatePath = filePath + privateEnd;
            String publicPath = filePath + publicEnd;

            FileWriter pubfw = new FileWriter(publicPath);
            FileWriter prifw = new FileWriter(privatePath);
            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从文件中加载私钥
     *
     *            私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadPrivateKeyByFile() throws Exception {
        if (isExist()){

        }
        String base = getSavePath;
        String path = base + privateEnd;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new Exception("私钥输入流为空");
        }
    }






    /**
     * 判断是否存在
     * @return
     */
    public static boolean isExist(){

        String base = getSavePath;
        File file = new File(base);
        if (!file.exists()){
            file.mkdirs();
        }

        if (new File(base+publicEnd).exists()){
            if (new File(base+privateEnd).exists()){
                return true;
            }else {
                genKeyPair();
                return false;
            }
        }else {
            genKeyPair();
            return false;
        }
    }



    /**
     * 从文件中输入流中加载公钥
     *
     *            公钥输入流
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static String loadPublicKeyByFile() throws Exception {

        if (isExist()){

        }

        String base = getSavePath;
        String path = base + publicEnd;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new Exception("公钥输入流为空");
        }
    }







    public static void main(String[] args) throws Exception {
        String privatekey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIqpxtAhkM0DWh2zCM2f1b4LO0C4C8JqzKQ/KU1KlQxxnVJjtedQHVEaHFOsfJyRJy+PX+YfQz/qG+mbKywCVsFWW5NriqzsKd4vxvtgAVLOmr/ZEaO1QIJ/jN9qzTERYDvqyk1G/EM7xT0p5lVquwVGLPRlbbVq9/IzSfR8Z6YpAgMBAAECgYA3GG5elHesfgB4fIKq8S9Ea8IHXRmPDlBxkoceptvCLgKMkgHc/wAnEXDf7lE1BJfFVfqMvS9DXgnpMDZfLi6528eUJXak2TRUR0HBEZIILJPnGCZtSyvgVUf9hUM44mx+HrmRz5qjToOtn3nfrXvC1J+XHFKTm8OBF7Embd94AQJBANv/mWeW2QwB93J2dFC3mOWsiYNS0q0LojRy5TNyp2mApCAdWExhln6Cy8kpWvJqhZgAi6qQ2Oe92EMZrDHSLSECQQChWswxThfURLYZgndMIZk0tRPY4FiG+dsDNrwwlSYcOE2EIZZGYJqPaufyyvxxR+xKMBGgvCT2xRqJN0zw8hAJAkB20cHE1RA9EALZa2i+/6owwdMzflQS6imdMkDgXJhWvwfl4HOELWDeyz3NU+yu6SFmS0Ujxj+gSkS3WPJLpjyBAkAIHMeNZw69pmK9b5jN5BaO1tfTnJv21VMUm0jk7GrJGm+TSBkFGtBKi6Zzx47fpy0TIc2RFkSJf6lw4q3ko1b5AkA7fceDcn8mCXS6Rkkms0pZH9ByAzHKKjwi6FWFxuBCqV38+vCiueju6wyvJzJt1I3WINx5WJ0p43BCkrctvqt+";

        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKqcbQIZDNA1odswjNn9W+CztAuAvCasykPylNSpUMcZ1SY7XnUB1RGhxTrHyckScvj1/mH0M/6hvpmyssAlbBVluTa4qs7CneL8b7YAFSzpq/2RGjtUCCf4zfas0xEWA76spNRvxDO8U9KeZVarsFRiz0ZW21avfyM0n0fGemKQIDAQAB";


        String da="{\"title\":\"  Ñµ Á·\",\"time\":\"2018880840000\",\"content\":\"³Ùµ½µÄºó²Ù³¡³Ùµ½³Ùµ½µÄºó¹û×Ô¸º×Ô¸º³Ùµ½³Ùµ½µÄºó¹ûºó¹û×Ô¸ººó¹ûºó²Ù³¡³Ùµ½µÄºó¹û×Ô¸º×Ô¸º³Ùµ½µÄºó²Ù--¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º--³¡ºó¹û×Ô¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º³Ùµ½µÄºó²Ù³¡ºó¹û×Ô¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º³Ùµ½µÄºó²Ù³¡ºó¹û×Ô¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º¸º³Ùµ½µÄºó²Ù³¡ºó¹û×Ô¸ººó¹ûºó¹û×Ô¸º³Ùµ½µÄºó¹û×Ô¸ººó¹û×Ô¸ººó¹û×Ô¸ºËÙ¶È¿ìºó¹û×Ô¸º\",\"addr\":\"¶«Èíºó²Ù³¡\",\"teamId\":\"2\"}×Ô¸º³Ùµ½¹û×Ô¸º×Ô¸º³Ùµ½µÄºó²Ù³¡ºó¹û×Ô¸ººó¹ûºó¹û×Ô¸º³Ùµ½xx";

//        String enString = "";

        RSAPublicKey pubKey=loadPublicKeyByStr(publicKey);

        RSAPrivateKey key=loadPrivateKeyByStr(privatekey);


//        String eRes=encryptByPublicKey(da, pubKey);
//        System.out.println(eRes);
        String enString = "Y9ZxsNAKt8csrMZS1lSw25j7Eks2/yWXdP3TwkZvUn8rkHi1z9eyLPkFXvFtq5TlnZz+MxWLu5LB5OxHDibHQbtSmmjS811+ujP8D0C4PoMqx3RSK4DAazPnbthBqGspeAohk5xtmJoU6DGpU5Wfdx8ZT9QySSSmsDU2a5v1+uBkgbsn4f6l9SlRgAfbM9bDtw8KvxRoMJ0UUj2o7touMgFyWeJAUg2jc6Zq2sE5MK2BBKH6nj14G1s39lkyNwzWFezTNDWQC5HEeT7QYo5hdzM6R7WC2t2Dw4Sl9WYwjUhJzyhJ3oiGo44KVJXqBIYsj2Mb0AbkTpCZXCyrJ5kqwzL3VIwHUe+cCUUosBPIq+XgcGj/RInBH49vH3tHe+wMwJQfDRXnamaGbAke34/b2VwUfon/1h97TL4+18IQ1K6H5JqwiQacBR1I8H7OGcd66PVo1GoaVMDuIXB+B4H1tVpFkJ20VEp3YD/5ReDKWBxpDyVxJ6DbLdQMXtGZmuhf";
        String res=decryptByPrivateKey(enString, key);
        System.out.println("head");
        System.out.println(res);
        System.out.println("tail");
    }



    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(publicKeyStr.getBytes());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            log.error(e.getMessage());
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new Exception("公钥数据为空");
        }
    }



    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] datas=data.getBytes("utf-8");
        String mi = "";
        ArrayList<Byte> list=new ArrayList<Byte>();
        for(int i=0;i<datas.length;i+=117){
            int s_length=datas.length-i;
            if(s_length>117){
                s_length=117;
            }
            byte[] SplitData=new byte[s_length];
            System.arraycopy(datas,i,SplitData,0,s_length);
            byte[] temp=cipher.doFinal(SplitData);
            for(int  length=0;length<temp.length;length++){
                list.add(Byte.valueOf(temp[length]));
            }
        }
        byte[] res=new byte[list.size()];
        for(int k=0;k<res.length;k++){
            res[k]=list.get(k).byteValue();
        }
        mi=Base64.getEncoder().encodeToString(res);
        return mi;
    }



    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
            throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            log.error(e.getMessage());
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new Exception("私钥数据为空");
        }
    }

    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = data.getBytes();
        byte[]bcd=Base64.getDecoder().decode(bytes);
        String ming = "";
        ArrayList<Byte> list=new ArrayList<Byte>();
        byte[] SplitData=new byte[128];
        for(int i=0;i<bcd.length;i+=128){
            System.arraycopy(bcd,i,SplitData,0,128);
            byte[] temp=cipher.doFinal(SplitData);
            for(int  length=0;length<temp.length;length++){
                list.add(Byte.valueOf(temp[length]));
            }
        }
        byte[] res=new byte[list.size()];
        for(int k=0;k<res.length;k++){
            res[k]=list.get(k).byteValue();
        }
        ming=new String(res,"utf-8");
        return ming;
    }
}
