package com.main.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 打印接口调用时间Filter,在yml配置文件中可以使用的的类(yml局部)注意这里的后缀的名称(GatewayFilterFactory)
 */
@Slf4j
public class ElapsedGatewayFilterFactory extends AbstractGatewayFilterFactory<ElapsedGatewayFilterFactory.Config> {

	private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";
	private static final String KEY = "withParams";

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList(KEY);//这里必须重写方法然后传入属性值的名称
	}

	/**
	 * 这里注意一下，一定要调用一下父类的构造器把Config类型传过去，否则会报ClassCastException
	 */
	public ElapsedGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {	//在这里传入我们之前定义的Config类
		return (exchange, chain) -> {
			exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
			return chain.filter(exchange).then(
					Mono.fromRunnable(() -> {
						Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
						if (startTime != null) {
							StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
									.append(": ")
									.append(System.currentTimeMillis() - startTime)
									.append("ms");
							if (config.isWithParams()) {
								sb.append(" params:").append(exchange.getRequest().getQueryParams());
							}
							log.info("[ONE]打印过滤器(配置在XML中) : " + sb.toString());
						}
					})
			);
		};
	}

	//自定义Config类,设置一个是否开启的布尔参数(yml通过这个来设置是否开启)
	public static class Config {
		private boolean withParams;
		public boolean isWithParams() {
			return withParams;
		}
		public void setWithParams(boolean withParams ) {
			this.withParams = withParams;
		}
	}
}