/**
 * Created by Jerry on 17/6/3.
 */

var basePath = $('#base_path').val();
var error = $('#error').val();

/*后台校验错误信息提示*/
$(function () {
    layer.ready(function () {
        if (error != null && error != '') {
            layer.alert(error);
            console.log(error);
        }
    });
});

/*接收数据*/
var vm = avalon.define({
    $id: "for_w",
    json: []
});

demo();

function demo(curr) {
    $.ajax({
        url: '' + basePath + '/org/findQueryAll.action',
        type: "POST",
        data: {
            "pageNum": curr || 1,
            "parameter": $("#search_info").val(),
        },
        dataType: "json",
        success: function (data) {
            if (JSON.stringify(data.list) == "[]") {
                $('tbody').append('<tr><td colspan="6">暂无数据</td></tr>');
            }
            vm.json = data.list; //给 json 赋值
            //显示分页
            laypage({
                cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                pages: data.pages, //通过后台拿到的总页数
                curr: curr || 1, //当前页
                jump: function (obj, first) { //触发分页后的回调
                    if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                        demo(obj.curr);

                    }
                }
            });
        },
        error: function (er) {
            console.log(er)
        }
    });
};

/*搜索赛事信息*/
function search_info() {
    if ($("#search_info").val() == "" || $("#search_info").val() == null) {
        layer.alert('请输入要查询的赛事信息!');
        return;
    }
    demo();
}


