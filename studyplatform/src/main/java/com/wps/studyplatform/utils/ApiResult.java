package com.wps.studyplatform.utils;

import com.wps.studyplatform.jwt.constant.ApiResultConstant;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID=1L;
    private String code;
    private String message;
    private T data;

    /**
     * 成功
     */
    public static <T> ApiResult<T> success(String message,T data){
        return new ApiResult<T>(ApiResultConstant.SUCCESS_CODE,message,data);
    }

    public  static <T>ApiResult<T> success(T data){
        return new ApiResult<T>(ApiResultConstant.SUCCESS_CODE,ApiResultConstant.OPERATION_SUCCESS,data);
    }
    /**
     * Fail
     */
    public static <T> ApiResult<T> fail(String message,T data){
        return new ApiResult<T>(ApiResultConstant.Fail_CODE,message,data);
    }
    public  static <T>ApiResult<T> fail(T data){
        return new ApiResult<T>(ApiResultConstant.Fail_CODE,ApiResultConstant.OPERATION_FAIL,data);
    }

    public ApiResult() {
    }

    public ApiResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static <T>ApiResult<T> success() {
        return new ApiResult<T>(ApiResultConstant.SUCCESS_CODE,"message",null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
