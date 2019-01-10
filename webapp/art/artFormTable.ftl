<@p.index setReferUrl=true>

<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../lib/css/bootstrap.min.css" rel="stylesheet">
    <link href="../lib/js/layer/2.1/skin/layer.css" rel="stylesheet">
    <link href="../lib/js/layer/2.1/skin/layer.ext.css" rel="stylesheet">
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
	        <div class="col-xs-12 text-center">
	            <h4 class="title">艺术报表信息</h4>
	        </div>
	        <div class="mt-20">
<table id="teacher" class="table table-bordered ">
				<tbody class="teacher">
					<tr>
						<td class="text-center" colspan="120"><h3>学校艺术工作年度报表</h3></td>
					</tr>
					<!-- 基础数据 begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">基础数据</td>
					</tr>
					<tr class="width-12">
						<td colspan="20">学校数量（所）</td>
						<td colspan="20">1</td>
						<td colspan="20">班级数量</td>
						<td colspan="20" name="classNum">${classNum?if_exists}</td>
						<td colspan="20">学生数量</td>
						<td colspan="20" name="studentNum">${studentNum?if_exists}</td>
					</tr>
					<!-- 基础数据 end -->
					<!-- 每周艺术课程begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">每周艺术课程</td>
					</tr>
					<tr class="width-8">
						<td colspan="20">音乐课时</td>
						<td colspan="20" name="musicClass">${musicClass?if_exists}</td>
						<td colspan="20">美术课时</td>
						<td colspan="20" name="artClass">${artClass?if_exists}</td>
						<td colspan="20">综合艺术课时</td>
						<td colspan="20" name="integratedArtClass">${integratedArtClass?if_exists}</td>
					</tr>
					<tr class="width-8">
						<td colspan="15">艺术课时(地方/学校)</td>
						<td colspan="15" name="localArtClass">${localArtClass?if_exists}</td>
						
						<#if enoughClass?exists>
							<td colspan="15" >开启开足</td>
							<#if enoughClass=="1">
							<td colspan="15">√</td>
								<td colspan="15">未开启开足</td>
								<td colspan="15"></td>
						    <#else>
							<td colspan="15"></td>
								<td colspan="15">未开启开足</td>
								<td colspan="15">√</td>
						    </#if>		
						<#else>
							<td colspan="15">开启开足</td>
							<td colspan="15" ></td>
							<td colspan="15">未开启开足</td>
							<td colspan="15" ></td>
						</#if>	
						<td colspan="15">自评得分</td>
						<td colspan="15" name="srscroeClass">${srscroeClass?if_exists}</td>
					</tr>
					<!-- 每周艺术课程end -->
					<!-- 艺术活动 begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">艺术活动</td>
					</tr>
					<tr class="width-8">
						<td colspan="12">学校每年开展艺术节场次(次/年)</td>
						<td colspan="12" name="screenings">${screenings?if_exists}</td>
						<td colspan="12">学校每周开展活动频次(次/周)</td>
						<td colspan="12" name="frequency">${frequency?if_exists}</td>
						<td colspan="12">艺术社团数量</td>
						<td colspan="12" name="artSocieties">${artSocieties?if_exists}</td>
						<td colspan="12">艺术活动学生参与面（%）</td>

						<#if participating?exists>
							<td colspan="12" name="participating">${participating?if_exists}</td>
						<#else>
							<td colspan="12"></td>
						</#if>
						
						<td colspan="12">自评得分</td>
						<td colspan="12" name="srscroeActivity">${srscroeActivity?if_exists}</td>
					</tr>
					<!-- 艺术活动 end -->
					<!-- 艺术教师begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">艺术教师</td>
					</tr>
					<tr class="width-16">
						<td colspan="24" rowspan="2">音乐教师</td>
						<td colspan="16">应配专职数</td>
						<td colspan="16" name="musicShouldTeacher">${musicShouldTeacher?if_exists}</td>
						<td colspan="16">实有专职数</td>
						<td colspan="16" name="musicRealTeacher">${musicRealTeacher?if_exists}</td>
						<td colspan="16">周课时数</td>
						<td colspan="16" name="musicClassNum">${musicClassNum?if_exists}</td>
					</tr>
					<tr class="width-16">
						<td colspan="16">缺额专职数</td>
						<td colspan="16" name="musicGapsTeacher">${musicGapsTeacher?if_exists}</td>
						<td colspan="16">兼职教师数</td>
						<td colspan="16" name="musicPartTimeTeacher">${musicPartTimeTeacher?if_exists}</td>
						<td colspan="16">生师比</td>
						<td colspan="16" name="musicRatioST">${musicRatioST?if_exists}</td>
					</tr>
					<tr class="width-16">
						<td colspan="24" rowspan="2">美术教师</td>
						<td colspan="16">应配专职数</td>
						<td colspan="16"  name="artShouldTeacher">${artShouldTeacher?if_exists}</td>
						<td colspan="16">实有专职数</td>
						<td colspan="16" name="artRealTeacher">${artRealTeacher?if_exists}</td>
						<td colspan="16">周课时数</td>
						<td colspan="16" name="artClassNum">${artClassNum?if_exists}</td>
					</tr>
					<tr class="width-16">
						<td colspan="16">缺额专职数</td>
						<td colspan="16" name="artGapsTeacher">${artGapsTeacher?if_exists}</td>
						<td colspan="16">兼职教师数</td>
						<td colspan="16" name="artPartTimeTeacher">${artPartTimeTeacher?if_exists}</td>
						<td colspan="16">生师比</td>
						<td colspan="16" name="artRatioST">${artRatioST?if_exists}</td>
					</tr>
					<tr class="width-16">
						<td colspan="20">参加县级以上培训人数</td>
						<td colspan="20" name="train">${train?if_exists}</td>
						<td colspan="20">荣获县级以上表彰人数</td>
						<td colspan="20" name="honor">${honor?if_exists}</td>
						<td colspan="20">自评得分</td>
						<td colspan="20" name="selfAssessment">${selfAssessment?if_exists}</td>
					</tr>
					<!-- 艺术教师 end -->
					<!-- 条件保障 begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">条件保障</td>
					</tr>
					<tr class="width-17">
						<td colspan="20" rowspan="3">音乐专用教室（间）</td>
						<td colspan="20">应配</td>
						<td colspan="20" name="musClassroomIdeal">${musClassroomIdeal?if_exists}</td>
						<td colspan="20" rowspan="3">美术专用教室（间）</td>
						<td colspan="20">应配</td>
						<td colspan="20" name="paintClassroomIdeal">${paintClassroomIdeal?if_exists}</td>
					</tr>
					<tr class="width-17">
						<td colspan="20">实有</td>
						<td colspan="20" name="musClassroomPresent">${musClassroomPresent?if_exists}</td>
						<td colspan="20">实有</td>
						<td colspan="20" name="paintClassroomPresent">${paintClassroomPresent?if_exists}</td>
					</tr>
					<tr class="width-17">
						<td colspan="20">缺额</td>
						<td colspan="20" name="musClassroomPequired">${musClassroomPequired?if_exists}</td>
						<td colspan="20">缺额</td>
						<td colspan="20" name="paintPequired">${paintPequired?if_exists}</td>
					</tr>
					<tr class="width-17">
						<td colspan="20">其他艺术活动室（间）</td>
						<td colspan="20" name="artClassroomOther">${artClassroomOther?if_exists}</td>
						<td colspan="20">艺术场馆（个）</td>
						<td colspan="20" name="artVenuesNum">${artVenuesNum?if_exists}</td>
						<td colspan="20">场馆面积（㎡）</td>
						<td colspan="20" name="venuesArea">${venuesArea?if_exists}</td>
					</tr>
					<tr class="width-17">
						
						
						<#if isEquipQualified?exists>
						   <#if isEquipQualified=="1">
						    <td colspan="20">器材未配备达标</td>
							<td colspan="20"></td>
							<td colspan="20">资金缺额</td>
						    <td colspan="20" ></td>
							<td colspan="20">器材配备达标</td>
							<td colspan="20">√</td>
						  <#else>
						    <td colspan="20">器材未配备达标</td>
							<td colspan="20">√</td>
							<td colspan="20">资金缺额</td>
						    <td colspan="20" name="fundRequired">${fundRequired?if_exists}</td>
							<td colspan="20">器材配备达标</td>
							<td colspan="20"></td>
						</#if>
						<#else>
						    <td colspan="20">器材配备达标</td>
							<td colspan="20"></td>
							<td colspan="20">资金缺额</td>
						    <td colspan="20" ></td>
							<td colspan="20">器材未配备达标</td>
							<td colspan="20"></td>
						</#if>
						
					</tr>
					<tr class="width-25">
						<td colspan="80"></td>
						<td colspan="20">自评得分</td>
						<td colspan="20" name="selfRemarkEnsurance">${selfRemarkEnsurance?if_exists}</td>
					</tr>
					<!-- 条件保障 end -->
					<!-- 特色发展 begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">特色发展</td>
					</tr>
					<tr class="width-17">
					
					    <#if features?exists>
						   <#if features=="1">
						       <td colspan="20">具有特色</td>
								<td colspan="20">√</td>
								<td colspan="20">不具特色</td>
								<td colspan="20"></td>
							 <#else>
							   <td colspan="20">具有特色</td>
								<td colspan="20"></td>
								<td colspan="20">不具特色</td>
								<td colspan="20">√</td>
						   </#if>
						  <#else>
						    <td colspan="20">具有特色</td>
							<td colspan="20"></td>
							<td colspan="20">不具特色</td>
							<td colspan="20"></td>
						</#if>
					
						<td colspan="20">自评得分</td>
						<td colspan="20" name="evaluationOne">${evaluationOne?if_exists}</td>
					</tr>
					<!-- 特色发展 end -->
					<!-- 艺术素质测评 begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">艺术素质测评</td>
					</tr>
					<tr class="width-33">
						
						<#if assessment?exists>
						   <#if assessment=="1">
						      <td colspan="20">开展测评</td>
								<td colspan="20">√</td>
								<td colspan="20">未开展测评</td>
								<td colspan="20"></td>
							 <#else>
							  <td colspan="20">开展测评</td>
								<td colspan="20"></td>
								<td colspan="20">未开展测评</td>
								<td colspan="20">√</td>
						   </#if>
						  <#else>
						   <td colspan="20">开展测评</td>
							<td colspan="20"></td>
							<td colspan="20">未开展测评</td>
							<td colspan="20"></td>
						</#if>
					
						
						
						
					
						<td colspan="20">测评覆盖面（%）</td>
						<td colspan="20"></td>
					</tr>
					<tr class="width-33">
						<td colspan="20" rowspan="2">优秀等级</td>
						<td colspan="20">学生数</td>
						<td colspan="20">${good?if_exists}</td>
						<td colspan="20" rowspan="2">良好等级</td>
						<td colspan="20">学生数</td>
						<td colspan="20">${fine?if_exists}</td>
					</tr>
					<tr class="width-33">
						
							<td colspan="20">占比（%）</td>
							<td colspan="20">${goodRatio?if_exists}</td>

							<td colspan="20">占比（%）</td>
							<td colspan="20">${fineRatio?if_exists}</td>
						
					</tr>
					<tr class="width-33">
						<td colspan="20" rowspan="2">合格等级</td>
						<td colspan="20">学生数</td>
						<td colspan="20">${qualified?if_exists}</td>
						<td colspan="20" rowspan="2">不合格等级</td>
						<td colspan="20">学生数</td>
						<td colspan="20">${notQualified?if_exists}</td>
					</tr>
					<tr class="width-33">
						
							<td colspan="20">占比（%）</td>
							<td colspan="20">${qualifiedRatio?if_exists}</td>
					
						
							<td colspan="20">占比（%）</td>
							<td colspan="20">${notQualifiedRatio?if_exists}</td>
						
					</tr>
					<tr class="width-25">
						<td colspan="80"></td>
						<td colspan="20">自评得分</td>
						<td colspan="20">${artEvaluation?if_exists}</td>
					</tr>
					<!-- 艺术素质测评end -->
					<!-- 教研员begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">教研员(行政部门填写)</td>
					</tr>
					<tr class="width-20">
						<td colspan="15">专职音乐</td>
						<td colspan="15"></td>
						<td colspan="15">兼职音乐</td>
						<td colspan="15"></td>
						<td colspan="15">专职美术</td>
						<td colspan="15"></td>
						<td colspan="15">兼职美术</td>
						<td colspan="15"></td>
					</tr>
					<!-- 教研员(行政部门填写)end -->
					<!-- 艺术教育经费支出情况begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">艺术教育经费支出情况（万元）</td>
					</tr>
					<tr class="width-20">
						<td colspan="20">专用教室/艺术场馆建设支出</td>
						<td colspan="20">${classRoomPay?if_exists}</td>
						<td colspan="20">艺术教育器材支出</td>
						<td colspan="20">${artEdaPay?if_exists}</td>
						<td colspan="20">艺术活动支出</td>
						<td colspan="20">${artPay?if_exists}</td>
					</tr>
					<!-- 艺术教育经费支出情况end -->
					<!-- 自评复评结果begin-->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">自评复评结果</td>
					</tr>
					<tr>
					<tr class="width-25">
						<td colspan="72"></td>
						<td colspan="24">得分总和</td>
						<td colspan="24" >${addScore?if_exists}</td>
					</tr>
					<tr class="width-25">
						<td colspan="24" rowspan="2">等级</td>
						<td colspan="24">优秀</td>
						<#if addType=="1">
							<td colspan="24">√</td>
						<#else>
							<td colspan="24"></td>
						</#if>
					
						<td colspan="24">良好</td>
						<#if addType=="2">
							<td colspan="24">√</td>
						<#else>
							<td colspan="24"></td>
						</#if>
					</tr>
					<tr class="width-25">
						<td colspan="24">合格</td>
						<#if addType=="3">
							<td colspan="24">√</td>
						<#else>
							<td colspan="24"></td>
						</#if>
						<td colspan="24">不合格</td>
						<#if addType=="4">
							<td colspan="24">√</td>
						<#else>
							<td colspan="24"></td>
						</#if>
					</tr>
					<!-- 自评复评结果 end -->
					<!-- 艺术综合信息 begin -->
					<tr>
						<td class="text-center"
							style="background-color: gray; color: white;" colspan="120">艺术综合信息</td>
					</tr>
					<tr class="width-25">
						<td colspan="30">地方艺术课程名称：</td>
						<#if artCousreNames?exists>
							<td colspan="90" style="text-align:left">${artCousreNames?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">学生艺术社团项目名称：</td>
						<#if artClubName?exists>
							<td colspan="90" style="text-align:left">${artClubName?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">校园文化艺术环境基本情况：</td>
						<#if artCultureAtmosphere?exists>
							<td colspan="90" style="text-align:left">${artCultureAtmosphere?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">其他艺术活动室或艺术场馆名称:</td>
						<#if artVenueNamesOther?exists>
							<td colspan="90" style="text-align:left">${artVenueNamesOther?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">学校艺术教育特色发展成果:</td>
						
						<#if achieveArtFeature?exists>
							<td colspan="90" style="text-align:left">${achieveArtFeature?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">艺术课程存在的主要问题</td>
						
						<#if artCurriculumProblems?exists>
							<td colspan="90" style="text-align:left">${artCurriculumProblems?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					<tr class="width-25" height="100px">
						<td colspan="30">艺术课程存在的主要问题的改进措施</td>
						<#if artCurriculumImprovement?exists>
							<td colspan="90" style="text-align:left">${artCurriculumImprovement?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						 
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">艺术活动存在的主要问题</td>
						<#if artActivityProblems?exists>
							<td colspan="90" style="text-align:left">${artActivityProblems?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						 
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">艺术活动存在的主要问题的改进措施</td>
						<#if artActivityImprovement?exists>
							<td colspan="90" style="text-align:left">${artActivityImprovement?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">艺术教师存在的主要问题</td>
						
						<#if artTeacherProblems?exists>
							<td colspan="90" style="text-align:left">${artTeacherProblems?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">艺术教师存在的问题的改进策略</td>
						
						<#if artTeacherImprovement?exists>
							<td colspan="90" style="text-align:left">${artTeacherImprovement?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">条件保障存在的主要问题</td>
						
						<#if conditionGuaranteeProblems?exists>
							<td colspan="90" style="text-align:left">${conditionGuaranteeProblems?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">条件保障改进策略</td>
						
						<#if conditionGuaranteeImprovement?exists>
							<td colspan="90" style="text-align:left">${conditionGuaranteeImprovement?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">特色发展存在的主要问题</td>
						
						<#if characteristicProblems?exists>
							<td colspan="90" style="text-align:left">${characteristicProblems?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">特色发展的改进策略</td>
						<#if characteristicImprovement?exists>
							<td colspan="90" style="text-align:left">${characteristicImprovement?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">学生艺术素质测评存在的主要问题</td>
						<#if assessmentProblems?exists>
							<td colspan="90" style="text-align:left">${assessmentProblems?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<tr class="width-25" height="100px">
						<td colspan="30">学生艺术素质测评的改进策略</td>
						
						<#if assessmentImprovement?exists>
							<td colspan="90" style="text-align:left">${assessmentImprovement?if_exists}</td>
						<#else>
							<td colspan="90"></td>
						</#if>
						
					</tr>
					<!-- 艺术综合信息 end -->
				</tbody>
			</table>
			</div>
		</div>
		<div class=" col-sm-12 text-c">
		    <button type="button" class="btn btn-success radius" onclick="window.location.href=('../fileDownloadView.action')"><strong>返&nbsp回</strong></button>
			<button type="button" class="btn btn-success radius" onclick="window.location.href=('../art/artFirstView.action')"><strong>修&nbsp改</strong></button>
		</div>
</div>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
<script src="../lib/layer/2.1/layer.js"></script>
<script src="../lib/js/layer/2.1/extend/layer.ext.js"></script>
<script>x

    
    $(window).scroll(function(){
        console.log($(document).scrollLeft() );
        $('.fixed').css({position: 'relative',left:$(document).scrollLeft()});
        if($(document).scrollLeft()<90){
            $('.fixed').css({position: 'relative',left:$(document).scrollLeft()});
        }
    });
</script>


</@p.index>