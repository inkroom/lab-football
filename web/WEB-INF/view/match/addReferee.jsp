<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"/>
<body>
<jsp:include page="../common/head.jsp"/>
<input hidden id="token" value="${token}">
<input hidden id="sid" value="${idSchedule}">
<div class="container mo-h">
    ${title}
</div>
<div class="container">
    <form id="createRefereeForm" class="form-horizontal">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
                <input type="text" id="username" value="${refereeUser}" name="username" class="form-control"
                       placeholder="请填写用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" id="password" name="password" class="form-control" placeholder="请填写密码">
            </div>
        </div>
        <div class="form-group">
            <label for="confirmPass" class="col-sm-2 control-label">确认密码</label>
            <div class="col-sm-10">
                <input type="password" id="confirmPass" class="form-control" placeholder="请确认密码">
            </div>
        </div>
        <%--<div class="form-group">--%>
        <%--<label for="phone" class="col-sm-2 control-label">电话</label>--%>
        <%--<div class="col-sm-10">--%>
        <%--<input type="text" id="phone" name="phone" class="form-control" placeholder="请填写手机号">--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
        <%--<label for="name" class="col-sm-2 control-label">姓名</label>--%>
        <%--<div class="col-sm-10">--%>
        <%--<input type="text" id="name" name="name" class="form-control" placeholder="请填写姓名">--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
        <%--<label for="idcard" class="col-sm-2 control-label">身份证账号</label>--%>
        <%--<div class="col-sm-10">--%>
        <%--<input type="text" id="idcard" name="idcard" class="form-control" placeholder="请填写用户名">--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="back" type="button" class="btn btn-danger">取消</button>
                <input type="button" onclick="createReferee();return false;"
                       class="btn btn-success" value="确认"/>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    var token;
    var idSch;

    function initData() {

    }
    function createReferee() {
        if (!checkuser() || !checkpass()) {
            return false;
        }
        token = $("#token").html().trim();
        idSch = $("#sid").val().trim();
        // var postData = new FormData(document.getElementById("createRefereeForm"));
        // postData.append("idSch", idSch);
        ajax({
            type: "post",//方法类型
            url: "${path}/schedule/createReferee",
            data: {
                idSch: idSch,
                username: $("#username").val(),
                password: $("#password").val()
            },
            headers: {token: token},
            // contentType: false,
            // processData: false,
            success: function (result) {
                if (result.status === 0) {
                    layer.alert("${title}" + "成功", function (index) {
                        closeLyaer();
                    });
                } else if (result.message) {
                    layer.alert(result.message, {icon: 2}, function (index) {
                        closeLyaer();
                    });
                }
            },
            error: function () {
                layer.alert("${title}" + "出错", function (index) {
                    closeLyaer();
                });
            }
        });
    }


    checkuser = function () {
        var username = document.getElementById('username').value;
        //是否为空
        if (username === '') {
            layer.alert('请输入用户名，用户名不能为空');
            document.getElementById('username').focus();
            return false;
        }
        //校验长度，类型
        else if (username.length < 2) {
            layer.alert('最小为2位');
            document.getElementById('idcard').focus();
            return false;
        }
        else if (username.length > 12) {
            layer.alert('最大为12位');
            document.getElementById('idcard').focus();
            return false;
        }
        return true;
    };
    checkpass = function () {
        var password = document.getElementById('password').value;
        var confirm = document.getElementById('confirmPass').value;

        //是否为空
        if (password === '') {
            layer.alert('请输入密码，密码不能为空');
            document.getElementById('password').focus();
            return false;
        }
        //校验长度，类型
        else if (password.length < 2) {
            layer.alert('最小为2位');
            document.getElementById('password').focus();
            return false;
        }
        else if (password.length > 20) {
            layer.alert('最大为20位');
            document.getElementById('password').focus();
            return false;
        } else if (password !== confirm) {
            layer.alert('两次密码不同');
            document.getElementById('password').focus();
            return false;
        }
        return true;
    };

    $("#back").click(closeLyaer);
</script>
</html>


