<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${type}练习</title>
    <%--<link rel="stylesheet" href="${base_path}/lib/h-ui/css/H-ui.css"/>--%>
    <link rel="stylesheet" href="${base_path}/lib/h-ui/css/H-ui.min.css"/>
    <%--<link rel="stylesheet" href="${base_path}/lib/Hui-iconfont/1.0.8/iconfont.css"/>--%>
    <link rel="stylesheet" href="${base_path}/lib/Hui-iconfont/1.0.8/iconfont.min.css"/>
    <link rel="stylesheet" href="${base_path}/lib/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${base_path}/lib/h-ui/icheck.css"/>
    <link rel="stylesheet" href="${base_path}/lib/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" href="${base_path}/resources/css/student/reset.css">
    <link rel="stylesheet" href="${base_path}/resources/css/student/style.css">
    <link rel="stylesheet" href="${base_path}/resources/css/student/practice.css"/>


</head>
<body>
<div class="row cl">
    <div class="col-xs-12 text-c c-white f-16 visible-xs" style=" padding-right: 0;
            padding-left: 0;">
        <div class="header2">四川省中小学生艺术素质测评系统</div>
    </div>
</div>
<div class="container visible-xs">
    <div id="nav-a">
        <ul>
            <li><a href="personal.html" style="background-color: #FF633C;border-radius: 5px 0 0 5px;">首页</a></li>
            <li><a href="exam_introduce.html">考试类型</a>
                <ul>
                    <li><a href="exam.html">美术考试</a></li>
                    <li><a href="exam.html">音乐考试</a></li>
                </ul>
            </li>
            <li><a href="practice_one.html">练习中心</a>
                <ul>
                    <li><a href="practice_one.html">美术练习</a></li>
                    <li><a href="practice_one.html">音乐练习</a></li>
                </ul>
            </li>
            <li><a href="jfb.html">积分榜单</a></li>
            <li><a href="personal.html">个人中心</a>
                <ul>
                    <li><a href="exam_inform.html">考试通知</a></li>
                    <li><a href="login.html">切换账户</a></li>
                    <li><a href="login.html">退出登录</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="row cl bg-fff mt-50 ml-10 mr-10" style="background-color: #F0F8FF">
        <div class="col-xs-12 text-c">
            <nav class="breadcrumb" style="background-color: transparent">
                <a href="personal.html"><i class="Hui-iconfont f-20" style="color: #8fc0f3;">&#xe625;</i></a>
                <a href="exam_introduce.html">考试类型</a>
                <span class="c-999 en">></span>练习
                <span class="c-999 en">></span>
                <span class="c-666"><span id="subject">${requestScope.get("type")}</span>练习</span>
            </nav>
        </div>
        <div class="row cl">
            <div class="col-xs-12">
                <div class="formControls col-xs-4 text-c mt-10">
        				<span class="select-box size-S radius">
        					<select class="type select c-green">
                                <option selected="" value="1">单选</option>
                                <option value="2">多选</option>
                                <option value="4">判断</option>
                            </select>
        				</span>
                </div>
                <div class="formControls col-xs-4 text-c mt-10">
        				<span class="select-box size-S radius">
        					<select class="difficulty select c-green">
                                <option selected="">难度</option>
                                <option value="1">初级</option>
                                <option value="2">中级</option>
                                <option value="3">高级</option>
                            </select>
        				</span>
                </div>
                <div class="formControls col-xs-4 text-c mt-10 ">
        				<span class="select-box size-S radius">
        					<select class="point select c-green" size="1">
                                <option selected="">默认考点</option>
                                <option value="舞蹈认知">舞蹈认知</option>
                                <option value="2">音阶</option>
                                <option value="3">歌曲</option>
                            </select>
        				</span>
                </div>
            </div>
        </div>
        <div class="col-xs-8 ml-20 visible-xs">
            <div class="col-xs-12">
                <p class="f-23 c-666 mt-20 title">6/900 表现蒙古音乐常用的乐器是 。（多选）</p>
            </div>
            <div class="col-xs-10 f-17 lh-30 visible-sm visible-xs">
                <div class="c-black select-answer">
                    <div>
                           <span class="radio_btn1">
		                        <input type="checkbox" name="A" autocomplete="off"/>
		                        <span></span>
		                    </span>
                        <label>A.二胡</label>
                    </div>
                    <div>
                        <span class="radio_btn1">
		                    <input type="checkbox" name="B" autocomplete="off">
                            <span></span>
                        </span>
                        <label>B.冬不拉</label>
                    </div>
                    <div>
                        <span class="radio_btn1">
		                      <input type="checkbox" name="C" autocomplete="off">
		                      <span></span>
                        </span>
                        <label>C.马头琴</label>
                    </div>
                    <div>
                        <span class="radio_btn1">
		                      <input type="checkbox" name="D" autocomplete="off">
                              <span></span>
		                </span>
                        <label>D.中阮</label>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 mb-10">
                <%--<a href="#">--%>
                <button class="btn btn-primary size-S" name="prev">上一题</button>
                <%--</a>--%>
                <%--<a href="ks2.html">--%>
                <button class="btn btn-primary size-S" name="next">下一题</button>
                <%--</a>--%>
            </div>
            <div class="col-xs-12 mb-10">
                <%--<a href="exam_end.html">--%>
                <button class="btn btn-success size-M" name="submit">提交</button>
                <%--</a>--%>
                <%--<a href="submit_fail.html">--%>
                <button class="btn btn-warning size-M" name="exit">退出</button>
                <%--</a>--%>
            </div>
            <div class="conc" style="margin-left: 20px;margin-bottom: 20px;">
                <div>
                    <a href="javascript:void(0);" name="showAnswer" class="btn btn-secondary-outline radius">查看解析</a>
                </div>
                <div class="answer" style="display: none;">
                        	<span class="f-10 c-666" style="line-height: 8px;">
                                告平台渠道指联盟采购一些小媒体和流量， 再卖给广告主。 听起来跟CPA联盟差不多， 但其实有两点不同：
                                一是这类联盟一般是原来做SEM的一批大媒体，比CPA联盟有信誉， 例如百度联盟、admob、广点通等；
                                二是这类联盟可以让广告主按照一些属性进行半精准的投放。 例如选择城市、媒体类别、用户性别等等。
                            </span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="c-white text-c f-24 hidden-xs">
    <div class="header1">四川省中小学生艺术素质测评系统</div>
