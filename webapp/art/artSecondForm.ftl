<@p.index setReferUrl=true>

<form action="artSecond.action" method="post" id="idForm">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>

    <div class="container">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">艺术教师</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-sort">
                <tbody>
                <tr class="text-c">
                	
                    <td rowspan="6">音乐教师</td>
                    <td>应配专职数</td>
                    <td><input class="input-text form-control" name="musicShouldTeacher" value="${musicShouldTeacher?if_exists}" id="musicShouldTeacher" onblur="checkMusicShouldTeacher();showValue();"/>
                    <label class="control-label" name="musicShouldTeacher1">前一年数据:${musicShouldTeacher1?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>实有专职数</td>
                    <td><input class="input-text form-control" name="musicRealTeacher" value="${musicRealTeacher?if_exists}" id="musicRealTeacher" onblur="checkMusicRealTeacher();showValue();showRatio();"/>
                    <label class="control-label" name="musicRealTeacher1">前一年数据:${musicRealTeacher1?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>缺额专职数</td>
                    <td><input class="input-text form-control" name="musicGapsTeacher" value="${musicGapsTeacher?if_exists}" id="musicGapsTeacher" disabled="disabled"/>
                    <label class="control-label" name="musicGapsTeacher1">前一年数据:${musicGapsTeacher1?if_exists}</label></td>
                </tr>
				<tr class="text-c">
                    <td>兼职教师数</td>
                    <td><input class="input-text form-control" name="musicPartTimeTeacher" value="${musicPartTimeTeacher?if_exists}" id="musicPartTimeTeacher"  onblur="checkMusicPartTimeTeacher();"/>
                    <label class="control-label" name="musicPartTimeTeacher1">前一年数据:${musicPartTimeTeacher1?if_exists}</label></td>
                </tr>
				<tr class="text-c">
                    <td>生师比</td>
                    <td><input class="input-text form-control" name="musicRatioST" value="${musicRatioST?if_exists}" id="musicRatioST" disabled="disabled"/>
                    <label class="control-label" name="musicRatioST1">前一年数据:${musicRatioST1?if_exists}</label></td>
                </tr>
				<tr class="text-c">
                    <td>周课时数</td>
                    <td><input class="input-text form-control" name="musicClassNum" value="${musicClassNum?if_exists}" id="musicClassNum" onblur="checkMusicClassNum();"/>
                    <label class="control-label" name="musicClassNum1">前一年数据:${musicClassNum1?if_exists}</label></td>
                </tr>
				
				<tr class="text-c">
                    <td rowspan="6">美术教师</td>
                    <td>应配专职数</td>
                    <td><input class="input-text form-control" name="artShouldTeacher" value="${artShouldTeacher?if_exists}" id="artShouldTeacher" onblur="checkArtShouldTeacher();showValue2();"/>
                    <label class="control-label" name="artShouldTeacher1">前一年数据:${artShouldTeacher1?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>实有专职数</td>
                    <td><input class="input-text form-control" name="artRealTeacher" value="${artRealTeacher?if_exists}" id="artRealTeacher" onblur="checkArtRealTeacher();showValue2();showRatio2();"/>
                    <label class="control-label" name="artRealTeacher1">前一年数据:${artRealTeacher1?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>缺额专职数</td>
                    <td><input class="input-text form-control" name="artGapsTeacher" value="${artGapsTeacher?if_exists}" id="artGapsTeacher" disabled="disabled"/>
                    <label class="control-label" name="artGapsTeacher1">前一年数据:${artGapsTeacher1?if_exists}</label></td>
                </tr>
				<tr class="text-c">
                    <td>兼职教师数</td>
                    <td><input class="input-text form-control" name="artPartTimeTeacher" value="${artPartTimeTeacher?if_exists}" id="artPartTimeTeacher" onblur="checkArtPartTimeTeacher();"/>
                    <label class="control-label" name="artPartTimeTeacher1">前一年数据:${artPartTimeTeacher1?if_exists}</label></td>
                </tr>
				<tr class="text-c">
                    <td>生师比</td>
                    <td><input class="input-text form-control" name="artRatioST" value="${artRatioST?if_exists}" id="artRatioST" disabled="disabled"/>
                    <label class="control-label" name="artRatioST1">前一年数据:${artRatioST1?if_exists}</label></td>
                </tr>
				<tr class="text-c">
                    <td>周课时数</td>
                    <td><input class="input-text form-control" name="artClassNum" value="${artClassNum?if_exists}" id="artClassNum" onblur="checkArtClassNum();"/>
                    <label class="control-label" name="artClassNum1">前一年数据:${artClassNum1?if_exists}</label></td>
                </tr>
                
                <tr class="text-c">
                    <td rowspan="3">艺术教师</td>
                    <td>参加县级以上培训人数</td>
                    <td><input class="input-text form-control" name="train" value="${train?if_exists}" id="train" onblur="checkTrain();"/>
                    <label class="control-label" name="train1">前一年数据:${train1?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>荣获县级以上表彰人数</td>
                    <td><input class="input-text form-control" name="honor" value="${honor?if_exists}" id="honor" onblur="checkHonor();"/>
                    <label class="control-label" name="honor1">前一年数据:${honor1?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>自评得分</td>
                    <td><input class="input-text form-control" name="selfAssessment" value="${selfAssessment?if_exists}" id="selfAssessment" placeholder = "满分为20分" onblur="checkSelfAssessment();"/>
                    <label class="control-label" name="selfAssessment1">前一年数据:${selfAssessment1?if_exists}</label></td>
                </tr>
                
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
    	 <button type="button" class="btn btn-success radius" onclick="window.location.href=('../art/artFirstView.action')"><strong>上一步</strong></button>
	     <button type="button" id="idbtn" class="btn btn-success radius" ><strong>下一步</strong></button>

    </div>

</div>

</form>

<script>
	<#if errorInfo?exists>
		alert('${errorInfo?if_exists}');
	</#if>
</script>


<script type="text/javascript">
	function showRatio() {
		var showBox = document.getElementById("musicRatioST");
		var inputBox1 = ${studentNum?if_exists};
		var inputBox2 = document.getElementById("musicRealTeacher").value;
		if (!(inputBox1.length==0) && !(inputBox2.length==0)) {
			if (!isNaN(inputBox1) && !isNaN(inputBox2)) {
				var x = parseInt(inputBox1);
				var y = parseInt(inputBox2);
				if((y == 0)){
					showBox.value = 0;
				}else{
					var a = parseInt(x  / y);
					showBox.value = a;
				}
			}
		} 
	}
	showRatio();
</script>

<script type="text/javascript">
	function showRatio2() {
		var showBox = document.getElementById("artRatioST");
		var inputBox1 = ${studentNum?if_exists};
		var inputBox2 = document.getElementById("artRealTeacher").value;
		if (!(inputBox1.length==0) && !(inputBox2.length==0)) {
			if (!isNaN(inputBox1) && !isNaN(inputBox2)) {
				var x = parseInt(inputBox1);
				var y = parseInt(inputBox2);
				if((y == 0)){
					showBox.value = 0;
				}else{
					var a = parseInt(x  / y);
					showBox.value = a;
				}
			}
		} 
	}
	showRatio2();
</script>

<script type="text/javascript">
	function showValue() {
		var showBox = document.getElementById("musicGapsTeacher");
		var inputBox1 = document.getElementById("musicShouldTeacher").value;
		var inputBox2 = document.getElementById("musicRealTeacher").value;
		if (!(inputBox1.length==0) && !(inputBox2.length==0)) {
			if (!isNaN(inputBox1) && !isNaN(inputBox2)) {
				var x = parseInt(inputBox1);
				var y = parseInt(inputBox2);
				var a = x - y;
				if(a < 0) {
					showBox.value = 0;
				}else{
					showBox.value = a;
				}
			}
		} 
	}
	showValue();
</script>

<script type="text/javascript">
	function showValue2() {
		var showBox = document.getElementById("artGapsTeacher");
		var inputBox1 = document.getElementById("artShouldTeacher").value;
		var inputBox2 = document.getElementById("artRealTeacher").value;
		if (!(inputBox1.length==0) && !(inputBox2.length==0)) {
			if (!isNaN(inputBox1) && !isNaN(inputBox2)) {
				var x = parseInt(inputBox1);
				var y = parseInt(inputBox2);
				var a = x - y;
				if(a < 0) {
					showBox.value = 0;
				}else{
					showBox.value = a;
				}
			}
		} 
	}
	showValue2();
</script>

<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>

<script type="text/javascript">
	<#--音乐教师应配专职数-->
	
	function checkMusicShouldTeacher(){
		var idnum = window.document.getElementById("musicShouldTeacher");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("音乐教师应配专职数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--音乐教师实有专职数-->
	function checkMusicRealTeacher(){
		var idnum = window.document.getElementById("musicRealTeacher");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("音乐教师实有专职数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--音乐教师兼职教师数-->
	function checkMusicPartTimeTeacher(){
		var idnum = window.document.getElementById("musicPartTimeTeacher");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("音乐教师兼职教师数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--音乐老师周课时数-->
	function checkMusicClassNum(){
		var idnum = window.document.getElementById("musicClassNum");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("音乐老师周课时数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--美术教师应配专职数-->
	function checkArtShouldTeacher(){
		var idnum = window.document.getElementById("artShouldTeacher");
	var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("美术教师应配专职数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--美术教师实有专职数-->
	function checkArtRealTeacher(){
		var idnum = window.document.getElementById("artRealTeacher");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("美术教师实有专职数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--美术教师兼职教师数-->
	function checkArtPartTimeTeacher(){
		var idnum = window.document.getElementById("artPartTimeTeacher");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("美术教师兼职教师数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--美术教师周课时数-->
	function checkArtClassNum(){
		var idnum = window.document.getElementById("artClassNum");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("美术教师周课时数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--参加县级以上培训人数-->
	function checkTrain(){
		var idnum = window.document.getElementById("train");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("参加县级以上培训人数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--荣获县级以上表彰人数-->
	function checkHonor(){
		var idnum = window.document.getElementById("honor");
		var flag = true;
		if(validateNum(idnum.value)){
			if(idnum.value==null || idnum.value.length==0){
				layer.msg("荣获县级以上表彰人数未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("你输入的为负数，请重新数输入！");
				idnum.value="";
				return false;
			}else if(idnum.value > 999999999){
				layer.msg("你输入的数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}else{
				layer.msg("非法字符，请输入正整数！");
				idnum.value="";
				return false;
			}
		}
		return flag;
	}
	
	<#--自评得分-->
	function checkSelfAssessment(){
		var idnum = window.document.getElementById("selfAssessment");
		var flag = false;
		if(validateNum(idnum.value)){
				layer.msg("自评得分只能输入整数");
				idnum.value = "";
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
	
	<#--最终判断所有的数据是否合法，判断是否提交表单-->
	$("#idbtn").click(function(){

		if(checkMusicShouldTeacher()&&checkMusicRealTeacher() &&checkMusicPartTimeTeacher() &&checkMusicClassNum()&&checkArtShouldTeacher()&&checkArtRealTeacher() &&checkArtPartTimeTeacher() &&checkArtClassNum()&& checkTrain()&&checkHonor()&& checkSelfAssessment()){
            $("#idForm").submit();            
        }else{
			layer.msg("请填写正确完整的信息");        
        }
	});
	
</script>

</@p.index>