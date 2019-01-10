<@p.index setReferUrl=true>
<style>
	.form-control{
		width:100%;
	}
</style>
<form action="sportSelf.action" method="post" id= "idForm" onSubmit="validateForm(this)">
<div class="container">
    <div class="page-container">
        <table id="teacher" class="table table-bordered ">
            <tbody class="teacher ">
                <tr><td class="text-center" colspan="12"><h3>中小学校体育工作评估自评结果报表</h3></td></tr>
                <tr>
                    <td colspan="2">单位全称(公章):	</td>
                    <td colspan="10" align="center">${unitName?if_exists}</td>
                </tr>
                <tr class="width-16">
                    <td colspan="2">校长</td><td colspan="2">${headMaster?if_exists}</td>
                    <td colspan="2">主管校长</td><td colspan="2">${competentPresident?if_exists}</td>
                    <td colspan="2">电话</td><td  colspan="2">${masterPhone?if_exists}</td>
                </tr>
                <tr>
                    <td colspan="2">教务(体育)主任</td><td colspan="2">${educateDirector?if_exists}</td>
                    <td colspan="2">体育主管</td><td colspan="2">${sportsDirector?if_exists}</td>
                    <td colspan="2">电话</td><td colspan="2">${directorPhone?if_exists}</td>
                </tr>
                <tr>
                    <td colspan="2">体育教师数</td><td colspan="2">${sportsTeacherNum?if_exists}</td>
                    <td colspan="2">体育教师缺额人数</td><td colspan="2">${sportsTeacherVacancyNum?if_exists}</td>
                    <td colspan="2">体育教师平均周课时</td><td colspan="2">${teacherEveryCourses?if_exists}</td>
                </tr>
                <tr class="width-8">
                    <td>年级 </td>
                    <td>班级数</td>
                    <td>学生人数</td>
                    <td>年级</td>
                    <td>班级数</td>
                    <td>学生人数</td>
                    <td>年级</td>
                    <td>班级数</td>
                    <td>学生人数</td>
                    <td>年级</td>
                    <td>班级数</td>
                    <td>学生人数</td>
                </tr>
                <tr class="width-8">
                    <td>一年级</td>
                    <td>${the1Grade?if_exists}</td>
                    <td>${the1GradeNum?if_exists}</td>
                    <td>四年级</td>
                    <td>${the4Grade?if_exists}</td>
                    <td>${the4GradeNum?if_exists}</td>
                    <td>七年级</td>
                    <td>${the7Grade?if_exists}</td>
                    <td>${the7GradeNum?if_exists}</td>
                    <td>高一</td>
                    <td>${the1Higher?if_exists}</td>
                    <td>${the1HigherNum?if_exists}</td>
                </tr>
                <tr class="width-8">
                    <td>二年级</td>
                    <td>${the2Grade?if_exists}</td>
                    <td>${the2GradeNum?if_exists}</td>
                    <td>五年级</td>
                    <td>${the5Grade?if_exists}</td>
                    <td>${the5GradeNum?if_exists}</td>
                    <td>八年级</td>
                    <td>${the8Grade?if_exists}</td>
                    <td>${the8GradeNum?if_exists}</td>
                    <td>高二</td>
                    <td>${the2Higher?if_exists}</td>
                    <td>${the2HigherNum?if_exists}</td>
                </tr>
                <tr class="width-8">
                    <td>三年级</td>
                    <td>${the3Grade?if_exists}</td>
                    <td>${the3GradeNum?if_exists}</td>
                    <td>六年级</td>
                    <td>${the6Grade?if_exists}</td>
                    <td>${the6GradeNum?if_exists}</td>
                    <td>九年级</td>
                    <td>${the9Grade?if_exists}</td>
                    <td>${the9GradeNum?if_exists}</td>
                    <td>高三</td>
                    <td>${the3Higher?if_exists}</td>
                    <td>${the3HigherNum?if_exists}</td>
                </tr>
                <tr class="text-center">
                    <td colspan="12"><h4>各项指标评估结果</h4></td>
                </tr>
                
                <tr>
                    <td>序号</td><td class="text-center" colspan="5">评估指标内容(要点)</td>
                    <td>得分</td>
                    <td colspan="5" class="text-center">存在主要问题</td>
                </tr>
                
                
                
                
                <tr class="text-center">
                    <td>1</td>
                    <td class="text-center" colspan="5">成立领导小组，定期研究工作 (2分)</td>
                    <td>${item1Num?if_exists}</td>
                    <td colspan="5" >${item1Str?if_exists}</td>
                </tr>
                
                <tr class="text-center">
                    <td>2</td>
                    <td class="text-center" colspan="5">将体育纳入学校整体工作计划(2分) </td>
                    <td>${item2Num?if_exists}</td>
                    <td colspan="5" >${item2Str?if_exists}</td>
                </tr>
                
               <tr class="text-center">
                    <td>3</td>
                    <td class="text-center" colspan="5">建立意外伤害应急管理机制(1分) </td>
                    <td>${item3Num?if_exists}</td>
                    <td colspan="5" >${item3Str?if_exists}</td>
                </tr>
                 <tr class="text-center">
                    <td>4</td>
                    <td class="text-center" colspan="5">校长将学校体育列入工作职责（1分）</td>
                    <td>${item4Num?if_exists}</td>
                    <td colspan="5" >${item4Str?if_exists}</td>
                </tr>
                
                
               
                 <tr class="text-center">
                    <td>5</td>
                    <td class="text-center" colspan="5">校长，分管校长听体育课次数（2分） </td>
                    <td>${item5Num?if_exists}</td>
                    <td colspan="5" >${item5Str?if_exists}</td>
                </tr>
                
              
                 <tr class="text-center">
                    <td>6</td>
                    <td class="text-center" colspan="5">严格落实体育与健康课时规定（7分） </td>
                    <td>${item6Num?if_exists}</td>
                    <td colspan="5" >${item6Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>7</td>
                    <td class="text-center" colspan="5">公布阳光体育运动工作方案（2分） </td>
                    <td>${item7Num?if_exists}</td>
                    <td colspan="5" >${item7Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>8</td>
                    <td class="text-center" colspan="5">每学期通报学生体育活动情况（3分）</td>
                    <td>${item8Num?if_exists}</td>
                    <td colspan="5" >${item8Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>9</td>
                    <td class="text-center" colspan="5">体育教学计划，单元计划等齐全（4分）</td>
                    <td>${item9Num?if_exists}</td>
                    <td colspan="5" >${item9Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>10</td>
                    <td class="text-center" colspan="5">依据课程标准组织体育教学（5分）</td>
                    <td>${item10Num?if_exists}</td>
                    <td colspan="5" >${item10Str?if_exists}</td>
                </tr>
                
                 <tr class="text-center">
                    <td>11</td>
                    <td class="text-center" colspan="5">加强教学研究与课程教学改革（3分）</td>
                    <td>${item11Num?if_exists}</td>
                    <td colspan="5" >${item11Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>12</td>
                    <td class="text-center" colspan="5">严格提醒体育考核，考勤制度（3分）</td>
                    <td>${item12Num?if_exists}</td>
                    <td colspan="5" >${item12Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>13</td>
                    <td class="text-center" colspan="5">制定阳光体育运动工作方案（2分）</td>
                    <td>${item13Num?if_exists}</td>
                    <td colspan="5" >${item13Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>14</td>
                    <td class="text-center" colspan="5">将校园体育活动纳入教学计划（2分）</td>
                    <td>${item14Num?if_exists}</td>
                    <td colspan="5" >${item14Str?if_exists}</td>
                </tr>
                
                 <tr class="text-center">
                    <td>15</td>
                    <td class="text-center" colspan="5">落实大课间体育活动等时间（3分）</td>
                    <td>${item15Num?if_exists}</td>
                    <td colspan="5" >${item15Str?if_exists}</td>
                </tr>
                
                <tr class="text-center">
                    <td>16</td>
                    <td class="text-center" colspan="5">学校每年召开春季，秋季运动会（3分）</td>
                    <td>${item16Num?if_exists}</td>
                    <td colspan="5" >${item16Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>17</td>
                    <td class="text-center" colspan="5">85%的学生掌握至少2项体育技能（4分）</td>
                    <td>${item17Num?if_exists}</td>
                    <td colspan="5" >${item17Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>18</td>
                    <td class="text-center" colspan="5">对学生加强体育安全教育（1分）</td>
                    <td>${item18Num?if_exists}</td>
                    <td colspan="5" >${item18Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>19</td>
                    <td class="text-center" colspan="5">体育教师数量达到规定标准（3分）</td>
                    <td>${item19Num?if_exists}</td>
                    <td colspan="5" >${item19Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>20</td>
                    <td class="text-center" colspan="5">体育教师职评聘公平，公正（3分）</td>
                    <td>${item20Num?if_exists}</td>
                    <td colspan="5" >${item20Str?if_exists}</td>
                </tr>
                  <tr class="text-center">
                    <td>21</td>
                    <td class="text-center" colspan="5">体育教师工资待遇、工作服装（2分）</td>
                    <td>${item21Num?if_exists}</td>
                    <td colspan="5" >${item21Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>22</td>
                    <td class="text-center" colspan="5">体育活动、测试纳入教学工作量（2分）</td>
                    <td>${item22Num?if_exists}</td>
                    <td colspan="5" >${item22Str?if_exists}</td>
                </tr>
                  <tr class="text-center">
                    <td>23</td>
                    <td class="text-center" colspan="5">体育教师集体备课、本校教研（2分）</td>
                    <td>${item23Num?if_exists}</td>
                    <td colspan="5" >${item23Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>24</td>
                    <td class="text-center" colspan="5">体育教师参加培训，继续教育（2分）</td>
                    <td>${item24Num?if_exists}</td>
                    <td colspan="5" >${item24Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>25</td>
                    <td class="text-center" colspan="5">体育场地、器材、设备达标（1分）</td>
                    <td>${item25Num?if_exists}</td>
                    <td colspan="5" >${item25Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>26</td>
                    <td class="text-center" colspan="5">体育场地平整，整洁，符合要求（2分）</td>
                    <td>${item26Num?if_exists}</td>
                    <td colspan="5" >${item26Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>27</td>
                    <td class="text-center" colspan="5">体育场馆管理规范，安全运行（2分）</td>
                    <td>${item27Num?if_exists}</td>
                    <td colspan="5" >${item27Str?if_exists}</td>
                </tr>
                
                 <tr class="text-center">
                    <td>28</td>
                    <td class="text-center" colspan="5">体育场地，器材等有专人负责（2分）</td>
                    <td>${item28Num?if_exists}</td>
                    <td colspan="5" >${item28Str?if_exists}</td>
                </tr>
                
                    <tr class="text-center">
                    <td>29</td>
                    <td class="text-center" colspan="5">课余，假日体育场馆向学生开放（4分）</td>
                    <td>${item29Num?if_exists}</td>
                    <td colspan="5" >${item29Str?if_exists}</td>
                </tr>
                
                     <tr class="text-center">
                    <td>30</td>
                    <td class="text-center" colspan="5">公用经费满足学校体育需求（5分）</td>
                    <td>${item30Num?if_exists}</td>
                    <td colspan="5" >${item30Str?if_exists}</td>
                </tr>
                
                     <tr class="text-center">
                    <td>31</td>
                    <td class="text-center" colspan="5">做好全体学生体质健康测试（3分）</td>
                    <td>${item31Num?if_exists}</td>
                    <td colspan="5" >${item31Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>32</td>
                    <td class="text-center" colspan="5">妥善保存体质健康测试数据（1分）</td>
                    <td>${item32Num?if_exists}</td>
                    <td colspan="5" >${item32Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>33</td>
                    <td class="text-center" colspan="5">按要求上报体质健康测试数据（1分）</td>
                    <td>${item33Num?if_exists}</td>
                    <td colspan="5" >${item33Str?if_exists}</td>
                </tr>
                
                 <tr class="text-center">
                    <td>34</td>
                    <td class="text-center" colspan="5">95%以上的学生达到标准合格等级（5分）</td>
                    <td>${item34Num?if_exists}</td>
                    <td colspan="5" >${item34Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>35</td>
                    <td class="text-center" colspan="5">40%以上的学生达到标准良好等级（4分）</td>
                    <td>${item35Num?if_exists}</td>
                    <td colspan="5" >${item35Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>36</td>
                    <td class="text-center" colspan="5">每年公布体质健康测试总体结果（2分）</td>
                    <td>${item36Num?if_exists}</td>
                    <td colspan="5" >${item36Str?if_exists}</td>
                </tr>
                
                   <tr class="text-center">
                    <td>37</td>
                    <td class="text-center" colspan="5">健康水平列入组合素质档案（2分）</td>
                    <td>${item37Num?if_exists}</td>
                    <td colspan="5" >${item37Str?if_exists}</td>
                </tr>
                
                  <tr class="text-center">
                    <td>38</td>
                    <td class="text-center" colspan="5">分析测试结果，把握体质趋势（2分,填写此项后得分总计、自评等级将进行计算）</td>
                    <td>${item38Num?if_exists}</td>
                    <td colspan="5" >${item38Str?if_exists}</td>
                </tr>
                
               
                <tr class="text-center">
                    <td colspan="3">加分项目</td><td colspan="3">${bonusItems?if_exists}</td>
                    <td colspan="3">加分分数（填写此项后得分总计、自评等级将进行重新计算）</td>
                    <td colspan="3" >
                    	<#if bonusNum?exists && bonusNum=="0">
                    		0分
                         <#elseif bonusNum?exists && bonusNum=="2">
                         	2分
                         <#elseif bonusNum?exists && bonusNum=="4">
                         	4分
                        <#elseif bonusNum?exists && bonusNum=="6">
                     		6分
                         <#elseif bonusNum?exists && bonusNum=="8">
                     		8分
	                     <#else>
                    	 	0分
                         </#if>
                    </td>
                </tr>
             
                
                <tr class="text-center">
                    <td colspan="3">得分总计</td><td colspan="3">${totalScore?if_exists}</td>
                    <td colspan="3">自评等级</td><td colspan="3" >${selfRatingScale?if_exists}</td>
                </tr>
                
               
            </tbody>
        </table>
    </div>
       <div class=" col-sm-12 text-c" align="center">
        <button type="button" id="idBtn"  class="btn btn-success radius"><strong>完成</strong></button>
	   </div>
 </div>
</form>
<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/layer/2.1/layer.js"></script>
</@p.index>