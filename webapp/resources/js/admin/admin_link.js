/**
 * Created by MeiXiebing on 5/24/17.
 */


$("#subbotton").click(function () {
    var linkName = $("#linkName").val();
    var linkAddr = $("#linkAddr").val();
    var linkType = $("#linkType").val();
    $.ajax({
        url:"link_save.action",    //请求的url地址
        type:"post",   //请求方式
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"id":linkJson.id,"linkName":linkName,"linkAddr":linkAddr,"linkType":linkType,"_csrf":$("#token").val()},
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
    });

})
