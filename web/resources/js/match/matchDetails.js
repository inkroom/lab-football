var match;

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
trData = "<tr><td>{time}</td><td>{place}</td><td><a href='javascript:showdiv();' id='strHref' class='hpn-1'>{teamA}</a></td><td><b>{scoreA}&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;{scoreB}</b></td><td><a href='#'>{teamB}</a></td><td>{status}</td></tr>";
liData = "<li dom_type='page_btn' onclick='pageClick(this)' index='{index}'><a href='#'>{pageNum}</a></li>";

$(document).ready(function () {
    match = document.getElementById("mid").value;
    matchDetails();
    schedule();
    $("#schedule-list").click(function () {
        schedule();
    });
    var url = document.location.toString();
    if (url.match('#')) {
        var link = url.split('#')[1];
        $('.nav-tabs a[href="#' + link + '"]').tab('show');
    }
    // Change hash for page-reload
    $('.nav-tabs a').on('shown.bs.tab', function (e) {
        window.location.hash = e.target.hash;
    })
});


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
var token;

function matchDetails() {
    ajax({
            url: getRootPath() + "/match/MatchInfo/" + match,
            success: function (matchInfo) {
                matchInfo;
                $("#matchName").text(matchInfo.data.matchInfo.name);
                $("#orgName").text(matchInfo.data.matchInfo.orgStaff.name);
                $("#titleName").append(matchInfo.data.matchInfo.name);
                $("#matchLevel").text(getMatchLevel(matchInfo.data.matchInfo.level));
                $("#matchStatus").text(getMatchStatus(matchInfo.data.matchInfo.status));
                $("#matchNumPlayer").text(matchInfo.data.matchInfo.numPlayer + "人");
                $("#matchStartTime").text(parseTime(matchInfo.data.matchInfo.startDate));
                $("#matchDeadLine").text(parseTime(matchInfo.data.matchInfo.applyDeadline));
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
            if (data.status == 1) {
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
                switch (data.data.schedule[i].status) {
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
                    .replace("{status}", state);
                $("#schedule-data tbody").append(tr);
            }
            $("#schedule-data").css("visibility", "visible");
        }
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


        $("#next").click(function () {
            $(this).children("a").css("background-color", "white");
            if (context.cur_page >= context.page_num) {
                return;
            }
            else if (context.cur_index === context.btn_num) {
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

        $("#previous").click(function () {
            $(this).children("a").css("background-color", "white");
            if (context.cur_page <= 1) {
                return;
            }
            if (context.cur_index === 1) {
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
    }
}
