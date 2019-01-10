package com.main.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 打印接口调用时间Filter(Bean局部),这个GatewayFilter好像只能作用在Bean方式的配置上面....
 */
@Slf4j
@Configuration
public class SimpleLogsGatewayFilter implements GatewayFilter, Ordered {

	private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

	/**
	 * 现在再来看我们之前的问题：怎么来区分是 “pre” 还是 “post” 呢？其实就是chain.filter(exchange)
	 * 之前的就是 “pre” 部分，之后的也就是then里边的是 “post” 部分。
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// Pre Begin
		exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
		// Pre End
		return chain.filter(exchange).then(
				// POST Begin
				Mono.fromRunnable(() -> {
					Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
					if (startTime != null) {
						log.info("SimpleLogs : " + exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
					}
				})
				//Post End
		);
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Component
	class SimpleLogsGatewayFilterFactory extends AbstractGatewayFilterFactory {
		@Override
		public GatewayFilter apply(Object config) {
			return new SimpleLogsGatewayFilter();
		}
	}
}