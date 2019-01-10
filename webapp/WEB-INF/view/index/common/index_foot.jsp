<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 4/24/17
  Time: 08:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="footer">
    <div class="brown3">
        <ul class="select">
            <li>
                <select onchange="eval(this.value);" id="link01">
                    <option value="void(0)">---市州教育行政部门---</option>
                    <option ms-for="(link) in linkList01" ms-attr="{value:@headU+link.link_addr+@tailU}"   >{{link.link_name}}</option>
                </select>
            </li>
            <li>
                <select onchange="eval(this.value);">
                    <option value="void(0)">---省内各高校---</option>
                    <option ms-for="(link) in linkList02" ms-attr="{value:@headU+link.link_addr+@tailU}"   >{{link.link_name}}</option>
                </select>
            </li>
            <li>
                <select onchange="eval(this.value);">
                    <option value="void(0)">---直属单位网站---</option>
                    <option ms-for="(link) in linkList03" ms-attr="{value:@headU+link.link_addr+@tailU}"   >{{link.link_name}}</option>
                </select>
            </li>
            <li>
                <select onchange="eval(this.value);">
                    <option value="void(0)">---其他教育网站---</option>
                    <option ms-for="(link) in linkList04" ms-attr="{value:@headU+link.link_addr+@tailU}"    >{{link.link_name}}</option>
                </select>
            </li>
        </ul>
        <div class="last">
            <p><span><a href="javascript:;">联系方式</a>&nbsp;|&nbsp;<a href="javascript:;">使用帮助</a></span>&nbsp;&nbsp;&nbsp;&nbsp;主办：四川省教育厅&nbsp;&nbsp;&nbsp;&nbsp;制作维护：成都东软学院</p>
            <p>投稿邮箱：高等院校:scgxxx@163.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市州区县: scjyxx@163.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基层学校: scpjxx@163.com</p>
        </div>
    </div>
    <div class="orange3"></div>
</div>