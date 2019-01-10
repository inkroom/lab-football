$(function () {

    //获取发送url
    var sendUrl = "send";
    var upload = "image";
    if (location.href.indexOf("m") > -1) {
        sendUrl = "../" + sendUrl;
        upload = "../" + upload;

        upload = getRootPath()+"/live/image";
    }


    var uploader = new WebUploader.Uploader({
        swf: getRootPath() + '/resources/lib/webuploader/Uploader.swf',
        fileVal: 'file',
        server: upload,
        pick: {
            id: '#hidden',
            innerHtml: '上传图片'
        },
        auto: true,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    $('#picImg').click(function () {
        $('.webuploader-element-invisible:eq(0)').trigger('click');
    })
    uploader.on('uploadBeforeSend', function (object, data, handler) {
        delete data.id;
        delete data.type;
        delete  data.lastModifiedDate;
        delete data.size;
        data.token = $('#token').html();
        data['X-Requested-With'] = 'XMLHttpRequest';
    })
    uploader.on('uploadError', function (file, reason) {
        msg('上传失败，请重试');
    })
    uploader.on('uploadSuccess', function (file, data) {
        $('#token').html(data.token);
        if (data.status === 0) {
            $('#del').show();
            msg('图片上传成功')
            $('#picImg').attr('src', getRootPath() + '/' + prefix + '/' + data.data.path);
        } else if (data.status === 1) {
            msg('图片上传失败');
        } else if (data.status === 5) {
            msg('系统错误');
        } else if (data.status === 14) {
            msg('页面过期，请刷新页面');
        } else if (data.status === 18) {
            msg('比赛已结束');
        }
    });
    uploader.on('error', function (type) {
        msg('系统错误');
    })
    var reCoonTime = 0;//重连次数
    // console.log(pageScroller.sections);
    $('#nextB').click = function (e) {
        // e.preventDefault();
        // pageScroller.next();
    }

    function transDate(value) {
        var date = new Date(value);
        return (date.getHours() < 10 ? ('0' + date.getHours()) : date.getHours()) + ':' +
            ((date.getMinutes()) < 10 ? ('0' + date.getMinutes()) : (date.getMinutes())) + ':' +
            (date.getSeconds() < 10 ? ('0' + date.getSeconds()) : date.getSeconds());
    }

    function scroll() {
        $('#messages').append("<div id='hash'></div>");
        location.hash = '#hash';
        $('#hash').remove();
        location.hash = '';
        // pageScroller.next();
    }

    var prefix = $('#prefix').val().trim();

    //创建文件选择器
    function createFileChooser() {


        $('#picID').remove();
        $('#imageBox').append('<input type="file" name="file" id="picID"\n' +
            '                                               accept="image/gif,image/jpeg,image/x-png" class="input-style"/>');
        $('#picID').on('change', function () {
            alert(1)
            uploader.addFiles(new WebUploader.File(this.files[0]));
            alert(2)
            console.log(uploader.getFiles());
            uploader.upload();
            alert(4)

            // $.ajaxFileUpload({
            //     url: upload,
            //     secureuri: false,
            //     fileElementId: 'picID',
            //     dataType: 'json',
            //     data: {
            //         token: $('#token').html(),
            //         'X-Requested-With': 'XMLHttpRequest'
            //     },
            //     success: function (data, status) {
            //         $('#token').html(data.token);
            //         // createFileChooser();
            //         $('#img').removeAttr('disabled').removeClass('disabled');
            //         // $(this).attr('disabled', true).addClass('disabled');
            //         if (data.status === 0) {
            //             $('#del').show();
            //             msg('图片上传成功')
            //             $('#picImg').attr('src', getRootPath() + '/' + prefix + '/' + data.data.path);
            //         } else if (data.status === 1) {
            //             msg('图片上传失败');
            //         } else if (data.status === 5) {
            //             msg('系统错误');
            //         } else if (data.status === 14) {
            //             msg('页面过期，请刷新页面');
            //         } else if (data.status === 18) {
            //             msg('比赛已结束');
            //         }
            //     },
            //     error: function (data, status, e)//服务器响应失败处理函数
            //     {
            //         // createFileChooser();
            //         // $('#img').removeAttr('disabled').removeClass('disabled');
            //         msg(e);
            //     }
            // })
            $(this).attr('disabled', true).prop('disabled', true);
        })
    }

    // createFileChooser();


    $('#send').click(function () {
        var src = $('#picImg').attr('src');
        var content = $('#content').val().trim();
        if (src.indexOf('resources') !== -1 && content.length === 0) {
            msg('文字和图片不能同时为空');
            return;
        }
        if (content.length > 300) {
            msg('文字长度限制为0-300字')
            return;
        }

        var data = {
            content: content,
            token: $('#token').html()
        };
        if (src.indexOf("resources") === -1) {
            data.imgPath = src.replace(getRootPath() + '/' + prefix + '/', '');
        }
        ajax({
            url: sendUrl,
            data: data,
            dataType: 'json',
            success: function (result) {
                $('#img').removeAttr('disabled').removeClass('disabled');
                switch (result.status) {
                    case 0:
                        msg('发送成功');
                        $('#content').val('');
                        $('#picImg').attr('src', getRootPath() + '/resources/img/live/img.png')
                        $('#hide').trigger('click');
                        break;
                    case 1:
                        msg('发送失败');
                        break;
                }
            }

            ,
            error: function () {
                $('#img').removeAttr('disabled').removeClass('disabled');
            }
        })

    });
    $('#img').click(function () {
        // $('#nextB').trigger('click');
        $('#image').trigger('click');
    });

    var $messages = $('#messages');
    // scroll();
    if ($("#status").html().trim().indexOf("正在比赛") > -1) {
        //创建web socket
        if (!("WebSocket" in window)) {
            msg('您的浏览器版本过低，暂不支持观看直播')
        } else {
            createWebsocket();
        }
    }

    $('#del').on('click', function () {
        $('#picImg').attr('src', getRootPath() + '/resources/img/live/img.png')
    });


    function createWebsocket() {
        var socket = new WebSocket('ws://' + location.host + getRootPath() + '/test');
        socket.onmessage = function (evt) {
            var message = JSON.parse(evt.data);
            if (message.status === 0 && message.data.schId === getSch()) {
                if (message.data.type === 1) {//消息

                    if ($messages.find('hr').length !== 0)
                        $messages.append('<hr class="hr-style"/>\n');
                    $messages.append('<!--用户名-->\n' +
                        '                                <div class="height margin">\n' +
                        '                                    <div class="width1 float">\n' +
                        '                                        <img src="' + getRootPath() + '/resources/img/live/user1.png" />\n' +
                        '                                    </div>\n' +
                        '                                    <div class="padding-top">\n' +
                        '                                        <span class="color1">' + message.data.message.phone + '</span>\n' +
                        '                                        <span class="color1 float-right">' + transDate(message.data.message.time) + '</span>\n' +
                        '                                    </div>\n' +
                        '                                </div>')

                    if (message.data.message.content !== null && message.data.message.content !== '') {
                        $messages.append('<!--文字-->\n' +
                            '                                    <div class="row margin">\n' +
                            '                                        <div class="col-xs-8 col-xs-offset-2 none-padding color1 font-size3">' + message.data.message.content + '</div>\n' +
                            '                                    </div>');
                    }

                    if (message.data.message.imgPath !== null && message.data.message.imgPath !== '') {//图文
                        $messages.append(' <!--图片-->\n' +
                            '                                    <div class="row text-center margin">\n' +
                            '                                        <div class="col-xs-10 col-xs-offset-1">\n' +
                            '                                            <img src="' + getRootPath() + '/' + prefix + '/' + message.data.message.imgPath + '" />\n' +
                            '                                        </div>\n' +
                            '                                    </div>');
                    }
                    scroll();
                } else if (message.data.type === 2) {//状态改变
                    $('#status').html('已结束');
                    $('#send').unbind('click');
                    $('#img').unbind('click');
                    $('#idEdit').attr('disabled', true);
                } else if (message.data.type === 3) {//比分修改
                    $('#scoreA').html(message.data.scoreA);
                    $('#scoreB').html(message.data.scoreB);
                }
            }
        }
        socket.onclose = function (ev) {
            if (reCoonTime >= 3) {
                msg('连接关闭');
                $('#status').html('连接关闭');
            } else {
                createWebsocket();
                reCoonTime++;
            }
        }
        socket.onerror = function (ev) {
            msg('连接中断，刷新重连')
            $('#status').html('连接中断');
        }
    }


    function getSch() {
        var regex = new RegExp('live/([1-9]+[0-9]*)/');
        return parseInt(regex.exec(location.pathname)[1]);
    }

    // //新增
    // function send() {
    //     layer.open({
    //         content: '您还未进行手机验证'
    //         , btn: ['前往', '取消']
    //         , yes: function (index) {
    //             document.location.href = 'phontVerification.html';
    //             layer.close(index);
    //         }
    //     });
    // }
})

