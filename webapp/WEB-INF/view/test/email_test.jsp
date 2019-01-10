<%--
  Created by IntelliJ IDEA.
  User: yrge
  Date: 2017/5/17
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <input type="text" id = "email"/> <span id="Prompt1"></span> <button type="button" id="button_w">发送验证码</button> <br/>
    <input type="text" id="RegisterCode"><span id="Prompt2"></span>
</body>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script>
    var email = $("#email");
    var state = false;
    var RegisterCode = $("#RegisterCode");
    $("#button_w").click(function (){
        if (email.val() == ''){
            alert("邮箱不能为空")
            return false;
        }
        $.ajax({
            url: "../email/getCode.action",
            type: "POST",
            data:{
                "email" : email.val()
            },
            dataType : "JSON",
            success : function (json) {
                alert("json=" + json);
                alert("JSON.stringify="+JSON.stringify(json));
                if (json.success){
                    $("#Prompt1").text(json.msg);
                }else {
                    $("#Prompt1").text(json.msg);
                }
            },
            error : function () {
                alert("服务器错误！")
            }
        })
    })
    $("#RegisterCode").blur(function () {

        $.ajax({
            url:"../email/getValidateCode.action",
            type : "POST",
            data:{
                "email" : email.val(),
                "randomCode" : RegisterCode.val()
            },
            dataType: "JSON",

            success : function (json) {
                if (json.success){
                    $("#Prompt2").text(json.msg).css("color","green");
                    state = true;
                }else {
                    $("#Prompt2").text(json.msg).css("color","red");
                    state = false;
                }

            },
            error : function () {
                alert("服务器错误！")
            }
        })
    })
</script>
</html>
