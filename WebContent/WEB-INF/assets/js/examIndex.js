$(function () {
    // init();
    $('#pre').on("click", function () {
        pre();
    });
    $('#next').on("click", function () {
        next();
    });

    $(document).on('keydown', function (e) {
        if (e.keyCode === 116) {//f5刷新事件
            parent.layer.confirm('该操作会导致状态丢失，你确定要刷新该页面吗？', {
                btn: ['刷新', '不刷新'], // 按钮
                shade: 0.7
                // 不显示遮罩
            }, function () {// 点了刷新按钮
                window.location.reload();
                parent.layer.closeAll(null);// 没有这句无法关闭
            }, function () {// 点了检查按钮
            });
            return false;
        }
        // alert(e.keyCode);
    });

});

var endTime; // 考试结束时间，毫秒数
var nowTime;// 现在的时间，毫秒，初始数据来自后台，并按1000毫秒自增

var totalQueNum = 0;// 题目总数
var nowQueId = -1;// 当前题目id
var nowQueNum = -1;// 当前题目序列，从1开始
var swiQueId = -1;//
// var swiQueNum = 1;// ����
/**
 * 设置为不能考试，并显示弹出框
 */
function disableExam() {
    // 菜单点击
    // 移除之前的事件，否则会累加
    $('.J_menuItem').unbind('click').on('click', function () {
        // if($(this).attr('href')=='javascript:void(0);'){//交卷按钮不注册事件
        showEndWindow();
        return false;
    });
    // 菜单点击
    // $('#J_menuItem').on("click", function() {
    // showEndWindow();
    // });
    // 替换上一题下一题按钮事件
    $('#pre').unbind('click').on('click', function () {
        showEndWindow();
    });
    $('#next').unbind('click').on('click', function () {
        showEndWindow();
    });
    // document.getElementById('pre').onclick = function () {
    //     showEndWindow();
    // };
    // document.getElementById('next').onclick = function () {
    //     showEndWindow();
    // };
    showEndWindow();
    $('#buttonGroup').hide();
}

/* 初始化 */
function init(isStart) {
    if (!isStart) {// 不能考试
        // showWindow('endWindow');
        disableExam();
    } else {// 可以考试
        // showWindow('startWindow');
        showStartWindow(function () {
            // loadThing();
            $.ajax({
                url: "initExam",
                type: "get",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data.status) {
                        initList(data);// 初始化列表
                        // 注册事件
                        loadThing();
                        // alert("开始初始化");
                        document.getElementById('join').children[0].disabled = false;// 将参加考试按钮设置为可以用
                        // swiQueNum = 1;
                        // nowQueNum = swiQueNum;
                    }
                },
                error: function () {
                    // alert("初始化网络错误！");
                    toastr.error('网络错误！请重试！', '初始化失败');
                },
                complete: function () {
                    toastr.info('正在初始化！', '初始化');
                    // alert("正在初始化");
                }
            });
        });

    }

}
/**
 * 显示结束窗口
 */
function showEndWindow() {
    var div_end = document.createElement('div');
    div_end.id = "endWindow";
    div_end.innerHTML = "<div class=\"end\"><h1>考试已结束！或您已交卷！</h1></div>";
    document.body.appendChild(div_end);
    document.getElementById('buttonGroup').style.display = "none";

}

function showStartWindow(callback) {
    var div_start = document.createElement('div');
    div_start.id = "startWindow";
    {
        var div_con = document.createElement('div');
        div_con.className = "con";
        var str = "<h1>考试说明</h1>"
            + "<ol>"
            + "<li>禁止携带手机等电子通讯设备进入考场，若有发现即视为作弊处理。</li>"
            + "<li>考生有关于考试的问题可以举手示意监考老师，不得交头接耳、左顾右盼。</li>"
            + "<li>考生进入试室后必须保持安静，不准随便说话，不准随意离位走动，不准吸烟。要严格遵守考试纪律，不准夹带、旁窥、抄袭；不准冒名顶替。</li>"
            + "<li>对犯有上述违纪行为或舞弊行为者，视其情节轻重，分别给予警告、扣分、该科试卷作废、当次考试各科试卷作废、推迟毕业、通报批评等处罚。</li>"
            + "</ol>";
        div_con.innerHTML = str;

    }
    div_start.appendChild(div_con);

    div_start.innerHTML += "<div class=\"join\" id=\"join\"><button class=\"btn btn-w-m btn-primary btn-lg\" disabled=\"true\">参加考试</button></div>";
    document.body.appendChild(div_start);
    $(div_start).find('button').on('click', function () {
        // if (!$(this).is(':disabled')) {// 当按钮的disable属性为false是可以点击
            nowQueNum = 1;
            // 请求第一道题
            swiQuestion(-1, swiQueId, "", 1);
            $('#buttonGroup').show();
            $('#startWindow').remove();
        // }
    });
    callback();
}

