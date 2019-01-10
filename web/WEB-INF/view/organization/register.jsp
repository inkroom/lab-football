<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>机构注册</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/player/orgregister.css"/>

</head>
<body>
<div class="or-bg">
    <div class="container">
        <div class="row">
            <div class="box_OR">
                <form class="login_bg text-center">
                    <!--注册面板标题-->
                    <div class="logo-w2 col-xs-10 col-xs-offset-1 form-group" style="margin-top: 25px">
                        <span class="title" style="color: green; font-size: 20px;">四川省青少年足球信息化</span>
                    </div>
                    <div class="logo-w2 col-xs-10 col-xs-offset-1 form-group" style="margin-bottom: 20px">
                        <span class="title" style="color: green; font-size: 18px;">———— 机构注册 ————</span>
                    </div>
                    <!--注册面板表单-->
                    <div class="form-horizontal" role="form">
                        <!--地区-->

                        <div class="col-xs-10 col-xs-offset-1 put1">
                            <div class="input-group" style="width: 100%;">
                                <select class="select1" id="city">
                                    <option value="">选择市</option>
                                </select>
                                <select class="select2" id="area" onchange="getOrg();return false;">
                                    <option value="">选择县</option>
                                </select>
                            </div>
                        </div>


                        <!--学校信息-->
                        <div class="col-xs-10 col-xs-offset-1 put1">
                            <select class="select3" id="school" onclick="checkOrg();return false;">
                                <option value="">选择学校</option>
                                <c:forEach var="org" items="${orgList}" >
                                    <option value="${org.schoolCode}">${org.name}</option>
                                </c:forEach>
                            </select>
                        </div>


                        <!--输入密码-->
                        <div class="col-xs-10 col-xs-offset-1 put1">
                            <input id="password1" name="password1" class="input" style="border-radius: 5px"  placeholder="请输入密码：6-20位"  maxlength="20" type="password" >
                        </div>

                        <!--确认密码-->
                        <div class="col-xs-10 col-xs-offset-1 put1">
                            <input id="password2" name="password2" class="input" style="border-radius: 5px" placeholder="请再次输入密码：6-20位"  maxlength="20" type="password" >
                        </div>


                        <!--输入电话号码-->
                        <div class="col-xs-10 col-xs-offset-1 put1">
                            <input id="phone" class="input" style="border-radius: 5px" placeholder="请输入电话号码：11位" maxlength="11" type="text">
                        </div>

                       <%-- <!--图片验证-->
                        <div class="col-xs-6 col-xs-offset-1 put1" style="float: left">
                            <div class="" style="width: 90%;padding-bottom: 16px;">
                                <input id="code" class="input" style="border-radius: 5px" placeholder="请输入验证码" maxlength="6" type="text">
                            </div>
                        </div>
                        <div style="float: left ;width: 30%;height: 34px;margin-left: 10px;" class="col-xs-6">
                            <!--验证码图片-->
                            <img src="${path}/imageCode" onclick="getCode();" id="checkimagecode">
                        </div>--%>

                        <!-- 验证码-->
                        <div class="col-xs-10 col-xs-offset-1 put0">
                            <div class="put0" style="width: 100%;">
                                <div class="input2">
                                    <input id="code" class="input2" placeholder="请输入验证码"  maxlength="6"   type="text" style="width: 100%;">
                                </div>
                                <div class=""style="float: left ;width: 40%;margin-left: 4px;margin-top: 1px" >
                                    <!--验证码图片-->
                                    <img src="${path}/imageCode" id="checkimagecode" onclick="getCode();return false;">
                                </div>
                            </div>
                        </div>

                        <!--注册按钮-->
                        <div class="form-group">
                            <div class="col-xs-8" style="width: 100%">
                                <button id="button" style="color:#eeeeee;" class="btn1 btn-success form-group" type="button" onclick="reg();return false;">注册</button>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/jquery/jquery-1.11.3.js"></script>
<script src="${path}/resources/js/player/District.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
    <script src="${path}/resources/js/player/OrganizationRegist.js"></script>
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
        var city = $("#city").val();
        var area = $("#area").val();
        var school = $("#school").val();
        var password1 = $("#password1").val();
        var password2 =$("#password2").val();
        var phone = $("#phone").val();
        var code =$("#code").val();
        /*正则-非法字符*/
        var reg = /^[0-9a-zA-Z]+$/;
        /*手机号*/
        var reg2=/^1[34578]\d{9}$/;
        /*下拉框判定*/
        if(city =="")
        {
            layer.msg('市下拉框没有选择');
            return false;
        }
        else if(area=="")
        {
            layer.msg('区县下拉框没有选择');
            return false;
        }
        else if(school=="")
        {
            layer.msg('学校下拉框没有选择');
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
        /*电话号码*/
        else if(phone=="")
        {
            layer.msg('电话不能为空');
            return false;
        }
        else if(phone.length <11)
        {
            layer.msg('电话号位数不足');
            return false;
        }
        else if(!reg2.test(phone))
        {
            layer.msg('电话号输入有误');
            return false;
        }
        /*验证码判定*/
        else if(code=="")
        {
            layer.msg('验证码不能为空');
            return false;
        }
        return true;
    }
    function doSubmit() {
        ajax({
            type: "POST",   //提交的方法
            url:"${path}/organization/register1", //提交的地址
            data:{
                phone:$("#phone").val(),
                password:$("#password1").val(),
                imagecode:$("#code").val(),
                schoolcode:$("#school").val(),
                token:$("#token").val()
            },
            dataType:'json',
            success: function(data) {  //成功
                if(data.status!=0){
                    getCode();
                }
                //就将返回的数据显示出来
                switch (data.status){
                    case 0:
                        layer.msg("注册成功，请登录");
                        setTimeout(function(){
                            window.location.href="${path}/organization";
                            },1000);
                        break;
                    case 1:
                        layer.msg("密码或验证码输入错误");
                        break;
                    case 11:
                        layer.msg("验证码已失效，请重新刷新页面");
                        break;
                    case 12:
                        layer.msg("验证码输入错误");
                        break;
                    case 23:
                        layer.msg("非法字符、位数、格式等其他错误");
                        break;
                    case 24:
                        layer.msg("该机构已经注册，请登录");
                        break;
                }
            },
            error: function(data) {  //失败的话
                console.log(data);
            }
        });
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
            error: function(data) {  //失败的话
                console.log(data);
            }
        });
    }
    function getCode()
    {
        $("#code").val("");
        $("#checkimagecode").attr('src',"${path}/imageCode?abc="+Math.random())//MAth.random是为了避免缓冲
    }
</script>
</body>
</html>
