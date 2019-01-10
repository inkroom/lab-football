<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
<title>发布比赛</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 球队管理 <span class="c-gray en">&gt;</span>
		教练员申请管理 <a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="mt-20">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<a class="text-r f-r">
				<form  accept-charset="utf-8" method="post" >
					<input type="text" class="input-text" style="width: 350px"
						placeholder="请输入教练员名" id="COACH_NAME" name="COACH_NAME"
						value="${COACH_NAME }">
					<button type="submit" class="btn btn-success radius" name="idsub"
						id="idsub">
						<i class="Hui-iconfont">&#xe665;</i> 查询
					</button>
				</form>
			</a>
		</div>

		<table
			class="table table-border table-bordered table-bg table-hover mt-20 ml-30 mr-20" style="width:95%;">
			<thead class="text-c">
				<tr>
					<th>教练员</th>
					<th>性别</th>
					<th>年龄</th>
					<th>申请时间</th>
					<th>审核状态</th>
					<th style="">操作</th>
				</tr>
			</thead>
		    <c:if test="${page.records == null || fn:length(page.records) == 0}">
              <tr class="text-c">
              	<td colspan="7" rowspan="5"><span style="color:#ff0000">无教练员信息</span></td>
              </tr>
        	</c:if>
			<c:forEach var="list" items="${page.records}" >
				<tr class="text-c">
					<td>${list.getCOACH_NAME() }</td>
					<td>${list.getCOACH_SEX() }</td>
					<td>${list.getCOACH_AGE() }</td>
					<td>${list.getTIME() }</td>
					<c:choose>
						<c:when test="${list.getCOACH_STATUS() == '通过' }">
							<td rowspan=""><span class="label label-success radius">${list.getCOACH_STATUS() }</span></td>
						</c:when>
						<c:when test="${list.getCOACH_STATUS()== '未通过'  }">
							<td rowspan=""><span class="label label-danger radius">${list.getCOACH_STATUS() }</span></td>
						</c:when>
						<c:otherwise>
							<td rowspan=""><span class="label label-primary radius">${list.getCOACH_STATUS() }</span></td>
						</c:otherwise>
					</c:choose>

					<td><button class="btn btn-success radius"
							onclick="modaldemo(${list.getCOACH_ID()},'${list.getCOACH_STATUS()}')">查看详情</button></td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${page.totalPageNum > 1}">
 			<%@include file="page.jsp"%>
        </c:if>

	</div>
	
	<!--模态框-->
	<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<!--  <div class="modal-header">
                 <h3 class="modal-title">比赛详情</h3>
                 <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
             </div> -->
				<div class="modal-body">
					<div class="mt-20">						
				    <div class="text-c">
                        <img class="avatar size-XXL radius " onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'"  id="photo" src="">
                    </div>
						<table
							class="table table-border table-bordered table-bg  table-striped mt-20">
							<tbody class="text-c">
								<!-- <tr>
                            <td colspan="4" rowspan=""><img class="avatar size-XXL radius" src="http://static.h-ui.net/h-ui/images/ucnter/avatar-default.jpg"></td>
                         </tr> -->
								<tr>
									<th colspan="2">姓名</th>
									<th colspan="2">性别</th>
									<th colspan="2">年龄</th>
									<th colspan="2">编号</th>
								</tr>
								<tr>
									<td colspan="2"><span id="C_NAME"></span></td>
									<td colspan="2"><span id="COACH_SEX"></span></td>
									<td colspan="2"><span id="COACH_AGE"></span></td>
									<td colspan="2"><span id="COACH_ID"></span></td>
								</tr>

								<tr>
									<td rowspan="5" colspan="2">积分</td>
									<td>校级(分)</td>
									<td><span id="SCHOOL_GRADE"></span></td>
									<td>胜场</td>
									<td><span id="CS_SCHOOL_WIN"></span></td>
									<td>败场</td>
									<td><span id="CS_SCHOOL_LOSE"></span></td>
								</tr>
								<tr>
									<td>县级(分)</td>
									<td><span id="COUNTRY_GRADE"></span></td>
									<td>胜</td>
									<td><span id="CS_COUNTRY_WIN"></span></td>
									<td>败</td>
									<td><span id="CS_COUNTRY_LOSE"></span></td>

								</tr>
								<tr>
									<td>市级(分)</td>
									<td><span id="CITY_GRADE"></span></td>
									<td>胜场</td>
									<td><span id="CS_CITY_WIN"></span></td>
									<td>败场</td>
									<td><span id="CS_CITY_LOSE"></span></td>

								</tr>
								<tr>
									<td>省级(分)</td>
									<td><span id=PRCVINCE_GRADE></span></td>
									<td>胜场</td>
									<td><span id="CS_PRCVINCE_WIN"></span></td>
									<td>败场</td>
									<td><span id="CS_PRCVINCE_LOSE"></span></td>

								</tr>
								<tr>
									<td>其他(分)</td>
									<td><span id=OTHER_GRADE></span></td>
									<td>胜场</td>
									<td><span id="CS_OTHER_WIN"></span></td>
									<td>败场</td>
									<td><span id="CS_OTHER_LOSS"></span></td>

								</tr>
								<tr>
									<td class="active">工作经历</td>
									<td colspan="7" style="text-align:left;height:100px">
										<span id="COACH_EXP"></span>
									</td>
								</tr>
							</tbody>
						</table>
						
					</div>
				</div>
				<div class="modal-footer">
				<button id="agreeBtn" class="btn btn-success" data-dismiss="modal" aria-hidden="true">同意</button>
                <button id="refuseBtn" class="btn btn-danger" data-dismiss="modal" aria-hidden="true">拒绝</button>
                <button id="closeBtn" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" 
	    src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
	<script>
	
	
	$(function(){
		if('${message}'!=null && '${message}'!=''){
			layer.msg('${message}');
			<% session.removeAttribute("message");%>
		}
	}); 

	
    function modaldemo(COACH_ID, COACH_STATUS){
    	$('#COACH_ID').val(COACH_ID);
    	$.ajax({
            url: "${base_path}/team/team_coach_see.action",
            type: "post",
            dataType: "json",
            data: {"COACH_ID":COACH_ID,"COACH_STATUS":COACH_STATUS},
            async: true,
            success: function (data) {
                if(data.status==200){
                	$.each(data.coachMap, function(index, json){
                		$('#C_NAME').html(json.COACH_NAME);
                    	$('#COACH_SEX').html(json.COACH_SEX);
                    	$('#COACH_AGE').html(json.COACH_AGE);
                    	$('#COACH_PHOTO').html(json.COACH_PHOTO);
                    	$('#COACH_ID').html(json.COACH_ID);
                    	$('#CS_PRCVINCE_WIN').html(json.CS_PRCVINCE_WIN);
                    	$('#CS_CITY_WIN').html(json.CS_CITY_WIN);
                    	$('#CS_COUNTRY_WIN').html(json.CS_COUNTRY_WIN);
                    	$('#CS_SCHOOL_WIN').html(json.CS_SCHOOL_WIN);
                    	$('#CS_PRCVINCE_LOSE').html(json.CS_PRCVINCE_LOSE);
                    	$('#CS_CITY_LOSE').html(json.CS_CITY_LOSE);
                    	$('#CS_COUNTRY_LOSE').html(json.CS_COUNTRY_LOSE);
                    	$('#CS_SCHOOL_LOSE').html(json.CS_SCHOOL_LOSS);
                    	$('#PRCVINCE_GRADE').html(json.PRCVINCE_GRADE);
                    	$('#COUNTRY_GRADE').html(json.COUNTRY_GRADE);
                    	$('#SCHOOL_GRADE').html(json.SCHOOL_GRADE);
                    	$('#CITY_GRADE').html(json.CITY_GRADE);
                    	$('#COACH_JOB').html(json.COACH_JOB);
                    	$('#COACH_EXP').html(json.COACH_EXP);
                    	$('#CS_OTHER_WIN').html(json.CS_OTHER_WIN);
                    	$('#CS_OTHER_LOSS').html(json.CS_OTHER_LOSS);
                    	$('#OTHER_GRADE').html(json.OTHER_GRADE);
                    	$('#photo').attr('src', "${base_path}/"+json.COACH_PHOTO);
                        var a = json.COACH_EXP;
                        console.log(a);
                	});
                	//隐藏按钮
                	if(COACH_STATUS != "待审核"){
                		$('#agreeBtn').hide();
                		$('#refuseBtn').hide();
                		
                	}else{
                		$('#agreeBtn').show();
                		$('#refuseBtn').show();
                		
                	}	
                	$("#modal-demo").modal("show") 
                }else{
                	layer.msg(data.message);
                	return false;
                }
            },
    		error : function(req, status, reason) {
    			layer.alert('系统异常,请刷新重试', {
    				skin: 'layer-bule-style'
    			    ,closeBtn: 0
    		  	});
    		}
        });   	
    }
    
    //同意
    $('#agreeBtn').click(function(){
    	layer.confirm('您确定同意该教练员加入您的球队吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			console.log()
	  			$.ajax({
	  	            url: "${base_path}/team/team_coach_one.action",
	  	            type: "post",
	  	            dataType: "json",
	  	            data: {"COACH_ID":$('#COACH_ID').val()},
	  	            success: function (data) {
	  	            	layer.msg(data.message);
	  	                if(data.status==200){
	  	                	layer.alert(data.message, {icon: 6});
	  	                }else{
	  	                	layer.alert(data.message, {icon: 5});
	  	                }
	  	              	setTimeout(function(){
	  	            	 window.location.reload();
                 		},1200);
	  	             
	  	            },
	  	    		error : function(req, status, reason) {
	  	    			layer.alert('系统异常,请刷新重试', {
	  	    				skin: 'layer-bule-style'
	  	    			    ,closeBtn: 0
	  	    		  	});
	  	    			 window.location.reload();
	  	    		}
	  	        });
	  			
	  		},function(){
	  			
	  		});
    });
 	//拒绝
    $('#refuseBtn').click(function(){
    	layer.confirm('您确定拒绝该教练员加入您的球队吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			$.ajax({
	  	            url: "${base_path}/team/team_coach_two.action",
	  	            type: "post",
	  	            dataType: "json",
	  	            data: {"COACH_ID":$('#COACH_ID').val()},
	  	            success: function (data) {
	  	            	if(data.status==200){
	  	                	layer.alert(data.message, {icon: 6});
	  	                }else{
	  	                	layer.alert(data.message, {icon: 5});
	  	                }
	  	            	setTimeout(function(){
		  	            	 window.location.reload();
	                 		},1500);
	  	            },
	  	    		error : function(req, status, reason) {
	  	    			layer.alert('系统异常,请刷新重试', {
	  	    				skin: 'layer-bule-style'
	  	    			    ,closeBtn: 0
	  	    		  	});
	  	    			 window.location.reload();
	  	    		}
	  	        });
	  		}, function(){
	  			
	  		});
    	
    });
    
    

    <!--查找-->
    $("#idsub").click(function(){
	     var value = $("#COACH_NAME").val();
	     if(value.length<10){
		        $("#CoachForm").attr("action", "${base_path}/team/team_coach_view.action");;
		     }else{
		     	layer.msg('查询失败');
		     }
	 
    });
    
    


    
</script>
</body>
</html>