<@p.index setReferUrl=true>

<form action="artThird.action"  method="post" id="idForm">
	<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
	<div class="container">
	    <div class="page-container">
	        <div class="col-xs-12 text-center">
	            <h4 class="title">条件保障</h4>
	        </div>
	         <div class="mt-20">	
	            <table class="table table-border table-bordered table-bg table-sort">
	            <tbody>
	                <tr class="text-c">
	                    <td rowspan="3">音乐专用教室</td>
	                    <td>应有</td>
	                    <td><input name="musClassroomIdeal" id="idMusClassroomIdeal" placeholder="单位(间)" class="input-text form-control" onblur="calMusPequired()" value="${(musClassroomIdeal)?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldMusClassroomIdeal)?if_exists}</label></td>
	                </tr>
	                <tr class="text-c">
	                    <td>实有</td>
	                    <td><input name="musClassroomPresent" id="idMusClassroomPresent" placeholder="单位(间)" class="input-text form-control" onblur="calMusPequired()" value="${(musClassroomPresent)?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldMusClassroomPresent)?if_exists}</label></td>
	                </tr>
	               <tr class="text-c">
	                    <td>缺额</td>
	                    <td><input disabled="disabled" name="musClassroomPequired" id="idMusClassroomPequired" class="input-text form-control" value="${(musClassroomPequired)?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldMusClassroomPequired)?if_exists}</label></td></td>
	                </tr>
	
	                <tr class="text-c">
	                    <td rowspan="3">美术专用教室</td>
	                    <td>应有</td>
	                    <td><input name="paintClassroomIdeal" id="idPaintClassroomIdeal" placeholder="单位(间)" class="input-text form-control" onblur="calPaintPequired()" value="${(paintClassroomIdeal)?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldPaintClassroomIdeal)?if_exists}</label></td>
	                </tr>
	                <tr class="text-c">
	                    <td>实有</td>
	                    <td><input  name="paintClassroomPresent" id="idPaintClassroomPresent" placeholder="单位(间)" class="input-text form-control" onblur="calPaintPequired()" value="${(paintClassroomPresent)?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldPaintClassroomPresent)?if_exists}</label></td>
	                </tr>
	                <tr class="text-c">
	                    <td>缺额</td>
	                    <td><input disabled="disabled" name="paintPequired" id="idPaintPequired" class="input-text form-control" value="${(paintPequired)?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldPaintPequired)?if_exists}</label></td>
	                </tr>
	                
	                <#if isEquipQualified?exists && isEquipQualified=="1">
			            <tr class="text-c">
			                <td rowspan="2">器材配备达标</td>
							<td>器材配备达标是否达标</td>
							<td><select class="form-control" name="isEquipQualified" id="idIsEquipQualified" onchange="bao(this.options[this.options.selectedIndex].value)" >
									<option value="1" selected>是</option>
									<option value="2">否</option>
								</select>
							</td>
			            </tr> 
		            <#else>
			            <tr class="text-c">
			                <td rowspan="2">器材配备达标</td>
							<td>器材配备达标是否达标</td>
							<td><select class="form-control" name="isEquipQualified" id="idIsEquipQualified" onchange="bao(this.options[this.options.selectedIndex].value)" >
									<option value="1">是</option>
									<option value="2" selected>否</option>
								</select>
							</td>
			            </tr> 
		            </#if>          
					<tr>
						<td>配备达标资金缺额</td>
						<td><input name="fundRequired" id="idFundRequired" onblur="checkFundRequired()" placeholder="若达标此项不填，单位(万元)" class="input-text form-control" value="${fundRequired?if_exists}"/>
						<label class="control-label">前一年：${(oldData.oldFundRequired)?if_exists}</label></td>
					</tr>
					
					
					<tr class="text-c">
	                    <td colspan="2">其他艺术活动室</td>
	                    <td><input name="artClassroomOther" id="idArtClassroomOther" onblur="checkArtClassroomOther()" placeholder="单位(间)" class="input-text form-control" value="${artClassroomOther?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldArtClassroomOther)?if_exists}</label></td>
	                </tr>
					<tr class="text-c">
	                    <td colspan="2">艺术场馆</td>
	                    <td><input name="artVenuesNum" id="idArtVenuesNum" onblur="checkArtVenNum()" placeholder="单位(个)" class="input-text form-control" value="${artVenuesNum?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldArtVenuesNum)?if_exists}</label></td>
	                </tr>
					<tr class="text-c">
	                    <td colspan="2">场馆面积<font color="red">（若艺术场馆数为0个，此处请填0）</font></td>
	                    <td><input name="venuesArea" id="idVenuesArea" onblur="checkArtVenNum()" placeholder="单位(㎡)" class="input-text form-control" value="${venuesArea?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldVenuesArea)?if_exists}</label></td>
	                </tr>
					<tr class="text-c">
	                    <td colspan="2">自评得分</td>
	                    <td><input name="selfRemarkEnsurance" id="idSelfRemarkEnsurance" onblur="checkSelfRemarkEnsurance()" placeholder="满分20分" class="input-text form-control" value="${selfRemarkEnsurance?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldSelfRemarkEnsurance)?if_exists}</label></td>
	                </tr>
	                </tbody>
	            </table>
	        </div>
	    </div>
	    <div class=" col-sm-12 text-c">
	        <button type="button" class="btn btn-success radius"  onclick="window.location.href=('../art/artSecondView.action')"><strong>上一步</strong></button>
	        <button type="button" id="idBtn" class="btn btn-success radius" onmouseover="changeTmp(0)" onmouseout="changeTmp(1)"><strong>下一步</strong></button>
	    </div>
	
	</div>
