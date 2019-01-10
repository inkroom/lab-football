<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
    
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/laypage.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/page.css" />
    
    <!--[if IE 6]>
    <script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>球队申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 机构管理 <span class="c-gray en">&gt;</span> 球队申请 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="${base_path }/org/team_application.html" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
		<form name="Matchform" accept-charset="utf-8" id="Matchform" method="post" action="">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
		
            <a class="text-r f-r">
                <input type="text" id="comName" name="comName"  class="input-text" style="width:350px"  placeholder="请输入要搜索的球队名称"  >
                <button type="button" onclick="selectName();" class="btn btn-success radius"  name=""><i class="Hui-iconfont">&#xe665;</i> 查询</button>
            </a>
        </div>
      </form>  
      
         <c:choose>
            <c:when test="${isClick.num == 1}">
            <table class="table table-border table-bordered table-bg table-hover mt-20">
		            <thead class="text-c">
		            <tr>
		                <th>球队</th>
		                <th>领队</th>
		                <th>所属机构</th>
		                <th>审核状态</th>
		                <th style="">操作</th>
		            </tr>
					</thead>
            			<c:forEach var="application" items="${message }">
            			<tr class="text-c" >
            			<td>${application.TEAM_NAME }</td>
						<td>${application.TEAM_LEADER }</td>
						<c:if test="${application.TEAM_AFFILIATION != null }"><td>${application.TEAM_AFFILIATION }</td></c:if>
						<c:if test="${application.TEAM_AFFILIATION == null || application.TEAM_AUDIT == 0 || application.TEAM_AUDIT == 2}"><td>暂无</td></c:if> 
		               	<c:if test="${application.TEAM_AUDIT == 0}"><td><span class="label label-primary radius">待审核</span></td></c:if>
		               	<c:if test="${application.TEAM_AUDIT == 1}"><td><span class="label label-success radius">审核通过</span></td></c:if>
		               	<c:if test="${application.TEAM_AUDIT == 2}"><td><span class="label label-warning radius">审核未通过</span></td></c:if>
		               	<c:if test="${application.TEAM_AUDIT == 3}"><td><span class="label label-warning radius">删除</span></td></c:if>
		               	<td><button class="btn btn-success radius btn_data_name"  onclick="modaldemo('${application.TEAM_NAME }','${application.TEAM_AUDIT}','${application.TEAM_ID}')">查看详情</button></td>
            		</tr>
            		</c:forEach>
            		</table>
            		<div></div>
        </c:when>
        <c:otherwise>
        			<table class="table table-border table-bordered table-bg table-hover mt-20" ms-controller="for_w">
		            <thead class="text-c">
		            <tr>
		                <th>球队</th>
		                <th>领队</th>
		                <th>所属机构</th>
		                <th>审核状态</th>
		                <th style="">操作</th>
		            </tr>
					</thead>
            		<tbody class="text-c" id="loadData">
	            		
		           </tbody>
        	</table>
        	<div id="page1" ></div>
            </c:otherwise>
	</c:choose>
    </div>
