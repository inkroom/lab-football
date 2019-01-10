<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.nsu.lib.bean.admin.db.DbPrize" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>获奖审核</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/animate.css">
    <link rel="stylesheet" href="${base_path}/resources/common/nav_css/style.css">
    <link href="${base_path}/resources/common/plugins/css-dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${base_path}/resources/common/style.css" rel="stylesheet">

    <script type="text/javascript" src="${base_path}/resources/common/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/js/index.js"></script>
    <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script src="${base_path}/resources/common/plugins/layer/layer.min.js"></script>
    <script src="${base_path}/resources/common/plugins/jeditable/jquery.jeditable.js"></script>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <p class="text-center text-info" style="font-size: 19px;"><%=request.getAttribute("lab_name")%>
                        获奖审核</p>
                </div>
                <div class="ibox-content">
                    <table class="table table-striped table-bordered table-hover table-condensed"
                           style="word-break: break-all;" id="editable">
                        <tbody>
                        <thead>
                        <tr class="text-center" style="color: #336699;font-size: 17px">
                            <td>所属人学号</td>
                            <td>所属人名字</td>
                            <td>获奖名字</td>
                            <td>学科竞赛</td>
                            <td>获奖等级</td>
                            <td>奖项官网</td>
                            <td>获奖时间</td>
                            <td>所属实验室</td>
                            <td>委员会</td>
                            <td>指导老师</td>
                            <td>审核结果</td>
                        </tr>
                        </thead>
                        <%
                            ArrayList<DbPrize> prizes = (ArrayList<DbPrize>) request.getAttribute("prizes");
                            for (DbPrize prize : prizes) {
                                if (!prize.isIs_checked()) {
                                    request.setAttribute("prize", prize);
                        %>
                        <tr class="text-center">
                            <td>${prize.owner}
                            </td>
                            <td>${prize.owner_name}
                            </td>
                            <td>${prize.prize_name}
                            </td>
                            <td>${prize.category}
                            </td>
                            <td>${prize.rank}
                            </td>
                            <td>${prize.url}
                            </td>
                            <td>${prize.time}
                            </td>
                            <td>${prize.lab_name}
                            </td>
                            <td>${prize.committee}
                            </td>
                            <td>${prize.adviser}
                            </td>
                            <td>
                                <input type="button" name="pass_prize_btn"
                                       class="btn btn-success btn-xs"
                                       prize_id="<%=prize.getId()%>"
                                       value="通过审核">
                                <input type="button" name="nopass_prize_btn"
                                       class="btn btn-danger btn-xs"
                                       prize_id="<%=prize.getId()%>"
                                       data-toggle="modal" value="不通过">
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <p class="text-center text-info" style="font-size: 19px;"><%=request.getAttribute("lab_name")%>
                        成员获奖信息</p>
                </div>
                <div class="ibox-content">
                    <table class="table table-striped table-bordered table-hover table-condensed"
                           style="word-break: break-all;" id="">
                        <tbody>
                        <tr class="text-center" style="color: #336699;font-size: 17px">
                            <td>所属人学号</td>
                            <td>所属人名字</td>
                            <td>获奖名字</td>
                            <td>学科竞赛</td>
                            <td>获奖等级</td>
                            <td>奖项官网</td>
                            <td>获奖时间</td>
                            <td>所属实验室</td>
                            <td>委员会</td>
                            <td>指导老师</td>
                            <td>审核结果</td>
                        </tr>
                        <%
                            for (DbPrize prize : prizes) {
                                if (prize.isIs_checked()) {
                                    request.setAttribute("check_prize", prize);
                        %>
                        <tr class="text-center">
                            <td>${check_prize.owner}
                            </td>
                            <td>${check_prize.owner_name}
                            </td>
                            <td>${check_prize.prize_name}
                            </td>
                            <td>${check_prize.category}
                            </td>
                            <td>${check_prize.rank}
                            </td>
                            <td>${check_prize.url}
                            </td>
                            <td>${check_prize.time}
                            </td>
                            <td>${check_prize.lab_name}
                            </td>
                            <td>${check_prize.committee}
                            </td>
                            <td>${check_prize.adviser}
                            </td>
                            <td>已通过 <input type="button" name="delete_prize_btn"
                                           class="btn btn-danger btn-xs" type="button" data-toggle="modal"
                                           prize_id="<%=prize.getId()%>"
                                           value="删除">
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<!-- 全局js -->

<script>
    window.onload = function () {
        // 初始页面的传递table中tr的id 进行ajax请求刷新table
        //        JQuery通过名字查找节点的时候一定不能在[]前面加上空格 例如：$("input [name='name']")
        $("input[name='pass_prize_btn']").each(function () {
            var $passbtn = $(this);
            $passbtn.click(function () {
                ajax({
                    url: "${base_path}/PrizeAdministrator/Passcheck",
                    type: "post",
                    async: false,
                    dataType: "json",
                    data: {
                        "prize_id": $passbtn.attr("prize_id")
                    },
                    success: function () {
//                        alert("通过的奖项id为：" + $passbtn.attr("prize_id"));
                        window.location.reload();
                    },
                    error: function () {
//                        alert("ajax失败");
                    }
                });
            });
        });

        //        JQuery通过名字查找节点的时候一定不能在[]前面加上空格 例如：$("input [name='name']")
        $("input[name='nopass_prize_btn']").each(function () {
            var $no_pass_btn = $(this);
            $no_pass_btn.click(function () {
                ajax({
                    url: "${base_path}/PrizeAdministrator/Nopass",
                    type: "post",
                    async: false,
                    dataType: "json",
                    data: {
                        "prize_id": $no_pass_btn.attr("prize_id")
                    },
                    success: function () {
//                        alert("没有通过的奖项id为：" + $no_pass_btn.attr("prize_id"));
                        window.location.reload();
                    },
                    error: function () {
//                        alert("ajax失败");
                    }
                });
            });
        });


        //        JQuery通过名字查找节点的时候一定不能在[]前面加上空格 例如：$("input [name='name']")
        $("input[name='delete_prize_btn']").each(function () {
            var $delete_pass_btn = $(this);
            $delete_pass_btn.click(function () {
                ajax({
                    url: "${base_path}/PrizeAdministrator/Deletewinner",
                    type: "post",
                    async: false,
                    dataType: "json",
                    data: {
                        "prize_id": $delete_pass_btn.attr("prize_id")
                    },
                    success: function () {
//                        alert("删除奖项id为：" + $delete_pass_btn.attr("prize_id"));
                        window.location.reload();
                    },
                    error: function () {
//                        alert("ajax失败");
                    }
                });
            });
        });

    }

</script>


</html>
