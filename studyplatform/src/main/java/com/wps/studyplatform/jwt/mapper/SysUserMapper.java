package com.wps.studyplatform.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wps.studyplatform.jwt.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> selectRolesById(@Param("id") Long id);
}
