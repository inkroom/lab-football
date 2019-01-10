<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/16/17
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>政策编辑</title>
        <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/editor/dist/css/wangEditor.min.css">
        <link rel="stylesheet" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css">
        <style>
            #input_title{
                width: 100%;
                height:22px;
                border:none;
                outline: none;
                padding-left: 5px;
            }
        </style>
    </head>
    <body id="vBody">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <input id="pId" type="hidden" value="${data.id}">
            <input id="token" type="hidden" value="${_csrf.token}">
            <input  placeholder="添加标题"  id="input_title"  value='${data.title}'>
        </div>
        <div id="editor" style="height:80%;">${data.content}</div>
        <div class="mt-10 f-r mb-10">
            <button id="subbotton" class="btn btn-success radius ">发布</button>
        </div>
    </body>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/editor/dist/js/wangEditor.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/editor/filter/xss.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/admin/admin_policy_view.js"></script>
</html>
