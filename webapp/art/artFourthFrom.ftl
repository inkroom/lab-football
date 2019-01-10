<@p.index setReferUrl=true>

<form action="artFourth.action"  method="post" id="artFo">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
    <div class="container">
		<div class="page-container">
			<div class="col-xs-12 text-center">
				<h3 class="title">特色发展及艺术素质测评</h3>
			</div>
			<div class="mt-20">
				<table
					class="table table-border table-bordered table-bg  table-sort">
					<tbody>

                   <#if features?exists && features=="1">
						<tr class="text-c">
							<td rowspan="2">特色发展</td>
							<td>是否具有特色</td>
							<td><select class="form-control" name="features" value="${features?if_exists}">
									<option value="1" selected="true">是</option>
									<option value="2" >否</option>
							</select></td>
						</tr>
					<#else>	
						<tr class="text-c">
							<td rowspan="2">特色发展</td>
							<td>是否具有特色</td>
							<td><select class="form-control" name="features" value="${features?if_exists}" >
									<option value="1">是</option>
									<option value="2" selected="true">否</option>
							</select></td>
						</tr>
					</#if> 	
						<tr class="text-c">
							<td>自评得分</td>
								<td><input class="input-text form-control" name="evaluationOne" id="iEva" value="${evaluationOne?if_exists}" onblur="checkeiEva()" placeholder="满分为10分"/><label
								class="control-label" name="lastEva">前一年数据:${lastEva?if_exists}</label></td>
						</tr>  


                    <#if assessment?exists && assessment=="1">
						<tr class="text-c">
							<td rowspan="6">艺术素质测评</td>
							<td>是否开展测评</td>
							<td><select class="form-control" name="assessment" id="idSele" value="${assessment?if_exists}" onchange="bao(this.options[this.options.selectedIndex].value)">
									<option value="1" selected>是</option>
									<option value="2" >否</option>
							</select></td>
						</tr>
					<#else>
					    <tr class="text-c">
							<td rowspan="6">艺术素质测评</td>
							<td>是否开展测评</td>
							<td><select class="form-control" name="assessment" id="idSele" value="${assessment?if_exists}" onchange="bao(this.options[this.options.selectedIndex].value)">
									<option value="1" >是</option>
									<option value="2" selected>否</option>
							</select></td>
						</tr>
					</#if> 
		
						<tr class="text-c">
							<td >优秀等级(人)</td>
							<td><input class="input-text form-control" name="good" id="igood" onblur="checkeigood()" value="${good?if_exists}"/><label
								class="control-label" name="lastGood" >前一年数据:${lastGood?if_exists}</label></td>
						</tr>
						
						<tr class="text-c">
							<td >良好等级(人)</td>
							<td><input class="input-text form-control" name="fine" id="ifine" onblur="checkeifine()"  value="${fine?if_exists}"/><label
								class="control-label" name="lastFine" >前一年数据:${lastFine?if_exists}</label></td>
						</tr>
					

						<tr class="text-c">
							<td >合格等级(人)</td>
							<td><input class="input-text form-control" name="qualified" id="ipass" onblur="checkeipass()"  value="${qualified?if_exists}" /><label
								class="control-label" name="lastPass" >前一年数据:${lastPass?if_exists}</label></td>
						</tr>
						

						<tr class="text-c">
							<td >不合格等级(人)</td>
							<td><input class="input-text form-control"
								name="notQualified" id="inot" onblur="checkeinot()"  value="${notQualified?if_exists}" name="lastNot" /><label class="control-label">前一年数据:${lastNot?if_exists}</label></td>
						</tr>
						

						<tr class="text-c">
							<td>自评得分</td>
							<td><input class="input-text form-control" name="artEvaluation" id="iartEvalu" value="${artEvaluation?if_exists}" onblur="checkeiartEvalu()" placeholder="满分为10分"/><label
								class="control-label" name="lastArtE">前一年数据:${lastArtE?if_exists}</label></td>
						</tr>
                   
                   <tr class="text-c">
                	
                    <td rowspan="3">艺术教育支出情况</td>
                    <td>专用教室/艺术场馆建设支出(万)</td>
                    <td><input class="input-text form-control" name="classRoomPay" value="${classRoomPay?if_exists}" id="classRoomPay" onblur="checkClassRoomPay()" placeholder="万元"/>
                    <label class="control-label" name="lastClassRoomPay">前一年数据:${lastClassRoomPay?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>艺术教育器材支出(万)</td>
                    <td><input class="input-text form-control" name="artEdaPay" value="${artEdaPay?if_exists}" id="artEdaPay" onblur="checkArtEdaPay()" placeholder="万元"/>
                    <label class="control-label" name="lastArtEdaPay">前一年数据:${lastArtEdaPay?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>艺术活动支出(万)</td>
                    <td><input class="input-text form-control" name="artPay" value="${artPay?if_exists}" id="artPay" onblur="checkArtPay()" placeholder="万元"/>
                    <label class="control-label" name="lastArtPay">前一年数据:${lastArtPay?if_exists}</label></td>
                </tr>
				</tbody>
				</table>
			</div>
		</div>
		
		<div class=" col-sm-12 text-c">
		
		   <button type="button" class="btn btn-success radius" onclick="window.location.href=('../art/artThirdView.action')"><strong>上一步</strong></button>
	       <button type="button" class="btn btn-success radius" id="idsub"><strong>下一步</strong></button>
		
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
			
		
		function initFundRequired(){
		var id = window.document.getElementById("idSele");
		var index = id.selectedIndex;
		var value = id.options[index].value;
		if(value=="2"){
			window.document.getElementById("igood").value = 0;
			window.document.getElementById("igood").disabled=true;
			window.document.getElementById("ifine").value = 0;
			window.document.getElementById("ifine").disabled=true;
			window.document.getElementById("ipass").value = 0;
			window.document.getElementById("ipass").disabled=true;
			window.document.getElementById("inot").value = 0;
			window.document.getElementById("inot").disabled=true;
		}else{
		    
		    var good='${good?if_exists}';
		    var fine='${fine?if_exists}';
		    var qualified='${qualified?if_exists}';
		    var notQualified='${notQualified?if_exists}';
		    
		    window.document.getElementById("igood").value =good;
		    window.document.getElementById("igood").disabled=false;
		    window.document.getElementById("ifine").value =fine;
		    window.document.getElementById("ifine").disabled=false;
		    window.document.getElementById("ipass").value =qualified;
		    window.document.getElementById("ipass").disabled=false;
		    window.document.getElementById("inot").value =notQualified;
		    window.document.getElementById("inot").disabled=false;	    
		}
	}
	initFundRequired();
			
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
	
		function isNumber( s ){
   			 var regu = "^[0-9]+\.?[0-9]{1,4}$";
    		 var re = new RegExp(regu);
   			 if (re.test(s)) {
       			 return true;
  		  }else{
        return false;
         }
     }
			
	function checkeiEva(){
		var idnum = window.document.getElementById("iEva");
		if(validateNum(idnum.value)){
				layer.msg("自评得分只能输入整数");
				idnum.value = "";
				return false;
		}else{
			if(10<parseInt(idnum.value) || parseInt(idnum.value)<0){
				layer.msg("自评得分满分为10分");
				idnum.value = "";
				return false;
			  }
			}
	        return true;
	  }
	  
	  function checkeigood(){		    
		var idnum= window.document.getElementById("igood");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("优秀学生数量未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }
		   var allNum=${studentNum?if_exists};
		   var good=document.getElementById("igood").value;
		      
		   if(parseInt(good)>allNum){
		      layer.msg("优秀人数超过贵校总人数"+allNum);
		      idnum.value="";
		      return false;
		   }
		   return true;
	  }
	  
	  
	  function checkeifine(){
		
		var idnum= window.document.getElementById("ifine");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("良好学生数字未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }
		   var allNum=${studentNum?if_exists};
		   var good=document.getElementById("igood").value;
		   var fine=document.getElementById("ifine").value; 
		   var add=parseInt(good)+parseInt(fine);
		   if(add>allNum){
		      var number=allNum-parseInt(good);
		      layer.msg("优秀学生和良好学生人数之和超过贵校总人数"+allNum);
		      idnum.value="";
		      return false;
		   }
	        return true;
	  }
	  
	  function checkeipass(){
		    var idnum= window.document.getElementById("ipass");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("合格学生数量未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }	      
		   var allNum=${studentNum?if_exists};
		   var good=document.getElementById("igood").value;
		   var fine=document.getElementById("ifine").value; 
		   var pass=document.getElementById("ipass").value;
		   var add=parseInt(good)+parseInt(fine)+parseInt(pass);
		   if(add>allNum){
		      var number=allNum-(parseInt(good)+parseInt(fine));
		      layer.msg("人数之和超过贵校总人数"+allNum);
		      idnum.value="";
		      return false;
		   }
	         return true;
	  }
	  
	  function checkeinot(){
		var idnum= window.document.getElementById("inot");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("不合格学生数量未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }
		   var allNum=${studentNum?if_exists};
		   var good=document.getElementById("igood").value;
		   var fine=document.getElementById("ifine").value;
           var pass=document.getElementById("ipass").value;
           var not=document.getElementById("inot").value;
           var add=parseInt(good)+parseInt(fine)+parseInt(pass)+parseInt(not);
           
           var id = window.document.getElementById("idSele");
		   var index = id.selectedIndex;
		   var value = id.options[index].value;
		   if(value=="2"){
		       return true;
		   }else if(add<allNum||add>allNum){
		       var num=parseInt(allNum)-(parseInt(good)+parseInt(fine)+parseInt(pass));
               layer.msg("所有人数之和不等于贵校总人数"+allNum+",不合格人数应为"+num);
               idnum.value="";
               return false;
           }
	        return true;
	  }
	  
	  function checkeiartEvalu(){
		    var idnum= window.document.getElementById("iartEvalu");
		if(validateNum(idnum.value)){
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("艺术素质自评得分未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }else if(idnum.value>10){
		        layer.msg("艺术素质自评数字过大，请输入0到10之间的整数！");
		        idnum.value="";
		        return false;
	     }
	        return true;
	  }
	  
	 function checkClassRoomPay(){
	 var idnum= window.document.getElementById("classRoomPay");
	     if(isNumber(idnum.value)==false){
	       if(validateNum(idnum.value)==false){
	          return true;
	       }
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("专用教室/艺术场馆建设支出未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("专用教室/艺术场馆建设支出不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("填写完整信息");
			    idnum.value="";
			    return false;
			}
	    } else if(idnum.value>1000){
		        layer.msg("专用教室/艺术场馆建设支出超过1000万，建议重新填写！");
		        return true;
	     }
	        return true;
	 } 
	 
	 function checkArtEdaPay(){
	 var idnum= window.document.getElementById("artEdaPay");
	      if(isNumber(idnum.value)==false){
	      if(validateNum(idnum.value)==false){
	          return true;
	       }
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("艺术教育器材支出未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("艺术教育器材支出不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }else if(idnum.value>1000){
		        layer.msg("艺术教育器材支出超过1000万，建议重新填写！");
		        return true;
	     }
	        return true;
	 } 
	 
	 function checkArtPay(){
	 var idnum= window.document.getElementById("artPay");
	      if(isNumber(idnum.value)==false){
	      if(validateNum(idnum.value)==false){
	          return true;
	       }
			if(idnum.value==null && idnum.value.length==0){
				layer.msg("艺术活动支出未填写");
				idnum.value="";
				return false;
			}else if(idnum.value < 0){
				layer.msg("艺术活动支出不能为负数！");
				idnum.value="";
				return false;
			}else{
			    layer.msg("请填写完整信息");
			    idnum.value="";
			    return false;
			}
		  }else if(idnum.value>1000){
		        layer.msg("艺术活动支出超过1000万，建议重新填写！");
		        return true;
	     }
	       return true;
	 } 
	  
	 function bao(s) {
		if(s=="2"){
			window.document.getElementById("igood").value = 0;
			window.document.getElementById("igood").disabled=true;
			window.document.getElementById("ifine").value =0;
			window.document.getElementById("ifine").disabled=true;
			window.document.getElementById("ipass").value =0;
			window.document.getElementById("ipass").disabled=true;
			window.document.getElementById("inot").value =0;
			window.document.getElementById("inot").disabled=true;
		}else{
		    window.document.getElementById("igood").value ="";
		    window.document.getElementById("igood").disabled=false;
		    window.document.getElementById("ifine").value ="";
		    window.document.getElementById("ifine").disabled=false;
		    window.document.getElementById("ipass").value ="";
		    window.document.getElementById("ipass").disabled=false;
		    window.document.getElementById("inot").value ="";
		    window.document.getElementById("inot").disabled=false;
		}
	}
      
     $("#idsub").click(function(){
		if(checkeiEva()&&checkeigood()&&checkeifine()&&checkeipass()&&checkeinot()&&checkeiartEvalu()&&checkClassRoomPay()&&checkArtEdaPay()&&checkArtPay()){
            $("#artFo").submit();   
        }else{
			layer.msg("请填写正确完整的信息");        
        }
	});
	
    	
   </script>
</@p.index>