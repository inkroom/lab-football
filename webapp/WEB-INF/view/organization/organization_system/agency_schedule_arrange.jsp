<%@ page import="com.nsu.common.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/skin/green/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
	<link rel="stylesheet" href="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
	<!--[if IE 6]>
	<script type="text/javascript" src="${base_path}/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>赛程安排</title>
	<style type="text/css">
		.height{height:71px;line-height: 71px}
		.w-150{width: 150px;height: 35px}
		.img_logo{width: 50px;height: 50px}
		.input-text{height: 35px;text-align: center;}
		.btn-default:hover a{text-decoration: none;color: black}
	</style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 赛程安排<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="${base_path}/org/${com_id}/${stage}/${st_time} /${end_time}/arrangement_view.html" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container text-c">

	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<a class="text-r f-r">
			<h5>队伍总数:<span id="num">${fn:length(list)}</span></h5>
		</a>
	</div>
	<form  id="myform">
		<table class="table table table-border table-bordered table-bg mt-20" id="match">
			<thead class="text-c">
			<tr class="text-c">
				<th>序号</th>
				<th>主队</th>
				<th>赛程时间</th>
				<th>地区</th>
				<th>客队</th>

			</tr>
			</thead>

			<tbody>

			<tr class="text-c">

				<td class="s_index">1</td>
				<td >
					<img  src="https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496462250&di=22c6032636d747ae3fc0c65f1a517ca5&src=http://pic.58pic.com/58pic/13/80/45/13D58PICMWc_1024.jpg" alt="LOGO" class="img_logo" id="leftpic">

					<select size="1" class="w-150 radius leftselect"  name="R_H_TEAM_ID">

						<%--第一个选项的值--%>
							 <option value="0" selected="selected">请选择</option>
						<%--其余的值--%>
						<c:forEach items="${list}" var="ls"  begin="0" end="${fn:length(list)}" step="1">
							<option value="${ls.TEAM_ID}" >${ls.TEAM_NAME}</option>
						</c:forEach>

					</select>

				</td>

				<td style="padding: 0 auto;">
					<input class="input-text col-md-5 date_a leftstart"  name="R_START_TIME" type="text" readonly	>
					<span class="input-text col-md-1" style="border:none">至</span>
					<input class="input-text col-md-5 date_a rightend"  name="R_END_TIME" type="text" readonly>
				</td>

				<td>
					<input type="text" class="input-text r_region" name="R_REGION" >
				</td>

				<td >
					<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496462250&di=22c6032636d747ae3fc0c65f1a517ca5&src=http://pic.58pic.com/58pic/13/80/45/13D58PICMWc_1024.jpg" alt="LOGO" class="img_logo" id="rightpic">
					<select size="1" class="w-150 radius rightselect" name="R_V_TEAM_ID">
						<option value="0" selected="selected">请选择</option>
						<c:forEach items="${list}" var="ls" begin="0" end="${fn:length(list)}" step="1">
							<option value="${ls.TEAM_ID}">${ls.TEAM_NAME}</option>
						</c:forEach>

					</select>

				</td>
			</tr>


			</tbody>
		</table>
	</form>
	<div class="btn-group mt-30">
		<span class="btn btn-success radius" id="addInfo">添加</span>
		<span class="btn btn-danger radius" id="removeInfo">删除</span>
		<span class="btn btn-default radius"> <a id="subinfo"  >确定</a></span>
		<span class="btn btn-default radius"><a id="returninfo">返回</a></span>
	</div>
</div>
<input type="text" id="base_path" hidden="" value="${base_path}">
<%--后台校验错误信息提示--%>
<input type="text" id="error" hidden="" value="${error}">
<%-- 赛事开始时间--%>
<input type="text" id="st_time" hidden="" value="${st_time}">
<%--赛事结束时间--%>
<input type="text" id="end_time" hidden="" value="${end_time}">

<!--_footer 作为公共模版分离出去-->

<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>

<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script src="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script src="${base_path}/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>

<script>
	var token = '${_csrf.token }';
	/* 添加赛程*/
    $("#addInfo").click(function () {
        $("#match tbody").append("<tr class='text-c'> <td class='s_index'></td> <td > <img src='https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496462250&di=22c6032636d747ae3fc0c65f1a517ca5&src=http://pic.58pic.com/58pic/13/80/45/13D58PICMWc_1024.jpg' alt='LOGO' class='img_logo' id='leftpic'> <select size='1' class='w-150 radius leftselect'  name='R_H_TEAM_ID'><%--第一个选项的值--%><c:forEach items='${list}' var='ls' begin='0' end='0'  > <option>请选择</option> <option value='${ls.TEAM_ID}' >${ls.TEAM_NAME}</option></c:forEach><%--其余的值--%><c:forEach items='${list}' var='ls'  begin='1' end='${fn:length(list)}' step='1'> <option value='${ls.TEAM_ID}' >${ls.TEAM_NAME}</option></c:forEach> </select> </td> <td style='padding: 0 auto;'> <input class='input-text col-md-5 date_a leftstart'  name='R_START_TIME' type='text' readonly ><span class='input-text col-md-1' style='border:none'>至</span><input class='input-text col-md-5 date_a rightend'  name='R_END_TIME' type='text' readonly></td><td><input type='text' class='input-text r_region' name='R_REGION'></td><td > <img src='https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496462250&di=22c6032636d747ae3fc0c65f1a517ca5&src=http://pic.58pic.com/58pic/13/80/45/13D58PICMWc_1024.jpg' alt='LOGO' class='img_logo' id='rightpic'> <select size='1' class='w-150 radius rightselect' name='R_V_TEAM_ID'><c:forEach items='${list}' var='ls' begin='0' end='0'> <option>请选择</option> <option disabled='true' value='${ls.TEAM_ID}' >${ls.TEAM_NAME}</option></c:forEach><c:forEach items='${list}' var='ls' begin='1' end='${fn:length(list)}' step='1'> <option value='${ls.TEAM_ID}'>${ls.TEAM_NAME}</option></c:forEach> </select> </td> </tr>")
    	$(".cxcalendar_lock").remove();
        $(".cxcalendar").remove();
		/*处理日期*/
        $(" .date_a").cxCalendar({
            type: 'datetime',
            format: 'YYYY-MM-DD HH:mm:ss'
        });

		/*动态添加序列号*/
        $(function () {
            var len=($("#match tbody tr").length);
            for(var i=0;i<len;i++){
               // $('table tr:eq('+i+') td:first').text(i);
                $("#match tbody tr").eq(i).find(".s_index").text(i+1);
            }
        })
    });
</script>
<script src="${base_path}/resources/js/organization/page/agency_schedule_arrange.js"></script>
</body>
</html>