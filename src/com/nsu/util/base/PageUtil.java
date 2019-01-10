package com.nsu.util.base;

/***
 *  用分页工具
 * @author MeiXiebing
 *
 */
public class PageUtil {
	public static String genPagination(String targetUrl,long totalNum,int currentPage,int pageSize,String param){
		long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		if(totalPage==0){
			String Msg = "<script type='text/javascript'>window.onload=function(){layer.alert('暂无数据！', {skin: 'layui-layer-molv',closeBtn: 0});}</script>";
			return Msg;
		}else{
			StringBuffer pageCode=new StringBuffer();
			pageCode.append("<li class='disabled'><a href='#' onclick=\"return checkUpdate()\">共 "+totalNum+" 条</a></li>");
			pageCode.append("<li><a href='"+targetUrl+"?page=1&"+param+"'>首页</a></li>");
			if(currentPage>1){
				pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage-1)+"&"+param+"'>上一页</a></li>");			
			}else{
				pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");		
			}
			for(int i=currentPage-2;i<=currentPage+2;i++){
				if(i<1||i>totalPage){
					continue;
				}
				if(i==currentPage){
					pageCode.append("<li class='active'><a href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");	
				}else{
					pageCode.append("<li><a href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");	
				}
			}
			if(currentPage<totalPage){
				pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage+1)+"&"+param+"'>下一页</a></li>");
			}else{
				pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");	
			}
			
			pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"&"+param+"'>尾页</a></li>");
			int temp = (totalNum%pageSize!=0)?1:0;
			int lastPage = (int) (totalNum/pageSize+temp);
			pageCode.append("<li class='disabled'><a href='#' onclick=\"return checkUpdate()\">共 "+lastPage+" 页</a></li>");
			return pageCode.toString();
		}
	}
	
	
	
	
}
