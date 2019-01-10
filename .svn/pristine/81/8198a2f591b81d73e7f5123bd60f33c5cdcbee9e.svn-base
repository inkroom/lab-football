$(function () {

    $('.up-height').height($('.input-height').height()-2);

    //记录原本的分数
    var score1 = $('#score1').val();
    var score2 = $('#score2').val();

    $('#upscore1,#upscore2').on('click', function () {
        console.log($(this).attr('id').substring(7))
        var $score = $('#score' + $(this).attr('id').substring(7));
        // if (parseInt($score.val()) === 0) return;
        $score.val(parseInt($score.val()) + 1);
    });
    $('#downscore1,#downscore2').on('click', function () {
        console.log($(this).attr('id').substring(9))
        var $score = $('#score' + $(this).attr('id').substring(9));
        if (parseInt($score.val()) === 0) return;
        $score.val(parseInt($score.val()) - 1);
    });

    $('#confirm').click(function () {
        var scoreA = $('#score1').val();
        var scoreB = $('#score2').val();
        ajax({
            url: 'modify',
            dataType: 'json',
            data: {
                scoreA: scoreA,
                scoreB: scoreB,
                token: $('#token').html().trim()
            },
            success: function (result) {
                switch (result.status) {
                    case 0:
                        msg('修改成功');
                        score1 = scoreA;
                        score2 = scoreB;
                        break;
                    case 1:
                        msg('修改失败，分数归位');
                        $('#score1').val(score1);
                        $('#score2').val(score2);
                        break;
                }
            },
            error: function () {
                msg('网络错误');
            }
        })
    });

    $('#finish').click(function () {
        layer.open({
            content: '确定结束比赛？',
            style:'font-size:20px',
            btn: ['确定', '取消'],
            yes: function (index) {
                layer.close(index);
                location.href = getRootPath() + '/referee/report'
            }
        })
    })

});
