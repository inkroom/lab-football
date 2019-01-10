<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 修改密码</title>

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
<div class="ibox-title text-center p-lg">
    修改教师密码
</div>
<div class="row">
    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">教师名字：</div>
    <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
       ${ lTeacherBean.name}&nbsp;
    </div>
</div>
<div class="row">
    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">教师性别：</div>
    <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
        &nbsp;
            <c:if test="${lTeacherBean.gender==0}">
                男
            </c:if>
            <c:if test="${lTeacherBean.gender==1}">
                女
            </c:if>
    </div>
</div>
<div class="row">
    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right m-t-lg">重置密码(123456)：</div>
    <div class="col-lg-7 col-md-7 col-sm-7  col-xs-7   m-t-lg">
    </div>
</div>

<div class="p-lg text-center">
    <button class="btn btn-primary"  onclick="modifyPasswd(${lTeacherBean.id})" type="button">&nbsp;确定</button>
    <button class="btn btn-warning m-l-lg" type="button" onclick="history.back()"><i class="fa fa-warning"></i> <span class="bold">取消</span></button>
</div>
<script src="${base_path}/resources/common/jquery/jquery.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
<script>
    function modifyPasswd(id) {
        ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: false,
                type: 'post',
                url: '${base_path}/leader/resetPassword',
                data:{'id':id},
                success: function (data) {
                    if(data.status==200)
                    {
                        layer.msg("重置成功请刷新页面")
                    }
                    else if(data.status==1)
                    {
                        layer.msg("重置失败");
                    }

                },
                error: function () {
                    layer.msg("重置失败ss");
                }
            })
    }
</script>
</body>
</html>