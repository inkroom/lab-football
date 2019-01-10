/**
 * Created by Jerry on 17/6/3.
 */

var basePath = $('#base_path').val();
var error = $('#error').val();
var com_id = $('#com_id').val();
var stage = $('#stage').val();


/*后台校验错误信息提示*/
$(function () {
    layer.ready(function () {
        if (error != null && error != '') {
            layer.alert(error);
            console.log(error);
        }
    });
});


/*加载分页信息*/
demo();
function demo(curr) {
    $.ajax({
        url: '' + basePath + '/org/' + com_id + '/' + stage + '/search_schedule.action',
        type: "POST",
        data: {
            "pageNum": curr || 1,
            "parameter": $("#search_info").val()
        },
        dataType: "json",
        success: function (data) {

            if (JSON.stringify(data.list) === "[]") {
                $('tbody').append('<tr><td colspan="11">暂无数据</td></tr>');
            }
            vm.json = data.list;
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
}


/*搜索 赛事下 相关的赛程信息*/
function search_info() {
    if ($("#search_info").val() == "" || $("#search_info").val() == null) {
        layer.alert('请输入要查询的赛程地区!');
        return;
    }
    demo();
}


/*关闭模态框*/
function model_close() {
    window.location.reload();
}

/*处理日期*/
$(" .date_a").cxCalendar({
    type: 'datetime',
    format: 'YYYY-MM-DD HH:mm:ss'
});


/*接收数据*/
var flag1 = true;
var flag2 = true;
var vm = avalon.define({
    $id: "for_w",
    json: [],
    basePath: basePath + "/",
    /*查找单个赛程信息*/
    editSchedue: function (id) {
        $("#modal-demo").modal("show");
        $.ajax({
            type: "post",
            url: '' + basePath + '/org/edit_schedule.action',
            data: {"id": id, "com_id": com_id},
            dataType: 'json',
            success: function (data) {

                $("#data_hidden").append("<input type='hidden' name='R_ID' value='" + id + "'>")

                for (var i in data.team_name) {
                    /*主队*/
                    $("#school1").append("<option value='" + i + "' > " + data.team_name[i] + "</option>");
                }

                for (var i in data.team_name) {

                    /*客队*/
                    $("#school2").append("<option  value='" + i + "'> " + data.team_name[i] + "  </option>");

                }

                /*开始时间*/
                $("#R_START_TIME").val(data.R_START_TIME);
                /*结束时间*/
                $("#R_END_TIME").val(data.R_END_TIME);

                /*赛程地区*/
                $("#R_REGION").val(data.R_REGION);

                if (flag1) {
                    $("select.special-flexselect").flexselect({hideDropdownOnEmptyInput: true});
                    flag1 = false;
                }

                $("select.flexselect").flexselect();
                $("input:text:enabled:first").focus();


            },
            error: function () {
                layer.alert('操作异常', function (index) {
                    window.location.reload();
                });
            }
        });

    },

    /*删除单个赛程信息*/
    deleteSchedue: function (id) {
        layer.confirm('确实要删除该赛程吗?', {icon: 3, title: '提示'}, function (index) {

            $.post('' + basePath + '/org/delete_schedule.html', {"id": id}, function (data) {

                layer.alert('赛程信息删除成功', {icon: 1}, function (index) {

                    window.location.reload();

                });

            });
            layer.close(index);
        });
    },

    /*重置该赛程密码*/
    resetPwd: function (a_username) {

        $("#modal-demm").modal("show");
        /*给模态框赋值*/
        if (flag2) {

            $("#data_account").append("<span class='f-l'>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:&nbsp;</span><span class='scol-md-5' id='a_username' name='a_username' value='" + "'>" + a_username + "</span>");
            flag2 = false;
        }


    }

});


/* layer.confirm('你确实要重置该赛程密码吗?', {icon: 3, title: '提示'}, function (index) {

 $.post(''+basePath+'/org/edit_schedulePwd.html', {"id": id}, function (data) {

 layer.alert('赛程信息删除成功', {icon: 1}, function (index) {

 window.location.reload();

 });

 });
 layer.close(index);
 });*/


/*重置该赛程密码*/


//去掉首尾空格
function trimStr(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function valiPwd() {

    var s1 = $("#a_password").val();
    var s2 = $("#re_password").val();

    if (s1 == s2) {
        return true;
    }
    return false;
}

function edit_schedulePwd() {

    var value = trimStr($("#a_password").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '#a_password', {
            tips: [1, '#FF0033	']
        });

        $("#a_password").focus();
        return;
    }


    var value = trimStr($("#re_password").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '#re_password', {
            tips: [1, '#FF0033	']
        });

        $("#re_password").focus();
        return;
    } else if (!valiPwd()) {
        layer.tips("密码输入不一致，请重新输入", '#re_password', {
            tips: [1, '#FF0033']
        });
        $("#a_password").focus();
        return;
    }

    layer.confirm('你确实要重置该赛程密码吗?', {icon: 3, title: '提示'}, function (index) {
        $.post('' + basePath + '/org/edit_schedulePwd.action', {
            "a_username": $("#a_username").text(),
            "a_password": $("#a_password").val(),
            "_csrf": token
        }, function (data) {

            if (data.status == 200 && data.success) {
                layer.alert("密码更新成功", {icon: 1}, function (index) {
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
        })
    });
}


/**
 * 第一次选中，第二次取消选中...
 */
var selectAllFlag = false;
function selectAll() {
    var oSelectOnes = document.getElementsByTagName("input");

    for (var i = 0; i < oSelectOnes.length; i++) {
        if (oSelectOnes[i].type == "checkbox") {
            oSelectOnes[i].checked = !selectAllFlag;
        }
    }

    selectAllFlag = !selectAllFlag;
}

/*修改单个赛程信息*/
function updateSchedue(com_id) {

    //去掉首尾空格
    function trimStr(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }


    //主队
    var value = trimStr($("#school1_flexselect").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '#school1_flexselect', {
            tips: [1, '#FF0033	']
        });

        $("#school1_flexselect").focus();
        return;
    }

    //开始时间
    var value = trimStr($(".leftstart").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '.leftstart', {
            tips: [1, '#FF0033	']
        });

        $(".leftstart").focus();
        return;
    }

    //结束时间
    var value = trimStr($(".rightend").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '.rightend', {
            tips: [1, '#FF0033	']
        });

        $(".rightend").focus();
        return;
    }

    //赛程地区
    var value = trimStr($("#R_REGION").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '#R_REGION', {
            tips: [1, '#FF0033	']
        });

        $("#r_region").focus();
        return;
    }

    //客队
    var value = trimStr($("#school2_flexselect").val());

    if (value.length == 0) {
        layer.tips('用户输入不能为空', '#school2_flexselect', {
            tips: [1, '#FF0033	']
        });

        $("#school2_flexselect").focus();
        return;
    }


    $.post('' + basePath + '/org/update_schedule.action', {
        "schedule": JSON.stringify($("#select_form").serializeArray()),
        "com_id": com_id,
        "_csrf": token
    }, function (data) {
        if (data.status == 200 && data.success) {
            layer.alert('赛程信息更新成功', {icon: 1}, function (index) {
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
        } else if (data.status == "400" && !data.success) {
            layer.alert(data.msg, function () {
                window.location.reload();
            })
        }
        else{
            layer.alert("提交失败，请重试！", function () {
                window.location.reload();
            })
        }
    })
}


/*校验 插入队伍 信息*/
$(document).on("focus blur change keyup", "#school1_flexselect,#school2_flexselect,#school3_flexselect,#R_START_TIME,#R_END_TIME", function () {

    var s1 = $("#school1_flexselect").val();
    var s2 = $("#school2_flexselect").val();

    if (s1 == s2 && s1 != "" && s2 != "") {
        layer.alert('第一二名不能为同一个队伍!');
        $(this).val("");
        $(this).prev().get(0).selectedIndex = -1;
    }

    var s3 = $("#R_START_TIME").val();
    var s4 = $("#R_END_TIME").val();
    var start = new Date(s3.replace("-", "/").replace("-", "/"));
    var end = new Date(s4.replace("-", "/").replace("-", "/"));
    if (start != "Invalid Date" && end != "Invalid Date") {
        if (end <= start) {
            layer.alert("开始时间不能先于结束时间");
            return true;
        }
    }


});


