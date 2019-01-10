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
                <span class="index_logo ml-40"><img src="${base}/static/football/images/logo.png" alt=""/></span>
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
					<li><a href="${base}/publicity/matchPublicity!getAllMatch.action" data-title="赛事信息公示" href="javascript:void(0)">赛事信息公示</a></li>
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
    <script type="text/javascript" src="${base}/lib/jquery/1.9.1/jquery.min.js"></script><!-- 引入jquery框架 -->
    <!--[if IE it 9]>
    <script type="${base}/text/javascript" src="lib/html5.js"></script>
    <script type="${base}/text/javascript" src="lib/respond.min.js"></script>
    <script type="${base}/text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
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
    <link rel="stylesheet" href="${base}/lib/slide/jquery.slideBox.css">
    <title>${macro_config.common_title}</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
        <style>
        body{
            background: url("${base}/static/football/images/index/background.jpg");
        }

        .navbar{
            padding: 3px 10%;
            color: white;
            line-height: 30px;
            background-color: rgb(38,152,64);
        }
        .navbar button{
            float: right;
            background-color: rgb(75,220,67);
        }

        #container{
            height:1000px;
            width: 100%;
            background:url("${base}/static/football/images/index/bg1.jpg") no-repeat -350px 0;
        }
        /*#logo{margin-top: 20px;}*/
        #nav{margin: 10px auto;}
        #nav ul li{float:left;font-size: 16px;padding: 2px 7px;}
        #nav ul li .nav_icon{color:rgb(143,146,145);padding-right: 4px;font-size: 19px;}
        
        #content td{background-color:white;}

        #lunbo-div{background: white;border-top: solid 3px red;padding:10px 0;}
        #lunbo-div .lunbo-r{padding-top: 10px;}

        .article-list{margin-top:10px;}
        .article-list li{padding-left:8px;padding-bottom:30px;background: url("${base}/static/football/images/index/dot2.png")no-repeat left 7px;}
        .article-list .title{float: left;max-width: 400px;}
        .article-list .time{float: right;font-size: 12px;color: rgb(192,149,147)}

        #reg-match{background: white;height: 40px;margin:20px 0;line-height: 40px;padding: 0 40px;letter-spacing: 1px;}

        .article-block div{background: white;}

        .article-cate li{float: left}

        .information li{border-bottom: solid 2px rgb(211,231,249);padding:5px 13.4%;  cursor: pointer;}
        
        .notice li{border-bottom: solid 2px rgb(211,231,249);padding:5px 4%;  cursor: pointer;}

        .article-cate li.active,.article-cate li:hover{border-bottom: solid 2px rgb(0,178,65);color: rgb(255,66,0)}

        .elegant-appearance{background: white;margin-top: 20px;}
        .fc-img{padding: 10px 0px;width: 214.5px;height: 143px;}

        .friend-link{background: rgb(162,239,229);margin-top: 20px;padding: 30px 0;}
        .footer{background: rgb(232,246,250);margin-top: 20px;color: black}
        .footer a{background: rgb(232,246,250);margin-top: 20px;color: black}

        .article-list .more{background: none;}
        .more a{color: rgb(68,160,100);font-weight: bold;float: right;}

        #lb img{width: 520px;}
    </style>
${head}
</head>
<body ${body}>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <span>2016年5月10日&nbsp;星期三</span>
        <a href="${base}/index/indexLogin!pageToIml.action" href="javascript:void(0)"><button class="btn btn-success radius Hui-iconfont">&#xe62d; 网站管理</button></a>
    </div>
