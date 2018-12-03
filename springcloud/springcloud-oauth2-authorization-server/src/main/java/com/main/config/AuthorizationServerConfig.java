package com.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer	//开启授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final static String RESOURCE_ID = "user";

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		/**
		 * 	authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
		 	 clientId：（必须的）用来标识客户的Id。
			 secret：（需要值得信任的客户端）客户端安全码，如果有的话。
			 scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围
			 authorities：此客户端可以使用的权限（基于Spring Security authorities）。
		 */

		//配置一个客户端,既可以通过授权码类型获取令牌,也可以通过密码类型获取令牌
		clients
				.inMemory()
				.withClient("client") //客户端ID
				.authorizedGrantTypes("authorization_code","password","refresh_token") //客户端可以使用的授权类型,refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。(还有implicit,client_credentials)
				.scopes("all")//允许请求范围
				.secret("$2a$10$cnYeZCVpPMlOTb7Jx/9zi.hTlGKqDSw8wgFhYWla/WQAYXabjQzV2")//客户端安全码(如果你使用了密码编码,安全码也需要是编码的格式!!!)
				.redirectUris("http://localhost:8888/");//回调地址
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//配置AuthorizationService tokenService
		endpoints
				.tokenStore(new InMemoryTokenStore())
				.accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager)
				.reuseRefreshTokens(false);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//允许所有人请求令牌,已验证的客户端才能请求check_token
		security
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter(){
		//配置JWT转换器
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("secret");
		return converter;
	}



}
