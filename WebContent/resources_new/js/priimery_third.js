
window.onload=function() {
    var oAside = document.getElementById('side');
    var oAside_liList = oAside.getElementsByTagName('li');
    var oDivList = document.getElementsByClassName('main_content');
    for (var i = 0; i < oAside_liList.length; i++) {
        oAside_liList[i].index = i;
        oAside_liList[i].onclick = function () {
            for (var i = 0; i < oAside_liList.length; i++) {
                oDivList[i].style.display = "none";
                oAside_liList[i].style.background = "url(../../resources_new/img/navbg1.png) no-repeat";
                oAside_liList[i].style.backgroundSize = "100%";
                oAside_liList[i].style.backgroundPosition = "-17px -10px";
            }
            if (this.index + 1) {
                oDivList[this.index].style.display = "block";
                this.style.background = "url(../../resources_new/img/navbg.png) no-repeat";
                this.style.backgroundSize = "100%";
            } else {
                oDivList[this.index].style.display = "none";
                this.style.background = "url(../../resources_new/img/navbg1.png) no-repeat";
                this.style.backgroundSize = "100%";
                this.style.backgroundPosition = "-17px -10px";
            }
        }
    }
}


