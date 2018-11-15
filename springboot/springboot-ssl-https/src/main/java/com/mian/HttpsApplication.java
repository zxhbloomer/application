package com.mian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpsApplication {

	public static void main(String[] args){
		SpringApplication.run(HttpsApplication.class,args);
	}

	@GetMapping("/")
	public String home(){
		return "Hello Welcome To Tomcat";
	}

}
