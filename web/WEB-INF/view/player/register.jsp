<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>球员注册</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/player/playerregister.css"/>

</head>
<body>
<div class="sl-bg">
    <div class="container">
        <div class="row">
            <div class="box_SR">
                <div class="login_bg text-center">
                    <!--注册面板标题-->
                    <div class="logo-w2 col-xs-10 col-xs-offset-1 form-group " style="margin-top: 15px">
                        <span class="title" style="color: green; font-size: 20px;">四川省青少年足球信息化</span>
                    </div>
                    <div class="logo-w2 col-xs-10 col-xs-offset-1 form-group" style="margin-bottom: 10px">
                        <span class="title" style="color: green; font-size: 18px;">————球员注册————</span>
                    </div>

                    <!--注册面板表单-->
                    <div class="form-horizontal" role="form">

                        <!--用户名-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <input id="username"  class="input" placeholder="请输入用户名：6-12位数字或字母" minlength="6" maxlength="12" type="text">
                        </div>

                        <!--姓名-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <input id="name"  class="input" placeholder="请输入姓名：1-12位中文" maxlength="12" type="text">
                        </div>

                        <!--输入密码-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <input id="password1" name="password1" class="input" placeholder="请输入密码：6-20位大小写字母或数字" minlength="6" maxlength="20" type="password" >
                        </div>

                        <!--确认密码-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <input id="password2" name="password2" class="input" placeholder="请再次输入密码：6-20位大小写或数字"  minlength="6" maxlength="20" type="password" >
                        </div>

                        <!--身份证号码-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <input id="idcard" class="input" placeholder="请输入身份证号码：18位" maxlength="18" type="text">
                        </div>

                        <!--地区信息-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <div class="" style="width: 100%;">
                                <select class="select1" id="city">
                                    <option value="">选择市</option>
                                </select>
                                <select class="select2" id="area" onchange="getOrg();return false;">
                                    <option value="">选择县</option>
                                </select>
                            </div>
                        </div>

                        <!--学校信息-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <select class="select3" id="school" onclick="checkOrg();return false;">
                                <option value="">选择学校</option>
                            </select>
                        </div>

                        <!--年级信息-->

                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <div class="" style="width: 100%;">
                                <select class="select4" id="grade">
                                    <option value="">选择年级</option>
                                    <option value="1">一年级</option>
                                    <option value="2">二年级</option>
                                    <option value="3">三年级</option>
                                    <option value="4">四年级</option>
                                    <option value="5">五年级</option>
                                    <option value="6">六年级</option>
                                    <option value="7">七年级</option>
                                    <option value="8">八年级</option>
                                    <option value="9">九年级</option>
                                </select>
                                <div class="input2">
                                    <input id="class" class="input2" placeholder="输入班级纯数字如：1"  type="text" maxlength="5" style="width: 100%;">
                                </div>
                            </div>
                        </div>
                        <!-- 验证码-->
                        <div class="col-xs-10 col-xs-offset-1 put0" style="margin-bottom: 10px">
                            <div class="put0" style="width: 100%;">
                                <div class="input2">
                                    <input id="code" class="input2" placeholder="请输入验证码"  maxlength="6"   type="text" style="width: 100%;">
                                </div>
                                <div class=""style="float: left ;width: 40%;margin-left: 4px;margin-top: 1px" >
                                    <!--验证码图片-->
                                    <img src="${path}/imageCode" id="imagecode" onclick="getCode();return false;">
                                </div>
                            </div>
                        </div>
                        <!--按钮-->
                        <div class="form-group">
                            <div class="col-xs-8" style="width: 100%">
                                <button id="button" style="color:#EEEEEE;" class="btn1 btn-success form-group" type="button" onclick="reg();return false;">注册</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${path}/resources/js/player/District.js"></script>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/jquery/jquery-1.11.3.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
