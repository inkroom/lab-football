	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>上传题库</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="middle-box text-center animated fadeInRightBig">
                    <h3 class="font-bold">欢迎使用在线考试系统-教师端</h3><br/>
                    
                    <div class="error-desc">
                    <form action="teacherAddQuestionLib" method="post" enctype="multipart/form-data">
                    <input type="text" name="questionLibName" placeholder="请输入题库名" class="form-control" required="required" /><br/>
                  <!--  只显示excel文件 -->
                    <input type="file" name="file" class="form-control" required="required" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/><br/>
                   <!--  <a href="javascript:void(0)" onclick="submit()" class="btn btn-primary btn-rounded btn-block btn btn-w-m btn-success" >上传题库资料</a> -->
                    <input type = "submit", class="btn btn-primary btn-rounded btn-block btn btn-w-m btn-success"/> 上传题库资料
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0">
    function submit(){
    	console.log("1");
    	var request = new XMLHttpRequest();
    	/* var questionLibName=$('input[name="questionLibName"]').attr("value");
    	
    	request.open("POST","teacherAddQuestionLib?questionLibName="+questionLibName,true);
    	request.send(); */
    	request.onreadystatechange = function(){
    		if(request.readyState===4){
    			if(request.status===200){
    				var data = JSON.parse(request.responseText);
    				if("success"===data.state){
    					alert("添加成功！");
    				}
    				if("fail"===data.state){
    					alert("添加失败！");
    				}
    				if("error"===data.state){
    					alert("题库重名！")
    				}
    			}
    		}
    	
    }
    
	}
    
    </script>
    
    
</body>
</html>