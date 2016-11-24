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
                                	<td><a href="javascipt:void(0);" onclick="editExam(<%=examInfoEntity.getExamId()%>,'<%=examInfoEntity.getExamName()%>')">编辑</a>
                                	|&nbsp;&nbsp;<a href="javascipt:void(0);" 
                                	onclick="deleteExam(<%=examInfoEntity.getExamId()%>)">删除</a>
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
    <!-- 修改考试信息 -->
<div class="wrapper wrapper-content animated fadeInRight" id="editExam" style="display:none">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5 id="head"></h5>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <td>考试名称</td>
                                    <th>考试开始时间</th>
                                    <th>考试结束时间</th>
                                    <th>单选题个数</th>
                                    <th>单选题分数</th>
                                    <th>多选题个数</th>
                                    <th>多选题分数</th>
                                    <th>判断题个数</th>
                                    <th>判断题分数</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="gradeC">
                                	<td id="examName"></td>
                                	<td><input class="form-control layer-date" id="examStartTime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})"></td>
                                	<td><input class="form-control layer-date" id="examEndTime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})"></td>
                                    <td><select class="form-control" id="danNum"></select></td>
                                    <td><select class="form-control" id="danScore">
                                    	<%for(int i = 1 ; i <= 20;i++){ %>
                                    	<option><%=i %></option>
                                    	<%} %></select>
                                    </td>
                                    <td><select class="form-control" id="duoNum"></select></td>
                                    <td><select class="form-control" id="duoScore">
                                    	<%for(int i = 1 ; i <= 20;i++){ %>
                                    	<option><%=i %></option>
                                    	<%} %></select>
                                    </td>
                                    <td><select class="form-control" id="pNum"></select></td>
                                    <td><select class="form-control" id="pScore">
                                    	<%for(int i = 1 ; i <= 20;i++){ %>
                                    	<option><%=i %></option>
                                    	<%} %></select>
                                    </td>
                                    <td><button class="btn btn-primary " type="button" onclick="editExamSubmit()"><i class="fa fa-check"></i>&nbsp;提交</button></td>
                                </tr>
                            </tbody>
                            <tfoot>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
            <!-- 表单检测提示弹窗DIV -->
        <div class="modal fade" id="form" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="formName"></h4>
                </div>
                <div class="modal-body">
                	<p id="formContent"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消更新</button>
                    <button type="button" onclick="createExamSubmit()" class="btn btn-default" data-dismiss="modal">确认更新</button>
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
        function editExam(examId,examName){
        	console.log(examId);
        	console.log(examName);
        	//保存考试id
        	examIdQ = examId;
        	//保存考试名称
        	examNameQ = examName;
        	//发送ajax获取题库信息
        	//使用XMLHttpRequest方法，实际上在上面的jquery中也是用的这个方法，只不过已经给我们封装好了
            var request = new XMLHttpRequest();
            //使用open方法填写参数，最后一个true表示使用使用异步提交，可以省略，默认值就是true
            request.open("POST","teacherGetExamInfoByExamId?examId="+examId,true);
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
                        console.log(data[0].choiceNumber);
                        //判断返回的json数据是否为空
                        if(data[0].libraryId!=0){
                        	//不为空则开始获取数据
                        	//获取单选个数
                        	var danNum = data[0].choiceNumber;
                        	//获取多选题数量
                        	var duoNum = data[0].multiputeChoiceNumber;
                        	//获取判断题数量
                        	var pNum = data[0].judgeNumber;
                        	//调用方法动态创建修改考试状态信息的div
                        	createExam(examId,examName,danNum,duoNum,pNum);
                        }
                    }
                }
            };
        	
        }
      	//动态创建更新考试信息列表
        function createExam(examId,examName,danNum,duoNum,pNum){
        	//修改表格提示的标题
        	document.getElementById("head").innerHTML="修改考试信息 <small>--"+examName+"</small>";	
        	//动态添加option
        	//单选题数量
        	for(var i = 1; i < duoNum;i++){
        		$("#danNum").append("<option value='"+i+"'>"+i+"</option>");
        	}
        	//多选题数量
        	for(var i = 1;i < duoNum;i++){
        		$("#duoNum").append("<option value='"+i+"'>"+i+"</option>");
        	}
        	//判断题数量
        	for(var i = 1;i < duoNum;i++){
        		$("#pNum").append("<option value='"+i+"'>"+i+"</option>");
        	}
        	//添加考试名称
        	document.getElementById("examName").innerHTML=examName;
        	//将选择考试的div隐藏
        	document.getElementById("showExamList").style.display="none";
        	//显示用于创建考试的div
        	document.getElementById("editExam").style.display="block";
        }
      	function editExamSubmit(){
      		//获取考试名称
        	var examName = examNameQ;
        	//获取考试开始时间
        	var examStartTime = $("#examStartTime").val();
        	//获取考试结束时间
        	var examEndTime = $("#examEndTime").val();
        	//获取单选题个数
        	var danNum = $("#danNum").val();
        	//获取单选题分数
        	var danScore = $("#danScore").val();
        	//获取多选题个数
        	var duoNum = $("#duoNum").val();
        	//获取多选题分数
        	var duoScore = $("#duoScore").val();
        	//获取判断题个数
        	var pNum = $("#pNum").val();
        	//获取判断题分数
        	var pScore = $("#pScore").val();
        	//验证考试开始时间是否为空
        	if(examStartTime===null||examStartTime===""){
        		console.log("检测考试开始时间和结束时间是否符合逻辑");
        		document.getElementById("formName").innerHTML="提示";
        		document.getElementById("formContent").innerHTML="考试开始时间不能为空";
        		$("#form").modal('show');
        		return;
        	}        	
        	//验证考试结束时间是否为空
        	if(examEndTime===null||examEndTime===""){
        		console.log("检测考试开始时间和结束时间是否符合逻辑");
        		document.getElementById("formName").innerHTML="提示";
        		document.getElementById("formContent").innerHTML="考试结束时间不能为空";
        		$("#form").modal('show');
        		return;
        	}
        	//验证考试开始时间与结束时间是否符合正常逻辑
        	if(examStartTime>examEndTime){
        		console.log("检测考试开始时间和结束时间是否符合逻辑");
        		document.getElementById("formName").innerHTML="提示";
        		document.getElementById("formContent").innerHTML="考试开始时间和考试结束时间不符合逻辑，请修改";
        		$("#form").modal('show');
        		return;
        	}
        	//计算当前的分数，并弹窗让教师确认创建考试
        	
        	//初始化总值
        	var count = danNum*danScore+duoNum*duoScore+pNum*pScore;
        	console.log(danNum);
        	console.log(danScore);
        	console.log(duoNum);
        	console.log(danScore);
        	console.log(pNum);
        	console.log(pScore);
    		document.getElementById("messageName").innerHTML="提示";
    		document.getElementById("messageContent").innerHTML="您创建的考试名称是："+examName+"<br/>"
    		+"考试开始时间为："+examStartTime+"<br/>"
    		+"考试结束时间为："+examEndTime+"<br/>"
    		+"本次考试的总分为："+count+"分"+"<br/>";
    		$("#showMessage").modal('show');
        }
        //提交创建考试请求的表单
        function createExamSubmit(){
        	//获取考试名称
        	var examName = examNameQ;
        	//获取考试id
        	var examId = examIdQ;
        	//获取考试开始时间
        	var examStartTime = $("#examStartTime").val();
        	//获取考试结束时间
        	var examEndTime = $("#examEndTime").val();
        	//获取单选题个数
        	var danNum = $("#danNum").val();
        	//获取单选题分数
        	var danScore = $("#danScore").val();
        	//获取多选题个数
        	var duoNum = $("#duoNum").val();
        	//获取多选题分数
        	var duoScore = $("#duoScore").val();
        	//获取判断题个数
        	var pNum = $("#pNum").val();
        	//获取判断题分数
        	var pScore = $("#pScore").val();
        	//使用XMLHttpRequest方法，实际上在上面的jquery中也是用的这个方法，只不过已经给我们封装好了
            var request = new XMLHttpRequest();
            //使用open方法填写参数，最后一个true表示使用使用异步提交，可以省略，默认值就是true
            request.open("POST","teacherEditExamDo?examId="+examId
            		+"&examStartTime="+examStartTime+"&examEndTime="+examEndTime
            		+"&danNum="+danNum+"&danScore="+danScore
            		+"&duoNum="+duoNum+"&duoScore="+duoScore
            		+"&pNum="+pNum+"&pScore="+pScore,true);
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
                        //创建成功
                        if("success"===data.state){
                        	console.log("更新成功");
                        }else if("fail"===data.state){
							console.log("更新失败");
                        }
                    }
                }
            };
      	}
    </script>
    

</body>

</html>
