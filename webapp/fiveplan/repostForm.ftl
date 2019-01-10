<@p.index setReferUrl=true>
 
    <div class="container">
<form id="uploadForm"  action="" method="post" name="form1">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
    <div class="page-container">
        <div class="col-xs-12 text-center">
        
            <h4 class="title">新增运动场及器材配备情况</h4>
             <a>填写说明和相关文档.rar</a>
     	<a href="downLoadWorld!downLoadWorld.action" class="btn btn-size btn-success">下载</a>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>  
                <tr class="text-c">
               
                    <td>学校类型</td>
                     <td>
                        <div class="form-group">
                           <select class="form-control"   name="stage" id="stage" />
                            <#if stage?exists >                      
                            <#if stage=="1">
                                <option value="-1">----请选择----</option>
                                <option value="1"selected >普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                             <#elseif stage=="2">
                                <option value="-1">----请选择----</option>
                                <option value="1" >普通小学</option>
                                <option value="2" selected>普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                             <#elseif stage=="3">
                                <option value="-1">----请选择----</option>
                                <option value="1" >普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3" selected>普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                                <#elseif stage=="4">
                                <option value="-1">----请选择----</option>
                                <option value="1" >普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4" selected>职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                                <#elseif stage=="5">
                                <option value="-1">----请选择----</option>
                                <option value="1" >普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5" selected>九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                                <#elseif stage=="6">
                                <option value="-1">----请选择----</option>
                                <option value="1" >普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6" selected>十二年一贯制学校</option>
                                <option value="7">完全中学</option>
                                <#elseif stage=="7">
                                <option value="-1">----请选择----</option>
                                <option value="1" >普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7" selected>完全中学</option>
                             </#if>
                              <#else>
                                <option value="-1" selected>----请选择----</option>
                             	<option value="1">普通小学</option>
                                <option value="2">普通初中</option>
                                <option value="3">普通高中</option>
                                <option value="4">职业高中</option>
                                <option value="5">九年一贯制学校</option>
                                <option value="6">十二年一贯制学校</option>
                                <option value="7" selected>完全中学</option>
                             </#if>  
                   

                            </select>
                            
                        </div>
                <label class="control-label">前一年数据：<#if oldYearData?exists><#if oldYearData.stage?exists><#if oldYearData.stage == 1>普通小学<#elseif oldYearData.stage == 2>普通初中<#elseif oldYearData.stage == 3>普通高中<#elseif oldYearData.stage == 4>职业高中<#elseif oldYearData.stage == 5>九年一贯制学校<#elseif oldYearData.stage == 6>十二年一贯制学校<#elseif oldYearData.stage == 7>完全中学</#if></#if></#if></label></td>
                </tr>
                <tr class="text-c">
                    
                    <td>运动场地达标情况</td>
                     <td>
                        <div class="form-group">
                          <select class="form-control"  value="${(newData.is_achieve_play_ground)?if_exists}" name="is_achieve_play_ground" id="is_achieve_play_ground" />
                             <#if is_achieve_play_ground?exists >
                             <#if is_achieve_play_ground=="1">
                                <option value="-1">----请选择----</option>
                                <option value="2">否，还未配备达标</option>
                                <option value="3">是，往年配备达标</option>
                                <option value="1" selected>是，今年新增达标</option>
                                 <#elseif is_achieve_play_ground=="2">
                                 <option value="-1">----请选择----</option>
                                <option value="2" selected>否，还未配备达标</option>
                                <option value="3">是，往年配备达标</option>
                                <option value="1">是，今年新增达标</option>
                                <#elseif is_achieve_play_ground=="3">
                                <option value="-1">----请选择----</option>
                                <option value="2">否，还未配备达标</option>
                                <option value="3" selected>是，往年配备达标</option>
                                <option value="1">是，今年新增达标</option>
                             </#if>
                             <#else>
                             <option value="-1" selected>----请选择----</option>
                                <option value="2">否，还未配备达标</option>
                                <option value="3">是，往年配备达标</option>
                                <option value="1">是，今年新增达标</option>
                             </#if>
                            </select>
                        </div>
                        <label class="control-label">前一年数据：<#if oldYearData?exists><#if oldYearData.is_achieve_play_ground?exists><#if oldYearData.is_achieve_play_ground == 1>是，今年新增达标<#elseif oldYearData.is_achieve_play_ground == 2>否，还未配备达标<#elseif oldYearData.is_achieve_play_ground == 3>是，往年配备达标</#if></#if></#if></label>
                       </td>
                </tr>
                
                
                <tr class="text-c">
                    <td>其中新建（个）</td>
                    <td><input whatever="其中新建（个）" class="input-text changetype form-control "  name="add_play_ground" value="${(newData.add_play_ground)?if_exists}"  id="add_play_ground" /><label class="control-label">前一年数据：${(oldYearData.add_play_ground)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>新建面积（㎡）</td>
                    <td><input whatever="新建面积（㎡）" class="input-text changetype form-control"  name="add_play_ground_area" value="${(newData.add_play_ground_area)?if_exists}"  id="add_play_ground_area"/><label class="control-label">前一年数据：${(oldYearData.add_play_ground_area)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    
                    <td>投入金额（万元）</td>
                    <td><input whatever="投入金额（万元）" id="spend_for_add_play_ground" class="input-text changetype form-control"  name="spend_for_add_play_ground" value="${(newData.spend_for_add_play_ground?if_exists.toString()?html)?if_exists}"  id="spend_for_add_play_ground"/><label class="control-label">前一年数据：${(oldYearData.spend_for_add_play_ground?if_exists.toString()?html)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>改扩建（个）</td>
                    <td><input whatever="改扩建（个）" class="input-text changetype form-control"  name="extension_modify_play_ground" value="${(newData.extension_modify_play_ground)?if_exists}" id="extension_modify_play_ground"/><label class="control-label">前一年数据：${(oldYearData.extension_modify_play_ground)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>改扩建面积（㎡）</td>
                    <td><input whatever="改扩建面积（㎡）" class="input-text changetype form-control"  name="area_exten_modify_play_ground"  value="${(newData.area_exten_modify_play_ground)?if_exists}" id="area_exten_modify_play_ground"/><label class="control-label">前一年数据：${(oldYearData.area_exten_modify_play_ground)?if_exists}</label></td>
                </tr>
                <tr class="text-c">                                                                        
                    
                    <td>投入金额（万元）</td>
                    <td><input whatever="投入金额（万元）" class="input-text changetype form-control" name="spend_for_mod_play_ground" value="${(newData.spend_for_mod_play_ground?if_exists.toString()?html)?if_exists}" id="spend_for_mod_play_ground"/><label class="control-label">前一年数据：${(oldYearData.spend_for_mod_play_ground?if_exists.toString()?html)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>运动器材达标情况</td>
                   <td>
                        <div class="form-group">
                           <select class="form-control"  name="is_add_euqi_gro"  id="is_add_euqi_gro"/>
                           <#if is_add_euqi_gro?exists >
                             <#if is_add_euqi_gro=="1">
                             <option value="-1">----请选择----</option>
                                <option value="2">否，还未配备达标</option>
                                <option value="3">是，往年配备达标</option>
                                <option value="1" selected>是，今年新增达标</option>
                             <#elseif is_add_euqi_gro=="2">
                             <option value="-1">----请选择----</option>
                                <option value="2" selected>否，还未配备达标</option>
                                <option value="3">是，往年配备达标</option>
                                <option value="1">是，今年新增达标</option>
                             <#elseif is_add_euqi_gro=="3">
                             <option value="-1">----请选择----</option>
                                <option value="2">否，还未配备达标</option>
                                <option value="3" selected>是，往年配备达标</option>
                                <option value="1">是，今年新增达标</option>
                                </#if>
                             <#else>
                             <option value="-1" selected>----请选择----</option>
                             <option value="2">否，还未配备达标</option>
                                <option value="3">是，往年配备达标</option>
                                <option value="1" selected>是，今年新增达标</option>
                             </#if>
                            </select>
                          
                        </div>
                        <label class="control-label">前一年数据：<#if oldYearData?exists><#if oldYearData.is_add_euqi_gro?exists><#if oldYearData.is_add_euqi_gro == 1>是，今年新增达标<#elseif oldYearData.is_add_euqi_gro == 2>否，还未配备达标<#elseif oldYearData.is_add_euqi_gro == 3>是，往年配备达标</#if></#if></#if></label>
                    </td>
                </tr>
                <tr class="text-d">
                    <td>投入金额（万元）</td>
                    <td><input whatever="投入金额（万元）" class="input-text changetype1 form-control"  name="spend_euqi_gro_totaL" value= "${(newData.spend_euqi_gro_totaL?if_exists.toString()?html)?default("0")}" id="spend_euqi_gro_totaL"/><label class="control-label">前一年数据：${(oldYearData.spend_euqi_gro_totaL?if_exists.toString()?html)?if_exists}</label></td>
              
        
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        <button id="button" type="button" class="btn btn-success radius" ><strong>下一步</strong></button>
    </div>
    
    
 
</form>
</div>

<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>

<script>
function set(){
	var spend_euqi_gro_totaL = document.getElementById("spend_euqi_gro_totaL");
		if(spend_euqi_gro_totaL.value.trim() == ""){
				spend_euqi_gro_totaL.value = 0;
				spend_euqi_gro_totaL.focus();
		}
}

	var status ="";
    //七个验证数字

	//整数最大为8位非负数，小数最多精确到4位	
	function checkZCNum(strNameId){
		var strValue = document.getElementById(strNameId).value;
		var strWhatever=document.getElementById(strNameId).getAttribute("whatever");
		var  regex1 = /^(([1-9][0-9]{0,7}\.([0-9]{1,4}))|([0-9]{1,1}\.([0-9]{1,4}))|([1-9][0-9]{0,7})|([0-0]{1,1}))$/;
		if(strValue==""){
		 	layer.msg(strWhatever+'输入不能为空');
		 	return false;
		}else if(regex1.test(strValue)){
			return true;
		}else{
			layer.msg(strWhatever+'整数最大为8位非负数，小数最多精确到4位');
			return false;
		}
		return true;
	}
		//要判断是否为8位非负整数
	function checkZCPNum(strNameId){
		var strValue = document.getElementById(strNameId).value;
		var strWhatever=document.getElementById(strNameId).getAttribute("whatever");
		var  regex1 = /^(([1-9][0-9]{0,7})|([0-0]{1,1}))$/;
		if(strValue==""){
		 	layer.msg(strWhatever+'输入不能为空');
		 	return false;
		}else if(regex1.test(strValue)){
			return true;
		}else{
			layer.msg(strWhatever+'最大为8位非负整数');
			return false;
		}
		return true;
	}
	


    $("#button").click(function(){
    var Y=true;
    var str1=$('#is_achieve_play_ground option:selected').val();
    var str2=$('#is_add_euqi_gro option:selected').val();
      if(str1)
      {
      if(!checkZCPNum("add_play_ground"))
         return ;
      if(!checkZCPNum("extension_modify_play_ground"))
	     return ;
	  if(!checkZCPNum("add_play_ground_area"))
	     return ;
	  if(!checkZCNum("spend_for_add_play_ground"))
	     return ;
	  if(!checkZCPNum("area_exten_modify_play_ground"))
	     return ;
	  if(!checkZCNum("spend_for_mod_play_ground"))
	      return ;
    }
    if(str2=="1")
    {
        if(!checkZCNum("spend_euqi_gro_totaL"))
              return ;
   }
   if($("#stage").val()=="-1"||$("#is_achieve_play_ground").val()=="-1"||$("#is_add_euqi_gro").val()=="-1")
    {
        layer.msg('请填写完整信息');
        return ;
    }
		$("#uploadForm").attr("action", "pageOne!uploadPageOneForm.action");
		$("#uploadForm").submit();
	});
	var Y1=0,Y2=0;
	$('#is_achieve_play_ground').click(function(){
	            if(document.getElementById('is_achieve_play_ground').value!=Y1)
	            {
	            Y1=document.getElementById('is_achieve_play_ground').value;
	            if(document.getElementById('is_achieve_play_ground').value=='1')
	            {
                   layer.msg('新增运动场请上传佐证');
                }
                }
             });
     	$('#is_add_euqi_gro').click(function(){
	            if(document.getElementById('is_add_euqi_gro').value!=Y2)
	            {
	            Y2=document.getElementById('is_add_euqi_gro').value;
	            if(document.getElementById('is_add_euqi_gro').value=='1')
	            {
                   layer.msg('新增体育器材请上传佐证');
                }
                }
             });
      function checkScope1(spend_money1){
	
        if(spend_money1.value >= 5){
            layer.msg('新建投入金额（万元）数据过大，请确认');
            return true;
        }
      }
      function checkScope2(spend_money2){
	
        if(spend_money2.value >= 5){
            layer.msg('改扩建投入金额（万元）数据过大，请确认');
            return true;
        }
      }
      function checkScope3(spend_money3){
	
        if(spend_money3.value >= 5){
            layer.msg('体育器材投入金额（万元）数据过大，请确认');
            return true;
        }
      }
      $("input[name='spend_for_add_play_ground']").blur(function(){
      	checkScope1(this);	
      });
      $("input[name='spend_for_mod_play_ground']").blur(function(){
      	checkScope2(this);	
      });
      $("input[name='spend_euqi_gro_totaL']").blur(function(){
      	checkScope3(this);	
      });
	 $(function(){
		<#if errorInfo?exists>
		layer.msg('${(errorInfo)?if_exists}');
		</#if>
	})
		
</script>
</@p.index>