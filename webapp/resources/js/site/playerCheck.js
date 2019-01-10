var one = document.getElementsByClassName("team-one")[0];
var two = document.getElementsByClassName("team-two")[0];
var li = document.getElementsByClassName("team-title")[0].children;
changeText();
for(var i= 0;i<li.length;i++){
    li[i].index=i;
    li[i].onclick=function () {
        if(this.index==0){
            li[1].className="f-l f-24 pl-20 pr-20";
            li[0].className+=" check";
            one.style.display="block";
            two.style.display="none";
        }else{
            li[0].className="f-l f-24 pl-20 pr-20";
            li[1].className+=" check";
            two.style.display="block";
            one.style.display="none";
        }
    }
}
function changeText() {
    var span=document.getElementsByTagName("span");
    for(var j=0;j<span.length;j++){
        if(span[j].innerText.length>=6){
            span[j].style.Height="26px";
            span[j].style.lineHeight="26px";
        }
    }
}