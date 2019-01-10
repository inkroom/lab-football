<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
		<div align="center">
			<h1>ajax 测试${base_path }</h1>
			<input type="text" name="username" id="username">	<br>
			<input type="password" name="username" id="username">  <br>
			<input type="button" name="button" id="button" value="提交到后台"> <br>
		</div>
	</body>
	<script type="text/javascript" src="${base_path }/resource/js/jquery-2.1.4.js"></script>
	<script type="text/javascript">
		$(document).ready(
			$('#button').click(function(){
				alert("1");
				$.ajax({
	                type: "post",   //访问WebService使用Post方式请求
	                /* contentType: "application/json",  */
	                url: "${base_path}/test/json.action", //调用WebService的地址和方法名称组合 ---- WsURL/方法名
	                data: {name:'value1',passw:'value2'},  //这里是要传递的参数，格式为 data: "{paraName:paraValue}",下面将会看到       
	                dataType: 'json',   //WebService 会返回Json类型
	                success:function(result) {     //回调函数，result，返回值
	                    alert(result);
	                },
	                error:function(error) {
	    				console.log(error);
	    			}
	            });
	            
	            
				/* $.post("${base_path}/test/json.action",{name:'123'},function(result){
					alert(result);
				},"json"); */
	            
			})
		)
	</script>
	
</html>