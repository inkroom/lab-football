<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
   <meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

  <!--[if lt IE 9]>
<script type="text/javascript" src="${base_path }/resources/common/lib/html5shiv.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/respond.min.js"></script>
<![endif]-->
<!--  [endif]-->
<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    
	<!--上传图像-->
    <link href="${base_path}/resources/common/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/uploadimg/css/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/amazeui.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/amazeui.cropper.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/custom_up_img.css">
         <link rel="stylesheet" href="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
    <!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

    <title>修改信息</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-coach-edit" enctype="multipart/form-data" action="${base_path}/coach/edit/save.action" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">证件照片：</label>
            <div class="up-img" id="up-img-touch">
           <c:if test="${einfo.COACH_PHOTO==null}">
                <img style="width: 150px;height: 150px;"class="am-circle" alt="点击图片上传"
                 src="${base_path}/resources/common/uploadimg/img/hu.jpg"
                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
           </c:if>
              <c:if test="${einfo.COACH_PHOTO!=null}">
                 <img style="width: 150px;height: 150px;"class="am-circle" alt="点击图片上传"
                 src="${base_path}/${einfo.COACH_PHOTO}"
                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
              </c:if>   
            </div>
        </div>
        <div class="row cl" id="name_remove">
				<label class="form-label col-xs-4 col-sm-2">姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${einfo.A_NAME}" placeholder=""
						id="coachName" name="coachName">
				</div>
			</div>
			<div class="row cl">
	            <label class="form-label col-xs-4 col-sm-2">邮箱：</label>
	            <div class="formControls col-xs-3 col-sm-3">
	                <input type="text" style="width:50%" class="input-text" value="${einfo.A_EMAIL}" placeholder="" id="email" name="coachEmail">
	                <input class="btn btn-primary radius" type="button" value="免费获取验证码" id="codeBtn" onclick="sendEmail(this)">
	            </div>
	        </div>
	        <div class="row cl">
	            <label class="form-label col-xs-4 col-sm-2">邮箱验证码：</label>
	            <div class="formControls col-xs-3 col-sm-3">
	                <input style="width:100px" type="text" class="input-text" placeholder="" id="A_EMAIL_CHECK_CODE" name="A_EMAIL_CHECK_CODE">
	            </div>
	        </div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">性别：</label>
				<div class="formControls col-xs-8 col-sm-9">
				<c:if test="${einfo.COACH_SEX==''||einfo.COACH_SEX==null}">
                	<input type="radio" value="男" name="coachSex" checked="checked">男 &nbsp;&nbsp;&nbsp;
                	<input type="radio" value="女" name="coachSex">女
                </c:if>
                <c:if test="${einfo.COACH_SEX=='男'}">
                	<input type="radio" value="男" name="coachSex" checked="checked">男 &nbsp;&nbsp;&nbsp;
                	<input type="radio" value="女" name="coachSex">女
                </c:if>
                <c:if test="${einfo.COACH_SEX=='女'}">
                	<input type="radio" value="男" name="coachSex">男 &nbsp;&nbsp;&nbsp;
                	<input type="radio" value="女" name="coachSex" checked="checked">女
                </c:if>
				
					<!-- <input type="text" class="input-text" value="${einfo.COACH_SEX}" placeholder=""
						id="coachUnit" name="coachSex"> -->
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">身高：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" style="width:100px" class="input-text" value="${einfo.COACH_HEIGHT}" placeholder=""
						id="coachHeight" name="coachHeight">单位(cm)
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">体重：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" style="width:100px" class="input-text" value="${einfo.COACH_WEIGHT}" placeholder=""
						id="coachWeight" name="coachWeight">单位(kg)
				</div>
			</div>
			
			<div class="row cl ">
                <label class="form-label col-xs-4 col-sm-2">工作经历：</label>
                <div class="formControls col-xs-3 col-sm-3">
                    <textarea name="coachExp" id="coachExp"  class="textarea radius"   maxlength="100"></textarea>
                   
                </div>
            </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">精彩图片上传：</label>
            <div class="formControls col-xs-3 col-sm-3">
                <div class="uploader-list-container">
                    <div class="queueList">
                        <div id="dndArea" class="placeholder">
                            <div id="filePicker-2"></div>
                            <p>或将照片拖到这里，单次最多可选4张，文件格式:(jpg,jpeg,png,gif),大小:(0-1MB)</p>
                        </div>
                    </div>
                    <div class="statusBar" style="display:none;">
                        <div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
                        <div class="info"></div>
                        <div class="btns">
                            <div id="filePicker2"></div>
                            <div class="uploadBtn">开始上传</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>.
        

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="sub" >
            </div>
        </div>
        </form>
