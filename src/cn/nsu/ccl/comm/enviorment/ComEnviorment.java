package cn.nsu.ccl.comm.enviorment;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <p>ComEnviorment类的描述</p>
 *
 * @author qingyi xuelongqy@foxmail.com
 * @ClassName: ComEnviorment
 * @Description: TODO(此类包括一些常用的方法，此类由dao层继承)
 * @date 2016年11月27日 下午8:19:52
 */
public class ComEnviorment {

    @Autowired
    private JdbcTemplate jt;

    /**
     * <p>GET_SQL方法的描述</p>
     *
     * @param parames 需要传入的属性值
     * @param SQL     SQL sql语句，参数用？占位
     * @return 拼接好的sql语句
     * @throws Exception ？数量与参数长度不一致
     * @Title: ComEnviorment的GET_SQL方法
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:20:24
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
                if (parames[i] == null) {
                    System.out.println(parames + "    i=" + i);
                }
                SQL = SQL.replaceFirst("\\?", parames[i]);
            }
        } else {
            throw new Exception("传入的值与设值对应有误！");
        }
        System.out.println("拼接的语句=" + SQL);
        return SQL;
    }

    /**
     * <p>getMD5方法的描述</p>
     *
     * @param plain 需要加密的字符串
     * @return 加密后的字符串
     * @Title: ComEnviorment的getMD5方法
     * @Description: TODO(此方法实现密码的MD5加密)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:22:05
     */
    public String getMD5(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();                      // 32位加密
            //return buf.toString().substring(8, 24);   // 16位加密
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>getJdbc方法的描述</p>
     *
     * @return 自动注入的JdbcTemplate
     * @Title: ComEnviorment的getJdbc方法
     * @Description: TODO(获取JdbcTemplate实例，用来执行sql语句)
     * @author qingyi xuelongqy@foxmail.com
     * @date 2016年11月27日 下午8:23:10
     */
    public JdbcTemplate getJdbc() {
        return jt;
    }

}
