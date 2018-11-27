package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(value = {"com.main","com.config"}) //扫描一些公共的配置(还要把自己也扫进来哦)
@SpringBootApplication
public class ResourceServerApplication {

	public static void main(String[] args){
		SpringApplication.run(ResourceServerApplication.class,args);
	}

}
