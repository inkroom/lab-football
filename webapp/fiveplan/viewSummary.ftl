<@p.index setReferUrl=true>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="lib/css/bootstrap.min.css" rel="stylesheet">
    <title>四川省体育艺术五年行动计划年度推进情况统计表</title>
    <style>
        body {
            padding: 0;
            margin: auto;
        }
        
        .subtitle {
            background-color: #ffff99;
        }
        
        tr td {
            text-align: center;
        }
    </style>
</head>

<body>
    <div class="container">
        <div>
            <h2 class="title text-center">${(uMp.O_NAME)?if_exists}&nbsp体育艺术五年行动计划年度推进情况统计表</h2>
        </div>
        <div>
            <table class="table table-bordered">
                <tr>
                    <td colspan="12" class="subtitle">运动场建设达标及器材设备配置达标情况</td>
                </tr>
                <tr>
                    <td>运动场地达标情况</td>
                    <td>其中：新建（个）</td>
                    <td>新建面积（㎡）</td>
                    <td>投入金额（万元）</td>
                    <td>改扩建（个）</td>
                    <td>改扩建面积（㎡）</td>
                    <td>投入金额（万元）</td>
                    <td>运动器材达标情况</td>
                    <td>投入金额（万元）</td>
                    
                </tr>
                <tr>
                    <td>${((preData.is_achieve_play_ground)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.add_play_ground)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.add_play_ground_area)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_for_add_play_ground)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.extension_modify_play_ground)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.area_exten_modify_play_ground)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_for_mod_play_ground)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.is_add_euqi_gro)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_euqi_gro_totaL)?if_exists.toString()?html)?if_exists}</td>         
                </tr>

                <tr>
                    <td colspan="12" class="subtitle text-center">艺术专用教室达标及器材设备配置达标情况</td>
                </tr>
                <tr>
                    <td>音乐专用教室达标情况</td>
                    <td>投入金额（万元)</td>
                    <td>音乐器材配备达标情况</td>
                    <td>投入金额 （万元）</td>
                    <td>美术专用教室达标情况</td>      
                    <td>投入金额 （万元）</td>  
                    <td>美术器材配备达标情况</td>
                    <td>投入金额 （万元） </td> 
                </tr>
                <tr>
                    <td>${((preData.is_achieve_music_room)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_for_music_equi)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.is_achieve_music_equi)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_for_music_equit)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.is_achieve_draw_room)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_for_draw_room)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.is_achieve_draw_equi)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_for_draw_equi)?if_exists.toString()?html)?if_exists}</td>
                </tr>

                <tr>
                    <td colspan="12" class="subtitle text-center">体育、艺术教师补充与配备情况</td>
                </tr>
                <tr>
                    <td>新增专职体育教师（人）</td>
                    <td>体育教师退休和离职（人）</td>
                    <td>净增体育教师（人）</td>                   
                    <td>新增专职音乐教师（人）</td>
                    <td>音乐教师退休和离职（人）</td>
                    <td>净增音乐教师（人）</td>         
                    <td>新增专职美术教师（人）</td>
                    <td>美术教师退休和离职（人）</td>
                    <td>净增美术教师（人）</td>        
                </tr>
                <tr>
                    <td>${((preData.add_new_phy_teachers_full)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.retire_phy_teacher)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.growth_phy_teacher)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.add_new_music_teachers_full)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.retire_music_teacher)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.growth_music_teacher)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.add_new_draw_teachers_full)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.retire_draw_teacher)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.growth_draw_teacher)?if_exists.toString()?html)?if_exists}</td>
                </tr>

                <tr>
                    <td colspan="12" class="subtitle text-center">体育、艺术教师培训与教研员配备情况</td>
                </tr>
                <tr>
                    <td>体育教师培训（人次) </td>
                    <td> 培训费用（万元）</td>
                    <td>音乐教师培训（人次）</td>
                    <td>培训费用（万元）</td>
                    <td>美术教师培训（人次）</td>
                    <td>培训费用（万元）</td>
                </tr>
                <tr>
                    <td>${((preData.phy_teacher_exer)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_phy_teacher_exer)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.music_teacher_exer)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_music_teacher_exer)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.draw_teacher_exer)?if_exists.toString()?html)?if_exists}</td>
                    <td>${((preData.spend_draw_teacher_exer)?if_exists.toString()?html)?if_exists}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        <a href="pageOne!intoRepostForm.action" class="btn btn-success radius" ><strong>返回</strong></a>
         <a href="downLoadExcel!download.action" class="btn btn-success radius" ><strong>导出</strong></a>
    </div>
    <script src="${base}/lib/js/jquery-2.1.4.js"></script>
	<script src="${base}/lib/js/bootstrap.js"></script>
	<script src="${base}/lib/layer/2.1/layer.js"></script>
    <script>
	<#if isFinshMessage?exists>
		layer.msg('${isFinshMessage?if_exists}');
	</#if>
</script>
</body>

</html>

</@p.index>