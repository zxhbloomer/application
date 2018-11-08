package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AopApplication {

	public static void main(String[] args){
		SpringApplication.run(AopApplication.class, args);
	}

	@GetMapping("/aop")
	public String aop(Integer age){

		if(age < 100) throw new RuntimeException("测试异常");

		return "Success";
	}
}
