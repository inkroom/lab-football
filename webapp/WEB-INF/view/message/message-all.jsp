<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
<link rel="stylesheet" href="${base_path}/resources/common/laypage/skin/laypage.css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</head>
<body class="page-container">

	<div class="mt-20">
		<table class="table table-border table-bordered mt-20">
			<tr>
				<td class="f-18 text-c ">欢迎使用该系统</td>
			</tr>
		</table>

		<div id="tab_demo" class="HuiTab mt-20">
			<div class="tabBar clearfix">
				<span>已读消息</span><span>系统消息</span><span>个人消息</span><a class="btn btn-success radius r" style="height:30px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
			</div>
			<div class="tabCon" id = "list" ms-controller="listPage">
				<table
					class="table table-border  table-bg th radius table-hover mt-20" >
					<thead>
						<tr class="text-c">
							<th>标题</th>
							<th>状态</th>
							<th>时间<font color="gray">(仅显示三个月内的消息)</font></th>
						</tr>
					</thead>
					<tbody class="text-c"  id="for_w">
							<tr ms-for="policy in list">
							    <td><button class="btn btn-link"   ms-click="modaldemo(policy.ID, policy.UM_TYPE)">{{policy.TITLE}}</button></td>
								<td><font color="green">已读</font></td>
								<td>{{policy.DATE}}</td>
							</tr>
					</tbody>

				</table>
				<div id="page" ></div>
			</div>
			<div class="tabCon"  id = "list1" ms-controller="listPage1">
				<table
					class="table table-border  table-bg th radius table-hover mt-20">
					<thead>
						<tr class="text-c">
							<th>标题</th>
							<th>状态</th>
							<th>时间<a class="btn btn-success radius f-r  size-MINI"  onclick="readAll()">全部设为已读</a></th>
						</tr>
					</thead>
					<tbody class="text-c"  id="for_w1">
							<tr  ms-for="value in list">
								<td><button class="btn btn-link"   ms-click="modaldemo(value.SM_ID, 3)">{{value.SM_TITLE}}</button></td>
								<td><font ms-attr="{id:3+value.SM_ID}"  name="SM_ID"    color="red">未读</font></td>
								<td>{{value.SM_DATE}}</td>
							</tr>
					</tbody>
				</table>
				<div id="page1" ></div>
			</div>
			<div class="tabCon" id = "list2" ms-controller="listPage2">
				<table
					class="table table-border  table-bg th radius table-hover mt-20">
					<thead>
						<tr class="text-c">
							<th>标题</th>
							<th>状态</th>
							<th>时间<a class="btn btn-success radius f-r  size-MINI"  onclick="readAllPerson()">全部设为已读</a></th>
						</tr>
					</thead>
					<tbody class="text-c"  id="for_w2">
							<tr  ms-for="value in list">
								<td><button class="btn btn-link"   ms-click="modaldemo(value.PS_ID, 4)">{{value.PS_TITLE}}</button></td>
								<td><font ms-attr="{id:4+value.PS_ID}"  name="PS_ID" color="red">未读</font></td>
								<td>{{value.PS_DATE}}</td>
							</tr>
					</tbody>
				</table>
				<div id="page2" ></div>
			</div>
		</div>
	</div>
</body>


<!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->

