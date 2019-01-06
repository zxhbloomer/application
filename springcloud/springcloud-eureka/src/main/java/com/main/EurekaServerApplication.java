package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 如果需要配置集群Eureka,需要在Program arguments里(或者Override parameters)指定配置文件
 * 	--spring.profiles.active=server1
 */
@EnableEurekaServer //开启EurekaServer
@SpringBootApplication
public class EurekaServerApplication {
	public static void main(String[] args){
		SpringApplication.run(EurekaServerApplication.class);
	}
}
