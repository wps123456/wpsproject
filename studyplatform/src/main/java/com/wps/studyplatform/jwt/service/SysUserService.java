package com.wps.studyplatform.jwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wps.studyplatform.jwt.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {
    Map<String,String> findUserByLoginName(String loginName, String password);

    boolean addUser(SysUser sysUser);

    boolean addRolesById(String loginName, List<Long> roles);
}
