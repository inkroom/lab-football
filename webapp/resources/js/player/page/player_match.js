var base_path=$('#base_path').val();
document.body.onload=getTeamList();
$('.select').change(function(){
	getMatchList($('.select').val());
});

function modaldemo() {
	$("#modal-demo").modal("show")
}
function getTeamListUrl(){
	return base_path+'/player/system/match/getTeam.action';
}
function getMatchListUrl(){
	return base_path+'/player/system/match/getMatch.action';
}
function getMatchInfo(){
	return base_path+'/player/system/match/getMatchInfo.action';
}
function getTeamList(){
	$.get(getTeamListUrl(),{},function(data){
		var myobj=eval(data); 
		for(var i=0;i<myobj.length;i++){ 
			$('.select').append('<option value="'+myobj[i].teamId+'">'+myobj[i].teamName+'</option>');
		}
		if(myobj.length==0){
			$('.select').empty();
			$('.select').append('<option value="" selected>选择你想查看的球队</option>');
		}
	});
}
function getMatchList(team_id){
	$.get(getMatchListUrl(),{teamId:team_id},function(data){
		$('#matchList').empty();
		var myobj=eval(data); 
		if(myobj.length==0){
			layer.msg("没有该球队的比赛信息",{icon:0}); 
		}
		for(var i=0;i<myobj.length;i++){ 
			var newDate=new Date(myobj[i].time.time);
			$('#matchList').append('<tr>'
					+'<td>'+myobj[i].r_name+'</td>'
					+'<td>'+myobj[i].hTeamName+'</td>'
					+'<td>'+myobj[i].vTeamName+'</td>'
					+'<td>'+myobj[i].score+'</td>'
					+'<td>'+newDate.toLocaleString()+'</td>'
					+'<td ><button class="btn btn-success radius" onclick="showPlayerGoal('+myobj[i].r_id+')">查看详情</button></td>'
					+'</tr>');
		} 
		if(myobj.length==0){
			$('#matchList').empty();
		}
	});
}
function showPlayerGoal(r_id){
	$.ajax({
		url:getMatchInfo(),
		type:'GET',
		dataType:'json',
		data:{r_id:r_id},
		success:function(data){
			var team = "主队";
			$("#h_name").text("主队:"+data.H_TEAM_NAME);
			$("#v_name").text("客队:"+data.V_TEAM_NAME);
			$("#h_num").text(data.H_TEAM_NUM);
			$("#h_score").text(data.R_REGULAR_H_T_S);
			$("#v_num").text(data.V_TEAM_NUM);
			$("#v_score").text(data.R_REGULAR_V_T_S);
			//警告
			$("#warning").empty();
			$("#warning").append('<tr>'
                        +'<td width="25%">警告人员姓名</td>'
                        +'<td width="10%">主客队</td>'
                        +'<td width="15%">球员号码</td>'
                        +'<td>原因</td>'
                        +'</tr>');
			for(i in data.RaceWarning){
				if(data.RaceWarning[i].W_TEAM==data.RaceWarning[i].R_V_TEAM_ID){
					team = "客队";
				}
				$("#warning tr:gt(1)").remove();
				$("#warning").append("<tr><td>"+data.RaceWarning[i].W_NAME+"</td><td>"+team+"</td><td>"+data.RaceWarning[i].W_NUM+"</td><td>"+data.RaceWarning[i].W_REASON+"</td></tr>");
			}
			if(data.RaceWarning.length==0){
				$("#warning").append("<tr><td>无</td><td>无</td><td>无</td><td>无</td></tr>");
			}
			//罚出
			$("#sendoff").empty();
			$("#sendoff").append('<tr>'
                    +'<td width="25%">罚出人员姓名</td>'
                    +'<td width="10%">主客队</td>'
                    +'<td width="15%">球员号码</td>'
                    +'<td>原因</td>'
                    +'</tr>');
			for(i in data.RaceSendOff){
				if(data.RaceSendOff[i].W_TEAM==data.RaceSendOff[i].R_V_TEAM_ID){
					team = "客队";
				}
				$("#sendoff tr:gt(1)").remove();
				$("#sendoff").append("<tr><td>"+data.RaceSendOff[i].S_NAME+"</td><td>"+team+"</td><td>"+data.RaceSendOff[i].S_NUM+"</td><td>"+data.RaceSendOff[i].S_REASON+"</td></tr>");
			}
			if(data.RaceSendOff.length==0){
				$("#sendoff").append("<tr><td>无</td><td>无</td><td>无</td><td>无</td></tr>");
			}
			//红牌情况
			for(i in data.RacePunish){
				if(isUndefine(data.RacePunish[i].R_RED_C_TEXT)){
					$("#redp").empty();
					$("#redp").append("<td width='25%'>红牌情况：</td><td>"+data.RacePunish[i].R_RED_C_TEXT+"</td>");
				}
				if(isUndefine(data.RacePunish[i].R_PENA_TEXT)){
					$("#dianqiu").empty();
					$("#dianqiu").append("<td>罚球点球情况：</td><td>"+data.RacePunish[i].R_PENA_TEXT+"</td>");
				}
				if(isUndefine(data.RacePunish[i].R_WRONG_TEXT)){
					$("#cuoloupan").empty();
					$("#cuoloupan").append("<td>严重错漏判情况:</td><td>"+data.RacePunish[i].R_WRONG_TEXT+"</td>");
				}
			}
			if(!isUndefine($("#redp").val())){
				$("#redp").empty();
				$("#redp").append("<td width='25%'>红牌情况：</td><td>无</td>");
			}
			if(!isUndefine($("#dianqiu").val())){
				$("#dianqiu").empty();
				$("#dianqiu").append("<td>罚球点球情况：</td><td>无</td>");
			}
			if(!isUndefine($("#cuoloupan").val())){
				$("#cuoloupan").empty();
				$("#cuoloupan").append("<td>严重错漏判情况:</td><td>无</td>");
			}
			$("#modal-demo").modal("show");
		},
		error:function(){
			$.Huimodalalert('未知错误，请刷新重试！',2000);
		}
	});
	//modaldemo();
}
function isUndefine(str){
	if(str==undefined || str=="" || str==null)return false;
	return true;
}