</div>
<div class="container hidden-xs" id="c1">
    <div class="row cl">
        <div class="col-xs-1 col-md-5 col-sm-6">
            <img src="${base_path}/resources/img/sun.png" alt="" class="img-responsive"/>
        </div>
        <div class="col-xs-1 col-md-5 col-sm-6 mt-50 hidden-xs">
            <div id="nav">
                <ul>
                    <li><a href="personal.html" style="background-color: #FF633C;border-radius: 10px 0 0 10px;">首页</a>
                    </li>
                    <li><a href="exam_introduce.html">考试类型</a>
                        <ul>
                            <li><a href="exam.html">美术考试</a></li>
                            <li><a href="exam.html">音乐考试</a></li>
                        </ul>
                    </li>
                    <li><a href="practice_one.html">练习中心</a>
                        <ul>
                            <li><a href="practice_one.html">美术练习</a></li>
                            <li><a href="practice_one.html">音乐练习</a></li>
                        </ul>
                    </li>
                    <li><a href="jfb.html">积分榜单</a></li>
                    <li><a href="personal.html">我的空间</a></li>
                    <li><a href="personal.html">个人中心</a>
                        <ul>
                            <li><a href="exam_inform.html">考试通知</a></li>
                            <li><a href="login.html">切换账户</a></li>
                            <li><a href="login.html">退出登录</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container hidden-xs">
    <div class="row cl mt-50 mb-50">
        <div class="col-md-10 col-sm-8 col-xs-10" id="c2">
            <nav class="breadcrumb" style="background-color: transparent">
                <a href="personal.html"><i class="Hui-iconfont f-20" style="color: #8fc0f3;">&#xe625;</i></a>
                <a href="exam_introduce.html">考试类型</a>
                <span class="c-999 en">></span>练习
                <span class="c-999 en">></span>
                <span class="c-666">${type}练习</span>
            </nav>
            <div class="row cl">
                <div class="formControls col-xs-8 col-sm-2 mt-10">
        				<span class="select-box size-S radius">
        					<select class="type select c-green">
                                <option selected="" value="1">单选</option>
                                <option value="2">多选</option>
                                <option value="4">判断</option>
                            </select>
        				</span>
                </div>
                <div class="formControls col-xs-8 col-sm-2 mt-10">
        				<span class="select-box size-S radius">
        					<select class="difficulty select c-green">
                                <option selected="">难度</option>
                                <option value="1">初级</option>
                                <option value="2">中级</option>
                                <option value="3">高级</option>
                            </select>
        				</span>
                </div>
                <div class="formControls col-xs-8 col-sm-2 mt-10">
        				<span class="select-box size-S radius">
        					<select class="point select c-green" size="1">
                                <option selected="">默认考点</option>
                                <option value="舞蹈认知">舞蹈认知</option>
                                <option value="2">音阶</option>
                                <option value="3">歌曲</option>
                            </select>
        				</span>
                </div>
            </div>
            <div class="row cl">
                <div class="ml-30 visible-lg visible-md">
                    <p class="f-26 mt-20 visible-lg visible-md title">
                        <img src="${base_path}/resources/img/考试.png" class="img-responsive hidden-xs"/>
                        6/900 表现蒙古音乐常用的乐器是 。（多选）</p>
                </div>
                <div class=" visible-lg visible-md">
                    <div class="kstimi ml-50 mb-50">
                        <div class="c-999 select-answer">
                            <div>
                               <span class="radio_btn1">
                                    <input type="checkbox" name="A" autocomplete="off"/>
                                    <span></span>
                                </span>
                                <label>A.二胡</label>
                            </div>
                            <div>
                            <span class="radio_btn1">
                                <input type="checkbox" name="B" autocomplete="off"/>
                                <span></span>
                            </span>
                                <label>B.冬不拉</label>
                            </div>
                            <div>
                            <span class="radio_btn1">
                                  <input type="checkbox" name="C" autocomplete="off"/>
                                  <span></span>
                            </span>
                                <label>C.马头琴</label>
                            </div>
                            <div>
                            <span class="radio_btn1">
                                  <input type="checkbox" name="D" autocomplete="off">
                                  <span></span>
                            </span>
                                <label>D.中阮</label>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="row cl mb-50">
                            <%--<a href="javascript:void (0);" id="prev">--%>
                            <button name="prev" class="b2 btn btn-primary radius ml-40">上一题</button>
                            <%--</a>--%>
                            <%--<a href="javascript:void (0);" id="next">--%>
                            <button name="next" class="b2 btn btn-primary radius ml-10">下一题</button>
                            <%--</a>--%>
                            <button class="b2 btn btn-success radius" style="margin-left: 180px;" name="submit"><img
                                    src="${base_path}/resources/img/提交.png"/>提交
                            </button>
                            <button class="b2 btn btn-warning radius ml-10" name="exit"><img
                                    src="${base_path}/resources/img/退出.png"/>退出
                            </button>
                        </div>
                    </div>
                    <div class="conc" style="margin-left: 550px;margin-top: -80px;margin-bottom: 50px;">
                        <div>
                            <a href="javascript:void(0);" name="showAnswer"
                               class="btn btn-secondary-outline radius">查看解析</a>
                        </div>
                        <div class="answer" style="display: none;">
                        	<span class="f-16 c-666" style="line-height: 25px;">
                                告平台渠道指联盟采购一些小媒体和流量， 再卖给广告主。 听起来跟CPA联盟差不多， 但其实有两点不同：
                                一是这类联盟一般是原来做SEM的一批大媒体，比CPA联盟有信誉， 例如百度联盟、admob、广点通等；
                                二是这类联盟可以让广告主按照一些属性进行半精准的投放。 例如选择城市、媒体类别、用户性别等等。
                            </span>
                        </div>
                    </div>
                </div>

                <div class="visible-sm">
                    <div class="ml-30" style="width: 250px">
                        <p class="f-23 mt-20 title">
                            6/900 表现蒙古音乐常用的乐器是 。（多选）</p>
                    </div>
                    <div class="f-14 col-sm-12 mt-10 ml-20 lh-24 mb-30 select-answer">
                        <div>
                            <span class="radio_btn1">
		                                <input type="checkbox" name="A" autocomplete="off"/>
		                                 <span></span>
		                            </span>
                            <label>A.二胡</label>
                        </div>
                        <div>
                             <span class="radio_btn1">
		                                <input type="checkbox" name="B" autocomplete="off">
		                                <span></span>
		                            </span>
                            <label>B.冬不拉</label>
                        </div>
                        <div>
                           <span class="radio_btn1">
		                                <input type="checkbox" name="C" autocomplete="off">
		                                <span></span>
		                            </span>
                            <label>C.马头琴</label>
                        </div>
                        <div>
                            <span class="radio_btn1">
		                                <input type="checkbox" name="D" autocomplete="off">
		                                <span></span>
		                            </span>
                            <label>D.中阮</label>
                        </div>

                    </div>
                    <div class="col-xs-12 mb-10">
                        <%--<a href="#">--%>
                        <button class="btn btn-primary size-S" name="prev">上一题</button>
                        <%--</a>--%>
                        <%--<a href="ks2.html">--%>
                        <button class="btn btn-primary size-S" name="next">下一题</button>
                        <%--</a>--%>
                    </div>
                    <div class="col-xs-12 mb-10">
                        <%--<a href="exam_end.html">--%>
                        <button class="btn btn-success size-M" name="submit">提交</button>
                        <%--</a>--%>
                        <%--<a href="submit_fail.html">--%>
                        <button class="btn btn-warning size-M" name="exit">退出</button>
                        <%--</a>--%>
                    </div>
                    <div class="col-sm-7" style="margin-left: 310px;margin-top:-280px;">
                        <div>
                            <a href="javascript:void(0);" name="showAnswer"
                               class="btn btn-secondary-outline radius">查看解析</a>
                        </div>
                        <div class="answer" style="display: none;width: 210px">
                        	<span class="f-10 c-666" style="line-height: 8px;">
                                告平台渠道指联盟采购一些小媒体和流量， 再卖给广告主。 听起来跟CPA联盟差不多， 但其实有两点不同：
                                一是这类联盟一般是原来做SEM的一批大媒体，比CPA联盟有信誉， 例如百度联盟、admob、广点通等；
                                二是这类联盟可以让广告主按照一些属性进行半精准的投放。 例如选择城市、媒体类别、用户性别等等。
                            </span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
