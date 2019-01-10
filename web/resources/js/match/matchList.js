var num = 1;
var token;
$(document).ready(function () {
    token = $("#token").html().trim();
    $("#home-tab").click(function () {
        num = 1;
        toPage(1);
    });
    $("#profile-tab").click(function () {
        num = 2;
        toPage(1);
    });
    $("#dropdown1-tab").click(function () {
        num = 3;
        toPage(1);
    });
    // 得到存储在浏览器 Local Storage 里的当前选项卡的名字
    var lastTab = localStorage.getItem('matchList');
    var myTab = $('a[href=' + lastTab + ']');
    // 找到以后，显示这个选项卡
    if (lastTab) {
        myTab.tab('show');
        switch (lastTab) {
            case "#home":
                num = 1;
                toPage(1);
                break;
            case "#profile":
                num = 2;
                toPage(1);
                break;
            case "#dropdown1":
                num = 3;
                toPage(1);
                break;
        }
    }
});

function toPage(pn) {
    ajax({
        url: getRootPath() + "/match/listMatch",
        data: {num: num, pn: pn},
        type: "GET",
        success: function (result) {
            token = result.token;
            if (result.status === 0) {
                buildTable(result);
                build_page_nav(result);
            }
            else {
                $("div.pages").empty();
                $("#tbody" + num).empty().append('<td colspan="4" style="font-size: 16px;padding-top: 10px;">暂无赛程</td>');
            }
        }
    })
}

function buildTable(result) {

    var body;
    var url;
    if (num == 1) {
        body = $("#tbody1");
        url = getRootPath() + '/match/turnDetails/?id=';
    }
    else if (num == 2) {
        body = $("#tbody2");
        url = getRootPath() + '/match/turnDetails/?id=';
    }
    else {
        body = $("#tbody3")
        url = getRootPath() + '/match/turnDetailsAdmin/?id=';
    }
    ;
    body.empty();
    $.each(result.data.pageInfo.list, function (index, item) {
        var itemid = item.idMatch;
        var a = $("<a></a>");
        a.attr('href', url + itemid);
        a.append(item.name);
        var itemNameTh = $("<td ></td>").append(a);
        itemNameTh.attr("matchId", itemid);
        if (num == 1) var dateTd = $("<td></td>").append(parseTime(item.applyDeadline));
        else var dateTd = $("<td></td>").append(parseTime(item.startDate));
        var statusTd;
        switch (item.status) {
            case 2 :
                statusTd = $("<td></td>").append("已结束");
                break;
            case 1 :
                statusTd = $("<td></td>").append("正在进行");
                break;
            case 0 :
                statusTd = $("<td></td>").append("正在报名");
                break;
        }
        var otherTd;
        var b;
        if (num == 1) {
            if (item.status == 0) {
                b = $("<a></a>");
                b.attr('href', getRootPath() + "/match/turnDetails?id=" + itemid + "#profile");
                b.append("报名");
                otherTd = $("<td></td>").append(b);
            }
            else otherTd = $("<td></td>").append("不能进行报名");
        }
        else if (num == 2) {
            if (item.exstatus == 0) {
                otherTd = $("<td></td>").append("待审核");
            }
            else if (item.exstatus == 1) {
                otherTd = $("<td></td>").append("已审核")
            }
            else {
                otherTd = $("<td></td>").append("被拒绝参赛")
            }
        }
        else if (num == 3) {
            b = $("<a></a>");
            b.attr('href', url + itemid + "#profile");
            b.append("审核列表");
            otherTd = $("<td></td>").append(b);
        }
        $("<tr></tr>")
            .append(itemNameTh)
            .append(dateTd)
            .append(statusTd)
            .append(otherTd)
            .appendTo(body);
    })
}

//分页条
function build_page_nav(result) {
    $("div.pages").empty();
    if (result.data.pageInfo.pages == 1) {
        return false;
    }
    var ul = $("<ul></ul>").addClass("pagination");
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.data.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            toPage(1);
        });
        prePageLi.click(function () {
            toPage(result.data.pageInfo.pageNum - 1);
        });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
    if (result.data.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled")
    }
    else {
        nextPageLi.click(function () {
            toPage(result.data.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            toPage(result.data.pageInfo.pages);
        });
    }

    ul.append(firstPageLi).append(prePageLi);
    $.each(result.data.pageInfo.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.data.pageInfo.pageNum == item) {
            numLi.addClass("active")
        }
        numLi.click(function () {
            toPage(item)
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("div.pages");
}


function createMatch() {
    if (!checkName()) {
        return false;
    }
    ajax({
        type: "post",//方法类型
        url: getRootPath() + "/match/createMatch",//url
        data: {
            name: $('#inputName').val(),
            level: $('#inputLevel').val(),
            numPlayer: $('#inputNumPlayer').val(),
            startDate: $('#inputTime').val(),
            applyDeadline: $('#inputDeadline').val()
        },
        headers: {token: token},
        // contentType: false,
        // processData: false,
        success: function (result) {
            token = result.token;
            if (result.status === 0) {
                layer.alert("创建成功");
            }
            else if (result.message) {
                layer.alert(result.message, {icon: 2});
            }
        },
        error: function () {
            layer.alert("Fail", {icon: 2});
        }
    });
}