<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>球员管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"><a class="logo navbar-logo f-l mr-10 hidden-xs">球员管理系统</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">
                            <c:if test="${username.A_NAME eq null}">
                                ${username.A_USERNAME}
                            </c:if>
                            <c:if test="${username.A_NAME ne null}">
                                ${username.A_NAME}
                            </c:if>
                            <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onClick="modaldemo1()">修改密码</a></li>
                            <li><a href="#" onclick="modaldemo()">更改手机号</a></li>
                            <li><a href="#" onclick="modaldemo2()">更改邮箱</a></li>
                            <li><a href="${base_path}/player/logOut.action">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg"><a onclick="check()" data-href="message-all.html" href="javascript:void(0)"
                                        title="消息中心"><span class="badge badge-danger" id="countMessage"></span><i
                            class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value=""/>
    <div class="menu_dropdown bk_2">
        <dl id="menu-message">
            <dt><i class="Hui-iconfont">&#xe616;</i> 个人中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="${base_path}/player/system/player_message.html" data-title="个人中心"
                           href="javascript:void(0)">个人中心</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-team">
            <dt><i class="Hui-iconfont">&#xe613;</i> 我的球队<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="${base_path}/player/system/player_team.html" data-title="我的球队"
                           href="javascript:void(0)">我的球队</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-match">
            <dt><i class="Hui-iconfont">&#xe620;</i> 我的比赛<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="${base_path}/player/system/player_match.html" data-title="我的比赛"
                           href="javascript:void(0)">我的比赛</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-training">
            <dt><i class="Hui-iconfont">&#xe620;</i> 申请球队<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="${base_path}/player/system/player_teamapply.html" data-title="申请球队"
                           href="javascript:void(0)">申请球队</a></li>
                </ul>
            </dd>
        </dl>
        <!--         <dl id="menu-info"> -->
        <!--             <dt><i class="Hui-iconfont">&#xe620;</i> 赛事公示<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt> -->
        <!--             <dd> -->
        <!--                 <ul> -->
        <!--                     <li><a data-href="info/info-public-game.html" data-title="球队公告" href="javascript:void(0)">球队公告</a></li> -->
        <!--                     <li><a data-href="info/info-public-team.html" data-title="赛事公告" href="javascript:void(0)">赛事公告</a></li> -->
        <!--                 </ul> -->
        <!--             </dd> -->
        <!--         </dl> -->
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
</div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="消息中心" data-href="${base_path }/message/message-all.html">消息中心</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S"
                                                  href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
        </div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="${base_path }/message/message-all.html"></iframe>
        </div>
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
                                       onclick="MatchCodeDialog(1)" value="获取验证码"></input></td>
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

<div id="modal-demo_info" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">完善信息</h3>
            </div>
            <div class="modal-body">
                <span class="f-16">请完成基本信息,再继续操作</span>
            </div>
            <div class="modal-footer">
                <div class="btn-group">
                    <a class="btn  btn-warning radius" href="${base_path}/player/logOut.action"><i
                            class="Hui-iconfont"></i>退出登录</a>
                    <a onclick="player_edit('球员信息编辑','${base_path}/player/system/player_edit.html','1','800','500')"
                       class="btn  btn-primary radius"><i class="Hui-iconfont"></i> 编辑信息</a>
                </div>
            </div>
        </div>
    </div>

    <input type="text" id="base_path" hidden="" value="${base_path}">
    <input type="text" id="typePhone" hidden="" value="1">
    <input type="text" id="SMSPhone" hidden="" value="${sessionScope.get('SMSPhone') }">
    <input type="text" id="SMSEmail" hidden="" value="${sessionScope.get('SMSEmail') }">
    <input type="text" id="SMSPhoneCode" hidden="" value="${sessionScope.get('VERIFY_CODE') }">
    <!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <!--/_footer 作为公共模版分离出去-->

    <!--请在下方写此页面业务相关的脚本-->
    <script type="text/javascript"
            src="${base_path}/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/common/updateEmail.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/common/update_phone_password.js"></script>
    <script>
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


        $(function () {
            if ('${username.A_NAME}' == null || '${username.A_NAME}' == '') {
                $('#modal-demo_info').modal({
                    show: true,
                    backdrop: true
                });
            }
        });

        function player_edit(title, url, id, w, h) {
            layer_show(title, url, w, h);
        }


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