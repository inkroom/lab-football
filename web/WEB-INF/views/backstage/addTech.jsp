<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<title>添加技术</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
			  rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
			  rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
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
                                    <c:forEach items="${settech}" var="settech">
                                        <option value="${settech.ts_id}">${settech.ts_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-1 m-b-xs">
                                <button type="button" class="btn btn-sm btn-danger" onclick="deleteset()">删除标签</button>
                            </div>
                            <div class="col-sm-3 m-b-xs"></div>
                            <div class="col-sm-10 m-b-xs"></div>
                            <div class="col-sm-10 m-b-xs"></div>
                            <div class="col-sm-5 pull-left">
                                <input type="text" class="input-sm form-control" placeholder="请输入技术名称" id="te_name">
                            </div>

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
		<script id="editor" type="text/plain"></script>
		<script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
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
                            url: '${pageContext.request.contextPath}/backstage/technical/upshowtech.action',
                            data: {te_id:${te_id}},
                            type: 'post',
                            dataType: 'json',
                            success: function (result) {
								if (result.te_id !=0) {
                                ue.setContent(result.te_html);
                                $('#setlist').val(result.te_set);
                                $('#te_name').val(result.te_name);
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

			function addset() {
                var  setname = $("#addset").val();
                if(setname.length!=0){
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/backstage/technical/addset.action",
                        data: {setname: setname},
                        dataType: "json",
                        success: function (data) {
                            if(data.flag==200) {
                                layer.alert('添加成功',function(){
                                    window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
                                });
                            }else{
                                layer.alert('添加失败',function(){

                                    window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
                                });
							}
                        },
                        error: function () {
                            layer.msg('网络错误');
                        }


                    });
				}
            }

            function deleteset() {
                index = layer.confirm('确定要删除该系列吗？',{
                    btn: ['确认', '取消'] //按钮
                },function () {
			    var set=$("#setlist option:selected");
                var ts_id=set.val();
                var onename=$("#setlist  option:first");
                if(ts_id==onename.val()){
                    layer.alert('操作失败',function(){
                        window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
                    });
				}else{
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/backstage/technical/deleteset.action",
                        data: {ts_id: ts_id},
                        dataType: "json",
                        success: function (data) {
                            if(data.flag==200) {
                                layer.alert('删除成功',function(){
                                    window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
                                });
                            }else{
                                layer.alert('删除失败',function(){
                                    window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
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
               var te_date = new Date();
                if ($('#setlist option:selected').val() !=$('#setlist option:first').val()) {

                    if (ue.getContentTxt().length > 0 && ue.getContentTxt().length < 5000 && ue.getContent().trim().length < 90000) {
                            $.ajax({
                                url: '${pageContext.request.contextPath}/backstage/technical/addtechnical.action',
                                data: {
                                    te_id:${te_id},
                                    te_name:$('#te_name').val(),
                                    content:ue.getContent().trim(),
                                    te_set: $('#setlist option:selected').val(),
                                    te_date:te_date
                                },
                                type:'post',
                                dataType: 'json',
                                success: function (data) {
                                    var te_id=data.te_id;
									if(data.flag==200){
                                        layer.alert('添加成功',function(){
                                            window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
                                        });
                                    }
                                    if(data.flag==500){
                                        layer.alert('添加失败',function(){
                                            window.location.href="${pageContext.request.contextPath}/backstage/technical/addtech.html"
                                        });
                                    }
                                    if(data.flag==201){
                                        layer.alert('修改成功',function(){
                                            window.location.href="${pageContext.request.contextPath}/backstage/technical/updatetech.html?te_id="+te_id
                                        });
                                    }
                                    if(data.flag==501){
                                        layer.alert('修改失败',function(){
                                            window.location.href="${pageContext.request.contextPath}/backstage/technical/updatetech.html?te_id="+te_id
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
	</body>

</html>