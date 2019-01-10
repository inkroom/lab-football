<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/laypage.css" />
    <title>球队申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 队员管理 <span class="c-gray en">&gt;</span> 球队申请<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <input type="text" class="input-text" id="search_code" maxlength="10" style="width:350px"  placeholder="请输入球队码" >
                <button type="submit" class="btn btn-success radius"  name="" onclick="modaldemo()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
            </a>
        </div>

        <table class="table table-border table-bordered table-bg  mt-20" id="for_w">
            <thead class="text-c">
            <tr><th colspan="10">我的球队申请</th></tr>
            </thead>
            <tbody class="text-c" id="loadData">
            
            </tbody>
        </table>
        <div id="page"></div>
    </div>
</div>
<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title" id="team_name"></h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-20">
                    <tbody class="text-c">
                    <tr>
                        <td colspan="2"><img id="team_badge" class="avatar size-XXL radius" src="${base_path}/resources/common/static/h-ui.admin/images/u=3438938354,2754681147&fm=23&gp=0.jpg"></td>
                    </tr>
                    <tr>
                        <td width="45%">球队等级：</td><td id="team_rank"></td>
                    </tr>
                    <tr>
                        <td width="45%">比赛场次：</td><td id="team_matchNum"></td>
                    </tr>
                    <tr>
                        <td>比赛胜率：</td><td id="team_winRate"></td>
                    </tr>
                    <tr>
                        <td>比赛积分：</td><td id="team_integral"></td>
                    </tr>
                    <tr>
                        <td>球队地址：</td><td id="team_affiliation"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="apply" onclick="applyTeam()">申请入队</button>
                <button class="btn" onclick="closePage()">关闭</button>
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
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<%-- <script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script> --%>
<script type="text/javascript" src="${base_path}/resources/js/player/page/laypage.js"></script>
<%-- <script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/vue1.0.28.js"></script> --%>
<%-- <script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script> --%>
<script type="text/javascript" src="${base_path}/resources/js/player/page/player_applyteam.js"></script>
</body>
</html>