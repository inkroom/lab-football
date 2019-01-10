<#include "config.ftl">
<#include "common_function.ftl">
<#macro page navi={} title=macro_config.default_title body="" head="" simple="false" setReferUrl=false>
<#if setReferUrl>${action.setReferUrl()}</#if>
<#if (navi?size > 0)>
    <#assign keys = navi?keys>
	<#if title==macro_config.default_title>
		<#local title = navi[keys?last]>
	</#if>
</#if>
<!DOCTYPE HTML>
<html>
<head>
<meta charset=${macro_config.charset}>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" /> <script type="text/javascript" src="${base}/lib/html5.js"></script>
    <script type="text/javascript" src="${base}/lib/respond.min.js"></script>
    <script type="text/javascript" src="${base}/lib/PIE_IE678.js"></script>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
    <script type="text/javascript" src="${base}/lib/jquery/1.9.1/jquery.min.js"></script><!-- 引入jquery框架 -->

<!--[if lte IE 9]>
    <script type="text/javascript" src="./${base}/lib/html5.js"></script>
    <script type="text/javascript" src="./${base}/lib/respond.min.js"></script>
    <script type="text/javascript" src="${base}/lib/PIE_IE678.js"></script>
<![end if]-->
<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${base}/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${base}/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/skin/default/skin.css"/>
<!--<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/css/style.css" />-->
<link rel="stylesheet" type="text/css" href="${base}/static/football/css/style.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/football/css/page.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>${macro_config.common_title}</title>
<meta name="keywords" content="">
<meta name="description" content="">
${head}
</head>
<body ${body}>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top" style="height:75px;">
            <div class="container-fluid cl pt-5">
                <span class="index_logo ml-40"><a href="${base}/index/indexPage!pageToIndex.action"><img src="${base}/static/football/images/logo.png" alt="" border="0"/></a></span>
                <span id="title" class="index_title ml-30" >
                	${macro_config.common_title}
                </span>
                <nav class="f-r index_top_nav">
                    <span class="mr-50">四川省教育厅&nbsp;&nbsp;&nbsp;&nbsp;<span id = "nameTop"></span></span>
                    <span class="mr-20"><a href="${base}/logout.action">退出</a></span>
                </nav>
            </div>
	</div>
</header>

<aside class="Hui-aside" style=" margin-top:22px;">
	<div class="menu_dropdown bk_2">
		<#if (_LOGIN_USER_.TYPE)?exists>
			<#if (_LOGIN_USER_.TYPE == 1)>
				<#include "page_player.ftl">
				<script>
					$("#nameTop")[0].innerHTML = '运动员姓名：'+'${(_LOGIN_USER_.P_NAME)?if_exists}';
				</script>
			</#if>
	
			<#if (_LOGIN_USER_.TYPE == 2)>
				<#include "page_coach.ftl">
				<script>
					$("#nameTop")[0].innerHTML = '教练员姓名：'+'${(_LOGIN_USER_.C_NAME)?if_exists}';
				</script>
			</#if>
	
			<#if (_LOGIN_USER_.TYPE == 3)>
				<#include "page_team.ftl">
				<script>
					$("#nameTop")[0].innerHTML = '球队名称：'+'${(_LOGIN_USER_.T_NAME)?if_exists}';
				</script>
			</#if>
	
			<#if (_LOGIN_USER_.TYPE == 4)>
				<#include "page_org.ftl">
				<script>
					$("#nameTop")[0].innerHTML = '机构名称：'+'${(_LOGIN_USER_.O_NAME)?if_exists}';
				</script>
			</#if> 
	
			<#if (_LOGIN_USER_.TYPE == 5)>
				<#include "page_record.ftl">
				<script>
					$("#nameTop")[0].innerHTML = '记录员：'+'${(_LOGIN_USER_.OP_NAME)?if_exists}';
				</script>
			</#if>
			
			<#if (_LOGIN_USER_.TYPE == 6)>
				<#include "page_index.ftl">
				<script>
					$("#nameTop")[0].innerHTML = '管理账号：'+'${(_LOGIN_USER_.USERNAME)?if_exists}';
				</script>
			</#if>
		</#if>
		<dl>
			<dt><i class="Hui-iconfont">&#xe6c6;</i>&nbsp;&nbsp;公示管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="${base}/publicity/teamPublicity!getAllPublicityTeam.action" data-title="球队公示" href="javascript:void(0)">球队公示</a></li>
					<li><a href="${base}/publicity/playerPublicity!getAllPublicity.action" data-title="运动员公示" href="javascript:void(0)">运动员公示</a></li>
					<li><a href="${base}/publicity/coachPublicity!pageToCp.action" data-title="教练员公示" href="javascript:void(0)">教练员公示</a></li>
					<li><a href="${base}/publicity/teamApplyPublicity!getAllMatch.action" data-title="报名信息公示" href="javascript:void(0)">报名信息公示</a></li>
					<li><a href="${base}/publicity/matchInfor!searchMatchInfor.action" data-title="比赛结果公示" href="javascript:void(0)">比赛结果公示</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>

