<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.css">
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.reset.css"/>


    <!--[if IE 6]>
    <![endif]-->

</head>
<body class="page-container">

<div class="mt-20">
    <table class="table table-border table-bordered mt-20" >
        <tr>
            <td class="f-18 text-c ">欢迎使用该系统</td>
        </tr>
    </table>

    <div id="tab_demo" class="HuiTab mt-20">
        <div class="tabBar clearfix"><span>已读</span><span>未读</span></div>
        <div class="tabCon">
            <table class="table table-border  table-bg th radius table-hover mt-20">
                <thead>
                <tr class="text-c">

                    <th>标题</th>
                    <th>状态</th>
                    <th>时间</th>
                </tr>

                </thead>

                <tbody class="text-c">
                <tr>
                    <td><a class="btn btn-link" href="message-detail.html">我是一条重要的消息</a></td>
                    <td >已读</td>
                    <td>2017年3月</td>
                </tr>
                <tr>
                    <td><a class="btn btn-link" href="message-detail.html">我是一条重要的消息</a></td>
                    <td >已读</td>
                    <td>2017年3月</td>
                </tr>
                <tr>
                    <td><a class="btn btn-link" href="message-detail.html">我是一条重要的消息</a></td>
                    <td >已读</td>
                    <td>2017年3月</td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="tabCon">
            <table class="table table-border  table-bg th radius table-hover mt-20">
                <thead>
                <tr class="text-c">

                    <th>标题</th>
                    <th>状态</th>
                    <th>时间</th>
                </tr>

                </thead>

                <tbody class="text-c">
                <tr>
                    <td><a class="btn btn-link" href="message-detail.html">我是一条重要的消息</a></td>
                    <td >未读</td>
                    <td>2017年3月</td>
                </tr>
                <tr>
                    <td><a class="btn btn-link" href="message-detail.html">我是一条重要的消息</a></td>
                    <td >未读</td>
                    <td>2017年3月</td>
                </tr>
                <tr>
                    <td><a class="btn btn-link" href="message-detail.html">我是一条重要的消息</a></td>
                    <td >未读</td>
                    <td>2017年3月</td>
                </tr>

                </tbody>
            </table>
        </div>

    </div>

</div>

</body>


<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.js"></script>
<script>
    $(function(){
        $.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","1")});
</script>
</html>