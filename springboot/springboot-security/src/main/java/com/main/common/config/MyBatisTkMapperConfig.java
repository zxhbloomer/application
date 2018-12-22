package com.main.common.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("com.main.mapper") //单数据源,注意是tk包下的MapperScan
public class MyBatisTkMapperConfig {

}
