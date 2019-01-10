function addplayer() {
	$("#Player_list").append("<tr></tr>")
	$("#Player_list tbody tr:last-child")
			.append(
					"<td class='text-c'><input type='text' class='input-text' disabled placeholder='输入球号显示'></td><td><span class='select-box'> <select class='select' size='1' name='demo1'><option value='1'>守门员</option> <option value='2'>后卫</option> <option value='3'>中场</option><option value='3'>前锋</option> </select> </span></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,0)'></td>");
	$("#Player_list tbody tr:last-child")
			.append(
					"<td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td><span class='select-box'> <select class='select' size='1' name='demo1'><option value='1'>守门员</option> <option value='2'>后卫</option> <option value='3'>中场</option><option value='3'>前锋</option> </select> </span></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,1)'></td>");
}
function removeplayer() {
	$("#Player_list tbody tr:last-child").remove();
}
function addAlternate() {
	$("#Alternate_list").append("<tr></tr>")
	$("#Alternate_list tbody tr:last-child")
			.append(
					"<td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,2)'></td><td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,3)'></td><td class='text-c'><input type='text' class='input-text calendar'  name='calendar' onchange='checkTime(this)' ></td><td class='text-c'><input type='text' class='input-text' disabled placeholder='输入球号显示' onblur='checkName(this),checkVTeamNames(this)'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,4)'></td><td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,5)'></td><td class='text-c'><input type='text' class='input-text calendar' name='calendar' onchange='checkTime(this)'></td>");
	$("#Alternate_list tbody ").on("click", function() {
		$("#Alternate_list tbody").off("click");
		/* 处理日期 */
		$(' .calendar').cxCalendar({
			type : 'datetime',
			format : 'YYYY-MM-DD HH:mm:ss'
		});
		/* 选择主队、客队 不能重复 */
	});
	$(' .calendar').cxCalendar({
		type : 'datetime',
		format : 'YYYY-MM-DD HH:mm:ss'
	});
}
function removeAlternate() {
	$("#Alternate_list tbody tr:last-child").remove();
}
function addInPlayer() {
	$("#InPlayer_list").append("<tr></tr>")
	$("#InPlayer_list tbody tr:last-child")
			.append(
					"<td class='text-c'><input type='text' class='input-text' disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,6)'></td><td class='text-c'><input type='text' class='input-text calendar'  name='calendar' onchange='checkTime(this)'> <td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkPlayersNums(this,7)'></td><td class='text-c'><input type='text' class='input-text calendar' name='calendar' onchange='checkTime(this)'></td>");
	$("#InPlayer_list tbody ").on("click", function() {
		$("#InPlayer_list tbody").off("click");
		/* 处理日期 */
		$(' .calendar').cxCalendar({
			type : 'datetime',
			format : 'YYYY-MM-DD HH:mm:ss'
		});
		/* 选择主队、客队 不能重复 */
	});
	$(' .calendar').cxCalendar({
		type : 'datetime',
		format : 'YYYY-MM-DD HH:mm:ss'
	});
}
function removeInPlayer() {
	$("#InPlayer_list tbody tr:last-child").remove();
}
function addWran() {
	$("#wran_list").append("<tr></tr>")
	$("#wran_list tbody tr:last-child")
			.append(
					"<td class='text-c'><input type='text' class='input-text'  onblur='checkTeam(this)'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkAllNumberOfNameWran(this)'></td><td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text' class='input-text calendar'   name='calendar'  onchange='checkTime(this)'  ></td><td class='text-c'><input type='text' class='input-text' onblur='checkReason(this)'></td>");
	$("#wran_list tbody ").on("click", function() {
		$("#wran_list tbody").off("click");
		/* 处理日期 */
		$(' .calendar').cxCalendar({
			type : 'datetime',
			format : 'YYYY-MM-DD HH:mm:ss'
		});
		/* 选择主队、客队 不能重复 */
	});
	$(' .calendar').cxCalendar({
		type : 'datetime',
		format : 'YYYY-MM-DD HH:mm:ss'
	});
}
function removeWran() {
	$("#wran_list tbody tr:last-child").remove();
}
function addOut() {
	$("#outReason").append("<tr></tr>");
	$("#outReason tbody tr:last-child")
			.append(
					"<td class='text-c'><input type='text' class='input-text'  onblur='checkTeam(this)'></td><td class='text-c'><input type='text' class='input-text' onblur='checkNum(this),checkAllNumberOfName(this)'></td><td class='text-c'><input type='text' class='input-text'  disabled placeholder='输入球号显示'></td><td class='text-c'><input type='text'   class='input-text calendar' name='calendar'  onchange='checkTime(this)'  ></td><td class='text-c'><input type='text' class='input-text' onblur='checkReason(this)'></td>");
	$("#outReason tbody ").on("click", function() {
		$("#outReason tbody").off("click");
		/* 处理日期 */
		$(' .calendar').cxCalendar({
			type : 'datetime',
			format : 'YYYY-MM-DD HH:mm:ss'
		});
		/* 选择主队、客队 不能重复 */
	});
	$(' .calendar').cxCalendar({
		type : 'datetime',
		format : 'YYYY-MM-DD HH:mm:ss'
	});
}
function removeOut() {
	$("#outReason tbody tr:last-child").remove();
}
function checkTeam(obj) {
	var hname = document.getElementById("hName").name;
	var vname = document.getElementById("vName").name;
	var winTeam = $(obj).val().replace(/\s/g, "");
	if (winTeam == "" || winTeam == null) {
		layer.msg("不能输入为空");
	} else if (hname != winTeam && vname != winTeam) {
		layer.msg("没有此队伍");
		$(obj).val('');
	}
}
function checkScreenings(obj) {
	var name = $(obj).val().replace(/\s/g, "");
	var regex = /^\d{1,9}$/;
	if (regex.test(name) == false) {
		layer.msg("请输入正确数字,不超过9位");
		$(obj).val('');
	}
}
function checkPlaces(obj) {
	var name = $(obj).val().replace(/\s/g, "");
	var regex = /^[a-zA-Z0-9\u4E00-\u9FA5]+$/;
	if (regex.test(name) == false) {
		layer.msg("只能输入中英文及数字")
		$(obj).val('');
	}

}
function checkName(obj) {
	var name = $(obj).val().replace(/\s/g, "");
	var regex = /^[\u4e00-\u9fa5a-zA-Z]+[\·]{0,1}[\u4e00-\u9fa5a-zA-Z]+$/;
	if (name != null && name != undefined && name != "") {
		if (name.length > 6 || regex.test(name) == false) {
			layer.msg("请输入正确姓名")
			$(obj).val('');
		}
	}
}
function checkReason(obj) {
	var reg = /^(.|\n){0,20}$/;
	var reason = $(obj).val().replace(/\s/g, "");
	if (!reg.test(reason)) {
		layer.msg("请输入20字以内的原因");
		$(obj).val('');
	}
}
function checkText(obj) {
	var reg = /^(.|\n){0,100}$/;
	var reason = $(obj).val().replace(/\s/g, "");
	if (!reg.test(reason)) {
		layer.msg("请输入100字以内的说明");
		$(obj).val('');
	}
}
function checkNum(obj) {
	var num = $(obj).val().replace(/\s/g, "");
	if (!/^[0-9]*$/.test(num)) {
		layer.msg("请输入正确数字!");
		$(obj).val('');
	} else {
		if (num > 99) {
			layer.msg("请输入小于100的正整数!");
			$(obj).val('');
		}
	}

}
function regCheckNames(value, name) {
	var reg = false;
	var pattern = new RegExp(
			"[`~!@#$^&*()=|{}':;'\\[\\]<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < value.length; i++) {
		rs = rs + value.substr(i, 1).replace(pattern, '');
	}
	var result = rs.split(",");
	for (var i = 0; i < result.length; i++) {
		if (name == result[i].replace(/\s/g, "")) {
			reg = true;
		}
	}
	return reg;
}
function checkPlayersNums(obj, number) {
	var name = $(obj).val().replace(/\s/g, "");
	var HNums = document.getElementById("HTeamNums").name;
	var VNums = document.getElementById("VTeamNums").name;
	var hplayers = document.getElementById("HPlayers").name;
	var vplayers = document.getElementById("VPlayers").name;
	switch (number) {
	case 0:
		checkNumerofName(name, HNums, hplayers, obj, 1);
		break;
	case 1:
		checkNumerofName(name, VNums, vplayers, obj, 2);
		break;
	case 2:
		checkNumerofName(name, HNums, hplayers, obj, 3);
		break;
	case 3:
		checkNumerofName(name, HNums, hplayers, obj, 4);
		break;
	case 4:
		checkNumerofName(name, VNums, vplayers, obj, 5);
		break;
	case 5:
		checkNumerofName(name, VNums, vplayers, obj, 6);
		break;
	case 6:
		checkNumerofName(name, HNums, hplayers, obj, 7);
		break;
	case 7:
		checkNumerofName(name, VNums, vplayers, obj, 8);
		break;
	}

}
function checkAllNumberOfName(obj) {
	var isNum = false;
	var error = 0;
	var name = $(obj).val().replace(/\s/g, "");
	var TeamName = $(obj).parent().parent().find("input:eq(0)").val().replace(
			/\s/g, "");
	var hname = document.getElementById("hName").name.replace(/\s/g, "");
	var vname = document.getElementById("vName").name.replace(/\s/g, "");
	var HNums = document.getElementById("HTeamNums").name;
	var VNums = document.getElementById("VTeamNums").name;
	var hplayers = document.getElementById("HPlayers").name;
	var vplayers = document.getElementById("VPlayers").name;

	if (TeamName != null && TeamName != "" && TeamName != undefined) {
		if (TeamName == hname) {
            HNums = HNums.replace("[", "");
            HNums = HNums.replace("]", "");
            hplayers = hplayers.replace("[", "");
            hplayers = hplayers.replace("]", "");
            var result = HNums.split(",");
			var resultName = hplayers.split(",");
			for (var i = 0; i < result.length; i++) {
				if (name == result[i].replace(/\s/g, "")) {
					if(getOut(name)){
                        $(obj).parent().parent().find("input:eq(2)").val(
                            resultName[i].replace(/\s/g, ""));
                        isNum = true;
					}else {
                       error = 1;
					}
				}
			}
		} else if (TeamName == vname) {
            VNums = VNums.replace("[", "");
            VNums = VNums.replace("]", "");
            vplayers = vplayers.replace("[", "");
            vplayers = vplayers.replace("]", "");
			var result = VNums.split(",");
			var resultName = vplayers.split(",");
			for (var i = 0; i < result.length; i++) {

				if (name == result[i].replace(/\s/g, "")) {
                    if(getOut(name)) {
                        $(obj).parent().parent().find("input:eq(2)").val(
                            resultName[i].replace(/\s/g, ""));
                        isNum = true;
                    }else {
                    	error = 1;
					}
				}
			}
		}
		if(error ==1){
			layer.msg("该球员已经下场");
            $(obj).val('');
            $(obj).parent().parent().find("input:eq(2)").val('');
		}else if (isNum == false) {
			layer.msg("该号码不在本场比赛主队人员中，请重新填写");
			$(obj).val('');
			$(obj).parent().parent().find("input:eq(2)").val('');
		}
	} else {
		layer.msg("请先填写队伍");
		$(obj).val('');
		$(obj).parent().parent().find("input:eq(2)").val('');
	}
}
function checkAllNumberOfNameWran(obj) {
    var isNum = false;
    var name = $(obj).val().replace(/\s/g, "");
    var TeamName = $(obj).parent().parent().find("input:eq(0)").val().replace(
        /\s/g, "");
    var hname = document.getElementById("hName").name.replace(/\s/g, "");
    var vname = document.getElementById("vName").name.replace(/\s/g, "");
    var HNums = document.getElementById("HTeamNums").name;
    var VNums = document.getElementById("VTeamNums").name;
    var hplayers = document.getElementById("HPlayers").name;
    var vplayers = document.getElementById("VPlayers").name;

    if (TeamName != null && TeamName != "" && TeamName != undefined) {
        if (TeamName == hname) {
            HNums = HNums.replace("[", "");
            HNums = HNums.replace("]", "");
            hplayers = hplayers.replace("[", "");
            hplayers = hplayers.replace("]", "");
            var result = HNums.split(",");
            var resultName = hplayers.split(",");
            for (var i = 0; i < result.length; i++) {
                if (name == result[i].replace(/\s/g, "")) {
                        $(obj).parent().parent().find("input:eq(2)").val(
                            resultName[i].replace(/\s/g, ""));
                        isNum = true;
                }
            }
        } else if (TeamName == vname) {
            VNums = VNums.replace("[", "");
            VNums = VNums.replace("]", "");
            vplayers = vplayers.replace("[", "");
            vplayers = vplayers.replace("]", "");
            var result = VNums.split(",");
            var resultName = vplayers.split(",");
            for (var i = 0; i < result.length; i++) {
                if (name == result[i].replace(/\s/g, "")) {

                        $(obj).parent().parent().find("input:eq(2)").val(
                            resultName[i].replace(/\s/g, ""));
                        isNum = true;

                }
            }
        }
        if (isNum == false) {
            layer.msg("该号码不在本场比赛主队人员中，请重新填写");
            $(obj).val('');
            $(obj).parent().parent().find("input:eq(2)").val('');
        }
    } else {
        layer.msg("请先填写队伍");
        $(obj).val('');
        $(obj).parent().parent().find("input:eq(2)").val('');
    }
}
function getHTeamPlayers(value) {
	var bl = true;
	var trList = $("#player_roles").children("tr");
	var temp = 0;
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		var number = tdArr.eq(2).find("input").val().replace(/\s/g, "");
		if (number != null && number != undefined && number != "") {
			if (value == number) {
				temp++;
			}
		}
	}
	if (temp != 1) {
		bl = false;
	}
	return bl;
}
function getVTeamPlayers(value) {
	var bl = true;
	var trList = $("#player_roles").children("tr");
	var temp = 0;
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		var number = tdArr.eq(5).find("input").val().replace(/\s/g, "");
		if (value == number) {
			temp++;
		}
	}
	if (temp != 1) {
		bl = false;
	}
	return bl;
}
function getAlternate(value, number) {
	var bl = true;
	var trList = $("#AlternatePlayer").children("tr");
	var temp = 0;
	var sameTemp = 0 ;
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		var num;
		var otherNum;
		switch (number) {
		case 1:
			otherNum = tdArr.eq(3).find("input").val().replace(/\s/g, "");
			num = tdArr.eq(1).find("input").val().replace(/\s/g, "");
			break;
		case 2:
			otherNum = tdArr.eq(1).find("input").val().replace(/\s/g, "");
			num = tdArr.eq(3).find("input").val().replace(/\s/g, "");
			break;
		case 3:
			otherNum = tdArr.eq(8).find("input").val().replace(/\s/g, "");
			num = tdArr.eq(6).find("input").val().replace(/\s/g, "");
			break;
		case 4:
			otherNum = tdArr.eq(6).find("input").val().replace(/\s/g, "");
			num = tdArr.eq(8).find("input").val().replace(/\s/g, "");
			break;
		}
		if(otherNum!=null&&otherNum!=undefined&&otherNum!=""&&otherNum == num){
			sameTemp ++;
		}
		if(value == num){
			temp++;
		}
	}
	if(sameTemp!=0){
		bl = false;
	}
	if (temp != 1) {
		bl = false;
	}
	return bl;
}
function getOut(value){
    var bl = true;
    var trList = $("#OutPlayer").children("tr");
    var temp = 0;
    for (var i = 0; i < trList.length; i++) {
        var tdArr = trList.eq(i).find("td");
        var number = tdArr.eq(1).find("input").val().replace(/\s/g, "");
        if (value == number) {
            temp++;
        }
    }
    if (temp != 1) {
        bl = false;
    }
    return bl;
}
function checkNumerofName(name, HNums, hplayers, obj, num) {
	if (name != null && name != undefined && name != "") {
		var isNumber = false;
		HNums = HNums.replace("[", "");
		HNums = HNums.replace("]", "");
		hplayers = hplayers.replace("[", "");
		hplayers = hplayers.replace("]", "");
		var result = HNums.split(",");
		var resultName = hplayers.split(",");
		for (var i = 0; i < result.length; i++) {
			if (name == result[i].replace(/\s/g, "")) {
				switch (num) {
				case 1:
					if (getHTeamPlayers(name)) {
						$(obj).parent().parent().find("input:eq(0)").val(
								resultName[i].replace(/\s/g, ""));
					} else {
						layer.msg("该球员号已经被填写，无法再次填写");
						$(obj).val('');
						$(obj).parent().parent().find("input:eq(0)").val('');
					}
					break;
				case 2:
					if (getVTeamPlayers(name)) {
						$(obj).parent().parent().find("input:eq(2)").val(
								resultName[i].replace(/\s/g, ""));
					} else {
						layer.msg("该球员号已经被填写，无法再次填写");
						$(obj).val('');
						$(obj).parent().parent().find("input:eq(2)").val('');
					}
					break;
				case 3:
					if (getAlternate(name, 1)) {
						$(obj).parent().parent().find("input:eq(0)").val(
								resultName[i].replace(/\s/g, ""));
					} else {
						layer.msg("该球员无法再次替补上场");
						$(obj).val('');
						$(obj).parent().parent().find("input:eq(0)").val('');
					}

					break;
				case 4:
					if (getAlternate(name, 2)) {
						$(obj).parent().parent().find("input:eq(2)").val(
								resultName[i].replace(/\s/g, ""));
					} else {
						layer.msg("该球员无法再次被替补");
						$(obj).val('');
						$(obj).parent().parent().find("input:eq(2)").val('');
					}
					break;
				case 5:
					if (getAlternate(name, 3)) {
						$(obj).parent().parent().find("input:eq(5)").val(
								resultName[i].replace(/\s/g, ""));
					} else {
						layer.msg("该球员无法再次替补上场");
						$(obj).val('');
						$(obj).parent().parent().find("input:eq(5)").val('');
					}
					break;
				case 6:
					if (getAlternate(name, 4)) {
						$(obj).parent().parent().find("input:eq(7)").val(
								resultName[i].replace(/\s/g, ""));
					} else {
						layer.msg("该球员无法再次被替补");
						$(obj).val('');
						$(obj).parent().parent().find("input:eq(7)").val('');
					}
					break;
				case 7:
					$(obj).parent().parent().find("input:eq(0)").val(
							resultName[i].replace(/\s/g, ""));
					break;
				case 8:
					$(obj).parent().parent().find("input:eq(3)").val(
							resultName[i].replace(/\s/g, ""));
					break;
				}
				isNumber = true;
			}
		}
		if (isNumber == false) {
			layer.msg("该号码不在本场比赛人员中，请重新填写");
			$(obj).val('');
		}
	}
}
function checkVTeamNames(obj) {
	var name = $(obj).val().replace(/\s/g, "");
	var vplayers = document.getElementById("VPlayers").name;
	if (name != null && name != undefined && name != "") {
		if (!regCheckNames(vplayers, name)) {
			layer.msg("球员姓名不存在客队球队中,请重新填写");
			$(obj).val('');
		}
	}
}
function checkHTeamNames(obj) {
	var name = $(obj).val().replace(/\s/g, "");
	var hplayers = document.getElementById("HPlayers").name;
	if (name != null && name != undefined && name != "") {
		if (!regCheckNames(hplayers, name)) {
			layer.msg("球员姓名不存在主队球队中,请重新填写");
			$(obj).val('');
		}
	}
}
function checkAllNames(obj) {
	var name = $(obj).val().replace(/\s/g, "");
	var TeamName = $(obj).parent().parent().find("input:eq(0)").val();
	var hname = document.getElementById("hName").name;
	var vname = document.getElementById("vName").name;
	if (TeamName == hname) {
		var hplayers = document.getElementById("HPlayers").name;
		if (name != null && name != undefined && name != "") {
			if (!regCheckNames(hplayers, name)) {
				layer.msg("球员姓名不存在主队球队中,请重新填写");
				$(obj).val('');
			}
		}
	} else if (TeamName == vname) {
		var vplayers = document.getElementById("VPlayers").name;
		if (name != null && name != undefined && name != "") {
			if (!regCheckNames(vplayers, name)) {
				layer.msg("球员姓名不存在客队球队中,请重新填写");
				$(obj).val('');
			}
		}
	}
}
function checkTime(obj) {
	var ti = true;
	var value = $(obj).val();
	var startTime = document.getElementById("startTime").name;
	var endTime = document.getElementById("endTime").name;
	value = Date.parse(new Date(value));
	startTime = Date.parse(new Date(startTime));
	endTime = Date.parse(new Date(endTime));
	if (value == null || value == undefined || value == "") {
		ti = true;
	} else {
		if (endTime > value) {
			if (value > startTime) {
				ti = true;
			} else {
				ti = false;
			}
		} else {
			ti = false;
		}
	}
	if (ti == false) {
		layer.msg("当前时间不在比赛时间之内，请重新填写");
		$(obj).val('');
	}
}
function checkEmpty(value) {
	if (value == null || value == undefined || value == "") {
		return false;
	} else {
		return true;
	}
}
function checkTeamNum(value) {
	if (value == 1 || value == 0) {
		return true;
	} else {
		return false;
	}
}
function isEmpty() {
	var empty = true;
	var fieldNum = $("#fieldNum").val();
	var gamePlace = $("#gamePlace").val();
	var HTeamregular = $("#HTeamregular").val();
	var VTeamregular = $("#VTeamregular").val();
	var HTeamExtra = $("#HTeamExtra").val();
	var VTeamExtra = $("#VTeamExtra").val();
	var HTeamPenalty = $("#HTeamPenalty").val();
	var VTeamPenalty = $("#VTeamPenalty").val();
	var winTeam = $("#winTeam").val();
	var refPhone = $("#refPhone").val();
	var refName = $("#refName").val();

	var parmas = [ fieldNum, gamePlace, HTeamregular, VTeamregular, HTeamExtra,
			VTeamExtra, HTeamPenalty, VTeamPenalty, winTeam, refName, refPhone ];
	var infos = [ "场序号", "地点", "常规赛比分主队", "常规赛比分客队", "加时赛比分主队", "加时赛比分客队",
			"点球决胜结果主队", "点球决胜结果客队", "获胜队", "裁判员姓名", "裁判员电话" ]
	for (var i = 0; i < parmas.length; i++) {
		if (!checkEmpty(parmas[i])) {
			layer.msg(infos[i] + "有误，请重新填写");
			empty = false;
			break;
		}
	}
	return empty;
}
function validationCHS(value) {
	var regex = /^[\u4e00-\u9fa5a-zA-Z]+[\·]{0,1}[\u4e00-\u9fa5a-zA-Z]+$/;
	if (value == null || value.length == 0 || value.length > 11
			|| regex.test(value) == false) {
		return false;
	} else {
		return true;
	}
}
window.onload = function() {
	$(' .calendar').cxCalendar({
		type : 'datetime',
		format : 'YYYY-MM-DD HH:mm:ss'
	});
	var info = document.getElementById("info").name;
	if (info == "nosession") {
		layer.confirm('当前会话失效，请重新登陆', {
			btn : [ '确定' ]
		// 按钮
		}, function() {
			location.href = "${base_path}/site/login_view.html"
		});
	}
	if (info == "noOpen") {
		layer.confirm('当前比赛还未开始，不能填写裁判书信息', {
			btn : [ '确定' ]
		}, function() {
			window.parent.location.reload();
		});
	} else if (info == "noEnd") {
		layer.confirm('当前比赛还未结束，不能填写裁判书信息', {
			btn : [ '确定' ]
		}, function() {
			window.parent.location.reload();
		});
	} else if(info =="posted"){
        layer.confirm('已经提交过裁判书了，不能填写裁判书信息', {
            btn : [ '确定' ]
        }, function() {
            window.parent.location.reload();
        });
	}
}
function showMessage() {
	var StringHPlayer = [];// 参赛球员名单
	var StringVPlayer = [];// 客队参赛球员
	var StringInHPlayer = [];// 进球主队球员名单
	var StringInVPlayer = [];// 进球客队球员名单
	var StringHAlternate = [];// 替补球员名单
	var StringVAlternate = [];// 客队替补球员名单
	var StringWran = [];// 警告球员名单
	var StringOut = [];// 出场球员名单
	var timeStatus = true;
	StringHPlayer[0] = "www";
	StringVPlayer[0] = "www";
	StringInHPlayer[0] = "www";
	StringInVPlayer[0] = "www";
	StringHAlternate[0] = "www";
	StringVAlternate[0] = "www";
	StringWran[0] = "www";
	StringOut[0] = "www";

	// 遍历参赛球员名单
	var trList = $("#player_roles").children("tr");
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		// 主队球员信息
		var HplayerName = tdArr.eq(0).find("input").val().replace(/\s/g, "");// 主队名
		var HplayerPlace = tdArr.eq(1).find("select").val().replace(/\s/g, "");// 主队位置
		if (HplayerPlace == 1) {
			HplayerPlace = "守门员";
		} else if (HplayerPlace == 2) {
			HplayerPlace = "后卫";
		} else if (HplayerPlace == 3) {
			HplayerPlace = "中场";
		} else if (HplayerPlace == 4) {
			HplayerPlace = "前锋";
		}
		var HplayerNum = tdArr.eq(2).find("input").val().replace(/\s/g, "");// 主队号码
		if (checkEmpty(HplayerName) && checkEmpty(HplayerPlace)
				&& checkEmpty(HplayerNum)) {
			var HPlayers = HplayerName + ";" + HplayerPlace + ";" + HplayerNum;
			StringHPlayer[i + 1] = HPlayers;
		}
		// 客队球员信息
		var VplayerName = tdArr.eq(3).find("input").val().replace(/\s/g, "");// 客队名
		var VplayerPlace = tdArr.eq(4).find("select").val().replace(/\s/g, "");// 客队位置
		if (VplayerPlace == 1) {
			VplayerPlace = "守门员";
		} else if (VplayerPlace == 2) {
			VplayerPlace = "后卫";
		} else if (VplayerPlace == 3) {
			VplayerPlace = "中场";
		} else if (VplayerPlace == 4) {
			VplayerPlace = "前锋";
		}
		var VplayerNum = tdArr.eq(5).find("input").val().replace(/\s/g, "");// 客队号码
		if (checkEmpty(VplayerName) && checkEmpty(VplayerPlace)
				&& checkEmpty(VplayerNum)) {

			var VPlayers = VplayerName + ";" + VplayerPlace + ";" + VplayerNum;
			StringVPlayer[i + 1] = VPlayers;
		}
	}

	// 遍历替补队员名单：
	var trList = $("#AlternatePlayer").children("tr");
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		// 主队球员信息
		var HplayerNameOn = tdArr.eq(0).find("input").val().replace(/\s/g, "");// 主队进场队员姓名
		var HplayerNumOn = tdArr.eq(1).find("input").val().replace(/\s/g, "");// 主队进场队员号码
		var HplayerNameOut = tdArr.eq(2).find("input").val().replace(/\s/g, "");// 主队出场队员姓名
		var HplayerNumOut = tdArr.eq(3).find("input").val().replace(/\s/g, "");// 主队出场队员号码
		var Htime = tdArr.eq(4).find("input").val();// 主队时间
		if (checkEmpty(HplayerNameOn) && checkEmpty(HplayerNumOn)
				&& checkEmpty(HplayerNameOut) && checkEmpty(HplayerNumOut)
				&& checkEmpty(Htime)) {

			var HStringAlternates = HplayerNameOn + ";" + HplayerNumOn + ";"
					+ HplayerNameOut + ";" + HplayerNumOut + ";" + Htime;
			StringHAlternate[i + 1] = HStringAlternates;

		}
		// 客队球员信息
		var VplayerNameOn = tdArr.eq(5).find("input").val().replace(/\s/g, "");// 客队进场队员姓名
		var VplayerNumOn = tdArr.eq(6).find("input").val().replace(/\s/g, "");// 客队进场队员号码
		var VplayerNameOut = tdArr.eq(7).find("input").val().replace(/\s/g, "");// 客队出场队员姓名
		var VplayerNumOut = tdArr.eq(8).find("input").val().replace(/\s/g, "");// 客队出场队员号码
		var Vtime = tdArr.eq(9).find("input").val();// 客队时间
		if (checkEmpty(VplayerNameOn) && checkEmpty(VplayerNumOn)
				&& checkEmpty(VplayerNameOut) && checkEmpty(VplayerNumOut)
				&& checkEmpty(Vtime)) {

			var VStringAlternates = VplayerNameOn + ";" + VplayerNumOn + ";"
					+ VplayerNameOut + ";" + VplayerNumOut + ";" + Vtime;
			StringVAlternate[i + 1] = VStringAlternates;

		}

	}

	// 遍历进球队员名单：
	var trList = $("#InPlayer").children("tr");
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		// 主队球员信息
		var InHPlayerName = tdArr.eq(0).find("input").val().replace(/\s/g, "");// 主队进场队员姓名
		var InHPlayerNum = tdArr.eq(1).find("input").val().replace(/\s/g, "");// 主队进场队员
		var Htime = tdArr.eq(2).find("input").val();// 主队时间
		if (checkEmpty(InHPlayerName) && checkEmpty(InHPlayerNum)
				&& checkEmpty(Htime)) {

			var HStringInPlayer = InHPlayerName + ";" + InHPlayerNum + ";"
					+ Htime;
			StringInHPlayer[i + 1] = HStringInPlayer;
		}

		// 客队球员信息
		var InVPlayerName = tdArr.eq(3).find("input").val().replace(/\s/g, "");// 客队进场队员姓名
		var InVPlayerNum = tdArr.eq(4).find("input").val().replace(/\s/g, "");// 客队进场队员号码
		var Vtime = tdArr.eq(5).find("input").val();// 客队时间

		if (checkEmpty(InVPlayerName) && checkEmpty(InVPlayerNum)
				&& checkEmpty(Vtime)) {

			var VStringInPlayer = InVPlayerName + ";" + InVPlayerNum + ";"
					+ Vtime;
			StringInVPlayer[i + 1] = VStringInPlayer;
		}

	}

	// 遍历警告队员名单：
	var trList = $("#WranPlayer").children("tr");
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		var hname = document.getElementById("hName").name;
		var vname = document.getElementById("vName").name;
		var TeamName = tdArr.eq(0).find("input").val().replace(/\s/g, "");// 队伍
		if (TeamName == hname) {
			TeamName = 0;
		} else if (TeamName == vname) {
			TeamName = 1;
		}
		var TeamNum = tdArr.eq(1).find("input").val().replace(/\s/g, "");// 号码
		var PlayerName = tdArr.eq(2).find("input").val().replace(/\s/g, "");// 队员姓名
		var time = tdArr.eq(3).find("input").val();// 时间
		var reason = tdArr.eq(4).find("input").val().replace(/\s/g, "");// 原因
		if (checkTeamNum(TeamName) && checkEmpty(TeamNum)
				&& checkEmpty(PlayerName) && checkEmpty(time)
				&& checkEmpty(reason)) {

			var StringWrans = TeamName + ";" + TeamNum + ";" + PlayerName + ";"
					+ time + ";" + reason;
			StringWran[i + 1] = StringWrans;
		}
	}

	// 罚令出场名单：
	var trList = $("#OutPlayer").children("tr");
	for (var i = 0; i < trList.length; i++) {
		var tdArr = trList.eq(i).find("td");
		var hname = document.getElementById("hName").name;
		var vname = document.getElementById("vName").name;
		var TeamName = tdArr.eq(0).find("input").val().replace(/\s/g, "");// 队伍
		if (TeamName == hname) {
			TeamName = 0;
		} else if (TeamName == vname) {
			TeamName = 1;
		}
		var TeamNum = tdArr.eq(1).find("input").val().replace(/\s/g, "");// 号码
		var PlayerName = tdArr.eq(2).find("input").val().replace(/\s/g, "");// 队员姓名
		var time = tdArr.eq(3).find("input").val();// 时间
		var reason = tdArr.eq(4).find("input").val().replace(/\s/g, "");// 原因
		if (checkTeamNum(TeamName) && checkEmpty(TeamNum)
				&& checkEmpty(PlayerName) && checkEmpty(time)
				&& checkEmpty(reason)) {

			var StringOuts = TeamName + ";" + TeamNum + ";" + PlayerName + ";"
					+ time + ";" + reason;
			StringOut[i + 1] = StringOuts;

		}
	}

	// 其余输入框的值
	var fieldNum = $("#fieldNum").val().replace(/\s/g, "");
	var gamePlace = $("#gamePlace").val().replace(/\s/g, "");
	var HTeamregular = $("#HTeamregular").val().replace(/\s/g, "");
	var VTeamregular = $("#VTeamregular").val().replace(/\s/g, "");
	var HTeamExtra = $("#HTeamExtra").val().replace(/\s/g, "");
	var VTeamExtra = $("#VTeamExtra").val().replace(/\s/g, "");
	var HTeamPenalty = $("#HTeamPenalty").val().replace(/\s/g, "");
	var VTeamPenalty = $("#VTeamPenalty").val().replace(/\s/g, "");
	var winTeam = $("#winTeam").val().replace(/\s/g, "");

	var winStatus;
	var hname = document.getElementById("hName").name;
	var vname = document.getElementById("vName").name;
	if (winTeam == hname) {
		winStatus = 0;
	} else if (winTeam == vname) {
		winStatus = 1;
	}
	var redText = $("#redText").val().replace(/\s/g, "");
	var wranText = $("#wranText").val().replace(/\s/g, "");
	var errorText = $("#errorText").val().replace(/\s/g, "");
	var refPhone = $("#refPhone").val().replace(/\s/g, "");
	var refName = $("#refName").val().replace(/\s/g, "");

	if (redText.length > 100) {
		document.getElementById("redText").value = document
				.getElementById("redText").value.substring(0, 100);
		redText = document.getElementById("redText").value.substring(0, 100);
	}
	if (wranText.length > 100) {
		document.getElementById("wranText").value = document
				.getElementById("wranText").value.substring(0, 100);
		wranText = document.getElementById("wranText").value.substring(0, 100);
	}
	if (errorText.length > 100) {
		document.getElementById("errorText").value = document
				.getElementById("errorText").value.substring(0, 100);
		errorText = document.getElementById("errorText").value
				.substring(0, 100);
	}
	var regex = /^[1]{1}[3-578]{1}[0-9]{9}$/;
	if (refPhone.length != 11 || regex.test(refPhone) == false) {
		$("#refPhone").val("");
		layer.msg("请输入正确的手机号码（11位）");
	}
	if (refName.length > 40 || validationCHS(refName) == false) {
		$("#refName").val("");
		layer.msg("名字仅支持中英文(点请用·)");
	}
	var parmas = [ fieldNum, gamePlace, HTeamregular, VTeamregular, HTeamExtra,
			VTeamExtra, HTeamPenalty, VTeamPenalty, winStatus, winTeam,
			redText, wranText, errorText, refPhone, refName ];
	if (isEmpty()) {
		layer.confirm('是否提交裁判书？提交后帐号不可再用！', {
			btn : [ '确定提交', '再进行完善' ]
		}, function() {
			var token = $("#csrf").val();
			$.ajax({
				type : "POST",
				url : "insertJudement.action",
				data : {
					"HPlayer" : StringHPlayer,
                    "VPlayer" : StringVPlayer,
                    "HAlternate" : StringHAlternate,
                    "VAlternate" : StringVAlternate,
                    "InHPlayer" : StringInHPlayer,
                    "InVPlayer" : StringInVPlayer,
                    "Wran" : StringWran,
                    "Out" : StringOut,
                    "parmas" : parmas,
                    "_csrf":token

				},
				traditional : true,
				success : function(data) {
					if(data!=200){
						layer.msg("抱歉,页面已经失效,请重新提交");
					}else{
						layer.confirm('裁判书已经成功上传', {
							btn : [ '返回主页' ]
						}, function() {
							window.parent.location.reload();
						});
					}
				},
				error : function(data) {
					layer.msg("提交失败");
				}
			});
		}, function(){
			layer.msg("请继续完善裁判书相关信息");
		});

	}
}