<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<c:set var="base_path" value="${path}"/>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>球队管理</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="${base_path}/resources/css/team/bootstrap-responsive.css" rel="stylesheet">
  <!--为了让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放，需要在网页的 head 之中添加 viewport meta 标签，-->
  <link href="${base_path}/resources/css/team/bootstrap.css" rel="stylesheet">
  <link href="${base_path}/resources/lib/layer/theme/default/layer.css" rel="stylesheet">
  <!-- 新 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/common/style.css" />
  <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/team/createTeam-style.css" />

  <link rel="stylesheet" type="text/css" href="${base_path}/resources/css/team/bootstarp3-btn.css"/>
  <style>
    tr:hover{
      background-color:white;
    }
  </style>
  <!--[if IE 8]>
  <script src="${base_path}/resources/js/team/html5shiv.min.js"></script>
  <script src="${base_path}/resources/js/team/placeholder.min.js" charset="utf-8"></script>
  <![endif]-->
</head>

<body>
 <jsp:include page="../common/head.jsp"/>

<div id="info" class="container2" style="width:1168px;">
  <p style="font-family: '微软雅黑';font-weight: 600;font-size: 24px;margin-top:30px;margin-bottom:21px;/*position:absolute;left:270px;top:81px;*/">球队管理</p>
<div style="border: 1px solid #FFFFFF;box-shadow: 0 0px 24px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);">
  <ul class="nav nav-tabs nav-color" >
    <li class="active"><a href="#tab1">球队预览</a></li>
    <li><a href="${base_path}/team/create" >球队创建</a></li>
  </ul>
  <table class="table table-hover " id="table_list" >
    <thead class="info" style="color:#666;border-bottom: 2px solid #dddddd">
      <th>编号</th>
      <th>队名</th>
      <th>教练编号</th>
      <th>人数</th>
      <th>状态</th>
      <th>操作</th>
    </thead>
    <tbody id="tableStyle">
    </tbody>
  </table>
  <center>
    <div class="pagination">
      <ul id="ulList" style="cursor:pointer;">
      </ul>
    </div>
  </center>
</div>
</div>
<!--container end-->
<!-- 模态框3Modal 开始-->
<div id="myModal3" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="position:fixed;top:10%;left:42.5%;width:800px;">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">球队信息</h3>
  </div>
  <div class="modal-body">
    <div>
      <table class="table table-bordered messageCard">
        <tr class="main-su">
          <td style="color: #5f5f5f;">基本信息</td>
        </tr>
        <tr>
          <td>
            <p style="width:50%;float:left;margin-top:10px;"><span class="muted">队&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
              <span id="teamName" readonly="readonly" type="text" style="height:20px;width:190px;" value=""></span></p>
            <p style="width:50%;float:left;margin-top:10px;"><span class="muted">球队类型:</span>
              <%--<select id="myInput" style="width:204px;">
                <option id="type" value=""></option>
                </select>--%>
              <span id="type" readonly="readonly" type="text" style="height:20px;width:190px;" value=""></span>
            </p>
            <p style="width:50%;float:right;margin-top: 10px"><span id="staus" class="muted">球队状态:</span>
            <span  name="TEAM_STATUS" > 在役</span>
            <%--<input type="radio" name="TEAM_STATUS" value="退役"> 退役--%>
            </p>
            <p style="width:50%;float:left;margin-top:10px;"><span class="muted">球队编号: </span><span id="idTeam"  ></span></p>
            <p style="width:50%;float:left;margin-top:10px;"><span class="muted">创建时间: </span><span id="createTeam"></span></p>
          </td>
        </tr>
      </table>
    </div>

    <div>
      <table class="table table-bordered  messageCard">
        <tr class="main-su">
          <td style="color: #5f5f5f;">教练信息</td>
        </tr>
        <tr>
          <td>
            <div style="width:50%;float:left;">
              <p style="margin-top:6px;">
                <span class="muted" >姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: <span id="coachName"style="color:black;"></span></span>
              </p>
              <p style="margin-top:15px;">
                <span class="muted" >年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄: <span id="coachAge"style="color:black;"></span></span>
              </p>
            </div>
            <div style="width:50%;float:right;">
              <p style="margin-top:6px;">
                <span class="muted" >性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别: <span id="coachSex"style="color:black;"></span></span>
              </p>
              <p style="margin-top:15px;"><span class="muted" >教练编号: <span id="idCoach"style="color:black;"></span></span></p>
            </div>
          </td>

        </tr>
      </table>
    </div>


    <div>
      <table class="table table-bordered messageCard">
        <tr class="main-su">
          <td style="color: #5f5f5f;">球员信息</td>
        </tr>
        <tr>
          <td>
            <div class="row">
            </div>
          </td>
        </tr>
      </table>
    </div>

  </div><!--中部结束-->
  <div class="modal-footer">
    <button class="btn3 btn3-default" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div><!-- 模态框3Modal 结束-->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${base_path}/resources/lib/html5shiv.min.js"></script>
