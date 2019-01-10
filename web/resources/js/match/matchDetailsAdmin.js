var match;
var token;
context = {
    num: 10,
    btn_num: 0,
    btn_max: 5,
    totalSize: 0,
    cur_page: 0,
    cur_index: 1,
    page_num: 0,
    isError: false,
    isCreateBtn: false,
    //isCreatePage: true
};
trData = "<tr idSchedule='{idSchedule}'><td>{time}</td><td>{place}</td><td><a href='javascript:showdiv();' id='strHref' class='hpn-1'>{teamA}</a></td><td><b>{scoreA}&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;{scoreB}</b></td><td><a href='#'>{teamB}</a></td><td>{status}</td><td><a onclick='modifySch(this)'><button class='btn btn-success btn-sm gwz'>修改</button></a><a onclick=\"referee(this)\"><button class=\"btn btn-warning btn-sm gwz\">裁判</button></a><a onclick='delet(this)'><button class='btn btn-danger btn-sm gwz'>删除</button></a></td></tr>";
liData = "<li dom_type='page_btn' onclick='pageClick(this)' index='{index}'><a href='#'>{pageNum}</a></li>";

$(document).ready(function () {
    match = document.getElementById("mid").value;
    token = $("#token").val().trim();
    matchDetails();
    schedule();
    $("#upBtn").attr("onclick", "modifyMatch()");
    $("#addBtn").attr("onclick", "addSch()");
    $("#schedule-list").click(function () {
        $("#pageBtn").empty();
        schedule();
    });
    $("#profile-tab").click(function () {
        examine();
    });

    var url = document.location.toString();
    if (url.match('#')) {
        var link = url.split('#')[1];
        $('.nav-tabs a[href="#' + link + '"]').tab('show');
        switch (link) {
            case "home":
                $("#pageBtn").empty();
                schedule();
            case "profile":
                examine();
        }
    }
    // Change hash for page-reload
    $('.nav-tabs a').on('shown.bs.tab', function (e) {
        window.location.hash = e.target.hash;
    })
});

function addSch() {
    layer.open({
        type: 2,
        title: '创建赛程',
        shadeClose: true,
        area: ['893px', '600px'],
        content: "../turnAddSch?id=" + match,
        end: function () {
            $("#pageBtn").empty();
            schedule();
        }
    });

}

function modifyMatch() {
    layer.open({
        type: 2,
        title: $("#matchName").text() + '-修改赛事',
        shadeClose: true,
        area: ['893px', '600px'],
        content: "../turnUpdate?id=" + match,
        end: function f() {
            matchDetails();
        }
    });
}

function getMatchLevel(level) {
    switch (level) {
        case 0:
            return "校级";
        case 1:
            return "县级";
        case 2:
            return "市级";
        case 3:
            return "省级";
        case 4:
            return "国级";
        default:
            return "Error Level Code" + level;
    }
}


function getMatchStatus(status) {
    switch (status) {
        case 0:
            return "报名阶段";
        case 1:
            return "进行中";
        case 2:
            return "已结束";
        case 3:
            return "删除状态"
    }
}


function matchDetails() {
    ajax({
        url: getRootPath() + "/match/MatchInfo/" + match,
        success: function (result) {
            result;
            $("#matchName").text(result.data.matchInfo.name);
            $("#orgName").text(result.data.matchInfo.orgStaff.name);
            $("#titleName").text(result.data.matchInfo.name + "赛事");
            $("#matchLevel").text(getMatchLevel(result.data.matchInfo.level));
            $("#matchStatus").text(getMatchStatus(result.data.matchInfo.status));
            $("#matchNumPlayer").text(result.data.matchInfo.numPlayer + "人");
            $("#matchStartTime").text(parseTime(result.data.matchInfo.startDate));
            $("#matchDeadLine").text(parseTime(result.data.matchInfo.applyDeadline));
            }
        }
    );
}


function pageClick(cur_this) {
    $(cur_this).attr("class", "active");
    var selector = "[index=" + (context.cur_index) + "]";
    $(selector).removeAttr("class");
    showSchedule(match, String($(cur_this).text()));
    context.cur_index = parseInt($(cur_this).attr("index"));
}


