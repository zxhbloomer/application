package com.main.service.impl.security;

import com.main.entity.vo.SysUsersVo;
import com.main.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class
SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsersVo vo = userService.loadByUsername(username);
        if(vo==null) throw new UsernameNotFoundException("用户名不存在");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(vo.getRoles()));
        SecurityUserDetails securityUserDetails2 = new SecurityUserDetails()
                .setId(vo.getId())
                .setUsername(vo.getUsername())
                .setPassword(vo.getPassword())
                .setAuthorities(authorities)
                .setAccountNonExpired(vo.getAccountNonExpired())
                .setAccountNonLocked(vo.getAccountNonLocked())
                .setCredentialsNonExpired(vo.getCredentialsNonExpired())
                .setEnabled(vo.getEnabled());
        return securityUserDetails2;
    }
}
