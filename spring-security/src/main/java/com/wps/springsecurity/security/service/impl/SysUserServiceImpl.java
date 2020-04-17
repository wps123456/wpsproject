package com.wps.springsecurity.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wps.springsecurity.security.entity.SysUser;
import com.wps.springsecurity.security.entity.SysUserRole;
import com.wps.springsecurity.security.mapper.SysUserMapper;
import com.wps.springsecurity.security.mapper.SysUserRoleMapper;
import com.wps.springsecurity.security.service.SysUserService;
import com.wps.springsecurity.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private HttpServletRequest request;
    @Override
    public Map<String,String> findUserByLoginName(String loginName,String password) {
        SysUser sysUser=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
        String userPassword=sysUser.getPassword();
        List<String> roles=sysUserMapper.selectRolesById(sysUser.getUserId());
        Map<String,String> tokenMap=new HashMap<>();
        return tokenMap;
    }

    @Override
    public boolean addUser(SysUser sysUser) {
        String loginName=sysUser.getLoginName();
        SysUser user=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
        if(null!=user){
            return false;
        }else {
            String password="1213";
            sysUser.setPassword(password);
            sysUser.setUserId(idWorker.nextId());
            sysUserMapper.insert(sysUser);
            return true;
        }
    }

    @Override
    public boolean addRolesById(String loginName, List<Long> roles) {

        SysUser sysUser=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
        int number=0;
        for (Long role:roles){
            number=sysUserRoleMapper.insert(new SysUserRole(sysUser.getUserId(),role));
            number++;
        }
        if(number==roles.size()){
            return true;
        }else {
            return false;
        }
    }


    @Override
    public Map<String, String> deleteUserById(String loginName, HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        Map<String,String> resultMap=new HashMap<>();

        return resultMap;
    }

    @Override
    public Map<String, String> removeUserById(String loginName) {
        Map<String,String> resultMap=new HashMap<>();

        return resultMap;
    }
}
