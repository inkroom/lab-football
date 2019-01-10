<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<body>
<input hidden id="token" value="${token}">
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
        <a id="upBtn" style="color: #fff;">
            <button class="btn btn-primary" style="float: right;margin-right: 10px;">修改</button>
        </a>
    </div>
</div>
<div class="container ml-tab">
    <div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
        <ul class="nav nav-tabs" id="myTabs" role="tablist">
            <li class="active" role="presentation">
                <a id="schedule-list" role="tab" href="#home" data-toggle="tab">赛程表</a>
            </li>
            <li role="presentation">
                <a id="profile-tab" role="tab" href="#profile" data-toggle="tab">审核表</a>
            </li>
            <!--<li class="dropdown" role="presentation"><a id="dropdown1-tab" role="tab" aria-controls="dropdown1" href="#dropdown1" data-toggle="tab">审核表2</a></li>-->
        </ul>
        <!--标签页-->
        <div class="tab-content">
            <!--第一个标签-->
            <div class="tab-pane fade active in sch-w" id="home" role="tabpanel" aria-labelledby="home-tab">
                <a id="addBtn" style="color: #fff;">
                    <button class="btn btn-primary" style="float: right;margin-right: 10px;margin-top: -5px;">新增
                    </button>
                </a>
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
                            <th>操作</th>
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
            <%--<!--第二个标签-->--%>
            <%--<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">--%>
            <%--<div class="container si-bo">--%>
            <%--<div class="t-h" id="examine">--%>

            <%--</div>--%>
            <%--<div class="t-c">--%>
            <%--<div class="show t-c-bo">--%>
            <%--<table class="table table-hover">--%>
            <%--<thead>--%>
            <%--<tr>--%>
            <%--<td colspan="2">教练：xxx</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>姓名</td>--%>
            <%--<td>所属队伍</td>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody id="playerbody">--%>

            <%--</tbody>--%>
            <%--</table>--%>
            <%--<div id="button" style="display: block"></div>--%>
            <%--<div id="pageInfo" style="display: block"></div>--%>

            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="container si-bo">
                    <div class="t-h" style="overflow-y: auto">
                        <div class="selected">
                            <a href="#demo1" data-toggle="collapse">
                                <div id="myCollapsibleExample1" class="t-h-n">
                                    待审核
                                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"
                                          style="font-size: 14px;left: 59%;"></span>
                                </div>
                            </a>
                            <div id="demo1" class="collapse t-h-bo">
                                <ul id="ul1">

                                </ul>
                            </div>
                        </div>
                        <div>
                            <a href="#demo2" data-toggle="collapse">
                                <div id="myCollapsibleExample2" class="t-h-n">
                                    审查通过
                                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"
                                          style="font-size: 14px;left: 52%;"></span>
                                </div>
                            </a>
                            <div id="demo2" class="collapse t-h-bo">
                                <ul id="ul2">

                                </ul>
                            </div>
                        </div>
                        <div>
                            <a href="#demo3" data-toggle="collapse">
                                <div id="myCollapsibleExample3" class="t-h-n">
                                    审核已拒绝
                                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"
                                          style="font-size: 14px;left: 45%;"></span>
                                </div>
                            </a>
                            <div id="demo3" class="collapse t-h-bo">
                                <ul id="ul3">

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="t-c">
					        	<span style="display: block; font-size: 18px;padding-left: 8.5%;float: left;margin-top: 15px;"
                                      id="oName">
				            	</span>
                        <span id="btn" style="display: none;padding-right: 7.1%;float: right;margin-top: 25px;">
				            		<a id="passbtn" type="button" style="float: left;margin-top: -18px;"
                                       class="btn btn-success btn-sm">通过</a>
									<a id="dangerbtn" type="button"
                                       style="float: left;margin-top: -18px; margin-left: 2px;"
                                       class="btn btn-danger btn-sm">拒绝</a>
				            	</span>
                        <div id="c1" class="show t-c-bo">

                            <table id="teamTable" class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>队伍名称</th>
                                    <th>教练</th>
                                    <th>人数</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="teamBody">
                                </tbody>

                            </table>
                            <div id="pageInfo" style="display: block"></div>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="tNameh"></h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">教练:</label>
                            <div class="col-sm-10">
                                <p style="margin-top: -9px" class="form-control-static" id="coatch"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">人数:</label>
                            <div class="col-sm-10">
                                <p style="margin-top: -9px" class="form-control-static" id="count"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10">
                                <table class="table table-hover table-bordered">
                                    <thead>
                                    <th>队员</th>
                                    <th>性别</th>
                                    <th>年级</th>
                                    <th>班级</th>
                                    </thead>
                                    <tbody id="playerBody">
                                    </tbody>
                                </table>

                            </div>
                        </div>


                        <div id="pageInfo2" style="display: block"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<div style="height: 50px;"></div>
<%--JS引用--%>
<script src="${path}/resources/js/match/slide.js"></script>
<script src="${path}/resources/js/match/matchDetailsAdmin.js"></script>

</body>
</html>
