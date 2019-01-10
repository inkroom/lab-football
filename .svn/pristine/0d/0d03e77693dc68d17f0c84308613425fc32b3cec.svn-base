var data = [
    {"code" : "1", "name" : "成都市",
        "cities" : [
            {"code" : "10002", "name" : "成华区"},
            {"code" : "10003", "name" : "金牛区"},
            {"code" : "10004", "name" : "都江堰市"}
        ]
    },
    {"code" : "2", "name" : "绵阳市",
        "cities" : [
            {"code" : "20002", "name" : "游仙区"},
            {"code" : "20003", "name" : "江油市"},
            {"code" : "20004", "name" : "平武县"}
        ]
    }
    // {"code" : "3", "name" : "广元市",
    //     "cities" : [
    //         {"code" : "30002", "name" : "剑阁县"},
    //         {"code" : "30003", "name" : "旺苍县"},
    //         {"code" : "30004", "name" : "青川县"}
    //     ]
    // }
];
window.onload = function(){
    // 获取省份城市select
    var proSelect = document.getElementById("city");
    var proSelect1 = document.getElementById("school");
    for (var i = 0; i < data.length; i++){
        var json = data[i];
        var option = new Option(json.name, json.code, false, false);
        proSelect.add(option);
    }
    // 为proSelect绑定onChange事件
    proSelect.onchange = function(){
        var citySelect = document.getElementById("area");
        // 在下次选择省份之前先清空城市下拉列表
        for (var i = citySelect.length - 1; i > 0; i--){
            citySelect.remove(i);
        }
        for (var i = 0; i < data.length; i++){
            var json = data[i];
            if (json.code == this.value){
                // 取城市
                var cities = json.cities;
                for (var j = 0; j < cities.length; j++){
                    // 获取其中的json
                    var temp = cities[j];
                    var option = new Option(temp.name, temp.code, false, false);
                    citySelect.add(option);
                }
            }
        }
    }
}