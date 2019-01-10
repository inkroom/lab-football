<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="${base_path}/resources/css/team/pageGroup.css"/>

<c:if test="${page!=null}">
	<div id="pageGro" class="pageCenter">		
			<ul class='page'>
         
  			</ul>	
  	</div>  	
</c:if>
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.js"></script>	
<script type="text/javascript">
$(function(){
	//根据总页数判断，如果小于5页，则显示所有页数，如果大于5页，则显示5页。根据当前点击的页数生成
	var pageCount = '${page.totalPageNum}';//模拟后台总页数
	var pageNowNum = '${page.pageNum}';
	if(pageNowNum!=''){
		pageNowNum = parseInt(pageNowNum);
	}
	if(pageCount!=''){
		pageCount = parseInt(pageCount);
	}
	//生成分页按钮
	if(pageCount>5){
		pageGroup(pageNowNum,pageCount)
	}else{
		page_icon(1,pageCount,pageNowNum);
	}
	$(function(){
		$("#pageGro li").removeClass("on");//清除所有选中
		$("#pageGro ul li").eq(pageNowNum-1).addClass("on");//设置选中
	});
	
	//点击分页按钮触发
	$("#pageGro li").on("click",function(){
		layer.load();
		var pageNum = $(this).attr("page-data");//获取当前页数
		window.location.replace('${base_path}${page.url}'+pageNum+'.action');
	});
});

//点击跳转页面
function pageGroup(pageNum,pageCount){
	var pageNowNum = '${page.pageNum}';
	switch(pageNum){
		case 1:
			page_icon(1,5,pageNowNum);
		break;
		case 2:
			page_icon(1,5,pageNowNum);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,pageNowNum);
		break;
		case pageCount:
			page_icon(pageCount-4,pageCount,pageNowNum);
		break;
		default:
			page_icon(pageNum-2,pageNum+2,pageNowNum);
		break;
	}
}

//根据当前选中页生成页面点击按钮
function page_icon(page,count,eq){
	var ul_html = "";
 	var prePageClass ="pageItem";
    var nextPageClass = "pageItem";
    var pageNowNum = '${page.pageNum}';
    if('${page.prePageNum}'<=0){
        prePageClass="pageItem";
    }
    if('${page.nextPageNum}'>'${page.totalPageNum}'){
        nextPageClass="pageItem";
    }
	if('${page.pageNum}'>1){
		 //首页
	    ul_html += "<li class='"+prePageClass+"' page-data='1' page-rel='firstpage'>首页</li>";
	    //上一页
	    ul_html += "<li class='"+prePageClass+"' page-data='${page.prePageNum}' page-rel='firstpage'>上一页</li>";
	}
	
    for(var i=page; i<=count; i++){
        var itemPageClass = "pageItem";
        if(i==pageNowNum){
            itemPageClass = "pageItemActive";
        }
        ul_html +="<li class='"+itemPageClass+"' page-data='"+i+"' page-rel='itempage'>"+i+"</li>";
	}
	if('${page.pageNum}' != '${page.totalPageNum}'){
		//下一页
		ul_html+="<li class='"+nextPageClass+"' page-data='${page.nextPageNum}' page-rel='nextpage'>下一页</li>";
		//尾页
		ul_html+="<li class='"+nextPageClass+"' page-data='${page.totalPageNum}' page-rel='lastpage'>尾页</li>";
	}
	$("#pageGro ul").html(ul_html);
}

</script>