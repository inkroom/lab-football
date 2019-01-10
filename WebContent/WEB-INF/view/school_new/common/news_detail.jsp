<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="${basePath }/resources_new/css/third.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="news">
	<h4>${newsMap.title }</h4>
	<p>${newsMap.oper_date }</p>
	<section class="maintext">
		${newsMap.content }
	</section>
</div>
</body>
</html>