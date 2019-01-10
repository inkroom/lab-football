<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">


</head>

<body>
<div class="wrapper wrapper-content animated fadeIn">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>添加技术</h5>

                </div>
                <div class="ibox-content">
                    <div class="row">

                        <div class="col-sm-3">
                            <a href="${pageContext.request.contextPath}/backstage/technical/addtech.html?te_id=0" class="btn btn-success">添加新技术</a>
                        </div>
                        <div class="col-sm-3 m-b-xs">
                            <div class="input-group">
                                <input type="text" class="input-sm form-control" placeholder="请输入系列名称" id="addset">
                                <span class="input-group-btn ">
                                        <button type="button" class="btn btn-sm btn-primary "onclick="addset(${page})"> 添加新系列</button> </span>
                            </div>
                        </div>
                        <div class="col-sm-6 pull-right">
                            <div class="input-group">
                                <c:choose>
                                    <c:when test="${find eq 0}">
                                        <input type="text" placeholder="请输入技术名称搜索" class="input-sm form-control" id="findname"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"onclick="find()"> 搜索</button> </span>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" placeholder="${findname}"  value="${findname}"  class="input-sm form-control" id="findname"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"onclick="find()"> 搜索</button> </span>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>技术名称</th>
                                <th>
                                    <select id="selsetlist">
                                        <option value="0" onclick="selsettech(0)">技术类型</option>
                                        <c:forEach items="${settech}" var="settech">
                                            <c:choose>
                                                <c:when test="${findset==settech.ts_id}">
                                                    <option value="${settech.ts_id}" onclick="selsettech(${settech.ts_id})" selected="selected">${settech.ts_name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${settech.ts_id}" onclick="selsettech(${settech.ts_id})">${settech.ts_name}</option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                    </select>
                                </th>
                                <th>编辑日期</th>
                                <th class="project-actions">操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${techs}" var="techs">
                            <tr>
                                <td>${techs.te_name}</td>
                                <td>${techs.te_setname}</td>
                                <td><fmt:formatDate value="${techs.te_date}"  pattern="yyyy/MM/dd  HH:mm:ss"/></td>
                                <td class="project-actions">
                                    <button type="button" class="btn btn-success btn-xs"  style="color: white;" onclick="update(${techs.te_id})">编辑</button>
                                    <button type="button" class="btn btn-danger btn-xs"  onclick="deletech(${techs.te_id},${page})">删除</button>
                                </td>
                            </tr>

                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="project-actions">
                            <div class="btn-group">
                                <c:choose>
                                    <c:when test="${maxpage eq 0}">
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${find eq 1}">
                                                <button class="btn btn-white" type="button" onclick="findlastpage(${page},${findname})"><i class="fa fa-chevron-left"></i></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-white" type="button" onclick="lastpage(${page})"><i class="fa fa-chevron-left"></i></button>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="i" begin="1" end="${maxpage}" step="1">
                                            <c:choose>
                                                <c:when test="${page==i}">
                                                    <button class="btn btn-white  active"  onclick="page(${i})">${i}</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-white" onclick="page(${i})">${i}</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${find eq 1}">
                                                <button class="btn btn-white" type="button" onclick="findnextpage(${page},${findname})"><i class="fa fa-chevron-right"></i></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-white" type="button" onclick="nextpage(${page})"><i class="fa fa-chevron-right"></i></button>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/layer/layer.js"></script>
<script type="text/javascript">
    function selsettech(findset){
        var page=${page};
        window.location.href = "${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=" + page+"&findset="+findset;

    }
    function page(pa) {
        window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page="+pa
    }
    function lastpage(pa) {
        window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=-1&page="+pa
    }
    function nextpage(pa) {
        window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=1&page="+pa
    }
    function findlastpage(pa,name) {
        window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=1&page="+pa+"&find=1&findname="+name
    }

    function findnextpage(pa,name) {
        window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=1&page="+pa+"&find=1&findname="+name
    }
    function update(te_id) {
        window.location.href="${pageContext.request.contextPath}/backstage/technical/updatetech.html?te_id="+te_id
    }
    function addset(page) {
        var  setname = $("#addset").val();
        if(setname.length!=0){
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/backstage/technical/addset.action",
                data: {setname: setname},
                dataType: "json",
                success: function (data) {
                    if(data.flag==200) {
                        layer.alert('添加成功',function(){
                            window.location.href = "${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=" + page
                        });
                    }else{
                        layer.alert('添加失败',function(){

                            window.location.href = "${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=" + page
                        });
                    }
                },
                error: function () {
                    layer.msg('网络错误');
                }


            });
        }
    }

    function deletech(id,page) {
        index = layer.confirm('确定要删除该技术吗？',{
            btn: ['确认', '取消'] //按钮
        },function () {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/backstage/technical/deletetech.action",
            data: {id: id},
            dataType: "json",
            success: function (data) {
                if (data.flag == 200) {
                    layer.alert('删除成功', function () {
                        window.location.href = "${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=" + page
                    });

                } else {
                    layer.alert('删除失败', function () {
                        window.location.href = "${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=" + page
                    })
                }
            }

        });


    })
    }

    function find() {
        var findname = $("#findname").val();
        if(findname.length==0){
            window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=1"
        }else{
            window.location.href="${pageContext.request.contextPath}/backstage/technical/index.html?flag=0&page=1&find=1&findname="+findname
        }

    }

</script>

</html>