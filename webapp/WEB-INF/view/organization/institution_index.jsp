<%@ page import="com.nsu.common.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css"/>

    <!--[if lt IE 9]>
    <script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->


    <title>机构系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body>


<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo f-l mr-10 hidden-xs">机构系统</a>
            <nav id="Hui-userbar"
                 class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"><a href="#"
                                                           class="dropDown_A">${org_usermap.ORG_NAME} <i
                            class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onClick="modaldemo1()">修改密码</a></li>
                            <li><a href="#" onclick="modaldemo()">更改手机号</a></li>
                            <li><a href="#" onclick="modaldemo2()">更改邮箱</a></li>
                            <li><a href="javascript:;" onClick="modaldemo3()">修改信息</a></li>

                            <li><a href="${base_path}/org/logOut.action">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg"><a onclick="check()"
                                        data-href="message-all.html" href="javascript:void(0)"
                                        title="消息中心"><span class="badge badge-danger" id="countMessage"></span><i
                            class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i></a>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value=""/>
    <div class="menu_dropdown bk_2">
        <dl id="menu-release">
            <dt>
                <i class="Hui-iconfont">&#xe613;</i> 比赛管理<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="${base_path }/org/to_create_match.html"
                           data-title="发布比赛" href="javascript:void(0)">发布比赛</a></li>
                    <li><a data-href="${base_path }/org/to_modify_match_view.html"
                           data-title="修改比赛" href="javascript:void(0)">修改比赛</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-match">
            <dt>
                <i class="Hui-iconfont">&#xe620;</i> 管理申请<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li id="team_application"><a id="team" data-href="${base_path }/org/team_application.html"
                                                 data-title="球队申请" href="javascript:void(0)">球队申请</a></li>
                    <li id="match_application"><a id="macth" data-href="${base_path }/org/macth_application.html"
                                                  data-title="赛事申请" href="javascript:void(0)">赛事申请</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-info">
            <dt>
                <i class="Hui-iconfont">&#xe620;</i> 信息公示<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>
            <dd>
                <ul>
                    <li><a data-href="${base_path }/info/gameInfoOrg.html"
                           data-title="比赛公示" href="javascript:void(0)">比赛公示</a></li>
                    <li><a data-href="${base_path }/info/teamInfoOrg.html"
                           data-title="球队公示" href="javascript:void(0)">球队公示</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-schedule">


            <dt>
                <i class="Hui-iconfont">&#xe620;</i> 赛程安排<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
            </dt>


            <dd>
                <ul>
                    <li><a data-href="${base_path}/org/race_schedule_view.html" data-title="赛事安排"
                           href="javascript:void(0)">赛程安排</a></li>
                    <li><a data-href="${base_path}/org/com_end_view.html" data-title="结束赛事" href="javascript:void(0)">结束赛事</a>
                    </li>
                </ul>
            </dd>


        </dl>

    </div>
</aside>
<div class="dislpayArrow hidden-xs">
    <a class="pngfix" href="javascript:void(0);"
       onClick="displaynavbar(this)"></a>
</div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="消息中心"
                                         data-href="${base_path }/message/message-all.html">消息中心</span> <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group">
            <a id="js-tabNav-prev" class="btn radius btn-default size-S"
               href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                id="js-tabNav-next" class="btn radius btn-default size-S"
                href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
        </div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display: none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="${base_path }/message/message-all.html"></iframe>
        </div>
    </div>
</section>


<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前</li>
        <li id="closeall">关闭全部</li>
    </ul>
