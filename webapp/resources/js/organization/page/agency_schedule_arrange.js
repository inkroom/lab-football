/**
 * Created by Jerry on 17/6/3.
 */

var basePath=$('#base_path').val();
var error=$('#error').val();
var st_time=$('#st_time').val();
var end_time=$('#end_time').val();

/*后台校验错误信息提示*/
$(function(){
    layer.ready(function(){
        if(error!= null && error!= ''){
            layer.alert(error);
            console.log(error);
        }
    });
});

/*提交赛程信息*/
var save_flag = true;
$("#subinfo").click(function () {
    if(tjqd()!=false&&tj()!=false&&tjdqu()!=false){
        save_flag=false;
        $.ajax({
            type:"post",
            url:''+basePath+'/org/add_arrangement.action',
            async:true,
            dataType:"json",
            data:  {schedule:  JSON.stringify($("#myform").serializeArray()),"_csrf":token},
            success:function (data) {
                if(data.status == 200 && data.success){
                    layer.alert("操作成功！",function () {
                        window.location.href = ''+basePath+'/org/race_details_view.html';
                    })
                }else if(data.status == 200 && !data.success){
                    layer.alert(data.msg,function () {
                        window.location.reload();
                    })
                }else if(data.status == "500" && !data.success){
                    layer.alert(data.msg,function () {
                        window.location.reload();
                    })
                }else{
                    layer.alert("提交失败，请重试！",function () {
                        window.location.reload();
                    })
                }

            }
        });
    }
});



/*左侧下拉框 选择option  加载图片*/
$(document).on("change",'.leftselect',function () {
    noequl(this, ".rightselect");

    var mythis = $(this);
    //添加图片
        var left_select_value = $(this).val();
        if (left_select_value !== '0') {

            $.ajax({
            type: "post",
            url: '' + basePath + '/org/getTeamPic.action',
            data: {team_id: left_select_value},
            success: function (data) {
                mythis.prev().attr('src', '' + basePath + '' + "/" + data);
            }


        });
    } else {
        mythis.prev().attr('src', 'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496462250&di=22c6032636d747ae3fc0c65f1a517ca5&src=http://pic.58pic.com/58pic/13/80/45/13D58PICMWc_1024.jpg')

    }


});
$(document).on("change",'.rightselect',function () {

    noequl(this, ".leftselect");

    var mythis = $(this);
    //添加图片
    var right_select_value = $(this).val();
    if (right_select_value !== '0') {
        $.ajax({
            type: "post",
            url: '' + basePath + '/org/getTeamPic.action',
            data: {team_id: right_select_value},
            success: function (data) {
                mythis.prev().attr('src', basePath + "/" + data);
            }
        });
    } else {
        mythis.prev().attr('src', 'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496462250&di=22c6032636d747ae3fc0c65f1a517ca5&src=http://pic.58pic.com/58pic/13/80/45/13D58PICMWc_1024.jpg')
    }

});


/*下拉框 不让用户重复选择*/
function noequl(e,f) {
    $(e).off("change");
    var ta = $(e).val();
    var oa = $(e).parent("td").parent("tr").find(f).val();
    var ov = $(e).parent("td").parent("tr").find(f).find("option");
    var tv = $(e).find("option");
    ov.removeAttr("disabled");
    tv.each(function () {
        if($(this).val() === oa ){
            $(this).attr("disabled","true");
        }
        if($(this).val()==='0'){
            $(this).removeAttr("disabled");
        }
    });
    ov.each(function(){
        if($(this).val() === ta){
            $(this).attr("disabled","true");
        }
        if($(this).val()==='0'){
            $(this).removeAttr("disabled");
        }
    });
}


/*时间校验*/
function checkEndTime(a,b,c){
    $(a).off("click change");
    var startTime=$(a).parent("td").parent("tr").find(".leftstart").val();
    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
    var endTime=$(a).parent("td").parent("tr").find(".rightend").val();
    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
    var st=st_time;
    var sttime=new Date(st.replace("-", "/").replace("-", "/"));
    var ed=end_time;
    var edtime=new Date(ed.replace("-", "/").replace("-", "/"));
    var now=new Date();
    if (start!="Invalid Date"&&end!="Invalid Date") {
        if (end <= start) {
            layer.msg("开始时间不能先于结束时间");
            return true;
        }
    }

    if(start<=sttime){
        layer.msg("赛程开始时间不能先于赛事开始时间");
        return true;
    }
    if(end>=ed){
        layer.msg("赛程结束时间不能先于赛事结束时间");
        return true;
    }
    if(start<now||end<now){

        layer.msg("不能选择以前的时间！")
        return true;
    }
}
$(document).on("change click",'.leftstart',function (){
    if(checkEndTime(this)){
        $(this).val("");
    }
});
$(document).on("change click",'.rightend',function (){
    if(checkEndTime(this)){
        $(this).val("");
    }
});


/*日期 球队  地区 输入不能为空  */
function tj() {
    for(var i=0;i<$("#match tbody tr").length;i++){
        var inputinfo1=$("#match tbody tr").eq(i).find(".leftstart").val();
        var inputinfo2=$("#match tbody tr").eq(i).find(".rightend").val();
        if (inputinfo1==null||inputinfo1==""||inputinfo2==null||inputinfo2==""){
            layer.msg("日期不能为空值！");
            return false;
        }
    }
}
function tjqd() {
    for(var i=0;i<$("#match tbody tr").length;i++){
        var inputinfo1=$("#match tbody tr").eq(i).find(".leftselect").val();
        var inputinfo2=$("#match tbody tr").eq(i).find(".rightselect").val();
        if (inputinfo1==null||inputinfo1=="请选择"||inputinfo2==null||inputinfo2=="请选择"){
            layer.msg("球队不能为空！");
            return false;
        }
    }
}

function tjdqu() {
    for(var i=0;i<$("#match tbody tr").length;i++){
        var inputinfo=$("#match tbody tr").eq(i).find(".r_region").val();
        if (inputinfo==""){
            layer.msg("地区不能为空");
            return false;
        }
    }
}


/*返回赛事信息界面*/
$("#returninfo").click(function () {
    window.location.href=''+basePath+'/org/race_schedule_view.html';
});



/*处理日期  日期组件 添加 时间信息*/
$(".date_a ").cxCalendar({
    type: 'datetime',
    format: 'YYYY-MM-DD HH:mm:ss'
});


/*删除赛程*/
$("#removeInfo").on("click", function () {
    if ($("#match tbody tr").length != 1) {
        $("#match tbody tr:last-child").remove();
    }
});