</article>
<!--图片上传框-->
<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
    <div class="am-modal-dialog up-frame-parent up-frame-radius">
        <div class="am-modal-hd up-frame-header">
            <label>修改头像</label>
            <a href="javascript: void(0)"  class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd  up-frame-body">
            <div class="am-g am-fl">
                <div class="am-form-group am-form-file">
                    <div class="am-fl">
                        <button type="button" class="am-btn am-btn-default am-btn-sm">
                            <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                        </button>
                        <span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;请上传图片文件:(jpg,jpeg,png,gif),大小:(0-1MB)</span>
                    </div>
                    <input type="file" id="inputImage">
                </div>
            </div>
            <div class="am-g am-fl">
                <div class="up-pre-before up-frame-radius">
                    <img alt="" src="" id="image">
                </div>
                <div class="up-pre-after up-frame-radius">
                </div>
            </div>
            <div class="am-g am-fl">
                <div class="up-control-btns">
                    <span class="am-icon-rotate-left" onclick="rotateimgleft()"></span>
                    <span class="am-icon-rotate-right" onclick="rotateimgright()"></span>
                    <span class="am-icon-check" id="up-btn-ok" url="${base_path }/coach/head_pic/upload.action"></span>
                </div>
            </div>

        </div>
    </div>
</div>

<!--加载框-->
<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">正在上传...</div>
        <div class="am-modal-bd">
            <span class="am-icon-spinner am-icon-spin"></span>
        </div>
    </div>
</div>

<!--警告框-->
<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">信息</div>
        <div class="am-modal-bd" id="alert_content">
            成功了
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" id="close">确定</span>
        </div>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
    <!--/_footer 作为公共模版分离出去-->


<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/webuploader/0.1.5/webuploader.js"></script>
<!--图像上传-->
<script src="${base_path}/resources/common/uploadimg/js/jquery-1.8.3.min.js"></script>
<script src="${base_path}/resources/common/uploadimg/js/amazeui.min.js" charset="utf-8"></script>
<script src="${base_path}/resources/common/uploadimg/js/cropper.min.js" charset="utf-8"></script>
<script src="${base_path}/resources/common/uploadimg/js/custom_up_img.js" charset="utf-8"></script>
<script src="${base_path}/resources/js/coach/coach_info.js" charset="utf-8"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
<![endif]-->

<!--[if IE 6]>
<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->

</body>
<script type="text/javascript">

var msg = "${errmsg}";
if(!(msg==null || msg=="")){
	layer.msg(msg);
}
</script>
<script type="text/javascript">
var cc="${einfo.COACH_EXP}"
var  ss= cc.replace(/<br>/g,"\r").replace(/@nbsp/g,' ')

$("#coachExp").val(ss)
</script>
<script type="text/javascript">

if(!('${_LOGIN_USER_.A_NAME}' == ''||'${_LOGIN_USER_.A_NAME}' == null)){
	
		$('input[name=coachEmail]').attr("readonly","readonly");
}
</script>

<script type="text/javascript">
if($("#coachName").val().length!=0){
	$("#name_remove").css("display","none")
}

</script>
<script type="text/javascript">
var g = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
var a=/^.{2,3}$/;
$("#coachName").blur(function(){
	var n=$("coachName").val().replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   

	$("coachName").val(n)
	if(n==""){
		layer.tips('姓名不能为空','#coachName',{ tips: [1, '#FF0033	']});
		return;
	}else if (validationCHE(n)==false){
		layer.tips('仅支持中英文和.，输入限制2到15位','#coachName',{ tips: [1, '#FF0033	']});
		return false;
	}else if (n.length<2||n.length>15){
		layer.tips('仅支持中英文和.,输入限制2到15位','#coachName',{ tips: [1, '#FF0033	']});
		return false;
	}
})
$("#coachHeight").blur(function(){
	var h =$("#coachHeight").val().replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   

	if($("#coachHeight").val()==""){
		layer.tips('身高为必填项','#coachHeight',{ tips: [1, '#FF0033	']});
		return;
	}else if(!g.test(h)){
		layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachHeight',{ tips: [1, '#FF0033	']});
    	return false;
   
	}else if(h<10||h>300)	{
		layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachHeight',{ tips: [1, '#FF0033	']});
		return false;
	}else if (h.split('.').length > 1 && h.split('.')[1].length !=1){
	     layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachHeight',{ tips: [1, '#FF0033	']});
	     return false;
	     }
})
$("#coachWeight").blur(function(){
	var w= $("#coachWeight").val().replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   

	if(w==""){
		layer.tips('体重为必填项','#coachWeight',{ tips: [1, '#FF0033	']});
		return;
	}else if(!g.test(w)){
    	layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachWeight',{ tips: [1, '#FF0033	']});
    	return;
    
	}else if(w<10||w>999)	{
		layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachWeight',{ tips: [1, '#FF0033	']});
		return;
	}else if (w.split('.').length > 1 && w.split('.')[1].length !=1){
		layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachWeight',{ tips: [1, '#FF0033	']});
	     return;
	     }
})

$("#email").blur(function(){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
	if(!reg.test($("#email").val())){
		layer.tips('请输入正确的邮箱','#email',{ tips: [1, '#FF0033	']});
    	return;
	}
})
//验证中英文和·
	function validationCHE(value) {
		var regex = /^[\u4e00-\u9fa5a-zA-Z]+[\·]{0,1}[\u4e00-\u9fa5a-zA-Z]+$/;
		if (regex.test(value) == true) {
			return true;
		} else {
			return false;
		}
	}

