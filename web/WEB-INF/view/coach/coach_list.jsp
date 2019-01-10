<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>教练列表</title>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/css/match/style.1.0.css"/>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/css/coach/coach_list.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${path}/resources/css/common/style.css"/>
    <link href="${path}/resources/lib/layer/theme/default/layer.css"/>

</head>

<body>

<jsp:include page="../common/head.jsp"/>


<div style="width: 100%;min-height:70%;background-color:#FBFBFB;display: flex;justify-content: center;">
    <div style="min-height:70%;background-color:#FBFBFB;padding: 10px;width: 1168px" >
        <table class="table table-bordered text-center table-striped class" style="margin-bottom: 10px;">
            <div style="width: 100%;padding:40px 0px 10px 0px">
                <div class="list-title">
                    <p class="col-lg-2 col-sm-2 col-xs-4 p3">教练列表</p>
                </div>
                <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">添加教练</button>
                <div id="xuanxiang">
                    <div class="btn-group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"
                                id="coachzt">
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="#" onclick="coach1()">在职教练</a>
                            </li>
                            <li>
                                <a href="#" onclick="coach2()">离职教练</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </table>

        <br/>
        <div id="all" class="clearfix">

            <div class="row" style="margin: 0px -30px;">
                <c:forEach var="allCoach" items="${pageInfo.list}">
                    <div class="col-xs-12 col-sm-6 col-md-6" style="padding: 0px 30px;">
                        <div class="thumbnail">
                            <div class="pull-left" style="width: 120px;height:140px;overflow: hidden;">
                                <fmt:bundle basename="properties.upload">
                                    <c:if test="${!empty allCoach.photo}">
                                        <img id='picImg' class="" src="${path}/<fmt:message key="upload.image.url.prefix"/>/${allCoach.photo}"
                                             style="height: 140px" >
                                    </c:if>
                                    <c:if test="${empty allCoach.photo}">
                                        <img class="headimg" id=""  src="${path}/resources/img/coach/uploaded.png" />
                                    </c:if>
                                </fmt:bundle>
                            </div>
                            <div class="pull-left">
                                <div class="coach-list-intro">
                                    <p>教练：${allCoach.name}</p>
                                    <p>编号：${allCoach.idCoach}</p>
                                    <p>性别：${allCoach.sex==0?"女":"男"}</p>
                                </div>
                            </div>
                            <div class="pull-right" style="margin: 120px 0px 0px 0px">
                                <a href="${path}/coach/getCoach?id_coach=${allCoach.idCoach}"
                                >详情>></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                    </div>
                </c:forEach>
            </div>
            <div class="coach-under-page">
                <nav aria-label="Page navigation">
                    <ul class="pagination">

                        <li><a href="${path}/coach/getAllCoach?pn=1&status=${status}">首页</a>
                        </li>

                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${path}/coach/getAllCoach?pn=${pageInfo.pageNum-1}&status=${status}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${!pageInfo.hasPreviousPage}">
                            <li class="disabled">
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                            <c:if test="${page_Num == pageInfo.pageNum}">
                                <li class="active"><a href="#">${page_Num}</a></li>
                            </c:if>
                            <c:if test="${page_Num != pageInfo.pageNum}">
                                <li>
                                    <a href="${path}/coach/getAllCoach?pn=${page_Num}&status=${status}">${page_Num}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${path}/coach/getAllCoach?pn=${pageInfo.pageNum+1}&status=${status}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${!pageInfo.hasNextPage}">
                            <li class="disabled">
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <li>
                            <a href="${path}/coach/getAllCoach?pn=${pageInfo.pages}&status=${status}">末页</a>
                        </li>

                    </ul>
                </nav>
            </div>


        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" style="font-family: '微软雅黑'">
                    添加新教练
                </h4>
            </div>
            <div class="modal-body">
                <form id="form" enctype="multipart/form-data">
                    <div>
                        <div class="form-group">
                            <label for="inputName" class="col-sm-2 control-label" style="text-align: right;padding-top: 7px">姓名</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="inputName" name="name" value=""
                                       onvalidation="onChineseValidation">
                            </div>
                            <div style="clear: both"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputIdcard" class="col-sm-2 control-label" style="text-align: center;padding-top: 7px">身份证号</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="inputIdcard" name="idcard" value="">
                        </div>
                        <div style="clear: both"></div>
                    </div>
                    <div class="form-group">
                        <label for="inputPhone" class="col-sm-2 control-label" style="text-align: center;padding-top: 7px">电话号码</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="inputPhone" name="phone" value="">
                        </div>
                        <div style="clear: both"></div>
                    </div>

                    <div class="form-group">

                        <label class="col-sm-2 control-label" style="text-align: center;padding-top: 7px    ">性别</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="pid" onchange="Gender()">
                                <option grade="1">男</option>
                                <option grade="0">女</option>
                            </select>
                        </div>
                        <div style="clear: both"></div>
                    </div>
                    <div id="gr">
                        <input type="hidden" id="inputSex" placeholder="" name="sex" value="1">
                    </div>
                    <input type="hidden" id="inputOrgid" placeholder="" name="orgId" value="${org_id}">
                </form>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

                <button type="button" class="btn btn-primary" onclick="tt()">
                    确定
                </button>
            </div>


        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<!-- 全局js -->
