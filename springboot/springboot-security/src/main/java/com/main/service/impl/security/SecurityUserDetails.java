package com.main.service.impl.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
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

    /**
     *  注意:如果配置了Security的单个账号不允许重复登录,你的UserDetails实现类必须要重写equals/hashCode/toString
     *  做规则判断,我这里使用的是根据id判断这个对象是否重复,也可以根据其他的进行重写(username,email等等)
     */
    @Override
    public String toString() {
        return this.id + "";
    }

    public boolean equals(Object rhs) {
        return rhs instanceof SecurityUserDetails ? this.id.equals(((SecurityUserDetails)rhs).id) : false;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public SecurityUserDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public SecurityUserDetails setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public SecurityUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public SecurityUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public SecurityUserDetails setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public SecurityUserDetails setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public SecurityUserDetails setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public SecurityUserDetails setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