$("#sub").click(function(){

	var w= $("#coachWeight").val().replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   

	var h= $("#coachHeight").val().replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   

	var n=$("#coachName").val().replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   
	var ss =document.getElementById("coachExp").value.replace(/\n/g, '<br>').replace(/\r/g, '@nbsp')
	
	 $("#coachExp").val(ss);

	if(n==""){
		layer.tips('姓名为必填项','#coachName',{ tips: [1, '#FF0033	']});
		$("#coachName").focus();
		return false;
	}else if (validationCHE(n)==false){
		layer.tips('仅支持中英文和.，输入限制2到15位','#coachName',{ tips: [1, '#FF0033	']});
		$("#coachName").focus();
		return false;
	}else if (n.length<2||n.length>15){
		layer.tips('仅支持中英文和.,输入限制2到15位','#coachName',{ tips: [1, '#FF0033	']});
		$("#coachName").focus();
		return false;
	}
	
		if(h==""){
			layer.tips('身高为必填项','#coachHeight',{ tips: [1, '#FF0033	']});
			return false;
		}else if(!g.test(h)){
			layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachHeight',{ tips: [1, '#FF0033	']});
        	return false;
        
		}else if(h<10||h>300)	{
			layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachHeight',{ tips: [1, '#FF0033	']});
			return false;
		}else if (h.split('.').length > 1 && h.split('.')[1].length !=1){
		     layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachHeight',{ tips: [1, '#FF0033	']});
		     return false;
		     }
		if(w==""){
			layer.tips('体重为必填项','#coachWeight',{ tips: [1, '#FF0033	']});
			return false;
		}else if(!g.test(w)){
			layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachWeight',{ tips: [1, '#FF0033	']});
        	return false;
        
		}else if(w<10||w>300)	{
			layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachWeight',{ tips: [1, '#FF0033	']});
			return false;
		}else if (w.split('.').length > 1 && w.split('.')[1].length !=1){
		     layer.tips('请在正确范围内(10-300)填写,仅保留一位小数','#coachWeight',{ tips: [1, '#FF0033	']});
		     return false;
		     }
			
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if(!reg.test($("#email").val())){
			layer.tips('请输入正确的邮箱','#email',{ tips: [1, '#FF0033	']});
			$("#email").focus();
	    	return false;
		}
		
		
		}

	
	)

</script>

<script type="text/javascript">
$("#close").click(function(){
	window.location.reload();
	
})

</script>
<script>
// 默认
$('#date_a').cxCalendar({
    type: 'datetime',
    format: 'YYYY-MM-DD '
});
</script>
<script type="text/javascript">
var msg = "${msg}";
var path="${base_path}";
var counts = 0;
if(msg!=""){
	layer.msg(msg);
	setInterval("count()",1000);
}
function count(){
	counts++;
	if(counts==1){
		window.parent.location.reload();
	}
}
$(function(){
    function article_save(){
        alert("刷新父级的时候会自动关闭弹层。")
        window.parent.location.reload();
    }
    $(function(){
    	layer.ready(function(){
    		if('${info}' != '' || '${info}' == null){
    			layer.alert('${info}',function(){
    				window.parent.location.reload();
    			});
    		}
        });
    });
}); 
//计时器
var wait=120;  
function time(o) {  
    if (wait == 0) {  
        o.removeAttribute("disabled");            
        o.value="免费获取验证码";  
        wait =120;  
    } else {  
        o.setAttribute("disabled", true);  
        o.value="重新发送(" + wait + ")";  
        wait--;  
        setTimeout(function() {  
            time(o)  
        },  
        1000)  
    }  
}
//发送验证码
function sendEmail(o){
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	if(!reg.test($("#email").val())){
		layer.tips('请输入正确的邮箱','#email',{ tips: [1, '#FF0033	']});
    	return;
	}
	$.ajax({
		type: "post",
		url: "${base_path}/email/getCode.action",
		data: {email:$("#email").val()},
		dataType: 'json',
		success:function(json) {
			if (json.success){
				layer.msg(json.msg)
    			time(o);
				
    		}else{
    			layer.msg(json.msg)
    			o.value="发送失败，请稍后再试";
    		}
		},
    	error:function(error) {
    		console.log(error);
  		}
		
	});
}	
$("#A_EMAIL_CHECK_CODE").blur(function () {

    $.ajax({
        url:"${base_path}/email/getValidateCode.action",
        type : "POST",
        data:{
            "email" : $("#email").val(),
            "randomCode" : $("#A_EMAIL_CHECK_CODE").val()
        },
        dataType: "JSON",

        success : function (json) {
            if (json.success){
            	layer.msg(json.msg)
                state = true;
            }else {
            	layer.msg(json.msg)
                state = false;
            }

        },
        error : function () {
            alert("服务器错误！")
        }
    })
})

</script>
</html>