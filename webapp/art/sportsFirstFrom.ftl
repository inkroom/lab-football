<@p.index setReferUrl=true>

<form action="sportFirstView.action" method="post name="sportFirst">
<div class="container">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">基础数据</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校级别</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="schoolType">
                                <option value="primarySchool">普通小学</option>
                                <option value="middleSchool">普通初中</option>
                                <option value="highSchool">普通高中</option>
                                <option value="vocationalHighSchool">职业高中</option>
                                <option value="nineYearSchool">九年一贯制学校</option>
                                <option value="twelveYearSchool">十二年一贯制学校</option>
                                <option value="completeMiddleSchool">完全中学</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr class="text-c">

                    <td>在校生人数</td>
                    <td><input class="input-text form-control" name="numOfStus" /></td>
                </tr>
                <tr class="text-c">
                    <td>实际班级数量</td>
                    <td><input class="input-text form-control" name="actualClassNumber"/></td>
                </tr>
                <tr class="text-c">
                    <td rowspan="3">开课及课外活动</td>
                    <td>体育课是否开足</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="enoughPE">
                                <option value="1">是</option>
                                <option value="2" selected="true">否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                <tr class="text-c">
                    <td>落实每天一小时体育锻炼数</td>
                    <td>
                        <div class="form-group" >
                            <select class="form-control" name="onehourPE">
                                <option value="1">是</option>
                                <option value="2" selected="true">否</option>

                            </select>
                        </div>
                    </td>
                </tr>
                <tr class="text-c">
                    <td>组织大课间体育活动数</td>
                    <td><input class="input-text form-control" name="numActivityInClass"/></td>
                </tr>
                <tr class="text-c">
                    <td rowspan="8">体育师资</td>
                    <td>应配教师数(人)</td>
                    <td><input class="input-text form-control" name="havaTeacher" /></td>
                </tr>
                 <tr class="text-c">
                    <td>专职体育教师人数(人)</td>
                    <td><input class="input-text form-control" name="numFulltimeTeachers"/></td>
                </tr>
                <tr class="text-c">
                    <td>兼职体育教师人数(人)</td>
                    <td><input class="input-text form-control" name="numParttimeTeachers"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师缺额比（%）</td>
                    <td><input class="input-text form-control" name="sportsTeacherVacancyRatio"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师缺额数</td>
                    <td><input class="input-text form-control" name="sportsTeacherVacancyNum"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师生师比</td>
                    <td><input class="input-text form-control" name="ratioOfStudentsToTeachers"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师参训人数</td>
                    <td><input class="input-text form-control" name="numPhysicalTeachers"/></td>
                </tr>
                <tr class="text-c">
                    <td>教师受县级以上表彰人数</td>
                    <td><input class="input-text form-control" name="numRecognitionTeachers"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        <button type="reset"   class="btn btn-success radius"><strong>重置</strong></button>
        <button type="submit"   class="btn btn-success radius"><strong>下一步</strong></button>
    </div>

</div>


  <script>

    	
			<#if error?exists>
				alert('${errorInfo?if_exists}');
			</#if>
    	
    </script>

</form>

</@p.index>