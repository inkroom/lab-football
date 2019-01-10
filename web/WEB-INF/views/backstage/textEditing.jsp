<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    HashMap<String, String> map = new HashMap<>();
    //党建工会
    map.put("1_0", "党群工会");
    map.put("1_1", "党建");
    map.put("1_2", "工会");

//开源数据
    map.put("2_0", "开源数据");
    map.put("2_1", "光学");
    map.put("2_2", "电子学");
    map.put("2_3", "机械");

    //招贤纳士
    map.put("3_0", "招贤纳士");
    map.put("3_1", "硬件");
    map.put("3_2", "软件");
    map.put("3_3", "工程");
    map.put("3_4", "其他");
    //走近大江东
    map.put("4_0", "走近大江东");
    map.put("4_1", "人才引进政策");
    map.put("4_2", "区域规划");

    //关于我们
    map.put("5_0", "关于我们");
    map.put("5_1", "本部介绍");
    map.put("5_2", "研究院介绍");
    map.put("5_3", "组织架构");

    request.setAttribute("map", map);
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">


    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>

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

<body>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <c:set var="key1" value="${param.get('owner')}_0"/>
                    <c:set var="key2" value="${param.get('owner')}_${param.get('type')}"/>
                    <h5>${map.get(key1)}-${map.get(key2)}</h5>
                </div>

                <div class="ibox-content">
                    <textarea name="editor" id="editor" style="height: 400px;"></textarea>

                    <div class="col-sm pull-right" style="padding-top: 10px;">
                        <button type="button" class="btn btn-w-m btn-success" id="save">保存</button>
                    </div>
                    <div style="clear:both;"></div>
                </div>

            </div>
        </div>
    </div>
</div>

<%--<script id="editor" type="text/plain"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        var ue = UE.getEditor('editor', {
            "serverUrl": "${pageContext.request.contextPath}/editor.action"
        });
        var index;

        $('#save').on('click', function () {
            var content = ue.getContentTxt();
            if (content.length > 10000 || ue.getContent().length > 500000) {
                layer.alert('最多输入10000个字符');
                return;
            }
            layer.confirm('保存后内容将显示在前台，确认保存当前内容？', {
                btn: ['确认', '取消'] //按钮
            }, function () {
                $.ajax({
                    url: '${pageContext.request.contextPath}/html/set.action',
                    type: 'post',
                    dataType: 'json',
                    data: {
                        action: 'get',
                        owner:<c:out value="${param.get('owner')}" default="-1"/>,
                        type:<c:out value="${param.get('type')}" default="-1"/>,
                        content: ue.getContent().trim(),
                        title: '',
                        url: '${param.get('url')}'
                    },
                    success: function (result) {
                        layer.close(index);
                        if (result.status === 0) {
                            // ue.setContent(result.data.html);
                            layer.msg('保存成功');
                        } else {
                            layer.msg(result.message);
                        }
                    },
                    error: function () {
                        layer.close(index);
                        layer.alert('网络错误');
                    },
                    beforeSend: function () {
                        index = layer.load(1);
                    }
                });
            }, function () {
                // layer.msg('也可以这样', {
                //     time: 20000, //20s后自动关闭
                //     btn: ['明白了', '知道了']
                // });
            });

        });

        ue.addListener('ready', function (editor) {
            $.ajax({
                url: '${pageContext.request.contextPath}/html/get.action',
                type: 'get',
                dataType: 'json',
                data: {
                    action: 'get',
                    owner:<c:out value="${param.get('owner')}" default="-1"/>,
                    type:<c:out value="${param.get('type')}" default="-1"/>
                },
                success: function (result) {
                    layer.close(index);
                    if (result.status === 0) {
                        ue.setContent(result.data.html);
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


    })

</script>


</body>

</html>