</div>
<!--模态框-->
<div id="modal-demo" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">更改手机号</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <form action="#">
                    <table class="table">
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">原手机号码:</p></td>
                            <td>
                                <input type="text" class="input-text radius" name="old_phone" id="old_phone"
                                       disabled="disabled"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">验证码:</p></td>
                            <td><input type="text" class="input-text radius" placeholder="验证码" id="oldPhoneCode"
                                       style="width: 70%"/>
                                <input type="button" class="btn btn-default radius sendcode"
                                       onclick="MatchCodeDialog(1)" value="获取验证码"></td>
                        </tr>
                    </table>
                </form>

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="next-step-phone">下一步</button>
                <button id="closed-1" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-demo2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">更改邮箱</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <form>
                    <table class="table">
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">旧邮箱地址:</p></td>
                            <td>
                                <input type="text" class="input-text radius" name="old_email" id="old_email"
                                       disabled="disabled"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">验证码:</p></td>
                            <td><input type="text" class="input-text radius" placeholder="验证码" id="oldEmailCode"
                                       style="width: 70%"/>
                                <input type="button" class="btn btn-default radius sendemailcode"
                                       onclick="old_email_code()" value="获取验证码"></input></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="next-step">下一步</button>
                <button id="closed" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<div id="modal-demo1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">修改密码</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <form class="form formControls">
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">旧密码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="password" class="input-text radius" value="" placeholder=""
                                       name="old_password" id="old_password">
                            </div>
                        </div>
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">新密码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="password" class="input-text radius" value="" placeholder=""
                                       name="new_password" id="new_password">
                            </div>
                        </div>
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">确认密码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="password" class="input-text radius" value="" placeholder=""
                                       name="new_password_n" id="new_password_n">
                            </div>
                        </div>
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">手机动态码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="text" class="input-text radius" value="" placeholder=""
                                       name="phone_dynamic_code" id="phone_dynamic_code">
                            </div>
                        </div>
                    </form>

                </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="updatepassword('${_SALT_IN_SESSION_}')">确认修改</button>

                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">完善信息</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <form class="form form-horizontal mt-20">
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">机构名称：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text " disabled="disabled" value="${map_name.ORG_NAME }"
                                   placeholder="" id="name" name="name">
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">联系人姓名：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text" value="" placeholder="姓名" id="user_name"
                                   name="user_name"/>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">联系人电话：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text" value="" placeholder="电话" id="user_phone"
                                   name="user_phone" onblur="checkPhone();"/>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">联系人邮箱：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text" value="" placeholder="邮箱" id="user_email"
                                   name="user_email" onblur="checkEmail();"/>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">手机验证码：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text" id="phoneCode" name="phoneCode" placeholder="验证码"
                                   style="width: 30%"/>
                            <input class="btn btn-default radius" type="button" id="sendMessage" value="获取验证码"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="but1" name="but1" type="button">确认</button>
            </div>
        </div>
    </div>
</div>

<!--修改信息-->
<form class="form form-horizontal mt-20" method="post">
    <div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title">修改信息</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true"
                       href="javascript:void();">×</a>
                </div>
                <div class="modal-body">

                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">机构名称：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text "
                                   value="${map_name.ORG_NAME }" placeholder="" id="orgName"
                                   name="orgName">
                        </div>
                    </div>

                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3">联系人姓名：</label>
                        <div class="formControls col-xs-8 col-sm-8">
                            <input type="text" class="input-text" value="${map_name.A_NAME }" placeholder=""
                                   id="userName" name="userName"/>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="but2" name="but2" type="button">确认</button>
                </div>
            </div>
        </div>
    </div>
</form>
<input type="text" id="base_path" hidden="" value="${base_path}">
<input type="text" id="typePhone" hidden="" value="3">
<input type="text" id="SMSPhone" hidden="" value="${sessionScope.get('SMSPhone') }">
<input type="text" id="SMSEmail" hidden="" value="${sessionScope.get('SMSEmail') }">
<input type="text" id="SMSPhoneCode" hidden="" value="${sessionScope.get('VERIFY_CODE') }">
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript"
        src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript"
        src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/common/updateEmail.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/common/update_phone_password.js"></script>
