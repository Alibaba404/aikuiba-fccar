package cn.aikuiba.exception;

import cn.aikuiba.constants.ErrorCode;

public class GlobalException extends RuntimeException {

    private int code;
    private String message;

    public GlobalException(String message) {
        this.message = message;
    }

    public GlobalException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }


    public static GlobalException me(String message) {
        return new GlobalException(message);
    }

    public static GlobalException me(int code, String message) {
        return new GlobalException(code, message);
    }

    public static GlobalException me(ErrorCode errorCode) {
        return new GlobalException(errorCode.getCode(), errorCode.getMessage());
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
