<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <style>
    .th_color{
    background-color:#f9f9f9;
    }
    </style>
    <title>球队中心</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>球队管理 <span class="c-gray en">&gt;</span> 球队中心 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a onclick="coach_edit('球队信息编辑','team_edit.html','1','800','500')" class="btn btn-success radius f-r"><i class="Hui-iconfont"></i> 编辑信息</a>
        </div>
         <div class="cl pd-5 bg-1 bk-gray mt-20 text-r" >
            <input type="hidden" id="TEAM_AUDIT" name="TEAM_AUDIT" value="${map.TEAM_AUDIT}">
            <input type="text"  class="input-text f-l ml-20 "  style="width:350px; display:none;" placeholder="请输入机构编号" id="ORG_ID" name="ORG_ID" value="${ORG_ID}">
            <button type="button" class="btn btn-success radius f-l ml-20" style="display:none;"  id="idsub" name="idsub" onclick="modaldemo()" ><i class="Hui-iconfont">&#xe665;</i> 查询</button>
            <span class="f-l c-green"  style="line-height:30px; display:none;" id="APPLY" name="APPLY" >机构审核中，请稍后......</span>
            <button id="btn_aud"class="f-r 	btn btn-success-outline radius  ml-10 f-20 " onclick="aud_play()">播放队歌<i class="Hui-iconfont Hui-iconfont-bofang"></i></button>
            <span class="btn-upload form-group f-r text-c">
            <span id="musicStatus" ></span>&nbsp;&nbsp;&nbsp;
            <input hidden class="input-text upload-url radius" type="text" name="uploadfile-1" id="uploadfile-1" readonly>　<a
                             href="javascript:void();" class="btn btn-primary radius"><i class="Hui-iconfont Hui-iconfont-upload"></i>
                         上传队歌</a>  
           <form id="songForm" action="" method="post"  enctype="multipart/form-data" >          
            	<input type="file" id="file1" multiple name="musicFile" class="input-file">
            </form>    
            </span>

            <audio  loop="loop" id="aud" src="${base_path}/${teamMusic}" class="f-r" >
                		您的浏览器版本过低，请使用IE8以上.
            </audio>
        </div>
        <table class="table table-border table-bordered table-bg  table-striped mt-20">
            <tbody class="text-c">
            <tr>
                <th>队徽：</th>
                <td colspan="" rowspan=""><img class="avatar size-XXL radius" onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'"  src="${base_path}/${teamLogo}"></td>
                <th>队旗：</th>
                <td colspan="" rowspan=""><img class="avatar size-XXL " onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'"  src="${base_path}/${teamFlag}"></td>
            </tr>
            <tr>
                <th>球队名称：</th>
                <td>${map.TEAM_NAME}</td>
                <th>球队类型：</th>
                <td>${map.TEAM_TYPE}</td>
                </tr>
            <tr>
                <th>所属机构： </th>
                <c:choose> 
                <c:when test="${ORG.ORG_NAME==null}">   
                <td>暂无</td>
                </c:when>
                <c:otherwise>
                <td>${ORG.ORG_NAME}</td>
                </c:otherwise>
                </c:choose>
                <th>球队码：</th>
                <td>${map.TEAM_NUM}</td>
            </tr>
            <tr>
                <th>比赛场次:</th>
                <td>${map.MATCHTIMES}</td>
                <th>平均胜率:</th>
                <td>${map.AVGWIN}%</td>
            </tr>
            <tr>
                <th>领队姓名:</th>
                <td>${map.TEAM_LEADER}</td>
                <th>教练员姓名:</th>
                <td>
                <c:forEach var="listcoach" items="${listcoach}">
                     ${listcoach.getCOACH_NAME()}
                </c:forEach>
                </td>
            </tr>
            <tr>
                <th>球队:</th>
                <td>${map.TEAM_SCORE}</td>
                <th>领队手机号:</th>
                <td>${map.TEAM_PHONE_NUM}</td></tr>
            <tr>
                <th>励志标语:</th>
                <td colspan="3">${map.TEAM_ISPIRATION_LOGO}</td>
            </tr>
            <tr><td colspan="4"></td></tr>
            <tr>
                <th>队员</th><th>年龄</th><th>擅长位置</th><th>个人积分</th>
            </tr>
             <c:forEach var="liststu" items="${liststu}">
            <tr>
                <td>${liststu.getPLAYER_NAME()}</td>		
                <td>${liststu.pAge}</td>
                <td>${liststu.p_POSITION}</td>
                <td>${liststu.p_GRADE_TABLE_ID}</td>
            </tr>
            </c:forEach>  

            </tbody>
        </table>
    </div>
