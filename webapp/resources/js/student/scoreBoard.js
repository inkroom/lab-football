/**
 * Created by 墨盒 on 2017/8/11.
 * 积分榜js
 */
$(function () {
    var index;
    var load = function () {
        index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        dataTable({
            id: "scoreBoardTable",
            url: "search.action",
            none: '没有结果',
            key: 'scores',
            data: {
                "isBlur": $('#blur').is(':checked') ? 1 : 0,
                "isStudent": $('#type').val(),
                "content": $('#content').val(),
                "schoolName": $('#school').val(),
                _csrf: $('#_csrf').val()
            },
            before: function (result) {
                if (result.status === 500 || result.status === "500") {
                    layer.msg(result.msg);
                    return false;
                }
                return true;
            },
            after: function () {
                layer.close(index);
            },
            error: function () {
                layer.close(index);
                layer.msg('网络错误！');
            },
            maxPage: 10,
            cols: [
                {value: 'name'},
                {value: 'grade'},
                {value: 'school'},
                {value: 'number'},
                {value: 'score'},
                {
                    value: 'rank',
                    callback: function (data) {
                        if (data === 1 || data === "1") {
                            return "<i class='icon Hui-iconfont' style='color:#FF0000;font-size:24px'>&#xe6d3;</i>";
                        } else if (data === 2 || data === "2") {
                            return "<i class='icon Hui-iconfont' style='color:#FF0000;font-size:24px'>&#xe72c;</i>";
                        } else if (data === 3 || data === "3") {
                            return "<i class='icon Hui-iconfont' style='color:#FF0000;font-size:24px'>&#xe61b;</i>";
                        } else {
                            return data;
                        }
                    }
                }
            ]
        });
    }
    load();
    $('#search_button').on('click', function () {
        $('#school').val('');
        load();
    });
    $('#school').on('change', function () {
        $('#type').val('1');
        $('#content').val($(this).val());
        load();
    });
});
