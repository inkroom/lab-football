/**
 * 学生登录js
 */
$(function () {
    $('input').on('blur focus', function () {
        if ($(this).is(":focus") && $(this).val() === "") {
            $(this).removeClass("error");
        } else if (!$(this).is(":focus") && $(this).val() === "") {
            $(this).addClass("error");
        }
    });
    $('button:eq(0)').on('click', function () {
        login();
    });
    // var inputs = document.getElementsByTagName('input');
    // for (var i = inputs.length - 1; i >= 0; i--) {
    // 	inputs[i].onfocus = function() {
    // 		this.style.borderWidth = "1px";
    // 		this.style.borderColor = "rgb(207, 218, 221)";
    // 		this.style.borderStyle = "solid";
    // 	};
    // 	inputs[i].onblur = function() {
    // 		if (this.value === "") {
    // 			this.style.borderWidth = "2px";
    // 			this.style.borderColor = "red";
    // 			this.style.borderStyle = "solid";
    // 		} else {
    // 			this.style.borderWidth = "1px";
    // 			this.style.borderColor = "rgb(207, 218, 221)";
    // 			this.style.borderStyle = "solid";
    // 		}
    // 	};
    // }
    // document.getElementsByTagName('button')[0].onclick = function() {
    //
    // }
});

function login() {
    // var inputs = document.getElementsByTagName('input');
    // for(var i=0;i<inputs.length;i++){
    // if(inputs[i].value==""){
    // inputs[i].
    // }
    // }
    var $inputs = $('input');
    $inputs.removeClass('error');

    for (var i = 0; i < $inputs.length; i++) {
        if ($inputs.eq(i).val() === "") {
            $inputs.eq(i).addClass('error');
            // inputs[i].value = "2344";
            // inputs[i].style.background="red";
            // inputs[i].style.borderWidth = "2px";
            // inputs[i].style.borderColor = "red";
            // inputs[i].style.borderStyle = "solid";
            return;
        }
    }
    $.ajax({
        url: "login",
        type: "post",
        dataType: "json",
        data: {
            "studentId": $inputs.eq(0).val(),
            "studentName": $inputs.eq(1).val(),
            "keyWord": $inputs.eq(2).val()
        },
        success: function (data) {
            if (data.status) {
                // alert("登录成功！");
                window.location.href = "exam";// 跳到考试页面
            } else {
                if (data.code === 0) {
                    $('label').html('学号或姓名错误，或者您不该参与此次考试！').show();
                } else if (data.code === 1) {
                    $('label').html('您已参加过本次考试，不能重复考试！').show();
                }
            }
        },
        error: function () {
            $('label').html('网络错误！').show();
        }
    });
}