/**
 * Created by 灵魂都在冒香气的神 on 2018/3/28.
 */

match = null;
var token;
idSchedule = null;
selectData = '<option teamId="{id}" {isSelect}>{name}</option>';
selectSch = null;
context = {
    error: false
};

function getSelect()
{
    ajax({
        async: false,
        url: getRootPath() + "/schedule/getSch/" + idSchedule,
        type: "POST",
        dataType: "json",
        success: function (res)
        {
            selectSch = res.data.schedule;   //this指针
        }
    });
}

function initTeam(match, sid) {
    ajax({
        url: getRootPath() + "/schedule/getTeam",
        data: {matchId: match, sid: sid},
        type: "POST",
        dataType: "json",
        error: function () {
            $("#teamA select").append('<option>未获取到队伍信息</option>');
            $("#teamB select").append('<option>未获取到队伍信息</option>');
            context.error = true;
        },
        success: function (teamData, status) {
            token = teamData.token;
            if (teamData.status == 1) {
                $("#teamA select").append('<option>未获取到队伍信息</option>');
                $("#teamB select").append('<option>未获取到队伍信息</option>');
                context.error = true;
                return;
            }
            var len = teamData.data.team.length;
            var allTeam = teamData.data.team;
            // $("#goalA").val(teamData.data.schedule.goalA);
            // $("#goalB").val(teamData.data.schedule.goalB);
            laydate.render({
                elem: '#time'
                , type: 'datetime'
                , value: new Date(teamData.data.schedule.time)
            });
            $("#place").val(teamData.data.schedule.place);
            $("#status").find("option").each(function () {
                if ($(this).attr("status") == teamData.data.schedule.status) {
                    $(this).attr("selected", "");
                }
            });
            for (var i = 0; i < len; i++) {
                if (allTeam[i].idTeam === teamData.data.schedule.teamA) {
                    $("#teamA select").append(selectData.replace("{name}", allTeam[i].name).replace("{id}", allTeam[i].idTeam).replace("{isSelect}", "selected"));
                } else {
                    $("#teamA select").append(selectData.replace("{name}", allTeam[i].name).replace("{id}", allTeam[i].idTeam).replace("{isSelect}", ""));
                }
                if (allTeam[i].idTeam === teamData.data.schedule.teamB) {
                    $("#teamB select").append(selectData.replace("{name}", allTeam[i].name).replace("{id}", allTeam[i].idTeam).replace("{isSelect}", "selected"));
                } else {
                    $("#teamB select").append(selectData.replace("{name}", allTeam[i].name).replace("{id}", allTeam[i].idTeam).replace("{isSelect}", ""));
                }
            }
        }
    });
}

$(document).ready(function () {
    token = $('#token').html().trim();
    match = $("#secret").attr("match");
    idSchedule = $("#secret").attr("idSchedule");
    // getSelect();
    initTeam(match, idSchedule);
    // if (context.error) {
    //     $("#submit").click(function () {
    //         layer.alert("获取队伍信息失败");          //记得还原
    //     });
    //     return;
    // }
    // $("#teamA select option").each(function ()
    // {
    //     var id = $(this).attr("teamId");
    //     if (selectSch.teamA == id)
    //     {
    //         this.attr("selected", true);
    //     }
    // });
    // $("#teamB select option").each(function ()
    // {
    //     var id = $(this).attr("teamId");
    //     if (selectSch.teamB == id)
    //     {
    //         this.attr("selected", true);
    //     }
    // });
    $("#submit").click(function () {
        var teamA = $("#teamA select").find("option:selected").attr("teamId");
        var teamB = $("#teamB select").find("option:selected").attr("teamId");
        var teamNameA = $("#teamA select").val();
        var teamNameA = $("#teamB select").val();
        if (teamA === teamB) {
            layer.alert("不能选择相同的队伍");
            return;
        }
        // var goalA = $("#goalA").val();
        // var goalB = $("#goalB").val();
        // if (goalA < 0 || goalB < 0 || goalA == "" || goalB == "") {
        //     layer.alert("请填写正确的比分");
        //     return;
        // }
        var tmp = $("#time").val();
        var time = Date.parse(tmp);
        if (time == "") {
            layer.alert("请选择开赛时间");
            return;
        }
        var place = $("#place").val();
        if (place == "") {
            layer.alert("请选择比赛地点");
            return;
        }
        var status = $("#status").find("option:selected").attr("status");
        // var json = {
        //     // "teamNameA":teamNameA,
        //     // "teamNameB":teamNameB,
        //     "matchId": match,    //fuck
        //     "teamA": teamA,
        //     "teamB": teamB,
        //     "idSchedule": idSchedule,    //fuck
        //     "goalA": goalA,
        //     "goalB": goalB,
        //     "time": time,
        //     "place": place,
        //     "status": status
        // };
        ajax({
            url: getRootPath() + "/schedule/modify",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            headers: {token: token},
            data: JSON.stringify({
                "matchId": match,
                "teamA": teamA,
                "teamB": teamB,
                "idSchedule": idSchedule,
                // "goalA": goalA,
                // "goalB": goalB,
                "time": time,
                "place": place,
                "status": status,
            }),
            error: function () {
                layer.alert("修改出错", function (index) {
                    closeLyaer();
                });
            },
            success: function (backData) {
                if (backData.status === 0) {
                    layer.alert("修改成功", function (index) {
                        closeLyaer();
                    });
                } else if (backData.message) {
                    layer.alert(backData.message, {icon: 2}, function (index) {
                        closeLyaer();
                    });
                }
            }
        })
    })
});
