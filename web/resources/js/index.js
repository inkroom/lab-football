/**
 * Created by 墨盒 on 2017/8/16.
 * 基本的js方法
 */
function show(content, btn, yes) {
    var index = layer.open({
        type: 0
        , title: false
        , icon: false
        , shade: 0.3
        , content: content
        , btn: btn === null ? '知道了' : btn
        , btnAlign: 'c' //按钮居中
        , yes: function () {
            layer.close(index);
            if (yes !== null && typeof yes === 'function')
                yes();
        }, cancel: function () {
            layer.close(index);
            if (yes !== null && typeof yes === 'function')
                yes();
        }
    });
}
function layerConfirm(content, callback) {
    var index = layer.confirm(content, {btn: ['确定', '取消']}, function () {
        layer.close(index);
        callback();
    }, function () {

    });
}
//提前处理ajax返回的数据
function ajax(data) {
    //TODO loading层无法关闭
    var temp = data.success;
    data.dataType = 'json';
    console.log(temp);
    data.success = function (result) {
        var tempIndex = layer.index + 2;
        // var index = layer.getFrameIndex(window.name);
        // layer.close(index);
        // layer.closeAll();
        switch (result.status) {
            case 2://参数格式不正确
                show(result.message, '知道了', null);
                return;
            case 3://登陆失效或未登录
                show('登陆已失效或未登录', '重新登陆', function () {
                    location.href = '/login'
                });
                return;
            case 4://没有权限
                show('权限不足', null, null);
                return;
            case 5://已经异地登陆
                show('您的账号在异地登陆，您已掉线', '重新登陆', function () {
                    location.href = '/login';
                });
                return;
            case 6://发生异常
                show(result.message, null, null);
                return;
        }
        if (typeof temp === 'function')
            temp(result);
    };
    var temp1 = data.error;
    data.error = function () {
        // layer.close(index);
        layer.closeAll();
        show('网络错误！');
        if (typeof temp1 === 'function')
            temp1();
    };
    var temp2 = data.complete;
    data.complete = function () {
        // layer.index = ; //0代表加载的风格，支持0-2
        // index = layer.load(0, {
        //     shade: false //0.1透明度的白色背景
        // });
        if (typeof temp2 === 'function')
            temp2();
    };
    $.ajax(data);
}
var index;