package com.wps.studyplatform.exception.base;

/**
 * @Title BaseException
 * @Description 统一异常处理
 * @auther wps
 * @Date 2020/4/1614:42
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID=1L;
    private String code;
    private String message;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseException(String message, Throwable e) {
        super(message,e);
        this.message = message;
    }


    public BaseException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    public BaseException(String message, Throwable cause, String code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, String code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    @Override
    public String toString() {
        return "异常信息{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
