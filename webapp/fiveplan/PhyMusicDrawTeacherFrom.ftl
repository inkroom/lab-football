<@p.index setReferUrl=true>

  <style>
        .page-container{
            margin-top: 50px;
        }
        .form-control{
            width: 50%;
            float: left;
        }
        .control-label{
            display: inline-block;
            width: 50%;
            text-align: center;
            line-height: 30px;
        }
        .delete{
            float: right;
        }
        .page-container{
            margin-top: 50px;
        }
       .form-control{
           width: 50%;
           float: left;
       }
        .control-label{
            display: inline-block;
            width: 50%;
            text-align: center;
            line-height: 30px;
        }
        #table{
        	width:100%;
        }
    </style>
</head>


<body>
<form action="savepmd!savePhyMusicDraw.action" id="PhyMusicDrawTeacherFrom" name ="PhyMusicDrawTeacherFrom" method="post">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<!--<input id="errorInfo" type="hidden" name="errorInfo" value="${errorInfo?if_exists}"/>-->

<div class="row">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">基础数据</h4>
        </div>
        <div class="col-xs-12">
            <table id="table"  class="table table-border table-bordered table-bg table-hover table-sort col-xs-12">
                <tbody>
               
                <tr class="text-c">
				 
                    <td rowspan="4">体育教师配备情况</td>
                    <td colspan="2"  >
                        <div class="">
                            <div class="page-container">
                                <div class="col-xs-12 text-center">
                                    <h4 class="title">体育教师</h4>
                                </div>
                                <div class="mt-20">
                                    <table id="teacher" class="table table-border table-bordered table-bg table-hover table-sort">
                                        <tbody class="teacher" id="phyteacher">
											<#if PhyTeacherList?exists>
               			 <#list PhyTeacherList as teacher>
                                        <tr class="text-c del">
                                            <td rowspan="5">基础数据</td>
                                            <td>教师姓名:</td>
                                            <td><input type="text" length-min='2' length-max='16' required='true' china='true' class="input-text form-control" name="t_name" value="${teacher.T_NAME?if_exists}" /> &nbsp;&nbsp;&nbsp; <button class="btn btn-danger radius delete" onclick="deletePhyTeacher(${teacher.TM_ID?if_exists})">删除教师</button></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师类型:</td>
                                            <td> <select class="form-control"  name="type">
                                                <option selected='selected' value="1">体育</option>
                                                <option value="2">音乐</option>
                                                <option value="2">美术</option>
                                            </select></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师年龄:</td>
                                            <td> <input type="text" zhengnum="true" required='true' integer='true' length-min='1' length-max='2' class="input-text form-control" name="t_age" value="${teacher.T_AGE?if_exists}" />
                                            </td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>新录用老师文件编号:</td>
                                            <td><input type="text" file-no='true' id="file_no"  required='true' class="input-text form-control" name="file_no" value="${teacher.FILE_NO?if_exists}"/></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师专业:</td>
                                            <td>
                                                <div class="form-group">
                                                    <input type="text"  required='true' china='true'  class="input-text form-control" name="t_major" value="${teacher.T_MAJOR?if_exists}" />  
                                                </div>
                                                
                                            </td>
                                            
                                        </tr>

	</#list>
	</#if>

                                        </tbody>

                                    </table>
                                </div>
                            </div>
                           <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：   点击新录用体育老师增添新老师</font> <div class="text-right"> <button type="button" onclick="addTeacher1()"  class="btn btn-success radius"><strong>新录用体育教师</strong></button></div>


                        </div>
                    </td>
                    </tr>
                
                
                <tr class="text-c">
					 <td>新录用专职体育教师</td>
                    <td><input readonly="readonly" type="text" length-min='0' length-max='8' required='true' integer='true' class="input-text form-control" name="add_new_phy_teachers_full" id="add_new_phy_teachers_full" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.add_new_phy_teachers_full?if_exists}</#if>" /> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.add_new_phy_teachers_full?if_exists}</#if></label></p></td>
                </tr>
                <tr class="text-c">

                    <td>退休和离职体育教师</td>
                    <td><input type="text" zhengnum="true"  required='true' length-min='0' length-max='8' class="input-text form-control" name="retire_phy_teacher" id="retire_phy_teacher" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.retire_phy_teacher?if_exists}</#if>" />  <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.retire_phy_teacher?if_exists}</#if></label></p></td>
                </tr>
                <tr class="text-c">
                    <td>净增体育教师</td>
                    <td><input readonly="readonly" type="text" length-min='0' length-max='8' required='true' integer='true' class="input-text form-control" name="growth_phy_teacher" id="growth_phy_teacher" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.growth_phy_teacher?if_exists}</#if>" /> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.growth_phy_teacher?if_exists}</#if></label></p></td>
                </tr>



                <tr class="text-c">

                    <td rowspan="4">音乐教师配备情况</td>
                    <td colspan="2"  >
                        <div class="container">
                            <div class="page-container">
                                <div class="col-xs-12 text-center">
                                    <h4 class="title">音乐教师</h4>
                                </div>
                                <div class="mt-20">
                                    <table id="teacher2" class="table table-border table-bordered table-bg table-hover table-sort">
                                        <tbody class="teacher" id="musicteacher">
									<#if MusicTeacherList?exists>
               					 <#list MusicTeacherList as musicteacher>
                                        <tr class="text-c del">
                                            <td rowspan="5">基础数据</td>
                                            <td>教师姓名:</td>
                                            <td><input type="text" length-min='2' length-max='16' required='true' china='true'  class="input-text form-control" name="t_name" value="${musicteacher.T_NAME?if_exists}" /> &nbsp;&nbsp;&nbsp; <button class="btn btn-danger radius delete" onclick="deleteMusicTeacher(${musicteacher.TM_ID?if_exists})">删除教师</button></td>
                                        </tr>
                                        
                                        <tr class="text-c del">
                                            <td>教师类型:</td>
                                            <td> <select class="form-control"  name="type">
                                                <option value="1">体育</option>
                                                <option selected='selected'  value="2">音乐</option>
                                                <option value="3">美术</option>
                                            </select></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师年龄:</td>
                                            <td> <input type="text" zhengnum="true" length-min='1' length-max='2' required='true' integer='true' class="input-text form-control"  name="t_age" value="${musicteacher.T_AGE?if_exists}" />
                                            </td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>新录用老师文件编号:</td>
                                            <td><input type="text"  required='true' file-no='true' class="input-text form-control" name="file_no" value="${musicteacher.FILE_NO?if_exists}" /></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师专业:</td>
                                            <td>
                                                <div class="form-group">
                                                    <input type="text" required='true'  china='true'   class="input-text form-control" name="t_major" value="${musicteacher.T_MAJOR?if_exists}" />
                                                </div>
                                            </td>
                                        </tr>

