package com.wps.springsecurity.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wps.springsecurity.security.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> selectRolesById(@Param("id") Long id);
}
