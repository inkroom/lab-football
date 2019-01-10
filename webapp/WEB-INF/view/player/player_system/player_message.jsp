<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<!--[if lt IE 9]>
	<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
    <title>个人信息</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 球员管理 <span class="c-gray en">&gt;</span> 个人中心 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="mt-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <a onclick="player_edit('球员信息编辑','${base_path}/player/system/player_edit.html','1')" class="btn btn-success radius f-r"><i class="Hui-iconfont"></i> 编辑信息</a>
        </div>

        <table class="table table-border table-bordered table-bg  table-striped mt-20">
            <tbody class="text-c">
            
            <tr>
                <td colspan="8" rowspan="">
                <c:if test="${player_info.P_PEROSONAL_PHOTO == null}">
                	<img class="avatar size-XXL radius" src="http://static.h-ui.net/h-ui/images/ucnter/avatar-default.jpg">
                </c:if>
                <c:if test="${player_info.P_PEROSONAL_PHOTO != null}">
                	<img class="avatar size-XXL radius" src="${base_path}/${player_info.P_PEROSONAL_PHOTO}">
                </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="2" width="25%">姓名:</td>
                <td colspan="2" >
					<c:if test="${player_info.A_NAME eq ''}">
						${stu_info.STU_NAME}
					</c:if>
					${player_info.A_NAME}
                </td>
                <td colspan="2">身份证号码：</td>
                <td colspan="2">${player_info.A_ID_CARD}</td>
            </tr>
            <tr>
                <td colspan="2">性别：</td>
                <td colspan="2">${player_info.P_SEX}
                	<c:if test="${player_info.P_SEX eq ''}">
						${stu_info.STU_SEX}
					</c:if>
                </td>
                <td colspan="2" >年龄 ：</td>
                <td colspan="2">${player_info.AGE}</td>
            </tr>
            <tr>
                <td colspan="2">身高(cm)： </td>
                <td colspan="2">${player_info.P_PLAYER_HEIGHT}</td>
                <td colspan="2" >体重（kg）：</td>
                <td colspan="2">${player_info.P_PLAYER_WEIGHT}</td>
            </tr>
            <tr>
                <td colspan="2">所在学校：</td>
                <td colspan="2">${player_info.P_SCHOOL}</td>
                <td colspan="2">当前绑定手机号：</td>
                <td colspan="2">${player_info.A_PHONE}</td>
            </tr>
            <tr >
                <td colspan="2" >学籍号 :</td>
                <td colspan="2">${player_info.STU_NUM}
                	<c:if test="${player_info.STU_NUM eq null}">
                		暂无信息
                	</c:if>
                </td>
                <td colspan="2">擅长位置：</td>
                <td colspan="2">${player_info.positionInfo}</td>
            </tr>
            <tr>
                <td colspan="2">个人积分: </td>
                <td colspan="7">${player_info.INTEGRAL}</td>
                
            </tr>
            <tr>
                <th colspan="8">活动与参加记录</th>
            </tr>
            <tr>
                <td colspan="1">校级</td>
                <td colspan="7" style="text-align:left;">
                    <c:if test="${activity_info_school eq '[]'}">
                    	暂无数据
                    </c:if>
                    <c:if test="${activity_info_school ne '[]'}">
                    	<c:forEach items="${activity_info_school}" var="list">
                    		${list.com_end},在${list.team_name}参加“${list.com_name}”
                           	<c:if test="${list.com_type == 1}">男子</c:if>
                            <c:if test="${list.com_type == 2}">女子</c:if>
                            <c:if test="${list.com_type == 3}"></c:if>

                            <c:if test="${list.com_grounp == 1}">5人</c:if>
                            <c:if test="${list.com_grounp == 2}">7人</c:if>
                            <c:if test="${list.com_grounp == 3}">11人</c:if>
                            <c:if test="${list.com_grounp == 4}"></c:if>
                            足球赛获得${list.com_rank}
                    		<br>
                    	</c:forEach>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="1">县级</td>
                <td colspan="7" style="text-align:left;">
                    <c:if test="${activity_info_country eq '[]'}">
                    	暂无数据
                    </c:if>
                    <c:if test="${activity_info_country ne '[]'}">
                    	<c:forEach items="${activity_info_country}" var="list">
                            ${list.com_end},在${list.team_name}参加“${list.com_name}”
                           	<c:if test="${list.com_type == 1}">男子</c:if>
                            <c:if test="${list.com_type == 2}">女子</c:if>
                            <c:if test="${list.com_type == 3}"></c:if>

                            <c:if test="${list.com_grounp == 1}">5人</c:if>
                            <c:if test="${list.com_grounp == 2}">7人</c:if>
                            <c:if test="${list.com_grounp == 3}">11人</c:if>
                            <c:if test="${list.com_grounp == 4}"></c:if>
                            足球赛获得${list.com_rank}
                    		<br>
                   		 </c:forEach>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="1">市级</td>
                <td colspan="7" style="text-align:left;">
                	<c:if test="${activity_info_city eq '[]'}">
                    	暂无数据
                    </c:if>
                    <c:if test="${activity_info_city ne '[]'}">
                    	<c:forEach items="${activity_info_city}" var="list">
                            ${list.com_end},在${list.team_name}参加“${list.com_name}”
                           	<c:if test="${list.com_type == 1}">男子</c:if>
                            <c:if test="${list.com_type == 2}">女子</c:if>
                            <c:if test="${list.com_type == 3}"></c:if>

                            <c:if test="${list.com_grounp == 1}">5人</c:if>
                            <c:if test="${list.com_grounp == 2}">7人</c:if>
                            <c:if test="${list.com_grounp == 3}">11人</c:if>
                            <c:if test="${list.com_grounp == 4}"></c:if>
                            足球赛获得${list.com_rank}
                    		<br>
                   		 </c:forEach>
                    </c:if>
                </td>
            </tr>
            <tr>
               <td colspan="1">省级</td>
                <td colspan="7" style="text-align:left;">
                    <c:if test="${activity_info_province eq '[]'}">
                    	暂无数据
                    </c:if>
                    <c:if test="${activity_info_province ne '[]'}">
                    	<c:forEach items="${activity_info_province}" var="list">
                            ${list.com_end},在${list.team_name}参加“${list.com_name}”
                           	<c:if test="${list.com_type == 1}">男子</c:if>
                            <c:if test="${list.com_type == 2}">女子</c:if>
                            <c:if test="${list.com_type == 3}"></c:if>

                            <c:if test="${list.com_grounp == 1}">5人</c:if>
                            <c:if test="${list.com_grounp == 2}">7人</c:if>
                            <c:if test="${list.com_grounp == 3}">11人</c:if>
                            <c:if test="${list.com_grounp == 4}"></c:if>
                            足球赛获得${list.com_rank}
                    		<br>
                   		 </c:forEach>
                    </c:if>
                </td>
            </tr>
            <tr>
               <td colspan="1">其他</td>
                <td colspan="7" style="text-align:left;">
                    <c:if test="${activity_info_other eq '[]'}">
                    	暂无数据
                    </c:if>
                    <c:if test="${activity_info_other ne '[]'}">
                    	<c:forEach items="${activity_info_other}" var="list">
                            ${list.com_end},在${list.team_name}参加“${list.com_name}”
                           	<c:if test="${list.com_type == 1}">男子</c:if>
                            <c:if test="${list.com_type == 2}">女子</c:if>
                            <c:if test="${list.com_type == 3}"></c:if>

                            <c:if test="${list.com_grounp == 1}">5人</c:if>
                            <c:if test="${list.com_grounp == 2}">7人</c:if>
                            <c:if test="${list.com_grounp == 3}">11人</c:if>
                            <c:if test="${list.com_grounp == 4}"></c:if>
                            足球赛获得${list.com_rank}
                    		<br>
                   		 </c:forEach>
                    </c:if>
                </td>
            </tr>
            <tr>
            	<td colspan="1">精彩图片</td>
                <td colspan="8" rowspan="" style="text-align:left;">
                	<c:if test="${wonderful_pic eq '[]'}">
                		暂无数据
                	</c:if>
                	<c:if test="${wonderful_pic ne '[]'}">
                		<c:forEach items="${wonderful_pic}" var="list">
                			<img class="" src="${base_path}/${list.get('SAVE_PATH')}" width="33%" height="">
                		</c:forEach>
                	</c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>

    </div>
</div>
<!--模态框-->

<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script>
    /*球员-编辑*/
    function player_edit(title,url,id){
    	var wt = window.screen.width * 0.7;
        var ht = window.screen.height * 0.7;
        layer_show(title,url,wt,ht);
    }

</script>


</body>
</html>