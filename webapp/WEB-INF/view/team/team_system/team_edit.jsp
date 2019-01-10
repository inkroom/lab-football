<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui/css/H-ui.ie.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui.admin/skin/green/skin.css" />
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/static/h-ui.admin/css/style.css" />
    <link href="${base_path}/resources/common/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
	<!--上传图像-->
    <link rel="stylesheet" type="text/css" href="${base_path}/resources/common/uploadimg/css/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/amazeui.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/amazeui.cropper.css">
    <link rel="stylesheet" href="${base_path}/resources/common/uploadimg/css/custom_up_img.css">
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>修改信息</title>
</head>
<body>
<article class="page-container">
    <div class="form form-horizontal">
        <div class="row cl">
        	<input type="hidden" id="badgeisUpload" value="${teamBasicInfo.teamLogo}"/>
            <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>队徽：</label>
            <div class="up-img" id="up-img-touch">
                <img onclick="chioceImg(1)" style="width: 150px;height: 150px;" class="am-circle " onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'" alt="点击图片上传" src="${base_path}/${teamBasicInfo.teamLogo}"
                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
            </div>
        </div>
         <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>队旗：</label>
            <div class="up-img" id="up-img-touch-flag">
                <img onclick="chioceImg(2)" style="width: 150px;height: 150px;" class="am-circle err-product" onerror="javascript:this.src='${base_path}/resources/common/uploadimg/img/hu.jpg'" alt="点击图片上传" src="${base_path}/${teamBasicInfo.teamFlag}"
                     data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
            </div>
        </div>
        
        <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>队名：</label>
                        <div class="formControls col-xs-3 col-sm-3">
                            <input type="text" class="input-text" value="${teamBasicInfo.teamName}" placeholder="" id="teamName" name="teamName">
                        </div>
                    </div>
                     <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>邮箱：</label>
                        <div class="formControls col-xs-4 col-sm-4">
                            <input type="text" style="width:42%" class="input-text" readonly="readonly" disabled value="${teamBasicInfo.email}" id="email" name="email" >
                            <c:choose>
	                            <c:when test="${needEmail == 1}">
	                            	<input class="btn btn-primary radius" type="hidden" value="" id="sendEmail">
	                            </c:when>
	                            <c:otherwise>
	                            	<input class="btn btn-primary radius" type="button" value="免费获取验证码" id="sendEmail">
	                            </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
             <c:if test="${needEmail != 1}">
                    <div class="row cl">
            			<label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>邮箱验证码：</label>
            			<div class="formControls col-xs-2 col-sm-2">
                			<input type="text" class="input-text" placeholder="" maxlength="6" id="code" name="code">
            			</div>
        			</div>
             </c:if>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>球队类型：</label>
                        <div class="formControls col-sm-1" style="width: auto">
                            <select class="select select-box" size="1" id="teamType" name="teamType">
                            		<c:choose>
				                		<c:when test="${teamBasicInfo.teamType == 1}">
				                			<option value="1" selected>小学</option>
											<option value="2" >初中</option>
											<option value="3">高中</option>
											<option value="4">混合</option>
				                		</c:when>
				                		<c:when test="${teamBasicInfo.teamType == 2}">
				                			<option value="1">小学</option>
											<option value="2" selected>初中</option>
											<option value="3">高中</option>
											<option value="4">混合</option>
				                		</c:when>
				                		<c:when test="${teamBasicInfo.teamType == 3}">
				                			<option value="1">小学</option>
											<option value="2" >初中</option>
											<option value="3" selected>高中</option>
											<option value="4">混合</option>
				                		</c:when>
				                		<c:otherwise>
											<option value="1">小学</option>
											<option value="2">初中</option>
											<option value="3">高中</option>
											<option value="4" selected>混合</option>
										</c:otherwise>
			                		</c:choose>
							</select>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>球队等级：</label>
                        <div class="formControls  col-sm-1" style="width: auto">
                            <select class="select select-box" size="1" id="teamRank" name="teamRank">
									<c:choose>
				                		<c:when test="${teamBasicInfo.teamRank == 1}">
				                			<option value="1" selected>省级</option>
											<option value="2">市级</option>
											<option value="3">县级</option>
											<option value="4">校级</option>
											<option value="5">无</option>
				                		</c:when>
				                		<c:when test="${teamBasicInfo.teamRank == 2}">
				                			<option value="1">省级</option>
											<option value="2" selected>市级</option>
											<option value="3">县级</option>
											<option value="4">校级</option>
											<option value="5">无</option>
				                		</c:when>
				                		<c:when test="${teamBasicInfo.teamRank == 3}">
				                			<option value="1">省级</option>
											<option value="2">市级</option>
											<option value="3" selected>县级</option>
											<option value="4">校级</option>
											<option value="5">无</option>
				                		</c:when>
				                		<c:when test="${teamBasicInfo.teamRank == 4}">
				                			<option value="1">省级</option>
											<option value="2">市级</option>
											<option value="3">县级</option>
											<option value="4" selected>校级</option>
											<option value="5">无</option>
				                		</c:when>
				                		<c:otherwise>
											<option value="1">省级</option>
											<option value="2">市级</option>
											<option value="3">县级</option>
											<option value="4">校级</option>
											<option value="5" selected>无</option>
										</c:otherwise>
			                		</c:choose>
							</select>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>领队名称：</label>
                        <div class="formControls col-xs-2 col-sm-2">
                            <input type="text" class="input-text" value="${teamBasicInfo.teamLeader}" placeholder="" id="teamLeader" name="teamLeader">
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>领队手机号：</label>
                        <div class="formControls col-xs-2 col-sm-2">
                            <input type="text" class="input-text" maxlength="11" value="${teamBasicInfo.teamLeaderPhone}" placeholder="" id="teamLeaderPhone" name="teamLeaderPhone">
                        </div>
                    </div>

                    <div class="row cl">
                        <label class="form-label col-xs-4 col-sm-3"><c:if test="${empty teamBasicInfo.teamLogo}"><span style="color:#F00">*</span></c:if>励志标语：</label>
                        <div class="formControls col-xs-3 col-sm-3">
                            <input type="text" class="input-text" maxlength="100" value="${teamBasicInfo.teamInspirationalSlogan}" placeholder="" id="teamInspirationalSlogan" name="teamInspirationalSlogan">
                        </div>
                    </div>
        
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-3 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" id="subid" >
            </div>
        </div>
        </div>
