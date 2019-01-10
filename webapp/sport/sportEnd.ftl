<@p.index setReferUrl=true>

<form action="sportEnd!submitData.action" method="post" id="idForm">
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
                    <td><input class="input-text form-control" name="name" value="${name?if_exists}" id="name" onblur="checkName(this.id)"/>            
                </tr>
                <tr class="text-c">
                    <td>手机号码</td>
                    <td><input class="input-text form-control" name="phone" value="${phone?if_exists}" id="phone" onblur="validatemobile(this.id)"/>                 
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
    	 <button type="button" class="btn btn-success radius" onclick="last()"><strong>上一步</strong></button>
	     <button type="button" id="idbtn2222" class="btn btn-success radius"><strong>提交</strong></button>
    </div>

</div>

</form>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>

<script>
var flag = false;

$("#idbtn2222").click(function(){
		  $("#idForm").submit(); 
	});
	
	<#if errorInfo?exists>
		layer.msg('${errorInfo?if_exists}');
	</#if>
	function last() {
		if(flag)
		{
			$("#idForm").attr("action","sportThirdView.action");
			$("#idForm").submit();
		}else{
			layer.msg("未填写完整，不能提交！");	
		}
			
	}

<#--验证是否是整数-->
function validateNum(val){
  var patten3 = /^([0-9][0-9]{0,20})$/;
  var patten4 = /^[0-9]$/;
  if(val!=null && val.length>0 &&  patten3.test(val)){
     return false;
   }else{
     return true;
   }
}

<#--验证电话号码-->
function validatemobile(val){
   var x=document.getElementById(val);
   if (validateNum(x.value)) {
      if (x.value == null || x.value.length == 0) {
          x.value = "";
	      layer.msg("电话号码未填写");
	      flag = flase;
	      return false;
      }else{
          x.value = "";
          layer.msg("不符合规范的电话的号码，请输入重新输入!");
         flag = flase;
	      return false;
	   }
   }
   if(x.value.length > 21 || x.value.length < 6){
   		x.value = "";
          layer.msg("请输入正确的电话号码！");
          flag = flase;
	      return false;
   
   }
 flag = true;
 return true;
}	

<#--验证姓名-->
function checkName(val){
		
		    var x=document.getElementById(val);
		     var reg =/^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/g;
 			if(x.value.length > 20 || x.value.length < 2){
 				x.value = "";
                layer.msg("姓名必须为2~20为纯中文或者纯英文，不包含其他字符！");
             	flag = flase;
                return false;
 			}
 			if(!reg.test(x.value)){
 				x.value = "";
                layer.msg("姓名必须为纯中文或纯英文，不能含有其他字符！");
                flag = flase;
                return false;
            }
        
         	flag = true;
            return true;
		}
	
</script>

</@p.index>