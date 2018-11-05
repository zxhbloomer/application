package com.main.service;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 实现一个使用请求 IP 作为限流键的KeyResolver
 */
public class RemoteAddressKeyResolver implements KeyResolver {
	public static final String BEAN_NAME = "remoteAddressKeyResolver"; //就是我在yml里面设置的那个Bean名称

	@Override
	public Mono<String> resolve(ServerWebExchange exchange) {
		return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
	}

}
