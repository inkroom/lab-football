<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>球队申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球队管理 <span class="c-gray en">&gt;</span> 成员管理<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<form name="Manageform" accept-charset="utf-8" id="Manageform" method="post" action="">
<div class="page-container">
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg  mt-20">
            <thead class="text-c">
            <tr><th colspan="3">教练员管理</th></tr>
            </thead>
            <tbody class="text-c">
            <tr>
                <th>教练员</th>
                <th>照片</th>
                <th>操作</th>
            </tr>
            <c:forEach var="coach" items="${coachlist}">
                <tr>
                    <td colspan="" rowspan="">${coach.getCOACH_NAME()}</td>
                    <td colspan="" rowspan=""><img class="avatar size-XXL radius" onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'" src="${base_path}/${coach.getCOACH_PHOTO()}"></td>
                    <td rowspan=""><span class="btn btn-danger radius" onclick="delc(${coach.getCOACH_ID()});">解雇</span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table class="table table-border table-bordered radius table-bg mt-30">
            <thead class="text-c">
            <tr><th colspan="6">队员管理</th></tr>
            </thead>
            <tbody>
            <tr class="text-c">
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>位置</th>
                <th>手机</th>
                <th>操作</th>
            </tr>
            <c:forEach var="stu" items="${stumap}">
            <tr class="text-c">
            <input type="hidden" name="operate">
                <td>${stu.getPLAYER_NAME()}</td>
                <td>${stu.pAge}</td>
                <td>${stu.pSex}</td>
                <td>${stu.p_POSITION}</td>
                <td>${stu.getPLAYER_PHONE()}</td>
                <td><input type="button" name="delete" class="btn btn-danger radius"  onclick="del(${stu.getP_ID()});" value="删除"></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>
</form>
<!--模态框-->

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/js/bootstrap.js"></script>
<script type="text/javascript">
    var token = "${_csrf.token }";
$(function(){
	if('${message}'!=null && '${message}'!=''){
		layer.msg('${message}',{icon: 0});
		<% session.removeAttribute("message");%>
	}
}); 
	
	
function delc(coach){
		layer.confirm('确认解雇教练么', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			//什么东西的什么属性
	  	$('#Manageform').attr("action", "${base_path }/team/team_manage_delatecoach/"+coach+".action");
			
	  	var tokenParm = $('<input type="hidden" name="_csrf">');
        tokenParm.attr('value', token);
        $("#Manageform").append(tokenParm);
	  	$("#Manageform").submit();
		})
};
		
		
function del(player){
    layer.confirm('确认删除球员么', { btn: ['确认','取消'] //按钮
		}, function(){
			//什么东西的什么属性
	  	$('#Manageform').attr("action", "${base_path }/team/team_manage_delatestu/"+player+".action");

        var tokenParm = $('<input type="hidden" name="_csrf">');
        tokenParm.attr('value', token);
        $("#Manageform").append(tokenParm);
	  	$("#Manageform").submit();
    })


};


</script>

</body>
</html>