<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/5/17
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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



    </head>
    <body>
    <header class="navbar-wrapper">
        <div class="navbar navbar-fixed-top" >
            <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs">四川省青少年校园足球信息网后台管理</a>
                <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                    <ul class="cl">
                        <li class="dropDown dropDown_hover">
                            <a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
                            <ul class="dropDown-menu menu radius box-shadow">
                                <li><a href="javascript:;" onClick="modaldemo1()">修改密码</a></li>
                                <li><a href="javascript:logout()">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <aside class="Hui-aside" style="background-color: #efeef0">
        <input runat="server" id="divScrollValue" type="hidden" value="" />
        <div class="menu_dropdown bk_2">
            <dl id="menu-image">
                <dt><i class="Hui-iconfont">&#xe613;</i> 图片管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="roll_img.html" data-title="滚动图片" href="javascript:void(0)">滚动图片</a></li>
                    </ul>
                </dd>
            </dl>

            <dl id="menu-policy">
                <dt><i class="Hui-iconfont">&#xe667;</i> 管理政策<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="policy_add_view.html" data-title="添加政策" href="javascript:void(0)">添加政策</a></li>
                        <li><a data-href="policy_list.html" data-title="管理政策" href="javascript:void(0)">管理政策</a></li>
                    </ul>
                </dd>
            </dl>

            <dl id="menu-link">
                <dt><i class="Hui-iconfont">&#xe6f1;</i> 链接管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="link_edit.html" data-title="链接添加" href="javascript:void(0)">链接添加</a></li>
                        <li><a data-href="link_list.html" data-title="链接管理" href="javascript:void(0)">链接管理</a></li>
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
                        <span title="欢迎" data-href="message-all.html">欢迎</span>
                        <em></em></li>
                </ul>
            </div>
            <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
        </div>
        <div id="iframe_box" class="Hui-article">
            <div class="show_iframe">
                <div style="display:none" class="loading"></div>
                <iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
            </div>
        </div>
    </section>

    <div class="contextMenu" id="Huiadminmenu">
        <ul>
            <li id="closethis">关闭当前 </li>
            <li id="closeall">关闭全部 </li>
        </ul>
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
								<input type="password" class="input-text radius" value="" placeholder=""  name="old_password" id="old_password">
							</div>
						</div>
						<div class="row cl ">
							<label class="form-label col-xs-4 col-sm-3">新密码：</label>
							<div class="formControls col-xs-8 col-sm-8">
								<input type="password" class="input-text radius" value="" placeholder=""  name="new_password"  id="new_password">
							</div>
						</div>
						<div class="row cl ">
							<label class="form-label col-xs-4 col-sm-3">确认密码：</label>
							<div class="formControls col-xs-8 col-sm-8">
								<input type="password" class="input-text radius" value="" placeholder="" name="new_password_n"  id="new_password_n">
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary"   onclick="updatepassword('${_SALT_IN_SESSION_}')" >确认修改</button>
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div>
		</div>
	</div>
</div>
<input type="text" id="base_path" hidden="" value="${base_path}">
    <!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/md5.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/common/update_phone_password.js"></script>
    <!--请在下方写此页面业务相关的脚本-->
    <script>
        function modaldemo(){
            $("#modal-demo").modal("show")}
        function modaldemo1(){
            $("#modal-demo1").modal("show")}
        $(function(){
            $('#myModal').modal({
                show:true,
                backdrop:true
            })
        });

        
        


        function logout() {
            layer.confirm('是否确认退出？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $.ajax({
                    url:"logout.action",    //请求的url地址
                    type:"post",   //请求方式
                    dataType:"json",   //返回格式为json
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    beforeSend:function(){
                        //请求前的处理
                        console.log("form submit");
                    }, success:function(data){
                        //请求成功时处理
                        if (data.status == "500"){
                            layer.alert(data.msg,{closeBtn: 0},function () {
                                window.location.reload();
                            });
                        }else {
                            layer.alert(data.msg,function (index) {
                                window.parent.location.href = "login_view.html";
                                layer.close(index);
                            },true);
                        }
                    }, complete:function(){
                        //请求完成的处理
                    }, error:function(XMLHttpRequest, textStatus, errorThrown) {
                        //请求出错处理
                        console.log("error");
                        console.log(XMLHttpRequest);
                        console.log(textStatus);
                        console.log(errorThrown);
                    }

                })
            }, function(){

            });
        }


    </script>
    </body>
</html>