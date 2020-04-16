package com.wps.studyplatform.jwt.controller;

import com.wps.studyplatform.jwt.constant.ApiResultConstant;
import com.wps.studyplatform.jwt.entity.SysUser;
import com.wps.studyplatform.jwt.service.SysUserService;
import com.wps.studyplatform.model.RolesModel;
import com.wps.studyplatform.utils.ApiResult;
import com.wps.studyplatform.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户管理",tags = {"用户管理"})
@RequestMapping("/sysUser")
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private HttpServletRequest request;



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
    @ApiOperation(value = "绑定角色")
    @PostMapping("/addRoles")
    public ApiResult addRoles(@RequestBody RolesModel rolesModel){
        String loginName=rolesModel.getLoginName();
        List<Long> roles= rolesModel.getRoles();
        boolean flag=sysUserService.addRolesById(loginName,roles);
        return ApiResult.success("sdf",flag);


    }

    @ApiOperation(value = "登录用户")
    @PostMapping("/login")
    public ApiResult login(@RequestBody SysUser sysUser){
        Map<String,String> resultMap=sysUserService.findUserByLoginName(sysUser.getLoginName(),sysUser.getPassword());
        if(resultMap.get("flag").equals("true")){
            return ApiResult.success(ApiResultConstant.LOGIN_SUCCESS,resultMap);
        }else {
            return ApiResult.fail(ApiResultConstant.LOGIN_Fail,resultMap);
        }

    }

    @ApiOperation(value = "删除用户")
    @GetMapping("/deleteUser/{loginName}")
    public ApiResult deleteUser(@PathVariable("loginName") String loginName){
        Map<String,String> map=sysUserService.deleteUserById(loginName,request);

        if(map.get("status").equals("删除成功")){
            return ApiResult.success(map);
        }else {
            return ApiResult.fail(map);
        }
    }
    @ApiOperation(value = "删除用户(拦截)")
    @GetMapping("/removeUser/{loginName}")
    public ApiResult removeUser(@PathVariable("loginName") String loginName){
        Map<String,String> map=sysUserService.removeUserById(loginName);

        if(map.get("status").equals("删除成功")){
            return ApiResult.success(map);
        }else {
            return ApiResult.fail(map);
        }
    }


}
