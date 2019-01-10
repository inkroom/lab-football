function loadJS(asideArray){
	
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host;
	var server_context=basePath;
	
	console.log(server_context);
	
	var oAside=document.getElementById('side');
	var oAside_liList=oAside.getElementsByTagName('li');
	var oiframe=document.getElementsByClassName('main_iframe')[0].getElementsByTagName('iframe')[0];
	var oNav=document.getElementById('main').getElementsByClassName('breadcrumb')[0];
	var oSpan=oNav.getElementsByTagName('span')[2];
	for(var i=0;i<oAside_liList.length;i++){
		oAside_liList[i].index=i;
		oAside_liList[i].onclick=function(){
			oSpan.innerHTML=this.innerHTML;
			for(var i=0;i<oAside_liList.length;i++){
				oAside_liList[i].style.background="url("+server_context+"/resources_new/img/navbg1.png) no-repeat";
				oAside_liList[i].style.backgroundSize="100%";
				oAside_liList[i].style.backgroundPosition="-17px -10px";
			}
			if(this.index+1){
				oiframe.src=asideArray[this.index];
				this.style.background="url("+server_context+"/resources_new/img/navbg.png) no-repeat";
				this.style.backgroundSize="100%";
			}else{
				this.style.background="url("+server_context+"/resources_new/img/navbg1.png) no-repeat";
				this.style.backgroundSize="100%";
				this.style.backgroundPosition="-17px -10px";
			}
		}
	}
}


//校园风采

//校园风采
