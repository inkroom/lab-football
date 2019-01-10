<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../lib/css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style>
        .page-container{
            margin-top: 50px;
        }
       .form-control{
           width: 50%;
           float: left;
       }
        .control-label{
            display: inline-block;
            width: 50%;
            text-align: center;
            line-height: 30px;
        }
    </style>
</head>
<body >

<div class="container">
<form id="uploadForm" action="repostForm!uploadForm.action" method="post">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">基础数据</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>
                <tr class="text-c">
                    <td rowspan="3">基础数据</td>
                    <td>学校数（所）</td>
                    <td><input class="input-text form-control" name="data1"/><label class="control-label">${(oldData.data1)?if_exists}</label></td>
                </tr>
                <tr class="text-c">
                    <td>在校生人数</td>
                    <td><input class="input-text form-control" name="data2"/></td>
                </tr>
                <tr class="text-c">
                    <td>实际班级数量</td>
                    <td>
                        <div class="form-group">
                            <select class="form-control"  name="data3">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr class="text-c">
                    <td rowspan="3">开课及课外活动</td>
                    <td>体育课开足数</td>
                    <td><input class="input-text form-control" name="data4" /></td>
                </tr>
                <tr class="text-c">
                    <td>落实每天一小时体育锻炼数</td>
                    <td><input class="input-text form-control"  name="data5"/></td>
                </tr>
                <tr class="text-c">
                    <td>组织大课间体育活动数</td>
                    <td><input class="input-text form-control" name="data6"/></td>
                </tr>
                <tr class="text-c">
                    <td rowspan="7">体育师资</td>
                    <td>专职体育教师人数(人)</td>
                    <td><input class="input-text form-control" name="data7"/></td>
                </tr>
                <tr class="text-c">
                    <td>兼职体育教师人数(人)</td>
                    <td><input class="input-text form-control" name="data8"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师缺额比（%）</td>
                    <td><input class="input-text form-control" name="data9"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师缺额数</td>
                    <td><input class="input-text form-control" name="data10"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师生师比</td>
                    <td><input class="input-text form-control" name="data11"/></td>
                </tr>
                <tr class="text-c">
                    <td>体育教师参训人数</td>
                    <td><input class="input-text form-control" name="data12"/></td>
                </tr>
                <tr class="text-c">
                    <td>教师受县级以上表彰人数</td>
                    <td><input class="input-text form-control" name="data13"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        <button type="submit"   class="btn btn-success radius"><strong>取消</strong></button>
        <button type="submit"   class="btn btn-success radius" ><strong>下一步</strong></button>
    </div>
</form>
</div>
<script src="../lib/js/jquery-2.1.4.js"></script>
<script src="../lib/js/bootstrap.js"></script>
</body>
</html>