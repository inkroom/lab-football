var base_path=$('#base_path').val();
function modaldemo(pid, reviewStatus){
    	$('#pid').val(pid);
    	
    	$.ajax({
            url: base_path+"/team/find_team_apply_players.action",
            type: "post",
            dataType: "json",
            data: {"pid":pid},
            async: true,
            success: function (data) {
                if(data.status==200){
                	$.each(data.playersList, function(index, json){
                		$('#playerName').html(json.playerName);
                    	$('#totalIntegral').html(json.totalIntegral);
                    	$('#playerStuID').html(json.playerStuID);
                    	$('#playerHight').html(json.playerHight);
                    	$('#playerWeight').html(json.playerWeight);
                    	$('#position').html(json.position);
                    	
                    	$('#school').html(json.school);
                    	
                    	$('#schoolHonrs').html(json.schoolHonrs);
                    	$('#countryHonrs').html(json.countryHonrs);
                    	$('#cityHours').html(json.cityHours);
                    	$('#provenceHours').html(json.provenceHours);
                    	$('#otherHours').html(json.otherHours);
                    	$('#ptNum').html(json.ptNum);
                    	$('#photo').attr('src', base_path+"/"+json.photo);
                	});
                	//隐藏按钮
                	if(reviewStatus!=0){
                		$('#agreeBtn').hide();
                		$('#refuseBtn').hide();
                	}else{
                		$('#agreeBtn').show();
                		$('#refuseBtn').show();
                		
                	}
                	if(reviewStatus!=1){
                		$('#ptNum').html("无");
                	}
                	
                	$("#modal-demo").modal("show");
                }else{
               
                	layer.alert(data.message, {icon: 0});
                	
                	if(data.status==500){
            	    	setTimeout(function(){
                    		window.top.location.replace(base_path+"/team/login_view.html");
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
    
    //同意
    $('#agreeBtn').click(function(){
    	
    	$("#modal-demo2").modal("show");
    });
    
    //验证球衣号
    $('#checkPtNum').click(function(){
    	var value = $('#ptNumArrange').val();
    	if(value == null || value.length==0){
    		layer.tips('请输入球衣号','#ptNumArrange',{ tips: [1, '#FF0033']});
    	}else{
    		if(value.length>2 || isNaN(value)){
    			layer.tips('请输入正确的球衣号(球衣号只有:0~99)','#ptNumArrange',{ tips: [1, '#FF0033']});
    		}else{
    			$.ajax({
    	            url: base_path+"/team/team_check_jersey_number.action",
    	            type: "post",
    	            dataType: "json",
    	            data: {"ptNum":value},
    	            async: false,
    	            success: function (data) {
    	            	if(data.status==200){
    	            		layer.confirm('您确定同意该球员加入您的球队吗?', {
    	          	  		  btn: ['确认','取消'] //按钮
    	          	  		}, function(){
    	          	  			$.ajax({
    	          	  	            url: base_path+"/team/team_audit_player_operation.action",
    	          	  	            type: "post",
    	          	  	            dataType: "json",
    	          	  	            data: {"pid":$('#pid').val(), "operType":1, "ptNum":value},
    	          	  	            async: true,
    	          	  	            success: function (data) {
    	          	  	            	
    	          	  	                if(data.status==200){
    	          	  	                	layer.alert(data.message, {icon: 6});
	    	          	  	                setTimeout(function(){
	       	          	  	            	 window.location.reload();
	       	                           		},1200);
    	          	  	                }else{
    	          	  	                	layer.alert(data.message, {icon: 5});
	    	          	  	                if(data.status==500){
	    	          	            	    	setTimeout(function(){
	    	          	                    		window.top.location.replace(base_path+"/team/login_view.html");
	    	          	                    		},2000);
	    	          	            	    }
    	          	  	                }
    	          	  	            },
    	          	  	    		error : function(req, status, reason) {
    	          	  	    			layer.alert('系统异常,请刷新重试', {
    	          	  	    				skin: 'layer-bule-style'
    	          	  	    			    ,closeBtn: 0
    	          	  	    		  	});
    	          	  	    			 window.location.reload();
    	          	  	    		}
    	          	  	        });
    	          	  		}, function(){
    	          	  			
    	          	  		});
    	                }else{
    	                	layer.alert(data.message, {icon: 5});
    	                	if(data.status==500){
    	            	    	setTimeout(function(){
    	                    		window.top.location.replace(base_path+"/team/login_view.html");
    	                    		},2000);
    	            	    }
    	                }
    	            },
    	    		error : function(req, status, reason) {
    	    			layer.alert('系统异常,请刷新重试', {
    	    				skin: 'layer-bule-style'
    	    			    ,closeBtn: 0
    	    		  	});
    	    			 window.location.reload();
    	    		}
    	        });
    		}
    	}
    });
    
 	//拒绝
    $('#refuseBtn').click(function(){
    	layer.confirm('您确定拒绝该球员加入您的球队吗?', {
	  		  btn: ['确认','取消'] //按钮
	  		}, function(){
	  			$.ajax({
	  	            url: base_path+"/team/team_audit_player_operation.action",
	  	            type: "post",
	  	            dataType: "json",
	  	            data: {"pid":$('#pid').val(), "operType":2, "ptNum":0},
	  	            async: true,
	  	            success: function (data) {
	  	            	if(data.status==200){
	  	                	layer.alert(data.message, {icon: 6});
	  	                	setTimeout(function(){
			  	            	 window.location.reload();
		                 		},1500);
	  	            	}else{
	  	                	layer.alert(data.message, {icon: 5});
	  	                	if(data.status==500){
	  	            	    	setTimeout(function(){
	  	                    		window.top.location.replace(base_path+"/team/login_view.html");
	  	                    		},2000);
	  	            	    }
	  	                }
	  	            },
	  	    		error : function(req, status, reason) {
	  	    			layer.alert('系统异常,请刷新重试', {
	  	    				skin: 'layer-bule-style'
	  	    			    ,closeBtn: 0
	  	    		  	});
	  	    			 window.location.reload();
	  	    		}
	  	        });
	  		}, function(){
	  			
	  		});
    	
    });
 	
    
  //搜索
	$("#seacherBtn").click(function(){
		
		$('#seacherForm').attr("action", base_path+"/team/seach_apply_join_team_players.action");
		$("#seacherForm").submit();
	});