function showSchedule(idMatch, page) {
    postData = "idMatch=" + idMatch;
    var myBody = $("#body");
    ajax({
        async: false,
        url: getRootPath() + "/schedule/showSchedule/" + page,
        type: "POST",
        data: postData,
        dataType: "json",
        error: function () {
            context.isError = true;
            $("#schedule-data tbody").empty();
            errorMessge = '<tr><td></td><td></td><td>获取信息失败!</td><td></td><td></td><td></td></tr>';
            $("#schedule-data tbody").append(errorMessge);
            $("#schedule-data").css("visibility", "visible");
        },
        success: function (data, status) {
            token = data.token;
            if (data.status === 1) {
                myBody.empty();
                myBody.append("<td colspan=\"6\">暂无赛程</td>")
                return false;
            }
            context.isError = false;
            context.totalSize = data.data.size;
            context.cur_page = page;
            var realSize = data.data.schedule.length;
            var num = realSize <= context.num ? realSize : context.num;
            context.isCreateBtn = (data.data.size > context.num);
            $("#schedule-data tbody").empty();
            for (var i = 0; i < num; i++) {
                var time = new Date(Number(data.data.schedule[i].time));
                var beginTime = String(time.getFullYear() + "/" + time.getMonth() + "/" + time.getDate());
                var state = null;
                switch (data.data.schedule[i].status)
                {
                    case 0:
                        state = "正在准备";
                        break;
                    case 1:
                        state = "正在比赛";
                        break;
                    case 2:
                        state = "比赛结束";
                        break;
                    case 3:
                        state = "已删除";
                        break;
                }
                var tr = trData.replace("{time}", beginTime).replace("{place}", data.data.schedule[i].place)
                    .replace("{teamA}", data.data.schedule[i].teamNameA).replace("{scoreA}", String(data.data.schedule[i].goalA))
                    .replace("{scoreB}", String(data.data.schedule[i].goalB)).replace("{teamB}", data.data.schedule[i].teamNameB)
                    .replace("{status}", state)
                    .replace("{idSchedule}", data.data.schedule[i].idSchedule);

                $("#schedule-data tbody").append(tr);
                $(tr).attr("idSchedule", data.data.schedule[i].idSchedule);
            }
            $("#schedule-data").css("visibility", "visible");
        }
    });
}

function modifySch(cur_this) {
    var idSchedule = $(cur_this).parentsUntil("#body").filter("tr").attr("idSchedule");
    var data = "idSchedule=" + idSchedule + "&match=" + match;
    layer.open({
        type: 2,
        title: ' 修改赛程',
        shadeClose: true,
        area: ['893px', '600px'],
        content: getRootPath() + "/schedule/modifyPage?" + data,
        end: function () {
            $("#pageBtn").empty();
            schedule();
        }
    });
}

function referee(cur_this) {
    var idSchedule = $(cur_this).parentsUntil("#body").filter("tr").attr("idSchedule");
    layer.open({
        type: 2,
        title: '裁判',
        shadeClose: true,
        area: ['893px', '400px'],
        content: getRootPath() + "/schedule/turnReferee?idSchedule=" + idSchedule
    });
}

function delet(cur_this) {
    var idSchedule = $(cur_this).parentsUntil("#body").filter("tr").attr("idSchedule");
    layer.confirm('确认删除吗？', {
        btn: ['确认', '取消'], //按钮
        title: "删除赛程"
    }, function () {
        ajax({
            url: getRootPath() + "/schedule/delete",
            type: "POST",
            data: {"idSchedule": idSchedule, "token": token},
            success: function (result) {
                if (result.status == 0) {
                    layer.msg('删除成功', {icon: 1});
                    schedule();
                }
            },
            error: function () {
                layer.msg('删除失败', {icon: 2});
            }
        });
    });
}

