<%--
  Created by IntelliJ IDEA.
  User: yrge
  Date: 2017/4/18
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>直播主页</title>
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/H-ui.reset.css">
    <link rel="stylesheet" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css">
    <link rel="stylesheet" href="${base_path}/resources/css/live/gamePage.css">
    <link rel="stylesheet" href="${base_path}/resources/common/laypage/skin/laypage.css">
    <link href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/PIE_IE678.js"></script>


    <![endif]-->
</head>
<body >
<div class="team-game" id="LivaAndOverGames" ms-controller = "LivaAndOverGames">
    <div class="team-area w-200 f-l box-shadow pt-10 pb-30" >
        <ul>
            <li><span class="text-overflow f-16 pl-20" data-city-code="0" >全部</span></li>
            <c:forEach items="${listCity}" var="map">
                <li><span class="text-overflow f-16 pl-20" data-city-code="${map.cCode}" >${map.name}</span></li>
            </c:forEach>
        </ul>
    </div>
    <div class="game-live f-r box-shadow live-middle">
        <div class="live-grade pos-r box-shadow">
            <span class="f-l f-20 text-c">直播预告</span>
        </div>
        <table class="table table-border table-hover hui-fadeinB" id="liveGamesfor">
            <tr ms-if="nolivejson.length==0">
                <td>暂无数据.......</td>
            </tr>
            <tr ms-for="value in nolivejson">
                <td class="f-14 text-c">{{value.startTime}}</td>
                <td class="f-13 c-666 text-c">
                    <i class=""></i>
                    <span>{{value.comName}}</span>
                </td>
                <td class="f-16  team-name">
                    <img class="f-l mr-10" ms-attr="{title:value.hTName,src: '${base_path}/'+value.hTBadgePach}" style="">
                    <span ms-attr="{title:value.hTName}" class="text-overflow mt-10">{{value.hTName}}</span>
                </td>
                <td class="f-30 c-red text-c">VS</td>
                <td class="f-16 team-name">
                    <span title="" class="text-overflow text-r f-l mt-10">{{value.vTName}}</span>
                    <img class="f-r ml-10" ms-attr="{title:value.vTName,src: '${base_path}/'+value.vTBadgepach}" style="">
                </td>
            </tr>
        </table>
        <div class="page-w" id="page3" ></div>
    </div>
    <div class="game-live f-r box-shadow live-now">
        <div class="live-grade pos-r box-shadow">
            <span class="f-l f-20 text-c">正在直播</span>
        </div>
        <table class="table table-border table-hover hui-fadeinB" id="sliveGamesfor">
            <tr ms-if="livejson.length==0">
                <td>暂无数据.......</td>
            </tr>
            <tr ms-for="value in livejson">
                <td class="f-14 text-c">{{value.startTime}}</td>
                <td class="f-13 c-666 text-c">
                    <i class=""></i>
                    <span>{{value.comName}}</span>
                </td>
                <td class="f-16  team-name">
                    <img class="f-l mr-10" ms-attr="{title:value.hTName,src: '${base_path}/'+value.hTBadgePach}" style="">
                    <span ms-attr="{title:value.hTName}" class="text-overflow mt-10">{{value.hTName}}</span>
                </td>
                <td class="f-30 c-red text-c">VS</td>
                <td class="f-16 team-name">
                    <span title="" class="text-overflow text-r f-l mt-10">{{value.vTName}}</span>
                    <img class="f-r ml-10" ms-attr="{title:value.vTName,src: '${base_path}/'+value.vTBadgepach}" style="">
                </td>
                <td class="f-16 c-red text-c">
                    <a target="_blank" ms-attr="{href:value.rId + '/live_page.html' }" class=""><i class="Hui-iconfont">&#xe6c1;</i>图文直播</a>
                </td>
            </tr>
        </table>
        <div class="page-w" id="page2" ></div>
    </div>

    <div class="game-live f-r box-shadow live-past " id="live-past">
        <div class="live-grade pos-r ">
            <span class="f-l f-20 text-c">精彩回放</span>
        </div>
        <div class="live-grade pos-r box-shadow">
            <ul id="gameP">
                <li class="f-l f-18 text-c" data-id="1">小学</li>
                <li class="f-l f-18 text-c" data-id="2">初中</li>
                <li class="f-l f-18 text-c" data-id="3">高中</li>
                <li class="f-l f-18 text-c" data-id="4">大学</li>
                <li class="f-l f-18 text-c" data-id="5">其他</li>
                <li id="slide-line" class="pos-a"></li>
            </ul>
        </div>
        <table class="table table-border table-hover hui-fadeinB" data-tid="primary" id="overGamesfor">
            <tr ms-if="overjson.length==0">
                <td>暂无数据.......</td>
            </tr>
            <tr ms-for="value in overjson">
                <td class="f-14 text-c">{{value.startTime}}</td>
                <td class="f-13 c-666 text-c">
                    <i class=""></i>
                    <span>{{value.comName}}</span>
                </td>
                <td class="f-16  team-name">
                    <img class="f-l" ms-attr="{title:value.hTName,src: '${base_path}/'+value.hTBadgePach}" style="">
                    <span title="{{value.hTName}}" class="text-overflow mt-10 ml-10">{{value.hTName}}
            </span>
                </td>
                <td class="f-30 c-primary text-c">{{value.homeScore}}-{{value.visitingScore}}</td>
                <td class="f-16 team-name">
                    <span title="" class="text-overflow text-r f-l mr-10 mt-10">{{value.vTName}}</span>
                    <img class="f-r" ms-attr="{title:value.vTName,src: '${base_path}/'+value.vTBadgepach}" style="">
                </td>
                <td class="f-15 c-primary text-c">
                    <a target="_blank" ms-attr="{href:value.rId + '/over_page.html' }" class=""><i class="Hui-iconfont">&#xe690;</i>直播回放</a>
                </td>
            </tr>

        </table>
        <div id="page1" class="page-w" ></div>
    </div>
</div>
</body>
<script type="application/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/laypage/laypage.js"></script>
<script type="application/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="application/javascript" src="${base_path}/resources/js/live/gamePage.js"></script>

</html>