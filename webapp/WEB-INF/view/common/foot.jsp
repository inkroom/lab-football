<%--
  User: 墨盒
  Date: 2017/9/13
  Time: 10:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="foot1">
    <input type="hidden" id="_csrf" value="${_csrf.getToken()}"/>
</div>
<div class="foot2">
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
                                    <li class="mt-30"><a href="../web/filedownload.html" class="c-white"><span
                                            class="c-white f-24">资料下载</span></a></li>
                                    <li class="mt-30"><a href="../web/myfile.html" class="c-white"><span
                                            class="c-white f-24">档案下载</span></a></li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4>个人中心</h4>
                                <ul class="list-unstyled">
                                    <li class="mt-30"><a href="../web/mycertificate.html" class="c-white"><span
                                            class="c-white f-24">我的证书</span></a></li>
                                    <li class="mt-30"><a href="../web/upporject.html" class="c-white"><span
                                            class="c-white f-24">课外学习</span></a></li>
                                    <li class="mt-30"><a href="../web/upporject.html" class="c-white"><span
                                            class="c-white f-24">实践活动</span></a></li>
                                    <li class="mt-30"><a href="../web/upporject.html" class="c-white"><span
                                            class="c-white f-24">特长专长</span></a></li>
                                </ul>
                            </div>
                            <div class="col-xs-3">
                                <h4><a href="scoreboard.scoreboard.jsp"><span class="c-white"> 积分榜单</span></a></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <div id="datepicker" class="calendar visible-lg"></div>

    <div class="row footer-bottom col-sm-12  col-lg-12">
        <hr/>
        <ul class="list-inline text-center">
            <li class="text-c">版权所有@四川省中小学生艺术测试系统</li>
        </ul>
    </div>
</div>
<div class="class_add">
    <article class="page-container" style="display: none;">
        <h2 style="background-color: #aee1fb" class="text-c">班级信息</h2>
        <form class="mt-50" id="form-admin-add" method="">
            <div>
                <label><span class="c-red">*</span>班级号：</label>
                <input type="text" class="input-text" value="" placeholder="" id="classId" name="" style="width: auto">
            </div>
            <div class="mt-30">
                <label><span class="c-red">*</span>口令：</label>&nbsp;&nbsp;&nbsp;
                <input type="password" class="input-text" autocomplete="off" value="" placeholder="" id="key" name=""
                       style="width: auto">
            </div>
            <div>
                <div class="mt-30 text-c">
                    <input class="btn btn-primary radius" type="button" id="joinClassButton"
                           value="&nbsp;&nbsp;申请加入&nbsp;&nbsp;">
                </div>
            </div>
        </form>

    </article>
</div>

