<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/respond.min.js"></script>--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/css/H-ui.min.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/css/H-ui.admin.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/lib/Hui-iconfont/1.0.8/iconfont.css" />--%>
    <%--<link rel="stylesheet" type="text/css" href="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/css/style.css" />--%>
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/js/echarts.common.min.js" ></script>--%>
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/jquery/1.9.1/jquery.min.js"></script>--%>
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/lib/layer/2.4/layer.js"></script>--%>
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui/js/H-ui.js"></script>--%>
    <%--<script type="text/javascript" src="../../../resources/js/TeacherAndSchoolAndMechanism/static/h-ui.admin/js/H-ui.admin.page.js"></script>--%>
    <title>四川省中小学生艺术测评系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="index.html">四川省中小学生艺术测评系统</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">四川省中小学生艺术测评系统</span> <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">机构端<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="#">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe705;</i> 账号管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="修改密码.html" title="修改密码">修改密码</a>
                    <li><a href="重置下级密码.html" title="重置下级密码">重置下级密码</a>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="数据分析.html" title="数据分析">数据分析</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe62b;</i> 教师认证<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="教师认证.html" title="教师认证">教师认证</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="首页.html">首页</a> <span class="c-gray en">&gt;</span> 数据分析 <span class="c-gray en">&gt;</span> 数据分析 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="Hui-article">
        <div class="Hui-article">
            <h2 class="text-c">四川各地区美术考试情况</h2>
            <div id="main" style="width: 80%;height:400px;margin: 0 auto"></div>
            <script>
                $(function () {
                    var myChart = echarts.init(document.getElementById('main'));
                    var nmb0=parseInt($("#nmb0").html());
                    var nmb1=parseInt($("#nmb1").html());
                    var nmb2=parseInt($("#nmb2").html());
                    var nmb3=parseInt($("#nmb3").html());
                    var nmb4=parseInt($("#nmb4").html());
                    var nmb5=parseInt($("#nmb5").html());
                    var nmb6=parseInt($("#nmb6").html());
                    var nmb7=parseInt($("#nmb7").html());
                    var nmb8=parseInt($("#nmb8").html());
                    var nmb9=parseInt($("#nmb9").html());
                    var nmb10=parseInt($("#nmb10").html());
                    var nmb11=parseInt($("#nmb11").html());
                    var nmb12=parseInt($("#nmb12").html());
                    var nmb13=parseInt($("#nmb13").html());
                    var nmb14=parseInt($("#nmb14").html());
                    var nmb15=parseInt($("#nmb15").html());
                    var nmb16=parseInt($("#nmb16").html());
                    var nmb17=parseInt($("#nmb17").html());
                    var nmb18=parseInt($("#nmb18").html());
                    var nmb19=parseInt($("#nmb19").html());
                    var nmb20=parseInt($("#nmb20").html());
                    var nmb21=parseInt($("#nmb21").html());
                    var nmb22=parseInt($("#nmb22").html());
                    var nmb23=parseInt($("#nmb23").html());
                    var nmb24=parseInt($("#nmb24").html());
                    var nmb25=parseInt($("#nmb25").html());
                    var nmb26=parseInt($("#nmb26").html());
                    var nmb27=parseInt($("#nmb27").html());
                    var nmb28=parseInt($("#nmb28").html());
                    var nmb29=parseInt($("#nmb29").html());
                    var option = {
                        title: {
                            text: ['详细分析']
                        },
                        tooltip: {},
                        legend: {
                            data: ['平均分', '最高分', '最低分', '通过率', '优秀成绩占比']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            name: '区县',
                            data: ['成都市', '绵阳市', '南充市', '达州市', '内江市', '阆中市']
                        },
                        yAxis: [{
                            name: '分数/百分比',
                            min: '0',
                            max: '100',
                            type: 'value'
                        }],
                        series: [
                            {
                                name: '平均分',
                                type: 'bar',
                                data: [nmb0, nmb5, nmb10, nmb15, nmb20, nmb25]
                            },
                            {
                                name: '最高分',
                                type: 'bar',
                                data: [nmb1, nmb6, nmb11, nmb16, nmb21, nmb26]
                            },
                            {
                                name: '最低分',
                                type: 'bar',
                                data: [nmb2, nmb7, nmb12, nmb17, nmb22, nmb27]
                            },
                            {
                                name: '通过率',
                                type: 'bar',
                                data: [nmb3, nmb8, nmb13, nmb18, nmb23, nmb28]
                            },
                            {
                                name: '优秀成绩占比',
                                type: 'bar',
                                data: [nmb4, nmb9, nmb14, nmb19, nmb24, nmb29]
                            }
                        ]
                    };
                    myChart.setOption(option);
                })
            </script>
            <article class="cl pd-20">
                <div class="mt-10">
                    <table class="table table-border table-bordered table-hover table-bg">
                        <thead>
                        <tr class="text-c">
                            <th>学校/教育局名称</th>
                            <th>地区</th>
                            <th>科目</th>
                            <th>平均成绩</th>
                            <th>最高分</th>
                            <th>最低分</th>
                            <th>通过率</th>
                            <th>优秀成绩比率</th>
                            <th>参与总人数</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="text-c">
                            <td>成都市教育局</td>
                            <td>成都市</td>
                            <td>美术</td>
                            <td id="nmb0">67</td>
                            <td id="nmb1">89</td>
                            <td id="nmb2">89</td>
                            <td id="nmb3">80%</td>
                            <td id="nmb4">25%</td>
                            <td>4561156156</td>
                        </tr>
                        <tr class="text-c">
                            <td>绵阳市教育局</td>
                            <td>绵阳市</td>
                            <td>美术</td>
                            <td id="nmb5">67</td>
                            <td id="nmb6">96</td>
                            <td id="nmb7">96</td>
                            <td id="nmb8">85%</td>
                            <td id="nmb9">25%</td>
                            <td>4561156156</td>
                        </tr>
                        <tr class="text-c">
                            <td>南充市教育局</td>
                            <td>成都市</td>
                            <td>美术</td>
                            <td id="nmb10">67</td>
                            <td id="nmb11">89</td>
                            <td id="nmb12">89</td>
                            <td id="nmb13">80%</td>
                            <td id="nmb14">25%</td>
                            <td>4561156156</td>
                        </tr>
                        <tr class="text-c">
                            <td>达州市教育局</td>
                            <td>南充市</td>
                            <td>美术</td>
                            <td id="nmb15">72</td>
                            <td id="nmb16">96</td>
                            <td id="nmb17">96</td>
                            <td id="nmb18">82%</td>
                            <td id="nmb19">28%</td>
                            <td>4561156156</td>
                        </tr>
                        <tr class="text-c">
                            <td>内江市教育局</td>
                            <td>成都市</td>
                            <td>美术</td>
                            <td  id="nmb20">67</td>
                            <td  id="nmb21">89</td>
                            <td  id="nmb22">89</td>
                            <td  id="nmb23">80%</td>
                            <td  id="nmb24">25%</td>
                            <td>4561156156</td>
                        </tr>
                        <tr class="text-c">
                            <td>阆中市教育局</td>
                            <td>成都市</td>
                            <td>美术</td>
                            <td id="nmb25">67</td>
                            <td id="nmb26">89</td>
                            <td id="nmb27">89</td>
                            <td id="nmb28">80%</td>
                            <td id="nmb29">25%</td>
                            <td>4561156156</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pt-10 text-c">
                        <button class="my-btn" onclick="">首页</button>
                        <button class="my-btn" onclick="">&lt;&lt;</button>
                        <button class="my-btn" onclick="">1</button>
                        <button class="my-btn" onclick="">2</button>
                        <button class="my-btn" onclick="">&gt;&gt;</button>
                        <button class="my-btn" onclick="">尾页</button>
                        <button class="my-btn" onclick="">尾页</button>
                    </div>
                </div>
            </article>
        </div>
    </div>
</section>
</body>
</html>