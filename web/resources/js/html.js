/**
 * 获取参数
 * @param name
 * @returns {*}
 * @constructor
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function trans(value) {
    return parseInt(value) < 10 ? ('0' + value) : value;
}

function transTime(mill) {
    var time = new Date(mill);
    return time.getFullYear() + '年' + trans(time.getMonth() + 1) + '月' + trans(time.getDate())
        + '日 ' + trans(time.getHours()) + ':' + trans(time.getMinutes()) + ':' + trans(time.getSeconds());
}

function change(id, obj, prefix) {
    var index = 0;
    $.ajax({
        url: prefix + '/html/html.action',
        data: {id: id},
        type: 'get',
        dataType: 'json',
        success: function (result) {
            layer.close(index);
            if (result.status === 0) {
                $('#external-html').html(result.data.union.content)
                    .append('<p style="margin-top: 20px;text-align: right">最后编辑于' + transTime(result.data.union.createTime) + '</p>')
                    .prepend('<h3 style="text-align: center">' + result.data.union.title + '</h3>');
                var nIndex = $("#accordion a").index($(obj));
                if (window.history) {
                    window.history.pushState(null, null, window.location.pathname + '?index=' + nIndex);
                } else {
                    window.location.hash = nIndex + '';
                }
                // window.location.hash = $(obj).attr('id');
            } else if (result.status === 1) {
                layer.msg(result.message);
            }
        },
        error: function () {
            layer.close(index);
            layer.alert('网络错误');
        },
        beforeSend: function () {
            index = layer.load(1);
        }
    })
}

$(function () {

    var length = $("#accordion a").length;
    var index = getQueryString('index');
    var hash = window.location.hash;
    var temp;
    if (index !== null && index.length > 0 && /[1-9]*[0-9]+/.test(index) && (temp = parseInt(index)) < length) {
        index = temp;
    } else {
        index = 0;
    }
    //兼容不支持h5的浏览器使用哈希值来判断
    if (hash.length > 1 && /[1-9]*[0-9]+/.test(hash.substring(1) && (temp = parseInt(hash.substring(1))) !== index) || temp < length) {
        index = temp;
    }
    var a = $('#accordion a:eq(' + index + ')');
    a.trigger('click');
});

