<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<script src="${path}/resources/lib/layer/laydate/laydate.js"></script>
    <body>
    <input hidden id="token" value="${token}">
    <input id="secret" hidden idSchedule=${idSchedule} match="${match}">
    <jsp:include page="../common/head.jsp"/>
        <div class="container mo-h">
            修改赛程
        </div>
        <div class="container">
            <form class="form-horizontal" onsubmit="return false;">
                <div class="form-group">
                    <label for="teamA" class="col-sm-2 control-label">主队</label>
                    <div id="teamA" class="col-sm-10">
                        <select name="teamA" class="form-control input-sm">

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="teamB" class="col-sm-2 control-label">客队</label>
                    <div id="teamB" class="col-sm-10">
                        <select name="teamB" class="form-control input-sm">

                        </select>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                <%--<label for="goalA" class="col-sm-2 control-label">主队分数</label>--%>
                <%--<div class="col-sm-10">--%>
                <%--<input type="text" class="form-control" name="goalA" id="goalA" placeholder="请填写更正比分">--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group has-success">--%>
                <%--<label for="goalB" class="col-sm-2 control-label">客队分数</label>--%>
                <%--<div class="col-sm-10">--%>
                <%--<input type="text" class="form-control" name="goalB" id="goalB" placeholder="请填写更正比分">--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label for="time" class="col-sm-2 control-label">开赛时间</label>
                    <div class="col-sm-10">
                        <input class="form-control" name="time" id="time" placeholder="请填写比赛时间">
                    </div>
                </div>
                <div class="form-group">
                    <label for="place" class="col-sm-2 control-label">开赛地点</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="place" id="place" placeholder="请填写比赛地点">
                    </div>
                </div>
                <div class="form-group">
                    <label for="status" class="col-sm-2 control-label">状态</label>
                    <div class="col-sm-10">
                        <select id="status" class="form-control input-sm">
                            <option status="0">正在准备</option>
                            <option status="1">正在比赛</option>
                            <option status="2">比赛结束</option>
                            <option status="3">已删除</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button id="back" type="button" class="btn btn-danger">取消</button>
                        <button id="submit" type="button" class="btn btn-success">确认</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
<script src="${path}/resources/js/match/modifySchedule.js"></script>
<script>
    $("#back").click(closeLyaer);
</script>
</html>

