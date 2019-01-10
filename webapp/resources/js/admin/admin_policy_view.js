/**
 * Created by MeiXiebing on 5/25/17.
 */
var editor = new wangEditor('editor');
editor.config.uploadImgUrl = 'upload.action';
editor.config.menus = [
    'source',
    '|',
    'bold',
    'underline',
    'italic',
    'strikethrough',
    'eraser',
    'forecolor',
    'bgcolor',
    '|',
    'quote',
    'fontfamily',
    'fontsize',
    'head',
    'unorderlist',
    'orderlist',
    'alignleft',
    'aligncenter',
    'alignright',
    '|',
    'link',
    'unlink',
    'table',
    'emotion',
    '|',
    'img',
    'video',
    '|',
    'undo',
    'redo',
    'fullscreen'
];
editor.config.uploadImgFileName = 'file';
editor.create();


$("#subbotton").click(function () {
    var policyContext = editor.$txt.html();
    var title = $("#input_title").val();
    var flag = true;
    if (policyContext.length > 10000){
        flag = false;
        layer.alert("内容的字数为：10000字！");
        return;
    }else {
        if (title.length > 50 || title.length < 1){
            layer.alert("标题的字数为：1-50字！");
            flag = false
            return;
        }
    }


    if (flag){
        $.ajax({
            url:"policy_save.action",    //请求的url地址
            type:"post",   //请求方式
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"id":$("#pId").val(),"title":title,"content":policyContext,"_csrf":$("#token").val()},    //参数值
            beforeSend:function(){
                //请求前的处理
                console.log("form submit");
            }, success:function(data){
                //请求成功时处理
                if (data.status == "500"){
                    layer.alert(data.msg,function () {
                        window.location.reload();
                    });
                }else {
                    layer.alert(data.msg,function () {
                        window.location.reload();
                    });
                }
            }, complete:function(){
                //请求完成的处理
            }, error:function(XMLHttpRequest, textStatus, errorThrown) {
                //请求出错处理

                console.log("error");

                console.log(XMLHttpRequest);
                console.log(textStatus);
                console.log(errorThrown);
            }

        })
    }
});