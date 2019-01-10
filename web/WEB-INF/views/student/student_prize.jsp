<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 基本表单</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    
    <link href="${base_path}/resources/common/boostrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base_path}/resources/css/teacher/iCheck/custom.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/animate.css" rel="stylesheet">
    <link href="${base_path}/resources/common/nav_css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${base_path}/resources/common/boostrap/css/bootstrap.min.css">


    <script src="${base_path}/resources/common/jquery/jquery.min.js"></script>
    <script src="${base_path}/resources/common/jquery/jquery.validate.min.js"></script>
    <script src="${base_path}/resources/js/index.js"></script>
    <script src="${base_path}/resources/common/jquery/messages_zh.min.js"></script>
    <script src="${base_path}/resources/common/layer/2.4/layer.js"></script>
    <!-- 日期选择器layerDate plugin javascript -->
    <script src="${base_path}/resources/js/student/laydate/laydate.js"></script>
    <!-- 获奖信息文本框增删 -->
    <script src="${base_path}/resources/js/student/inputGroup.js"></script>
    <script src="${base_path}/resources/common/boostrap/js/bootstrap.min.js"></script>

    

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加获奖信息</h5>
                    </div>
                    <div class="ibox-content">
                    	
                        <form method="get" class="form-horizontal" id="stuInfo">

                            <div class="form-group">
                                <label class="col-sm-2 control-label">奖项名称</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="prize_name" name="prize_name" required>
                                </div>        
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">奖项级别</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="region" name="region">
                                </div>        
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">奖项等级</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="rank" name ="rank">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">获奖类型</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="category" name="category">
                                </div>        
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">获奖官网</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="url" name="url">
                                </div>        
                            </div>
                            
                            <div class="form-group">
                                        <label class="col-sm-2 control-label">获奖时间</label>
                                        <div class="col-sm-10">
                                            <input class="form-control layer-date" placeholder="YYYY-MM-DD" id="time" name="time" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                                            <label class="laydate-icon"></label>
                                        </div>
                            </div>
                            
                          
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit" id="">保存内容</button>
                                    <button class="btn btn-white" type="reset">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>

        $.validator.setDefaults({
            submitHandler: function() {
                subStuInfo();
            }
        });
        $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
            $("#stuInfo").validate({
                    //重写showErrors
                    showErrors: function (errorMap, errorList) {
                        var msg = "";
                        $.each(errorList, function (i, v) {
                            //msg += (v.message + "\r\n");
                            //在此处用了layer的方法,显示效果更美观
                            layer.tips(v.message, v.element, { tips:[1,"#cc2a32"],time: 2000 });
                            return false;
                        });
                    },
                        /* 失去焦点时不验证 */
                    onfocusout: false
                }
            )

        })

        function subStuInfo() {
            layer.confirm('提交后无法修改,确定吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                subInfo();
            }, function () {
                layer.msg('已取消', {icon: 1});
            });
        }
        function  subInfo() {
            ajax(
                {
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    async: true,
                    type: 'post',
                    url: 'submitPrizeInfo',
                    data:$("#stuInfo").serialize(),
                    success: function (data) {
                        switch(data.status)
                        {
                            case 200:
                                layer.msg("提交成功");
                                break;
                            case 1:
                                layer.msg("提交失败，请重试");
                                break;
                        }
                    },
                    error: function () {
                        layer.msg("系统错误，请联系管理员");
                        // layer.msg( JSON.stringify($("#stuInfo").serializeObject()));
                        //layer.msg($("#stuInfo").serialize());
                    }
                })

        }
    </script>
    <!-- 全局js -->

    <script>  
        $(function() {  
            $('.input-group-add').initInputGroup({  
                'widget' : 'textarea', //输入框组中间的空间类型  
                /*'add' : '添加',  
                'del' : '删除'*/  
            });  
        });  
    </script>



    <script>
        //外部js调用
        laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });

        //日期范围限制
        var start = {
            elem: '#start',
            format: 'YYYY/MM/DD hh:mm:ss',
            min: laydate.now(), //设定最小日期为当前日期
            max: '2099-06-16 23:59:59', //最大日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#end',
            format: 'YYYY/MM/DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-06-16 23:59:59',
            istime: true,
            istoday: false,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);
    </script>
    

</body>

</html>
