<%--
  Created by IntelliJ IDEA.
  User: inkbox
  Date: 18-2-23
  Time: 下午9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>测试富文本编辑器</title>
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/lib/editor/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/lib/editor/ueditor.all.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/lib/editor/third-party\SyntaxHighlighter\shCore.js"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/lib/editor/third-party\SyntaxHighlighter\shCoreDefault.css">
</head>
<body>
<jsp:forward page="/home.html"/>
<script id="editor" type="text/plain" ></script>
<button onclick="show()">获取内容</button>
<script type="text/javascript">
    var ue = UE.getEditor('editor',{
        "serverUrl":"${pageContext.request.contextPath}/editor.action",
        "imageActionName": "editor", /* 执行上传图片的action名称 */
        "imageFieldName": "file", /* 提交的图片表单名称 */
        "imageMaxSize": 2048000, /* 上传大小限制，单位B */
        "imageAllowFiles": [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */
        "imageCompressEnable": true, /* 是否压缩图片,默认是true */
        "imageCompressBorder": 1600, /* 图片压缩最长边限制 */
        "imageInsertAlign": "none", /* 插入的图片浮动方式 */
        "imageUrlPrefix": "/gate/image/" /* 图片访问路径前缀 */
    });
    // alert(ue.opt('imageActionName'));
  // ue.setOpt('imageActionName','editor');
</script>


</body>
</html>
