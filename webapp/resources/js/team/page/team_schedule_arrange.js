var base_path=$('#base_path').val();
var vm = avalon.define({
	$id: "listPlayer",
	infoList: [],
	operPlanPlayer:function(pname, pid, rID, type){
		operPlanPlayers(pname, pid, rID, type);
	}
});

$('#okBtn').click(function(){
	 window.location.reload();	
});

function modaldemo(rID, planContinue){
	if(planContinue==1){
		$.ajax({
            url: base_path+"/team/find_team_plan_players.action",
            type: "POST",
            dataType: "json",
            data: {"rID":rID},
            async: true,
            success: function (data) {
                if(data.status==200){
                	var json=eval(data)
                    vm.infoList =json.playersList;
                	$("#modal-demo").modal("show");
                }else{
                	layer.alert(data.message, {icon: 0});
                	if(data.status==500){
            	    	setTimeout(function(){
                    		window.parent.location.replace(base_path+"/team/login_view.html");
                    		},2000);
            	    }
                	return false;
                }
            },
    		error : function(req, status, reason) {
    			layer.alert('系统异常,请刷新重试', {
    				skin: 'layer-bule-style'
    			    ,closeBtn: 0
    		  	});
    		}
        });
	}else{
		layer.alert('比赛已经开始,不能再做更改', {
			skin: 'layer-bule-style'
		    ,closeBtn: 0
	  	});
	}
	
}

function operPlanPlayers(pname, pid, rID, type){
	if(type==1){
		layer.confirm('您确认要移除'+pname+'吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			$.ajax({
		            url: base_path+"/team/team_arrange_players.action",
		            type: "POST",
		            dataType: "json",
		            data: {"rID":rID, "pid":pid, "type":type},
		            async: true,
		            success: function (data) {
		            	
		                if(data.status==200){
		                	//更新球员框
		                	layer.alert(data.message, {icon: 1});
		                	var json =eval(data)
                			vm.infoList =json.playersList;
		                }else{
		                	layer.alert(data.message, {icon: 0});
		                	if(data.status==500){
		            	    	setTimeout(function(){
		                    		window.parent.location.replace(base_path+"/team/login_view.html");
		                    		},2000);
		            	    }
		                	return false;
		                }
		            },
		    		error : function(req, status, reason) {
		    			layer.alert('系统异常,请刷新重试', {
		    				skin: 'layer-bule-style'
		    			    ,closeBtn: 0
		    		  	});
		    		}
		        });
	  		}, function(){
	  			
	  		});
	}else{
		$.ajax({
            url: base_path+"/team/team_arrange_players.action",
            type: "POST",
            dataType: "json",
            data: {"rID":rID, "pid":pid, "type":type},
            async: true,
            success: function (data) {
            	
                if(data.status==200){
                	//更新球员框
                	layer.alert(data.message, {icon: 1});
                	var json=eval(data);
                	vm.infoList =json.playersList;
                }else{
                	layer.alert(data.message, {icon: 0});
                	if(data.status==500){
            	    	setTimeout(function(){
                    		window.parent.location.replace(base_path+"/team/login_view.html");
                    		},2000);
            	    }
                	return false;
                }
            },
    		error : function(req, status, reason) {
    			layer.alert('系统异常,请刷新重试', {
    				skin: 'layer-bule-style'
    			    ,closeBtn: 0
    		  	});
    		}
        });
	}
}