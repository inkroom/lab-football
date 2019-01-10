//以下将以jquery.ajax为例，演示一个异步分页

/*
 * 实例化 Vue
 */
   var vm = avalon.define({
        $id: "for_w",
        json:[]
    });
function demo(curr){
	$.ajax({
		url:"list1.action",
		type:"POST",
		data:{
			"pageNum":curr||1,
		},
		dataType:"json",
		success:function(data){
			$("#addjson").empty();
			var json=eval(data);
			vm.json =json.pageInfo.list//给 json 赋值
			$('#loadData').empty();
			for(var i=0;i<vm.json.length;i++){
            	loadData(vm.json[i]);
            }
			
		    //显示分页
		    laypage({
		      cont: 'page1', 
		      pages: json.pageInfo.pages, 
		      curr: curr || 1, 
		      jump: function(obj, first){ 
		        if(!first){ 
		          demo(obj.curr);
		        }
		      }
		    });
		},
		error:function(er){
			console.log(er)
		}
		  });
		};

//运行
demo();
function loadData(value) {
	var level="";
	if(value.TEAM_AFFILIATION != null && value.TEAM_AUDIT == 1){
		level = value.TEAM_AFFILIATION;
	}else{
		level = '暂无';
	}
	var status="";
	switch(value.TEAM_AUDIT){
		case 0:
			status='<span class="label label-primary radius">待审核</span>';
			break;
		case 1:
			status='<span class="label label-success radius">审核通过</span>';
			break;
		case 2:
			status='<span class="label label-warning radius">审核未通过</span>';
			break;
		case 3:
			status='<span class="label label-warning radius">删除</span>';
	}
	var team_name="'"+value.TEAM_NAME+"'";
	var team_status="'"+value.TEAM_AUDIT+"'";
	var team_id="'"+value.TEAM_ID+"'";
	'++""+','+value.TEAM_COM_STATUS+','+value.COM_NAME+','+value.TEAM_ID+'
	$('#loadData').append('<tr class="text-c">'
	+'<td>'+value.TEAM_NAME+'</td>'
	+'<td>'+value.TEAM_LEADER+'</td>'
	+'<td>'+level+'</td>'
    +'<td>'+status+'</td>'
    +'<td>'+'<button class="btn btn-success radius btn_data_name" onclick="modaldemo('+team_name+','+team_status+','+team_id+')">查看详情</button>'+'</td>'
+'</tr>');
	
}