</form>

<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>

<script >
<#--初始化设置配备达标资金缺额输入框是否可用-->
 	function initFundRequired(){
		var id = window.document.getElementById("idIsEquipQualified");
		var index = id.selectedIndex;
		var value = id.options[index].value;
		if(value=="1"){
			window.document.getElementById("idFundRequired").type = "hidden";
		}else{
			window.document.getElementById("idFundRequired").type = "text";
		}
	}
	initFundRequired();
    var tmp = 1;
    
<#--改变tmp的值-->
	function changeTmp(varTmp){
		tmp = varTmp;
	}
	
<#--验证正整数-->
	function validateNum(val){
		var patten3 = /^([1-9][0-9]{0,7})$/;
		var patten4 = /^[0-9]$/;
		if(val!=null && val.length>0 && (patten3.test(val) || patten4.test(val))){
			return false;
		}else{
			return true;
		}
	}  
<#--验证小数和整数-->
	function validateFloat(val){
		var patten1 = /^([1-9][0-9]{0,7}\.([0-9]{1,4}))$/;
		var patten2 = /^([0-9]\.[0-9]{1,4})$/;
		if(val!=null && val.length>0 && val.length<14 && (patten1.test(val) || patten2.test(val) || !validateNum(val) )){
			return false;
		}else{
			return true;
		}
	}
	
<#--1.验证音乐各类型教室输入是否合法-->
	function calMusPequired() {
 			var musIdeal = window.document.getElementById("idMusClassroomIdeal").value;
 			var musPresent = window.document.getElementById("idMusClassroomPresent").value;
 			var idmusIdeal = window.document.getElementById("idMusClassroomIdeal");
 			var idmusPresent = window.document.getElementById("idMusClassroomPresent");
 			var idmusPequired = window.document.getElementById("idMusClassroomPequired");
 			var flag = false;
 			
 			if(validateNum(musIdeal)){
 				if(musIdeal!=null && musIdeal.length>0){
					layer.msg("音乐专用教室应有数量只能输入正整数或您输入的数据过大");
					idmusIdeal.value = "";
				}
				idmusPequired.value = "";
			}
			if(validateNum(musPresent)){
				if(musPresent!=null && musPresent.length>0){
					layer.msg("音乐专用教室实有数量只能输入正整数或您输入的数据过大");
					idmusPresent.value = "";
				}
				idmusPequired.value = "";
			}
 			
 			if(musIdeal!=null && musPresent!=null && musIdeal.length>0 && musPresent.length>0) {
 				if(!isNaN(musIdeal) && !isNaN(musPresent)) {
 					var i = parseInt(musIdeal) - parseInt(musPresent);
 					if(i < 0){
 						i = 0;
 					}
 					idmusPequired.value = "" + i;
 					flag = true;
 				}
 			}else{
 				idmusPequired.value = "";
 			}	 	 
 			
 			return flag;
 	}
 	
 <#--2.验证美术各类型教室输入是否合法-->
 	function calPaintPequired() {
 			var paintIdeal = window.document.getElementById("idPaintClassroomIdeal").value;
 			var paintPresent = window.document.getElementById("idPaintClassroomPresent").value;
 			var idPaintIdeal = window.document.getElementById("idPaintClassroomIdeal");
 			var idPaintPresent = window.document.getElementById("idPaintClassroomPresent");
 			var paintPequired = window.document.getElementById("idPaintPequired");
 			var flag = false;
 			
 			if(validateNum(paintIdeal)){
 				if(paintIdeal!=null && paintIdeal.length>0){
	 				layer.msg("美术专用教室应有数量只能输入正整数或您输入的数据过大");
					idPaintIdeal.value ="";
 				}
				
				paintPequired.value = "";
			}
			if(validateNum(paintPresent)){
				if(paintPresent!=null && paintPresent.length>0){
 					layer.msg("美术专用教室实有数量只能输入正整数或您输入的数据过大");
					idPaintPresent.value = "";
 				}
				paintPequired.value = "";
			}
 			
 			if(paintIdeal!=null && paintPresent!=null && paintIdeal.length>0 && paintPresent.length>0) {
 				if(!isNaN(paintIdeal) && !isNaN(paintPresent)) {
 					var i = parseInt(paintIdeal) - parseInt(paintPresent);
 					if(i < 0){
 						i = 0;
 					}
 					paintPequired.value = "" + i;
 					flag = true;
 				}
 			}else{
 				paintPequired.value = "";
 			}
 			
 			return flag;
 	}
	
