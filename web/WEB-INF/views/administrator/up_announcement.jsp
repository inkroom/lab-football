<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 上传公告</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">

</head>
<body style=" background: url('${base_path}/resources/img/leader/school.jpeg') no-repeat;background-size: 100%">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    新公告
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addNotice_from" onsubmit=" return checkFile()"action="${base_path}/admin/UploadFile" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="noticeTitle">标题</label><input type="text"  class="form-control"
                                                                  name="title" id="noticeTitle"
                                                                  placeholder="添加的新通知"/>
                    </div>
                    <div class="form-group">
                        <label for="noticeContent">内容</label><textarea required class="form-control" rows="5"
                                                                       name="content" id="noticeContent"
                                                                       placeholder="这是要给新的通知"></textarea>
                        <%--<input type="password" class="form-control" name="noticeContent" id="noticeContent" />--%>
                    </div>
                    <div class="form-group">
                       上传附件</label><input type="file" class="btn btn-primary"  id="noticeFile"/>
                        <p class="help-block">
                            如果有多个文件请上传压缩包
                        </p>
                    </div>
                        <div id="noticeResult" class="alert" hidden>通知发布成功</div>
                        <div>
                            <button type="submit" class="btn btn-primary">上传</button>
                            <button type="reset" class="btn btn-primary" id="modal_reset" >重置</button>
                        </div>

                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

</body>
<script src="${base_path}/resources/common/jquery/jquery.js"></script>
<script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
<script src="${base_path}/resources/common/jquery/jquery-form.js"></script>
<script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
<script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>

<script>

    $(document).ready(function() {
        $('#addNotice_from').ajaxForm({

            success: function (data) {
                if(data.status!=1||data.status!=undefined)
                {
                    layer.msg('上传成功');
                }
                 else if(data.status===1)
                {
                    layer.msg('上传失败');
                }
            }

        });

    }


    )

    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
        $("#addNotice_from").validate({

                //重写showErrors
                showErrors: function (errorMap, errorList) {
                    var msg = "";
                    $.each(errorList, function (i, v) {
                        //msg += (v.message + "\r\n");
                        //在此处用了layer的方法,显示效果更美观
                        layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
                        return false;
                    });
                },
                    /* 失去焦点时不验证 */
                onfocusout: false
            }
        )

    })
function checkFile() {
    if ( $("#noticeFile").val()== "" || $("#noticeFile").length == 0) {
        $("#noticeFile").removeAttr("name");
        console.log("没有文件");
        return true;
    } else {
        $("#noticeFile").attr("name","commonsMultipartFile");
        console.log("有文件");
        return true;
    }
}
</script>
</html>