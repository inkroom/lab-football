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
	<style>
		.myul{
		  position:absolute;
		  margin-left:22%;
		  margin-top:90%; 
		}
	</style>
</head>
<body>
	<div class="leader">
		<c:forEach items="${peopleList }" var="people">
			<div class="person">
				<img src="${picPath }${people.pic}">
				<hgroup>
				<h3><a href="${basePath }/${param.get('schoolUrl')}/people/info/${typePeople }/${people.sc_people}" style="color: black">${people._name }</a></h3>
				</hgroup>
				<article class="remarks">
				<span>简介：</span>
				${people.content_text }
				</article>
			</div>
		</c:forEach>
	</div>
	<div class="myul"><ul class="pagination pagination">${pageCode }</ul></div>
	
</body>
</html>