</header>
<div id="container" class="container">
    <div style="width:900px;margin: 0 auto;">
        <div id="logo"><img src="${base}/static/football/images/index/logoAndText.png"></div>
        <div class="col-md-12 mt-20" id="nav">
        	<div style="width:1090px;margin:auto;height:34px;">
            <ul class="">
                <li><i class="Hui-iconfont nav_icon">&#xe625;</i><a href="${base}/index/indexPage!pageToIndex.action">首页</a></li>
                <li class=><i class="Hui-iconfont nav_icon">&#xe643;</i><a href="${base}/account/accountLoginDispatcher.action">机构管理</a></li>
                <li><i class="Hui-iconfont nav_icon">&#xe60a;</i> <a href="${base}/player/playerLoginDispatcher.action">球员管理</a></li>
                <li><i class="Hui-iconfont nav_icon">&#xe62c;</i><a href="${base}/coach/login.action">教练员管理</a></li>
                <li><i class="Hui-iconfont nav_icon">&#xe62b;</i><a href="${base}/team/teamLogin!pageToTl.action">球队管理</a></li>
                <li><i class="Hui-iconfont nav_icon">&#xe653;</i><a href="${base}/match/recorderLogin!login.action">现场管理</a></li>
                <li><i class="Hui-iconfont nav_icon">&#xe64f;</i><a href="${base}/broadcast/broadcast!broadLiving.action">比赛现场直播</a></li>
                <li><i class="Hui-iconfont nav_icon">&#xe6c6;</i><a href="${base}/publicity/matchInfor!searchMatchInfor.action">比赛信息公示</a></li>
            </ul>
            </div>
        </div>
        <div id="content">
        	<#nested>
        </div>
        
         <div class="col-md-12 friend-link">
            <div class="col-md-3">
                <span class="select-box">
                  <select class="select" size="1" name="demo1">
                      <option value='void(0)'>---市州教育行政部门---</option><option value="window.open('http://www.cdedu.gov.cn/','_blank');">成都市教育局</option><option value="window.open('http://www.my-edu.net/','_blank');">绵阳市教育局</option><option value="window.open('http://www.dy-edu.cn/','_blank');">德阳市教育局</option><option value="window.open('http://www.sczgjy.gov.cn/','_blank');">自贡市教育局 </option><option value="window.open('http://www.pzhedu.cn/','_blank');">攀枝花市教育局</option><option value="window.open('http://www.sclzjy.com/','_blank');">泸州市教育局</option><option value="window.open('http://www.gyedu.gov.cn/','_blank');">广元市教育局</option><option value="window.open('http://www.snsedu.gov.cn/','_blank');">遂宁市教育局</option><option value="window.open('http://www.scnjedu.net/','_blank');">内江市教育局</option><option value="window.open('http://www.sclsedu.gov.cn/','_blank');">乐山市教育局</option><option value="window.open('http://www.zysjyj.com/','_blank');">资阳市教育局</option><option value="window.open('http://www.ybedu.gov.cn/html/','_blank');">宜宾市教育局</option><option value="window.open('http://www.ncedu.net.cn','_blank');">南充市教育局</option><option value="window.open('http://www.yaei.gov.cn','_blank');">雅安市教育局</option><option value="window.open('http://www.gaedu.gov.cn/','_blank');">广安市教育局</option><option value="window.open('http://www.bzedu.com.cn/','_blank');">巴中市教育局</option><option value="window.open('http://www.msedu.cn','_blank');">眉山市教育体育局</option><option value="window.open('http://www.abedu.gov.cn/','_blank');">阿坝州教育局</option><option value="window.open('http://www.lszedu.cn/','_blank');">凉山彝族自治州教育局</option><option value="window.open('http://www.dzei.net/','_blank');">达州市教育局</option><option value="window.open('http://www.gzzedu.gov.cn/f','_blank');">甘孜州教育局</option>
                  </select>
                </span>
            </div>
            <div class="col-md-3">
                <span class="select-box">
                  <select class="select" size="1" name="demo1">
                      <option value='void(0)'>---省内各高校---</option><option value="window.open('http://www.scu.edu.cn/','_blank');">四川大学</option><option value="window.open('http://www.uestc.edu.cn/','_blank');">电子科技大学</option><option value="window.open('http://www.swjtu.edu.cn/','_blank');">西南交通大学</option><option value="window.open('http://www.swufe.edu.cn/','_blank');">西南财经大学</option><option value="window.open('http://www.sicau.edu.cn/','_blank');">四川农业大学</option><option value="window.open('http://www.swun.edu.cn/','_blank');">西南民族大学</option><option value="window.open('http://www.cdut.edu.cn/','_blank');">成都理工大学</option><option value="window.open('http://www.swust.edu.cn/','_blank');">西南科技大学</option><option value="window.open('http://www.xhu.edu.cn/','_blank');">西华大学</option><option value="window.open('http://www.cdutcm.edu.cn/','_blank');">成都中医药大学</option><option value="window.open('http://www.sicnu.edu.cn/','_blank');">四川师范大学</option><option value="window.open('http://www.cwnu.edu.cn/','_blank');">西华师范大学</option><option value="window.open('http://www.swpu.edu.cn/','_blank');">西南石油大学</option><option value="window.open('http://www.cuit.edu.cn/','_blank');">成都信息工程大学</option><option value="window.open('http://www.suse.edu.cn/','_blank');">四川理工学院</option><option value="window.open('indexPage!pageToIndex.action','_blank');">更多．．．</option></select>

                    </select>
                </span>
            </div>
            <div class="col-md-3">
                <span class="select-box">
                  <select class="select" size="1" name="demo1">
                      <option value='void(0)'>---直属单位网站---</option><option value="window.open('http://www.sceea.cn/','_blank');">四川省教育考试院</option><option value="window.open('http://www.scjks.net/','_blank');">四川省教育科学研究所</option><option value="window.open('http://www.scjyzb.net/','_blank');">四川教育装备网</option><option value="window.open('http://www.scyxhq.com/','_blank');">学校后勤与产业网</option><option value="window.open('http://www.scdjg.com.cn/','_blank');">四川省电教馆</option><option value="window.open('http://www.cdxzzx.com/','_blank');">四川省成都西藏中学</option><option value="window.open('http://www.ntjx.org/','_blank');">内江铁路机械学校</option><option value="window.open('http://www.scxszz.cn/','_blank');">四川学生资助网</option><option value="window.open('http://jyzdzx.scedu.net/','_blank');">四川省大学生就业创业服务网</option><option value="window.open('http://psc.scedu.net/','_blank');">四川省普通话水平测试中心</option>
                  </select>
                </span>
            </div>
            <div class="col-md-3">
                <span class="select-box">
                  <select class="select" size="1" name="demo1">
                      <option value='void(0)'>---其他教育网站---</option><option value="window.open('http://www.jyb.cn/','_blank');">中国教育新闻网</option><option value="window.open('http://www.ict.edu.cn/','_blank');">中国教育信息化网</option><option value="window.open('http://www.qspfw.edu.cn/','_blank');">教育部全国青少年普法网</option><option value="window.open('http://www.scedu.com.cn/','_blank');">四川省教育资源公共服务平台</option><option value="window.open('http://www.scjyjc.com.cn/','_blank');">四川省基础教育监测评估中心</option><option value="window.open('http://www.scmbjy.net/','_blank');">四川民办教育网</option>
                  </select>
                </span>
            </div>
        </div>
        
        <div class="col-md-12 footer text-c">
            <span><a href="#">联系方式</a> </span>
            <span>|</span>
            <span><a href="#">使用帮助</a> </span>
            <span>主办:四川省教育厅</span>
            <span>制作维护:四川省教育管理信息中心</span>
            <br>
            <span>投稿邮箱:高等院校:scgxxx@163.com</span>
            <span>市州区县:scgxxx@163.com</span>
            <span>基层学校:scgxxx@163.com</span>
        </div>
        
    </div>
</div>

<script type="text/javascript" src="${base}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base}/lib/layer/2.1/layer.js"></script> <!-- 引入layer弹出框.js -->
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.js"></script> <!-- 兼容处理等功能 -->
<script type="text/javascript" src="${base}/static/h-ui/js/H-ui.admin.js"></script> <!-- 封装页面所需的js函数 -->
<script src="${base}/lib/slide/jquery.slideBox.js"></script>
<script type="text/javascript" src="${base}/lib/layer/2.1/layer.js"></script>
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
	<!--[if IE it 9]>
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
			<span class="index_logo ml-40"><img src="${base}/static/football/images/logo.png" alt=""/></span>
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