</#list>
	</#if>

                                        </tbody>

                                    </table>
                                </div>
                            </div>
                          <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：   点击新录用音乐老师增添新老师</font>  <div class="text-right"><button type="button" onclick="addTeacher2()"  class="btn btn-success radius"><strong>新录用音乐教师</strong></button></div>


                        </div>
                    </td>
                </tr>
                
                
                <tr class="text-c">
					 <td>新录用专职音乐教师</td>
                    <td><input readonly="readonly" type="text" length-min='0' length-max='8' required='true' integer='true' class="input-text form-control" name="add_new_music_teachers_full" id="add_new_music_teachers_full" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.add_new_music_teachers_full?if_exists}</#if>" /> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.add_new_music_teachers_full?if_exists}</#if></label></p></td>
                </tr>
                
                <tr class="text-c">

                    <td>退休和离职音乐教师</td>
                    <td><input type="text" length-min='0' zhengnum="true" length-max='8' required='true'  class="input-text form-control" name="retire_music_teacher" id="retire_music_teacher" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.retire_music_teacher?if_exists}</#if>" />  <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.retire_music_teacher?if_exists}</#if></label></p></td>
                </tr>
                
                <tr class="text-c">
                    <td>净增音乐教师</td>
                    <td><input readonly="readonly" type="text" length-min='0' length-max='8' required='true' integer='true' class="input-text form-control" name="growth_music_teacher" id="growth_music_teacher" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.growth_music_teacher?if_exists}</#if>"/> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.growth_music_teacher?if_exists}</#if></label></p></td>
                </tr>
               
                <tr class="text-c">

                    <td rowspan="4">美术教师配备情况</td>
                    <td colspan="2"  >
                        <div class="container">
                            <div class="page-container">
                                <div class="col-xs-12 text-center">
                                    <h4 class="title">美术教师</h4>
                                </div>
                                <div class="mt-20">
                                    <table id="teacher3" class="table table-border table-bordered table-bg table-hover table-sort">
                                        <tbody class="teacher" id="drawteacher">
					<#if DrawTeacherList?exists>
               					 <#list DrawTeacherList as drawteacher>

                                        <tr class="text-c del">
                                            <td rowspan="5">基础数据</td>
                                            <td>教师姓名:</td>
                                            <td><input type="text" length-min='2' length-max='16' required='true' china='true'  class="input-text form-control" name="t_name" value="${drawteacher.T_NAME?if_exists}" /> &nbsp;&nbsp;&nbsp; <button class="btn btn-danger radius delete" onclick="deleteDrawTeacher(${drawteacher.TM_ID?if_exists})">删除教师</button></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师类型:</td>
                                            <td> <select class="form-control"  name="type">
                                                <option value="1">体育</option>
                                                <option value="2">音乐</option>
                                                <option selected='selected' value="3">美术</option>
                                            </select></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师年龄:</td>
                                            <td> <input type="text" zhengnum="true" required='true' integer='true'  class="input-text form-control"  name="t_age"  value="${drawteacher.T_AGE?if_exists}" />
                                            </td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>新录用老师文件编号:</td>
                                            <td><input type="text" required='true' file-no='true' class="input-text form-control" name="file_no"  value="${drawteacher.FILE_NO?if_exists}" /></td>
                                        </tr>
                                        <tr class="text-c del">
                                            <td>教师专业:</td>
                                            <td>
                                                <div class="form-group">
                                                    <input type="text" required='true' china='true' class="input-text form-control" name="t_major"  value="${drawteacher.T_MAJOR?if_exists}" />
                                                </div>
                                            </td>
                                        </tr>

