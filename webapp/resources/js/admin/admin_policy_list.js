/**
 * Created by MeiXiebing on 5/25/17.
 */
var token = "";

var commonPageNum = 1;

var vm = avalon.define({
    $id: "listPage",
    list: [],
    startRow:0,
    endRow:0,
    total:0,
    editPolicy:function(id) {
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
            content: ['policy_add_view.action?id='+id, 'no'], //iframe的url，no代表不显示滚动条
            end: function(){
                getPage(commonPageNum);
            },
            resizing:true
        });
    },
    delPolicy:function (id) {
        layer.confirm('是否确认删除？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                url:"policy_del.action",    //请求的url地址
                type:"post",   //请求方式
                dataType:"json",   //返回格式为json
                async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                data:{"id":id,"status":'0',"_csrf":token},    //参数值
                beforeSend:function(){
                    //请求前的处理
                    console.log("form submit");
                }, success:function(data){
                    //请求成功时处理
                    if (data.status == "500"){
                        layer.alert(data.msg,{closeBtn: 0},function () {
                            window.location.reload();
                        });
                    }else {
                        layer.alert(data.msg,function (index) {
                            getPage(commonPageNum);
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
})



function getPage(pageNum) {
    var myDate;
    $.ajax({
        url:"policy_page.action",    //请求的url地址
        type:"post",   //请求方式
        dataType:"json",   //返回格式为json
        async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        data:{"pageNum":pageNum},   //参数值
        beforeSend:function(){
            //请求前的处理
            console.log("form submit");
        }, success:function(data){
            //请求成功时处
            vm.list=data.data.page.list;
            vm.startRow=data.data.page.startRow;
            vm.endRow=data.data.page.endRow;
            vm.total=data.data.page.total;
            token = data.token.token;
            myDate = data.data.page;
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
    loadPage(1);
})



function loadPage(pageNum){
    var data = getPage(pageNum);
    $(".tcdPageCode").createPage({
        pageCount:data.pages,
        current:data.pageNum,
        backFn:function(p){
            getPage(p);
            commonPageNum = p;
        }
    });
}


function editPolicy(id) {
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
        content: ['policy_add_view.action?id='+id, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){
            getPage(commonPageNum);
        },
        resizing:true
    });
}
