package com.main.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.Resource;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.main.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryMaster") //mapper对应master数据源,注意这里引入的是TK的MapperScan,以及不要加 .* (mapper或者mapper*)
public class DataSourceMasterConfig {

	/**
	 * 注入主数据源
	 */
	@Autowired
	@Qualifier("dataSourceMaster")
	private DruidDataSource dataSourceMaster;

	/**
	 * 对SqlSessionFactory进行相关配置
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactoryMaster() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceMaster);
		//由于配置了多数据源,Mapper.xml文件会无法映射到Dao接口,需要加上此配置,不然会出现 Invalid bound statement (not found)问题
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		factoryBean.setTypeAliasesPackage("com.main.entity.*");
		factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
		return factoryBean.getObject();
	}

	/**
	 * 注入MasterFactory到Template
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplateMaster() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryMaster()); // 使用上面配置的Factory
		return template;
	}


}
