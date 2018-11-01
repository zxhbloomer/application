package com.mian;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * 1. 自定义拦截器(输出每个请求),在springboot-swagger2项目里面使用了这个start
 */
@Slf4j
public class SimpleLoggerFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("SimpleLoggerFilter: Init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		log.info("Your Request URL {}",request.getRequestURL());
		filterChain.doFilter(servletRequest,servletResponse);
	}

	@Override
	public void destroy() {
		log.info("SimpleLoggerFilter: Destroy");
	}
}
