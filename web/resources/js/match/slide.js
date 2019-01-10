$(function () {
    var win = $(".pic");
    var links = $(".tit a");
    var float = $(".float");
    var divs = $(".box-1 div");
    var num1 = 0;  //
    var num2 = 0;
    var timer = null; //定时器返回值，主要用于关闭定时器
    var iNow = 0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0
    win.hover(function () {
        $(".leftB,.rightB").css("display", "block");
    }, function () {
        $(".leftB,.rightB").css("display", "none");
    });
    $(".leftB").click(function () {
        divs.finish();
        float.stop(true);
        var temp = num1;
        num1--;
        if (num1 == -1) {
            num1 = 4;
        }
        divs.eq(num1).css("left", 700).animate({left: 0});
        divs.eq(temp).animate({left: -700});
        links.css("color", "#d3a268");
        float.animate({left: links.eq(num1).position().left})
        links.eq(num1).css("color", "#fff");
    });
    $(".rightB").click(function () {
        divs.finish();
        float.stop(true);
        var temp = num1;
        num1++;
        if (num1 == 5) {
            num1 = 0;
        }
        divs.eq(num1).css("left", -700).animate({left: 0});
        divs.eq(temp).animate({left: 700});
        links.css("color", "#d3a268");
        float.animate({left: links.eq(num1).position().left})
        links.eq(num1).css("color", "#fff");
    });
    links.hover(function () {
        //
        divs.finish();
        float.stop(true);
        links.css("color", "#d3a268");
        var that = $(this);
        var lefts = $(this).position().left;
        float.animate({left: lefts}, function () {
            that.css("color", "#fff");
        })
        //
        var index = $(this).index(".tit a");
        num2 = index;
        if (num1 == num2) {
            return;
        } else if (num1 < num2) {
            divs.eq(num2).css("left", 700).animate({left: 0});
            divs.eq(num1).animate({left: -700});
        } else if (num1 > num2) {
            divs.eq(num2).css("left", -700).animate({left: 0});
            divs.eq(num1).animate({left: 700});
        }
        num1 = num2;
        num2 = "";
    }, function () {

    });
    timer = setInterval(function () { //打开定时器
        iNow++;    //让图片的索引值次序加1，这样就可以实现顺序轮播图片
        if (iNow > links.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
            iNow = 0;
        }
        links.eq(iNow).trigger("click"); //模拟触发数字按钮的click
    }, 2000); //2000为轮播的时间
});