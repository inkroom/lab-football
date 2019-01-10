/*
 * 比赛情况
 */
$(function(){
	$('.col-center').height($('.col-left').height());
});
/*
 * 底部
 */
$('#index').click(function(){
	$('#jf>p,#call>p,#my>p').css('color','#323232');
 	$('#index>p').css('color','#5496F9');
	$('#index>img').attr('src','img/index-on.png');
	$('#jf>img').attr('src','img/identity.png');
	$('#call>img').attr('src','img/QRCode.png');
	$('#my>img').attr('src','img/revise.png');
})
		  	
$('#jf').click(function(){

	$('#index>p,#call>p,#my>p').css('color','#323232');
	$('#jf>p').css('color','#5496F9');
	$('#jf>img').attr('src','img/identity-on.png');
	$('#index>img').attr('src','img/index.png');
	$('#call>img').attr('src','img/QRCode.png');
	$('#my>img').attr('src','img/revise.png');
})
		  	
$('#call').click(function(){
	$('#jf>p,#index>p,#my>p').css('color','#323232');
	$('#call>p').css('color','#5496F9');
	$('#call>img').attr('src','img/QRCode-on.png');
	$('#jf>img').attr('src','img/identity.png');
	$('#index>img').attr('src','img/index.png');
	$('#my>img').attr('src','img/revise.png');
})
		  	
$('#my').click(function(){
	$('#jf>p,#call>p,#index>p').css('color','#323232');
	$('#my>p').css('color','#5496F9');
	$('#my>img').attr('src','img/revise-on.png');
	$('#jf>img').attr('src','img/identity.png');
	$('#call>img').attr('src','img/QRCode.png');
	$('#index>img').attr('src','img/index.png');
})
/*完善信息按钮*/