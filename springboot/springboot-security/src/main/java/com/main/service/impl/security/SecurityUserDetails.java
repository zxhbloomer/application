package com.main.service.impl.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUserDetails implements UserDetails {

    private Long id;

    private Collection<? extends GrantedAuthority> authorities;

    private String password;

    private String username;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    public SecurityUserDetails() {

    }

    public SecurityUserDetails(Collection<? extends GrantedAuthority> authorities, String password, String username, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Integer enabled, Long id) {
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;

        //注意如果enabled为false是无法登录的
        if(enabled.equals(1)){ this.enabled = true; }else{ this.enabled = false; }

        this.id = id;
    }

    // 注意:如果是开始了Security的单个账号不允许重复,你的UserDetails实现类必须要重写equals和hashCode做规则判断,
    // 我这里使用的是根据username判断这个对象是否重复,也可以根据其他的进行重写(id,email等等)
    public boolean equals(Object rhs) {
        return rhs instanceof SecurityUserDetails ? this.username.equals(((SecurityUserDetails)rhs).username) : false;
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return username;
    }
}
