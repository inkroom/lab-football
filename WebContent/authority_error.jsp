<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>权限不足</title>
<link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
</head>
<body>
<input type="hidden" name="tokenP" id="tokenP" value="${token }">
<table border="0" cellpadding="0" cellspacing="1" class="default" style="width:50%;margin-top: 100px;" align="center">
	<tr class="title">
		<td style="text-align:center;color:red;font-size: 30px">对不起，您没有执行此操作的权限！请登出后执行其他操作</td>
	</tr>
	<tr>
		<td align="center" style="line-height:150%;padding:10px;font-size: 15px">	
		请选择以下任务继续：<br><br>
		<a href="javascript:history.go(-1)" style="font-size: 15px">返回</a> | <a onclick = "dispater()" style="font-size: 15px">转到主页</a>
		</td>
	</tr>
</tr>
</table>
</body>
<script>
	function dispater(){
		window.parent.location.href = 'common_logout.action';
	}
</script>
</html>