/**
 * Created by MeiXiebing on 5/24/17.
 */
var token = "";

var commonPageNum = 1;

var vm = avalon.define({
    $id:'list',
    list: [],
    startRow:0,
    endRow:0,
    total:0,
    oderNum:0,
    updateOther:function (id,type) {
        updateOther(id,type);
    },
    editLink:function (id) {
        editLink(id);
    }
});






function getPage(pageNum,type) {
    var myDate;
    $.ajax({
        url:"link_page.action",    //请求的url地址
        type:"post",   //请求方式
        dataType:"json",   //返回格式为json
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"pageNum":pageNum,"type":type},   //参数值
        beforeSend:function(){
            //请求前的处理
            console.log("form submit");
        }, success:function(data){
            //请求成功时处
            if (data.status == 500){
                layer.alert(data.msg);
                window.location.reload();
            }else {
                vm.list=data.data.page.list;
                vm.startRow=data.data.page.startRow;
                vm.endRow=data.data.page.endRow;
                vm.total=data.data.page.total;
                token = data.token.token;
                myDate = data.data.page;
            }
        }, complete:function(){
            //请求完成的处理
        }, error:function(XMLHttpRequest, textStatus, errorThrown) {
            //请求出错处理
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        }
    })
    return myDate;
}


$(function () {
    loadPage(1,1);
})

$("#linkType").change(function () {
    loadPage(1,$("#linkType").val());
    vm.oderNum = 0;
})


function loadPage(pageNum,type){
    var data = getPage(pageNum,type);
    $(".tcdPageCode").createPage({
        pageCount:data.pages,
        current:data.pageNum,
        backFn:function(p){
            getPage(p,$("#linkType").val());
            commonPageNum = p;
            vm.oderNum = (p - 1) * 10;
        }
    });
}


function updateOther(id,type) {
    layer.confirm('是否确认提交？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            url:"link_update_other.action",    //请求的url地址
            type:"post",   //请求方式
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"id":id,"type":type,"_csrf":token,"linkType":$("#linkType").val()},    //参数值
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
                    layer.alert(data.msg,function (index) {
                        getPage(commonPageNum,$("#linkType").val());
                        $(".tcdPageCode").
                        layer.close(index);
                    },true);
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
    }, function(){

    });
}


function editLink(id) {
    layer.open({
        type: 2,
        title: false,
        closeBtn: 1, //不显示关闭按钮
        shade: [0],
        area: ['700px', '500px'],
        //offset: 'rb', //右下角弹出
        // time: 2000, 2秒后自动关闭
        maxmin: true, //开启最大化最小化按钮
        anim: 2,
        content: ['link_edit.action?id='+id, 'yes'], //iframe的url，no代表不显示滚动条
        end: function(){
            getPage(commonPageNum,$("#linkType").val());
        },
        resizing:true
    });
}
