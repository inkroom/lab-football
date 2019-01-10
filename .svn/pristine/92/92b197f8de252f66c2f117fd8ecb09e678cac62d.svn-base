/**
 * Created by 墨盒 on 2017/8/10.
 * 练习js
 */
$(function () {
    $('#prev').on('click', function () {

    });
    $('#next').on('click', function () {

    });
    $('#submit').on('click', function () {

    });
    $('#exit').on('click', function () {

    });
    var $type = $('.type');
    var $difficulty = $('.difficulty');
    var $point = $('.point');
    $type.on('change', function () {
        $type.val($(this).val());
    });
    $difficulty.on('change', function () {
        $difficulty.val($(this).val());
    });
    $point.on('change', function () {
        $point.val($(this).val());
    });

    $('button[name=next]').on('click', function () {
        $.ajax({
            url: '../search.action',
            type: 'get',
            data: {
                _csrf: $('#_csrf').val(),
                difficulty: $('.difficulty:eq(0)').val(),
                type: $('.type:eq(0)').val(),
                subject: getSubject(),
                point: $('.point:eq(0)').val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.status === 500 || result.status === "500") {
                    layer.msg(result.msg);
                } else if (result.status === 200 || result.status === "200") {
                    loadQuestion(result.data.question);
                    loadSelectEvent();
                    if (typeof result.data.other !== 'undefined') {
                        layer.msg('新搜索到的题目与上一题相同');
                    }
                    loadAnswer(result.data.question.answer);
                    // $('.answer>span').html('');
                }
            },
            error: function () {
                layer.msg('网络错误！');
            }
        });
    }).trigger('click');
    $('button[name=prev]').on('click', function () {
        $.ajax({
            url: '../prev',
            data: {_csrf: $('#_csrf').val()},
            dataType: 'json',
            success: function (result) {
                if (result.status === "500") {
                    layer.msg(result.msg);
                    return;
                }
                loadQuestion(result.data.question);
                loadAnswer(result.data.answer);
                loadSelectEvent();
                if (typeof result.data.other !== 'undefined') {
                    layer.msg('新搜索到的题目与上一题相同');
                }
            }
        })
    });
    $('button[name=submit]').on('click', function () {
        var answer = JSON.stringify(getSelect());
        console.log(answer);
        if (answer === '[]') {
            layer.msg('未选择答案！');
        } else {
            $.ajax({
                url: '../commit.action',
                type: 'post',
                dataType: 'json',
                data: {
                    _csrf: $('#_csrf').val(),
                    key: answer
                },
                success: function (result) {
                    if (result.status === "500") {
                        layer.msg(result.msg);
                        return;
                    }
                    layer.msg('回答' + (result.data.result ? '正确' : '错误'));
                },
                error: function () {
                    layer.msg('网络错误！');
                }
            })
        }
    });
    $('button[name=exit]').on('click', function () {

    });
    $('a[name=showAnswer]').on('click', function () {
        var $this = $(this);
        if ($('.answer>span:eq(0)').html() === '') {//没有加载解析
            // loadAnswer(function () {
            //     //显示答案
            //     $('.answer').show(500);
            //     $this.html('收起解析');
            // });
        } else {//已经加载解析
            var $answer = $('.answer');
            if ($answer.eq(0).css('display') === 'none') {
                $answer.show(500);
                $this.html('收起解析');
            } else {//隐藏答案
                $answer.hide(500);
                $this.html('查看解析');
            }
        }

        // var $answer = $('.answer');
        // var $this = $(this);
        // $.ajax({
        //     url: '../search.action',
        //     type: 'get',
        //     data: {
        //         _csrf: $('#_csrf').val(),
        //         difficulty: $('.difficulty:eq(0)').val(),
        //         type: $('.type:eq(0)').val(),
        //         subject: getSubject(),
        //         point: $('.point:eq(0)').val()
        //     },
        //     dataType: 'json',
        //     success: function (result) {
        //         console.log(result);
        //         if (result.status === "500") {
        //             layer.msg(result.msg);
        //             return;
        //         }
        //         loadQuestion(result.data.question);
        //         loadSelectEvent();
        //     }
        // });
        // //显示答案
        // if ($answer.eq(0).css('display') === 'none') {
        //     if ($answer.eq(0).find('span').eq(0).html() === '') {//没有答案
        //         var result = loadAnswer();
        //         if (!result)//没有加载成功答案
        //             return;
        //     }
        //     $answer.show(500);
        //     $this.html('收起解析');
        // }
        // //隐藏答案
        // else {
        //     $answer.hide(500);
        //     $this.html('查看解析');
        // }
    });

    loadSelectEvent();

    // $('.radio_btn1>input[checked=true]').trigger('change');
});
//获取科目
function getSubject() {
    var url = location.href;
    var result = url.match(/practice\/(.+)\/index/);
    console.log(result);
    if (result !== null) {
        console.log(decodeURI(result[1]));
        if (decodeURI(result[1]) === "美术")
            return 2;
        else if (decodeURI(result[1]) === "音乐")
            return 1;
        return 0;
    }
}