<script>


    /*检查Session是否存在*/
    $(function () {
        var strSession = "<%=request.getSession().getAttribute(Constants.LOGIN_USER) %>".toString();
        if (strSession == "") {
            window.location.href = "${base_path}/org/to_login.html"
            return false;
        }
    })


    $(function () {
        if ('${map_name.A_NAME}' == null || '${map_name.A_NAME}' == '' || '${map_name.A_PHONE}' == null || '${map_name.A_PHONE}' == '') {
            $('#myModal').modal({
                show: true,
                backdrop: true
            });
        }
    })


    var InterValObj; //timer变量，控制时间
    var count = 120; //间隔函数，120秒执行
    var curCount;//当前剩余秒数
    //发送验证码
    $('#sendMessage').on('click', function () {
        var user_phone = $('#user_phone').val();
        if (user_phone == "" || user_phone == null) {
            layer.alert("手机号码不能为空！");
            return;
        }
        if (!(/^[1]{1}[2-578]{1}[0-9]{9}$/.test($("#user_phone").val()))) {
            layer.alert("手机号码格式有误，请重填");
            return;
        }
        curCount = count;
        //设置button效果，开始计时
        $("#sendMessage").attr("disabled", "true");
        //向后台发送处理数据
        $.ajax({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: base_path + "/sms/getRegisterCode.action",
            type: "POST",
            dataType: "json",
            data: {"phone": user_phone, "type": 3},
            success: function (data) {
                if (data.errorCode == 1) {
                    layer.msg(data.msg, {icon: 1});
                    $("#sendMessage").val("重新获取(" + curCount + ")");
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次

                } else {
                    layer.msg(data.msg, {icon: 0});
                    $("#sendMessage").removeAttr("disabled");
                    $("#sendMessage").val("重新获取");
                }
            }
        });
    });


    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#sendMessage").removeAttr("disabled");//启用按钮
            $("#sendMessage").val("重新获取");
        }
        else {
            curCount--;
            $("#sendMessage").val("重新获取(" + curCount + ")");
        }
    }

    //完善信息
    $('#but1').click(function () {
        var username = $("#user_name").val();
        var userphone = $("#user_phone").val();
        var useremail = $("#user_email").val();
        var phoneCode = $("#phoneCode").val();
        var state = false;
        if (username == "" || username == null || userphone == "" || userphone == null || useremail == "" || useremail == null || phoneCode == "" || phoneCode == null) {
            layer.alert("完善信息有误，请重新填写!");
            return;
        }
        if (!(/^([a-zA-Z]*|[\u4E00-\u9FA5\uf900-\ufa2d]*){3,13}$/.test($("#user_name").val()))) {
            layer.alert("联系人姓名格式错误,应3-13中英文!");
            return;
        }

        if (!(/^[1]{1}[2-578]{1}[0-9]{9}$/.test($("#user_phone").val()))) {
            layer.alert("手机号码格式错误，请重填!");
            return;
        }

        if (!(/^[A-Za-z0-9\u4e00-\u9fa5._-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test($("#user_email").val()))) {
            layer.alert("邮箱格式错误，请重填!");
            return;
        }

        $.ajax({
            type: "POST",
            url: base_path + "/sms/validateMobileCode.action",
            data: {
                "mobile": userphone,
                "randomCode": $('#phoneCode').val(),
                "type": "3"
            },
            dataType: 'json',
            success: function (json) {
                if (json.success) {
                    $.ajax({
                        type: "POST",
                        url: "${base_path}/org/organization_EVPI.action",
                        data: {user_name: username, user_phone: userphone, user_email: useremail},
                        dataType: "json",
                        success: function (result) {
                            layer.msg("完善信息成功！");
                            console.log(result.info);
                            window.location.reload();
                        },
                        error: function (error) {
                            layer.msg("服务器忙，请重新尝试！");
                            console.log(error);
                        }
                    });
                } else {
                    layer.alert("验证码错误！");
                }
            },
            error: function (error) {
                layer.msg("提交失败")
            }
        });
    });


    //修改信息
    $('#but2').click(function () {
        var orgName = $("#orgName").val();
        var userName = $("#userName").val();

        if (orgName == "" || orgName == null || userName == "" || userName == null) {
            layer.alert("修改信息有误，请重新填写!");
            return;
        }

        if (!(/^([a-zA-Z]*|[\u4E00-\u9FA5\uf900-\ufa2d]*){3,13}$/.test($("#userName").val()))) {
            layer.alert("联系人姓名格式错误,应3-13中英文!");
            return;
        }
        $.ajax({
            type: "POST",
            url: "${base_path}/org/organization_modify.action",
            data: {orgName: orgName, userName: userName, "_csrf": "${_csrf.token}"},
            dataType: "json",
            success: function (result) {
                if (result.status == 500) {
                    layer.alert(result.msg, function () {
                        window.location.reload();
                    })
                } else {
                    layer.msg("修改信息成功！");
                    console.log(result.info);
                    window.location.reload();
                }

            },
            error: function (error) {
                layer.msg("服务器忙，请重新尝试！");
                console.log(error.info);
            }
        });
    });

    function modaldemo() {
        $.ajax({
            type: "get",
            url: "${base_path}/updatePhone/getPhone.action",
            dataType: 'json',
            success: function (data) {
                $("#old_phone").val(data.getPhone);
            },
            error: function (error) {
                console.log(error);
            }
        });
        $("#que-1").remove();
        $("#modal-demo").modal("show")
    }

    function modaldemo1() {
        $("#modal-demo1").modal("show")
    }

    function modaldemo3() {
        $("#myModal1").modal("show")
    }

    function modaldemo2() {
        $.ajax({
            type: "get",
            url: "${base_path}/updateEmail/getEmail.action",
            dataType: 'json',
            success: function (data) {
                $("#old_email").val(data.getEmail);
            },
            error: function (error) {
                console.log(error);
            }
        });
        $("#que").remove();
        $("#modal-demo2").modal("show")
    }

    function nextstep() {
        $("#modal-demo2 form table").remove();
        $("#next-step").remove();
        $("#modal-demo2 form").append("<table class='table'><tr><td class='text-r'><p class='f-16 mr-10'>新邮箱地址:</p></td><td><input type='text'class='input-text radius'  name='new_email' id='new_email'  placeholder='输入新邮箱地址'/></td></tr><tr><td class='text-r'><p class='f-16 mr-10'>验证码:</p></td><td><input type='text' class='input-text radius' placeholder='验证码' id = 'newEmailCode' style='width: 70%'/><input type='button' class='btn btn-default radius sendemailcode1' onclick='new_email_code()' value='获取验证码'></input></td></tr></table>")
        $("#modal-demo2 .modal-footer #closed").before("<button id='que' class='btn btn-primary' onclick='change_email()'>确认更换</button>")
    }

    $("a.close,#closed").click(function () {
        $("#modal-demo2 form table").remove();
        $("#next-step").remove();
        $("#modal-demo2 form").append("<table class='table'><tr><td class='text-r'><p class='f-16 mr-10'>旧邮箱地址:</p></td><td><input type='text' class='input-text radius'  name='old_email' id='old_email'  disabled='disabled'/></td></tr><tr><td class='text-r'><p class='f-16 mr-10'>验证码:</p></td><td><input type='text' class='input-text radius' placeholder='验证码' id = 'oldEmailCode' style='width: 70%''/><input type='button' class='btn btn-default radius sendemailcode' onclick='old_email_code()' value='获取验证码'></input></td></tr></table>")
        $("#modal-demo2 .modal-footer #closed").before("<button class='btn btn-primary'  id='next-step'>下一步</button>")
    })

    function nextstep_phone() {
        $("#modal-demo form table").remove();
        $("#next-step-phone").remove();
        $("#modal-demo form").append("<table class='table'><tr><td class='text-r'><p class='f-16 mr-10'>新手机号码:</p></td><td><input type='text' class='input-text radius'  name='new_phone' id='new_phone'  placeholder='输入新手机号码'/></td></tr><tr><td class='text-r'><p class='f-16 mr-10'>验证码:</p></td><td><input type='text' class='input-text radius' placeholder='验证码' id = 'newPhoneCode' style='width: 70%'/><input type='button' class='btn btn-default radius sendcode1' onclick='MatchCodeDialog(2)' value='获取验证码'></input></td></tr></table>")
        $("#modal-demo .modal-footer").append("<button id='que-1' class='btn btn-primary' onclick='change_phone()'>确认更换</button>")
    }

    $("#modal-demo a.close,#closed-1").click(function () {
        $("#modal-demo form table").remove();
        $("#next-step-phone").remove();
        $("#modal-demo form").append("<table class='table'><tr><td class='text-r'><p class='f-16 mr-10'>原手机号码:</p></td><td><input type='text' class='input-text radius'  name='old_phone'  id='old_phone'  disabled='disabled'/></td></tr><tr><td class='text-r'><p class='f-16 mr-10'>验证码:</p></td><td><input type='text' class='input-text radius' placeholder='验证码'  id = 'oldPhoneCode' style='width: 70%'/><input type='button' class='btn btn-default radius sendcode'  onclick='MatchCodeDialog(1)' value='获取验证码'></input></td></tr></table>")
        $("#modal-demo .modal-footer #closed-1").before("<button class='btn btn-primary'  id='next-step-phone'>下一步</button>")
    })
    $(document).ready(function () {
        $.ajax({
            type: "get",
            url: "${base_path}/message/countMessage.action",
            dataType: 'json',
            success: function (data) {
                $("#countMessage").html(data.countMessage);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    function countMessage(count) {
        $("#countMessage").html(count);
    }

</script>
</body>
</html>