/**
 * 上一道题按钮事件
 */
function pre() {
    if (nowQueNum > 1) {
        var id = findId(-1);
        if (id !== -1) {
            swiQuestion(nowQueId, id, getAnswer(), nowQueNum - 1);
        }
    } else {
        toastr.info("已经是第一道题", "");
        // alert("已经是第一道题");
    }

}
/**
 * 下一道题按钮事件
 */
function next() {
    if (nowQueNum < totalQueNum) {
        // alert("��ǰ��=" + nowQueNum + " �ܹ���=" + totalQueNum);
        var id = findId(1);
        if (id !== -1) {
            var answer = getAnswer();
            // if (answer != "")
            swiQuestion(nowQueId, id, answer, nowQueNum + 1);
            // else
            // toastr.error('请选择答案', '网络错误！请重试！');/
        }
    } else {
        swiQuestion(nowQueId, -1, getAnswer(), nowQueNum);
        finish('您已做完最后一道题，是否交卷？');
    }
}
/**
 * 结束考试，交卷，成功进行相应操作
 *
 * @param text
 *            {string} 显示的内容
 */
function finish(text) {
    parent.layer.confirm(text, {
        btn: ['交卷', '我还是在检查检查'], // 按钮
        shade: 0.7
        // 不显示遮罩
    }, function () {// 点了交卷按钮
        $.ajax({
            url: "endExam",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.status) {
                    toastr.success('您已交卷，考试结束!', '交卷成功');
                    disableExam();
                    // parent.layer.msg('的确很重要', {icon: 1});
                } else {
                    toastr.error('交卷失败，请重试！', '交卷失败');
                }
            },
            error: function () {
                toastr.error('网络错误！请重试！', '交卷失败');
            }
        });
        // parent.layer.msg('的确很重要', {icon: 1});
        parent.layer.closeAll(null);// 没有这句无法关闭
    }, function () {// 点了检查按钮
        // parent.layer.msg('奇葩么么哒', {shift: 6});
    });

}

/**
 * 根据目前的题目次序，查找下一道题的id
 *
 * @param {int}
 *            flag 标志查找上一道，还是下一道题 flag 为-1 则找前一道题，+1 则为下一道题
 * @returns {int} 找到的题目id
 */
function findId(flag) {
    var topLi = document.getElementById("side-menu").children;
    // alert("������"+topLi.length);
    for (var i = 2; i < topLi.length; i++) {
        var middleUls = topLi[i].children[1];
        for (var j = 0; j < middleUls.children.length; j++) {
            var num = parseInt(middleUls.children[j].children[0]
                .getAttribute("num"));
            if (num === (nowQueNum + flag)) {
                // alert(middleUls.children[j].children[0].getAttribute("queId")+"��ID");
                // var id =
                // middleUls.children[j].children[0].getAttribute("queId");
                // alert("�ҵ�����Ŀid��" + id + " num="
                // + middleUls.children[j].children[0].getAttribute("num")+"
                // "+middleUls.children[j].children[0].innerHTML);

                return parseInt(middleUls.children[j].children[0]
                    .getAttribute("queId"));
                // return middleUls.children[j].children[0].queId;
            }
            // alert("��ʱ num="+num+" j="+j+" i="+i);
        }
        // alert("��ʱ num="+num+" j="+j+" i="+i);
    }
    // alert("没找到id" + " 当前数量为=" + nowQueNum + " " + totalQueNum);
    return -1;
}
/**
 * 切换题目
 *
 * @param nowQueId
 *            目前这道题的id
 * @param swiId
 *            下一道题的id
 * @param swiQueNu
 *      当前题目序号
 * @param answer
 *            选择的答案，多选以@@分隔
 */
