<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/lib/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/player/innerupdate.css" />
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/common/style.css" />
    <link href="${path}/resources/lib/layer/theme/default/layer.css"/>
</head>
<body>
<jsp:include page="../common/head.jsp"/>

<div class="wrapper">
    <div class="top_">
        <h2>球员信息</h2>
    </div>
    <div class="box">
        <div class="img_">
            <fmt:setBundle basename="properties.upload"/>
            <div id="hidden" style="display: none"></div>
            <img id="picImg" style="cursor: pointer;"  src="${path}/<fmt:message key="upload.image.url.prefix"/>/${item.headPic}"  />
            <label for="image" id="image">上传头像</label >
            <p id="image1"  />

        </div>
        <div class="mess">
            <h2>基本信息</h2>
            <a href="javascript:void(0)" id="change"   data-toggle="modal" data-target="#myModal" >修改信息</a>
        </div>
        <div class="main">
            <div class="lef">
                <p>用户名:</p>
                <p>姓名:</p>
                <p>身份证号:</p>
                <p>性别:</p>
                <p>学校:</p>
                <p>年级:</p>
                <p>班级:</p>
                <p>球队:</p>
            </div>
            <div class="rig">
                <p>${item.userName }</p>
                <p>${item.name }</p>
                <p>${item.idCard }</p>
                <p>
                    <c:if test="${item.sex==0}">男</c:if>
                    <c:if test="${item.sex==1}">女</c:if>
                    <c:if test="${item.sex==null}">没有</c:if>
                </p>
                <p>${orgname }</p>
                <p>${item.grade } 年级</p>
                <p>${item.classes } 班</p>
                <p>
                    <c:if test="${empty teamnames}">
                        没有
                    </c:if>
                    <c:if test="${!empty teamnames}">
                        <c:forEach var="teamname" items="${teamnames}">[ ${teamname} ] </c:forEach>
                    </c:if>
                </p>
            </div>
        </div>
    </div>
</div><!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">球员信息修改</h4>
            </div>
            <div class="modal-body" style="padding:0;">
                <div class="wrapper1">

                    <form class="form-horizontal">

                        <div class="main">

                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="name">姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" class="form-control" placeholder="姓名" value="${item.name}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label"style="line-height:12px;">性别</label>
                                <div class="col-sm-9">
                                    <label for="boy" class="sex">男</label>
                                    <input type="radio" id="boy" class="sex1" value="0" name="sex" <c:if test="${item.sex eq '0'}" >checked="checked"</c:if>>
                                    <label for="girl" class="sex">女</label>
                                    <input type="radio" id="girl" class="sex1" value="1" name="sex" <c:if test="${item.sex eq '1'}" >checked="checked"</c:if>>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="idcard" >身份证号</label>
                                <div class="col-sm-9">
                                    <input type="text" id="idcard" class="form-control" placeholder="身份证号" maxlength="18" value="${item.idCard}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="password1">原密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="password1" placeholder="请输入原密码" maxlength="20" class="form-control">

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="newPassword">新密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPassword" placeholder="请输入新密码" maxlength="20" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="newPassword">确认密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPassword2" placeholder="请再次输入新密码" maxlength="20" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="grade">班级信息</label>
                                <div class="col-sm-9">
                                    <select id="grade">
                                        <option value="1" <c:if test="${item.grade eq '1'}" >selected="selected"</c:if>>1年级</option>
                                        <option value="2" <c:if test="${item.grade eq '2'}" >selected="selected"</c:if>>2年级</option>
                                        <option value="3" <c:if test="${item.grade eq '3'}" >selected="selected"</c:if>>3年级</option>
                                        <option value="4" <c:if test="${item.grade eq '4'}" >selected="selected"</c:if>>4年级</option>
                                        <option value="5" <c:if test="${item.grade eq '5'}" >selected="selected"</c:if>>5年级</option>
                                        <option value="6" <c:if test="${item.grade eq '6'}" >selected="selected"</c:if>>6年级</option>
                                        <option value="7" <c:if test="${item.grade eq '7'}" >selected="selected"</c:if>>7年级</option>
                                        <option value="8" <c:if test="${item.grade eq '8'}" >selected="selected"</c:if>>8年级</option>
                                        <option value="9" <c:if test="${item.grade eq '9'}" >selected="selected"</c:if>>9年级</option>
                                    </select>
                                    <input type="text" id="classes" placeholder="请输入班级" class="form-control" value="${item.classes}">
                                </div>
                            </div>

                        </div>
                    </form>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="button" class="btn btn-success" id="btn_" onclick="reg();return false;">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="${path}/resources/lib/respond.min.js"></script>
<script src="${path}/resources/lib/jquery/jquery-1.11.3.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
<script src="${path}/resources/lib/webuploader/webuploader.withoutimage.min.js"></script>
<script src="${path}/resources/js/index.js"></script>

