package cn.nsu.edu.web.four.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;


public class XSSRequestWrapper extends HttpServletRequestWrapper {
    private static Logger log = LoggerFactory.getLogger(XSSRequestWrapper.class);

    protected XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public static String EDITOR_FILTER = "html";

    @Override
    public String getContextPath() {
        String path = getServletContext().getInitParameter("contextPath");
        if (path==null)
        return super.getContextPath();
        else return path;
    }

    @Override
    public String[] getParameterValues(String parameter) {
        log.info("pa = " + parameter);
        String[] values = super.getParameterValues(parameter);
        if (!parameter.equals(EDITOR_FILTER)) {
            if (values == null) {
                return null;
            }

            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                //log.debug("---------------------"+values[i]+"-------------------------");
                encodedValues[i] = stripXSS(values[i]);
            }
            return encodedValues;
        } else {
            if (values == null) {
                return null;
            }

            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                //log.debug("---------------------"+values[i]+"-------------------------");
                encodedValues[i] = stripLittleXSS(values[i]);
            }
            return values;
        }


    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (!parameter.equals(EDITOR_FILTER)) {
            //log.debug("---------------------getParameter-------------------------");
            //log.debug("---------------------parameter-------------------------"+parameter);

            ////log.debug("---------------------value-------------------------"+value);
            return stripXSS(value);
        } else {
            ////log.debug("---------------------value-------------------------"+value);
            return stripLittleXSS(value);
        }


    }


    @Override
    public Map<String, String[]> getParameterMap() {
        //log.debug("---------------------getParameterMap-------------------------");
        // Map<String,String[]> paramMap = super.getParameterMap();
        HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
        Enumeration<String> enums = super.getRequest().getParameterNames();
        while (enums.hasMoreElements()) {
            String aParam = enums.nextElement();
            String[] values = super.getRequest().getParameterValues(aParam);
            paramMap.put(aParam, values);
        }
        //paramMap =  (HashMap<String, String[]>) paramMap.clone();
        for (Entry<String, String[]> entry : paramMap.entrySet()) {
            String[] values = entry.getValue();
            String key = entry.getKey();
            if (!key.equals(EDITOR_FILTER)) {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null) {
                        values[i] = stripXSS(values[i]);
                        //log.debug("---------------------"+values[i]+"-------------------------");
                    }
                }
            } else {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null) {
                        values[i] = stripLittleXSS(values[i]);
                        //log.debug("---------------------"+values[i]+"-------------------------");
                    }
                }
            }
            entry.setValue(values);
        }
        return paramMap;

    }

    @Override
    public String getHeader(String name) {
        //log.debug("---------------------getHeader-------------------------");
        //log.debug("---------------------name-------------------------"+name);
        //log.debug("---------------------super.getHeader(name)-------------------------"+super.getHeader(name));
        if (!name.equals(EDITOR_FILTER)) {
            String value = super.getHeader(name);
            return stripXSS(value);
        } else {
            String value = super.getHeader(name);
            return stripLittleXSS(value);
        }
    }

    private static Pattern[] scriptPattern;
    private static Pattern[] littlePattern;

    public static void compile() {
        scriptPattern = new Pattern[13];
        int i = 0;
        // Avoid anything between script tags
        scriptPattern[i++] = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        // Avoid anything in a src='...' type of e­xpression
        scriptPattern[i++] = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        scriptPattern[i++] = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        scriptPattern[i++] = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        // Remove any lonesome <script ...> tag
        scriptPattern[i++] = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        // Avoid eval(...) e­xpressions
        scriptPattern[i++] = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        // Avoid e­xpression(...) e­xpressions
        scriptPattern[i++] = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        // Avoid javascript:... e­xpressions
        scriptPattern[i++] = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        // Avoid vbscript:... e­xpressions
        scriptPattern[i++] = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        // Avoid onload= e­xpressions
        scriptPattern[i++] = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        //Avoid < > ' '
        scriptPattern[i++] = Pattern.compile("<.*?>");
        scriptPattern[i++] = Pattern.compile("\\(.*?\\)");
        scriptPattern[i++] = Pattern.compile("\\'.*?\\'");

        i = 0;

        littlePattern = new Pattern[10];
        // Avoid anything between script tags
        littlePattern[i++] = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        // Remove any lonesome </script> tag
        littlePattern[i++] = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        // Remove any lonesome <script ...> tag
        littlePattern[i++] = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        // Avoid eval(...) e­xpressions
        littlePattern[i++] = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        // Avoid e­xpression(...) e­xpressions
        littlePattern[i++] = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        // Avoid javascript:... e­xpressions
        littlePattern[i++] = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        // Avoid vbscript:... e­xpressions
        littlePattern[i++] = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        // Avoid onload= e­xpressions
        littlePattern[i++] = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        littlePattern[i++] = Pattern.compile("\\(.*?\\)");
        littlePattern[i++] = Pattern.compile("\\'.*?\\'");
    }

    public static String stripXSS(String value) {
        return xss(value, false);
    }

    private static String xss(String value, boolean isContent) {
        Pattern[] patterns = isContent ? littlePattern : scriptPattern;
        if (value != null) {
            value = value.replaceAll("", "");
            for (Pattern p : patterns) {
                value = p.matcher(value).replaceAll("");
            }
            if (value.isEmpty())
                value = null;

        }
        return value;
    }


    public static String stripLittleXSS(String value) {
        return xss(value, true);
    }
}