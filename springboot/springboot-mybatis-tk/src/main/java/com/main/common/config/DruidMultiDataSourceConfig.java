package com.main.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多数据源配置
 */
@Configuration
public class DruidMultiDataSourceConfig {

	/**
	 * 主数据源
	 */
	@Bean("dataSourceMaster")
	@ConfigurationProperties("spring.datasource.master")
	public DruidDataSource dataSourceMaster() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 从数据源
	 */
	@Bean("dataSourceSlave")
	@ConfigurationProperties("spring.datasource.slave")
	public DruidDataSource dataSourceSlave() {
		return DruidDataSourceBuilder.create().build();
	}

}
