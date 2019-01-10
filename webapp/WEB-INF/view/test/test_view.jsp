<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
		<form action="${base_path}/mobile/test.action" method="post">
			<input type="password" name="token" id="token">  <br>
			<input type="submit" name="button" id="button" value="提交到后台"> <br>
		</form>
	</body>

</html>