///获取题目答案和解析
function loadAnswer(answer, after) {

    answer = JSON.parse(answer);
    var html = '答案是：';
    for (var i = 0; i < answer.length; i++) {
        html += answer[i];
        if (i !== answer.length - 1)
            html += "、";
    }
    $('.answer>span').html(html);
    // $.ajax({
    //         url: '../get_answer.action',
    //         data: {_csrf: $('#_csrf').val()},
    //         dataType: 'json',
    //         success: function (result) {
    //             if (result.status === "500") {
    //                 layer.msg(result.msg);
    //                 return;
    //             }
    //             $('.answer>span').html('答案是：' + result.data.answer);
    //             var html = '';
    //             var answers = result.data.answer.split('\n');
    //             console.log(" answers  =  " + answers);
    //             for (var i = 0; i < answers.length; i++) {
    //                 var xml = azyXml(answers[i]);
    //                 var value = answers[i];
    //                 if (xml !== null) {
    //                     if (xml.type === "image") {
    //                         value = value.replace(/\[(.*)\].*\[\/\1\]/,
    //                             '<img class="img-responsive" src="../../exam/res/' + xml.type + '/' + xml.id + '.action" />');
    //                     } else if (xml.type === "audio") {
    //                         value = value.replace(/\[(.*)\].*\[\/\1\]/,
    //                             '<audio controls="controls">' +
    //                             '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="audio/mpeg">' +
    //                             'Your browser does not support the audio element.</audio>');
    //                     } else if (xml.type === "video") {
    //                         value = value.replace(/\[(.*)\].*\[\/\1\]/,
    //                             '<audio controls="controls" width="150" height="160">' +
    //                             '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="video/ogg">' +
    //                             'Your browser does not support the audio element.</audio>');
    //                     }
    //                 }
    //                 html += value;
    //             }
    //             console.log("最后拼接的答案" + html);
    //             if (typeof after === 'function') {
    //                 after();
    //             }
    //         },
    //         error: function () {
    //             layer.msg('网络错误');
    //         }
    //     }

    return false;
}
var questionIndex = ['A', 'B', 'C', 'D'];
///加载题目
function loadQuestion(question) {

    $('.select-answer').html('');
    $.each(question.select, function (key, value) {
        var $select = $('<div></div>');
        // var obj = JSON.parse(question.select[i]);
        var types = getType(question.type);
        console.log('key = ' + key + ",,,value = " + value);
        var xml = azyXml(value);
        if (xml !== null) {//引用了资源
            // value = value.replace(/\[(.*)\].*\[\/\1\]/, '');
            $select.append('<span class="' + types[2] + '">'
                + '<input type="checkbox" name="exam" value="' + key + '" index="' + types[1] + '"/>'
                + '<span></span></span>');
            var html = '';
            if (xml.type === "image") {
                value = value.replace(/\[(.*)\].*\[\/\1\]/,
                    '<img class="img-responsive" src="../../exam/res/' + xml.type + '/' + xml.id + '.action" />');
                html += '<img class="img-responsive" src="../../exam/res/' + xml.type + '/' + xml.id + '.action" />';
            } else if (xml.type === "audio") {
                value = value.replace(/\[(.*)\].*\[\/\1\]/,
                    '<audio controls="controls">' +
                    '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="audio/mpeg">' +
                    'Your browser does not support the audio element.</audio>');
                html += '<audio controls="controls">' +
                    '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="audio/mpeg">' +
                    'Your browser does not support the audio element.</audio>';
            } else if (xml.type === "video") {
                value = value.replace(/\[(.*)\].*\[\/\1\]/,
                    '<audio controls="controls" width="150" height="160">' +
                    '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="video/ogg">' +
                    'Your browser does not support the audio element.</audio>');
                html += '<audio controls="controls" width="150" height="160">' +
                    '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="video/ogg">' +
                    'Your browser does not support the audio element.</audio>';
            }
            $select.append('<label>' + key + '.' + value + '' +
                '</label>');
            // $select.append('<label>' + key + '.' + value + '' + html +
            //     '</label>');

        } else {
            $select.append('<span class="' + types[2] + '">'
                + '<input type="' + types[1] + '" name="exam" value="' + key + '"/>'
                + '<span></span></span>');
            $select.append('<label>' + key + '.' + value + '' +
                '</label>');
        }
        $('.select-answer').append($select);
    });
    var radios = $('.select-answer input[type=radio]');
    console.log("radio 长度 = " + radios.length)
    for (var i = 0; i < radios.length; i = i + (radios.length / 3)) {
        console.log("       radio  " + radios.length);
        for (var j = i; j < i + (radios.length / 3); j++) {
            radios.eq(j).attr('name', radios.eq(j).attr('name') + i);
            radios.eq(j).attr('index', i);
        }
    }
    // for (var i = 0; i < question.select.length; i++) {
    //
    // }
//加载题目题干
    loadTitle(question);
}
//加载题干
function loadTitle(question) {
    var xml = azyXml(question.content);
    var $select_answer = $('.select-answer');
    if (xml !== null) {//引用了资源
        if (xml.type === "image") {
            console.log('引用了图片');
            $select_answer.eq(0).parent().append('<div class="btn-block mb-30"  style="margin-top: -80px;margin-left: 110px">' +
                '<img src="../../exam/res/' + xml.type + '/' + xml.id + '.action" class="img-responsive"/></div>')
            $select_answer.eq(1).parent().append('<div class="kstp">' +
                '<img src="../../exam/res/' + xml.type + '/' + xml.id + '.action" class="img-responsive"/></div>')
            $select_answer.eq(2).parent().append('<div class="col-sm-4 f-r" style="margin-top: -70px">' +
                '<img src="../../exam/res/' + xml.type + '/' + xml.id + '.action" class="img-responsive"/></div>')
        } else if (xml.type === "audio") {
            console.log('引用了图片audio');
            $select_answer.eq(0).parent().append(' <div class="" >' +
                '<audio controls="controls" style="width: 180px">' +
                '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="audio/mpeg">' +
                'Your browser does not support the video tag.</audio></div>')
            $select_answer.eq(1).parent().append('<div style="margin-left: 650px;margin-top: -150px;margin-bottom: 150px" class="visible-lg">' +
                '<audio controls="controls" style="width: 180px">' +
                '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="audio/mpeg">' +
                'Your browser does not support the video tag.</audio></div>')
            $select_answer.eq(2).parent().append('<div class="mb-30">' +
                '<audio controls="controls" style="width: 180px">' +
                '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="audio/mpeg">' +
                'Your browser does not support the video tag.</audio></div>')
        } else if (xml.type === "video") {
            console.log('video');
            $select_answer.eq(0).parent().append(' <div style="margin-left: 10px" >' +
                '<video width="100" height="110" controls="controls">' +
                '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="video/ogg">' +
                'Your browser does not support the video tag.</video></div>')
            $select_answer.eq(1).parent().append('<div style="margin-left: 660px;margin-top: -200px">' +
                '<video width="100" height="110" controls="controls">' +
                '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="video/ogg">' +
                'Your browser does not support the video tag.</video></div>')
            $select_answer.eq(2).parent().append('<div style="margin-left: 10px">' +
                '<video width="100" height="110" controls="controls">' +
                '<source src="../../exam/res/' + xml.type + '/' + xml.id + '.action" type="video/ogg">' +
                'Your browser does not support the video tag.</video></div>')
        }
    } else {
        $('.title').html('<img src="/resources/img/考试.png" class="img-responsive hidden-xs"/>'
            + question.content + '（' + getType(question.type)[0] + '）');
    }
}
function getType(type) {
    console.info('type = ' + type);
    switch (type) {
        case 1:
            return ["单选", "radio", "radio_btn"];
        case 2:
            return ["多选", "checkbox", "radio_btn1"];
        // case 3:
        //     return ["判断", "radio", "radio_btn"];
        case 4:
            return ["判断", "radio", "radio_btn"];
        default:
            return ["错误类型", "radio", "radio_btn"];
    }
}
function azyXml(text) {
    // /<(.*)>.*<\/\1>/
    var result = text.match(/\[(.*)\].*\[\/\1\]/);
    console.log(result);
    if (result !== null) {
        var res = {};
        res.type = result[1];
        res.id = text.match(/\]([^</]+)\[\//)[1];
        // text = text.replace(/\[(.*)\].*\[\/\1\]/, '');
        // console.log(text);
        console.log(res);
        return res;
    }
    return null;
}
///获取选中的选项
function getSelect() {
    var array = [];
    var $input = $('.radio_btn1>input');
    if ($input.length === 0)
        $input = $('.radio_btn>input');
    console.log($input.length);
    console.log($input);
    for (var i = 0; i < $input.length / 3; i++) {
        if ($input.eq(i).prop('checked'))
            array.push($input.eq(i).val());
    }
    return array;
}
///加载选项同步事件
function loadSelectEvent() {
    var $inputs = $('.radio_btn1>input');
    if ($inputs.length === 0)
        $inputs = $('.radio_btn>input');
    // $inputs = $('input[name=exam]');
    console.log($inputs);
    $inputs.on('change', function () {
        if ($(this).attr('index') === 'radio') {
            //将同一组的设置成相反状态，
            $('.radio_btn1 input[name=' + $(this).attr('name') + ']').prop('checked', !$(this).prop('checked'));
            $('.radio_btn input[name=' + $(this).attr('name') + ']').prop('checked', !$(this).prop('checked'));

            //将同一组的状态同步到其他组
            var $input = $('.radio_btn1 input[name=' + $(this).attr('name') + ']');
            if ($input.length === 0)
                $input = $('.radio_btn input[name=' + $(this).attr('name') + ']');
            for (var i = 0; i < $input.length; i++) {//遍历同一组的所有input
                //遍历不同组的相同位置input
                var $this = $input.eq(i);
                for (var j = i; j < $inputs.length; j += ($inputs.length / 3)) {
                    if (!$inputs.eq(j).is($this)) {
                        $inputs.eq(j).prop('checked', $(this).prop('checked'));
                    }
                }
            }
            // for (var i = 0; i < $inputs.length; i += ($inputs.length / 3)) {
            //     // if (i !== parseInt($(this).attr('index'))) {
            //     if (!$inputs.eq(i).is($(this))) {
            //         $inputs.eq(i).prop('checked', $(this).prop('checked'));
            //     }
            //     // }
            // }
        } else {
            $('.radio_btn>input[value=' + $(this).val() + ']').prop('checked', $(this).prop('checked'));
            $('.radio_btn1>input[value=' + $(this).val() + ']').prop('checked', $(this).prop('checked'));
        }
    });

    for (var i = 0; i < $inputs.length / 3; i++) {
        $inputs.eq(i).trigger('change');
    }
}