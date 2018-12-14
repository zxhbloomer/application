package com.main.config;

import com.main.filter.ElapsedFilter;
import com.main.filter.ElapsedGatewayFilterFactory;
import com.main.filter.RateLimitByIpGatewayFilter;
import com.main.filter.TokenFilter;
import com.main.service.RemoteAddressKeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GatewayConfig {


	/**
	 *  4 配置RemoteAddressKeyResolver Bean 对象
	 */
	@Bean(name = RemoteAddressKeyResolver.BEAN_NAME)
	public RemoteAddressKeyResolver remoteAddressKeyResolver() {
		return new RemoteAddressKeyResolver();
	}

	/**
	 * 3 启用自定义yml中可使用的Filter类:工厂类我们有了，再把它注册到 Spring 当中
	 * 注意此方法只会使yml配置的routs起作用,Bean方式配置的访问路径是不起作用的
	 */
	@Bean
	public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new ElapsedGatewayFilterFactory();
	}

//	/**
//	 * 2 启用TokenFilter全局处理器
//	 */
//	@Bean
//	public TokenFilter tokenFilter(){
//		return new TokenFilter();
//	}


	/**
	 * 1 配置路由规则
	 */
	@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/service/user/**")	//路由映射规则
						.filters(f -> f.stripPrefix(2)	//由于我们这里加了两个前缀/client/personal/ 所以去掉两个路径
							.addResponseHeader("X-Response-Default-Foo", "Default-Bar") 	//添加响应头
								.filter(new ElapsedFilter())	//添加我们自定义的filter
								.filter(rateLimitByIpGatewayFilter())  //令牌桶算法:添加限流filter这里指定了 bucket 的容量为 10 且每60秒会补充 1 个 Token。
						)
						.uri("lb://service-client-personal")	//路由到service-client-personal服务
						.order(0)	//顺序(好像某些情况下必须为0才行访问到)
						.id("serviceRoute01")	//路由ID
				)
				.route(r -> r.path("/service/indent/**")
						.filters(f -> f.stripPrefix(2)
							.addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
						.uri("lb://service-client-order")
						.order(0)
						.id("serviceRoute02")
				)
				.build();
	}

	/**
	 * 注入根据IP限流的过滤器
	 */
	@Bean
	public RateLimitByIpGatewayFilter rateLimitByIpGatewayFilter(){
		return new RateLimitByIpGatewayFilter(10000,1, Duration.ofSeconds(60));
	}
}
