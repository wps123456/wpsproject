package com.wps.studyplatform.jwt.controller;

import com.wps.studyplatform.jwt.constant.ApiResultConstant;
import com.wps.studyplatform.jwt.entity.SysUser;
import com.wps.studyplatform.jwt.service.SysUserService;
import com.wps.studyplatform.utils.ApiResult;
import com.wps.studyplatform.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "用户管理",tags = {"用户管理"})
@RequestMapping("/sysUser")
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder encoder;



    @ApiOperation(value = "增加用户")
    @PostMapping("/add")
    public ApiResult addUser(@RequestBody SysUser sysUser){
        boolean flag=sysUserService.addUser(sysUser);
        if(flag){
            return ApiResult.success(ApiResultConstant.OPERATION_SUCCESS,flag);
        }else {
            return ApiResult.fail(ApiResultConstant.OPERATION_FAIL,flag);
        }
    }
    @ApiOperation(value = "登录用户")
    @PostMapping("/login")
    public ApiResult login(@RequestBody Map<String,String> param){
        boolean flag=sysUserService.findUserByLoginName(param.get("loginName"),param.get("password"));
        if(flag){
            return ApiResult.success(ApiResultConstant.LOGIN_SUCCESS,flag);
        }else {
            return ApiResult.fail(ApiResultConstant.LOGIN_Fail,flag);
        }

    }
}
