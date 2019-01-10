<@p.index setReferUrl=true>
<form action="sportThird.action" method="post" id="idForm">
	<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
	<div class="container">
	    <div class="page-container">
	        <div class="col-xs-12 text-center">
	            <h4 class="title">学校体育工作评估</h4>
	        </div>
	        <div class="mt-20">
	            <table class="table table-border table-bordered table-bg table-sort">
	                <tbody>
	                <#if isAddPointProject?exists && isAddPointProject=="1">
						<tr class="text-c">
		                    <td colspan="2">学校体育是否有加分项</td>
							<td><select class="form-control" name="isAddPointProject">
										<option value="1" selected>是</option>
										<option value="2">否</option>
								</select>
		                </tr>
	               <#else>
		               <tr class="text-c">
		                    <td colspan="2">学校体育是否有加分项</td>
							<td><select class="form-control" name="isAddPointProject">
										<option value="1" >是</option>
										<option value="2" selected>否</option>
								</select>
		                </tr>
	               </#if>
	                
	                <tr class="text-c">
	                    <td rowspan="4">体育经费</td>
	                    <td>体育工作经费支出</td>
	                    <td><input  name="expenseActivity" id="idExpenseActivity" onblur="checkExpense()" placeholder="单位(万元)" class="input-text form-control"  value="${expenseActivity?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldExpenseActivity)?if_exists}</label></td>
	                </tr>
					<tr class="text-c">
	                    <td>体育场地经费支出</td>
	                    <td><input name="expenseField" id="idExpenseField" onblur="checkExpense()" placeholder="单位(万元)" class="input-text form-control"  value="${expenseField?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldExpenseField)?if_exists}</label></td>
	                </tr>
	                <tr class="text-c">
	                    <td>体育专用器材经费支出</td>
	                    <td><input name="expenseEquip" id="idExpenseEquip" onblur="checkExpense()" placeholder="单位(万元)" class="input-text form-control"  value="${expenseEquip?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldExpenseEquip)?if_exists}</label></td>
	                </tr>
	                <tr class="text-c">
	                   <td>体育经费支出总额</td>
	                    <td><input name="expense" disabled="disabled" id="idExpense" placeholder="单位(万元)" class="input-text form-control" value="${expense?if_exists}"/>
	                    <label class="control-label">前一年：${(oldData.oldExpense)?if_exists}</label></td>
	                </tr>
	                
	                
	                
					<#if isInsurance?exists && isInsurance=="1">
						<tr class="text-c">
		                    <td colspan="2">制定体育活动意外伤害保障措施</td>
							<td><select class="form-control" name="isInsurance">
										<option value="1" selected>是</option>
										<option value="2">否</option>
								</select>
								</td>
		                </tr>
	               <#else>
		               <tr class="text-c">
		                    <td colspan="2">制定体育活动意外伤害保障措施</td>
							<td><select class="form-control" name="isInsurance">
										<option value="1" >是</option>
										<option value="2" selected>否</option>
								</select>
							</td>
		                </tr>
	               </#if>
	               </tbody>
	            </table>
	        </div>
	    </div>
	    <div class=" col-sm-12 text-c">
	        <button type="button" class="btn btn-success radius" onclick="window.location.href=('../sport/sportSecondView.action')"><strong>上一步</strong></button>
	    	<button type="button" id="idBtn" class="btn btn-success radius" >
				<strong>下一步</strong>
			</button>
	    </div>
	
	</div>
</form>

<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>

<script>
	<#if errorInfo?exists && errorInfo!="">
		layer.msg('${errorInfo?if_exists}');
	</#if>
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

<#--验证体育经费-->
	function checkExpense(){
		var idExpense = window.document.getElementById("idExpense");
		var idnumExpenseField = window.document.getElementById("idExpenseField");
		var idExpenseEquip = window.document.getElementById("idExpenseEquip");
		var idExpenseActivity = window.document.getElementById("idExpenseActivity");
		var flag = true;
		
		if(validateFloat(idnumExpenseField.value)){
			if(idnumExpenseField.value!=null && idnumExpenseField.value.length>0){
				layer.msg("体育场地经费支出只能输入数字或您输入的数据过大");
				idnumExpenseField.value = "";
			}
			idExpense.value = ""
			flag = false;
		}
		if(validateFloat(idExpenseEquip.value)){
			if(idExpenseEquip.value!=null && idExpenseEquip.value.length>0){
			
				layer.msg("体育专用器材经费支出只能输入数字或您输入的数据过大");
				idExpenseEquip.value = "";
			}
			idExpense.value = ""
			flag = false;
		}
		if(validateFloat(idExpenseActivity.value)){
			if(idExpenseActivity.value!=null && idExpenseActivity.value.length>0){
				layer.msg("体育工作经费支出只能输入数字或您输入的数据过大");
				idExpenseActivity.value = "";
			}
			idExpense.value = ""
			flag = false;
		}
		if(flag){
			var i = parseFloat(idnumExpenseField.value) + parseFloat(idExpenseEquip.value) + parseFloat(idExpenseActivity.value);
			idExpense.value = "" + i;
			if(i > 1000 && tmp == 1){
				layer.msg("注意:<br/>所有经费支出的单位是:万元<br/>体育经费支出总额已超过1000万元<br/>");
			}
		}
		return flag;
		
	}

<#--最终判断所有的数据是否合法，判断是否提交表单-->
	$("#idBtn").click(function(){
		tmp = 0;
		if(checkExpense()){
			var expense = window.document.getElementById("idExpense").value;
			if(expense > 1000){
				layer.msg('注意: 所有经费支出的单位为万元<br/>体育经费支出总额已超过1000万元<br/>您仍确认提交吗？',
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
</@p.index>