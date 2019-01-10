<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
	<title>主页滚动图片</title>
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
	       <h4 class="title">主页滚动图片</h4>
	   	</div>
		<div class="main">
			<table class="table table-hover table-bordered text-center" id="myTab">

				<thead>
					<tr>
						<th>图片</th>
						<th>操作</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
					<tr>
						<form enctype="multipart/form-data" method="post" name="index1" action="${basePath }/school_admin/update_index_head_pic">
							<input type="hidden" name="token" value="${token }">
							<td><img alt="" src="${picPath }${headPic}" height="100" width="200"></td>
							<td><input type="file" name="file" class="input-file form-control"  ></td>
							<td><button type="submit"  class="btn btn-success radius" style="float: center;">保&nbsp&nbsp&nbsp&nbsp存</button></td>
						</form>
					</tr>
				</tbody>
			</table>
		</div>
		
	</div>
	<script type="text/javascript">
		$(function(){
			if('${errorInfo}'!=null && '${errorInfo}'!=''){	
			 layer.alert('${errorInfo}', {
					skin: 'layer-bule-style'
				    ,closeBtn: 0
			  	});
			}
		})
	</script>
</body>
</html>