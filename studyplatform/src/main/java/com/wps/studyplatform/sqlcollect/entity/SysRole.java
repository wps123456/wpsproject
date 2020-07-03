package com.wps.studyplatform.sqlcollect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysRole  extends BaseEntity{
    private static final long serialVersionUID=1L;
    /**
     * 角色ID
     */
    @TableId(value = "role_id")
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

    /**
     * 角色排序
     */
    @TableField("role_sort")
    @Range(min = 0,max = 10000,message = "排序范围为0-10000")
    @NotNull(message = "不能为空")
    private Long roleSort;

    /**
     * 数据范围（1所有数据权限，2：自定数据权限）
     */
    @TableField("data_scope")
    private String dataScope;
    /**
     * 角色状态（0正常。1停用）
     */
    @TableField("status")
    private String status;

    /**
     * 删除标志（0代表存在，2代表删除）
     */

    @TableField("del_flag")
    private String delFlag;
    /**
     * 默认角色 0否1是
     */
    @TableField("role_default")
    private String roleDefault;
    /**
     * 角色对应类型
     * 0超级管理员
     * 1用户管理员
     * 2普通话管理yuan
     * 3应用初始角色
     */
    @TableField("role_type")
    @NotBlank(message = "角色类型不能为空")
    private String roleType;

    /**
     * 用户是否存在此角色，默认不存在
     */
    private transient boolean flag=false;
    /**
     * 菜单组
     */
    private transient List<Long> menuIds;
    /**
     * 第三方菜单节点id
     */

     private transient List<String> optNodeIds;
    /**
     * 第三方数据权限节点id
     */
    private transient List<String> optDataAuthIds;
    /**
     * 部门组（数据权限）
     */
    private transient List<Long> deptIds;
    /**
     * 应用id列表
     */
    private transient List<ReqAppRoleDetail> appDetails;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Long getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Long roleSort) {
        this.roleSort = roleSort;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRoleDefault() {
        return roleDefault;
    }

    public void setRoleDefault(String roleDefault) {
        this.roleDefault = roleDefault;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public List<String> getOptNodeIds() {
        return optNodeIds;
    }

    public void setOptNodeIds(List<String> optNodeIds) {
        this.optNodeIds = optNodeIds;
    }

    public List<String> getOptDataAuthIds() {
        return optDataAuthIds;
    }

    public void setOptDataAuthIds(List<String> optDataAuthIds) {
        this.optDataAuthIds = optDataAuthIds;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public List<ReqAppRoleDetail> getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(List<ReqAppRoleDetail> appDetails) {
        this.appDetails = appDetails;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", roleSort=" + roleSort +
                ", dataScope='" + dataScope + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", roleDefault='" + roleDefault + '\'' +
                ", roleType='" + roleType + '\'' +
                ", flag=" + flag +
                ", menuIds=" + menuIds +
                ", optNodeIds=" + optNodeIds +
                ", optDataAuthIds=" + optDataAuthIds +
                ", deptIds=" + deptIds +
                ", appDetails=" + appDetails +
                '}';
    }
}
