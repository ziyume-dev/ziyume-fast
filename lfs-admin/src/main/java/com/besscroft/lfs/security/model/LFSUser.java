package com.besscroft.lfs.security.model;

import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.entity.AuthUser;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 10:57
 */
public class LFSUser implements UserDetails {

    private AuthUser authUser;

    private List<AuthResource> authResources;

    public LFSUser(@NonNull AuthUser authUser, @NonNull List<AuthResource> authResources) {
        this.authUser = authUser;
        this.authResources = authResources;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的角色
        return authResources.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        // 返回密码
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        // 返回用户名
        return authUser.getUsername();
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
        // 是否启用
        return authUser.getStatus().equals(1);
    }

}
