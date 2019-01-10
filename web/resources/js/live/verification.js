$(function () {
    var count = 60;
    var countdown = 0;

    function CountDown() {
        var $confirm = $("#send").attr("disabled", true).addClass('disabled').val("重新发送(" + count + ")");
        if (count <= 0) {
            $confirm.val("发送验证码").removeAttr("disabled").removeClass('disabled');
            clearInterval(countdown);
        }
        count--;
    }

    $('#send').click(function () {

        var phone = $('#phone').val().trim();
        if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
            msg('请输入正确的手机号')
            return;
        }

        window.index = -1;
        ajax({
            url: getRootPath() + '/sendPhoneCode',
            data: {
                phone: phone,
                token: $('#token').html()
            },
            async:false,
            dataType: 'json',
            // beforeSend: function () {
            //     window.index = layer.open({type: 2});
            // },
            success: function (result) {
                // layer.close(window.index);
                switch (result.status) {
                    case 0:
                        countdown = setInterval(CountDown, 1000);
                        msg('验证码发送成功')
                        break;
                    case 1:
                        msg('验证码发送失败')
                        break;
                    case 15:
                        msg('短信发送过于频繁，请稍后再试');
                        break;
                    case 16:
                        msg('系统错误，请联系管理人员');
                        break;
                    case 19: //手机号码与之前的手机号码不一致
                        msg('手机号码不一致');
                        break;
                }
            },
            error: function () {
                // layer.close(window.index);
                msg('网络错误');
            }
        });

    });

    $('#submit').click(function () {
        var phone = $('#phone').val().trim();
        var code = $('#validate').val().trim();
        if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
            msg('请输入正确的手机号码')
            return;
        }
        if (code.length === 0) {
            msg('请输入验证码')
            return;
        }

        layer.open({
            content: '确认提交信息？'
            , btn: ['确定', '取消']
            , yes: function (index) {
                layer.close(index);

                ajax({
                    url: 'ver',
                    data: {
                        phone: phone,
                        code: code,
                        token: $('#token').html()
                    },
                    success: function (result) {
                        switch (result.status) {
                            case 0:
                                msg('认证成功，2秒后跳转页面');
                                setTimeout(function () {
                                    location.href = 'm/index#hash';
                                }, 2000);
                                break;
                            case 1:
                                msg('认证失败');
                                break;
                            case 11:
                                msg('验证码已过期');
                                break;
                            case 12:
                                msg('验证码错误')
                                break;
                            case 13:
                                msg('验证码错误次数过多，请重新获取')
                                break;
                            case 19:
                                msg('手机号码不一致');
                                break;
                        }
                    }
                })

            }
        })

    })
})

/*发送验证码*/
function send() {
    var count = 60;
    var countdown = setInterval(CountDown, 1000);

    function CountDown() {
        $("#confim").attr("disabled", true);
        $("#confim").val("重新发送(" + count + ")");
        if (count == 0) {
            $("#confim").val("发送验证码").removeAttr("disabled");
            clearInterval(countdown);
        }
        count--;
    }
}

/*手机验证*/
function sure() {
    var num = $('#num').val();
    var validate = $('#validate').val();
    if (num == '' || validate == '') {
        console.log("错误");
        $('#msg').removeClass('invisible');
    }
    else {
        console.log("正确");
        $('#msg').addClass('invisible');
    }
}