<div id="topmenu2" >

         
    
<#if (navi?size > 0)>
<nav class="breadcrumb" style="padding-left:200px;"><i class="Hui-iconfont">&#xe67f;</i>&nbsp;&nbsp;<a href="${base}/login_view.action">首页</a> 
<#assign keys = navi?keys>
<#list keys as key><span class="c-gray en">&gt;</span><a href="${parseLink(navi[key])}"> ${key}</a> </#list>
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
</#if> 
</div>

<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box" style=" margin-top:22px;">
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			 <#nested> 	
		</div>
	</div>
</section>


<script type="text/javascript" src="${base}/lib/layer/2.1/layer.js"></script> <!-- 引入layer弹出框.js -->
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.js"></script> <!-- 兼容处理等功能 -->
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.admin.js"></script> <!-- 封装页面所需的js函数 -->

</body>
</html>
</#macro>

<#macro index title=macro_config.default_title body="" head="" simple="false" setReferUrl=false>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
   	<link href="${base}/lib/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${base}/css/index.css" rel="stylesheet">
    
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
     <style>
     	.show_iframe{
     		background-color: white;
     		height:100vh;
     		overflow:auto;
     	}
     	.container{
     		
     	}
     	.page-container{
            margin-top: 50px;
            
        }
       .form-control{
           width: 50%;
           float: left;
       }
        .control-label{
            display: inline-block;
            width: 50%;
            text-align: center;
            line-height: 30px;
        }
        .show_iframe>div{
        	width:100%;
        }
  </style>
</head>
<body>
<nav>
     <a href="${base}/fiveplan/fivePlanLogin!logout.action"><button class="btn btn-success">安&nbsp;全&nbsp;退&nbsp;出</button></a>
</nav>
<div class="left">
    <div class="left-header">
        账号单位：${_LOGIN_USER_ID_?if_exists}
    </div>
    <ul>
        <li><a href="fiveplanmodifyPD!pageToTl.action" ><span><img src="${base}/images/index/leftNavHome.png"></span>修改密码</a></li>
        <li><a href="pageOne!intoRepostForm.action" ><span><img src="${base}/images/index/leftNavPlan.png"></span>体育/艺术五年行动计划 </a></li>
        <li><a href="file!pageToTl.action" ><span><img src="${base}/images/index/leftNavSports.png"></span>上传佐证 </a></li>
        <li><a href="viewFiveSum!intoViewFiveSum.action" ><span><img src="${base}/images/index/leftNavArt.png"></span>查看导出excel</a></li>
   		<li><a href="auditStatus!intoStatusPage.action" ><span><img src="${base}/images/index/leftNavArt.png"></span>查看审核</a></li>
    </ul>
    <div class="left-footer">
        <div class="left-footer-content">
            <p><strong>主办：</strong>四川省教育厅体卫艺处</p>
            <p><strong>技术支持：</strong>成都东软学院</p>
            <p><strong>项目版本：</strong>1.0</p>
            <p>COPYRIGHT © 2016</p>
        </div>

    </div>

