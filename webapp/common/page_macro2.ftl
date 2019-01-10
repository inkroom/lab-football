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
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
    <script type="text/javascript" src="${base}/lib/jquery/1.9.1/jquery.min.js"></script><!-- 引入jquery框架 -->

<!--[if IE it 9]>
    <script type="text/javascript" src="./${base}/lib/html5.js"></script>
    <script type="text/javascript" src="./${base}/lib/respond.min.js"></script>
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
                <span class="index_logo ml-40"><img src="${base}/static/football/images/logo.png" alt=""/></span>
                <span class="index_title ml-30" >${macro_config.common_title}</span>
                <nav class="f-r index_top_nav">
                    <span class="mr-50">四川省教育厅&nbsp;&nbsp;&nbsp;&nbsp;管理账号：SCSJYT22334</span>
					<span class="mr-10"><a href="#">管理</a></span>
                    <span class="mr-20"><a href="${base}/logout.action">退出</a></span>
                </nav>
            </div>
	</div>
</header>


<aside class="Hui-aside" style=" margin-top:22px;">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<dl>
			<dt class="menu-no-child"><a _href="account/accountManage.html" data-title="账号管理" href="javascript:void(0)"><i class="Hui-iconfont">&#xe62c;</i>&nbsp;&nbsp;账号管理</a></dt>
        </dl>
		<dl>
			<dt><i class="Hui-iconfont">&#xe639;</i>&nbsp;&nbsp;赛事管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="match/matchRelease.html" data-title="赛事发布" href="javascript:void(0)">赛事发布</a></li>
					<li><a _href="match/addTeamMember.html" data-title="赛事报名" href="javascript:void(0)">赛事报名</a></li>
					<li><a _href="" data-title="赛事组织" href="${base}/match/matchorganization!showMatchO.action">赛事组织</a></li>
					<li><a _href="" data-title="赛事记录" href="javascript:void(0)">赛事记录</a></li>
					<li><a _href="" data-title="赛事记录" href="javascript:void(0)">入场验证</a></li>
					<li><a _href="" data-title="赛事记录" href="javascript:void(0)">导入记录</a></li>
				</ul>
			</dd>
		</dl>
		<dl>
			<dt><i class="Hui-iconfont">&#xe62b;</i>&nbsp;&nbsp;球队管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="team/teamAdd.html" data-title="创建球队" href="javascript:void(0)">创建球队</a></li>
					<li><a _href="team/teamInfo.html" data-title="查看球队" href="javascript:void(0)">查看球队</a></li>
					<li><a _href="team/teamManage.html" data-title="修改球队信息" href="javascript:void(0)">修改球队信息</a></li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt><i class="Hui-iconfont">&#xe616;</i>&nbsp;&nbsp;信息查询发布<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="select/selectReport.html" data-title="信息报表查询" href="javascript:void(0)">信息报表查询</a></li>
					<li><a _href="" data-title="信息发布" href="javascript:void(0)">信息发布</a></li>
				</ul>
			</dd>
		</dl>
		<dl>
			<dt><i class="Hui-iconfont">&#xe637;</i>&nbsp;&nbsp;公示管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="publicity/publicList.html" data-title="公示信息" href="javascript:void(0)">公示信息</a></li>
					<li><a _href="publicity/complainDeal.html" data-title="投诉处理" href="javascript:void(0)">投诉处理</a></li>
					<li><a _href="publicity/myComplain.html" data-title="我的投诉" href="javascript:void(0)">我要举报</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>

<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>

<section class="Hui-article-box" style=" margin-top:22px;">
	<div id="iframe_box" class="Hui-article"  margin-top:0px;">
	     <#if (navi?size > 0)>
          <div class="site"><img alt="" src="${base}/images/icon.jpg" />当前位置：       
            <#assign keys = navi?keys>
            <#list keys as key>&gt;&gt;<a href="${parseLink(navi[key])}"> ${key}</a> </#list>
          </div>
    </#if> 
		<div class="show_iframe">
			 <#nested> 	
		</div>
	</div>
</section>
  <div id="footer" class="layout">
    <div class="text">    四川省教育厅版权所有    <a href="http://www.ccniit.com/" target="_new">成都东软学院技术支持</a></p> </div>
  </div>
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
<#return pageUrl + "pageNum=" + pageNum>
<#else>
<#return pageUrl + "&pageNum=" + pageNum>
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
<table>
<tr >
	
	<td style="line-height:150%" >
	
	共 ${rowCount} 条记录 ${pageCount} 页
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
	<#if (pageCount > 1)>
	<#if (pageNum != 1)>
	<#if (pageCount > 11)>
	<a class="page" href="${getPageUrl(1)}" style="font-family:Webdings" title="首页">9</a>
	</#if>
	<a class="page" href="${getPageUrl(pageNum-1)}" style="font-family:Webdings" title="上页">3</a>
	<#else>
	<#if (pageCount > 11)><span style="font-family:Webdings;color:#999">9</span></#if>
	<span style="font-family:Webdings;color:#999">3</span></#if>
	<#list startPage..endPage as x>
	<#if x=pageNum>
	<span class="selectedPage">${x}</span>
	<#else>
	<span class="noSelectedPage"><a class="page" href="${getPageUrl(x)}">${x}</a></span>
	</#if>
	</#list>
	<#if (pageCount != pageNum)>
	<a class="page" href="${getPageUrl(pageNum+1)}" style="font-family:Webdings" title="下页">4</a>
	<#if (pageCount > 11)>
	<a class="page" href="${getPageUrl(pageCount)}" style="font-family:Webdings" title="尾页">:</a>
	</#if>
	<#else><span style="font-family:Webdings;color:#999">4</span>
	<#if (pageCount > 11)><span style="font-family:Webdings;color:#999">:</span></#if>
	</#if>
	</#if>
	
	</td>
	
</tr>
</table>
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