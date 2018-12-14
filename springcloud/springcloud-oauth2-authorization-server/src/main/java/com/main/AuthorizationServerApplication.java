package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;

@SpringBootApplication
@ComponentScan(value = {"com.main","com.config"}) //扫描一些公共的配置(还要把自己也扫进来哦)
public class AuthorizationServerApplication {

	public static void main(String[] args){
		SpringApplication.run(AuthorizationServerApplication.class,args);
	}

}
