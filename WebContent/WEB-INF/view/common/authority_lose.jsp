<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>登陆失效</title>
<link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
</head>
<body>
<table border="0" cellpadding="0" cellspacing="1" class="default" style="width:50%" align="center">
	<tr>
		<td align="center" style="line-height:150%;padding:10px">	
		
		</td>
	</tr>
	<tr>
		<td align="center" style="line-height:150%;padding:10px">	
		
		</td>
	</tr>
	<tr>
		<td align="center" style="line-height:150%;padding:10px">	
		
		</td>
	</tr>
	<tr>
		<td align="center" style="line-height:150%;padding:10px">	
		
		</td>
	</tr>
	<tr>
		<td align="center" style="line-height:150%;padding:10px">	
		
		</td>
	</tr>
	<tr class="title">
		<td style="text-align:center;color:red;font-size: 30px">对不起，您的登录已经失效，请重新登录！</td>
	</tr>
	<tr>
		<td align="center" style="line-height:150%;padding:10px">	
		请选择以下任务继续：<br><br>
		<a onclick = "dispater()">转到登录页面</a>
		</td>
	</tr>
</tr>
</table>
</body>
<script>
	function dispater(){
		window.parent.location.href = 'login_view.action';
	}
</script>
</html>