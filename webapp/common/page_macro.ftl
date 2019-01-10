<#include "config.ftl">
<#include "common_function.ftl">


<#macro index title=macro_config.default_title body="" head="" simple="false" setReferUrl=false>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="${base}/lib/css/bootstrap.min.css" rel="stylesheet">
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
  </style>
</head>
<body>
<nav>
   <a href="${base}/logout.action"><button class="btn btn-success" >安全退出</button></a>
</nav>
<div class="left">
    <div class="left-header">
        账号单位：${_LOGIN_USER_ID_?if_exists}
    </div>
    <ul>
        <li><a href="${base}/index.action"><span><img src="${base}/images/index/leftNavHome.png"></span>首页 </a></li> 
        <li><a href="${base}/sport/sportFirstView.action" ><span><img src="${base}/images/index/leftNavSports.png"></span>体育工作年度报表 </a></li>
        <li><a href="${base}/sport/sportSelfView.action" ><span><img src="${base}/images/index/leftNavSports.png"></span>体育工作年度自评表 </a></li>
        <li><a href="${base}/art/artFirstView.action" ><span><img src="${base}/images/index/leftNavArt.png"></span>艺术工作年度报表 </a></li>
        <li><a href="${base}/fileDownloadView.action"><span><img src="${base}/images/index/upload.png"	style="width:16px;height:16px"></span>汇总数据查看与导出</a></li> 
        <li><a href="${base}/auditStatus.action" ><span><img src="${base}/images/index/Magnifier.png" style="width:18px;height=18px"></span>审核结果</a></li>
        <li><a href="${base}/updateView.action"><span><img src="${base}/images/index/modify.png" style="width:18px"></span>修改密码</a></li> 
    </ul>
    <div class="left-footer">
        <div class="left-footer-content">
            <p><strong>主办：</strong>四川省教育厅体卫艺处</p>
             <p><strong>版本：</strong>1.0</p>
            <p><strong>技术支持：</strong>成都东软学院</p>
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
	
<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/js/bootstrap.js"></script>
</body>
</html>

</#macro>
