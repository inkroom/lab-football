<@p.index setReferUrl=true>
<style>
	.form-control{
		width:100%;
	}
</style>
<form action="sportSelf.action" method="post" id= "idForm" onSubmit="validateForm(this)">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
    <div class="page-container">
        <table id="teacher" class="table table-bordered ">
            <tbody class="teacher ">
                <tr><td class="text-center" colspan="12"><h3>中小学校体育工作评估自评结果报表</h3></td></tr>
                <tr>
                    <td colspan="2">单位全称(公章):	</td>
                    <td colspan="10" align="center"><input class="input-text form-control" id="idunitName" name="unitName" value="${unitName?if_exists}" disabled="disabled" /></td>
                </tr>
                <tr class="width-16">
                    <td colspan="2">校长</td><td colspan="2"> <input class="input-text form-control" id="idheadMaster" name="headMaster" onblur="checkName(this.id)"  emptyInfo="不能为空" value="${headMaster?if_exists}"/> </td>
                    <td colspan="2">主管校长</td><td colspan="2"><input class="input-text form-control" id="idcompetentPresident" name="competentPresident" onblur="checkName(this.id)" emptyInfo="不能为空"  value="${competentPresident?if_exists}"/></td>
                    <td colspan="2">电话</td><td  colspan="2"><input class="input-text form-control" id="idmasterPhone" name="masterPhone" onblur="checkPhone(this.id)" emptyInfo="不能为空"  value="${masterPhone?if_exists}"/></td>
                </tr>
                <tr>
                    <td colspan="2">教务(体育)主任</td><td colspan="2"> <input class="input-text form-control" id="ideducateDirector" name="educateDirector" onblur="checkName(this.id)" emptyInfo="不能为空"  value="${educateDirector?if_exists}"/> </td>
                    <td colspan="2">体育主管</td><td colspan="2"><input class="input-text form-control" id="idsportsDirector" name="sportsDirector" onblur="checkName(this.id)" emptyInfo="不能为空"  value="${sportsDirector?if_exists}"/></td>
                    <td colspan="2">电话</td><td colspan="2"><input class="input-text form-control" id="iddirectorPhone" name="directorPhone" onblur="checkPhone(this.id)" emptyInfo="不能为空"  value="${directorPhone?if_exists}"/></td>
                </tr>
                <tr>
                    <td colspan="2">体育教师数</td><td colspan="2">  <input class="input-text form-control" id="idsportsTeacherNum" name="sportsTeacherNum" onblur="checkValue(this.id)" emptyInfo="不能为空"  value="${sportsTeacherNum?if_exists}" disabled="disabled"/> </td>
                    <td colspan="2">体育教师缺额人数</td><td colspan="2"> <input class="input-text form-control" id="idsportsTeacherVacancyNum" name="sportsTeacherVacancyNum" onblur="checkValue(this.id)" emptyInfo="不能为空"  value="${sportsTeacherVacancyNum?if_exists}" disabled="disabled"/></td>
                    <td colspan="2">体育教师平均周课时</td><td colspan="2"> <input class="input-text form-control" id="idteacherEveryCourses" name="teacherEveryCourses" onblur="validateFloat(this.id)" emptyInfo="不能为空"  value="${teacherEveryCourses?if_exists}"/></td>
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
                    <td><input class="input-text form-control" id="idthe1Grade" name="the1Grade" onblur="checkValue(this.id)" value="${the1Grade?if_exists}"/> </td>
                    <td><input class="input-text form-control" id="idthe1GradeNum" name="the1GradeNum" onblur="checkValue(this.id)" value="${the1GradeNum?if_exists}"/></td>
                    <td>四年级</td>
                    <td><input class="input-text form-control" id="idthe4Grade" name="the4Grade" onblur="checkValue(this.id)" value="${the4Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe4GradeNum" name="the4GradeNum" onblur="checkValue(this.id)" value="${the4GradeNum?if_exists}"/></td>
                    <td>七年级</td>
                    <td><input class="input-text form-control" id="idthe7Grade" name="the7Grade" onblur="checkValue(this.id)" value="${the7Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe7GradeNum" name="the7GradeNum" onblur="checkValue(this.id)" value="${the7GradeNum?if_exists}"/></td>
                    <td>高一</td>
                    <td><input class="input-text form-control" id="idthe1Higher" name="the1Higher" onblur="checkValue(this.id)" value="${the1Higher?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe1HigherNum" name="the1HigherNum" onblur="checkValue(this.id)" value="${the1HigherNum?if_exists}"/></td>
                </tr>
                <tr class="width-8">
                    <td>二年级</td>
                    <td><input class="input-text form-control" id="idthe2Grade" name="the2Grade" onblur="checkValue(this.id)" value="${the2Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe2GradeNum"name="the2GradeNum" onblur="checkValue(this.id)" value="${the2GradeNum?if_exists}"/></td>
                    <td>五年级</td>
                    <td><input class="input-text form-control" id="idthe5Grade" name="the5Grade" onblur="checkValue(this.id)" value="${the5Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe5GradeNum" name="the5GradeNum" onblur="checkValue(this.id)" value="${the5GradeNum?if_exists}"/></td>
                    <td>八年级</td>
                    <td><input class="input-text form-control" id="idthe8Grade" name="the8Grade" onblur="checkValue(this.id)" value="${the8Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe8GradeNum" name="the8GradeNum" onblur="checkValue(this.id)" value="${the8GradeNum?if_exists}"/></td>
                    <td>高二</td>
                    <td><input class="input-text form-control" id="idthe2Higher"name="the2Higher" onblur="checkValue(this.id)" value="${the2Higher?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe2HigherNum"name="the2HigherNum" onblur="checkValue(this.id)" value="${the2HigherNum?if_exists}"/></td>
                </tr>
                <tr class="width-8">
                    <td>三年级</td>
                    <td><input class="input-text form-control" id="idthe3Grade" name="the3Grade" onblur="checkValue(this.id)" value="${the3Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe3GradeNum" name="the3GradeNum" onblur="checkValue(this.id)" value="${the3GradeNum?if_exists}"/></td>
                    <td>六年级</td>
                    <td><input class="input-text form-control" id="idthe6Grade" name="the6Grade" onblur="checkValue(this.id)" value="${the6Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe6GradeNum" name="the6GradeNum" onblur="checkValue(this.id)" value="${the6GradeNum?if_exists}"/></td>
                    <td>九年级</td>
                    <td><input class="input-text form-control" id="idthe9Grade" name="the9Grade" onblur="checkValue(this.id)" value="${the9Grade?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe9GradeNum" name="the9GradeNum" onblur="checkValue(this.id)" value="${the9GradeNum?if_exists}"/></td>
                    <td>高三</td>
                    <td><input class="input-text form-control" id="idthe3Higher" name="the3Higher" onblur="checkValue(this.id)" value="${the3Higher?if_exists}"/></td>
                    <td><input class="input-text form-control" id="idthe3HigherNum" name="the3HigherNum" onblur="checkValue(this.id)" value="${the3HigherNum?if_exists}"/></td>
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
                    <td><input class="input-text form-control" id="iditem1Num" name="item1Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item1Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem1Str" name="item1Str" onblur="checkStr(this.id)"  value="${item1Str?if_exists}"/></td>
                </tr>
                
                <tr class="text-center">
                    <td>2</td>
                    <td class="text-center" colspan="5">将体育纳入学校整体工作计划(2分) </td>
                    <td><input class="input-text form-control" id="iditem2Num" name="item2Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item2Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem2Str" name="item2Str" onblur="checkStr(this.id)"  value="${item2Str?if_exists}"/></td>
                </tr>
                
               <tr class="text-center">
                    <td>3</td>
                    <td class="text-center" colspan="5">建立意外伤害应急管理机制(1分) </td>
                    <td><input class="input-text form-control" id="iditem3Num" name="item3Num" onblur="checkNum(this.id,1)" emptyInfo="不能为空"  value="${item3Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem3Str" name="item3Str" onblur="checkStr(this.id)"  value="${item3Str?if_exists}"/></td>
                </tr>
                 <tr class="text-center">
                    <td>4</td>
                    <td class="text-center" colspan="5">校长将学校体育列入工作职责（1分）</td>
                    <td><input class="input-text form-control" id="iditem4Num" name="item4Num" onblur="checkNum(this.id,1)" emptyInfo="不能为空"  value="${item4Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem4Str" name="item4Str" onblur="checkStr(this.id)"  value="${item4Str?if_exists}"/></td>
                </tr>
                
                
               
                 <tr class="text-center">
                    <td>5</td>
                    <td class="text-center" colspan="5">校长，分管校长听体育课次数（2分） </td>
                    <td><input class="input-text form-control" id="iditem5Num" name="item5Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item5Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem5Str" name="item5Str" onblur="checkStr(this.id)"  value="${item5Str?if_exists}"/></td>
                </tr>
                
              
                 <tr class="text-center">
                    <td>6</td>
                    <td class="text-center" colspan="5">严格落实体育与健康课时规定（7分） </td>
                    <td><input class="input-text form-control" id="iditem6Num" name="item6Num" onblur="checkNum(this.id,7)" emptyInfo="不能为空"  value="${item6Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem6Str" name="item6Str" onblur="checkStr(this.id)"  value="${item6Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>7</td>
                    <td class="text-center" colspan="5">公布阳光体育运动工作方案（2分） </td>
                    <td><input class="input-text form-control" id="iditem7Num" name="item7Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item7Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem7Str" name="item7Str" onblur="checkStr(this.id)"  value="${item7Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>8</td>
                    <td class="text-center" colspan="5">每学期通报学生体育活动情况（3分）</td>
                    <td><input class="input-text form-control" id="iditem8Num" name="item8Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item8Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem8Str" name="item8Str" onblur="checkStr(this.id)"  value="${item8Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>9</td>
                    <td class="text-center" colspan="5">体育教学计划，单元计划等齐全（4分）</td>
                    <td><input class="input-text form-control" id="iditem9Num" name="item9Num" onblur="checkNum(this.id,4)" emptyInfo="不能为空"  value="${item9Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem9Str" name="item9Str" onblur="checkStr(this.id)"  value="${item9Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>10</td>
                    <td class="text-center" colspan="5">依据课程标准组织体育教学（5分）</td>
                    <td><input class="input-text form-control" id="iditem10Num" name="item10Num" onblur="checkNum(this.id,5)" emptyInfo="不能为空"  value="${item10Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem10Str" name="item10Str" onblur="checkStr(this.id)"  value="${item10Str?if_exists}"/></td>
                </tr>
                
                 <tr class="text-center">
                    <td>11</td>
                    <td class="text-center" colspan="5">加强教学研究与课程教学改革（3分）</td>
                    <td><input class="input-text form-control" id="iditem11Num" name="item11Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item11Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem11Str" name="item11Str" onblur="checkStr(this.id)"  value="${item11Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>12</td>
                    <td class="text-center" colspan="5">严格提醒体育考核，考勤制度（3分）</td>
                    <td><input class="input-text form-control" id="iditem12Num" name="item12Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item12Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem12Str" name="item12Str" onblur="checkStr(this.id)"  value="${item12Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>13</td>
                    <td class="text-center" colspan="5">制定阳光体育运动工作方案（2分）</td>
                    <td><input class="input-text form-control" id="iditem13Num" name="item13Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item13Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem13Str" name="item13Str" onblur="checkStr(this.id)"  value="${item13Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>14</td>
                    <td class="text-center" colspan="5">将校园体育活动纳入教学计划（2分）</td>
                    <td><input class="input-text form-control" id="iditem14Num" name="item14Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item14Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem14Str" name="item14Str" onblur="checkStr(this.id)" value="${item14Str?if_exists}"/></td>
                </tr>
                
                 <tr class="text-center">
                    <td>15</td>
                    <td class="text-center" colspan="5">落实大课间体育活动等时间（3分）</td>
                    <td><input class="input-text form-control" id="iditem15Num" name="item15Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item15Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem15Str" name="item15Str" onblur="checkStr(this.id)"  value="${item15Str?if_exists}"/></td>
                </tr>
                
                <tr class="text-center">
                    <td>16</td>
                    <td class="text-center" colspan="5">学校每年召开春季，秋季运动会（3分）</td>
                    <td><input class="input-text form-control" id="iditem16Num" name="item16Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item16Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem16Str" name="item16Str" onblur="checkStr(this.id)" value="${item16Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>17</td>
                    <td class="text-center" colspan="5">85%的学生掌握至少2项体育技能（4分）</td>
                    <td><input class="input-text form-control" id="iditem17Num" name="item17Num" onblur="checkNum(this.id,4)" emptyInfo="不能为空"  value="${item17Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem17Str" name="item17Str" onblur="checkStr(this.id)"  value="${item17Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>18</td>
                    <td class="text-center" colspan="5">对学生加强体育安全教育（1分）</td>
                    <td><input class="input-text form-control" id="iditem18Num" name="item18Num" onblur="checkNum(this.id,1)" emptyInfo="不能为空"  value="${item18Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem18Str" name="item18Str" onblur="checkStr(this.id)"  value="${item18Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>19</td>
                    <td class="text-center" colspan="5">体育教师数量达到规定标准（3分）</td>
                    <td><input class="input-text form-control" id="iditem19Num" name="item19Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item19Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem19Str" name="item19Str" onblur="checkStr(this.id)"  value="${item19Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>20</td>
                    <td class="text-center" colspan="5">体育教师职评聘公平，公正（3分）</td>
                    <td><input class="input-text form-control" id="iditem20Num" name="item20Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item20Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem20Str" name="item20Str" onblur="checkStr(this.id)" value="${item20Str?if_exists}"/></td>
                </tr>
                  <tr class="text-center">
                    <td>21</td>
                    <td class="text-center" colspan="5">体育教师工资待遇、工作服装（2分）</td>
                    <td><input class="input-text form-control" id="iditem21Num" name="item21Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item21Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem21Str" name="item21Str" onblur="checkStr(this.id)" value="${item21Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>22</td>
                    <td class="text-center" colspan="5">体育活动、测试纳入教学工作量（2分）</td>
                    <td><input class="input-text form-control" id="iditem22Num" name="item22Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item22Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem22Str" name="item22Str" onblur="checkStr(this.id)"  value="${item22Str?if_exists}"/></td>
                </tr>
                  <tr class="text-center">
                    <td>23</td>
                    <td class="text-center" colspan="5">体育教师集体备课、本校教研（2分）</td>
                    <td><input class="input-text form-control" id="iditem23Num" name="item23Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item23Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem23Str" name="item23Str" onblur="checkStr(this.id)"  value="${item23Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>24</td>
                    <td class="text-center" colspan="5">体育教师参加培训，继续教育（2分）</td>
                    <td><input class="input-text form-control" id="iditem24Num" name="item24Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item24Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem24Str" name="item24Str" onblur="checkStr(this.id)"  value="${item24Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>25</td>
                    <td class="text-center" colspan="5">体育场地、器材、设备达标（1分）</td>
                    <td><input class="input-text form-control" id="iditem25Num" name="item25Num" onblur="checkNum(this.id,1)" emptyInfo="不能为空"  value="${item25Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem25Str" name="item25Str" onblur="checkStr(this.id)"  value="${item25Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>26</td>
                    <td class="text-center" colspan="5">体育场地平整，整洁，符合要求（2分）</td>
                    <td><input class="input-text form-control" id="iditem26Num" name="item26Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item26Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem26Str" name="item26Str" onblur="checkStr(this.id)"  value="${item26Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>27</td>
                    <td class="text-center" colspan="5">体育场馆管理规范，安全运行（2分）</td>
                    <td><input class="input-text form-control" id="iditem27Num" name="item27Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item27Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem27Str" name="item27Str" onblur="checkStr(this.id)"   value="${item27Str?if_exists}"/></td>
                </tr>
                
                 <tr class="text-center">
                    <td>28</td>
                    <td class="text-center" colspan="5">体育场地，器材等有专人负责（2分）</td>
                    <td><input class="input-text form-control" id="iditem28Num" name="item28Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item28Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem28Str" name="item28Str" onblur="checkStr(this.id)" value="${item28Str?if_exists}"/></td>
                </tr>
                
                    <tr class="text-center">
                    <td>29</td>
                    <td class="text-center" colspan="5">课余，假日体育场馆向学生开放（4分）</td>
                    <td><input class="input-text form-control" id="iditem29Num" name="item29Num" onblur="checkNum(this.id,4)" emptyInfo="不能为空"  value="${item29Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem29Str" name="item29Str" onblur="checkStr(this.id)" value="${item29Str?if_exists}"/></td>
                </tr>
                
                     <tr class="text-center">
                    <td>30</td>
                    <td class="text-center" colspan="5">公用经费满足学校体育需求（5分）</td>
                    <td><input class="input-text form-control" id="iditem30Num" name="item30Num" onblur="checkNum(this.id,5)" emptyInfo="不能为空"  value="${item30Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem30Str" name="item30Str" onblur="checkStr(this.id)"  value="${item30Str?if_exists}"/></td>
                </tr>
                
                     <tr class="text-center">
                    <td>31</td>
                    <td class="text-center" colspan="5">做好全体学生体质健康测试（3分）</td>
                    <td><input class="input-text form-control" id="iditem31Num" name="item31Num" onblur="checkNum(this.id,3)" emptyInfo="不能为空"  value="${item31Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem31Str" name="item31Str" onblur="checkStr(this.id)"  value="${item31Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>32</td>
                    <td class="text-center" colspan="5">妥善保存体质健康测试数据（1分）</td>
                    <td><input class="input-text form-control" id="iditem32Num" name="item32Num" onblur="checkNum(this.id,1)" emptyInfo="不能为空"  value="${item32Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem32Str" name="item32Str" onblur="checkStr(this.id)"  value="${item32Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>33</td>
                    <td class="text-center" colspan="5">按要求上报体质健康测试数据（1分）</td>
                    <td><input class="input-text form-control" id="iditem33Num" name="item33Num" onblur="checkNum(this.id,1)" emptyInfo="不能为空"  value="${item33Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem33Str" name="item33Str" onblur="checkStr(this.id)"  value="${item33Str?if_exists}"/></td>
                </tr>
                
                 <tr class="text-center">
                    <td>34</td>
                    <td class="text-center" colspan="5">95%以上的学生达到标准合格等级（5分）</td>
                    <td><input class="input-text form-control" id="iditem34Num" name="item34Num" onblur="checkNum(this.id,5)" emptyInfo="不能为空"  value="${item34Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem34Str" name="item34Str" onblur="checkStr(this.id)"  value="${item34Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>35</td>
                    <td class="text-center" colspan="5">40%以上的学生达到标准良好等级（4分）</td>
                    <td><input class="input-text form-control" id="iditem35Num" name="item35Num" onblur="checkNum(this.id,4)" emptyInfo="不能为空"  value="${item35Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem35Str" name="item35Str" onblur="checkStr(this.id)"  value="${item35Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>36</td>
                    <td class="text-center" colspan="5">每年公布体质健康测试总体结果（2分）</td>
                    <td><input class="input-text form-control" id="iditem36Num" name="item36Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item36Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem36Str" name="item36Str" onblur="checkStr(this.id)"  value="${item36Str?if_exists}"/></td>
                </tr>
                
                   <tr class="text-center">
                    <td>37</td>
                    <td class="text-center" colspan="5">健康水平列入组合素质档案（2分）</td>
                    <td><input class="input-text form-control" id="iditem37Num" name="item37Num" onblur="checkNum(this.id,2)" emptyInfo="不能为空"  value="${item37Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem37Str" name="item37Str" onblur="checkStr(this.id)"  value="${item37Str?if_exists}"/></td>
                </tr>
                
                  <tr class="text-center">
                    <td>38</td>
                    <td class="text-center" colspan="5">分析测试结果，把握体质趋势（2分,填写此项后得分总计、自评等级将进行计算）</td>
                    <td><input class="input-text form-control" id="iditem38Num" name="item38Num" onblur="checkNum(this.id,2)"  emptyInfo="不能为空"  value="${item38Num?if_exists}"/></td>
                    <td colspan="5" ><input class="input-text form-control" id="iditem38Str" name="item38Str" onblur="checkStr(this.id)"  value="${item38Str?if_exists}"/></td>
                </tr>
                
               
                <tr class="text-center">
                    <td colspan="3">加分项目</td><td colspan="3"> <input class="input-text form-control" id="idbonusItems" name="bonusItems" onblur="checkAddProj(this.id)" value="${bonusItems?if_exists}"/></td>
                    <td colspan="3">加分分数（填写此项后得分总计、自评等级将进行重新计算）</td>
                    <td colspan="3" >
                    	<#if bonusNum?exists && bonusNum=="0">
                    		<select class="form-control" id="idbonusNum" name="bonusNum" onblur="checkNum(this.id,8)">
                                <option value="0" selected="true">0分</option>
                                <option value="2">2分</option>
                                <option value="4">4分</option>
                                <option value="6">6分</option>
                                <option value="8">8分</option>
                            
                            </select>
                         <#elseif bonusNum?exists && bonusNum=="2">
                         	<select class="form-control" id="idbonusNum" name="bonusNum" onblur="checkNum(this.id,8)">
                                <option value="0">0分</option>
                                <option value="2" selected="true">2分</option>
                                <option value="4">4分</option>
                                <option value="6">6分</option>
                                <option value="8">8分</option>
                            
                            </select>
                         <#elseif bonusNum?exists && bonusNum=="4">
                         	<select class="form-control" id="idbonusNum" name="bonusNum" onblur="checkNum(this.id,8)">
                                <option value="0" selected="true">0分</option>
                                <option value="2">2分</option>
                                <option value="4 selected="true"">4分</option>
                                <option value="6">6分</option>
                                <option value="8">8分</option>
                            
                            </select>
                            <#elseif bonusNum?exists && bonusNum=="6">
                         	<select class="form-control" id="idbonusNum" name="bonusNum" onblur="checkNum(this.id,8)">
                                <option value="0" selected="true">0分</option>
                                <option value="2">2分</option>
                                <option value="4">4分</option>
                                <option value="6" selected="true">6分</option>
                                <option value="8">8分</option>
                            
                            </select>
                             <#elseif bonusNum?exists && bonusNum=="8">
                         	<select class="form-control" id="idbonusNum" name="bonusNum" onblur="checkNum(this.id,8)">
                                <option value="0" selected="true">0分</option>
                                <option value="2">2分</option>
                                <option value="4">4分</option>
                                <option value="6">6分</option>
                                <option value="8" selected="true">8分</option>
                            
                            </select>
                         <#else>
                        	 <select class="form-control" id="idbonusNum" name="bonusNum" disabled="disabled" onblur="checkNum(this.id,8)">
                                <option value="0" selected="true">0分</option>
                                <option value="2">2分</option>
                                <option value="4">4分</option>
                                <option value="6">6分</option>
                                <option value="8">8分</option>
                            
                            </select>
                         </#if>
                    </td>
                </tr>
             
                
                <tr class="text-center">
                    <td colspan="3">得分总计</td><td colspan="3"> <input class="input-text form-control" name="totalScore" id="idtotalScore" disabled="disabled" onchange="addNum(this.id)" value="${totalScore?if_exists}"/></td>
                    <td colspan="3">自评等级</td><td colspan="3" ><input class="input-text form-control" name="selfRatingScale" id="idselfRatingScale" disabled="disabled" value="${selfRatingScale?if_exists}"/></td>
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
 
  <script type="text/javascript">
  			

  				
  			var a = '${stage?if_exists}';

  			if(a ==1 ){
  				$('#idthe1Higher').attr('disabled',true);
  				$('#idthe1HigherNum').attr('disabled',true);
  				$('#idthe2Higher').attr('disabled',true);
  				$('#idthe2HigherNum').attr('disabled',true);
  				$('#idthe3Higher').attr('disabled',true);
  				$('#idthe3HigherNum').attr('disabled',true);
  				
  				
  				$('#idthe7Grade').attr('disabled',true);
  				$('#idthe7GradeNum').attr('disabled',true);
  				$('#idthe8Grade').attr('disabled',true);
  				$('#idthe8GradeNum').attr('disabled',true);
  				$('#idthe9Grade').attr('disabled',true);
  				$('#idthe9GradeNum').attr('disabled',true);
  				
  				
  				$('#idthe1Higher').val(0);
				$('#idthe1HigherNum').val(0);
				$('#idthe2Higher').val(0);
				$('#idthe2HigherNum').val(0);
				$('#idthe3Higher').val(0);
				$('#idthe3HigherNum').val(0);
				
				
				$('#idthe7Grade').val(0);
				$('#idthe7GradeNum').val(0);
				$('#idthe8Grade').val(0);
				$('#idthe8GradeNum').val(0);
				$('#idthe9Grade').val(0);
				$('#idthe9GradeNum').val(0);
  				
  				
  				
  				
  			}else if(a == 2){
  				$('#idthe1Grade').attr('disabled',true);
  				$('#idthe1GradeNum').attr('disabled',true);
  				$('#idthe2Grade').attr('disabled',true);
  				$('#idthe2GradeNum').attr('disabled',true);
  				$('#idthe3Grade').attr('disabled',true);
  				$('#idthe3GradeNum').attr('disabled',true);
  				$('#idthe4Grade').attr('disabled',true);
  				$('#idthe4GradeNum').attr('disabled',true);
  				$('#idthe5Grade').attr('disabled',true);
  				$('#idthe5GradeNum').attr('disabled',true);
  				$('#idthe6Grade').attr('disabled',true);
  				$('#idthe6GradeNum').attr('disabled',true);

  				$('#idthe1Higher').attr('disabled',true);
  				$('#idthe1HigherNum').attr('disabled',true);
  				$('#idthe2Higher').attr('disabled',true);
  				$('#idthe2HigherNum').attr('disabled',true);
  				$('#idthe3Higher').attr('disabled',true);
  				$('#idthe3HigherNum').attr('disabled',true);
  				
  				
  				
  				$('#idthe1Grade').val(0);
				$('#idthe1GradeNum').val(0);
				$('#idthe2Grade').val(0);
				$('#idthe2GradeNum').val(0);
				$('#idthe3Grade').val(0);
				$('#idthe3GradeNum').val(0);
				$('#idthe4Grade').val(0);
				$('#idthe4GradeNum').val(0);
				$('#idthe5Grade').val(0);
				$('#idthe5GradeNum').val(0);
				$('#idthe6Grade').val(0);
				$('#idthe6GradeNum').val(0);
				
				$('#idthe1Higher').val(0);
				$('#idthe1HigherNum').val(0);
				$('#idthe2Higher').val(0);
				$('#idthe2HigherNum').val(0);
				$('#idthe3Higher').val(0);
				$('#idthe3HigherNum').val(0);
  				
  				
  				
  				
  				
  			}else if(a == 3|| a==4	){
  				$('#idthe1Grade').attr('disabled',true);
  				$('#idthe1GradeNum').attr('disabled',true);
  				$('#idthe2Grade').attr('disabled',true);
  				$('#idthe2GradeNum').attr('disabled',true);
  				$('#idthe3Grade').attr('disabled',true);
  				$('#idthe3GradeNum').attr('disabled',true);
  				$('#idthe4Grade').attr('disabled',true);
  				$('#idthe4GradeNum').attr('disabled',true);
  				$('#idthe5Grade').attr('disabled',true);
  				$('#idthe5GradeNum').attr('disabled',true);
  				$('#idthe6Grade').attr('disabled',true);
  				$('#idthe6GradeNum').attr('disabled',true);
  				
  				
  				$('#idthe7Grade').attr('disabled',true);
  				$('#idthe7GradeNum').attr('disabled',true);
  				$('#idthe8Grade').attr('disabled',true);
  				$('#idthe8GradeNum').attr('disabled',true);
  				$('#idthe9Grade').attr('disabled',true);
  				$('#idthe9GradeNum').attr('disabled',true);
  				
  				
  				
  				
  				
  				
  				$('#idthe1Grade').val(0);
				$('#idthe1GradeNum').val(0);
				$('#idthe2Grade').val(0);
				$('#idthe2GradeNum').val(0);
				$('#idthe3Grade').val(0);
				$('#idthe3GradeNum').val(0);
				$('#idthe4Grade').val(0);
				$('#idthe4GradeNum').val(0);
				$('#idthe5Grade').val(0);
				$('#idthe5GradeNum').val(0);
				$('#idthe6Grade').val(0);
				$('#idthe6GradeNum').val(0);
				
				
				$('#idthe7Grade').val(0);
				$('#idthe7GradeNum').val(0);
				$('#idthe8Grade').val(0);
				$('#idthe8GradeNum').val(0);
				$('#idthe9Grade').val(0);
				$('#idthe9GradeNum').val(0);
  			}else if(a == 5){
  				$('#idthe1Higher').attr('disabled',true);
  				$('#idthe1HigherNum').attr('disabled',true);
  				$('#idthe2Higher').attr('disabled',true);
  				$('#idthe2HigherNum').attr('disabled',true);
  				$('#idthe3Higher').attr('disabled',true);
  				$('#idthe3HigherNum').attr('disabled',true);
  				
  				
  				$('#idthe1Higher').val(0);
				$('#idthe1HigherNum').val(0);
				$('#idthe2Higher').val(0);
				$('#idthe2HigherNum').val(0);
				$('#idthe3Higher').val(0);
				$('#idthe3HigherNum').val(0);
  			}else if(a == 6){
  				
  			}else if(a == 7){
  				$('#idthe1Grade').attr('disabled',true);
  				$('#idthe1GradeNum').attr('disabled',true);
  				$('#idthe2Grade').attr('disabled',true);
  				$('#idthe2GradeNum').attr('disabled',true);
  				$('#idthe3Grade').attr('disabled',true);
  				$('#idthe3GradeNum').attr('disabled',true);
  				$('#idthe4Grade').attr('disabled',true);
  				$('#idthe4GradeNum').attr('disabled',true);
  				$('#idthe5Grade').attr('disabled',true);
  				$('#idthe5GradeNum').attr('disabled',true);
  				$('#idthe6Grade').attr('disabled',true);
  				$('#idthe6GradeNum').attr('disabled',true);
  				
  				$('#idthe1Grade').val(0);
				$('#idthe1GradeNum').val(0);
				$('#idthe2Grade').val(0);
				$('#idthe2GradeNum').val(0);
				$('#idthe3Grade').val(0);
				$('#idthe3GradeNum').val(0);
				$('#idthe4Grade').val(0);
				$('#idthe4GradeNum').val(0);
				$('#idthe5Grade').val(0);
				$('#idthe5GradeNum').val(0);
				$('#idthe6Grade').val(0);
				$('#idthe6GradeNum').val(0);
  				
  			}

  			
          
			<#if errorInfo?exists>
				layer.msg('${errorInfo?if_exists}');
			</#if>
			<#--文本框验证不能只输入英文或数字符号-->
			
			function checkStr(val){
				 var regExp = /[\u4E00-\u9FA5]+/;
				 var idtxt = window.document.getElementById(val);
				if(idtxt.value == null || idtxt.value.length == 0){
					if(val == "idbonusItems"){
						document.getElementById("idbonusNum").value = "0";
						$('#idbonusNum').attr('disabled',true);
						
					}
					flag = true;
					return true;
				}
				if(val == "idbonusItems"){
						$('#idbonusNum').attr('disabled',false);
					}
	 			if(regExp.test(idtxt.value)==false){
		 			layer.msg("不能只输入英文或数字符号");
		 			idtxt.value="";
		 			flag = false;
		 			return false;
				}
				flag = true;
				return true;
		
  			}
  			
  			<#--验证加分项目-->
  			function checkAddProj(val){
  				
  				
				 var regExp = /[\u4E00-\u9FA5]+/;
				 var idtxt = window.document.getElementById(val);
				 temp = idtxt.value.replace(/\s+/g, "");
				if(idtxt.value.length > temp.length){
					layer.msg("输入中字符中不能有空格，请重新输入！");
		 			idtxt.value="";
		 			flag = false;
		 			return false;
				}
				if(idtxt.value == null || idtxt.value.length == 0){
					if(val == "idbonusItems"){
						document.getElementById("idbonusNum").value = "0";
						$('#idbonusNum').attr('disabled',true);
						
					}
					flag = true;
					return true;
				}
				if(idtxt.value.length > 20){
					layer.msg("你输入的字符过长，请重新输入！");
		 			idtxt.value="";
		 			flag = false;
		 			return false;
				}
			
	 			if(regExp.test(idtxt.value)==false){
		 			layer.msg("不能只输入英文或数字符号");
		 			idtxt.value="";
		 			flag = false;
		 			return false;
				}
				if(val == "idbonusItems"){
					$('#idbonusNum').attr('disabled',false);
				}
				flag = true;
				return true;
		
  			}
			
			<#--验证正整数-->
			function validateNum(val){
			  var patten3 = /^([0-9][0-9]{0,20})$/;
		      var patten4 = /^[0-9]$/;
		      if(val!=null && val.length>0 &&  patten3.test(val)){
			     return false;
		       }else{
			     return true;
		       }
			}
			 
			<#--验证手机号码是否合法-->
			function checkPhone(val){
			   var x=document.getElementById(val);
			   if (validateNum(x.value)) {
			      if (x.value == null || x.value.length == 0) {
			          x.value = "";
				      layer.msg("电话号码未填写");
				      flag = false;
				      return false;
			      }else{
			          x.value = "";
			          layer.msg("不符合规范的电话的号码，请输入重新输入!");
			          flag = false;
				      return false;
				   }
		       }
		       if(x.value.length > 21 || x.value.length < 6){
		       		x.value = "";
			          layer.msg("请输入正确的电话号码！");
			          flag = false;
				      return false;
		       
		       }
		       flag = true;
		     return true;
		}
		
		
		
			
		
		<#--验证名字输入是否合法-->
		function checkName(val){
		
		    var x=document.getElementById(val);
		     var reg =/^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/g;
 			if(x.value.length > 20 || x.value.length < 2){
 				x.value = "";
                layer.msg("姓名必须为2~20为纯中文或者纯英文，不包含其他字符！");
                flag = false;
                return false;
 			}
 			if(!reg.test(x.value)){
 				x.value = "";
                layer.msg("姓名必须为纯中文或纯英文，不能含有其他字符！");
                flag = false;
                return false;
            }
         	flag = true;
         	
            return true;
		}
		<#--验证普通输入数字-->
		function checkValue(val){
		   var x=document.getElementById(val);
		    if (x.value == null || x.value.length == 0) {
		              x.value = "";
				      layer.msg("不能为空,请填写实际数据！");
				      flag = false;
				      return false;
			}
			
			if(x.value.length > 8){
				
				x.value = "";
				      layer.msg("你输入的数字过大！");
				      flag = false;
				      return false;
			
			}
			
		    if (validateNum(x.value)) {
				 if (x.value < 0) {
				      x.value = "";
				      layer.msg("不能为负数！");
				      flag = false;
				      return false;
			    }else {
			          x.value = "";
				      layer.msg("输入字符不合法");
				      flag = false;
				      return false;
		     	}
		    }
		    flag = true;
		    return true;
		}
		
		<#--验证小数和整数-->
	function validateFloat(val){
		var patten1 = /^([1-9][0-9]{0,7}\.([0-9]{1,4}))$/;
		var patten2 = /^([0-9]\.[0-9]{1,4})$/;
		if(val!=null && val.length>0 && val.length<14 && (patten1.test(val) || patten2.test(val) || !validateNum(val) )){
			return false;
		}else{
			return true;
		}
	}
		
		<#--验证的加分，可以为空-->
		function isSore(val,a){
		    var x=document.getElementById(val);
		    if (x.value == null || x.value.length == 0) {
				      flag = true;
				      return true;
		    }
		    if (validateNum(x.value)) {
		        if (x.value == null) {
				      flag = true;
				      return true;
				     
				 }else if (x.value < 0) {
				      x.value = "";
				      layer.msg("不能为负数！");
				      flag = false;
				      return false;
			    }else {
			          x.value = "";
				      layer.msg("输入字符不合法");
				      flag = false;
				      return false;
		     	}
		    }
		    if(x.value>a||x.value<0){
		    	 x.value = "";
		       layer.msg("输入的数字不在范围内，请重新输入");
		       flag = false;
		       return false;
		    }
		  	flag = true;
		    return true;
		}
		
		
		function isNum(val,a){
		    var x=document.getElementById(val);
		    if (validateNum(x.value)) {
		        if (x.value == null) {
		              x.value = ""; 
				      layer.msg("得分项不能为空！");
				      flag = false;
				      return false;
				     
				 }else if (x.value < 0) {
				      x.value = "";
				      layer.msg("不能为负数！");
				      flag = false;
				      return false;
			    }else {
			          x.value = "";
				      layer.msg("输入字符不合法");
				      flag = false;
				      return false;
		     	}
		    }
		    if(x.value>a||x.value<0){
		    	 x.value = "";
		       layer.msg("输入的数字不在范围内，请重新输入");
		       flag = false;
		       return false;
		    }
		  	flag = true;
		    return true;
		}
		
		function checkNum(val,num){
			if(val == "idbonusNum"){
				isSore(val,num);
			}else{
				isNum(val,num);
			}
		   
		var x=document.getElementById(val);
		var arr = new Array(2,2,1,1,2,7,2,3,4,5,3,3,2,2,3,3,4,1,3,3,2,2,2,2,1,2,2,2,4,5,3,1,1,5,4,2,2,2);
		var	a1 = 	$('#iditem1Num').val();	if(a1 == null){a1 = 0; }
		var	a2 =	$('#iditem2Num').val();	if(a2 == null){a2  = 0; }
		var	a3 =	$('#iditem3Num').val();	if(a3 == null){a3  = 0; }
		var	a4 =	$('#iditem4Num').val();	if(a4 == null){a4  = 0; }
		var	a5 =	$('#iditem5Num').val();	if(a5 == null){a5  = 0; }
		var	a6 =	$('#iditem6Num').val();	if(a6 == null){a6  = 0; }
		var	a7 =	$('#iditem7Num').val();	if(a7 == null){a7  = 0; }
		var	a8 =	$('#iditem8Num').val();	if(a8 == null){a8  = 0; }
		var	a9 =	$('#iditem9Num').val();	if(a9 == null){a9  = 0; }
		var	a10 =	$('#iditem10Num').val();	if(a10 == null){a10  = 0; }
		var	a11 =	$('#iditem11Num').val();	if(a11 == null){a11  = 0; }
		var	a12 =	$('#iditem12Num').val();	if(a12 == null){a12  = 0; }
		var	a13 =	$('#iditem13Num').val();	if(a13 == null){a13  = 0; }
		var	a14 =	$('#iditem14Num').val();	if(a14 == null){a14  = 0; }
		var	a15 =	$('#iditem15Num').val();	if(a15 == null){a15  = 0; }
		var	a16 =	$('#iditem16Num').val();	if(a16 == null){a16  = 0; }
		var	a17 =	$('#iditem17Num').val();	if(a17 == null){a17  = 0; }
		var	a18 =	$('#iditem18Num').val();	if(a18 == null){a18  = 0; }
		var	a19 =	$('#iditem19Num').val();	if(a19 == null){a19  = 0; }
		var	a20 =	$('#iditem20Num').val();	if(a20 == null){a20  = 0; }
		var	a21 =	$('#iditem21Num').val();	if(a21 == null){a21  = 0; }
		var	a22 =	$('#iditem22Num').val();	if(a22 == null){a22  = 0; }
		var	a23 =	$('#iditem23Num').val();	if(a23 == null){a23  = 0; }
		var	a24 =	$('#iditem24Num').val();	if(a24 == null){a24  = 0; }
		var	a25 =	$('#iditem25Num').val();	if(a25 == null){a25  = 0; }
		var	a26 =	$('#iditem26Num').val();	if(a26 == null){a26  = 0; }
		var	a27 =	$('#iditem27Num').val();	if(a27 == null){a27  = 0; }
		var	a28 =	$('#iditem28Num').val();	if(a28 == null){a28  = 0; }
		var	a29 =	$('#iditem29Num').val();	if(a29 == null){a29  = 0; }
		var	a30 =	$('#iditem30Num').val();	if(a30 == null){a30  = 0; }
		var	a31 =	$('#iditem31Num').val();	if(a31 == null){a31  = 0; }
		var	a32 =	$('#iditem32Num').val();	if(a32 == null){a32  = 0; }
		var	a33 =	$('#iditem33Num').val();	if(a33 == null){a33  = 0; }
		var	a34 =	$('#iditem34Num').val();	if(a34 == null){a34  = 0; }
		var	a35 =	$('#iditem35Num').val();	if(a35 == null){a35  = 0; }
		var	a36 =	$('#iditem36Num').val();	if(a36 == null){a36  = 0; }
		var	a37 =	$('#iditem37Num').val();	if(a37 == null){a37  = 0; }
		var	a38 =	$('#iditem38Num').val();	if(a38 == null){a38  = 0; }
		var	a39 =	$('#idbonusNum').val();	if(a39 == null){a39  = 0; }
		var sore = 0;
		sore = a1*1+	a2*1+	a3*1+	a4*1+	a5*1+	a6*1+	a7*1+	a8*1+	a9*1+	a10*1+	a11*1+	a12*1+	a13*1+	a14*1+	a15*1+	a16*1+	a17*1+	a18*1+	a19*1+	a20*1+	a21*1+	a22*1+	a23*1+	a24*1+	a25*1+	a26*1+	a27*1+	a28*1+	a29*1+	a30*1+	a31*1+	a32*1+	a33*1+	a34*1+	a35*1+	a36*1+	a37*1+	a38*1 + a39*1;
		
		document.getElementById("idtotalScore").value = sore;
		if(parseInt(sore) <60){
			document.getElementById("idselfRatingScale").value = "不合格";	
		
		}else if(parseInt(sore) >=60 && parseInt(sore) <=74){
			document.getElementById("idselfRatingScale").value = "合格";
		}else if(parseInt(sore) >=75 && parseInt(sore) <89){
			document.getElementById("idselfRatingScale").value = "良好";
		}else{
			document.getElementById("idselfRatingScale").value = "优秀";
		}
	}
	
	
	
	
	
  				
	
	
	<#--最終判断所有的方法，确认正确后方可提交表单-->
		var flag = true;
		
		
		$("#idBtn").click(function(){
					var stage = '${stage?if_exists}';
					if(NumOfStus())
					{
						if(flag == true){
							$("#idForm").submit();
						}else{
							flag = false;
						}
						
						
					}
					
					
		})	
		
		
		
		
		