function schedule() {


    // $("#schedule-list").click(function ()
    // {
        //if (context.isCreatePage)
        {
            showSchedule(match, 1);
            if (context.isError) {
                return;
            }



            if (context.isCreateBtn) {
                context.page_num = parseInt(context.totalSize / context.num);
                if (context.page_num < context.totalSize / context.num) {
                    context.page_num++;
                }
                var realBtn_num = (context.page_num < context.btn_max) ? context.page_num : context.btn_max;
                for (var i = 1, tmp = ""; i <= realBtn_num; i++) {
                    tmp += liData.replace("{pageNum}", String(i)).replace("{index}", String(i));
                }
                context.btn_num = realBtn_num;
                $("#previous").after(tmp);
                $("#pageBtn").css("display", "block");
                $("[dom_type=page_btn]:eq(0)").attr("class", "active");
            }
            //context.isCreatePage = false;
        }
    // }
    // );

    $("#next").click(function ()
    {
        $(this).children("a").css("background-color", "white");
        if (context.cur_page >= context.page_num)
        {
            return;
        }
        else if (context.cur_index === context.btn_num)
        {
            $("[dom_type=page_btn]").each(function () {
                var number = parseInt($(this).text()) + context.btn_num;
                if (number > context.page_num) {
                    $(this).css("display", "none");
                }
                else {
                    $(this).children("a").text(String(number));
                }
            });
            $("[index=1]").click();
        }
        else {
            var selector = "[index=" + (context.cur_index + 1) + "]";
            $(selector).click();
        }

    });

    $("#previous").click(function ()
    {
        $(this).children("a").css("background-color", "white");
        if (context.cur_page <= 1)
        {
            return;
        }
        if (context.cur_index === 1)
        {
            var number = parseInt($("[index=1]").text()) - context.btn_num;
            $("[dom_type=page_btn]").each(function () {
                $(this).children("a").text(String(number));
                $(this).css("display", "inline");
                number++;
            });
            var selector = "[index=" + context.btn_max + "]";
            $(selector).click();
        }
        else {
            var selector = "[index=" + (context.cur_index - 1) + "]";
            $(selector).click();
        }
    });
};


function examine() {
    orgExamine();
}

function input(oid, status) {
    ajax({
        url: getRootPath() + "/match/setExamine",
        data: {id: oid, status: status, mid: match, token: token},
        type: "POST",
        success: function (result) {
            if (result.status == 0) {
                $("#teamBody").empty();
                $("#pageInfo").empty();
                orgExamine();

            }
        }
    })
}

var style = getRootPath() + "/resources/css/match/style.1.0.css";

function clear() {
    $("#ul1").empty();
    $("#ul2").empty();
    $("#ul3").empty();
}

var aaa;
var oName;
function orgExamine() {
    clear();
    turn();
    ajax({
        url: getRootPath() + "/match/listExamine",
        data: {mid: match},
        type: "POST",
        success: function (result) {
            token = result.token;
            $.each(result.data.examine, function (index, item) {
                var oid = item.orgId;
                var li = $("<li></li>").append(item.orgName);
                var a = $("<a></a>").append(li).attr("type", "button");
                oName = item.orgName;
                if (item.status == 0) {
                    a.click(function () {
                        aaa = 0;
                        listTeam(1, oid);
                });
                    a.appendTo("#ul1");
                }
                else if (item.status == 1) {
                    a.click(function () {
                        aaa = 1;
                        listTeam(1, oid);
                    });
                    a.appendTo("#ul2");
                }
                else {
                    a.click(function () {
                        aaa = 1;
                        listTeam(1, oid);
                    });
                    a.appendTo("#ul3")
                }
                ;
            });
        }

    });
}

function turn() {
    for (var i = 0; i < 8; i++) {
        var tr = $("<tr></tr>");
        for (var j = 0; j < 4; j++) {
            var td = $("<td></td>").attr("style", "height:50px");
            tr.append(td);
        }
        $("#teamBody").append(tr);
    }
    $("#btn").css("display", "none");
    $("#oName").empty();

}