</#list>
	</#if>

                                        </tbody>

                                    </table>
                                </div>
                            </div>
                           <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：   点击新录用美术老师增添新老师</font> <div class="text-right"><button type="button" onclick="addTeacher3()"  class="btn btn-success radius"><strong>新录用美术教师</strong></button></div>


                        </div>
                    </td>
                </tr>
                
                <tr class="text-c">
                    <td>新录用专职美术教师</td>
                    <td><input type="text" readonly="readonly" length-min='0' length-max='8' required='true' integer='true'class="input-text form-control" id="add_new_draw_teachers_full" name="add_new_draw_teachers_full" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.add_new_draw_teachers_full?if_exists}</#if>" /> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.add_new_draw_teachers_full?if_exists}</#if></label></p></td>
                </tr>
                <tr class="text-c">
                    <td>退休和离职美术教师</td>
                    <td><input type="text" length-min='0' zhengnum="true" length-max='8' required='true' integer='true' class="input-text form-control" id="retire_draw_teacher" name="retire_draw_teacher" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.retire_draw_teacher?if_exists}</#if>" /> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.retire_draw_teacher?if_exists}</#if></label></p></td>
                </tr>
                <tr class="text-c">
                    <td>净增美术教师</td>
                    <td><input readonly="readonly" type="text" length-min='0' length-max='8' required='true' integer='true' class="input-text form-control" name="growth_draw_teacher" id="growth_draw_teacher" value="<#if nowPhyMusicDrawDate?exists>${nowPhyMusicDrawDate.growth_draw_teacher?if_exists}</#if>"/> <p><label class="control-label">前一年数据：<#if oldPhyMusicDrawDate?exists>${oldPhyMusicDrawDate.growth_music_teacher?if_exists}</#if></label></p></td>
                </tr>
               

                </tbody>
            </table>
        </div>

    <div class=" col-sm-12 text-c">
        <button type="button"   class="btn btn-success radius"  onclick="getPreStep()" ><strong>上一步</strong></button>
        <button type="submit" id="next_btn_success_submit"  class="btn btn-success radius next_btn_success" ><strong>下一步</strong></button>
    </div>

