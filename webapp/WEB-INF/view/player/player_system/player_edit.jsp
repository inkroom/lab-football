<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.reset.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />

	<!--[if lt IE 9]>
	<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="${base_path }/resources/common/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->

    <!--上传图像-->
    <link href="${base_path}/resources/common/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/uploadimg/css/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/amazeui.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/amazeui.cropper.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/custom_up_img.css">
   
   	<link rel="stylesheet" href="${base_path}/resources/js/player/icheck/icheck.css">
    <title>修改信息</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-coach-edit" enctype="multipart/form-data" action="${base_path}/player/system/update_info.action" method="post">
    	<input type="text" value="${player_info.P_PEROSONAL_PHOTO}" style="display:none" id="photo_img">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">证件照片：</label>
            <div class="up-img" >
            	<c:if test="${player_info.P_PEROSONAL_PHOTO != null}">
                	<img style="width: 150px;height: 150px;" id="up-img-touch" class="am-circle" alt="点击图片更改" src="${base_path}/${player_info.P_PEROSONAL_PHOTO}"
                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
                </c:if>
                <c:if test="${player_info.P_PEROSONAL_PHOTO == null}">
                	<img style="width: 150px;height: 150px;" id="up-img-touch" class="am-circle" alt="点击图片上传" src="${base_path}/resources/common/uploadimg/img/hu.jpg"
                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
                </c:if>
            </div>
        </div>
        <c:if test="${stu_info eq null && player_info.A_NAME eq null}">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">姓名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${player_info.A_NAME}" placeholder="" id="A_NAME" name="A_NAME" maxlength="13">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">性别：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <c:if test="${player_info.P_SEX eq null}">
                	<input type="radio" value="男" name="P_SEX" checked="checked">男 &nbsp;&nbsp;&nbsp;
                	<input type="radio" value="女" name="P_SEX">女
                </c:if>
                <c:if test="${player_info.P_SEX eq '男'}">
                	<input type="radio" value="男" name="P_SEX" checked="checked">男 &nbsp;&nbsp;&nbsp;
                	<input type="radio" value="女" name="P_SEX">女
                </c:if>
                <c:if test="${player_info.P_SEX eq '女'}">
                	<input type="radio" value="男" name="P_SEX">男 &nbsp;&nbsp;&nbsp;
                	<input type="radio" value="女" name="P_SEX" checked="checked">女
                </c:if>
            </div>
        </div>
        </c:if>
        <c:if test="${stu_info ne null}">
        	<input type="text" id="STU_ID" name="STU_ID" value="${stu_info.STU_ID}" style="display: none;" readonly>
        	<input type="text" id="A_NAME" name="A_NAME" value="${stu_info.STU_NAME}" style="display: none;" readonly>
        	<input type="text" id="P_SEX" name="P_SEX" value="${stu_info.STU_SEX}" style="display: none;" readonly>
        </c:if>
       <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">邮箱：</label>
            <div class="formControls col-xs-3 col-sm-3">
        		<c:if test="${player_info.A_EMAIL eq null}">
                	<input type="text" style="width:50%" class="input-text" value="" placeholder="" id="email" name="A_EMAIL">
        		</c:if>
        		<c:if test="${player_info.A_EMAIL ne null}">
        			<input type="text" style="width:50%" class="input-text" value="${player_info.A_EMAIL}" placeholder="" id="email" name="A_EMAIL" readonly>
        		</c:if>
                <input class="btn btn-primary radius" type="button" value="免费获取验证码" id="codeBtn" onclick="sendEmail(this)">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">邮箱验证码：</label>
            <div class="formControls col-xs-3 col-sm-3">
                <input type="text" class="input-text" placeholder="" id="code" name="A_EMAIL_CHECK_CODE">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">身高：</label>
            <div class="formControls col-xs-2 col-sm-2">
                <input type="text" style="width:25%" class="input-text" value="${player_info.P_PLAYER_HEIGHT}" placeholder="" id="P_PLAYER_HEIGHT" name="P_PLAYER_HEIGHT">单位(cm)
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">体重：</label>
            <div class="formControls col-xs-2 col-sm-2">
               	<input type="text" style="width:25%" class="input-text" value="${player_info.P_PLAYER_WEIGHT}" placeholder="" id="P_PLAYER_WEIGHT" name="P_PLAYER_WEIGHT">单位(kg)
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">所在学校：</label>
            <div class="formControls col-xs-3 col-sm-3">
               	<input type="text" class="input-text" value="${player_info.P_SCHOOL}" placeholder="" id="P_SCHOOL" name="P_SCHOOL" maxlength="50">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">擅长位置：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<%--  <input type="text" class="input-text" value="${player_info.P_POSITION}" placeholder="" id="P_POSITION" name="P_POSITION" maxlength="50"> --%>
					<!-- <select id="P_POSITION" name="P_POSITION" class="selectpicker show-tick form-control" multiple data-live-search="false">
                	<option value="0">苹果</option>
                    <option value="1">菠萝</option>
                    <option value="2">香蕉</option>
                </select> -->
					<div class="skin-minimal" id="P_POSITION">
						<div class="check-box">
							<input type="checkbox" id="checkbox-1" name="P_POSITION" value="门将"
							<c:if test="${fn:contains(player_info.positionInfo, '门将')}">checked</c:if>>门将
						</div>
						<div class="check-box">
							<input type="checkbox" id="checkbox-2" name="P_POSITION" value="后卫"
							<c:if test="${fn:contains(player_info.positionInfo, '后卫')}">checked</c:if>>后卫
						</div>
						<div class="check-box">
							<input type="checkbox" id="checkbox-2" name="P_POSITION" value="中锋"
							<c:if test="${fn:contains(player_info.positionInfo, '中锋')}">checked</c:if>>中锋
						</div>
						<div class="check-box">
							<input type="checkbox" id="checkbox-2" name="P_POSITION" value="中卫"
							<c:if test="${fn:contains(player_info.positionInfo, '中卫')}">checked</c:if>>中卫
						</div>
						<div class="check-box">
							<input type="checkbox" id="checkbox-2" name="P_POSITION" value="前锋"
							<c:if test="${fn:contains(player_info.positionInfo, '前锋')}">checked</c:if>>前锋
						</div>
					</div>
				</div>
			
            <div class="check-box">
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
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="sub" onclick="active()">
            </div>
        </div>
        </form>
