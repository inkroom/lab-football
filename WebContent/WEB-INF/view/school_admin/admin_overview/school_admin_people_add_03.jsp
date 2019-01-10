	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
	<title>学生风采</title>
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
	       <h4 class="title">添加学生风采</h4>
	   	</div>
		<div class="main">
			<form id="hform" enctype="multipart/form-data" action="${basePath }/school_admin/add_people_03" method="post">
				<input id="token" type="hidden" name="token" value="${token}"/>
				<input id="content" type="hidden" name="myContent" value=""/>
				<table class="table table-hover table-bordered text-center" id="myTab">
					<tbody id="tbody">
						<tr>
							<td>姓名</td>
							<td colspan="2"><input type="text" name="name"  id="name" value="${name }" class="input-text form-control"/></td>
						</tr>
						<tr>
							<td>照片</td>
							<td colspan="2"><input type="file" name="file"  class="input-text form-control"/></td>
						</tr>
						<tr class="text-c" height="46px" style="">
							<td>介绍</td>
							<td colspan="2">
								<div id="editor1" style="height:550px;margin: 0px;"><p></p></div>
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
	$(function(){
		createEditor("${basePath}/edit/uploadpic/3");
		if('${peopleInfo}'!=null && '${peopleInfo}'!=''){
			if('${peopleInfo}'==1){
				layer.confirm('添加成功', {
					  btn: ['查看学生风采','继续添加'] //按钮
				}, function(){
					layer.msg('请稍后...',{
						icon:16
					});
					window.location.href = '${basePath }/school_admin/get_people_list_03';
				});
			}else{
				layer.alert('${peopleInfo}', {
					skin: 'layer-bule-style'
				    ,closeBtn: 0
			  	});
			}
    		
    	}
	})
	function saveContent(){
		 $('#content').val(editor.$txt.html());
		 var content = $('#content').val();
		 var name = $('#name').val();

		 if(content == null || content== '' || name == null || name == ''){
			 layer.alert('请填写完整信息', {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		 }else{
			 if(content.length >1900 || name.length > 10){
				 
			 }else{
				 if(/^[a-zA-z\u4E00-\u9FA5\(^\s+)|(\s+$)/g]*$/.test(name)){
					 $("#hform").submit();
				 }else{
					 layer.alert('请填写规范姓名', {
							skin: 'layer-bule-style'
						    ,closeBtn: 0
					  	});
				 }
				 //$("#hform").submit();
			 } 
		 }

		 
	}
	function clearContent(){
		editor.$txt.html('');
	}
</script>
</html>