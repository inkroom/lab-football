/**
 * Created by ChenGang on 2017/10/19 0019.
 */
//获取验证码

//登陆成功跳转
function tologin(authority) {
    //身份判断决定跳转
    if (authority === 0) {
        location.href = 'student/index'
    }
    else if (authority === 1) {
        location.href = 'admin/index'
    }
    else if (authority === 2) {
        location.href = 'Teacher/index'
    }
    else if (authority === 3) {
        location.href = 'leader/index'
    }
    else {

                        layer.msg("您已退出实验室，无法登陆");
    }
}

function checklogin() {
    ajax(
        {
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            async: false,
            type: 'post',
            url: 'login',
            data: {
                'username': $("#username").val(),
                'password': $("#password").val(),
                'role': 1,
                'code': $("#code").val()
            },
            success: function (data) {
                switch (data.status) {
                    case 1:
                        layer.msg("账号密码错误");
                        getCode();
                        break;
                    case 12:
                        layer.msg("验证码错误");
                        getCode();
                        break;

                    case 200:
                        layer.msg("登陆成功：正在跳转");
                        tologin(data.data.authority);//跳转页面
                        break;

                    case  8:
                        layer.msg("格式不能为空");
                        break;
                    default:
                        layer.msg("系统错误请联系管理员或重试");
                        break
                }
            },
            error: function () {
                layer.msg("连接失败系统异常");
            }
        })
}

function ModifyPasswd(base) {
    ajax(
        {
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            async: false,
            type: 'post',
            url: base + '/updatePassword',
            data: {
                'password': $("#password").val(),
                'newPassword': $("#newPassword").val(),
                'newPassword2': $("#newPassword2").val()
            },
            success: function (data) {
                switch (data.status) {
                    case 1:
                        layer.msg("原密码错误");
                        break;
                    case 2:
                        layer.msg("登陆失效请重新登陆");
                        break
                    case 10:
                        layer.msg("两次输入密码不一致");
                        break;
                    case 200:
                        layer.msg("修改成功");
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