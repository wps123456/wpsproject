package com.wps.studyplatform.designpattern.adapterpattern.objectadapter;

/**
 * @Project: spring
 * @description: 登录返回的结果的类
 * @ModificationHistory who      when       What
 **/
public class ResultMsg {
    private  String code;
    private  String msg;
    private  Object data;

    public ResultMsg(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