</div>

<div class="right" >
	<div id="iframe_box">
        <div class="show_iframe" style="height:100vh;width:100%;">
        	<#nested>
        </div>
    </div>
    
</div>
	
</body>
</html>

</#macro>


<#-- 功能描述：用于赛事直播页面的宏；  author:彭思瑞     Create Date:2016年4月19日14:58:31-->
<#macro broadcast navi={} title=macro_config.default_title body="" head="" simple="false" setReferUrl=false>
<#if setReferUrl>${action.setReferUrl()}</#if>
<#if (navi?size > 0)>
    <#assign keys = navi?keys>
	<#if title==macro_config.default_title>
		<#local title = navi[keys?last]>
	</#if>
</#if>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset=${macro_config.charset}>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="/favicon.ico" >
	<LINK rel="Shortcut Icon" href="/favicon.ico" />
	<!--[if lte IE 9]>
	<script type="text/javascript" src="${base}/lib/html5.js"></script>
	<script type="text/javascript" src="${base}/lib/respond.min.js"></script>
	<script type="text/javascript" src="${base}/lib/PIE_IE678.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base}/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base}/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/skin/default/skin.css"/>
	<!--<link rel="stylesheet" type="text/css" href="${base}/static/h-ui/css/style.css" />-->
	<link rel="stylesheet" type="text/css" href="${base}/static/football/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>${macro_config.common_title}</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
${head}
</head>
<body ${body}>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top" style="height:75px;">
		<div class="container-fluid cl pt-5">
			<span class="index_logo ml-40"><a href="${base}/index/indexPage!pageToIndex.action"><img src="${base}/static/football/images/logo.png" alt="" bordder="0"/></a></span>
			<span class="index_title ml-30" style="font-size:28px;">${macro_config.common_title}</span>
			<nav class="f-r index_top_nav">
                  <span class="mr-50">四川省教育厅&nbsp;&nbsp;&nbsp;&nbsp;<span id = "nameTop"></span></span>
                  <span class="mr-20"><a href="${base}/logout.action">退出</a></span>
            </nav>
		</div>
	</div>
</header>


<aside class="Hui-aside" style=" margin-top:22px;">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">

		<dl id="menu-product">
			<dt class="menu-no-child"><a data-title="正在直播" href="${base}/broadcast/broadcast!broadLiving.action"><i class="Hui-iconfont">&#xe6bd;</i>&nbsp;&nbsp;正在直播</a></dt>

		</dl>
		<dl id="">
			<dt class="menu-no-child"><a data-title="查看赛程" href="${base}/broadcast/broadcast!showSchedule.action"><i class="Hui-iconfont">&#xe695;</i>&nbsp;&nbsp;查看赛程</a></dt>
		</dl>
		<dl id="">
			<dt class="menu-no-child"><a data-title="已结束的比赛" href="${base}/broadcast/broadcast!gameFinal.action"><i class="Hui-iconfont">&#xe624;</i>&nbsp;&nbsp;已结束的比赛</a></dt>
		</dl>
	</div>
</aside>



<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>

<section class="Hui-article-box" style="margin-top:-10px;">
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">

			<#if (navi?size > 0)>
			<nav class="breadcrumb" style="padding-left:10px;"><i class="Hui-iconfont">&#xe67f;</i>&nbsp;&nbsp;<a href="${base}/login_view.action">首页</a> 
			<#assign keys = navi?keys>
			<#list keys as key><span class="c-gray en">&gt;</span><a href="${parseLink(navi[key])}"> ${key}</a> </#list><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
			</#if> 
			<#nested> 	
		</div>
	</div>
</section>

<script type="text/javascript" src="${base}/lib/layer/2.1/layer.js"></script> <!-- 引入layer弹出框.js -->
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.js"></script> <!-- 兼容处理等功能 -->
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.admin.js"></script> <!-- 封装页面所需的js函数 -->

</body>
</html>
</#macro>


