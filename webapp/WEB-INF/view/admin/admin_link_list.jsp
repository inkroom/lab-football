<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/23/17
  Time: 08:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>链接管理</title>
        <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
        <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/jquery/jquery_page/jquery.page.css" />
    </head>
    <body>
        <div class="container-fluid mt-10" id = "list" ms-controller="list">
            <select class="select mb-20 mt-10" size="1" name="linkType" id="linkType" style="height: 30px;">
                <option value="1">市政府教育行政部门</option>
                <option value="2">省内各高校</option>
                <option value="3">直属单位网站</option>
                <option value="4">其它教育网站</option>
            </select>
            <table class="table table-border table-bordered table-bg" id="tableElement">
                <thead class="text-c">
                <th>编号</th>
                <th>序号</th>
                <th>标题</th>
                <th>链接</th>
                <th>类型</th>
                <th>修改时间</th>
                <th colspan="3">操作${_csrf.token }</th>
                </thead>
                <tbody class="text-c" id="imgtbody">
                <tr ms-for="($index,link) in @list">
                    <td>{{$index + 1}}</td>
                    <td>{{$index + 1 + @oderNum}}</td>
                    <td>{{link.link_name}}</td>
                    <td>{{link.link_addr}}</td>
                    <td>
                        <p ms-if="link.link_type == '1'">市政府教育行政部门</p>
                        <p ms-if="link.link_type == '2'">省内各高校</p>
                        <p ms-if="link.link_type == '3'">直属单位网站</p>
                        <p ms-if="link.link_type == '4'">其它教育网站</p>
                    </td>
                    <td>{{link.oper_date}}</td>
                    <td> <button id="topbotton" class="btn btn-success radius " ms-click="updateOther(link.id,2)">至顶</button> </td>
                    <td> <button id="subbotton" class="btn btn-success radius " ms-click="editLink(link.id)">修改</button> </td>
                    <td> <button id="delbotton" class="btn btn-success radius " ms-click="updateOther(link.id,1)">删除</button> </td>
                </tr>
                </tbody>

            </table>
            <span>从{{startRow}}到{{endRow}}共{{total}}</span>
            <div class="tcdPageCode"></div>
        </div>
    </body>
    <script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.js"></script>
    <script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
    <script type="text/javascript" src="${base_path}/resources/common/jquery/jquery_page/jquery.page.js"></script>
    <script type="text/javascript" src="${base_path }/resources/js/admin/admin_link_list.js" ></script>
</html>
