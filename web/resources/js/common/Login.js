// function checklogin() {
//     $.ajax(
//         {
//             contentType: "application/x-www-form-urlencoded;charset=utf-8",
//             async: false,
//             type: 'post',
//             url: 'login.action',
//             data: {
//                 'username': $("#username").val(),
//                 'password': $("#password").val(),
//                 'code': $("#code").val()
//             },
//             success: function (data) {
//                 alert("checklogin():"+data.status);
//                 switch(data.status)
//                 {
//                     case 1:
//                         layer.msg("账号或者密码错误");
//                         $("#Imagecode").trigger('click');
//                         getCode();
//                         break;
//                     case 11:
//                         layer.msg("验证码过期");
//                         $("#Imagecode").trigger('click');
//                         getCode();
//                         break;
//
//                     case 12:
//                         layer.msg("验证码错误");
//                         $("#Imagecode").trigger("click");
//                         getCode();
//                         break;
//
//                     case 200:
//                         layer.msg("登陆成功：正在跳转");
//                         //跳转页面
//                         location.href = 'backstage/index.html';
//                         break;
//
//                     default:
//                         layer.msg("系统错误请联系管理员或重试");
//                         $("#Imagecode").trigger("click");
//                         getCode();
//                         break
//                 }
//             },
//             error: function () {
//                 layer.msg("连接失败系统异常");
//                 $("#Imagecode").trigger("click");
//                 getCode();
//             }
//         })
// }


//点击切换验证码
function getCode()
{
    console.log("点击了验证码");
    $("#Imagecode").attr('src',"${pageContext.request.contextPath}/getCode/getImageCode.action?abc="+Math.random());//MAth.random是为了避免缓冲
}


function ModifyPasswd() {
    $.ajax(
        {
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            async: false,
            type: 'post',
            url: 'changepsw.action',
            data: {
                'password': $("#password").val(),
                'newPassword': $("#newPassword").val(),
                'newPassword2': $("#newPassword2").val()
            },
            success: function (data) {
                switch(data.status)
                {
                    case 1:
                        layer.msg("原密码错误");
                        break;
                    case 2:
                        layer.msg("登陆失效请重新登陆");
                        break;
                    case 10:
                        layer.msg("两次输入密码不一致");
                        break;
                    case 200:
                        layer.msg("修改成功");
                        //跳转页面
                        // location.href = '/login.html';
                        break;
                    case  8:
                        layer.msg("格式不能为空");
                        break;
                    case  5:
                        layer.msg("修改失败失败请联系管理员");
                        break;
                    default:
                        layer.msg("系统错误请联系管理员或重试");
                        break;
                }
            },
            error: function () {
                layer.msg("连接失败系统异常");
            }
        })
}