function swiQuestion(nowQueId, swiId, answer, swiQueNu) {
    console.log("答案是" + answer);
    $.ajax({
        url: "switchQue",
        type: "post",
        dataType: "json",
        data: {
            "nowQueId": nowQueId,
            "swiQueId": swiId,
            "answer": answer
        },
        success: function (data) {
            if (data.status) {
                nowQueNum = swiQueNu;
                if (swiId === -1) {
                    // disableExam();
                    return;
                }
                nowQueId = data.queId;
                loadQues(data);
                // $('#buttonGroup').show();
                // $('#question').show();
            } else {
                // $('#buttonGroup').hide();
                // $('#question').hide();
                toastr.error("题目加载失败，请重试！", '');
            }
        },
        error: function () {
            toastr.error("网络错误，请重试！", '');
        }
    });
}
/**
 *
 * @param data
 *            json数据
 */
function loadQues(data) {
    var $que_div = $('#question');
    if (data.queType === "多选题" || data.queType === "单选题" || data.queType === "判断题") {
        $que_div.html('');

        var queType = (data.queType === "多选题" ? "checkbox" : "radio");
        //加载头部，即题干
        var $title = $('<div class="title"></div>');

        $title.append("<p><span>" + nowQueNum
            + "、</span><span>[" + data.queType + "]&nbsp;</span>"
            + data.queStem + "</p>");
        var $ul = $('<ul></ul>');

        //加载选项
        $.each(data.queOptions, function (index, content) {
            if (content !== "")
                $ul.append('<li><label><input type="' + queType + '" value="' + content + '" name="' + nowQueNum + '" />&nbsp;' + index + '、' + content + '</label></li>');
        });
        if (data.queKeys !== "") {
            //加载答案
            $.each(data.queKeys, function (index, content) {
                if (content !== "")
                    $ul.find('li>input[value=' + content + ']').prop('checked', true);
            });
        }


        $que_div.append($title);
        $que_div.append($ul);

    } else if (data.queType === "解答题") {
        $que_div.html('');
        //加载头部，即题干
        var $title = $('<div class="title"></div>');

        $title.append("<p><span>" + nowQueNum
            + "、</span><span>[" + data.queType + "]&nbsp;</span>"
            + data.queStem + "</p>");
        var $textarea = $('<textarea style="margin-left: 3%"></textarea>');
        if (data.queKeys !== "") {
            $.each(data.queKeys, function (index, content) {
                if (content !== "")
                    $textarea.val($textarea.val() + content);
            });
        }
        $que_div.append($title);
        $que_div.append($textarea);

    } else if (data.queType === "填空题") {
        $que_div.html('');
        //加载头部，即题干
        var $title = $('<div class="title"></div>');
        var sys = data.queStem.replace(new RegExp("##", "gm"), '<input type="text"/>');//替换输入框

        $title.append("<p><span>" + nowQueNum
            + "、</span><span>[" + data.queType + "]&nbsp;</span>"
            + sys + "</p>");
        if (data.queKeys !== "") {
            //填充答案
            $.each(data.queKeys, function (index, content) {
                console.log(index + '   ' + content);
                $title.find('input[type=text]').eq(index).val(content);
            });
        }
        $que_div.append($title);
    }
    // var que_div = document.getElementById("question");
    // var HTMLCode = "";
    // HTMLCode += "<div class=\"title\"><p><span>" + nowQueNum
    //     + "、</span><span>[" + data.queType + "]&nbsp;</span>"
    //     + data.queStem + "</p></div>";
    // HTMLCode += "<ul>";
    // {
    //     var keys = data.queKeys.split("##");
    //     $.each(data.queOptions, function (name, value) {
    //         if (keys.length == 1 && keys[0] == "**") {// 考生没有答案
    //             HTMLCode += "<li><label><input type=\"" + queType
    //                 + "\" name=\"option\" value=\"" + value
    //                 + "\" checked=\"true\" />&nbsp;" + name + "." + value
    //                 + "</label></div>";
    //             return;
    //         }
    //         for (var i = 0; i < keys.length; i++) {
    //             if (keys[i] == value) {
    //                 HTMLCode += "<li><label><input type=\"" + queType
    //                     + "\" name=\"option\" value=\"" + value
    //                     + "\" checked=\"true\" />&nbsp;" + name + "."
    //                     + value + "</label></div>";
    //                 return;
    //             }
    //         }
    //         HTMLCode += "<li><label><input type=\"" + queType
    //             + "\" name=\"option\" value=\"" + value + "\" />&nbsp;"
    //             + name + "." + value + "</label></div>";
    //     });
    //     // for (var i = 0;; i++) {
    //     // if (typeof (data.queOptions[i]) == 'undefined') {
    //     // break;
    //     // }
    //     // HTMLCode += "<li><label><input type=\"" + queType
    //     // + "\" name=\"option\" value=\"" + data.queOptions[i].value
    //     // + "\">&nbsp;" + data.queOptions[i].key + "."
    //     // + data.queOptions[i].value + "</label></li>";
    //     // }
    // }
    // HTMLCode += "</ul>";
    // HTMLCode += "</div>";

    // 答案
    // que_div.innerHTML = HTMLCode;
    nowQueId = data.queId;
}

