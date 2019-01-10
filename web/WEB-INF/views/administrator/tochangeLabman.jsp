<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbStudent" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.form.Student_form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbMajor" %>

<!-- 陈顺秋
2017年9月28日
-->
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">

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
    <title>修改学生信息</title>
</head>

<body class="gray-bg">
<div class="Inbox-content text-center">
    <h1>学生管理系统</h1>
</div>
<div class="ibox-content">
    <div class="form-horizontal" id="formData">
        <%--<%--%>
        <%--Student_form student = (Student_form) request.getAttribute("student");--%>
        <%--ArrayList<DbMajor> majors = (ArrayList<DbMajor>) request.getAttribute("majors");--%>
        <%--request.setAttribute("majors", majors);--%>
        <%--%>--%>
        <form id="loginFrom">
            <div class="form-group">
                <label class="col-sm-4 control-label">学号</label>
                <div class="col-sm-6">
                    <span type="text" class="form-control" id="student_id"
                          name="student_id">${student.getStudent_id()}</span>
                </div>
            </div>
            <div class="hr-line-dashed"></div>

            <div class="form-group">
                <label class="col-sm-4 control-label">姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入你的姓名"
                           value="${student.getName()}">
                </div>
            </div>
            <div class="hr-line-dashed"></div>

            <div class="form-group">
                <label class="col-sm-4 control-label">性别</label>
                <div class="checkbox">
                    <label class="col-sm-1"><input type="radio" value="0"
                                                   name="gender" ${(student.getGender() eq 0)?'checked':''}>男</label>
                    <label class="col-sm-1"><input type="radio" value="1"
                                                   name="gender" ${(student.getGender() eq 1)?'checked':''}>女</label>
                    <%--<%--%>
                    <%--int manorwoman = Integer.parseInt(student.getGender());--%>
                    <%--if (manorwoman == 0) {--%>
                    <%--%>--%>
                    <%--<label class="col-sm-1"><input type="radio" value="0" name="gender" checked>男</label>--%>
                    <%--<label class="col-sm-1"><input type="radio" value="1" name="gender">女</label>--%>
                    <%--<%--%>
                    <%--} else {--%>
                    <%--%>--%>
                    <%--<label class="col-sm-1"><input type="radio" value="0" name="gender">男</label>--%>
                    <%--<label class="col-sm-1"><input type="radio" value="1" name="gender" checked>女</label>--%>
                    <%--<%--%>
                    <%--}--%>
                    <%--%>--%>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">系别</label>
                    <div class="col-sm-6">
                        <select id="department" name="department" class="form-control">
                            <option value="计算机科学与技术系">计算机科学与技术系</option>
                            <option value="信息技术与商务管理系">信息技术与商务管理系</option>
                            <option value="数字艺术系">数字艺术系</option>
                            <option value="应用外语系">应用外语系</option>
                        </select>
                        <%--<input type="text" class="form-control" id="department" name="department"--%>
                        <%--placeholder="请输入你的系别"--%>
                        <%--value="${student.getDepartment()}">--%>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">专业</label>
                    <div class="col-sm-6">
                        <select id="major" name="major" class="form-control">
                            <c:forEach var="major" items="${majors}">
                                <option value="${major.id}">${major.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">年级</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="grade" name="grade" placeholder="请输入你的年级如2015"
                               value="${student.getGrade()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">班级</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="stuClass" name="stuClass"
                               placeholder="请输入你的班级如15201"
                               value="${ student.getStuClass()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">加入实验室时间</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" id="time" name="time" placeholder="请输入你加入实验室的时间"
                               value="${ student.getTime()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>


                <%--<div class="form-group">--%>
                <%--<label class="col-sm-4 control-label">所属实验室名字</label>--%>
                <%--<div class="col-sm-6">--%>
                <%--<input type="text" class="form-control" id="lab_name" name="lab_name" placeholder="请输入你所属实验室id"--%>
                <%--value="${ student.getLab_name()}">--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="hr-line-dashed"></div>--%>

                <div class="form-group">
                    <label class="col-sm-4 control-label">身份证</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="IDcard" name="IDcard" placeholder="请输入你的身份证"
                               value="${ student.getIDcard()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">联系电话</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="tel" name="tel" placeholder="请输入你的联系电话"
                               value="${ student.getTel()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">辅导员</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="instructor" name="instructor"
                               placeholder="请输入你的辅导员姓名"
                               value="${ student.getInstructor()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">退出实验室时间</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" id="outTime" name="outTime" placeholder="请输入你退出实验室的时间"
                               value="${ student.getOutTime()}">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="ibox-content">
                    <p class="text-center">
                        <!--这里有url-->
                        <button class="btn btn-info postBtn" type="submit" id="alert_btn"><i class="fa fa-paste"></i>&nbsp;确认修改</button>
                        <button class="btn btn-primary refresh" type="button"><a href=""></a><i class="fa fa-check"></i>&nbsp;取消</button>
                    </p>
                </div>
            </div>
        </form>
    </div>
</div>


<script>
    //JQuery表单验证器
    $.validator.setDefaults({
        submitHandler: function () {
            check_alertStu();
        }
    });
    //表单验证信息的规范性
    $().ready(function () {
// 在键盘按下并释放及提交后验证提交表单
        $("#loginFrom").validate({
                //正则规则
                rules: {
                    name: {
                        required: true
                    },
                    grade: {
                        required: true,
                        rangelength: [4, 4],
                        digits: true//必须为整数
                    },
                    time: {
                        required: true,
                    },
                    IDcard: {
                        required: true,
                        rangelength: [18, 18],
                        digits: true//必须为整数
                    },
                    stuClass: {
                        required: true,
                        rangelength: [5, 5],
                        digits: true//必须为整数
                    },
                    tel: {
                        rangelength: [11, 11],
                        digits: true//必须为整数
                    },
//                    outTime: {
//                        required: true
//                    }
                },
                //提示信息
                messages: {
                    name: {
                        required: '必须输入'
                    },
                    grade: {
                        required: '必须输入',
                        rangelength: '请输入正确的年级，如2015',
                        digits: '必须为整数'//必须为整数
                    },
                    time: {
                        required: '必须输入',
                    },
                    IDcard: {
                        required: '必须输入',
                        rangelength: '身份证十八位长度',
                        digits: '必须为整数'//必须为整数
                    },
                    stuClass: {
                        required: '必须输入',
                        rangelength: '请输入正确的班级，如15201',
                        digits: '必须为整数'//必须为整数
                    },
                    tel: {
                        rangelength: '必须十一位长度',
                        digits: '必须为整数'//必须为整数
                    },
//                    outTime: {
//                        required: '必须填一个'
//                    }
                },
                //重写showErrors，layer提示的位置
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, {tips: [1, "#cc2a32"], time: 2000});
                        return false;
                    });
                },
            }
        )
    });


    function check_alertStu() {
//        alert("修改按钮被电击");
        var data = {
            name: $("#name").val(),
            student_id: $("#student_id").text(),
            gender: $("input:radio[name='gender']:checked").val(),
            grade: $("#grade").val(),
            time: $("#time").val(),
            lab_name: $("#lab_name").val(),
            major: $("#major option:selected").val(),
            instructor: $("#instructor").val(),
            tel: $("#tel").val(),
            IDcard: $("#IDcard").val(),
            stuClass: $("#stuClass").val(),
            department: $("#department").val(),
            outTime: $("#outTime").val()
        };
        //HTML5使用FormData，在构造这个对象的时候，把表单的对象，
        // 作为一个参数放进去，就可以了，然后FormData，就会得到这个表单对象里面的所有的参数
        //        var student_form = new FormData(document.getElementById("form1"));
        //        alert(student_form);
        ajax({
            url: "${base_path}/LabmanCAdministrator/checkalter",
            type: "POST",
            async: false,
            data: data,
            dataType: "json",
            success: function (data) {
                //获得ajaxbean对象\
                if (data.status === 0) {
                    layer.alert("修改成功");
                } else {
                    layer.alert("修改失败");
                }

            },
            error: function (data) {
//                alert("失败");
            }
        });

    }
</script>
</body>

</html>