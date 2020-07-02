package com.wps.studyplatform.sqlcollect.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wps.studyplatform.sqlcollect.entity.SysRole;
import com.wps.studyplatform.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title SysRoleService
 * @Description
 * @auther wps
 * @Date 2020/7/29:17
 */

public interface SysRoleService extends IService<SysRole> {
    public PageUtils queryRoleList(Page page, Map<String,Object> params);
}
