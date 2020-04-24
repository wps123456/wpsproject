package com.wps.springsecurity.exception.SystemLoginException;

import com.wps.springsecurity.exception.base.BaseException;

/**
 * @Title UserPasswordNotMatchException
 * @Description
 * @auther wps
 * @Date 2020/4/2410:32
 */
public class UserPasswordNotMatchException extends BaseException {
    public UserPasswordNotMatchException(String message) {
        super(message);
    }
}