</div>
</div>
</form>
	
	
	
	
<bord/>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>
<script src='../lib/vaildate.js'></script>
<script src='../lib/jquery.validate.min.js'></script>
<script src='../lib/validate-methods.js'></script>
<script>
	
	var a=50;
	var b=50;
	var c=50;
	
	var add_new_phy_teachers_full_js=document.getElementById("add_new_phy_teachers_full").value;
	if(add_new_phy_teachers_full_js==0){
		add_new_phy_teachers_full_js=0;
	}else{
		add_new_phy_teachers_full_js=parseInt(document.getElementById("add_new_phy_teachers_full").value);
	}
	var add_new_music_teachers_full_js=document.getElementById("add_new_music_teachers_full").value;
	if(add_new_music_teachers_full_js==0){
		add_new_music_teachers_full_js=0;
	}else{
		add_new_music_teachers_full_js=parseInt(document.getElementById("add_new_music_teachers_full").value);
	}
	var add_new_draw_teachers_full_js=document.getElementById("add_new_draw_teachers_full").value;
	
	if(add_new_draw_teachers_full_js==0){
		
		add_new_draw_teachers_full_js=0;
	}else{
		add_new_draw_teachers_full_js=parseInt(document.getElementById("add_new_draw_teachers_full").value);
	}
	
    $(function(){
        $("#phyteacher").on("click",".delete",function(){
            var thisPDel = $(this).parents('.del');
            thisPDel.next().next().next().next().remove();
            thisPDel.next().next().next().remove();
            thisPDel.next().next().remove();
            thisPDel.next().remove();
            thisPDel.remove();
            a--;
            add_new_phy_teachers_full_js--;
            
        });
    })
	$(function(){

        $("#musicteacher").on("click",".delete",function(){
            var thisPDel = $(this).parents('.del');
            thisPDel.next().next().next().next().remove();
            thisPDel.next().next().next().remove();
            thisPDel.next().next().remove();
            thisPDel.next().remove();
            thisPDel.remove();
            a--;
            add_new_music_teachers_full_js--;

        });
    })
    $(function(){

        $("#drawteacher").on("click",".delete",function(){
            var thisPDel = $(this).parents('.del');
            thisPDel.next().next().next().next().remove();
            thisPDel.next().next().next().remove();
            thisPDel.next().next().remove();
            thisPDel.next().remove();
            thisPDel.remove();
            a--;
            add_new_draw_teachers_full_js--;
        });
    })

    function addTeacher1(){
        $("#teacher").append($("<tr class='text-c del'>"+

                "<td rowspan='5'>基础数据</td>"+
                "<td>教师姓名:</td>"+
                "<td><input length-min='2' length-max='16' required='true' china='true'  class='input-text form-control' name='t_name' id='t_name' />&nbsp;&nbsp;&nbsp; <button class='btn btn-danger radius delete'>删除教师</button></td>"+
                "</tr>"+
                "<tr class='text-c del'>"+
                "<td>教师类型:</td>"+
                "<td> <select class='form-control'  name='type'>"+
                "<option value='1' selected='selected'>体育</option>"+
           		"<option value='2'>音乐</option>"+
           		"<option value='3'>美术</option>"+
                "</select></td>"+
                "</tr>"+
                "<tr class='text-c del'>"+
                "<td>教师年龄:</td>"+
                "<td> <input class='input-text form-control' length-min='1' length-max='2'  integer='true' required='true' name='t_age'/>"+
                "</td>"+
                "   </tr>"+
                "   <tr class='text-c del'>"+
                "   <td>新录用老师文件编号:</td>"+
                "<td><input id='"+a+"' onblur='checkPassword(this)' file-no='true' class='input-text form-control'   required='true' name='file_no' /> <span id='"+a+"passwordmessage'></span></td>"+
                "   </tr>"+
                "   <tr class='text-c '>"+
                "   <td>教师专业:</td>"+
                "<td>"+
                "   <div class='form-group'>"+
                "       <input class='input-text form-control'  required='true'  china='true'  name='t_major'/>"+
                "   </div>"+
                "   </td>"+
                "   </tr>"+
                "</tbody>"));
          
                a++;
        add_new_phy_teachers_full_js++;

    }
    
    function addTeacher2(){
    $("#teacher2").append($("<tr class='text-c del'>"+

            "<td rowspan='5'>基础数据</td>"+
            "<td>教师姓名:</td>"+
            "<td><input length-min='2' length-max='16' required='true' china='true'  class='input-text form-control' name='t_name' id='t_name' />&nbsp;&nbsp;&nbsp;<button class='btn btn-danger radius delete'>删除教师</button></td>"+
            "</tr>"+
            "<tr class='text-c del'>"+
            "<td>教师类型:</td>"+
            "<td> <select class='form-control'  name='type' >"+
            "<option value='1'>体育</option>"+
            "<option value='2' selected='selected'>音乐</option>"+
            "<option value='3'>美术</option>"+
            "</select></td>"+
            "</tr>"+
            "<tr class='text-c del'>"+
            "<td>教师年龄:</td>"+
            "<td> <input class='input-text form-control' length-min='1' length-max='2'  integer='true' required='true'  name='t_age'/>"+
            "</td>"+
            "   </tr>"+
            "   <tr class='text-c del'>"+
            "   <td>新录用老师文件编号:</td>"+
            "<td><input class='input-text form-control ' id='"+a+"' file-no='true' onblur='checkPassword(this)'  name='file_no'  required='true' /> <span id='"+a+"passwordmessage'></span></td>"+
            "   </tr>"+
            "   <tr class='text-c '>"+
            "   <td>教师专业:</td>"+
            "<td>"+
            "   <div class='form-group'>"+
            "       <input class='input-text form-control' name='t_major'  china='true'  required='true'/>"+
            "   </div>"+
            "   </td>"+
            "   </tr>"+
            "</tbody>"));
            a++;
            add_new_music_teachers_full_js++;
    }
    function addTeacher3(){
    $("#teacher3").append($("<tr class='text-c del'>"+

            "<td rowspan='5'>基础数据</td>"+
            "<td>教师姓名:</td>"+
            "<td><input length-min='2' length-max='16' required='true' china='true'  class='input-text form-control' name='t_name' id='t_name'/>&nbsp;&nbsp;&nbsp; <button class='btn btn-danger radius delete'>删除教师</button></td>"+
            "</tr>"+
            "<tr class='text-c del'>"+
            "<td>教师类型:</td>"+
            "<td> <select class='form-control'  name='type'>"+
            "<option value='1'>体育</option>"+
            "<option value='2'>音乐</option>"+
            "<option value='3' selected='selected'>美术</option>"+
            "</select></td>"+
            "</tr>"+
            "<tr class='text-c del'>"+
            "<td>教师年龄:</td>"+
            "<td> <input length-min='1'  length-max='2'  required='true' integer='true' class='input-text form-control'  name='t_age'/>"+
            "</td>"+
            "   </tr>"+
            "   <tr class='text-c del'>"+
            "   <td>新录用老师文件编号:</td>"+
            "<td><input  required='true' id='"+a+"' file-no='true' onblur='checkPassword(this)'   class='input-text form-control '  name='file_no' /> <span id='"+a+"passwordmessage'></span></td>"+
            "   </tr>"+
            "   <tr class='text-c '>"+
            "   <td>教师专业:</td>"+
            "<td>"+
            "   <div class='form-group'>"+
            "       <input  required='true'  china='true'  class='input-text form-control' name='t_major'/>"+
            "   </div>"+
            "   </td>"+
            "   </tr>"+
            "</tbody>"));
            a++;
        	add_new_draw_teachers_full_js++;
    }

    	var arr =new Array();
		$(function(){
		for(var i =0 ;i < $("input").length;i++){
		if($("input").eq(i).val()=="")
			arr.push(0);
		else{
			arr.push(1);
		}
	}
	
})
$(window).my_validate_layer({
        success_function:function(){
       		 $("form").attr("action", "savepmd!savePhyMusicDraw.action");
        },
        error_function:function(dom){
			
        },
        'trigger':{'event-dom':'#next_btn_success_submit','event':'click','dom':'form'}
        
    })
    

