<%@page import="cn.nsu.ccl.teacher.entity.ExamInfoEntity"%>
<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>编辑考试信息</title>
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
<!-- 查看考试信息列表 -->
    <div class="wrapper wrapper-content animated fadeInRight" id="showExamList">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>开始考试 <small>--请选择考试信息</small></h5>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>考试编号</th>
                                    <th>考试名称</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%ArrayList<ExamInfoEntity> list = (ArrayList<ExamInfoEntity>)request.getAttribute("examInfoList");
                            	for(int i = 0; i < list.size();i++){
                            		ExamInfoEntity examInfoEntity = list.get(i);
                            		request.setAttribute("exam", examInfoEntity);
                            %>
                                <tr class="gradeX">
                                    <td><%=i+1 %></td>
                                    <td>${exam.examName }</td>
                                    <td>${exam.startTime }</td>
                                    <td>${exam.endTime }</td>
                                    <td>
                                    <span id="notClick<%=examInfoEntity.getExamId() %>" style="display:none">无考生信息</span>
                                    <a href="javascript:void(0);"  onclick="startExam(<%=examInfoEntity.getExamId()%>)" style="display:block" id="click<%=examInfoEntity.getExamId() %>">开始考试</a>
                                    </td>
                                </tr>
                                <%} %>
                            </tbody>
                            <tfoot>
                            </tfoot>
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
        <!-- layerDate plugin javascript -->
    <script src="js/plugins/layer/laydate/laydate.js"></script>


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

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData([
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row"]);

        }
        //动态设置添加考生标签
        var readJson = JSON.parse('<%=request.getAttribute("jsonArray")%>');
        console.log(readJson);
    	for(var p in readJson){
    		//如果有考生信息，就隐藏到带点击事件的标签，显示不带点击事件的标签
    		if(readJson[p].state==="exist"){
    			//显示不带点击时间的标签
    			//找到带点击事件的id并显示
    			var click = "click"+readJson[p].examId;
    			document.getElementById(click).style.display="block";
    			//找到不带点击事件的id并隐藏
    			var notClick = "notClick"+readJson[p].examId;
    			document.getElementById(notClick).style.display="none";
    		}
    		//如果不存在考生信息，就隐藏不带点击时间的标签，显示带点击时间的标签
    		if(readJson[p].state==="null"){
    			//显示带点击时间的标签
    			//找到不带点击时间的id并隐藏
    			var notClick = "notClick"+readJson[p].examId;
    			document.getElementById(notClick).style.display="none";
    			//找到带点击时间的id并显示
    			var click = "click"+readJson[p].examId;
    			console.log("id="+click);
    			document.getElementById(click).style.display = "block";
    		}
    	}
    	//跳转到开始考试的界面
    	function startExam(examId){
    		window.location.href='teacherStartExamDo?examId='+examId;
    	}
    </script>
    

</body>

</html>
