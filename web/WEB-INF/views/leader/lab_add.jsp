<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 添加实验室</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie"/>
    <![endif]-->

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
</head>
<body>
<div style="height: 120px"></div>
<div class="ibox-title text-center p-lg">
    创建新的实验室
</div>
<form id="labInfo">
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">实验室名称：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
            <input type="text" id="name" name="name" required>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">实验室地址：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
            <input type="text" id="address" name="address" required>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">实验室qq群(选填)：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
            <input type="text" id="qqGroup" name="qqGrop" digits>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">实验室简介：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
            <textarea id="labDescribe" name="labDescribe" required></textarea>
        </div>
    </div>
    <div class="p-lg text-center">
        <button class="btn btn-primary " type="submit">&nbsp;确定</button>
        <button class="btn btn-warning m-l-lg" type="button">取消</button>
    </div>
</form>
</body>
<script src="${base_path}/resources/common/jquery/jquery.min.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
<script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script>

    $.validator.setDefaults({
        submitHandler: function () {
            subLabInfo();
        }
    });
    $().ready(function () {
// 在键盘按下并释放及提交后验证提交表单
        $("#labInfo").validate({
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

    function subLabInfo() {
        layer.confirm('提交后无法修改,确定吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            subInfo();
        }, function () {
            layer.msg('已取消', {icon: 1});
        });
    }

    function subInfo() {
        //判断是否为空为空移除qqGropname属性
        var str = $("#qqGroup").val();
        if (str === '' || typeof (str) === 'undefined' || str === null) {
            $("#qqGroup").removeAttr("name");
        }
        ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: true,
                type: 'post',
                url: 'subLabInfo',
                data: $("#labInfo").serialize(),
                success: function (data) {
                    switch (data.status) {
                        case 200:
                            layer.msg("提交成功");
                            break;
                        case 1:
                            layer.msg("创建实验室失败,输入格式不正确或系统错误");
                            break;
                    }
                }
            })

        $("#qqGroup").add("name", "qqGroup");

    }
</script>
</html>