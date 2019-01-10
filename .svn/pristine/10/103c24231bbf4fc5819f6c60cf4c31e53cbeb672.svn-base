/**
 * Created by 墨盒 on 2017/8/11.
 * 班级管理js
 */
$(document).ready(function () {
    var index;
    var load = function () {
        index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
        dataTable({
            id: "classManageTable",
            url: "init.action",
            none: '没有结果',
            key: 'classes',
            data: {
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
                {value: 'school'},
                {value: 'name'},
                {
                    value: 'joinTime',
                    callback: function FormatDate(strTime) {
                        var date = new Date(strTime);
                        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
                    }
                },
                {
                    value: 'status',
                    callback: function (data) {
                        if (data === 1 || data === "1") {
                            return "以前所在班级";
                        } else if (data === 2 || data === "2") {
                            return "目前所在班级";
                        } else if (data === 0 || data === "0") {
                            return "审核未通过";
                        } else {
                            return data;
                        }
                    }
                }
            ]
        });
    }
    load();
    $('#joinClass').on('click', function () {
        layer.open({
            type: 1,
            shade: false,
//                area: ['500px', '400px'],
            title: false, //不显示标题
            content: $('article').eq(0), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                // layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', {time: 5000, icon: 6});
            }
        });
    });
    $('#joinClassButton').on('click', function () {
        if (isNaN($('#classId').val())) {
            layer.msg('班级号只能是数字！', {time: 5000, icon: 6});
            return;
        }
        if (isNaN($('#key').val())) {
            layer.msg('口令只能是数字！', {time: 5000, icon: 6});
            return;
        }
        $.ajax({
            url: 'join_class.action',
            type: 'get',
            dataType: 'json',
            data: {
                classId: $('#classId').val(),
                key: $('#key').val(),
                _csrf: $('#_csrf').val()
            },
            success: function (result) {
                if (result.status === 500 || result.status === "500") {
                    layer.msg(result.msg);
                } else if (result.status === 200 || result.status === "200") {
                    layer.msg('申请成功，请等待教师审核');
                }
            },
            error: function () {
                layer.msg('网络错误！');
            }
        });
    })
});