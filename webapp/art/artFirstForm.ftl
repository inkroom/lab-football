<@p.index setReferUrl=true>

 <form action="artFirst.action" method="post" name="artFirst" id = "idForm">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">艺术课程和活动</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-sort">
                <tbody>
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                </tr>
                <tr class="text-c">
                    <td>在校生人数</td>
                    <td><input disabled="true" class="input-text form-control" id="idnumOfStus" name="numOfStus" onblur="checknumOfStus()" value="${numOfStus?if_exists}"
                    /><label class="control-label" name="lastnumOfStus">前一年数据:${lastnumOfStus?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>实际班级数量</td>
                    <td><input disabled="true" class="input-text form-control" id="idactualClassNumber" name="actualClassNumber" onblur="checkClassNumber()" value="${actualClassNumber?if_exists}"
                    /><label class="control-label" name="lastactualClassNumber">前一年数据:${lastactualClassNumber?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td rowspan="6">每周艺术课程</td>
                    <td>音乐课时</td>
                    <td><input class="input-text form-control"  id="idmusicClass"  name="musicClass"  value="${musicClass?if_exists}"  onblur = "checkmusicClass()"/>
                             <label class="control-label" name="musicClassLast">前一年数据:${musicClassLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>美术课时</td>
                    <td><input class="input-text form-control"  name="artClass"  id="idartClass" value="${artClass?if_exists}" onblur = "checkartClass()"/>
                              <label class="control-label" name="artClassLast">前一年数据:${artClassLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>综合艺术课时</td>
                    <td><input class="input-text form-control"  name="integratedArtClass"  id="idintegratedArtClass"  value="${integratedArtClass?if_exists}" onblur = "checkintegratedArtClass()"/>
                             <label class="control-label" name="integratedArtClassLast">前一年数据:${integratedArtClassLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>地方/学校艺术课时</td>
                    <td><input class="input-text form-control"  name = "localArtClass" id = "idlocalArtClass" value="${localArtClass?if_exists}" onblur = "checklocalArtClass()"/>
                              <label class="control-label" name = "localArtClassLast">前一年数据:${localArtClassLast?if_exists}</label></td>
                </tr>
                 <#if enoughClass?exists && enoughClass=="1">
						<tr class="text-c">
							<td>是否开齐开足</td>
							<td><select class="form-control" name="enoughClass" value="${enoughClass?if_exists}">
									<option value="1" selected="true">是</option>
									<option value="2" >否</option>
							</select></td>
						</tr>
					<#else>
					    <tr class="text-c">
							<td>是否开齐开足</td>
							<td><select class="form-control" name="enoughClass" value="${enoughClass?if_exists}">
									<option value="1" >是</option>
									<option value="2" selected="true">否</option>
							</select></td>
						</tr>
					</#if> 
               
                <tr class="text-c">  
                    <td>自评得分</td>
                    <td><input class="input-text form-control"  name = "srscroeClass"  id ="idsrscroeClass" value="${srscroeClass?if_exists}"  placeholder = "满分为30分" onblur = "checksrscroeClass()"/>
                             <label class="control-label" name = "srscroeClassLast" >前一年数据:${srscroeClassLast?if_exists}</label></td>
                </tr>
                
                <tr class="text-c">
                    <td rowspan="5">艺术活动</td>
                    <td>学校每年开展艺术节场（次/年）</td>
                    <td><input class="input-text form-control"  name = "screenings" id = "idscreenings"value="${screenings?if_exists}" onblur = "checkscreenings()" />
                             <label class="control-label" name = "screeningsLast">前一年数据:${screeningsLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>每周开展活动频次（次/周）</td>
                    <td><input class="input-text form-control"  name = "frequency" id = "idfrequency" value="${frequency?if_exists}" onblur = "checkfrequency()"/>
                             <label class="control-label" name = "frequencyLast">前一年数据:${frequencyLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>艺术社团数量</td>
                    <td><input class="input-text form-control"  name = "artSocieties" id= "idartSocieties"value="${artSocieties?if_exists}" onblur = "checkartSocieties()"/>
                             <label class="control-label" name = "artSocietiesLast">前一年数据:${artSocietiesLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>艺术活动学生参与面（%）</td>
                    <td><input class="input-text form-control"  name = "participating" id = "idparticipating" value="${participating?if_exists}" onblur = "checkparticipating()" />
                             <label class="control-label" name = "participatingLast">前一年数据:${participatingLast?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>自评得分</td>
                    <td><input class="input-text form-control"  name = "srscroeActivity" id = "idsrscroeActivity" value="${srscroeActivity?if_exists}" placeholder = "满分为20分" onblur = "checksrscroeActivity()"/>
                             <label class="control-label" name = "srscroeActivityLast">前一年数据:${srscroeActivityLast?if_exists}</label></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        
        <button type="button"  class="btn btn-success radius" id = "idbtn" ><strong>下一步</strong></button>
    </div>

</div>



 </form>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>
 <script>
			<#if errorInfo?exists>
				layer.msg('${errorInfo?if_exists}');
			</#if>
			
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
	
	<#--验证音乐课时-->
	function checkmusicClass(){
		var idnum = window.document.getElementById("idmusicClass");
		var flag = false;
		if(validateNum(idnum.value)){
				layer.msg("音乐课时只能输入整数或您输入的数据过大！");
				idnum.value = "";
			return false;
		}else{
			return true;
		}
	}
	
	<#--验证美术课时-->
	function checkartClass(){
		var idnum = window.document.getElementById("idartClass");
		var flag = false;
		if(validateNum(idnum.value)){
				layer.msg("美术课时只能输入整数或您输入的数据过大！");
				idnum.value = "";
			return false;
		}else{
			return true;
		}
	}
	
	<#--验证综合艺术课时-->
	function checkintegratedArtClass(){
		var idnum = window.document.getElementById("idintegratedArtClass");
		var flag = false;
		if(validateNum(idnum.value)){
				layer.msg("综合艺术课时只能输入整数或您输入的数据过大！");
			idnum.value = "";
			return false;
		}else{
			return true;
		
		}
	}
	
	<#--验证艺术课时 地方/学校-->
	function checklocalArtClass(){
		var idnum = window.document.getElementById("idlocalArtClass");
		var flag = false;
		if(validateNum(idnum.value)){
				layer.msg("艺术课时 地方/学校只能输入整数或您输入的数据过大！");
				idnum.value = "";
			return false;
		}else{
			return true;
		
		
		}
	}
	
	<#--验证每周艺术课程自评得分-->
	function checksrscroeClass(){
		
		var idnum = window.document.getElementById("idsrscroeClass");
		var flag = false;
		if(validateNum(idnum.value)){
				layer.msg("每周艺术课程自评得分只能输入整数");
				idnum.value = "";
		}else{
			if(30<parseInt(idnum.value) || parseInt(idnum.value)<0){
				layer.msg("每周艺术课程自评得分满分为30分");
				idnum.value = "";
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
	<#--验证学校每年开展艺术节场次(次/年)-->
	function checkscreenings(){
		var idnum = window.document.getElementById("idscreenings");
		var flag = false;
		if(validateNum(idnum.value)){
			layer.msg("学校每年开展艺术节场次(次/年)只能输入整数或您输入的数据过大!");
			idnum.value = "";
			return false;
		}else{
			return true;
		}
		
	}
	
	<#--验证学校每周开展活动频次(次/周)-->
	function checkfrequency(){
		var idnum = window.document.getElementById("idfrequency");
		var flag = false;
		if(validateFloat(idnum.value)){
			layer.msg("学校每周开展活动频次(次/周)输入数据不合法");
			idnum.value = "";
			return false;
		}else{
			return true;
		}
		
	}
	
	<#--验证艺术社团数量-->
	function checkartSocieties(){
		var idnum = window.document.getElementById("idartSocieties");
		var flag = false;
		if(validateNum(idnum.value)){
			layer.msg("艺术社团数量只能输入整数或您输入的数据过大！");
			idnum.value = "";
			return false;
		}else{
			return true;
		}
		
	}
	
	<#--验证艺术活动学生参与面（%）-->
	function checkparticipating(){
		
		var idnum = window.document.getElementById("idparticipating");
		var flag = false;
	    if(validateNum(idnum.value)){
				layer.msg("艺术活动学生参与面（%）只能输入整数");
				idnum.value = "";
		}else{
			if(100<parseInt(idnum.value) || parseInt(idnum.value)<0){
				layer.msg("艺术活动学生参与面（%）在100%以内包括100%");
				idnum.value = "";
			}else{
				flag = true;
			}
			}
			return flag;	
		
	}
	
	<#--验证艺术活动自评得分-->
	function checksrscroeActivity(){
		var idnum = window.document.getElementById("idsrscroeActivity");
		var flag = false;
	    if(validateNum(idnum.value)){
				layer.msg("艺术活动自评得分只能输入整数");
				idnum.value = "";
		}else{
			if(20<parseInt(idnum.value) || parseInt(idnum.value)<0){
				layer.msg("每周艺术课程自评得分满分为20分");
				idnum.value = "";
			}else{
				flag = true;
			}
			}
				return flag;
	}
	
	<#--最终判断所有的数据是否合法，判断是否提交表单-->
	$("#idbtn").click(function(){
		
		if(checkmusicClass()&&checkartClass() &&checkintegratedArtClass() &&checklocalArtClass()&&checksrscroeClass() &&checksrscroeClass()&& checkscreenings()&&checkfrequency()&& checkartSocieties()&&checkparticipating()&&checksrscroeActivity()){
            $("#idForm").submit();
            
        }else{
			layer.msg("请填写正确完整的信息");        
        }
	});

</script>
</@p.index>