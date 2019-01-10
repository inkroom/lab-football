<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>${schoolName }</title>
    <!--[if lt IE 9]>
    <script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="${basePath }/resources_new/favi/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${basePath }/resources_new/css/footer.css">
    <link href="${basePath }/resources_new/css/PrimerySecond.css" type="text/css" rel="stylesheet">
    <link href="${basePath}/resources/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="${basePath }/resources/lib/js/jquery-2.1.4.js"></script>
    <script src="${basePath}/resources/bootstrap/bootstrap.min.js"></script>
    <style>
        body {
            background: url(${basePath }/resources_new/img/bg1-2.png) no-repeat center;
        }

        .content > a {
            margin-left: 75px;
        }
    </style>

    <style>
        #main {
            height: 1060px;
            width: 450px;
            overflow: auto;
            margin-left: 150px;
            margin-top: 26px;
            background: white;

        }

        .dd {
            padding-left: 15%;
            padding-right: 15%;
        }

        .table td {
            text-align: center;

        }

        .title_2 {
            background-color: #8D8D8D;
            text-align: center;
            font-size: large;
        }

        .title_3 {
            text-align: center;
            color: #149bdf;
            font-size: medium;
        }

        .td_1 {
            width: 65%;
        }

        .td_2 {
            width: 35%;
            text-align: center;
        }
    </style>
</head>
<body>
<!--头部-->
<div id="bg" style="background:url(${basePath }/resources_new/img/bg1-2.png) no-repeat center;">
    <div class="content">
        <div class="logo"><img src="${basePath }/resources_new/img/logo18.png"><span>${schoolName }</span></div>
        <div class="nav">
            <jsp:include page="../common/title.jsp"/>
        </div>
    </div>
    <section id="main">
        <table class="table table-bordered table-hover">
            <tr>
                <th class="title_2" colspan="2">标题</th>
            </tr>

            <tr>
                <td class="td_1" colspan="2">${found.title_in }</td>
            </tr>

            <tr>
                <th class="title_2" colspan="2">收入(万元)</th>
            </tr>
            <tr>
                <th class="title_3" colspan="2">财政资金支持</th>
            </tr>
            <tr>
                <td class="td_1">省级资金金额</td>
                <td class="td_2">${found.content1 }</td>
            </tr>
            <tr>
                <td class="td_1">市级资金金额</td>
                <td class="td_2">${found.content2 }</td>
            </tr>
            <tr>
                <td class="td_1">县级资金金额</td>
                <td class="td_2">${found.content3 }</td>
            </tr>


            <tr>
                <th class="title_3" colspan="2">学校自有资金</th>
            </tr>
            <tr>
                <td class="td_1">净余资金金额</td>
                <td class="td_2">${found.content4 }</td>
            <tr>
                <td class="td_1">生均费用</td>
                <td class="td_2">${found.content5 }</td>
            </tr>

            <tr>
                <th class="title_3" colspan="2">接受社会捐助</th>
            </tr>
            <tr>
                <td class="td_1">捐助金额</td>
                <td class="td_2">${found.content6 }</td>
            </tr>
            <tr>
                <th class="title_2" colspan="2">总计(万元)</th>
            </tr>
            <tr>
                <td class="td_1">收入总计
                </th>
                <td class="td_2">${found.content7 }</td>
            </tr>
        </table>
        <table class="table table-bordered table-hover">

            <tr>
                <th class="title_2" colspan="2">标题</th>
            </tr>

            <tr>
                <td class="td_1" colspan="2">${found.title_out }</td>
            </tr>

            <tr>
                <th class="title_2" colspan="2">支出项(万元)</th>
            </tr>
            <tr>
                <th class="title_3" colspan="2">用于聘请国内外该水平教师/教练员教学演讲费用</th>
            </tr>
            <tr>
                <td class="td_1">用于研制特色学校建设标准及管理办法研发费用</td>
                <td class="td_2">${found.content8 }</td>
            </tr>
            <tr>
                <td class="td_1">用于编写师资系列培训教材费用</td>
                <td class="td_2">${found.content9 }</td>
            </tr>
            <tr>
                <td class="td_1">用于开展校园足球改革交流活动费用</td>
                <td class="td_2">${found.content10 }</td>
            </tr>


            <tr>
                <th class="title_3" colspan="2">校园足球特色学校和试点区建设支出</th>
            </tr>
            <tr>
                <td class="td_1">用于特色学校添置运动器材及设施费用</td>
                <td class="td_2">${found.content11 }</td>
            </tr>
            <tr>
                <td class="td_1">用于建立校园足球队费用</td>
                <td class="td_2">${found.content12 }</td>
            </tr>
            <tr>
                <td class="td_1">用于购置运动装备费用</td>
                <td class="td_2">${found.content13 }</td>
            </tr>
            <tr>
                <td class="td_1">用于聘请足球教练员费用</td>
                <td class="td_2">${found.content14 }</td>
            </tr>
            <tr>
                <td class="td_1">用于参加各级比赛费用</td>
                <td class="td_2">${found.content15 }</td>
            </tr>
            <tr>
                <td class="td_1">用于购买参赛球员意外伤害险费用</td>
                <td class="td_2">${found.content16 }</td>
            </tr>


            <tr>
                <th class="title_3" colspan="2">各级校园足球联赛支出</th>
            </tr>
            <tr>
                <td class="td_1">用于校园足球冬/夏令营费用</td>
                <td class="td_2">${found.content17 }</td>
            </tr>

            <tr>
                <th class="title_3" colspan="2">校园足球师资队伍，管理队伍建设支出</th>
            </tr>
            <tr>
                <td class="td_1">用于聘请国内外该水平教师/教练员教学演讲费用</td>
                <td class="td_2">${found.content18 }</td>
            </tr>


            <tr>
                <th class="title_3" colspan="2">用于校园足球宣传与文化建设和课题研究</th>
            </tr>
            <tr>
                <td class="td_1">用于校园足球科普和知识宣传费用</td>
                <td class="td_2">${found.content19 }</td>
            </tr>
            <tr>
                <td class="td_1">用于校园足球文化建设及氛围营造费用</td>
                <td class="td_2">${found.content20 }</td>
            </tr>
            <tr>
                <td class="td_1">用于专家指导团队和联盟建设费用</td>
                <td class="td_2">${found.content21 }</td>
            </tr>
            <tr>
                <td class="td_1">用于校园足球相关课题研究费用</td>
                <td class="td_2">${found.content22 }</td>
            </tr>


            <tr>
                <th class="title_3" colspan="2">经验交流与集中调研</th>
            </tr>
            <tr>
                <td class="td_1">组织开展校园足球工作经验交流和调研费用</td>
                <td class="td_2">${found.content23 }</td>
            </tr>
            <tr>
                <td class="td_1">支出总计</td>
                <td class="td_2">${found.content24 }</td>
            </tr>
        </table>
    </section>
    <!--*********************************脚部开始**************************************-->
    <div class="footer">
        <jsp:include page="../common/index_foot.jsp"/>
    </div>
    <script type="text/javascript" src="${basePath }/resources_new/js/primery_second.js"></script>
    <script type="text/javascript">

        window.onload = function () {
            var asideArray = ["${basePath }/${param.get('schoolUrl')}/news/8", "${basePath }/${param.get('schoolUrl')}/news/9"];
            loadJS(asideArray);
        }
    </script>
</body>
</html>