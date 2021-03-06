package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
@EnableResourceServer	//开启资源服务器
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	RestTemplate restTemplate;



	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(new JwtTokenStore(accessTokenConverter())).stateless(true);
		//配置RemoteTokenService,用于向AuthorizationServer验证令牌
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setAccessTokenConverter(accessTokenConverter());
		RestTemplate restTemplate = restTemplate();
		//为restTemplate配置异常处理器,忽略400错误
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
			@Override
			// Ignore 400
			public void handleError(ClientHttpResponse response) throws IOException{
				if(response.getRawStatusCode() != 400){
					super.handleError(response);
				}
			}
		});
		tokenServices.setRestTemplate(restTemplate);
		tokenServices.setCheckTokenEndpointUrl("http://SERVICE-OAUTH2-AUTHORIZATION-SERVER/oauth/check_token");
		tokenServices.setClientId("client");
		tokenServices.setClientSecret("secret");
		resources.tokenServices(tokenServices).stateless(true);
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter(){
		//配置JWT转换器
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("secret");
		return converter;
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.requestMatchers().anyRequest()
				.and()
				.anonymous()
				.and()
				.authorizeRequests()
				.antMatchers("/user/**").authenticated()	// /user/**端点的访问必须要验证
				.and()
				.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}
