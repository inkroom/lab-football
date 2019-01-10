<@p.index setReferUrl=true>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/lib/css/bootstrap.min.css" rel="stylesheet">
    <link href="/lib/js/layer/2.1/skin/layer.css" rel="stylesheet">
    <link href="/lib/js/layer/2.1/skin/layer.ext.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style>
        .fixed{
            width: 90px;
            background-color: #ffffff;
            z-index: 55555;
            position: relative;
        }
        .line{
            background-image: url("images/line/line.png");
            background-size: 100% 100%;
            position: relative;
            padding: 0;
        }
        .line-div1{
            width: 50%;
            position: absolute;
            right: 0;
            left: 50%;
            top: 0;
        }
        .line-div2{
            width: 50%;
            bottom: 0;
            position: absolute;
            left: 0;
        }
        .table td{
            text-align: center;
        }
    </style>
</head>
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
	    <div class="page-container">
	        <div class="mt-20">
				<table id="teacher" class="table table-bordered ">
        <input name="token" value="${token }" hidden="true">
            <tbody  class="teacher">
                <tr><td class="text-center" colspan="120"><h3>学校体育工作年度报表</h3></td></tr>
                <!-- 基础数据 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;" colspan="120">基础数据</td>
                </tr>
                <tr class="width-12">
                    <td colspan="15">学校类别</td>
                    <td colspan="15">
                    ${schoolType?if_exists}
                    </td>
                    <td colspan="15">学校数（所）</td>
                    <td colspan="15">
                    1
                    </td>
                    <td colspan="15">在校生人数</td>
                    <td colspan="15">${numOfStus?if_exists}</td>
                    <td colspan="15">实际班级数量</td>
                    <td colspan="15">${actualClassNumber?if_exists}</td>
                </tr>
                <!-- 基础数据 end -->
                <!-- 开课及课外活动 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;" colspan="120">开课及课外活动</td>
                </tr>
                <tr class="width-17">
                    <td colspan="20">体育课开足学校数</td>
                    <td colspan="20">
					${enoughPE?if_exists}
					</td>
                    <td colspan="20">落实每天一小时体育锻炼学校数</td>
                    <td colspan="20">
                    ${onehourPE?if_exists}
                    </td>
                    <td colspan="20">组织大课间体育活动学校数</td>
                    <td colspan="20">
                    ${numActivityInClass?if_exists}
                    </td>
                </tr> 
                <!-- 开课及课外活动 end -->
                <!-- 体育师资 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;" colspan="120">体育师资</td>
                </tr>
                <tr class="width-20">
                    <td colspan="24" rowspan="2">体育教师人数</td>
                    <td colspan="24">专职</td>
                    <td colspan="24">
                    ${numFulltimeTeachers?if_exists}
                    </td>
                    <td colspan="24">体育教师参训人数</td>
                    <td colspan="24">
                    ${numPhysicalTeachers?if_exists}
                    </td>
                </tr>
               <tr class="width-20">
                    <td colspan="24">兼职</td>
                    <td colspan="24">
                    ${numParttimeTeachers?if_exists}
                    </td>
                    <td colspan="24">教师受县级以上表彰人数</td>
                    <td colspan="24">
                    ${numRecognitionTeachers?if_exists}
                    </td>
                </tr>
                <tr class="width-17">
                    <td colspan="20">体育教师缺额数</td>
                    <td colspan="20">
                    ${sportsTeacherVacancyNum?if_exists}
                    </td>
                    <td colspan="20">体育教师缺额比（%）</td>
                    <td colspan="20">
                    ${sportsTeacherVacancyRatio?if_exists}
                    </td>
                    <td colspan="20">体育教师生师比（%）</td>
                    <td colspan="20">
                    ${ratioOfStudentsToTeachers?if_exists}
                    </td>
                </tr>
                <!-- 体育师资 end -->
                <!-- 场地设施 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;" colspan="120">场地设施</td>
                </tr>
                <tr class="width-17">
                    <td colspan="20" rowspan="4">田径场（块）</td>
                    <td colspan="20">200米</td>
                    <td colspan="20">
                    ${length2?if_exists}
                    </td>
                    <td colspan="20" rowspan="2">体育馆</td>
                    <td colspan="20">个数</td>
                    <td colspan="20">
                     ${sportHall?if_exists}
                    </td>
                </tr>
               <tr class="width-17">
                    <td colspan="20">300米</td>
                    <td colspan="20">
                    ${length3?if_exists}
                    </td>
                    <td colspan="20">总面积（平方米）</td>
                    <td colspan="20">
                    ${sportHallArea?if_exists}
                    </td>
                </tr>
                <tr class="width-17">
                    <td colspan="20">300米 至 400米</td>
                    <td colspan="20">
                    ${length3t4?if_exists}
                    </td>
                    <td colspan="20" rowspan="2">游泳馆</td>
                    <td colspan="20">个数</td>
                    <td colspan="20">
                    ${swimPool?if_exists}
                    </td>
                </tr>
                <tr class="width-17">
                    <td colspan="20">400米</td>
                    <td colspan="20">
                    ${length4?if_exists}
                    </td>
                    <td colspan="20">总面积（平方米）</td>
                    <td colspan="20">
                    ${swimPoolArea?if_exists}
                    </td>
                </tr>
                <tr class="width-17">
                    <td colspan="20">器械体操/游戏区面积（平方米）</td>
                    <td colspan="20">
                    ${gameArea?if_exists}
                    </td>
                    <td colspan="20">学生体质测试室（个）</td>
                    <td colspan="20">
                    ${testRoom?if_exists}
                    </td>
                    <td colspan="20">体育器材达标学校数</td>
                    <td colspan="20">
                    ${storard?if_exists}
                    </td>
                </tr>
                <tr class="width-25">
                    <td colspan="30">篮球场（块）</td>
                    <td colspan="30">
                    ${basketball?if_exists}
                    </td>
                    <td colspan="30">排球场（块）</td>
                    <td colspan="30">
                    ${volleyball?if_exists}
                    </td>
                </tr>
                <!-- 场地设施 end -->
                <!-- 学校体育工作等级评估 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;" colspan="120">学校体育工作等级评估</td>
                </tr>
                 <tr class="width-25">
                    <td colspan="30">优秀</td>
                    <td colspan="30">
                    <#if selfRatingScale?exists && selfRatingScale=="1">
                    	<font color="red">√</font>
                    </#if>
                    </td>
                    <td colspan="30">良好</td>
                    <td colspan="30">
                    <#if selfRatingScale?exists && selfRatingScale=="2">
                    	<font color="red">√</font>
                    </#if>
                    </td>
                </tr>
                 <tr class="width-25">
                    <td colspan="30">及格</td>
                    <td colspan="30">
                    <#if selfRatingScale?exists && selfRatingScale=="3">
                    	<font color="red">√</font>
                    </#if>
                    </td>
                    <td colspan="30">不及格</td>
                    <td colspan="30">
                    <#if selfRatingScale?exists && selfRatingScale=="4">
                    	<font color="red">√</font>
                    </#if>
                    </td>
                </tr>
                <tr class="width-25">
                    <td colspan="30">有加分项目的学校数（个）</td>
                    <td colspan="90">
                    ${isAddPointProject?if_exists}
                    </td>
                </tr>
                <!-- 学校体育工作等级评估 end -->
                <!-- 体育经费 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;"   colspan="120">体育经费</td>
                </tr>
                <tr class="width-33">
                    <td colspan="40" rowspan="4" align="center" valign="middle">学校体育经费支出情况（万元）</td>
                    <td colspan="40">支出总额</td>
                    <td colspan="40">
                    ${expense?if_exists}
                    </td>
                </tr>
                <tr class="width-33">
                    <td colspan="40">体育场地经费支出</td>
                    <td colspan="40">
                    ${expenseField?if_exists}
                    </td>
                </tr>
                <tr class="width-33">
                    <td colspan="40">体育专用器材经费支出</td>
                    <td colspan="40">
                    ${expenseEquip?if_exists}
                    </td>
                </tr>
                <tr class="width-33">
                    <td colspan="40">体育工作经费</td>
                    <td colspan="40">
                    ${expenseActivity?if_exists}
                    </td>
                </tr>
                <!-- 体育经费 end -->
                <!-- 制订体育活动意外伤害保障措施 begin -->
                <tr>
                    <td class="text-center" style="background-color:gray;color:white;" colspan="120">制订体育活动意外伤害保障措施</td>
                </tr>
                <tr class="width-25">
                    <td colspan="30">是</td>
                    <td colspan="30">
                    <#if isInsurance?exists && isInsurance=='1'>
                    	<font color="red">√</font>
                    </#if>
                    </td>
                    <td colspan="30">否</td>
                    <td colspan="30">
                    <#if isInsurance?exists && isInsurance=='2'>
                    	<font color="red">√</font>
                    </#if>
                    </td>
                </tr>
                <!-- 制订体育活动意外伤害保障措施 end -->
            </tbody>
        </table>
			</div>
		</div>
		<div class=" col-sm-12 text-c">
		    <button type="button" class="btn btn-success radius" onclick="window.location.href=('../fileDownloadView.action')"><strong>返&nbsp回</strong></button>
			<button type="button" class="btn btn-success radius" onclick="window.location.href=('../sport/sportFirstView.action')"><strong>修&nbsp改</strong></button>
		</div>
</div>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>
<script src="../lib/js/layer/2.1/extend/layer.ext.js"></script>
<script>
    $(window).scroll(function(){
        console.log($(document).scrollLeft() );
        $('.fixed').css({position: 'relative',left:$(document).scrollLeft()});
        if($(document).scrollLeft()<90){
            $('.fixed').css({position: 'relative',left:$(document).scrollLeft()});
        }
    });
</script>


</@p.index>