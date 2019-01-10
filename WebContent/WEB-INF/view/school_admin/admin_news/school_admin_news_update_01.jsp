<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
	<title>校园新闻</title>
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
	       <h4 class="title">修改校园新闻</h4>
	   	</div>
		<div class="main">
			<form id="hform" action="${basePath }/school_admin/update_news_01" method="post">
				<input type="hidden" name="scId"  id="scId" value="${scId }" class="input-text form-control"/>
				<input id="token" type="hidden" name="token" value="${token}"/>
				<input id="myContent" type="hidden" name="myContent" value=""/>
				<input id="content" type="hidden" name="content" value=""/>
				<table class="table table-hover table-bordered text-center" id="myTab">
					<tbody id="tbody">
						<tr>
							<td>标&nbsp&nbsp题</td>
							<td colspan="2"><input type="text" name="title"  id="title" value="${newsMap.title }" class="input-text form-control"/></td>
						</tr>
						<tr class="text-c" height="46px" style="">
							<td>内&nbsp&nbsp容</td>
							<td colspan="2">
								<div id="editor1" style="height:550px;margin: 0px;">${newsMap.content}</div>
							</td>
						</tr>
						<tr class="text-c">
							
							<td colspan="3">
								<input style="width:100px;" id="save" class="btn btn-success radius" type="button" onclick="saveContent()" value="保&nbsp;&nbsp;&nbsp;&nbsp;存">
								<input style="width:100px;margin-left:100px;;" id="clear" class="btn btn-success radius" type="button" onclick="clearContent()" value="清&nbsp;&nbsp;&nbsp;&nbsp;空">
								<input style="width:100px;margin-left:100px;;" id="clear" class="btn btn-success radius" type="button" onclick="back()" value="返&nbsp;&nbsp;&nbsp;&nbsp;回">
							</td>
						</tr>
						
					</tbody>
				</table>
			</form>
		</div>
		
	</div>
	
</body>

<script type="text/javascript">
	var flag = true;
	var editor = new wangEditor('editor1');
	$(function(){
		createEditor("${basePath}/edit/uploadpic/2");
		if('${newsInfo}'!=null && '${newsInfo}'!=''){		
    		layer.alert('${newsInfo}', {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
    	}
	})
	function saveContent(){
		$('#content').val(editor.$txt.html());
		 $('#myContent').val(editor.$txt.html());
		 var cotent = $('#content').val();
		 var title = $('#title').val();
		 if(title == null || title.length == 0 || title.length > 26){
			 layer.alert('标题的范围：0-26字', {
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
	function back(){
		window.location.href = '${basePath }/school_admin/get_news_list_01.action?page=${page}&token=${token}';
	}
</script>
</html>