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
<link rel="stylesheet" href="${base_path }/resources/css/common/findPassword.css">
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
            <li class="active">
                <div>
                    <i>3</i>
                    <p>设置新密码</p>
                </div>
                <i></i>
            </li>
            <li class="last">
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
        <form id = "forget_form" action="">
            <div class="pro-input">
                <label>设置新密码：</label>
                <div>
                    <div class="inputbox">
                        <input type="password"  name="new_password"  id="new_password">
                    </div>
                    
                </div>
            </div>
            <div class="pro-input">
                <label>再次输入新密码：</label>
                <div>
                    <div class="inputbox">
                        <input type="password" name="new_password_n"  id="new_password_n">
                    </div>
                </div>
                
            </div>
            <div class="pro-input">
                <a class="pro-btn"  id="Btn" style="margin-top:10px"><span>确定</span></a>
            </div>
            </form>
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

<script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>
<script>
$('#Btn').click(function(){
	var new_password = $("#new_password").val();
	var new_password_n = $("#new_password_n").val();
	if (new_password == "" || new_password_n == "" || new_password == null || new_password_n == null) {
		layer.alert("密码不能为空！"); 
		return;
	}
	if(new_password!=new_password_n){
		layer.msg('两次输入不一致！');
		return false;
	}
	var re = /^([^\u4e00-\u9fa5]){6,18}$/;
	if (!re.exec(new_password)) {
		layer.alert("密码只能为6-18位且不能为中文！");
		return;
	}
	encryptPassword($("#new_password"),'');
	$("#forget_form").attr("action", "${base_path}/forgetPassword/thirdPassword_check.action");
	$("#forget_form").submit();
  });
</script>
</html>