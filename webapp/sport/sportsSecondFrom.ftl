<@p.index setReferUrl=true>

<form action="../sport/sportSecond.action"  method="post" name="sportSecond" id="sportSe">
   <input id="token" type="hidden" name="token" value="${token?if_exists}"/>
   <div class="container">
		<div class="page-container">
			<div class="col-xs-12 text-center">
				<h3 class="title">场地设施</h3>
			</div>
			<div class="mt-20">
				<table
					class="table table-border table-bordered table-bg table-sort">
					<tbody>
						<tr class="text-c">
							<td rowspan="4">田径块</td>
							<td>200米场地(块数)</td>
							<td><input class="input-text form-control" name="length2" id="len2"  value="${length2?if_exists}" onblur="checklength2()"><label
								class="control-label" name="last2">前一年数据:${last2?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>300米场地(块数)</td>
							<td><input class="input-text form-control" name="length3" id="len3" value="${length3?if_exists}" onblur="checklength3()"><label
								class="control-label" name="last3">前一年数据:${last3?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>300米至400米场地(块数)</td>
							<td><input class="input-text form-control" name="length3t4" id="len3t4" value="${length3t4?if_exists}" onblur="checklength3t4()" ><label
								class="control-label" name="last3t4">前一年数据:${last3t4?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>400米场地(块数)</td>
							<td><input class="input-text form-control" name="length4" id="len4" value="${length4?if_exists}" onblur="checklength4()"><label
								class="control-label" name="last4">前一年数据:${last4?if_exists}</label></td>
						</tr>

						<tr class="text-c">
							<td rowspan="3">球场游戏区域</td>
							<td>篮球场个数</td>
							<td><input class="input-text form-control" name="basketball" id="bas" value="${basketball?if_exists}" onblur="checkbas()"><label
								class="control-label" name="lastBasket">前一年数据:${lastBasket?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>排球场个数</td>
							<td><input class="input-text form-control" name="volleyball" id="volley" value="${volleyball?if_exists}" onblur="checkvolley()"><label
								class="control-label" name="lastVolley">前一年数据:${lastVolley?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>器械体操/游戏区面积(平方米)</td>
							<td><input class="input-text form-control" name="gameArea" id="gArea"  value="${gameArea?if_exists}" onblur="checkgArea()"><label
								class="control-label" name="lastGameArea">前一年数据:${lastGameArea?if_exists}</label></td>
						</tr>

						<tr class="text-c">
							<td rowspan="2">体育馆</td>
							<td>体育馆个数</td>
							<td><input class="input-text form-control" name="sportHall" id="sHall" value="${sportHall?if_exists}" onblur="checksHall()"><label
								class="control-label" name="lastSportHall">前一年数据:${lastSportHall?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>体育馆面积(平方米)</td>
							<td><input class="input-text form-control" name="sportHallArea" id="sArea" value="${sportHallArea?if_exists}" onblur="checksArea()"><label
								class="control-label" name="lastSportArea">前一年数据:${lastSportArea?if_exists}</label></td>
						</tr>

						<tr class="text-c">
							<td rowspan="2">游泳池</td>
							<td>游泳池个数</td>
							<td><input class="input-text form-control" name="swimPool" id="sPool" value="${swimPool?if_exists}" onblur="checksPool()"><label
								class="control-label" name="lastSwimPool">前一年数据:${lastSwimPool?if_exists}</label></td>
						</tr>
						<tr class="text-c">
							<td>游泳池面积(平方米)</td>
							<td><input class="input-text form-control" name="swimPoolArea" id="sPoolArea" value="${swimPoolArea?if_exists}" onblur="checksPoolArea()"><label
								class="control-label" name="lastSwimArea">前一年数据:${lastSwimArea?if_exists}</label></td>
						</tr>

						<tr class="text-c">
							<td rowspan="1">学生体质测试室</td>
							<td>测试室个数</td>
							<td><input class="input-text form-control" name="testRoom" id="tRoom" value="${testRoom?if_exists}" onblur="checktRoom()"><label
								class="control-label" name="lastTestRoom">前一年数据:${lastTestRoom?if_exists}</label></td>
						</tr>

                <#if storard?exists && storard=="1">
						<tr class="text-c">
							<td rowspan="1">体育器材</td>
							<td>体育器材是否达标</td>
							<td><select class="form-control" name="storard" id="isStorard" value="${storard?if_exists}">
									<option value="1" selected="true">是</option>
									<option value="2" >否</option>
							</select></td>
						</tr>
				<#else>
				    <tr class="text-c">
							<td rowspan="1">体育器材</td>
							<td>体育器材是否达标</td>
							<td><select class="form-control" name="storard" id="isStorard" value="${storard?if_exists}">
									<option value="1">是</option>
									<option value="2" selected="true">否</option>
							</select></td>
						</tr>
				 </#if> 		
					</tbody>
				</table>
			</div>
		</div>
		<div class=" col-sm-12 text-c">
		    <button type="button" class="btn btn-success radius" onclick="window.location.href=('../sport/sportFirstView.action')"><strong>上一步</strong></button>
			<button type="button" id="sub" class="btn btn-success radius" >
				<strong>下一步</strong>
			</button>
		</div>

	</div>
	</form>
	
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>
   
  <script>
	    <#if errorInfo?exists>
				<#if errorInfo!="">
					layer.msg('${errorInfo?if_exists}');
				</#if>
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
		
		function checklength2(){
		    var idnum= window.document.getElementById("len2");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		  }else if(idnum.value>99999999){
		        layer.msg("200米田径场块数字过大，不符合实际情况，请重新输入！");
		        idnum.value="";
		        return false;
	     }
	        return true;
	  }
	   
	    function checklength3(){
		    var idnum = window.document.getElementById("len3");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		 }else if(idnum.value>99999999){
				layer.msg("300米田径场块数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
		 }
		     return true;
	  }
	     
	    function checklength3t4(){
		  
		 var idnum = window.document.getElementById("len3t4");
		 if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		  }else if(idnum.value>99999999){
				layer.msg("300米至400米田径场块数数字过大，不符合实际情况，请重新输入！");
			    idnum.value="";
				return false;
		  }
		   return true;
	  }
	  
	   function checklength4(){
		    var idnum = window.document.getElementById("len4");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("400米田径场块数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  function checkbas(){
		    var idnum = window.document.getElementById("bas");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("篮球场个数数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	   function checkvolley(){
		    var idnum = window.document.getElementById("volley");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("排球场个数数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  function checkgArea(){
		    var idnum = window.document.getElementById("gArea");
		if(validateNum(idnum.value)){
		   if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("游戏体操区域面积数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	   function checksHall(){
		    var idnum = window.document.getElementById("sHall");
		    var x=window.document.getElementById("sArea"); 
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("体育馆个数数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  function checksArea(){
		    var idnum = window.document.getElementById("sArea");
		    var x=window.document.getElementById("sHall");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			}
		}else if(parseInt(idnum.value)==0){
				if(parseInt(x.value)!=0){
				  layer.msg("存在体育馆，体育馆面积不能为0");
				  idnum.value="";
				  x.value="";
				  return false;
				}
			}else if(parseInt(x.value)==0){
				if(parseInt(idnum.value)!=0){
				  layer.msg("没有体育馆，体育馆面积为0");
				  idnum.value="";
				  x.value="";
				  return false;
				}
			}else if(idnum.value>99999999){
				layer.msg("体育馆面积数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  
	    function checksPool(){
		    var idnum = window.document.getElementById("sPool");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("游泳池个数数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  function checksPoolArea(){
		    var idnum = window.document.getElementById("sPoolArea");
		    var x = window.document.getElementById("sPool");
		if(validateNum(idnum.value)){
			if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			}
		}else if(parseInt(idnum.value)==0){
				if(parseInt(x.value)!=0){
				  layer.msg("存在游泳池，游泳池面积不能为0");
				  x.value="";
				  idnum.value="";
				  return false;
				}
			}else if(parseInt(x.value)==0){
				if(parseInt(idnum.value)!=0){
				  layer.msg("没有游泳池，游泳池面积为0");
				  idnum.value="";
				  x.value="";
				  return false;
				}
			}else if(idnum.value>99999999){
				layer.msg("游泳池面积数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  
	  function checktRoom(){
		    var idnum = window.document.getElementById("tRoom");
		if(validateNum(idnum.value)){
		   if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("输入字符不合法,默认填写为0");
			    idnum.value="0";
			    return true;
			}
		}else if(idnum.value>99999999){
				layer.msg("测试室个数数字过大，不符合实际情况，请重新输入！");
				idnum.value="";
				return false;
			}
			 return true;
	  }
	  
	  $("#sub").click(function(){
		if(checklength2()&&checklength3()&&checklength3t4()&&checklength4()&&checkbas()&&checkvolley()&&checkgArea()&&checksHall()&&checksArea()&&checksPool()&&checksPoolArea()&&checktRoom()){
		   
		   
            $("#sportSe").submit();
            
        }else{
			layer.msg("请填写正确完整的信息");        
        }
	});
    </script>
    
</@p.index>
