//页面加载就绪时
$(document).ready(function(){
    var isInit = false;//标注是否是第一次加载
    var lablist={};//记录实验室信息列表
//        mLaborder=0;//记录显示的实验室序号

    //点击当前实验室菜单
    $('.lab-on').click(function (){
        console.log("您点击了lab-on");
        if(!isInit){
            isInit = true;//实验室名称列表只需要请求一次
            labs();//请求实验室名称列表
        }else{
//                refreshli();//刷新菜单列表，当前显示的实验室不同，菜单不同（选择其他菜单项时，再次打开）
        };
    });
    $('#addNotice_from').ajaxForm({

        success: function (result) {
            var noticeResult =  $('#noticeResult');
            noticeResult.addClass('alert-success');
            noticeResult.text("公告发布成功");
            noticeResult.show();
        },
        error: function () {
            $('#noticeResult').addClass('alert-danger').text("公告发布失败，请检查网络状态").show();
        }

    });

    $('#modal_close').click(function () {
        $('#modal_reset').click();
    })
});
//初次点击列表，展示该教师管理的实验室名称列表
//向后台发出请求，初始化lablist
function labs(){
    $.ajax({
        data:{
            data:'labs'
        },
        type:'post',
        url:'init',
        dataType:'json',
        success:function(result){
            console.log("get_labs请求成功");
            lablist =  result.data.labEntityList;
            refreshli(0);
        },
        error:function (result) {
            console.log("get_labs请求失败");
        }
    })
}
// 当实验室切换时，实验室菜单，人员列表,公告，实验室详细信息和标题都要改变
function  lab_changed(lab_order) {
    $.ajax({
        data:{
            data:lab_order
        },
        type:'post',
        url:'init',
        dataType:"json",
        success:function (result) {
            refreshli(lab_order);//刷新菜单项列表

//            console.log("result.data.studentEntityList=" +
//                result.data.stuEntityList);
//            mLaborder=lab_order;//请求成功之后将全局变量mLaborder赋值当前展示实验室序号


            //刷新学生信息表格
            var stulist = result.data.stuEntityList;
            var stu_admin_id=result.data.stu_admin_id;
            if(stulist!=null&stu_admin_id!=null){
                //遍历该实验室所拥有的学生信息填充表格
                refreshstu(stulist,stu_admin_id);
            }else {
                var t_body = $('#stu_body');
                t_body.empty();//清空学生信息表格
                t_body.append(
                    '<h3>暂无信息</h3>'
                );

                console.log("暂无通知");
            }

            //刷新公告信息表格


            console.log("lab_order:"+lab_order);
            //刷新公告表
            var noticelist =  result.data.noticeEntityList;//返回公告列表
            if(noticelist!=null){
                console.log("noticelist:"+noticelist);
                refresh_notice(noticelist);
            }else {
                var notice_body=$('#notice-body');
                notice_body.empty();
                notice_body.append( "<h3>暂无信息</h3>");
                console.log("暂无通知");
            }

            //刷新实验室详细信息


            refresh_labinfo(lab_order);
            //刷新教师信息
            var teacherList = result.data.teacherEntityList;
            var teacher_list =$('#teacher_list');
            teacher_list.empty();
            for(var i = 0; i<teacherList.length;i++){
                teacher_list.append(edit_teacher_tr(teacherList[i]))
            }

        },
        error:function (result) {
            console.log("请求失败");
        }
    });
}
//返回html标签字符串-菜单项
function editli(i,lab_name) {
    var str = "<li >" +
        " <a href=\"javascript:void(0);\" onclick=\"lab_changed(" +
        i +
        ")\"  data-labId='" +
        i +
        "'>"+lablist[i].name+"</a>" +
        "</li>";
    console.log("str : "+str);
    return str;
}
//刷新下拉菜单列表
function refreshli(lab_order){
    var mylab_list =$('#lab-list');
    mylab_list.empty();//清空列表
    //遍历实验室信息并且显示名称列表
    for(var i = 0 ; i < lablist.length;i++){
        if (i==lab_order){
            console.log("标注显示的实验室："+lablist[i].name);

            //刷新显示菜单项
            var lab_on = $('.lab-on');
            lab_on.text(lablist[lab_order].name);//修改下拉菜单的默认显示
//            lab_on.first().attr({'title':lab_order});//只给首次出现lab_on的标签加上title
//            console.log('显示菜单的title：'+lab_on.first().attr('title'));

            continue;
        }
        mylab_list.append(editli(i,lablist[i].name));

    }
}
//遍历该实验室所拥有的学生信息填充表格
function refreshstu(stulist,stu_admin_id) {
    var admin_i;
    var t_body = $('#stu_body');
    t_body.empty();//清空学生信息表格
    for(var i = 0 ; i < stulist.length;i++){
        console.log("当前实验室包含的学生："+stulist[i].name);
        if(stulist[i].id==stu_admin_id){//如果该学生是管理员
            admin_i=i;
            var stu_admin = $('#stu_admin');//获取显示实验室管理员名称的标签
            stu_admin.text(stulist[i].name);
            stu_admin.attr({'data-stuId':i});
            console.log(" $('#stu_admin'):"+stu_admin.text());
        }
        t_body.append(edit_stu_tr(i,stulist));//追加学生信息

    }
    var admin_td=$('#t_body').find('tr:eq('+admin_i+')');//找到管理员所在的行的备注单元格
    admin_td.find("input[name='setting]").attr({//标记为选中状态
        "checked":"checked",
        "hidden":"hidden"
    });
    // var item = $('input[name="setting"][checked]').hidden;
    $('<span>管理员</span>').appendTo(admin_td);
}
function refresh_notice(noticelist) {
    var notice_body=$('#notice-body');
    notice_body.empty();
    for(var i =0;i<noticelist.length;i++){
        notice_body.append(edit_pan(noticelist[i]));
    }
}
//刷新实验室详细信息
function refresh_labinfo(lab_order){
    console.log("lablist[lab_order].address:"+lablist[lab_order].address);
    console.log("刷新实验室详细信息"+lab_order);
    $('#lab_des').text(lablist[lab_order].describe);
    $('#lab_address').text(lablist[lab_order].address);
//    $('#lab_fre').text("1234");
    $('#lab_fre').text(lablist[lab_order].avg_fre);
}
//返回html标签字符串-学生信息列表区域
function edit_stu_tr(i,stulist){
    var str="<tr>" +
        "<td>" +
        stulist[i].id+
        "</td>" +
        "<td><a href='/Teacher/stu_info?stu_id=" +
        stulist[i].id+
        "' target=\"_blank\">" +
        stulist[i].name +
        "</a></td>" +
        "<td>" +
        stulist[i].grade +
        "</td>" +
        "<td>" +
        stulist[i].major+
        "</td>" +
        "<td>" +
        stulist[i].tel +
        "</td>" +
        "<td>" +
        stulist[i].frequency +
        "</td>"+
        "<td><input type='radio' name='setting' ></td>" +
        "</tr>"
    return str;
}
//返回html标签字符串-公告区域
function edit_pan(notice){
    var str = "<div class=\"panel panel-warning\">\n" +
        "<div class=\"panel-heading\">\n" +
        "<h3 class=\"panel-title\">\n" +
        "<span class=\"notice_title\">" +
        notice.title +//标题
        "</span>\n" +
        "<span class=\"pull-right\">" +
        notice.publisher//发布人
        + notice.time +//发布时间
        "||<a href=\"/student/downLoad?filePath=" +
        notice.file_path +
        "\" title=\"" +
        notice.file_name +
        "\"> 附件 </a></span>\n" +
        "</h3>\n" +
        "</div>\n" +
        "<div class=\"panel-body\">\n" +
        notice.content +
        "</div>\n" +
        "</div>";
    console.log("pan_str="+str);
    return str;
};
function edit_teacher_tr(teacher){
    var str = "<tr >\n" +
        "<td>" +
        teacher.id +
        "</td>\n" +
        "<td>" +
        teacher.name +
        "</td>\n" +
        "<td>" +
        teacher.gender +
        "</td>\n" +
        "<td>" +
        teacher.tel +
        "</td>\n" +
        "</tr>"
    return str;
}