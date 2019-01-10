package com.nsu.util;

public class PageUtil {
	
	/**
	 * 生成分页代码
	 * @param targetUrl 目标地址
	 * @param totalNum 总记录数
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @param param 其他参数
	 * @return
	 */
	public static String getPeripheryPageCode(String targetUrl, long totalNum, int currentPage, int pageSize, String param) {
		
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "未查询到数据";
		} else {
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<div class=\"dataTables_paginate paging_simple_numbers\">");
			if (currentPage > 1) {
				pageCode.append("<a class=\"paginate_button previous\" href=\""+targetUrl+"?currentPage="+(currentPage-1)+"&"+param+"\" > 上一页</a>");	
			} else {
				pageCode.append("<a class=\"paginate_button previous disabled\"  href=\"javascript:void(0)\" >上一页 </a>");
			}
			
			for (int i = currentPage - 2; i <=  currentPage + 2; i++) {
				if (i < 1 || i > totalPage) {
					continue;
				}
				if (i == currentPage) {		//当前页
					pageCode.append("<a class=\"paginate_button current\" href='"+targetUrl+"?currentPage="+i+"&"+param+"'>"+i+"</a>");
				} else {
					pageCode.append("<a href='"+targetUrl+"?currentPage="+i+"&"+param+"'>"+i+"</a>");	
				}
			}
			
			if (currentPage < totalPage) {
				pageCode.append("<a class=\"paginate_button next\" href=\""+targetUrl+"?currentPage="+(currentPage+1)+"&"+param+"\">下一页</a>");
			} else {
				pageCode.append("<a class=\"paginate_button next disabled\" href=\"javascript:void(0)\">下一页</a>");
			}
			
			pageCode.append("</div>");
			return pageCode.toString();
		}
	}

	/**
	 * 生成分页代码
	 * @param targetUrl 目标地址
	 * @param totalNum 总记录数
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @param param 其他参数，比如
	 * @return
	 */
	public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize, String param){
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0 ) {
			return "未查询到数据";
		} else {
			StringBuffer pageCode=new StringBuffer();
			pageCode.append("<li><a href='"+targetUrl+"?currentPage=1&"+param+"'>首页</a></li>");
			if (currentPage > 1) {
				pageCode.append("<li><a href='"+targetUrl+"?currentPage="+(currentPage-1)+"&"+param+"'>上一页</a></li>");			
			} else {
				pageCode.append("<li class='disabled'><a href='"+targetUrl+"?currentPage="+(currentPage-1)+"&"+param+"'>上一页</a></li>");
			}
			for (int i = currentPage - 2; i <= currentPage + 2;i++){
				if(i<1||i>totalPage){
					continue;
				}
				if(i==currentPage){
					pageCode.append("<li class='active'><a href='"+targetUrl+"?currentPage="+i+"&"+param+"'>"+i+"</a></li>");	
				}else{
					pageCode.append("<li><a href='"+targetUrl+"?currentPage="+i+"&"+param+"'>"+i+"</a></li>");	
				}
			}
			if(currentPage<totalPage){
				pageCode.append("<li><a href='"+targetUrl+"?currentPage="+(currentPage+1)+"&"+param+"'>下一页</a></li>");		
			}else{
				pageCode.append("<li class='disabled'><a href='"+targetUrl+"?currentPage="+(currentPage+1)+"&"+param+"'>下一页</a></li>");	
			}
			pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"&"+param+"'>尾页</a></li>");
			return pageCode.toString();
		}
	}

}
