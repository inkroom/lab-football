/**
 * Created by MeiXiebing on 5/24/17.
 */
$("#butt").click(function(){
    var username = $("#username").val();
    if(username != null && username != ""){
        var password = $("#password").val();
        if(password != null && password != ""){
            password = encryptPasswordAjax(password,$("#salt").val());
            var code = $("#code").val();
            if (code != null && code != ""){
                $.ajax({
                    url:"login_view.action",    //请求的url地址
                    type:"post",   //请求方式
                    dataType:"json",   //返回格式为json
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data:{"userName":username,"password":password,"code":code},    //参数值
                    beforeSend:function(){
                        //请求前的处理
                        console.log("form submit");
                    },
                    success:function(data){
                        //请求成功时处理
                        layer.alert(data.msg,{closeBtn: 0},function () {
                            window.location.reload();
                        });
                    },
                    complete:function(){
                        //请求完成的处理
                    },
                    error:function(){
                        //请求出错处理
                        console.log("error");
                    }
                });
            }else{
                layer.alert("验证码不能为空!");
            }
        }else {
            layer.alert("密码不能为空!");
        }

    }else {
        layer.alert("用户名不能为空!");
    }
})
