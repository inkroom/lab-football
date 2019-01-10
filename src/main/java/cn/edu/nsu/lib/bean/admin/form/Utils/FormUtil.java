package cn.edu.nsu.lib.bean.admin.form.Utils;

import java.math.BigInteger;

/**
 * Created by 王振科 on 2017/10/8.
 */
public class FormUtil { //该类帮助表单转换类型

    public static BigInteger getBI(String string){
        /**
            getBI() 这个方法的描述
         * @ClassName: getBI
         * @Description: 把字符串转换为biginteger类型
         * @Author:  王振科
         * @Date: 14:56
         * @URL:
         * @param string
         */
        BigInteger bigInteger = new BigInteger(string);
        return  bigInteger;
    }

    public  static int getInt(String string){
        /**
            getInt() 这个方法的描述
         * @ClassName: getInt
         * @Description: 把字符串转换为int类型
         * @Author:  王振科
         * @Date: 15:00
         * @URL:
         * @param string
         */
        return Integer.parseInt(string);
    }


}