<script src="${path}/resources/lib/respond.min.js"></script>
<script src="${path}/resources/lib/jquery/jquery-1.11.3.js"></script>
<script src="${path}/resources/lib/bootstrap/bootstrap.min.js"></script>
<script src="${path}/resources/lib/layer/layer.js"></script>
<script src="${path}/resources/js/index.js"></script>
<%--表单验证--%>
<script type="text/javascript">

    function tt() {

        checkCard();
        isCardNo();
        var id = document.getElementById("form").getElementsByTagName("input");
        for (var x = 0; x < id.length; x++) {

        }
    };

    var vcity = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    };

    checkCard = function () {
        var id = document.getElementById("form").getElementsByTagName("input");
        var truename = id[0].value;
        var reg = /^[\u4e00-\u9fa5]{2,4}$/i;
        // var img = document.getElementById("image");
        var myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
        if (!reg.test(truename)) {
            layer.alert("请输入真实姓名，只能是2-4个汉字！");
            id[0].focus();
        } else {
            var card = id[1].value;
            //是否为空
            if (card === '') {
                layer.alert('请输入身份证号，身份证号不能为空');
                id[1].focus();
                return false;
            }
            //校验长度，类型
            if (isCardNo(card) === false) {
                layer.alert('您输入的身份证号码不正确，请重新输入');
                alert("221");
                id[1].focus();
                return false;
            }
            //检查省份
            if (checkProvince(card) === false) {
                layer.alert('您输入的身份证号码不正确,请重新输入');
                alert("3");
                id[1].focus();
                return false;
            }
            //校验生日
            if (checkBirthday(card) === false) {
                layer.alert('您输入的身份证号码生日不正确,请重新输入');
                alert("4");
                id[1].focus();
                return false;
            }
            //检验位的检测
            if (checkParity(card) === false) {
                layer.alert('您的身份证校验位不正确,请重新输入');
                id[1].focus();
                return false;
            }
            if (!myreg.test(id[2].value)) {
                layer.alert('手机号码不正确');
                id[1].focus();
                return false;
            }


            // layer.alert('身份证验证通过，可以注册');
            addCoach();
            return true;
        }
    };

    //检查号码是否符合规范，包括长度，类型
    isCardNo = function (card) {
        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
        if (reg.test(card) === false) {
            return false;
        }

        return true;
    };

    //取身份证前两位,校验省份
    checkProvince = function (card) {
        var province = card.substr(0, 2);
        if (vcity[province] == undefined) {
            return false;
        }
        return true;
    };

    //检查生日是否正确
    checkBirthday = function (card) {
        var len = card.length;
        //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
        if (len == '15') {
            var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
            var arr_data = card.match(re_fifteen);
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date('19' + year + '/' + month + '/' + day);
            return verifyBirthday('19' + year, month, day, birthday);
        }
        //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
        if (len == '18') {
            var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
            var arr_data = card.match(re_eighteen);
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date(year + '/' + month + '/' + day);
            return verifyBirthday(year, month, day, birthday);
        }
        return false;
    };

    //校验日期
    verifyBirthday = function (year, month, day, birthday) {
        var now = new Date();
        var now_year = now.getFullYear();
        //年月日是否合理
        if (birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {
            //判断年份的范围（3岁到100岁之间)
            var time = now_year - year;
            if (time >= 3 && time <= 100) {
                return true;
            }
            return false;
        }
        return false;
    };

    //校验位的检测
    checkParity = function (card) {
        //15位转18位
        card = changeFivteenToEighteen(card);
        var len = card.length;
        if (len == '18') {
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var cardTemp = 0,
                i, valnum;
            for (i = 0; i < 17; i++) {
                cardTemp += card.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[cardTemp % 11];
            if (valnum == card.substr(17, 1)) {
                return true;
            }
            return true;
        }
        return false;
    };

    //15位转18位身份证号
    changeFivteenToEighteen = function (card) {
        if (card.length == '15') {
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var cardTemp = 0,
                i;
            card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
            for (i = 0; i < 17; i++) {
                cardTemp += card.substr(i, 1) * arrInt[i];
            }
            card += arrCh[cardTemp % 11];
            return card;
        }
        return card;
    };

    function addCoach() {
        var info = document.getElementById("form").getElementsByTagName("input");

        ajax({
            url: "${path}/coach/addCoach",
            type: "POST",
            data: {
                name: info[0].value,
                idcard: info[1].value,
                phone: info[2].value,
                sex: info[3].value,
                orgId: info[4].value,
                token: $('#token').html()
            },
            success: function (data, status) {

                $('#img').removeAttr('disabled').removeClass('disabled');
                // $(this).attr('disabled', true).addClass('disabled');
                if (data.status == 0) {
                    layer.msg('创建成功');
                    setTimeout("rel()", "1000");
                } else if (data.status == 1) {
                    layer.msg('身份证号或电话号码已占用，创建失败');
                    setTimeout("rel()", "1000");
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                layer.msg('服务器错误');
            },

        })

    }

    function rel() {
        window.location.reload();
    }


</script>

<script type="text/javascript">
    function Gender() {
        var objS = document.getElementById("pid");
        var grade = objS.options[objS.selectedIndex].getAttribute('grade');
        var gr = document.getElementById("gr").getElementsByTagName("input");
        gr[0].value = grade;
    }

</script>

<script>
    var test = window.location.href;
    var coachnum = test.substr(-1);
    var coachul = document.getElementById("coachzt");
    if (coachnum == 0) {
        coachul.innerHTML = "在职教练 <span class=\"caret\"></span>"
    } else if (coachnum == 1) {
        coachul.innerHTML = "离职教练 <span class=\"caret\"></span>"
    } else {
        coachul.innerHTML = "查看教练就职状态 <span class=\"caret\"></span>"
    }

    function coach1() {
        if (coachnum == 1) {
            window.location.href = "${path}/coach/getAllCoach?pn=1&status=0"
        }

    }

    function coach2() {
        if (coachnum == 0) {
            window.location.href = "${path}/coach/getAllCoach?pn=1&status=1"
        }

    }


</script>
<!--模态框居中-->
<script type="text/javascript">
    $(function () {
        // dom加载完毕
        var $m_btn = $('#modalBtn');
        var $modal = $('#myModal');
        $m_btn.on('click', function () {
            $modal.modal({
                backdrop: 'static'
            });
        });

        // 测试 bootstrap 居中
        $modal.on('show.bs.modal', function () {
            var $this = $(this);
            var $modal_dialog = $this.find('.modal-dialog');
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $this.css('display', 'block');
            $modal_dialog.css({
                'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2)
            });
        });

    });
</script>

<script>
    var imgwd = document.getElementById("all").getElementsByTagName("img");

    for(var i=0;i<imgwd.length;i++){
        if(imgwd[i].offsetWidth>120){
            var imgwd1 = imgwd[i].offsetWidth / 2 - 60;
            imgwd[i].style.marginLeft=-imgwd1 + "px";
        }

    }
</script>



</body>


</html>