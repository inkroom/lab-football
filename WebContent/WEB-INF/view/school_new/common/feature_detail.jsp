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
	<div class="content">
		<div class="main_content">
			<h1>${featureMap.title }</h1>
			<h4>${featureMap.oper_date }</h4>
		    <img src="${picPath}${featureMap.pic }">
		    <p>${featureMap.content }</p>
		</div>
    </div>
</body>
</html>