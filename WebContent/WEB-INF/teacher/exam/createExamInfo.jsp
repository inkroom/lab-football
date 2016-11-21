<%@page import="cn.nsu.ccl.teacher.entity.QuestionLibListEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建考试</title>
</head>
<body>
<%ArrayList<QuestionLibListEntity> list = (ArrayList<QuestionLibListEntity>)request.getAttribute("questionLibList");
for(int i = 0; i < list.size();i++){
	QuestionLibListEntity questionLibListEntity = list.get(i);
	System.out.println(questionLibListEntity.getLibraryName());
}
%>
</body>
</html>