package com.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer	//开启授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	/**
	 * 配置Client的信息
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
//		配置一个客户端,既可以通过授权码类型获取令牌,也可以通过密码类型获取令牌
//		authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
//		clientId：（必须的）用来标识客户的Id。
//		secret：（需要值得信任的客户端）客户端安全码，如果有的话。
//		scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围
//		authorities：此客户端可以使用的权限（基于Spring Security authorities）
		clients
				.inMemory()
				.withClient("gatewayService") //客户端ID
				.authorizedGrantTypes("authorization_code","password","refresh_token") //客户端可以使用的授权类型,refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。(还有implicit,client_credentials)
				.scopes("all")//允许请求范围
				.secret("$2a$10$cnYeZCVpPMlOTb7Jx/9zi.hTlGKqDSw8wgFhYWla/WQAYXabjQzV2")//客户端安全码(如果你使用了密码编码,安全码也需要是编码的格式!!!)[secret]
				.redirectUris("http://localhost:8888/");//回调地址
	}

	/**
	 * 配置端点的信息
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		//配置AuthorizationService tokenService
//		endpoints
//				.redisTokenStore(new InMemoryTokenStore())
//				.accessTokenConverter(accessTokenConverter())
//				.authenticationManager(authenticationManager)
//				.reuseRefreshTokens(false);//这里配置了不允许刷新令牌
		/**
		 * 新配置
		 */
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));//这里添加了两个(JwtAccessTokenConverter)
        endpoints.tokenStore(redisTokenStore())
				.tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
	}

	/**
	 * 配置端点访问的安全限制
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//允许所有人请求令牌,已验证的客户端才能请求check_token
		security
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("permitAll()")//permitAll=全部可访问 isAuthenticated=授权的可访问(比如资源服务器可访问)
				.allowFormAuthenticationForClients();
	}

	/**
	 * RedisFactory(RedisTokenStore需要此Bean)
	 */
	@Autowired
	LettuceConnectionFactory lettuceConnectionFactory;

	/**
	 * Token存储在哪里[InMemoryTokenStore/JdbcTokenStore/RedisTokenStore],JwtTokenStore把Token里面包含的信息存储到了Token里面返回给用户
	 * 这里最好使用父类型TokenStore好一点
	 */
	@Bean
	public RedisTokenStore redisTokenStore() {
//		return new JwtTokenStore(accessTokenConverter());		//使用JWT存储(非JwtTokenStore可以使用或者不使用JwtAccessTokenConverter,但是JwtTokenStore必须使用)
		return new RedisTokenStore(lettuceConnectionFactory);	//使用Redis存储
	}


	/**
	 * Token转换器[DefaultAccessTokenConverter],JwtTokenStore需要此Converter,可配置非对称加密
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter(){
		//配置JWT转换器
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("secret");
		return converter;
	}
	/**
	 * 配置生成Token的格式,可以往生成的Token里添加自己的一些自定义信息
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}
