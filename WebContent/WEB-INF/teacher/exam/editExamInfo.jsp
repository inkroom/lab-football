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
    <div class="wrapper wrapper-content animated fadeInRight" id="choseQuestionLibList">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>创建考试信息 <small>--请选择一个题库进行考试创建</small></h5>
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
                                	<td><a href="javascipt:void(0);" onclick="editExam(<%=examInfoEntity.getExamId()%>)">编辑</a>
                                	|&nbsp;&nbsp;<a href="javascipt:void(0);" onclick="deleteExam(<%=examInfoEntity.getExamId()%>)">删除</a>
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
    <!-- 创建提示弹窗DIV -->
        <div class="modal fade" id="showMessage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="messageName"></h4>
                </div>
                <div class="modal-body">
                	<p id="messageContent"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消创建</button>
                    <button type="button" onclick="createExamSubmit()" class="btn btn-default" data-dismiss="modal">确认创建</button>
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
    	var libraryId = -1;
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
    </script>
    

</body>

</html>
