package com.mian;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 3. 根据条件实例化Bean
 */
@Configuration
@ConditionalOnClass({SimpleLoggerFilterRegistrationBean.class,SimpleLoggerFilter.class}) //某个class位于类路径上，才会实例化一个Bean
public class SimpleLoggerFilterAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(SimpleLoggerFilterRegistrationBean.class) //当不存在某个对象的时候才实例此Bean
	public SimpleLoggerFilterRegistrationBean simpleLoggerFilterRegistrationBean(){
		return new SimpleLoggerFilterRegistrationBean();
	}

}
