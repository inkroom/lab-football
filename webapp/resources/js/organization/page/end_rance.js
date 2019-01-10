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


/* 加载赛事信息  异步分页*/
demo();
function demo(curr) {
    $.ajax({
        url: '' + basePath + '/org/find_com_end.action',
        type: "POST",
        data: {
            "pageNum": curr || 1,
            "parameter": $("#search_info").val(),
        },
        dataType: "json",
        success: function (data) {
            if (JSON.stringify(data.list)=== "[]"){
                $('tbody').append('<tr><td colspan="6">暂无数据</td></tr>');
            }
            vm.json = data.list;//给 json 赋值
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
    if ($("#search_info").val() == "" && $("#search_info").val() == null) {
        layer.alert('请输入要查询的赛事名称!');
        return;
    }
    demo();
}

/*关闭模态框*/
// function model_close() {
//     $("#modal-demo").css("display","none");
// }


/*点击 结束赛事  ，返回参赛球队信息  */
var flag = true;
var vm = avalon.define({
    $id: "for_w",
    json: [],
    modaldemo: function (id, level) {
        $("#modal-demo").modal("show");
        com_id = id,
            $.ajax({
                type: "post",
                url: '' + basePath + '/org/race_end.action',
                data: {"id": id, "level": level},
                dataType: 'json',
                success: function (data) {
                    for (var i in data.team_name) {

                        $("#school1").append("<option value='" + i + "' > " + data.team_name[i] + "</option>");
                    }

                    for (var i in data.team_name) {

                        $("#school2").append("<option  value='" + i + "'> " + data.team_name[i] + "  </option>");

                    }
                    for (var i in data.team_name) {

                        $("#school3").append("<option  value='" + i + "'> " + data.team_name[i] + "  </option>");

                    }
                    if (flag) {
                        $("select.special-flexselect").flexselect({hideDropdownOnEmptyInput: true});
                        flag = false;
                    }


                    // $("select.flexselect").flexselect();
                    $("input:text:enabled:first").focus();

                }
            });
    }
});

/*结束赛事 ,1、校验球队插入数据是否为空  2、当没有球队时无法结束赛事 3、添加第一名，第二名，第三名  校验不能为相同的队伍*/
function end_racebtn() {
    if ($("#school1").val()) {
        //去掉首尾空格
        function trimStr(str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }


        var value = trimStr($("#school1_flexselect").val());

        if (value.length == 0) {
            layer.tips('用户输入不能为空', '#school1_flexselect', {
                tips: [1, '#FF0033	']
            });

            $("#school1_flexselect").focus();
            return;
        }


        var value = trimStr($("#school2_flexselect").val());

        if (value.length == 0) {
            layer.tips('用户输入不能为空', '#school2_flexselect', {
                tips: [1, '#FF0033	']
            });

            $("#school2_flexselect").focus();
            return;
        }


        var value = trimStr($("#school3_flexselect").val());

        if (value.length == 0) {

            layer.tips('用户输入不能为空', '#school3_flexselect', {
                tips: [1, '#FF0033	']
            });

            $("#school3_flexselect").focus();
            return;
        }


        layer.confirm('确实结束该赛事吗?', {icon: 3, title: '提示'}, function (index) {
            $.post('' + basePath + '/org/end_prize.action', {
                "bean": JSON.stringify($("#select_form").serializeArray()),
                "id": com_id,
                "_csrf": token
            }, function (data) {
                if (data.status == 200 && data.success) {
                    layer.alert('赛事结束成功', {icon: 1}, function (index) {

                        window.location.reload();
                    });
                } else if (data.status == 200 && !data.success) {
                    layer.alert(data.msg, function () {
                        window.location.reload();
                    })
                } else if (data.status == "500" && !data.success) {
                    layer.alert(data.msg, function () {
                        window.location.reload();
                    })
                } else {
                    layer.alert("提交失败，请重试！", function () {
                        window.location.reload();
                    })
                }

            });
            layer.close(index);
        });
    } else {
        layer.alert('没有球队加入,无法结束赛事', function (index) {
            window.location.reload();
        });
    }


    /*添加第一名，第二名，第三名－－－－校验－－－插入*/
    $(document).on("focus blur change keyup", "#school1_flexselect,#school2_flexselect,#school3_flexselect", function () {

        var s1 = $("#school1_flexselect").val();
        var s2 = $("#school2_flexselect").val();
        var s3 = $("#school3_flexselect").val();

        if (s1 == s2 && s1 != "" && s2 != "") {
            layer.alert('第一二名不能为同一个队伍!');
            $(this).val("");
            $(this).prev().get(0).selectedIndex = -1;
        }

        if (s1 == s3 && s1 != "" && s3 != "") {
            layer.alert('第一三名不能为同一个队伍!');
            $(this).val("");
            $(this).prev().get(0).selectedIndex = -1;
        }
        if (s2 == s3 && s2 != "" && s3 != "") {
            layer.alert('第二三名不能为同一个队伍!');
            $(this).val("");
            $(this).prev().get(0).selectedIndex = -1;
        }
    });


}
