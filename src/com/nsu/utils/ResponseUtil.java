package com.nsu.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理向前台发送响应工具类
 * @author Tig
 *
 */
public class ResponseUtil {
	
	public static void write(HttpServletResponse response, Object o) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
