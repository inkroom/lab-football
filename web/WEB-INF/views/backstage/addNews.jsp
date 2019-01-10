<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新闻中心-${empty(param.get('id'))?'添加':'修改'}新闻</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">

    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/lib/editor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/lib/editor/ueditor.all.min.js">
    </script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/lib/editor/third-party/SyntaxHighlighter/shCore.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/lib/editor/third-party/SyntaxHighlighter/shCoreDefault.css">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>新闻中心-${empty(param.get('id'))?'添加':'修改'}新闻</h5>

        </div>
        <div class="ibox-content">
            <div class="row">
                <div class="col-sm-5 m-b-xs">
                    <select class="input-sm form-control input-s-sm inline" id="type" style="padding: 3px 0">
                        <option value="0">请选择新闻类型</option>
                        <option value="1">综合新闻</option>
                        <option value="2">科研动态</option>
                        <option value="3">人才招聘</option>
                    </select>
                </div>
                <%--<div class="col-sm-4 m-b-xs">--%>
                <%--<button type="button" class="btn btn-sm btn-danger">删除标签</button>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                <%--<div class="input-group">--%>
                <%--<input type="text" placeholder="请输入标签名称" class="input-sm form-control"> <span class="input-group-btn">--%>
                <%--<button type="button" class="btn btn-sm btn-primary"> 添加标签</button> </span>--%>
                <%--</div>--%>
                <%--</div>--%>

            </div>
            <div class="row">
                <div class="col-sm-10" style="width: 100%;">
                    <input type="text" class="input-sm form-control" placeholder="请输入新闻标题" id="title">
                </div>
            </div>

            <div class="table-responsive">
                <div class="ibox-content" style="border:0px;padding:10px 0;">
                    <textarea name="editor" id="editor" style="height: 300px;width: 100%;"></textarea>

                    <div class="col-sm pull-right" style="padding-top: 10px;">
                        <button type="button" class="btn btn-w-m btn-success" onclick="save()">保存</button>
                    </div>
                    <div style="clear:both;"></div>
                </div>

            </div>

        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/layer.js"></script>
<script type="text/javascript">
    var ue = UE.getEditor('editor', {
        "serverUrl": "${pageContext.request.contextPath}/editor.action",
        "imageUrlPrefix": "${pageContext.request.contextPath}/image/" /* 图片访问路径前缀 */
    });
    var index;
    <c:if test="${ !empty(param.get('id'))}">
    ue.addListener('ready', function (editor) {
        $.ajax({
            url: '${pageContext.request.contextPath}/news/get.action',
            type: 'get',
            dataType: 'json',
            data: {
                id: <c:out value="${param.get('id')}" default="-1"/>
            },
            success: function (result) {
                layer.close(index);
                if (result.status === 0) {
                    ue.setContent(result.data.news.content);
                    $('#title').val(result.data.news.title);
                    $('#type').val(result.data.news.type);
                } else {
                    layer.msg(result.message);
                }
            },
            error: function () {
                layer.close(index);
                layer.msg('获取以往内容失败');
            },
            beforeSend: function () {
                index = layer.load(1);
            }
        });
    });
    </c:if>

    function save() {
        if ($('#type').val() !== '0') {
            if($('#title').val().length>0){
                if (ue.getContentTxt().length > 0 && ue.getContentTxt().length < 5000 && ue.getContent().trim().length < 90000) {
                    index = layer.confirm('保存成功后将会自动发布，确认保存该新闻？', {
                        btn: ['确认', '取消'] //按钮
                    }, function () {
                        layer.close(index);
                        $.ajax({
                            url: '${pageContext.request.contextPath}/news/set.action',
                            data: {
                                id:<c:out value="${param.get('id')}" default="-1"/>,
                                content: ue.getContent().trim(),
                                title: $('#title').val(),
                                type: $('#type').val()
                            },
                            type:'post',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status === 0) {
                                    layer.msg('${empty(param.get('id'))?'添加':'修改'}成功');
                                } else if (result.status === 1) {
                                    layer.msg('${empty(param.get('id'))?'添加':'修改'}失败');
                                } else if (result.status === 5) {
                                    layer.msg('系统异常，请联系管理人员');
                                } else if (result.status === 6) {
                                    layer.msg(result.message);
                                } else {
                                    layer.msg(result.message);
                                }
                            },
                            error: function () {
                                layer.msg('网络错误');
                            }
                        })

                    }, function () {
                    });

                } else {
                    layer.msg('字数不符合要求，请限制在0-5000个字符以内');
                }

            }else{
                layer.msg('新闻标题不能为空');
            }

        } else {
            layer.msg('请选择新闻类型');
        }
    }
</script>
</body>

</html>