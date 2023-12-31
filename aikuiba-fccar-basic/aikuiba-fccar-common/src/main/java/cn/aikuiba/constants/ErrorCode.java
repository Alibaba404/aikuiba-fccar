package cn.aikuiba.constants;

//错误码封装
public enum ErrorCode {

    OK(200, "成功"),
    NO_PERMISSION(403, "没有访问权限"),
    PARAM_ERROR(400, "参数校验异常"),
    SERVICE_ERROR(500, "系统异常，我们正在殴打程序员"),
    SERVICE_REQUEST_ERROR(501, "远程服务调用异常"),

    /*参数相关:1000~1999*/
    PARAM_PHONE_ERROR(1000, "手机号错误"),
    PARAM_PHONE_EXIST(1001, "账号已经存在"),
    PARAM_CODE_ERROR(1002, "授权码无效"),
    PARAM_STRING_EMPTY(1003, "字符串为空"),
    PARAM_ORIGINAL_PASSWORD_ERROR(1004, "原密码错误"),
    /*系统相关:2000~2999*/
    /*小程序相关:3000~3999*/
    APP_GET_OPENID_ERROR(3000, "微信OPENID获取失败"),
    APP_GET_ACCESSTOKEN_ERROR(3001, "微信ACCESS_TOKEN获取失败"),
    APP_GET_PHONE_ERROR(3002, "获取微信手机号失败"),
    APP_SAVE_LOGIN_ERROR(3010, "登录信息保存失败"),
    APP_DRIVER_LOGIN_ERROR(3011, "司机登录信息保存失败"),
    APP_CUSTOMER_LOGIN_ERROR(3012, "乘客登录信息保存失败"),
    APP_NOT_REGISTER(3020, "未注册,请先注册"),
    APP_NOT_LOGIN(3021, "未登录,请先登录"),
    /*用户相关:4000~4999*/
    USER_USERNAME_OR_PASSWORD_ERROR(4000, "账号或密码错误"),
    USER_ACCOUNT_DISABLED(4001, "账号已被禁用"),
    /*三方API*/
    API_MINIO_UPLOAD_ERROR(5000, "Minio文件上传失败"),
    API_MINIO_DELETE_ERROR(5001, "Minio文件删除失败"),
    API_ALI_OSS_UPLOAD_ERROR(5002, "阿里云OSS文件上传失败"),
    API_ALI_OSS_DELETE_ERROR(5003, "阿里云OSS文件删除失败"),
    LOGIN_GET_ERROR(6000, "获取登录信息失败"),
    LOGIN_ALREADY_LOGOUT(6001, "已退出登录"),
    DRIVER_AGREEMENT_SN_EXIST(7000, "合同编号已存在"),
    ;
    //错误码
    private int code;
    //错误信息
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
