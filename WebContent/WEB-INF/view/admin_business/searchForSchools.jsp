﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>学校查询</title>

<link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
<link href="${basePath }/resources/lib/css/jquery.dataTables.min.css" rel="stylesheet">
</head>
<style>
.selectBox {
	padding: 5px;
}

select {
	padding: 10px;
	margin-left: 20px;
	border-radius: 5px;
	border: 1px solid gainsboro;
	font-size: 15px;
	margin:10px !important;
}

.selectLabel {
	font-size: 15px;
	margin-left: 10px;
	font-family: '楷体';
}

.selectBox table tr td {
	padding: 2px;
}

.greenStyle {
	background-color: RGB(234, 254, 217);
}

.main table tr th {
	text-align: center;
}

.main table tr td {
	text-align: center;
}
.main{
	padding-top:10px;
}
</style>
<body>
	<input type="hidden" name="tokenP" id="tokenP" value="${token }">
	<div class="container">
		<div class="col-xs-12 text-center" style="margin-bottom: 20px;">
	       <h4 class="title">查找学校</h4>
	   	</div>
	   <form id="iejianrong" method="post" action="">
	   <input type="hidden" name="token" id="token" value="${token }">
	   <div class="header text-center">
            <div class="col-xs-2 text-right"></div>
            <div class="input-group col-xs-8" style="margin-bottom: 20px;">
                <input type="text" name="searchInput" placeholder="请输入学校名称(或者类别)" class="form-control input-lg"/>
                <span class="input-group-addon btn btn-primary" onclick="submitForm();">搜索</span>
            </div>
        </div>
        </form>
		<div class="main">
			<table class="table table-hover table-bordered text-center" id="myTab">
			<thead>
				<tr class="greenStyle">
					<td>学校ID</td>
					<td>学校名称</td>
					<td>学校类别</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${SchoolList}" var="list">
				<c:if test="${!empty list.s_id}">
				<tr>
					<td>${list.s_id}</td>
					<td>${list.school_name}</td>
					<td><c:choose>
						<c:when test="${list.school_type == '1'}">小学</c:when>
						<c:when test="${list.school_type == '2'}">初中</c:when>
						<c:when test="${list.school_type == '3'}">高中</c:when>
					</c:choose></td>
					<td><button type="button" name='${list.s_id}' onclick="managed_Ads(this);" class="btn btn-success radius">管理</button></td>
				</tr>
				</c:if>
			</c:forEach>
			</tbody>
			</table>
			<div class="col-sm-12 text-center">
				<nav><ul class="pagination pagination">${pageCode}</ul></nav>
			</div>
		</div>
	</div>
</body>
<script src="${basePath }/resources/lib/js/jquery-2.1.4.js"></script>
<script src="${basePath }/resources/lib/js/bootstrap.js"></script>
<script src="${basePath }/resources/lib/js/jquery.dataTables.min.js"></script>
<script src="${basePath }/resources/lib/js/json-minified.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
<script type="text/javascript">
function submitForm() {
	$("#iejianrong").attr("action","${basePath}/admin_business/Search!searchSchool.action?adType="+'${adType }');
	$("#iejianrong").submit();
}
function managed_Ads(school_id){
	var s_id = school_id.name;
	window.location.href = "${basePath}/admin_business/Managed!viewForManaged.action?token="+'${token }'+"&s_id="+s_id+"&adType="+'${adType}';
}
</script>
<script type="text/javascript">
$(function(){
	layer.ready(function(){
		if('${success}'!=""){
			layer.msg('${success}');
		}
		if('${error}'!=""){
			layer.msg('${error}');
		}
		if('${info}'!=""){
			layer.msg('${info}');
		}
    });
})
</script>
</html>