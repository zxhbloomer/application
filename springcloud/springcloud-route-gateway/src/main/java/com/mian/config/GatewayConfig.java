package com.mian.config;

import com.netflix.hystrix.Hystrix;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.filter.factory.HystrixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

import java.time.Duration;

@Configuration
public class GatewayConfig {

	/**
	 * 配置路由规则
	 */
	@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/service/user/**")	//路由映射规则
						.filters(f -> f.stripPrefix(2)	//由于我们这里加了两个前缀/client/personal/ 所以去掉两个路径
							.addResponseHeader("X-Response-Default-Foo", "Default-Bar"))	//添加响应头
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
}
