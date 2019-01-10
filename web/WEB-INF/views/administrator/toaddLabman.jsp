<%@ page import="java.util.ArrayList" %>
<!-- 陈顺秋
2017年9月28日
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbMajor" %>

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
    <script src="${base_path}/resources/common/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
    <script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
    <script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
    <title>添加学生信息</title>
</head>

<body class="gray-bg">
<div class="Inbox-content text-center">
    <h1>学生管理系统</h1>
</div>
<%
    ArrayList<DbMajor> majors = (ArrayList<DbMajor>) request.getAttribute("majors");
    request.setAttribute("majors", majors);
%>
<div class="ibox-content">
    <div class="ibox-content text-center">
        <div class="form-horizontal" id="formData">
            <form id="loginFrom">
                <div class="form-group">
                    <label class="col-sm-4 control-label">学号</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="student_id" name="student_id" placeholder="请输入你的学号"
                               pattern="^1\d{10}$">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" id="name" placeholder="请输入你的姓名"
                               pattern="^[\u4e00-\u9fa5]{2,4}$">
                    </div>
                </div>
                <div class="hr-line-dashed"></div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">性别</label>
                    <div class="checkbox">
                        <label class="col-sm-1"><input type="radio" value="0" name="gender">男</label>
                        <label class="col-sm-1"><input type="radio" value="1" name="gender" checked>女</label>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">系部</label>
                        <div class="col-sm-6">
                            <select id="department" name="department" class="form-control">
                                <option value="计算机科学与技术系">计算机科学与技术系</option>
                                <option value="信息技术与商务管理系">信息技术与商务管理系</option>
                                <option value="数字艺术系">数字艺术系</option>
                                <option value="应用外语系">应用外语系</option>
                            </select>
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
                            <input type="text" class="form-control" id="grade" name="grade" placeholder="请输入你的年级,如2015"
                                   pattern="[1-9]*[0-8]+">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">班级</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="stuClass" name="stuClass" placeholder="请输入你的班级如15201"
                                   pattern="[1-9]*[0-9]+">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>


                    <div class="form-group">
                        <label class="col-sm-4 control-label">加入实验室时间</label>
                        <div class="col-sm-6">
                            <input type="date" class="form-control" id="time" name="time" placeholder="请输入你加入实验室的时间">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">身份证</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="IDcard" name="IDcard" placeholder="请输入你的身份证"
                                   pattern="^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>


                    <div class="form-group">
                        <label class="col-sm-4 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="tel" name="tel" placeholder="请输入你的联系电话"
                                   pattern="^1[0-9]{10}$">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">辅导员</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="instructor" name="instructor"
                                   placeholder="请输入你的辅导员姓名" pattern="^[\u4e00-\u9fa5]{2,4}$">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="hr-line-dashed"></div>
                    <div class="hr-line-dashed"></div>
                    <div class="ibox-content">
                        <p class="text-center">
                            <!--这里有url-->
                            <button class="btn btn-primary refresh" type="button"><a href=""></a><i
                                    class="fa fa-check"></i>&nbsp;取
                                消
                            </button>
                            <button class="btn btn-info postBtn" type="submit" id="add_btn"><i class="fa fa-paste"></i>&nbsp;确认添加
                            </button>
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    //JQuery表单验证器
    $.validator.setDefaults({
        submitHandler: function () {
            check_addStu();
        }
    });
    //表单验证信息的规范性
    $().ready(function () {
// 在键盘按下并释放及提交后验证提交表单
        $("#loginFrom").validate({
                //正则规则
                rules: {
                    student_id: {
                        required: true,
                        rangelength: [11, 11],
                        digits: true//必须为整数
                    },
                    name: {
                        required: true
                    },
                    grade: {
                        required: true,
                        rangelength: [4, 4],
                        digits: true//必须为整数
                    },
                    IDcard: {
                        required: true,
                        rangelength: [18, 18],
                        digits: true//必须为整数
                    },
                    stuClass: {
                        required: true,
                        rangelength: [1, 2],
                        digits: true//必须为整数
                    },
                    tel: {
                        rangelength: [11, 11],
                        digits: true//必须为整数
                    }
                },
                //提示信息
                messages: {
                    student_id: {
                        required: '必须输入',
                        rangelength: '必须十一位长度',
                        digits: '学号为整数'//必须为整数
                    },
                    name: {
                        required: '必须输入'
                    },
                    grade: {
                        required: '必须输入',
                        rangelength: '正确年级格式如2015',
                        digits: '必须为整数'//必须为整数
                    },
                    IDcard: {
                        required: '必须输入',
                        rangelength: '身份证十八位长度',
                        digits: '必须为整数'//必须为整数
                    },
                    stuClass: {
                        required: '必须输入',
                        rangelength: '班级个位或十位数',
                        digits: '必须为整数'//必须为整数
                    },
                    tel: {
                        rangelength: '必须十一位长度',
                        digits: '必须为整数'//必须为整数
                    }
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


    //    $("#add_btn").click(function () {
    function check_addStu() {
//        alert("添加按钮被电击");
        //获得要传递添加的标签
        var data = {
            name: $("#name").val(),
            student_id: $("#student_id").val(),
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

        ajax({
            url: "${base_path}/LabmanCAdministrator/checkadd",
            type: "POST",
            async: false,
            data: data,
            dataType: "json",
            success: function (data) {
                //获得ajaxbean对象\
                if (data.status === 0) {
                    layer.alert("添加成功");
                } else {
                    layer.alert("添加失败，可能已有该学生");
                }

            },
            error: function (data) {
//                    alert("失败");
            }
        });
    }

    //    });


</script>
</body>

</html>