</div>

<!--模态框-->

<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class="modal-header">
                <h3 class="modal-title">机构信息</h3>
                <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
            </div>
            <div class="modal-body">
               
               <table  class="table table-border table-bordered table-bg  mt-20">
							<tbody class="text-c">
								<tr>
									<th colspan="" class="th_color" width="200">机构编号:</th>
									<td colspan=""><span id=ORG_id></span></td>
								</tr>

							</tbody>
						</table>
						    <table  class="table table-border table-bordered table-bg  mt-20">
							<tbody class="text-c">
						
								<tr>
									<th colspan="" class="th_color" width="200">机构名称:</th>
									<td colspan=""><span id=ORG_NAME></span></td>
								</tr>

							</tbody>
						</table>
               
            </div>
            <div class="modal-footer">
                            <button id="agreeBtn" class="btn btn-success"  >确认加入</button>
            
                            <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">取消</button>
                
            </div>
        </div>
    </div>
</div>
<input type="text" id="base_path" hidden="" value="${base_path}"> 
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
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/team/page/team_message.js"></script>
<script>

//打印错误
	$(function(){
		if('${message}'!=null && '${message}'!=''){
			layer.msg('${message}',{icon: 0});
			<% session.removeAttribute("message");%>
		}
	}); 


//当机构审核状态为2的时候，显示查询窗口。显示1的时候显示"正在查询"。显示0的时候啥都不显示
	$(function() {
		var TEAM_AUDIT = $("#TEAM_AUDIT").val();
		if(TEAM_AUDIT=="2"){
			$('#ORG_ID').show();
			$('#idsub').show();
			$('#APPLY').hide();
		}else if(TEAM_AUDIT=="0"){
			$('#ORG_ID').hide();
			$('#idsub').hide();
			$('#APPLY').show();
		}else{
			$('#ORG_ID').hide();
			$('#idsub').hide();
			$('#APPLY').hide();
		}
	});

//模态框函数
function modaldemo(){
	 var ORG_ID = $("#ORG_ID").val();
	$.ajax({
		url: "${base_path}/team/team_center_org.action",
        type: "POST",
        dataType: "json",
        data: {"ORG_ID":ORG_ID},
        success: function (data) {
            if(data.status==200){
            	$.each(data.ORG, function(index, json){
//             		alert(json.ORG_NAME);
//             		alert(json.ORG_ID);
            		$('#ORG_id').html(json.ORG_ID);
                	$('#ORG_NAME').html(json.ORG_NAME);
            	});
            	$("#modal-demo").modal("show");
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

//确认加入
$('#agreeBtn').click(function(){
	layer.confirm('您确定申请加入该机构吗?', {
  		  btn: ['确认','取消'] //按钮
  		}, function(){
  			var ORG_ID = $("#ORG_ID").val();			
  			$.ajax({
  	            url: "${base_path}/team/team_center_agree.action",
  	            type: "post",
  	            dataType: "json",
  	            data: {"ORG_ID":ORG_ID},
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

   function coach_edit(title,url,id,w,h){
       layer_show(title,url,w,h);
   }
   
    		
    
</script>
</body>

</html>