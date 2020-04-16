package com.wps.studyplatform.jwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wps.studyplatform.exception.base.BaseException;
import com.wps.studyplatform.exception.system.SysUserLoginException;
import com.wps.studyplatform.jwt.entity.SysUser;
import com.wps.studyplatform.jwt.entity.SysUserRole;
import com.wps.studyplatform.jwt.mapper.SysUserMapper;
import com.wps.studyplatform.jwt.mapper.SysUserRoleMapper;
import com.wps.studyplatform.jwt.service.SysUserService;
import com.wps.studyplatform.utils.IdWorker;
import com.wps.studyplatform.utils.JWTUtil;
import com.wps.studyplatform.utils.SysUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private SysUtils sysUtils;
    @Autowired
    private HttpServletRequest request;
    @Override
    public Map<String,String> findUserByLoginName(String loginName,String password) {
        SysUser sysUser=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
        String userPassword=sysUser.getPassword();
        List<String> roles=sysUserMapper.selectRolesById(sysUser.getUserId());
        Map<String,String> tokenMap=new HashMap<>();
        if(encoder.matches(password,userPassword)){
            //生成token
            String token=jwtUtil.createJWT(sysUser.getUserId().toString(),loginName,roles);

            tokenMap.put("flag","true");
            tokenMap.put("name",loginName);
            tokenMap.put("token","Bearer "+token);

        }else {
            tokenMap.put("flag","false");
            tokenMap.put("name",loginName);
            tokenMap.put("token","密码错误");
        }
        return tokenMap;
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
        if(null!=token){
            Claims claims=jwtUtil.parseJWT(token);
            List<String> roles=(List<String>) claims.get("roles");
            if (roles.contains("admin")){
                SysUser sysUser=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
                if (null!=sysUser){
                    sysUserMapper.deleteById(sysUser.getUserId());
                    resultMap.put("token","用户已登录");
                    resultMap.put("loginName",sysUser.getLoginName());
                    resultMap.put("status","删除成功");
                    resultMap.put("anthor","有权限");
                }else {
                    throw new BaseException("没有此用户信息");
                }

            }else {

                throw new BaseException("您没有权限删除用户");
            }

        }else {
         throw new BaseException("您没有登录，请登录！");
        }

        return resultMap;
    }

    @Override
    public Map<String, String> removeUserById(String loginName) {
       boolean flag=sysUtils.isAdmin(request);
        Map<String,String> resultMap=new HashMap<>();
       if (flag){
           SysUser sysUser=sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName,loginName));
           if (null!=sysUser){
               sysUserMapper.deleteById(sysUser.getUserId());
               resultMap.put("token","用户已登录");
               resultMap.put("loginName",sysUser.getLoginName());
               resultMap.put("status","删除成功");
               resultMap.put("anthor","有权限");
           }else {
               throw new BaseException("没有此用户信息");
           }
       }else {

           throw new BaseException("您没有权限删除用户");
       }

        return resultMap;
    }
}
