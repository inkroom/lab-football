<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
    <title>业务管理员主页</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
    <link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath }/resources/css/index.css" rel="stylesheet">
    <link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.css" rel="stylesheet">
    <link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.ext.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style>
        .menu{
            height: 0px;
            overflow: hidden;
        }
    </style>
</head>
<body>
<nav style="padding-left:50%; padding-top:1.5em;">
	<span style="font-size:1.5em; margin-left:-3em; padding:10px; margin-top:20px;"><strong>后台管理系统</strong></span>
	<button class="btn btn-success" onclick="loginOut()">安全退出</button>
	<button class="btn btn-success" onclick="modifyPD()">修改密码</button>
</nav>
<div class="left">
	<input type="hidden" name="token" id="token" value="${token }">
    <div class="left-header">
    	欢迎:${_LOGIN_USER_.info }
    </div>
    <ul>
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>广告管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a id="floatingAds" onclick="floatingAds()" target="iframe">浮动广告</a></li>
                    <li><a id="rollingAds" onclick="rollingAds()" target="iframe">滚动广告</a></li>
                    <li><a id="textAds" onclick="textAds()" target="iframe">文字广告</a></li>
                    <li><a id="dropdownAds" onclick="dropdownAds()" target="iframe">下拉广告</a></li>
                </ul>
            </div>
        </li>
	</ul>
    <div class="left-footer">
        <div class="left-footer-content">
            <p><strong>主办：</strong>四川省教育厅体卫艺处</p>
            <p><strong>版本号：</strong>1.0</p>
            <p><strong>技术支持：</strong>成都东软学院</p>
            <p>COPYRIGHT © 2016</p>
        </div>

    </div>

</div>
<div class="right" >
    <div id="iframe_box">
        <div class="show_iframe" >
            <iframe id="iframeId" style="bottom: 0;height: 90vh;width: 100%;" name="iframe" scrolling="yes" frameborder="0" src=""></iframe>
        </div>
    </div>
</div>
<script src="${basePath }/resources/lib/js/jquery-2.1.4.js"></script>
<script src="${basePath }/resources/lib/js/bootstrap.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/extend/layer.ext.js"></script>
<script type="text/javascript">
document.getElementById("iframeId").onload = function() {
	alert("myframe is loaded");
	var tokena = $('#iframeId').contents().find('#tokenP')[0];
	alert("子页面token值为："+tokena.value);
	$("#token").attr('value', tokena.value);
	var token = document.getElementById("token");
	alert("父页面token值为："+token.value);
};
function floatingAds(){
	var token = document.getElementById("token");
	$("#floatingAds").attr('href', "${basePath }/admin_business/Search!viewForSearch.action?adType=1&token="+token.value);
	
}
function rollingAds(){
	var token = document.getElementById("token");
	$("#rollingAds").attr('href', "${basePath }/admin_business/Search!viewForSearch.action?adType=2&token="+token.value);
}
function textAds(){
	$("#textAds").attr('href', "${basePath }/admin_business/Search!viewForSearch.action?adType=3&token="+token.value);
}
function dropdownAds(){
	var token = document.getElementById("token");
	$("#dropdownAds").attr('href', "${basePath }/admin_business/Search!viewForSearch.action?adType=4&token="+token.value);
}
</script>
<script type="text/javascript">
    $(function(){
        $(".menuHeader").click(function(){
            closeMenu();
            showMenu(this);
        });
    });
    $(".left a[target='iframe']").click(function(){
    	layer.msg('请稍后...',{
			icon:16
		});
    });
    $("#iframeId").on("load",function(){
    	 layer.closeAll();
    });
    function loginOut(){
    	window.location.href="${basePath}/admin_school/common_logout";
    }
    function modifyPD(){
    	layer.open({
    	  type: 2,
    	  title: '<div style="text-align:center;">修改密码</div>',
    	  skin: 'layui-layer-rim', //加上边框
    	  area: ['500px', '260px'], //宽高
    	  id: 'modifypwd',
    	  closeBtn: 2,
    	  content: '${basePath}/school/modifyPassword!view.action',
    		});
    }
    function showMenu(dom){
        var menu = $(dom).parents("li").find(".menu")
        if(menu.hasClass("hidden")){
            menu.removeClass("hidden");
            menu.addClass("show");
            var height = menu.find("ul").height();
            menu.animate({
                height:height+"px"
            },300);
        }else{
            menu.animate({
                height:"0px"
            },300,false,function(){
                menu.addClass("hidden");
                menu.removeClass("show");
            });
        }
    }

    function closeMenu(){
        var menus = $(".menu.show");
        menus.animate({
            height:"0px"
        },300,false,function(){
            menus.addClass("hidden");
            menus.removeClass("show");
        });
    }
</script>
</body>
</html>