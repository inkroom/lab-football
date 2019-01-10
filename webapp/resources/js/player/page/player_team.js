function change(path) {
		var id = $("#selecta").val();
		$.ajax({
			type: "post", 
            url: path+"/player/system/player_team/info.action",
            data:{id:$("#team_id").val()},
            dataType:"json", 
            success:function(data) {
				if (data.msg != null) {
                    layer.alert(data.msg);
                    setTimeout(function() {
                        window.location.href = path+"/player/login_view.html";
                    }, 2000);
				} else {
                    if (data.TEAM_NAME == null)
                        $("#TEAM_NAME").text("暂无");
                    else
                        $("#TEAM_NAME").text(data.TEAM_NAME);
                    if (data.TEAM_NUM == null)
                        $("#TEAM_NUM").text("暂无");
                    else
                        $("#TEAM_NUM").text(data.TEAM_NUM);
                    if (data.INTEGRAL == null)
                        $("#INTEGRAL").text("暂无");
                    else
                        $("#INTEGRAL").text(data.INTEGRAL);
                    if (data.TEAM_AFFILIATION == null)
                        $("#TEAM_AFFILIATION").text("暂无");
                    else
                        $("#TEAM_AFFILIATION").text(data.TEAM_AFFILIATION);
                    if (data.TEAM_LEADER == null)
                        $("#TEAM_LEADER").text("暂无");
                    else
                        $("#TEAM_LEADER").text(data.TEAM_LEADER);
                    if (data.COACH_NAME_LIST.length == 0) {
                        $("#COACH_NAME").text("暂无");
                    } else {
                        console.log(data.COACH_NAME_LIST);
                        $("#COACH_NAME").text("");
                        var last = data.COACH_NAME_LIST.length;
                        var cut = 0;
                        for (i in data.COACH_NAME_LIST) {
                            $("#COACH_NAME").append(data.COACH_NAME_LIST[i].COACH_NAME);
                            cut++;
                            if (cut != last)
                                $("#COACH_NAME").append("、");
                        }
                    }
                    if (data.TEAM_BADGE == null)
                        $("#TEAM_IMG").attr('src', path+'/resources/common/uploadimg/img/hu.jpg');
                    else
                        $("#TEAM_IMG").attr('src', path + '/' + data.TEAM_BADGE);
                    $("#MATCH_PLAY").text(data.MATCH_PLAY);
                    $("#BAT_AVG").text(data.BAT_AVG + "%");
                    if (data.TEAM_MEMBER.length == 0) {
                        $("#TEAM_MEMBER").text("暂无")
                    } else {
                        $("#TEAM_MEMBER").text("");
                        var last = data.TEAM_MEMBER.length;
                        var count = 0;
                        for (i in data.TEAM_MEMBER) {
                            $("#TEAM_MEMBER").append(data.TEAM_MEMBER[i].A_NAME);
                            count++;
                            if (count != last)
                                $("#TEAM_MEMBER").append("、");
                        }
                    }
                }
				
            },
            error:function(error) {
				console.log(error);
			}
		});
	}