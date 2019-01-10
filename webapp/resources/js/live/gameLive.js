/**
 * Created by WHY on 2017/3/23.
 */
// (function ($) {
//     $("#l-plus").click(function () {
//         var lscore=parseInt($("#l-score").text());
//         $("#l-score").text(lscore+1);
//     })
//     $("#l-minus").click(function () {
//         var lscore=parseInt($("#l-score").text());
//         if(lscore>0){
//             $("#l-score").text(lscore-1);
//         }else{
//             $("#l-score").text(0);
//         }
//     })
//     $("#r-plus").click(function () {
//         var rscore=parseInt($("#r-score").text());
//         $("#r-score").text(rscore+1);
//     })
//     $("#r-minus").click(function () {
//         var rscore=parseInt($("#r-score").text());
//         if(rscore>0){
//             $("#r-score").text(rscore-1);
//         }else{
//             $("#r-score").text(0);
//         }
//     })
// })(jQuery)

window.onload=function(){
    var l_btn_add=document.getElementById("l-plus");
    var l_btn_minus=document.getElementById("l-minus");
    var r_btn_add=document.getElementById("r-plus");
    var r_btn_minus=document.getElementById("r-minus");
    l_btn_add.onclick=function(){
        var num=parseInt(document.getElementById("l-score").textContent);
        num=num+1;
        document.getElementById("l-score").innerHTML=num.toString();
    }
    l_btn_minus.onclick=function(){
        var num=parseInt(document.getElementById("l-score").textContent);
        num=num-1;
        if(num<=0){
            num=0;
            document.getElementById("l-score").innerHTML=num.toString();
        }
        else{
            document.getElementById("l-score").innerHTML=num.toString();
        }
    }
    r_btn_add.onclick=function(){
        var num=parseInt(document.getElementById("r-score").textContent);
        num=num+1;
        document.getElementById("r-score").innerHTML=num.toString();
    }
    r_btn_minus.onclick=function(){
        var num=parseInt(document.getElementById("r-score").textContent);
        num=num-1;
        if(num<=0){
            num=0;
            document.getElementById("r-score").innerHTML=num.toString();
        }
        else{
            document.getElementById("r-score").innerHTML=num.toString();
        }
    }
}