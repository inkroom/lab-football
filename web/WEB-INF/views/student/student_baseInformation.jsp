<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 基本表单</title>

    <link rel="shortcut icon" href="favicon.ico">

    <link href="${base_path}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base_path}/resources/css/teacher/iCheck/custom.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">


</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生信息完善</h5>
                </div>
                <div class="ibox-content">

                    <form class="form-horizontal" id="stuInfo">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" rangelength="[1,14]" name="name" id="name"
                                       required value="${studentMap.get("name")}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">身份证</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="IDcard" id="IDcard" required
                                       value="${studentMap.get("IDcard")}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>

                            <div class="col-sm-10">
                                <div class="radio i-checks">
                                    <label>
                                        <input type="radio" value="0"
                                        <c:if test="${!studentMap.get('gender')}">
                                               checked="checked"
                                        </c:if>
                                               name="gender" id="gender1" required> <i></i>男</label>
                                </div>
                                <div class="radio i-checks">
                                    <label>
                                        <input type="radio"
                                        <c:if test="${studentMap.get('gender')}">
                                               checked="checked"
                                        </c:if>
                                               value="1" name="gender" id="gender2"> <i></i>女</label>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label">年级</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" rangelength="[4,4]" id="grade" name="grade"
                                       required digits value="${studentMap.get("grade")}" placeholder="请输入年级，如2015">级
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">系部</label>
                            <div class="col-sm-10">
                                <select id="department" name="department" class="form-control m-b">
                                    <option value="信息与软件工程系">信息与软件工程系</option>
                                    <option value="信息技术与商务管理系">信息技术与商务管理系</option>
                                    <option value="数字艺术系">数字艺术系</option>
                                    <option value="应用外语系">应用外语系</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">专业</label>

                            <div class="col-sm-10">
                                <select class="form-control m-b" name="maj_id" id="maj_id">
                                    <c:forEach var="el" items="${sMajorBeans}">
                                        <option value="${el.id}"
                                                <c:if test="${el.id== studentMap.get('maj_id')}">
                                                    selected = "selected"
                                                </c:if>
                                        >${el.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">班级</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="stuClass" name="stuClass" required
                                       rangelength="[5,5]" digits value="${studentMap.get("stuClass")}"
                                       placeholder="输入班级，如15201">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">辅导员</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" rangelength="[1,14]" name="instructor"
                                       id="instructor" required value="${studentMap.get("instructor")}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">电话</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="tel" id="tel" value="${studentMap.get("tel")}">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">所属实验室</label>--%>

                        <%--<div class="col-sm-10">--%>
                        <%--<select class="form-control m-b" name="lab_id" id="lab_id">--%>
                        <%--<c:forEach var="el"   items="${sLabBeans}">--%>
                        <%--<option value="${el.id}"--%>
                        <%--<c:if test="${el.id== studentMap.get('lab_id')}">--%>
                        <%--selected = "selected"--%>
                        <%--</c:if>--%>
                        <%-->${el.name}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">加入实验室时间</label>
                            <div class="col-sm-10">
                                <input class="form-control layer-date" value="${studentMap.get("time")}"
                                       placeholder="YYYY-MM-DD" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
                                       id="time" name="time" required>
                                <label class="laydate-icon"></label>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">高中毕业学校</label>--%>

                        <%--<div class="col-sm-10">--%>
                        <%--<input type="text" class="form-control" name="highSchool" id="highSchool">--%>
                        <%--</div>        --%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">所在地区</label>--%>

                        <%--<div class="col-sm-10">--%>
                        <%--<input type="text" class="form-control" name="region" id="region">--%>
                        <%--</div>        --%>
                        <%--</div>--%>


                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button type="submit" class="btn btn-primary" id="submitButton">保存内容</button>
                                <button type="submit" class="btn btn-white">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<script src="${base_path}/resources/common/jquery/jquery.min.js"></script>
<script src="${base_path}/resources/js/index.js"></script>
<script src="${base_path}/resources/js/student/laydate/laydate.js"></script>
<script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
<script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
<script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>


<script>

    jQuery.validator.addMethod("isPositive", function (value, element) {
      return IdentityCodeValid(value);
    }, "身份证格式不正确");
    jQuery.validator.addMethod("isPhone", function (value, element) {
        var myreg = /^1(3|4|5|7|8)\d{9}$/;
        if(myreg.test(value))
        {
            return true;
        }
        else
            return false;
    }, "手机号不正确");

    $.validator.setDefaults({
        submitHandler: function () {
            subStuInfo();
        }
    });
    $().ready(function () {
// 在键盘按下并释放及提交后验证提交表单
        $("#stuInfo").validate({

                //重写showErrors
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, {tips: [1, "#cc2a32"], time: 2000});
                        return false;
                    });
                }, rules: {
                    IDcard: {
                        required: true,
                        isPositive: true
                    },
                    tel: {
                        required: true,
                        isPhone: true
                    }

                },

                /* 失去焦点时不验证 */
                onfocusout: false
            }
        )
    })

    function subStuInfo() {
        layer.confirm('提交后无法修改,确定吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            subInfo();
        }, function () {
            layer.msg('已取消', {icon: 1});
        });
    }

    function subInfo() {
        ajax(
            {
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                async: false,
                cache: false,
                type: 'post',
                url: 'submitStuInfo',
                data: $("#stuInfo").serialize(),
                success: function (data) {
                    switch (data.status) {
                        case 200:
                            layer.msg("提交成功");
                            break;
                        case 1:
                            layer.msg("提交失败，请重试");
                            break;
                    }
                },
                error: function () {
                    layer.msg("系统错误，请联系管理员");
                    console.log($("#stuInfo").serialize());
                    // layer.msg( JSON.stringify($("#stuInfo").serializeObject()));
                    //layer.msg($("#stuInfo").serialize());
                }
            })

    }

    //身份证验证
    function IdentityCodeValid(code) {
        var city = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江 ",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北 ",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏 ",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外 "
        };
        var pass = true;

        if (!code|| !/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/i.test(code)) {
            pass = false;
        }
        else if (!city[code.substr(0, 2)]) {
            pass = false;
        }
        return pass;
    }

</script>

<script>
    //外部js调用
    laydate({
        elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });

    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD hh:mm:ss',
        min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD hh:mm:ss',
        min: laydate.now(),
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

</script>

</html>
