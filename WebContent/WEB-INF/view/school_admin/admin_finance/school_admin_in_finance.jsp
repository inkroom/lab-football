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
    </head>
<body>
	<div class="jumbotron" align="center">
    <h2>经费公式表</h2>
</div>
<div align="center" class="dd">
	    <form id="in_form" action="${basePath }/school_admin/update_in_found" method="post">
			<input type="hidden" name="token" value="${token }">
	    <table class="table table-bordered table-hover" >
	    	<tr>
	            <th  class="title_2" colspan="2">标题</th>
	        </tr>
	        
	        <tr>
	            <td  class="td_1" colspan="2"><input type="text" name="inFoundTitle" class="input-text form-control" value="${found.title_in }"></td>
	        </tr>
	
	        <tr>
	            <th  class="title_2" colspan="2">收入(万元)</th>
	        </tr>
	        <tr>
	            <th  class="title_3" colspan="2">财政资金支持</th>
	        </tr>
	        <tr>
	            <td class="td_1">省级资金金额</td>
	            <td class="td_2"><input type="number" id="province_fund" name="provinceFund" class="input-text form-control" value="${found.content1 }" /></td>
	        </tr>
	        <tr>
	            <td class="td_1">市级资金金额</td>
	            <td class="td_2"><input type="number" id="city_fund" name="cityFund" class="input-text form-control" value="${found.content2 }" /></td>
	        </tr>
	        <tr>
	            <td class="td_1">县级资金金额</td>
	            <td class="td_2"><input type="number" id="county_fund" name="countyFund" class="input-text form-control" value="${found.content3 }" /></td>
	        </tr> 
	
	
	        <tr>
	            <th  class="title_3" colspan="2">学校自有资金</th>
	        </tr>
	        <tr>
	            <td class="td_1">净余资金金额</td>
	            <td class="td_2"><input type="number" id="self_fund" name="selfFund" class="input-text form-control" value="${found.content4 }" /></td>
	        <tr>
	            <td class="td_1">生均费用</td>
	            <td class="td_2"><input type="number" id="ave_self_fund" name="aveSelfFund" class="input-text form-control" value="${found.content5 }" /></td>
	        </tr>
	
	        <tr>
	            <th  class="title_3" colspan="2">接受社会捐助</th>
	        </tr>
	        <tr>
	            <td class="td_1">捐助金额</td>
	            <td class="td_2"><input type="number" id="aid_fund" name="aidFund" class="input-text form-control" value="${found.content6 }" /></td>
	        </tr>
	        <tr>
	            <th  class="title_2" colspan="2">总计(万元)</th>
	        </tr>
	        <tr>
	            <td class="td_1">收入总计</th>
	            <td class="td_2"><input type="number" id="in_count" name="in_count" disabled="disabled" class="input-text form-control" value="${found.content7 }" /></td>
	        </tr>
			
			<tr>
				<td class="td_1" colspan="2">
	            	<input style="width:100px;" id="commit" class="btn btn-success radius" type="button" value="保&nbsp;&nbsp;&nbsp;&nbsp;存">
	            </td>
			</tr>
	
	    </table>
    </form>
    
 </div>
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


	$("#province_fund").blur(function(){
		var checkValue = $("#province_fund").val();
		if(validateNum(checkValue)){
			var province_fund = parseFloat($("#province_fund").val());
			var city_fund = parseFloat($("#city_fund").val());
			var county_fund = parseFloat($("#county_fund").val());
			var self_fund = parseFloat($("#self_fund").val());
			
			var aid_fund = parseFloat($("#aid_fund").val());
			var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
			console.log(count);
			$("#in_count").val(count);
		}else{
			layer.alert("省级资金金额数据有误，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#city_fund").blur(function(){
		var checkValue = $("#city_fund").val();
		if(validateNum(checkValue)){
			var province_fund = parseFloat($("#province_fund").val());
			var city_fund = parseFloat($("#city_fund").val());
			var county_fund = parseFloat($("#county_fund").val());
			var self_fund = parseFloat($("#self_fund").val());
			
			var aid_fund = parseFloat($("#aid_fund").val());
			var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
			console.log(count);
			$("#in_count").val(count);
		}else{
			layer.alert("省级资金金额数据有误，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	
	$("#county_fund").blur(function(){
		var checkValue = $("#county_fund").val();
		if(validateNum(checkValue)){
			var province_fund = parseFloat($("#province_fund").val());
			var city_fund = parseFloat($("#city_fund").val());
			var county_fund = parseFloat($("#county_fund").val());
			var self_fund = parseFloat($("#self_fund").val());
			
			var aid_fund = parseFloat($("#aid_fund").val());
			var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
			console.log(count);
			$("#in_count").val(count);
		}else{
			layer.alert("省级资金金额数据有误，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#self_fund").blur(function(){
		var checkValue = $("#self_fund").val();
		if(validateNum(checkValue)){
			var province_fund = parseFloat($("#province_fund").val());
			var city_fund = parseFloat($("#city_fund").val());
			var county_fund = parseFloat($("#county_fund").val());
			var self_fund = parseFloat($("#self_fund").val());
			
			var aid_fund = parseFloat($("#aid_fund").val());
			var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
			console.log(count);
			$("#in_count").val(count);
		}else{
			layer.alert("省级资金金额数据有误，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#ave_self_fund").blur(function(){
		var checkValue = $("#ave_self_fund").val();
		if(validateNum(checkValue)){
			var province_fund = parseFloat($("#province_fund").val());
			var city_fund = parseFloat($("#city_fund").val());
			var county_fund = parseFloat($("#county_fund").val());
			var self_fund = parseFloat($("#self_fund").val());
			var aid_fund = parseFloat($("#aid_fund").val());
			var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
			console.log(count);
			$("#in_count").val(count);
		}else{
			layer.alert("省级资金金额数据有误，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#aid_fund").blur(function(){
		var checkValue = $("#aid_fund").val();
		if(validateNum(checkValue)){
			var province_fund = parseFloat($("#province_fund").val());
			var city_fund = parseFloat($("#city_fund").val());
			var county_fund = parseFloat($("#county_fund").val());
			var self_fund = parseFloat($("#self_fund").val());
			
			var aid_fund = parseFloat($("#aid_fund").val());
			var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
			console.log(count);
			$("#in_count").val(count);
		}else{
			layer.alert("省级资金金额数据有误，请输入0-10000", {
				skin: 'layer-bule-style'
			    ,closeBtn: 0
		  	});
		}
	})
	
	
	$("#commit").click(function(){

		var province_fund = parseFloat($("#province_fund").val());
		var city_fund = parseFloat($("#city_fund").val());
		var county_fund = parseFloat($("#county_fund").val());
		var self_fund = parseFloat($("#self_fund").val());
		var ave_self_fund = parseFloat($("#ave_self_fund").val());
		var aid_fund = parseFloat($("#aid_fund").val());
		
		if(!validateNum(province_fund)){
			return false;
		}
		if(!validateNum(city_fund)){
			return false;
		}
		if(!validateNum(county_fund)){
			return false;
		}
		if(!validateNum(self_fund)){
			return false;
		}
		if(!validateNum(ave_self_fund)){
			return false;
		}
		if(!validateNum(aid_fund)){
			return false;
		}
		
		var count = province_fund + city_fund + county_fund + self_fund + aid_fund;
		$("#in_count").val(count);
		
		$("#in_form").submit();
		
		

	})
	
	
</script>
</html>