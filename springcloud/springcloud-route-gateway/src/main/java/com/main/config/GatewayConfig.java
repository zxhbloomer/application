package com.main.config;

import com.main.filter.SimpleLogsGatewayFilter;
import com.main.filter.ElapsedGatewayFilterFactory;
import com.main.filter.SwaggerHeaderGatewayFilter;
import com.main.filter.RateLimitByIpGatewayFilter;
import com.main.service.RemoteAddressKeyResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;
import static com.main.config.GatewayRouteEnum.*;

@Configuration
public class GatewayConfig {

	/**
	 * 配置RemoteAddressKeyResolver Bean 对象
	 */
	@Bean(name = RemoteAddressKeyResolver.BEAN_NAME)
	public RemoteAddressKeyResolver remoteAddressKeyResolver() {
		return new RemoteAddressKeyResolver();
	}

	/**
	 * 启用自定义yml中可使用的Filter类:工厂类我们有了，再把它注册到 Spring 当中
	 * 注意此方法只会使yml配置的routs起作用,Bean方式配置的访问路径是不起作用的
	 */
	@Bean
	public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new ElapsedGatewayFilterFactory();
	}

	@Autowired
	private RouteLocatorBuilder builder;

	/**
	 * 配置路由规则
	 */
	@Bean
	public RouteLocator customerRouteLocator() {
		RouteLocator routeLocator = builder.routes()
				.route(r -> r.path(USER.getPath())	//路由映射规则
						.filters(f -> f
                                .filter(new SwaggerHeaderGatewayFilter().apply(new Object()))
                                .stripPrefix(2)	//由于我们这里加了两个前缀/client/personal/ 所以去掉两个路径
								.addResponseHeader("X-Response-Default-Foo", "Default-Bar") 	//添加响应头
                                .filter(new SimpleLogsGatewayFilter())	//添加我们自定义的filter
								.filter(rateLimitByIpGatewayFilter())
						)
						.uri("lb://service-client-personal")	//路由到service-client-personal服务
						.order(0)	//顺序(好像某些情况下必须为0才行访问到)
						.id(USER.getId())	//路由ID
				)
				.route(r -> r.path(INDENT.getPath())
						.filters(f -> f
                                .filter(new SwaggerHeaderGatewayFilter().apply(new Object()))
                                .stripPrefix(2)
								.addResponseHeader("X-Response-Default-Foo", "Default-Bar")

                        )
						.uri("lb://service-client-order")
						.order(0)
						.id(INDENT.getId())
				)
				.build();
		return routeLocator;
	}

	/**
	 * 注入根据IP限流的过滤器
	 */
	@Bean
	public RateLimitByIpGatewayFilter rateLimitByIpGatewayFilter(){
		//令牌桶算法:添加限流filter这里指定了 bucket 的容量为 10 且每60秒会补充 1 个 Token。
		return new RateLimitByIpGatewayFilter(10,1, Duration.ofSeconds(60));
	}
}
