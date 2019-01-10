<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8">
<title>赛事公示</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/lib/respond.min.js"></script>
    <script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/laypage.css" />
<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/page.css" />
<!--[if IE 6]>
    <script type="text/javascript" src="${base_path}/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->

<script type="text/javascript">
	function find() {
		var str = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
		var search = $("#search").val().trim();
		if(search==null || search.length==0){
			layer.msg("请输入赛事名！");
			$("#search").val("");
		}else if(str.test(search)==false){
			layer.msg("赛事名只能包含中英文和数字！");
			$("#search").val("");
		}else if(search.length>100){
			layer.msg("赛事名最大长度为100！");
			$("#search").val("");
		}else{
			searchList(search);
		}
	}
	
	function searchList(search) {
		$.ajax({
			data : {"search":search,},
			type : "POST",
			dataType : 'json',
			url : "${pageContext.request.contextPath }/info/searchGame.html",

			success : function(result) {
				if (result.success) {
					if(result.size==0){
						layer.msg("没有此赛事！");
						$("#search").val("");
					}else{
						$("#addjson").empty();
						$("#page1").empty();
						$.each(result.gameAll,function(index,item){
							var _tr=$("<tr>"+
								"<td>"+result.gameAll[index].gameName+"</td>"+
								"<td>"+result.gameAll[index].organization+"</td>"+
								"<td>"+result.gameAll[index].startTime+"</td>"+
								"<td>"+result.gameAll[index].endTime+"</td>"+
								"<td><button class='btn btn-success-outline radius' onClick='modaldemo("+result.gameAll[index].gameNum+")'>查看详情</button></td>"+
							"</tr>")
				        	$("#addjson").append(_tr);
						})
					}
				} else {
					layer.msg("获取数据失败！");
				}
			}
		});
	}
</script>
</head>
<body class="page-container">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 比赛公示 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href='${base_path }/info/gameInfoOrg.html');" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
    <a class="text-r f-r" style="text-decoration: none;">
        <input type="text" class="input-text" style="width:350px" placeholder="请输入赛事名" id="search" name="search">
        <button type="button" class="btn btn-success radius"  name="" onclick="find()">
        	<i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
    </a>
</div>


	<table
		class="table table-border table-bordered table-bg radius table-hover mt-20">
		<thead>
			<tr class="text-c">
				<th>赛事名</th>
				<th>所属机构</th>
				<th>赛事开始时间</th>
				<th>赛事结束时间</th>
				<th>操作</th>
			</tr>

		</thead>

		<tbody class="text-c" id="addjson">
			
		</tbody>
	</table>

	<div id="page1" ></div>

	<!--弹出层-->
	<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title">赛事信息</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true"
						href="javascript:void();">×</a>
				</div>
				<div class="modal-body text-c">
					<table id="detailTable"
						class="table table-border table-bordered table-bg radius table-hover ">
						<tbody class="text-c">
						
						</tbody>
					</table>
					
					<table id="teamTable"
						class="table table-border table-bordered table-bg radius table-hover mt-20">
						<tbody class="text-c">
							
						</tbody>
					</table>
					<!--
               <p> <strong class="f-15">赛事名称：</strong> <label class="f-12">成都东软学院校赛</label></p>
               <p> <strong class="f-15">创建人：</strong> <label class="f-12">成都东软学院校赛</label> </p>
               <p>  <strong class="f-15">所属机构：</strong> <label class="f-12">成都东软学院校赛</label></p>-->
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>



<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script>
    function modaldemo(num) {
		$.ajax({
			data : {"num":num,},
			type : "POST",
			dataType : 'json',
			url : "${pageContext.request.contextPath }/info/gameInfoDetail.html",

			success : function(result) {
				if (result.success) {
					$("#modal-demo").modal("show");
					
					$('#detailTable').children('tbody').empty();
					$('#teamTable').children('tbody').empty();
					
					var str = "<tr><th colspan='4'>赛事详细信息</th></tr><tr class='text-c'><th colspan='2'>赛事名称</th><th colspan='2'>所属机构</th></tr><tr>"+
						"<td colspan='2'>" + result.detail.gameName + "</td>"+
				    	"<td colspan='2'>" + result.detail.organization + "</td>";
				    str += "<tr class='text-c'><th>报名开始时间</th><th>报名截止时间</th><th>赛事开始时间</th><th>赛事结束时间</th></tr><tr>"+
				    "<td>" + result.detail.enrollStartTime + "</td>"+
			    	"<td>" + result.detail.enrollEndTime + "</td>"+
			    	"<td>" + result.detail.startTime + "</td>"+
			    	"<td>" + result.detail.endTime + "</td></tr>";
				    str += "<tr class='text-c'><th>类型</th><th>组别</th><th>赛制</th><th>级别</th></tr><tr>"+
				    	"<td>" + result.detail.type + "</td>"+
				    	"<td>" + result.detail.grade + "</td>"+
				    	"<td>" + result.detail.gro + "</td>"+
				    	"<td>" + result.detail.level + "</td></tr>";
					$('#detailTable').children('tbody').append(str);
					
					var str2 = "<tr><th colspan='4'>参赛球队</th></tr><tr><th>序号</th><th colspan='2'>球队名称</th><th>领队名称</th></tr>";
					$.each(result.team,function(index,item){
						str2+="<tr><td>"+(index+1)+"</td><td colspan='2'>"+result.team[index].teamName+"</td><td>"+result.team[index].leaderName+"</td></tr>"
					})
					$('#teamTable').children('tbody').append(str2);
				} else {
					layer.msg("获取数据失败！");
				}
			}
		});
	}
</script>
<script>
//以下将以jquery.ajax为例，演示一个异步分页
function demo(curr){
	$.ajax({
		url:"listOrg.html",
		type:"POST",
		data:{
			"pageNum":curr||1,
		},
		dataType:"json",
		success:function(data){
			$("#addjson").empty();
			//var json=eval(data);
			var json=eval(data)
			
			$.each(json.pageInfo.list,function(index,item){
				var _tr=$("<tr>"+
					"<td>"+json.pageInfo.list[index].gameName+"</td>"+
					"<td>"+json.pageInfo.list[index].organization+"</td>"+
					"<td>"+json.pageInfo.list[index].startTime+"</td>"+
					"<td>"+json.pageInfo.list[index].endTime+"</td>"+
					"<td><button class='btn btn-success-outline radius' onClick='modaldemo("+json.pageInfo.list[index].gameNum+")'>查看详情</button></td>"+
				"</tr>")
	        	$("#addjson").append(_tr);
			})
			 //此处仅仅是为了演示变化的内容
		    //var demoContent = (new Date().getTime()/Math.random()/1000)|0;
		    //document.getElementById('view1').innerHTML = res.content + demoContent;
		    //显示分页
			if(json.pageInfo.pages>1){
			    laypage({
			      cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
			      pages: json.pageInfo.pages, //通过后台拿到的总页数
			      curr: curr || 1, //当前页
			      jump: function(obj, first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			          demo(obj.curr);
			        }
			      }
			    });
		    }
		},
		error:function(er){
			layer.msg("服务器出错，请重试！"+console.log(er));
			console.log(er)
		}
		  });
		};
//运行
demo();

</script>
</html>