<@p.index setReferUrl=true>

	    <div class="container">

      

        <div class="body">
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <td align="center">
                            <a href="fileDownload.action">填写说明和相关文档</a>
                        </td>
                        <td align="center" colspan = " 3 ">
                          <button class="btn btn-success" onclick="window.location.href=('fileDownload.action')">导出</button>
                        </td>
                    </tr>
                    
                    <tr>
	                    <td align="center">
	                    	<a href="sport/sportFormTable.action">
	                    		体育报表.xls
	                    	</a>
	                    </td>
                      <td align="center">       
                            <button class="btn btn-success" onclick="window.location.href=('sport/sportFormTable.action')">查看</button>
                      </td>
                      <td align="center">
                            <button class="btn btn-success" onclick="window.location.href=('sport/sportFirstView.action')">修改</button>
                      </td>
                       <td align="center">
                           	<button class="btn btn-success" onclick="window.location.href=('sportDownload.action')">导出</button>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="center">
                        <a href="art/artFormTable.action">
                        	体育自评表.xls
                        </a>
                         <td align="center">
                            <button class="btn btn-success" onclick="window.location.href=('sport/sport_self_view.action')">查看</button>
                         </td>
                   		  <td align="center" colspan = "2">
                           	 <button class="btn btn-success" onclick="window.location.href=('sport/sportSelfView.action')">修改</button>
                        </td>
                        
                    </tr>
                    
                    
                    <tr>
                        <td align="center">
                        <a href="art/artFormTable.action">
                        	艺术报表.xls
                        </a>
                         <td align="center">
                            <button class="btn btn-success" onclick="window.location.href=('art/artFormTable.action')">查看</button>
                         </td>
                   		  <td align="center">
                           	 <button class="btn btn-success" onclick="window.location.href=('art/artFirstView.action')">修改</button>
                        </td>
                  		 <td align="center">
                          	<button class="btn btn-success" onclick="window.location.href=('artDownload.action')">导出</button>
                        </td>
                    </tr>
                    
                    
                </tbody>
            </table>
        </div>
    </div>
	
 	<script src="${base}/lib/js/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="${base}/lib/js/bootstrap.js" type="text/javascript"></script>
    <script src="lib/layer/2.1/layer.js"></script>
	<script>
		$(function(){

	    	<#if errorInfo?exists>
				      layer.msg('${errorInfo?if_exists}');
			</#if>
			
		})
	</script>
</@p.index>