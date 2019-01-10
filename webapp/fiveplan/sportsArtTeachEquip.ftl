<@p.index setReferUrl=true>
<div class="container">
<form id="uploadSportsArtTeachEquipForm" name ="uploadSportsArtTeachEquipForm" action = "sportsArtTeachEquip!uploadSportsArtTeachEquipForm.action" method="post" >
  <input id="token" type="hidden" name="token" value="${token?if_exists}"/>
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">体育、艺术教师培训与教研员配备情况</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>
                <tr class="text-c">
     				<td rowspan="2">体育</td>
                    <td>体育教师培训（人次）：</td>
                    <td><input class="input-text form-control" id = "phy_teacher_exer" value = "${(preData.phy_teacher_exer)?if_exists}" name="phy_teacher_exer"/><label class="control-label">前一年数据：${(oldData.phy_teacher_exer)?if_exists}</label></td>
                </tr>
             	<tr class="text-c">
                    <td>体育教师培训费用（万元）：</td>
                    <td><input class="input-text form-control" id = "spend_phy_teacher_exer" value ="${((preData.spend_phy_teacher_exer)?if_exists.toString()?html)?if_exists}" name="spend_phy_teacher_exer" onblur="checkScope1(this.id)"/><label class="control-label">前一年数据：${((oldData.spend_phy_teacher_exer)?if_exists.toString()?html)?if_exists}</label></td>
                </tr>
        		<tr class="text-c">
                 <td rowspan="2">音乐</td>
                    <td>音乐教师培训（人次）：</td>
                    <td><input class="input-text form-control" id = "music_teacher_exer" value ="${(preData.music_teacher_exer)?if_exists}" name="music_teacher_exer"/><label class="control-label">前一年数据：${(oldData.music_teacher_exer)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
              
                    <td>音乐教师培训费用（万元）：</td>
                    <td><input class="input-text form-control" id = "spend_music_teacher_exer" value="${((preData.spend_music_teacher_exer)?if_exists.toString()?html)?if_exists}"  name ="spend_music_teacher_exer"  onblur="checkSc2(this.id)"/><label class="control-label" >前一年数据：${((oldData.spend_music_teacher_exer)?if_exists.toString()?html)?if_exists}</label></td>
                </tr>
             
                <tr class="text-c">
                <td rowspan="2">美术</td>
                    <td>美术教师培训（人次）：</td>
                    <td><input class="input-text form-control" id = "draw_teacher_exer" value="${(preData.draw_teacher_exer)?if_exists}" name="draw_teacher_exer"/><label class="control-label">前一年数据：${(oldData.draw_teacher_exer)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>美术教师培训费用（万元）：</td>
                    <td><input class="input-text form-control" id = "spend_draw_teacher_exer" value="${((preData.spend_draw_teacher_exer)?if_exists.toString()?html)?if_exists}" name="spend_draw_teacher_exer" onblur="checkSc3(this.id)"/><label class="control-label" >前一年数据：${((oldData.spend_draw_teacher_exer)?if_exists.toString()?html)?if_exists}</label></td>
                </tr>
                    </tbody>
             </table>
             <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>
                <div class="col-xs-12 text-center">
            		<h4 class="title">填报人信息</h4>
        		</div>
                <tr class="text-c">
                    <td>填报人姓名</td>
                   	<td><input class="input-text form-control" id="person_name" name="person_name" value="${((information.PERSON_NAME)?if_exists.toString()?html)?if_exists}"></td>              	
                </tr>
                <tr class="text-c">
                    <td>联系电话</td>
                   	<td><input class="input-text form-control" id="person_phone" name="person_phone" value="${((information.PERSON_PHONE)?if_exists.toString()?html)?if_exists}"></td>              	
                </tr>
                </tbody>
             </table>
   <div class=" col-sm-12 text-c">
        <button type="button"   class="btn btn-success radius" onclick="getPreStep()"><strong>上一步</strong></button>
        <button type="button"   class="btn btn-success radius"  onclick="getNextStep()"><strong>完成</strong></button>
    </div>
</form>
</div>
<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/js/bootstrap.js"></script>
<script src="${base}/lib/layer/2.1/layer.js"></script>
<script>
	var arr =new Array();
function checkScope1(spend_money1){
		var x=document.getElementById(spend_money1).value;
        if(x >= 5){
            layer.msg('体育教师培训费用（万元）数据过大，请确认');
            return true;
        }
}
function checkSc2(spend_money2){
		var x=document.getElementById(spend_money2).value;
        if(x >= 5){
            layer.msg('音乐教师培训费用（万元）数据过大，请确认');
            return true;
        }
}
function checkSc3(spend_money3){
		var x=document.getElementById(spend_money3).value;
        if(x >= 5){
            layer.msg('美术教师培训费用（万元）数据过大，请确认');
            return true;
        }
}

//document.uploadSportsArtTeachEquipForm.spend_phy_teacher_exer.onblur = function(){
	//checkScope1(eval("spend_phy_teacher_exer"));	}
//document.uploadSportsArtTeachEquipForm.spend_music_teacher_exer.onblur = function(){
		//checkScope2(eval("spend_music_teacher_exer"));}
//document.uploadSportsArtTeachEquipForm.spend_draw_teacher_exer.onblur = function(){
		//checkScope3(eval("spend_draw_teacher_exer"));	}
$(function(){
	for(var i =0 ;i < $("input").length;i++){
		if($("input").eq(i).val()=="")
			arr.push(1);
		else{
			arr.push(1);
		}
	}
})
$(function(){
		<#if errorMessage?exists>
		layer.msg('${(errorMessage)?if_exists}');
		</#if>
	})
	
	
function checkNum(inputObj){
		var strValue = document.getElementById(inputObj).value;
		//var str = /^[A-Za-z0-9]{18}/;
		var  regex1 = /^[1-9]{1,8}([.][0-9]{1,4})$/;//整数是8位小数后面只能有4位
		var  regex2 = /^[1-9]{1,8}$/;//整数是8位，没有小数
		var  regex3 = /^[0-9]{1,1}$/;//匹配一位的整数
		var  regex4 = /^[0-9]([.][0-9]{1,4})$/;//整数只有一位，个位为0-9小数后面只有4位
		var  regex5=/^[1-9]\d{0,7}$/;
		if(strValue==""){
	
		 	return false;
		}else if(regex1.test(strValue)||regex2.test(strValue)||regex3.test(strValue)||regex4.test(strValue)||regex5.test(strValue)){
				return true;
		}else{
			return false;
		}
	}

function checkZNum(str){
		var strValue = document.getElementById(str).value;
		var  reg = /^[1-9]{1,8}$/;//整数是8位，没有小数
		var  regex2 = /^[0-9]{1,1}$/;//匹配一位的整数
		var  regex5=/^[1-9]\d{0,7}$/;
		if(strValue==""){
		 	
		}else if(reg.test(strValue)||regex2.test(strValue)||regex5.test(strValue)){
			return true;
		}
		else{
			
			return false;
		}
}
function checkiphone(str){
	var strValue = document.getElementById(str).value;
	var  reg = /^\d{11,20}$/;//整数是8位，没有小数
		if(strValue==""){
		 	
		}else if(reg.test(strValue)){
			return true;
		}
		else{
			
			return false;
		}
}

	function getNextStep(){	
	if(checkZNum("phy_teacher_exer")){arr[0]=1;}else{arr[0]=0; layer.msg('体育教师人数最多是8位整数且不能为空',{time:1000});return}		
		if(checkZNum("music_teacher_exer")){arr[1]=1;}else{arr[1]=0;layer.msg('音乐教师人数最多是8位整数且不能为空',{time:1000});return}
		if(checkZNum("draw_teacher_exer")){arr[2]=1;}else{arr[2]=0;layer.msg('美术教师人数最多是8位整数且不能为空',{time:1000});return}
		if(checkNum("spend_phy_teacher_exer")){arr[3]=1;}else{arr[3]=0;layer.msg('体育教师培训费用只能是8位整数或者是8位整数小数点后4位，且不能为空',{time:1000});return}
		if(checkNum("spend_music_teacher_exer")){arr[4]=1;}else{arr[4]=0;layer.msg('音乐教师培训费用只能是8位整数或者是8位整数小数点后4位，且不能为空',{time:1000});return}
		if(checkNum("spend_draw_teacher_exer")){arr[5]=1;}else{arr[5]=0;layer.msg('美术教师培训费用只能是8位整数或者是8位整数小数点后4位，且不能为空',{time:1000});return}
		if(checkiphone("person_phone")){arr[6]=1;}else{arr[6]=0;layer.msg('电话号码填写错误',{time:1000});return}	
	for(var i =0;i<arr.length;i++){
		if(arr[i]==0){
			return;
		}	
	}
	$("#uploadSportsArtTeachEquipForm").submit();	 
}
function getPreStep(){
		$("#uploadSportsArtTeachEquipForm").attr("action", "sportsArtTeachEquip!preStep.action");
		
		$("#uploadSportsArtTeachEquipForm").submit();

	}
	 $(function(){
		<#if errorInfo?exists>
		layer.msg('${(errorInfo)?if_exists}');
		</#if>
	})
</script>
</@p.index>