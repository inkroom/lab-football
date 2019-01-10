<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>裁判书</title>
<meta charset="UTF-8">
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
<link rel="stylesheet" href="${base_path}/resources/css/site/judgement.css">
<link rel="stylesheet" href="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<style>
.W-10 {
	width: 10%;
}
</style>
</head>
<body class="pt-20 pb-30">
	<div id="judgment" class="pl-20 pr-20 box-shadow">
		<h1 class="text-c pd-40 mb-40">裁判员报告书</h1>
		<h4>
			赛事名称：<span>${R_NAME}</span>
		</h4>
		<h4>
			参赛队伍：主队：<span>${HName}</span> 客队：<span>${VName}</span>
		</h4>
		<h4 id="game-start-time">
			比赛开始时间：<span>${R_START_TIME}</span>
		</h4>
		<h4 id="game-end-time">
			比赛结束时间：<span>${R_END_TIME}</span>
		</h4>
		<h4>
			场序号：<input type="text" id="fieldNum" class="input-text mr-15"
				placeholder="场序号" value="${detailsList['R_FIELD_NUM']}"
				onblur="checkScreenings(this)"> 地点：<input type="text"
				id="gamePlace" class="input-text" placeholder="地点"
				value="${detailsList['R_REGION']}" onblur="checkPlaces(this)">
		</h4>
		<h4>比赛情况：</h4>
		<table class="table table-border table-bordered mt-20 mb-20">
			<thead class="text-c">
				<tr>
					<th class="text-c" colspan="3">常规赛比分</th>
					<th class="text-c" colspan="3">加时赛比分</th>
					<th class="text-c" colspan="3">点球决胜结果</th>
					<th class="text-c" colspan="3">获胜队</th>
				</tr>
				<tr>
					<td class="W-10">主队</td>
					<td class="W-10">VS</td>
					<td class="W-10">客队</td>
					<td class="W-10">主队</td>
					<td class="W-10">VS</td>
					<td class="W-10">客队</td>
					<td class="W-10">主队</td>
					<td class="W-10">VS</td>
					<td class="W-10">客队</td>
					<td colspan="3" class="W-10">队名</td>
				</tr>
			</thead>
			<tbody>

				<tr class="text-c">

					<td class="text-c"><input type="text" class="input-text"
						id="HTeamregular" value="${detailsList['R_REGULAR_H_T_S']}"
						onblur="checkNum(this)"></td>
					<td class="text-c">:</td>
					<td class="text-c"><input type="text" class="input-text"
						id="VTeamregular" value="${detailsList['R_REGULAR_V_T_S']}"
						onblur="checkNum(this)"></td>

					<td class="text-c"><input type="text" class="input-text"
						id="HTeamExtra" value="${detailsList['R_OVERTIME_H_T_S']}"
						onblur="checkNum(this)"></td>
					<td class="text-c">:</td>
					<td class="text-c"><input type="text" class="input-text"
						id="VTeamExtra" value="${detailsList['R_OVERTIME_V_T_S']}"
						onblur="checkNum(this)"></td>

					<td class="text-c"><input type="text" class="input-text"
						id="HTeamPenalty" value="${detailsList['R_PENA_H_T_S']}"
						onblur="checkNum(this)"></td>
					<td class="text-c">:</td>
					<td class="text-c"><input type="text" class="input-text"
						id="VTeamPenalty" value="${detailsList['R_PENA_V_T_S']}"
						onblur="checkNum(this)"></td>

					<td class="text-c"><input type="text" class="input-text"
						id="winTeam" onblur="checkTeam(this)"
						value="${detailsList['R_WIN_T']}"></td>

				</tr>
			</tbody>
		</table>
		<h4>参赛队员名单：</h4>
		<table id="Player_list"
			class="table table-border table-bordered mt-20 mb-20">
			<thead>
				<tr>
					<th colspan="3" class="text-c">主队</th>
					<th colspan="3" class="text-c">客队</th>
				</tr>
				<tr>
					<th class="text-c">队员姓名</th>
					<th class="text-c">位置</th>
					<th class="text-c">球衣号码</th>
					<th class="text-c">队员姓名</th>
					<th class="text-c">位置</th>
					<th class="text-c">球衣号码</th>
				</tr>
			</thead>
			<tbody id="player_roles">
				<c:forEach var="Hplayer" items="${PlayerLists}">
					<tr>
						<td class="text-c"><input type="text" class="input-text"
							value="${Hplayer['HTeamPlayerName']}" disabled placeholder="输入球号显示"></td>
						<td class="text-c"><span class="select-box"> <select
								class="select" size="1" name="demo1">
									<c:choose>
										<c:when test="${Hplayer['HTeamPlayerRole']=='守门员'}">
											<option value="1" selected>守门员</option>
											<option value="2">后卫</option>
											<option value="3">中场</option>
											<option value="4">前锋</option>
										</c:when>
										<c:when test="${Hplayer['HTeamPlayerRole']=='后卫'}">
											<option value="1">守门员</option>
											<option value="2" selected>后卫</option>
											<option value="3">中场</option>
											<option value="4">前锋</option>
										</c:when>
										<c:when test="${Hplayer['HTeamPlayerRole']=='中场'}">
											<option value="1">守门员</option>
											<option value="2">后卫</option>
											<option value="3" selected>中场</option>
											<option value="4">前锋</option>
										</c:when>
										<c:when test="${Hplayer['HTeamPlayerRole']=='前锋'}">
											<option value="1">守门员</option>
											<option value="2">后卫</option>
											<option value="3">中场</option>
											<option value="4" selected>前锋</option>
										</c:when>
										<c:otherwise>
											<option value="1" selected>守门员</option>
											<option value="2">后卫</option>
											<option value="3">中场</option>
											<option value="4">前锋</option>
										</c:otherwise>
									</c:choose>

							</select>
						</span></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${Hplayer['HTeamPlayerNum']}" onblur="checkNum(this),checkPlayersNums(this,0)"></td>

						<td class="text-c"><input type="text" class="input-text"
							value="${Hplayer['VTeamPlayerName']}" disabled placeholder="输入球号显示"></td>
						<td class="text-c"><span class="select-box"> <select
								class="select" size="1" name="demo1">
									<c:choose>
										<c:when test="${Hplayer['VTeamPlayerRole']=='守门员'}">
											<option value="1" selected>守门员</option>
											<option value="2">后卫</option>
											<option value="3">中场</option>
											<option value="4">前锋</option>
										</c:when>
										<c:when test="${Hplayer['VTeamPlayerRole']=='后卫'}">
											<option value="1">守门员</option>
											<option value="2" selected>后卫</option>
											<option value="3">中场</option>
											<option value="4">前锋</option>
										</c:when>
										<c:when test="${Hplayer['VTeamPlayerRole']=='中场'}">
											<option value="1">守门员</option>
											<option value="2">后卫</option>
											<option value="3" selected>中场</option>
											<option value="4">前锋</option>
										</c:when>
										<c:when test="${Hplayer['VTeamPlayerRole']=='前锋'}">
											<option value="1">守门员</option>
											<option value="2">后卫</option>
											<option value="3">中场</option>
											<option value="4" selected>前锋</option>
										</c:when>
										<c:otherwise>
											<option value="1" selected>守门员</option>
											<option value="2">后卫</option>
											<option value="3">中场</option>
											<option value="4">前锋</option>
										</c:otherwise>
									</c:choose>

							</select></span></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${Hplayer['VTeamPlayerNum']}" onblur="checkNum(this),checkPlayersNums(this,1)"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btn-group text-c">
			<span class="btn btn-primary radius" onclick="addplayer();"><i
				class="Hui-iconfont">&#xe600;</i>添加队员</span> <span
				class="btn btn-warning radius" onclick="removeplayer();"><i
				class="Hui-iconfont">&#xe6a1;</i>删除队员</span>
		</div>

		<h4>替补队员名单：</h4>
		<table class="table table-border table-bordered mt-20 mb-20"
			id="Alternate_list">
			<thead>
				<tr>
					<th colspan="5" class="text-c">主队</th>
					<th colspan="5" class="text-c">客队</th>
				</tr>
				<tr>
					<th class="text-c">进场队员姓名</th>
					<th class="text-c">号码</th>
					<th class="text-c">出场队员姓名</th>
					<th class="text-c">号码</th>
					<th class="text-c">时间</th>
					<th class="text-c">进场队员姓名</th>
					<th class="text-c">号码</th>
					<th class="text-c">出场队员姓名</th>
					<th class="text-c">号码</th>
					<th class="text-c">时间</th>
				</tr>
			</thead>
			<tbody id="AlternatePlayer">
				<c:forEach var="ALter" items="${AlterLists}">
					<tr>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['HInName']}" disabled placeholder="输入球号显示"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['HInNum']}" onblur="checkNum(this),checkPlayersNums(this,2)"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['HOutName']}" disabled placeholder="输入球号显示"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['HOutNum']}" onblur="checkNum(this),checkPlayersNums(this,3)"></td>
						<td class="text-c"><input type="text"
							class="input-text calendar" value="${ALter['HTime']}"
							name="calendar" onchange="checkTime(this)"></td>

						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['VInName']}" disabled placeholder="输入球号显示"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['VInNum']}" onblur="checkNum(this),checkPlayersNums(this,4)"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['VOutName']}" disabled placeholder="输入球号显示"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${ALter['VOutNum']}" onblur="checkNum(this),checkPlayersNums(this,5)"></td>
						<td class="text-c"><input type="text"
							class="input-text calendar" value="${ALter['VTime']}"
							name="calendar" onchange="checkTime(this)"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btn-group text-c">
			<span class="btn btn-primary radius" onclick="addAlternate();"><i
				class="Hui-iconfont">&#xe600;</i>添加队员</span> <span
				class="btn btn-warning radius" onclick="removeAlternate();"><i
				class="Hui-iconfont">&#xe6a1;</i>删除队员</span>
		</div>




		<h4>进球队员：</h4>
		<table class="table table-border table-bordered mt-20 mb-20"
			   id="InPlayer_list">
			<thead>
			<tr>
				<th colspan="3" class="text-c">主队</th>
				<th colspan="3" class="text-c">客队</th>
			</tr>
			<tr>
				<th class="text-c">姓名</th>
				<th class="text-c">号码</th>
				<th class="text-c">时间</th>

				<th class="text-c">姓名</th>
				<th class="text-c">号码</th>
				<th class="text-c">时间</th>
			</tr>
			</thead>
			<tbody id="InPlayer">
			<c:forEach var="InBall" items="${InBallPlayers}">
				<tr>

					<td class="text-c"><input type="text" class="input-text"
											  value="${InBall['H_PLAYER_NAME']}"
											  disabled placeholder="输入球号显示"></td>
					<td class="text-c"><input type="text" class="input-text"
											  value="${InBall['H_PLAYER_NUM']}" onblur="checkNum(this),checkPlayersNums(this,6)"></td>
					<td class="text-c"><input type="text"
											  class="input-text calendar" value="${InBall['H_TIME']}"
											  name="calendar" onchange="checkTime(this)"></td>

					<td class="text-c"><input type="text" class="input-text"
											  value="${InBall['V_PLAYER_NAME']}"
											  disabled placeholder="输入球号显示"></td>
					<td class="text-c"><input type="text" class="input-text"
											  value="${InBall['V_PLAYER_NUM']}" onblur="checkNum(this),checkPlayersNums(this,7)"></td>
					<td class="text-c"><input type="text"
											  class="input-text calendar" value="${InBall['V_TIME']}"
											  name="calendar" onchange="checkTime(this)"></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="btn-group text-c">
			<span class="btn btn-primary radius" onclick="addInPlayer();"><i
					class="Hui-iconfont">&#xe600;</i>添加队员</span> <span
				class="btn btn-warning radius" onclick="removeInPlayer();"><i
				class="Hui-iconfont">&#xe6a1;</i>删除队员</span>
		</div>


		<h4>警告队员名单：</h4>
		<table class="table table-border table-bordered mt-20 mb-20"
			id="wran_list">
			<thead>
				<tr>
					<th class="text-c">队伍</th>
					<th class="text-c">号码</th>
					<th class="text-c">队员姓名</th>
					<th class="text-c">时间</th>
					<th class="text-c">原因</th>
				</tr>
			</thead>
			<tbody id="WranPlayer">
				<c:forEach var="warnLists" items="${WranList}">
					<tr>
						<c:choose>
							<c:when test="${warnLists['W_TEAM']==0}">
								<td class="text-c"><input type="text" class="input-text"
									value="${HName}" onblur="checkTeam(this)"></td>
							</c:when>
							<c:when test="${warnLists['W_TEAM']==1}">
								<td class="text-c"><input type="text" class="input-text"
									value="${VName}" onblur="checkTeam(this)"></td>
							</c:when>
							<c:otherwise>
								<td class="text-c"><input type="text" class="input-text"
									value="" onblur="checkTeam(this)"></td>
							</c:otherwise>
						</c:choose>
						<td class="text-c"><input type="text" class="input-text"
							value="${warnLists['W_NUM']}" onblur="checkNum(this),checkAllNumberOfNameWran(this)"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${warnLists['W_NAME']}"  disabled placeholder="输入球号显示"></td>
						<td class="text-c"><input type="text"
							class="input-text calendar" value="${warnLists['W_TIME']}"
							name="calendar" onchange="checkTime(this)"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${warnLists['W_REASON']}" onblur="checkReason(this)"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btn-group text-c">
			<span class="btn btn-primary radius" onclick="addWran();"><i
				class="Hui-iconfont">&#xe600;</i>添加队员</span> <span
				class="btn btn-warning radius" onclick="removeWran();"><i
				class="Hui-iconfont">&#xe6a1;</i>删除队员</span>
		</div>
		<h4>罚令出场名单：</h4>
		<table class="table table-border table-bordered mt-20 mb-20"
			id="outReason">
			<thead>
				<tr>
					<th class="text-c">队伍</th>
					<th class="text-c">号码</th>
					<th class="text-c">队员姓名</th>
					<th class="text-c" onchange="checkTime(this)">时间</th>
					<th class="text-c">原因</th>
				</tr>
			</thead>
			<tbody id="OutPlayer">
				<c:forEach var="outLists" items="${OutList}">
					<tr>
							<c:choose>
								<c:when test="${outLists['S_TEAM']==0}">
									<td class="text-c">
									<input type="text" class="input-text" value="${HName}" onblur="checkTeam(this)"></td>
								</c:when>
							<c:when test="${outLists['S_TEAM']==1}">
									<td class="text-c"><input type="text" class="input-text"
									value="${VName}" onblur="checkTeam(this)"></td>
							</c:when>
							<c:otherwise>
								<td class="text-c"><input type="text" class="input-text"
									value="" onblur="checkTeam(this)"></td>
							</c:otherwise>
						</c:choose>
						<td class="text-c"><input type="text" class="input-text"
							value="${outLists['S_NUM']}" onblur="checkNum(this),checkAllNumberOfName(this)"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${outLists['S_NAME']}"  disabled placeholder="输入球号显示"></td>
						<td class="text-c"><input type="text"
							class="input-text calendar" value="${outLists['S_TIME']}"
							name="calendar" onchange="checkTime(this)"></td>
						<td class="text-c"><input type="text" class="input-text"
							value="${outLists['S_REASON']}" onblur="checkReason(this)"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btn-group text-c">
			<span class="btn btn-primary radius" onclick="addOut();"><i
				class="Hui-iconfont">&#xe600;</i>添加队员</span> <span
				class="btn btn-warning radius" onclick="removeOut();"><i
				class="Hui-iconfont">&#xe6a1;</i>删除队员</span>
		</div>
		<h4>
			红牌情况说明：
			<textarea name="" cols="" rows="" class="textarea mt-20 mb-20"
				id="redText" placeholder="请输入本场比赛红牌情况，不超过100字"
				onblur="checkText(this)">${detailsList['R_RED_C_TEXT']}</textarea>
		</h4>
		<h4>
			罚球点球情况说明：
			<textarea name="" cols="" rows="" class="textarea mt-20 mb-20"
				id="wranText" placeholder="请输入本场比赛罚球点球情况，不超过100字"
				onblur="checkText(this)">${detailsList['R_PENA_TEXT']}</textarea>
		</h4>
		<h4>
			严重错漏判情况说明：
			<textarea name="" cols="" rows="" class="textarea mt-20 mb-20"
				id="errorText" placeholder="请输入本场比赛严重错漏判情况，不超过100字"
				onblur="checkText(this)">${detailsList['R_WRONG_TEXT']}</textarea>
		</h4>
		<h4 class="f-l">
			裁判员姓名： <input type="text" class="input-text" id="refName"
				value="${detailsList['R_REFEREE']}">
		</h4>
		<h4 class="f-r">
			裁判员电话： <input type="text" class="input-text" id="refPhone"
				value="${detailsList['R_REFEREE_PHONE']}">
		</h4>
		<input class="btn btn-success radius mt-50 mb-20" type="button"
			onclick="showMessage()" value="提交">
	</div>
	<input type="text" id="info" style="display: none;" name="${type}" />
	<input type="text" id="hName" style="display: none;" name="${HName}" />
	<input type="text" id="vName" style="display: none;" name="${VName}" />
	<input type="text" id="HPlayers" style="display: none;" name="${HTeamPlayers}" />
	<input type="text" id="VPlayers" style="display: none;" name="${VTeamPlayers}" />
	<input type="text" id="startTime" style="display: none;" name="${R_START_TIME}" />
	<input type="text" id="endTime" style="display: none;" name="${R_END_TIME}" />
	<input type="text" id="HTeamNums" style="display: none;" name="${HTeamPlayerNum}" />
	<input type="text" id="VTeamNums" style="display: none;" name="${VTeamPlayerNum}" />
	<input type="hidden" id="csrf" name="_csrf" value="${_csrf.token }">
	<script type="text/javascript" src="${base_path}/resources/js/site/judement.js"></script>
</body>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>
</html>