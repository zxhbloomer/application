package com.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)	//开启注解权限控制的开关(使@PreAuthorize等注解起作用)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}



}
