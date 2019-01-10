<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbStudent" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbTeacher" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>添加实验室公告信息</title>
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
    <link href="${base_path}/resources/common/plugins/css-dataTables/dataTables.bootstrap.css" rel="stylesheet">


    <script type="text/javascript" src="${base_path}/resources/common/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/index.js"></script>
    <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
    <script src="${base_path}/resources/common/plugins/jeditable/jquery.jeditable.js"></script>
</head>

<body class="full-height-layout">
<div>
    <h1 class="text-center" style="font-size: 40px">实验室公告信息</h1>
</div>
<div style="padding: 100px 100px 10px;">
    <form class="bs-example bs-example-form" role="form" method="get" action="index.php">
        <div class="form-group">
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入公告名称">
        </div>
        <br>
        <div class="form-group">
            <input type="text" class="form-control" style="height:500px" id="content" name="content"
                   value=getNowFormatDate()
                   placeholder="请输入公告内容">
        </div>
        <br>
    </form>
</div>
<div class="ibox-content">


    <p class="text-center">
        <button class="btn btn-primary " type="button"><i class="fa fa-check"></i>&nbsp;取消</button>

        <button class="btn btn-info " type="submit" id="add_notice_btn"><i class="fa fa-paste"></i> 确认添加</button>

    </p>
</div>
</div>
</body>

<script>
    $("#title").val("公告标题");
    $("#content").val(getNowFormatDate());
    $("#add_notice_btn").click(function () {
        alert("添加公告被点击");
        var data = {
            time: getNowFormatDate(),
            title: $("#title").val(),
            content: $("#content").val(),
            file: $("#file").val()
        };
        //ajax上传参数
        ajax({
            url: "${base_path}/NoticeCAdministrator/Uploadnotice",
            type: "POST",
            async: false,
            data: data,
            dataType: "json",
            success: function (data) {
                //获得ajaxbean对象\
                if (data.status == 0) {
                    alert("添加成功");
                } else {
                    alert("添加失败");
                }

            },
            error: function () {
                alert("失败");
            }
        });
    });

    //    js获取当前日期时间“yyyy-MM-dd HH:MM:SS”的方法
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }

</script>

</html>