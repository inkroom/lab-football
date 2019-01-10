<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>联系我们后台</title>

    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/bootstrap.min.css?v=3.3.6"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/boostrap/css/font-awesome.css?v=4.4.0"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common/nav_css/style.css?v=4.1.0" rel="stylesheet">
    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/resources/common/jquery/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/resources/common/boostrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/common/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>

</head>

<body>
<div class="wrapper wrapper-content animated fadeIn">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>联系我们-走进大江东</h5>

                </div>
                <div class="ibox-content">
                    <div class="row">

                        <div class="col-sm-3">
                            <button type="button" id="add_contact_btnID" class="btn btn-success" data-toggle="modal" data-target="#myModal6">
                                添加新的板块
                            </button>

                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>板块标题</th>
                                    <th>联系方式1</th>
                                    <th>联系方式2</th>
                                    <th>联系方式3</th>
                                    <th>联系方式4</th>
                                    <th>联系方式5</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${contacts}" var="bean">
                            <tr>

                                <td>${bean.title}</td>
                                <td>${bean.contactname1} <c:if test="${bean.contactname1 != ''}">:</c:if> ${bean.contactway1}</td>
                                <td>${bean.contactname2} <c:if test="${bean.contactname2 != ''}">:</c:if> ${bean.contactway2}</td>
                                <td>${bean.contactname3} <c:if test="${bean.contactname3 != ''}">:</c:if> ${bean.contactway3}</td>
                                <td>${bean.contactname4} <c:if test="${bean.contactname4 != ''}">:</c:if> ${bean.contactway4}</td>
                                <td>${bean.contactname5} <c:if test="${bean.contactname5 != ''}">:</c:if> ${bean.contactway5}</td>

                                <td>
                                    <button type="button"
                                            name = "edit_contact_btn"
                                            class="btn btn-success btn-xs"
                                            data-toggle="modal"
                                            data-target="#myModa66"
                                            onclick=""
                                            contact_id = "${bean.id}">编辑
                                    </button>
                                    <button type="button"
                                            name = "delete_contact_btn"
                                            class="btn btn-danger btn-xs"
                                            contact_id = "${bean.id}">删除</button>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>

<%--添加弹框--%>
<form method="post" id="addform">
<div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="padding: 16px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">添加联系方式</h4>
            </div>
            <div class="modal-body">

                <div class="form-group"><label>板块类型：</label> <input type="text" id="add_title" name = "add_title" placeholder="请输入板块类型"
                                                                    class="form-control"></div>
                <%--<a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" id="AddMoreFileBox"--%>
                   <%--class="btn btn-success btn-xs">添加更多联系方式</a>--%>

                <div id="InputsWrapper">
                    <div class="form-group">
                        <input type="text" style="width: 33%;" class="form-control pull-left"
                               id="contactname1" name="contactname1" placeholder="联系名1"/>
                        <input type="text" style="width: 47%;" class="form-control pull-left"
                               id="contactway1" name="contactway1" placeholder="联系方式1"/>
                        <span>
									<a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow">
										<input type="button" class="btn btn-danger btn-sm" name="delete_addtext_btn"
                                               contactname="contactname1" contactway="contactway1" value="删除"></a>
						</span>

                    </div>
                </div>
            </div>
            <div class="modal-footer ">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="submit" id = "add_save_btn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</form>

<%--编辑弹框--%>
<form method="post" id="editform">
<div class="modal inmodal fade" id="myModa66" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="padding: 16px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑联系方式</h4>
            </div>
            <div class="modal-body">

                <div class="form-group"><label>板块类型：</label> <input type="text" id="edit_title" name="edit_title"  placeholder="请输入板块类型"
                                                                    class="form-control"></div>
                <%--<a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" id="AddMoreFileBox"--%>
                   <%--class="btn btn-success btn-xs">添加更多联系方式</a>--%>

                <div id="InputsWrapper1">
                    <div class="form-group">
                        <input type="text" style="width: 33%;" class="form-control pull-left"
                               id="editcontactname1" name="contactname1" placeholder="联系名1"/>
                        <input type="text" style="width: 47%;" class="form-control pull-left"
                               id="editcontactway1"  name="contactway1" placeholder="联系方式1"/>
                        <span>
									<a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow">
										<input type="button" class="btn btn-danger btn-sm" name="delete_edittext_btn"
                                               contactname="editcontactname1" contactway="editcontactway1" value="删除"></a>
								</span>

                    </div>
                </div>
            </div>
            <div class="modal-footer ">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="submit" id = "edit_save_btn"  class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</form>





