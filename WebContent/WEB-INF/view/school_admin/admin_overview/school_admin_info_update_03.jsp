<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
	<title>校园风光</title>
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
	       <h4 class="title">管理校园风光</h4>
	   	</div>
		<div class="main">
			<form id="hform" action="${basePath }/school_admin/update_info_03" method="post">
				<input id="token" type="hidden" name="token" value="${token}"/>
				<input id="content" type="hidden" name="myContent" value=""/>
				<table border="0" style="width:1000px;margin:auto;margin-bottom:10px">
					<tr>
						<td colspan="2"><span>上次修改时间：${infoMap.oper_date }</span></td>
					</tr>
					<tr class="text-c" height="46px" style="">
						<td colspan="2">
							<div id="editor1" style="height:550px;margin: 0px;">${infoMap.content}</div>
						</td>
					</tr>
					<tr class="text-c">
						<td colspan="2">
							<input style="width:100px;" id="save" class="btn btn-success radius" type="button" onclick="saveContent()" value="保&nbsp;&nbsp;&nbsp;&nbsp;存">
							<input style="width:100px;margin-left:100px;;" id="clear" class="btn btn-success radius" type="button" onclick="clearContent()" value="清&nbsp;&nbsp;&nbsp;&nbsp;空">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
	</div>
	
</body>

<script type="text/javascript">
	var editor = new wangEditor('editor1');
	$(function(){
		createEditor("${basePath}/edit/uploadpic/1");
		if('${infoInfo}'!=null && '${infoInfo}'!=''){		
    		layer.alert('${infoInfo}', {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
    	}
	})
	function saveContent(){
		 $('#content').val(editor.$txt.html());
		 var content = $('#content').val();
		 if(content.length==0||content.length>1900){
			 layer.alert('请输入0-1900个字数', {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
			return;
		 }else{
			 $("#hform").submit();
		 }
		 
		 
		 
	}
	function clearContent(){
		editor.$txt.html('');
	}
</script>
</html>