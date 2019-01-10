<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<title>添加产品</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<link href="${pageContext.request.contextPath}/resources/css/prodandtech/amazeui.cropper.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/prodandtech/H-ui.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/prodandtech/amazeui.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/prodandtech/custom_up_img.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
			  rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
			  rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/lib/editor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/lib/editor/ueditor.all.min.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/lib/editor/third-party\SyntaxHighlighter\shCore.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/lib/editor/third-party\SyntaxHighlighter\shCoreDefault.css">


	</head>

	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">

			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>自定义响应式表格</h5>

				</div>
				<div class="ibox-content">
					<div>
						<div class="row pull-left" style="width: 70%">
							<div class="col-sm-5 m-b-xs">
								<select class="input-sm form-control input-s-sm inline" id="setlist">
									<option value="0">请选择所属系列</option>
									<c:forEach items="${setprod}" var="setprod">
										<option value="${setprod.ps_id}">${setprod.ps_name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2 m-b-xs">
								<button type="button" class="btn btn-sm btn-danger" onclick="deleteset()">删除标签</button>
							</div>
							<div class="col-sm-3 m-b-xs"></div>
							<div class="col-sm-10 m-b-xs"></div>
							<div class="col-sm-10 m-b-xs"></div>
							<div class="col-sm-5 pull-left">
								<input type="text" class="input-sm form-control" id="pr_name" placeholder="请输入技术名称">
							</div>

						</div>
						<div class="row">
							<img src="../../resources/img/upimg.png"  id="up-img-touch" style="float: left;height: 110px;height:100px;margin:-10px 15px -10px -10px">
							<div style="clear:both;" > </div>
						</div>
						<div style="clear:both;"> </div>
					</div>
					<div style="clear:both;"> </div>


					<div >
						<div class="table-responsive">
							<div class="ibox-content" style="border:0px solid #f00;padding:10px 0;">
								<textarea name="editor" id="editor" style="height: 300px;width: 100%;"></textarea>

								<div class="col-sm pull-right" style="padding-top: 10px;">
									<button type="button" class="btn btn-w-m btn-success" onclick="save()">保存</button>
								</div>
								<div style="clear:both;"> </div>
							</div>

						</div>
					</div>



				</div>
			</div>

		</div>
		<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
			<div class="am-modal-dialog up-frame-parent up-frame-radius">
				<div class="am-modal-hd up-frame-header">
					<label>上传产品图像</label>
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
							<span class="am-icon-check" id="up-btn-ok" url="${pageContext.request.contextPath}/backstage/product/upload.action"></span>
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
		<input type="text" id="base_path" hidden="" value="${pageContext.request.contextPath}">
		<input type="text" id="pr_photo" hidden="" value="">

		<script id="editor" type="text/plain"></script>
		<script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/layer.js"></script>
		<script type="text/javascript">
            var ue = UE.getEditor('editor', {
                "serverUrl": "${pageContext.request.contextPath}/editor.action",
                "imageUrlPrefix": "${pageContext.request.contextPath}/image/" /* 图片访问路径前缀 */
            });
            var index;
            <c:if test="${isupdate}">
            ue.addListener('ready', function (editor) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/backstage/product/upshowprod.action',
                    data: {pr_id:${pr_id}},
                    type: 'post',
                    dataType: 'json',
                    success: function (result) {
                        if (result.pr_id !=0) {
                            ue.setContent(result.pr_html);
                            $('#setlist').val(result.pr_set);
                            $('#pr_name').val(result.pr_name);
                            $('#pr_photo').val(result.pr_photo);
                            $("#up-img-touch").attr("src",'/image/'+result.pr_photo+'?'+Math.random());
                        } else {
                            layer.msg("操作异常");
                        }
                    },
                    error: function () {
                        layer.close(index);
                        layer.msg('获取以往内容失败');
                    },

                });
            });
            </c:if>
            function deleteset() {
                index = layer.confirm('确定要删除该系列吗？',{
                    btn: ['确认', '取消'] //按钮
                },function () {
			    var set=$("#setlist option:selected");
			    var onename=$("#setlist  option:first");
			    var ts_id=set.val();
			    if(ts_id==onename.val()){
                    layer.alert('操作失败',function(){
                        window.location.href="${pageContext.request.contextPath}/backstage/product/addprod.html"
                    });

				}else{
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/backstage/product/deleteset.action",
                        data: {ts_id: ts_id},
                        dataType: "json",
                        success: function (data) {
                            if(data.flag==200) {
                                layer.alert('删除成功',function(){
                                    window.location.href="${pageContext.request.contextPath}/backstage/product/addprod.html"
                                });
                            }else{
                                layer.alert('删除失败',function(){
                                    window.location.href="${pageContext.request.contextPath}/backstage/product/addprod.html"
                                });
                            }
                        },
                        error: function () {
                            layer.msg('网络错误');
                        }


                    });

				}


            })
			}

            function save() {
                var pr_date = new Date();
                if ($('#setlist option:selected').val() !=$('#setlist option:first').val()) {
                    if (ue.getContentTxt().length > 0 && ue.getContentTxt().length < 5000 && ue.getContent().trim().length < 90000) {
                        $.ajax({
                            url: '${pageContext.request.contextPath}/backstage/product/addproduct.action',
                            data: {
                                pr_id:${pr_id},
                                pr_name:$('#pr_name').val(),
                                content:ue.getContent().trim(),
								pr_photo:$('#pr_photo').val(),
                                pr_set: $('#setlist option:selected').val(),
                                pr_date:pr_date,
                                isupdate:${isupdate}
                            },
                            type:'post',
                            dataType: 'json',
                            success: function (data) {
                                var pr_id=data.pr_id;
                                if(data.flag==200){
                                    layer.alert('添加成功',function(){
                                        window.location.href="${pageContext.request.contextPath}/backstage/product/addprod.html"
                                    });
                                }
                                if(data.flag==500){
                                    layer.alert('添加失败',function(){
                                        window.location.href="${pageContext.request.contextPath}/backstage/product/addprod.html"
                                    });
                                }
                                if(data.flag==201){
                                    layer.alert('修改成功',function(){
                                        window.location.href="${pageContext.request.contextPath}/backstage/product/updateprod.html?pr_id="+pr_id
                                    });
                                }
                                if(data.flag==501){
                                    layer.alert('修改失败',function(){
                                        window.location.href="${pageContext.request.contextPath}/backstage/technical/updateprod.html?te_id="+pr_id
                                    });
                                }

                            },
                            error: function () {
                                layer.msg('网络错误');
                            }
                        });

                    }

                    else {
                        layer.msg('字数不符合要求，请限制在0-5000个字符以内');
                    }

                } else {
                    layer.msg('请选择技术类型');
                }
            }


		</script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/prodandtech/amazeui.min.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/prodandtech/html5shiv.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/prodandtech/custom_up_img.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/prodandtech/cropper.min.js"> </script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/js/prodandtech/webuploader.js"> </script>
	</body>

</html>