<script>
    $(document).ready(function () {
        var MaxInputs = 4; //maximum input boxes allowed
        var InputsWrapper = $("#InputsWrapper"); //添加弹框append
        var InputsWrapper1 = $("#InputsWrapper1"); //编辑弹框append
//        var AddButton = $("#AddMoreFileBox");
        var x = InputsWrapper.length; //initlal text box count
        var FieldCount = 1; //to keep track of text box added
//        $(AddButton).click(function (e) //on add input button click
//        {
//            if (x <= MaxInputs) //max input box allowed
        for(var i = 0; i<MaxInputs; i++)
            {
                FieldCount++; //text box added increment
                //add input box
                $(InputsWrapper).append('<div class="form-group"><input type="text" style="width: 33%;" class="form-control pull-left" id="contactname' + FieldCount + '" name="contactname' + FieldCount + '" placeholder="联系名 '+ FieldCount + '"/><input type="text" style="width: 47%;" class="form-control pull-left" id="contactway' + FieldCount + '" name="contactway' + FieldCount + '" placeholder="联系方式 ' + FieldCount + '"/><a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" class="removeclass"><input type="button" class="btn btn-danger btn-sm" name="delete_addtext_btn" contactname="contactname' + FieldCount + '" contactway="contactway'+ FieldCount +'" value="删除"></a></div>');
                $(InputsWrapper1).append('<div class="form-group"><input type="text" style="width: 33%;" class="form-control pull-left" id="editcontactname' + FieldCount + '" name="contactname' + FieldCount + '" placeholder="联系名 '+ FieldCount + '"/><input type="text" style="width: 47%;" class="form-control pull-left" id="editcontactway' + FieldCount + '" name="contactway' + FieldCount + '" placeholder="联系方式 ' + FieldCount + '"/><a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" class="removeclass"><input type="button" class="btn btn-danger btn-sm" name="delete_edittext_btn" contactname="editcontactname' + FieldCount + '" contactway="editcontactway'+ FieldCount +'" value="删除"></a></div>');
                // x++; //text box increment
            }
//            return false;
//        });
        //点击移除弹框效果
        $("body").on("click", ".removeclass", function (e) { //user click on remove text
            if (x > 1) {
                $(this).parent('div').remove(); //remove text box
                x--; //decrement textbox
                FieldCount--;
            }
            return false;
        });
    });


    //添加保存按钮
    $("#add_save_btn").click(function () {
            $("#addform").validate({
                submitHandler: function () {
                    add_from();
                },

                rules: {

                    add_title: {
                        required: true
                    },
                    contactname1: {
                        required: true
                    },
                    contactway1: {
                        required: true
                    }

                },
                messages: {
                    add_title: {
                        required: '添加联系人标题必须填写'
                    },
                    contactname1: {
                        required: '第一个联系名字必须填写'
                    },
                    contactway1: {
                        required: '第一个联系方式必须填写'
                    }
                },
                //layer重写showErrors，提示信息的位置
                showErrors: function (errorMap, errorList) {
                    $.each(errorList, function (i, v) {
                        //用了layer让显示效果更美观
                        layer.tips(v.message, v.element, {tips: [1, "#cc2a32"], time: 2000});
                        return false;
                    });
                },
                /* 失去焦点时不验证 */
                onfocusout: false
            });

    });

    //添加表单的ajax
    function add_from() {
        var data = {
            title : $.trim($("#add_title").val()),
            contactname1:$.trim($("#contactname1").val()),
            contactway1:$.trim($("#contactway1").val()),
            contactname2:$.trim($("#contactname2").val()),
            contactway2:$.trim($("#contactway2").val()),
            contactname3:$.trim($("#contactname3").val()),
            contactway3:$.trim($("#contactway3").val()),
            contactname4:$.trim($("#contactname4").val()),
            contactway4:$.trim($("#contactway4").val()),
            contactname5:$.trim($("#contactname5").val()),
            contactway5:$.trim($("#contactway5").val())
        };

        $.ajax({
            url: "${pageContext.request.contextPath}/contactAdd.action",
            type: "post",
            async: false,
            dataType: "json",
            data: data,
            success: function (data) {
                switch (data.status){
                    case 0:
                        layer.alert('添加信息成功！',function () {
                            window.location.reload();
                        });
                        break;
                    case 1:
                        layer.alert('添加信息失败');
                        break;
                }
            },
            error: function () {
                layer.alert('添加信息失败');
            }
        });
    }

    //添加按钮被点击设置弹出框动作
    $("#add_contact_btnID").click(function () {
        delete_addtext();//添加弹框事件
    });



    //保存弹框删除text
    function delete_addtext() {
        $("input[name='delete_addtext_btn']").each(function () {
            var $btn = $(this);
            $btn.click(function () {
                var $contactname =  $("#"+$btn.attr("contactname"));
                var $contactway =  $("#"+$btn.attr("contactway"));
                $contactname.val("");
                $contactway.val("");
            });
        });
    }

    //编辑弹框删除text
    function delete_edittext() {
        $("input[name='delete_edittext_btn']").each(function () {
            var $btn = $(this);
            $btn.click(function () {
                var $contactname =  $("#"+$btn.attr("contactname"));
                var $contactway =  $("#"+$btn.attr("contactway"));
                $contactname.val("");
                $contactway.val("");
            });
        });
    }

    //修改保存按钮
    $("#edit_save_btn").click(function () {
        var $btn = $(this);
        var $data = {
            id : $btn.attr("contact_id"),
            title : $.trim($("#edit_title").val()),
            contactname1:$.trim($("#editcontactname1").val()),
            contactway1:$.trim($("#editcontactway1").val()),
            contactname2:$.trim($("#editcontactname2").val()),
            contactway2:$.trim($("#editcontactway2").val()),
            contactname3:$.trim($("#editcontactname3").val()),
            contactway3:$.trim($("#editcontactway3").val()),
            contactname4:$.trim($("#editcontactname4").val()),
            contactway4:$.trim($("#editcontactway4").val()),
            contactname5:$.trim($("#editcontactname5").val()),
            contactway5:$.trim($("#editcontactway5").val())
        };

        $("#editform").validate({
            submitHandler: function(){
                layer.alert("保存被点击了");
                edit_from($data);
            },

            rules: {

                edit_title: {
                    required: true
                },
                contactname1: {
                    required: true
                },
                contactway1: {
                    required: true
                }

            },
            messages: {
                edit_title: {
                    required: '添加联系人标题必须填写'
                },
                contactname1: {
                    required: '第一个联系名字必须填写'
                },
                contactway1: {
                    required: '第一个联系方式必须填写'
                }
            },
            //layer重写showErrors，提示信息的位置
            showErrors: function (errorMap, errorList) {
                $.each(errorList, function (i, v) {
                    //用了layer让显示效果更美观
                    layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
                    return false;
                });
            },
            /* 失去焦点时不验证 */
            onfocusout: false
        });

    });

    function edit_from(data) {
        $.ajax({
            url: "${pageContext.request.contextPath}/contactEdit.action",
            type: "post",
            async: false,
            dataType: "json",
            data: data,
            success: function (data) {
                switch (data.status){
                    case 0:
                        layer.alert('编辑信息成功',function () {
                            window.location.reload();
                        });
                        break;
                    case 1:
                        layer.alert('编辑信息失败');
                        break;
                }
            },
            error: function () {
                layer.alert('编辑信息失败');
            }
        });
    }


    //编辑获得信息按钮
    $("button[name='edit_contact_btn']").each(function () {
        var $btn = $(this);
        $btn.click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/contactEdit.action",
                type: "get",
                async: false,
                dataType: "json",
                data: {
                    contact_id: $btn.attr("contact_id")
                },
                success: function (data) {
                    switch (data.status){
                        case 0:
                            $("#edit_save_btn").attr("contact_id",$btn.attr("contact_id"));
                            var mydata = data.data["contactbean"];
                            edit_setinfo(mydata);
                            delete_edittext();
                            break;
                        case 1:
                            layer.alert('添加信息失败');
                            break;
                    }
                },
                error: function () {
                    layer.alert('获得编辑信息失败');
                }
            });
        });
    });

    function edit_setinfo(mydata) {
        //给编辑弹出框传入数据
        $("#edit_title").val(mydata.title);
        $("#editcontactname1").val(mydata.contactname1);
        $("#editcontactway1").val(mydata.contactway1);
        $("#editcontactname2").val(mydata.contactname2);
        $("#editcontactway2").val(mydata.contactway2);
        $("#editcontactname3").val(mydata.contactname3);
        $("#editcontactway3").val(mydata.contactway3);
        $("#editcontactname4").val(mydata.contactname4);
        $("#editcontactway4").val(mydata.contactway4);
        $("#editcontactname5").val(mydata.contactname5);
        $("#editcontactway5").val(mydata.contactway5);
    }


            //删除按钮
            $("button[name='delete_contact_btn']").each(function () {
                var $btn = $(this);
                $btn.click(function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/contactDelete.action",
                        type: "post",
                        async: false,
                        dataType: "json",
                        data: {
                            contact_id: $btn.attr("contact_id")
                        },
                        success: function (data) {
                            switch (data.status){
                                case 0:
                                    layer.alert("删除成功",function () {
                                        window.location.reload();
                                    });
                                    break;
                                case 1:
                                    layer.alert('删除信息失败');
                                    break;
                            }
                        },
                        error: function () {
                            layer.alert('删除信息失败');
                        }
                    });
                });
            });
</script>


</body>
</html>