package com.wps.springsecurity.security;

import com.wps.springsecurity.testcontroller.entity.SysRole;
import com.wps.springsecurity.testcontroller.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Title LoginUser
 * @Description
 * @auther wps
 * @Date 2020/4/2214:55
 */
public class LoginUser  implements UserDetails {

    /**
     * redis中存放LoginUser对应的userKey值
     */
    private String UUID;

    private SysUser sysUser;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * 权限列表

     */
    private Set<String> permissions;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public LoginUser() {
    }

    public LoginUser(SysUser user) {
        this.sysUser = user;
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.sysUser = user;
        this.permissions = permissions;
    }

    /**
     * 使用自定义注解实现权限的判断时@PreAuthorize("@ss.hasRole('admin')"),此处的返回值设置为null
     * 返回grantedAuthorities时，是使用spring security自身的注解@PreAuthorize("hasRole('admin')")，
     *     其中user的密码和名称需要返回spring security内部的UserDetails中
     *     @Override
     *     public String getPassword() {
     *         return user.getPassword();
     *     }
     *     @Override
     *     public String getUsername() {
     *         return user.getLoginName();
     *     }
     */


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(null!=sysUser){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            List<SysRole>sysRoles=sysUser.getRoles();
            List<String> roles=sysRoles.stream().map(sysRole -> sysRole.getRoleKey()).collect(Collectors.toList());
            for(String role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }

            return grantedAuthorities;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
