var base_path = $('#base_path').val();
var needEmail = $('#needEmai').val();
var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，1秒执行
var curCount;//当前剩余秒数

//发送验证码
$('#sendEmail').click(function () {
    var email = $('#email').val();
    var regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (email != null && email.length > 0) {
        if (email.length > 100 || regex.test(email) == false) {
            return false;
        }
    } else {
        return false;
    }
    curCount = count;
    //设置button效果，开始计时
    $("#sendEmail").attr("disabled", "true");

    //向后台发送处理数据
    $.ajax({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: base_path + "/email/getCode.action",
        type: "POST",
        dataType: "json",
        data: {"email": email},
        success: function (data) {
            if (data.success) {
                $("#sendEmail").val(curCount + "秒后重新获取");
                InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                layer.msg(data.msg, {icon: 1});
            } else {
                $("#sendEmail").removeAttr("disabled");
                $("#sendEmail").val("重新获取");
                layer.msg(data.msg, {icon: 0});
            }
        }
    });
});
//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#sendEmail").removeAttr("disabled");//启用按钮
        $("#sendEmail").val("重新发送验证码");
    }
    else {
        curCount--;
        $("#sendEmail").val(curCount + "秒后重新获取");
    }
}

//验证中英数字
function validationCNZNNumber(value, len) {
    var regex = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
    if (value != null && value.length > 0 && value.length <= len && regex.test(value) == true) {
        return true;
    } else {
        return false;
    }
}

//验证中英数字、逗号、句号、空格且最大长度为60
function validation(value) {
    var regex1 = /^[\u4E00-\u9FA5A-Za-z0-9\\,\\。\\，\\!\\！\\、\\.\s]+$/;//匹配中英数字、逗号、句号和空格
    if (value != null && value.length > 0 && value.length < 61 && regex1.test(value) == true) {
        return true;
    } else {
        return false;
    }
}
//验证中文和点
function validationCHS(value) {
    var regex = /^[\u4e00-\u9fa5a-zA-Z]+[\·]{0,1}[\u4e00-\u9fa5a-zA-Z]*$/;
    if (value == null || value.length == 0 || value.length > 20 || regex.test(value) == false) {
        return false;
    } else {
        return true;
    }
}

function validationEmail(value) {
    var regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (value == null || value.length == 0 || value.length > 100 || regex.test(value) == false) {
        return false;
    } else {
        return true;
    }
}


//邮箱验证
//    $('#email').blur(function(){
//    	var value = $('#email').val().trim();
//    	if(value==null || value.length==0){
//    		layer.tips('请填写邮箱','#email',{tips: [1, '#FF0033']});
//    		
//    	}else{
//    		if(value!=null && validationEmail(value) == false){
//    			$("#email").val("");
//    			layer.tips('请输入正确的邮箱','#email',{ tips: [1, '#FF0033']});
//    		}
//    	}
//    });

//球队名
$('#teamName').blur(function () {
    var value = $('#teamName').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写球队名', '#teamName', {tips: [1, '#FF0033']});
    } else {
        if (value != null && validationCNZNNumber(value, 30) == false) {
            $("#teamName").val("");
            layer.tips('球队名仅支持中英文数字且最大长度为30', '#teamName', {tips: [1, '#FF0033']});
        }
    }

});
//标语
$('#teamInspirationalSlogan').blur(function () {
    var value = $('#teamInspirationalSlogan').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写励志标语', '#teamInspirationalSlogan', {tips: [1, '#FF0033']});
    } else {
        if (value != null && value.length > 0) {
            if (value.length > 60 || validation(value) == false) {
                $("#teamInspirationalSlogan").val("");
                layer.tips('励志标语支持中英数字和普通标点符号(最长60)', '#teamInspirationalSlogan', {tips: [1, '#FF0033']});
            }
        }
    }
});


//领队名
$('#teamLeader').blur(function () {
    var value = $('#teamLeader').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写领队名字', '#teamLeader', {tips: [1, '#FF0033']});
    } else {
        if (value.length > 20 || validationCHS(value) == false) {
            $("#teamLeader").val("");
            layer.tips('领队名字仅支持中英文(最长20,点请用·)', '#teamLeader', {tips: [1, '#FF0033']});
        }
    }

});
//领队电话
$('#teamLeaderPhone').blur(function () {
    var value = $('#teamLeaderPhone').val().trim();
    var regex = /^[1]{1}[2-578]{1}[0-9]{9}$/;
    if (value == null || value.length == 0) {
        layer.tips('请填写手机号码（11位）', '#teamLeaderPhone', {tips: [1, '#FF0033']});
    } else {
        if (value.length != 11 || regex.test(value) == false) {
            $("#teamLeaderPhone").val("");
            layer.tips('请输入正确的手机号码（11位）', '#teamLeaderPhone', {tips: [1, '#FF0033']});
        }
    }


});
$('#code').blur(function () {
    var value = $('#code').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写邮箱验证码', '#code', {tips: [1, '#FF0033']});
    } else {
        if (value.length > 10) {
            layer.tips('邮箱验证码错误', '#code', {tips: [1, '#FF0033']});
        }
    }
});


