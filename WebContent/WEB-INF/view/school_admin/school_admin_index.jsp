<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
    <title>学校管理</title>
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
	<span style="font-size:1.5em; margin-left:-3em; padding:10px; margin-top:20px;"  ><strong>后台管理系统</strong></span>
	<button class="btn btn-success" onclick="loginOut()">安全退出</button>
	<button class="btn btn-success" onclick="modifyPD()">修改密码</button>
</nav>
<div class="left">
    <div class="left-header">
    	欢迎:${_LOGIN_USER_.info }
    </div>
    <ul>
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>主&nbsp页&nbsp管&nbsp理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/admin_index_roll_picture" target="iframe">管理轮换图片</a></li>
                </ul>
            </div>
        </li>
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>学校概况管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/admin_info_01" target="iframe">管理校长寄语</a></li>
                    <li><a href="${basePath }/school_admin/add_people_view_01" target="iframe">添加学校领导</a></li>
                    <li><a href="${basePath }/school_admin/get_people_list_01" target="iframe">管理学校领导</a></li>
                    <li><a href="${basePath }/school_admin/admin_info_02" target="iframe">管理学校简介</a></li>
                    <li><a href="${basePath }/school_admin/add_people_view_02" target="iframe">添加名师介绍</a></li>
                    <li><a href="${basePath }/school_admin/get_people_list_02" target="iframe">管理名师介绍</a></li>
                    <li><a href="${basePath }/school_admin/admin_info_03" target="iframe">管理校园风光</a></li>
                    <li><a href="${basePath }/school_admin/add_people_view_03" target="iframe">添加学生风采</a></li>
                    <li><a href="${basePath }/school_admin/get_people_list_03" target="iframe">管理学生风采</a></li>
                </ul>
            </div>
        </li>
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>校园资讯管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/add_news_view_01" target="iframe">添加校园新闻</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_01" target="iframe">管理校园新闻</a></li>
                    <li><a href="${basePath }/school_admin/add_news_view_02" target="iframe">添加教育新闻</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_02" target="iframe">管理教育新闻</a></li>
                    <li><a href="${basePath }/school_admin/add_news_view_03" target="iframe">添加合作交流</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_03" target="iframe">管理合作交流</a></li>
                    <li><a href="${basePath }/school_admin/add_news_view_04" target="iframe">添加通知通告</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_04" target="iframe">管理通知通告</a></li>
                </ul>
            </div>
        </li>
        
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>足球特色管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/add_feature_view_01" target="iframe">添加足球课程</a></li>
                    <li><a href="${basePath }/school_admin/get_feature_list_01" target="iframe">管理足球课程</a></li>
                    <li><a href="${basePath }/school_admin/add_people_view_04" target="iframe">添加足球教练</a></li>
                    <li><a href="${basePath }/school_admin/get_people_list_04" target="iframe">管理足球教练</a></li>
                    <li><a href="${basePath }/school_admin/admin_info_04" target="iframe">管理体育设施</a></li>
                </ul>
            </div>
        </li>
        
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>足球比赛管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/add_feature_view_02" target="iframe">添加学校球队</a></li>
                    <li><a href="${basePath }/school_admin/get_feature_list_02" target="iframe">管理学校球队</a></li>
                    <li><a href="${basePath }/school_admin/add_feature_view_03" target="iframe">添加比赛展示</a></li>
                    <li><a href="${basePath }/school_admin/get_feature_list_03" target="iframe">管理比赛展示</a></li>
                    <li><a href="${basePath }/school_admin/add_people_view_05" target="iframe">添加校园球星</a></li>
                    <li><a href="${basePath }/school_admin/get_people_list_05" target="iframe">管理校园球星</a></li>
                </ul>
            </div>
        </li>
        
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>艺术特色管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/add_feature_view_04" target="iframe">添加艺术课程</a></li>
                    <li><a href="${basePath }/school_admin/get_feature_list_04" target="iframe">管理艺术课程</a></li>
                    <li><a href="${basePath }/school_admin/add_people_view_06" target="iframe">添加艺术教师</a></li>
                    <li><a href="${basePath }/school_admin/get_people_list_06" target="iframe">管理艺术教师</a></li>
                    <li><a href="${basePath }/school_admin/add_feature_view_05" target="iframe">添加作品展示</a></li>
                    <li><a href="${basePath }/school_admin/get_feature_list_05" target="iframe">管理作品展示</a></li>
                </ul>
            </div>
        </li>
        
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>学习推荐管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/add_news_view_05" target="iframe">添加升学推荐</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_05" target="iframe">管理升学推荐</a></li>
                    <li><a href="${basePath }/school_admin/add_news_view_06" target="iframe">添加课外练习</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_06" target="iframe">管理课外练习</a></li>
                    <li><a href="${basePath }/school_admin/add_news_view_07" target="iframe">添加自我拓展</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_07" target="iframe">管理自我拓展</a></li>
                </ul>
            </div>
        </li>
        
        
        <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>招生招聘管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/add_news_view_08" target="iframe">添加招生信息</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_08" target="iframe">管理招生信息</a></li>
                    <li><a href="${basePath }/school_admin/add_news_view_09" target="iframe">添加招聘信息</a></li>
                    <li><a href="${basePath }/school_admin/get_news_list_09" target="iframe">管理招聘信息</a></li>
                </ul>
            </div>
        </li>
        
         <li>
            <a class="menuHeader"><span><img src="${basePath }/resources/images/index/sidebar/student.png"></span>经费公示管理</a>
            <div class="menu hidden">
                <ul>
                    <li><a href="${basePath }/school_admin/update_in_found_view" target="iframe">管理收入经费公示</a></li>
        			<li><a href="${basePath }/school_admin/update_out_found_view" target="iframe">管理消费经费公示</a></li>
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
<script>
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
    	window.location.href="${basePath}/school_admin/common_logout";
    }
    function modifyPD(){
    	layer.open({
    	  type: 2,
    	  title: '<div style="text-align:center;">修改密码</div>',
    	  skin: 'layui-layer-rim', //加上边框
    	  area: ['500px', '260px'], //宽高
    	  id: 'modifypwd',
    	  closeBtn: 2,
    	  content: '${basePath}/school_admin/modify_password_view.action',
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