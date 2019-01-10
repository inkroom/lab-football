$(function () {
    /*发送验证码*/
    $('#send').click(function () {
        var count = 60;
        var countdown = 0;
        if (valid(false)) {
            var $msg = $('#msg');
            window.index = -1;
            ajax({
                url: getRootPath() + '/sendPhoneCode',
                data: {
                    phone: $('#phone').val(),
                    token: $('#token').html()
                },
                async:false,
                dataType: 'json',
                beforeSend: function () {
                    window.index = layer.open({type: 2});
                },
                success: function (result) {
                    layer.close(index);
                    switch (result.status) {
                        case 0:
                            countdown = setInterval(CountDown, 1000);
                            msg('验证码发送成功')
                            break;
                        case 1:
                            msg('验证码发送失败')
                            break;
                        case 15:
                            msg('验证码发送过于频繁，请稍后再试');
                            break;
                        case 16:
                            msg('验证码发送失败，请联系管理人员');
                            break;
                    }
                },
                error: function () {
                    layer.close(index);
                    msg('网络错误')
                }
            });
        }

        function CountDown() {
            var $confirm = $("#confirm").attr("disabled", true).addClass('disabled').val("重新发送(" + count + ")");
            if (count <= 0) {
                $confirm.val("发送验证码").removeAttr("disabled").removeClass('disabled');
                clearInterval(countdown);
            }
            count--;
        }
    })


    $('#submit').click(function () {
        if (valid(true)) {
            layer.open({
                content: '确认提交信息？'
                , btn: ['确定', '取消']
                , yes: function (index) {
                    layer.close(index);
                    var name = $('#name').val();
                    var card = $('#card').val();
                    var phone = $('#phone').val();
                    var veri = $('#verification').val();
                    var $msg = $('#msg');
                    var token = $('#token').html();
                    // $msg.addClass('invisible');
                    ajax({
                        url: 'consummate',
                        type: 'get',
                        dataType: 'json',
                        data: {
                            name: name,
                            phone: phone,
                            id: card,
                            code: veri,
                            token: token
                        },
                        success: function (result) {
                            switch (result.status) {
                                case 0:
                                    msg('完善身份成功');
                                    $('#token').val('');
                                    //跳往首页
                                    location.reload();
                                    break;
                                case 1:
                                    msg('完善身份失败');
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
                                case 19: //手机号码与之前的手机号码不一致
                                    msg('手机号码不一致');
                                    break;
                            }
                        },
                        error: function () {
                            msg('网络错误');
                        }
                    });
                }
            });

        }
    })

})

/**
 * 验证输入框
 * @param isCode 是否验证验证码
 * @returns {boolean} 通过返回true
 */
function valid(isCode) {
    var name = $('#name').val();
    var card = $('#card').val();
    var phone = $('#phone').val();
    var veri = $('#verification').val();
    var $msg = $('#msg');
    if (name === '') {
        msg('名字不能为空');
        return false;
    } else if (card === '') {
        msg('身份证不能为空');
        return false;
    } else if (phone === '') {
        msg('电话号码不能为空');
        return false;
    } else if (isCode && veri === '') {
        msg('验证码不能为空');
        return false;
    } else if (!/[\u4e00-\u9fa5]+/.test(name)) {
        msg('请输入中文姓名');
        return false;
    } else if (!/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/.test(card)) {
        msg('请输入正确的身份证号');
        return false;
    }
    else if (!/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
        msg('请输入正确的手机号');
        return false;
    }
    return true;
}

// /*提交按钮*/
// function confirm() {
//
// }
