<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<script src="${path}/resources/lib/layer/laydate/laydate.js"></script>
<body>

<jsp:include page="../common/head.jsp"/>
<input hidden id="mid" value="${mid}"/>
<input hidden id="token" value="${token}">
<div class="container mo-h">
    修改赛事
</div>
<div class="container">
    <form id="modifyMatchForm" class="form-horizontal">
        <div class="form-group">
            <label for="matchName" class="col-sm-2 control-label">赛事名称</label>
            <div class="col-sm-10">
                <input type="text" id="matchName" name="name" class="form-control"
                       placeholder="请填写赛事名称">
            </div>
        </div>
        <div class="form-group">
            <label for="matchLevel" class="col-sm-2 control-label">赛事级别</label>
            <div class="col-sm-10">
                <select id="matchLevel" name="level" class="form-control input-sm">
                    <option value="0">校级</option>
                    <option value="1">县级</option>
                    <option value="2">市级</option>
                    <option value="3">省级</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="matchNumPlayer" class="col-sm-2 control-label">赛制</label>
            <div class="col-sm-10">
                <select id="matchNumPlayer" name="numPlayer" class="form-control input-sm">
                    <option value="5">5人制</option>
                    <option value="7">7人制</option>
                    <option value="11">11人制</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="matchStartTime" class="col-sm-2 control-label">赛事时间</label>
            <div class="col-sm-10">
                <input class="form-control" id="matchStartTime" name="startDate"
                       placeholder="请填写比赛时间">
            </div>
        </div>
        <div class="form-group">
            <label for="matchDeadLine" class="col-sm-2 control-label">报名截止时间</label>
            <div class="col-sm-10">
                <input class="form-control" id="matchDeadLine" name="applyDeadline"
                       placeholder="请填写报名截止时间">
            </div>
        </div>
        <div class="form-group">
            <label for="matchStatus" class="col-sm-2 control-label">状态</label>
            <div class="col-sm-10">
                <select id="matchStatus" class="form-control input-sm">
                    <option value="0">报名阶段</option>
                    <option value="1">进行中</option>
                    <option value="2">已结束</option>
                    <option value="3">删除赛事</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="falseBtn" type="button" class="btn btn-danger">取消</button>
                <input type="button" onclick=" modifyMatch();return false;" class="btn btn-success" value="确认"/>
            </div>
        </div>
    </form>
</div>
</body>
<script type="application/javascript">
    var matchId;
    var token;
    $(document).ready(function () {
        matchId = document.getElementById("mid").value
        setFormInfo(matchId);
        $("#falseBtn").click(closeLyaer);
        laydate.render({
            elem: '#matchStartTime',
            min: 0
        });
        laydate.render({
            elem: '#matchDeadLine',
            min: 0
        });
    });

    function modifyMatch() {
        // var postData = new FormData(document.getElementById("modifyMatchForm"));
        // postData.append("idMatch", matchId);
        ajax({
            type: "post",//方法类型
            url: getRootPath() + "/match/modifyMatch",//url
            data: {
                idMatch: matchId,
                name: $('#matchName').val(),
                level: $('#matchLevel').val(),
                numPlayer: $('#matchNumPlayer').val(),
                startDate: $('#matchStartTime').val(),
                applyDeadline: $('#matchDeadLine').val(),
                status: $('#matchStatus').val()
            },
            headers: {token: token},
            // contentType: false,
            // processData: false,
            success: function (result) {
                if (result.status === 0) {
                    layer.alert("修改成功", function (index) {
                        closeLyaer();
                    });
                } else if (result.message) {
                    layer.alert(result.message, {icon: 2}, function (index) {
                        closeLyaer();
                    });
                }
            },
            error: function () {
                layer.alert("修改出错", function (index) {
                    closeLyaer();
                });
            }
        });
    }

    function setFormInfo(match) {
        ajax({
            url: getRootPath() + "/match/MatchInfo/" + match,
                success: function (matchInfo) {
                    token = matchInfo.token;
                    $("#matchName").val(matchInfo.data.matchInfo.name);
                    $('#matchLevel').val(matchInfo.data.matchInfo.level);
                    $('#matchNumPlayer').val(matchInfo.data.matchInfo.numPlayer);
                    $("#matchStartTime").val(parseTime(matchInfo.data.matchInfo.startDate));
                    $("#matchDeadLine").val(parseTime(matchInfo.data.matchInfo.applyDeadline));
                    $("#matchStatus").val(matchInfo.data.matchInfo.status);
                }
            }
        );
    }

    function initData() {
        $("#inputName").val("德玛西亚杯水友赛");
        $("#inputTime").val("2018-08-07");
        $("#inputDeadline").val("2018-07-02");
    }

    modifySch = function () {
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
        layer.alert('OK');
        return true;
    };

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
        layer.alert('OK');
        return true;
    };

</script>
</html>