function getPreStep(){
		$("#PhyMusicDrawTeacherFrom").attr("action","repostFormTwo!intoRepostForm.action");
		$("#PhyMusicDrawTeacherFrom").submit();
	}

	 
	function deletePhyTeacher(tm_id){
		$("form").attr("action", "deleteTeacher!deletePhyTeacher.action?tm_id="+tm_id);
		$("form").submit();
	}
	function deleteMusicTeacher(tm_id){
		$("form").attr("action", "deleteTeacher!deleteMusicTeacher.action?tm_id="+tm_id);
		$("form").submit();
	}
	function deleteDrawTeacher(tm_id){
		$("form").attr("action", "deleteTeacher!deleteDrawTeacher.action?tm_id="+tm_id);
		$("form").submit();
	}
	
	function phyNum(){
			var growth_phy_teacher_js=document.getElementById("growth_phy_teacher");
			var retire_phy_teacher_js=document.getElementById("retire_phy_teacher").value;
			if(retire_phy_teacher_js.length==0||add_new_phy_teachers_full_js.length==0){
				growth_phy_teacher_js.value=0;
			}else{
				growth_phy_teacher_js.value=parseInt(add_new_phy_teachers_full_js) - parseInt(retire_phy_teacher_js);
			}
	}
	function musicNum(){
		var growth_music_teacher_js=document.getElementById("growth_music_teacher");
		var retire_music_teacher_js=document.getElementById("retire_music_teacher").value;
		if(retire_music_teacher_js.length==0||add_new_music_teachers_full_js.length==0){
				growth_music_teacher_js.value=0;
		}else{
			growth_music_teacher_js.value=parseInt(add_new_music_teachers_full_js) - parseInt(retire_music_teacher_js);
		}
	}
	function drawNum(){
		var retire_draw_teacher_js=document.getElementById("retire_draw_teacher").value;
		var growth_draw_teacher_js=document.getElementById("growth_draw_teacher");
		
		if(retire_draw_teacher_js.length==0||add_new_draw_teachers_full_js.length==0){
				growth_draw_teacher_js.value=0;
			}else{
				growth_draw_teacher_js.value=parseInt(add_new_draw_teachers_full_js) - parseInt(retire_draw_teacher_js);
			}
			
		
	}
	function new_phy(){
		
		if(add_new_phy_teachers_full_js.length==0){
			document.getElementById("add_new_phy_teachers_full").value =0;
		}else{
			document.getElementById("add_new_phy_teachers_full").value =add_new_phy_teachers_full_js;
			
		}
		
	}
	
	function new_music(){
		if(add_new_music_teachers_full_js.length==0){
			document.getElementById("add_new_music_teachers_full").value =0;
		}else{
			document.getElementById("add_new_music_teachers_full").value =add_new_music_teachers_full_js;
		}
	}
	
	function new_draw(){
		if(add_new_draw_teachers_full_js.length==0){
			document.getElementById("add_new_draw_teachers_full").value =0
			
		}else{
			document.getElementById("add_new_draw_teachers_full").value =add_new_draw_teachers_full_js;
			
		}
	}
	
	window.setInterval("phyNum()",100);
	window.setInterval("musicNum()",100);
	window.setInterval("drawNum()",100);
	window.setInterval("new_phy()",100);
	window.setInterval("new_music()",100);
	window.setInterval("new_draw()",100);
   $(function(){
		<#if errorInfo?exists>
		layer.msg('${(errorInfo)?if_exists}');
		</#if>
		
	})
	
	function getToken(){
    	return $("#token").val();
    }
    function checkPassword(e)
    {  
    	 
         var xhr = ajaxFunction();
         
         xhr.open("get","savepmd!id_card.action?card_id="+$("#"+e.id).val()+"&token="+getToken(),true); 
         xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");   
         xhr.onreadystatechange = function()
         {  
             if(xhr.readyState==4)
             {
                 if(xhr.status==200)
                 {      
                    var vp = xhr.responseText;
                    
                    $("#token").attr("value",xhr.getResponseHeader("token"));
                    if (vp=="1") {
                    	$("#"+e.id).val("");
						document.getElementById(e.id+"passwordmessage").innerHTML = "<font color='red'>&nbsp;&nbsp;文件编号重复！</font>";  
						return false; 
					} else if(vp=="2"){
						
						document.getElementById(e.id+"passwordmessage").innerHTML = "<font color='green'>&nbsp;&nbsp;验证通过！</font>"; 
						return true;   
					} else if(vp=="3"){
						$("#"+e.id).val("");
						document.getElementById(e.id+"passwordmessage").innerHTML = "<font color='red'>&nbsp;&nbsp;请输入信息！</font>";  
						return false;  
					}else if(vp=="4"){
						$("#"+e.id).val("");
						document.getElementById(e.id+"passwordmessage").innerHTML = "<font color='red'>&nbsp;&nbsp;特殊字符只能输入-_ ,请重新输入！</font>";  
						return false;  
					}
                 }  
             }  
        }  
        xhr.send();  
    }
    

    
    function ajaxFunction()
    {  
         var xmlHttp;  
         try{  
                xmlHttp=new XMLHttpRequest();  
            }  
         catch (e){  
                      try{  
                             xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");  
                         }  
        catch (e){  
                      try{  
                             xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");  
                         }  
         catch (e){}  
                 }  
           }  
           
    return xmlHttp;  
   
   }
	
</script>
</@p.index>
