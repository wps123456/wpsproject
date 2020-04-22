package com.wps.springsecurity.testcontroller.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wps.springsecurity.testcontroller.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> selectRolesById(@Param("id") Long id);
    SysUser selectUserByName(@Param("userName") String userName);
}
