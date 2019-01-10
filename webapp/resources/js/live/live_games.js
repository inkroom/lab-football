/**
 * Created by yrge on 2017/4/18.
 */
// var vm =new Vue({
//     el:'#vue_w',
//     data: {
//         json: []
//     }
// });
var vm = avalon.define({
    $id: "vue_w",
    json: []
});
function city(curr){
    $.ajax({
        url:"list_city.action",
        type:"POST",

        success:function(data){
            var json=eval(data)
            vm.json =json
        },
        error:function(er){

            console.log(er)
        }
    });
};



//运行
city();