<script type="text/javascript" src="${base_path}/resources/common/laypage/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script>
	$(function() {
		$.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current",
				"click", "1")
	});
 
	function readAll(){
		layer.confirm('确认全部设为已读？',
				function(index){
			 $.ajax({
					type: "get",
					url: "${base_path}/message/allReadMessage.action",
					dataType: 'json',
					success:function(data) {
							window.parent.location.reload(); 
					  },
			    	error:function(error) {
			    		console.log(error);
			  			}
					});
				layer.close(index);
				});
	}
	

	
	function readAllPerson(){
		layer.confirm('确认全部设为已读？',
				function(index){
			 $.ajax({
					type: "get",
					url: "${base_path}/message/allReadMessagePerson.action",
					dataType: 'json',
					success:function(data) {
							window.parent.location.reload(); 
					  },
			    	error:function(error) {
			    		console.log(error);
			  			}
					});
				layer.close(index);
				});
	}
	
	
	
	//已读消息
	var vm = avalon.define({
		 $id: "listPage",
		 list: [],
		 modaldemo:function (n,m) {
			 modaldemo(n,m);
		    },
	     
	});
	function demo(curr) {
		$.ajax({
			url : "${base_path}/message/messageRead.action",
			type : "POST",
			data : {
				"pageNum" : curr || 1,
			},
			dataType : "json",
			success : function(data) {
				var json = eval(data)
				 vm.list=json.pageInfo.list;
				//显示分页
				laypage({
					cont : 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
					pages : json.pageInfo.pages, //通过后台拿到的总页数
					curr : curr || 1, //当前页
					jump : function(obj, first) { //触发分页后的回调
						if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
							demo(obj.curr);
						}
					}
				});
			},
			error : function(er) {
				console.log(er)
			}
		});
	};
	
	
	//系统消息
	var vm1 = avalon.define({
		 $id: "listPage1",
		 list: [],
		 modaldemo:function (n,m) {
			 modaldemo(n,m);
		    },
	      
	});
	function demo1(curr) {
		$.ajax({
			url : "${base_path}/message/messageUnread.action",
			type : "POST",
			data : {
				"pageNum1" : curr || 1,
			},
			dataType : "json",
			success : function(data) {
				var json = eval(data)
				vm1.list = json.pageInfo1.list //给 json 赋值
				//显示分页
				laypage({
					cont : 'page1', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
					pages : json.pageInfo1.pages, //通过后台拿到的总页数
					curr: curr || 1, //当前页
					jump : function(obj, first) {    //触发分页后的回调
						if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
							if(json.number==5){
								demo1();
							}else{
								demo1(obj.curr);
							}
						     
							allReadSMRed();
						}
					}
				});
			},
			error : function(er) {
				console.log(er)
			}
		});
	};
	//个人消息
	var vm2 = avalon.define({
		 $id: "listPage2",
		 list: [],
		 modaldemo:function (n,m) {
			 modaldemo(n,m);
		    },
	});
	function demo2(curr) {
		$.ajax({
			url : "${base_path}/message/messageUnreadPerson.action",
			type : "POST",
			data : {
				"pageNum2" : curr || 1,
			},
			dataType : "json",
			success : function(data) {
				var json = eval(data)
				vm2.list = json.pageInfo2.list //给 json 赋值
				//显示分页
				laypage({
					cont : 'page2', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
					pages : json.pageInfo2.pages, //通过后台拿到的总页数
					curr: curr || 1, //当前页
					jump : function(obj, first) {   //触发分页后的回调
						if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
							if(json.number==5){
								demo2();
							}else{
								demo2(obj.curr);
							}
						    allReadPSRed();
						}
					}
				});
			},
			error : function(er) {
				console.log(er)
			}
		});
	};

	//运行
	
	demo1();
	demo();
	demo2();
	
	 function modaldemo(n,m) {
		
         layer.open({
             type: 2,
             title: '消息',
             shadeClose: true,
             shade: false,
             maxmin: false, //开启最大化最小化按钮
             area: ['600px', '300px'],
             content: '${base_path}/message/message-detail/'+n+'/'+m+'.html'
         });
         
         if(m==3||m==4){
			 var id = m+n;
			 document.getElementById(id).innerText='已读';
			 document.getElementById(id).style.color="green";
			 demo();
		 }
         
		countMessage();
     }
	
	 
	 
	 function allReadSMRed(){
		 var spans=document.getElementsByName("SM_ID");
		 for(var i=0;i<spans.length;i++) {
		 spans[i].innerText='未读';
		 spans[i].style.color='red';
		 }
	 }
	 
	 function allReadPSRed(){
		 var spans=document.getElementsByName("PS_ID");
		 for(var i=0;i<spans.length;i++) {
		 spans[i].innerText='未读';
		 spans[i].style.color='red';
		 }
	 }
	 
	 
	 function countMessage(){
		 $.ajax({
				type: "get",
				url: "${base_path}/message/countMessage.action",
				dataType: 'json',
				success:function(data) {
					var count = data.countMessage;
					window.parent.countMessage(count);
				  },
		    	error:function(error) {
		    		console.log(error);
		  			}
				});
	}
	 
</script>
</html>