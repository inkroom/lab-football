<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>艺术课程</title>
	<jsp:include page="../common/include.jsp"></jsp:include>
<style>
        .dd{
            padding-left: 15%;
            padding-right: 15%;
        }
        .table td{
            text-align: center;
        }
        .title_2{
            background-color: #8D8D8D;
            text-align: center;
            font-size: large;
        }
        .title_3{
            text-align: center;
            color: #149bdf;
            font-size: medium;
        }
        .td_1{
            width: 65%;
        }
        .td_2{
            width: 35%;
            text-align: center;
        }
    </style>
<body>
	<form id="out_form" action="${basePath }/school_admin/update_out_found" method="post">
		<input type="hidden" name="token" value="${token }">
		<div class="jumbotron" align="center">
		    <h2>经费公式表</h2>
		</div>
		<div align="center" class="dd">
		    <table class="table table-bordered table-hover" >
				<tr>
		            <th  class="title_2" colspan="2">标题</th>
		        </tr>
		        
		        <tr>
		            <td  class="td_1" colspan="2"><input type="text" name="outFoundTitle" class="input-text form-control" value="${found.title_out }"></td>
		        </tr>
		        
		        <tr>
		            <th  class="title_2" colspan="2">支出项(万元)</th>
		        </tr>
		        <tr>
		            <th  class="title_3" colspan="2">用于聘请国内外该水平教师/教练员教学演讲费用</th>
		        </tr>
		        <tr>
		            <td class="td_1">用于研制特色学校建设标准及管理办法研发费用</td>
		           <td class="td_2"><input type="number" class="input-text form-control" name="content8" id="content8" value="${found.content8 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于编写师资系列培训教材费用</td>
		           <td class="td_2"><input type="number" class="input-text form-control" name="content9" id="content9" value="${found.content9 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于开展校园足球改革交流活动费用</td>
		           <td class="td_2"><input type="number" class="input-text form-control" name="content10" id="content10" value="${found.content10 }"></td>
		        </tr>
		
		
		        <tr>
		            <th  class="title_3" colspan="2">校园足球特色学校和试点区建设支出</th>
		        </tr>
		        <tr>
		            <td class="td_1">用于特色学校添置运动器材及设施费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content11" id="content11" value="${found.content11 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于建立校园足球队费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content12" id="content12" value="${found.content12 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于购置运动装备费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content13" id="content13" value="${found.content13 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于聘请足球教练员费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content14" id="content14" value="${found.content14 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于参加各级比赛费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content15" id="content15" value="${found.content15 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于购买参赛球员意外伤害险费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content16" id="content16" value="${found.content16 }"></td>
		        </tr>
		
		
		        <tr>
		            <th class="title_3" colspan="2">各级校园足球联赛支出</th>
		        </tr>
		        <tr>
		            <td class="td_1">用于校园足球冬/夏令营费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content17" id="content17" value="${found.content17 }"></td>
		        </tr>
		
		        <tr>
		            <th class="title_3" colspan="2">校园足球师资队伍，管理队伍建设支出</th>
		        </tr>
		        <tr>
		            <td class="td_1">用于聘请国内外该水平教师/教练员教学演讲费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content18" id="content18" value="${found.content18 }"></td>
		        </tr>
		
		
		        <tr>
		            <th class="title_3" colspan="2">用于校园足球宣传与文化建设和课题研究</th>
		        </tr>
		        <tr>
		            <td class="td_1">用于校园足球科普和知识宣传费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content19" id="content19" value="${found.content19 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于校园足球文化建设及氛围营造费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content20" id="content20" value="${found.content20 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于专家指导团队和联盟建设费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content21" id="content21" value="${found.content21 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">用于校园足球相关课题研究费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content22" id="content22" value="${found.content22 }"></td>
		        </tr>
		
		
		        <tr>
		            <th class="title_3" colspan="2">经验交流与集中调研</th>
		        </tr>
		        <tr>
		            <td class="td_1">组织开展校园足球工作经验交流和调研费用</td>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content23" id="content23" value="${found.content23 }"></td>
		        </tr>
		        <tr>
		            <td class="td_1">支出总计</th>
		          <td class="td_2"><input type="number" class="input-text form-control" name="content24" id="content24" value="${found.content24 }"></td>
	
	        	<td class="td_1" colspan="2">
	            	<input style="width:100px;" id="commit" class="btn btn-success radius" type="button" value="保&nbsp;&nbsp;&nbsp;&nbsp;存">
	            </td>
		    </table>
		 </div>
	 </form>
</body>
<script type="text/javascript">

	$(function(){
		if('${errorInfo}' != null && '${errorInfo}' != ""){
			layer.alert("${errorInfo}", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})


	function validateNum(val){
		var patten = /^-?\d+\.?\d{0,3}$/;
		if(patten.test(val)){
			if(val <= 10000 && val >=0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}  

	$("#content8").blur(function(){
		var checkValue = $("#content8").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于研制特色学校建设标准及管理办法研发费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content9").blur(function(){
		var checkValue = $("#content9").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于编写师资系列培训教材费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content10").blur(function(){
		var checkValue = $("#content10").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于开展校园足球改革交流活动费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	
	$("#content11").blur(function(){
		var checkValue = $("#content11").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于特色学校添置运动器材及设施费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	

	
	
	$("#content12").blur(function(){
		var checkValue = $("#content12").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于建立校园足球队费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	$("#content13").blur(function(){
		var checkValue = $("#content13").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于购置运动装备费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	
	$("#content14").blur(function(){
		var checkValue = $("#content14").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于聘请足球教练员费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content15").blur(function(){
		var checkValue = $("#content15").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于参加各级比赛费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content16").blur(function(){
		var checkValue = $("#content16").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于购买参赛球员意外伤害险费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content17").blur(function(){
		var checkValue = $("#content17").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于校园足球冬/夏令营费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content18").blur(function(){
		var checkValue = $("#content18").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于聘请国内外该水平教师/教练员教学演讲费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content19").blur(function(){
		var checkValue = $("#content19").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于校园足球科普和知识宣传费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content20").blur(function(){
		var checkValue = $("#content20").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于校园足球文化建设及氛围营造费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content21").blur(function(){
		var checkValue = $("#content21").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于专家指导团队和联盟建设费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content22").blur(function(){
		var checkValue = $("#content22").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("用于校园足球相关课题研究费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#content23").blur(function(){
		var checkValue = $("#content23").val();
		if(validateNum(checkValue)){
			var content8 = parseFloat($("#content8").val());
			var content9 = parseFloat($("#content9").val());
			var content10 = parseFloat($("#content10").val());
			var content11 = parseFloat($("#content11").val());
			var content12 = parseFloat($("#content12").val());
			var content13 = parseFloat($("#content13").val());
			var content14 = parseFloat($("#content14").val());
			var content15 = parseFloat($("#content15").val());
			var content16 = parseFloat($("#content16").val());
			var content17 = parseFloat($("#content17").val());
			var content18 = parseFloat($("#content18").val());
			var content19 = parseFloat($("#content19").val());
			var content20 = parseFloat($("#content20").val());
			var content21 = parseFloat($("#content21").val());
			var content22 = parseFloat($("#content22").val());
			var content23 = parseFloat($("#content23").val());
			var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;
			console.log(count);
			$("#content24").val(count);
		}else{
			layer.alert("组织开展校园足球工作经验交流和调研费用，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#commit").click(function(){

		var content8 = parseFloat($("#content8").val());
		var content9 = parseFloat($("#content9").val());
		var content10 = parseFloat($("#content10").val());
		var content11 = parseFloat($("#content11").val());
		var content12 = parseFloat($("#content12").val());
		var content13 = parseFloat($("#content13").val());
		var content14 = parseFloat($("#content14").val());
		var content15 = parseFloat($("#content15").val());
		var content16 = parseFloat($("#content16").val());
		var content17 = parseFloat($("#content17").val());
		var content18 = parseFloat($("#content18").val());
		var content19 = parseFloat($("#content19").val());
		var content20 = parseFloat($("#content20").val());
		var content21 = parseFloat($("#content21").val());
		var content22 = parseFloat($("#content22").val());
		var content23 = parseFloat($("#content23").val());
		
		if(!validateNum(content8)){
			return false;
		}
		if(!validateNum(content9)){
			return false;
		}
		if(!validateNum(content10)){
			return false;
		}
		if(!validateNum(content11)){
			return false;
		}
		if(!validateNum(content12)){
			return false;
		}
		if(!validateNum(content13)){
			return false;
		}
		if(!validateNum(content14)){
			return false;
		}
		if(!validateNum(content15)){
			return false;
		}
		if(!validateNum(content16)){
			return false;
		}
		if(!validateNum(content17)){
			return false;
		}
		if(!validateNum(content18)){
			return false;
		}
		if(!validateNum(content19)){
			return false;
		}
		if(!validateNum(content20)){
			return false;
		}
		if(!validateNum(content21)){
			return false;
		}
		if(!validateNum(content22)){
			return false;
		}
		if(!validateNum(content23)){
			return false;
		}
		
		var count = content8 + content9 + content10 + content11 + content12 + content13 + content13 + content15 + content16 + content17 + content18 + content19 + content20 + content21 + content22 + content23;

		$("#content24").val(count);
		
		$("#out_form").submit();
		
		

	})
	
</script>
</html>