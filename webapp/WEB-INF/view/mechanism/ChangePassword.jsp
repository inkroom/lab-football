<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/respond.min.js"></script>--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/css/H-ui.min.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/css/H-ui.admin.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/lib/Hui-iconfont/1.0.8/iconfont.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/css/style.css" />--%>
    <title>四川省中小学生艺术测评系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="index.html">四川省中小学生艺术测评系统</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">四川省中小学生艺术测评系统</span> <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">机构端<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="#">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe705;</i> 账号管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="修改密码.html" title="修改密码">修改密码</a>
                    <li><a href="重置下级密码.html" title="重置下级密码">重置下级密码</a>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="数据分析.html" title="数据分析">数据分析</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe62b;</i> 机构认证<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="机构认证.html" title="机构认证">机构认证</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="首页.html">首页</a> <span class="c-gray en">&gt;</span> 账号管理 <span class="c-gray en">&gt;</span> 修改密码 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="Hui-article">
        <article class="cl pd-40 mt-20">
            <form action="" method="post" class="form form-horizontal" id="form-teacher">
                <h2 class="text-c">修改密码</h2>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>旧密码：</label>
                    <div class="formControls col-xs-8 col-sm-7">
                        <input type="password" class="input-text" value="" placeholder="旧密码" id="oldpassword" name="oldpassword">
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
                    <div class="formControls col-xs-8 col-sm-7">
                        <input type="password" class="input-text" autocomplete="off" value="" placeholder="新密码" id="password" name="password">
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
                    <div class="formControls col-xs-8 col-sm-7">
                        <input type="password" class="input-text" autocomplete="off"  placeholder="确认新密码" id="password2" name="password2">
                    </div>
                </div>
                <div class="row cl">
                    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                        <input class="my-btn" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                        <input class="my-btn" type="reset" value="&nbsp;&nbsp;重置&nbsp;&nbsp;">
                    </div>
                </div>
            </form>
        </article>
    </div>
</section>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/jquery/1.9.1/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/layer/2.4/layer.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/js/H-ui.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/js/H-ui.admin.page.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/jquery.validation/1.14.0/jquery.validate.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/jquery.validation/1.14.0/validate-methods.js"></script>--%>
<%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/jquery.validation/1.14.0/messages_zh.js"></script>--%>
<script>
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-teacher").validate({
            rules:{
                oldpassword:{
                    required:true,
                    minlength:4,
                    maxlength:16
                },
                password:{
                    required:true,
                    minlength:4,
                    maxlength:16
                },
                password2:{
                    required:true,
                    minlength:4,
                    maxlength:16,
                    equalTo: "#password"
                }
            },
//            onkeyup:false,
//            focusCleanup:true,
//            success:"valid",
//            submitHandler:function(form){
//                $(form).ajaxSubmit();
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.$('.btn-refresh').click();
//                parent.layer.close(index);
//            }
            onkeyup:false,
            focusCleanup:true,
            submitHandler:function(form){
                if(confirm("确认提交？")){
                    alert("密码修改成功！")
                }
                else {
                    return false
                }
            }
        });
    });
</script>
</body>
</html>