<@p.index setReferUrl=true>

<form action="sportFirst.action" method="post" id= "idForm">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">基础数据</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-b table-sort">
                <tbody>
 
                <#if schoolType?exists && schoolType=="1">
                	<tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1" selected="true">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#elseif schoolType?exists && schoolType=="2">
                	<tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1" >普通小学</option>
                                <option value="2" selected="true">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#elseif schoolType?exists && schoolType=="3">
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3" selected="true">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#elseif schoolType?exists && schoolType=="4">
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4" selected="true">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#elseif schoolType?exists && schoolType=="5">
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5" selected="true">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#elseif schoolType?exists && schoolType=="6">
                <tr class="text-c">
                	
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6" selected="true">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#elseif schoolType?exists && schoolType=="7">
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7" selected="true">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <#else>
                 <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
               	</#if> 
                
                
                
                
                <tr class="text-c">

                    <td>在校生人数</td>
                    <td><input class="input-text form-control" id="idnumOfStus" name="numOfStus" onblur="checknumOfStus()" value="${numOfStus?if_exists}"
                    /><label class="control-label" name="lastnumOfStus">前一年数据:${lastnumOfStus?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>实际班级数量</td>
                    <td><input class="input-text form-control" id="idactualClassNumber" name="actualClassNumber" onblur="checkClassNumber()" value="${actualClassNumber?if_exists}"
                    /><label class="control-label" name="lastactualClassNumber">前一年数据:${lastactualClassNumber?if_exists}</label></td>
                </tr>
                 <#if enoughPE?exists && enoughPE=="1">
                <tr class="text-c">
                    <td rowspan="3">开课及课外活动</td>
                    <td>体育课是否开足</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="enoughPE" value="${enoughPE?if_exists}">
                                <option value="1" selected="true">是</option>
                                <option value="2" >否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                <#else>
                 <tr class="text-c">
                    <td rowspan="3">开课及课外活动</td>
                    <td>体育课是否开足</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="enoughPE" value="${enoughPE?if_exists}">
                                <option value="1">是</option>
                                <option value="2" selected="true">否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                </#if> 
                <#if onehourPE?exists && onehourPE=="1">
                <tr class="text-c">
                    <td>落实每天一小时体育锻炼数</td>
                    <td>
                        <div class="form-group" >
                            <select class="form-control" name="onehourPE" value="${onehourPE?if_exists}">
                                <option value="1" selected="true">是</option>
                                <option value="2" >否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                <#else>
                <tr class="text-c">
                    <td>落实每天一小时体育锻炼数</td>
                    <td>
                        <div class="form-group" >
                            <select class="form-control" name="onehourPE" value="${onehourPE?if_exists}">
                                <option value="1">是</option>
                                <option value="2" selected="true">否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                </#if> 
                <#if numActivityInClass?exists && numActivityInClass=="1">
                <tr class="text-c">
                    <td>组织大课间体育活动数</td>
                    <td>
                    	<div class="form-group" >
                            <select class="form-control" name="numActivityInClass" value="${numActivityInClass?if_exists}">
                                <option value="1" selected="true">是</option>
                                <option value="2" >否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                <#else>
                 <tr class="text-c">
                    <td>组织大课间体育活动数</td>
                    <td>
                    	<div class="form-group" >
                            <select class="form-control" name="numActivityInClass" value="${numActivityInClass?if_exists}">
                                <option value="1">是</option>
                                <option value="2" selected="true">否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                </#if> 
                
                <tr class="text-c">
                    <td rowspan="8">体育师资</td>
                    <td>应配教师数(人)</td>
                    <td><input class="input-text form-control" id="idhavaTeacher" name="havaTeacher" onblur="checkhavaTeacher()" value="${havaTeacher?if_exists}"/><label class="control-label" name="lasthavaTeacher">前一年数据:${lasthavaTeacher?if_exists}</label></td>
                </tr>
                 <tr class="text-c">
                    <td>专职体育教师人数(人)</td>
                    <td><input class="input-text form-control" id="idnumFulltimeTeachers" name="numFulltimeTeachers" onblur="chechnumFulltimeTeachers()" value="${numFulltimeTeachers?if_exists}"/><label class="control-label" name="lastnumFulltimeTeachers">前一年数据:${lastnumFulltimeTeachers?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>兼职体育教师人数(人)</td>
                    <td><input class="input-text form-control" id="idnumParttimeTeachers" name="numParttimeTeachers" onblur="checknumParttimeTeachers()" value="${numParttimeTeachers?if_exists}"/>
                    <label class="control-label" name="lastnumParttimeTeachers">前一年数据:${lastnumParttimeTeachers?if_exists}</label></td>
                </tr>
                 <tr class="text-c">
                    <td>体育教师缺额数</td>
                    <td><input class="input-text form-control" id="idsportsTeacherVacancyNum" name="sportsTeacherVacancyNum" value="${sportsTeacherVacancyNum?if_exists}" disabled="disabled"/>
                    <label class="control-label" name="lastsportsTeacherVacancyNum">前一年数据:${lastsportsTeacherVacancyNum?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师缺额比（%）</td>
                    <td><input class="input-text form-control" id="idsportsTeacherVacancyRatio" name="sportsTeacherVacancyRatio" onblur="checksportsTeacherVacancyRatio()" value="${sportsTeacherVacancyRatio?if_exists}" disabled="disabled"/>
                    <label class="control-label" name="lastsportsTeacherVacancyRatio">前一年数据:${lastsportsTeacherVacancyRatio?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师生师比</td>
                    <td><input class="input-text form-control" id="idratioOfStudentsToTeachers" name="ratioOfStudentsToTeachers" onblur="checkratioOfStudentsToTeachers()" value="${ratioOfStudentsToTeachers?if_exists}" disabled="disabled"/>
                    <label class="control-label" name="lastratioOfStudentsToTeachers">前一年数据:${lastratioOfStudentsToTeachers?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师参训人数</td>
                    <td><input class="input-text form-control" id="idnumPhysicalTeachers" name="numPhysicalTeachers"onblur="checknumPhysicalTeachers()" value="${numPhysicalTeachers?if_exists}"/>
                    <label class="control-label" name="lastnumPhysicalTeachers">前一年数据:${lastnumPhysicalTeachers?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>教师受县级以上表彰人数</td>
                    <td><input class="input-text form-control" id="idnumRecognitionTeachers" name="numRecognitionTeachers" onblur="checknumRecognitionTeachers()" value="${numRecognitionTeachers?if_exists}"/>
                    <label class="control-label" name="lastnumRecognitionTeachers">前一年数据:${lastnumRecognitionTeachers?if_exists}</label></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
       
        <button type="button"  id="idBtn" class="btn btn-success radius"><strong>下一步</strong></button>
    </div>

</div>

  <script>

    	
			<#if error?exists>
				layer.msg('${errorInfo?if_exists}',{time:5000});
			</#if>
    	
    </script>

</form>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>

<script>
	<#if errorInfo?exists && errorInfo!="">
		alert('${errorInfo?if_exists}');
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
	<#--验证实际班级数量-->
	function checkClassNumber(){
		var idnum = window.document.getElementById("idactualClassNumber");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value < 0){
				idnum.value = "";
				layer.msg("你输入的为负数，请重新数输入！");
				return false;
			}else{
				idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
			
		}
		if(idnum.value > 999999999){
			idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
		}
		return true;
	}
	<#--验证在校人数-->
	function checknumOfStus(){
		

	
		var idnum = window.document.getElementById("idnumOfStus");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
			    idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value < 0){
			    idnum.value = "";
				layer.msg("你输入的为负数，请重新数输入！");
				return false;
			}else{
			    idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
		}
		if(idnum.value == 0){
		    idnum.value = "";
			layer.msg("在校人数不能为0！");
			return false;
		}
		
		if(idnum.value > 999999999){
		    idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
			return false;
		}
		return true;
		
	}
	
	<#--验证应配教师数-->
	function checkhavaTeacher(){
		var idnum = window.document.getElementById("idhavaTeacher");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
			    idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value <0){
		     	idnum.value = "";
				layer.msg("应配教师书必须为正整数，请重新数输入！");
				return false;
			}else{
		    	idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
		}
		if(idnum.value == 0){
			idnum.value = "";
			layer.msg("应配教书不能为0，请重新输入！");
			return false;
		}
		if(idnum.value > 999999999){
			idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
			return false;
		}
		return true;
	}
	<#--验证专职教数-->
	function chechnumFulltimeTeachers(){
		var idnum = window.document.getElementById("idnumFulltimeTeachers");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
			    idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value < 0){
				idnum.value = "";
				layer.msg("不能为负数，请重新输入");
				return false;
			}else{
				idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
		}
		if(idnum.value > 999999999){
			idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
			return false;
		}
		var haveTeacher = $('#idhavaTeacher').val();
		var fullTime = $('#idnumFulltimeTeachers').val();
		if(parseInt(haveTeacher) < parseInt(fullTime)) {
			idnum.value = "";
			layer.msg("你输入的人数大于应配教师数，不符合实际情况，请重新插入！");
			return false;
		}
		if(idnum.value == 0){
			var students = $('#idnumOfStus').val();
			document.getElementById("idratioOfStudentsToTeachers").value = parseInt(students);
		}else{
			
			<#--生师比-->
			var students = $('#idnumOfStus').val();
			var ratio = parseInt(parseInt(students)  / parseInt(idnum.value));
			document.getElementById("idratioOfStudentsToTeachers").value = ratio;
			
		}
		<#--缺额数-->
		var vacancyNum = (parseInt(haveTeacher) - parseInt(fullTime));
		document.getElementById("idsportsTeacherVacancyNum").value = vacancyNum;
		<#--缺额比-->
		var vacancyRatio = parseInt(parseInt(vacancyNum) / parseInt(haveTeacher) * 100);
		document.getElementById("idsportsTeacherVacancyRatio").value = vacancyRatio;
		
		return true;
	}
	<#--兼职教师数-->
	function checknumParttimeTeachers(){
		var idnum = window.document.getElementById("idnumParttimeTeachers");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value < 0){
				idnum.value = "";
				layer.msg("你输入的为负数，请重新数输入！");
				return false;
			}else{
				idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
		}
		if(idnum.value > 999999999){
			idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
			return false;
		}
	
		return true;
	}
	
	<#--验证体育教书参训人数-->
	function checknumPhysicalTeachers(){
		var idnum = window.document.getElementById("idnumPhysicalTeachers");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value < 0){
				idnum.value = "";
				layer.msg("你输入的为负数，请重新数输入！");
				return false;
			}else{
				idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
		}
		if(idnum.value > 999999999){
			idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
			return false;
		}
		return true;
	}
	<#--教师受县级以上表彰人数-->
	function checknumRecognitionTeachers(){
		var idnum = window.document.getElementById("idnumRecognitionTeachers");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				idnum.value = "";
				layer.msg("实际班级数量未填写");
				return false;
			}else if(idnum.value < 0){
				idnum.value = "";
				layer.msg("你输入的为负数，请重新数输入！");
				return false;
			}else{
				idnum.value = "";
				layer.msg("非法字符，请输入正整数！");
				return false;
			}
		}
		if(idnum.value > 999999999){
		idnum.value = "";
			layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
			return false;
		}
		return true;
	
	}
	
		

	<#--最终判断所有的方法正确，确认提交表单-->
	$("#idBtn").click(function(){
	
		
	
		
		if(checkClassNumber() && checknumOfStus() && checkhavaTeacher() && chechnumFulltimeTeachers() && checknumParttimeTeachers() && checknumPhysicalTeachers() && checknumRecognitionTeachers()){
		
			$("#idForm").submit();
		}else{
			layer.msg("请将次表单填写完整，再点击下一步！");
		}
	})
	
	
</script>
</@p.index>