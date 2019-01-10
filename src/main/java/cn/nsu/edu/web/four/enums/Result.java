package cn.nsu.edu.web.four.enums;

public enum Result {
    SUCCESS(0),
    FAIL(1),//账号或密码错误
    LOGIN_NOT(2),//登陆失效或者未登录
    NO_AUTHORITY(3),//没有操作权限
    LOGIN_EXISTS(4),//已经异地登陆
    EXCEPTION(5),//发生异常
    PARAM_NOT_SUIT(6),//参数不正确
    PARAM_LENGTH_NOT_SUIT(7),//参数长度不合适
    PARAM_NOT_EMPTY(8),//参数不能为空
    FILE_SIZE_NOT_SUIT(9),//文件大小不正确
    FILE_TYPE_NOT_SUIT(10),//文件类型不正确
    CODE_NOT_EXISTS(11),//验证码不存在，或者已失效
    CODE_NOT_CORRECT(12),//验证码错误
    CODE_ERROR_COUNT(13),//验证码错误次数已达上限
    TOKEN_ERROR(14),//token错误，多半页面过期
    SEND_PHONE_ERROR_COUNT(15),//短信发送过于频繁
    SEND_PHONE_ERROR_OTHER(16),//短信发送其余错误，不常见，多半是程序错误


    REFEREE_NOT_CONSUMMATE(17),//裁判身份信息未完善
    SCHEDULE_FINISH(18),//赛程已结束

    PHONE_NOT_PATTERN(19),//手机号码与之前的手机号码不一致

    REFEREE_NOE_CORRECT(20),//无效的裁判账号
    REFEREE_CHECK_ALREADY(21),//已进行入场验证


    //额外的枚举类型，只能在这之后添加
    IDCARD_EXIST(22),
    ILLEGAL_EXIST(23),
    ORG_EXIST(24),
    PLAYER_EXIST(25),
    PLAYER_DEL(26),

    ;

    //额外的枚举类型，在这之前添加，这以下的勿动
    private int code;

    private Result(int code) {
        this.code = code;
    }

    public int value() {
        return this.code;
    }
}