<script src="${base_path}/resources/lib/respond.min.js"></script>
<script src="${path}/resources/js/index.js"></script>
<%--<script src="${base_path}/resources/lib/jquery/jquery-3.1.1.min.js "></script>--%>
<script src="${base_path}/resources/lib/jquery/jquery-1.11.3.js "></script>
<script src="${base_path}/resources/lib/layer/layer.js "></script>
<!-- <script src="js/jquery-3.1.1.min.js"></script> -->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${base_path}/resources/js/team/bootstrap.min.js"></script>

</body>
<script type="text/javascript">
  $(document).ready(function () {
      var  pn = 1;
      load(1);
      //查看事件
      $("#tableStyle").on('click','.look',function () {
          $(".row").empty();
          var  td = $(this).parents("#first").children().eq(0).text();
            ajax({
                type:"post",
                url:"${base_path}/team/teamInfo",
                dataType:"json",
                data: {"idTeam":td,"token":$('#token').html()},
                success:function (result) {
                        if (result.status ==0) {
                            $("#teamName").text(result.data.team.name);
                            $("#idTeam").text(result.data.team.idTeam);
                            var realType = "";
                            var type = result.data.team.type;

                            switch (type) {
                                case 1:
                                    realType = "小学一年级";
                                    break;
                                case 2:
                                    realType = "小学二年级";
                                    break;
                                case 3:
                                    realType = "小学三年级";
                                    break;
                                case 4:
                                    realType = "小学四年级";
                                    break;
                                case 5:
                                    realType = "小学五年级";
                                    break;
                                case 6:
                                    realType = "小学六年级";
                                    break;
                                case 7:
                                    realType = "初中一年级";
                                    break;
                                case 8:
                                    realType = "初中二年级";
                                    break;
                                case 9:
                                    realType = "初中三年级";
                                    break;
                                case 10:
                                    realType = "混合年级";
                                    break;
                                case 11:
                                    realType = "混合校级";
                                    break;
                            }

                            var time = result.data.team.createTime;
                            var realTime = new Date(time);
                            var year = realTime.getYear();
                            var month = realTime.getMonth()+1;
                            var day = realTime.getDate();
                            var hour = realTime.getHours();
                            var min = realTime.getMinutes();
                            var ss = realTime.getSeconds();

                            var datime = year + "/" + month + "/" + day + "    " + hour + ":" + min + ":" + ss;

                            $("#type").text(realType);
                            $("#createTeam").text(datime);

                            //todo还要存储教练名称
                            if (result.data.coach != null) {
                                $("#coachAge").text(result.data.coach.age);
                                if (result.data.coach.sex == 0) {
                                    $("#coachSex").text("女");
                                } else {
                                    $("#coachSex").text("男");
                                }
                                //$("#coachSex").text(result.data.coach.sex);
                                $("#coachName").text(result.data.coach.name);
                                $("#idCoach").text(result.data.coach.idCoach);
                            }
                            else {
                                $("#coachName").text("未添加教练");
                                $("#coachName").css("color", "red");
                            }
                            var row = $(".row");
                            <fmt:bundle basename="properties.upload">
                            if (result.data.playerId.length != 0) {
                                for (var i = 0; i < result.data.playerId.length; i++) {
                                    var div = $("<div class=\"zhazhazha\" ></div>");
                                    var img = $("<img class=\"clo\" src=\"${base_path}/<fmt:message key="upload.image.url.prefix"/>/"+result.data.players[i].headPic+"\" style=\"width:78px;height:91px;\">");
                                    var nameSpan = $("<span></span>");
                                    nameSpan.text(result.data.players[i].name);
                                    var playerIdSpan = $("<span id=\"idPlayer\"></span>");
                                    playerIdSpan.text(result.data.players[i].idPlayer);
                                    var p = $("<p class=\"muted zhazhahui\" ></p>");
                                    var br = $("<br>");
                                    playerIdSpan.text(result.data.playerId[i]);
                                    p.append(nameSpan);
                                    p.append(br);
                                    p.append(playerIdSpan);
                                    div.append(img);
                                    div.append(p);
                                    row.append(div);
                                }
                            } else {

                                row.append("<div style='color: red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未添加球员!!!</div>");
                            }
                            </fmt:bundle>
                        }else if (result.status ==1){
                            msg("请求错误，请刷新");
                        }
                },
                error:(function (error) {
                    console.log(error);
                })
            });
          $(this).attr("href","#myModal3");



      });

    //修改事件
      $("#tableStyle").on('click','.update',function () {
          var  td = $(this).parents("#first").children().eq(0).text();
          window.location.href="${base_path}/team/updateInfo?idTeam="+td;
      });



//解散事件
      $("#tableStyle").on('click','.fire',function () {
          var  td = $(this).parents("#first").children().eq(0).text();
          layer.confirm('确认解散吗？',{ btn: ['确定', '取消']},
          function (index) {
              var  flag = false ;
                ajax({
                  type:"post",
                  url: "${base_path}/team/breakTeam",
                  dataType:"json",
                  data:{"idTeam":td,"token":$('#token').html()} ,
                  success:function (result) {
                      if (result.status ==0){
                          layer.alert("解散成功");
                          window.load(1);
                      }else if (result.status ==1){
                          msg("解散失败");
                      }
                  },
                 error:function (error) {
                     console.log(error);
                     layer.msg("服务器异常");
                 }

                });
              //window.location.reload(true);
                  //layer.alert("解散成功");
                  //load(1);
          },
              function () {
              }
          );
      });


  });

  function load(pn){
      $("#tableStyle").empty();
      $("#ulList").empty();
      //页面加载数据
      ajax({
              type:"post",
              url:"${base_path}/team/findTeams",
              dataType:"json",
              data:{"page":pn,"token":$('#token').html()},
              success:function (result) {
                  if (result.status == 0){
                  if (result.data.teams.length <= 0)
                  {
                      $("#tableStyle").clear;
                      $("#tableStyle").html("还没有球队，请先创建！3秒后自动跳转");
                      setTimeout(function () {
                          window.location.href="${path}/team/create";
                      },3000)
                  }else {
                      var table = $('#tableStyle');
                      for (var i = 0; i < result.data.teams.length; i++) {
                          var $tr = $("<tr id='first'></tr>");
                          var $td1 = $("<td></td>");
                          var $td2 = $("<td></td>");
                          var $td3 = $("<td></td>");
                          var $td4 = $("<td></td>");
                          var $td5 = $("<td></td>");
                          var $td6 = $("<td ></td>");
                          var $center = $("<center></center>");
                          var  button1 = ("<button href=\"\" role=\"button\" class=\"btn3 btn3-success look\" data-toggle=\"modal\">查看</button>");
                          var  button2 = ("<button type=\"button\" style='margin: 0 10px '  class=\"btn3 btn3-warning update\">修改</button>");
                          var  button3 = ("<button type=\"button\"  class=\"btn3 btn3-danger btn3-sm fire\">解散</button>");

                          $center.append(button1);
                          $center.append(button2);
                          $center.append(button3);
                          $td6.append($center);
                          $td1.append(result.data.teams[i].idTeam);
                          $td2.append(result.data.teams[i].name);

                          if (result.data.coachList[i] != null){
                          $td3.append(result.data.coachList[i]);}
                          else {
                              $td3.append("未添加教练");
                              $td3.css("color","red");
                          }
                          if (result.data.teamSize[i] != 0){
                          $td4.append(result.data.teamSize[i]);}
                          else {
                              $td4.append("未添加球员");
                              $td4.css("color","red");
                          }
                          if (result.data.teams[i].status ==0){
                          $td5.append("在役");
                          }
                          $tr.append($td1);
                          $tr.append($td2);
                          $tr.append($td3);
                          $tr.append($td4);
                          $tr.append($td5);
                          $tr.append($td6);
                          table.append($tr);
                      }

                  var  $ulList =   $("#ulList");


                  var  $li2 = $("<li style='border-left: 1px solid #ddd'></li>");
                  var  $a2 = $('<a id="pageOne"></a>');
                  $a2.append( "首页");
                  $li2.append($a2);
                  var  $li3 = $("<li></li>");
                  var  $a3 = $('<a id="pagePre">&laquo;</a>');
                   $li3.append($a3);

                   if (result.data.pageInfo.hasPreviousPage == false){
                       $li2.addClass("disabled");
                       $li3.addClass("disabled");
                   }else {
                       $li2.click(function () {
                           load(1);
                       });
                       $li3.click(function () {
                           load(result.data.pageInfo.pageNum-1);
                       });
                   }

                   var  $li4 = $("<li></li>");
                   var  $a4 = $('<a id="pageNext">&raquo;</a>');
                   $li4.append($a4);

                   var  $li5 = $("<li></li>");
                   var  $a5 = $('<a id="pageLast"></a>');
                   $a5.append( "尾页");
                   $li5.append($a5);
                   if (result.data.pageInfo.hasNextPage == false){
                          $li4.addClass("disabled");
                          $li5.addClass("disabled");
                      }else {
                       $li4.click(function () {
                           load(result.data.pageInfo.pageNum+1);
                       });
                       $li5.click(function () {
                           load(result.data.pageInfo.pages);
                       })
                   }

                  //$ulList.append($li1);
                  $ulList.append($li2);
                  $ulList.append($li3);

                      //do
                      jQuery.each(result.data.pageInfo.navigatepageNums,function (index,item) {
                          var  $li6 = $("<li></li>");
                          var  $a6 = $('<a id="page1" ></a>');
                          if (result.data.pageInfo.pageNum == item){
                             $li6.attr("class","active");
                          }
                          $a6.click(function () {
                              load(item);
                          });
                          $a6.append(item);
                          $li6.append($a6);
                          $ulList.append($li6);
                      });

                  $ulList.append($li4);
                  $ulList.append($li5);
                  }
              }else if (result.status ==1 ){
                      msg("查询失败，请刷新页面");
                  }
              },
              error:function (error) {
                  //错误页面
                  console.log(error);
              }
      });

  };

//切换用户显示
  function userOne() {
      var a = $('#user2');
         a.css('display',"block");
  }
  function userTwo() {
      var a = $('#user2');
      a.css('display',"none");
  }
  $("[data-toggle='tooltip']").tooltip();
</script>

</html>