</div>
<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <!--  <div class="modal-header">
                <h3 class="modal-title">比赛详情</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">x</a>
            </div>  -->
            <div class="modal-body">
            <div class="mt-20">
                <%-- <div class="text-c" > ${base_path}/{{value.TEAM_BADGE}}
                    <img id="pic" onerror="javascript:this.src='http://static.h-ui.net/h-ui/images/ucnter/avatar-default.jpg'" class="avatar size-XXL radius " src="">
                </div> --%>
            <table class="table table-border table-bordered table-bg  table-striped mt-20">
                
                <tbody class="text-c" ms-controller="for_w1">
                <tr ms-for="($index,value) in json">
                    <td colspan="4">
                    	<img class="avatar size-XXL radius" ms-attr="{src: basePath+value.TEAM_BADGE}" />
                    </td>
                 </tr>
                <tr>
                    <th>球队名称</th>
                    <th>球队积分</th>
                    <th>所属机构 </th>
                    <th>球队编号</th>
                </tr>
                <tr ms-for="($index,value) in json">
                	<td id="a1">{{value.TEAM_NAME}}</td>
                    <td id="a2">{{value.team_score}}</td> 
                    <td id="a3">{{value.TEAM_AFFILIATION}}</td>
                    <td id="a4">{{value.TEAM_NUM}}</td>
                </tr>
                <tr>
                    <th>比赛场次</th>
                    <th>平均胜率</th>
                    <th>领队 </th>
                    <th>教练员</th>
                </tr>
                <tr ms-for="($index,value) in json">
                	<td id="a5">{{value.team_Match_play}}</td>
                	<td id="a6">{{value.team_win_probability}}</td>
                    <td id="a7">{{value.TEAM_LEADER}}</td>
                    <td id="a8">{{value.A_NAME}}</td>
                </tr>
                </tbody>
                
                <tbody ms-controller="for_w2" class="text-c">
                <tr>
                    <th>队员</th>
                    <th>年龄</th>
                    <th>擅长位置</th>
                    <th>个人积分</th>
                </tr>
               
               
                <tr ms-for="($index,value) in json">
                	<td>{{value.A_NAME}}</td>
                    <td>{{value.Age}}</td>
                    <td>{{value.P_POSITION}}</td>
                    <td>{{value.player_score}}</td>
                </tr>
                
             
               </tbody>
            </table>
    </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success btn_data_name" id="agreeBtn" data-dismiss="modal"  onclick="agree();" aria-hidden="true">同意</button>
                <button class="btn btn-danger btn_data_name" id="refuseBtn" data-dismiss="modal" onclick="disagree();" aria-hidden="true">拒绝</button>
              <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path }/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/organization/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript" src="${base_path}//resources/js/organization/team_application.js"></script>
<script>
var basePath = "${base_path}";
$(function(){
	if('${Msg}'!=null && '${Msg}'!=''){
		layer.alert('${Msg}');
		<% session.removeAttribute("Msg");%>
	}
}); 
		 
			
		var vm2 =avalon.define({
			$id: "for_w2",
		    json:[]
		});
		
		var vm1 =avalon.define({
			$id: "for_w1",
			basePath:basePath+"/",
		    json:[]
		});
		 
	/* 查看详情 */
    function modaldemo(name,TEAM_AUDIT,TEAM_ID){
    	$.ajax({
            url: "${base_path }/org/seeTeamAllApplicattions.action",
            type: "POST",
            dataType: "json",
            data: {"name":name,"TEAM_ID":TEAM_ID},
            success: function (data) {
                console.log(data);
//                if(data.status==="200"){
//                    var json=eval(data);
                    vm1.json =data.jsonstr;
                    vm2.json =data.jsonplayer;
                    $. each(data.jsonstr, function(index, json){
                        $("#modal-demo").modal("show");
                    });
                    //隐藏按钮
                    if(TEAM_AUDIT!=0){
                        $('#agreeBtn').hide();
                        $('#refuseBtn').hide();

                    }else{
                        $('#agreeBtn').show();
                        $('#refuseBtn').show();
                    }
//                }else {
//                    layer.alert(data.msg);
//                }
            },
    		error : function(error) {
    			layer.alert('系统异常!');
    			console.log(error);
    		}
        });  
    }
	
	//通过球队申请
	function agree(){
		
		layer.confirm('您确定同意该球队申请比赛吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			console.log()
	  			$.ajax({
	  	            url: "${base_path }/org/updateTeamStatus.html",
	  	            type: "post",
	  	            dataType: "json",
	  	            data: {},
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
	}
	
	
	//不通过球队申请
	function disagree(){
		layer.confirm('您确定要拒绝该球队申请吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			console.log()
	  			$.ajax({
	  	            url: "${base_path }/org/updateTeamfailerStatus.html",
	  	            type: "post",
	  	            dataType: "json",
	  	            data: {},
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
	}

    
    /* 根据赛事查申请 */
    function selectName (){
    	var comName =  document.getElementById("comName").value;
    	if(comName == "" || comName == null){
    		layer.alert('请输入要查询的球队名称!');
    		return;
    	}
    	if(comName.length<20){
	        $("#Matchform").attr("action", "${base_path }/org/teams_select.html");
	    	$('#Matchform').submit();
	     }else{
	     	layer.alert('球队名过长,查询失败!');
	     	return;
	     }
    	
    }
	
    
</script>
</body>
</html>