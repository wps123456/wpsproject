package com.wps.studyplatform.httpResponse.exception.licenseException;

public class LicenseException extends RuntimeException{
    private static final long serialVersionUID=1L;
    private String code;
    private String data;
    private String msg;

    /**
     * 构造函数
     */
    public LicenseException(String code,String data,String msg){
        super(msg);
        this.code=code;
        this.data=data;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LicenseException{" +
                "code='" + code + '\'' +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
