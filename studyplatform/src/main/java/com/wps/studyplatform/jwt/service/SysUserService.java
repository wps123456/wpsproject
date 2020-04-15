package com.wps.studyplatform.jwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wps.studyplatform.jwt.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    boolean findUserByLoginName(String loginName,String password);

    boolean addUser(SysUser sysUser);
}
