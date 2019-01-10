<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/25/17
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
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

    <!--[if lt IE 9]>
    <script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->



    <title>教练员系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" style="height: 55px;line-height: 55px;font-size: 24px">教练员后台管理系统</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A" style="line-height: 55px">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onClick="modaldemo1()">修改密码</a></li>
                            <li><a href="#" onclick="modaldemo()">更改手机号</a></li>
                            <li><a href="login/index.html">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-msg"  style="line-height: 55px"><a onclick="check()" data-href="message-all.html" href="javascript:void(0)"title="消息中心"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
        <dl id="menu-message">
            <dt><i class="Hui-iconfont">&#xe616;</i> 个人中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="coach-system/coach-message.html" data-title="个人中心" href="javascript:void(0)">个人中心</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-team">
            <dt><i class="Hui-iconfont">&#xe613;</i> 我的球队<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="coach-system/coach-team.html" data-title="我的球队" href="javascript:void(0)">我的球队</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-match">
            <dt><i class="Hui-iconfont">&#xe620;</i> 我的比赛<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="coach-system/coach-match.html" data-title="我的比赛" href="javascript:void(0)">我的比赛</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-training">
            <dt><i class="Hui-iconfont">&#xe620;</i> 发布训练<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="coach-system/training.html" data-title="发布训练" href="javascript:void(0)">发布训练</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-info">
            <dt><i class="Hui-iconfont">&#xe620;</i> 赛事公示<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="info/info-player.html" data-title="球员公示" href="javascript:void(0)">球员公示</a></li>
                    <li><a data-href="info/info-coach.html" data-title="教练员公示" href="javascript:void(0)">教练员公示</a></li>

                    <li><a data-href="info/info-public-team.html" data-title="球队公示" href="javascript:void(0)">球队公示</a></li>
                    <li><a data-href="info/info-public-game.html" data-title="赛事公示" href="javascript:void(0)">赛事公示</a></li>
                </ul>
            </dd>
        </dl>

    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="消息中心" data-href="message-all.html">消息中心</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="message-all.html"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前 </li>
        <li id="closeall">关闭全部 </li>
    </ul>
</div>
<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                            <td class="text-r"><p class="f-16 mr-10">手机号码:</p></td>
                            <td>
                                <input type="text" class="input-text radius" placeholder="输入旧手机号码"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">验证码:</p></td>
                            <td><input type="text" class="input-text radius" placeholder="验证码" style="width: 70%"/>
                                <button class="btn btn-default radius">获取验证码</button></td>
                        </tr>
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">新手机号码:</p></td>
                            <td>
                                <input type="text" class="input-text radius" placeholder="输入新手机号码"/></td>
                        </tr>
                        <tr>
                            <td class="text-r"><p class="f-16 mr-10">验证码:</p></td>
                            <td><input type="text" class="input-text radius" placeholder="验证码" style="width: 70%"/>
                                <button class="btn btn-default radius">获取验证码</button></td>
                        </tr>
                    </table>
                </form>

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary">确认更换</button>

                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
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
                    <form  class="form formControls">
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">旧密码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="text" class="input-text radius" value="" placeholder="">
                            </div>
                        </div>
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">新密码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="text" class="input-text radius" value="" placeholder="">
                            </div>
                        </div>
                        <div class="row cl ">
                            <label class="form-label col-xs-4 col-sm-3">确认密码：</label>
                            <div class="formControls col-xs-8 col-sm-8">
                                <input type="text" class="input-text radius" value="" placeholder="">
                            </div>
                        </div>
                    </form>

                </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-primary">确认修改</button>

                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->

<script>
    function modaldemo(){
        $("#modal-demo").modal("show")}
    function modaldemo1(){
        $("#modal-demo1").modal("show")}

</script>
</body>
</html>