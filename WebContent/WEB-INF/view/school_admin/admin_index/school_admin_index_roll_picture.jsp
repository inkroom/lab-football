<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
	<title>主页滚动图片</title>
	<jsp:include page="../common/include.jsp"/>
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
						<form id="index1" enctype="multipart/form-data" method="post" name="index1" action="${basePath }/school_admin/update_index_roll_pic.action">
							<input type="hidden" name="token" value="${token }">
							<input type="hidden" name="picNum" value="${indexRollPicture[0].pic_num}">
							<td><img alt="" src="${picPath }${indexRollPicture[0].save_path}" height="100" width="200"></td>
							<td><input type="file" name="file" class="input-file form-control"  ></td>
							<td><button type="submit" name='${indexRollPicture[0].pic_num}' onclick="adminClass(this);" class="btn btn-success radius" style="float: center;">保&nbsp&nbsp&nbsp&nbsp存</button></td>
						</form>
					</tr>
					
					<tr>
						<form id="index2" enctype="multipart/form-data" method="post" name="index2" action="${basePath }/school_admin/update_index_roll_pic.action">
							<input type="hidden" name="token" value="${token }">
							<input type="hidden" name="picNum" value="${indexRollPicture[1].pic_num}">
							<td><img alt="" src="${picPath }${indexRollPicture[1].save_path}" height="100" width="200"></td>
							<td><input type="file" name="file" class="input-file form-control"  ></td>
							<td><button type="submit" name='${indexRollPicture[1].pic_num}' onclick="adminClass(this);" class="btn btn-success radius" style="float: center;">保&nbsp&nbsp&nbsp&nbsp存</button></td>
						</form>
					</tr>
					<tr>
						<form id="index3" enctype="multipart/form-data" method="post" name="index3" action="${basePath }/school_admin/update_index_roll_pic.action">
							<input type="hidden" name="token" value="${token }">
							<input type="hidden" name="picNum" value="${indexRollPicture[2].pic_num}">
							<td><img alt="" src="${picPath }${indexRollPicture[2].save_path}" height="100" width="200"></td>
							<td><input type="file" name="file" class="input-file form-control"  ></td>
							<td><button type="submit" name='${indexRollPicture[2].pic_num}' onclick="adminClass(this);" class="btn btn-success radius" style="float: center;">保&nbsp&nbsp&nbsp&nbsp存</button></td>
						</form>
					</tr>
					<tr>
						<form id="index4" enctype="multipart/form-data" method="post" name="index4" action="${basePath }/school_admin/update_index_roll_pic.action">
							<input type="hidden" name="token" value="${token }">
							<input type="hidden" name="picNum" value="${indexRollPicture[3].pic_num}">
							<td><img alt="" src="${picPath }${indexRollPicture[3].save_path}" height="100" width="200"></td>
							<td><input type="file" name="file" class="input-file form-control"  ></td>
							<td><button type="submit" name='${indexRollPicture[3].pic_num}' onclick="adminClass(this);" class="btn btn-success radius" style="float: center;">保&nbsp&nbsp&nbsp&nbsp存</button></td>
						</form>
					</tr>
					<tr>
						<form id="index5" enctype="multipart/form-data" method="post" name="index5" action="${basePath }/school_admin/update_index_roll_pic.action">
							<input type="hidden" name="token" value="${token }">
							<input type="hidden" name="picNum" value="${indexRollPicture[4].pic_num}">
							<td><img alt="" src="${picPath }${indexRollPicture[4].save_path}" height="100" width="200"></td>
							<td><input type="file" name="file" class="input-file form-control"  ></td>
							<td><button type="submit" name='${indexRollPicture[4].pic_num}' onclick="adminClass(this);" class="btn btn-success radius" style="float: center;">保&nbsp&nbsp&nbsp&nbsp存</button></td>
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