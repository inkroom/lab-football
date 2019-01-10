package cn.nsu.edu.web.four.utils.string;

import cn.nsu.edu.web.four.config.RegexStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : 磐石
 * @Date : 2018/3/30 9:26
 * @Description :获取字符串长度
 **/
public class WordCheck {

    private static Logger log = LoggerFactory.getLogger(WordCheck.class);

    //检验是否是字符或数字或中文
    public static int checkLength(String str)
    {
        int valueLength = 0;
        for (int i = 0; i < str.length(); i++) {
            valueLength += 1;
        }
        return valueLength;
    }


    //匹配正则表达式：检验中文、身份证证号
    public static boolean isNumeric(String str,String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    //检验是否含有非法字符(如果包含非法字符返回true；不是返回false）
    public static boolean isIllagel(String str){
        for(int i=0;i<str.length();i++){
            int chr=str.charAt(i);
            if(!((chr>47 && chr<58)||(chr>64 && chr<91)
                    ||(chr>96 && chr<123))){
                log.info(str.charAt(i)+" 是非法字符");
                return true;
            }
        }
        return false;
    }
}