<#macro intro tasks={}>
 <div class="explain-box">
 	<div class="explain"><#nested></div>
 </div>
<#if (tasks?size > 0)>
<div style="margin-top:5px;margin-top:5px">
<#assign keys = tasks?keys>
<#list keys as key>
<a href="${parseLink(tasks[key])}" class="navlink">${key}</a> 
</#list>
</div>
</#if>
</#macro>

<#macro trClass>
<#if lineNumber?exists>
<#assign lineNumber=lineNumber+1/>
<#else>
<#assign lineNumber=1/>
</#if>
class="style${lineNumber%2}"
</#macro>

<#macro explain width="100%" align="center">
<table class="explain" width="${width}" align="${align}">
<tr>
	<td>
	<#nested>
	</td>
</tr>
</table>
</#macro>

<#macro mustMark><span class="notNull">*</span></#macro>

<#macro menuMark><span style="font-family:color:#FF3300;font-weight:bold;">&gt;&gt;</span></#macro>

<#macro folderMark><span style="font-family:Wingdings;color:#FF3300;font-weight:normal;font-size:18px;">0</span></#macro>

<#macro fileMark><span style="font-family:Wingdings;color:#FF3300;font-weight:normal;font-size:22px;padding-left:3px;padding-right:2px;">3</span></#macro>

<#macro chartColumn width height="8" color="#0066CC"><span style="background:${color};height:${height};width:${width}"></span></#macro>

<#macro trChangeColor>
 onmouseout="this.style.backgroundColor=''" onmouseover="this.style.backgroundColor='#BFDFFF'"
</#macro>

<#macro trChangeDataColor>
 onmouseout="this.style.backgroundColor=''" onmouseover="this.style.backgroundColor='#E1F2B6'"
</#macro>

<#-- 处理分页参数 -->
<#function getPageUrl pageNum>
<#local pageUrl=base+fullUrlWithoutPageNum>
<#if pageUrl?ends_with("?")>
	<#return pageUrl + "pageNum=" + pageNum + "&token=" + token?if_exists>
<#else>
	<#if pageUrl?index_of("token") == -1>
		<#return pageUrl + "&pageNum=" + pageNum + "&token=" + token?if_exists>
	<#else>
		<#return pageUrl?substring(0,pageUrl?index_of("token")) + "pageNum=" + pageNum + "&token=" + token?if_exists>
		<#-- 这句话没有错，这句话没有错，这句话没有错，重要的事情说三遍！！！-->
	</#if>
</#if>
</#function>

<#-- 分页信息 -->
<#macro paging pagingList>
<#local pageCount=pagingList.pageCount>
<#local rowCount=pagingList.rowCount>
<#local pageNum=pagingList.pageNum>
<#local pageSize=pagingList.pageSize>
<#if rowCount == 0>
	<#if request.servletPath?starts_with("/manage")>
		<#if useFlag?exists>
		<div style="border:1px solid #666;padding:2 5 2 5;background:#efefef;color:#333">没有相关记录</div>
		<#else>
		<#assign useFlag = 1>
		</#if>
	<#else>
		<#if !useFlag?exists>
		<div style="border:1px solid #666;padding:2 5 2 5;background:#efefef;color:#333">没有相关记录</div>
		<#assign useFlag = 1>
		</#if>
	</#if>
