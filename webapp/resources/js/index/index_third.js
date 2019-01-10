/**
 * Created by MeiXiebing on 5/27/17.
 */
var vm = avalon.define({
    $id:'main',
    listPolicy:[],
    linkList01:[],
    linkList02:[],
    linkList03:[],
    linkList04:[],
    headU:"window.open('",
    tailU:"','_blank');"
});




$(function () {
    getLink(1);
    getLink(2);
    getLink(3);
    getLink(4);
})



function getLink(type) {
    $.ajax({
        url:"../index_link.action",    //请求的url地址
        type:"post",   //请求方式
        dataType:"json",   //返回格式为json
        data:{type:type},
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        beforeSend:function(){
            //请求前的处理
            console.log("form submit");
        },
        success:function(data){
            //请求成功时处理
            var data = data.data;
            var count = eval("(" + data.link + ")");
            if (type == 1){
                vm.linkList01 = count;
            }else if (type == 2){
                vm.linkList02 = count;
            }else if (type == 3){
                vm.linkList03 = count;
            }else if (type == 4){
                vm.linkList04 = count;
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(){
            //请求出错处理
            console.log("error");
        }
    });
}