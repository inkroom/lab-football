<@p.index setReferUrl=true>

<form action="artEnd!getData.action" method="post" id="idForm">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>

    <div class="container">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">填写人信息</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-sort">
                <tbody>
                <tr class="text-c">
                    <td rowspan="6">填写人信息</td>
                    <td>填写人姓名</td>
                    <td><input class="input-text form-control" name="name" value="${name?if_exists}" id="name" onblur="checkName()"/>
                    </td>
                </tr>
                <tr class="text-c">
                    <td>手机号码</td>
                    <td><input class="input-text form-control" name="phone" value="${phone?if_exists}" id="phone" onblur="checkPhone()"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class=" col-sm-12 text-c">
    	 <button type="button" class="btn btn-success radius" onclick="window.location.href=('../art/artSelfView.action')"><strong>上一步</strong></button>
	     <button type="button" id="idbtn" class="btn btn-success radius" ><strong>完成</strong></button>
    </div>

</div>

</form>

<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>
<script>
	<#if errorInfo?exists>
		alert('${errorInfo?if_exists}');
	</#if>
	
	<#--验证手机号码是否合法-->
			function checkPhone(){
			   var x=document.getElementById("phone");
			   var tel=/^\d{7,11}$/;
			   var re = new RegExp(tel);
			   if (re.test(x.value)) {
       			 return true;
  		 	 }else{
  		 	    x.value="";
        		layer.msg("请填写正确号码");
        		return false;
       		 }
       		 return true;
		}
		
		<#--验证名字输入是否合法-->
		function checkName(){
		    var x=document.getElementById("name");
		     var reg =/^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/g;
 			if(x.value.length > 20 || x.value.length < 2){
 				x.value = "";
                layer.msg("姓名必须为2~20为纯中文或者纯英文，不包含其他字符！");
                flag = false;
                return false;
 			}
 			if(!reg.test(x.value)){
 				x.value = "";
                layer.msg("姓名必须为纯中文或纯英文，不能含有其他字符！");
                flag = false;
                return false;
            }
         	flag = true;
         	
            return true;
		}
		$("#idbtn").click(function(){
		if(checkName()&&checkPhone()){
            $("#idForm").submit();   
        }else{
			layer.msg("请填写正确完整的信息");        
        }
	});
</script>

</@p.index>