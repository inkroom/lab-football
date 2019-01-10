<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>管理滚动广告位</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit"/> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <link href="${basePath }/resources/lib/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath }/resources/css/index.css" rel="stylesheet">
    <script src="${basePath }/resources/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
    <link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.css" rel="stylesheet">
    <link href="${basePath }/resources/lib/js/layer/2.1/skin/layer.ext.css" rel="stylesheet">
</head>
<style>
.main table tr th {
	text-align: center;
}

.main table tr td {
	text-align: center;
}

.container{
    padding-left: 60px;
    padding-right: 60px;
    padding-top:30px;
    padding-bottom: 30px;
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



a:hover{text-decoration:none;}
.btn_addPic{
	display: block;
	position: relative;
	width: 140px;
	height: 39px;
	overflow: hidden;
	border: 1px solid #EBEBEB;
	border-radius:5px;
	background: none repeat scroll 0 0 #fff;
	color: #999999;
	cursor: pointer;
	text-align: center;
}
.btn_addPic span{display: block;line-height: 40px;}
.btn_addPic em {
	background:url(http://p7.qhimg.com/t014ce592c1a0b2d489.png) 0 0;
	display: inline-block;
	width: 18px;
	height: 18px;
	overflow: hidden;
	margin: 10px 5px 10px 0;
	line-height: 20em;
	vertical-align: middle;
}
.btn_addPic:hover em{background-position:-19px 0;}
.filePrew {
	display: block;
	position: absolute;
	top: 0;
	left: 0;
	width: 140px;
	height: 39px;
	font-size: 100px; /* 增大不同浏览器的可点击区域 */
	opacity: 0; /* 实现的关键点 */
	filter:alpha(opacity=0);/* 兼容IE */
}
</style>
<body>
<input type="hidden" name="tokenP" id="tokenP" value="${token }">
<div class="container">
<div class="main">
	<div class="col-xs-12 text-center" style="margin-bottom: 20px;">
	       <h3 class="title">管理滚动广告位</h3>
	</div>
	<form id="form1" class="form" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="token" value=""/>
		<input type="hidden" name="type" value="1"/>
		<input type="hidden" name="position" value="1"/>
		<input type="hidden" name="pic_num" value="1"/>
		<input type="hidden" name="school_url" value=""/>
		<table id="add1" class="table table-border table-bordered table-hover table-bg table-sort" style="margin-bottom:10px;">
			<thead>
				<tr class="text-c" height="46px">
					<th colspan="2" style="background-color:rgb(183, 219, 119);">左边第一张广告</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td style="font-weight:bold;background-color:#F0F1EE;">标题：</td>
					<td>
						<input onblur="" value="" type="text" class="input-text form-control" id="title" name="title">
					</td>
				</tr>
				<tr class="text-c">
					<td style="font-weight:bold;background-color:#F0F1EE;">链接：</td>
					<td>
						<input onblur="" value="" type="text" class="input-text form-control" id="url_path" name="url_path">
					</td>
				</tr>
				<tr class="text-c">
					<td style="font-weight:bold;background-color:#F0F1EE;">图片：</td>
					<td class="text-l">
			            <a class=btn_addPic href="javascript:void(0);">
			            <span><em>+</em>添加图片</span>
			            <input id="filePrew" class="filePrew" title="支持jpg、jpeg、gif、png格式，文件小于5M" tabIndex="3" type="file" size="3" name="pic">
			            </a>
					</td>
				</tr>
				<tr class="text-c">
					<td class="text-c" colspan="2"><button type="button" onclick="alertInfo();" class="btn btn-success radius">提交</button></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</div>
</body>
<script src="${basePath }/resources/lib/js/bootstrap.js" type="text/javascript"></script>
<script src="${basePath }/resources/lib/js/layer/2.1/layer.js"></script>
<script type="text/javascript">
document.getElementById("filePrew").on("change","input[type='file']",function(){
	var filePath=$(this).val();
    alert("53465::::::filePath = "+filePath);
    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
        $(".fileerrorTip").html("").hide();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $(".showFileName").html(fileName);
    }else{
        $(".showFileName").html("");
        $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
        return false 
    }
})
</script>
</html>