$(document).ready(function(){
    var DEFAULT_VERSION = "11";
    var ua = navigator.userAgent.toLowerCase();
    var isIE = ua.indexOf("msie")>-1;
    var safariVersion;
    if(isIE){
        safariVersion =  ua.match(/msie ([\d.]+)/)[1];
    }
    if(safariVersion <=DEFAULT_VERSION ){
        alert("您的IE浏览器版本过低，请升级到最新版本");
    }
        $("#up-img-touch").click(function(){
        		$("#doc-modal-1").modal({width:'600px'});
        });
});
$(function(){
    'use strict';
    // 初始化
    var $image = $('#image');
    $image.cropper({
        aspectRatio: '1',
        autoCropArea:0.8,
        preview: '.up-pre-after',
        
    });

    // 事件代理绑定事件
    $('.docs-buttons').on('click', '[data-method]', function() {
        var $this = $(this);
        var data = $this.data();
        var result = $image.cropper(data.method, data.option, data.secondOption);
        switch (data.method) {
            case 'getCroppedCanvas':
            if (result) {
                // 显示 Modal
                $('#cropped-modal').modal().find('.am-modal-bd').html(result);
                $('#download').attr('href', result.toDataURL('image/jpeg'));
            }
            break;
        }
    });
    

    // 上传图片
    var $inputImage = $('#inputImage');
    var URL = window.URL || window.webkitURL;
    var blobURL;

    if (URL) {
        $inputImage.change(function () {
            var files = this.files;
            var file;

            if (files && files.length) {
               file = files[0];

               if (/^image\/\w+$/.test(file.type)) {
                    blobURL = URL.createObjectURL(file);
                    $image.one('built.cropper', function () {
                        // Revoke when load complete
                       URL.revokeObjectURL(blobURL);
                    }).cropper('reset').cropper('replace', blobURL);
                    $inputImage.val('');
                } else {
                	layer.msg("请选择一个图片文件",{icon: 0});
                }
            }
 
            // Amazi UI 上传文件显示代码
            var fileNames = '';
            $.each(this.files, function() {
                fileNames += '<span class="am-badge">' + this.name + '</span> ';
            });
            $('#file-list').html(fileNames);
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }
    
    //绑定上传事件
    $('#up-btn-ok').on('click',function(){
    	var $modal = $('#my-modal-loading');
    	var $modal_alert = $('#my-alert');
    	var img_src=$image.attr("src");
    	if(img_src==""){
    		set_alert_info("没有选择上传的图片");
    		$modal_alert.modal();
    		return false;
    	}
    	
    	$modal.modal();
    	var url=$(this).attr("url");
    	var canvas=$("#image").cropper('getCroppedCanvas');
    	var data=canvas.toDataURL("image/jpeg",0.7); //转成base64
    	var imgs = data.substring(23);
        $.ajax( {  
                url:url,
                type: "POST",  
                data: {"image":imgs},
                success: function(data){
                	 $modal.modal('close');
                	if(data.flag==200){
                	    var basepath=$('#base_path').val();
                        $("#up-img-touch").attr("src",basepath+'/image/'+data.name+'?'+Math.random());
                        $("#pr_photo").val(data.name);
                        layer.alert('图片上传成功',function(index){
                                $("#doc-modal-1").modal('close');
                            layer.close(index);


                        });

                	}
                	if(data.flag==500){
                        layer.alert('图片上传失败',function(index){
                            $("#doc-modal-1").modal('close');
                            layer.close(index);


                        });
                    }
                },
                error: function(){
                	$modal.modal('close');
                    layer.alert('图片上传失败',function(index){
                        $("#doc-modal-1").modal('close');
                        layer.close(index);


                    });

                }  
         });  
    	
    });
    
});

function rotateimgright() {
$("#image").cropper('rotate', 90);
}


function rotateimgleft() {
$("#image").cropper('rotate', -90);
}

function set_alert_info(content){
	$("#alert_content").html(content);
}
function closemodel(){
    $("#doc-modal-1").modal('close');
}


 
