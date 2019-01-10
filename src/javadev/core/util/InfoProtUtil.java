package javadev.core.util;

/**
 * 
 * 功能描述：主要用于对敏感信息的保护                 
 * @author： 罗晓飞               
 * @version：1.0                     
 * create Date ：2016-4-13
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class InfoProtUtil {
	private static final byte key = 7;

	/**
	 * 
	 * 功能的描述: 将明文信息进行128位MD5加密
	 * 
	 * @author:罗晓飞
	 * @param:lainText 需要加密的明文信息
	 * @return:String 返回32位小写字母的md5码 Create Date:2016-4-13
	 */
	public static String parseStrToMd5L32(String lainText) {
		String resultStr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(lainText.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				int bt = bytes[i] & 0xff;
				if (bt < 16) {
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}

			resultStr = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultStr;
	}

	/**
	 * 
	 * 功能的描述: 将明文(密码)信息加盐后进行128位MD5加密,返回两个值：加密结果和盐
	 * 
	 * @author:罗晓飞
	 * @param:password 需要加密的明文信息
	 * @return:String[] str[0]返回32位小写字母的md5码,str[1] 盐 Create Date:2016-4-13
	 */
	public static String[] parsePassToMd5(String password) {
		String[] result = new String[2];
		String salt = getRandomString(8);
		String lainText = password + salt;
		result[0] = parseStrToMd5L32(lainText);
		result[1] = salt;
		return result;
	}

	/**
	 * 
	 * 功能的描述: 将明文密码+盐进行Md5运算后，与在数据库中保存的md5值进行比较
	 * 
	 * @author:罗晓飞
	 * @param:pass 明文密码,salt 盐,md5Pass 要比较的md5值
	 * @return:boolean true，表示比较结果相同，说明验证通过，false 验证不同过 Create Date:2016-4-13
	 */
	public static boolean comparePass(String pass,String salt,String md5Pass) {
		boolean bool = false;
		String lainText = pass+salt;
		if(parseStrToMd5L32(lainText).equals(md5Pass)){
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 
	 * 功能的描述: 将隐私信息进行xor简答加密
	 * 
	 * @author:罗晓飞
	 * @param:info 隐私信息
	 * @return:String Create Date:2016-4-13
	 */
	public static String xorInfo(String info) {
		byte[] infoBytes = info.getBytes();
		for (int i = 0; i < infoBytes.length; i++) {
			infoBytes[i] = (byte) (infoBytes[i] ^ key);
		}

		return new String(infoBytes);
	}

	/**
	 * 
	 * 功能的描述: 生成指定长度的随机字符串
	 * 
	 * @author:罗晓飞
	 * @param:length
	 * @return:String Create Date:2016-4-13
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		String tel = "18333333333XXXXXXXXXX";
		String enco = InfoProtUtil.xorInfo(tel);
		String deco = InfoProtUtil.xorInfo(enco);
		System.out.println(enco);
		System.out.println(deco);

		String s = "abc";
		String parseStrToMd5L32 = InfoProtUtil.parseStrToMd5L32(s);
		System.out.println("未加盐的MD5加密信息:" + parseStrToMd5L32);
		String[] result = InfoProtUtil.parsePassToMd5(s);
		System.out.println("加盐MD5加密信息：" + result[0] + "    salt值:" + result[1]);
		
		System.out.println(InfoProtUtil.comparePass(s,result[1],result[0]));
		System.out.println(InfoProtUtil.comparePass("abd",result[1],result[0]));
	}
}