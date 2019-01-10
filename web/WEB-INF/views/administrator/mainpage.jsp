<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.edu.nsu.lib.config.Constants" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.Student_check" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 管理员界面</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
    <link href="${base_path}/resources/common/plugins/css-dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${base_path}/resources/common/style.css" rel="stylesheet">
    <style type="text/css">
        td {
            text-align: center
        }
    </style>

    <script type="text/javascript" src="${base_path}/resources/common/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/index.js"></script>
    <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
    <script src="${base_path}/resources/common/plugins/jeditable/jquery.jeditable.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <p class="text-center text-info" style="font-size: 19px;"><%=request.getAttribute("lab_name")%>
                        成员考勤信息</p>
                </div>
                <div class="ibox-content">
                    <%--<div class="text-right">--%>
                        <%--<input type="date" id="time"/>--%>
                    <%--</div>--%>
                    <%--<!--&lt;%&ndash;需要通过客户输入数据刷新页面的时候，用一个设置了id的tbody标签，然后用js更新数据&ndash;%&gt;-->--%>
                    <%--<input class="btn btn-primary dim pull-right m-t-xs" type="button" name="bt" id="bt" value="查询考勤">--%>
                    <table class="table table-striped table-bordered table-hover table-condensed" id="checkin_table">
                        <thead>
                        <tr class="text-center" style="color: #336699;font-size: 17px">
                            <th class="text-center">学生学号</th>
                            <th class="text-center">学生名字</th>
                            <th class="text-center">时间</th>
                            <th class="text-center">签到</th>
                        </tr>
                        </thead>

                        <tbody id="tbodys">
                        <%
                            ArrayList<Student_check> student_checks = (ArrayList<Student_check>) request.getAttribute("student_checks");
                            for (Student_check student : student_checks) {
                        %>
                        <tr>
                            <td><%=student.getStudent_id()%>
                            </td>
                            <td><%=student.getName()%>
                            </td>
                            <td><%=student.getDate()%>
                            </td>
                            <td><%=student.isRegister() ? "签到成功" : "签到失败"%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->


<script>
    $(function () {
        $("#bt").click(function () {
            var pagetime = $("#time");
            //                    alert("该按钮成功被点击");
            ajax({
                url: "${base_path}/Administrator/timechangedata",
                type: "POST",
                async: false,
                data: {
                    "time": pagetime.val()
//    "time" : "2017-01-07"
                },
                dataType: "json",
                success: function (data) {
                    //获得ajaxbean对象
                    showData(data);
                },
                error: function () {
//                    alert("失败");
                }
            });
        });
    });

    function showData(data) {
        //获取后端返回的json的考勤数据
        var mapdata = null;
        var tbodys = document.getElementById("tbodys");
        if (data == null) {
//            alert("返回数据为空");
        } else {
            mapdata = data.data["student_checks"];
//            alert("数据不为空,长度为：" + mapdata.length);
            var str = "";
            for (var key in mapdata) {
                str += "<tr class=\"text-center\">" +
                    "<td>" + mapdata[key].student_id + "</td>" +
                    "<td>" + mapdata[key].name + "</td>" +
                    "<td>" + mapdata[key].date + "</td>" +
                    "<td>" + mapdata[key].register + "</td>" +
                    "</tr>";
            }
            tbodys.innerHTML = str;
        }
    }


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


</body>

</html>