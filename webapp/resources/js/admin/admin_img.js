/**
 * Created by MeiXiebing on 5/25/17.
 */
var vm = avalon.define({
    $id:'tableElement',
    list: [],
    basePath:basePath+"/",
    updateOther:function (id,imgId) {
        updateImg(id,imgId);
    },
});



$(function(){
    vm.list = listImg;
})

function updateImg(elementId,imgId) {
    layer.confirm('是否提交？', {
        btn: ['是','否'] //按钮
    }, function(){
        $.ajaxFileUpload(
            {
                url: 'roll_update_img.action', //用于文件上传的服务器端请求地址
                type:"post",
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: elementId, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                data:{"imgId":imgId,"_csrf":token},
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                success: function (data)  //服务器成功响应处理函数
                {
                    layer.alert(data.msg,function () {
                        window.location.reload();
                    })
                },
                error:function(XMLHttpRequest, textStatus, errorThrown)//服务器响应失败处理函数
                {
                    layer.alert('上传失败，请选择标准大小为1M一下的图片!',function(){
                        window.location.reload();
                    });
                }
            }
        )
    })
}
