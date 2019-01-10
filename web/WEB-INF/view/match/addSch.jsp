<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<script src="${path}/resources/lib/layer/laydate/laydate.js"></script>
<body>
<input hidden id="token" value="${token}">
<jsp:include page="../common/head.jsp"/>
<input hidden id="mid" value="${mid}">
<div class="container mo-h">
    新增赛程
</div>
<div class="container">
    <form id="createSchForm" class="form-horizontal">
        <div class="form-group">
            <label for="orgA" class="col-sm-2 control-label">主队组织</label>
            <div class="col-sm-10">
                <select id="orgA" class="form-control input-sm">
                    <option>选择主队的队伍</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="teamA" class="col-sm-2 control-label">主队</label>
            <div class="col-sm-10">
                <select id="teamA" name="teamA" class="form-control input-sm">
                    <option>选择主队的队伍</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="orgB" class="col-sm-2 control-label">客队组织</label>
            <div class="col-sm-10">
                <select id="orgB" class="form-control input-sm">
                    <option>选择客队的组织</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="teamB" class="col-sm-2 control-label">客队</label>
            <div class="col-sm-10">
                <select id="teamB" name="teamB" class="form-control input-sm">
                    <option>选择客队的队伍</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="beginTime" class="col-sm-2 control-label">开赛时间</label>
            <div class="col-sm-10">
                <input id="beginTime" name="time"
                       class="form-control" placeholder="请填写比赛时间">
            </div>
        </div>
        <div class="form-group">
            <label for="place" class="col-sm-2 control-label">开赛地点</label>
            <div class="col-sm-10">
                <input id="place" name="place" type="text" class="form-control" placeholder="请填写比赛地点">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="back" type="button" class="btn btn-danger">取消</button>
                <input type="button" onclick="checkplace(); createSch();return false;" class="btn btn-success"
                       value="确认"/>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    var matchId;
    var token;
    idSchedule = null;
    selectData = '<option value="{id}">{name}</option>';
    context = {
        error: false
    };
    $("#back").click(closeLyaer);

    function initOrg() {
        ajax({
            url: "${path}/schedule/showOrgs/" + matchId,
            type: "GET",
            dataType: "json",
            success: function (orgData) {
                token = orgData.token;
                var len = orgData.data.orgs.length;
                var Orgs = orgData.data.orgs;
                if (len <= 0) {
                    $("#orgA").append('<option>未获取到组织信息</option>');
                    $("#orgB").append('<option>未获取到组织信息</option>');
                }
                $.each(Orgs, function (index, org) {
                    $("#orgA").append(selectData.replace("{name}", org.name).replace("{id}", org.idOrg));
                    $("#orgB").append(selectData.replace("{name}", org.name).replace("{id}", org.idOrg));
                });
            }
        });
    }

    /**
     * 整合时需要传值的变量
     * MatchId
     */
    function createSch() {
        // var postData = new FormData(document.getElementById("createSchForm"));
        // postData.append("matchId", matchId);
        // postData.set("time", Date.parse(postData.get("time")));
        // datas = postData.entries();
        var teamA = $("#teamA").find("option:selected").attr("value");
        var teamB = $("#teamB").find("option:selected").attr("value");
        ajax({
            type: "post",//方法类型
            url: "${path}/schedule/create",//url
            data: {
                time: Date.parse($("#beginTime").val()),
                matchId: matchId,
                teamA: teamA,
                teamB: teamB,
                place: $("#place").val()
            },
            headers: {token: token},
            // contentType: false,
            // processData: false,
            success: function (result) {
                if (result.status === 0) {
                    layer.alert("创建成功", function (index) {
                        closeLyaer();
                    });
                } else if (result.message) {
                    layer.alert(result.message, {icon: 2});
                }
            },
            error: function () {
                layer.alert("创建出错", function (index) {
                    closeLyaer();
                });
            }
        });
    }

    function initTeam(orgId, isA) {
        formId = isA == "orgA" ? "#teamA" : "#teamB";
        $(formId).empty();
        ajax({
            url: "${path}/schedule/showTeams/" + matchId + "/" + orgId,
            type: "GET",
            dataType: "json",
            success: function (teamData) {
                token = teamData.token;
                var len = teamData.data.teams.length;
                var teams = teamData.data.teams;
                if (len <= 0) {
                    $(formId).append('<option>未获取到组织信息</option>');
                }
                $.each(teams, function (index, team) {
                    $(formId).append(selectData.replace("{name}", team.name).replace("{id}", team.idTeam));
                });
            }
        });
    }

    $(document).ready(function () {
        matchId = document.getElementById("mid").value;
        initOrg();
        $("#orgA,#orgB").change(function () {
            orgId = $(this).children('option:selected').val();
            initTeam(orgId, $(this).attr("id"));
        });

        laydate.render({
            elem: '#beginTime',
            min: 0
            , type: 'datetime'
        });

    });
    checkplace = function () {
        var place = document.getElementById('place').value;
        //是否为空
        if (place === '') {
            layer.alert('请输入比赛地点，比赛地点不能为空');
            document.getElementById('place').focus();
            return false;
        }
        //校验长度，类型
        else if (place.length > 50) {
            layer.alert('最大为50位');
            document.getElementById('idcard').focus();
            return false;
        }
        return true;
    };


</script>
</html>

