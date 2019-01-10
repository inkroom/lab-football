<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8">
	<title></title>
    <!--[if lt IE 9]>
      <script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${basePath }/resources_new/css/second.css">
    <link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
	<link href="${basePath }/resources/lib/slide/jquery.slideBox.css" rel="stylesheet">
	<style>
		.mydiv{
			position:relative;
		}
		.myul{
			position:fixed;
			top:90%;
			left:23%;		
		}
	</style>
</head>
	<body>
	<!--**********************************************学校简介*********************************-->
		<div class="schoolprofile">
			<ul class="main_ul">   
				<c:forEach items="${featureList }" var="feature">
					<li>
						<a href="${basePath }/${param.get('schoolUrl')}/feature/info/${typeFeature }/${feature.sc_feature }">
						<img src="${picPath }${feature.pic }">  
						<div class="main_text">
							<h4>${feature.title }</h4>
							<p>${feature.oper_date }</p>
							<p style="text-indent: 32px;text-overflow: ellipsis;">${feature.content_text }</p>
						</div>
						</a>
					</li>
				</c:forEach>
				<div class="myul">
			          <ul class="pagination pagination">${pageCode }</ul>
			    </div>
			</ul>
			
		</div>
		
	</body>
</html>		