var base_path = $('#base_path').val();
function closePage() {
	window.location = base_path + '/player/system/player_teamapply.action';
}
function modaldemo() {
	getTeamInfo($('#search_code').val());
}
function applyTeam() {
	$.post(getApplyTeamInfoUrl(), {
		team_id : $('#apply').val()
	}, function(data) {
		var obj = eval('(' + data + ')');
		layer.msg(obj.msg, {
			icon : 0
		});
	});
}
function getTeamInfo(team_num) {
	$.post(getTeamInfoUrl(), {
		team_num : team_num
	}, function(data) {
		var obj = eval('(' + data + ')');
		console.log(obj);
		if (obj.status == 404) {
			layer.msg(obj.msg, {
				icon : 0
			});
		}
		if (obj.status == 200) {
			if (obj.userData.teamRank == 1) {
				$('#team_rank').text("省级");
			}
			if (obj.userData.teamRank == 2) {
				$('#team_rank').text("市级");
			}
			if (obj.userData.teamRank == 3) {
				$('#team_rank').text("县级");
			}
			if (obj.userData.teamRank == 4) {
				$('#team_rank').text("校级");
			}
			if (obj.userData.teamRank == 5) {
				$('#team_rank').text("其它");
			}
			$('#team_name').text(obj.userData.teamName);
			$('#team_winRate').text(obj.userData.winRate);
			$('#team_integral').text(obj.userData.integral);
			$('#team_matchNum').text(obj.userData.matchNum);
			$('#team_affiliation').text(obj.userData.teamAffiliation);
			$('#team_badge').attr("src",
					base_path + "/" + obj.userData.teamBadge);
			$('#apply').attr('value', obj.userData.teamID);
			$("#modal-demo").modal("show");
		}
	});
}
function getApplyTeamInfoUrl() {
	return base_path + '/player/system/applay/team.action';
}
function getTeamInfoUrl() {
	return base_path + '/player/system/applay/getTeamInfo.action';
}
function getTeamList() {
	return base_path + '/player/system/player_teamapplys.action';
}
$(function() {
	$.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click",
			"1")
});
function demo(curr) {
	$.ajax({
		url : getTeamList(),
		type : "GET",
		data : {
			"pageNum" : curr || 1,
		},
		dataType : "json",
		success : function(data) {
			$("#addjson").empty();
			var json = eval(data)
			// 给 json 赋值
			$('#loadData').empty();
			for (var i = 0; i < json.pageInfo.list.length; i++) {
				loadData(json.pageInfo.list[i]);
			}
			// 显示分页
			laypage({
				cont : 'page', // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div
				// id="page1"></div>
				pages : json.pageInfo.pages, // 通过后台拿到的总页数
				curr : curr || 1, // 当前页
				jump : function(obj, first) { // 触发分页后的回调
					if (!first) { // 点击跳页触发函数自身，并传递当前页：obj.curr
						demo(obj.curr);
					}
				}
			});
		},
		error : function(er) {
			layer.msg("服务器出错，请重试！" + console.log(er));
			console.log(er)
		}
	});
};
demo(); 
// 运行
function loadData(value) {
	var level = "";
	switch (value.teamRank) {
	case '1':
		level = "省级";
		break;
	case '2':
		level = "市级";
		break;
	case '3':
		level = "县级";
		break;
	case '4':
		level = "校级";
		break;
	case '5':
		level = "其它";
	}
	var status = "";
	switch (value.teamPlayerStatus) {
	case '0':
		status = '<span class="label label-primary radius">待审核</span>';
		break;
	case '1':
		status = '<span class="label label-success radius">审核通过</span>';
		break;
	case '2':
		status = '<span class="label label-warning radius">审核未通过</span>';
		break;
	case '3':
		status='<span class="label label-warning radius">被解雇</span>';
	}
	$('#loadData')
			.append(
					'<tr>' + '<td colspan="" rowspan="2" width="15%">'
							+ value.teamName
							+ '</td>'
							+ '<td colspan="" rowspan="2" width="15%">'
							+ '	<img class="avatar size-XXL radius" id="img_teamBadge" src="'
							+ base_path + '/' + value.teamBadge + '">'
							+ '</td>' + '<td width="10%">所属机构:</td>'
							+ '<td width="10%">' + value.teamAffiliation
							+ '</td>' + '<td width="10%">球队积分:</td>'
							+ '<td width="10%">' + value.integral + '</td>'
							+ '<td width="10%">球队等级:</td>'
							+ ' <td width="10%">'
							+ '	<span v-if="value.teamRank==1">' + level
							+ '</span>' + '</td>'
							+ '<td width="15%" rowspan="2">' + status + '</td>'
							+ '</tr>' + '<tr>' + '<td>比赛场次:</td>' + '<td>'
							+ value.matchNum + '</td>' + '<td>平均胜率:</td>'
							+ '<td>' + value.winRate + '</td>'
							+ '<td width="10%">申请时间:</td>' + '<td>'
							+ value.applyTime + '</td>' + '</tr>');
}