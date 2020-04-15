package com.wps.studyplatform.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wps.studyplatform.jwt.entity.SysUser;
import com.wps.studyplatform.jwt.mapper.SysUserMapper;
import com.wps.studyplatform.jwt.service.SysUserService;
import com.wps.studyplatform.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IdWorker idWorker;
    @Override
    public boolean findUserByLoginName(String loginName,String password) {
        SysUser sysUser=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
        String userPassword=sysUser.getPassword();

        return encoder.matches(password,userPassword);
    }

    @Override
    public boolean addUser(SysUser sysUser) {
        String loginName=sysUser.getLoginName();
        SysUser user=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
        if(null!=user){
            return false;
        }else {
            String password=encoder.encode(sysUser.getPassword());
            sysUser.setPassword(password);
            sysUser.setId(idWorker.nextId());
            sysUserMapper.insert(sysUser);
            return true;
        }

    }
}
