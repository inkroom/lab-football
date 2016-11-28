<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>创建考试信息</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight"
		id="choseQuestionLibList">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							创建考试信息 <small>--请选择一个题库进行考试创建</small>
						</h5>
					</div>
					<div class="ibox-content">

						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>题库编号</th>
									<th>题库名称</th>
									<th>单选题个数</th>
									<th>多选题个数</th>
									<th>判断题个数</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<%ArrayList<QuestionLibListEntity> list = (ArrayList<QuestionLibListEntity>)request.getAttribute("questionLibList");
                            	for(int i = 0; i < list.size();i++){
                            		QuestionLibListEntity questionLibListEntity = list.get(i);
                            %>
								<tr class="gradeX">
									<td><%=i+1 %></td>
									<td><%=questionLibListEntity.getLibraryName() %></td>
									<td><%=questionLibListEntity.getChoiceNumber() %></td>
									<td><%=questionLibListEntity.getMultiputeChoiceNumber() %></td>
									<td><%=questionLibListEntity.getJudgeNumber() %></td>
									<td><a href="javascript:void(0);"
										onclick="createExam(
                                    <%=questionLibListEntity.getLibraryId() %>,
                                    '<%=questionLibListEntity.getLibraryName()%>',
                                    <%=questionLibListEntity.getChoiceNumber()%>,
                                    <%=questionLibListEntity.getMultiputeChoiceNumber()%>,
                                    <%=questionLibListEntity.getJudgeNumber()%>
                                    )">用此题库创建考试</a></td>
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
<!--创建考试信息DIV-->
<div class="wrapper wrapper-content animated fadeInRight"
		id="createExam" style="display: none">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							创建考试信息 <small>--请填写相关信息</small>
						</h5>
					</div>
					<div class="ibox-content">

						<table	class="table table-striped table-bordered table-hover dataTables-example">
						
							<tbody>
								<tr>
									<th colspan="2">题库名称</th>
									<td id="questionLibName" colspan="2"></td>
								</tr>
								<tr>
									<th colspan="2">考试名称</th>
                                    <td colspan="2"><input class="form-control" id="examName" /></td>
								</tr>
								<tr>
									<th>考试开始时间</th>
                                    <td><input class="form-control layer-date"
                                        id="examStartTime" placeholder="YYYY-MM-DD hh:mm:ss"
                                        onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})"></td>
									 <th>考试结束时间</th>
                                    <td><input class="form-control layer-date"
                                        id="examEndTime" placeholder="YYYY-MM-DD hh:mm:ss"
                                        onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})"></td>
								</tr>
								<tr>
									 <th>单选题个数</th>
                                    <td><select class="form-control" id="danNum"></select></td>
                                     <th>单选题分数</th>
                                    <td><select class="form-control" id="danScore">
                                            <%for(int i = 1 ; i <= 20;i++){ %>
                                            <option><%=i %></option>
                                            <%} %>
                                    </select></td>
								</tr>
								<tr>
                                    <th>多选题个数</th>
                                    <td><select class="form-control" id="duoNum"></select></td>
									 <th>多选题分数</th>

                                    <td><select class="form-control" id="duoScore">
                                            <%for(int i = 1 ; i <= 20;i++){ %>
                                            <option><%=i %></option>
                                            <%} %>
                                    </select></td>
                                </tr>
                                   <tr>
                                    <th>判断题个数</th>
                                    <td><select class="form-control" id="pNum"></select></td>
                                      <th>判断题分数</th>
                                    <td><select class="form-control" id="pScore">
                                            <%for(int i = 1 ; i <= 20;i++){ %>
                                            <option><%=i %></option>
                                            <%} %>
                                    </select></td>
                                </tr>
                                <tr>
                                  
                                    <td colspan="4"><button class="btn btn-primary btn-rounded btn-block"  type="button"
                                            onclick="toCreateExamSubmit()">
                                            <i class="fa fa-check"></i>&nbsp;提交
                                        </button></td>

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
	<!-- 上传学生信息 -->
	<div class="wrapper wrapper-content" id="uploadStudent"
		style="display: none">
		<div class="row">
			<div class="col-sm-12">
				<div class="middle-box text-center animated fadeInRightBig">
					<h3 class="font-bold" id="studentMessage"></h3>
					<br />
					<div class="error-desc">
						<form action=teacherUploadStudentExcel method="post"
							enctype="multipart/form-data">
							<input type="hidden" id="examNameInput" name="examName" value="">
							<!-- 添加的 accept属性使文件只显示excel文件 -->
							<input type="file" name="file" class="form-control"
								required="required" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"/><br /> <input type="submit"
								class="btn btn-primary btn-rounded btn-block btn btn-w-m btn-success"
								value="上传题库资料" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 表单检测提示弹窗DIV -->
	<div class="modal fade" id="form" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
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
	<div class="modal fade" id="showMessage" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="messageName"></h4>
				</div>
				<div class="modal-body">
					<p id="messageContent"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消创建</button>
					<button type="button" onclick="createExamSubmit()"
						class="btn btn-default" data-dismiss="modal">确认创建</button>
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
        //选择题库编号，开始创建考试
        function createExam(id,name,danNum,duoNum,pNum){
        	//将题库编号存在全局，便于其他function调用
        	libraryId = id;
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
        	//动态添加考试名称
        	document.getElementById("questionLibName").innerHTML=name;
        	//隐藏选择题库的div
        	document.getElementById("choseQuestionLibList").style.display="none";
        	//显示用于创建考试的div
        	document.getElementById("createExam").style.display="block";
        }
        //验证创建考试请求的表单
        function toCreateExamSubmit(){
        	//获取考试名称
        	var examName = $("#examName").val();
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
        	//验证考试名称是否填写
        	if(examName===null||examName===""){
        		document.getElementById("formName").innerHTML="提示";
        		document.getElementById("formContent").innerHTML="请填写考试名称";
        		$("#form").modal('show');
        		return;
        	}
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
        	var examName = $("#examName").val();
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
            request.open("POST","teacherCreateExamDo?libraryId="+libraryId+"&examName="+examName
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
                        	//隐藏填写考试信息的div
                        	document.getElementById("createExam").style.display="none";
                        	//操作上传考生信息的div
                        	//设置提示标题
                        	document.getElementById("studentMessage").innerHTML="上传考生信息-"+examName;
                        	//给input隐藏域的value赋值
                        	$("#examNameInput").val(examName);
                        	//显示上传考生信息的div
                        	document.getElementById("uploadStudent").style.display="block";
                        }else if("exist"===data.state){
                        	//考试名字重复
                        	document.getElementById("formName").innerHTML="提示";
                    		document.getElementById("formContent").innerHTML="考试名称已经存在，请重新设置考试名称或删除上一场考试";
                    		$("#form").modal('show');
                        }else if("fail"===data.state){
                        	//创建失败
                        	document.getElementById("formName").innerHTML="提示";
                    		document.getElementById("formContent").innerHTML="很遗憾，由于某种原因本次创建失败。";
                    		$("#form").modal('show');
                        }
                    }
                }
            };
        }
    </script>



</body>

</html>
