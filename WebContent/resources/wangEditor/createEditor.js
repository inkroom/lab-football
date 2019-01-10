function createEditor(url){
	editor.config.menus = [
		'source',
	    '|',
	    'bold',
	    'underline',
	    'italic',
	    'strikethrough',
	    'eraser',
	    'forecolor',
	    'bgcolor',
	    '|',
	    'quote',
	    'fontfamily',
	    'fontsize',
	    'head',
	    'unorderlist',
	    'orderlist',
	    'alignleft',
	    'aligncenter',
	    'alignright',
	    '|',
	    'link',
	    'unlink',
	    'table',
	    '|',
	    'img',
	    '|',
	    'undo',
	    'redo',
	    'fullscreen'
	];
	editor.config.uploadImgUrl = url;
	editor.config.uploadImgFileName = 'file';
	editor.config.hideLinkImg = true;
	
	editor.create();
}