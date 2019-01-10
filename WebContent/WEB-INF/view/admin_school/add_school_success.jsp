<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
<meta charset="utf-8">
<title>添加学校管理</title>
<link href="${basePath }/resources/lib/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
<link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.css"
	rel="stylesheet">
<link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.ext.css"
	rel="stylesheet">
<link href="${basePath }/resources/css/right.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<style>
.main table tr {
	background-color: #ffffff;
}

.main table tr th {
	text-align: center;
}

.main table tr td {
	text-align: center;
}

.container{
    padding: 20px;
}
.page-container {
	margin-top: 50px;
}

.control-label {
	text-align: center;
	line-height: 30px;
}

.td {
	layout-flow: vertical-ideographic;
}
</style>
<body>
	<div class="container">
		<div class="col-xs-12 text-center">
	       <h4 class="title">课程管理</h4>
	   	</div>
		<div class="main">
			<table class="table table-hover table-bordered text-center" id="myTab">

				<thead>
					<tr>
						<th colspan="3">添加学校</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<tr>
						<td>学校账号</td>
						<td>${school.username }</td>
					</tr>
					<tr>
						<td>学校名称</td>
						<td>${school.school_name }</td>
					</tr>
					<tr>
						<td>学校类型</td>
						<td>${school.school_type }</td>
					</tr>
					<tr>
						<td>访问地址</td>
						<td>${school.school_url }</td>
					</tr>
				</tbody>
				
				
				
				
				
				
			</table>
		</div>
	</div>
</body>

<script src="${basePath }/resources/lib/js/jquery-2.1.4.js"></script>
<script src="${basePath }/resources/lib/js/bootstrap.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/extend/layer.ext.js"></script>
</html>