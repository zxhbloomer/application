package com.main.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流过滤器
 */
@Slf4j
@Component
public class RateLimitByIpGatewayFilter implements GatewayFilter,Ordered {

	/** 这里我们简单使用一个 Map 来存储 bucket，所以也决定了它只能单点使用，如果是分布式的话，可以采用 Hazelcast 或 Redis 等解决方案 **/
	private static final Map<String,Bucket> CACHE = new ConcurrentHashMap<>();

	private int capacity;
	private int refillTokens;
	private Duration refillDuration;


	/**
	 * 注入自定义类型的操作模板
	 */
	@Qualifier("redisTemplate")
	@Autowired
	private RedisTemplate redisTemplate;

	public RateLimitByIpGatewayFilter(){

	}

	public RateLimitByIpGatewayFilter(int capacity, int refillTokens, Duration refillDuration) {
		this.capacity = capacity;
		this.refillTokens = refillTokens;
		this.refillDuration = refillDuration;
	}

	private Bucket createNewBucket() {
		Refill refill = Refill.of(refillTokens,refillDuration);
		Bandwidth limit = Bandwidth.classic(capacity,refill);
		return Bucket4j.builder().addLimit(limit).build();
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

		Bucket bucket = CACHE.computeIfAbsent(ip,k -> createNewBucket());


		log.info("[ONE]IP限流处理: IP = {}",ip);
		if (bucket.tryConsume(1)) {
			return chain.filter(exchange);
		} else {
			exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
			return exchange.getResponse().setComplete();
		}
	}

	@Override
	public int getOrder() {
			return -1000;
	}

}