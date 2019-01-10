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
<link rel="stylesheet" type="text/css"
	href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

<title>个人信息</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 教练员管理 <span class="c-gray en">&gt;</span>
		个人中心 <a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="mt-20">
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<a
					onclick="coach_edit('教练员信息编辑','${base_path}/coach/editinfo/get.action','1','800','500')"
					class="btn btn-success radius f-r"><i class="Hui-iconfont"></i>
					编辑信息</a>
			</div>

			<table
				class="table table-border table-bordered table-bg  table-striped mt-20">
				<tbody class="text-c">
					<tr>
						<td colspan="" rowspan="2">
						<c:if test="${info.COACH_PHOTO==null }">
							<img class="avatar size-XXL"src="http://static.h-ui.net/h-ui/images/ucnter/avatar-default.jpg">
						</c:if>
						<c:if test="${info.COACH_PHOTO!=null }">
							<img class="avatar size-XXL"src="${base_path }/${info.COACH_PHOTO}">
						</c:if>
							</td>
						<td>姓名</td>
						<td>${info.A_NAME }</td>
						<td>身份证号码</td>
						<td>${info.A_ID_CARD }</td>
						<td>编号</td>
						<td>${info.COACH_ID }</td>

					</tr>
					<tr>
						<td>性别</td>
						<td>${info.COACH_SEX }</td>
						<td>年龄</td>
						<td>${info.age }</td>
						<td>当前绑定手机号</td>
						<td>${info.A_PHONE}</td>

					</tr>
					
					<tr>
						<td rowspan="5">积分榜</td>
						<td>校级(分)</td>
						
						<td>${integral.CS_SCHOOL_WIN*3+integral.CS_SCHOOL_LOSS }</td>
						<td>胜场</td>
						<td>
						<c:if test="${integral.CS_SCHOOL_WIN==null }">0</c:if>
						${integral.CS_SCHOOL_WIN }
						</td>
						
						<td>败场</td>
						<td>
						<c:if test="${integral.CS_SCHOOL_LOSS==null }">0</c:if>
						${integral.CS_SCHOOL_LOSS }
						</td>
					</tr>
					<tr>
						<td>县级(分)</td>
						<td>${integral.CS_COUNTRY_WIN*3+integral.CS_COUNTRY_LOSE }</td>
						
						<td>胜场</td>
						<td>
						<c:if test="${integral.CS_COUNTRY_WIN==null }">0</c:if>
						${integral.CS_COUNTRY_WIN }
						</td>
					
						
						
						<td>败场</td>
						<td>
						<c:if test="${integral.CS_COUNTRY_LOSE==null }">0</c:if>
						${integral.CS_COUNTRY_LOSE }
						</td>
					</tr>
					<tr>
						<td>市级(分)</td>
							<td>${integral.CS_CITY_WIN*3+integral.CS_CITY_LOSE }</td>
						
						<td>胜场</td>
						
					<td>
						<c:if test="${integral.CS_CITY_WIN==null }">0</c:if>
						${integral.CS_CITY_WIN }
						</td>
						<td>败场</td>
						<td>
						<c:if test="${integral.CS_CITY_LOSE==null }">0</c:if>
						${integral.CS_CITY_LOSE }
						</td>
					</tr>
					<tr>
						<td>省级(分)</td>
						
						<td>${integral.CS_PRCVINCE_WIN*3+integral.CS_PRCVINCE_LOSE }</td>
						<td>胜场</td>
						
						<td>
						<c:if test="${integral.CS_PRCVINCE_WIN==null }">0</c:if>
						${integral.CS_PRCVINCE_WIN }
						</td>
						<td>败场</td>
						<td>
						<c:if test="${integral.CS_PRCVINCE_LOSE==null }">0</c:if>
						${integral.CS_PRCVINCE_LOSE }
						</td>
					</tr>
					<tr>
						<td>其他(分)</td>
						<td>${integral.CS_OTHER_WIN*3+integral.CS_OTHER_LOSS }</td>
						
						<td>胜场</td>
						
						<td>
						
						<c:if test="${integral.CS_OTHER_WIN==null }">0</c:if>
						${integral.CS_OTHER_WIN }
						</td>
						<td>败场</td>
						<td>
						<c:if test="${integral.CS_OTHER_LOSS==null }">0</c:if>
						${integral.CS_OTHER_LOSS}
						</td>
					</tr>
					<tr>
						<td>工作经历</td>
					
						<td colspan="6" id="coachExp" style="text-align:left;">
					
						</td>
					</tr>
					<tr>
						<td>校级</td>
						<td colspan="6">
						
						<c:if test="${school==null||school=='[]'}">暂无数据</c:if>
						<c:forEach items="${school}" var="li"
								varStatus="status">
						${li.R_END_TIME},${li.R_REGION}" ${li.COM_NAME}"赛,${li.ORG_NAME}&nbsp; ${li.H_ORG_NAME } ${li.H_TEAM_NAME }
						(<c:if test="${li.R_WIN_STATUS==0 }">胜</c:if><c:if test="${li.R_WIN_STATUS==1 }">负</c:if> )
						 VS ${li.V_ORG_NAME } ${li.V_TEAM_NAME }
						  (<c:if test="${li.R_WIN_STATUS==1 }">胜</c:if><c:if test="${li.R_WIN_STATUS==0 }">负</c:if>)
						 ，积（<c:choose>
										<c:when test="${li.R_H_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==0 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==1 }">0</c:if>
										</c:when>
										<c:when test="${li.R_V_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==1 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==0 }">0</c:if>
										</c:when>
						 </c:choose>）分 <br>
							</c:forEach></td>
					</tr>
					<tr>
						<td>县级</td>
						<td colspan="6">
					
						<c:if test="${country==null||country=='[]'}">暂无数据</c:if>
						<c:forEach items="${country}" var="li"
								varStatus="status">
						${li.R_END_TIME},${li.R_REGION}" ${li.COM_NAME}"赛,${li.ORG_NAME}&nbsp; ${li.H_ORG_NAME } ${li.H_TEAM_NAME }
						(<c:if test="${li.R_WIN_STATUS==0 }">胜</c:if><c:if test="${li.R_WIN_STATUS==1 }">负</c:if> )
						 VS ${li.V_ORG_NAME } ${li.V_TEAM_NAME }
						  (<c:if test="${li.R_WIN_STATUS==1 }">胜</c:if><c:if test="${li.R_WIN_STATUS==0 }">负</c:if>)
						 ，积（<c:choose><c:when test="${li.R_H_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==0 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==1 }">0</c:if>
										</c:when>
										<c:when test="${li.R_V_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==1 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==0 }">0</c:if>
										</c:when>
						 </c:choose>）分 <br>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>市级</td>
						<td colspan="6">
					
						<c:if test="${city==null||city=='[]'}">暂无数据</c:if>
						<c:forEach items="${city}" var="li"
								varStatus="status">
						${li.R_END_TIME},${li.R_REGION}" ${li.COM_NAME}"赛,${li.ORG_NAME}&nbsp; ${li.H_ORG_NAME } ${li.H_TEAM_NAME }
						(<c:if test="${li.R_WIN_STATUS==0 }">胜</c:if><c:if test="${li.R_WIN_STATUS==1 }">负</c:if> )
						 VS ${li.V_ORG_NAME } ${li.V_TEAM_NAME }
						  (<c:if test="${li.R_WIN_STATUS==1 }">胜</c:if><c:if test="${li.R_WIN_STATUS==0 }">负</c:if>)
						 ，积（<c:choose><c:when test="${li.R_H_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==0 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==1 }">0</c:if>
										</c:when>
										<c:when test="${li.R_V_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==1 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==0 }">0</c:if>
										</c:when>
						 </c:choose>）分 <br>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>省级</td>
						<td colspan="6">
						
						<c:if test="${province==null||province=='[]'}">暂无数据</c:if>
						<c:forEach items="${province}" var="li"
								varStatus="status">
						${li.R_END_TIME},${li.R_REGION}" ${li.COM_NAME}"赛,${li.ORG_NAME}&nbsp; ${li.H_ORG_NAME } ${li.H_TEAM_NAME }
						(<c:if test="${li.R_WIN_STATUS==0 }">胜</c:if><c:if test="${li.R_WIN_STATUS==1 }">负</c:if> )
						 VS ${li.V_ORG_NAME } ${li.V_TEAM_NAME }
						  (<c:if test="${li.R_WIN_STATUS==1 }">胜</c:if><c:if test="${li.R_WIN_STATUS==0 }">负</c:if>)
						 ，积（<c:choose><c:when test="${li.R_H_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==0 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==1 }">0</c:if>
										</c:when>
										<c:when test="${li.R_V_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==1 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==0 }">0</c:if>
										</c:when>	
						 </c:choose>）分 <br>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>其他比赛</td>
						<td colspan="6">
						
						<c:if test="${other==null||other=='[]'}">暂无数据</c:if>
						<c:forEach items="${other}" var="li"
								varStatus="status">
						${li.R_END_TIME},${li.R_REGION}" ${li.COM_NAME}"赛,${li.ORG_NAME}&nbsp; ${li.H_ORG_NAME } ${li.H_TEAM_NAME }
						(<c:if test="${li.R_WIN_STATUS==0 }">胜</c:if><c:if test="${li.R_WIN_STATUS==1 }">负</c:if> )
						 VS ${li.V_ORG_NAME } ${li.V_TEAM_NAME }
						  (<c:if test="${li.R_WIN_STATUS==1 }">胜</c:if><c:if test="${li.R_WIN_STATUS==0 }">负</c:if>)
						 ，积（<c:choose><c:when test="${li.R_H_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==0 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==1 }">0</c:if>
										</c:when>
										<c:when test="${li.R_V_TEAM_ID==li.TEAM_ID }">
											<c:if test="${li.R_WIN_STATUS==1 }">3</c:if>
											<c:if test="${li.R_WIN_STATUS==0 }">0</c:if>
										</c:when>	
						 </c:choose>）分 <br>
							</c:forEach>
						</td>
					</tr>
					<tr><td colspan="8">精彩图片</td></tr>
					<tr>
					<td colspan="8"> 
					<c:forEach items="${wonderful}" var ="li">
					<div style="text-align:center;width:33%;float:left">					
						<img  style="width:100% " src="${base_path }/${li.SAVE_PATH}">	
					</div>			
					</c:forEach>
					</td>
						
						
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${base_path }/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${base_path }/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${base_path }/resources/common/lib/laypage/1.2/laypage.js"></script>
	<script>
		/*管理员-编辑*/
		function coach_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
	
	</script>
	<script type="text/javascript">
	$(function() {
	var ss="${info.COACH_EXP}"
	
	ss= ss.replace('/r', "<br>").replace( ' ',"&nbsp");
	
	$("#coachExp").html(ss);
	})
</script>
</body>
</html>