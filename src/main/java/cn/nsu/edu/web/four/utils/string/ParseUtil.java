package cn.nsu.edu.web.four.utils.string;

public class ParseUtil {

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parseInt(Object value) {
        if (value instanceof String)
            return parseInt(((String) value));
        else if (value instanceof Integer)
            return (Integer) value;
        return null;
    }

    public static Boolean parseBoolean(Object value) {
        if (value instanceof String)
            return parseBoolean(((String) value));
        else if (value instanceof Boolean)
            return (Boolean) value;
        return null;
    }

    public static Boolean parseBoolean(String value) {
        if (value.equals("true"))
            return Boolean.TRUE;
        else if (value.equals("false"))
            return Boolean.FALSE;
        return null;
    }
}
