package com.wps.studyplatform.exception.system;

import com.wps.studyplatform.exception.base.BaseException;

/**
 * @Title SysUserLoginException
 * @Description 用户登录异常
 * @auther wps
 * @Date 2020/4/1614:43
 */
public class SysUserLoginException extends BaseException {
    public SysUserLoginException(String message){
        super(message);
    }
    public SysUserLoginException(String code,String message){
        super(code,message);
    }
}