$('#subid').click(function () {
    //队徽是否上传
    var value = $('#badgeisUpload').val().trim();
    if (value == null || value.length == 0) {
        layer.msg('请上传队徽', {icon: 0});
        return false;
    }
    //球队名
    value = $('#teamName').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写球队名', '#teamName', {tips: [1, '#FF0033']});
        return false;
    }
    if (validationCNZNNumber(value, 30) == false) {
        $("#teamName").val("");
        layer.tips('球队名仅支持中英文数字且最大长度为30', '#teamName', {tips: [1, '#FF0033']});
        return false;
    }

//		value = $('#email').val().trim();
//		if(value == null || value.length == 0){
//			layer.tips('请填写邮箱','#email',{ tips: [1, '#FF0033']});
//			return false;
//		}
//		if(validationEmail(value) == false){
//			$("#email").val("");	
//			layer.tips('请输入正确的邮箱','#email',{ tips: [1, '#FF0033']});
//			return false;
//		}

    value = "123456";
    if (needEmail != 1) {
        value = $('#code').val().trim();
    }
    var codeValue = value;
    if (value == null || value.length == 0) {
        layer.tips('请填写邮箱验证码', '#code', {tips: [1, '#FF0033']});
        return false;
    } else {
        if (value.length > 10) {
            layer.tips('邮箱验证码错误', '#code', {tips: [1, '#FF0033']});
            return false;
        }
    }

    //标语
    value = $('#teamInspirationalSlogan').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写励志标语', '#teamInspirationalSlogan', {tips: [1, '#FF0033']});
        return false;
    }
    if (value.length > 60 || validation(value) == false) {
        $("#teamInspirationalSlogan").val("");
        layer.tips('励志标语支持中英数字和普通标点符号(最长60)', '#teamInspirationalSlogan', {tips: [1, '#FF0033']});
        return false;
    }

    //领队名
    value = $('#teamLeader').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写领队名字', '#teamLeader', {tips: [1, '#FF0033']});
        return false;
    }
    if (value.length > 20 || validationCHS(value) == false) {
        $("#teamLeader").val("");
        layer.tips('领队名字仅支持中英文(最长20,点请用·)', '#teamLeader', {tips: [1, '#FF0033']});
        return false;
    }
    //电话
    value = $('#teamLeaderPhone').val().trim();
    if (value == null || value.length == 0) {
        layer.tips('请填写手机号', '#teamLeaderPhone', {tips: [1, '#FF0033']});
        return false;
    }
    var regex = /^[1]{1}[2-578]{1}[0-9]{9}$/;
    if (value.length != 11 || regex.test(value) == false) {
        $("#teamLeaderPhone").val("");
        layer.tips('请输入正确的手机号码（11位）', '#teamLeaderPhone', {tips: [1, '#FF0033']});
        return false;
    }

    var jsonStr = {
        "teamName": $('#teamName').val().trim(),
        "teamInspirationalSlogan": $('#teamInspirationalSlogan').val().trim(),
        "teamType": $('#teamType').val(),
        "teamRank": $('#teamRank').val(),
        "teamLeader": $('#teamLeader').val().trim(),
        "email": $('#email').val().trim(),
        "code": codeValue,
        "teamLeaderPhone": $('#teamLeaderPhone').val().trim()
    };
    $.ajax({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url: base_path + "/team/team_improve_info.action",
        type: "POST",
        dataType: "json",
        data: jsonStr,
        async: true,
        success: function (data) {
            if (data.status == 200) {

                layer.alert(data.message, {icon: 1});
                setTimeout(function () {
                    // window.parent.location.reload();
                    window.parent.location.reload();
                    window.parent.parent.parent.location.reload();
                }, 1200);

            } else {
                layer.alert(data.message, {icon: 0});
                if (data.status == 500) {
                    setTimeout(function () {
                        window.top.location.replace(base_path + "/team/login_view.html");
                    }, 2000);
                }
                return false;
            }
        },
        error: function (req, status, reason) {
            layer.alert('系统异常,请刷新重试', {
                skin: 'layer-bule-style'
                , closeBtn: 0
            });
        }
    });
});