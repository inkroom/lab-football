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
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                     
                        <td>
                            <a>
                            四川省体育艺术五年行动计划年度推进情况统计表.xls
                            </a>
                        </td>
                        <td>
                            <a href="downLoadExcel!download.action" class="btn btn-size btn-success">下载</a>
                        </td>
                    </tr>
                     <tr>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                     
                        <td>
                            <a>
                       0829-相关标准和文件依据.rar
                            </a>
                        </td>
                        <td>
                            <a href="downLoadWorld!downLoadWorld.action" class="btn btn-size btn-success">下载</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</body>
    <script src="${base}/lib/js/jquery-2.1.4.js"></script>
    <script src="${base}/lib/js/bootstrap.js"></script>
    <script>
        $(function(){

           $("tbody tr").mouseover(function(){
               $(this).addClass("active");
           });
           $("tbody tr").mouseout(function(){
               $(this).removeClass("active");
           });
        });
    </script>
</@p.index>