function initList(data) {
    var side = document.getElementById("side-menu");
    var HTMLCode = "";
    var num = 1;
    // 加载左边列表
    for (var i = 0; i < data.ques.length; i++) {
        // if (typeof (data.ques[i]) == 'undefined') {
        //     break;
        // }
        // side.innerHTML += "";
        HTMLCode += "<li>";
        HTMLCode += "<a href=\"javascript:void(0);\"><i class=\"fa fa fa-bar-chart-o\"></i><span class=\"nav-label\">"
            + data.ques[i].type
            + "</span><span class=\"fa arrow\"></span></a>";
        {
            HTMLCode += "<ul class=\"nav nav-second-level\">";
            $.each(data.ques[i].summaries, function (index, content) {
                HTMLCode += "<li><a class=\"J_menuItem\" href=\"javascript:void(0);\" queId=\""
                    + content.id
                    + "\" num=\""
                    + num
                    + "\">第&nbsp;" + num + "&nbsp;题</a></li>";
                if (num === 1) {
                    swiQueId = content.id;
                }
                num++;
            });
            // for (var j = 0; ; j++) {
            //     if (typeof (data.ques[i].summaries[j]) === 'undefined') {
            //         break;
            //     }
            //     HTMLCode += "<li><a class=\"J_menuItem\" href=\"javascript:void(0);\" queId=\""
            //         + data.ques[i].summaries[j].id
            //         + "\" num=\""
            //         + num
            //         + "\">第&nbsp;" + num + "&nbsp;题</a></li>";
            //     if (num === 1)
            //         swiQueId = data.ques[i].summaries[j].id;
            //     num++;
            // }
            HTMLCode += "</ul>";
        }
        HTMLCode += "</li>";
    }
    side.innerHTML += HTMLCode;
    // 加载头部标题
    document.getElementById("studentID").innerHTML = "学号："
        + data.student.id;
    document.getElementById("examName").innerHTML = data.exam.title;
    // 加载倒计时
    endTime = data.exam.endTime;
    nowTime = data.exam.nowTime;
    setInterval(changeTime, 1000);

    totalQueNum = num - 1;// 当所有题目加载完毕之后，num依然自加
}
/**
 * 倒计时执行的函数
 */
function changeTime() {
    // var NowTime = new Date();
    var t = endTime - nowTime;
    nowTime += 1000;
    var d = Math.floor(t / 1000 / 60 / 60 / 24);
    var h = Math.floor(t / 1000 / 60 / 60 % 24);
    var m = Math.floor(t / 1000 / 60 % 60);
    var s = Math.floor(t / 1000 % 60);

    if (d > 0) {
        document.getElementById("t_d").innerHTML = d + "天";
    }
    document.getElementById("t_h").innerHTML = h + "时";
    document.getElementById("t_m").innerHTML = m + "分";
    document.getElementById("t_s").innerHTML = s + "秒";

    if (d === 0 && h === 0 && m <= 15 && s === 0) {
        toastr.warring('离考试结束还有 15 分钟！', '考试即将结束');
    }

    // 考试结束
    // d <= 0 && h <= 0 && m <= 0 && s <= 0 ||
    if (nowTime >= endTime) {
        finish()
        // jsSubmit("POST", "EndExam", "");
    }
}

