<@p.index setReferUrl=true>
<div class="container">
    <div class="page-container" style="margin-top:50px;">
		
		

        <div class="col-xs-12 text-center">
            <h4 class="title">上传佐证（.doc或.docx）</h4>
        </div>
        <form id="form" class="bs-example bs-example-form" role="form" action="upload.action" method="post" enctype="multipart/form-data">
            <input id="token" type="hidden" name="token" value="${token?if_exists}"/>
			
            <div class="row">
                <div class="col-lg-12">
                    <div class="input-group">
                        <input type="text" class="form-control" readonly="readonly">
                        <input type="file" name="myUpload" style="position: absolute;z-index:9999;width:100%;height:34px;opacity: 0;">
                    <span class="input-group-btn">
                        <button id="button" class="btn btn-default" type="button" style=" ">点击上传</button>
                    </span>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top:50px;">
            	<div class="text-center">
            		<button id="submit" class="btn btn-success">提交</button>
            	</div>
            </div>
        </form>
    </div>
</div>
<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/js/bootstrap.js"></script>
<script src="${base}/lib/layer/2.1/layer.js"></script>
<script>
    $("#button").click(function(){
        $("input[type='file']").trigger("click");
    });
    $("input[type='file']").change(function(){ 
        $("input[type='text']").val($(this).val());
    });
     $("#submit").click(function(){
    	if(($("input[type='file']").val()=="")){
    		layer.msg("请选择文件");
    		return false;
    	}
    })
     $(function(){
		<#if errorInfo?exists>
		layer.msg('${(errorInfo)?if_exists}');
		</#if>
	})
</script>
</@p.index>