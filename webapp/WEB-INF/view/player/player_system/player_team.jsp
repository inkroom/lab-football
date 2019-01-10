<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />

	<!--[if lt IE 9]>
	<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
    <title>球队信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球员管理 <span class="c-gray en">&gt;</span> 我的球队 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <span class="select-box radius">
                     <select class="select" size="1" name="select1" onchange="change('${base_path}')" id="team_id">
                         <option value="" selected>选择你的球队</option>
                         <c:forEach items="${team_list}" var="list" varStatus="status">
                         	<option value="${list.TEAM_ID}">${list.TEAM_NAME}</option>
                         </c:forEach>
                     </select>
                </span>
            </a>
        </div>
        <c:if test="${info eq '暂未加入任何球队'}">
        	<div style="font-size:24px; width:250px; height:50px;line-height:50px;text-align:center;margin:50px auto;">${info}</div>
        </c:if>
        <c:if test="${info ne '暂未加入任何球队'}">
        <table class="table table-border table-bordered table-bg  table-striped mt-20">
            <tbody class="text-c">
            <tr>
                <td colspan="4" rowspan="">
                	<c:if test="${team_info.TEAM_BADGE eq null}">
                		<img class="avatar size-XXL radius" src="http://static.h-ui.net/h-ui/images/ucnter/avatar-default.jpg" id="TEAM_IMG">
                	</c:if>
                	<c:if test="${team_info.TEAM_BADGE ne null}">
                		<img class="avatar size-XXL radius" src="${base_path}/${team_info.TEAM_BADGE}" id="TEAM_IMG">
                	</c:if>
                </td>
              </tr>
            <tr>
                <td>球队名称：</td><td id="TEAM_NAME">${team_info.TEAM_NAME}</td>
                <td >球队编号：</td><td id="TEAM_NUM">${team_info.TEAM_NUM}</td>
            </tr>
            <tr>
                <td>球队积分：</td><td id="INTEGRAL">${team_info.INTEGRAL}</td> <td >所属机构： </td>
                <td id="TEAM_AFFILIATION">${team_info.TEAM_AFFILIATION}
                	<c:if test="${team_info.TEAM_AFFILIATION eq null}">
                		暂无
                	</c:if>
                </td>
            </tr>
            <tr>
                <td>比赛场次：</td><td id="MATCH_PLAY">${team_info.MATCH_PLAY}</td>
                <td>平均胜率：</td><td id="BAT_AVG">${team_info.BAT_AVG}%</td>
            </tr>
            <tr>
                <td >领队： </td>
                <td id="TEAM_LEADER">${team_info.TEAM_LEADER}
                	<c:if test="${team_info.TEAM_LEADER eq null}">
                		暂无
                	</c:if>
                </td>
                <td >教练员：</td><td id="COACH_NAME">${team_info.COACH_NAME}
                	<c:forEach items="${COACH_NAME_LIST}" var="list" varStatus="stat">
                		${list.get("COACH_NAME")}
                		<c:if test="${!stat.last}">、</c:if>
                	</c:forEach>
                	<c:if test="${COACH_NAME_LIST eq '[]'}">
                		暂无
                	</c:if>
                </td>
            </tr>
            <tr>
                <td>队员：</td>
                <td colspan="3" id="TEAM_MEMBER">
                	<c:forEach items="${TEAM_MEMBER}" var="list" varStatus="stat">
                		${list.get("A_NAME")}
                		<c:if test="${!stat.last}">、</c:if>
                	</c:forEach>
                </td></tr>


            </tbody>
        </table>
        </c:if>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/player/page/player_team.js"></script>
</body>
</html>