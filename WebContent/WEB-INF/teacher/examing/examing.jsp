<%@page import="cn.nsu.ccl.teacher.entity.ExamingInfoEntity"%>
<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>开始</title>
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
                        <h5>开始考试 <small>--请在右侧获取教师口令（点击一次更新一次）</small></h5>
						<div class="ibox-tools">
                            <a href="javascript:void(0);" onclick="refresh()" class="collapse-link" style="color:#0066FF">刷新</a>
                            &nbsp;|
                            <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);" onclick="getTeacherToken(${examId})" style="color:#0066FF">获取教师口令</a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                	<th>序号</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%ArrayList<ExamingInfoEntity> list = (ArrayList<ExamingInfoEntity>)request.getAttribute("examList");
                            	for(int i = 0; i < list.size();i++){
                            		ExamingInfoEntity examingInfoEntity = list.get(i);
                            		request.setAttribute("exam", examingInfoEntity);
                            %>
                                <tr class="gradeX">
                                    <td><%=i+1 %></td>
                                    <td>${exam.studentNumber }</td>
                                    <td>${exam.studentName }</td>
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
        <!-- 创建教师口令弹窗DIV -->
        <div class="modal fade" id="showMessage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="messageName">教师口令</h4>
                </div>
                <div class="modal-body">
                	<p id="messageContent"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="createExamSubmit()" class="btn btn-default" data-dismiss="modal">确定</button>
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
    	//保存考试id
    	var examIdQ = -1;
    	//保存考试名称
    	var examNameQ = "";
    	
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
        //刷新界面
        function refresh(){
        	location.reload(true);
        }
        //使用ajax获取教师口令
        function getTeacherToken(examId){
        	//使用ajax连接后台获取数据
        	//使用XMLHttpRequest方法，实际上在上面的jquery中也是用的这个方法，只不过已经给我们封装好了
            var request = new XMLHttpRequest();
            //使用open方法填写参数，最后一个true表示使用使用异步提交，可以省略，默认值就是true
            request.open("POST","teacherGetToken?examId="+examId,true);
            //发送ajax请求
            request.send();
            //监听请求的状态，主要有0 1 2 3 4 这几种，但是一边只会监听2 3 4 ，其中4表示成功
            request.onreadystatechange = function(){
                //判断只有当请求成功时才进行下一步
                if(request.readyState===4){
                //判断只有当网页的返回码为200 OK时才进行下一步
                    if(request.status===200){
                        //使用JSON.parse方法格式化返回的json数据
                        var data = JSON.parse(request.responseText);
                        console.log(data);
                        console.log(data.token)
                        //显示教师口令
						//设置提示内容
						document.getElementById("messageContent").innerHTML="教师口令为：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+data.token+"<br/>提示：教师口令中所有字母均为大写，并且每次单击“获取教师口令”按钮都将重新生成教师口令。";
						$("#showMessage").modal('show');
					}
                }
            }
        }
    </script>
    

</body>

</html>
