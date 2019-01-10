<%@ page import="cn.edu.nsu.lib.bean.teacher.PrizeEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.nsu.lib.bean.teacher.ScoreEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>学生详情页</title>

    <link  rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.css">
    <script type="application/javascript" src="${base_path}/resources/common/jquery.min.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/layer/2.4/layer.js"></script>
    <script type="application/javascript" src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <style>
        html{
            margin: 10px;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-2 column"></div>
        <div class="col-md-8 column">
            <h1 class="text-center"><span>${stu.name}<small>来自${stu.lab_name}</small></span>
            </h1>
            <h1><small>基本信息</small></h1>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th>学号</th>
                    <td id ="stu_id">${stu.id}</td>
                    <th>姓名</th>
                    <td id="stu_name">${stu.name}</td>
                </tr>
                <tr>
                    <th>系部</th>
                    <td id="stu_department">${stu.department}</td>
                    <th>专业</th>
                    <td id="stu_major">${stu.major}</td>
                </tr>
                <tr>
                    <th>年级</th>
                    <td id="stu_grade">${stu.grade}</td>
                    <th>班级</th>
                    <td id="stu_class">${stu.stuClass}</td>
                </tr>
                <tr>
                    <th>电话</th>
                    <td id="stu_tel">${stu.tel}</td>
                    <th>性别</th>
                    <td id="stu_gender">${stu.gender}</td>
                </tr>

                <tr>
                    <th>加入实验室时间</th>
                    <td id="stu_time">${stu.time}</td>
                    <th>获奖次数</th>
                    <td id="stu_price_num">${stu.prize_sum}</td>
                </tr>
                <tr>
                    <th>辅导员</th>
                    <td id="instructor">${stu.instructor}</td>
                    <th title="统计本月情况">签到情况</th>
                    <td id="stu_fre">${stu.frequency}</td>
                </tr>
                </tbody>
            </table>
            <h1>
                <small>获奖信息</small>
            </h1>

            <table class="table table-bordered">
                <tbody id="prize_list">
                <%
                    List<PrizeEntity> prizeList = (List<PrizeEntity>) request.getAttribute("prizeList");
                    if(prizeList.size()==0){
                %>
                <tr>
                    <th>无<th>
                </tr>
                 <%
                    }else {
                        for (int i = 0; i < prizeList.size(); i++) {
                        PrizeEntity prize = prizeList.get(i);
                        request.setAttribute("prize",prize);
                        request.setAttribute("order",i+1);
                %>
                <tr class="bg-success">
                    <th>
                        序号
                    </th>
                    <td id="prize_order">${order}</td>
                    <th>
                        级别
                    </th>
                    <td id="prize_region">${prize.region}</td>
                </tr>
                <tr>
                    <th>
                        奖项名称
                    </th>
                    <td colspan="3"><a href="${empty(prize.url)?'javascript:void(0);':prize.url}" id="prize_name">${prize.prize_name}</a></td>
                </tr>
                <tr><th>
                    等级
                </th>
                    <td id="prize_rank">${prize.rank}</td>
                    <th>
                        类别
                    </th>
                    <td id="prize_category">${prize.category}</td>
                </tr><tr>
                    <th>
                        获奖时间
                    </th>
                    <td id="prize_time">${prize.time}</td>
                    <th>
                        审核结果
                    </th>
                    <td id="prize_isChecked">${prize.is_checked}</td>
                </tr>
                <tr>
                    <th >
                        组委会
                    </th>
                    <td  id="prize_committee">
                        ${prize.committee}
                    </td>
                    <th >
                        指导老师
                    </th>
                    <td  >
                        ${prize.adviser}
                    </td>
                </tr>
                <%
                    }}
                %>
                </tbody>

            </table>
            <h1><small>成绩列表</small></h1>
            <table class="table table-bordered ">
                <thead>
                <tr>
                    <th>
                        学期
                    </th>
                    <th>
                        课程
                    </th>
                    <th>
                        成绩
                    </th>

                </tr>
                </thead>
                <tbody>
                <%
                    List<ScoreEntity> scoreList = (List<ScoreEntity>) request.getAttribute("scoreList");
                    for (int i = 0; i < scoreList.size(); i++) {
                        request.setAttribute("score",scoreList.get(i));
                %>
                <tr>
                    <td>${score.term}</td>
                    <td><a href="">${score.course}</a></td>
                    <td>${score.score}</td>

                </tr>
                <% }
                    if(scoreList.size()==0){
                %>
                <tr><td colspan="3">暂无</td></tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-md-2 column">
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){

    });
</script>
</body>
</html>