function getMatchLevel(level) {
    switch (level) {
        case 0:
            return "校级";
        case 1:
            return "县级";
        case 2:
            return "市级";
        case 3:
            return "省级";
        case 4:
            return "国级";
        default:
            return "Error Level Code" + level;
    }
}

function closeLyaer() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}


function getMatchStatus(status) {
    switch (status) {
        case 0:
            return "报名阶段";
        case 1:
            return "进行中";
        case 2:
            return "已结束";
        case 3:
            return "删除状态"
    }
}

function getDateString(startDate) {
    date = new Date(startDate);
    return date.getFullYear() + "年" + date.getMonth() + "月" + date.getDay() + "日";
}

function parseTime(timestamp) {
    var date = new Date(timestamp);
    var day = ("0" + date.getDate()).slice(-2);
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    return date.getFullYear() + "-" + (month) + "-" + (day);
}