</article>
<!--图片上传框-->
<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
    <div class="am-modal-dialog up-frame-parent up-frame-radius">
        <div class="am-modal-hd up-frame-header">
            <label>修改头像</label>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
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
                    <span class="am-icon-check" id="up-btn-ok" url="${base_path}/player/system/upload_photo.action"></span>
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
<input type="text" id="base_path" hidden="" value="${base_path}">
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
<script src="${base_path}/resources/js/player/page/custom_up_img.js" charset="utf-8"></script>
<script src="${base_path}/resources/js/player/page/player_info.js" charset="utf-8"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>

<script type="text/javascript" src="${base_path}/resources/js/player/icheck/jquery.icheck.min.js"></script>
</body>
<script type="text/javascript">

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	})});

$("#close").click(function(){
	window.location.reload();
	
});

//验证体重
function validation(value){
	var regex1 = /^[0-9]{2,3}([.][0-9]{1})?$/;
	var regex2 = /^[1-9]\d*([.][0-9]{1})?$/;
	if(regex1.test(value) == true && regex2.test(value) == true){
		if(value - 300 <= 0 && value - 30 >= 0){
			return true;
		}else{
			return false;
		}
	}else{
		return false;
	}
}
	//验证中文
	function validationCH(value) {
		var regex = /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
		if (regex.test(value)) {
			return true;
		} else {
			return false;
		}
	}
	//验证身高
	function valHeight(value) {
		var regex1 = /^[0-9]{2,3}([.][0-9]{1})?$/;
		var regex2 = /^[1-9]\d*([.][0-9]{1})?$/;
		if (regex1.test(value) == true && regex2.test(value) == true) {
			if (value - 300 <= 0 && value - 90 >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	//验证中英文和·
	function validationCHE(value) {
		var regex1 = /^[\u4e00-\u9fa5]+[\·]{0,1}[\u4e00-\u9fa5]+$/;
        var regex2 = /^[a-zA-Z]+[\·]{0,1}[a-zA-Z]+$/;
		if (regex1.test(value) == true || regex2.test(value) == true) {
			return true;
		} else {
			return false;
		}
	}
	//邮箱验证
	function valiEmail(value) {
		var regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		var regex2 = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
		if (regex2.test(value) == true) {
			return true;
		} else {
			return false;
		}
	}
    //去掉首尾空格
    function trimStr(str){
        return str.replace(/(^\s*)|(\s*$)/g,"");
    }

	$("#A_NAME").blur(function() {
		if ($("#A_NAME").length > 0) {
            var value = trimStr($("#A_NAME").val());
			if (value.length == 0) {
				layer.tips('姓名不能为空', '#A_NAME', {
					tips : [ 1, '#FF0033	' ]
				});
			} else if (value.length<2 || value.length>13) {
				layer.tips('姓名应在2-13字符之间', '#A_NAME', {
					tips : [ 1, '#FF0033	' ]
				});
			} else if (validationCHE(value) == false) {
				layer.tips('仅支持中文或英文', '#A_NAME', {
					tips : [ 1, '#FF0033	' ]
				});
			}
		}
	});
	$("#email").blur(function() {
		var value = trimStr($("#email").val());
		if (value.length == 0) {
			layer.tips('邮箱不能为空', '#email', {
				tips : [ 1, '#FF0033	' ]
			});
		} else if (!valiEmail(value)) {
			layer.tips("邮箱格式错误", '#email', {
				tips : [ 1, '#FF0033' ]
			});
		}

	});
	$("#code").blur(function() {
		var value = trimStr($("#code").val());
		if (value.length == 0) {
			layer.tips('邮箱验证码不能为空', '#code', {
				tips : [ 1, '#FF0033	' ]
			});
		}
	});
	$("#P_PLAYER_HEIGHT").blur(function() {
		var value = trimStr($("#P_PLAYER_HEIGHT").val());
		if (value.length == 0) {
			layer.tips('身高不能为空', '#P_PLAYER_HEIGHT', {
				tips : [ 1, '#FF0033	' ]
			});
		} else if (!valHeight(value)) {
			layer.tips("请在正确范围内(90-300)填写,仅保留一位小数", '#P_PLAYER_HEIGHT', {
				tips : [ 1, '#FF0033' ]
			});
		}
	});
	$("#P_PLAYER_WEIGHT").blur(function() {
		var value = trimStr($("#P_PLAYER_WEIGHT").val());
		if (value.length == 0) {
			layer.tips('体重不能为空', '#P_PLAYER_WEIGHT', {
				tips : [ 1, '#FF0033	' ]
			});
		} else if (!validation(value)) {
			layer.tips("请在正确范围内(30-300)填写,仅保留一位小数", '#P_PLAYER_WEIGHT', {
				tips : [ 1, '#FF0033' ]
			});
		}
	});
	$("#P_SCHOOL").blur(function() {
		var value = trimStr($("#P_SCHOOL").val());
		if (value.length == 0) {
			layer.tips('所在学校不能为空', '#P_SCHOOL', {
				tips : [ 1, '#FF0033	' ]
			});
		} else if (value.length < 2) {
			layer.tips("最少两个字符", '#P_SCHOOL', {
				tips : [ 1, '#FF0033' ]
			});
		} else if (!validationCH(value)) {
			layer.tips("仅支持中文", '#P_SCHOOL', {
				tips : [ 1, '#FF0033' ]
			});
		}
	});
	//计时器
	var wait = 120;
	function time(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.value = "免费获取验证码";
			wait = 120;
		} else {
			o.setAttribute("disabled", true);
			o.value = "重新发送(" + wait + ")";
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000)
		}
	}
	//发送验证码
	function sendEmail(o) {
		var value = $("#photo_img").val();
		if (value.length == 0) {
			layer.alert("请先完善头像信息");
			return;
		}
		var value = $("#email").val();
		if (value.length == 0) {
			layer.tips('邮箱不能为空', '#email', {
				tips : [ 1, '#FF0033	' ]
			});
			$("#email").focus();
			return;
		} else if (!valiEmail(value)) {
			layer.tips("邮箱格式错误", '#email', {
				tips : [ 1, '#FF0033' ]
			});
			$("#email").focus();
			return;
		}
		time(o);
		$.ajax({
            url: "${base_path}/email/getCode.action",
            type: "POST",
            data:{
                "email" : $("#email").val()
            },
            dataType : "JSON",
            success : function (json) {
                if (json.success){
                    layer.msg(json.msg, {
                        icon : 1
                    });
                }else {
                    layer.msg(json.msg, {
                        icon : 0
                    });
                    wait = 0;
                }
            },
            error : function () {
                layer.msg("服务器错误！", {
                    icon : 2
                });
                wait = 0;
            }

		});
	}

	function active() {
		var value = $("#photo_img").val();
		if (value.length == 0) {
			layer.alert("请先完善头像信息");
			return;
		}
		if ($("#A_NAME").length > 0) {
			var value = trimStr($("#A_NAME").val());
			if (value.length == 0) {
				layer.tips('姓名不能为空', '#A_NAME', {
					tips : [ 1, '#FF0033	' ]
				});
				$("#A_NAME").focus();
				return;
			} else if (value.length<2 || value.length>13) {
				layer.tips('姓名应在2-13字符之间', '#A_NAME', {
					tips : [ 1, '#FF0033	' ]
				});
				$("#A_NAME").focus();
				return;
			} else if (validationCHE(value) == false) {
				layer.tips('仅支持中文或英文', '#A_NAME', {
					tips : [ 1, '#FF0033	' ]
				});
				$("#A_NAME").focus();
				return;
			}
		}
		var value = trimStr($("#email").val());
		if (value.length == 0) {
			layer.tips('邮箱不能为空', '#email', {
				tips : [ 1, '#FF0033	' ]
			});
			$("#email").focus();
			return;
		} else if (!valiEmail(value)) {
			layer.tips("邮箱格式错误", '#email', {
				tips : [ 1, '#FF0033' ]
			});
			$("#email").focus();
			return;
		}
		var value = trimStr($("#code").val());
		if (value.length == 0) {
			
			layer.tips('邮箱验证码不能为空', '#code', {
				tips : [ 1, '#FF0033	' ]
			});
			$("#code").focus();
			return;
		}
        var value = trimStr($("#P_PLAYER_HEIGHT").val());
		if (value.length == 0) {
			layer.tips('身高不能为空', '#P_PLAYER_HEIGHT', {
				tips : [ 1, '#FF0033	' ]
			});
			$("#P_PLAYER_HEIGHT").focus();
			return;
		} else if (!valHeight(value)) {
			layer.tips("请在正确范围内(90-300)填写,仅保留一位小数", '#P_PLAYER_HEIGHT', {
				tips : [ 1, '#FF0033' ]
			});
			$("#P_PLAYER_HEIGHT").focus();
			return;
		}
		var value = trimStr($("#P_PLAYER_WEIGHT").val());
		if (value.length == 0) {
			layer.tips('体重不能为空', '#P_PLAYER_WEIGHT', {
				tips : [ 1, '#FF0033	' ]
			});
			$("#P_PLAYER_WEIGHT").focus();
			return;
		} else if (!validation(value)) {
			layer.tips("请在正确范围内(30-300)填写,仅保留一位小数", '#P_PLAYER_WEIGHT', {
				tips : [ 1, '#FF0033' ]
			});
			$("#P_PLAYER_WEIGHT").focus();
			return;
		}
        var value = trimStr($("#P_SCHOOL").val());
		if (value.length == 0) {
			layer.tips('所在学校不能为空', '#P_SCHOOL', {
				tips : [ 1, '#FF0033	' ]
			});
			$("#P_SCHOOL").focus();
			return;
		} else if (value.length < 2) {
			layer.tips("最少两个字符", '#P_SCHOOL', {
				tips : [ 1, '#FF0033' ]
			});
			$("#P_SCHOOL").focus();
			return;
		} else if (!validationCH(value)) {
			layer.tips("仅支持中文", '#P_SCHOOL', {
				tips : [ 1, '#FF0033' ]
			});
			$("#P_SCHOOL").focus();
			return;
		}
		//擅长位置验证
		var flag = false;
		var oSelectOnes = document.getElementsByTagName("input");
		for (var i = 0; i < oSelectOnes.length; i++) {
			if (oSelectOnes[i].name=="P_POSITION") {
				if(oSelectOnes[i].checked ==true){
					flag=true;		
				}
			}
		}
		if(!flag) {
			layer.tips("至少选择一个擅长位置", '#P_POSITION', {
				tips : [ 1, '#FF0033' ]});
			return;
		}

		$.ajax({
			type : "post",
			url : "${base_path}/player/system/update_info.action",
			data : $("#form-coach-edit").serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.info != null) {
					layer.alert(data.info);
					setTimeout(function() {
						window.parent.location.reload();
					}, 1350);
				} else if (data.msg != null) {
					layer.tips(data.msg, '#code', {
						tips : [ 1, '#FF0033' ]
					});
					$("#code").focus();
				} else if (data.error != null) {
					layer.msg(data.error, {
						icon : 2
					});
				}
			},
			error : function(error) {
				console.log(error);
			}
		});

	}
	$(function() {
		layer.ready(function() {
			if ('${info}' != '' || '${info}' == null) {
				layer.alert('${info}');
				setTimeout(function() {
					window.parent.location.reload();
				}, 1350);
			<% session.removeAttribute("info"); %>
    		}
			
        });
    });
</script>
</html>