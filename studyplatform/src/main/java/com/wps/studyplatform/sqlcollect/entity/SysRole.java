package com.wps.studyplatform.sqlcollect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Title SysRole
 * @Description
 * @auther wps
 * @Date 2020/7/220:11
 */
@TableName("sys_role")
public class SysRole {
    private static final long serialVersionUID=1L;
    private Long roleId;

    @TableField("role_name")
    @Length(min = 3,max = 20,message = "长度为3-20字符")
    @NotBlank(message = "不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$",message = "只能包含中英文数字和下划线")
    private String roleName;

    @TableField("role_key")
    @Length(min = 3,max = 20,message = "长度为3-20字符")
    @NotBlank(message = "不能为空")
    @Pattern(regexp = "^[A-Za-z0-9_]+$",message = "只能包含英文字母、数字和下划线")
    private String roleKey;

    @TableField("role_key")
    @Range(min = 0,max = 10000,message = "排序范围为0-10000")
    @NotNull(message = "不能为空")
    private Long roleSort;

    /**
     * 用户是否存在此角色，默认不存在
     */
    private transient boolean flag=false;
    /**
     * 菜单组
     */
    private transient List<Long> menuIds;
    /**
     * 第三方
     */

}
