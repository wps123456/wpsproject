package com.wps.studyplatform.sqlcollect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wps.studyplatform.sqlcollect.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Title SysRoleMapper
 * @Description
 * @auther wps
 * @Date 2020/7/219:58
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

   public IPage<SysRole> queryRoleList(Page page, Map<String,Object> params);
}
