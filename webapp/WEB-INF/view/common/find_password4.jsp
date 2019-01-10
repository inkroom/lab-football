<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>找回密码</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
<link rel="stylesheet" href="${base_path }/resources/css/common/findPassword.css">ssword.css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>
<body>
<div class="pro-head">
<div class="pro-title">
			<div>
				<span>足球管理系统</span> <span>找回密码</span>
			</div>
		</div>
    <div class="pro-box">
        <ul class="process">
            <li class="passed">
                <div>
                    <i>1</i>
                    <p>填写帐号</p>
                </div>
                <i></i>
            </li>
            <li class="passed">
                <div>
                    <i>2</i>
                    <p>身份验证</p>
                </div>
                <i></i>
            </li>
            <li class="passed">
                <div>
                    <i>3</i>
                    <p>设置新密码</p>
                </div>
                <i></i>
            </li>
            <li class="active">
                <div>
                    <i>4</i>
                    <p>完成</p>
                </div>
            </li>
        </ul>
    </div>
</div>
<div class="pro1-main">
    <div class="pro1-content">
        <div class="pro-completed error">
            <div class="completed—pic"></div>
            <p class="completed-text">密码修改失败</p>
            <p class="completed-tips">您的请求不合法，请按照正确的流程执行</p>
            <div class="pro-input">
                <a class="pro-btn" href="${base_path }/forgetPassword/firstPassword/1.html" style="margin-top:10px"><span>返回重试</span></a>
            </div>
        </div>
    </div>
</div>
</div>
</body>


<!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->

<script>
</script>
</html>