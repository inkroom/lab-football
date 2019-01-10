package cn.inkroom.web.money.gate.utils;

public class LoginUtil{
        public static boolean isEmpty(Object value) {
            return value == null || value.toString().trim().equals("");
        }
}
