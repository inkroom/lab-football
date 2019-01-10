<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 4/24/17
  Time: 09:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content">
    <div id="mainmain" class="content_bg">
        <div id="content_main1">
            <ul>
                <li><a href="javascript:void(0);">政策发布</a></li>
                <li ms-for="(policy) in listPolicy"><a ms-attr="{href: '${base_path}/index/'+policy.id+'/index_third.html'}" target="_blank">{{policy.title}}</a><span>{{policy.create_date}}</span></li>
                <p><a href="${base_path}/index/index_second.html" target="_blank">更多&raquo;</a></p>
            </ul>
        </div>
    </div>
</div>
<div class="pix">
    <ul>
        <li>
            <a href="javascript:;"><img src="${base_path}/resources/img/index/6.jpg">
                <div class="mengban">
                    <img src="${base_path}/resources/img/index/mengban.png" alt=""/>
                    <p>精彩瞬间</p>
                </div>
            </a>
        </li>
        <li>
            <a href="javascript:;"><img src="${base_path}/resources/img/index/6.jpg">
                <div class="mengban">
                    <img src="${base_path}/resources/img/index/mengban.png" alt=""/>
                    <p>精彩瞬间</p>
                </div>
            </a>
        </li>
        <li>
            <a href="javascript:;"><img src="${base_path}/resources/img/index/6.jpg">
                <div class="mengban">
                    <img src="${base_path}/resources/img/index/mengban.png" alt=""/>
                    <p>精彩瞬间</p>
                </div>
            </a>
        </li>
    </ul>
</div>