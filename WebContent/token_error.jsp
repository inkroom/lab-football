<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>请不要重复提交</title>
<link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
</head>
<body>
	<input type="hidden" name="tokenP" id="tokenP" value="${token }">
	<table border="0" cellpadding="0" cellspacing="1" class="default" style="width:50%;margin-top:10%;" align="center">
	<tr class="title">
		<td style="text-align:center;color:red;line-height:250%;padding:10px;">对不起，此页面为高度安全页面，为了您信息的安全,请不要点击浏览器的回退、刷新按钮或重复提交数据！</td>
	</tr>
	<tr>
		<td align="center" style="line-height:250%;padding:10px">	
		请选择以下任务继续(<span style="color:red;">若选择返回选项请返回后刷新页面</span>)：<br><br>
		<a style="font-size:2em;" href="javascript:history.go(-1),location.reload()">返回</a> | <a style="font-size:2em;" onclick = "dispater()" href="#">转到主页面</a>
		</td>
	</tr>
	<tr>
		<td align="center">
			<span style="color:red;line-height:250%;padding:10px;">若返回选项无效，请使用左侧导航栏经行重新导航。</span>
		<td>
	</td>
	</tr>
</table>
</body>
<script type="text/javascript">
function dispater(){
	window.parent.location.href = 'login_view.action';
}
</script>
</html>