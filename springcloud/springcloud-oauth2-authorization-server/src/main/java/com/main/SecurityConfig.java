package com.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//在内存中配置两个用户
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(User.withUsername("user_authorization_code").password("123456").authorities("USER").build());
		userDetailsManager.createUser(User.withUsername("user_password").password("123456").authorities("USER").build());
		auth.userDetailsService(userDetailsManager);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//授权允许oauth授权接口
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
			.requestMatchers().anyRequest()
			.and()
			.formLogin().permitAll()
			.and()
			.authorizeRequests()
			.antMatchers("/oauth/*").permitAll();
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		//配置密码解析器
		return NoOpPasswordEncoder.getInstance();
	}

}
