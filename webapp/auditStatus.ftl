<@p.index setReferUrl=true>

	    <div class="container">

      

        <div class="body">
            <table class="table table-bordered">
            	 <thead>
                    <tr>
                        <td align="center"><a>文件名称</a></th>
                        <td align="center"><a>审核状态</a></th>
                    </tr>
                </thead>
                <tbody>
                	
                    <tr>
	                    <td align="center">
	                    	<a >
	                    		体育年度报表
	                    	</a>
	                    </td>
                      <td align="center">
                  			<#if sport?exists && sport == "0">
	                  			<a >待审核</a>
	                  		<#elseif sport?exists && sport == "1">
	                  			<a >审核通过</a>
	                  		<#elseif sport?exists && sport == "2">
	                  			<a >审核不通过</a>
	                  		<#else>
	                  			<a >填写未完成</a>
	                  		</#if>
                        </td>
                    </tr>
                    
                     <tr>
                        <td align="center">
                        <a>
                        	体育年度自评表
                        </a>
                        </td>
                         <td align="center">
                           	<#if selfSport?exists && selfSport == "0">
                      			<a >待审核</a>
                      		<#elseif selfSport?exists && selfSport == "1">
                      			<a >审核通过</a>
                      		<#elseif selfSport?exists && selfSport == "2">
                      			<a >审核不通过</a>
                      		<#else>
                      			<a >填写未完成</a>
                      		</#if> 
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="center">
                        <a>
                        	艺术年度报表
                        </a>
                        </td>
                       
                        
                         <td align="center">
                            	<#if art?exists && art == "0">
	                      			<a>待审核</a>
	                      		<#elseif art?exists && art == "1">
	                      			<a>审核通过</a>
	                      		<#elseif art?exists && art == "2">
	                      			<a >审核不通过</a>
	                      		<#else>
	                      			<a >填写未完成</a>
	                     	 	</#if> 
	   
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
	
 	<script src="${base}/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="${base}/lib/js/bootstrap.js" type="text/javascript"></script>
    <script src="lib/layer/2.1/layer.js"></script>
	</script>
</@p.index>