function loadThing() {
    // MetsiMenu
    $('#side-menu').metisMenu();

    // 打开右侧边栏
    $('.right-sidebar-toggle').click(function () {
        $('#right-sidebar').toggleClass('sidebar-open');
    });

    // 固定菜单栏
    $(function () {
        $('.sidebar-collapse').slimScroll({
            height: '100%',
            railOpacity: 0.9,
            alwaysVisible: false
        });
    });

    // 菜单切换
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    // 侧边栏高度
    function fix_height() {
        var heightWithoutNavbar = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
    }

    fix_height();

    $(window).bind("load resize click scroll", function () {
        if (!$("body").hasClass('body-small')) {
            fix_height();
        }
    });

    // 侧边栏滚动
    $(window).scroll(function () {
        if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
            $('#right-sidebar').addClass('sidebar-top');
        } else {
            $('#right-sidebar').removeClass('sidebar-top');
        }
    });

    $('.full-height-scroll').slimScroll({
        height: '100%'
    });

    $('#side-menu>li').click(function () {
        if ($('body').hasClass('mini-navbar')) {
            NavToggle();
        }
    });
    $('#side-menu>li li a').click(function () {
        if ($(window).width() < 769) {
            NavToggle();
        }
    });

    $('.nav-close').click(NavToggle);

    // ios浏览器兼容性处理
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        $('#content-main').css('overflow-y', 'auto');
    }
    // 菜单点击
    $(".J_menuItem").on('click', function () {
        // if($(this).attr('href')=='javascript:void(0);'){//交卷按钮不注册事件
        var queId = $(this).attr('queId');
        var queNum = $(this).attr('num');
        // alert('结束前');
        if (typeof queId === 'undefined' || typeof queNum === 'undefined') {// 交卷按钮没有这两个属性
            queId = -1;
            queNum = nowQueNum;
        } else
            queNum = parseInt(queNum);
        // swiQuetion(queId, queNum, getAnswer());
        swiQuestion(nowQueId, queId, getAnswer(), queNum);
        // alert(queId + " =" + typeof (queId));
        if (queId === -1 || typeof( queNum) === 'undefined')
            finish('确认交卷？');
        return false;
    });
}

/**
 * 获取选中内容 多选答案以@@分隔
 *
 * @returns {String} 答案，多选以@@分隔
 */
function getAnswer() {
    var $input = $('#question input');
    var answer = "";
    for (var i = 0; i < $input.length; i++) {
        if ($input.eq(i).attr('type') !== 'text') {//单选或多选
            if ($input.eq(i).prop('checked')) {
                if (answer !== "") {
                    answer += "@@";
                }
                answer += $input.eq(i).val();
            }
        } else {//填空
            if (answer !== "") {
                answer += "@@";
            }
            answer += $input.eq(i).val();
        }
    }
    if ($input.length === 0) {//解答题
        answer = $('#question textarea:eq(0)').val();
    }
    return answer;
    // var obj = document.getElementById("question").getElementsByTagName("input");
    // var selectInfo = "";
    // for (var i = 0; i < obj.length; i++) {
    //     if (obj[i].checked) {
    //         if (selectInfo != "") {
    //             selectInfo += "@@";
    //         }
    //         selectInfo += obj[i].value;
    //     }
    // }
    // // alert(selectInfo);
    // return selectInfo;
}

// 自定义js

// 公共配置

$(window).bind("load resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('mini-navbar');
        $('.navbar-static-side').fadeIn();
    }
});

function NavToggle() {
    $('.navbar-minimalize').trigger('click');
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar')) {
        $('#side-menu').hide();
        setTimeout(function () {
            $('#side-menu').fadeIn(500);
        }, 100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(function () {
            $('#side-menu').fadeIn(500);
        }, 300);
    } else {
        $('#side-menu').removeAttr('style');
    }
}
