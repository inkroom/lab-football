<@p.index setReferUrl=true>



    <style>
        body{
            background-color: transparent;
        }
        
        .col-xs-12{
            text-align: center;
            padding: 3em 0;
        }
        .container{
        	 background-color: transparent;
        
        	 
        }

    </style>

    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <img src="${base}/images/index/top/titleName.png">
            </div>
        </div>
        
        <div class="row" align="center" style="font-size:20px;margin-bottom:16px;">
        	在填写报表前，请查看相关填<a href="fileDownload.action">规范文档<a>
        </div> 
        <div class="row" align="center" style="font-size:20px;margin-bottom:16px;">
        	
        </div> 
        
        <div class="row">
            <div class="col-xs-4">
                <a href="#"><img src="${base}/images/index/test/报表按钮图片.jpg"></a>
            </div>
            <div class="col-xs-4">
                <a href="#"><img src="${base}/images/index/test/报表按钮图片2.jpg"></a>
            </div>
            <div class="col-xs-4">
                <a href="#"><img src="${base}/images/index/test/报表按钮图片3.jpg"></a>
            </div>
           
        </div>
    </div>
 	<script src="${base}/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="${base}/lib/js/bootstrap.js" type="text/javascript"></script>
    <script src="${base}/lib/layer/2.1/layer.js"></script>
    <script>
    	
    	$(function(){


	    	<#if errorInfo?exists>
				      layer.msg('${errorInfo?if_exists}');
			</#if>
			
			<#if isFinshMessage?exists>
				      layer.msg('${isFinshMessage?if_exists}');
			</#if>
			
		})
    	
    </script>
</@p.index>