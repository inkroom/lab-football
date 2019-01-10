<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
<meta charset="utf-8">
<title>添加学校管理</title>
<link href="${basePath }/resources/lib/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon" />
<link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.css"
	rel="stylesheet">
<link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.ext.css"
	rel="stylesheet">
<link href="${basePath }/resources/css/right.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<style>
.main table tr {
	background-color: #ffffff;
}

.main table tr th {
	text-align: center;
}

.main table tr td {
	text-align: center;
}

.container{
    padding: 20px;
}
.page-container {
	margin-top: 50px;
}

.control-label {
	text-align: center;
	line-height: 30px;
}

.td {
	layout-flow: vertical-ideographic;
}
</style>
<body>
	<div class="container">
		<div class="col-xs-12 text-center">
	       <h4 class="title">课程管理</h4>
	   	</div>
		<div class="main">
				<table class="table table-hover table-bordered text-center" id="myTab">
					<thead>
						<tr>
							<th>学校账号</th>
							<th>学校名称</th>
							<th>学校类型</th>
							<td>访问地址</td>
							<td>状&nbsp&nbsp&nbsp&nbsp态</td>
							<td>操&nbsp&nbsp&nbsp&nbsp作</td>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${allSchool }" var="t_school" varStatus="vs">  
							<tr>
								<td>${t_school.username }</td>
								<td>${t_school.school_name }</td>
								<td>
									<c:if test="${t_school.school_type == 1 }">小学</c:if>
									<c:if test="${t_school.school_type == 2 }">初中</c:if>
									<c:if test="${t_school.school_type == 3 }">高中</c:if>
								</td>
								<td>${t_school.school_url }</td>
								<td>
									<c:if test="${t_school._status == 0 }">保留</c:if>
									<c:if test="${t_school._status == 1 }">启用</c:if>
									<c:if test="${t_school._status == 2 }">禁用</c:if>
								</td>
								<td>
									<c:if test="${t_school._status == 0 }"><button type="button" name='${t_school.username }' id='${t_school.school_url }' class="btn btn-success radius" onclick="reStart(this)">启&nbsp&nbsp&nbsp&nbsp用</button></c:if>
									<c:if test="${t_school._status == 1 }"><button type="button" name='${t_school.username }' id='${t_school.school_url }' class="btn btn-success radius" onclick="reStop(this)">禁&nbsp&nbsp&nbsp&nbsp用</button></c:if>
									<c:if test="${t_school._status == 2 }"><button type="button" name='${t_school.username }' id='${t_school.school_url }' class="btn btn-success radius" onclick="reStart(this)">启&nbsp&nbsp&nbsp&nbsp用</button></c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<div class="col-sm-12 text-center">
				<nav><ul class="pagination pagination">${pageCode }</ul></nav>
			</div>
		</div>
	</div>
</body>
<script src="${basePath }/resources/lib/js/jquery-2.1.4.js"></script>
<script src="${basePath }/resources/lib/js/bootstrap.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/extend/layer.ext.js"></script>
<script type="text/javascript">
	function reStart(school){
		layer.confirm('是否确认重新用该学校？', {
			  btn: ['启用','取消'] //按钮
		}, function(){
			var username = school.name;
			var url = school.id;
			layer.msg('请稍后...',{
				icon:16
			});
			$.ajax({
				type: "post",
				url: "${basePath}/admin_school/restart_school",
				data : {'username': school.name,'schoolUrl':school.id,'token':'${token}'},
				success: function(result){
					result  = eval( "(" + result + ")" );
					if(result.success){
						layer.alert("操作成功", {
							skin: 'layer-bule-style'
						    ,closeBtn: 0
					  	},function(){
					  		window.location.href = '${basePath }/admin_school/get_school/${page }/'+result.token;
					  	});
					}else{
						layer.alert(result.errorInfo, {
							skin: 'layer-bule-style'
						    ,closeBtn: 0
					  	},function(){
					  		window.location.href = '${basePath }/admin_school/get_school/${page }/'+result.token;
					  	});
					}
					
				},
				error: function(data){
					
					layer.alert("操作失败,请重试", {
						skin: 'layer-bule-style'
					    ,closeBtn: 0
				  	},function(){
				  		window.location.href = '${basePath }/teacher/get_school/${page }/${token}';
				  	});
				}
			});
		});
	}
	
	function reStop(school){
		layer.confirm('是否确认禁用该学校？', {
			  btn: ['禁用','取消'] //按钮
		}, function(){
			layer.msg('请稍后...',{
				icon:16
			});
			$.ajax({
				type: "post",
				url: "${basePath}/admin_school/restop_school",
				data : {'username': school.name,'schoolUrl':school.id,'token':'${token}'},
				success: function(result){
					result  = eval( "(" + result + ")" );
					if(result.success){
						layer.alert("操作成功", {
							skin: 'layer-bule-style'
						    ,closeBtn: 0
					  	},function(){
					  		window.location.href = '${basePath }/admin_school/get_school/${page }/'+result.token;
					  	});
					}else{
						layer.alert(result.errorInfo, {
							skin: 'layer-bule-style'
						    ,closeBtn: 0
					  	},function(){
					  		window.location.href = '${basePath }/admin_school/get_school/${page }/'+result.token;
					  	});
					}
					
				},
				error: function(data){
					
					layer.alert("操作失败,请重试", {
						skin: 'layer-bule-style'
					    ,closeBtn: 0
				  	},function(){
				  		window.location.href = '${basePath }/teacher/get_school/${page }/${token}';
				  	});
				}
			});
		});
	}
</script>
</html>