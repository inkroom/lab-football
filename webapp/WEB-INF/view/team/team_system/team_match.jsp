<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>个人信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球队管理<span class="c-gray en">&gt;</span> 球队比赛<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form name="Matchform" accept-charset="utf-8" id="Matchform" method="post" action="">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <a class="text-r f-r">
            <input type="text" class="input-text" style="width:350px" placeholder="请输入你想查看的赛事" id="C_NAME" name="C_NAME" value="${C_NAME}">
            <button type="button" class="btn btn-success radius"  id="idsub" name="idsub"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </a>
    </div>
</form>   
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg  mt-20">
            <thead class="text-c">
            <tr>
                <th>赛事名称</th>
                <th>赛事类型</th>
                <th>赛事级别</th>
                <th>所属机构</th>
                <th>足球组别</th>
                <th>赛事时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody class="text-c">
	            <c:if test="${page.records == null || fn:length(page.records) == 0}">
	              <tr class="text-c">
	              	<td colspan="7" rowspan="5"><span style="color:#ff0000">无赛事信息</span></td>
	              </tr>
	        	</c:if>
	            <c:forEach var="com" items="${page.records}">
		            <tr>		  
		                <td>${com.getCOM_NAME()}</td>
		                <td>${com.getCOM_TYPE()}</td>
		                <td>${com.getCOM_LEVEL()}</td>
		                <td>${com.getCOM_ORGAZITION()}</td>
		                <td>${com.getCOM_GROUNP()}</td>
		                <td>${com.getCOM_START()} — ${com.getCOM_END()}</td> 
		                <td><a href="${base_path }/team/team_match_viewtwo/${com.comID}/1.html" class="btn btn-success radius mr-10">查看赛程</a></td>
		            </tr>
	            </c:forEach>
            </tbody> 
              
        </table>
    </div>
    <c:if test="${page.totalPageNum > 1}">
         	<div align="center">
 				<%@include file="page.jsp"%>
 			</div>
     </c:if>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script>


    <!--查找-->
    $("#idsub").click(function(){
	     var value = $("#C_NAME").val();
	     if(value.length<10){
	        $("#Matchform").attr("action", "${base_path}/team/team_match_find.action");
	    	$('#Matchform').submit();
	     }else{
	     	layer.msg('查询失败');
	     }
    });
    
    
    
    
</script>
</body>
</html>