<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>球员管理系统</title>
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
<style type="text/css">
body{text-align:center} 
.sx{
	margin:100px auto;
	width:100%;
	text-center:center;
	height:40px;
}
.sx button{
	margin:0 10px;
}
</style>
</head>
<body>
<div class="sx">
	<div>
	  当前登录已失效,
	   请点击<button type="submit" class="btn btn-success radius"  name="" onclick="modaldemo()">登录</button>按钮，重新登录
</div>
</div>
</body>
<script type="text/javascript">

function modaldemo(){
	top.location.href="${base_path}/org/login.html";
}
</script>
</html>