/**
 * Created by WHY on 2017/3/21.
 */

// var vm= new Vue({
//     el:'#LivaAndOverGames',
//     data: {
//         livejson: [],
//         overjson:[]
//     }
// })
var liList = document.getElementById("gameP").getElementsByTagName("li");
var table = document.getElementById("live-past").getElementsByTagName("table");
for (var i = 0; i < liList.length; i++) {
    liList[i].index = i;
    liList[i].onclick = function liMove() {
        if (this.index != 0) {
            document.getElementById("slide-line").style.left = this.index * 80 + "px";
            findOverGames($(this).data("id"));
            findNoLiveGames($(this).data("id"));
        } else {
            document.getElementById("slide-line").style.left = "0px";
            findOverGames($(this).data("id"));
            findNoLiveGames($(this).data("id"));
        }
    }
}
var vm = avalon.define({
    $id: "LivaAndOverGames",
    livejson: [],
    overjson:[],
    nolivejson:[]
})
$(function () {
    $(document).on("ready",function() {
        var areali = $(".team-area ul li");
        areali.eq(0).attr("class", "team-area-checked pos-r");
        areali.eq(0).append("<i class='Hui-iconfont f-14 pos-a'" + ">" + "&#xe63d;" + "</i>");
        $(document).on('click', ".team-area ul li", function() {
            var areali = $(".team-area ul li");
            areali.attr("class", "");
            areali.find("i").remove();
            $(this).attr("class", "team-area-checked pos-r");
            $(this).append("<i class='Hui-iconfont f-14 f-l pos-a'" + ">" + "&#xe63d;" + "</i>");
            findOverGames(1);
            liveGames();
            findNoLiveGames(1);
        });
    });
});
<!--查询当前城市的直播比赛-->
function findOverGames(level,curr) {
    //alert("curr="+curr)
    var ii = layer.load();
    var contest=new Object();
    contest.cityCode=$(".team-area-checked span").data("city-code") || 0;
    contest.level=level;
    contest.liveType=2;
    $.ajax({
        url:"list_over_games.action",
        type:"POST",
        data:{
            "subjectData": JSON.stringify(contest),
            "pageNum": curr||1
        },
        success:function(data){
            layer.close(ii);
            var jsonlist=JSON.parse(data);
            //alert(jsonlist.pages);
            //vm.json =jsonlist.list
            vm.overjson=jsonlist.list;
            laypage({
                cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                pages: jsonlist.pages, //通过后台拿到的总页数
                curr: curr || 1, //当前页
                jump: function(obj, first){ //触发分页后的回调
                    if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                        findOverGames(level,obj.curr);
                    }
                }
            });

        },
        error:function(er){
            console.log(er)
        }
    });
}

function findNoLiveGames(level,curr) {
    //alert("curr="+curr)
    var ii = layer.load();
    var contest=new Object();
    contest.cityCode=$(".team-area-checked span").data("city-code") || 0;
    contest.level=level;
    contest.liveType=2;
    $.ajax({
        url:"list_NoStart_games.action",
        type:"POST",
        data:{
            "subjectData": JSON.stringify(contest),
            "pageNum": curr||1
        },
        success:function(data){
            layer.close(ii);
            var jsonlist=JSON.parse(data);
            //alert(jsonlist.pages);
            //vm.json =jsonlist.list
            vm.nolivejson=jsonlist.list;
            laypage({
                cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                pages: jsonlist.pages, //通过后台拿到的总页数
                curr: curr || 1, //当前页
                jump: function(obj, first){ //触发分页后的回调
                    if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                        findNoLiveGames(level,obj.curr);
                    }
                }
            });

        },
        error:function(er){
            console.log(er)
        }
    });
}

function liveGames(curr) {
    var contest=new Object();
    contest.cityCode=$(".team-area-checked span").data("city-code") || 0;
    contest.liveType=1;
    $.ajax({
        url:"list_live_games.action",
        type:"POST",
        data:{
            "subjectData": JSON.stringify(contest),
            "pageNum": curr||1
        },
        success:function(data){

            var jsonlist=JSON.parse(data);
            //
            // alert(jsonlist);
            //vm.json =json
            vm.livejson=jsonlist.list;
            laypage({
                cont: 'page2', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                pages: jsonlist.pages, //通过后台拿到的总页数
                curr: curr || 1, //当前页
                jump: function(obj, first){ //触发分页后的回调
                    if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                        liveGames(obj.curr);
                    }
                }
            });
        },
        error:function(er){
            
            console.log(er)
        }
    })

}
findOverGames(1);
liveGames();
findNoLiveGames(1);

