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
    private SysUser user;
    /**
     * 权限列表
     */
    private Set<String> permissions;

    public SysUser getSysUser() {
        return user;
    }

    public void setSysUser(SysUser sysUser) {
        this.user = sysUser;
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
        this.user = user;
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(null!=user){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            List<SysRole>sysRoles=user.getRoles();
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
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginName();
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
