/**
 * Created by WHY on 2017/3/23.
 */
(function ($) {
    $(".team-one").css("display","block");
    $(".team-two").css("display","none");
    $(".team-title li").click(function () {
        if($(this).index()==0){
            $(".team-two").css("display","none");
            $(".team-one").css("display","block");
        }else{
            $(".team-one").css("display","none");
            $(".team-two").css("display","block");
        }
    })
})(jQuery);