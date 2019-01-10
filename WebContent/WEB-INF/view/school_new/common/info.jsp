<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head lang="en">
	    <meta charset="UTF-8">
	    <title></title>
	    <!--[if lt IE 9]>
	      <script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	    <![endif]-->
	    <link href="${basePath }/resources_new/css/second.css" type="text/css" rel="stylesheet">
	</head>
	<body>
	    <div class="principalnote">
	        <h3 align="right">${headMessage.oper_date }</h3>
	        <article>
	        	${headMessage.content }
	        </article> 
		</div>
	</body>
</html>