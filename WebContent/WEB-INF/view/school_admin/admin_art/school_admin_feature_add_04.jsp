<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
	<title>艺术课程</title>
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
	       <h4 class="title">添加艺术课程</h4>
	   	</div>
		<div class="main">
			<form id="hform" enctype="multipart/form-data" action="${basePath }/school_admin/add_feature_04.action" method="post">
				<input id="token" type="hidden" name="token" value="${token}"/>
				<input id="myContent" type="hidden" name="myContent" value=""/>
				<input id="content" type="hidden" name="content" value=""/>
				<table class="table table-hover table-bordered text-center" id="myTab">
					<tbody id="tbody">
						<tr>
							<td>标题</td>
							<td colspan="2"><input type="text" name="title"  id="title" value="${title }" class="input-text form-control"/></td>
						</tr>
						<tr>
							<td>照片</td>
							<td colspan="2"><input type="file" name="file"  class="input-text form-control"/></td>
						</tr>
						<tr class="text-c" height="46px" style="">
							<td>介绍</td>
							<td colspan="2">
								<div id="editor1" style="height:550px;margin: 0px;"><p>${myContent}</p></div>
							</td>
						</tr>
						<tr class="text-c">
							
							<td colspan="3">
								<input style="width:100px;" id="save" class="btn btn-success radius" type="button" onclick="saveContent()" value="保&nbsp;&nbsp;&nbsp;&nbsp;存">
								<input style="width:100px;margin-left:100px;;" id="clear" class="btn btn-success radius" type="button" onclick="clearContent()" value="清&nbsp;&nbsp;&nbsp;&nbsp;空">
							</td>
						</tr>
						
					</tbody>
				</table>
			</form>
		</div>
		
	</div>
	
</body>

<script type="text/javascript">
	var editor = new wangEditor('editor1');
	var flag = true;
	$(function(){
		createEditor("${basePath}/edit/uploadpic/4");
		if('${featureInfo}'!=null && '${featureInfo}'!=''){
			if('${featureInfo}'==1){
				layer.confirm('添加成功', {
					  btn: ['查看艺术课程','继续添加'] //按钮
				}, function(){
					layer.msg('请稍后...',{
						icon:16
					});
					window.location.href = '${basePath }/school_admin/get_feature_list_04';
				});
			}else{
				layer.alert('${featureInfo}', {
					skin: 'layer-bule-style'
				    ,closeBtn: 0
			  	});
			}
    	}
	})
	function saveContent(){
		 $('#content').val(editor.$txt.html());
		 $('#myContent').val(editor.$txt.html());
		 var cotent = $('#content').val();
		 var title = $('#title').val();
		 if(title == null || title.length == 0 || title.length > 16){
			 layer.alert('标题的范围：0-16字', {
					skin: 'layer-bule-style'
				    ,closeBtn: 0
			  	});
		 }else{
			 if(cotent == null || cotent.length == 0 || cotent.length > 1900){
				 layer.alert('内容的范围：0-1000字', {
						skin: 'layer-bule-style'
					    ,closeBtn: 0
				  	});
			 }else{
				 if(flag){
					 flag = false;
					 $("#hform").submit();
				 }
			 }
		 }
	}
	function clearContent(){
		editor.$txt.html('');
	}
</script>
</html>