function listTeam(pn, oid) {
    $("#oName").empty();
    $("#teamBody").empty();
    $("#blank").css("display", "none");
    $("#oName").append(oName);
    if (aaa == 0) {
        $("#btn").css("display", "block");
        $("#passbtn").click(function () {
            layer.confirm("确认通过？", {btn: ['确认', '取消']}, function (index) {
                layer.close(index);
                input(oid, 1);
            }, function (index) {
                layer.close(index);
            });
        });
        $("#dangerbtn").click(function () {
            layer.confirm("确认拒绝？", {btn: ['确认', '取消']}, function (index) {
                layer.close(index);
                input(oid, 2);
            }, function (index) {
                layer.close(index);
            });

        });
    }
    else {
        $("#btn").css("display", "none");
    }
    ajax({
        url: getRootPath() + "/match/listExamineTeam",
        type: "POST",
        data: {mid: match, pn: pn, oid: oid},
        success: function (result) {
            token = result.token;
            if (result.status == 0) {
                $.each(result.data.pageInfo.list, function (index, item) {
                    var tid = item.teamId;
                    var tnameTd = $("<td></td>").append(item.teamName);
                    var cnameTd = $("<td></td>").append(item.coatchName);
                    var pCountTd = $("<td></td>").append(item.playerCount);
                    var list = [tid, item.teamName, item.coatchName, item.playerCount];
                    var a = $("<a></a>").append("查看详情").attr("type", "button").attr("class", "btn btn-primary btn-sm");
                    a.click(function () {
                        $("#Modal").modal({
                            backdrop: "static"
                        });
                        listPlayer(1, list);
                    });
                    var btnTd = $("<td></td>").append(a);
                    $("<tr></tr>")
                        .append(tnameTd)
                        .append(cnameTd)
                        .append(pCountTd)
                        .append(btnTd)
                        .appendTo("#teamBody");
                });
                build_page_nav(result, oid);
            }
            else {
                $("<tr></tr>").append("没有数据").appendTo("#teamBody");
            }
        }
    })
}

function listPlayer(pn, list) {
    $("#tNameh").empty();
    $("#coatch").empty();
    $("#count").empty();
    $("#playerBody").empty();
    var tid = list[0];
    var tname = list[1];
    var cname = list[2];
    var count = list[3];
    $("#tNameh").append(tname);
    $("#coatch").append(cname);
    $("#count").append(count);
    ajax({
        url: getRootPath() + "/match/listExaminePlayer",
        type: "POST",
        data: {pn: pn, tid: tid, mid: match},
        success: function (result) {
            token = result.token;
            if (result.status == 0) {
                $.each(result.data.pageInfo.list, function (index, item) {
                    var nameTd = $("<td></td>").append(item.name);
                    var sexTd = $("<td></td>").append(item.sex == "0" ? "男" : "女");
                    var gradeTd = $("<td></td>").append(item.grade);
                    var classTd = $("<td></td>").append(item.classes);
                    $("<tr></tr>")
                        .append(nameTd)
                        .append(sexTd)
                        .append(gradeTd)
                        .append(classTd)
                        .appendTo("#playerBody")
                })
                build_page_nav_p(result, list);
            }
            else {
                $("<tr></tr>").append("无数据").appendTo("#playerBody");
            }
        }
    })
}

// function orgExamine() {
//     $("#examine").empty();
//     ajax({
//         url: getRootPath() + "/match/listExamine",
//         data: {mid: match},
//         type: "POST",
//         success: function (result) {
//             token = result.data.token;
//             $.each(result.data.examine, function (index, item) {
//                 var oid = item.orgId;
//                 var div1 = $("<div></div>").attr("class", "mouse");
//                 var div2 = $("<div></div>").attr("class", "t-h-n");
//                 var a = $("<a></a>").attr("data-toggle", "collapse").append(item.orgName).attr("class", "ona").attr("oid", oid);
//                 div2.append(a);
//                 var div3 = $("<div></div>").attr("class", "collapse t-h-bo");
//                 var ul = $("<ul></ul>");
//                 $.each(item.teamName, function (item) {
//                     var li = $("<li></li>").append(item);
//                     ul.append(li);
//                 });
//                 div3.append(ul);
//                 div1.append(div2).append(div3).appendTo("#examine");
//                 if (index == 0) {
//                     listPlayer(1, oid);
//                 }
//             });
//         }
//
//     })
// }
//
// $(document).on("click", ".ona", function () {
//     listPlayer(1, $(this).attr("oid"));
// });
// $(document).on("click", ".pass", function () {
//     input($(this).attr("oid"), 1);
// });
// $(document).on("click", ".danger", function () {
//     input($(this).attr("oid"), 2);
// });