</article>
<input type="text" id="base_path" hidden="" value="${base_path}">
<input type="text" id="needEmai" hidden="" value="${needEmail}">
<!--图片上传框-->
<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
	<input type="hidden" id="imgType" name="imgType" value=""/>
    <div class="am-modal-dialog up-frame-parent up-frame-radius">
        <div class="am-modal-hd up-frame-header">
            <label>修改</label>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd  up-frame-body">
            <div class="am-g am-fl">
                <div class="am-form-group am-form-file">
                    <div class="am-fl">
                        <button type="button" class="am-btn am-btn-default am-btn-sm">
                            <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                        </button>
                    </div>
                    <input type="file" id="inputImage">
                </div>
            </div>
            <div class="am-g am-fl">
                <div class="up-pre-before up-frame-radius">
                    <img alt="" src="" id="image" >
                </div>
                <div class="up-pre-after up-frame-radius">
                </div>
            </div>
            <div class="am-g am-fl">
                <div class="up-control-btns">
                    <span class="am-icon-rotate-left" onclick="rotateimgleft()"></span>
                    <span class="am-icon-rotate-right" onclick="rotateimgright()"></span>
                    <span class="am-icon-check" id="up-btn-ok" url="${base_path}/team/upload_photo.action"></span>
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
            <span class="am-modal-btn">确定</span>
        </div>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${base_path}/resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${base_path}/resources/common/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/webuploader/0.1.5/webuploader.min.js"></script>
<!-- 验证js -->
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/lib/jQuery-Validation-Engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${base_path}/resources/js/team/page/team_edit.js"></script>
<script type="text/javascript">

	$(function(){
		
	    function article_save(){
	        alert("刷新父级的时候会自动关闭弹层。")
	        window.parent.location.reload();
	    }
	});
	
	//设置上传的是哪个图片
	function chioceImg(type){
		if(type==1){
			//队徽
			$('#imgType').val("teamLogo");
		}else{
			//队旗
			$('#imgType').val("teamFlag");
		}
	}
	
	</script>
</body>
<!--图像上传-->
<script src="${base_path}/resources/common/uploadimg/js/jquery-1.8.3.min.js"></script>
<script src="${base_path}/resources/common/uploadimg/js/amazeui.min.js" charset="utf-8"></script>
<script src="${base_path}/resources/common/uploadimg/js/cropper.min.js" charset="utf-8"></script>
<script src="${base_path}/resources/js/team/page/custom_up_img.js" charset="utf-8"></script>
<%-- <script src="${base_path}/resources/js/team/page/team_info.js" charset="utf-8"></script> --%>

</html>