package com.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * EurekaServer的安全配置
 */
@Configuration
@EnableWebSecurity	//开启Security
public class WebSecurityConfigurator extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/
		// 这种方式登录,所以必须是httpBasic,如果是form方式,不能使用url格式登录
		http
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
			.authorizeRequests()
			.anyRequest()
			.authenticated().and()
			.httpBasic();//开启HttpBasic
	}

}
