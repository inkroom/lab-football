package cn.nsu.edu.web.four.utils.code;

import cn.nsu.edu.web.four.config.BaseStatic;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.UUID;

public class CodeUtil {
    public static String createToken() {
        try {
            return UUID.randomUUID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter(BaseStatic.KEY_PARAMETER_TOKEN);
        if (token == null) {
            token = request.getHeader(BaseStatic.KEY_PARAMETER_TOKEN);
        }
        return token;
    }

    public static String createPhoneCode(int count) {

        Random random = new Random();
        int result = (int) (random.nextInt((int) (Math.pow(10, count) - 1 - Math.pow(10, count - 1))) + Math.pow(10, count - 1));

        return String.valueOf(result);
    }

    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String createMixCode(int count) {
        StringBuilder builder = new StringBuilder(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            builder.append(codeSequence[random.nextInt(codeSequence.length)]);
        }
        return builder.toString();
    }
}
