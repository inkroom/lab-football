<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 添加教师</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
</head>
<body>
<div style="height: 120px"></div>
<form id="addTeacher" type="post">
    <div class="ibox-title text-center p-lg">

    </div>
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg" >教师名字：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
            <input type="text" id="name" name="name" required>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-5  col-md-5 col-sm-5  col-xs-5  text-right m-t-lg">老师性别：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
                <select  id="gender" name="gender" required>
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5  col-md-5 col-sm-5  col-xs-5  text-right m-t-lg">老师电话：</div>
        <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
            <input type="text" id="tel" name="tel" required>
        </div>
    </div>
    <%--<div class="row">--%>
    <%--<div class="col-lg-5  col-md-5 col-sm-5  col-xs-5  text-right m-t-lg">所属实验室：</div>--%>
    <%--<div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">--%>
    <%--<form>--%>
    <%--<select class="" id = "lId" name ="lid">--%>
    <%----%>

    <%--</select>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--</div>--%>

    <div class="p-lg text-center">
        <button class="btn btn-primary" type="submit">确定</button>
        <button class="btn btn-warning m-l-lg" type="reset">取消</button>
    </div>
    <%--<button  type="submit">确定</button>--%>
    <%--<button  type="reset">取消</button>--%>
</form>
</body>
<script src="${base_path}/resources/common/jquery/jquery.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
<script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script>
    $.validator.setDefaults({
        submitHandler: function() {
          subTeacherInfo();
        }
    });
    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
        $("#addTeacher").validate({
                //重写showErrors
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
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
            subInfo();
        }, function () {
            layer.msg('已取消', {icon: 1});
        });
    }
    function  subInfo() {
        ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: true,
                type: 'post',
                url: 'addTeacher',
                data:$("#addTeacher").serialize(),
                success: function (data) {
                    switch(data.status)
                    {
                        case 200:
                            layer.msg("创建老师成功");
                            break;
                        case 1:
                            layer.msg("创建老师失败,输入格式不正确或系统错误");
                            break;
                    }
                },
                error: function () {
                    layer.msg("无法连接到系统，请联系管理员");

                }
            })
    }
</script>
</html>