<%--</div>--%>
<%--</div>--%>
<div class="foot1">
    <input type="hidden" id="_csrf" value="${_csrf.getToken()}"/>
</div>
<div class="foot2 visible-lg">
    <div style="width: 100%;height: 30px;">
        <footer class="footer ">
            <div class="container">
                <div class="row footer-top">
                    <div class="col-sm-6  col-lg-6 col-lg-offset-1 f-28 c-white">
                        <div class="row about">
                            <div class="col-xs-3">
                                <h4>关于</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">音乐考试</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">美术考试</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">音乐练习</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">美术练习</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4>下载中心</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">资料下载</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">档案下载</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4>个人中心</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">我的证书</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">课外学习</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">实践活动</span></a>
                                    </li>
                                    <li class="mt-30"><a href="#" class="c-white"><span class="c-white f-24">特长专长</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4>积分榜单</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <div id="datepicker" class="calendar"></div>

    <div class="row footer-bottom col-sm-12  col-lg-12 visible-lg">
        <hr class="mt-15"/>
        <ul class="list-inline text-center mt-15">
            <li class="text-c">版权所有@四川省中小学生艺术测试系统</li>
        </ul>
    </div>
</div>

<script src="${base_path}/lib/jquery/1.9.1/jquery.min.js"></script>
<%--<script src="${base_path}/lib/jquery/1.9.1/jquery.js"></script>--%>
<%--<script src="${base_path}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>--%>
<script src="${base_path}/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script src="${base_path}/lib/h-ui/js/H-ui.js"></script>
<script src="${base_path}/lib/layer/2.4/layer.js"></script>

<script src="${base_path}/resources/js/student/jquery-ui.js"></script>
<script src="${base_path}/resources/js/student/index.js"></script>
<script src="${base_path}/resources/js/student/practice.js"></script>

</body>
</html>
