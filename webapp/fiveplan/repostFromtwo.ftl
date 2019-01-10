<@p.index setReferUrl=true>
<div class="container">
	<form id="uploadForm"   method="post" name="form1">
		<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">艺术专用教室达标及器材设备配置达标情况</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>
                <tr class="text-c">
                    <td>音乐专用教室达标情况</td>

                    <td><div class="form-group">
                            <select class="form-control" name="isAchieveMusicRoom" id="isAchieveMusicRoom"  whatever = "音乐专用教室">
								  <#if isAchieveMusicRoom?exists >
                            		<#if isAchieveMusicRoom=="1">
								<option value="-1">----请选择----</option>
								<option value="2">否，还未配备达标</option>
                                <option value="3" >是，往年配备达标</option>
                                <option value="1" selected>是，今年新增达标</option>
                               
                                	<#elseif isAchieveMusicRoom=="2">
                                	<option value="-1">----请选择----</option>
                               	    <option value="2" selected>否，还未配备达标</option>
                               	 	<option value="3" >是，往年配备达标</option>
                               	 	   <option value="1" >是，今年新增达标</option>
                               	    <#elseif isAchieveMusicRoom== "-1">
                               	    <option value="-1" selected>----请选择----</option>
                                	<option value="2" >否，还未配备达标</option>
                               	    <option value="3" >是，往年配备达标</option>
                               	    <option value="1" >是，今年新增达标</option>
                               	    
                               	    <#elseif isAchieveMusicRoom== "3">
                               	    <option value="-1" >----请选择----</option>
                               	    <option value="2" >否，还未配备达标</option>
                               	    <option value="3" selected>是，往年配备达标</option>
                               	    <option value="1" >是，今年新增达标</option>
                               	    
                               	 </#if>
                               	</#if>
                               	<#if isAchieveMusicRoom?exists>
                               	<#else>
                               		<option value="-1" >----请选择----</option>
                               	    <option value="2" >否，还未配备达标</option>
                               	    <option value="3">是，往年配备达标</option>
                               	    <option value="1" >是，今年新增达标</option>
                               	    </#if>
                            </select>
                        </div>
					<label class="control-label">前一年数据:　${(oldData.isAchieveMusicRoom)?if_exists}</label></td>
                </tr>



                <tr class="text-c">
                    <td>新增音乐专用教室投入金额(万元)</td>
                    <td><input whatever = "新增音乐专用教室投入金额(万元)" class="input-text form-control changetype" name="spendForAddMusicRoom" id="spendForAddMusicRoom"
                    	value= "${(nowData.spendForAddMusicRoom?if_exists.toString()?html)?default("0")}"  />
                    	<label class="control-label">前一年数据:　${(oldData.spendForAddMusicRoom)?if_exists}</label></td>
                    </td>
                    
                </tr>
                

            

                <tr class="text-c">
                    <td>音乐器材配备达标情况</td>

                    <td><div class="form-group">
                            <select class="form-control" name="isAchieveMusicEqui" id="isAchieveMusicEqui" whatever = "音乐器材设备">
								  <#if isAchieveMusicEqui?exists >
                            		<#if isAchieveMusicEqui=="1">
								<option value="-1">----请选择----</option>
								<option value="2">否，还未配备达标</option>
                                <option value="3" >是，往年配备达标</option>
                                <option value="1" selected>是，今年新增达标</option>
                                	<#elseif isAchieveMusicEqui=="2">
                                		<option value="-1">----请选择----</option>
								<option value="2" selected>否，还未配备达标</option>
                                <option value="3" >是，往年配备达标</option>
                                <option value="1" >是，今年新增达标</option>
                                <#elseif isAchieveMusicEqui=="3">
                                		<option value="-1">----请选择----</option>
								<option value="2">否，还未配备达标</option>
                                <option value="3" selected>是，往年配备达标</option>
                                <option value="1" >是，今年新增达标</option>
                               	    <#elseif isAchieveMusicEqui== "-1">
                               	   <option value="-1" selected>----请选择----</option>
								<option value="2">否，还未配备达标</option>
                                <option value="3" >是，往年配备达标</option>
                                <option value="1" >是，今年新增达标</option>
                               	 </#if>
                               	</#if>
                               	<#if isAchieveMusicEqui?exists>
                               	<#else>
                               		<option value="-1" selected>----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	    </#if>
                            </select>
                        </div>
                        <label class="control-label">前一年数据:　${(oldData.isAchieveMusicEqui)?if_exists}</label></td>
					
                </tr>
				<!--找不到 新增音乐器材设备投入金额-->
                <tr class="text-c">
                    <td>新增音乐器材设备投入金额(万元)</td>
                    <td><input whatever = "新增音乐器材设备投入金额(万元)" class="input-text form-control changetype1" name="spendForMusicEquiment" id="spendForMusicEquiment"
                    value="${(spendForMusicEquiment?if_exists.toString()?html)?default("0")}"/>
                    <label class="control-label">前一年数据:　${(oldData.spendForMusicEquiment)?if_exists}</label>
                    </td>
                </tr>




                <!--找不到-->
                <tr class="text-c">
                    <td>美术专用教室达标情况</td>

                    <td><div class="form-group">
                            <select class="form-control" name="isAchieveDrawClass" id="isAchieveDrawClass" whatever = "美术专用教室">
								 <#if isAchieveDrawClass?exists >
                            		<#if isAchieveDrawClass=="1">
									<option value="-1" >----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" selected>是，今年新增达标</option>
                                	<#elseif isAchieveDrawClass=="2">
                                	<option value="-1" >----请选择----</option>
									<option value="2" selected>否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                                	
                               	 <#elseif isAchieveDrawClass== "3">
                               	   <option value="-1" >----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" selected>是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	    <#elseif isAchieveDrawClass== "-1">
                               	   <option value="-1" selected>----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	 </#if>
                               	</#if>
                               	<#if isAchieveDrawClass?exists>
                               	<#else>
                               		<option value="-1" selected>----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	    </#if>
                            </select>
                        </div>
                        <label class="control-label">前一年数据:　${(oldData.isAchieveDrawClass)?if_exists}</label></td>
					
                </tr>
				

                <tr class="text-c">
                    <td>新增美术专用教室投入金额(万元)</td>
                    <td><input whatever = "新增美术专用教室投入金额(万元)" class="input-text form-control changetype2" name="spendForDrawRoom" id="spendForDrawRoom"
                    value="${(spendForDrawRoom?if_exists.toString()?html)?default("0")}"/>
                    <label class="control-label">前一年数据:　${(oldData.spendForDrawRoom)?if_exists}</label>
                    </td>
                </tr>


				 
                <tr class="text-c">
                    <td>美术器材配备达标情况</td>
                    <td><div class="form-group">
                            <select class="form-control" name="isAchieveDrawEqui" id="isAchieveDrawEqui" whatever = "美术器材设备">
								<#if isAchieveDrawEqui?exists >
                            		<#if isAchieveDrawEqui=="1">
									<option value="-1" >----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" selected>是，今年新增达标</option>
                                	<#elseif isAchieveDrawEqui=="2">
                                	<option value="-1" >----请选择----</option>
									<option value="2" selected>否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                                	<#elseif isAchieveDrawEqui== "3">
                               	   <option value="-1" >----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" selected>是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	    <#elseif isAchieveDrawEqui== "-1">
                               	   <option value="-1" selected>----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	 </#if>
                               	</#if>
                               	<#if isAchieveDrawEqui?exists>
                               	<#else>
                               		<option value="-1" selected>----请选择----</option>
									<option value="2">否，还未配备达标</option>
                                	<option value="3" >是，往年配备达标</option>
                                	<option value="1" >是，今年新增达标</option>
                               	    </#if>
                            </select>
                        </div>
                        <label class="control-label">前一年数据:　${(oldData.isAchieveDrawEqui)?if_exists}</label>
                   </td>
                </tr>
                
		<!--新增美术设备花费-->
                <tr class="text-c">
                    <td>新增美术设备投入金额(万元)</td>
                    <td><input whatever = "新增美术设备投入金额(万元)" class="input-text form-control changetype3" name="spendForDrawEqui" id="spendForDrawEqui"
                    value="${(spendForDrawEqui?if_exists.toString()?html)?default("0")}"/>
                    <label class="control-label">前一年数据:　${(oldData.spendForDrawEqui)?if_exists}</label>
                    </td>
                </tr>
               
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        <button type="button"   class="btn btn-success radius"  onclick="getPreStep()" ><strong>上一步</strong></button>
        <button type="button"   class="btn btn-success radius next_btn_success" onclick="getNextStep()" ><strong>下一步</strong></button>
    </div>
