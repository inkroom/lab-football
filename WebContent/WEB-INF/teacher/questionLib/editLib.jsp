<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>编辑现有题库</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${ctx}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="${ctx}/assets/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${ctx}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<%List<QuestionLibList> list = (List<QuestionLibList>)request.getAttribute("questionLibList");
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
                                	QuestionLibList questionLibListEntity = list.get(i);%>
                                <tr class="gradeA">
                               
                                    <td> <%= i+1%></td>
                                    <!-- <td>Trident</td> -->
                                    <td><%=questionLibListEntity.getLibraryName()%>
                                    </td>
                                    <td><%=questionLibListEntity.getsChoice() %></td>
                                    <td class="center"><%=questionLibListEntity.getmChoice() %></td>
                                    <td class="center"><%=questionLibListEntity.getTofChoice() %></td>
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
    <script src="${ctx}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/assets/js/bootstrap.min.js?v=3.3.6"></script>



    <script src="${ctx}/assets/js/plugins/jeditable/jquery.jeditable.js"></script>

    <!-- Data Tables -->
    <script src="${ctx}/assets/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${ctx}/assets/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- 自定义js -->
    <script src="${ctx}/assets/js/content.js?v=1.0.0"></script>


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
      
      
        //调用jquery中的ajax
        function deleteLib(){
    	$.ajax({
    
        
        
            //设定提交方式，主要是"GET"和"POST"
            type:"POST",
            //设定提交的url，这里只能选择本地的，如果需要调用其他域的资源，请google解决跨域问题
           // url:"teacherDeleteQuestionLib?libraryNames="+"我是要传给后台的字符串",//$("input[name='selectFlag']:checkbox").val(),//-----修改按类查找为按name值查找
           url:"teacherDeleteQuestionLib",
        		   data:{
        			   "str": getSelected()},
        		   
        		   //设定后台返回的格式，一般都是直接使用json，这一句不能少，否则当后台返回数据时，不会调用success方法
            dataType:"json",
            //当后台成功返回数据时调用该方法，data参数表示被jquery中的ajax格式化的json数据（实际上在非jquery的ajax中需要我们手动格式化，纯JS的方法我也写在了注释里面。jquery中格式json数据的方法是parse）
            success:function(data){

    			if("success"===data.state){
    				removeRow();
					alert("删除成功");
				}
    		
    			if("fail"===data.state){
					alert("删除失败")
				}
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
           return str;
          }
          function removeRow(){  
        	   
        	    $("input[name='selectFlag']:checked").each(function() { // 遍历选中的checkbox
    	            n = $(this).parents("tr").index();  // 获取checkbox所在行的顺序
    	            document.getElementsByTagName('tbody')[0].deleteRow(n);
    	       //     $(".table table-striped table-bordered table-hover dataTables-example").find("tr:eq("+n+")").remove();
    	        });
        	};
      
        
    </script>

    
    

</body>

</html>
