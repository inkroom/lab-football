<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/15/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>精彩瞬间</title>
        <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
        <style>
            #imgtbody tr td img{
                width: 50px;
                height: 40px;
            }
        </style>
        <script type="text/javascript" src="${base_path }/resources/common/avalon/avalon.js"></script>
    </head>

    <body>
    <div class="container-fluid mt-10">
        <table class="table table-border table-bordered table-bg" id="tableElement" ms-controller="tableElement">
            <thead class="text-c">
            <th>编号</th>
            <th>缩略图</th>
            <th>图片路径</th>
            <th>修改时间</th>
            <th>操作</th>
            </thead>
            <tbody class="text-c" id="imgtbody">
                <tr ms-for="($index,img) in list">
                    <td>{{$index + 1}}</td>
                    <td>{{img.id}}</td>
                    <td><img ms-attr="{src: basePath+img.addr}" /></td>
                    <td>{{img.date}}</td>
                    <td><span class="btn-upload"><a href="javascript:void();" class='btn btn-warning radius'>替换</a><input type="file" ms-attr="{id:'file'+ img.id}" name="file" class="input-file" ms-change="updateOther('file'+ img.id,img.id)"></span></td>
                </tr>
            </tbody>
        </table>
    </div>
    </body>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/jquery/jquery_upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/avalon/avalon.js"></script>
    <script type="application/javascript">
        var listImg = ${listImg};
        var basePath = "${base_path}";
        var token = "${_csrf.token}";
    </script>
    <script type="text/javascript" src="${base_path }/resources/js/admin/admin_img.js"></script>
</html>
