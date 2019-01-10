/**
 * Created by Jerry on 17/6/3.
 */

var basePath=$('#base_path').val();
var error=$('#error').val();

/*后台校验错误信息提示*/
$(function(){
    layer.ready(function(){
        if(error!= null && error!= ''){
            layer.alert(error);
            console.log(error);
        }
    });
});


/*下载Excel*/
$(function () {

    $("#exportExcel").click(function(){

        window.location.href = ''+basePath+'/org/excel.html';

    })
});

/*返回赛程发布信息界面*/
$("#return_info").click(function () {

    window.location.href=''+basePath+'/org/race_schedule_view.html';

});