$(function() {
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.link-button');
		// Evento
		links.on('click', {
			el: this.el,
			multiple: this.multiple
		}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
		$this = $(this),
			$next = $this.next();

		$next.slideToggle(0);
		$this.parent().toggleClass('open');

		if(!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp(0).parent().removeClass('open');
		};
	}

	var accordion = new Accordion($('#accordion'), false);
});


//二级交互
function js(id) {
			return document.getElementById(id).getElementsByClassName("a");
		}
		for(var i = 0; i < js("accordion").length; i++) {
			js("accordion")[i].onclick = function() {
				delBackgroundColor();
				this.style.background = "#EBE5E1";
				}
			}
		

		function delBackgroundColor() {
			for(var i = 0; i < js("accordion").length; i++) {
				js("accordion")[i].style.background = '';
			}
		}