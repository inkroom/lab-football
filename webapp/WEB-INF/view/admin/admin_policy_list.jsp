<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/18/17
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>政策管理</title>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/jquery/jquery_page/jquery.page.css" />
    <style type="text/css">
        .ms-controller{
            visibility: hidden
        }
    </style>
</head>
<body>

<div class="container-fluid mt-10" id = "list" ms-controller="listPage">
    <table class="table table-border table-bordered table-bg" id="tableElement">
        <thead class="text-c">
        <th>编号</th>
        <th>标题</th>
        <th>发布时间</th>
        <th>修改时间</th>
        <th colspan="2">操作</th>
        </thead>
        <tbody class="text-c" id="imgtbody">
            <tr  ms-for="($index,policy) in list">
                <td>{{$index + 1}}</td>
                <td>{{policy.title}}</td>
                <td>{{policy.create_date}}</td>
                <td>{{policy.oper_date}}</td>
                <td> <button id="subbotton" class="btn btn-success radius " ms-click="editPolicy(policy.id)">修改</button> </td>
                <td> <button id="delbotton" class="btn btn-success radius " ms-click="delPolicy(policy.id)">删除</button> </td>

            </tr>
        </tbody>

    </table>
    <span>从{{@startRow}}到{{@endRow}}共{{@total}}</span>
    <div class="tcdPageCode"></div>
</div>
</body>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/jquery/jquery_page/jquery.page.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/admin/admin_policy_list.js"></script>
</html>
