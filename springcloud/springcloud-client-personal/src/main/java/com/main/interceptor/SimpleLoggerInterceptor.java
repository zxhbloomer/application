package com.main.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 注意这里不要使用Component注解(被扫描到的RequestInterceptor默认为全局拦截器)
 */
@Slf4j
public class SimpleLoggerInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		log.info("GitHubFeign拦截器");
	}
}
