<style>
    #container {
        margin: 15px 15px 15px 30px;
        float: left;
        height: 670px;
        width: 800px;
        /*border: 2px solid green;*/
    }

    #div_select {
        height: 40%;
        width: 100%;
        margin-bottom: 20px;
        /*border: 2px solid red;*/
    }

    #select_head {
        width: 100%;
        height: 17%;
    }

    .btn_mormal {
        display: inline-block;
        width: 80px;
        float: right;
        /*消除按下按钮之后的边框*/
        outline: none;
    }

    #select_player {
        overflow-y: auto;
        width: 100%;
        height: 83%;
        border: 1px solid rgb(197, 197, 197);
        border-radius: 12px;
    }

    #real_player {
        width: 95%;
        height: 80%;
        margin: 20px;
    }

    #real_wait {
        width: 95%;
        height: 85%;
        margin: 20px;
    }

    #div_wait {
        height: 60%;
        width: 100%;
        /*border: 2px solid red;*/
    }

    #wait_head {
        width: 100%;
        height: 10%;
    }

    #select_all {
        margin-left: 15px;
        /*消除按下按钮之后的边框*/
        outline: none;
    }

    #wait_player {
        margin-top: 10px;
        overflow-y: auto;
        width: 100%;
        height: 83%;
        border: 1px solid rgb(197, 197, 197);
        border-radius: 12px;
    }

    /*#teamNameList div:link {*/
    /*background-color: rgb(56, 50, 72);*/
    /*}*/

    /*#teamNameList div:visited {*/
    /*background-color: rgb(56, 50, 72);*/
    /*}*/

    /*#teamNameList div:hover {*/
    /*background-color: rgb(141, 144, 149);*/
    /*}*/

    /*#teamNameList div:active {*/
    /*background-color: rgb(141, 144, 149);*/
    /*}*/

</style>
<script>
    var teamList = '${teamList}';
    var matchId = Number(${matchid});
    var state = Number(${state});
</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="../common/head.jsp"/>
<div style="height: 20px;"></div>
<div class="container">
    <div class="pic">
        <div class="box-1">
            <div class="color1" style="left:0">
            </div>
            <div class="color2"></div>
            <div class="color3"></div>
            <div class="color4"></div>
            <div class="color5"></div>
        </div>
        <div class="tit">
            <a href="javascript:;" style="color:#fff">1</a>
            <a href="javascript:;">2</a>
            <a href="javascript:;">3</a>
            <a href="javascript:;">4</a>
            <a href="javascript:;">5</a>
            <div class="float"></div>
        </div>
        <div class="leftB">&lt;</div>
        <div class="rightB">&gt;</div>
    </div>
    <input hidden id="mid" value="${mid}"/>
    <div class="deta col-lg-4 col-md-3 col-sm-12 col-xs-12">
        <div class="d-h"><b><span id="matchName"></span></b></div>
        <div class="deta-w">主办方：<b><span id="orgName"></span></b></div>
        <div class="deta-w">赛事级别：<b><span id="matchLevel"></span></b></div>
        <div class="deta-w">赛事状态：<b><span id="matchStatus"></span></b></div>
        <div class="deta-w">赛制：<b><span id="matchNumPlayer"></span></b></div>
        <div class="deta-w">赛事开始时间：<b><span id="matchStartTime"></span></b></div>
        <div class="deta-w">报名截止时间：<b><span id="matchDeadLine"></span></b></div>
    </div>
</div>
<div class="container ml-tab">
    <div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
        <ul class="nav nav-tabs" id="myTabs" role="tablist">
            <li class="active" role="presentation"><a id="schedule-list" role="tab" aria-expanded="true"
                                                      aria-controls="home" href="#home" data-toggle="tab">赛程表</a></li>
            <li role="presentation"><a id="profile-tab" role="tab"
                                       href="#profile" data-toggle="tab">报名表</a></li>
        </ul>
        <!--标签页-->
        <div class="tab-content">
            <!--第一个标签-->
            <div class="tab-pane fade active in sch-w" id="home" role="tabpanel" aria-labelledby="home-tab">
                <div class="tab-4" id="schedule-data">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>开赛时间</th>
                            <th>地点</th>
                            <th>主队</th>
                            <th>得分</th>
                            <th>客队</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody id="body">

                        </tbody>
                    </table>
                </div>
                <div class="pag" id="pageBtn" style="display: none">
                    <nav aria-label="Page navigation" class="pag">
                        <ul class="pagination">
                            <li id="previous">
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                                <!--这里动态加载分页按钮-->
                            <li id="next">
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                        </ul>
                    </nav>
                </div>
            </div>
            <!--第二个标签-->
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="container">
                    <div class="t-h" id="teamNameList" style="overflow-y: auto;background-color: #f7f7f7;">
                        <!--<div onclick="selectTeam(this)">-->
                        <!--<div class="t-h-n">-->
                        <!--1队-->
                        <!--</div>-->
                        <!--</div>-->

                    </div>
                    <div id="container">
                        <div id="div_select">
                            <!--已选人员-->
                            <div id="select_head">
                                <span style="font-size: 25px">已选人员</span>
                                <button id="submit" class="btn btn-success btn_mormal">提交</button>
                            </div>
                            <div id="select_player">
                                <div class="plus-tag tagbtn clearfix" id="real_player">


                                </div>
                            </div>
                        </div>
                        <div id="div_wait">
                            <!--待选人员-->
                            <div id="wait_head">
                                <table>
                                    <tr>
                                        <td>
                                            <span style="font-size: 25px">待选人员</span>
                                        </td>
                                        <td>
                                            <!--s是标志位-->
                                            <button class="btn btn-success" id="select_all" s="1">全选
                                                <span id="select_all_flag" style="margin-left: 4px;line-height: 1px"
                                                      class="glyphicon glyphicon-refresh"></span>
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="wait_player" class="default-tag tagbtn">
                                <div class="clearfix nowtips" id="real_wait">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="height: 50px;"></div>
<script type="application/javascript" src="${path}/resources/js/match/matchDetails.js"></script>
<script src="${path}/resources/js/match/matchSign.js"></script>
<script src="${path}/resources/js/match/tab.js"></script>
    <script src="${path}/resources/js/match/help.js"></script>
</body>
</html>