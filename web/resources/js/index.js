function msg(msg) {
    if (layer.v.indexOf('2.0') > -1 || layer.v === '2.0' || layer.v === 2) {//移动端
        layer.open({
            content: msg
            , skin: 'msg'
            , time: 2 //2秒后自动关闭
        });
    } else {
        layer.msg(msg);
    }
}

function checkToken(setting) {
    if (typeof (setting.header) !== 'undefined' && typeof (setting.header.token) !== 'undefined' && setting.header.token !== null && setting.header.token === '')
        return true;
    return typeof (setting.data) !== 'undefined' && typeof (setting.data.token) !== 'undefined' && setting.data.token !== null && setting.data.token === '';

}

function ajax(setting) {
    if (checkToken(setting)) {
        return;
    }
    $('#token').html('');
    var temp = setting.success;
    setting.success = function (result) {
        $('#token').html(result.token);
        try {
            if (window.index)
                layer.close(window.index);
        } catch (e) {

        }

        switch (result.status) {
            case 2://未登录
                msg('您未登录');
                setTimeout(2000, function () {
                    location.href = getRootPath() + '';
                })
                break;
            case 3://没有权限
                msg('您没有权限')

                break;
            case 4://异地登录
                break;
            case 5://系统异常
                msg('系统错误')
                break;
            case 6://参数错误
                msg('错误请求')
                break;
            case 7://参数长度错误
                break;
            case 8://参数类型错误
                break;
            case 14://token错误，页面过期
                msg('页面已过期，请刷新页面');
                // setTimeout(2000, function () {
                //     location.reload();
                // })
                break;
            case 17://裁判身份信息未完善
                msg('您未完善信息，不允许操作')
                break;
            case 18://赛程已结束
                msg('比赛已结束，请勿重复提交')
                break;
            default:
                temp(result);
        }
    }
    $.ajax(setting)
}

/**
 * 获取contextPath
 * @returns {string} 如4th，注:没有斜杠
 */
function getRootPath() {
    var s = document.getElementsByTagName('script');
    var src;
    for (var i = 0; i < s.length; i++) {
        src = s[i].src;
        if (src !== '')
            break;
    }
    var regex = new RegExp(location.host + '/(.*)/resources');
    var result = (regex.exec(src));
    if (result !== null && result.length > 1)
        return '/' + result[1];
    else {
        return "";
    }
}