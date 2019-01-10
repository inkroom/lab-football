<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>新闻</title>

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row">
        <div class="col-sm-12">

            <div class="ibox">
                <div class="ibox-title">
                    <h5>所有项目</h5>
                    <div class="ibox-tools">
                        <a href="${pageContext.request.contextPath}/news/add.html"
                           class="btn btn-primary btn-xs">创建新文章</a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row m-b-sm m-t-sm">
                        <div class="col-md-1">
                            <button type="button" id="loading-example-btn" class="btn btn-white btn-sm"
                                    onclick="location.reload()">刷新
                            </button>
                        </div>
                        <div class="col-md-11">
                            <div class="input-group">
                                <input type="text" placeholder="请输入新闻标题" class="input-sm form-control" id="searchText"> <span
                                    class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary" onclick="search()"> 搜索</button> </span>
                            </div>
                        </div>
                    </div>

                    <div class="project-list">

                        <table class="table table-hover" id="news">
                            <thead>
                            <tr>
                                <th>发布状态</th>
                                <th>新闻标题</th>
                                <th>标签</th>

                                <th class="project-actions">操作</th>

                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="item" items="${news}">
                                <tr>
                                    <td class="project-status">
												<span class="label ${(item.flag eq 1)?'label-primary':'label-default'}">${(item.flag eq 1)?'已':'未'}发布
                                    </td>
                                    <td class="project-title">
                                        <a href="project_detail.html" target="_blank">${item.title}</a>
                                        <br/>
                                        <small>创建于<fmt:formatDate value="${item.createTime}"
                                                                  pattern="yyyy.MM.dd"/></small>
                                    </td>
                                    <td class="project-completion">
                                        <c:choose>
                                            <c:when test="${item.type eq 1}">综合新闻</c:when>
                                            <c:when test="${item.type eq 2}">科研动态</c:when>
                                            <c:when test="${item.type eq 3}">人才招聘</c:when>
                                        </c:choose>
                                    </td>
                                    <td class="project-actions">
                                        <button class="btn btn-success btn-sm"
                                                onclick="upOrDown(${item.id},${(item.flag eq 1)?0:1},this)">${(item.flag eq 1)?'下架':'发布'}
                                        </button>
                                        <a href="${pageContext.request.contextPath}/news/add.html?id=${item.id}"
                                           class="btn btn-primary btn-sm">编辑 </a>
                                            <%--<a href="projects.html#" class="btn btn-danger btn-sm">删除 </a>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="project-actions">
                            <div class="btn-group" id="pages">
                                <%--<button class="btn btn-white" type="button"><i class="fa fa-chevron-left"></i>--%>
                                <%--</button>--%>
                                <%--<button class="btn btn-white">1</button>--%>
                                <%--<button class="btn btn-white  active">2</button>--%>
                                <%--<button class="btn btn-white">3</button>--%>
                                <%--<button class="btn btn-white">4</button>--%>
                                <%--<button class="btn btn-white">5</button>--%>
                                <%--<button class="btn btn-white">6</button>--%>
                                <%--<button class="btn btn-white">7</button>--%>
                                <%--<button class="btn btn-white" type="button"><i class="fa fa-chevron-right"></i>--%>
                                <%--</button>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/resources/lib/layer/layer.js"></script>
<script>

    var index;

    var nowPage = 0;
    var eachPageCount = 10;
    var row = $('#news tr').length;
    //计算有多少页
    var pageCount = parseInt((row / eachPageCount) + '') + 1;
    //翻页
    window.exchange = function (page) {
        if (page === -1) {//上一页
            if (nowPage === 0)
                return;
            page = nowPage - 1;
        } else if (page === -2) {//下一页
            if (nowPage === pageCount) return;
            page = nowPage + 1;
        }

        for (var i = 1; i < row; i++) {
            var $tr = $('#news tr:eq(' + i + ')');
            $tr.hide();
            if (i < (page + 1) * eachPageCount && i > page * eachPageCount) {
                $tr.show();
            }
        }
        $('#pages button:eq(' + (nowPage + 1) + ')').removeClass('active');
        nowPage = page;
        $('#pages button:eq(' + (nowPage + 1) + ')').addClass('active');
    };

    function upOrDown(id, flag, obj) {
        index = layer.confirm('确认' + ((flag === 1) ? '发布' : '下架') + '该新闻？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            layer.close(index);
            $.ajax({
                url: '${pageContext.request.contextPath}/news/setFlag.action',
                dataType: 'json',
                data: {
                    id: id,
                    flag: flag
                },
                success: function (result) {
                    layer.close(index);
                    if (result.status === 0) {
                        var $this = $(obj);
                        $this.parent().parent().find('span:eq(0)').removeClass((flag === 1 ? 'label-default' : 'label-primary'))
                            .addClass((flag === 1 ? 'label-primary' : 'label-default')).html((flag === 1 ? '已发布' : '未发布'));
                        $this.html(flag === 1 ? '下架' : '发布');
                        $this.removeAttr('onclick');
                        $this.attr('onclick', 'upOrDown(' + id + ',' + ((flag === 1) ? 0 : 1) + ',this)');
                        layer.msg((flag===1?'发布':'下架')+'成功');
                    } else if (result.status === 1) {
                        layer.msg('修改失败');
                    } else if (result.status === 5) {
                        layer.msg('系统错误，请联系管理人员');
                    } else if (result.status === 6) {
                        layer.msg(result.message);
                    }
                },
                error: function () {
                    layer.close(index);
                    layer.msg('网络错误');
                },
                beforeSend: function () {

                    index = layer.load(1);
                }
            })
        }, function () {
        });

    }


    function search() {
        var value =$('#searchText').val();
        if(value.length>0){
            for(var i=1;i<row;i++){
                var $tr = $('#news tr:eq('+i+')');
                $tr.hide();

                if ($tr.find('.project-title>a').eq(0).html().indexOf(value)>-1){
                    $tr.show();
                }
            }
            $('#pages').hide();
        }

    }

    $('#loading-example-btn').click(function () {
        var btn = $(this);
        simpleLoad(btn, true)

        // Ajax example
//                $.ajax().always(function () {
//                    simpleLoad($(this), false)
//                });

        simpleLoad(btn, false)
    });
    $(document).ready(function () {


        //启用静态分页
        //获取有多少行


        //加载按钮
        var pageDiv = $('#pages');
        for (var i = -1; i < pageCount + 1; i++) {
            if (i === -1) {//加载上一页按钮
                pageDiv.append('<button class="btn btn-white" type="button" onclick="exchange(-1)"><i class="fa fa-chevron-left"></i>\n' +
                    '                                </button>');
            } else if (i === (pageCount)) {//加在下一页按钮
                pageDiv.append(' <button class="btn btn-white" type="button" onclick="exchange(-2)"><i class="fa fa-chevron-right"></i>\n' +
                    '                                </button>');
            } else {
                pageDiv.append('<button class="btn btn-white' + ((i === nowPage) ? ' active' : '')
                    + '" onclick="exchange(' + (i) + ')">' + (i + 1) + '</button>');
            }
        }
        //翻当前页
        $('#pages button:eq(1)').trigger('click');
    });

    function simpleLoad(btn, state) {
        if (state) {
            btn.children().addClass('fa-spin');
            btn.contents().last().replaceWith(" Loading");
        } else {
            setTimeout(function () {
                btn.children().removeClass('fa-spin');
                btn.contents().last().replaceWith(" Refresh");
            }, 2000);
        }
    }
</script>


</body>
</html>