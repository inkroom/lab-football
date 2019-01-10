<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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

    <title>个人信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球员管理 <span class="c-gray en">&gt;</span> 我的比赛<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <span class="select-box radius">
                     <select class="select" size="1" name="select1">
                         <option value="" selected>选择你想查看的球队</option>
                     </select>
                 </span>
            </a>
        </div>
        <table class="table table-border table-bordered table-bg  mt-20">
            <thead class="text-c">
            <tr>
                <th width="15%">赛事名称</th>
                <th width="15%">主队</th>
                <th width="15%">客队</th>
                <th width="15%">比分</th>
                <th width="15%">结束时间</th>
                <th width="30%">操作</th>
            </tr>
            </thead>
            <tbody class="text-c" id="matchList">
            </tbody>
        </table>
    </div>
</div>

<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">比赛详情</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>

            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c">
                    <tr>
                        <td width="20%"></td>
                        <td>人数</td>
                        <td>比分</td>
                      
                    </tr>
                    <tr>
                        <td id="h_name">/</td>
                        <td id="h_num">/</td>
                        <td id="h_score">/</td>
                    </tr>
                    <tr>
                        <td id="v_name">/</td>
                        <td id="v_num">/</td>
                        <td id="v_score">/</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c" id="warning">
                    <tr>
                        <td width="25%">警告人员姓名</td>
                        <td width="10%">主客队</td>
                        <td width="15%">球员号码</td>
                        <td>原因</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c" id="sendoff">
                    <tr>
                        <td width="25%">罚出人员姓名</td>
                        <td width="10%">主客队</td>
                        <td width="15%">球员号码</td>
                        <td>原因</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-20">
                    <tbody class="text-c">
                     <tr id="redp">
                        <td width="25%">红牌情况：</td>
                    </tr>
                     <tr id="dianqiu">
                        <td>罚球点球情况：</td>
                    </tr>
                    <tr id="cuoloupan">
                        <td>严重错漏判情况:</td>
                    </tr>
                    </tbody>
                    </table>
            </div>
            <div class="modal-footer">
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
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/player/page/player_match.js"></script>
</body>
</html>