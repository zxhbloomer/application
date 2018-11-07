package com.main.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.main.manager"}, sqlSessionFactoryRef = "sqlSessionFactorySlave") //manager对应slave数据源,注意这里引入的是TK的MapperScan,以及不要加 .* (mapper或者mapper*)
public class DataSourceSlaveConfig {

	@Autowired
	@Qualifier("dataSourceSlave")
	private DruidDataSource druidDataSourceSlave;

	@Bean
	public SqlSessionFactory sqlSessionFactorySlave() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(druidDataSourceSlave);
		//由于配置了多数据源,Mapper.xml文件会无法映射到Dao接口,需要加上此配置,不然会出现 Invalid bound statement (not found)问题
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		factoryBean.setTypeAliasesPackage("com.main.entity.*");
		factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplateSlave() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactorySlave()); // 使用上面配置的Factory
		return template;
	}

}
