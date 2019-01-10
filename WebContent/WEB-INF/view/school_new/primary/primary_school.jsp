<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${schoolName }</title>
    <link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${basePath }/resources_new/css/main.css">
  	<link rel="stylesheet" href="${basePath }/resources_new/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath }/resources_new/css/footer.css">
    <link rel="stylesheet" href="${basePath }/resources_new/css/advertise.css">
    <style>
       body{
            background:url(${basePath }/resources_new/img/bg1-1.png) no-repeat center;
        }        
        
    </style>
</head>
<body>
<div id="bg" style="background:url(${basePath }/resources_new/img/bg1-1.png) no-repeat center">
<jsp:include page="../common/index_ad.jsp"/>
<div class="content">
   	<div class="logo"><img src="${basePath }/resources_new/img/logo18.png"><span>${schoolName }</span></div>
    <div class="nav">
        <jsp:include page="../common/title.jsp"/>
    </div>
    <section id="carousel-example-generic" class="carousel slide lunbo" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            <li data-target="#carousel-example-generic" data-slide-to="4"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            
            <div class="item active">
                <img src="${picPath }${indexRollPicture[0].save_path}" alt="">
            </div>
            <div class="item">
                <img src="${picPath }${indexRollPicture[1].save_path}" alt="">
            </div>
            <div class="item">
                <img src="${picPath }${indexRollPicture[2].save_path}" alt="">
            </div>
            <div class="item">
                <img src="${picPath }${indexRollPicture[3].save_path}" alt="">
            </div>
            <div class="item">
                <img src="${picPath }${indexRollPicture[4].save_path}" alt="">
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </section>
   	<jsp:include page="../common/index_content.jsp"/>
   	<marquee class="ad" behavior="alternate" onmouseover="this.stop()" onmouseout="this.start()">
		    <ul>
		    	<c:if test="${advertisementSecond[1]!=null }">
					<li><a href=""${advertisementSecond[1].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[1].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[2]!=null }">
					<li><a href=""${advertisementSecond[2].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[2].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[3]!=null }">
					<li><a href=""${advertisementSecond[3].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[3].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[4]!=null }">
					<li><a href=""${advertisementSecond[4].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[4].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[5]!=null }">
					<li><a href=""${advertisementSecond[5].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[5].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[6]!=null }">
					<li><a href=""${advertisementSecond[6].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[6].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[7]!=null }">
					<li><a href=""${advertisementSecond[7].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[7].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[8]!=null }">
					<li><a href=""${advertisementSecond[8].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[8].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[9]!=null }">
					<li><a href=""${advertisementSecond[9].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[9].save_path}"></a></li>
				</c:if>
				<c:if test="${advertisementSecond[0]!=null }">
					<li><a href=""${advertisementSecond[10].url_path}><img style="width:200px;height:40px;" src="${picPath }${advertisementSecond[0].save_path}"></a></li>
				</c:if>
		    </ul>
		</marquee>
</div>
	<div class="footer">
		<jsp:include page="../common/index_foot.jsp"/>
	</div>
<script src="${basePath }/resources_new/js/jquery-3.1.1.min.js" type="application/javascript"></script>
<script src="${basePath }/resources_new/js/bootstrap.min.js" type="application/javascript"></script>
<script type="text/javascript">
    $("#close1").click(function(){
        $("#ad1").css({"display":"none"});
    });
    $("#close2").click(function(){
        $("#ad2").css({"display":"none"});
    });
</script>
</body>
</html>