<%@ page import="cn.edu.nsu.lib.bean.admin.Notice" %><%--
  Created by IntelliJ IDEA.
  User: 王振科
  Date: 2017/9/30
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.Notice" %>

<html>
<head>
    <head lang="en">
        <meta charset="UTF-8">
        <title><%=request.getAttribute("lab_name")%>公告信息</title>

        <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
        <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
        <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
        <link href="${base_path}/resources/common/plugins/css-dataTables/dataTables.bootstrap.css" rel="stylesheet">
        <link href="${base_path}/resources/common/style.css" rel="stylesheet">

        <script type="text/javascript" src="${path}/resources/common/jquery.min.js"></script>
        <script type="text/javascript" src="${path}/resources/js/index.js"></script>
        <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
        <script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
        <script src="${base_path}/resources/common/plugins/jeditable/jquery.jeditable.js"></script>
    </head>
</head>
<body class="full-height-layout">
<h1 class="text-center" style="font-size: 40px">实验室人员管理系统</h1>
<div class="wrapper wrapper-content animated fadeInRight">
    <input type="button" class="btn btn-success m-b-md" onclick=window.open("/NoticeAdministrator/toUploadnotice")
           value="添加公告">
    <table style="word-break: break-all;" class="table table-bordered table-hover text-center">
        <tr style="color: #6495ED">
            <th class="text-center">实验室名字</th>
            <th class="text-center">公告发布时间</th>
            <th class="text-center">文件名字</th>
            <th class="text-center">公告标题</th>
            <th class="text-center">公告内容</th>
            <th class="text-center">公告附件路径</th>
            <th class="text-center">发布人名字</th>
            <th class="text-center">操作</th>
        </tr>
        <%
            ArrayList<Notice> notices = (ArrayList<Notice>) request.getAttribute("notices");
            for (Notice notice : notices) {
                request.setAttribute("notice",notice);
        %>
        <tr class="text-center" style="color: 	#5F9EA0">
            <td>${notice.lab_name}
            </td>
            <td>${notice.time}
            </td>
            <td>${notice.file_name}
            </td>
            <td>${notice.title}
            </td>
            <td>${notice.content}
            </td>
            <td>${notice.file_path}
            </td>
            <td>${notice.publisher_name}
            </td>
            <td>
                <input type="button" class="btn btn-danger btn-xs del"
                       name="delete_notice_btn"
                       notice_id="<%=notice.getId()%>"
                       value="删除"/>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<script>
    //获得删除按钮的
    //        JQuery通过名字查找节点的时候一定不能在[]前面加上空格 例如：$("input [name='name']")
    $("input[name='delete_notice_btn']").each(function () {
        var $delete_btn = $(this);
        $delete_btn.click(function () {
            ajax({
                url: "${base_path}/NoticeAdministrator/Deletenotice",
                type: "post",
                async: false,
                dataType: "json",
                data: {
                    notice_id: $delete_btn.attr("notice_id")
                },
                success: function () {
//                    alert("删除学生id为：" + $delete_btn.attr("notice_id"));
                    window.location.reload();
                },
                error: function () {
//                    alert("ajax失败");
                }
            });
        });
    });
</script>

</body>
</html>
