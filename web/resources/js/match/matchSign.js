/**
 * Created by 灵魂都在冒香气的神 on 2018/4/10.
 */


/**
 * title是队员名字，teamName为队伍名字，value是队员ID:teamName
 * @type {string}
 */
teamTab = '<div onclick="selectTeam(this)" onmouseover="move(this)" onmouseout="out(this)" style="cursor: pointer;"><div class="t-h-n" style="background-color: #f2f2f2;color: #545454;"><span class="t_name">{teamName}</span></div></div>';
player = '<a title="{name}" teamName="{team}" value="{idAndTeam}" onclick="selectOne(this)" href="#"><span>{name}</span><em></em></a>';
teamMap = {};    //格式化后的数据，队名为key
tmPalyer = {};    //待选人员，已选人员缓存   {teamName:{playerId:xxx}},最后提交的时候记得判断队伍为空
cur_team = null;   //当前选中队  <div class="t-h-n">{teamName}</div>

btn_state = {};



function move(cur_this)
{
    $(cur_this).find(".t-h-n").css("background-color", "#1bad83");
    $(cur_this).find(".badge").css("background-color", "#1bad83");
    $(cur_this).find(".t-h-n").css("color", "#fff");
}

function out(cur_this)
{
    var a = $(cur_this).find(".t-h-n").find(".t_name").text();
    var b = $(cur_team).find(".t-h-n").find(".t_name").text();
    if (a != b)
    {
        $(cur_this).find(".t-h-n").css("background-color", "#f2f2f2");
        $(cur_this).find(".badge").css("background-color", "#ccc");
        $(cur_this).find(".t-h-n").css("color", "#545454");
    }
}

/**
 * 全选/取消全选按钮事件
 */

function slectAll()
{
    var name = $(cur_team).find(".t-h-n").find(".t_name").text();
    if (btn_state[name] == 1)   //可以全选
    {
        $("#real_wait a").click();   //全选
        $("#select_all_flag").attr("class", "glyphicon glyphicon-chevron-down");
        // $(this).attr("s", "0");

        // //调用了上面的函数之后,生成了dom。此时绑定事件
        // $("#real_player em").click(cancel);
        btn_state[name] = 0;
    }
    else
    {
        $("#real_player em").click();   //全部取消
        $("#select_all_flag").attr("class", "glyphicon glyphicon-chevron-up");
        // $(this).attr("s", "1");
        btn_state[name] = 1;
    }
}

/**
 * 单个选中事件
 */
function selectOne(cur_this)
{
    var id = $(cur_this).attr("value").split(":")[0];
    var teamName = $(cur_this).attr("teamName");    //队名唯一
    if (tmPalyer[teamName][id])   //若此队队伍中此队员已经被选中
    {
        return;
    }
    var list = teamMap[teamName];  //找到目标所在队伍
    var one = null;
    for (var i = 0, len = list.length; i < len; i++)
    {
        if (list[i].playerId == id)    //找到目标运动员信息
        {
            one = {
                "matchId": $("#mid").html().trim(),
                "orgId": list[i].orgId,
                "teamId": list[i].teamId,
                "playerId": id
            };
            break;
        }
    }
    tmPalyer[teamName][id] = one;
    updatabadge(cur_team);
}

/**
 * 单个取消事件
 */
function cancel()
{
    var value = $("#real_player em").parent().attr("value").split(":");
    delete tmPalyer[value[1]][value[0]];
    updatabadge(cur_team);
}


function handleSuccess(playerList, teamName)
{
    for (var j = 0; j < playerList.length; j++)
    {
        var id = playerList[j].playerId;
        var name = playerList[j].playerName;
        var value = id + ":" + teamName;
        var a = $("<a></a>").attr("value", value)
            .attr("title", name)
            .attr("href", "javascript:void(0);");
        var span = $('<span></span>').html(name);
        a.append(span);
        $("#real_player").append(a);
    }
}


function updatabadge(team)
{
    var exist = $(team).find("span").length != 2;
    var name = $(team).find(".t-h-n").find(".t_name").text();
    var map = Object.keys(tmPalyer[name]);
    if (exist)
    {
        //创建dom
        var span = $("<span></span>").attr("class", "badge")
            .attr("style", "margin-left:50px;display:inline");
        $(span).html(map.length);
        $(team).find(".t-h-n").append(span);
    }
    else
    {
        // var size = Number($(team).find(".badge").html()) + 1;
        var size = map.length;
        $(team).find(".badge").html(size);
    }
}

/**
 * 选择队伍列表
 */
function selectTeam(cur_this)
{
    $(cur_this).find(".t-h-n").css("background-color", "#1bad83");
    $(cur_this).find(".badge").css("background-color", "#1bad83");
    $(cur_this).find(".t-h-n").css("color", "#fff");
    if (cur_team != null)
    {
        $(cur_team).find(".t-h-n").css("background-color", "#f2f2f2");
        $(cur_team).find(".badge").css("background-color", "#ccc");
        $(cur_team).find(".t-h-n").css("color", "#545454");
    }
    cur_team = cur_this;

    var teamName = $(cur_this).find(".t-h-n").find(".t_name").text();
    if (btn_state[teamName] == 1)
    {
        $("#select_all_flag").attr("class", "glyphicon glyphicon-chevron-up");
    }
    else
    {
        $("#select_all_flag").attr("class", "glyphicon glyphicon-chevron-down");
    }
    var playerList = teamMap[teamName];
    $("#real_player").empty();
    $("#real_wait").empty();   //必须清空

    if (state == 1)   //上次报名成功
    {
        handleSuccess(playerList, teamName);
        return;
    }
    for (var j = 0, len = playerList.length; j < len; j++)
    {
        // if (playerList[j].status > 0)   //若次队员不可以报名
        // {
        //     continue;
        // }
        var id = playerList[j].playerId;
        var name = (playerList[j].playerName);
        var value = id + ":" + teamName;
        var tmp = player.replace("{name}", name)
            .replace("{name}", name)
            .replace("{id}", id)
            .replace("{team}", teamName)
            .replace("{idAndTeam}", value);
        $("#real_wait").append(tmp);
        if (tmPalyer[teamName][id] || playerList[j].status > 0)
        {
            var value = "[value=" + id + "\\:" + teamName + "]";
            $("#real_wait").find(value).click();
        }
    }
}

