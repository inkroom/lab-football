<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 数据表格</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<%ArrayList<QuestionLibListEntity> list = (ArrayList<QuestionLibListEntity>)request.getAttribute("questionLibList");
%>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>题库信息 <small></small></h5>
                        <div class="ibox-tools">
                        	
                        	 <a class="J_menuItem" href="teacherToCreatelib"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true" title="添加">添加</span></a>
                        	 <a class="J_menuItem" href="javascript:void(0);" onclick="deleteLib()" ><span class="glyphicon glyphicon-minus-sign" aria-hidden="true" title="删除">删除</span></a>
                        	
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>题库号</th>
                                    <th>题库名称</th>
                                    <th>单选题个数</th>
                                    <th>多选题个数</th>
                                    <th>判断题个数</th>
                                    <th><label><input name="isDelete" type="checkbox" value="">操作</label> </th>
                                </tr>
                            </thead>
                            <tbody>
                               
                                <%for(int i = 0; i <list.size();i++){
                                	QuestionLibListEntity questionLibListEntity = list.get(i);%>
                                <tr class="gradeA">
                               
                                    <td> <%= i+1%></td>
                                    <!-- <td>Trident</td> -->
                                    <td><%=questionLibListEntity.getLibraryName()%>
                                    </td>
                                    <td><%=questionLibListEntity.getChoiceNumber() %></td>
                                    <td class="center"><%=questionLibListEntity.getMultiputeChoiceNumber()%></td>
                                    <td class="center"><%=questionLibListEntity.getJudgeNumber()%></td>
                                    <td><input class="isSelected" id="id<%=i+1 %>" name="selectFlag" type="checkbox"  value="<%= questionLibListEntity.getLibraryName()%>"></td>
                                </tr>
                                <% } %>
                                 
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>



    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>

    <!-- Data Tables -->
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
            $('.dataTables-example').dataTable();

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable('../example_ajax.php', {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1]);
                },
                "submitdata": function (value, settings) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition(this)[2]
                    };
                },

                "width": "90%",
                "height": "100%"
            });
           

        });
        window.onload = function(){
        	 var che = document.getElementsByTagName("thead")[0].children[0].children[5].children[0].children[0];
         	che.onchange = function(){

                 	var tbody = document.getElementsByTagName("tbody")[0];
                 	for(var i=0;i<tbody.rows.length;i++){
                 		 if(tbody.rows[i].style.display!="none") 
                 			{
                 			 if( tbody.rows[i].cells[5].children[0].checked==false&&che.checked==true)
                 			 tbody.rows[i].cells[5].children[0].checked=true;
                 			 else if(tbody.rows[i].cells[5].children[0].checked==true&&che.checked==false)
                 				 tbody.rows[i].cells[5].children[0].checked =false;
                 			}
                 	}
         	
         	}
        }

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData([
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row"]);

        }
        $(function(){
        	
        	
        });
        //纯js提交数据到后台-------------------------------
       /*  function deleteLib() {  
        	var truthBeTold = window.confirm("该操作不可逆，确认要删除么？"); 
        	
        	if (truthBeTold) { 
        		var array = new Array(); //用于保存 选中的那一条数据的ID   
                var flag; //判断是否一个未选  
                var i=1;
                 
                $("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox 
                	
                	console.log(document.getElementById("id"+i).checked);
                			 
                            if (document.getElementById("id"+i).checked) { //判断是否选中    
                                flag = true; //只要有一个被选择 设置为 true 
                               
                            } 
                            i++;
                        })  
                if (flag) {  
                	i=0;
                    $("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                    	 i++;
                    	
                                if (document.getElementById("id"+i).checked) { //判断是否选中    
                                	
                                    array.push($(this).val()); //将选中的值 添加到 array中  
                                    //str+=$(this).val()+",";
                                    alert($(this).val());  
                                }  
                            })  
                    //将要集体删除的数据 传递给action处理   
                   // window.self.location = "teacherDeleteQuestionLib?libraryNames=" + array;  
                            var request = new XMLHttpRequest();
                            var text="蔡洁是个大傻瓜";
                    request.open("POST","teacherDeleteQuestionLib?libraryNames="+text,true);
                    request.send();
                    request.onreadystate=new function(){
                    	alert("request.readyState="+request.readyState);
                    	if(request.readyState===4){
                    		if(request.status===200||request.status===306){
                    			var data = JSON.parse(request.responseTxt);
                    			if("success"===data.state){
            						alert("删除成功");
            					}
                    		
                    			if("fail"===data.state){
            						alert("删除失败")
            					}
                    			
                      		}
                    		
                    	}
                    	else
                    		
                    }
                } else {  
                    alert("请至少选择一个题库");  
                }  
        	} 
           */
           //----request.readyState="+request.readyState===1------------------------------------------------
          /*  $(document).ready(function(){
    //监听id为class的select控件的改变动作，当这个控件所选中的控件改变时会出发这个function
    $("#class").change(function(){  */
        //调用jquery中的ajax
        function deleteLib(){
    	var list = $('[name=ids]').length;

    	alert("changdu "+list);
    
    	$.ajax({
    
        
        
            //设定提交方式，主要是"GET"和"POST"
            type:"POST",
            //设定提交的url，这里只能选择本地的，如果需要调用其他域的资源，请google解决跨域问题
           // url:"teacherDeleteQuestionLib?libraryNames="+"我是要传给后台的字符串",//$("input[name='selectFlag']:checkbox").val(),//-----修改按类查找为按name值查找
           url:"teacherDeleteQuestionLib?libraryNames="+getSelected(),
        		   //设定后台返回的格式，一般都是直接使用json，这一句不能少，否则当后台返回数据时，不会调用success方法
            dataType:"json",
            //当后台成功返回数据时调用该方法，data参数表示被jquery中的ajax格式化的json数据（实际上在非jquery的ajax中需要我们手动格式化，纯JS的方法我也写在了注释里面。jquery中格式json数据的方法是parse）
            success:function(data){
               /*  //清空id为name的select控件
                $("#name").empty();
                //给id为那么的select控件添加一个选项
                $("#name").append("<option>请选择姓名</option>");
                //循环遍历整个data（JSON数据），并给id为name的select控件添加option选项
                $.each(data,function(i,n){
                    $("#name").append("<option>"+data[i].name+"</option>"); 
                    
                });*/
                alert("传回成功");
            },
            //当返回数据不成功时的操作
            error:function(jqXHR,XMLResponse){
                alert(arguments[1]);
                alert(XMLResponse.responseText);
                alert("发生错误:"+jqXHR.status);
            }
        });
/*     });
});
     */    } 
          
          function getSelected(){
        	  var str="";
        	  var i = 0;
              $("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox
              	 i++;
                          if (document.getElementById("id"+i).checked) { //判断是否选中    
                              //array.push($(this).val()); //将选中的值 添加到 array中  
                              str+=$(this).val()+",";//拼接字符串，以逗号分隔  
                              //alert($(this).val());  
                          }  
           })  
          }
      
        
    </script>

    
    

</body>

</html>
