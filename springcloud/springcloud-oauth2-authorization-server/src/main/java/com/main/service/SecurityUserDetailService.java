package com.main.service;

import com.main.domain.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class
SecurityUserDetailService implements UserDetailsService {
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserVo userVo = new SysUserVo();
        userVo.setId(123456789L);
        userVo.setUsername(username);
        userVo.setPassword("$2a$10$PSfO0GUkEakNNE.LIZCmPu.E/iS7KYzrhIzBfbM6onR0MAl3OKjsa");
        userVo.setAccountNonExpired(true);
        userVo.setAccountNonLocked(true);
        userVo.setCredentialsNonExpired(true);
        userVo.setEnabled(1);
        userVo.setRoles("USER");
        
        if(userVo==null) throw new UsernameNotFoundException("用户名不存在");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authoritie = new SimpleGrantedAuthority(userVo.getRoles());
        authorities.add(authoritie);
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(authorities, userVo.getPassword(), userVo.getUsername(), userVo.getAccountNonExpired(), userVo.getAccountNonLocked(),  userVo.getCredentialsNonExpired(), userVo.getEnabled(),userVo.getId());
        return securityUserDetails;
    }
}
