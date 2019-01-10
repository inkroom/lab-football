package com.nsu.filter;


import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XSSFilter extends HttpServletRequestWrapper {
    protected final Log log = LogFactory.getLog(getClass());

    public XSSFilter(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    private static final String EXCLUDE = "myContent";

    @Override
    public String[] getParameterValues(String parameter) {
        if (!parameter.equals(EXCLUDE)) {
            log.debug("------------getParameterValues---------过滤----------------------" + parameter);
            String[] values = super.getParameterValues(parameter);
            if (values == null) {
                return null;
            }

            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                log.debug("---------values[i]------------" + values[i] + "-------------------------");
                encodedValues[i] = stripXSS(values[i]);
            }
            return encodedValues;
        } else {
            log.debug("-------------getParameterValues--------未过滤----------------------" + parameter);
            String[] values = super.getParameterValues(parameter);
            if (values == null) {
                return null;
            }
            int count = values.length;
            return new String[count];
        }


    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (!parameter.equals(EXCLUDE)) {
            log.debug("---------getParameter------------过滤----------------------" + parameter);
            return stripXSS(value);
        } else {
            log.debug("----------getParameter-----------未过滤----------------------" + parameter);
            return value;
        }

    }

//	@Override
//	public ServletInputStream getInputStream() throws IOException {
//		try {
//			System.out.println("************getInputStream***********");
//			ServletInputStream in = super.getInputStream();
//			int len = super.getContentLength();
//		    byte buffer[];
//		    buffer = new byte[len];
//		    int total = 0;
//		    for (int once = 0; total < len && once >= 0; total += once) {
//		     once = in.readLine(buffer, total, len);
//		    }
//		    System.out.println(new String(buffer,"utf-8"));
//		    return null;
//		} catch (Exception e) {
//			return super.getInputStream();
//		}
//		
//	}

    @Override
    public Map<String, String[]> getParameterMap() {
        // Map<String,String[]> paramMap = super.getParameterMap();
        HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
        Enumeration<String> enums = super.getRequest().getParameterNames();
        while (enums.hasMoreElements()) {
            String aParam = enums.nextElement();
            String[] values = super.getRequest().getParameterValues(aParam);
            log.debug("---------getParameterValues-------aParam-----" + aParam + "-------------------------");
            log.debug("---------getParameterValues-------values-----" + values + "-------------------------");
            paramMap.put(aParam, values);
        }

        //paramMap =  (HashMap<String, String[]>) paramMap.clone();
        Iterator<Entry<String, String[]>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
            String[] values = (String[]) entry.getValue();
            String key = entry.getKey();

            if (!key.equals(EXCLUDE)) {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] instanceof String) {
                        log.debug("--------getParameterMap-------------过滤----------------------" + key);
                        log.debug("---------getParameterMap----values--------" + values[i] + "-------------------------");
                        values[i] = stripXSS(values[i]);
                    }
                }
            } else {
                log.debug("----------getParameterMap-----------未过滤----------------------" + key);
            }
            entry.setValue(values);

        }
        return paramMap;
    }

    @Override
    public String getHeader(String name) {
        if (!name.equals(EXCLUDE)) {
            log.debug("--------getHeader-------------过滤----------------------" + name);
            String value = super.getHeader(name);
            return stripXSS(value);
        } else {
            log.debug("-----------getHeader----------未过滤----------------------" + name);
            return super.getHeader(name);
        }
    }

    public static String stripXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onabort(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("ondblclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onfocus(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onkeydown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");


            scriptPattern = Pattern.compile("onkeypress(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onmousedown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onmousemove(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onmouseout(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");


            scriptPattern = Pattern.compile("onmouseover(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onmouseup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");


            scriptPattern = Pattern.compile("onreset(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onresize(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");


            scriptPattern = Pattern.compile("onselect(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("onunload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            //Avoid < > ' '
            scriptPattern = Pattern.compile("<.*?>");
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("\\(.*?\\)");
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("\\'.*?\\'");
            value = scriptPattern.matcher(value).replaceAll("");

        }
        return value;
    }
   /* public static void main(String[] args) {
        String s = "http://182.140.219.133/coach/coach!CoachLogin.action?'password=g00dPa'%24%24w0rD&token=kjmbgmopmyzgz2yk&username=efmydkan%22%20onmouseover%3dprompt(961389)%20bad%3d%22&validcode=94102";
		
		System.out.println(XSSRequestWrapper.sXSS(s));
    }*/
}