package cn.nsu.ccl.comm.Envirment;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class ComEnviorment {

	@Autowired
	protected JdbcTemplate jt;

	/**
	 * 此方法帮助拼接字符串，只用传入存储过程的函数名称和需要的值（值填？）再输入要传入的值放入String[]里进行了
	 * 
	 * @param parames
	 * @param SQL
	 * @return
	 * @throws Exception
	 */
	public String GET_SQL(String[] parames, String SQL) throws Exception {
		int x = 0;
		// 遍历数组的每个元素
		for (int i = 0; i <= SQL.length() - 1; i++) {
			String getstr = SQL.substring(i, i + 1);
			if (getstr.equals("?")) {
				x++;
			}
		}
		if (parames.length == x) {
			for (int i = 0; i < x; i++) {
				if(parames[i]==null){
					System.out.println(parames+"    i="+i);
				}
				SQL = SQL.replaceFirst("\\?", parames[i]);
			}
		} else {
			throw new Exception("传入的值与设值对应有误！");
		}
		return SQL;
	}
	/**
	 * 此方法实现密码的MD5加密
	 * @param plain
	 * @return
	 */
	public String getMD5(String plain) {
		try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++){
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();                      // 32位加密
            //return buf.toString().substring(8, 24);   // 16位加密
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}
	
}
