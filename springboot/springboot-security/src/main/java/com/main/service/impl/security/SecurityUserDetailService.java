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

import java.util.ArrayList;
import java.util.Collection;

@Component
public class
SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsersVo sysUsersVo = userService.loadByUsername(username);
        if(sysUsersVo==null) throw new UsernameNotFoundException("用户名不存在");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authoritie = new SimpleGrantedAuthority(sysUsersVo.getRoles());
        authorities.add(authoritie);
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(authorities, sysUsersVo.getPassword(), sysUsersVo.getUsername(), sysUsersVo.getAccountNonExpired(), sysUsersVo.getAccountNonLocked(),  sysUsersVo.getCredentialsNonExpired(), sysUsersVo.getEnabled(),sysUsersVo.getId());
        return securityUserDetails;
    }
}
