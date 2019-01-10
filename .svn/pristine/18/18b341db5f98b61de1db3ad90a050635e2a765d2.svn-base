$(function () {
    $('#isRemeber').on('change', function () {
        if (!$(this).is(':checked')) {
            $('#isAuto').prop('checked', false);
        }
    });
    $('#isAuto').on('change', function () {
        if ($(this).is(':checked')) {
            $('#isRemeber').prop('checked', true);
        }
    })
    $('#submit').click(function () {

        var name = $('#username').val();
        var pass = $('#password').val();
        if (name === '') {
            $('#msg').html('请输入用户名').removeClass('invisible');
            return;
        } else if (pass === '') {
            $('#msg').html('请输入密码').removeClass('invisible');
            return;
        }
        $('#msg').html('').addClass('invisible');
        ajax({
            url: getRootPath() + '/referee/login',
            data: {
                username: name,
                password: pass,
                isRemember: $('#isRemember').is(':checked'),
                isAuto: $('#isAuto').is(':checked'),
                token: $('#token').html()
            },
            dataType: 'json',
            type: 'post',
            success: function (result) {
                switch (result.status) {
                    case 0:
                        msg('登录成功')
                        // $('#msg').html('登录成功').removeClass('invisible');
                        if (location.href.indexOf("login") !== -1) {
                            location.href = getRootPath() + '/referee/index';
                            break;
                        }
                        location.reload();
                        break;
                    case 1:
                        msg('账号或密码错误')
                        // $('#msg').html('账号或密码错误').removeClass('invisible');
                        break;
                    case 20:
                        msg('无效的账号')
                        // $('#msg').html('无效的账号').removeClass('invisible');
                }
            },
            error: function (e) {
                $('#msg').html('网络错误').removeClass('invisible');
            }
        })
    })
})