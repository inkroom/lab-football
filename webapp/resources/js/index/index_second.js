/**
 * Created by MeiXiebing on 5/27/17.
 */
var vm = avalon.define({
    $id:'main',
    linkList01:[],
    linkList02:[],
    linkList03:[],
    linkList04:[],
    headU:"window.open('",
    tailU:"','_blank');"
});





$(function () {
    layui.use('flow', function(){
        var flow = layui.flow;
        flow.load({
            elem: '#policy' //流加载容器
            ,scrollElem: '#LAY_demo1' //滚动条所在元素，一般不用填，此处只是演示需要。
            ,done: function(page, next){ //执行下一页的回调
                //模拟数据插入
                $.ajax({
                    url:"policy.action",    //请求的url地址
                    type:"post",   //请求方式
                    dataType:"json",   //返回格式为json
                    data:{pageNum:page},
                    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
                    beforeSend:function(){
                        //请求前的处理
                        console.log("form submit");
                    },
                    success:function(data){
                        //请求成功时处理
                        setTimeout(function(){
                            var lis = [];
                            $.each(data.list,function(index, policy) {
                                lis.push("<li><a href='"+policy.id+"/index_third.html' target='_blank'>"+policy.title+"</a><span>"+policy.create_date+"</span></li>")
                            });
                            //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                            //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                            next(lis.join(''), page < data.lastPage); //假设总页数为 10
                        }, 500);
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
        });
    });
    getLink(1);
    getLink(2);
    getLink(3);
    getLink(4);
})



function getLink(type) {
    $.ajax({
        url:"index_link.action",    //请求的url地址
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