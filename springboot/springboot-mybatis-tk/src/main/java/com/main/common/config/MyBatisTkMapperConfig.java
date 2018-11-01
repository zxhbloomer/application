package com.main.common.config;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.main.mapper") //注意这里引入的是TK的MapperScan,以及不要加 .* (mapper或者mapper*)
public class MyBatisTkMapperConfig {

}
