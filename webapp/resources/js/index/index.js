var vm = avalon.define({
    $id:'main',
    listImg:[],
    listPolicy:[],
    linkList01:[],
    linkList02:[],
    linkList03:[],
    linkList04:[],
    playerCount:0,
    teamCount:0,
    coachCount:0,
    comCount:0,
    headU:"window.open('",
    tailU:"','_blank');"
});


$(function () {

    $.ajax({
        url:"index/index_base.action",    //请求的url地址
        type:"post",   //请求方式
        dataType:"json",   //返回格式为json
        async:true,//请求是否异步，默认为异步，这也是ajax重要特性
        beforeSend:function(){
            //请求前的处理
            console.log("form submit");
        },
        success:function(data){
            //请求成功时处理
            setTimeout(function(){
                var dataJ = data.data;
                var count = eval("(" + dataJ.count + ")");
                var policy = eval("(" + dataJ.policy + ")");
                vm.playerCount = count.player;
                vm.teamCount = count.team;
                vm.coachCount = count.coach;
                vm.comCount = count.com;
                // $.each(imgs, function (index, img) {
                //     $("#rollId"+index).attr("src",img.addr);
                //     document.getElementById("rollId"+index).src = img.addr;
                //     console.log($("#rollId"+index));
                // });

                // var ul=document.getElementById("rollGroup");
                // $.each(imgs, function (index, img) {
                //     var obj=document.createElement("li");
                //     obj.innerHTML="<li><a href='#1'><img src='${base_path}/resources/img/index/1a.jpg' /></a></li>";
                //     ul.appendChild(obj);
                // });



                vm.listPolicy = policy;
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



    myFocus.set({
        id:'myFocus',//ID
        pattern:'mF_fancy'//风格
    });





    getLink(1);
    getLink(2);
    getLink(3);
    getLink(4);
});


function getLink(type) {
    $.ajax({
        url:"index/index_link.action",    //请求的url地址
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


