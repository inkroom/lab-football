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
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>队员审核</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 球队管理 <span class="c-gray en">&gt;</span> 球员申请管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
		<form id="seacherForm" action="" method="post">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a class="text-r f-r">
                <input type="text" id="seacherKeyWord" name="seacherKeyWord" class="input-text" style="width:350px" placeholder="请输入球员名字" >
                <button type="button" class="btn btn-success radius" id="seacherBtn" name=""><i class="Hui-iconfont">&#xe665;</i> 查询</button>
            </a>
        </div>
        </form>
        <table class="table table-border table-bordered table-bg table-hover mt-20">
            <thead class="text-c">
            <tr>
                <th>球员</th>
                <th>性别</th>
                <th>年龄</th>
                <th>所在学校</th>
                <th>申请时间</th>
                <th>审核状态</th>
                <th style="">操作</th>
            </tr>
            </thead>
            <c:if test="${page.records == null || fn:length(page.records) == 0}">
              <tr class="text-c">
              	<td colspan="7" rowspan="5"><span style="color:#ff0000">无球员信息</span></td>
              </tr>
        	</c:if>
            <c:forEach items="${page.records}" var="info" varStatus="vs">
            <tr class="text-c">
                <td>${info.PLAYER_NAME}</td>
                <td>${info.pSex}</td>
                <td>${info.pAge}</td>
                <td>${info.pSchool}<c:if test="${empty info.pSchool}">无</c:if></td>
                <td>${info.tpTime}</td>
                <td rowspan="">
                	<c:choose>
                		<c:when test="${info.TEAM_PLAYER_STATUS == 0}">
                			<span class="label label-primary radius">待审核</span>
                		</c:when>
                		<c:when test="${info.TEAM_PLAYER_STATUS == 1}">
                			<span class="label label-success radius">审核通过</span>
                		</c:when>
                		<c:otherwise>
							<span class="label label-danger radius">审核未通过</span>
						</c:otherwise>
                	</c:choose>
                </td>
                <td><button class="btn btn-success radius"  onclick="modaldemo(${info.pID}, ${info.TEAM_PLAYER_STATUS})">查看详情</button></td>

            </tr>
            </c:forEach>
        </table>
		 <c:if test="${page.totalPageNum > 1}">
    		<%@include file="page.jsp"%>
          </c:if>
    </div>
</div>

<!--模态框-->
<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <!--  <div class="modal-header">
                 <h3 class="modal-title">比赛详情</h3>
                 <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
             </div> -->
             <input type="hidden" id="pid">
            <div class="modal-body">
                <div class="mt-20">
                    <div class="text-c">
                        <img class="avatar size-XXL radius " onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'"  id="photo" src="">
                    </div>
                    <table class="table table-border table-bordered table-bg  table-striped mt-20">
                        <tbody class="text-c">
                        
                        <!-- <tr>
                            <td colspan="4" rowspan=""><img class="avatar size-XXL radius" src="http://static.h-ui.net/h-ui/images/ucnter/avatar-default.jpg"></td>
                         </tr> -->
                        <tr>
                            <th width="33%">姓名</th><th>积分</th>
                            <th>学籍号</th>
                        </tr>
                        <tr> <td><span id="playerName"></span></td>
                            <td><span id="totalIntegral"></span></td><td><span id="playerStuID"></span></td>
                        </tr>
                        <tr>
                            <th>身高（Cm）</th>
                            <th>体重(Kg)</th><th>擅长位置 </th>
                        </tr>
                        <tr><td><span id="playerHight"></span></td><td><span id="playerWeight"></span></td>
                            <td><span id="position"></span></td>
                        </tr>
                       
                        <tr>
                        	<th colspan="1">球衣号</th><th colspan="2">所在学校</th>
                        </tr>
                        <tr><td colspan="1"><span id="ptNum"></span></td>
                        	<td colspan="2"><span id="school"></span></td>
                        </tr>
                        </tbody>
                        </table>
                    <table class="table table-border table-bordered table-bg  table-striped mt-20">
                        <tbody class="text-c">
                        <tr><th colspan="3">活动及获奖情况</th></tr>
                        <tr>
                            <td colspan="1" width="20%">校级</td>
                            <td colspan="2">
                                <span id="schoolHonrs"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">县级</td>
                            <td colspan="2">
                                <span id="countryHonrs"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">市级</td>
                            <td colspan="2">
                            	<span id="cityHours"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">省级</td>
                            <td colspan="2">
                               <span id="provenceHours"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">其他</td>
                            <td colspan="2">
                               <span id="otherHours"></span>
                            </td>
                        </tr>
						
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button id="agreeBtn" class="btn btn-success"  aria-hidden="true">同意</button>
                <button id="refuseBtn" class="btn btn-danger" data-dismiss="modal" aria-hidden="true">拒绝</button>
                <button id="closeBtn" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 球衣号弹窗 -->
    <div id="modal-demo2" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="">
    	<div class="modal-dialog">
    		<div class="modal-content radius">
    			<div class="modal-header">
    				<h3 class="modal-title">设置球衣号</h3>
    				<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
    			</div>
    			<div class="modal-body">
	    			<div class="text-c">
	    				<span>球衣号:</span><input type="text" placeholder="请输入球衣号(0~99)" maxlength="2" id="ptNumArrange" name="ptNumArrange" class="input-text radius size-S ml-10" style="width:250px;">
	    			</div>
    			</div>
    			<div class="modal-footer">
    				<button class="btn btn-primary" id="checkPtNum">确定</button>
    				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
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
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/team/page/audit_application.js"></script>
<script type="text/javascript">
	
	$(function(){
		if('${message}'!=null && '${message}'!=''){
			layer.msg('${message}',{icon: 0});
			<% session.removeAttribute("message");%>
		}
	}); 
	

</script>
</body>
</html>