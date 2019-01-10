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
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/index.css" />
   <!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

    <title>球队申请</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 队员管理 <span class="c-gray en">&gt;</span> 球队申请<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <input id="teamID" type="text" class="input-text" style="width:350px" placeholder="申请球队，请在此输入球队码" >
                <button type="submit" class="btn btn-success radius"  name="" onclick="modaldemo()"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
            </a>
        </div>

        <table class="table table-border table-bordered table-bg  mt-20">
            <thead class="text-c">
            <tr><th colspan="12">我的球队申请</th></tr>
            </thead>
            <tbody class="text-c">
            <c:forEach items="${tInfo }" var="team">         
            <tr>
                <td width='15%' colspan="" rowspan="2">${team.TEAM_NAME }</td>
                <td width='15%' colspan="" rowspan="2">
                <img class="avatar size-XXL radius" src="${base_path }/${team.TEAM_BADGE }"></td>
                <td width='10%'>所属机构 </td>
                <td width="10%">
                <c:choose>
                <c:when test='${team.TEAM_AFFILIATION==null}'>无</c:when>
                <c:when test='${team.TEAM_AFFILIATION==""}'>无</c:when>
                <c:otherwise>
                 ${team.TEAM_AFFILIATION} 
                </c:otherwise>
                </c:choose>
                  </td>
                <td rowspan="" width='10%'>球队积分:</td><td width='10%'>${team.SCORE }</td>
                <td width='10%'>球队等级</td>
                <td width='10%'>
                
                   <c:if test="${team.TEAM_RANK==1 }">省</c:if>
                <c:if test="${team.TEAM_RANK==2 }">市</c:if>
                <c:if test="${team.TEAM_RANK==3 }">县</c:if>
                <c:if test="${team.TEAM_RANK==4 }">校</c:if>
                <c:if test="${team.TEAM_RANK==5 }"> 其他</c:if>
                 </td>
                 <td rowspan="2" width='15%'>
               
                <c:if test="${team.COACH_TEAM_STATSU==1 }"><span class="label label-success radius">通过</span></c:if>
                <c:if test="${team.COACH_TEAM_STATSU==0 }"><span class="label label-error radius">待审核</span></c:if>
                <c:if test="${team.COACH_TEAM_STATSU==2 }"><span class="label label-warning radius">不通过</span></c:if>
                <c:if test="${team.COACH_TEAM_STATSU==3 }"><span class="label label-warning radius">被解雇</span></c:if>
                </td>
                </tr>
                <tr>
                <td rowspan="" >比赛场次:</td><td width='10%'>${team.MATCH_NUM }</td>
                <td rowspan="">平均胜率:</td><td>
                <c:choose>
                	<c:when test="${team.WINNING_RATIO==null }">无</c:when>
                	<c:otherwise>
                		${team.WINNING_RATIO }
                	</c:otherwise>
                </c:choose>
                </td>
               <td width="10%">申请时间:</td>
               <td> ${team.TIME}</td>
               </tr>
            
            </c:forEach>
            </tbody>
        </table>
        <div class="page-icon" style="margin-top: 50px">
		    <c:if test="${page==1}">
		   	 	<span class="page-disabled"><i></i>上一页</span>
		    </c:if>
	        <c:if test="${page>1}">
	        	<a class="page-next" href="${base_path}/coach/apply_info.html?page=${page-1}">上一页<i></i></a>
	        </c:if>
	        <c:if test="${page==totalPage  and totalPage >= 1}">
		   	 	<span class="page-disabled"><i></i>下一页</span>
		    </c:if>		    
		    <c:if test="${page <totalPage}">
		   	 	<a class="page-next" href="${base_path}/coach/apply_info.action?page=${page+1 }">下一页<i></i></a>
		    </c:if>
		    <span class="page-disabled">
	            <span>第${page }页,总：${totalPage}页&nbsp;跳转到</span>
	            <input id="page" class="input-text size-MINI radius" style="width: 40px" value="${page }">
	            <button type="button" value="跳转" onclick="redirect();" class="btn radius ml-10 size-MINI">跳转</button>
       		 </span>
	       </div>
    </div>
</div>
<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title" id="tName">队名</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
                <table class="table table-border table-bordered table-bg  mt-20">
                    <tbody class="text-c">
                    <tr>
                        <td colspan="2"><img id="tImg" class="avatar size-XXL radius" ></td>
                    </tr>
                    <tr>
                        <td width="45%">比赛场次：</td><td id="tMatch">无</td>
                    </tr>
                    <tr>
                        <td>比赛胜率：</td><td id="tWin">无</td>
                    </tr>
                    <tr>
                        <td>比赛积分：</td><td id="tInt">无</td>
                    </tr>
                    <tr>
                        <td>所属机构：</td><td id="tAdd">无</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="applyteam();">申请入队</button>

                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
    <input type="hidden" id="td" />
</div>

<!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path }/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/laypage/1.2/laypage.js"></script>
<script>
var path = "${base_path}";
var reg=/^[a-zA-Z0-9]{10,10}$/;
    function modaldemo(){
    	var td = $("#teamID").val().replace(/^\s+|\s+$/g,"");
    	if(td==""||td==null){
    		$.Huimodalalert('请输入搜索内容',2000);
    		return;
    	}else if(!reg.test(td)){
     		$.Huimodalalert('请输入正确的球队码',2000);
    		return;
    	} 
    	$.ajax({
    		url:path+'/coach/apply_team_info.action',
    		type:'POST',
    		data:{td:td},
    		success:function(data){
    			if(data!=""){
        			$("#tName").text(data.TEAM_NAME);
        			$("#tImg").attr('src',path+'/'+data.TEAM_BADGE);
        			$("#tMatch").text(data.MATCH_NUM);
        			$("#tWin").text(data.WINNING_RATIO);
        			$("#tInt").text(data.SCORE);
        			$("#tAdd").text(data.TEAM_AFFILIATION);
        			$("#td").text(data.TEAM_ID);
        			$("#modal-demo").modal("show");
    			}else{
    				$.Huimodalalert('没有此队伍',2000)
    			}
    		},
    		error:function(){
    			$.Huimodalalert('未知错误，请刷新重试！',2000)
    		}
    	});
    }
    
function applyteam(){
	var td = $("#td").text();
	$.ajax({
		url:path+'/coach/applyteam.action',
		type:'POST',
		data:{td:td},
		success:function(data){
			if(data!=""){
				console.log(data);
				$.Huimodalalert(data,2000);
				setTimeout(function(){
				    window.location.reload();
                },2000);
			}else{
				$.Huimodalalert('错误',2000);
			}
		},
		error:function(){
			$.Huimodalalert('未知错误，请刷新重试！',2000)
		}
	});
}
function redirect(){
	var page = $("#page").val();
	var totalPage = "${totalPage}";
	if(page>totalPage){
		$.Huimodalalert('请输入正确的页数',2000);
		return;
	}
	if(page<1){
		$("#page").val("1");
		$.Huimodalalert('请输入正确的页数',2000);
		return;
	}
	window.location.href="${base_path}/coach/apply_info.action?page="+page;
}
function isNull(checkValue){
	if(checkValue==""||checkValue==null){
		return "无";
	}else {
		return checkValue;
	}
}
</script>
</body>
</html>