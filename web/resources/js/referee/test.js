$(function () {
    /*入场验证*/
    // $('.img').click(function () {
    //     if ($(this).hasClass('img')) {
    //         $(this).addClass("scorer");
    //         $(this).removeClass("img");
    //     } else {
    //         $(this).addClass("img");
    //         $(this).removeClass("scorer");
    //     }
    // })

    $('.img1').click(function(){
        if($(this).hasClass('scorer')){
            $(this).removeClass("scorer");
            $(this).addClass("img");
            $(this).next().removeClass("invisible");
        }else{
            $(this).next().trigger('click')
        }

    });
    $(".img2").click(function(){
        $(this).addClass('invisible');
        $(this).prev().removeClass("img");
        $(this).prev().addClass("scorer");
    })

});

function submit() {
    // var m = 0;
    // var n = 0;
    // var aa =[];
    // var bb =[];
    var playerIdA = [];
    var playerIdB = [];

    $('#master .img').each(function (index) {
        playerIdA.push($(this).attr('playerId'));

    })

    $('#servlet .img').each(function (index) {
        playerIdB.push($(this).attr('playerId'));

    })

    console.log(playerIdA);
    console.log(playerIdB);
    if (playerIdA.length === 0 || playerIdB.length === 0) {
        msg('您选择的球员个数不合法');
        return;
    }
    layer.open({
        content: '提交后不可修改，您确定要提交选中的球员吗？'
        , btn: ['确定', '取消']
        , yes: function (index) {
            layer.close(index);

            ajax({
                url: getRootPath() + "/referee/check",
                dataType: "json",
                data: {
                    "playerIdA": JSON.stringify(playerIdA),
                    "playerIdB": JSON.stringify(playerIdB),
                    token: $('#token').html()
                },
                type: "post",
                success: function (result) {
                    switch (result.status) {
                        case 0 :
                            msg('球员加入赛程成功！');
                            break;
                        case 1 :
                            msg('球员加入赛程失败！');
                            break;
                        case 21:
                            msg('您已完成入场验证，不可重复提交');
                    }
                }
            });
        }
    });


}

