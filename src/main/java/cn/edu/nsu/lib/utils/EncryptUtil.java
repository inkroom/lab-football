package cn.edu.nsu.lib.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/15
 * @Time 20:41
 * @Descorption 加密类
 */
public class EncryptUtil {
    private static final byte key = 7;

    /**
     * 功能的描述: 将明文信息进行128位MD5加密
     *
     * @param lainText 需要加密的明文信息
     * @param salt     盐
     * @return 返回32位小写字母的md5码
     */
    public static String parseMd5(String lainText, String salt) {
        //确定计算方法
        try {
            lainText += salt;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            return base64en.encode(md5.digest(lainText.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能的描述: 将明文(密码)信息加盐后进行128位MD5加密,返回两个值：加密结果和盐
     *
     * @param password 需要加密的明文信息
     * @return 第一位返回32位小写字母的md5码, 第二位 盐
     */
    public static String[] parseMd5(String password) {
        String[] result = new String[2];
        String salt = getRandomString(8);
        result[0] = parseMd5(password, salt);
        result[1] = salt;
        return result;
    }

    /**
     * 功能的描述: 将明文密码+盐进行Md5运算后，与在数据库中保存的md5值进行比较
     *
     * @param pass    明文密码
     * @param salt    盐
     * @param md5Pass 加密后的md5密码
     * @return true，表示比较结果相同，说明验证通过，false 验证不同过 Create Date:2016-4-13
     */
    public static boolean comparePass(String pass, String salt, String md5Pass) {
        String md5 = parseMd5(pass, salt);
        return md5 != null && md5.equals(md5Pass);
    }

    /**
     * 功能的描述: 将隐私信息进行xor简答加密，解密只需再次调用该方法
     *
     * @param info 隐私信息
     * @return Create Date:2016-4-13
     */
    public static String xorInfo(String info) {
        byte[] infoBytes = info.getBytes();
        for (int i = 0; i < infoBytes.length; i++) {
            infoBytes[i] = (byte) (infoBytes[i] ^ key);
        }

        return new String(infoBytes);
    }

    /**
     * 功能的描述: 生成指定长度的随机字符串
     *
     * @param length
     * @return Create Date:2016-4-13
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String md5[] = parseMd5("inkbox");
        System.out.println(md5[0]);
        System.out.println(md5[1]);
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.HOUR_OF_DAY));
        System.out.println(new Date());
    }
}
