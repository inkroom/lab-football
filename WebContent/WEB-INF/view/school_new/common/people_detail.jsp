<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="${basePath }/resources_new/css/third.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="teacher">

   <div class="col-xs-12" >
      	
        <div class="box">
                <img src="${picPath}${peopleMap.pic }">
                <h3>${peopleMap._name }</h3>
                <div class="fliter1"> </div>
                <div class="fliter2"> </div>
        </div>
        
        <section class="introduces">
              ${peopleMap.content }
        </section>
	</div>
</div>
</body>
</html>