<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
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
    <title>球员公示</title>
<script type="text/javascript">
	function find() {
		var str = /^(([\u4e00-\u9fa5]+[\·]?)+|([a-zA-Z]+\s?)+)$/;
		var search = $("#search").val().trim();
		if(search==null || search.length==0){
			layer.msg("请输入球员名！");
			$("#search").val("");
		}else if(str.test(search)==false){
			layer.msg("请输入正确的球员名！");
			$("#search").val("");
		}else if(search.length>40){
			layer.msg("球员名最大长度为40！");
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
			url : "${pageContext.request.contextPath }/info/searchPlayer.html",

			success : function(result) {
				if (result.success) {
					if(result.size==0){
						layer.msg("没有此球员！");
						$("#search").val("");
					}else{
						$("#addjson").empty();
						$("#page1").empty();
						$.each(result.playerAll,function(index,item){
							var _tr=$("<tr>"+
								"<td>"+result.playerAll[index].name+"</td>"+
								"<td>"+result.playerAll[index].sex+"</td>"+
								"<td>"+result.playerAll[index].age+"</td>"+
								"<td>"+result.playerAll[index].score+"</td>"+
								"<td><button class='btn btn-success-outline radius' onClick='modaldemo("+result.playerAll[index].id+")'>查看详情</button></td>"+
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

<div class="">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
    <a class="text-r f-r" style="text-decoration: none;">
        <input type="text" class="input-text" style="width:350px" placeholder="请输入球员名" id="search" name="search">
        <button type="button" class="btn btn-success radius"  name="" onclick="find()">
        	<i class="Hui-iconfont">&#xe665;</i> 查询
        </button>
    </a>
</div>

    <table class="table table-border table-bordered table-bg radius table-hover mt-20">
        <thead>
        <tr class="text-c">

            <th>球员</th>
            <th>性别</th>
            <th>年龄</th>
            <th>积分</th>
            <th>操作</th>
        </tr>

        </thead>

        <tbody class="text-c" id="addjson">
        
        </tbody>
    </table>
<div id="page1" ></div>
</div>
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">球员信息</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body text-c">
                <table id="detailTable" class="table table-border table-bordered table-bg radius table-hover mt-20">
                    <tbody class="text-c">
                    
                    </tbody>
                </table>
                
                <table id="scoreTable" class="table table-border table-bordered table-bg radius table-hover mt-20">
                    <tbody class="text-c">
                    
                    </tbody>
                </table>
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
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script>
    function modaldemo(num) {
		$.ajax({
			data : {"num":num,},
			type : "POST",
			dataType : 'json',
			url : "${pageContext.request.contextPath }/info/playerDetail.html",

			success : function(result) {
				if (result.success) {
					$("#modal-demo").modal("show");
					
					$('#detailTable').children('tbody').empty();
					
					$('#scoreTable').children('tbody').empty();
					
					var str = "<tr><td colspan='6' rowspan=''><img class='avatar size-XXL radius' src='${base_path}/"+result.detail.photo+"'></td></tr>"+

                        "<tr class='text-c'><th>姓名</th><th>积分</th><th>性别</th></tr>"+

                        "<tr><td>"+result.detail.name+"</td><td>"+result.detail.score+"</td><td>"+result.detail.sex+"</td></tr>"+
                        
                        "<tr class='text-c'><th>身高(Cm)</th><th>体重（Kg）</th><th>年龄</th></tr>"+
                        
                        "<tr><td>"+result.detail.height+"</td><td>"+result.detail.weight+"</td><td>"+result.detail.age+"</td></tr>"+
                        
                        "<tr class='text-c'><th>擅长位置</th><th>所在学校</th><th>比赛场次</th></tr>"+
                        
                        "<tr><td>"+result.detail.position+"</td><td>"+result.detail.school+"</td><td>"+result.detail.matches+"</td></tr>"+
                        
                        "<tr><th>所属球队</th><td colspan='2'>"+result.detail.team+"</td></tr>";

					$('#detailTable').children('tbody').append(str);
					
					var str2 = "<tr class='text-c'><th colspan='4'>活动及获奖情况</th></tr>"+

                    	"<tr><td>校级</td><td colspan='3'>"+result.detail.classHonor+"</td></tr>"+
                    	
                    	"<tr><td>县级</td><td colspan='3'>"+result.detail.schoolHonor+"</td></tr>"+
                    	
                    	"<tr><td>市级</td><td colspan='3'>"+result.detail.countryHonor+"</td></tr>"+
                    	
                    	"<tr><td>省级</td><td colspan='3'>"+result.detail.cityHonor+"</td></tr>";
                    	
					$('#scoreTable').children('tbody').append(str2);
					
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
		url:"playerList.html",
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
					"<td>"+json.pageInfo.list[index].name+"</td>"+
					"<td>"+json.pageInfo.list[index].sex+"</td>"+
					"<td>"+json.pageInfo.list[index].age+"</td>"+
					"<td>"+json.pageInfo.list[index].score+"</td>"+
					"<td><button class='btn btn-success-outline radius' onClick='modaldemo("+json.pageInfo.list[index].id+")'>查看详情</button></td>"+
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