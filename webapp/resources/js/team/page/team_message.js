var base_path=$('#base_path').val();
var aud = document.getElementById("aud");


$(function () { 

		var s = aud.networkState;
		if(s==1){
			
			aud.play();
			document.getElementById("btn_aud").innerHTML="暂停播放"+"<i class='Hui-iconfont Hui-iconfont-zanting'></i>";
			$('#musicStatus').html("正在播放队歌");
		}
});


function aud_play() {
	var s = aud.networkState;
	if(s!=1){
		layer.msg("未找到队歌，请上传", {icon: 5});
	}else{
		if(aud.paused){
	        aud.play();
	        document.getElementById("btn_aud").innerHTML="暂停播放"+"<i class='Hui-iconfont Hui-iconfont-zanting'></i>";
	        $('#musicStatus').html("正在播放队歌");
	    }else{
	        aud.pause();
	        document.getElementById("btn_aud").innerHTML="播放队歌"+"<i class='Hui-iconfont Hui-iconfont-bofang'></i>";
	        $('#musicStatus').html("");
	    }
	}
		
    
}

$("#file1").change(function(){
	var dom1 = document.getElementById("file1");
	var fileName = $("#file1").val();
	if( $("#file1").val() != ""){
		layer.confirm('是否确认上传队歌', {
  		  btn: ['确认','取消'] //按钮
  		}, function(){
  	  			//验证文件格式
  	  			var index = fileName.lastIndexOf('.');
	       		var filetype = fileName.substring(index, fileName.length);
	       		if(filetype!=".mp3"){
	       			layer.alert('文件格式错误  (请上传mp3格式的歌曲)', {icon: 5});
	       			clearFile();
	       			setTimeout(function(){
                		//刷新本页 (注:这里使用 window.location.reload();不会从新加载音乐资源文件
                		window.location.reload(true);
                 	},1000);
	       			return false;
		       	}
  	  			//验证文件大小
  	  	        var fileSize = dom1.files[0].size;
  	  			if(fileSize>5*1024*1024){
  	  				layer.alert('文件最大为5M', {icon: 5});
  	  				clearFile();
  	  				setTimeout(function(){
          			//刷新本页 (注:这里使用 window.location.reload();不会从新加载音乐资源文件
          				window.location.reload(true);
           			},1000);
	  	  			return false;
  	  			}
  	  			layer.closeAll('dialog');
  	  			layer.load();
  	  		 	var option = {
  	                url : base_path+'/team/upload_team_song.action',
  	                type : 'POST',
  	                dataType : 'json',
  	                headers : {"ClientCallMode" : "ajax"}, //添加请求头部
  	                success : function(data) {
  	                	if(data.status==200){
  	                		layer.alert(data.message, {icon: 6});
  	                		setTimeout(function(){
  	                			//刷新本页 (注:这里使用 window.location.reload();不会从新加载音乐资源文件
  	                			window.location.reload(true);
  	                 		},1000);
  	                		
  	                	}else{
  	                		layer.alert(data.message, {icon: 5});
  	                	}
  	                },
  	                error: function(data) {
  	                	layer.alert('文件上传失败', {icon: 5});
  	                }
  	             };
  	            $("#songForm").ajaxSubmit(option);
	  	          	clearFile();
	  	            return false;
  	  		
  	  		}, function(){
  	  			clearFile();
    		});
    	}   	
    });
    		
   function clearFile(){
	    var file = $("#file1") ;
	file.after(file.clone().val(""));      
	file.remove();
	$("#uploadfile-1").val("");
   }