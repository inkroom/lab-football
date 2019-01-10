/**
 * Created by 墨盒 on 2017/8/10.
 * 表格数据绑定
 */
function dataTable(data) {
    if (typeof data.data === 'undefined') {
        data.data = {};
    }
    if (typeof data.data.now === 'undefined') {
        // data.data.searchBean = {};
        data.data.now = 1;
    }

    if (typeof data.maxPage === 'undefined')
        data.maxPage = 10;
    $.ajax({
        url: data.url,
        data: data.data,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json;charset=utf-8',
        success: function (result) {
            if (typeof data.before === 'function') {
                if (!data.before(result)) {
                    if (typeof data.after === 'function')
                        data.after();
                    return;
                }
            }
            var rowCount = loadData(result.data[data.key], data.cols, data.id);
            if (rowCount > 0)
                loadPage(data.id, result.data.nowPage, result.data.totalPage);
            else {
                emptyTable(data.id, data.none);
            }
            if (typeof data.after === 'function')
                data.after();
        },
        error: function () {
            if (typeof data.error === 'function')
                data.error();
        }
    });
    ///加载表格行
    var loadData = function (result, cols, table) {
        table = '#' + table;
        $(table).find('tbody').remove();
        var $tbody = $('<tbody></tbody>');
        for (var i = 0; i < result.length; i++) {
            var $tr = $('<tr></tr>');
            $tr.addClass('text-c');
            for (var j = 0; j < cols.length; j++) {
                var $td = $('<td></td>');
                //回调
                console.log(typeof (cols[j].callback));
                if (typeof cols[j].callback === 'function') {
                    $td.html(cols[j].callback(result[i][cols[j].value]));
                } else {
                    $td.html((result[i][cols[j].value]));
                }
                $tr.append($td);
            }
            $tbody.append($tr);
        }
        $(table).append($tbody);
        return result.length;
    };

    function loadPage(table, nowPage, totalPage) {
        table = '#' + table;
        $(table).parent().find('#page').remove();
        var $pageDiv = $('<div id="page" style="margin-left: 15px"></div>');
        if (nowPage > 1)
            $pageDiv.append($("<a href='javascript:void(0);' class='page'><上一页</a>").on('click', function () {
                data.data.now = nowPage - 1;
                dataTable(data);
            }));
        var end = nowPage + (data.maxPage + data.maxPage % 2) / 2;
        for (var i = nowPage - (data.maxPage - data.maxPage % 2) / 2; i < end; i++) {
            if (i === nowPage) {
                $pageDiv.append("<a href='javascript:void(0);' class='page-none'>" + i + "</a>");
            } else {
                if (i <= 0) {
                    end += 1;
                }
                else if (i <= totalPage) {
                    $pageDiv.append($("<a href='javascript:void(0);' class='page'>" + i + "</a>").on('click', function () {
                        data.data.now = i;
                        dataTable(data);
                    }));
                }
            }

        }
        if (nowPage < totalPage)
            $pageDiv.append($("<a href='javascript:void(0);' class='page'>下一页></a>").on('click', function () {
                data.data.now = nowPage + 1;
                dataTable(data);
            }));
        $(table).parent().append($pageDiv);
    }

    var emptyTable = function (table, text) {
        table = '#' + table;
        var $table = $(table);
        $table.find('tbody').remove();
        $table.append($('<tbody>' +
            '<tr class="text-c">' +
            '<td colspan="' + $table.find("tr").find("th").length + '">' + text + '</td>' +
            '</tr>' +
            '</tbody>'))
    }
}


