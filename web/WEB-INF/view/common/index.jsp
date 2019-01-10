<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>校园足球信息化管理系统</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/match/style.1.0.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css"/>
    <script src="${path}/resources/lib/respond.js" type="text/javascript" charset="utf-8"></script>

    <%--<script src="${path}/resources/js/index.js" type="text/javascript" charset="utf-8"></script>--%>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="hp-bg">
    <div class="container">
        <div class="head">
            <div class="head-w-1 col-lg-11 col-md-11 col-sm-10 col-xs-9"><h1>精彩赛况</h1></div>
            <div class="head-w-2 col-lg-1 col-md-1 col-sm-2 col-xs-3">更多>></div>
        </div>
        <div class="body">
            <div class="body-l col-lg-7 col-md-7 col-sm-12 col-xs-12">
                <video class="col-lg-12 col-md-12 col-sm-12 col-xs-12" controls >
                    <source src="${path}/resources/img/oceans.mp4" type="video/mp4">
                </video>
            </div>
            <div class="body-r col-lg-5 col-md-5 col-sm-12 col-xs-12">
                <div class="body-r-1">
                    <ul>教练员
                        <li><a href="#">xxx，</a>是来自xx学校的老师，带领了......</li>
                        <li><a href="#">xxx，</a>是来自xx学校的老师，带领了......</li>
                        <li><a href="#">xxx，</a>是来自xx学校的老师，带领了......</li>
                        <li><a href="#">xxx，</a>是来自xx学校的老师，带领了......</li>
                        <li><a href="#">...</a></li>
                    </ul>
                </div>
                <div class="body-r-1" style="margin-top: 35px;">
                    <ul>xxx队
                        <li><a href="#">xxx队，</a>是来自xx学校的队伍，他们有了......</li>
                        <li><a href="#">xxx队，</a>是来自xx学校的队伍，他们有了......</li>
                        <li><a href="#">xxx队，</a>是来自xx学校的队伍，他们有了......</li>
                        <li><a href="#">xxx队，</a>是来自xx学校的队伍，他们有了......</li>
                        <li><a href="#">...</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="head">
            <div class="head-w-1 col-lg-11 col-md-11 col-sm-10 col-xs-9"><h1>近期赛事</h1></div>
            <div class="head-w-2 col-lg-1 col-md-1 col-sm-2 col-xs-3">更多>></div>
        </div>
        <div class="body-2">
            <div class="body-2-p col-lg-3 col-md-3">
                <p>xx比xx</p>
                <p>计算机科学，研究计算机及其周围各种现象和规律的科学，亦即研究计算机系统结构、程序系统（即软件）、人工智能以及计算本身的性质和问题的学科。<a href="#">...</a></p>
            </div>
            <div class="body-2-p col-lg-3  col-md-3 hidden-sm hidden-xs " style="margin-left: 12%;">
                <p>xx比xx</p>
                <p>计算机科学，研究计算机及其周围各种现象和规律的科学，亦即研究计算机系统结构、程序系统（即软件）、人工智能以及计算本身的性质和问题的学科。<a href="#">...</a></p>
            </div>
            <div class="body-2-p col-lg-3 col-md-3 hidden-sm hidden-xs" style="margin-left: 12%;">
                <p>xx比xx</p>
                <p>计算机科学，研究计算机及其周围各种现象和规律的科学，亦即研究计算机系统结构、程序系统（即软件）、人工智能以及计算本身的性质和问题的学科。<a href="#">...</a></p>
            </div>
            <div class="body-2-p col-sm-12 col-xs-12 hidden-lg hidden-md">
                <p>xx比xx</p>
                <p>计算机科学，研究计算机及其周围各种现象和规律的科学，亦即研究计算机系统结构、程序系统（即软件）、人工智能以及计算本身的性质和问题的学科。<a href="#">...</a></p>
            </div>
            <div class="body-2-p col-sm-12 col-xs-12 hidden-lg hidden-md">
                <p>xx比xx</p>
                <p>计算机科学，研究计算机及其周围各种现象和规律的科学，亦即研究计算机系统结构、程序系统（即软件）、人工智能以及计算本身的性质和问题的学科。<a href="#">...</a></p>
            </div>
        </div>
        <div class="head">
            <div class="head-w-1 col-lg-12 col-md-12 col-sm-12 col-xs-12"><h1>赛点查询</h1></div>
        </div>
        <div class="body-3">
            <div style="height:20px;"></div>
            <div>请输入学校查询相关比赛：</div>
            <div>
                <div><input type="text" class="form-control"/></div>
                <div>
                    <button class="btn btn-success">查询</button>
                </div>
            </div>
            <div class="col-lg-10 col-lg-offset-1">
                <table class="table">
                    <thead>
                    <tr>
                        <th>标号</th>
                        <th>队名</th>
                        <th>xxx</th>
                        <th>xxx</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-offset-8 col-lg-3">
                <nav aria-label="Page navigation" class="pag">
                    <ul class="pagination ">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!--<div class"head">
            <div class="head-w-1 col-lg-12 col-md-12 col-sm-12 col-xs-12">赛事规则</h1></div>
        </div>
        <div class="body-4">
            <div class="body-4-w col-lg-12">
                &nbsp;&nbsp;&nbsp;&nbsp;32支参赛队通过抽签分为八个小组,每个小组分别有四支球队进行比赛，每支球队都必须和其他三支球队进行且只进行一场比赛,每组4个队循环比赛，共打6场(a1-a2;a1-a3;a1-a4;a2-a3;a2-a4;a3-a4),每场比赛90分钟，胜平负分别积3、1、0分。每个小组积分的前两名球队出线进入淘汰赛阶段的1/8决赛，共16支队，即“16强”。
            </div>
        </div>-->
    </div>
</div>
<script src="${path}/resources/lib/jquery/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${path}/resources/lib/layer/layer.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