<#--3.验证配备达标资金缺额-->
	function checkFundRequired() {
		var idnum = window.document.getElementById("idFundRequired");
		if(validateFloat(idnum.value)){
			if(idnum.value!=null && idnum.value.length>0){
				layer.msg("配备达标资金缺额只能输入正数或您输入的数据过大");
				idnum.value = "";
			}
			return false;
		}else{
			if(tmp == 1 && parseFloat(idnum.value)>1000){
				layer.msg("注意：<br/>配备达标资金缺额单位为万元,<br/>当前已超过1000万元");
			}
			return true;
		}
	}
  
<#--4.验证其他艺术活动室-->
	function checkArtClassroomOther() {
		var idnum = window.document.getElementById("idArtClassroomOther");
		if(validateNum(idnum.value)){
			if(idnum.value!=null && idnum.value.length>0){
				layer.msg("其他艺术活动室数量只能输入正整数或您输入的数据过大");
				idnum.value = "";
			}
			return false;
		}else{
			return true;
		}
	}

<#--验证艺术场馆和面积-->
	function checkArtVenNum(){
		var idnum = window.document.getElementById("idArtVenuesNum");
		var idnum1 = window.document.getElementById("idVenuesArea");
		var flag = true;
		<#--验证场馆个数-->
		if(validateNum(idnum.value)){
			if(idnum.value!=null && idnum.value.length>0){
				layer.msg("艺术场馆数量只能输入正整数或您输入的数据过大");
				idnum.value = "";
			}
			flag = false;
		}
		
		<#--验证面积-->
		if(validateFloat(idnum1.value)){
			if(idnum1.value!=null && idnum1.value.length>0){
				layer.msg("场馆面积只能输入正数或您输入的数据过大");
				idnum1.value = "";
			}
			flag = false;
		}else{
			if(parseFloat(idnum1.value)>=10000){
				layer.msg("场馆面积输入的数据过大");
				idnum1.value = "";
				flag = false;
			}
		}
		
		<#--验证数据逻辑-->
		if(flag == true){
			<#--场馆个数为0，面积不为0-->
			if(idnum.value=="0" && idnum1.value!="0"){
				idnum.value = "";
				idnum1.value = "";
				layer.msg("没有艺术场馆，则场馆面积应当为0");
				flag = false;
			}
			<#--场馆个数不为0，面积不能为0-->
			if(idnum.value!="0" && idnum1.value=="0"){
				idnum.value = "";
				idnum1.value = "";
				layer.msg("有艺术场馆时，场馆面积不能为0");
				flag = false;
			}
		}
		
		return flag;
	}
	
<#--7.验证自评得分-->
	function checkSelfRemarkEnsurance() {
		var idnum = window.document.getElementById("idSelfRemarkEnsurance");
		var flag = false;
		if(validateNum(idnum.value)){
				if(idnum.value!=null && idnum.value.length>0){
					layer.msg("自评得分只能输入正整数");
					idnum.value = "";
				}
		}else{
			if(20<parseInt(idnum.value) || parseInt(idnum.value)<0){
				layer.msg("自评得分满分为20分");
				idnum.value = "";
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
<#--最终判断所有的数据是否合法，判断是否提交表单-->
	$("#idBtn").click(function(){
		
		var flag = true;
		tmp = 0;
		var num = document.getElementById("idIsEquipQualified").value;
		if(num=="2"){
			 flag = checkFundRequired();
		}
		if(flag && calMusPequired() && calPaintPequired()  && checkArtClassroomOther() && checkArtVenNum() && checkSelfRemarkEnsurance()){
            var expense = window.document.getElementById("idFundRequired").value;
			if(num == '2' && expense > 1000){
				layer.msg('注意: 配备达标资金缺额单位为万元,<br/>当前已超过1000万元<br/>您仍确认保存吗？',
						   {time: 0 ,btn:['确 认', '取 消'], yes: function(index){  
						        $("#idForm").submit();
				}});
			}else{
				$("#idForm").submit();
			}
            
        }else{
			layer.msg("请填写正确完整的信息");
			tmp = 1;        
        }
	});
	
</script>

 <script>
	<#if errorInfo?exists && errorInfo!="">
		layer.msg('${errorInfo?if_exists}');
	</#if>

	function bao(s) {
		if(s=="1"){
			window.document.getElementById("idFundRequired").type = "hidden";
		}else{
			window.document.getElementById("idFundRequired").type = "text";
		}
	}
	
 </script>
</@p.index>