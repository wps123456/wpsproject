package com.wps.springsecurity.exception;

import com.wps.springsecurity.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title GlobalExceptionHandler
 * @Description 全局捕获异常
 *                做法：指定一个需要捕获的异常类，当程序中弹出这个异常时，则进行捕获
 * @auther wps
 * @Date 2020/4/2219:44
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return ApiResult.fail("403","没有访问权限，请联系管理员");
    }
}
