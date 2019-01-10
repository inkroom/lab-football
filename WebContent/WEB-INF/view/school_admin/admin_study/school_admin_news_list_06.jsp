<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
	<title>课外练习</title>
	<jsp:include page="../common/include.jsp"></jsp:include>
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
	       <h4 class="title">课外练习</h4>
	   	</div>
		<div class="main">
				<input id="token" type="hidden" name="token" value="${token}"/>
				<input id="content" type="hidden" name="myContent" value=""/>
				<table class="table table-hover table-bordered text-center" id="myTab">
					<thead>
						<tr>
							<td>标&nbsp&nbsp&nbsp&nbsp题</td>
							<td>时&nbsp&nbsp&nbsp&nbsp间</td>
							<td colspan="2">操&nbsp&nbsp&nbsp&nbsp作</td>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${newsList }" var="news" varStatus="vs">  
							<tr>
								<td>${news.title }</td>
								<td>${news.oper_date }</td>
								<td><button type="button"  name='${news.sc_news }' onclick="update_person(this)" class="btn btn-success radius " style="float: center;">修改</button></td>
								<td><button type="button"  name='${news.sc_news }' onclick="delete_person(this)" class="btn btn-success radius " style="float: center;">删除</button></td>
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

<script type="text/javascript">
	var flag = true;
	function update_person(news){
		if(flag){
			flag = false;
			window.location.href = '${basePath }/school_admin/update_news_view_06.action?scId='+news.name+"&token="+'${token}';	
		}
	}
	function delete_person(news){
		if(flag){
			flag = false;
			layer.confirm('是否确认删除该条课外练习', {
				  btn: ['是','否'] //按钮
			}, function(){
				layer.msg('请稍后...',{
					icon:16
				});
				$.ajax({
					type: "post",
					url: "${basePath}/school_admin/delele_news_06",
					data : {'scId': news.name,'token':'${token}'},
					success: function(result){
						result  = eval( "(" + result + ")" );
						if(result.success){
							layer.alert("操作成功", {
								skin: 'layer-bule-style'
							    ,closeBtn: 0
						  	},function(){
						  		window.location.href = '${basePath }/school_admin/get_news_list_06.action?page=${page}&token='+result.token;
						  	});
							
						}else{
							layer.alert(result.errorInfo, {
								skin: 'layer-bule-style'
							    ,closeBtn: 0
						  	},function(){
						  		window.location.href = '${basePath }/school_admin/get_news_list_06.action?page=${page}&token='+result.token;
						  	});
						}
					},
					error: function(data){
						layer.alert(result.errorInfo, {
							skin: 'layer-bule-style'
						    ,closeBtn: 0
					  	},function(){
					  		window.location.href = '${basePath }/school_admin/get_news_list_06.action?page=${page}&token=${token}';
					  	});
					}
				});
			});
		}
	}
</script>
</html>