// function listPlayer(pn, oid) {
//     $("#playerbody").empty();
//     $("#button").empty();
//     ajax({
//         url: getRootPath() + "/match/listExaminePlayer",
//         data: {pn: pn, oid: oid},
//         type: "POST",
//         success: function (result) {
//             token = result.data.token;
//             $.each(result.data.playerPage.list, function (index, item) {
//                 var playerTd = $("<td></td>").append(item.playerName);
//                 var teamTd = $("<td></td>").append(item.teamName);
//                 $("<tr></tr>")
//                     .append(playerTd)
//                     .append(teamTd)
//                     .appendTo("#playerbody")
//             });
//             var passA = $("<button></button>")
//                 .attr("type", "button")
//                 .attr("style", "margin-left: 20%;display: block;float: left;margin-top: -18px")
//                 .attr("class", "btn btn-success btn-sm pass")
//                 .attr("oid", oid)
//                 .append("通过");
//             var dangerA = $("<button></button>")
//                 .attr("type", "button")
//                 .attr("style", "margin-left: 40%;display: block;float: left;margin-top: -18px")
//                 .attr("class", "btn btn-danger btn-sm danger")
//                 .attr("oid", oid)
//                 .append("拒绝");
//             passA.appendTo("#button");
//             dangerA.appendTo("#button");
//             build_page_nav(result, oid)
//         }
//
//     })
// }

// function toPage(pn) {
//     $.ajax({
//         url: "/match/listExamine",
//         data: {pn: pn, mid: match},
//         dataType: "json",
//         type: "POST",
//         success: function (result) {
//             if (result.status == 0) {
//                 buildTable(result);
//                 build_page_nav(result);
//             }
//             else if (result.status == 1) {
//                 $("#body").empty();
//                 $("<tr></tr>").append("无数据").appendTo("#body");
//
//             }
//         }
//     });
// }

// function buildTable(result) {
//     nowpage = result.data.pageInfo.pageNum;
//     $("#body").empty();
//     $.each(result.data.pageInfo.list, function (index, item) {
//         var orgid = item.orgId;
//         var nameTd = $("<td></td>").append(item.orgName);
//         var teamTd = $("<td></td>");
//         $.each(item.teamName, function (index, item) {
//             var li = $("<li></li>").append(item);
//             teamTd.append(li).append(" ");
//         });
//         var passBtn = $("<button></button>").addClass("btn btn-success pass").append("通过");
//         passBtn.attr("id", orgid);
//         var refuseBtn = $("<button></button>").addClass("btn btn-danger danger").append("拒绝");
//         refuseBtn.attr("id", orgid);
//         var btn = $("<td></td>").append(passBtn).append(" ").append(refuseBtn);
//         $("<tr></tr>")
//             .append(nameTd)
//             .append(teamTd)
//             .append(btn)
//             .appendTo("#body");
//
//     })
// }

// function build_page_info(result){
//     $("#page_info").empty();
//     $("#page_info").append("当前"+result.data.pageInfo.pageNum+"页，" +
//         "总"+result.data.pageInfo.pages+"页，总"+
//         result.data.pageInfo.total+"条记录")
//     thispage=result.datapageInfo.pageNum;
// }
function build_page_nav_p(result, id) {
    $("#pageInfo2").empty();
    if (result.data.pageInfo.pages == 1) {
        return false;
    }
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.data.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            listPlayer(1, id);
        });
        prePageLi.click(function () {
            listPlayer(result.data.pageInfo.pageNum - 1, id);
        });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
    if (result.data.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled")
    }
    else {
        nextPageLi.click(function () {
            listPlayer(result.data.pageInfo.pageNum + 1, id);
        });
        lastPageLi.click(function () {
            listPlayer(result.data.pageInfo.pages, id);
        });
    }

    ul.append(firstPageLi).append(prePageLi);
    $.each(result.data.pageInfo.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.data.pageInfo.pageNum == item) {
            numLi.addClass("active")
        }
        numLi.click(function () {
            listPlayer(item, id)
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#pageInfo2");
}


function build_page_nav(result, id) {
    $("#pageInfo").empty();
    if (result.data.pageInfo.pages == 1) {
        return false;
    }
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.data.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            listTeam(1, id);
        });
        prePageLi.click(function () {
            listTeam(result.data.pageInfo.pageNum - 1, id);
        });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
    if (result.data.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled")
    }
    else {
        nextPageLi.click(function () {
            listTeam(result.data.pageInfo.pageNum + 1, id);
        });
        lastPageLi.click(function () {
            listTeam(result.data.pageInfo.pages, id);
        });
    }

    ul.append(firstPageLi).append(prePageLi);
    $.each(result.data.pageInfo.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.data.pageInfo.pageNum == item) {
            numLi.addClass("active")
        }
        numLi.click(function () {
            listTeam(item, id)
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#pageInfo");
}