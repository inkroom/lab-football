<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css"/>
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>个人信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球队管理<span class="c-gray en">&gt;</span> 我的比赛<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form name="Matchform" accept-charset="utf-8" id="Matchform" method="post" action="">

</form>   
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg  mt-20">
            <thead class="text-c">
            <tr>
                <th>赛事名称</th>
                <th>主队</th>
                <th>客队</th>
                <th>比分</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <c:if test="${page.records == null || fn:length(page.records) == 0}">
              <tr class="text-c">
              	<td colspan="6" rowspan="5"><span style="color:#ff0000">暂无赛程安排</span></td>
              </tr>
        	</c:if>
            <tbody class="text-c">
	            <c:forEach var="com" items="${page.records}">
		            <tr>		  
		                <td>${com.getCOM_NAME()}</td>
		                <td>${com.getrHTeamName()}</td>
		                <td>${com.getrVTeamName()}</td>
		                <td>${com.getHOME_GRADE()}：${com.getVISITING_GRADE()}</td>
		                <td>${com.getsTime()} — ${com.geteTime()}</td> 
		                <c:choose>
		                <c:when test="${com.getSE_STATUS()==2}">  
		                <td ><button class="btn btn-success radius" onclick="modaldemo(${com.rID})">查看详情 </button> </td>
		                </c:when>
		                <c:otherwise> 
		                <td ><button class="btn btn-danger radius" >暂无结果</button> </td>
		                </c:otherwise>
		                </c:choose>
		            </tr>
	            </c:forEach>
            </tbody>
            <c:if test="${page.totalPageNum > 1}"> 
            	<td align="center" colspan="6">
    				<%@include file="page.jsp"%>
    			</td>
    		</c:if>
        </table>
    </div>
</div>

<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">比赛详情</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-10">
                    <tbody class="text-c">
                    <tr>
                        <td width="20%"></td>
                        <td>实到人数</td>
                        <td>比分</td>

                    </tr>
                    <tr>
                        <td>主队</td>
                        <td><span id="H_TEAM_NUM"></span></td>
                        <td><span id="H_GRADE"></span></td>
                    </tr>
                    <tr>
                        <td>客队</td>
                        <td><span id="V_TEAM_NUM"></span></td>
                        <td><span id="V_GRADE"></span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div ms-controller="list"> 
	            <div class="modal-body">
	                <table class="table table-border table-bordered table-bg  mt-10">
	                    <tbody id="for_w1" class="text-c">
	                    <tr>
	                        <td width="25%">警告人员姓名</td>
	                        <td width="10%">主客队</td>
	                        <td width="15%">球员号码</td>
	                        <td>原因</td>
	                    </tr>
	                    <tr ms-for="value1 in jsonHomeList" ms-if="value1.w_NAME!=''">
	                        <td>{{value1.w_NAME}}</td>
	                        <td>主队</td>
	                        <td>{{value1.w_NUM}}</td>
	                        <td>{{value1.w_REASON}}</td>
	                    </tr>
	                    </tbody>
	                    <tbody id="for_w2" class="text-c">
	                    <tr ms-for="value2 in jsonVisitList" ms-if="value2.w_NAME!=''">
	                        <td>{{value2.w_NAME}}</td>
	                        <td>客队</td>
	                        <td>{{value2.w_NUM}}</td>
	                        <td>{{value2.w_REASON}}</td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-body">
	                <table class="table table-border table-bordered table-bg  mt-10">
	                    <tbody id="for_w3" class="text-c">
	                    <tr>
	                        <td width="25%">罚出人员姓名</td>
	                        <td width="10%">主客队</td>
	                        <td width="15%">球员号码</td>
	                        <td>原因</td>
	                    </tr>
	                    <tr ms-for="value3 in jsonHomeList" ms-if="value3.s_NAME!=''">
	                        <td>{{value3.s_NAME}}</td>
	                        <td>主队</td>
	                        <td>{{value3.s_NUM}}</td>
	                        <td>{{value3.s_REASON}}</td>
	                    </tr>
	                    </tbody>
	                    <tbody id="for_w4" class="text-c">
	                    <tr ms-for="value4 in jsonVisitList" ms-if="value4.s_NAME!=''">
	                        <td>{{value4.s_NAME}}</td>
	                        <td>客队</td>
	                        <td>{{value4.s_NUM}}</td>
	                        <td>{{value4.s_REASON}}</td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-20">
                    <tbody class="text-c">
                    <tr>
                        <td width="25%">红牌情况：</td><td><span id="R_RED_C_TEXT"></span></td>
                    </tr>
                    <tr>
                        <td>罚球点球情况：</td><td><span id="R_PENA_TEXT"></span></td>
                    </tr>
                    <tr>
                        <td>严重错漏判情况:</td><td><span id="R_WRONG_TEXT"></span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<%-- <script type="text/javascript" src="${base_path}/resources/js/team/vue1.0.28.js"></script> --%>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script>

//警告
var vm = avalon.define({
	$id: "list",
	jsonHomeList: [],
	jsonVisitList: []
});
// 	var vm1 =new Vue({
// 	    el:'#for_w1',   //刚才说到的for_w
// 	    data: { 
// 	        json: []   //定义一个空的json
// 	    }
// 	});
	
// 	var vm3 =new Vue({
// 	    el:'#for_w3',   //刚才说到的for_w
// 	    data: { 
// 	        json: []   //定义一个空的json
// 	    }
// 	});
// //罚出	
// 	var vm2 =new Vue({
// 	    el:'#for_w2',   //刚才说到的for_w
// 	    data: { 
// 	        json: []   //定义一个空的json
// 	    }
// 	});
	
// 	var vm4 =new Vue({
// 	    el:'#for_w4',   //刚才说到的for_w
// 	    data: { 
// 	        json: []   //定义一个空的json
// 	    }
// 	});


    function modaldemo(R_ID){
    	$.ajax({
            url: "${base_path}/team/team_match_see.action",
            type: "POST",
            dataType: "json",
            data: {"R_ID":R_ID},
            success: function (data){
            	var json=eval(data);
//             	vm1 3.json =json.jsonHomeList;
//             	vm2 4.json =json.jsonVisitList;
//             	vm3.json =json.jsonHomeList;
//             	vm4.json =json.jsonVisitList;
				vm.jsonHomeList = json.jsonHomeList;
				vm.jsonVisitList = json.jsonVisitList;
                	$.each(data.jsonError, function(index, json){
                		$('#R_RED_C_TEXT').html(json.r_RED_C_TEXT);
                    	$('#R_PENA_TEXT').html(json.r_PENA_TEXT);
                    	$('#R_WRONG_TEXT').html(json.r_WRONG_TEXT); 
                	});
                	$.each(data.jsonHomeInfo, function(index, json){
                		$('#H_TEAM_NUM').html(json.TEA_NUM);
                		$('#H_GRADE').html(json.h_GRADE);
                	});
                	$.each(data.jsonVisitInfo, function(index, json){
                		$('#V_TEAM_NUM').html(json.TEA_NUM);
                		$('#V_GRADE').html(json.v_GRADE);
                	});
            },
            error : function(req, status, reason) {
            	consle.log(req);
    			layer.alert('系统异常,请刷新重试', {
    				skin: 'layer-bule-style'
    			    ,closeBtn: 0
    		  	});
    		}
    	})
    	
        $("#modal-demo").modal("show");
       }

    

    
</script>
</body>
</html>