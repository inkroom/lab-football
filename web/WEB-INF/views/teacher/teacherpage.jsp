<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>教师端首页</title>
    <script type="application/javascript" src="${base_path}/resources/common/jquery.min.js"></script>
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="${base_path}/resources/common/layer/2.4/layer.js"></script>
    <script type="application/javascript" src="${base_path}/resources/common/jquery/jquery-form.js"></script>
    <script type="application/javascript" src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="${base_path}/resources/js/teacher/for-teacherpage.js"></script>
    <style>
        html {
            margin: 10px;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-1 column">
        </div>
        <div class="col-md-10 column">
            <%--顶部导航信息--%>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#Page_stu" data-toggle="tab">首页</a>
                </li>
                <li>
                    <a href="#Page_notice" data-toggle="tab">公告</a>
                </li>
                <li>
                    <a href="#Page_LabInfo" data-toggle="tab">信息</a>
                </li>
                <%--实验室切换信息--%>
                <li class="dropdown pull-right">
                    <a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle"><span class="lab-on"
                                                                                                data-labId="0">${lab.name}</span><strong
                            class="caret"></strong></a>
                    <ul class="dropdown-menu" id="lab-list">
                        <c:forEach items="${labEntityList}" var="item" varStatus="status">
                            <li><a href="javascript:void(0);" onclick="lab_changed(${status.index})">${item.name}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${labEntityList.size() <= 0}">
                            <li><a href="javascript:void(0);">未加入实验室</a></li>
                        </c:if>
                        <%--<%--%>

                        <%--List<LabEntity> labEntityList = (List<LabEntity>) request.getAttribute("labEntityList");--%>
                        <%--if (labEntityList != null) {--%>
                        <%--for (int i = 1; i < labEntityList.size(); i++) {--%>
                        <%--request.setAttribute("lab_name", labEntityList.get(i).getName());--%>
                        <%--request.setAttribute("lab_order", i);--%>
                        <%--}--%>
                        <%--%>--%>
                        <%--<li><a href="javascript:void(0);" onclick="lab_changed(${lab_order})">${lab_name}</a></li>--%>
                        <%--<%--%>
                        <%--} else {%>--%>
                        <%--<li><a href="javascript:void(0);">未加入实验室</a></li>--%>
                        <%--<%--%>
                        <%--}--%>
                        <%--%>--%>
                    </ul>
                </li>
            </ul>
            <%--三个选项卡div--%>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="Page_stu">
                    <%--表格标题--%>
                    <div class="page-header">
                        <h1>
                            <span class="lab-on">${lab.name}</span>
                            <small>学生信息列表<c:if test="${!empty stu_admin }">（管理员： <a
                                    href="${base_path}/Teacher/stu_info?stu_id=${stu_admin.id}" target="_blank"
                                    id="stu_admin">${stu_admin.name}</a>）</c:if>
                            </small>
                        </h1>
                    </div>
                    <%--学生信息列表--%>
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th>
                                学号
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                年级
                            </th>
                            <th>
                                专业
                            </th>
                            <th>
                                电话
                            </th>
                            <th title="月签到次数">
                                签到/次
                            </th>
                            <th>
                                备注
                            </th>
                        </tr>
                        </thead>
                        <%--学生信息列表--%>
                        <tbody id="stu_body">
                        <c:forEach items="${stuList}" var="stu">
                            <tr>
                                <td>${stu.id}</td>
                                <td><a href="${base_path}/Teacher/stu_info?stu_id=${stu.id}"
                                       target="_blank">${stu.name}</a>
                                </td>
                                <td>${stu.grade}</td>
                                <td>${stu.major}</td>
                                <td>${stu.tel}</td>
                                <td>${stu.frequency}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${stu_admin.id eq stu.id}">
                                            <input type="button" name="setting" class="btn btn-primary" value="取消管理员"
                                                   onclick="set(${stu.id},0,this)">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="button" name="setting" class="btn btn-primary" value="指定管理员"
                                                   onclick="set(${stu.id},1,this)">
                                        </c:otherwise>
                                    </c:choose>
                                        <%--<%--%>
                                        <%--if ((stu_admin != null) && (stu != null)) {--%>
                                        <%--if (stu_admin.getId().equals(stu.getId())) {--%>
                                        <%--%>--%>
                                        <%----%>
                                        <%--<%--%>
                                        <%--}--%>
                                        <%--} else {--%>
                                        <%--%>--%>
                                        <%----%>
                                        <%--<%--%>
                                        <%--}--%>
                                        <%--%>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        <%--<%--%>
                        <%--//                    String stuList = (String) request.getAttribute("stuList");--%>
                        <%--List<StudentEntity> stuList = (List<StudentEntity>) request.getAttribute("stuList");--%>
                        <%--if (stuList != null) {--%>
                        <%--for (int i = 0; i < stuList.size(); i++) {--%>
                        <%--StudentEntity stu_admin = (StudentEntity) request.getAttribute("stu_admin");--%>
                        <%--StudentEntity stu = stuList.get(i);--%>
                        <%--request.setAttribute("stu", stu);--%>
                        <%--%>--%>
                        <%----%>
                        <%--<%--%>
                        <%--}--%>
                        <%--}--%>
                        <%--%>--%>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade " id="Page_notice">
                    <%--表格标题 公告通知--%>
                    <div class="page-header">
                        <input type="button" class="pull-right btn btn-primary btn-group-xs" value="发布新公告"
                               id="addNotice" data-toggle="modal" data-target="#myModal"/>
                        <h1>
                            <span class="lab-on">${lab.name}</span>
                            <small>通知列表</small>
                        </h1>
                    </div>
                    <%--通知列表--%>
                    <div id="notice-body">
                        <c:forEach items="${noticeEntityList}" var="notice">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <span class="notice_title">${notice.title}</span>
                                        <c:if test="${!empty(notice.file_path)}">
                                            <%--<jsp:useBean id="dateValue" class="java.util.Date"/>--%>
                                            <%--<jsp:setProperty name="dateValue" property="time"--%>
                                            <%--value="${notice.time.getTime()-(3600*1000*8)}"/>--%>
                                            <span class="pull-right">${notice.publisher}&nbsp;于&nbsp;${notice.time}&nbsp;&nbsp;
                                                 <fmt:formatDate value="${notice.time}" pattern="yyyy-MM-dd hh:mm"/> ||
                                                <c:if test="${!empty(notice.file_path)&&!empty(notice.file_name)}">
                                                     <a href="${base_path}/student/downLoad?filePath=${notice.file_path}"
                                                        title="${notice.file_name}"> 附件 </a>
                                                </c:if>
                                               </span>
                                        </c:if>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                        ${notice.content}
                                </div>
                            </div>
                        </c:forEach>
                        <%--<%--%>
                        <%--List<NoticeEntity> noticeEntityList = (List<NoticeEntity>) request.getAttribute("noticeEntityList");--%>
                        <%--if (noticeEntityList != null) {--%>
                        <%--for (int i = 0; i < noticeEntityList.size(); i++) {--%>
                        <%--NoticeEntity notice = noticeEntityList.get(i);--%>
                        <%--request.setAttribute("notice", notice);--%>
                        <%--%>--%>
                        <%----%>
                        <%--<%--%>
                        <%--}--%>
                        <%--}--%>
                        <%--%>--%>
                    </div>

                </div>
                <div class="tab-pane fade" id="Page_LabInfo">
                    <%--实验室详细信息页面--%>
                    <h1 class="text-center"><span class="lab-on">${lab.name}</span>
                        <small class="frequency" title="实验室成员共计${lab.stu_num}人">本月人均考勤<span
                                id="lab_fre">${lab.avg_fre}</span>次
                        </small>
                    </h1>
                    <blockquote>
                        <p>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="lab_des">${lab.describe}</span>
                        </p>
                        <small>地址：<cite id="lab_address">${lab.address}</cite></small>
                    </blockquote>
                    <h1>
                        <small>教师团队</small>
                    </h1>
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th>
                                教师
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                性别
                            </th>
                            <th>
                                电话
                            </th>
                        </tr>
                        </thead>
                        <tbody id="teacher_list">
                        <c:forEach items="${teacherEntitieList}" var="teacher">
                            <tr>
                                <td>${teacher.id}</td>
                                <td>${teacher.name}</td>
                                <td>${teacher.gender}</td>
                                <td>${teacher.tel}</td>
                            </tr>
                        </c:forEach>
                        <%--<%--%>
                        <%--List<TeacherEntity> teacherList = (List<TeacherEntity>) request.getAttribute("teacherEntitieList");--%>
                        <%--if (teacherList != null) {--%>
                        <%--for (int i = 0; i < teacherList.size(); i++) {--%>
                        <%--TeacherEntity teacher = teacherList.get(i);--%>
                        <%--request.setAttribute("teacher", teacher);--%>
                        <%--request.setAttribute("order", i);--%>
                        <%--%>--%>
                        <%----%>
                        <%--<%--%>
                        <%--}--%>
                        <%--}--%>
                        <%--%>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-1 column">
        </div>
    </div>
</div>
<!-- 发布公告模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    新公告
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addNotice_from" action="${base_path}/Teacher/UploadFile" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="noticeTitle">标题</label><input type="text" required class="form-control"
                                                                  name="title" id="noticeTitle"
                                                                  placeholder="添加的新通知"/>
                    </div>
                    <div class="form-group">
                        <label for="noticeContent">内容</label><textarea required class="form-control" rows="5"
                                                                       name="content" id="noticeContent"
                                                                       placeholder="这是要给新的通知"></textarea>
                        <%--<input type="password" class="form-control" name="noticeContent" id="noticeContent" />--%>
                    </div>
                    <div class="form-group">
                        <label for="noticeFile">上传附件</label><input type="file" name="commonsMultipartFile"
                                                                   id="noticeFile"/>
                        <p class="help-block">
                            如果有多个文件请上传压缩包
                        </p>
                    </div>
                    <div class="form-group">
                        <label>发布到下列实验室</label>
                        <div class="checkbox" name="notice_choose_lab">
                            <c:forEach items="${labEntityList}" var="item">
                                <label>
                                    <input type="radio" checked name="lab" value="${item.id}">${item.name}
                                </label>
                            </c:forEach>
                            <%--<%--%>
                            <%--if (labEntityList != null) {--%>
                            <%--for (int i = 0; i < labEntityList.size(); i++) {--%>
                            <%--request.setAttribute("lab_name", labEntityList.get(i).getName());--%>
                            <%--request.setAttribute("lab_id", labEntityList.get(i).getId());--%>
                            <%--%>--%>
                            <%--<label>--%>
                            <%--<input type="checkbox" checked name="lab" value="${lab_id}">${lab_name}--%>
                            <%--</label>--%>
                            <%--<%--%>
                            <%--}--%>
                            <%--}--%>
                            <%--%>--%>
                        </div>
                        <div id="noticeResult" class="alert" hidden>通知发布成功</div>
                        <div>
                            <button type="submit" class="btn btn-primary">上传</button>
                        </div>
                        <button type="reset" id="modal_reset" hidden>重置</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="modal_close" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <%--<button type="button" class="btn btn-primary">--%>
                <%--发布--%>
                <%--</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${base_path}/resources/js/index.js"></script>
<script type="application/javascript">
    function set(stu, identity, obj) {
        ajax({
            url: '${base_path}/Teacher/set',
            dataType: 'json',
            data: {
                stuId: stu,
                identity: identity
            },
            success: function (result) {
                if (result.status === 0) {
                    layer.msg('设置成功');
                    if (identity === 1) {//设置成管理员
                        obj.value = '指定管理员'
                        obj.onclick = function () {
                            set(stu, 0, obj);
                        }
                    } else if (identity === 0) {
                        obj.value = '取消管理员'
                        obj.onclick = function () {
                            set(stu, 1, obj);
                        }
                    }
                    location.reload();
                } else {
                    layer.msg('设置失败');
                }
            }
        });
    }
</script>
</body>
</html>