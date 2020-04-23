package com.wps.springsecurity.constant;

/**
 * @Title SecurityConstant
 * @Description
 * @auther wps
 * @Date 2020/4/2314:23
 */
public class SecurityConstant {
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
}
