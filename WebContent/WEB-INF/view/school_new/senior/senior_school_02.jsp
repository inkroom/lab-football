<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <jsp:include page="../common/index_include_junior.jsp"/>
</head>
<body>
<!--头部-->
<div id="bg" style="background:url(${basePath }/resources_new/img/bg3-2.png) no-repeat center;">
<div class="content">
  <div class="logo"><img src="${basePath }/resources_new/img/logo18.png"><span>${schoolName }</span></div>
    <div class="nav">
       <jsp:include page="../common/title2.jsp"/>
    </div>
</div>
  <section id="main">
        <aside id="side">
        <ul>
                <li style="background: url(${basePath }/resources_new/img/navbg.png) no-repeat;background-size: 100%;">校园新闻</li>
                <li>教育新闻</li>
                <li>合作交流</li>
                <li>通知公告</li>
        </ul>
        </aside>
        <nav class="breadcrumb">
            <h2>校园资讯</h2>
            <span>校园资讯</span>
            <span>/</span>
            <span>校园新闻</span>
        </nav>
        <section class="main_iframe">
            <c:if test="${scId  == null || scId == '' }">
			<iframe src="${basePath }/${param.get('schoolUrl')}/news/1"> </iframe>
		</c:if>
		<c:if test="${scId != null && scId != '' }">
			<iframe src="${basePath }/${param.get('schoolUrl')}/news/info/1/${scId}">
			</iframe>
		</c:if> 

        </section>
</section>
    <!--*********************************脚部开始**************************************-->
<div class="footer">
   <jsp:include page="../common/index_foot.jsp"/>
</div>
<script type="text/javascript" src="${basePath }/resources_new/js/primery_second.js"></script>
<script type="text/javascript">
	
	window.onload = function(){
		var asideArray = [ "${basePath }/${param.get('schoolUrl')}/news/1",
			"${basePath }/${param.get('schoolUrl')}/news/2",
			"${basePath }/${param.get('schoolUrl')}/news/3",
			"${basePath }/${param.get('schoolUrl')}/news/4" ];
		loadJS(asideArray);
	}
</script>
</body>
</html>