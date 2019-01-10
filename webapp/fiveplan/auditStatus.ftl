<@p.index setReferUrl=true>
<head lang="en">
    <meta charset="UTF-8">
</head>
<body>

    <div class="container">

        <div class="body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                   
                        <th>文件名称</th>
                        <th>审核状态</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>      
                        <td>
                            <a> 
                            ${(uMp.O_NAME)?if_exists}&nbsp&nbsp&nbsp体育艺术五年行动计划年度推进情况统计表
                            </a>
                        </td>
                        <td>
                            ${(sMp.audit_status)?if_exists}
                        </td>
                    </tr>
                </thead>
            </table>
        </div>
    </div>

</body>
    <script src="${base}/lib/js/jquery-2.1.4.js"></script>
    <script src="${base}/lib/js/bootstrap.js"></script>
</@p.index>