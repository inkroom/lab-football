<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 上传公告</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">

</head>
<body>
<div class="ibox-content">
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <form id="upFileFrom" name="Form" action="${base_path}/leader/UploadFile" method="post"
                  enctype="multipart/form-data">
                <div class="form-group">
                    <label>公告标题</label>
                    <input type="text" placeholder="请输入公告标题" class="form-control" id="title" name="title">
                </div>
                <div class="form-group">
                    <label>公告内容</label>
                    <textarea placeholder="请输入公告内容" class="form-control" id="content" name="content"></textarea>
                </div>
                <div class="form-group">
                    <label>选择实验室</label>
                    <select id="lab_id" name="lab_id" class="form-control" required>
                        <c:forEach var="el" items="${lLaBeans}">
                            <option value="${el.id}">${el.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="">
                    <span class="btn btn-success fileinput-button">
                    <span>上传</span>
                        <input type="file" id="commonsMultipartFile" name="commonsMultipartFile">
                </span>
                </div>
                <div class="p-lg text-center">
                    <button class="btn btn-primary m-l-lg" type="submit"><i class="fa fa-check"></i>&nbsp;发布</button>
                    <button class="btn btn-warning m-l-lg" type="button"><i class="fa fa-warning"></i> <span
                            class="bold">取消</span></button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script src="${base_path}/resources/common/jquery/jquery.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
<script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
<script src="${base_path}/resources/common/jquery/jquery-form.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script>
    $.validator.setDefaults({
        submitHandler: function () {
            subTeacherInfo();
        }
    });
    $().ready(function () {
// 在键盘按下并释放及提交后验证提交表单
        $("#upFileFrom").validate({
                //重写showErrors
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, {tips: [1, "#cc2a32"], time: 2000});
                        return false;
                    });
                },
                /* 失去焦点时不验证 */
                onfocusout: false
            }
        )

    })

    function subTeacherInfo() {
        layer.confirm('提交后无法修改,确定吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            document.forms[0].submit();
        }, function () {
            layer.msg('已取消', {icon: 1});
        });
    }
</script>
</html>