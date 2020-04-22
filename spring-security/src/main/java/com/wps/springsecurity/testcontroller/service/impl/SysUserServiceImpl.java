package com.wps.springsecurity.testcontroller.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wps.springsecurity.testcontroller.entity.SysUser;
import com.wps.springsecurity.testcontroller.mapper.SysUserMapper;
import com.wps.springsecurity.testcontroller.service.SysUserService;
import org.springframework.stereotype.Service;



@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
}
