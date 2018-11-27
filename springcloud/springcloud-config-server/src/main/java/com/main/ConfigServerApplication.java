package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient	//向Eureka注册自己
@EnableConfigServer	//表明自己是一个ConfigServer
@SpringBootApplication
@ComponentScan(value = {"com.main","com.config"}) //扫描一些公共的配置(还要把自己也扫进来哦)
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
