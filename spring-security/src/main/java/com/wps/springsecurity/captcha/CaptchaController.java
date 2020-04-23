package com.wps.springsecurity.captcha;

import com.wps.springsecurity.constant.SecurityConstant;
import com.wps.springsecurity.utils.ApiResult;
import com.wps.springsecurity.utils.Base64;
import com.wps.springsecurity.utils.IdUtils;
import com.wps.springsecurity.redis.RedisCache;
import com.wps.springsecurity.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title CaptchaController
 * @Description
 * @auther wps
 * @Date 2020/4/2314:05
 */
@RestController
@RequestMapping("/system")
@Api(value = "验证码",tags = {"验证码"})
public class CaptchaController {

    @Autowired
    private RedisCache redisCache;


    @GetMapping("/captchaImage")
    @ApiOperation(value = "获取验证码")
    public ApiResult getCode() throws IOException {

        //获取随机4位字符串
        String verifyCode= VerifyCodeUtils.generateVerifyCode(4);
        //获取验证码的唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = SecurityConstant.CAPTCHA_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyKey,verifyCode,SecurityConstant.CAPTCHA_EXPIRATION,TimeUnit.MINUTES);
        //生成图片
        int w=111,h=36;
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w,h,stream,verifyCode);


        try {
            Map<String,String> result=new HashMap<>();
            result.put("code",verifyCode);
            result.put("uuid",uuid);
            result.put("img", Base64.encode(stream.toByteArray()));
            return ApiResult.success("返回验证码成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResult.fail("生成验证码失败");
        }finally
        {
            stream.close();
        }

    }



}