<script>
    $(function() {
        // dom加载完毕
        var $m_btn = $('#modalBtn');
        var $modal = $('#myModal');
        $m_btn.on('click', function() {
            $modal.modal({
                backdrop: 'static'
            });
        });

        // 测试 bootstrap 居中
        $modal.on('show.bs.modal', function() {
            var $this = $(this);
            var $modal_dialog = $this.find('.modal-dialog');
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $this.css('display', 'block');
            $modal_dialog.css({
                'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2)
            });
        });

    });
    function reg(){

        if(checkInput()){
            doSubmit();
        }
    }
    function  rel() {
        location.reload();
    }
    function checkInput(){
        /*数字*/
        var reg = /[1-9]+[0-9]*/;
        /*中文*/
        var reg1 = /^[\u4E00-\u9FA5]*$/;
        /*正则-数字字母*/
        var reg2 = /^[0-9a-zA-Z]+$/;
        /*身份证号*/
        var reg3 = /^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/;

        var name=$("#name").val();
        var sex = $("input[name='sex']:checked").val();
        var idcard=$("#idcard").val();
        var city=$("#city").val();
        var oldpassword=$("#password1").val();
        var newpassword=$("#newPassword").val();
        var newpassword2=$("#newPassword2").val();
        var classes=$("#classes").val();
        var grade=$("#grade").val();


        if(name==""){
            layer.msg("姓名不能为空");
            return false;
        }
        else if(!reg1.test(name)){
            layer.msg("姓名不是中文");
            return false;
        }
        else if(sex != 1&& sex != 0){
            layer.msg("没有选择性别");
            return false;
        }
        else if(idcard==""){
            layer.msg("身份证号不能为空");
            return false;
        }
        else if(idcard.length<18){
            layer.msg("身份证号位数不足18位");
            return false;
        }
        else if(!reg3.test(idcard)){
            layer.msg("身份证号格式错误");
            return false;
        }
        else if(oldpassword==""){
            layer.msg("原密码不能为空");
            return false;
        }
        else if(!reg2.test(oldpassword)){
            layer.msg("密码格式错误");
            return false;
        }
        else if(oldpassword.length<6){
            layer.msg("密码位数不足");
            return false;
        }
        else if(newpassword==""){
            layer.msg("新密码不能为空");
            return false;
        }
        else if(newpassword2 != newpassword)
        {
            layer.msg('输入的新密码不一样');
            return false;
        }
        else if(newpassword.length<6){
            layer.msg("密码位数不足");
            return false;
        }
        else if(!reg2.test(newpassword)){
            layer.msg("新密码有非法字符");
            return false;
        }
        else if(city==""){
            layer.msg("市下拉框没有选择");
            return false;
        }
        else if(grade==""){
            layer.msg("年级不能为空");
            return false;
        }
        else if(!reg.test(grade)){
            layer.msg("年级格式错误");
            return false;
        }
        else if(classes==""){
            layer.msg("班级不能为空");
            return false;
        }
        else if(!reg.test(classes)){
            layer.msg("班级格式错误");
            return false;
        }
        return true;

    }
    function doSubmit() {
        layer.msg("123");
        ajax({
            type: "POST",   //提交的方法
            url:"${path}/player/update", //提交的地址
            data:{
                name:$("#name").val(),
                sex:$("input[name='sex']:checked").val(),
                idCard:$("#idcard").val(),
                password:$("#newPassword").val(),
                grade:$("#grade").val(),
                classes:$("#classes").val(),
                oldPassword:$("#password1").val(),
                token:$("#token").html()

            },
            dataType:'json',
            success: function(data) {  //成功
                switch(data.status){
                    case 0:
                        layer.msg("球员信息更新成功");
                        setTimeout(function(){
                            window.location.href="${path}/player/list";
                        },1000);
                        break;
                    case 6:
                        layer.msg("参数错误");
                        break;
                }
            },
            error:function(){
                layer.msg("请求异常");
            }

        });
    }
</script>
<script>
    $('#image').click(function () {
        $('.webuploader-element-invisible:eq(0)').trigger('click');
    });

    var uploader = new WebUploader.Uploader({
        swf: getRootPath() + '/resources/lib/webuploader/Uploader.swf',
        fileVal: 'file',
        server: getRootPath() +'/player/updatePhoto',
        pick:{
            id:'#hidden'
        },
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    uploader.on('fileQueued', function (file) {
        console.log(file);

        var  flag=true;
        if(file.size>204800){
            layer.msg('图片大于200K！');
            flag= false;
            return flag;
        }
        if(file.type!="image/png" && file.type!="image/jpg" && file.type!="image/jpeg"){
            layer.msg('图片格式错误！');
            flag= false;
            return flag;
        }
        if(flag){
            layer.open({
                content: '您确定要修改头像吗？'
                , btn: ['确定', '取消']
                , yes: function (index) {
                    layer.close(index);

                    uploader.upload();
                }
            });
        }
    });
    uploader.on('uploadBeforeSend', function (object, data, handler) {
        delete data.id;
        delete data.type;
        delete  data.lastModifiedDate;
        delete data.size;
        data.token = $('#token').html();
        data['X-Requested-With'] = 'XMLHttpRequest';
    });
    uploader.on('uploadError', function (file, reason) {
        msg('上传失败，请重试');
    });
    uploader.on('uploadSuccess', function (file, data) {
        $('#token').html(data.token);
        if (data.status == 0) {
            layer.msg('图片修改成功');
            $("#picImg").attr("src", getRootPath() + "/<fmt:message key="upload.image.url.prefix"/>/"+  data.data.src+"?abc="+Math.random() );
        } else if (data.status == 1) {
            layer.msg('图片修改失败请重新尝试');
            setTimeout("rel()", "1000");
        }

    });
    uploader.on('error', function (type) {
        msg('系统错误');
    })

</script>
</body>
</html>