</form>
</div>

<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/js/bootstrap.js"></script>
<script src="${base}/lib/layer/2.1/layer.js"></script>

<script>
	
	//验证输入四个输入金额
	document.form1.spendForAddMusicRoom.onblur = function(){
		alertNum("spendForAddMusicRoom");
	}
	
	document.form1.spendForMusicEquiment.onblur = function(){
		
		alertNum("spendForMusicEquiment");
	}
	
	
	document.form1.spendForDrawRoom.onblur = function(){
		
		alertNum("spendForDrawRoom");
	}
	document.form1.spendForDrawEqui.onblur = function(){
			alertNum("spendForDrawEqui");
	}

	//是否提示信息
	var Y1=0,Y2=0;
	function alerMessage(strName){
		if(document.getElementById(strName).value!=Y1){
	            Y1=document.getElementById(strName).value;
	            var strWhatever=document.getElementById(strName).getAttribute("whatever");
	            if(document.getElementById(strName).value=='1')
	            {
                   layer.msg(strWhatever+'请上传佐证');
                }
                }
	}

	
	//提示大于5万元的提示信息
	function alertNum(strName){
		var strValue = document.getElementById(strName).value;
		var strWhatever=document.getElementById(strName).getAttribute("whatever");
		var money = new Number(strValue);
		if(money >=5){
			layer.msg(strWhatever+'数据过大,请确认!');
		}
	}
	//验证选择
	function checkIs(strName,flag){
		var strId =  document.getElementById(strName);
		if(strId.value == "-1"){//1
			layer.msg('选择不能为空');
			return false;
		}
		return true;
	}
	
	//验证数字
	function checkNum(strNameId){
		var strWhatever=document.getElementById(strNameId).getAttribute("whatever");
		var strValue = document.getElementById(strNameId).value;
		var  regex1 = /^[1-9][0-9]{0,7}([.][0-9]{1,4})$/;//整数是八位小数后面有5位小数
		
		var  regex2  = /^[1-9][0-9]{1,7}$/;//整数是8位，没有小数,第一位不可以为0
		
		var  regex3 = /^[0-9]([.][1-9]{1,4})$/;//整数只有一位，个位为0-9小数后面只有五位
		
		var  regex4 = /^[0-9]{1,1}$/;//匹配一位的整数
		
		var isAchieveMusicRoom = document.getElementById("isAchieveMusicRoom").value;
		var isAchieveMusicEqui = document.getElementById("isAchieveMusicEqui").value;
		var isAchieveDrawClass = document.getElementById("isAchieveDrawClass").value;
		var isAchieveDrawEqui = document.getElementById("isAchieveDrawEqui").value;
		if(isAchieveMusicRoom == "2" && strValue==""){
			return true;
		}else if(isAchieveMusicEqui == "2" && strValue==""){
			return true;
		}else if(isAchieveDrawClass == "2" && strValue==""){
			return true;
		}else if(isAchieveDrawEqui == "2" && strValue==""){
			return true;
		}else if(strValue==""){
		 	layer.msg(strWhatever+'输入金额不能为空');
		 	return false;
		}else if((regex1.test(strValue))||(regex2.test(strValue))|| (strValue=="0") || (regex3.test(strValue)) || (regex4.test(strValue))){
	
			return true;
		}else{
	
			layer.msg(strWhatever+'整数最大为8位非负数，小数最多精确到4位');
			return false;
		}
		return true;
	}

	//函数打印错误信息
	 $(function(){
		<#if errorInfo?exists>
			<#if errorInfo!="">
			layer.msg('${(errorInfo)?if_exists}');
			 </#if>
		</#if>
	})
    

	//下一步
	function getPreStep(){
		$("#uploadForm").attr("action", "repostFormTwo!intoPreForm.action");
		$("#uploadForm").submit();

	}
	//点击下一步
	function getNextStep(){
		//选择是
	 	if(checkIs("isAchieveMusicRoom") &&
	   		 checkIs("isAchieveMusicEqui") && checkIs("isAchieveDrawClass") && checkIs("isAchieveDrawEqui") &&checkNum("spendForAddMusicRoom")
	   		 &&checkNum("spendForMusicEquiment")&&checkNum("spendForDrawRoom") && checkNum("spendForDrawEqui")
		){
			
			$("#uploadForm").attr("action", "repostFormTwo!uploadForm.action");
			$("#uploadForm").submit();
		}
	}
	
	
	
</script>
</@p.index>