/**
 * 解析数据，队名为key
 * @param data
 */
function parseData(data)
{
    var tmp = JSON.parse(data);
    for (var i = 0, len = tmp.length; i < len; i++)
    {
        teamMap[tmp[i].teamName] = tmp[i].playerList;
        tmPalyer[tmp[i].teamName] = {};
        btn_state[tmp[i].teamName] = 1;   //初始全部即将全选
    }
    for (var name in teamMap)
    {
        var list = teamMap[name];
        for (var j = 0; j < list.length; j++)
        {
            if (list[j].status > 0)
            {
                var id = list[j].playerId;
                one = {
                    "matchId": $("#mid").html().trim(),
                    "orgId": list[j].orgId,
                    "teamId": list[j].teamId,
                    "playerId": id
                };
                tmPalyer[name][id] = one;
            }
        }
    }
}

/**
 * 初始化队名选项列表
 */
function initTeamTab()
{
    for (var name in teamMap)
    {
        var div = teamTab.replace("{teamName}", name);
        $("#teamNameList").append(div);
    }
    var teams = $("#teamNameList").children().each(function ()
    {
        updatabadge(this);
    })
}

function tip()
{
    layer.alert('上次报名未审核通过，您现在可以重新报名', {
        title: '提示',
        skin: 'layui-layer-molv' //样式类名
        , closeBtn: 0
    });
}

/**
 * 绑定some事件
 */
function initEvent()
{
    $("#select_all").click(slectAll);
    $(document).on("click", "#real_player em", cancel);   //必须绑定动态dom事件,1.9以上用on
}

function getPlayerName(teamName, id)
{
    var tmp = teamMap[teamName];
    for (var j = 0; j < tmp.length; j++)
    {
        if (tmp[j].playerId == id)
        {
            return tmp[j].playerName;
        }
    }
}

/**
 * 显示提交数据
 */

function QAQ()
{
    var div = $('<div style="margin: 12px 12px 12px 25px;"></div>').attr("class", "plus-tag tagbtn clearfix");
    //判断选择
    var flag = true;
    for (var team in tmPalyer)
    {
        var tmp = tmPalyer[team];
        for (var player in tmp)
        {
            flag = false;
            break;
        }
        if (!flag)
        {
            break;
        }
    }
    if (flag)
    {
        layer.alert("请选择队员", {
            offset: ["140px", ""],
            title: '提示'
        });
        return;
    }
    //判断选择


    for (var teamName in tmPalyer)
    {
        var team = tmPalyer[teamName];
        for (var playerId in team)
        {
            var value = playerId + ":" + teamName;
            var playerName = getPlayerName(teamName, playerId);
            var a = $("<a></a>").attr("value", value)
                .attr("title", playerName)
                .attr("href", "javascript:void(0);");
            var span = $('<span></span>').html(playerName);
            a.append(span);
            div.append(a);
        }
    }

    var btn = $('<div></div>').append($('<button onclick="submit()" class="btn btn-success btn_mormal">确定</button>'));
    btn.css("margin", "50%");
    btn.appendTo(div);
    var html = div.prop("outerHTML");
    layer.open({
        offset: ["140px", ""],
        title: '人员',
        type: 1,
        // skin: 'layui-layer-rim', //加上边框
        area: ['600px', '400px'], //宽高
        content: html
    });
}
function submit()
{
    var tmp = {players: []};
    for (var team in tmPalyer)
    {
        var a = tmPalyer[team];
        for (var player in a)
        {
            tmp.players.push(a[player]);
        }
    }
    if (tmp.players.length === 0)
    {
        layer.alert("请选择队员");
    }
    var json = JSON.stringify(tmp);
    ajax({
        async: false,
        url: getRootPath() + "/match/addPlayer",
        type: "POST",
        data: json,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        headers: {token: token},
        error: function ()
        {
            layer.alert('报名失败', {
                offset: ["240px", ""],
                closeBtn: 0,
                btn: ['确定']
                , yes: function (index, layero)
                {
                    window.location.href = getRootPath() + "/match/turnMatchList";
                }
            });
            // alert(222);
        },
        success: function (data)
        {
            layer.alert('报名成功', {
                offset: ["240px", ""],
                closeBtn: 0,
                btn: ['确定']
                , yes: function (index, layero)
                {
                    window.location.href = getRootPath() + "/match/turnMatchList";
                }
            });
        }
    });
}


$(function ()
{
    initEvent();
    parseData(teamList);
    initTeamTab();


    if (state == 1)
    {
        $("#submit").attr("disabled", "disabled");
    }
    else if (state == 2)
    {
        tip();
    }
    $("#submit").click(QAQ);
    // $("#teamNameList").children().eq(0).click();    //选中一个队伍
});