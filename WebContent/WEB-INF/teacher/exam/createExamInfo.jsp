<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>创建考试</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<!--选择题库div id=choseQuestionLib-->
<div id="choseQuestionLib" class="ibox float-e-margins">
    <div class="ibox-title">
        <h5>请选择一个题库进行创建考试</h5>
        <div class="ibox-tools">
            <a class="collapse-link">
                <i class="fa fa-chevron-up"></i>
            </a>
            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                <i class="fa fa-wrench"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="table_basic.html#">选项1</a>
                </li>
                <li><a href="table_basic.html#">选项2</a>
                </li>
            </ul>
            <a class="close-link">
                <i class="fa fa-times"></i>
            </a>
        </div>
    </div>
    <div class="ibox-content">

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>题库名称</th>
                    <th>单选题总数</th>
                    <th>多选题总数</th>
                    <th>判断题总数</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <%ArrayList<QuestionLibListEntity> list = (ArrayList<QuestionLibListEntity>)request.getAttribute("questionLibList");
            	for(int i = 0; i < list.size();i++){
            		 QuestionLibListEntity questionLibListEntity = list.get(i);
            %>
                <tr>
                    <td><%=i+1 %></td>
                    <td><%=questionLibListEntity.getLibraryName() %></td>
                    <td><%=questionLibListEntity.getChoiceNumber() %></td>
                    <td><%=questionLibListEntity.getMultiputeChoiceNumber()%></td>
                    <td><%=questionLibListEntity.getJudgeNumber() %></td>
                    <td class="text-navy"><a href="javascript:void(0);" onclick="createExam(<%=questionLibListEntity.getLibraryId()%>)">用此题库创建考试</a></td>
                </tr>
                <%} %>
            </tbody>
        </table>
    </div>
</div>
<!-- 创建考试DIV id=createExam -->
<div class="col-md-12">
    <div class="form-group">
        <label class="col-sm-3 control-label">当前选择的题库是：</label>
        <div class="col-sm-9">
            <p class="form-control-static">这里是纯文字信息</p>
        </div>
    </div>
    <br/><br/>
    <div class="form-group">
        <label class="col-sm-3 control-label">设置试卷中单选题个数：</label>
        <div class="col-sm-9">
            <select class="form-control" name=""></select>
        </div>
    </div>
    <br/><br/>
    <div class="form-group">
        <label class="col-sm-3 control-label">设置试卷中单个单选题分数：</label>
        <div class="col-sm-9">
            <select class="form-control" name=""></select>
        </div>
    </div>
    <br/><br/>
    <div class="form-group">
        <label class="col-sm-3 control-label">设置试卷中多选题个数：</label>
        <div class="col-sm-9">
            <select class="form-control" name=""></select>
        </div>
    </div>
    <br/><br/>
    <div class="form-group">
        <label class="col-sm-3 control-label">设置试卷中单个多选题选题分数：</label>
        <div class="col-sm-9">
            <select class="form-control" name=""></select>
        </div>
    </div>
    <br/><br/>
    <div class="form-group ui-sortable-helper">
        <label class="col-sm-3 control-label">设置试卷中判断题个数：</label>
        <div class="col-sm-9">
            <select class="form-control" name=""></select>
        </div>
    </div>
    <br/><br/>
    <div class="form-group">
        <label class="col-sm-3 control-label">设置试卷中单个判断题分数：</label>
        <div class="col-sm-9">
            <select class="form-control" name=""></select>
        </div>
    </div>
</div>
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>



    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>


    <script>
    //创建考试
    function createExam(id){
    	console.log(id);
    	//隐藏选择题库的div
    	document.getElementById("choseQuestionLib").style.display="none";
    }
    </script>
</body>

</html>