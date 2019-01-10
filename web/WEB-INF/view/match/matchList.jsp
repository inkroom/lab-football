<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<body>
    <script src="${path}/resources/lib/layer/laydate/laydate.js"></script>
<jsp:include page="../common/head.jsp"/>
<input hidden id="token" value="${token}">
    <div class="container ml-h" style="font-weight: 600">
    赛事列表
</div>
    <div class="container ml-tab" style="margin-top: 30px">
    <div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
        <ul class="nav nav-tabs" id="myTabs">
            <li class="active">
                <a id="home-tab" role="tab" href="#home" data-toggle="tab">赛事报名</a>
            </li>
            <li>
                <a id="profile-tab" role="tab" href="#profile" data-toggle="tab">已报名赛事</a>
            </li>
            <li>
                <a id="dropdown1-tab" role="tab" href="#dropdown1" data-toggle="tab">赛事审核</a>
            </li>
            <li>
                <a id="createMatch-tab" role="tab" href="#createMatch" data-toggle="tab">赛事发布</a>
            </li>
        </ul>

        <%--<ul class="layui-tab-title" id="myTabs" >--%>
        <%--<li  class="layui-this" lay-id="1">--%>
        <%--<a id="home-tab" role="tab" aria-expanded="true" aria-controls="home" href="#home" data-toggle="tab">未加入赛事</a>--%>
        <%--</li>--%>
        <%--<li lay-id="2">--%>
        <%--<a id="profile-tab" role="tab" aria-expanded="false" aria-controls="profile" href="#profile" data-toggle="tab">我加入的赛事</a>--%>
        <%--</li>--%>
        <%--<li lay-id="3">--%>
        <%--<a id="dropdown1-tab" role="tab" aria-controls="dropdown1" href="#dropdown1" data-toggle="tab">我发布的赛事</a>--%>
        <%--</li>--%>
        <%--<li lay-id="4">--%>
        <%--<a id="dropdown2-tab" role="tab" aria-controls="dropdown2" href="#dropdown2" data-toggle="tab">创建赛事</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <!--标签页-->
        <div class="tab-content" id="myTabContent">
            <!--第一个标签-->
            <div class="tab-pane fade active in" id="home" role="tabpanel" aria-labelledby="home-tab">
                <div class="tab-1">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>赛事</th>
                            <th>报名截止日期</th>
                            <th>赛事状态</th>
                            <th>其他</th>
                        </tr>
                        </thead>
                        <tbody id="tbody1">
                        </tbody>
                    </table>
                </div>
                <div class="col-md-4 pages col-md-offset-8"></div>
            </div>
            <!--第二个标签-->
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="tab-1">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>赛事</th>
                            <th>比赛开始日期</th>
                            <th>赛事状态</th>
                            <th>审核状态</th>
                        </tr>
                        </thead>
                        <tbody id="tbody2">

                        </tbody>
                    </table>
                </div>
                <div class="col-md-4 pages col-md-offset-8 pages"></div>
            </div>
            <!--第三个标签-->
            <div class="tab-pane fade" id="dropdown1" role="tabpanel" aria-labelledby="dropdown1-tab">
                <div class="tab-1">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>赛事</th>
                            <th>比赛开始日期</th>
                            <th>赛事状态</th>
                            <th>其他</th>
                        </tr>
                        </thead>
                        <tbody id="tbody3">

                        </tbody>
                    </table>
                </div>
                <div class="col-md-4 pages col-md-offset-8 pages"></div>
            </div>
            <!--第四个标签-->
            <div class="tab-pane fade" id="createMatch" role="tabpanel" aria-labelledby="dropdown2-tab">
                <form id="createMatchForm" class="form-horizontal">
                    <div class="form-group" style="width: 98%;">
                        <label for="inputName" class="col-sm-2 control-label">赛事名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="inputName" name="name" class="form-control"
                                   placeholder="请填写赛事名称">
                        </div>
                    </div>
                    <div class="form-group" style="width: 98%;">
                        <label for="inputLevel" class="col-sm-2 control-label">赛事级别</label>
                        <div class="col-sm-10">
                            <select id="inputLevel" name="level" class="form-control input-sm">
                                <option value="0">校级</option>
                                <option value="1">县级</option>
                                <option value="2">市级</option>
                                <option value="3">省级</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="width: 98%">
                        <label for="inputNumPlayer" class="col-sm-2 control-label">赛制</label>
                        <div class="col-sm-10">
                            <select id="inputNumPlayer" name="numPlayer" class="form-control input-sm">
                                <option value="5">5人制</option>
                                <option value="7">7人制</option>
                                <option value="11">11人制</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="width: 98%">
                        <label for="inputDeadline" class="col-sm-2 control-label">报名截止时间</label>
                        <div class="col-sm-10">
                            <input id="inputDeadline" class="form-control"
                                   name="applyDeadline" placeholder="请点击填写报名截止时间">
                        </div>
                    </div>
                    <div class="form-group" style="width: 98%">
                        <label for="inputTime" class="col-sm-2 control-label">开赛时间</label>
                        <div class="col-sm-10">
                            <input id="inputTime" name="startDate" class="form-control"
                                   placeholder="请点击填写比赛时间">
                        </div>
                    </div>

                    <div class="form-group" style="width: 98%">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="button" onclick="createMatch();return false;"
                                   class="btn btn-success"
                                   value="确认"/>
                            <!--<button type="submit" class="btn btn-default">取消</button>-->
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>
<script type="application/javascript" src="${path}/resources/js/match/matchList.js"></script>
<script>
    laydate.render({
        elem: '#inputTime',
        min: 0
    });
    laydate.render({
        elem: '#inputDeadline',
        min: 0
    });
    checkName = function () {

        var inputName = document.getElementById('inputName').value;
        var adate = document.getElementById('inputDeadline').value;
        var sdate = document.getElementById('inputTime').value;
        //是否为空
        if (inputName === '') {
            layer.alert('请输入比赛地点，比赛地点不能为空');
            document.getElementById('inputName').focus();
            return false;
        }
        //校验长度，类型
        else if (inputName.length > 50) {
            layer.alert('最大为50位');
            document.getElementById('inputName').focus();
            return false;
        }
        if (adate === '') {
            layer.alert('报名截止时间不能为空');
            document.getElementById('inputDeadline').focus();
            return false;
        }
        else if (sdate === '') {
            layer.alert('开赛时间不能为空');
            document.getElementById('inputTime').focus();
            return false;
        }
        //TODO 报名截止日期小于开赛时间
        return true;
    };
    //设置标题
    $("#titleName").text("赛事列表");

    //测试时初始化数据
    function initData() {
        $("#inputName").val("德玛西亚杯水友赛");
        $("#inputTime").val("2018-08-07");
        $("#inputDeadline").val("2018-07-02");
    }


    var myATab = $('a[data-toggle="tab"]');
    // 监听 tab 的 shown.bs.tab 事件
    // 发生时在浏览器的 Local Storage 里面存储当前所显示的选项卡。
    myATab.on('shown.bs.tab', function () {
        localStorage.setItem('matchList', $(this).attr('href'));
    });


</script>
