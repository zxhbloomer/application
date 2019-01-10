package com.main.filter;

import com.main.feign.AuthorizationServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;
import java.util.Random;

/**
 * Token全局过滤器
 */
@Slf4j
//@Component
public class AuthGatewayFilter implements GlobalFilter {

	public static final String HEADER_AUTH = "Authorization";

	@Autowired
	AuthorizationServerService authFeign;

	@Value("${eureka.instance.instance-id}")
	public String instanceId;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    	URI uri = gatewayUrl.getUri();
    	ServerHttpRequest request = exchange.getRequest();

    	//不过滤api文档
    	if(exchange.getRequest().getURI().getRawPath().endsWith("/v2/api-docs")){
			return chain.filter(exchange);
		}

    	HttpHeaders header = request.getHeaders();
    	String token = header.getFirst(HEADER_AUTH);

		Map<String,?> userMap = authFeign.checkToken(token);
		ServerHttpRequest.Builder mutate = request.mutate();

    	if(userMap != null) {
    		Object userName = userMap.get("user_name");
			Object scope = userMap.get("scope");
			Object active = userMap.get("active");
			Object exp = userMap.get("exp");
			Object authorities = userMap.get("authorities");
			Object jti = userMap.get("jti");
			Object clientId = userMap.get("client_id");

			mutate.header("x-user-id",Math.abs(new Random().nextLong()) + "");
    		mutate.header("x-user-name",userName != null ? userName.toString() : null);
			mutate.header("x-token-scope",scope != null ? scope.toString() : null);
			mutate.header("x-token-active",active != null ? active.toString() : null);
			mutate.header("x-token-exp",exp != null ? exp.toString() : null);
			mutate.header("x-token-authorities",authorities != null ? authorities.toString() : null);
			mutate.header("x-token-jti",jti != null ? jti.toString() : null);
			mutate.header("x-client-id",clientId != null ? clientId.toString() : null);
			mutate.header("x-access-token",token != null ? token.toString() : null);

        	mutate.header("x-user-serviceName",instanceId); //uri.getHost()
        	log.info("HostName:{}",uri.getHost());
    	}else {
    		//或者抛出PermissionException
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
    	}

    	ServerHttpRequest buildRequest =  mutate.build();
    	return chain.filter(exchange.mutate().request(buildRequest).build());
    }
}
