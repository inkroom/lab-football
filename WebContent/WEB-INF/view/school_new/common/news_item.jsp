<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <!--[if lt IE 9]>
      <script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
    <link href="${basePath }/resources_new/css/second.css" type="text/css" rel="stylesheet">
    <link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
	<link href="${basePath }/resources/lib/slide/jquery.slideBox.css" rel="stylesheet">
	<style type="text/css">
		.news{
			width: 870px;
			margin: 0px auto;
			padding: 0px 20px;
		
		}
		.news ul li{
			padding: 10px 0px;
			border-bottom: 1px solid lightgray;
			overflow: hidden;
		}
		.news ul li a{
			float: left;
			color: black;
		}
		.news ul li a:hover{
			text-decoration: underline;
			color: #551A8B;
		}
		.news ul li p{
			text-indent: 30px;
			color:gray;
			clear: both;
		}
		.news ul li span{
			color: orange;
			clear: both;
			display: block;
			float: right;
			margin-top: 10px;
		}
		.myul{
			position:fixed;
			top:90%;
			left:23%;		
		}
	</style>
</head>
<body>
<div class="news">
	<ul>
		<c:forEach items="${newsList }" var="news">
			<li>
				<a href="${basePath }/${param.get('schoolUrl')}/news/info/${typeNews }/${news.sc_news }">${news.title }</a>
				<p>${news.content_text }</p>
				<span>${news.oper_date }</span>
			</li>
		</c:forEach>
		<div class="myul">
            <ul class="pagination pagination">${pageCode }</ul>
      	</div>
	</ul>
</div>
</body>
</html>