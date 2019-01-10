<@p.index setReferUrl=true>

 <form action="artSelf.action" method="post" name="artSelf" id="idForm">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
    <div class="page-container">
        <div>
            <h4 class="title text-center" style="line-height:3;  background-color: #cefbd2;">艺术工作年度自评表</h4>
        </div>
         <table class="table table-bordered table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">地方艺术课程名称</a></td>
                <td><textarea class="form-control" name = "artCousreNames" id = "idartCousreNames" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artCousreNames?if_exists}</textarea></td>
            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">学生艺术社团项目名称</a></td>
                <td><textarea class="form-control" name = "artClubName"  id = "idartClubName" rows="3" onblur = "checkStr(this.id)" cols="15" style="resize:none;">${artClubName?if_exists}</textarea></td>
            </tr>
             <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">校园文化艺术环境基本情况</a></td>
                <td><textarea class="form-control" name = "artCultureAtmosphere"  id = "idartCultureAtmosphere" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artCultureAtmosphere?if_exists}</textarea></td>
            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">其他艺术活动室或艺术场馆名称</a></td>
                <td><textarea class="form-control" name = "artVenueNamesOther"  id = "idartVenueNamesOther" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artVenueNamesOther?if_exists}</textarea></td>
            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">学校艺术教育特色发展成果</a></td>
                <td><textarea class="form-control" name = "achieveArtFeature"  id = "idachieveArtFeature" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${achieveArtFeature?if_exists}</textarea></td>
            </tr>
        </table>
        <div>
            <h4 class="title text-center" style="line-height:3;  background-color: #cefbd2;">艺术课程</h4>
        </div>
        <table class="table table-bordered table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">存在的主要问题</a></td>
                <td><textarea class="form-control" name = "artCurriculumProblems"  id = "idartCurriculumProblems" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artCurriculumProblems?if_exists}</textarea></td>

            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">改进措施</a></td>
                <td><textarea class="form-control" name = "artCurriculumImprovement"  id = "idartCurriculumImprovement" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artCurriculumImprovement?if_exists}</textarea></td>
            </tr>
        </table>
        <div>
            <h4 class="title text-center" style="line-height: 3;  background-color: #cefbd2;">艺术活动</h4>
        </div>
        <table class="table table-bordered  table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">存在的主要问题</a></td>
                <td><textarea class="form-control" name = "artActivityProblems"  id="idartActivityProblems" rows="3" onblur = "checkStr(this.id)" cols="15" style="resize:none;">${artActivityProblems?if_exists}</textarea></td>

            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">改进措施</a></td>
                <td><textarea class="form-control" name = "artActivityImprovement"  id= "idartActivityImprovement" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artActivityImprovement?if_exists}</textarea></td>
            </tr>
        </table>
        <div>
            <h4 class="title text-center" style="line-height: 3;  background-color: #cefbd2;">艺术教师</h4>
        </div>
        <table class="table table-bordered table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">存在的主要问题</a></td>
                <td><textarea class="form-control" name = "artTeacherProblems"  id="idartTeacherProblems" rows="3" onblur = "checkStr(this.id)" cols="15" style="resize:none;">${artTeacherProblems?if_exists}</textarea></td>

            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">改进措施</a></td>
                <td><textarea class="form-control" name = "artTeacherImprovement"  id="idartTeacherImprovement" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${artTeacherImprovement?if_exists}</textarea></td>
            </tr>
        </table>
        <div>
            <h4 class="title text-center" style="line-height: 3;  background-color: #cefbd2;">条件保障</h4>
        </div>
        <table class="table table-bordered table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">存在的主要问题</a></td>
                <td><textarea class="form-control" name = "conditionGuaranteeProblems" id="idconditionGuaranteeProblems" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${conditionGuaranteeProblems?if_exists}</textarea></td>

            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">改进措施</a></td>
                <td><textarea class="form-control" name = "conditionGuaranteeImprovement"  id="idconditionGuaranteeImprovement" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${conditionGuaranteeImprovement?if_exists}</textarea></td>
            </tr>
        </table>
        <div>
            <h4 class="title text-center" style="line-height: 3;  background-color: #cefbd2;">特色发展</h4>
        </div>
        <table class="table table-bordered  table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">存在的主要问题</a></td>
                <td><textarea class="form-control" name = "characteristicProblems"  id="idcharacteristicProblems" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${characteristicProblems?if_exists}</textarea></td>

            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">改进措施</a></td>
                <td><textarea class="form-control" name = "characteristicImprovement"  id="idcharacteristicImprovement" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${characteristicImprovement?if_exists}</textarea></td>
            </tr>
        </table>
        <div>
            <h4 class="title text-center" style="line-height: 3;  background-color: #cefbd2;">学生艺术素质测评</h4>
        </div>
        <table class="table  table-bordered table-hover">
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight:bold">存在的主要问题</a></td>
                <td><textarea class="form-control" name = "assessmentProblems"  id="idassessmentProblems" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${assessmentProblems?if_exists}</textarea></td>

            </tr>
            <tr class="text-center form-group">
                <td style="text-align: left;width: 200px;"><a style="font-weight: bold">改进措施</a></td>
                <td><textarea class="form-control" name = "assessmentImprovement"  id="idassessmentImprovement" onblur = "checkStr(this.id)" rows="3" cols="15" style="resize:none;">${assessmentImprovement?if_exists}</textarea></td>
            </tr>
        </table>
    </div>
    <div class=" col-sm-12 text-center">
         <button type="button" class="btn btn-success radius" onclick="window.location.href=('../art/artFourthView.action')"><strong>上一步</strong></button>
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
			var flag=true;
			<#--文本框验证不能只输入英文或数字符号-->
			function checkStr(val){
			
				 
				 var idtxt = window.document.getElementById(val);
				 if(idtxt.value == null || idtxt.value.length==0){
				 flag=true;
				     return true;
				 }else if(idtxt.value.length>200){
		 			layer.msg("字数不能超过200字");
		 			
		 			flag=false;
		 			return false;
				}
				flag=true;
				return true;
  			}
  			
  			function checkID(val){
				
				 var idtxt = window.document.getElementById(val);
				 if(idtxt.value == null || idtxt.value.length==0){
				    
				     return true;
				 }else if(idtxt.value.length>200){
		 			
		 			
		 			return false;
				}
				
				return true;
  			}
  		

  		<#--最终判断所有的数据是否合法，判断是否提交表单-->
  		
	$("#idbtn").click(function(){
	if(flag==true){
		if(checkID("idartCousreNames")==true){
			if(checkID("idartClubName")==true){
			   if(checkID("idartCultureAtmosphere")==true){
			      if(checkID("idartVenueNamesOther")==true){
			        if(checkID("idachieveArtFeature")==true){
			          if(checkID("idartCurriculumProblems")==true){
			            if(checkID("idartCurriculumImprovement")==true){
			               if(checkID("idartActivityProblems")==true){
			                  if(checkID("idartActivityImprovement")==true){
			                     if(checkID("idartTeacherProblems")==true){
			                        if(checkID("idartTeacherImprovement")==true){
			                           if(checkID("idconditionGuaranteeProblems")==true){
			                              if(checkID("idconditionGuaranteeImprovement")==true){
			                                 if(checkID("idcharacteristicProblems")==true){
			                                    if(checkID("idcharacteristicImprovement")==true){
			                                        if(checkID("idassessmentProblems")==true){
			                                            if(checkID("idassessmentImprovement")==true){
			                                         
			                                                $("#idForm").submit();
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
			}
		}
		else{
		flag=true;
			layer.msg("请填写正确完整的信息");        
        
        }
        
	});
	
	
</script>
</@p.index>