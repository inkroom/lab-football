<%--
  Created by IntelliJ IDEA.
  User: MeiXiebing
  Date: 5/22/17
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>链接添加</title>
    <link rel="stylesheet" type="text/css" href="${base_path }/resources/common/static/h-ui/css/H-ui.min.css" />
    <style type="text/css">
        .btn-upload{position: relative; display:inline-block;height:36px; *display:inline;overflow:hidden;vertical-align:middle;cursor:pointer}
        .upload-url{cursor: pointer}
        .input-file{position:absolute; right:0; top:0; cursor: pointer; z-index:1; font-size:30em; *font-size:30px;opacity:0;filter: alpha(opacity=0)}
        .btn-upload .input-text{ width:auto}
        .form-group .upload-btn{ margin-left:-1px}
    </style>
</head>
<body>
<form>
    <div class="codeView docs-example" id="my-body" ms-controller="my-body">
        <form action="" method="post" class="form form-horizontal" id="demoform-1">
            <input type="hidden" value="${_csrf.token }" id="token">
            <div class="row cl mb-20">
                <label class="form-label col-xs-4 col-sm-3 text-r">链接名称：</label>
                <div class="formControls col-xs-8 col-sm-7">
                    <input type="text" name="linkName" id="linkName" class="input-text" autocomplete="off" placeholder="链接名称" ms-duplex="@name">
                </div>
            </div>
            <div class="row cl mb-20">
                <label class="form-label col-xs-4 col-sm-3 text-r">链接地址：</label>
                <div class="formControls col-xs-8 col-sm-7">
                    <input type="text" name="linkAddr" id="linkAddr" class="input-text" autocomplete="off" placeholder="链接地址" ms-duplex="@addr">
                </div>
            </div>
            <div class="row cl mb-20">
                <label class="form-label col-xs-4 col-sm-3 text-r">下拉框：</label>
                <div class="formControls col-xs-8 col-sm-7"> <span class="select-box">
							<select class="select" size="1" name="linkType" id="linkType"  >
                                <option value="1">市政府教育行政部门</option>
                                <option value="2">省内各高校</option>
                                <option value="3">直属单位网站</option>
                                <option value="4">其它教育网站</option>
							</select>
							</span> </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-6 col-sm-offset-5">
                    <input class="btn btn-primary radius" type="button" value="提交" id="subbotton">
                </div>
            </div>
        </form>
    </div>
</form>

<script type="text/javascript" src="${base_path }/resources/common/lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="${base_path }/resources/common/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${base_path}/resources/common/avalon/avalon.js"></script>
<script type="text/javascript" src="${base_path }/resources/js/admin/admin_link.js"></script>
<script type="text/javascript">
    var linkJson = ${link};
    if(linkJson != null){
        var vm = avalon.define({
            $id:"my-body",
            name : linkJson.link_name,
            addr : linkJson.link_addr,
            type : linkJson.link_type
        })
        var linkSelect = $(".select").val(linkJson.link_type);
    }else {
        linkJson = {id:'',link_name:'',link_addr:''};
    }
</script>


</body>
</html>