<#else>
<div class="page">
	
	<span id="page_left">共 ${rowCount} 条记录 ${pageCount} 页</span>
	<span id="page_right">
	<#if (pageCount <= 11)>
		<#local startPage = 1>
		<#local endPage = pageCount>
	<#elseif (pageNum + 5 > pageCount)>
		<#local startPage = pageCount - 10>
		<#local endPage = pageCount>
	<#elseif (pageNum - 5 < 1)>
		<#local startPage = 1>
		<#local endPage = 11>
	<#else>
		<#local startPage = pageNum - 5>
		<#local endPage = pageNum + 5>
	</#if>
	<#if (pageCount == 1)>
	<a href="#" class="next">上一页</a>
	<a href="#" class="page-current">1</a>
	<a href="#" class="next">下一页</a>
	</#if>
	<#if (pageCount > 1)>
	<#if (pageNum != 1)>
	<#if (pageCount > 11)>
	<a href="${getPageUrl(1)}" class="next">首页</a>
	</#if>
	<a href="${getPageUrl(pageNum-1)}" class="next">上一页</a>
	<#else>
	<#if (pageCount > 11)><a href="" class="next">首页</a></#if>
	<a href="#" class="next">上一页</a></#if>
	<#list startPage..endPage as x>
	<#if x=pageNum>
	<a href="#" class="page-current">${x}</a>
	<#else>
	<a href="${getPageUrl(x)}">${x}</a>
	</#if>
	</#list>
	<#if (pageCount != pageNum)>
	<a href="${getPageUrl(pageNum+1)}" class="next">下一页</a>
	<#if (pageCount > 11)>
	<a href="${getPageUrl(pageCount)}" class="next">尾页</a>
	</#if>
	<#else><a href="#" class="next">下一页</a>
	<#if (pageCount > 11)><a href="#" class="next">尾页</a></#if>
	</#if>
	</#if>
	</span>
</div>
</#if>
</#macro>

<#-- 处理缩进 len 为ID长度，pre为前置内容 marker为项标记 -->
<#macro indent len pre="　" marker="|-">
<#local indentNum = len/3?int>
<#if (indentNum>1)><#list 2..indentNum as x>${pre}</#list></#if>${marker}
</#macro>

<#-- 日历控件 -->
<#macro cal name format="%Y-%m-%d" text="选择日期">
<#if calcount?exists>
<#assign calcount=calcount+1/>
<#else>
<#assign calcount=0/>
</#if>
<img id="cal${calcount}" src="${base}/components/calendar/skins/aqua/cal.gif" border="0" alt="${text}" style="cursor:pointer">
<script language="JavaScript">var cal${calcount} = calendar("${name}", "cal${calcount}", "${format}");</script>
</#macro>

<#-- 分割线 -->
<#macro tabLine colspan="1">
<tr>
	<td colspan="${colspan}" style="height:10px"></td>
</tr>
<tr>
	<td colspan="${colspan}" style="height:1px" background="${base}/images/table_bz_03.gif"></td>
</tr>
<tr>
	<td colspan="${colspan}" style="height:10px"></td>
</tr>
</#macro>

<#-- 交替变色 -->
<#macro trClass>
<#if count?exists>
<#assign count=count+1/>
<#else>
<#assign count=0/>
</#if>
<#if count%2=0>class="contenttd1"<#else>class="contenttd2"</#if>
</#macro>

<#-- 学习页面头部 -->
<#macro learnhead>
<table width="94%" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="F5F5F4">
    <tr>
        <td align="center"><a href="learning!latelyList.action"><img src="images/xxzx_09.gif" alt="" width="89" height="76" border="0"></a></td>
		<td align="center"><a href="learning!allList.action"><img src="images/xxzx_07.gif" alt="" width="65" height="76" border="0"></a></td>
	    <td align="center"><a href="learning!finishList.action"><img src="images/xxzx_11.gif" alt="" width="89" height="76" border="0"></a></td>
	    <td align="center"><a href="learning!overList.action"><img src="images/xxzx_13.gif" alt="" width="89" height="76" border="0"></a></td>
	</tr>
	<tr>
	    <td align="center"><a href="learning!latelyList.action"><font color="#000000">最近学习的课程</font></a></td>
		<td align="center"><a href="learning!allList.action"><font color="#000000">全部课程</font></a></td>
	    <td align="center"><a href="learning!finishList.action"><font color="#000000">已完成的课程</font></a></td>
	    <td align="center"><a href="learning!overList.action"><font color="#000000">过期课程</font></a></td>
	</tr>
</table>
</#macro>

<#-- 表格数据上方内容格式 -->
<#macro info>
<div class="info"><#nested></div>
</#macro>