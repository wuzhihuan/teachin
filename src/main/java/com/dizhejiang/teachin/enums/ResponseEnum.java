package com.dizhejiang.teachin.enums;

public enum ResponseEnum {

    //成功
    SUCCESS(10000, "成功"),

    //常用异常code 值 10001~11000
    METHOD_ARGUMENT_NOT_VALID_ERROR(10001, "请求参数校验不合格"),
    PLEASE_CHOOSE_OPERATION_OBJECT(10002, "请选择操作对象"),
    SERVICE_MATURITY(10003, "您好,您的租户服务已到期"),
    RESPONSE_STATUS_EXCEPTION(10005, "服务器响应异常"),
    LOGIN_TOKEN_ACCESS_TIME_OUT_PLEASE_TRY_RELOGIN(10006, "访问令牌已过期，请登录后重试"),
    LIST_CHANGE_TREE_CANT_FIND_ROOT_NODE(10007, "列表转换成树时未找到根节点"),
    DID_NOT_FOUND_LABEL_CONTENT_TYPE_ENUM_BY_THIS_CODE(10008, "未根据评价标签code找到对应评价枚举对象"),
    UNKNOW_EVALUATE(10009, "未知的社保类型"),
    REQUEST_METHOD_UNSUPPORT_ERROR(10010, "不支持的请求方式"),
    API_ACCOUNT_TYPE_NOT_SUPPORT(10011, "不支持的用户类型"),
    UNAUHORIZATION(10012,"权限不足"),

    //用户模块code 使用 11001~19999
    USERNAME_EXIST(11001, "用户名已存在"),
    SMS_CODE_INVALID(11002,"短信验证码已失效，请重新发送"),
    SMS_CODE_ERROE(11003,"验证码错误，请重新输入"),
    USER_NOT_EXIST(11004, "用户不存在"),
    FRIEND_APPLY_NOT_EXIST(11005,"好友申请不存在"),
    GROUP_NAME_IS_EXIST(11006,"组名称重复"),
    GROUP_NOT_EXIST(11007,"组不存在"),
    JWT_INVALID_CLAIM(11010, "当前登录信息失效，请重新登录"),
    CAN_NOT_ADD_SELF(11011,"不能添加自己为好友"),
    NOT_VERIFIED(11012,"用户认证未提交"),
    USER_NOT_VERIFIED(11013,"用户未实名,请先实名认证"),
    CITY_NOT_EXIST(11014,"该城市信息还未录入"),
    PHONE_IS_NOT_EXIST(11015,"手机号不正确，请重新输入"),
    PHONE_IS_EXIST(11016,"手机号已存在"),
    ACCOUNT_NOT_EXIST(11017,"账户不存在"),
    USER_GOAL_IS_EXIST(11018,"请完善资料"),
    SMS_CODE_FREQUENCY(11019,"短信发送过于频繁，请稍后再试"),
    USER_DETAILS_EXIST(11020,"用户详情已存在"),

    //文章
    ARTICLE_NOT_EXIST(12000,"文章不存在"),
    NEWS_NOT_EXIST(12001,"新闻你不存在"),
    COMMENT_NOT_EXIST(12002,"评论不存在"),
    PRAISE_ALREADY(12003,"已点赞"),
    //系统级别异常
    DATA_ADD_FAILED(99001, "服务调用失败！"),
    SERVICE_CALL_FAILED(99001, "服务调用失败！"),
    ERROR(99999, "系统错误，未知异常");

    private final int code;
    private final String desc;


    ResponseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
