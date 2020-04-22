package com.wps.springsecurity.security.service;

/**
 * @Title SysPermissionService
 * @Description
 * @auther wps
 * @Date 2020/4/2215:25
 */

import com.wps.springsecurity.testcontroller.entity.SysUser;
import com.wps.springsecurity.testcontroller.mapper.SysUserMapper;
import com.wps.springsecurity.testcontroller.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@Component
public class SysPermissionService
{
    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user)
    {

        List<String> sysRoles=sysUserMapper.selectRolesById(user.getUserId());
        Set<String> roles= new HashSet<>(sysRoles);
        return roles;
    }

}
