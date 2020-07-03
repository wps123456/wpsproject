package com.wps.studyplatform.sqlcollect.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wps.studyplatform.sqlcollect.entity.SysRole;
import com.wps.studyplatform.sqlcollect.mapper.SysRoleMapper;
import com.wps.studyplatform.sqlcollect.service.SysRoleService;
import com.wps.studyplatform.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title SysRoleServiceImpl
 * @Description
 * @auther wps
 * @Date 2020/7/29:17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public PageUtils queryRoleList(Page page, Map<String, Object> params) {
        baseMapper.queryRoleList(page,params);

        List<Long> wanIds=new ArrayList<>();
        Map<String ,Object> params1=new HashMap<>();
        params1.put("wanIds",wanIds);
        params1.put("warnStatus",1);

        sysRoleMapper.batchUpdate(params1);

        return null;
    }
}
