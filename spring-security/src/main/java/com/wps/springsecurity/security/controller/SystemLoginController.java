package com.wps.springsecurity.security.controller;

import com.wps.springsecurity.security.service.SysLoginService;
import com.wps.springsecurity.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title SystemLoginController
 * @Description
 * @auther wps
 * @Date 2020/4/2215:46
 */
@RestController
@Api(value = "登录管理",tags = {"登录管理"})
public class SystemLoginController {
    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ApiResult login(@RequestBody Map<String,String>param){
        String token=sysLoginService.login(param.get("userName"),param.get("password"));
        return ApiResult.success(token);
    }
    @PostMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "拥有admin角色的访问")
    public ApiResult admin(@RequestBody Map<String,String> param){

        return ApiResult.success("admin访问权限");
    }
    @PostMapping("/remove")
    @PreAuthorize("hasRole('system')")
    @ApiOperation(value = "拥有system角色的访问")
    public ApiResult remove(@RequestBody Map<String,String> param){

        return ApiResult.success("system有访问权限");
    }
}
