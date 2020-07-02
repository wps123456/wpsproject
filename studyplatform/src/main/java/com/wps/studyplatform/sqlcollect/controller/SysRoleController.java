package com.wps.studyplatform.sqlcollect.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wps.studyplatform.sqlcollect.config.PageController;
import com.wps.studyplatform.sqlcollect.service.SysRoleService;
import com.wps.studyplatform.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title SysRoleController
 * @Description
 * @auther wps
 * @Date 2020/7/119:25
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends PageController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("list")
    public PageUtils list(@RequestBody Map<String, Object> params){


        return sysRoleService.queryRoleList(Query(params),params);


    }
}