function NumOfStus()
{
	var sumOfStus1 = ${sumOfStus?if_exists};
	var	x1 =	$('#idthe1GradeNum').val();
	var	x2 =	$('#idthe2GradeNum').val();
	var	x3 =	$('#idthe3GradeNum').val();
	var	x4 =	$('#idthe4GradeNum').val();
	var	x5 =	$('#idthe5GradeNum').val();
	var	x6 =	$('#idthe6GradeNum').val();
	var	x7 =	$('#idthe7GradeNum').val();
	var	x8 =	$('#idthe8GradeNum').val();
	var	x9 =	$('#idthe9GradeNum').val();
	
	
	var	g1 =	$('#idthe1HigherNum').val();
	var	g2 =	$('#idthe2HigherNum').val();
	var	g3 =	$('#idthe3HigherNum').val();
	
	var sumOfStus2 = parseInt(x1) + parseInt(x2) + parseInt(x3) + parseInt(x4) + parseInt(x5)
				+ parseInt(x6) + parseInt(x7) + parseInt(x8) + parseInt(x9) + parseInt(g1)  
				+ parseInt(g2) + parseInt(g3);
	
	console.log(sumOfStus1);
	console.log(sumOfStus2);
	if(sumOfStus1 != sumOfStus2){
		layer.msg("学生总人数与体育年度工作表报中的不相等，请重新核实！");
		return false;
	}
	return true;
}	
		
		
function validateForm(theForm) {
	
	var disableList=new Array();
	var field = theForm.elements;// 将表单中的所有元素放入数组
	for(var i = 0; i < field.length; i++) {
	
		// 先判断是否设定了不验证表单
		var empty=false;
		var value=trim(field[i].value);
		if(value.length==0) {// 是否空值
			empty=true;
		}

		var emptyInfo=field[i].getAttribute("emptyInfo");// 空值验证
		if(emptyInfo!=null&&empty==true) {
			layer.msg(emptyInfo);
			if (!notFocus){
				field[i].focus();
			}
			return false;
		}
		var spaceInfo=field[i].getAttribute("spaceInfo");// 空格验证
		if(spaceInfo!=null) {
			if (haveSpace(value)) {
				layer.msg(spaceInfo);
				field[i].focus();
				return false;
			}
		}
		
		
	}
	
	return true;
}


function trim(str) { 
	if(str.length==0)
		return(str);
	else {
		var idx=0;
		while(str.charAt(idx).search(/\s/)==0)
			idx++;
		return(str.substring(idx));
	}
}

   </script>
</@p.index>