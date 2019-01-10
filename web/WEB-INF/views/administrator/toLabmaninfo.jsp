<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbStudent" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbTeacher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>成员信息页面</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
    <link href="${base_path}/resources/common/plugins/css-dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${base_path}/resources/common/style.css" rel="stylesheet">

    <script type="text/javascript" src="${base_path}/resources/common/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/index.js"></script>
    <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
    <script src="${base_path}/resources/common/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
    <script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <p class="text-center text-info" style="font-size: 19px;">${lab_name}
                        成员信息</p>
                </div>
                <div class="ibox-content">
                    <div class="">
                        <!--这里有url-->
                        <input type="button" class="btn btn-success m-b-md"
                               onclick="window.open('${base_path}/LabmanAdministrator/toaddLabman')" value="添加成员"/>
                    </div>
                    <table class="table table-striped table-bordered table-hover table-condensed"
                           style="word-break: break-all;" id="editable">
                        <tbody>
                        <tr class="text-center" style="color: #336699;font-size: 17px">
                            <th>学号</th>
                            <th>名字</th>
                            <th>性别</th>
                            <th>年级</th>
                            <th>加实验室时间</th>
                            <th>实验室名字</th>
                            <th>专业名字</th>
                            <th>辅导员名字</th>
                            <th>电话</th>
                            <th>身份证</th>
                            <th>系部</th>
                            <th>班级</th>
                            <th>退出实验室时间</th>
                            <th>管理员操作</th>
                        </tr>
                        <c:forEach items="${students}" var="student">
                            <c:if test="${!(student.id eq id)}">
                                <tr class="text-center">
                                    <td>${student.id}</td>
                                    <td>${student.name}</td>
                                    <td>${student.gender}</td>
                                    <td>${student.grade}</td>
                                    <td>${student.time}</td>
                                    <td>${student.lab_name}</td>
                                    <td>${student.maj_name}</td>
                                    <td>${student.instructor}</td>
                                    <td>${student.tel}</td>
                                    <td>${student.IDcard}</td>
                                    <td>${student.department}</td>
                                    <td>${student.stuClass}</td>
                                    <td>${student.outTime}</td>
                                    <td>
                                        <input type="button" class="btn btn-danger btn-xs del"
                                               name="alert_student_btn"
                                               student_id="${student.id}"
                                               value="修改"/>
                                        <input type="button" class="btn btn-danger btn-xs del"
                                               name="delete_student_btn"
                                               student_id="${student.id}"
                                               value="删除"/>
                                            <%--<a href="finish_stu_information.html" class="btn btn-primary btn-xs">重置密码</a>--%>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <table class="table table-striped table-bordered table-hover table-condensed" style="word-break: break-all;">
            <tr style="color: #336699;font-size: 17px">
                <th class="text-center">老师名字</th>
                <th class="text-center">性别</th>
                <th class="text-center">电话</th>
            </tr>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td class="text-center">${teacher.name}</td>
                    <td class="text-center">${(teacher.gender eq 0)?'男':'女'}</td>
                    <td class="text-center">${teacher.tel}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    //获得删除按钮的
    //        JQuery通过名字查找节点的时候一定不能在[]前面加上空格 例如：$("input [name='name']")
    $("input[name='delete_student_btn']").each(function () {
        var $delete_btn = $(this);
        $delete_btn.click(function () {
            ajax({
                url: "${base_path}/LabmanAdministrator/deleteLabman",
                type: "post",
                async: false,
                dataType: "json",
                data: {
                    student_id: $delete_btn.attr("student_id")
                },
                success: function (result) {
//                    alert("删除学生id为：" + $delete_btn.attr("student_id"));
                    if (result.status === 0) {
                        layer.msg('删除成功', {icon: 1, time: 2000});
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    } else if (result.status == 1) {
                        layer.msg('删除失败', {icon: 2, time: 2000});
                    }
                },
                error: function () {
//                    alert("ajax失败");
                }
            });
        });
    });

    //跳转到修改页面，向传递修改的学生id，修改页面显示要修改的学生信息
    $("input[name='alert_student_btn']").each(function () {
        var $alert_btn = $(this);
        $alert_btn.click(function () {
            //在url后面拼接点击的按钮行的学生id，Controller通过@requestparam获得该id并且跳转到相应的界面
            window.location.href = "${base_path}/LabmanAdministrator/tochangeLabman?student_id=" + $alert_btn.attr("student_id");
        });
    });
</script>


</body>
</html>

