package com.main;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * 2. 注册一个Bean
 */
public class SimpleLoggerFilterRegistrationBean extends FilterRegistrationBean<SimpleLoggerFilter> {

	public SimpleLoggerFilterRegistrationBean() {
		super();
		this.setFilter(new SimpleLoggerFilter());	//注册自己定义的SimpleLoggerFilter
		this.addUrlPatterns("/*");					//匹配拦截规则
		this.setOrder(1);							//设置优先级
	}
}