<script src="${path}/resources/js/player/SoccerRegist.js"></script>
<script src="${path}/resources/js/index.js"></script>
<script type="text/javascript">
    function reg(){
        if(this.checkInput()){
            layer.confirm('您是否要注册？', {
                btn: ['确认','取消'] //按钮
                }, function(){
                    doSubmit();
                }, function(){
            });
        }else{
            getCode();
        }
    }
    function checkOrg(){
        var city = $("#city").val();
        var area = $("#area").val();
        if(city =="")
        {
            layer.msg('请选择市');
            return false;
        }
        else if(area=="")
        {
            layer.msg('请选择区县');
            return false;
        }

    }
    function checkInput(){
        var username = $("#username").val();
        var name = $("#name").val();
        var password1 = $("#password1").val();
        var password2 =$("#password2").val();
        var idcard = $("#idcard").val();
        var city = $("#city").val();
        var area = $("#area").val();
        var school = $("#school").val();
        var grade = $("#grade").val();
        var classes = $("#class").val();
        var imagecode =$("#code").val();

        /*正则-数字字母*/
        var reg = /^[0-9a-zA-Z]+$/;
        /*正则-中文*/
        var reg2 = /^[\u4E00-\u9FA5]*$/;
        /*正则-身份证*/

        var reg3 = /^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2018)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/;
        /*正则-纯数字*/
        var reg4 = /^[0-9]*$/;
        /*用户名判定*/
        if(username =="")
        {
            layer.msg('用户名不能为空');
            return false;
        }
        else if(!reg.test(username)){
            layer.msg('用户名存在非法字符');
            return false;
        }
        else if(username.length<6||username.length>12)
        {
            layer.msg('用户名位数不足');
            return false;
        }
        /*姓名判断*/
        else if(name=="")
        {
            layer.msg('姓名不能为空');
            return false;
        }
        else if(!reg2.test(name))
        {
            layer.msg('只允许中文输入');
            return false;
        }

        /*密码判定*/
        else if(password1 =="")
        {
            layer.msg('密码不能为空');
            return false;
        }
        else if(password1.length <6)
        {
            layer.msg('密码位数不足');
            return false;
        }
        else if(!reg.test(password1))
        {
            layer.msg('密码含有非法字符');
            return false;
        }
        /*密码确认*/
        else if(password2 != password1)
        {
            layer.msg('输入的密码不一样');
            return false;
        }
        /*身份证*/
        else if(idcard=="")
        {
            layer.msg('请输入身份证');
            return false;
        }
        else if(idcard.length<18)
        {
            layer.msg('身份证位数不够');
            return false;
        }
        else if(!reg3.test(idcard))
        {
            layer.msg('身份证格式有错误');
            return false;
        }
        /*下拉框*/
        else if(city =="")
        {
            layer.msg('请选择市');
            return false;
        }
        else if(area=="")
        {
            layer.msg('请选择区县');
            return false;
        }
        else if(school=="")
        {
            layer.msg('请选择学校');
            return false;
        }
        else if(grade=="")
        {
            layer.msg('请选择年级');
            return false;
        }
        /*班级判定*/
        else if(classes=="")
        {
            layer.msg('请输入班级');
            return false;
        }
        else if(!reg4.test(classes))
        {
            layer.msg('输入的班级有错误');
            return false;
        }
        /*验证码判定*/
        else if(imagecode=="")
        {
            layer.msg('验证码不能为空');
            return false;
        }
        return true;
    }
    function doSubmit() {
        ajax({
            type: "POST",   //提交的方法
            url:"${path}/player/register1", //提交的地址
            data: {
                userName:$("#username").val(),
                name:$("#name").val(),
                password:$("#password1").val(),
                idCard:$("#idcard").val(),
                grade:$("#grade").val(),
                classes:$("#class").val(),
                imageCode:$("#code").val(),
                schoolCode:$("#school").val(),
                token:$("#token").val()
            },
            dataType:'json',
            success: function(data) {  //成功
                if(data.status!=0){
                    getCode();
                }
                switch(data.status){
                    case 0:
                        layer.msg("注册成功，即将跳转登录页面");
                        setTimeout(function () {
                            window.location.href="${path}/player";
                        },500);
                        break;
                    case 1:
                        layer.msg("文本框输入有误");
                        break;
                    case 11:
                        layer.msg("验证码已失效，请重新刷新页面");
                        break;
                    case 12:
                        layer.msg("验证码输入错误");
                        break;
                    case 22:
                        layer.msg("该身份证号已存在");
                        break;
                    case 23:
                        layer.msg("非法字符、位数、格式或其他错误");
                        break;
                    case 25:
                        layer.msg("该用户名已经注册");
                        break;
                }
            },
            error: function() {  //失败的话
                layer.msg("请求异常");
            }
        });
    }
    function getCode()
    {
        $("#code").val("");
        $("#imagecode").attr('src',"${path}/imageCode?abc="+Math.random())//MAth.random是为了避免缓冲
    }
    function getOrg(){
        var countryCode = $("#city option:selected").val();
        var villageCode = $("#area option:selected").val();
        if(countryCode==""||villageCode==""){
            layer.msg("请先选择市、区，再选择学校")
            return false;
        }
        ajax({
            type: "POST",   //提交的方法
            url:"${path}/organization/showorg", //提交的地址
            data:{
                "countrycode":countryCode,
                "villagecode":villageCode
            },
            dataType:'json',
            success: function(data) {  //成功
                $("#school").empty();
                $("#school").append("<option value=''>"+"选择学校"+"</option>");
                $.each( data.data.OrgList,function(index,item){
                    $("#school").append("<option value='"+item.schoolCode+"'>"+item.name+"</option>");
                });
            },
            error: function() {  //失败的话
                msg("请求错误");
            }
        });
    }
</script>
</body>
</html>
