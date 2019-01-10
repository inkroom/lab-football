<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="top" id="topb" style="z-index: 100">
    <img src="${pageContext.request.contextPath}/resources/img/topLfw.jpg" style="float: left;">
    <img src="${pageContext.request.contextPath}/resources/img/logo3.png" style="float: left;padding:20px 0px;margin-left:-16px ;">
    <img src="${pageContext.request.contextPath}/resources/img/topRtw.jpg" style="float: right;">
    <div class="top1">
        <div class="menu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/home.html" class="topcolor">网站首页</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/html/aboutUs.html" class="topcolor">关于我们</a>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/aboutUs.html?index=0#0">本部介绍</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/aboutUs.html?index=1#1">研究院介绍</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/aboutUs.html?index=2#2">组织架构</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ProdAndTech/proandte.html?set=${te_set}" class="topcolor">技术产品</a>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/ProdAndTech/proandte.html?set=${te_set}">技术</a>
                            <ul>
                             <c:forEach items="${settech}" var="tech">
                                <li>
                                    <a href="${pageContext.request.contextPath}/ProdAndTech/proandte.html?set=${tech.ts_id}">${tech.ts_name}</a>
                                </li>
                             </c:forEach>
                            </ul>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/ProdAndTech/proandte.html?type=2&set=${pr_set}">产品</a>
                            <ul>
                              <c:forEach items="${setprod}" var="prod">
                                <li>
                                    <a href="${pageContext.request.contextPath}/ProdAndTech/proandte.html?type=2&set=${prod.ps_id}">${prod.ps_name}</a>
                                </li>
                              </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="topcolor">合作单位</a>
                    <ul>
                      <c:forEach items="${colist}" var="coper">
                        <li>
                            <a href="${pageContext.request.contextPath}/cooperator/${coper.id}.html" style="cursor: default">${coper.name}</a>
                        </li>
                      </c:forEach>

                    </ul>

                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/html/OSdata.html" class="topcolor">开源数据</a>
                    <ul>
                        <%// TODO: 18-3-5 导航栏点击之后不会刷新页面，可以考虑之后使用参数 %>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/OSdata.html?index=0#0" onclick="location.replace(this.href)">光学</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/OSdata.html?index=1#1">电子学</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/OSdata.html?index=2#2">机械</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/news/news.html" class="topcolor">新闻中心</a>

                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/html/partyAndUnion.html" class="topcolor">党群工会</a>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/partyAndUnion.html?index=0#0">党建</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/partyAndUnion.html?index=1#1">工会</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/html/recruit.html" class="topcolor">招贤纳士</a>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/recruit.html?index=0#0">硬件</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/recruit.html?index=1#1">软件</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/recruit.html?index=2#2">工程</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/recruit.html?index=3#3">其他</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/html/goInto.html" class="topcolor">走进大江东</a>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/goInto.html?index=0#0">人才引进政策</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/html/goInto.html?index=1#1">区域规划</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/contactUs/contactUs.html" class="topcolor">联系我们</a>
                </li>
            </ul>
        </div>
    </div>

</div>
<%--搜索按钮--%>
<a href="javascript:;"><img src="${pageContext.request.contextPath}/resources/img/search1.png" class="searchIco" data-toggle="modal" data-target="#myModal6"></a>
<%--搜索模态框--%>
<div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm pull-right">
        <div class="modal-content">
            <div class="modal-body">
                <div class="input-group">
                    <input type="text" placeholder="搜索" name="search" id="searchText" class="form-control btn-sm">
                    <div class="input-group-btn">
                        <button class="btn btn-primary" type="button" onclick="if($('#searchText').val().length>0){location.href = '${pageContext.request.contextPath}/search.html?page=0&key='+$('#searchText').val()}">
                            搜索
                        </button>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>