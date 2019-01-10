<%--
  User: 墨盒
  Date: 2017/9/17
  Time: 22:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
测试按钮
<input value="点一下，这时后台会抛出异常，并且直接被异常处理器处理" type="button">
<input value="再点一下，这个一切正常，注意查看后台返回的数据" type="button">

<%--引入公共的部分--%>
<jsp:include page="common/foot.jsp" flush="false"/>
<%--这两个js文件基本都需要引用--%>
<script type="text/javascript" src="${path}/resources/common/jquery.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/index.js"></script>
<script type="text/javascript">
    $(function () {
        $('input:eq(0)').on('click', function () {
            ajax({
                url: 'test',
                data: {
                    data: 'error'
                },
                success: function (result) {
                    alert("访问result.data.key ，对于的value = " + result.data.key);
                },
                error: function () {
                    alert("尽管error，但是不一定会执行")
                }
            });
        })
        $('input:eq(1)').on('click', function () {
            ajax({
                url: 'test',
                data: {
                    data: '1'
                },
                success: function (result) {
                    alert("访问result.data.key ，对于的value = " + result.data.key);
                },
                error: function () {
                    alert("尽管error，但是不一定会执行")
                }
            });
        })
    })
</script>
</body>
</html>
