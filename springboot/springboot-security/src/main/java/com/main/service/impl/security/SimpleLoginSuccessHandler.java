package com.main.service.impl.security;


import com.main.common.util.NetworkUtils;
import com.main.entity.SysUser;
import com.main.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class SimpleLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserService sysUsersService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        log.info(securityUserDetails.getUsername(),"{}->登录成功");
        SysUser user = new SysUser();
        user.setId(securityUserDetails.getId());
        user.setLastLoginTime(new Date());
        user.setLoginIp(NetworkUtils.getIpAddr(httpServletRequest));
        try {
            sysUsersService.updateByIdSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpServletResponse.sendRedirect("/index");
    }
}
